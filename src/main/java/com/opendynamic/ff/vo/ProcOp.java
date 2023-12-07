package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class ProcOp implements Serializable {
    private static final long serialVersionUID = 1L;

    private String procOpId; // 流程操作ID
    private String operationId; // 操作ID
    private String operationType; // 操作类型
    private Integer operationOrder; // 操作顺序
    private Date operationDate; // 操作日期
    private String operationStatus; // 操作状态
    private String procId; // 流程ID
    private String procDefId; // 流程定义ID
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
    private Date creationDate; // 创建日期

    public ProcOp() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
     */
    public ProcOp(Map<String, Object> data) {
        this.procOpId = (String) data.get("PROC_OP_ID_");
        this.operationId = (String) data.get("OPERATION_ID_");
        this.operationType = (String) data.get("OPERATION_TYPE_");
        this.operationOrder = (data.get("OPERATION_ORDER_") != null) ? (((BigDecimal) data.get("OPERATION_ORDER_")).intValue()) : null;
        this.operationDate = (Date) data.get("OPERATION_DATE_");
        this.operationStatus = (String) data.get("OPERATION_STATUS_");
        this.procId = (String) data.get("PROC_ID_");
        this.procDefId = (String) data.get("PROC_DEF_ID_");
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
        this.creationDate = (Date) data.get("CREATION_DATE_");
    }

    public String getProcOpId() {
        return procOpId;
    }

    public void setProcOpId(String procOpId) {
        this.procOpId = procOpId;
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

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}