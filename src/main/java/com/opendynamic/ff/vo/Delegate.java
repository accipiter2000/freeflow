package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Delegate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String delegateId; // 代理ID
    private String assignee; // 办理人
    private String assigneeName; // 办理人名称
    private String delegator; // 代理人
    private String delegatorName; // 代理人名称
    private Date startDate; // 开始日期
    private Date endDate; // 结束日期

    public Delegate() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
     */
    public Delegate(Map<String, Object> data) {
        this.delegateId = (String) data.get("DELEGATE_ID_");
        this.assignee = (String) data.get("ASSIGNEE_");
        this.assigneeName = (String) data.get("ASSIGNEE_NAME_");
        this.delegator = (String) data.get("DELEGATOR_");
        this.delegatorName = (String) data.get("DELEGATOR_NAME_");
        this.startDate = (Date) data.get("START_DATE_");
        this.endDate = (Date) data.get("END_DATE_");
    }

    public String getDelegateId() {
        return delegateId;
    }

    public void setDelegateId(String delegateId) {
        this.delegateId = delegateId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getDelegator() {
        return delegator;
    }

    public void setDelegator(String delegator) {
        this.delegator = delegator;
    }

    public String getDelegatorName() {
        return delegatorName;
    }

    public void setDelegatorName(String delegatorName) {
        this.delegatorName = delegatorName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}