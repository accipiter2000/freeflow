package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfOperationService;
import com.opendynamic.ff.vo.Operation;

@Service
public class OperationQuery {
    private FfOperationService ffOperationService;

    private String operationId;
    private String operation;
    private String procId;
    private String nodeId;
    private String taskId;
    private List<String> operatorList;
    private String operatorName;
    private Date fromOperationDate;
    private Date toOperationDate;
    private List<String> operationStatusList;
    private String bizId;
    private List<String> bizTypeList;
    private String bizCode;
    private String bizName;
    private Integer page;
    private Integer limit;

    public OperationQuery(FfOperationService ffOperationService) {
        super();
        this.ffOperationService = ffOperationService;
    }

    public OperationQuery setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public OperationQuery setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public OperationQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    public OperationQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public OperationQuery setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public OperationQuery setOperator(String operator) {
        if (StringUtils.isNotEmpty(operator)) {
            this.operatorList = new ArrayList<>();
            this.operatorList.add(operator);
        }
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

    public OperationQuery setFromOperationDate(Date fromOperationDate) {
        this.fromOperationDate = fromOperationDate;
        return this;
    }

    public OperationQuery setToOperationDate(Date toOperationDate) {
        this.toOperationDate = toOperationDate;
        return this;
    }

    public OperationQuery setOperationStatus(String operationStatus) {
        if (StringUtils.isNotEmpty(operationStatus)) {
            this.operationStatusList = new ArrayList<>();
            this.operationStatusList.add(operationStatus);
        }
        return this;
    }

    public OperationQuery setOperationStatusList(List<String> operationStatusList) {
        this.operationStatusList = operationStatusList;
        return this;
    }

    public OperationQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    public OperationQuery setBizType(String bizType) {
        if (StringUtils.isNotEmpty(bizType)) {
            this.bizTypeList = new ArrayList<>();
            this.bizTypeList.add(bizType);
        }
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

    public OperationQuery setBizName(String bizName) {
        this.bizName = bizName;
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

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffOperationService.selectOperation(operationId, operation, procId, nodeId, taskId, operatorList, operatorName, fromOperationDate, toOperationDate, operationStatusList, bizId, bizTypeList, bizCode, bizName, page, limit);
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
        return ffOperationService.countOperation(operationId, operation, procId, nodeId, taskId, operatorList, operatorName, fromOperationDate, toOperationDate, operationStatusList, bizId, bizTypeList, bizCode, bizName);
    }
}