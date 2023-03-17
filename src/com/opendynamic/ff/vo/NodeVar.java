package com.opendynamic.ff.vo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class NodeVar implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nodeVarId; // 节点变量ID
    private String nodeId; // 节点ID
    private String varType; // 变量类型
    private String varName; // 变量名称
    private String value; // 值
    private Serializable obj; // 对象
    private Date creationDate; // 创建日期
    private String parentNodeId; // 上级节点ID
    private String procId; // 流程ID

    public NodeVar() {
    }

    /**
     * 通过数据库记录构造。
     * 
     * @param data
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

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }
}