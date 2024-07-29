package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 节点操作。记录节点操作的历史数据。
 */
public class NodeOp implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nodeOpId; // 节点操作ID。
    private String operationId; // 操作ID。
    private String operationType; // 操作类型。
    private Integer operationOrder; // 操作顺序。
    private Date operationDate; // 操作日期。
    private String operationStatus; // 操作状态。
    private String nodeId; // 节点ID。
    private String parentNodeId; // 上级节点ID。
    private String procId; // 流程ID。
    private String previousNodeIds; // 前节点IDs。
    private String lastCompleteNodeIds; // 最后完成节点IDs。
    private String subProcDefId; // 子流程定义ID。
    private String adjustSubProcDefId; // 调整子流程定义ID。
    private String nodeType; // 节点类型。
    private String nodeCode; // 节点编码。
    private String nodeName; // 节点名称。
    private String parentNodeCode; // 上级节点编码。
    private String candidateAssignee; // 候选人。
    private String completeExpression; // 完成表达式。
    private String completeReturn; // 完成后返回前一个节点。
    private String exclusive; // 排他。
    private String waitingForCompleteNode; // 等待完成节点。
    private String autoCompleteSameAssignee; // 自动完成相同办理人任务。
    private String autoCompleteEmptyAssignee; // 自动完成没有办理人节点。
    private String inform; // 通知。
    private String assignee; // 办理人。
    private String action; // 业务行为。
    private String dueDate; // 截止日期。
    private String claim; // 认领。
    private String forwardable; // 可转发。
    private String priority; // 优先级。
    private String nodeEndUser; // 节点完成人员。
    private String nodeEndUserName; // 节点完成人员名称。
    private Date nodeEndDate; // 节点完成日期。
    private String nextCandidate; // 下一个候选。
    private String isolateSubProcDefCode; // 独立子流程定义编码。
    private String isolateSubProcCandidate; // 独立子流程候选。
    private String isolateSubProcStatus; // 独立子流程状态。
    private String nodeStatus; // 节点状态。
    private Date creationDate; // 创建日期。

    public NodeOp() {
    }

    /**
     * 依据数据库数据构造。
     * 
     * @param data
     *        数据库数据。
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

    /**
     * 获取节点操作ID。
     * 
     * @return 节点操作ID。
     */
    public String getNodeOpId() {
        return nodeOpId;
    }

    /**
     * 设置节点操作ID。
     * 
     * @param nodeOpId
     *        节点操作ID。
     */
    public void setNodeOpId(String nodeOpId) {
        this.nodeOpId = nodeOpId;
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
     * 获取上级节点ID。
     * 
     * @return 上级节点ID。
     */
    public String getParentNodeId() {
        return parentNodeId;
    }

    /**
     * 设置上级节点ID。
     * 
     * @param parentNodeId
     *        上级节点ID。
     */
    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    /**
     * 获取流程ID。
     * 
     * @return 流程ID。
     */
    public String getProcId() {
        return procId;
    }

    /**
     * 设置流程ID。
     * 
     * @param procId
     *        流程ID。
     */
    public void setProcId(String procId) {
        this.procId = procId;
    }

    /**
     * 获取前节点IDs。
     * 
     * @return 前节点IDs。
     */
    public String getPreviousNodeIds() {
        return previousNodeIds;
    }

    /**
     * 设置前节点IDs。
     * 
     * @param previousNodeIds
     *        前节点IDs。
     */
    public void setPreviousNodeIds(String previousNodeIds) {
        this.previousNodeIds = previousNodeIds;
    }

    /**
     * 获取最后完成节点IDs。
     * 
     * @return 最后完成节点IDs。
     */
    public String getLastCompleteNodeIds() {
        return lastCompleteNodeIds;
    }

    /**
     * 设置最后完成节点IDs。
     * 
     * @param lastCompleteNodeIds
     *        最后完成节点IDs。
     */
    public void setLastCompleteNodeIds(String lastCompleteNodeIds) {
        this.lastCompleteNodeIds = lastCompleteNodeIds;
    }

    /**
     * 获取子流程定义ID。
     * 
     * @return 子流程定义ID。
     */
    public String getSubProcDefId() {
        return subProcDefId;
    }

    /**
     * 设置子流程定义ID。
     * 
     * @param subProcDefId
     *        子流程定义ID。
     */
    public void setSubProcDefId(String subProcDefId) {
        this.subProcDefId = subProcDefId;
    }

    /**
     * 获取调整子流程定义ID。
     * 
     * @return 调整子流程定义ID。
     */
    public String getAdjustSubProcDefId() {
        return adjustSubProcDefId;
    }

    /**
     * 设置调整子流程定义ID。
     * 
     * @param adjustSubProcDefId
     *        调整子流程定义ID。
     */
    public void setAdjustSubProcDefId(String adjustSubProcDefId) {
        this.adjustSubProcDefId = adjustSubProcDefId;
    }

    /**
     * 获取节点类型。
     * 
     * @return 节点类型。
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 设置节点类型。
     * 
     * @param nodeType
     *        节点类型。
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 获取节点编码。
     * 
     * @return 节点编码。
     */
    public String getNodeCode() {
        return nodeCode;
    }

    /**
     * 设置节点编码。
     * 
     * @param nodeCode
     *        节点编码。
     */
    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    /**
     * 获取节点名称。
     * 
     * @return 节点名称。
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 设置节点名称。
     * 
     * @param nodeName
     *        节点名称。
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * 获取上级节点编码。
     * 
     * @return 上级节点编码。
     */
    public String getParentNodeCode() {
        return parentNodeCode;
    }

    /**
     * 设置上级节点编码。
     * 
     * @param parentNodeCode
     *        上级节点编码。
     */
    public void setParentNodeCode(String parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
    }

    /**
     * 获取候选人。
     * 
     * @return 候选人。
     */
    public String getCandidateAssignee() {
        return candidateAssignee;
    }

    /**
     * 设置候选人。
     * 
     * @param candidateAssignee
     *        候选人。
     */
    public void setCandidateAssignee(String candidateAssignee) {
        this.candidateAssignee = candidateAssignee;
    }

    /**
     * 获取完成表达式。
     * 
     * @return 完成表达式。
     */
    public String getCompleteExpression() {
        return completeExpression;
    }

    /**
     * 设置完成表达式。
     * 
     * @param completeExpression
     *        完成表达式。
     */
    public void setCompleteExpression(String completeExpression) {
        this.completeExpression = completeExpression;
    }

    /**
     * 获取完成后返回前一个节点。
     * 
     * @return 完成后返回前一个节点。
     */
    public String getCompleteReturn() {
        return completeReturn;
    }

    /**
     * 设置完成后返回前一个节点。
     * 
     * @param completeReturn
     *        完成后返回前一个节点。
     */
    public void setCompleteReturn(String completeReturn) {
        this.completeReturn = completeReturn;
    }

    /**
     * 获取排他。
     * 
     * @return 排他。
     */
    public String getExclusive() {
        return exclusive;
    }

    /**
     * 设置排他。
     * 
     * @param exclusive
     *        排他。
     */
    public void setExclusive(String exclusive) {
        this.exclusive = exclusive;
    }

    /**
     * 获取等待完成节点。
     * 
     * @return 等待完成节点。
     */
    public String getWaitingForCompleteNode() {
        return waitingForCompleteNode;
    }

    /**
     * 设置等待完成节点。
     * 
     * @param waitingForCompleteNode
     *        等待完成节点。
     */
    public void setWaitingForCompleteNode(String waitingForCompleteNode) {
        this.waitingForCompleteNode = waitingForCompleteNode;
    }

    /**
     * 获取自动完成相同办理人任务。
     * 
     * @return 自动完成相同办理人任务。
     */
    public String getAutoCompleteSameAssignee() {
        return autoCompleteSameAssignee;
    }

    /**
     * 设置自动完成相同办理人任务。
     * 
     * @param autoCompleteSameAssignee
     *        自动完成相同办理人任务。
     */
    public void setAutoCompleteSameAssignee(String autoCompleteSameAssignee) {
        this.autoCompleteSameAssignee = autoCompleteSameAssignee;
    }

    /**
     * 获取自动完成没有办理人节点。
     * 
     * @return 自动完成没有办理人节点。
     */
    public String getAutoCompleteEmptyAssignee() {
        return autoCompleteEmptyAssignee;
    }

    /**
     * 设置自动完成没有办理人节点。
     * 
     * @param autoCompleteEmptyAssignee
     *        自动完成没有办理人节点。
     */
    public void setAutoCompleteEmptyAssignee(String autoCompleteEmptyAssignee) {
        this.autoCompleteEmptyAssignee = autoCompleteEmptyAssignee;
    }

    /**
     * 获取通知。
     * 
     * @return 通知。
     */
    public String getInform() {
        return inform;
    }

    /**
     * 设置通知。
     * 
     * @param inform
     *        通知。
     */
    public void setInform(String inform) {
        this.inform = inform;
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
    public String getDueDate() {
        return dueDate;
    }

    /**
     * 设置截止日期。
     * 
     * @param dueDate
     *        截止日期。
     */
    public void setDueDate(String dueDate) {
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
    public String getPriority() {
        return priority;
    }

    /**
     * 设置优先级。
     * 
     * @param priority
     *        优先级。
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * 获取节点完成人员。
     * 
     * @return 节点完成人员。
     */
    public String getNodeEndUser() {
        return nodeEndUser;
    }

    /**
     * 设置节点完成人员。
     * 
     * @param nodeEndUser
     *        节点完成人员。
     */
    public void setNodeEndUser(String nodeEndUser) {
        this.nodeEndUser = nodeEndUser;
    }

    /**
     * 获取节点完成人员名称。
     * 
     * @return 节点完成人员名称。
     */
    public String getNodeEndUserName() {
        return nodeEndUserName;
    }

    /**
     * 设置节点完成人员名称。
     * 
     * @param nodeEndUserName
     *        节点完成人员名称。
     */
    public void setNodeEndUserName(String nodeEndUserName) {
        this.nodeEndUserName = nodeEndUserName;
    }

    /**
     * 获取节点完成日期。
     * 
     * @return 节点完成日期。
     */
    public Date getNodeEndDate() {
        return nodeEndDate;
    }

    /**
     * 设置节点完成日期。
     * 
     * @param nodeEndDate
     *        节点完成日期。
     */
    public void setNodeEndDate(Date nodeEndDate) {
        this.nodeEndDate = nodeEndDate;
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
     * 获取独立子流程定义编码。
     * 
     * @return 独立子流程定义编码。
     */
    public String getIsolateSubProcDefCode() {
        return isolateSubProcDefCode;
    }

    /**
     * 设置独立子流程定义编码。
     * 
     * @param isolateSubProcDefCode
     *        独立子流程定义编码。
     */
    public void setIsolateSubProcDefCode(String isolateSubProcDefCode) {
        this.isolateSubProcDefCode = isolateSubProcDefCode;
    }

    /**
     * 获取独立子流程候选。
     * 
     * @return 独立子流程候选。
     */
    public String getIsolateSubProcCandidate() {
        return isolateSubProcCandidate;
    }

    /**
     * 设置独立子流程候选。
     * 
     * @param isolateSubProcCandidate
     *        独立子流程候选。
     */
    public void setIsolateSubProcCandidate(String isolateSubProcCandidate) {
        this.isolateSubProcCandidate = isolateSubProcCandidate;
    }

    /**
     * 获取独立子流程状态。
     * 
     * @return 独立子流程状态。
     */
    public String getIsolateSubProcStatus() {
        return isolateSubProcStatus;
    }

    /**
     * 设置独立子流程状态。
     * 
     * @param isolateSubProcStatus
     *        独立子流程状态。
     */
    public void setIsolateSubProcStatus(String isolateSubProcStatus) {
        this.isolateSubProcStatus = isolateSubProcStatus;
    }

    /**
     * 获取节点状态。
     * 
     * @return 节点状态。
     */
    public String getNodeStatus() {
        return nodeStatus;
    }

    /**
     * 设置节点状态。
     * 
     * @param nodeStatus
     *        节点状态。
     */
    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
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