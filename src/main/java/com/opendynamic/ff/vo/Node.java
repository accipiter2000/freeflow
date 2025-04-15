package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 节点。
 */
public class Node implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nodeId; // 节点ID。
    private String parentNodeId; // 上级节点ID。
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
    private String waitingForCompleteNode;// 等待完成节点。
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
    private String procId; // 流程ID。
    private String adjustProcDefId; // 调整流程定义ID。
    private String isolateSubProcNodeId; // 独立子流程所属节点ID。
    private String bizId; // 业务主键。
    private String bizType; // 业务类型。
    private String bizCode; // 业务编码。
    private String bizName; // 业务名称。
    private String bizDesc; // 业务备注。
    private String procStartUser; // 流程开始人员。
    private String procStartUserName; // 流程开始人员名称。
    private String procEndUser; // 流程完成人员。
    private String procEndUserName; // 流程完成人员名称。
    private Date procEndDate; // 流程完成日期。
    private String procStatus; // 流程状态。
    private Date procCreationDate; // 流程创建日期。
    private String procDefId; // 流程定义ID。
    private String procDefCode; // 流程定义编码。
    private String procDefName; // 流程定义名称。
    private String procDefCat; // 流程定义分类。
    private Integer version; // 版本。
    private String procDefStatus; // 流程定义状态。
    private String subProcDefCode; // 子流程定义编码。

    public Node() {
    }

    /**
     * 依据数据库数据构造。
     * 
     * @param data
     *        数据库数据。
     */
    public Node(Map<String, Object> data) {
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
        this.subProcDefCode = (String) data.get("SUB_PROC_DEF_CODE_");
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
     * 获取调整流程定义ID。
     * 
     * @return 调整流程定义ID。
     */
    public String getAdjustProcDefId() {
        return adjustProcDefId;
    }

    /**
     * 设置调整流程定义ID。
     * 
     * @param adjustProcDefId
     *        调整流程定义ID。
     */
    public void setAdjustProcDefId(String adjustProcDefId) {
        this.adjustProcDefId = adjustProcDefId;
    }

    /**
     * 获取独立子流程所属节点ID。
     * 
     * @return 独立子流程所属节点ID。
     */
    public String getIsolateSubProcNodeId() {
        return isolateSubProcNodeId;
    }

    /**
     * 设置独立子流程所属节点ID。
     * 
     * @param isolateSubProcNodeId
     *        独立子流程所属节点ID。
     */
    public void setIsolateSubProcNodeId(String isolateSubProcNodeId) {
        this.isolateSubProcNodeId = isolateSubProcNodeId;
    }

    /**
     * 获取业务主键。
     * 
     * @return 业务主键。
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * 设置业务主键。
     * 
     * @param bizId
     *        业务主键。
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /**
     * 获取业务类型。
     * 
     * @return 业务类型。
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * 设置业务类型。
     * 
     * @param bizType
     *        业务类型。
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    /**
     * 获取业务编码。
     * 
     * @return 业务编码。
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * 设置业务编码。
     * 
     * @param bizCode
     *        业务编码。
     */
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * 获取业务名称。
     * 
     * @return 业务名称。
     */
    public String getBizName() {
        return bizName;
    }

    /**
     * 设置业务名称。
     * 
     * @param bizName
     *        业务名称。
     */
    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    /**
     * 获取业务备注。
     * 
     * @return 业务备注。
     */
    public String getBizDesc() {
        return bizDesc;
    }

    /**
     * 设置业务备注。
     * 
     * @param bizDesc
     *        业务备注。
     */
    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    /**
     * 获取流程开始人员。
     * 
     * @return 流程开始人员。
     */
    public String getProcStartUser() {
        return procStartUser;
    }

    /**
     * 设置流程开始人员。
     * 
     * @param procStartUser
     *        流程开始人员。
     */
    public void setProcStartUser(String procStartUser) {
        this.procStartUser = procStartUser;
    }

    /**
     * 获取流程开始人员名称。
     * 
     * @return 流程开始人员名称。
     */
    public String getProcStartUserName() {
        return procStartUserName;
    }

    /**
     * 设置流程开始人员名称。
     * 
     * @param procStartUserName
     *        流程开始人员名称。
     */
    public void setProcStartUserName(String procStartUserName) {
        this.procStartUserName = procStartUserName;
    }

    /**
     * 获取流程完成人员。
     * 
     * @return 流程完成人员。
     */
    public String getProcEndUser() {
        return procEndUser;
    }

    /**
     * 设置流程完成人员。
     * 
     * @param procEndUser
     *        流程完成人员。
     */
    public void setProcEndUser(String procEndUser) {
        this.procEndUser = procEndUser;
    }

    /**
     * 获取流程完成人员名称。
     * 
     * @return 流程完成人员名称。
     */
    public String getProcEndUserName() {
        return procEndUserName;
    }

    /**
     * 设置流程完成人员名称。
     * 
     * @param procEndUserName
     *        流程完成人员名称。
     */
    public void setProcEndUserName(String procEndUserName) {
        this.procEndUserName = procEndUserName;
    }

    /**
     * 获取流程完成日期。
     * 
     * @return 流程完成日期。
     */
    public Date getProcEndDate() {
        return procEndDate;
    }

    /**
     * 设置流程完成日期。
     * 
     * @param procEndDate
     *        流程完成日期。
     */
    public void setProcEndDate(Date procEndDate) {
        this.procEndDate = procEndDate;
    }

    /**
     * 获取流程状态。
     * 
     * @return 流程状态。
     */
    public String getProcStatus() {
        return procStatus;
    }

    /**
     * 设置流程状态。
     * 
     * @param procStatus
     *        流程状态。
     */
    public void setProcStatus(String procStatus) {
        this.procStatus = procStatus;
    }

    /**
     * 获取流程创建日期。
     * 
     * @return 流程创建日期。
     */
    public Date getProcCreationDate() {
        return procCreationDate;
    }

    /**
     * 设置流程创建日期。
     * 
     * @param procCreationDate
     *        流程创建日期。
     */
    public void setProcCreationDate(Date procCreationDate) {
        this.procCreationDate = procCreationDate;
    }

    /**
     * 获取流程定义ID。
     * 
     * @return 流程定义ID。
     */
    public String getProcDefId() {
        return procDefId;
    }

    /**
     * 设置流程定义ID。
     * 
     * @param procDefId
     *        流程定义ID。
     */
    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    /**
     * 获取流程定义编码。
     * 
     * @return 流程定义编码。
     */
    public String getProcDefCode() {
        return procDefCode;
    }

    /**
     * 设置流程定义编码。
     * 
     * @param procDefCode
     *        流程定义编码。
     */
    public void setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
    }

    /**
     * 获取流程定义名称。
     * 
     * @return 流程定义名称。
     */
    public String getProcDefName() {
        return procDefName;
    }

    /**
     * 设置流程定义名称。
     * 
     * @param procDefName
     *        流程定义名称。
     */
    public void setProcDefName(String procDefName) {
        this.procDefName = procDefName;
    }

    /**
     * 获取流程定义分类。
     * 
     * @return 流程定义分类。
     */
    public String getProcDefCat() {
        return procDefCat;
    }

    /**
     * 设置流程定义分类。
     * 
     * @param procDefCat
     *        流程定义分类。
     */
    public void setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
    }

    /**
     * 获取版本。
     * 
     * @return 版本。
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本。
     * 
     * @param version
     *        版本。
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取流程定义状态。
     * 
     * @return 流程定义状态。
     */
    public String getProcDefStatus() {
        return procDefStatus;
    }

    /**
     * 设置流程定义状态。
     * 
     * @param procDefStatus
     *        流程定义状态。
     */
    public void setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
    }

    /**
     * 获取子流程定义编码。
     * 
     * @return 子流程定义编码。
     */
    public String getSubProcDefCode() {
        return subProcDefCode;
    }

    /**
     * 设置子流程定义编码。
     * 
     * @param subProcDefCode
     *        子流程定义编码。
     */
    public void setSubProcDefCode(String subProcDefCode) {
        this.subProcDefCode = subProcDefCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            if (node != null && node.getNodeId() != null && node.getNodeId().equals(nodeId)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId);
    }
}