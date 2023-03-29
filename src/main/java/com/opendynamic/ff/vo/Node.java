package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private String assignee; // 办理人
    private String candidate; // 候选人
    private String action; // 业务行为
    private String dueDate; // 截止日期
    private String completeExpression; // 完成表达式
    private String completeReturn; // 完成后返回前一个节点
    private String exclusive; // 排他
    private String forwardable; // 可转发
    private String autoCompleteSameAssignee; // 自动完成相同办理人任务
    private String autoCompleteEmptyAssignee; // 自动完成没有办理人节点
    private String inform; // 通知
    private Integer priority; // 优先级
    private String nodeEndUser; // 节点完成人员
    private String nodeEndUserName; // 节点完成人员名称
    private Date nodeEndDate; // 节点完成日期
    private String isolateSubProcDefCode; // 独立子流程定义编码
    private String isolateSubProcStatus; // 独立子流程状态
    private String nodeStatus; // 节点状态
    private Date creationDate; // 创建日期
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

    public Node() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
     */
    public Node(Map<String, Object> data) {
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
        this.assignee = (String) data.get("ASSIGNEE_");
        this.candidate = (String) data.get("CANDIDATE_");
        this.action = (String) data.get("ACTION_");
        this.dueDate = (String) data.get("DUE_DATE_");
        this.completeExpression = (String) data.get("COMPLETE_EXPRESSION_");
        this.completeReturn = (String) data.get("COMPLETE_RETURN_");
        this.exclusive = (String) data.get("EXCLUSIVE_");
        this.forwardable = (String) data.get("FORWARDABLE_");
        this.autoCompleteSameAssignee = (String) data.get("AUTO_COMPLETE_SAME_ASSIGNEE_");
        this.autoCompleteEmptyAssignee = (String) data.get("AUTO_COMPLETE_EMPTY_ASSIGNEE_");
        this.inform = (String) data.get("INFORM_");
        this.priority = ((BigDecimal) data.get("PRIORITY_")).intValue();
        this.nodeEndUser = (String) data.get("NODE_END_USER_");
        this.nodeEndUserName = (String) data.get("NODE_END_USER_NAME_");
        this.nodeEndDate = (Date) data.get("NODE_END_DATE_");
        this.isolateSubProcDefCode = (String) data.get("ISOLATE_SUB_PROC_DEF_CODE_");
        this.isolateSubProcStatus = (String) data.get("ISOLATE_SUB_PROC_STATUS_");
        this.nodeStatus = (String) data.get("NODE_STATUS_");
        this.creationDate = (Date) data.get("CREATION_DATE_");
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

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
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

    public String getForwardable() {
        return forwardable;
    }

    public void setForwardable(String forwardable) {
        this.forwardable = forwardable;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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