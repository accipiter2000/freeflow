package com.opendynamic.ff.vo;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 节点变量操作。记录节点变量操作的历史数据。
 */
public class NodeVarOp implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nodeVarOpId; // 节点变量操作ID。
    private String operationId; // 操作ID。
    private String operationType; // 操作类型。
    private Integer operationOrder; // 操作顺序。
    private Date operationDate; // 操作日期。
    private String operationStatus; // 操作状态。
    private String nodeVarId; // 节点变量ID。
    private String nodeId; // 节点ID。
    private String varType; // 变量类型。
    private String varName; // 变量名称。
    private String value; // 值。
    private Serializable obj; // 对象。
    private Date creationDate; // 创建日期。

    public NodeVarOp() {
    }

    /**
     * 依据数据库数据构造。
     * 
     * @param data
     *        数据库数据。
     */
    public NodeVarOp(Map<String, Object> data) {
        this.nodeVarOpId = (String) data.get("NODE_VAR_OP_ID_");
        this.operationId = (String) data.get("OPERATION_ID_");
        this.operationType = (String) data.get("OPERATION_TYPE_");
        this.operationOrder = (data.get("OPERATION_ORDER_") != null) ? (((BigDecimal) data.get("OPERATION_ORDER_")).intValue()) : null;
        this.operationDate = (Date) data.get("OPERATION_DATE_");
        this.operationStatus = (String) data.get("OPERATION_STATUS_");
        this.nodeVarId = (String) data.get("NODE_VAR_ID_");
        this.nodeId = (String) data.get("NODE_ID_");
        this.varType = (String) data.get("VAR_TYPE_");
        this.varName = (String) data.get("VAR_NAME_");
        this.value = (String) data.get("VALUE_");
        this.creationDate = (Date) data.get("CREATION_DATE_");

        byte[] OBJ_BYTE = (byte[]) data.get("OBJ_");
        if (OBJ_BYTE != null) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(OBJ_BYTE))) {
                this.obj = (Serializable) objectInputStream.readObject();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取节点变量操作ID。
     * 
     * @return 节点变量操作ID。
     */
    public String getNodeVarOpId() {
        return nodeVarOpId;
    }

    /**
     * 设置节点变量操作ID。
     * 
     * @param nodeVarOpId
     *        节点变量操作ID。
     */
    public void setNodeVarOpId(String nodeVarOpId) {
        this.nodeVarOpId = nodeVarOpId;
    }

    /**
     * 获取操作ID。
     * 
     * @return 操作ID。
     */
    public String getOperationId() {
        return operationId;
    }

    /**
     * 设置操作ID。
     * 
     * @param operationId
     *        操作ID。
     */
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    /**
     * 获取操作类型。
     * 
     * @return 操作类型。
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * 设置操作类型。
     * 
     * @param operationType
     *        操作类型。
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     * 获取操作顺序。
     * 
     * @return 操作顺序。
     */
    public Integer getOperationOrder() {
        return operationOrder;
    }

    /**
     * 设置操作顺序。
     * 
     * @param operationOrder
     *        操作顺序。
     */
    public void setOperationOrder(Integer operationOrder) {
        this.operationOrder = operationOrder;
    }

    /**
     * 获取操作日期。
     * 
     * @return 操作日期。
     */
    public Date getOperationDate() {
        return operationDate;
    }

    /**
     * 设置操作日期。
     * 
     * @param operationDate
     *        操作日期。
     */
    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    /**
     * 获取操作状态。
     * 
     * @return 操作状态。
     */
    public String getOperationStatus() {
        return operationStatus;
    }

    /**
     * 设置操作状态。
     * 
     * @param operationStatus
     *        操作状态。
     */
    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    /**
     * 获取节点变量ID。
     * 
     * @return 节点变量ID。
     */
    public String getNodeVarId() {
        return nodeVarId;
    }

    /**
     * 设置节点变量ID。
     * 
     * @param nodeVarId
     *        节点变量ID。
     */
    public void setNodeVarId(String nodeVarId) {
        this.nodeVarId = nodeVarId;
    }

    /**
     * 获取节点ID。
     * 
     * @return 节点ID。
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 设置节点ID。
     * 
     * @param nodeId
     *        节点ID。
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 获取变量类型。
     * 
     * @return 变量类型。
     */
    public String getVarType() {
        return varType;
    }

    /**
     * 设置变量类型。
     * 
     * @param varType
     *        变量类型。
     */
    public void setVarType(String varType) {
        this.varType = varType;
    }

    /**
     * 获取变量名称。
     * 
     * @return 变量名称。
     */
    public String getVarName() {
        return varName;
    }

    /**
     * 设置变量名称。
     * 
     * @param varName
     *        变量名称。
     */
    public void setVarName(String varName) {
        this.varName = varName;
    }

    /**
     * 获取值。
     * 
     * @return 值。
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置值。
     * 
     * @param value
     *        值。
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取对象。
     * 
     * @return 对象。
     */
    public Serializable getObj() {
        return obj;
    }

    /**
     * 设置对象。
     * 
     * @param obj
     *        对象。
     */
    public void setObj(Serializable obj) {
        this.obj = obj;
    }

    /**
     * 获取创建日期。
     * 
     * @return 创建日期。
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * 设置创建日期。
     * 
     * @param creationDate
     *        创建日期。
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}