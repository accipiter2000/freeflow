package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 任务操作。记录任务操作的历史数据。
 */
public class TaskOp implements Serializable {
    private static final long serialVersionUID = 1L;

    private String taskOpId; // 任务操作ID。
    private String operationId; // 操作ID。
    private String operationType; // 操作类型。
    private Integer operationOrder; // 操作顺序。
    private Date operationDate; // 操作日期。
    private String operationStatus; // 操作状态。
    private String taskId; // 任务ID。
    private String nodeId; // 节点ID。
    private String previousTaskId; // 前一个任务ID。
    private String taskType; // 任务类型。
    private String assignee; // 办理人。
    private String assigneeName; // 办理人名称。
    private String action; // 业务行为。
    private Date dueDate; // 截止日期。
    private String claim; // 认领。
    private String forwardable; // 可转发。
    private Integer priority; // 优先级。
    private String forwardStatus; // 转发状态。
    private String taskEndUser; // 任务完成人员。
    private String taskEndUserName; // 任务完成人员名称。
    private Date taskEndDate; // 任务完成日期。
    private String nextCandidate; // 下一个候选。
    private String taskStatus; // 任务状态。
    private Date creationDate; // 创建日期。

    public TaskOp() {
    }

    /**
     * 依据数据库数据构造。
     * 
     * @param data
     *        数据库数据。
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

    /**
     * 获取任务操作ID。
     * 
     * @return 任务操作ID。
     */
    public String getTaskOpId() {
        return taskOpId;
    }

    /**
     * 设置任务操作ID。
     * 
     * @param taskOpId
     *        任务操作ID。
     */
    public void setTaskOpId(String taskOpId) {
        this.taskOpId = taskOpId;
    }

    /**
     * 获取操作ID。
     * 
     * @return 操作ID。
     */
    public String getOperationId() {
        return operationId;
    }

    /**
     * 设置操作ID。
     * 
     * @param operationId
     *        操作ID。
     */
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    /**
     * 获取操作类型。
     * 
     * @return 操作类型。
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * 设置操作类型。
     * 
     * @param operationType
     *        操作类型。
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     * 获取操作顺序。
     * 
     * @return 操作顺序。
     */
    public Integer getOperationOrder() {
        return operationOrder;
    }

    /**
     * 设置操作顺序。
     * 
     * @param operationOrder
     *        操作顺序。
     */
    public void setOperationOrder(Integer operationOrder) {
        this.operationOrder = operationOrder;
    }

    /**
     * 获取操作日期。
     * 
     * @return 操作日期。
     */
    public Date getOperationDate() {
        return operationDate;
    }

    /**
     * 设置操作日期。
     * 
     * @param operationDate
     *        操作日期。
     */
    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    /**
     * 获取操作状态。
     * 
     * @return 操作状态。
     */
    public String getOperationStatus() {
        return operationStatus;
    }

    /**
     * 设置操作状态。
     * 
     * @param operationStatus
     *        操作状态。
     */
    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    /**
     * 获取任务ID。
     * 
     * @return 任务ID。
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置任务ID。
     * 
     * @param taskId
     *        任务ID。
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取节点ID。
     * 
     * @return 节点ID。
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 设置节点ID。
     * 
     * @param nodeId
     *        节点ID。
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 获取前一个任务ID。
     * 
     * @return 前一个任务ID。
     */
    public String getPreviousTaskId() {
        return previousTaskId;
    }

    /**
     * 设置前一个任务ID。
     * 
     * @param previousTaskId
     *        前一个任务ID。
     */
    public void setPreviousTaskId(String previousTaskId) {
        this.previousTaskId = previousTaskId;
    }

    /**
     * 获取任务类型。
     * 
     * @return 任务类型。
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * 设置任务类型。
     * 
     * @param taskType
     *        任务类型。
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * 获取办理人。
     * 
     * @return 办理人。
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * 设置办理人。
     * 
     * @param assignee
     *        办理人。
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * 获取办理人名称。
     * 
     * @return 办理人名称。
     */
    public String getAssigneeName() {
        return assigneeName;
    }

    /**
     * 设置办理人名称。
     * 
     * @param assigneeName
     *        办理人名称。
     */
    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    /**
     * 获取业务行为。
     * 
     * @return 业务行为。
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置业务行为。
     * 
     * @param action
     *        业务行为。
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取截止日期。
     * 
     * @return 截止日期。
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * 设置截止日期。
     * 
     * @param dueDate
     *        截止日期。
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * 获取认领。
     * 
     * @return 认领。
     */
    public String getClaim() {
        return claim;
    }

    /**
     * 设置认领。
     * 
     * @param claim
     *        认领。
     */
    public void setClaim(String claim) {
        this.claim = claim;
    }

    /**
     * 获取可转发。
     * 
     * @return 可转发。
     */
    public String getForwardable() {
        return forwardable;
    }

    /**
     * 设置可转发。
     * 
     * @param forwardable
     *        可转发。
     */
    public void setForwardable(String forwardable) {
        this.forwardable = forwardable;
    }

    /**
     * 获取优先级。
     * 
     * @return 优先级。
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置优先级。
     * 
     * @param priority
     *        优先级。
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取转发状态。
     * 
     * @return 转发状态。
     */
    public String getForwardStatus() {
        return forwardStatus;
    }

    /**
     * 设置转发状态。
     * 
     * @param forwardStatus
     *        转发状态。
     */
    public void setForwardStatus(String forwardStatus) {
        this.forwardStatus = forwardStatus;
    }

    /**
     * 获取任务完成人员。
     * 
     * @return 任务完成人员。
     */
    public String getTaskEndUser() {
        return taskEndUser;
    }

    /**
     * 设置任务完成人员。
     * 
     * @param taskEndUser
     *        任务完成人员。
     */
    public void setTaskEndUser(String taskEndUser) {
        this.taskEndUser = taskEndUser;
    }

    /**
     * 获取任务完成人员名称。
     * 
     * @return 任务完成人员名称。
     */
    public String getTaskEndUserName() {
        return taskEndUserName;
    }

    /**
     * 设置任务完成人员名称。
     * 
     * @param taskEndUserName
     *        任务完成人员名称。
     */
    public void setTaskEndUserName(String taskEndUserName) {
        this.taskEndUserName = taskEndUserName;
    }

    /**
     * 获取任务完成日期。
     * 
     * @return 任务完成日期。
     */
    public Date getTaskEndDate() {
        return taskEndDate;
    }

    /**
     * 设置任务完成日期。
     * 
     * @param taskEndDate
     *        任务完成日期。
     */
    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    /**
     * 获取下一个候选。
     * 
     * @return 下一个候选。
     */
    public String getNextCandidate() {
        return nextCandidate;
    }

    /**
     * 设置下一个候选。
     * 
     * @param nextCandidate
     *        下一个候选。
     */
    public void setNextCandidate(String nextCandidate) {
        this.nextCandidate = nextCandidate;
    }

    /**
     * 获取任务状态。
     * 
     * @return 任务状态。
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务状态。
     * 
     * @param taskStatus
     *        任务状态。
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 获取创建日期。
     * 
     * @return 创建日期。
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * 设置创建日期。
     * 
     * @param creationDate
     *        创建日期。
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}