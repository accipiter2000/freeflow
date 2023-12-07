package com.opendynamic.ff.vo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class NodeVarOp implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nodeVarOpId; // 节点变量操作ID
    private String operationId; // 操作ID
    private String operationType; // 操作类型
    private Integer operationOrder; // 操作顺序
    private Date operationDate; // 操作日期
    private String operationStatus; // 操作状态
    private String nodeVarId; // 节点变量ID
    private String nodeId; // 节点ID
    private String varType; // 变量类型
    private String varName; // 变量名称
    private String value; // 值
    private Serializable obj; // 对象
    private Date creationDate; // 创建日期

    public NodeVarOp() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
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
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(OBJ_BYTE));
                this.obj = (Serializable) objectInputStream.readObject();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                try {
                    objectInputStream.close();
                }
                catch (IOException e) {
                }
            }
        }
    }

    public String getNodeVarOpId() {
        return nodeVarOpId;
    }

    public void setNodeVarOpId(String nodeVarOpId) {
        this.nodeVarOpId = nodeVarOpId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Integer getOperationOrder() {
        return operationOrder;
    }

    public void setOperationOrder(Integer operationOrder) {
        this.operationOrder = operationOrder;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getNodeVarId() {
        return nodeVarId;
    }

    public void setNodeVarId(String nodeVarId) {
        this.nodeVarId = nodeVarId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Serializable getObj() {
        return obj;
    }

    public void setObj(Serializable obj) {
        this.obj = obj;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}