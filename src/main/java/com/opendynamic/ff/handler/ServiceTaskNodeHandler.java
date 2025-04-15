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

import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfHelper;
import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.Candidate;
import com.opendynamic.ff.vo.CandidateList;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.OperationContext;
import com.opendynamic.ff.vo.ProcDef;
import com.opendynamic.ff.vo.Task;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ServiceTaskNodeHandler implements NodeHandler {
    @Autowired
    private FfService ffService;
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfHelper ffHelper;

    @Override
    public String getNodeType() {
        return FfService.NODE_TYPE_SERVICE_TASK;
    }

    @Override
    public FfResult insertNodeByNodeDef(NodeDef nodeDef, Node branchNode, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        Node node;

        // 新增节点
        String nodeId = OdUtils.getUuid();
        ffNodeService.insertNode(nodeId, branchNode.getNodeId(), branchNode.getProcId(), previousNodeIds, null, branchNode.getSubProcDefId(), branchNode.getAdjustSubProcDefId(), FfService.NODE_TYPE_SERVICE_TASK, nodeDef.getNodeCode(), nodeDef.getNodeName(), nodeDef.getParentNodeCode(), nodeDef.getCandidateAssignee(), nodeDef.getCompleteExpression(), nodeDef.getCompleteReturn(), nodeDef.getExclusive(), nodeDef.getWaitingForCompleteNode(), nodeDef.getAutoCompleteSameAssignee(), nodeDef.getAutoCompleteEmptyAssignee(), nodeDef.getInform(), nodeDef.getAssignee(), nodeDef.getAction(), nodeDef.getDueDate(), nodeDef.getClaim(), nodeDef.getForwardable(), nodeDef.getPriority(), null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
        node = ffService.loadNode(nodeId);
        ffResult.addCreateNode(node);

        // 执行服务
        // 设置JUEL解析环境
        if (operationContext.getCurrentProc() == null || !operationContext.getCurrentProc().getProcId().equals(branchNode.getProcId())) {
            operationContext.setCurrentProc(ffService.loadProc(branchNode.getProcId()));
        }
        if (operationContext.getCurrentBranchNode() == null || !operationContext.getCurrentBranchNode().getNodeId().equals(branchNode.getNodeId())) {
            operationContext.setCurrentBranchNode(branchNode);
        }
        if (operationContext.getCurrentNode() == null || !operationContext.getCurrentNode().getNodeId().equals(node.getNodeId())) {
            operationContext.setCurrentNode(node);
        }
        if (operationContext.getCurrentNodeVarMapNode() == null || !operationContext.getCurrentNodeVarMapNode().getNodeId().equals(branchNode.getNodeId())) {
            operationContext.setCurrentNodeVarMapNode(branchNode);
            operationContext.setCurrentNodeVarMap(ffService.createNodeVarQuery().setNodeId(branchNode.getNodeId()).setRecursive(true).queryForMap());// 获取节点变量
        }
        HashMap<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.putAll(ffService.getInternalServiceMap());
        nodeVarMap.putAll(ffService.getExternalServiceMap());
        nodeVarMap.put("proc", operationContext.getCurrentProc());
        nodeVarMap.put("branch", operationContext.getCurrentBranchNode());
        nodeVarMap.put("node", operationContext.getCurrentNode());
        nodeVarMap.putAll(operationContext.getCurrentNodeVarMap());
        ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
        SimpleContext simpleContext = new SimpleContext();
        for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
            simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
        }
        // 执行Service调用
        ValueExpression expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getAction(), String.class);
        expression.getValue(simpleContext);

        String waitingForCompleteNode = nodeDef.getWaitingForCompleteNode();
        if (waitingForCompleteNode != null && waitingForCompleteNode.contains("${")) {// JUEL解析
            expression = expressionFactory.createValueExpression(simpleContext, waitingForCompleteNode, String.class);
            waitingForCompleteNode = (String) expression.getValue(simpleContext);
        }
        if (!FfService.BOOLEAN_TRUE.equals(waitingForCompleteNode)) {// 自动完成节点
            // 完成节点
            ffResult.addAll(completeNode(node, node.getNodeId(), candidateList, operationContext));
        }

        return ffResult;
    }

    @Override
    public FfResult appendCandidate(Node node, CandidateList candidateList, OperationContext operationContext) {
        return new FfResult();
    }

    @Override
    public FfResult completeNode(Node node, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {// 如已经完成，直接返回
            return ffResult;
        }

        // 完成节点
        String nodeEndUserName = ffHelper.getUserName(operationContext.getExecutor());
        Date nodeEndDate = new Date();
        ffNodeService.updateNodeStatus(node.getNodeId(), operationContext.getExecutor(), nodeEndUserName, nodeEndDate, candidateList.toJson(), FfService.NODE_STATUS_COMPLETE);// 完成节点
        node.setNodeEndUser(operationContext.getExecutor());
        node.setNodeEndUserName(nodeEndUserName);
        node.setNodeEndDate(nodeEndDate);
        node.setNextCandidate(candidateList.toJson());
        node.setNodeStatus(FfService.NODE_STATUS_COMPLETE);
        ffResult.addCompleteNode(node);

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
        nodeVarMap.putAll(ffNodeService.getTaskStatistic(node.getNodeId()));// 获取节点任务完成信息
        ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
        SimpleContext simpleContext = new SimpleContext();
        for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
            simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
        }
        // JUEL解析
        String completeReturn = FfService.BOOLEAN_FALSE;
        ValueExpression expression;
        if (StringUtils.isNotEmpty(node.getCompleteReturn())) {
            expression = expressionFactory.createValueExpression(simpleContext, node.getCompleteReturn(), String.class);// 判断是否满足节点完成表达式
            completeReturn = (String) expression.getValue(simpleContext);
        }

        ProcDef procDef = ffService.getNodeProcDef(node); // 获取当前节点所属流程定义
        NodeDef nodeDef = procDef.getNodeDef((node.getNodeCode()));// 获取当前节点所属节点定义
        List<? extends NodeDef> nextNodeDefList = nodeDef.getNextNodeDefList(nodeVarMap);// 查找下一个节点定义
        Node parentNode = ffService.loadNode(node.getParentNodeId());
        if (!nextNodeDefList.isEmpty()) {// 有后续节点定义，新增后续节点。
            for (NodeDef nextNodeDef : nextNodeDefList) {
                ffResult.addAll(ffService.getNodeHandler(nextNodeDef.getNodeType()).insertNodeByNodeDef(nextNodeDef, parentNode, previousNodeIds, candidateList, operationContext));
            }
        }
        else// 无后续节点定义。
            if (FfService.BOOLEAN_TRUE.equals(completeReturn)) {// 完成返回节点，激活前一个节点。
                Node previousNode = ffService.loadNode(node.getPreviousNodeIds());// 获取前一个节点。
                NodeDef previousNodeDef = ffService.getNodeProcDef(previousNode).getNodeDef((previousNode.getNodeCode()));// 获取当前节点所属节点定义

                // 计算激活的办理人，为上一次该节点任务的办理人。
                List<Task> taskList = ffService.createTaskQuery().setNodeId(previousNode.getNodeId()).queryForObjectList();
                List<String> assigneeList = new ArrayList<>();
                for (Task task : taskList) {
                    assigneeList.add(task.getAssignee());
                }
                candidateList.add(new Candidate(ffService.getSubProcPath(previousNode), previousNode.getNodeCode(), StringUtils.join(assigneeList, ",")));

                ffResult.addAll(ffService.getNodeHandler(previousNode.getNodeType()).insertNodeByNodeDef(previousNodeDef, parentNode, previousNode.getPreviousNodeIds(), candidateList, operationContext));
            }
            else {// 非完成返回节点，递归完成上级节点。
                ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).completeNode(parentNode, node.getNodeId(), candidateList, operationContext));
            }

        return ffResult;
    }

    @Override
    public FfResult rejectNode(Node node, CandidateList candidateList, OperationContext operationContext) {
        return new FfResult();// 返回值
    }

    @Override
    public FfResult activateNode(Node node, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        // 有并发下级节点不能驳回
        if (ffService.createNodeQuery().setParentNodeId(node.getParentNodeId()).setPreviousNodeIds(node.getPreviousNodeIds()).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE, FfService.NODE_STATUS_SUSPEND, FfService.NODE_STATUS_COMPLETE)).count() > 1) {
            throw new RuntimeException("errors.cannotRejectInParallel");
        }

        Node parentNode = ffService.loadNode(node.getParentNodeId());
        if (StringUtils.isNotEmpty(node.getPreviousNodeIds()) && !node.getPreviousNodeIds().equals(parentNode.getPreviousNodeIds())) {// 有前一个节点，且与上级节点的前一个节点不同，激活前一个节点。
            Node previousNode;
            for (String previousNodeId : node.getPreviousNodeIds().split(",")) {
                previousNode = ffService.loadNode(previousNodeId);
                ffResult.addAll(ffService.getNodeHandler(previousNode.getNodeType()).activateNode(previousNode, null, candidateList, operationContext));
            }
        }
        else {// 递归驳回上级节点
            ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).rejectNode(parentNode, candidateList, operationContext));
        }

        return ffResult;
    }
}