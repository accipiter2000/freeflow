package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 流程。
 */
public class Proc implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private Date creationDate; // 创建日期。
    private String procDefId; // 流程定义ID。
    private String procDefCode;// 流程定义编码。
    private String procDefName;// 流程定义名称。
    private String procDefCat;// 流程定义分类。
    private Integer version;// 版本。
    private String procDefStatus;// 流程定义状态。

    public Proc() {
    }

    /**
     * 依据数据库数据构造。
     * 
     * @param data
     *        数据库数据。
     */
    public Proc(Map<String, Object> data) {
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
        this.creationDate = (Date) data.get("CREATION_DATE_");
        this.procDefId = (String) data.get("PROC_DEF_ID_");
        this.procDefCode = (String) data.get("PROC_DEF_CODE_");
        this.procDefName = (String) data.get("PROC_DEF_NAME_");
        this.procDefCat = (String) data.get("PROC_DEF_CAT_");
        this.version = (data.get("VERSION_") != null) ? (((BigDecimal) data.get("VERSION_")).intValue()) : null;
        this.procDefStatus = (String) data.get("PROC_DEF_STATUS_");
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
}