package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 代理。
 */
public class Delegate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String delegateId; // 代理ID。
    private String assignee; // 办理人。
    private String assigneeName; // 办理人名称。
    private String delegator; // 代理人。
    private String delegatorName; // 代理人名称。
    private Date startDate; // 开始日期。
    private Date endDate; // 结束日期。

    public Delegate() {
    }

    /**
     * 依据数据库数据构造。
     * 
     * @param data
     *        数据库数据。
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

    /**
     * 获取代理ID。
     * 
     * @return 代理ID。
     */
    public String getDelegateId() {
        return delegateId;
    }

    /**
     * 设置代理ID。
     * 
     * @param delegateId
     *        代理ID。
     */
    public void setDelegateId(String delegateId) {
        this.delegateId = delegateId;
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
     * 获取办理人名称。
     * 
     * @return 办理人名称。
     */
    public String getAssigneeName() {
        return assigneeName;
    }

    /**
     * 设置办理人名称。
     * 
     * @param assigneeName
     *        办理人名称。
     */
    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    /**
     * 获取代理人。
     * 
     * @return 代理人。
     */
    public String getDelegator() {
        return delegator;
    }

    /**
     * 设置代理人。
     * 
     * @param delegator
     *        代理人。
     */
    public void setDelegator(String delegator) {
        this.delegator = delegator;
    }

    /**
     * 获取代理人名称。
     * 
     * @return 代理人名称。
     */
    public String getDelegatorName() {
        return delegatorName;
    }

    /**
     * 设置代理人名称。
     * 
     * @param delegatorName
     *        代理人名称。
     */
    public void setDelegatorName(String delegatorName) {
        this.delegatorName = delegatorName;
    }

    /**
     * 获取开始日期。
     * 
     * @return 开始日期。
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置开始日期。
     * 
     * @param startDate
     *        开始日期。
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束日期。
     * 
     * @return 结束日期。
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束日期。
     * 
     * @param endDate
     *        结束日期。
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}