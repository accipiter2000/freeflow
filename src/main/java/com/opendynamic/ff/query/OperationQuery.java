package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfOperationService;
import com.opendynamic.ff.vo.Operation;

@Service
public class OperationQuery {
    private FfOperationService ffOperationService;

    private String operationId;
    private List<String> operationIdList;
    private String operation;
    private List<String> operationList;
    private String nodeId;
    private List<String> nodeIdList;
    private String taskId;
    private List<String> taskIdList;
    private String operator;
    private List<String> operatorList;
    private String operatorName;
    private List<String> operatorNameList;
    private Date fromOperationDate;
    private Date toOperationDate;
    private String operationStatus;
    private List<String> operationStatusList;
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

    public OperationQuery(FfOperationService ffOperationService) {
        super();
        this.ffOperationService = ffOperationService;
    }

    public OperationQuery setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public OperationQuery setOperationIdList(List<String> operationIdList) {
        this.operationIdList = operationIdList;
        return this;
    }

    public OperationQuery setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public OperationQuery setOperationList(List<String> operationList) {
        this.operationList = operationList;
        return this;
    }

    public OperationQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public OperationQuery setNodeIdList(List<String> nodeIdList) {
        this.nodeIdList = nodeIdList;
        return this;
    }

    public OperationQuery setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public OperationQuery setTaskIdList(List<String> taskIdList) {
        this.taskIdList = taskIdList;
        return this;
    }

    public OperationQuery setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public OperationQuery setOperatorList(List<String> operatorList) {
        this.operatorList = operatorList;
        return this;
    }

    public OperationQuery setOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public OperationQuery setOperatorNameList(List<String> operatorNameList) {
        this.operatorNameList = operatorNameList;
        return this;
    }

    public OperationQuery setFromOperationDate(Date fromOperationDate) {
        this.fromOperationDate = fromOperationDate;
        return this;
    }

    public OperationQuery setToOperationDate(Date toOperationDate) {
        this.toOperationDate = toOperationDate;
        return this;
    }

