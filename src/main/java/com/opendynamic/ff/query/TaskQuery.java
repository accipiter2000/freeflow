package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfTaskService;
import com.opendynamic.ff.vo.Task;

/**
 * 任务查询类。
 */
@Service
public class TaskQuery {
    private final FfTaskService ffTaskService;

    private String taskId; // 任务ID。
    private List<String> taskIdList; // 任务ID列表。
    private String previousTaskId;// 前一个任务ID。
    private List<String> previousTaskIdList;// 前一个任务ID列表。
    private String taskType; // 任务类型。
    private List<String> taskTypeList; // 任务类型列表。
    private String assignee;// 办理人。
    private List<String> assigneeList;// 办理人列表。
    private String assigneeName;// 办理人名称。
    private List<String> assigneeNameList;// 办理人名称列表。
    private String action;// 业务行为。
    private List<String> actionList;// 业务行为列表。
    private Date fromDueDate;// 起始截止日期。
    private Date toDueDate;// 截止截止日期。
    private String claim;// 认领。
    private List<String> claimList;// 认领列表。
    private String forwardable;// 可转发。
    private List<String> forwardableList;// 可转发列表。
    private Integer priority;// 优先级。
    private List<Integer> priorityList;// 优先级列表。
    private String forwardStatus;// 转发状态。
    private List<String> forwardStatusList;// 转发状态列表。
    private String taskEndUser;// 任务完成人员。
    private List<String> taskEndUserList;// 任务完成人员列表。
    private String taskEndUserName; // 任务完成人员名称。
    private List<String> taskEndUserNameList; // 任务完成人员名称列表。
    private Date fromTaskEndDate; // 起始任务完成日期。
    private Date toTaskEndDate; // 截止任务完成日期。
    private String taskStatus;// 任务状态。
    private List<String> taskStatusList;// 任务状态列表。
    private Date fromCreationDate;// 起始创建日期。
    private Date toCreationDate;// 截止创建日期列表。
    private String nodeId; // 节点ID。
    private List<String> nodeIdList; // 节点ID列表。
    private String parentNodeId;// 上级节点ID。
    private List<String> parentNodeIdList;// 上级节点ID列表。
    private String previousNodeIds;// 前节点IDs。
    private String lastCompleteNodeIds;// 最后完成节点IDs。
    private String subProcDefId; // 子流程定义ID。
    private List<String> subProcDefIdList; // 子流程定义ID列表。
    private String adjustSubProcDefId; // 调整子流程定义ID。
    private List<String> adjustSubProcDefIdList; // 调整子流程定义ID列表。
    private String nodeType;// 节点类型。
    private List<String> nodeTypeList;// 节点类型列表。
    private String nodeCode;// 节点编码。
    private List<String> nodeCodeList;// 节点编码列表。
    private String nodeName;// 节点名称。
    private List<String> nodeNameList;// 节点名称列表。
    private String parentNodeCode; // 上级节点编码。
    private List<String> parentNodeCodeList; // 上级节点编码列表。
    private String nodeEndUser;// 节点完成人员。
    private List<String> nodeEndUserList;// 节点完成人员列表。
    private String nodeEndUserName;// 节点完成人员名称。
    private List<String> nodeEndUserNameList;// 节点完成人员名称列表。
    private Date fromNodeEndDate; // 起始节点完成日期。
    private Date toNodeEndDate; // 截止节点完成日期。
    private String isolateSubProcDefCode; // 独立子流程定义编码。
    private List<String> isolateSubProcDefCodeList; // 独立子流程定义编码列表。
    private String isolateSubProcStatus;// 独立子流程状态。
    private List<String> isolateSubProcStatusList;// 独立子流程状态列表。
    private String nodeStatus;// 节点状态。
    private List<String> nodeStatusList;// 节点状态列表。
    private Date fromNodeCreationDate;// 起始节点创建日期。
    private Date toNodeCreationDate;// 截止节点创建日期。
    private String procId;// 流程ID。
    private List<String> procIdList;// 流程ID列表。
    private String adjustProcDefId; // 调整流程定义ID。
    private List<String> adjustProcDefIdList; // 调整流程定义ID列表。
    private String isolateSubProcNodeId;// 独立子流程所属节点ID。
    private List<String> isolateSubProcNodeIdList;// 独立子流程所属节点ID列表。
    private String bizId; // 业务主键。
    private List<String> bizIdList; // 业务主键列表。
    private String bizType; // 业务类型。
    private List<String> bizTypeList; // 业务类型列表。
    private String bizCode;// 业务编码。
    private List<String> bizCodeList;// 业务编码列表。
    private String bizName;// 业务名称。
    private List<String> bizNameList;// 业务名称列表。
    private String bizDesc;// 业务备注。
    private List<String> bizDescList;// 业务备注列表。
    private String procStartUser;// 流程开始人员。
    private List<String> procStartUserList;// 流程开始人员列表。
    private String procStartUserName; // 流程开始人员名称。
    private List<String> procStartUserNameList; // 流程开始人员名称列表。
    private String procEndUser; // 流程完成人员。
    private List<String> procEndUserList; // 流程完成人员列表。
    private String procEndUserName;// 流程完成人员名称。
    private List<String> procEndUserNameList;// 流程完成人员名称列表。
    private Date fromProcEndDate;// 起始流程完成日期。
    private Date toProcEndDate;// 截止流程完成日期。
    private String procStatus; // 流程状态。
    private List<String> procStatusList; // 流程状态列表。
    private Date fromProcCreationDate; // 起始流程创建日期。
    private Date toProcCreationDate; // 截止流程创建日期。
    private String procDefId;// 流程定义ID。
    private List<String> procDefIdList;// 流程定义ID列表。
    private String procDefCode;// 流程定义编码。
    private List<String> procDefCodeList;// 流程定义编码列表。
    private String procDefName;// 流程定义名称。
    private List<String> procDefNameList;// 流程定义名称列表。
    private String procDefCat;// 流程定义分类。
    private List<String> procDefCatList;// 流程定义分类列表。
    private Integer version;// 版本。
    private List<Integer> versionList;// 版本列表。
    private String procDefStatus;// 流程定义状态。
    private List<String> procDefStatusList;// 流程定义状态列表。
    private Boolean emptyParentNode;// 空上级节点编码。
    private Boolean emptyPreviousNodes;// 空前节点IDs。
    private Boolean emptyLastCompleteNodes;// 空最后完成节点IDs。
    private Integer page;// 页。默认为1。
    private Integer limit;// 每页数据数量。默认为-1(全部)。
    private String dataScope;// 数据范围。

