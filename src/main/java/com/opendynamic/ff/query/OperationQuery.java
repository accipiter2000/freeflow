package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfOperationService;
import com.opendynamic.ff.vo.Operation;

/**
 * 操作查询类。
 */
@Service
public class OperationQuery {
    private final FfOperationService ffOperationService;

    private String operationId; // 操作ID。
    private List<String> operationIdList; // 操作ID列表。
    private String operation;// 操作。
    private List<String> operationList;// 操作列表。
    private String procId;// 流程ID。
    private List<String> procIdList;// 流程ID列表。
    private String nodeId;// 节点ID。
    private List<String> nodeIdList;// 节点ID列表。
    private String taskId;// 任务ID。
    private List<String> taskIdList;// 任务ID列表。
    private String operator;// 操作人。
    private List<String> operatorList;// 操作人列表。
    private String operatorName; // 操作人名称。
    private List<String> operatorNameList; // 操作人名称列表。
    private Date fromOperationDate; // 起始操作日期。
    private Date toOperationDate; // 截止操作日期。
    private String operationStatus;// 操作状态。
    private List<String> operationStatusList;// 操作状态列表。
    private String adjustProcDefId;// 调整流程定义ID。
    private List<String> adjustProcDefIdList;// 调整流程定义ID列表。
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
    private String procStartUser;// 流程开始人员。
    private List<String> procStartUserList;// 流程开始人员列表。
    private String procStartUserName;// 流程开始人员名称。
    private List<String> procStartUserNameList;// 流程开始人员名称列表。
    private String procEndUser; // 流程完成人员。
    private List<String> procEndUserList; // 流程完成人员列表。
    private String procEndUserName;// 流程完成人员名称。
    private List<String> procEndUserNameList;// 流程完成人员名称列表。
    private Date fromProcEndDate; // 起始流程完成日期。
    private Date toProcEndDate; // 截止流程完成日期。
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

    public OperationQuery(FfOperationService ffOperationService) {
        super();
        this.ffOperationService = ffOperationService;
    }

