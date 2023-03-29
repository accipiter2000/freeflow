package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Proc implements Serializable {
    private static final long serialVersionUID = 1L;

    private String procId; // 流程ID
    private String procDefId; // 流程定义ID
    private String adjustProcDefId; // 调整流程定义ID
    private String isolateSubProcNodeId; // 独立子流程所属节点ID
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
    private Date creationDate; // 创建日期
    private String procDefCode;// 流程定义编码
    private String procDefName;// 流程定义名称
    private String procDefCat;// 流程定义分类

    public Proc() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
     */
    public Proc(Map<String, Object> data) {
        this.procId = (String) data.get("PROC_ID_");
        this.procDefId = (String) data.get("PROC_DEF_ID_");
        this.adjustProcDefId = (String) data.get("ADJUST_PROC_DEF_ID_");
        this.isolateSubProcNodeId = (String) data.get("ISOLATE_SUB_PROC_NODE_ID_");
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
        this.creationDate = (Date) data.get("CREATION_DATE_");
        this.procDefCode = (String) data.get("PROC_DEF_CODE_");
        this.procDefName = (String) data.get("PROC_DEF_NAME_");
        this.procDefCat = (String) data.get("PROC_DEF_CAT_");
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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