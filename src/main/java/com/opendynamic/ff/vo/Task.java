package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private String taskId; // 任务ID
    private String nodeId; // 节点ID
    private String previousTaskId; // 前一个任务ID
    private String taskType; // 任务类型
    private String assignee; // 办理人
    private String assigneeName; // 办理人名称
    private String executor; // 执行人
    private String executorName; // 执行人名称
    private String action; // 业务行为
    private Date claimDate; // 认领日期
    private Date dueDate; // 截止日期
    private Date completeDate; // 完成日期
    private Integer priority; // 优先级
    private String forwardable; // 可转发
    private String forwardStatus; // 转发状态
    private String taskStatus; // 任务状态
    private Date creationDate; // 创建日期
    private String parentNodeId; // 上级节点ID
    private String procId; // 流程ID
    private String nodeType; // 节点类型
    private String nodeCode; // 节点编码
    private String nodeName; // 节点名称
    private String exclusive; // 排他
    private String nodeEndUser; // 节点完成人员
    private String nodeEndUserName; // 节点完成人员名称
    private Date nodeEndDate; // 节点完成日期
    private String nodeStatus; // 节点状态
    private String bizId; // 业务主键
    private String bizType; // 业务类型
    private String bizCode; // 业务编码
    private String bizName; // 业务名称
    private String procStartUser; // 流程开始人员
    private String procStartUserName; // 流程开始人员名称
    private Date procStartDate; // 流程开始日期
    private String procEndUser; // 流程完成人员
    private String procEndUserName; // 流程完成人员名称
    private Date procEndDate; // 流程完成日期
    private String procStatus; // 流程状态

    public Task() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
     */
    public Task(Map<String, Object> data) {
        this.taskId = (String) data.get("TASK_ID_");
        this.nodeId = (String) data.get("NODE_ID_");
        this.previousTaskId = (String) data.get("PREVIOUS_TASK_ID_");
        this.taskType = (String) data.get("TASK_TYPE_");
        this.assignee = (String) data.get("ASSIGNEE_");
        this.assigneeName = (String) data.get("ASSIGNEE_NAME_");
        this.executor = (String) data.get("EXECUTOR_");
        this.executorName = (String) data.get("EXECUTOR_NAME_");
        this.action = (String) data.get("ACTION_");
        this.claimDate = (Date) data.get("CLAIM_DATE_");
        this.dueDate = (Date) data.get("DUE_DATE_");
        this.completeDate = (Date) data.get("COMPLETE_DATE_");
        this.priority = ((BigDecimal) data.get("PRIORITY_")).intValue();
        this.taskStatus = (String) data.get("TASK_STATUS_");
        this.forwardable = (String) data.get("FORWARDABLE_");
        this.forwardStatus = (String) data.get("FORWARD_STATUS_");
        this.creationDate = (Date) data.get("CREATION_DATE_");
        this.parentNodeId = (String) data.get("PARENT_NODE_ID_");
        this.procId = (String) data.get("PROC_ID_");
        this.nodeType = (String) data.get("NODE_TYPE_");
        this.nodeCode = (String) data.get("NODE_CODE_");
        this.nodeName = (String) data.get("NODE_NAME_");
        this.exclusive = (String) data.get("EXCLUSIVE_");
        this.nodeEndUser = (String) data.get("NODE_END_USER_");
        this.nodeEndUserName = (String) data.get("NODE_END_USER_NAME_");
        this.nodeEndDate = (Date) data.get("NODE_END_DATE_");
        this.nodeStatus = (String) data.get("NODE_STATUS_");
        this.bizId = (String) data.get("BIZ_ID_");
        this.bizType = (String) data.get("BIZ_TYPE_");
        this.bizCode = (String) data.get("BIZ_CODE_");
        this.bizName = (String) data.get("BIZ_NAME_");
        this.procStartUser = (String) data.get("PROC_START_USER_");
        this.procStartUserName = (String) data.get("PROC_START_USER_NAME_");
        this.procStartDate = (Date) data.get("PROC_START_DATE_");
        this.procEndUser = (String) data.get("PROC_END_USER_");
        this.procEndUserName = (String) data.get("PROC_END_USER_NAME_");
        this.procEndDate = (Date) data.get("PROC_END_DATE_");
        this.procStatus = (String) data.get("PROC_STATUS_");
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getForwardable() {
        return forwardable;
    }

    public void setForwardable(String forwardable) {
        this.forwardable = forwardable;
    }

    public String getForwardStatus() {
        return forwardStatus;
    }

    public void setForwardStatus(String forwardStatus) {
        this.forwardStatus = forwardStatus;
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

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
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

    public String getExclusive() {
        return exclusive;
    }

    public void setExclusive(String exclusive) {
        this.exclusive = exclusive;
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

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
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

    public Date getProcStartDate() {
        return procStartDate;
    }

    public void setProcStartDate(Date procStartDate) {
        this.procStartDate = procStartDate;
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
}