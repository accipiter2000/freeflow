package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfDelegateService;
import com.opendynamic.ff.vo.Delegate;

/**
 * 代理查询类。
 */
@Service
public class DelegateQuery {
    private final FfDelegateService ffDelegateService;

    private String delegateId; // 代理ID。
    private List<String> delegateIdList; // 代理ID列表。
    private String assignee;// 办理人。
    private List<String> assigneeList;// 办理人列表。
    private String assigneeName; // 办理人名称。
    private List<String> assigneeNameList; // 办理人名称列表。
    private String delegator;// 代理人。
    private List<String> delegatorList;// 代理人列表。
    private String delegatorName;// 代理人名称。
    private List<String> delegatorNameList;// 代理人名称列表。
    private Date fromStartDate;// 起始开始日期。
    private Date toStartDate;// 截止开始日期。
    private Date fromEndDate;// 起始结束日期。
    private Date toEndDate;// 截止结束日期。
    private Integer page;// 页。默认为1。
    private Integer limit; // 每页数据数量。默认为-1(全部)。

    public DelegateQuery(FfDelegateService ffDelegateService) {
        super();
        this.ffDelegateService = ffDelegateService;
    }

    /**
     * 设置代理ID。
     * 
     * @param delegateId
     *        代理ID。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setDelegateId(String delegateId) {
        this.delegateId = delegateId;
        return this;
    }

    /**
     * 设置代理ID列表。
     * 
     * @param delegateIdList
     *        代理ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setDelegateIdList(List<String> delegateIdList) {
        this.delegateIdList = delegateIdList;
        return this;
    }

    /**
     * 设置办理人。
     * 
     * @param assignee
     *        办理人。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setAssignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    /**
     * 设置办理人列表。
     * 
     * @param assigneeList
     *        办理人列表。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setAssigneeList(List<String> assigneeList) {
        this.assigneeList = assigneeList;
        return this;
    }

    /**
     * 设置办理人名称。
     * 
     * @param assigneeName
     *        办理人名称。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
        return this;
    }

    /**
     * 设置办理人名称列表。
     * 
     * @param assigneeNameList
     *        办理人名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setAssigneeNameList(List<String> assigneeNameList) {
        this.assigneeNameList = assigneeNameList;
        return this;
    }

    /**
     * 设置代理人。
     * 
     * @param delegator
     *        代理人。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setDelegator(String delegator) {
        this.delegator = delegator;
        return this;
    }

    /**
     * 设置代理人列表。
     * 
     * @param delegatorList
     *        代理人列表。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setDelegatorList(List<String> delegatorList) {
        this.delegatorList = delegatorList;
        return this;
    }

    /**
     * 设置代理人名称。
     * 
     * @param delegatorName
     *        代理人名称。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setDelegatorName(String delegatorName) {
        this.delegatorName = delegatorName;
        return this;
    }

    /**
     * 设置代理人名称列表。
     * 
     * @param delegatorNameList
     *        代理人名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setDelegatorNameList(List<String> delegatorNameList) {
        this.delegatorNameList = delegatorNameList;
        return this;
    }

    /**
     * 设置起始开始日期。
     * 
     * @param fromStartDate
     *        起始开始日期。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setFromStartDate(Date fromStartDate) {
        this.fromStartDate = fromStartDate;
        return this;
    }

    /**
     * 设置截止开始日期。
     * 
     * @param toStartDate
     *        截止开始日期。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setToStartDate(Date toStartDate) {
        this.toStartDate = toStartDate;
        return this;
    }

    /**
     * 设置起始结束日期。
     * 
     * @param fromEndDate
     *        起始结束日期。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setFromEndDate(Date fromEndDate) {
        this.fromEndDate = fromEndDate;
        return this;
    }

    /**
     * 设置截止结束日期。
     * 
     * @param toEndDate
     *        截止结束日期。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setToEndDate(Date toEndDate) {
        this.toEndDate = toEndDate;
        return this;
    }

    /**
     * 设置页。默认为1。
     * 
     * @param page
     *        页。默认为1。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 设置每页数据数量。默认为-1(全部)。
     * 
     * @param limit
     *        每页数据数量。默认为-1(全部)。
     * @return 当前查询实例，支持链式调用。
     */
    public DelegateQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 查询对象列表。数据格式为Map。
     * 
     * @return Map类型数据列表。
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffDelegateService.selectDelegate(delegateId, delegateIdList, assignee, assigneeList, assigneeName, assigneeNameList, delegator, delegatorList, delegatorName, delegatorNameList, fromStartDate, toStartDate, fromEndDate, toEndDate, page, limit);
    }

    /**
     * 查询单个对象。数据格式为Map。
     * 
     * @return 单个Map类型数据。
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
     * 查询对象列表。数据格式为实体Bean。
     * 
     * @return Bean类型数据列表。
     */
    public List<Delegate> queryForObjectList() {
        List<Map<String, Object>> resultList = queryForMapList();
        List<Delegate> delegateList = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
            delegateList.add(new Delegate(result));
        }

        return delegateList;
    }

    /**
     * 查询单个对象。数据格式为实体Bean。
     * 
     * @return 单个Bean类型数据。
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
     * @return 总数。
     */
    public int count() {
        return ffDelegateService.countDelegate(delegateId, delegateIdList, assignee, assigneeList, assigneeName, assigneeNameList, delegator, delegatorList, delegatorName, delegatorNameList, fromStartDate, toStartDate, fromEndDate, toEndDate);
    }
}