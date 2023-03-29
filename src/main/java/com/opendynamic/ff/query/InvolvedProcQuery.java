package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfProcService;
import com.opendynamic.ff.vo.Proc;

@Service
public class InvolvedProcQuery {
    private FfProcService ffProcService;

    private List<String> assigneeList;
    private String procId;
    private String procDefId;
    private String adjustProcDefId;
    private String isolateSubProcNodeId;
    private String bizId;
    private List<String> bizTypeList;
    private String bizCode;
    private String bizName;
    private List<String> procStartUserList;
    private Date fromProcStartDate;
    private Date toProcStartDate;
    private List<String> procEndUserList;
    private Date fromProcEndDate;
    private Date toProcEndDate;
    private List<String> procStatusList;
    private Date fromCreationDate;
    private Date toCreationDate;
    private List<String> procDefCodeList;
    private String procDefCat;
    private Integer page;
    private Integer limit;

    public InvolvedProcQuery(FfProcService ffProcService) {
        super();
        this.ffProcService = ffProcService;
    }

    public InvolvedProcQuery setAssignee(String assignee) {
        if (StringUtils.isNotEmpty(assignee)) {
            this.assigneeList = new ArrayList<>();
            this.assigneeList.add(assignee);
        }
        return this;
    }

    public InvolvedProcQuery setAssigneeList(List<String> assigneeList) {
        this.assigneeList = assigneeList;
        return this;
    }

    public InvolvedProcQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    public InvolvedProcQuery setProcDefId(String procDefId) {
        this.procDefId = procDefId;
        return this;
    }

    public InvolvedProcQuery setAdjustProcDefId(String adjustProcDefId) {
        this.adjustProcDefId = adjustProcDefId;
        return this;
    }

    public InvolvedProcQuery setIsolateSubProcNodeId(String isolateSubProcNodeId) {
        this.isolateSubProcNodeId = isolateSubProcNodeId;
        return this;
    }

    public InvolvedProcQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    public InvolvedProcQuery setBizType(String bizType) {
        if (StringUtils.isNotEmpty(bizType)) {
            this.bizTypeList = new ArrayList<>();
            this.bizTypeList.add(bizType);
        }
        return this;
    }

    public InvolvedProcQuery setBizTypeList(List<String> bizTypeList) {
        this.bizTypeList = bizTypeList;
        return this;
    }

    public InvolvedProcQuery setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    public InvolvedProcQuery setBizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    public InvolvedProcQuery setProcStartUser(String procStartUser) {
        if (StringUtils.isNotEmpty(procStartUser)) {
            this.procStartUserList = new ArrayList<>();
            this.procStartUserList.add(procStartUser);
        }
        return this;
    }

    public InvolvedProcQuery setProcStartUserList(List<String> procStartUserList) {
        this.procStartUserList = procStartUserList;
        return this;
    }

    public InvolvedProcQuery setFromProcStartDate(Date fromProcStartDate) {
        this.fromProcStartDate = fromProcStartDate;
        return this;
    }

    public InvolvedProcQuery setToProcStartDate(Date toProcStartDate) {
        this.toProcStartDate = toProcStartDate;
        return this;
    }

    public InvolvedProcQuery setProcEndUser(String procEndUser) {
        if (StringUtils.isNotEmpty(procEndUser)) {
            this.procEndUserList = new ArrayList<>();
            this.procEndUserList.add(procEndUser);
        }
        return this;
    }

    public InvolvedProcQuery setProcEndUserList(List<String> procEndUserList) {
        this.procEndUserList = procEndUserList;
        return this;
    }

    public InvolvedProcQuery setFromProcEndDate(Date fromProcEndDate) {
        this.fromProcEndDate = fromProcEndDate;
        return this;
    }

    public InvolvedProcQuery setToProcEndDate(Date toProcEndDate) {
        this.toProcEndDate = toProcEndDate;
        return this;
    }

    public InvolvedProcQuery setProcStatus(String procStatus) {
        if (StringUtils.isNotEmpty(procStatus)) {
            this.procStatusList = new ArrayList<>();
            this.procStatusList.add(procStatus);
        }
        return this;
    }

    public InvolvedProcQuery setProcStatusList(List<String> procStatusList) {
        this.procStatusList = procStatusList;
        return this;
    }

    public InvolvedProcQuery setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
        return this;
    }

    public InvolvedProcQuery setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
        return this;
    }

    public InvolvedProcQuery setProcDefCode(String procDefCode) {
        if (StringUtils.isNotEmpty(procDefCode)) {
            this.procDefCodeList = new ArrayList<>();
            this.procDefCodeList.add(procDefCode);
        }
        return this;
    }

    public InvolvedProcQuery setProcDefCodeList(List<String> procDefCodeList) {
        this.procDefCodeList = procDefCodeList;
        return this;
    }

    public InvolvedProcQuery setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
        return this;
    }

    public InvolvedProcQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    public InvolvedProcQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffProcService.selectInvolvedProc(assigneeList, procId, procDefId, adjustProcDefId, isolateSubProcNodeId, bizId, bizTypeList, bizCode, bizName, procStartUserList, fromProcStartDate, toProcStartDate, procEndUserList, fromProcEndDate, toProcEndDate, procStatusList, fromCreationDate, toCreationDate, procDefCodeList, procDefCat, page, limit);
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
    public List<Proc> queryForObjectList() {
        List<Map<String, Object>> result = queryForMapList();
        List<Proc> procList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            procList.add(new Proc(result.get(i)));
        }

        return procList;
    }

    /**
     * 查询单个对象。对象格式为实体Bean。
     * 
     * @return
     */
    public Proc queryForObject() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return new Proc(result.get(0));
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
        return ffProcService.countInvolvedProc(assigneeList, procId, procDefId, adjustProcDefId, isolateSubProcNodeId, bizId, bizTypeList, bizCode, bizName, procStartUserList, fromProcStartDate, toProcStartDate, procEndUserList, fromProcEndDate, toProcEndDate, procStatusList, fromCreationDate, toCreationDate, procDefCodeList, procDefCat);
    }
}