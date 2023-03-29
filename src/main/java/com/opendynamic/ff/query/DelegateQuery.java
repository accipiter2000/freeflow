package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfDelegateService;
import com.opendynamic.ff.vo.Delegate;

@Service
public class DelegateQuery {
    private FfDelegateService ffDelegateService;

    String delegateId;
    String assignee;
    String assigneeName;
    String delegator;
    String delegatorName;
    Date fromStartDate;
    Date toStartDate;
    Date fromEndDate;
    Date toEndDate;
    private Integer page;
    private Integer limit;

    public DelegateQuery(FfDelegateService ffDelegateService) {
        super();
        this.ffDelegateService = ffDelegateService;
    }

    public DelegateQuery setDelegateId(String delegateId) {
        this.delegateId = delegateId;
        return this;
    }

    public DelegateQuery setAssignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    public DelegateQuery setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
        return this;
    }

    public DelegateQuery setDelegator(String delegator) {
        this.delegator = delegator;
        return this;
    }

    public DelegateQuery setDelegatorName(String delegatorName) {
        this.delegatorName = delegatorName;
        return this;
    }

    public DelegateQuery setFromStartDate(Date fromStartDate) {
        this.fromStartDate = fromStartDate;
        return this;
    }

    public DelegateQuery setToStartDate(Date toStartDate) {
        this.toStartDate = toStartDate;
        return this;
    }

    public DelegateQuery setFromEndDate(Date fromEndDate) {
        this.fromEndDate = fromEndDate;
        return this;
    }

    public DelegateQuery setToEndDate(Date toEndDate) {
        this.toEndDate = toEndDate;
        return this;
    }

    public DelegateQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    public DelegateQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffDelegateService.selectDelegate(delegateId, assignee, assigneeName, delegator, delegatorName, fromStartDate, toStartDate, fromEndDate, toEndDate, page, limit);
    }

    /**
     * 查询单个对象。对象格式为Map。
     * 
     * @return
     */
    public Map<String, Object> queryForMap() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * 查询对象列表。对象格式为实体Bean。
     * 
     * @return
     */
    public List<Delegate> queryForObjectList() {
        List<Map<String, Object>> result = queryForMapList();
        List<Delegate> delegateList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            delegateList.add(new Delegate(result.get(i)));
        }

        return delegateList;
    }

    /**
     * 查询单个对象。对象格式为实体Bean。
     * 
     * @return
     */
    public Delegate queryForObject() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return new Delegate(result.get(0));
        }
        else {
            return null;
        }
    }

    /**
     * 查询总数。
     * 
     * @return
     */
    public int count() {
        return ffDelegateService.countDelegate(delegateId, assignee, assigneeName, delegator, delegatorName, fromStartDate, toStartDate, fromEndDate, toEndDate);
    }
}