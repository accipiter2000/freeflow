package com.opendynamic.ff.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 流转定义。
 */
public class FlowDef implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String flowCode;// 流转编码。
    protected String flowName;// 流转名称。
    protected String sourceNodeCode;// 来源节点编码。
    protected String targetNodeCode;// 目标节点编码。
    protected String conditionExpression = "true";// 流转表达式。

    protected Shape shape;// 形状。

    @JsonIgnore
    protected transient ProcDef procDef;// 所属流程定义。
    @JsonIgnore
    protected transient NodeDef sourceNodeDef;// 来源节点定义。
    @JsonIgnore
    protected transient NodeDef targetNodeDef;// 目标节点定义。

    /**
     * 初始化。
     * 
     * @param procDef
     *        流程定义。
     */
    public void init(ProcDef procDef) {
        this.procDef = procDef;

        sourceNodeDef = procDef.getNodeDef(sourceNodeCode);
        targetNodeDef = procDef.getNodeDef(targetNodeCode);
        sourceNodeDef.addOutgoingFlowDef(this);
        targetNodeDef.addIncomingFlowDef(this);

        shape.init(this);
    }

    /**
     * 获取流转编码。
     * 
     * @return 流转编码。
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * 获取流转名称。
     * 
     * @return 流转名称。
     */
    public String getFlowName() {
        return flowName;
    }

    /**
     * 获取来源节点编码。
     * 
     * @return 来源节点编码。
     */
    public String getSourceNodeCode() {
        return sourceNodeCode;
    }

    /**
     * 获取目标节点编码。
     * 
     * @return 目标节点编码。
     */
    public String getTargetNodeCode() {
        return targetNodeCode;
    }

    /**
     * 获取流转表达式。
     * 
     * @return 流转表达式。
     */
    public String getConditionExpression() {
        return conditionExpression;
    }

    /**
     * 获取形状。
     * 
     * @return 形状。
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * 获取所属流程定义。
     * 
     * @return 所属流程定义。
     */
    public ProcDef getProcDef() {
        return procDef;
    }

    /**
     * 获取来源节点定义。
     * 
     * @return 来源节点定义。
     */
    public NodeDef getSourceNodeDef() {
        return sourceNodeDef;
    }

    /**
     * 获取目标节点定义。
     * 
     * @return 目标节点定义。
     */
    public NodeDef getTargetNodeDef() {
        return targetNodeDef;
    }
}