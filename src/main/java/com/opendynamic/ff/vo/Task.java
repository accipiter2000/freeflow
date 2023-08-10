package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private String taskId; // 任务ID
    private String previousTaskId; // 前一个任务ID
    private String taskType; // 任务类型
    private String assignee; // 办理人
    private String assigneeName; // 办理人名称
    private String action; // 业务行为
    private Date dueDate; // 截止日期
    private String claim; // 认领
    private String forwardable; // 可转发
    private Integer priority; // 优先级
    private String forwardStatus; // 转发状态
    private String taskEndUser; // 任务完成人员
    private String taskEndUserName; // 任务完成人员名称
    private Date taskEndDate; // 任务完成日期
    private String taskStatus; // 任务状态
    private Date creationDate; // 创建日期
    private String nodeId; // 节点ID
    private String parentNodeId; // 上级节点ID
    private String previousNodeIds; // 前节点IDs
    private String lastCompleteNodeIds; // 最后完成节点IDs
    private String subProcDefId; // 子流程定义ID
    private String adjustSubProcDefId; // 调整子流程定义ID
    private String nodeType; // 节点类型
    private String nodeCode; // 节点编码
    private String nodeName; // 节点名称
    private String parentNodeCode; // 上级节点编码
    private String candidate; // 候选人
    private String completeExpression; // 完成表达式
    private String completeReturn; // 完成后返回前一个节点
    private String exclusive; // 排他
    private String autoCompleteSameAssignee; // 自动完成相同办理人任务
    private String autoCompleteEmptyAssignee; // 自动完成没有办理人节点
    private String inform; // 通知
    private String nodeEndUser; // 节点完成人员
    private String nodeEndUserName; // 节点完成人员名称
    private Date nodeEndDate; // 节点完成日期
    private String isolateSubProcDefCode; // 独立子流程定义编码
    private String isolateSubProcStatus; // 独立子流程状态
    private String nodeStatus; // 节点状态
    private Date nodeCreationDate; // 节点创建日期
    private String procId; // 流程ID
    private String adjustProcDefId; // 调整流程定义ID
    private String isolateSubProcNodeId; // 独立子流程所属节点ID
    private String bizId; // 业务主键
    private String bizType; // 业务类型
    private String bizCode; // 业务编码
    private String bizName; // 业务名称
    private String bizDesc; // 业务备注
    private String procStartUser; // 流程开始人员
    private String procStartUserName; // 流程开始人员名称
    private String procEndUser; // 流程完成人员
    private String procEndUserName; // 流程完成人员名称
    private Date procEndDate; // 流程完成日期
    private String procStatus; // 流程状态
    private Date procCreationDate; // 流程创建日期
    private String procDefId; // 流程定义ID
    private String procDefCode; // 流程定义编码
    private String procDefName; // 流程定义名称
    private String procDefCat; // 流程定义分类
    private Integer version; // 版本
    private String procDefStatus; // 流程定义状态

    public Task() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
     */
    public Task(Map<String, Object> data) {
        this.taskId = (String) data.get("TASK_ID_");
        this.previousTaskId = (String) data.get("PREVIOUS_TASK_ID_");
        this.taskType = (String) data.get("TASK_TYPE_");
        this.assignee = (String) data.get("ASSIGNEE_");
        this.assigneeName = (String) data.get("ASSIGNEE_NAME_");
        this.action = (String) data.get("ACTION_");
        this.dueDate = (Date) data.get("DUE_DATE_");
        this.claim = (String) data.get("CLAIM_");
        this.forwardable = (String) data.get("FORWARDABLE_");
        this.priority = (data.get("PRIORITY_") != null) ? (((BigDecimal) data.get("PRIORITY_")).intValue()) : null;
        this.forwardStatus = (String) data.get("FORWARD_STATUS_");
        this.taskEndUser = (String) data.get("TASK_END_USER_");
        this.taskEndUserName = (String) data.get("TASK_END_USER_NAME_");
        this.taskEndDate = (Date) data.get("TASK_END_DATE_");
        this.taskStatus = (String) data.get("TASK_STATUS_");
        this.creationDate = (Date) data.get("CREATION_DATE_");
        this.nodeId = (String) data.get("NODE_ID_");
        this.parentNodeId = (String) data.get("PARENT_NODE_ID_");
        this.previousNodeIds = (String) data.get("PREVIOUS_NODE_IDS_");
        this.lastCompleteNodeIds = (String) data.get("LAST_COMPLETE_NODE_IDS_");
        this.subProcDefId = (String) data.get("SUB_PROC_DEF_ID_");
        this.adjustSubProcDefId = (String) data.get("ADJUST_SUB_PROC_DEF_ID_");
        this.nodeType = (String) data.get("NODE_TYPE_");
        this.nodeCode = (String) data.get("NODE_CODE_");
        this.nodeName = (String) data.get("NODE_NAME_");
        this.parentNodeCode = (String) data.get("PARENT_NODE_CODE_");
        this.candidate = (String) data.get("CANDIDATE_");
        this.completeExpression = (String) data.get("COMPLETE_EXPRESSION_");
        this.completeReturn = (String) data.get("COMPLETE_RETURN_");
        this.exclusive = (String) data.get("EXCLUSIVE_");
        this.autoCompleteSameAssignee = (String) data.get("AUTO_COMPLETE_SAME_ASSIGNEE_");
        this.autoCompleteEmptyAssignee = (String) data.get("AUTO_COMPLETE_EMPTY_ASSIGNEE_");
        this.inform = (String) data.get("INFORM_");
        this.nodeEndUser = (String) data.get("NODE_END_USER_");
        this.nodeEndUserName = (String) data.get("NODE_END_USER_NAME_");
        this.nodeEndDate = (Date) data.get("NODE_END_DATE_");
        this.isolateSubProcDefCode = (String) data.get("ISOLATE_SUB_PROC_DEF_CODE_");
        this.isolateSubProcStatus = (String) data.get("ISOLATE_SUB_PROC_STATUS_");
        this.nodeStatus = (String) data.get("NODE_STATUS_");
        this.nodeCreationDate = (Date) data.get("NODE_CREATION_DATE_");
        this.procId = (String) data.get("PROC_ID_");
        this.adjustProcDefId = (String) data.get("ADJUST_PROC_DEF_ID_");
        this.isolateSubProcNodeId = (String) data.get("ISOLATE_SUB_PROC_NODE_ID_");
        this.bizId = (String) data.get("BIZ_ID_");
        this.bizType = (String) data.get("BIZ_TYPE_");
        this.bizCode = (String) data.get("BIZ_CODE_");
        this.bizName = (String) data.get("BIZ_NAME_");
        this.bizDesc = (String) data.get("BIZ_DESC_");
        this.procStartUser = (String) data.get("PROC_START_USER_");
        this.procStartUserName = (String) data.get("PROC_START_USER_NAME_");
        this.procEndUser = (String) data.get("PROC_END_USER_");
        this.procEndUserName = (String) data.get("PROC_END_USER_NAME_");
        this.procEndDate = (Date) data.get("PROC_END_DATE_");
        this.procStatus = (String) data.get("PROC_STATUS_");
        this.procCreationDate = (Date) data.get("PROC_CREATION_DATE_");
        this.procDefId = (String) data.get("PROC_DEF_ID_");
        this.procDefCode = (String) data.get("PROC_DEF_CODE_");
        this.procDefName = (String) data.get("PROC_DEF_NAME_");
        this.procDefCat = (String) data.get("PROC_DEF_CAT_");
        this.version = (data.get("VERSION_") != null) ? (((BigDecimal) data.get("VERSION_")).intValue()) : null;
        this.procDefStatus = (String) data.get("PROC_DEF_STATUS_");
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPreviousTaskId() {
        return previousTaskId;
    }

    public void setPreviousTaskId(String previousTaskId) {
        this.previousTaskId = previousTaskId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public String getForwardable() {
        return forwardable;
    }

    public void setForwardable(String forwardable) {
        this.forwardable = forwardable;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getForwardStatus() {
        return forwardStatus;
    }

    public void setForwardStatus(String forwardStatus) {
        this.forwardStatus = forwardStatus;
    }

    public String getTaskEndUser() {
        return taskEndUser;
    }

    public void setTaskEndUser(String taskEndUser) {
        this.taskEndUser = taskEndUser;
    }

    public String getTaskEndUserName() {
        return taskEndUserName;
    }

    public void setTaskEndUserName(String taskEndUserName) {
        this.taskEndUserName = taskEndUserName;
    }

    public Date getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public String getPreviousNodeIds() {
        return previousNodeIds;
    }

    public void setPreviousNodeIds(String previousNodeIds) {
        this.previousNodeIds = previousNodeIds;
    }

    public String getLastCompleteNodeIds() {
        return lastCompleteNodeIds;
    }

    public void setLastCompleteNodeIds(String lastCompleteNodeIds) {
        this.lastCompleteNodeIds = lastCompleteNodeIds;
    }

    public String getSubProcDefId() {
        return subProcDefId;
    }

    public void setSubProcDefId(String subProcDefId) {
        this.subProcDefId = subProcDefId;
    }

    public String getAdjustSubProcDefId() {
        return adjustSubProcDefId;
    }

    public void setAdjustSubProcDefId(String adjustSubProcDefId) {
        this.adjustSubProcDefId = adjustSubProcDefId;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getParentNodeCode() {
        return parentNodeCode;
    }

    public void setParentNodeCode(String parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getCompleteExpression() {
        return completeExpression;
    }

    public void setCompleteExpression(String completeExpression) {
        this.completeExpression = completeExpression;
    }

    public String getCompleteReturn() {
        return completeReturn;
    }

    public void setCompleteReturn(String completeReturn) {
        this.completeReturn = completeReturn;
    }

    public String getExclusive() {
        return exclusive;
    }

    public void setExclusive(String exclusive) {
        this.exclusive = exclusive;
    }

    public String getAutoCompleteSameAssignee() {
        return autoCompleteSameAssignee;
    }

    public void setAutoCompleteSameAssignee(String autoCompleteSameAssignee) {
        this.autoCompleteSameAssignee = autoCompleteSameAssignee;
    }

    public String getAutoCompleteEmptyAssignee() {
        return autoCompleteEmptyAssignee;
    }

    public void setAutoCompleteEmptyAssignee(String autoCompleteEmptyAssignee) {
        this.autoCompleteEmptyAssignee = autoCompleteEmptyAssignee;
    }

    public String getInform() {
        return inform;
    }

    public void setInform(String inform) {
        this.inform = inform;
    }

    public String getNodeEndUser() {
        return nodeEndUser;
    }

    public void setNodeEndUser(String nodeEndUser) {
        this.nodeEndUser = nodeEndUser;
    }

    public String getNodeEndUserName() {
        return nodeEndUserName;
    }

    public void setNodeEndUserName(String nodeEndUserName) {
        this.nodeEndUserName = nodeEndUserName;
    }

    public Date getNodeEndDate() {
        return nodeEndDate;
    }

    public void setNodeEndDate(Date nodeEndDate) {
        this.nodeEndDate = nodeEndDate;
    }

    public String getIsolateSubProcDefCode() {
        return isolateSubProcDefCode;
    }

    public void setIsolateSubProcDefCode(String isolateSubProcDefCode) {
        this.isolateSubProcDefCode = isolateSubProcDefCode;
    }

    public String getIsolateSubProcStatus() {
        return isolateSubProcStatus;
    }

    public void setIsolateSubProcStatus(String isolateSubProcStatus) {
        this.isolateSubProcStatus = isolateSubProcStatus;
    }

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public Date getNodeCreationDate() {
        return nodeCreationDate;
    }

    public void setNodeCreationDate(Date nodeCreationDate) {
        this.nodeCreationDate = nodeCreationDate;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }

    public String getAdjustProcDefId() {
        return adjustProcDefId;
    }

    public void setAdjustProcDefId(String adjustProcDefId) {
        this.adjustProcDefId = adjustProcDefId;
    }

    public String getIsolateSubProcNodeId() {
        return isolateSubProcNodeId;
    }

    public void setIsolateSubProcNodeId(String isolateSubProcNodeId) {
        this.isolateSubProcNodeId = isolateSubProcNodeId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public String getProcStartUser() {
        return procStartUser;
    }

    public void setProcStartUser(String procStartUser) {
        this.procStartUser = procStartUser;
    }

    public String getProcStartUserName() {
        return procStartUserName;
    }

    public void setProcStartUserName(String procStartUserName) {
        this.procStartUserName = procStartUserName;
    }

    public String getProcEndUser() {
        return procEndUser;
    }

    public void setProcEndUser(String procEndUser) {
        this.procEndUser = procEndUser;
    }

    public String getProcEndUserName() {
        return procEndUserName;
    }

    public void setProcEndUserName(String procEndUserName) {
        this.procEndUserName = procEndUserName;
    }

    public Date getProcEndDate() {
        return procEndDate;
    }

    public void setProcEndDate(Date procEndDate) {
        this.procEndDate = procEndDate;
    }

    public String getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(String procStatus) {
        this.procStatus = procStatus;
    }

    public Date getProcCreationDate() {
        return procCreationDate;
    }

    public void setProcCreationDate(Date procCreationDate) {
        this.procCreationDate = procCreationDate;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getProcDefCode() {
        return procDefCode;
    }

    public void setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
    }

    public String getProcDefName() {
        return procDefName;
    }

    public void setProcDefName(String procDefName) {
        this.procDefName = procDefName;
    }

    public String getProcDefCat() {
        return procDefCat;
    }

    public void setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProcDefStatus() {
        return procDefStatus;
    }

    public void setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
    }
}