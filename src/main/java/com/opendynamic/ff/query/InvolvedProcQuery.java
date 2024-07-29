package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfProcService;
import com.opendynamic.ff.vo.Proc;

/**
 * 下级节点查询类。
 */
@Service
public class InvolvedProcQuery {
    private final FfProcService ffProcService;

    private String assignee;// 办理人。
    private List<String> assigneeList;// 办理人列表。
    private String procId;// 流程ID。
    private List<String> procIdList;// 流程ID列表。
    private String adjustProcDefId; // 调整流程定义ID。
    private List<String> adjustProcDefIdList; // 调整流程定义ID列表。
    private String isolateSubProcNodeId;// 独立子流程所属节点ID。
    private List<String> isolateSubProcNodeIdList;// 独立子流程所属节点ID列表。
    private String bizId;// 业务主键。
    private List<String> bizIdList;// 业务主键列表。
    private String bizType;// 业务类型。
    private List<String> bizTypeList;// 业务类型列表。
    private String bizCode;// 业务编码。
    private List<String> bizCodeList;// 业务编码列表。
    private String bizName;// 业务名称。
    private List<String> bizNameList;// 业务名称列表。
    private String bizDesc;// 业务备注。
    private List<String> bizDescList;// 业务备注列表。
    private String procStartUser; // 流程开始人员。
    private List<String> procStartUserList; // 流程开始人员列表。
    private String procStartUserName; // 流程开始人员名称。
    private List<String> procStartUserNameList; // 流程开始人员名称列表。
    private String procEndUser; // 流程完成人员。
    private List<String> procEndUserList; // 流程完成人员列表。
    private String procEndUserName;// 流程完成人员名称。
    private List<String> procEndUserNameList;// 流程完成人员名称列表。
    private Date fromProcEndDate;// 起始流程完成日期。
    private Date toProcEndDate;// 截止流程完成日期。
    private String procStatus;// 流程状态。
    private List<String> procStatusList;// 流程状态列表。
    private Date fromCreationDate;// 起始创建日期。
    private Date toCreationDate;// 截止创建日期。
    private String procDefId;// 流程定义ID。
    private List<String> procDefIdList;// 流程定义ID列表。
    private String procDefCode;// 流程定义编码。
    private List<String> procDefCodeList;// 流程定义编码列表。
    private String procDefName;// 流程定义名称。
    private List<String> procDefNameList;// 流程定义名称列表。
    private String procDefCat;// 流程定义分类。
    private List<String> procDefCatList;// 流程定义分类列表。
    private Integer version;// 版本。
    private List<Integer> versionList;// 版本列表。
    private String procDefStatus;// 流程定义状态。
    private List<String> procDefStatusList;// 流程定义状态列表。
    private Integer page;// 页。默认为1。
    private Integer limit;// 每页数据数量。默认为-1(全部)。
    private String dataScope;// 数据范围。

    public InvolvedProcQuery(FfProcService ffProcService) {
        super();
        this.ffProcService = ffProcService;
    }

