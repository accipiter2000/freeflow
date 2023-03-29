package com.opendynamic.ff.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.opendynamic.ff.service.FfNodeVarService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.service.FfTaskService;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.FfUser;
import com.opendynamic.ff.vo.FlowDef;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.Proc;
import com.opendynamic.ff.vo.ProcDef;
import com.opendynamic.ff.vo.Task;

import de.odysseus.el.util.SimpleContext;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CenterFarwardTaskNodeHandler implements NodeHandler {
    @Autowired
    private FfService ffService;
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfTaskService ffTaskService;
    @Autowired
    private FfNodeVarService ffNodeVarService;
    @Autowired
    private FfHelper ffHelper;

    private Pattern outerPattern = Pattern.compile(FfService.CENTER_FORWARD_STEP + " *== *\\d+");
    private Pattern innerPattern = Pattern.compile("\\d+");

    @Override
    public String getNodeType() {
        return FfService.NODE_TYPE_CENTER_FORWARD_TASK;
    }

    @SuppressWarnings("unchecked")
    @Override
    public FfResult insertNodeByNodeDef(NodeDef nodeDef, Node branchNode, String previousNodeIds, String triggerOperation, String executor) {
        FfResult ffResult = new FfResult();// 返回值

        Proc proc = ffService.loadProc(branchNode.getProcId());
        Node centerFarwardTaskNode;

        // 新增节点
        String centerFarwardTaskNodeId = OdUtils.getUuid();
        ffNodeService.insertNode(centerFarwardTaskNodeId, branchNode.getNodeId(), branchNode.getProcId(), previousNodeIds, null, branchNode.getSubProcDefId(), branchNode.getAdjustSubProcDefId(), FfService.NODE_TYPE_CENTER_FORWARD_TASK, nodeDef.getNodeCode(), nodeDef.getNodeName(), nodeDef.getParentNodeCode(), nodeDef.getAssignee(), nodeDef.getCandidate(), nodeDef.getAction(), nodeDef.getDueDate(), nodeDef.getCompleteExpression(), nodeDef.getCompleteReturn(), nodeDef.getExclusive(), nodeDef.getForwardable(), nodeDef.getAutoCompleteSameAssignee(), nodeDef.getAutoCompleteEmptyAssignee(), nodeDef.getInform(), nodeDef.getPriority(), null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
        centerFarwardTaskNode = ffService.loadNode(centerFarwardTaskNodeId);
        ffResult.addCreateNode(centerFarwardTaskNode);

        // 更新中心转发步骤
        int nextCenterForwardStep = getNextCenterForwardStep(centerFarwardTaskNode);
        Map<String, Object> nodeVarMap = new HashMap<String, Object>();
        nodeVarMap.put(FfService.CENTER_FORWARD_STEP, String.valueOf(nextCenterForwardStep));
        ffNodeVarService.updateNodeVar(branchNode.getNodeId(), nodeVarMap);

        // 新增任务
        ExpressionFactory expressionFactory = ffService.getExpressionFactory();
        SimpleContext simpleContext = ffService.getSimpleContext(ffService.createNodeVarQuery().setNodeId(branchNode.getNodeId()).setRecursive(true).queryForMap());
        simpleContext.setVariable("proc", expressionFactory.createValueExpression(proc, Object.class));
        simpleContext.setVariable("branch", expressionFactory.createValueExpression(branchNode, Object.class));
        simpleContext.setVariable("node", expressionFactory.createValueExpression(centerFarwardTaskNode, Object.class));
        // 计算办理人
        ValueExpression expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getAssignee(), Object.class);
        Object object = expression.getValue(simpleContext);// 获取办理人
        List<FfUser> assigneeList;
        if (object instanceof List) {
            assigneeList = (List<FfUser>) object;
        }
        else {
            assigneeList = new ArrayList<>();
            if (StringUtils.isNotEmpty((String) object)) {
                String[] assignees = ((String) object).split(",");
                for (int i = 0; i < assignees.length; i++) {
                    FfUser ffUser = new FfUser();
                    ffUser.setId(assignees[i]);
                    ffUser.setUserName(ffHelper.getUserName(assignees[i]));
                    assigneeList.add(ffUser);
                }
            }
        }
        Task task;
        List<Task> createTaskList = new ArrayList<>();
        // 为每个办理人新增任务
        for (FfUser assignee : assigneeList) {
            String taskId = OdUtils.getUuid();
            String action = null;
            Date dueDate = null;

            task = new Task();
            task.setTaskId(taskId);
            task.setNodeId(centerFarwardTaskNodeId);
            task.setTaskType(FfService.TASK_TYPE_TASK);
            task.setAssignee(assignee.getId());
            task.setAssigneeName(assignee.getUserName());
            task.setPriority(nodeDef.getPriority());
            task.setForwardable(nodeDef.getForwardable());
            task.setForwardStatus(FfService.FORWARD_STATUS_NOT_FORWARDED);
            task.setTaskStatus(FfService.TASK_STATUS_ACTIVE);
            task.setCreationDate(new Date());
            simpleContext.setVariable("task", expressionFactory.createValueExpression(task, Object.class));
            if (StringUtils.isNotEmpty(nodeDef.getAction())) {// 解析JUEL,获取业务系统操作
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getAction(), String.class);
                action = (String) expression.getValue(simpleContext);
            }
            if (StringUtils.isNotEmpty(nodeDef.getDueDate())) {// 解析JUEL,获取节点截止日期
                expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getDueDate(), Date.class);
                dueDate = (Date) expression.getValue(simpleContext);
            }
            task.setAction(action);
            task.setDueDate(dueDate);

            ffService.insertTask(task, executor);
            task = ffService.loadTask(taskId);
            ffResult.addCreateTask(task);
            createTaskList.add(task);
        }

        String systemExecutor = FfService.USER_FF_SYSTEM;
        String systemExecutorName = ffHelper.getUserName(systemExecutor);
        // 自动完成通知节点
        if (FfService.BOOLEAN_TRUE.equals(nodeDef.getInform())) {
            ffResult.addAll(completeNode(centerFarwardTaskNode, previousNodeIds, FfService.OPERATION_COMPLETE, systemExecutor));
        }
        // 自动完成相同办理人任务
        if (FfService.BOOLEAN_TRUE.equals(nodeDef.getAutoCompleteSameAssignee())) {
            for (Task createTask : createTaskList) {
                if (ffService.createTaskQuery().setProcId(createTask.getProcId()).setAssigneeList(ffHelper.getAllUserIdList(createTask.getAssignee())).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_COMPLETE)).count() > 0) {
                    Date completeDate = new Date();
                    ffTaskService.updateTaskStatus(createTask.getTaskId(), systemExecutor, systemExecutorName, completeDate, FfService.TASK_STATUS_COMPLETE);// 完成任务
                    createTask.setExecutor(systemExecutor);
                    createTask.setExecutorName(systemExecutorName);
                    createTask.setCompleteDate(completeDate);
                    createTask.setTaskStatus(FfService.TASK_STATUS_COMPLETE);
                    ffResult.addCompleteTask(createTask);

                    if (centerFarwardTaskNode.getExclusive().equals(FfService.BOOLEAN_TRUE)) {// 排他处理
                        List<Task> remainActiveTaskList = ffService.createTaskQuery().setNodeId(centerFarwardTaskNode.getNodeId()).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE)).queryForObjectList();
                        for (Task remainActiveTask : remainActiveTaskList) {
                            ffTaskService.updateTaskStatus(remainActiveTask.getTaskId(), systemExecutor, systemExecutorName, completeDate, FfService.TASK_STATUS_TERMINATE);
                            remainActiveTask.setExecutor(systemExecutor);
                            remainActiveTask.setExecutorName(systemExecutorName);
                            remainActiveTask.setCompleteDate(completeDate);
                            remainActiveTask.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
                            ffResult.addTerminateTask(remainActiveTask);
                        }

                        ffResult.addAll(completeNode(centerFarwardTaskNode, previousNodeIds, FfService.OPERATION_COMPLETE, systemExecutor));
                        break;
                    }

                    ffResult.addAll(completeNode(centerFarwardTaskNode, previousNodeIds, FfService.OPERATION_COMPLETE, systemExecutor));
                }
            }
        }
        // 自动完成没有办理人节点
        if (createTaskList.size() == 0 && FfService.BOOLEAN_TRUE.equals(nodeDef.getAutoCompleteEmptyAssignee())) {
            ffResult.addAll(completeNode(centerFarwardTaskNode, previousNodeIds, FfService.OPERATION_COMPLETE, FfService.USER_FF_SYSTEM));
        }

        return ffResult;
    }

    @Override
    public FfResult completeNode(Node node, String previousNodeIds, String triggerOperation, String executor) {
        FfResult ffResult = new FfResult();// 返回值

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {// 如已经完成，直接返回
            return ffResult;
        }

        // 非通知节点需完成判断
        Map<String, Object> nodeVarMap = ffService.createNodeVarQuery().setNodeId(node.getNodeId()).setRecursive(true).queryForMap();// 获取节点变量
        nodeVarMap.put("proc", ffService.loadProc(node.getProcId()));
        nodeVarMap.put("branch", ffService.loadNode(node.getParentNodeId()));
        nodeVarMap.put("node", node);
        nodeVarMap.putAll(ffNodeService.getTaskStatistic(node.getNodeId()));// 获取节点任务完成信息
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
        FfResult ffResult = new FfResult();// 返回值

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_TERMINATE) || node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {
            return ffResult;
        }

        // 有并发子节点不能驳回
        if (ffService.createNodeQuery().setParentNodeId(node.getParentNodeId()).setPreviousNodeIds(node.getPreviousNodeIds()).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE, FfService.NODE_STATUS_SUSPEND, FfService.NODE_STATUS_COMPLETE)).count() > 1) {
            throw new RuntimeException("errors.cannotRejectInParallel");
        }

        // 完成节点
        String nodeEndUserName = ffHelper.getUserName(executor);
        Date nodeEndDate = new Date();
        ffNodeService.updateNodeStatus(node.getNodeId(), executor, nodeEndUserName, nodeEndDate, FfService.NODE_STATUS_TERMINATE);// 完成节点
        node.setNodeEndUser(executor);
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
                ffResult.addAll(ffService.getNodeHandler(previousNode.getNodeType()).activateNode(previousNode, null, FfService.OPERATION_REJECT, executor));
            }
        }
        else {// 递归驳回上级节点
            ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).rejectNode(parentNode, triggerOperation, executor));
        }

        return ffResult;
    }

    @Override
    public FfResult activateNode(Node node, String previousNodeIds, String triggerOperation, String executor) {
        FfResult ffResult = new FfResult();// 返回值

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

            if (FfService.OPERATION_ACTIVATE.equals(triggerOperation)) {
                Node parentNode = ffService.loadNode(node.getParentNodeId());// 递归激活上级节点
                if (parentNode != null) {
                    ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).activateNode(parentNode, node.getNodeId(), triggerOperation, executor));
                }
            }

            if (FfService.OPERATION_REJECT.equals(triggerOperation) && taskList.size() == 0 && FfService.BOOLEAN_TRUE.equals(node.getAutoCompleteEmptyAssignee())) {
                ffResult.addAll(rejectNode(node, FfService.OPERATION_REJECT, FfService.USER_FF_SYSTEM));
            }
        }

        return ffResult;
    }

    private int getNextCenterForwardStep(Node node) {
        Map<String, Object> nodeVarMap = ffService.createNodeVarQuery().setNodeId(node.getParentNodeId()).queryForMap();
        int currentCenterForwardStep = 0;
        if (nodeVarMap.get(FfService.CENTER_FORWARD_STEP) != null) {
            currentCenterForwardStep = Integer.parseInt((String) nodeVarMap.get(FfService.CENTER_FORWARD_STEP));
        }

        ProcDef procDef = ffService.getNodeProcDef(node); // 获取当前节点所属流程定义
        NodeDef nodeDef = procDef.getNodeDef(node.getNodeCode());
        List<? extends FlowDef> outgoingFlowDefList = nodeDef.getOutgoingFlowDefList();
        Matcher matcher;
        int nextStep = currentCenterForwardStep;
        int step;
        for (FlowDef flowDef : outgoingFlowDefList) {
            if (flowDef.getConditionExpression() == null) {
                continue;
            }
            matcher = outerPattern.matcher(flowDef.getConditionExpression());
            if (!matcher.find()) {
                continue;
            }
            matcher = innerPattern.matcher(matcher.group());
            if (!matcher.find()) {
                continue;
            }
            step = Integer.parseInt(matcher.group());
            if (step <= currentCenterForwardStep) {
                continue;
            }
            if (step < nextStep || nextStep == currentCenterForwardStep) {
                nextStep = step;
            }
        }

        if (nextStep == currentCenterForwardStep) {
            nextStep++;
        }

        return nextStep;
    }
}