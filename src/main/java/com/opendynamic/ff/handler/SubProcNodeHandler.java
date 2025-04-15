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
public class SubProcNodeHandler implements NodeHandler {
    @Autowired
    private FfService ffService;
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfHelper ffHelper;

    @Override
    public String getNodeType() {
        return FfService.NODE_TYPE_SUB_PROC;
    }

    @SuppressWarnings("unchecked")
    @Override
    public FfResult insertNodeByNodeDef(NodeDef nodeDef, Node branchNode, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        Node node;

        // 新增子流程节点
        String nodeId = OdUtils.getUuid();
        ffNodeService.insertNode(nodeId, branchNode.getNodeId(), branchNode.getProcId(), previousNodeIds, null, branchNode.getSubProcDefId(), branchNode.getAdjustSubProcDefId(), FfService.NODE_TYPE_SUB_PROC, nodeDef.getNodeCode(), nodeDef.getNodeName(), nodeDef.getParentNodeCode(), nodeDef.getCandidateAssignee(), nodeDef.getCompleteExpression(), nodeDef.getCompleteReturn(), nodeDef.getExclusive(), nodeDef.getWaitingForCompleteNode(), nodeDef.getAutoCompleteSameAssignee(), nodeDef.getAutoCompleteEmptyAssignee(), nodeDef.getInform(), nodeDef.getAssignee(), nodeDef.getAction(), nodeDef.getDueDate(), nodeDef.getClaim(), nodeDef.getForwardable(), nodeDef.getPriority(), null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
        node = ffService.loadNode(nodeId);
        ffResult.addCreateNode(node);

        // 新增子流程
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
        ValueExpression expression;
        // JUEL解析
        // 计算子流程
        List<ProcDef> assignSubProcDefList;
        if (StringUtils.isNotEmpty(nodeDef.getCandidateSubProcDef())) {// 如果节点定义中有候选子流程定义，使用用户指定的候选子流程
            Candidate candidate = candidateList.getCandidate(nodeDef.getNodeCode());
            if (candidate == null) {
                candidate = candidateList.getCandidate(ffService.getSubProcPath(branchNode), nodeDef.getNodeCode());
            }
            assignSubProcDefList = new ArrayList<>();
            if (candidate != null && candidate.getCandidateExpression() != null) {
                String[] subProcDefCodes = candidate.getCandidateExpression().split(",");
                for (String subProcDefCode : subProcDefCodes) {
                    assignSubProcDefList.add(ffService.loadProcDefByCode(subProcDefCode));
                }
            }
        }
        else { // 否则使用子流程定义
            expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getAssignSubProcDef(), Object.class);
            Object object = expression.getValue(simpleContext);// 获取子流程定义
            if (object instanceof List) {
                assignSubProcDefList = (List<ProcDef>) object;
            }
            else {
                assignSubProcDefList = new ArrayList<>();
                if (StringUtils.isNotEmpty((String) object)) {
                    String[] assignSubProcDefs = ((String) object).split(",");
                    for (String ssignSubProcDef : assignSubProcDefs) {
                        assignSubProcDefList.add(ffService.loadProcDefByCode(ssignSubProcDef));
                    }
                }
            }
        }

        // 为每个子流程新增分支
        for (ProcDef assignSubProcDef : assignSubProcDefList) {
            String subProcBranchNodeId = OdUtils.getUuid();
            ProcDef procDef = ffService.loadProcDefByCode(assignSubProcDef.getProcDefCode());// 获取子流程定义对应的流程定义
            ffNodeService.insertNode(subProcBranchNodeId, node.getNodeId(), branchNode.getProcId(), previousNodeIds, null, procDef.getProcDefId(), null, FfService.NODE_TYPE_BRANCH, null, assignSubProcDef.getProcDefName(), null, null, FfService.DEFAULT_COMPLETE_EXPRESSION_, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, null, null, null, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, "5", null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
            Node subProcBranchNode = ffService.loadNode(subProcBranchNodeId);
            ffResult.addCreateNode(subProcBranchNode);

            Map<String, Object> subProcVarDefMap = procDef.getProcVarDefMap();
            for (Map.Entry<String, Object> entry : subProcVarDefMap.entrySet()) {
                expression = expressionFactory.createValueExpression(simpleContext, (String) entry.getValue(), Object.class);
                subProcVarDefMap.put(entry.getKey(), expression.getValue(simpleContext));
            }
            ffService.updateNodeVar(subProcBranchNodeId, subProcVarDefMap);// 更新子流程节点变量

            List<? extends NodeDef> startNodeDefList = procDef.getStartNodeDefList();
            for (NodeDef startNodeDef : startNodeDefList) {
                ffResult.addAll(ffService.getNodeHandler(startNodeDef.getNodeType()).insertNodeByNodeDef(startNodeDef, subProcBranchNode, previousNodeIds, candidateList, operationContext));
            }
        }

        String waitingForCompleteNode = nodeDef.getWaitingForCompleteNode();
        String inform = nodeDef.getInform();
        if (waitingForCompleteNode != null && waitingForCompleteNode.contains("${")) {// JUEL解析
            expression = expressionFactory.createValueExpression(simpleContext, waitingForCompleteNode, String.class);
            waitingForCompleteNode = (String) expression.getValue(simpleContext);
        }
        if (inform != null && inform.contains("${")) {// JUEL解析
            expression = expressionFactory.createValueExpression(simpleContext, inform, String.class);
            inform = (String) expression.getValue(simpleContext);
        }
        if (!FfService.BOOLEAN_TRUE.equals(waitingForCompleteNode)) {// 自动完成节点
            // 自动完成通知节点
            if (FfService.BOOLEAN_TRUE.equals(inform)) {
                OperationContext systemExecutorOperationContext = (OperationContext) OdUtils.deepClone(operationContext);
                systemExecutorOperationContext.setExecutor(FfService.USER_FF_SYSTEM);
                ffResult.addAll(completeNode(node, previousNodeIds, candidateList, systemExecutorOperationContext));
            }
        }

        return ffResult;
    }

    @Override
    public FfResult appendCandidate(Node node, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        Node branchNode = ffService.loadNode(node.getParentNodeId());

        // JUEL解析
        // 计算子流程
        List<ProcDef> assignSubProcDefList;
        Candidate candidate = candidateList.getCandidate(node.getNodeCode());
        if (candidate == null) {
            candidate = candidateList.getCandidate(ffService.getSubProcPath(branchNode), node.getNodeCode());
        }
        assignSubProcDefList = new ArrayList<>();
        if (candidate != null && candidate.getCandidateExpression() != null) {
            String[] subProcDefCodes = candidate.getCandidateExpression().split(",");
            for (String subProcDefCode : subProcDefCodes) {
                assignSubProcDefList.add(ffService.loadProcDefByCode(subProcDefCode));
            }
        }

        // 新增子流程
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
        ValueExpression expression;

        // 为每个子流程新增分支
        for (ProcDef assignSubProcDef : assignSubProcDefList) {
            String subProcBranchNodeId = OdUtils.getUuid();
            ProcDef procDef = ffService.loadProcDefByCode(assignSubProcDef.getProcDefCode());// 获取子流程定义对应的流程定义
            ffNodeService.insertNode(subProcBranchNodeId, node.getNodeId(), branchNode.getProcId(), node.getPreviousNodeIds(), null, procDef.getProcDefId(), null, FfService.NODE_TYPE_BRANCH, null, assignSubProcDef.getProcDefName(), null, null, FfService.DEFAULT_COMPLETE_EXPRESSION_, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, null, null, null, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, "5", null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
            Node subProcBranchNode = ffService.loadNode(subProcBranchNodeId);
            ffResult.addCreateNode(subProcBranchNode);

            Map<String, Object> subProcVarDefMap = procDef.getProcVarDefMap();
            for (Map.Entry<String, Object> entry : subProcVarDefMap.entrySet()) {
                expression = expressionFactory.createValueExpression(simpleContext, (String) entry.getValue(), Object.class);
                subProcVarDefMap.put(entry.getKey(), expression.getValue(simpleContext));
            }
            ffService.updateNodeVar(subProcBranchNodeId, subProcVarDefMap);// 更新子流程节点变量

            List<? extends NodeDef> startNodeDefList = procDef.getStartNodeDefList();
            for (NodeDef startNodeDef : startNodeDefList) {
                ffResult.addAll(ffService.getNodeHandler(startNodeDef.getNodeType()).insertNodeByNodeDef(startNodeDef, subProcBranchNode, node.getPreviousNodeIds(), candidateList, operationContext));
            }
        }

        return ffResult;
    }

    @Override
    public FfResult completeNode(Node node, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
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
        node.setLastCompleteNodeIds(StringUtils.join(lastCompleteNodeIdList, ","));

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
        nodeVarMap.putAll(ffNodeService.getChildNodeStatistic(node.getNodeId()));// 获取节点任务完成信息
        ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
        SimpleContext simpleContext = new SimpleContext();
        for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
            simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
        }
        // JUEL解析
        String inform = FfService.BOOLEAN_FALSE;
        String completeReturn = FfService.BOOLEAN_FALSE;
        ValueExpression expression;
        if (StringUtils.isNotEmpty(node.getInform())) {
            expression = expressionFactory.createValueExpression(simpleContext, node.getInform(), String.class);// 判断是否满足节点完成表达式
            inform = (String) expression.getValue(simpleContext);
        }
        if (StringUtils.isNotEmpty(node.getCompleteReturn())) {
            expression = expressionFactory.createValueExpression(simpleContext, node.getCompleteReturn(), String.class);// 判断是否满足节点完成表达式
            completeReturn = (String) expression.getValue(simpleContext);
        }

        if (FfService.BOOLEAN_FALSE.equals(inform) && !nodeVarMap.get("TOTAL").equals(0)) { // 非通知节点需完成判断
            expression = expressionFactory.createValueExpression(simpleContext, node.getCompleteExpression(), Boolean.class);// 判断是否满足节点完成表达式
            if (Boolean.FALSE.equals(expression.getValue(simpleContext))) {
                return ffResult;
            }
        }

        // 完成节点
        // 合并下级节点的候选
        CandidateList fullCandidateList = new CandidateList();
        fullCandidateList.addAll(candidateList);
        for (Node childNode : ffService.createChildNodeQuery().setNodeId(node.getNodeId()).queryForObjectList()) {
            if (StringUtils.isNotEmpty(childNode.getNextCandidate())) {
                fullCandidateList.addAll(new Gson().fromJson(childNode.getNextCandidate(), CandidateList.class));
            }
        }
        String nodeEndUserName = ffHelper.getUserName(operationContext.getExecutor());
        Date nodeEndDate = new Date();
        ffNodeService.updateNodeStatus(node.getNodeId(), operationContext.getExecutor(), nodeEndUserName, nodeEndDate, fullCandidateList.toJson(), FfService.NODE_STATUS_COMPLETE);// 完成节点
        node.setNodeEndUser(operationContext.getExecutor());
        node.setNodeEndUserName(nodeEndUserName);
        node.setNodeEndDate(nodeEndDate);
        node.setNextCandidate(fullCandidateList.toJson());
        node.setNodeStatus(FfService.NODE_STATUS_COMPLETE);
        ffResult.addCompleteNode(node);

        ProcDef procDef = ffService.getNodeProcDef(node); // 获取当前节点所属流程定义
        NodeDef nodeDef = procDef.getNodeDef((node.getNodeCode()));// 获取当前节点所属节点定义
        List<? extends NodeDef> nextNodeDefList = nodeDef.getNextNodeDefList(nodeVarMap);// 查找下一个节点定义
        Node parentNode = ffService.loadNode(node.getParentNodeId());
        if (!nextNodeDefList.isEmpty()) {// 有后续节点定义，新增后续节点。
            for (NodeDef nextNodeDef : nextNodeDefList) {
                ffResult.addAll(ffService.getNodeHandler(nextNodeDef.getNodeType()).insertNodeByNodeDef(nextNodeDef, parentNode, node.getNodeId(), fullCandidateList, operationContext));
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
                fullCandidateList.add(new Candidate(ffService.getSubProcPath(previousNode), previousNode.getNodeCode(), StringUtils.join(assigneeList, ",")));

                ffResult.addAll(ffService.getNodeHandler(previousNode.getNodeType()).insertNodeByNodeDef(previousNodeDef, parentNode, previousNode.getPreviousNodeIds(), fullCandidateList, operationContext));
            }
            else {// 非完成返回节点，递归完成上级节点。
                ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).completeNode(parentNode, node.getNodeId(), fullCandidateList, operationContext));
            }

        return ffResult;
    }