    public OperationQuery setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
        return this;
    }

    public OperationQuery setOperationStatusList(List<String> operationStatusList) {
        this.operationStatusList = operationStatusList;
        return this;
    }

    public OperationQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    public OperationQuery setProcIdList(List<String> procIdList) {
        this.procIdList = procIdList;
        return this;
    }

    public OperationQuery setAdjustProcDefId(String adjustProcDefId) {
        this.adjustProcDefId = adjustProcDefId;
        return this;
    }

    public OperationQuery setAdjustProcDefIdList(List<String> adjustProcDefIdList) {
        this.adjustProcDefIdList = adjustProcDefIdList;
        return this;
    }

    public OperationQuery setIsolateSubProcNodeId(String isolateSubProcNodeId) {
        this.isolateSubProcNodeId = isolateSubProcNodeId;
        return this;
    }

    public OperationQuery setIsolateSubProcNodeIdList(List<String> isolateSubProcNodeIdList) {
        this.isolateSubProcNodeIdList = isolateSubProcNodeIdList;
        return this;
    }

    public OperationQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    public OperationQuery setBizIdList(List<String> bizIdList) {
        this.bizIdList = bizIdList;
        return this;
    }

    public OperationQuery setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    public OperationQuery setBizTypeList(List<String> bizTypeList) {
        this.bizTypeList = bizTypeList;
        return this;
    }

    public OperationQuery setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    public OperationQuery setBizCodeList(List<String> bizCodeList) {
        this.bizCodeList = bizCodeList;
        return this;
    }

    public OperationQuery setBizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    public OperationQuery setBizNameList(List<String> bizNameList) {
        this.bizNameList = bizNameList;
        return this;
    }

    public OperationQuery setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
        return this;
    }

    public OperationQuery setBizDescList(List<String> bizDescList) {
        this.bizDescList = bizDescList;
        return this;
    }

    public OperationQuery setProcStartUser(String procStartUser) {
        this.procStartUser = procStartUser;
        return this;
    }

    public OperationQuery setProcStartUserList(List<String> procStartUserList) {
        this.procStartUserList = procStartUserList;
        return this;
    }

    public OperationQuery setProcStartUserName(String procStartUserName) {
        this.procStartUserName = procStartUserName;
        return this;
    }

    public OperationQuery setProcStartUserNameList(List<String> procStartUserNameList) {
        this.procStartUserNameList = procStartUserNameList;
        return this;
    }

    public OperationQuery setProcEndUser(String procEndUser) {
        this.procEndUser = procEndUser;
        return this;
    }

    public OperationQuery setProcEndUserList(List<String> procEndUserList) {
        this.procEndUserList = procEndUserList;
        return this;
    }

    public OperationQuery setProcEndUserName(String procEndUserName) {
        this.procEndUserName = procEndUserName;
        return this;
    }

    public OperationQuery setProcEndUserNameList(List<String> procEndUserNameList) {
        this.procEndUserNameList = procEndUserNameList;
        return this;
    }

    public OperationQuery setFromProcEndDate(Date fromProcEndDate) {
        this.fromProcEndDate = fromProcEndDate;
        return this;
    }

    public OperationQuery setToProcEndDate(Date toProcEndDate) {
        this.toProcEndDate = toProcEndDate;
        return this;
    }

    public OperationQuery setProcStatus(String procStatus) {
        this.procStatus = procStatus;
        return this;
    }

    public OperationQuery setProcStatusList(List<String> procStatusList) {
        this.procStatusList = procStatusList;
        return this;
    }

    public OperationQuery setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
        return this;
    }

    public OperationQuery setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
        return this;
    }

    public OperationQuery setProcDefId(String procDefId) {
        this.procDefId = procDefId;
        return this;
    }

    public OperationQuery setProcDefIdList(List<String> procDefIdList) {
        this.procDefIdList = procDefIdList;
        return this;
    }

    public OperationQuery setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
        return this;
    }

    public OperationQuery setProcDefCodeList(List<String> procDefCodeList) {
        this.procDefCodeList = procDefCodeList;
        return this;
    }

    public OperationQuery setProcDefName(String procDefName) {
        this.procDefName = procDefName;
        return this;
    }

    public OperationQuery setProcDefNameList(List<String> procDefNameList) {
        this.procDefNameList = procDefNameList;
        return this;
    }

    public OperationQuery setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
        return this;
    }

    public OperationQuery setProcDefCatList(List<String> procDefCatList) {
        this.procDefCatList = procDefCatList;
        return this;
    }

    public OperationQuery setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public OperationQuery setVersionList(List<Integer> versionList) {
        this.versionList = versionList;
        return this;
    }

    public OperationQuery setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
        return this;
    }

    public OperationQuery setProcDefStatusList(List<String> procDefStatusList) {
        this.procDefStatusList = procDefStatusList;
        return this;
    }

    public OperationQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    public OperationQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public OperationQuery setDataScope(String dataScope) {
        this.dataScope = dataScope;
        return this;
    }

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffOperationService.selectOperation(operationId, operationIdList, operation, operationList, nodeId, nodeIdList, taskId, taskIdList, operator, operatorList, operatorName, operatorNameList, fromOperationDate, toOperationDate, operationStatus, operationStatusList, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromCreationDate, toCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName,
                procDefNameList, procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, page, limit, dataScope);
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
    public List<Operation> queryForObjectList() {
        List<Map<String, Object>> result = queryForMapList();
        List<Operation> operationList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            operationList.add(new Operation(result.get(i)));
        }

        return operationList;
    }

    /**
     * 查询单个对象。对象格式为实体Bean。
     * 
     * @return
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
     * @return
     */
    public int count() {
        return ffOperationService.countOperation(operationId, operationIdList, operation, operationList, nodeId, nodeIdList, taskId, taskIdList, operator, operatorList, operatorName, operatorNameList, fromOperationDate, toOperationDate, operationStatus, operationStatusList, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromCreationDate, toCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName,
                procDefNameList, procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, dataScope);
    }
}