package com.opendynamic.ff.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FlowDef implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String flowCode;// 流转编码
    protected String flowName;// 流转名称
    protected String sourceNodeCode;// 来源节点编码
    protected String targetNodeCode;// 目标节点编码
    protected String conditionExpression = "true";// 流转表达式

    protected Shape shape;// 形状

    @JsonIgnore
    protected transient ProcDef procDef;// 所属流程定义
    @JsonIgnore
    protected transient NodeDef sourceNodeDef;// 来源节点定义
    @JsonIgnore
    protected transient NodeDef targetNodeDef;// 目标节点定义

    /**
     * 初始化。
     * 
     * @param procDef
     */
    public void init(ProcDef procDef) {
        this.procDef = procDef;

        sourceNodeDef = procDef.getNodeDef(sourceNodeCode);
        targetNodeDef = procDef.getNodeDef(targetNodeCode);
        sourceNodeDef.addOutgoingFlowDef(this);
        targetNodeDef.addIncomingFlowDef(this);

        shape.init(this);
    }

    public String getFlowCode() {
        return flowCode;
    }

    public String getFlowName() {
        return flowName;
    }

    public String getSourceNodeCode() {
        return sourceNodeCode;
    }

    public String getTargetNodeCode() {
        return targetNodeCode;
    }

    public String getConditionExpression() {
        return conditionExpression;
    }

    public Shape getShape() {
        return shape;
    }

    public ProcDef getProcDef() {
        return procDef;
    }

    public NodeDef getSourceNodeDef() {
        return sourceNodeDef;
    }

    public NodeDef getTargetNodeDef() {
        return targetNodeDef;
    }
}