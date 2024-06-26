package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class NodeOp implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nodeOpId; // 节点操作ID
    private String operationId; // 操作ID
    private String operationType; // 操作类型
    private Integer operationOrder; // 操作顺序
    private Date operationDate; // 操作日期
    private String operationStatus; // 操作状态
    private String nodeId; // 节点ID
    private String parentNodeId; // 上级节点ID
    private String procId; // 流程ID
    private String previousNodeIds; // 前节点IDs
    private String lastCompleteNodeIds; // 最后完成节点IDs
    private String subProcDefId; // 子流程定义ID
    private String adjustSubProcDefId; // 调整子流程定义ID
    private String nodeType; // 节点类型
    private String nodeCode; // 节点编码
    private String nodeName; // 节点名称
    private String parentNodeCode; // 上级节点编码
    private String candidateAssignee; // 候选人
    private String completeExpression; // 完成表达式
    private String completeReturn; // 完成后返回前一个节点
    private String exclusive; // 排他
    private String waitingForCompleteNode; // 等待完成节点
    private String autoCompleteSameAssignee; // 自动完成相同办理人任务
    private String autoCompleteEmptyAssignee; // 自动完成没有办理人节点
    private String inform; // 通知
    private String assignee; // 办理人
    private String action; // 业务行为
    private String dueDate; // 截止日期
    private String claim; // 认领
    private String forwardable; // 可转发
    private String priority; // 优先级
    private String nodeEndUser; // 节点完成人员
    private String nodeEndUserName; // 节点完成人员名称
    private Date nodeEndDate; // 节点完成日期
    private String nextCandidate; // 下个候选人
    private String isolateSubProcDefCode; // 独立子流程定义编码
    private String isolateSubProcCandidate; // 独立子流程候选人
    private String isolateSubProcStatus; // 独立子流程状态
    private String nodeStatus; // 节点状态
    private Date creationDate; // 创建日期

    public NodeOp() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
     */
    public NodeOp(Map<String, Object> data) {
        this.nodeOpId = (String) data.get("NODE_OP_ID_");
        this.operationId = (String) data.get("OPERATION_ID_");
        this.operationType = (String) data.get("OPERATION_TYPE_");
        this.operationOrder = (data.get("OPERATION_ORDER_") != null) ? (((BigDecimal) data.get("OPERATION_ORDER_")).intValue()) : null;
        this.operationDate = (Date) data.get("OPERATION_DATE_");
        this.operationStatus = (String) data.get("OPERATION_STATUS_");
        this.nodeId = (String) data.get("NODE_ID_");
        this.parentNodeId = (String) data.get("PARENT_NODE_ID_");
        this.procId = (String) data.get("PROC_ID_");
        this.previousNodeIds = (String) data.get("PREVIOUS_NODE_IDS_");
        this.lastCompleteNodeIds = (String) data.get("LAST_COMPLETE_NODE_IDS_");
        this.subProcDefId = (String) data.get("SUB_PROC_DEF_ID_");
        this.adjustSubProcDefId = (String) data.get("ADJUST_SUB_PROC_DEF_ID_");
        this.nodeType = (String) data.get("NODE_TYPE_");
        this.nodeCode = (String) data.get("NODE_CODE_");
        this.nodeName = (String) data.get("NODE_NAME_");
        this.parentNodeCode = (String) data.get("PARENT_NODE_CODE_");
        this.candidateAssignee = (String) data.get("CANDIDATE_ASSIGNEE_");
        this.completeExpression = (String) data.get("COMPLETE_EXPRESSION_");
        this.completeReturn = (String) data.get("COMPLETE_RETURN_");
        this.exclusive = (String) data.get("EXCLUSIVE_");
        this.waitingForCompleteNode = (String) data.get("WAITING_FOR_COMPLETE_NODE_");
        this.autoCompleteSameAssignee = (String) data.get("AUTO_COMPLETE_SAME_ASSIGNEE_");
        this.autoCompleteEmptyAssignee = (String) data.get("AUTO_COMPLETE_EMPTY_ASSIGNEE_");
        this.inform = (String) data.get("INFORM_");
        this.assignee = (String) data.get("ASSIGNEE_");
        this.action = (String) data.get("ACTION_");
        this.dueDate = (String) data.get("DUE_DATE_");
        this.claim = (String) data.get("CLAIM_");
        this.forwardable = (String) data.get("FORWARDABLE_");
        this.priority = (String) data.get("PRIORITY_");
        this.nodeEndUser = (String) data.get("NODE_END_USER_");
        this.nodeEndUserName = (String) data.get("NODE_END_USER_NAME_");
        this.nodeEndDate = (Date) data.get("NODE_END_DATE_");
        this.nextCandidate = (String) data.get("NEXT_CANDIDATE_");
        this.isolateSubProcDefCode = (String) data.get("ISOLATE_SUB_PROC_DEF_CODE_");
        this.isolateSubProcCandidate = (String) data.get("ISOLATE_SUB_PROC_CANDIDATE_");
        this.isolateSubProcStatus = (String) data.get("ISOLATE_SUB_PROC_STATUS_");
        this.nodeStatus = (String) data.get("NODE_STATUS_");
        this.creationDate = (Date) data.get("CREATION_DATE_");
    }

    public String getNodeOpId() {
        return nodeOpId;
    }

    public void setNodeOpId(String nodeOpId) {
        this.nodeOpId = nodeOpId;
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

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
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

    public String getCandidateAssignee() {
        return candidateAssignee;
    }

    public void setCandidateAssignee(String candidateAssignee) {
        this.candidateAssignee = candidateAssignee;
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

    public String getWaitingForCompleteNode() {
        return waitingForCompleteNode;
    }

    public void setWaitingForCompleteNode(String waitingForCompleteNode) {
        this.waitingForCompleteNode = waitingForCompleteNode;
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

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public String getNextCandidate() {
        return nextCandidate;
    }

    public void setNextCandidate(String nextCandidate) {
        this.nextCandidate = nextCandidate;
    }

    public String getIsolateSubProcDefCode() {
        return isolateSubProcDefCode;
    }

    public void setIsolateSubProcDefCode(String isolateSubProcDefCode) {
        this.isolateSubProcDefCode = isolateSubProcDefCode;
    }

    public String getIsolateSubProcCandidate() {
        return isolateSubProcCandidate;
    }

    public void setIsolateSubProcCandidate(String isolateSubProcCandidate) {
        this.isolateSubProcCandidate = isolateSubProcCandidate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}