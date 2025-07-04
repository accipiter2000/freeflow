package com.opendynamic.ff.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfHelper;
import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.CandidateList;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.FlowDef;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.NodeHandlerOperation;
import com.opendynamic.ff.vo.OperationContext;
import com.opendynamic.ff.vo.ProcDef;

import de.odysseus.el.ExpressionFactoryImpl;
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
    public FfResult insertNodeByNodeDef(NodeDef nodeDef, Node branchNode, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        Node node = ffService.createChildNodeQuery().setNodeId(branchNode.getNodeId()).setNodeCode(nodeDef.getNodeCode()).setNodeStatus(FfService.NODE_STATUS_ACTIVE).queryForObject();
        if (node == null) {// 新增节点
            String nodeId = OdUtils.getUuid();
            ffNodeService.insertNode(nodeId, branchNode.getNodeId(), branchNode.getProcId(), null, null, branchNode.getSubProcDefId(), branchNode.getAdjustSubProcDefId(), FfService.NODE_TYPE_GATEWAY, nodeDef.getNodeCode(), nodeDef.getNodeName(), nodeDef.getParentNodeCode(), nodeDef.getCandidateAssignee(), nodeDef.getCompleteExpression(), nodeDef.getCompleteReturn(), nodeDef.getExclusive(), nodeDef.getWaitingForCompleteNode(), nodeDef.getAutoCompleteSameAssignee(), nodeDef.getAutoCompleteEmptyAssignee(), nodeDef.getInform(), nodeDef.getAssignee(), nodeDef.getAction(), nodeDef.getDueDate(), nodeDef.getClaim(), nodeDef.getForwardable(), nodeDef.getPriority(), null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
            node = ffService.loadNode(nodeId);
            ffResult.addCreateNode(node);
            operationContext.addNodeHandlerOperation(new NodeHandlerOperation(FfService.NODE_HANDLER_OPERATION_INSERT, node));
        }

        // 更新前节点IDs
        List<String> previousNodeIdList = new ArrayList<>();
        if (StringUtils.isNotEmpty(node.getPreviousNodeIds())) {
            previousNodeIdList.addAll(Arrays.asList(node.getPreviousNodeIds().split(",")));
        }
        if (StringUtils.isNotEmpty(previousNodeIds)) {
            previousNodeIdList.addAll(Arrays.asList(previousNodeIds.split(",")));
        }
        ffNodeService.updateNodePreviousNodeIds(node.getNodeId(), StringUtils.join(previousNodeIdList, ","));

        node.setPreviousNodeIds(StringUtils.join(previousNodeIdList, ","));
        ffResult.addAll(completeNode(node, null, candidateList, operationContext));

        return ffResult;
    }

    @Override
    public FfResult appendCandidate(Node node, CandidateList candidateList, OperationContext operationContext) {
        operationContext.addNodeHandlerOperation(new NodeHandlerOperation(FfService.NODE_HANDLER_OPERATION_APPEND, node));
        return new FfResult();
    }

    @Override
    public FfResult completeNode(Node node, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值
        operationContext.addNodeHandlerOperation(new NodeHandlerOperation(FfService.NODE_HANDLER_OPERATION_COMPLETE, node));

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
        // 设置JUEL解析环境
        if (operationContext.getCurrentProc() == null || !operationContext.getCurrentProc().getProcId().equals(node.getProcId())) {
            operationContext.setCurrentProc(ffService.loadProc(node.getProcId()));
        }
        if (operationContext.getCurrentBranchNode() == null || !operationContext.getCurrentBranchNode().getNodeId().equals(node.getParentNodeId())) {
            operationContext.setCurrentBranchNode(ffService.loadNode(node.getParentNodeId()));
        }
        if (operationContext.getCurrentNode() == null || !operationContext.getCurrentNode().getNodeId().equals(node.getNodeId())) {
            operationContext.setCurrentNode(node);
        }
        if (operationContext.getCurrentNodeVarMapNode() == null || !operationContext.getCurrentNodeVarMapNode().getNodeId().equals(node.getNodeId())) {
            operationContext.setCurrentNodeVarMapNode(node);
            operationContext.setCurrentNodeVarMap(ffService.createNodeVarQuery().setNodeId(node.getNodeId()).setRecursive(true).queryForMap());// 获取节点变量
        }
        HashMap<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.putAll(ffService.getInternalServiceMap());
        nodeVarMap.putAll(ffService.getExternalServiceMap());
        nodeVarMap.put("proc", operationContext.getCurrentProc());
        nodeVarMap.put("branch", operationContext.getCurrentBranchNode());
        nodeVarMap.put("node", operationContext.getCurrentNode());
        nodeVarMap.putAll(operationContext.getCurrentNodeVarMap());
        nodeVarMap.put("TOTAL", TOTAL);
        nodeVarMap.put("COMPLETE", COMPLETE);
        if (!nodeVarMap.get("TOTAL").equals(0)) {
            ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
            SimpleContext simpleContext = new SimpleContext();
            for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
                simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
            }
            simpleContext.setVariable("operationContext", expressionFactory.createValueExpression(operationContext, Object.class));
            // JUEL解析
            ValueExpression expression = expressionFactory.createValueExpression(simpleContext, node.getCompleteExpression(), Boolean.class);// 判断是否满足节点完成表达式
            if (Boolean.FALSE.equals(expression.getValue(simpleContext))) {
                return ffResult;
            }
        }

        // 完成节点
        // 合并前节点的候选
        CandidateList fullCandidateList = new CandidateList();
        fullCandidateList.addAll(candidateList);
        for (Node previousNode : ffService.createNodeQuery().setNodeIdList(Arrays.asList(node.getPreviousNodeIds().split(","))).queryForObjectList()) {
            if (StringUtils.isNotEmpty(previousNode.getNextCandidate())) {
                fullCandidateList.addAll(new Gson().fromJson(previousNode.getNextCandidate(), CandidateList.class));
            }
        }
        String nodeEndUserName = ffHelper.getUserName(operationContext.getCurrentExecutor());
        Date nodeEndDate = new Date();
        ffNodeService.updateNodeStatus(node.getNodeId(), operationContext.getCurrentExecutor(), nodeEndUserName, nodeEndDate, fullCandidateList.toJson(), FfService.NODE_STATUS_COMPLETE);// 完成节点
        node.setNodeEndUser(operationContext.getCurrentExecutor());
        node.setNodeEndUserName(nodeEndUserName);
        node.setNodeEndDate(nodeEndDate);
        node.setNextCandidate(fullCandidateList.toJson());
        node.setNodeStatus(FfService.NODE_STATUS_COMPLETE);
        ffResult.addCompleteNode(node);

        List<? extends NodeDef> nextNodeDefList = nodeDef.getNextNodeDefList(nodeVarMap);// 查找下一个节点定义
        if (!nextNodeDefList.isEmpty()) {// 有后续节点定义，新增后续节点。
            for (NodeDef nextNodeDef : nextNodeDefList) {
                ffResult.addAll(ffService.getNodeHandler(nextNodeDef.getNodeType()).insertNodeByNodeDef(nextNodeDef, ffService.loadNode(node.getParentNodeId()), node.getNodeId(), fullCandidateList, operationContext));
            }
        }
        else {// 无后续节点定义。递归完成上级节点。
            Node parentNode = ffService.loadNode(node.getParentNodeId());
            ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).completeNode(parentNode, node.getNodeId(), fullCandidateList, operationContext));
        }

        return ffResult;
    }

    @Override
    public FfResult rejectNode(Node node, CandidateList candidateList, OperationContext operationContext) {
        operationContext.addNodeHandlerOperation(new NodeHandlerOperation(FfService.NODE_HANDLER_OPERATION_REJECT, node));
        return new FfResult();
    }

    @Override
    public FfResult activateNode(Node node, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值
        operationContext.addNodeHandlerOperation(new NodeHandlerOperation(FfService.NODE_HANDLER_OPERATION_ACTIVATE, node));

        if (!node.getNodeStatus().equals(FfService.NODE_STATUS_ACTIVE)) {
            ffNodeService.updateNodeStatus(node.getNodeId(), FfService.NODE_STATUS_ACTIVE);// 激活当前节点
            node.setNodeStatus(FfService.NODE_STATUS_ACTIVE);
            ffResult.addActivateNode(node);

            previousNodeIds = node.getPreviousNodeIds();
            if (StringUtils.isNotEmpty(previousNodeIds)) {
                ffNodeService.updateNodePreviousNodeIds(node.getNodeId(), null);
                List<Node> previousNodeList = ffService.createNodeQuery().setNodeIdList(Arrays.asList(previousNodeIds.split(","))).queryForObjectList();
                for (Node _previousNode : previousNodeList) {
                    ffResult.addAll(ffService.getNodeHandler(_previousNode.getNodeType()).activateNode(_previousNode, null, candidateList, operationContext));
                }
            }
        }

        return ffResult;
    }
}