    /**
     * 设置操作ID。
     * 
     * @param operationId
     *        操作ID。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    /**
     * 设置操作ID列表。
     * 
     * @param operationIdList
     *        操作ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setOperationIdList(List<String> operationIdList) {
        this.operationIdList = operationIdList;
        return this;
    }

    /**
     * 设置操作。
     * 
     * @param operation
     *        操作。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    /**
     * 设置操作列表。
     * 
     * @param operationList
     *        操作列表。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setOperationList(List<String> operationList) {
        this.operationList = operationList;
        return this;
    }

    /**
     * 设置流程ID。
     * 
     * @param procId
     *        流程ID。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setProcId(String procId) {
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
    public OperationQuery setProcIdList(List<String> procIdList) {
        this.procIdList = procIdList;
        return this;
    }

    /**
     * 设置节点ID。
     * 
     * @param nodeId
     *        节点ID。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * 设置节点ID列表。
     * 
     * @param nodeIdList
     *        节点ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setNodeIdList(List<String> nodeIdList) {
        this.nodeIdList = nodeIdList;
        return this;
    }

    /**
     * 设置任务ID。
     * 
     * @param taskId
     *        任务ID。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    /**
     * 设置任务ID列表。
     * 
     * @param taskIdList
     *        任务ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setTaskIdList(List<String> taskIdList) {
        this.taskIdList = taskIdList;
        return this;
    }

    /**
     * 设置操作人。
     * 
     * @param operator
     *        操作人。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    /**
     * 设置操作人列表。
     * 
     * @param operatorList
     *        操作人列表。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setOperatorList(List<String> operatorList) {
        this.operatorList = operatorList;
        return this;
    }

    /**
     * 设置操作人名称。
     * 
     * @param operatorName
     *        操作人名称。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 设置操作人名称列表。
     * 
     * @param operatorNameList
     *        操作人名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setOperatorNameList(List<String> operatorNameList) {
        this.operatorNameList = operatorNameList;
        return this;
    }

    /**
     * 设置起始操作日期。
     * 
     * @param fromOperationDate
     *        起始操作日期。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setFromOperationDate(Date fromOperationDate) {
        this.fromOperationDate = fromOperationDate;
        return this;
    }

    /**
     * 设置截止操作日期。
     * 
     * @param toOperationDate
     *        截止操作日期。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setToOperationDate(Date toOperationDate) {
        this.toOperationDate = toOperationDate;
        return this;
    }

    /**
     * 设置操作状态。
     * 
     * @param operationStatus
     *        操作状态。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
        return this;
    }

    /**
     * 设置操作状态列表。
     * 
     * @param operationStatusList
     *        操作状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setOperationStatusList(List<String> operationStatusList) {
        this.operationStatusList = operationStatusList;
        return this;
    }

    /**
     * 设置调整流程定义ID。
     * 
     * @param adjustProcDefId
     *        调整流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public OperationQuery setAdjustProcDefId(String adjustProcDefId) {
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
    public OperationQuery setAdjustProcDefIdList(List<String> adjustProcDefIdList) {
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
    public OperationQuery setIsolateSubProcNodeId(String isolateSubProcNodeId) {
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
    public OperationQuery setIsolateSubProcNodeIdList(List<String> isolateSubProcNodeIdList) {
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
    public OperationQuery setBizId(String bizId) {
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
    public OperationQuery setBizIdList(List<String> bizIdList) {
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
    public OperationQuery setBizType(String bizType) {
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
    public OperationQuery setBizTypeList(List<String> bizTypeList) {
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
    public OperationQuery setBizCode(String bizCode) {
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
    public OperationQuery setBizCodeList(List<String> bizCodeList) {
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
    public OperationQuery setBizName(String bizName) {
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
    public OperationQuery setBizNameList(List<String> bizNameList) {
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
    public OperationQuery setBizDesc(String bizDesc) {
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
    public OperationQuery setBizDescList(List<String> bizDescList) {
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
    public OperationQuery setProcStartUser(String procStartUser) {
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
    public OperationQuery setProcStartUserList(List<String> procStartUserList) {
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
    public OperationQuery setProcStartUserName(String procStartUserName) {
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
    public OperationQuery setProcStartUserNameList(List<String> procStartUserNameList) {
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
    public OperationQuery setProcEndUser(String procEndUser) {
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
    public OperationQuery setProcEndUserList(List<String> procEndUserList) {
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
    public OperationQuery setProcEndUserName(String procEndUserName) {
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
    public OperationQuery setProcEndUserNameList(List<String> procEndUserNameList) {
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
    public OperationQuery setFromProcEndDate(Date fromProcEndDate) {
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
    public OperationQuery setToProcEndDate(Date toProcEndDate) {
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
    public OperationQuery setProcStatus(String procStatus) {
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
    public OperationQuery setProcStatusList(List<String> procStatusList) {
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
    public OperationQuery setFromCreationDate(Date fromCreationDate) {
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
    public OperationQuery setToCreationDate(Date toCreationDate) {
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
    public OperationQuery setProcDefId(String procDefId) {
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
    public OperationQuery setProcDefIdList(List<String> procDefIdList) {
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
    public OperationQuery setProcDefCode(String procDefCode) {
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
    public OperationQuery setProcDefCodeList(List<String> procDefCodeList) {
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
    public OperationQuery setProcDefName(String procDefName) {
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
    public OperationQuery setProcDefNameList(List<String> procDefNameList) {
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
    public OperationQuery setProcDefCat(String procDefCat) {
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
    public OperationQuery setProcDefCatList(List<String> procDefCatList) {
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
    public OperationQuery setVersion(Integer version) {
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
    public OperationQuery setVersionList(List<Integer> versionList) {
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
    public OperationQuery setProcDefStatus(String procDefStatus) {
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
    public OperationQuery setProcDefStatusList(List<String> procDefStatusList) {
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
    public OperationQuery setPage(Integer page) {
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
    public OperationQuery setLimit(Integer limit) {
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
    public OperationQuery setDataScope(String dataScope) {
        this.dataScope = dataScope;
        return this;
    }

    /**
     * 查询对象列表。数据格式为Map。
     * 
     * @return Map类型数据列表。
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffOperationService.selectOperation(operationId, operationIdList, operation, operationList, nodeId, nodeIdList, taskId, taskIdList, operator, operatorList, operatorName, operatorNameList, fromOperationDate, toOperationDate, operationStatus, operationStatusList, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromCreationDate, toCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName,
                procDefNameList, procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, page, limit, dataScope);
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
    public List<Operation> queryForObjectList() {
        List<Map<String, Object>> resultList = queryForMapList();
        List<Operation> operationList = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
            operationList.add(new Operation(result));
        }

        return operationList;
    }

    /**
     * 查询单个对象。数据格式为实体Bean。
     * 
     * @return 单个Bean类型数据。
     */
    public Operation queryForObject() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return new Operation(result.get(0));
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
        return ffOperationService.countOperation(operationId, operationIdList, operation, operationList, nodeId, nodeIdList, taskId, taskIdList, operator, operatorList, operatorName, operatorNameList, fromOperationDate, toOperationDate, operationStatus, operationStatusList, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromCreationDate, toCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName,
                procDefNameList, procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, dataScope);
    }
}