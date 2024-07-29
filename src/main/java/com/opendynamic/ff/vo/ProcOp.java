package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 流程操作。记录流程操作的历史数据。
 */
public class ProcOp implements Serializable {
    private static final long serialVersionUID = 1L;

    private String procOpId; // 流程操作ID。
    private String operationId; // 操作ID。
    private String operationType; // 操作类型。
    private Integer operationOrder; // 操作顺序。
    private Date operationDate; // 操作日期。
    private String operationStatus; // 操作状态。
    private String procId; // 流程ID。
    private String procDefId; // 流程定义ID。
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
    private Date creationDate; // 创建日期。

    public ProcOp() {
    }

    /**
     * 依据数据库数据构造。
     * 
     * @param data
     *        数据库数据。
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

    /**
     * 获取流程操作ID。
     * 
     * @return 流程操作ID。
     */
    public String getProcOpId() {
        return procOpId;
    }

    /**
     * 设置流程操作ID。
     * 
     * @param procOpId
     *        流程操作ID。
     */
    public void setProcOpId(String procOpId) {
        this.procOpId = procOpId;
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