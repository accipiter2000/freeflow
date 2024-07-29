package com.opendynamic.ff.vo;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 节点变量。
 */
public class NodeVar implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nodeVarId; // 节点变量ID。
    private String nodeId; // 节点ID。
    private String varType; // 变量类型。
    private String varName; // 变量名称。
    private String value; // 值。
    private Serializable obj; // 对象。
    private Date creationDate; // 创建日期。
    private String parentNodeId; // 上级节点ID。
    private String procId; // 流程ID。

    public NodeVar() {
    }

    /**
     * 依据数据库数据构造。
     * 
     * @param data
     *        数据库数据。
     */
    public NodeVar(Map<String, Object> data) {
        this.nodeVarId = (String) data.get("NODE_VAR_ID_");
        this.nodeId = (String) data.get("NODE_ID_");
        this.varType = (String) data.get("VAR_TYPE_");
        this.varName = (String) data.get("VAR_NAME_");
        this.value = (String) data.get("VALUE_");
        this.creationDate = (Date) data.get("CREATION_DATE_");
        this.parentNodeId = (String) data.get("PARENT_NODE_ID_");
        this.procId = (String) data.get("PROC_ID_");

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

    /**
     * 获取上级节点ID。
     * 
     * @return 上级节点ID。
     */
    public String getParentNodeId() {
        return parentNodeId;
    }

    /**
     * 设置上级节点ID。
     * 
     * @param parentNodeId
     *        上级节点ID。
     */
    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    /**
     * 获取流程ID。
     * 
     * @return 流程ID。
     */
    public String getProcId() {
        return procId;
    }

    /**
     * 设置流程ID。
     * 
     * @param procId
     *        流程ID。
     */
    public void setProcId(String procId) {
        this.procId = procId;
    }
}