    public TaskQuery(FfTaskService ffTaskService) {
        super();
        this.ffTaskService = ffTaskService;
    }

    /**
     * 设置任务ID。
     * 
     * @param taskId
     *        任务ID。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    /**
     * 设置任务ID列表。
     * 
     * @param taskIdList
     *        任务ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setTaskIdList(List<String> taskIdList) {
        this.taskIdList = taskIdList;
        return this;
    }

    /**
     * 设置前一个任务ID。
     * 
     * @param previousTaskId
     *        前一个任务ID。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setPreviousTaskId(String previousTaskId) {
        this.previousTaskId = previousTaskId;
        return this;
    }

    /**
     * 设置前一个任务ID列表。
     * 
     * @param previousTaskIdList
     *        前一个任务ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setPreviousTaskIdList(List<String> previousTaskIdList) {
        this.previousTaskIdList = previousTaskIdList;
        return this;
    }

    /**
     * 设置任务类型。
     * 
     * @param taskType
     *        任务类型。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setTaskType(String taskType) {
        this.taskType = taskType;
        return this;
    }

    /**
     * 设置任务类型列表。
     * 
     * @param taskTypeList
     *        任务类型列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setTaskTypeList(List<String> taskTypeList) {
        this.taskTypeList = taskTypeList;
        return this;
    }

    /**
     * 设置办理人。
     * 
     * @param assignee
     *        办理人。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setAssignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    /**
     * 设置办理人列表。
     * 
     * @param assigneeList
     *        办理人列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setAssigneeList(List<String> assigneeList) {
        this.assigneeList = assigneeList;
        return this;
    }

    /**
     * 设置办理人名称。
     * 
     * @param assigneeName
     *        办理人名称。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
        return this;
    }

    /**
     * 设置办理人名称列表。
     * 
     * @param assigneeNameList
     *        办理人名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setAssigneeNameList(List<String> assigneeNameList) {
        this.assigneeNameList = assigneeNameList;
        return this;
    }

    /**
     * 设置业务行为。
     * 
     * @param action
     *        业务行为。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setAction(String action) {
        this.action = action;
        return this;
    }

    /**
     * 设置业务行为列表。
     * 
     * @param actionList
     *        业务行为列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setActionList(List<String> actionList) {
        this.actionList = actionList;
        return this;
    }

    /**
     * 设置起始截止日期。
     * 
     * @param fromDueDate
     *        起始截止日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setFromDueDate(Date fromDueDate) {
        this.fromDueDate = fromDueDate;
        return this;
    }

    /**
     * 设置截止截止日期。
     * 
     * @param toDueDate
     *        截止截止日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setToDueDate(Date toDueDate) {
        this.toDueDate = toDueDate;
        return this;
    }

    /**
     * 设置认领。
     * 
     * @param claim
     *        认领。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setClaim(String claim) {
        this.claim = claim;
        return this;
    }

    /**
     * 设置认领列表。
     * 
     * @param claimList
     *        认领列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setClaimList(List<String> claimList) {
        this.claimList = claimList;
        return this;
    }

    /**
     * 设置可转发。
     * 
     * @param forwardable
     *        可转发。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setForwardable(String forwardable) {
        this.forwardable = forwardable;
        return this;
    }

    /**
     * 设置可转发列表。
     * 
     * @param forwardableList
     *        可转发列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setForwardableList(List<String> forwardableList) {
        this.forwardableList = forwardableList;
        return this;
    }

    /**
     * 设置优先级。
     * 
     * @param priority
     *        优先级。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    /**
     * 设置优先级列表。
     * 
     * @param priorityList
     *        优先级列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setPriorityList(List<Integer> priorityList) {
        this.priorityList = priorityList;
        return this;
    }

    /**
     * 设置转发状态。
     * 
     * @param forwardStatus
     *        转发状态。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setForwardStatus(String forwardStatus) {
        this.forwardStatus = forwardStatus;
        return this;
    }

    /**
     * 设置转发状态列表。
     * 
     * @param forwardStatusList
     *        转发状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setForwardStatusList(List<String> forwardStatusList) {
        this.forwardStatusList = forwardStatusList;
        return this;
    }

    /**
     * 设置任务完成人员。
     * 
     * @param taskEndUser
     *        任务完成人员。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setTaskEndUser(String taskEndUser) {
        this.taskEndUser = taskEndUser;
        return this;
    }

    /**
     * 设置任务完成人员列表。
     * 
     * @param taskEndUserList
     *        任务完成人员列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setTaskEndUserList(List<String> taskEndUserList) {
        this.taskEndUserList = taskEndUserList;
        return this;
    }

    /**
     * 设置任务完成人员名称。
     * 
     * @param taskEndUserName
     *        任务完成人员名称。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setTaskEndUserName(String taskEndUserName) {
        this.taskEndUserName = taskEndUserName;
        return this;
    }

    /**
     * 设置任务完成人员名称列表。
     * 
     * @param taskEndUserNameList
     *        任务完成人员名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setTaskEndUserNameList(List<String> taskEndUserNameList) {
        this.taskEndUserNameList = taskEndUserNameList;
        return this;
    }

    /**
     * 设置起始任务完成日期。
     * 
     * @param fromTaskEndDate
     *        起始任务完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setFromTaskEndDate(Date fromTaskEndDate) {
        this.fromTaskEndDate = fromTaskEndDate;
        return this;
    }

    /**
     * 设置截止任务完成日期。
     * 
     * @param toTaskEndDate
     *        截止任务完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setToTaskEndDate(Date toTaskEndDate) {
        this.toTaskEndDate = toTaskEndDate;
        return this;
    }

    /**
     * 设置任务状态。
     * 
     * @param taskStatus
     *        任务状态。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }

    /**
     * 设置任务状态列表。
     * 
     * @param taskStatusList
     *        任务状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setTaskStatusList(List<String> taskStatusList) {
        this.taskStatusList = taskStatusList;
        return this;
    }

    /**
     * 设置起始创建日期。
     * 
     * @param fromCreationDate
     *        起始创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
        return this;
    }

    /**
     * 设置截止创建日期列表。
     * 
     * @param toCreationDate
     *        截止创建日期列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
        return this;
    }

    /**
     * 设置节点ID。
     * 
     * @param nodeId
     *        节点ID。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * 设置节点ID列表。
     * 
     * @param nodeIdList
     *        节点ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeIdList(List<String> nodeIdList) {
        this.nodeIdList = nodeIdList;
        return this;
    }

    /**
     * 设置上级节点ID。
     * 
     * @param parentNodeId
     *        上级节点ID。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
        return this;
    }

    /**
     * 设置上级节点ID列表。
     * 
     * @param parentNodeIdList
     *        上级节点ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setParentNodeIdList(List<String> parentNodeIdList) {
        this.parentNodeIdList = parentNodeIdList;
        return this;
    }

    /**
     * 设置前节点IDs。
     * 
     * @param previousNodeIds
     *        前节点IDs。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setPreviousNodeIds(String previousNodeIds) {
        this.previousNodeIds = previousNodeIds;
        return this;
    }

    /**
     * 设置最后完成节点IDs。
     * 
     * @param lastCompleteNodeIds
     *        最后完成节点IDs。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setLastCompleteNodeIds(String lastCompleteNodeIds) {
        this.lastCompleteNodeIds = lastCompleteNodeIds;
        return this;
    }

    /**
     * 设置子流程定义ID。
     * 
     * @param subProcDefId
     *        子流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setSubProcDefId(String subProcDefId) {
        this.subProcDefId = subProcDefId;
        return this;
    }

    /**
     * 设置子流程定义ID列表。
     * 
     * @param subProcDefIdList
     *        子流程定义ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setSubProcDefIdList(List<String> subProcDefIdList) {
        this.subProcDefIdList = subProcDefIdList;
        return this;
    }

    /**
     * 设置调整子流程定义ID。
     * 
     * @param adjustSubProcDefId
     *        调整子流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setAdjustSubProcDefId(String adjustSubProcDefId) {
        this.adjustSubProcDefId = adjustSubProcDefId;
        return this;
    }

    /**
     * 设置调整子流程定义ID列表。
     * 
     * @param adjustSubProcDefIdList
     *        调整子流程定义ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setAdjustSubProcDefIdList(List<String> adjustSubProcDefIdList) {
        this.adjustSubProcDefIdList = adjustSubProcDefIdList;
        return this;
    }

    /**
     * 设置节点类型。
     * 
     * @param nodeType
     *        节点类型。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeType(String nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    /**
     * 设置节点类型列表。
     * 
     * @param nodeTypeList
     *        节点类型列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeTypeList(List<String> nodeTypeList) {
        this.nodeTypeList = nodeTypeList;
        return this;
    }

    /**
     * 设置节点编码。
     * 
     * @param nodeCode
     *        节点编码。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
        return this;
    }

    /**
     * 设置节点编码列表。
     * 
     * @param nodeCodeList
     *        节点编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeCodeList(List<String> nodeCodeList) {
        this.nodeCodeList = nodeCodeList;
        return this;
    }

    /**
     * 设置节点名称。
     * 
     * @param nodeName
     *        节点名称。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    /**
     * 设置节点名称列表。
     * 
     * @param nodeNameList
     *        节点名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeNameList(List<String> nodeNameList) {
        this.nodeNameList = nodeNameList;
        return this;
    }

    /**
     * 设置上级节点编码。
     * 
     * @param parentNodeCode
     *        上级节点编码。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setParentNodeCode(String parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
        return this;
    }

    /**
     * 设置上级节点编码列表。
     * 
     * @param parentNodeCodeList
     *        上级节点编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setParentNodeCodeList(List<String> parentNodeCodeList) {
        this.parentNodeCodeList = parentNodeCodeList;
        return this;
    }

    /**
     * 设置节点完成人员。
     * 
     * @param nodeEndUser
     *        节点完成人员。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeEndUser(String nodeEndUser) {
        this.nodeEndUser = nodeEndUser;
        return this;
    }

    /**
     * 设置节点完成人员列表。
     * 
     * @param nodeEndUserList
     *        节点完成人员列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeEndUserList(List<String> nodeEndUserList) {
        this.nodeEndUserList = nodeEndUserList;
        return this;
    }

    /**
     * 设置节点完成人员名称。
     * 
     * @param nodeEndUserName
     *        节点完成人员名称。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeEndUserName(String nodeEndUserName) {
        this.nodeEndUserName = nodeEndUserName;
        return this;
    }

    /**
     * 设置节点完成人员名称列表。
     * 
     * @param nodeEndUserNameList
     *        节点完成人员名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeEndUserNameList(List<String> nodeEndUserNameList) {
        this.nodeEndUserNameList = nodeEndUserNameList;
        return this;
    }

    /**
     * 设置起始节点完成日期。
     * 
     * @param fromNodeEndDate
     *        起始节点完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setFromNodeEndDate(Date fromNodeEndDate) {
        this.fromNodeEndDate = fromNodeEndDate;
        return this;
    }

    /**
     * 设置截止节点完成日期。
     * 
     * @param toNodeEndDate
     *        截止节点完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setToNodeEndDate(Date toNodeEndDate) {
        this.toNodeEndDate = toNodeEndDate;
        return this;
    }

    /**
     * 设置独立子流程定义编码。
     * 
     * @param isolateSubProcDefCode
     *        独立子流程定义编码。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setIsolateSubProcDefCode(String isolateSubProcDefCode) {
        this.isolateSubProcDefCode = isolateSubProcDefCode;
        return this;
    }

    /**
     * 设置独立子流程定义编码列表。
     * 
     * @param isolateSubProcDefCodeList
     *        独立子流程定义编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setIsolateSubProcDefCodeList(List<String> isolateSubProcDefCodeList) {
        this.isolateSubProcDefCodeList = isolateSubProcDefCodeList;
        return this;
    }

    /**
     * 设置独立子流程状态。
     * 
     * @param isolateSubProcStatus
     *        独立子流程状态。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setIsolateSubProcStatus(String isolateSubProcStatus) {
        this.isolateSubProcStatus = isolateSubProcStatus;
        return this;
    }

    /**
     * 设置独立子流程状态列表。
     * 
     * @param isolateSubProcStatusList
     *        独立子流程状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setIsolateSubProcStatusList(List<String> isolateSubProcStatusList) {
        this.isolateSubProcStatusList = isolateSubProcStatusList;
        return this;
    }

    /**
     * 设置节点状态。
     * 
     * @param nodeStatus
     *        节点状态。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
        return this;
    }

    /**
     * 设置节点状态列表。
     * 
     * @param nodeStatusList
     *        节点状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setNodeStatusList(List<String> nodeStatusList) {
        this.nodeStatusList = nodeStatusList;
        return this;
    }

    /**
     * 设置起始节点创建日期。
     * 
     * @param fromNodeCreationDate
     *        起始节点创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setFromNodeCreationDate(Date fromNodeCreationDate) {
        this.fromNodeCreationDate = fromNodeCreationDate;
        return this;
    }

    /**
     * 设置截止节点创建日期。
     * 
     * @param toNodeCreationDate
     *        截止节点创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setToNodeCreationDate(Date toNodeCreationDate) {
        this.toNodeCreationDate = toNodeCreationDate;
        return this;
    }

    /**
     * 设置流程ID。
     * 
     * @param procId
     *        流程ID。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    /**
     * 设置流程ID列表。
     * 
     * @param procIdList
     *        流程ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcIdList(List<String> procIdList) {
        this.procIdList = procIdList;
        return this;
    }

    /**
     * 设置调整流程定义ID。
     * 
     * @param adjustProcDefId
     *        调整流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setAdjustProcDefId(String adjustProcDefId) {
        this.adjustProcDefId = adjustProcDefId;
        return this;
    }

    /**
     * 设置调整流程定义ID列表。
     * 
     * @param adjustProcDefIdList
     *        调整流程定义ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setAdjustProcDefIdList(List<String> adjustProcDefIdList) {
        this.adjustProcDefIdList = adjustProcDefIdList;
        return this;
    }

    /**
     * 设置独立子流程所属节点ID。
     * 
     * @param isolateSubProcNodeId
     *        独立子流程所属节点ID。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setIsolateSubProcNodeId(String isolateSubProcNodeId) {
        this.isolateSubProcNodeId = isolateSubProcNodeId;
        return this;
    }

    /**
     * 设置独立子流程所属节点ID列表。
     * 
     * @param isolateSubProcNodeIdList
     *        独立子流程所属节点ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setIsolateSubProcNodeIdList(List<String> isolateSubProcNodeIdList) {
        this.isolateSubProcNodeIdList = isolateSubProcNodeIdList;
        return this;
    }

    /**
     * 设置业务主键。
     * 
     * @param bizId
     *        业务主键。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    /**
     * 设置业务主键列表。
     * 
     * @param bizIdList
     *        业务主键列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setBizIdList(List<String> bizIdList) {
        this.bizIdList = bizIdList;
        return this;
    }

    /**
     * 设置业务类型。
     * 
     * @param bizType
     *        业务类型。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    /**
     * 设置业务类型列表。
     * 
     * @param bizTypeList
     *        业务类型列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setBizTypeList(List<String> bizTypeList) {
        this.bizTypeList = bizTypeList;
        return this;
    }

    /**
     * 设置业务编码。
     * 
     * @param bizCode
     *        业务编码。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    /**
     * 设置业务编码列表。
     * 
     * @param bizCodeList
     *        业务编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setBizCodeList(List<String> bizCodeList) {
        this.bizCodeList = bizCodeList;
        return this;
    }

    /**
     * 设置业务名称。
     * 
     * @param bizName
     *        业务名称。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setBizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    /**
     * 设置业务名称列表。
     * 
     * @param bizNameList
     *        业务名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setBizNameList(List<String> bizNameList) {
        this.bizNameList = bizNameList;
        return this;
    }

    /**
     * 设置业务备注。
     * 
     * @param bizDesc
     *        业务备注。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
        return this;
    }

    /**
     * 设置业务备注列表。
     * 
     * @param bizDescList
     *        业务备注列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setBizDescList(List<String> bizDescList) {
        this.bizDescList = bizDescList;
        return this;
    }

    /**
     * 设置流程开始人员。
     * 
     * @param procStartUser
     *        流程开始人员。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcStartUser(String procStartUser) {
        this.procStartUser = procStartUser;
        return this;
    }

    /**
     * 设置流程开始人员列表。
     * 
     * @param procStartUserList
     *        流程开始人员列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcStartUserList(List<String> procStartUserList) {
        this.procStartUserList = procStartUserList;
        return this;
    }

    /**
     * 设置流程开始人员名称。
     * 
     * @param procStartUserName
     *        流程开始人员名称。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcStartUserName(String procStartUserName) {
        this.procStartUserName = procStartUserName;
        return this;
    }

    /**
     * 设置流程开始人员名称列表。
     * 
     * @param procStartUserNameList
     *        流程开始人员名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcStartUserNameList(List<String> procStartUserNameList) {
        this.procStartUserNameList = procStartUserNameList;
        return this;
    }

    /**
     * 设置流程完成人员。
     * 
     * @param procEndUser
     *        流程完成人员。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcEndUser(String procEndUser) {
        this.procEndUser = procEndUser;
        return this;
    }

    /**
     * 设置流程完成人员列表。
     * 
     * @param procEndUserList
     *        流程完成人员列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcEndUserList(List<String> procEndUserList) {
        this.procEndUserList = procEndUserList;
        return this;
    }

    /**
     * 设置流程完成人员名称。
     * 
     * @param procEndUserName
     *        流程完成人员名称。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcEndUserName(String procEndUserName) {
        this.procEndUserName = procEndUserName;
        return this;
    }

    /**
     * 设置流程完成人员名称列表。
     * 
     * @param procEndUserNameList
     *        流程完成人员名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcEndUserNameList(List<String> procEndUserNameList) {
        this.procEndUserNameList = procEndUserNameList;
        return this;
    }

    /**
     * 设置起始流程完成日期。
     * 
     * @param fromProcEndDate
     *        起始流程完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setFromProcEndDate(Date fromProcEndDate) {
        this.fromProcEndDate = fromProcEndDate;
        return this;
    }

    /**
     * 设置截止流程完成日期。
     * 
     * @param toProcEndDate
     *        截止流程完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setToProcEndDate(Date toProcEndDate) {
        this.toProcEndDate = toProcEndDate;
        return this;
    }

    /**
     * 设置流程状态。
     * 
     * @param procStatus
     *        流程状态。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcStatus(String procStatus) {
        this.procStatus = procStatus;
        return this;
    }

    /**
     * 设置流程状态列表。
     * 
     * @param procStatusList
     *        流程状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcStatusList(List<String> procStatusList) {
        this.procStatusList = procStatusList;
        return this;
    }

    /**
     * 设置起始流程创建日期。
     * 
     * @param fromProcCreationDate
     *        起始流程创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setFromProcCreationDate(Date fromProcCreationDate) {
        this.fromProcCreationDate = fromProcCreationDate;
        return this;
    }

    /**
     * 设置截止流程创建日期。
     * 
     * @param toProcCreationDate
     *        截止流程创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setToProcCreationDate(Date toProcCreationDate) {
        this.toProcCreationDate = toProcCreationDate;
        return this;
    }

    /**
     * 设置流程定义ID。
     * 
     * @param procDefId
     *        流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcDefId(String procDefId) {
        this.procDefId = procDefId;
        return this;
    }

    /**
     * 设置流程定义ID列表。
     * 
     * @param procDefIdList
     *        流程定义ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcDefIdList(List<String> procDefIdList) {
        this.procDefIdList = procDefIdList;
        return this;
    }

    /**
     * 设置流程定义编码。
     * 
     * @param procDefCode
     *        流程定义编码。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
        return this;
    }

    /**
     * 设置流程定义编码列表。
     * 
     * @param procDefCodeList
     *        流程定义编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcDefCodeList(List<String> procDefCodeList) {
        this.procDefCodeList = procDefCodeList;
        return this;
    }

    /**
     * 设置流程定义名称。
     * 
     * @param procDefName
     *        流程定义名称。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcDefName(String procDefName) {
        this.procDefName = procDefName;
        return this;
    }

    /**
     * 设置流程定义名称列表。
     * 
     * @param procDefNameList
     *        流程定义名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcDefNameList(List<String> procDefNameList) {
        this.procDefNameList = procDefNameList;
        return this;
    }

    /**
     * 设置流程定义分类。
     * 
     * @param procDefCat
     *        流程定义分类。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
        return this;
    }

    /**
     * 设置流程定义分类列表。
     * 
     * @param procDefCatList
     *        流程定义分类列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcDefCatList(List<String> procDefCatList) {
        this.procDefCatList = procDefCatList;
        return this;
    }

    /**
     * 设置版本。
     * 
     * @param version
     *        版本。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * 设置版本列表。
     * 
     * @param versionList
     *        版本列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setVersionList(List<Integer> versionList) {
        this.versionList = versionList;
        return this;
    }

    /**
     * 设置流程定义状态。
     * 
     * @param procDefStatus
     *        流程定义状态。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
        return this;
    }

    /**
     * 设置流程定义状态列表。
     * 
     * @param procDefStatusList
     *        流程定义状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setProcDefStatusList(List<String> procDefStatusList) {
        this.procDefStatusList = procDefStatusList;
        return this;
    }

    /**
     * 设置空上级节点编码。
     * 
     * @param emptyParentNode
     *        空上级节点编码。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setEmptyParentNode(Boolean emptyParentNode) {
        this.emptyParentNode = emptyParentNode;
        return this;
    }

    /**
     * 设置空前节点IDs。
     * 
     * @param emptyPreviousNodes
     *        空前节点IDs。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setEmptyPreviousNodes(Boolean emptyPreviousNodes) {
        this.emptyPreviousNodes = emptyPreviousNodes;
        return this;
    }

    /**
     * 设置空最后完成节点IDs。
     * 
     * @param emptyLastCompleteNodes
     *        空最后完成节点IDs。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setEmptyLastCompleteNodes(Boolean emptyLastCompleteNodes) {
        this.emptyLastCompleteNodes = emptyLastCompleteNodes;
        return this;
    }

    /**
     * 设置页。默认为1。
     * 
     * @param page
     *        页。默认为1。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 设置每页数据数量。默认为-1(全部)。
     * 
     * @param limit
     *        每页数据数量。默认为-1(全部)。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 设置数据范围。
     * 
     * @param dataScope
     *        数据范围。
     * @return 当前查询实例，支持链式调用。
     */
    public TaskQuery setDataScope(String dataScope) {
        this.dataScope = dataScope;
        return this;
    }

