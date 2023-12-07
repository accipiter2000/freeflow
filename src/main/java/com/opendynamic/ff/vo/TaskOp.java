package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class TaskOp implements Serializable {
    private static final long serialVersionUID = 1L;

    private String taskOpId; // 任务操作ID
    private String operationId; // 操作ID
    private String operationType; // 操作类型
    private Integer operationOrder; // 操作顺序
    private Date operationDate; // 操作日期
    private String operationStatus; // 操作状态
    private String taskId; // 任务ID
    private String nodeId; // 节点ID
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
    private String nextCandidate; // 下个候选人
    private String taskStatus; // 任务状态
    private Date creationDate; // 创建日期

    public TaskOp() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
     */
    public TaskOp(Map<String, Object> data) {
        this.taskOpId = (String) data.get("TASK_OP_ID_");
        this.operationId = (String) data.get("OPERATION_ID_");
        this.operationType = (String) data.get("OPERATION_TYPE_");
        this.operationOrder = (data.get("OPERATION_ORDER_") != null) ? (((BigDecimal) data.get("OPERATION_ORDER_")).intValue()) : null;
        this.operationDate = (Date) data.get("OPERATION_DATE_");
        this.operationStatus = (String) data.get("OPERATION_STATUS_");
        this.taskId = (String) data.get("TASK_ID_");
        this.nodeId = (String) data.get("NODE_ID_");
        this.previousTaskId = (String) data.get("PREVIOUS_TASK_ID_");
        this.taskType = (String) data.get("TASK_TYPE_");
        this.assignee = (String) data.get("ASSIGNEE_");
        this.assigneeName = (String) data.get("ASSIGNEE_NAME_");
        this.action = (String) data.get("ACTION_");
        this.dueDate = (Date) data.get("DUE_DATE_");
        this.claim = (String) data.get("CLAIM_");
        this.forwardable = (String) data.get("FORWARDABLE_");
        this.priority = (data.get("OPERATION_ORDER_") != null) ? (((BigDecimal) data.get("PRIORITY_")).intValue()) : null;
        this.forwardStatus = (String) data.get("FORWARD_STATUS_");
        this.taskEndUser = (String) data.get("TASK_END_USER_");
        this.taskEndUserName = (String) data.get("TASK_END_USER_NAME_");
        this.taskEndDate = (Date) data.get("TASK_END_DATE_");
        this.nextCandidate = (String) data.get("NEXT_CANDIDATE_");
        this.taskStatus = (String) data.get("TASK_STATUS_");
        this.creationDate = (Date) data.get("CREATION_DATE_");
    }

    public String getTaskOpId() {
        return taskOpId;
    }

    public void setTaskOpId(String taskOpId) {
        this.taskOpId = taskOpId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Integer getOperationOrder() {
        return operationOrder;
    }

    public void setOperationOrder(Integer operationOrder) {
        this.operationOrder = operationOrder;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
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

    public String getNextCandidate() {
        return nextCandidate;
    }

    public void setNextCandidate(String nextCandidate) {
        this.nextCandidate = nextCandidate;
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
}