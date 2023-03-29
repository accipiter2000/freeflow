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
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.Proc;
import com.opendynamic.ff.vo.ProcDef;

import de.odysseus.el.util.SimpleContext;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class IsolateSubProcNodeHandler implements NodeHandler {
    @Autowired
    private FfService ffService;
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfHelper ffHelper;

    @Override
    public String getNodeType() {
        return FfService.NODE_TYPE_ISOLATE_SUB_PROC;
    }

    @SuppressWarnings("unchecked")
    @Override
    public FfResult insertNodeByNodeDef(NodeDef nodeDef, Node branchNode, String previousNodeIds, String triggerOperation, String executor) {
        FfResult ffResult = new FfResult();// 返回值

        Proc proc = ffService.loadProc(branchNode.getProcId());
        Node isolateSubProcNode;

        // 新增子流程节点
        String subProcNodeId = OdUtils.getUuid();
        ffNodeService.insertNode(subProcNodeId, branchNode.getNodeId(), branchNode.getProcId(), previousNodeIds, null, branchNode.getSubProcDefId(), branchNode.getAdjustSubProcDefId(), FfService.NODE_TYPE_ISOLATE_SUB_PROC, nodeDef.getNodeCode(), nodeDef.getNodeName(), nodeDef.getParentNodeCode(), nodeDef.getAssignee(), nodeDef.getCandidate(), nodeDef.getAction(), nodeDef.getDueDate(), nodeDef.getCompleteExpression(), nodeDef.getCompleteReturn(), nodeDef.getExclusive(), nodeDef.getForwardable(), nodeDef.getAutoCompleteSameAssignee(), nodeDef.getAutoCompleteEmptyAssignee(), nodeDef.getInform(), nodeDef.getPriority(), null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
        isolateSubProcNode = ffService.loadNode(subProcNodeId);
        ffResult.addCreateNode(isolateSubProcNode);

        // 新增子流程
        ExpressionFactory expressionFactory = ffService.getExpressionFactory();
        SimpleContext simpleContext = ffService.getSimpleContext(ffService.createNodeVarQuery().setNodeId(branchNode.getNodeId()).setRecursive(true).queryForMap());
        simpleContext.setVariable("proc", expressionFactory.createValueExpression(proc, Object.class));
        simpleContext.setVariable("branch", expressionFactory.createValueExpression(branchNode, Object.class));
        simpleContext.setVariable("node", expressionFactory.createValueExpression(isolateSubProcNode, Object.class));
        // 计算子流程
        ValueExpression expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getAssignSubProcDef(), Object.class);
        Object object = expression.getValue(simpleContext);// 获取子流程定义
        List<ProcDef> assignSubProcDefList;
        if (object instanceof List) {
            assignSubProcDefList = (List<ProcDef>) object;
        }
        else {
            assignSubProcDefList = new ArrayList<>();
            if (StringUtils.isNotEmpty((String) object)) {
                String[] assignSubProcDefs = ((String) object).split(",");
                for (int i = 0; i < assignSubProcDefs.length; i++) {
                    assignSubProcDefList.add(ffService.loadProcDefByCode(assignSubProcDefs[i]));
                }
            }
        }
        // 为每个子流程新增分支
        for (ProcDef assignSubProcDef : assignSubProcDefList) {
            String subProcBranchNodeId = OdUtils.getUuid();
            ProcDef procDef = ffService.loadProcDefByCode(assignSubProcDef.getProcDefCode());// 获取子流程定义对应的流程定义
            ffNodeService.insertNode(subProcBranchNodeId, isolateSubProcNode.getNodeId(), branchNode.getProcId(), previousNodeIds, null, procDef.getProcDefId(), null, FfService.NODE_TYPE_BRANCH, null, assignSubProcDef.getProcDefName(), null, null, null, null, null, FfService.DEFAULT_COMPLETE_EXPRESSION_, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, 5, null, null, null, assignSubProcDef.getProcDefCode(), FfService.PROC_STATUS_NOT_START, FfService.NODE_STATUS_ACTIVE, new Date());
            Node subProcBranchNode = ffService.loadNode(subProcBranchNodeId);
            ffResult.addCreateNode(subProcBranchNode);

            Map<String, Object> subProcVarDefMap = procDef.getProcVarDefMap();
            for (Map.Entry<String, Object> entry : subProcVarDefMap.entrySet()) {
                expression = expressionFactory.createValueExpression(simpleContext, (String) entry.getValue(), Object.class);
                subProcVarDefMap.put(entry.getKey(), expression.getValue(simpleContext));
            }
            ffService.updateNodeVar(subProcBranchNodeId, subProcVarDefMap);// 更新子流程节点变量
        }

        // 自动完成通知节点
        if (FfService.BOOLEAN_TRUE.equals(nodeDef.getInform())) {
            ffResult.addAll(completeNode(isolateSubProcNode, previousNodeIds, FfService.OPERATION_COMPLETE, FfService.USER_FF_SYSTEM));
        }

        return ffResult;
    }

    @Override
    public FfResult completeNode(Node node, String previousNodeIds, String triggerOperation, String executor) {
        FfResult ffResult = new FfResult();// 返回值

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {// 如已经完成，直接返回
            return ffResult;
        }

        // 更新最后完成节点IDs
        List<String> lastCompleteNodeIdList = new ArrayList<>();
        if (StringUtils.isNotEmpty(node.getLastCompleteNodeIds())) {
            lastCompleteNodeIdList.addAll(Arrays.asList(node.getLastCompleteNodeIds().split(",")));
        }
        if (StringUtils.isNotEmpty(previousNodeIds)) {
            lastCompleteNodeIdList.addAll(Arrays.asList(previousNodeIds.split(",")));
        }
        ffNodeService.updateNodeLastCompleteNodeIds(node.getNodeId(), StringUtils.join(lastCompleteNodeIdList, ","));

        // 非通知节点需完成判断
        Map<String, Object> nodeVarMap = ffService.createNodeVarQuery().setNodeId(node.getNodeId()).setRecursive(true).queryForMap();// 获取节点变量
        Proc proc = ffService.loadProc(node.getProcId());
        nodeVarMap.put("proc", proc);
        nodeVarMap.put("branch", ffService.loadNode(node.getParentNodeId()));
        nodeVarMap.put("node", node);
        nodeVarMap.putAll(ffNodeService.getChildNodeStatistic(node.getNodeId()));// 获取节点任务完成信息
        if (FfService.BOOLEAN_FALSE.equals(node.getInform()) && !nodeVarMap.get("TOTAL").equals(0)) {
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

        ProcDef procDef = ffService.getNodeProcDef(node); // 获取当前节点所属流程定义
        NodeDef nodeDef = procDef.getNodeDef((node.getNodeCode()));// 获取当前节点所属节点定义
        List<? extends NodeDef> nextNodeDefList = nodeDef.getNextNodeDefList(nodeVarMap);// 查找下一个节点定义
        Node parentNode = ffService.loadNode(node.getParentNodeId());
        if (nextNodeDefList.size() > 0) {// 有后续节点定义，新增后续节点。
            for (NodeDef nextNodeDef : nextNodeDefList) {
                ffResult.addAll(ffService.getNodeHandler(nextNodeDef.getNodeType()).insertNodeByNodeDef(nextNodeDef, parentNode, node.getNodeId(), FfService.OPERATION_COMPLETE, executor));
            }
        }
        else// 无后续节点定义。
            if (node.getCompleteReturn().equals(FfService.BOOLEAN_TRUE)) {// 完成返回节点，激活前一个节点。
                Node previousNode = ffService.loadNode(node.getPreviousNodeIds());// 获取前一个节点。
                NodeDef previousNodeDef = ffService.getNodeProcDef(previousNode).getNodeDef((previousNode.getNodeCode()));// 获取当前节点所属节点定义
                ffResult.addAll(ffService.getNodeHandler(previousNode.getNodeType()).insertNodeByNodeDef(previousNodeDef, parentNode, previousNode.getPreviousNodeIds(), FfService.OPERATION_COMPLETE, executor));
            }
            else {// 非完成返回节点，递归完成上级节点。
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
        return new FfResult();
    }
}