    /**
     * 设置办理人。
     * 
     * @param assignee
     *        办理人。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setAssignee(String assignee) {
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
    public InvolvedProcQuery setAssigneeList(List<String> assigneeList) {
        this.assigneeList = assigneeList;
        return this;
    }

    /**
     * 设置流程ID。
     * 
     * @param procId
     *        流程ID。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    /**
     * 设置流程ID列表。
     * 
     * @param procIdList
     *        流程ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcIdList(List<String> procIdList) {
        this.procIdList = procIdList;
        return this;
    }

    /**
     * 设置调整流程定义ID。
     * 
     * @param adjustProcDefId
     *        调整流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setAdjustProcDefId(String adjustProcDefId) {
        this.adjustProcDefId = adjustProcDefId;
        return this;
    }

    /**
     * 设置调整流程定义ID列表。
     * 
     * @param adjustProcDefIdList
     *        调整流程定义ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setAdjustProcDefIdList(List<String> adjustProcDefIdList) {
        this.adjustProcDefIdList = adjustProcDefIdList;
        return this;
    }

    /**
     * 设置独立子流程所属节点ID。
     * 
     * @param isolateSubProcNodeId
     *        独立子流程所属节点ID。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setIsolateSubProcNodeId(String isolateSubProcNodeId) {
        this.isolateSubProcNodeId = isolateSubProcNodeId;
        return this;
    }

    /**
     * 设置独立子流程所属节点ID列表。
     * 
     * @param isolateSubProcNodeIdList
     *        独立子流程所属节点ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setIsolateSubProcNodeIdList(List<String> isolateSubProcNodeIdList) {
        this.isolateSubProcNodeIdList = isolateSubProcNodeIdList;
        return this;
    }

    /**
     * 设置业务主键。
     * 
     * @param bizId
     *        业务主键。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    /**
     * 设置业务主键列表。
     * 
     * @param bizIdList
     *        业务主键列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setBizIdList(List<String> bizIdList) {
        this.bizIdList = bizIdList;
        return this;
    }

    /**
     * 设置业务类型。
     * 
     * @param bizType
     *        业务类型。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    /**
     * 设置业务类型列表。
     * 
     * @param bizTypeList
     *        业务类型列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setBizTypeList(List<String> bizTypeList) {
        this.bizTypeList = bizTypeList;
        return this;
    }

    /**
     * 设置业务编码。
     * 
     * @param bizCode
     *        业务编码。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    /**
     * 设置业务编码列表。
     * 
     * @param bizCodeList
     *        业务编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setBizCodeList(List<String> bizCodeList) {
        this.bizCodeList = bizCodeList;
        return this;
    }

    /**
     * 设置业务名称。
     * 
     * @param bizName
     *        业务名称。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setBizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    /**
     * 设置业务名称列表。
     * 
     * @param bizNameList
     *        业务名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setBizNameList(List<String> bizNameList) {
        this.bizNameList = bizNameList;
        return this;
    }

    /**
     * 设置业务备注。
     * 
     * @param bizDesc
     *        业务备注。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
        return this;
    }

    /**
     * 设置业务备注列表。
     * 
     * @param bizDescList
     *        业务备注列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setBizDescList(List<String> bizDescList) {
        this.bizDescList = bizDescList;
        return this;
    }

    /**
     * 设置流程开始人员。
     * 
     * @param procStartUser
     *        流程开始人员。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcStartUser(String procStartUser) {
        this.procStartUser = procStartUser;
        return this;
    }

    /**
     * 设置流程开始人员列表。
     * 
     * @param procStartUserList
     *        流程开始人员列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcStartUserList(List<String> procStartUserList) {
        this.procStartUserList = procStartUserList;
        return this;
    }

    /**
     * 设置流程开始人员名称。
     * 
     * @param procStartUserName
     *        流程开始人员名称。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcStartUserName(String procStartUserName) {
        this.procStartUserName = procStartUserName;
        return this;
    }

    /**
     * 设置流程开始人员名称列表。
     * 
     * @param procStartUserNameList
     *        流程开始人员名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcStartUserNameList(List<String> procStartUserNameList) {
        this.procStartUserNameList = procStartUserNameList;
        return this;
    }

    /**
     * 设置流程完成人员。
     * 
     * @param procEndUser
     *        流程完成人员。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcEndUser(String procEndUser) {
        this.procEndUser = procEndUser;
        return this;
    }

    /**
     * 设置流程完成人员列表。
     * 
     * @param procEndUserList
     *        流程完成人员列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcEndUserList(List<String> procEndUserList) {
        this.procEndUserList = procEndUserList;
        return this;
    }

    /**
     * 设置流程完成人员名称。
     * 
     * @param procEndUserName
     *        流程完成人员名称。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcEndUserName(String procEndUserName) {
        this.procEndUserName = procEndUserName;
        return this;
    }

    /**
     * 设置流程完成人员名称列表。
     * 
     * @param procEndUserNameList
     *        流程完成人员名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcEndUserNameList(List<String> procEndUserNameList) {
        this.procEndUserNameList = procEndUserNameList;
        return this;
    }

    /**
     * 设置起始流程完成日期。
     * 
     * @param fromProcEndDate
     *        起始流程完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setFromProcEndDate(Date fromProcEndDate) {
        this.fromProcEndDate = fromProcEndDate;
        return this;
    }

    /**
     * 设置截止流程完成日期。
     * 
     * @param toProcEndDate
     *        截止流程完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setToProcEndDate(Date toProcEndDate) {
        this.toProcEndDate = toProcEndDate;
        return this;
    }

    /**
     * 设置流程状态。
     * 
     * @param procStatus
     *        流程状态。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcStatus(String procStatus) {
        this.procStatus = procStatus;
        return this;
    }

    /**
     * 设置流程状态列表。
     * 
     * @param procStatusList
     *        流程状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcStatusList(List<String> procStatusList) {
        this.procStatusList = procStatusList;
        return this;
    }

    /**
     * 设置起始创建日期。
     * 
     * @param fromCreationDate
     *        起始创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
        return this;
    }

    /**
     * 设置截止创建日期。
     * 
     * @param toCreationDate
     *        截止创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
        return this;
    }

    /**
     * 设置流程定义ID。
     * 
     * @param procDefId
     *        流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcDefId(String procDefId) {
        this.procDefId = procDefId;
        return this;
    }

    /**
     * 设置流程定义ID列表。
     * 
     * @param procDefIdList
     *        流程定义ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcDefIdList(List<String> procDefIdList) {
        this.procDefIdList = procDefIdList;
        return this;
    }

    /**
     * 设置流程定义编码。
     * 
     * @param procDefCode
     *        流程定义编码。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
        return this;
    }

    /**
     * 设置流程定义编码列表。
     * 
     * @param procDefCodeList
     *        流程定义编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcDefCodeList(List<String> procDefCodeList) {
        this.procDefCodeList = procDefCodeList;
        return this;
    }

    /**
     * 设置流程定义名称。
     * 
     * @param procDefName
     *        流程定义名称。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcDefName(String procDefName) {
        this.procDefName = procDefName;
        return this;
    }

    /**
     * 设置流程定义名称列表。
     * 
     * @param procDefNameList
     *        流程定义名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcDefNameList(List<String> procDefNameList) {
        this.procDefNameList = procDefNameList;
        return this;
    }

    /**
     * 设置流程定义分类。
     * 
     * @param procDefCat
     *        流程定义分类。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
        return this;
    }

    /**
     * 设置流程定义分类列表。
     * 
     * @param procDefCatList
     *        流程定义分类列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcDefCatList(List<String> procDefCatList) {
        this.procDefCatList = procDefCatList;
        return this;
    }

    /**
     * 设置版本。
     * 
     * @param version
     *        版本。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * 设置版本列表。
     * 
     * @param versionList
     *        版本列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setVersionList(List<Integer> versionList) {
        this.versionList = versionList;
        return this;
    }

    /**
     * 设置流程定义状态。
     * 
     * @param procDefStatus
     *        流程定义状态。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
        return this;
    }

    /**
     * 设置流程定义状态列表。
     * 
     * @param procDefStatusList
     *        流程定义状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setProcDefStatusList(List<String> procDefStatusList) {
        this.procDefStatusList = procDefStatusList;
        return this;
    }

    /**
     * 设置页。默认为1。
     * 
     * @param page
     *        页。默认为1。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setPage(Integer page) {
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
    public InvolvedProcQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 设置数据范围。
     * 
     * @param dataScope
     *        数据范围。
     * @return 当前查询实例，支持链式调用。
     */
    public InvolvedProcQuery setDataScope(String dataScope) {
        this.dataScope = dataScope;
        return this;
    }

    /**
     * 查询对象列表。数据格式为Map。
     * 
     * @return Map类型数据列表。
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffProcService.selectInvolvedProc(assignee, assigneeList, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromCreationDate, toCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName, procDefNameList, procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, page, limit, dataScope);
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
    public List<Proc> queryForObjectList() {
        List<Map<String, Object>> resultList = queryForMapList();
        List<Proc> procList = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
            procList.add(new Proc(result));
        }

        return procList;
    }

    /**
     * 查询单个对象。数据格式为实体Bean。
     * 
     * @return 单个Bean类型数据。
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
     * @return 总数。
     */
    public int count() {
        return ffProcService.countInvolvedProc(assignee, assigneeList, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromCreationDate, toCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName, procDefNameList, procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, dataScope);
    }
}