    @Override
    public FfResult rejectNode(Node node, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_TERMINATE) || node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {
            return ffResult;
        }

        // 有并发下级节点不能驳回
        if (ffService.createNodeQuery().setParentNodeId(node.getParentNodeId()).setPreviousNodeIds(node.getPreviousNodeIds()).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE, FfService.NODE_STATUS_SUSPEND, FfService.NODE_STATUS_COMPLETE)).count() > 1) {
            throw new RuntimeException("errors.cannotRejectInParallel");
        }

        String nodeEndUserName = ffHelper.getUserName(operationContext.getExecutor());
        Date nodeEndDate = new Date();
        ffNodeService.updateNodeStatus(node.getNodeId(), operationContext.getExecutor(), nodeEndUserName, nodeEndDate, FfService.NODE_STATUS_TERMINATE);// 完成任务
        node.setNodeEndUser(operationContext.getExecutor());
        node.setNodeEndUserName(nodeEndUserName);
        node.setNodeEndDate(nodeEndDate);
        node.setNodeStatus(FfService.NODE_STATUS_TERMINATE);
        ffResult.addTerminateNode(node);

        Node parentNode = ffService.loadNode(node.getParentNodeId());
        if (StringUtils.isNotEmpty(node.getPreviousNodeIds()) && !node.getPreviousNodeIds().equals(parentNode.getPreviousNodeIds())) {// 有前一个节点，且与上级节点的前一个节点不同，激活前一个节点。
            String[] previousNodeIds = node.getPreviousNodeIds().split(",");
            Node previousNode;
            for (String previousNodeId : previousNodeIds) {
                previousNode = ffService.loadNode(previousNodeId);
                ffResult.addAll(ffService.getNodeHandler(previousNode.getNodeType()).activateNode(previousNode, null, candidateList, operationContext));
            }
        }
        else {// 递归驳回上级节点
            ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).rejectNode(parentNode, candidateList, operationContext));
        }

        return ffResult;
    }

    @Override
    public FfResult activateNode(Node node, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        if (FfService.OPERATION_ACTIVATE.equals(operationContext.getInitialOperation()) && StringUtils.isNotEmpty(previousNodeIds)) {// 去除最后完成节点ID
            List<String> lastCompleteNodeIdList = new ArrayList<>();
            if (StringUtils.isNotEmpty(node.getLastCompleteNodeIds())) {
                lastCompleteNodeIdList.addAll(Arrays.asList(node.getLastCompleteNodeIds().split(",")));
            }
            lastCompleteNodeIdList.remove(Arrays.asList(previousNodeIds.split(",")));
            ffNodeService.updateNodeLastCompleteNodeIds(node.getNodeId(), StringUtils.join(lastCompleteNodeIdList, ","));
        }

        if (!node.getNodeStatus().equals(FfService.NODE_STATUS_ACTIVE)) {
            ffNodeService.updateNodeStatus(node.getNodeId(), FfService.NODE_STATUS_ACTIVE);// 激活当前节点
            node.setNodeStatus(FfService.NODE_STATUS_ACTIVE);
            ffResult.addActivateNode(node);

            if (FfService.OPERATION_ACTIVATE.equals(operationContext.getInitialOperation())) {// 上行激活
                Node parentNode = ffService.loadNode(node.getParentNodeId());// 递归激活上级节点
                ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).activateNode(parentNode, node.getNodeId(), candidateList, operationContext));
            }
            else {// 下行激活
                String lastCompleteNodeIds = node.getLastCompleteNodeIds();
                if (StringUtils.isNotEmpty(lastCompleteNodeIds)) {
                    ffNodeService.updateNodeLastCompleteNodeIds(node.getNodeId(), null);
                    List<Node> lastCompleteNodeList = ffService.createNodeQuery().setNodeIdList(Arrays.asList(lastCompleteNodeIds.split(","))).queryForObjectList();
                    for (Node lastCompleteNode : lastCompleteNodeList) {
                        ffResult.addAll(ffService.getNodeHandler(lastCompleteNode.getNodeType()).activateNode(lastCompleteNode, null, candidateList, operationContext));
                    }
                }
            }
        }

        return ffResult;
    }
}