    /**
     * 查询对象列表。数据格式为Map。
     * 
     * @return Map类型数据列表。
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffTaskService.selectTask(taskId, taskIdList, previousTaskId, previousTaskIdList, taskType, taskTypeList, assignee, assigneeList, assigneeName, assigneeNameList, action, actionList, fromDueDate, toDueDate, claim, claimList, forwardable, forwardableList, priority, priorityList, forwardStatus, forwardStatusList, taskEndUser, taskEndUserList, taskEndUserName, taskEndUserNameList, fromTaskEndDate, toTaskEndDate, taskStatus, taskStatusList, fromCreationDate, toCreationDate, nodeId, nodeIdList, parentNodeId, parentNodeIdList, previousNodeIds, lastCompleteNodeIds, subProcDefId, subProcDefIdList, adjustSubProcDefId, adjustSubProcDefIdList, nodeType, nodeTypeList, nodeCode, nodeCodeList, nodeName, nodeNameList, parentNodeCode, parentNodeCodeList, nodeEndUser, nodeEndUserList,
                nodeEndUserName, nodeEndUserNameList, fromNodeEndDate, toNodeEndDate, isolateSubProcDefCode, isolateSubProcDefCodeList, isolateSubProcStatus, isolateSubProcStatusList, nodeStatus, nodeStatusList, fromNodeCreationDate, toNodeCreationDate, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromProcCreationDate, toProcCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName, procDefNameList,
                procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, page, limit, dataScope);
    }

    /**
     * 查询单个对象。数据格式为Map。
     * 
     * @return 单个Map类型数据。
     */
    public Map<String, Object> queryForMap() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * 查询对象列表。数据格式为实体Bean。
     * 
     * @return Bean类型数据列表。
     */
    public List<Task> queryForObjectList() {
        List<Map<String, Object>> resultList = queryForMapList();
        List<Task> nodeList = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
            nodeList.add(new Task(result));
        }

