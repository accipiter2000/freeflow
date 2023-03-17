package com.opendynamic.ff.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfHelper;
import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.FlowDef;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.Proc;
import com.opendynamic.ff.vo.ProcDef;

import de.odysseus.el.util.SimpleContext;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class GatewayNodeHandler implements NodeHandler {
    @Autowired
    private FfService ffService;
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfHelper ffHelper;

    @Override
    public String getNodeType() {
        return FfService.NODE_TYPE_GATEWAY;
    }

    @Override
    public FfResult insertNodeByNodeDef(NodeDef nodeDef, Node branchNode, String previousNodeIds, String triggerOperation, String executor) {
        FfResult ffResult = new FfResult();// 返回值

        Node gatewayNode = ffService.createChildNodeQuery().setNodeId(branchNode.getNodeId()).setNodeCode(nodeDef.getNodeCode()).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE)).queryForObject();
        if (gatewayNode == null) {// 新增节点
            String gatewayNodeId = OdUtils.getUuid();
            ffNodeService.insertNode(gatewayNodeId, branchNode.getNodeId(), branchNode.getProcId(), null, null, branchNode.getSubProcDefId(), branchNode.getAdjustSubProcDefId(), FfService.NODE_TYPE_GATEWAY, nodeDef.getNodeCode(), nodeDef.getNodeName(), nodeDef.getParentNodeCode(), nodeDef.getAssignee(), nodeDef.getCandidate(), nodeDef.getAction(), nodeDef.getDueDate(), nodeDef.getCompleteExpression(), nodeDef.getCompleteReturn(), nodeDef.getExclusive(), nodeDef.getForwardable(), nodeDef.getAutoCompleteSameAssignee(), nodeDef.getAutoCompleteEmptyAssignee(), nodeDef.getInform(), nodeDef.getPriority(), null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
            gatewayNode = ffService.loadNode(gatewayNodeId);
            ffResult.addCreateNode(gatewayNode);
        }

        // 更新前节点IDs
        List<String> previousNodeIdList = new ArrayList<>();
        if (StringUtils.isNotEmpty(gatewayNode.getPreviousNodeIds())) {
            previousNodeIdList.addAll(Arrays.asList(gatewayNode.getPreviousNodeIds().split(",")));
        }
        if (StringUtils.isNotEmpty(previousNodeIds)) {
            previousNodeIdList.addAll(Arrays.asList(previousNodeIds.split(",")));
        }
        ffNodeService.updateNodePreviousNodeIds(gatewayNode.getNodeId(), StringUtils.join(previousNodeIdList, ","));

        gatewayNode.setPreviousNodeIds(StringUtils.join(previousNodeIdList, ","));
        ffResult.addAll(completeNode(gatewayNode, null, FfService.OPERATION_INSERT, executor));

        return ffResult;
    }

    @Override
    public FfResult completeNode(Node node, String previousNodeIds, String triggerOperation, String executor) {
        FfResult ffResult = new FfResult();// 返回值

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {// 如已经完成，直接返回
            return ffResult;
        }

        /// 完成判断
        ProcDef procDef = ffService.getNodeProcDef(node); // 获取当前节点所属流程定义
        NodeDef nodeDef = procDef.getNodeDef((node.getNodeCode()));// 获取当前节点所属节点定义
        List<? extends FlowDef> incomingFlowDefList = nodeDef.getIncomingFlowDefList();
        double TOTAL = incomingFlowDefList.size();
        double COMPLETE = 0;
        if (StringUtils.isNotEmpty(node.getPreviousNodeIds())) {
            COMPLETE = node.getPreviousNodeIds().split(",").length;
        }
        Map<String, Object> nodeVarMap = ffService.createNodeVarQuery().setNodeId(node.getNodeId()).setRecursive(true).queryForMap();// 获取节点变量
        Proc proc = ffService.loadProc(node.getProcId());
        nodeVarMap.put("proc", proc);
        nodeVarMap.put("branch", ffService.loadNode(node.getParentNodeId()));
        nodeVarMap.put("node", node);
        nodeVarMap.put("TOTAL", TOTAL);
        nodeVarMap.put("COMPLETE", COMPLETE);
        if (!nodeVarMap.get("TOTAL").equals(0)) {
            ExpressionFactory expressionFactory = ffService.getExpressionFactory();
            SimpleContext simpleContext = ffService.getSimpleContext(nodeVarMap);
            ValueExpression expression = expressionFactory.createValueExpression(simpleContext, node.getCompleteExpression(), Boolean.class);// 判断是否满足节点完成表达式
            if (Boolean.FALSE.equals(expression.getValue(simpleContext))) {
                return ffResult;
            }
        }

        // 完成节点
        String nodeEndUserName = ffHelper.getUserName(executor);
        Date nodeEndDate = new Date();
        ffNodeService.updateNodeStatus(node.getNodeId(), executor, nodeEndUserName, nodeEndDate, FfService.NODE_STATUS_COMPLETE);// 完成节点
        node.setNodeEndUser(executor);
        node.setNodeEndUserName(nodeEndUserName);
        node.setNodeEndDate(nodeEndDate);
        node.setNodeStatus(FfService.NODE_STATUS_COMPLETE);
        ffResult.addCompleteNode(node);

        List<? extends NodeDef> nextNodeDefList = nodeDef.getNextNodeDefList(nodeVarMap);// 查找下一个节点定义
        if (nextNodeDefList.size() > 0) {// 有后续节点定义，新增后续节点。
            for (NodeDef nextNodeDef : nextNodeDefList) {
                ffResult.addAll(ffService.getNodeHandler(nextNodeDef.getNodeType()).insertNodeByNodeDef(nextNodeDef, ffService.loadNode(node.getParentNodeId()), node.getNodeId(), FfService.OPERATION_COMPLETE, executor));
            }
        }
        else {// 无后续节点定义。递归完成上级节点。
            Node parentNode = ffService.loadNode(node.getParentNodeId());
            ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).completeNode(parentNode, node.getNodeId(), triggerOperation, executor));
        }

        return ffResult;
    }

    @Override
    public FfResult rejectNode(Node node, String triggerOperation, String executor) {
        return new FfResult();
    }

    @Override
    public FfResult activateNode(Node node, String previousNodeIds, String triggerOperation, String executor) {
        FfResult ffResult = new FfResult();// 返回值

        if (!node.getNodeStatus().equals(FfService.NODE_STATUS_ACTIVE)) {
            ffNodeService.updateNodeStatus(node.getNodeId(), FfService.NODE_STATUS_ACTIVE);// 激活当前节点
            node.setNodeStatus(FfService.NODE_STATUS_ACTIVE);
            ffResult.addActivateNode(node);

            previousNodeIds = node.getPreviousNodeIds();
            if (StringUtils.isNotEmpty(previousNodeIds)) {
                ffNodeService.updateNodePreviousNodeIds(node.getNodeId(), null);
                List<Node> previousNodeList = ffService.selectNodeByIdList(Arrays.asList(previousNodeIds.split(",")));
                for (Node _previousNode : previousNodeList) {
                    ffResult.addAll(ffService.getNodeHandler(_previousNode.getNodeType()).activateNode(_previousNode, null, triggerOperation, executor));
                }
            }
        }

        return ffResult;
    }
}