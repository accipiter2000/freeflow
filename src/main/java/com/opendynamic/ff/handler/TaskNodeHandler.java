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
import com.opendynamic.ff.service.FfTaskService;
import com.opendynamic.ff.vo.Candidate;
import com.opendynamic.ff.vo.CandidateList;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.FfUser;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.NodeHandlerOperation;
import com.opendynamic.ff.vo.OperationContext;
import com.opendynamic.ff.vo.ProcDef;
import com.opendynamic.ff.vo.Task;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskNodeHandler implements NodeHandler {
    @Autowired
    private FfService ffService;
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfTaskService ffTaskService;
    @Autowired
    private FfHelper ffHelper;

    @Override
    public String getNodeType() {
        return FfService.NODE_TYPE_TASK;
    }

    @SuppressWarnings("unchecked")
    @Override
    public FfResult insertNodeByNodeDef(NodeDef nodeDef, Node branchNode, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        Node node;

        // 新增节点
        String nodeId = OdUtils.getUuid();
        ffNodeService.insertNode(nodeId, branchNode.getNodeId(), branchNode.getProcId(), previousNodeIds, null, branchNode.getSubProcDefId(), branchNode.getAdjustSubProcDefId(), FfService.NODE_TYPE_TASK, nodeDef.getNodeCode(), nodeDef.getNodeName(), nodeDef.getParentNodeCode(), nodeDef.getCandidateAssignee(), nodeDef.getCompleteExpression(), nodeDef.getCompleteReturn(), nodeDef.getExclusive(), nodeDef.getWaitingForCompleteNode(), nodeDef.getAutoCompleteSameAssignee(), nodeDef.getAutoCompleteEmptyAssignee(), nodeDef.getInform(), nodeDef.getAssignee(), nodeDef.getAction(), nodeDef.getDueDate(), nodeDef.getClaim(), nodeDef.getForwardable(), nodeDef.getPriority(), null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
        node = ffService.loadNode(nodeId);
        ffResult.addCreateNode(node);
        operationContext.addNodeHandlerOperation(new NodeHandlerOperation(FfService.NODE_HANDLER_OPERATION_INSERT, node));

        // 新增任务
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
        simpleContext.setVariable("operationContext", expressionFactory.createValueExpression(operationContext, Object.class));
        ValueExpression expression;
        // JUEL解析
        // 计算办理人
        List<FfUser> assigneeList;
        if (StringUtils.isNotEmpty(nodeDef.getCandidateAssignee())) { // 如果节点定义中有候选人，使用用户指定的候选人
            Candidate candidate = candidateList.getCandidate(nodeDef.getNodeCode());
            if (candidate == null) {
                candidate = candidateList.getCandidate(ffService.getSubProcPath(branchNode), nodeDef.getNodeCode());
            }
            if (candidate != null) {
                assigneeList = ffService.getAssigneeList(candidate.getCandidateExpression());
            }
            else {
                assigneeList = new ArrayList<>();
            }
        }
        else { // 否则使用办理人
            expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getAssignee(), Object.class);
            Object object = expression.getValue(simpleContext);// 获取办理人
            if (object instanceof List) {
                assigneeList = (List<FfUser>) object;
            }
            else {
                assigneeList = ffService.getAssigneeList((String) object);
            }
        }

        Task task;
        List<Task> createTaskList = new ArrayList<>();
        // 为每个办理人新增任务
        for (FfUser assignee : assigneeList) {
            String taskId = OdUtils.getUuid();
            String action = null;
            Date dueDate = null;
            String claim = FfService.BOOLEAN_FALSE;
            String forwardable = FfService.BOOLEAN_FALSE;
            Integer priority = 5;

            task = new Task();
            task.setTaskId(taskId);
            task.setNodeId(nodeId);
            task.setTaskType(FfService.TASK_TYPE_TASK);
            task.setAssignee(assignee.getId());
            task.setAssigneeName(assignee.getUserName());
            // 解析JUEL,从节点定义中获取相关任务属性
            simpleContext.setVariable("task", expressionFactory.createValueExpression(task, Object.class));
            if (StringUtils.isNotEmpty(nodeDef.getAction())) {
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getAction(), String.class);
                action = (String) expression.getValue(simpleContext);
            }
            if (StringUtils.isNotEmpty(nodeDef.getDueDate())) {
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getDueDate(), Date.class);
                dueDate = (Date) expression.getValue(simpleContext);
            }
            if (StringUtils.isNotEmpty(nodeDef.getClaim())) {
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getClaim(), String.class);
                claim = (String) expression.getValue(simpleContext);
            }
            if (StringUtils.isNotEmpty(nodeDef.getForwardable())) {
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getForwardable(), String.class);
                forwardable = (String) expression.getValue(simpleContext);
            }
            if (StringUtils.isNotEmpty(nodeDef.getPriority())) {
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getPriority(), Integer.class);
                priority = (Integer) expression.getValue(simpleContext);
            }
            task.setAction(action);
            task.setDueDate(dueDate);
            task.setClaim(claim);
            task.setForwardable(forwardable);
            task.setPriority(priority);
            task.setForwardStatus(FfService.FORWARD_STATUS_NOT_FORWARDED);
            task.setTaskStatus(FfService.TASK_STATUS_ACTIVE);
            task.setCreationDate(new Date());

            ffService.insertTask(task, operationContext.getCurrentExecutor());
            task = ffService.loadTask(taskId);
            ffResult.addCreateTask(task);
            createTaskList.add(task);
        }

        String systemExecutor = FfService.USER_FF_SYSTEM;
        String systemExecutorName = ffHelper.getUserName(systemExecutor);
        String exclusive = nodeDef.getExclusive();
        String waitingForCompleteNode = nodeDef.getWaitingForCompleteNode();
        String autoCompleteSameAssignee = nodeDef.getAutoCompleteSameAssignee();
        String autoCompleteEmptyAssignee = nodeDef.getAutoCompleteEmptyAssignee();
        String inform = nodeDef.getInform();
        if (exclusive != null && exclusive.contains("${")) {// JUEL解析
            expression = expressionFactory.createValueExpression(simpleContext, exclusive, String.class);
            exclusive = (String) expression.getValue(simpleContext);
        }
        if (waitingForCompleteNode != null && waitingForCompleteNode.contains("${")) {// JUEL解析
            expression = expressionFactory.createValueExpression(simpleContext, waitingForCompleteNode, String.class);
            waitingForCompleteNode = (String) expression.getValue(simpleContext);
        }
        if (autoCompleteSameAssignee != null && autoCompleteSameAssignee.contains("${")) {// JUEL解析
            expression = expressionFactory.createValueExpression(simpleContext, autoCompleteSameAssignee, String.class);
            autoCompleteSameAssignee = (String) expression.getValue(simpleContext);
        }
        if (autoCompleteEmptyAssignee != null && autoCompleteEmptyAssignee.contains("${")) {// JUEL解析
            expression = expressionFactory.createValueExpression(simpleContext, autoCompleteEmptyAssignee, String.class);
            autoCompleteEmptyAssignee = (String) expression.getValue(simpleContext);
        }
        if (inform != null && inform.contains("${")) {// JUEL解析
            expression = expressionFactory.createValueExpression(simpleContext, inform, String.class);
            inform = (String) expression.getValue(simpleContext);
        }

        OperationContext systemExecutorOperationContext = (OperationContext) OdUtils.deepClone(operationContext);
        systemExecutorOperationContext.setCurrentExecutor(systemExecutor);
        if (!FfService.BOOLEAN_TRUE.equals(waitingForCompleteNode)) {// 自动完成节点
            // 自动完成通知节点
            if (FfService.BOOLEAN_TRUE.equals(inform)) {
                ffResult.addAll(completeNode(node, previousNodeIds, candidateList, systemExecutorOperationContext));
            }
            // 自动完成相同办理人任务
            if (FfService.BOOLEAN_TRUE.equals(autoCompleteSameAssignee)) {
                for (Task createTask : createTaskList) {
                    if (ffService.createTaskQuery().setProcId(createTask.getProcId()).setAssigneeList(ffHelper.getAllUserIdList(createTask.getAssignee())).setTaskStatus(FfService.TASK_STATUS_COMPLETE).count() > 0) {
                        Date completeDate = new Date();
                        ffTaskService.updateTaskStatus(createTask.getTaskId(), systemExecutor, systemExecutorName, completeDate, FfService.TASK_STATUS_COMPLETE);// 完成任务
                        createTask.setTaskEndUser(systemExecutor);
                        createTask.setTaskEndUserName(systemExecutorName);
                        createTask.setTaskEndDate(completeDate);
                        createTask.setTaskStatus(FfService.TASK_STATUS_COMPLETE);
                        ffResult.addCompleteTask(createTask);

                        if (FfService.BOOLEAN_TRUE.equals(exclusive)) {// 排他处理
                            List<Task> remainActiveTaskList = ffService.createTaskQuery().setNodeId(node.getNodeId()).setTaskStatus(FfService.TASK_STATUS_ACTIVE).queryForObjectList();
                            for (Task remainActiveTask : remainActiveTaskList) {
                                ffTaskService.updateTaskStatus(remainActiveTask.getTaskId(), systemExecutor, systemExecutorName, completeDate, FfService.TASK_STATUS_TERMINATE);
                                remainActiveTask.setTaskEndUser(systemExecutor);
                                remainActiveTask.setTaskEndUserName(systemExecutorName);
                                remainActiveTask.setTaskEndDate(completeDate);
                                remainActiveTask.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
                                ffResult.addTerminateTask(remainActiveTask);
                            }

                            ffResult.addAll(completeNode(node, previousNodeIds, candidateList, systemExecutorOperationContext));
                            break;
                        }

                        ffResult.addAll(completeNode(node, previousNodeIds, candidateList, systemExecutorOperationContext));
                    }
                }
            }
            // 自动完成没有办理人节点
            if (createTaskList.isEmpty() && FfService.BOOLEAN_TRUE.equals(autoCompleteEmptyAssignee)) {
                ffResult.addAll(completeNode(node, previousNodeIds, candidateList, systemExecutorOperationContext));
            }
        }

        return ffResult;
    }

    @Override
    public FfResult appendCandidate(Node node, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值
        operationContext.addNodeHandlerOperation(new NodeHandlerOperation(FfService.NODE_HANDLER_OPERATION_APPEND, node));

        Node branchNode = ffService.loadNode(node.getParentNodeId());
        ProcDef procDef = ffService.loadProcDef(node.getSubProcDefId());
        NodeDef nodeDef = procDef.getNodeDef(node.getNodeCode());

        // 新增任务
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
        simpleContext.setVariable("operationContext", expressionFactory.createValueExpression(operationContext, Object.class));
        ValueExpression expression;
        // JUEL解析
        // 计算办理人
        List<FfUser> assigneeList;
        Candidate candidate = candidateList.getCandidate(node.getNodeCode());
        if (candidate == null) {
            candidate = candidateList.getCandidate(ffService.getSubProcPath(branchNode), node.getNodeCode());
        }
        if (candidate != null) {
            assigneeList = ffService.getAssigneeList(candidate.getCandidateExpression());
        }
        else {
            assigneeList = new ArrayList<>();
        }

        Task task;
        List<Task> createTaskList = new ArrayList<>();
        // 为每个办理人新增任务
        for (FfUser assignee : assigneeList) {
            String taskId = OdUtils.getUuid();
            String action = null;
            Date dueDate = null;
            String claim = FfService.BOOLEAN_FALSE;
            String forwardable = FfService.BOOLEAN_FALSE;
            Integer priority = 5;

            task = new Task();
            task.setTaskId(taskId);
            task.setNodeId(node.getNodeId());
            task.setTaskType(FfService.TASK_TYPE_TASK);
            task.setAssignee(assignee.getId());
            task.setAssigneeName(assignee.getUserName());
            // 解析JUEL,从节点定义中获取相关任务属性
            simpleContext.setVariable("task", expressionFactory.createValueExpression(task, Object.class));
            if (StringUtils.isNotEmpty(nodeDef.getAction())) {
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getAction(), String.class);
                action = (String) expression.getValue(simpleContext);
            }
            if (StringUtils.isNotEmpty(nodeDef.getDueDate())) {
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getDueDate(), Date.class);
                dueDate = (Date) expression.getValue(simpleContext);
            }
            if (StringUtils.isNotEmpty(nodeDef.getClaim())) {
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getClaim(), String.class);
                claim = (String) expression.getValue(simpleContext);
            }
            if (StringUtils.isNotEmpty(nodeDef.getForwardable())) {
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getForwardable(), String.class);
                forwardable = (String) expression.getValue(simpleContext);
            }
            if (StringUtils.isNotEmpty(nodeDef.getPriority())) {
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getPriority(), Integer.class);
                priority = (Integer) expression.getValue(simpleContext);
            }
            task.setAction(action);
            task.setDueDate(dueDate);
            task.setClaim(claim);
            task.setForwardable(forwardable);
            task.setPriority(priority);
            task.setForwardStatus(FfService.FORWARD_STATUS_NOT_FORWARDED);
            task.setTaskStatus(FfService.TASK_STATUS_ACTIVE);
            task.setCreationDate(new Date());

            ffService.insertTask(task, operationContext.getCurrentExecutor());
            task = ffService.loadTask(taskId);
            ffResult.addCreateTask(task);
            createTaskList.add(task);
        }

        String systemExecutor = FfService.USER_FF_SYSTEM;
        String systemExecutorName = ffHelper.getUserName(systemExecutor);
        String autoCompleteSameAssignee = nodeDef.getAutoCompleteSameAssignee();
        if (autoCompleteSameAssignee != null && autoCompleteSameAssignee.contains("${")) {// JUEL解析
            expression = expressionFactory.createValueExpression(simpleContext, autoCompleteSameAssignee, String.class);
            autoCompleteSameAssignee = (String) expression.getValue(simpleContext);
        }

        // 自动完成相同办理人任务
        if (FfService.BOOLEAN_TRUE.equals(autoCompleteSameAssignee)) {
            for (Task createTask : createTaskList) {
                if (ffService.createTaskQuery().setProcId(createTask.getProcId()).setAssigneeList(ffHelper.getAllUserIdList(createTask.getAssignee())).setTaskStatus(FfService.TASK_STATUS_COMPLETE).count() > 0) {
                    Date completeDate = new Date();
                    ffTaskService.updateTaskStatus(createTask.getTaskId(), systemExecutor, systemExecutorName, completeDate, FfService.TASK_STATUS_COMPLETE);// 完成任务
                    createTask.setTaskEndUser(systemExecutor);
                    createTask.setTaskEndUserName(systemExecutorName);
                    createTask.setTaskEndDate(completeDate);
                    createTask.setTaskStatus(FfService.TASK_STATUS_COMPLETE);
                    ffResult.addCompleteTask(createTask);

                }
            }
        }

        return ffResult;
    }

    @Override
    public FfResult completeNode(Node node, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值
        operationContext.addNodeHandlerOperation(new NodeHandlerOperation(FfService.NODE_HANDLER_OPERATION_COMPLETE, node));

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {// 如已经完成，直接返回
            return ffResult;
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
        nodeVarMap.putAll(ffNodeService.getTaskStatistic(node.getNodeId()));// 获取节点任务完成信息
        ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
        SimpleContext simpleContext = new SimpleContext();
        for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
            simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
        }
        simpleContext.setVariable("operationContext", expressionFactory.createValueExpression(operationContext, Object.class));
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

        if (FfService.BOOLEAN_FALSE.equals(inform) && !nodeVarMap.get("TOTAL").equals(0)) {// 非通知节点需完成判断
            expression = expressionFactory.createValueExpression(simpleContext, node.getCompleteExpression(), Boolean.class);// 判断是否满足节点完成表达式
            if (Boolean.FALSE.equals(expression.getValue(simpleContext))) {
                return ffResult;
            }
        }

        // 完成节点
        // 合并任务的候选
        CandidateList fullCandidateList = new CandidateList();
        fullCandidateList.addAll(candidateList);
        for (Task task : ffService.createTaskQuery().setNodeId(node.getNodeId()).queryForObjectList()) {
            if (StringUtils.isNotEmpty(task.getNextCandidate())) {
                fullCandidateList.addAll(new Gson().fromJson(task.getNextCandidate(), CandidateList.class));
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
        operationContext.addNodeHandlerOperation(new NodeHandlerOperation(FfService.NODE_HANDLER_OPERATION_REJECT, node));

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_TERMINATE) || node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {
            return ffResult;
        }

        // 有并发下级节点不能驳回
        if (ffService.createNodeQuery().setParentNodeId(node.getParentNodeId()).setPreviousNodeIds(node.getPreviousNodeIds()).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE, FfService.NODE_STATUS_SUSPEND, FfService.NODE_STATUS_COMPLETE)).count() > 1) {
            throw new RuntimeException("errors.cannotRejectInParallel");
        }

        // 完成节点
        String nodeEndUserName = ffHelper.getUserName(operationContext.getCurrentExecutor());
        Date nodeEndDate = new Date();
        ffNodeService.updateNodeStatus(node.getNodeId(), operationContext.getCurrentExecutor(), nodeEndUserName, nodeEndDate, FfService.NODE_STATUS_TERMINATE);// 完成节点
        node.setNodeEndUser(operationContext.getCurrentExecutor());
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
        operationContext.addNodeHandlerOperation(new NodeHandlerOperation(FfService.NODE_HANDLER_OPERATION_ACTIVATE, node));

        if (!node.getNodeStatus().equals(FfService.NODE_STATUS_ACTIVE)) {
            ffNodeService.updateNodeStatus(node.getNodeId(), FfService.NODE_STATUS_ACTIVE);
            node.setNodeStatus(FfService.NODE_STATUS_ACTIVE);
            ffResult.addActivateNode(node);

            List<Task> taskList = ffService.createTaskQuery().setNodeId(node.getNodeId()).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_COMPLETE, FfService.TASK_STATUS_TERMINATE)).queryForObjectList();
            for (Task task : taskList) {
                ffTaskService.updateTaskStatus(task.getTaskId(), FfService.TASK_STATUS_ACTIVE);
                task.setTaskStatus(FfService.TASK_STATUS_ACTIVE);
                ffResult.addActivateTask(task);
            }

            if (FfService.OPERATION_ACTIVATE.equals(operationContext.getInitialOperation())) {
                Node parentNode = ffService.loadNode(node.getParentNodeId());// 递归激活上级节点
                if (parentNode != null) {
                    ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).activateNode(parentNode, node.getNodeId(), candidateList, operationContext));
                }
            }

            String autoCompleteEmptyAssignee = node.getAutoCompleteEmptyAssignee();
            if (autoCompleteEmptyAssignee != null && autoCompleteEmptyAssignee.contains("${")) {// JUEL解析
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
                simpleContext.setVariable("operationContext", expressionFactory.createValueExpression(operationContext, Object.class));
                // JUEL解析
                ValueExpression expression = expressionFactory.createValueExpression(simpleContext, autoCompleteEmptyAssignee, String.class);
                autoCompleteEmptyAssignee = (String) expression.getValue(simpleContext);
            }

            if (FfService.OPERATION_REJECT.equals(operationContext.getInitialOperation()) && taskList.isEmpty() && FfService.BOOLEAN_TRUE.equals(autoCompleteEmptyAssignee)) {
                OperationContext systemExecutorOperationContext = (OperationContext) OdUtils.deepClone(operationContext);
                systemExecutorOperationContext.setCurrentExecutor(FfService.USER_FF_SYSTEM);
                ffResult.addAll(rejectNode(node, candidateList, systemExecutorOperationContext));
            }
        }

        return ffResult;
    }
}