        return nodeList;
    }

    /**
     * 查询单个对象。数据格式为实体Bean。
     * 
     * @return 单个Bean类型数据。
     */
    public Task queryForObject() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return new Task(result.get(0));
        }
        else {
            return null;
        }
    }

    /**
     * 查询总数。
     * 
     * @return 总数。
     */
    public int count() {
        return ffTaskService.countTask(taskId, taskIdList, previousTaskId, previousTaskIdList, taskType, taskTypeList, assignee, assigneeList, assigneeName, assigneeNameList, action, actionList, fromDueDate, toDueDate, claim, claimList, forwardable, forwardableList, priority, priorityList, forwardStatus, forwardStatusList, taskEndUser, taskEndUserList, taskEndUserName, taskEndUserNameList, fromTaskEndDate, toTaskEndDate, taskStatus, taskStatusList, fromCreationDate, toCreationDate, nodeId, nodeIdList, parentNodeId, parentNodeIdList, previousNodeIds, lastCompleteNodeIds, subProcDefId, subProcDefIdList, adjustSubProcDefId, adjustSubProcDefIdList, nodeType, nodeTypeList, nodeCode, nodeCodeList, nodeName, nodeNameList, parentNodeCode, parentNodeCodeList, nodeEndUser, nodeEndUserList,
                nodeEndUserName, nodeEndUserNameList, fromNodeEndDate, toNodeEndDate, isolateSubProcDefCode, isolateSubProcDefCodeList, isolateSubProcStatus, isolateSubProcStatusList, nodeStatus, nodeStatusList, fromNodeCreationDate, toNodeCreationDate, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromProcCreationDate, toProcCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName, procDefNameList,
                procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, dataScope);
    }
}