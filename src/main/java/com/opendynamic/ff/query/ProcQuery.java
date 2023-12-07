package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfProcService;
import com.opendynamic.ff.vo.Proc;

@Service
public class ProcQuery {
    private FfProcService ffProcService;

    private String procId;
    private List<String> procIdList;
    private String adjustProcDefId;
    private List<String> adjustProcDefIdList;
    private String isolateSubProcNodeId;
    private List<String> isolateSubProcNodeIdList;
    private String bizId;
    private List<String> bizIdList;
    private String bizType;
    private List<String> bizTypeList;
    private String bizCode;
    private List<String> bizCodeList;
    private String bizName;
    private List<String> bizNameList;
    private String bizDesc;
    private List<String> bizDescList;
    private String procStartUser;
    private List<String> procStartUserList;
    private String procStartUserName;
    private List<String> procStartUserNameList;
    private String procEndUser;
    private List<String> procEndUserList;
    private String procEndUserName;
    private List<String> procEndUserNameList;
    private Date fromProcEndDate;
    private Date toProcEndDate;
    private String procStatus;
    private List<String> procStatusList;
    private Date fromCreationDate;
    private Date toCreationDate;
    private String procDefId;
    private List<String> procDefIdList;
    private String procDefCode;
    private List<String> procDefCodeList;
    private String procDefName;
    private List<String> procDefNameList;
    private String procDefCat;
    private List<String> procDefCatList;
    private Integer version;
    private List<Integer> versionList;
    private String procDefStatus;
    private List<String> procDefStatusList;
    private Integer page;
    private Integer limit;
    private String dataScope;

    public ProcQuery(FfProcService ffProcService) {
        super();
        this.ffProcService = ffProcService;
    }

    public ProcQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    public ProcQuery setProcIdList(List<String> procIdList) {
        this.procIdList = procIdList;
        return this;
    }

    public ProcQuery setAdjustProcDefId(String adjustProcDefId) {
        this.adjustProcDefId = adjustProcDefId;
        return this;
    }

    public ProcQuery setAdjustProcDefIdList(List<String> adjustProcDefIdList) {
        this.adjustProcDefIdList = adjustProcDefIdList;
        return this;
    }

    public ProcQuery setIsolateSubProcNodeId(String isolateSubProcNodeId) {
        this.isolateSubProcNodeId = isolateSubProcNodeId;
        return this;
    }

    public ProcQuery setIsolateSubProcNodeIdList(List<String> isolateSubProcNodeIdList) {
        this.isolateSubProcNodeIdList = isolateSubProcNodeIdList;
        return this;
    }

    public ProcQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    public ProcQuery setBizIdList(List<String> bizIdList) {
        this.bizIdList = bizIdList;
        return this;
    }

    public ProcQuery setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    public ProcQuery setBizTypeList(List<String> bizTypeList) {
        this.bizTypeList = bizTypeList;
        return this;
    }

    public ProcQuery setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    public ProcQuery setBizCodeList(List<String> bizCodeList) {
        this.bizCodeList = bizCodeList;
        return this;
    }

    public ProcQuery setBizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    public ProcQuery setBizNameList(List<String> bizNameList) {
        this.bizNameList = bizNameList;
        return this;
    }

    public ProcQuery setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
        return this;
    }

    public ProcQuery setBizDescList(List<String> bizDescList) {
        this.bizDescList = bizDescList;
        return this;
    }

    public ProcQuery setProcStartUser(String procStartUser) {
        this.procStartUser = procStartUser;
        return this;
    }

    public ProcQuery setProcStartUserList(List<String> procStartUserList) {
        this.procStartUserList = procStartUserList;
        return this;
    }

    public ProcQuery setProcStartUserName(String procStartUserName) {
        this.procStartUserName = procStartUserName;
        return this;
    }

    public ProcQuery setProcStartUserNameList(List<String> procStartUserNameList) {
        this.procStartUserNameList = procStartUserNameList;
        return this;
    }

    public ProcQuery setProcEndUser(String procEndUser) {
        this.procEndUser = procEndUser;
        return this;
    }

    public ProcQuery setProcEndUserList(List<String> procEndUserList) {
        this.procEndUserList = procEndUserList;
        return this;
    }

    public ProcQuery setProcEndUserName(String procEndUserName) {
        this.procEndUserName = procEndUserName;
        return this;
    }

    public ProcQuery setProcEndUserNameList(List<String> procEndUserNameList) {
        this.procEndUserNameList = procEndUserNameList;
        return this;
    }

    public ProcQuery setFromProcEndDate(Date fromProcEndDate) {
        this.fromProcEndDate = fromProcEndDate;
        return this;
    }

    public ProcQuery setToProcEndDate(Date toProcEndDate) {
        this.toProcEndDate = toProcEndDate;
        return this;
    }

    public ProcQuery setProcStatus(String procStatus) {
        this.procStatus = procStatus;
        return this;
    }

    public ProcQuery setProcStatusList(List<String> procStatusList) {
        this.procStatusList = procStatusList;
        return this;
    }

    public ProcQuery setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
        return this;
    }

    public ProcQuery setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
        return this;
    }

    public ProcQuery setProcDefId(String procDefId) {
        this.procDefId = procDefId;
        return this;
    }

    public ProcQuery setProcDefIdList(List<String> procDefIdList) {
        this.procDefIdList = procDefIdList;
        return this;
    }

    public ProcQuery setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
        return this;
    }

    public ProcQuery setProcDefCodeList(List<String> procDefCodeList) {
        this.procDefCodeList = procDefCodeList;
        return this;
    }

    public ProcQuery setProcDefName(String procDefName) {
        this.procDefName = procDefName;
        return this;
    }

    public ProcQuery setProcDefNameList(List<String> procDefNameList) {
        this.procDefNameList = procDefNameList;
        return this;
    }

    public ProcQuery setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
        return this;
    }

    public ProcQuery setProcDefCatList(List<String> procDefCatList) {
        this.procDefCatList = procDefCatList;
        return this;
    }

    public ProcQuery setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public ProcQuery setVersionList(List<Integer> versionList) {
        this.versionList = versionList;
        return this;
    }

    public ProcQuery setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
        return this;
    }

    public ProcQuery setProcDefStatusList(List<String> procDefStatusList) {
        this.procDefStatusList = procDefStatusList;
        return this;
    }

    public ProcQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    public ProcQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public ProcQuery setDataScope(String dataScope) {
        this.dataScope = dataScope;
        return this;
    }

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffProcService.selectProc(procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromCreationDate, toCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName, procDefNameList, procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, page, limit, dataScope);
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
        return ffProcService.countProc(procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromCreationDate, toCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName, procDefNameList, procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, dataScope);
    }
}