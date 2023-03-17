package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String operationId; // 操作ID
    private String operation; // 操作
    private String procId; // 流程ID
    private String nodeId; // 节点ID
    private String taskId; // 任务ID
    private String memo; // 备注
    private String operator; // 操作人
    private String operatorName; // 操作人名称
    private Date operationDate; // 操作日期
    private String operationStatus; // 操作状态
    private String bizId; // 业务主键
    private String bizType; // 业务类型
    private String bizCode; // 业务编码
    private String bizName; // 业务名称
    private String procDefId; // 流程定义ID
    private String procDefCode; // 流程定义编码
    private String procDefName; // 流程定义名称
    private String procDefCat; // 流程定义分类

    public Operation() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
     */
    public Operation(Map<String, Object> data) {
        this.operationId = (String) data.get("OPERATION_ID_");
        this.operation = (String) data.get("OPERATION_");
        this.procId = (String) data.get("PROC_ID_");
        this.nodeId = (String) data.get("NODE_ID_");
        this.taskId = (String) data.get("TASK_ID_");
        this.memo = (String) data.get("MEMO_");
        this.operator = (String) data.get("OPERATOR_");
        this.operatorName = (String) data.get("OPERATOR_NAME_");
        this.operationDate = (Date) data.get("OPERATION_DATE_");
        this.operationStatus = (String) data.get("OPERATION_STATUS_");
        this.bizId = (String) data.get("BIZ_ID_");
        this.bizType = (String) data.get("BIZ_TYPE_");
        this.bizCode = (String) data.get("BIZ_CODE_");
        this.bizName = (String) data.get("BIZ_NAME_");
        this.procDefId = (String) data.get("PROC_DEF_ID_");
        this.procDefCode = (String) data.get("PROC_DEF_CODE_");
        this.procDefName = (String) data.get("PROC_DEF_NAME_");
        this.procDefCat = (String) data.get("PROC_DEF_CAT_");
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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
}