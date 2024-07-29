package com.opendynamic.ff.vo;

import java.io.Serializable;

/**
 * 运行期流转定义。
 */
public class RunningFlowDef extends FlowDef implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 依据流转定义构建运行期流转定义。
     * 
     * @param flowDef
     *        流转定义。
     * @param runningProcDef
     *        运行期流程定义。
     */
    public RunningFlowDef(FlowDef flowDef, RunningProcDef runningProcDef) {
        super();

        this.flowCode = flowDef.getFlowCode();
        this.flowName = flowDef.getFlowName();
        this.sourceNodeCode = flowDef.getSourceNodeCode();
        this.targetNodeCode = flowDef.getTargetNodeCode();
        this.conditionExpression = flowDef.getConditionExpression();

        this.shape = flowDef.getShape();

        this.procDef = runningProcDef;
    }

    /**
     * 设置流转编码。
     * 
     * @param flowCode
     *        流转编码。
     */
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    /**
     * 设置流转名称。
     * 
     * @param flowName
     *        流转名称。
     */
    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    /**
     * 设置来源节点编码。
     * 
     * @param sourceNodeCode
     *        来源节点编码。
     */
    public void setSourceNodeCode(String sourceNodeCode) {
        this.sourceNodeCode = sourceNodeCode;
    }

    /**
     * 设置目标节点编码。
     * 
     * @param targetNodeCode
     *        目标节点编码。
     */
    public void setTargetNodeCode(String targetNodeCode) {
        this.targetNodeCode = targetNodeCode;
    }

    /**
     * 设置流转表达式。
     * 
     * @param conditionExpression
     *        流转表达式。
     */
    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    /**
     * 设置形状。
     * 
     * @param shape
     *        形状。
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * 设置所属流程定义。
     * 
     * @param procDef
     *        所属流程定义。
     */
    public void setProcDef(ProcDef procDef) {
        this.procDef = procDef;
    }

    /**
     * 设置来源节点定义。
     * 
     * @param sourceNodeDef
     *        来源节点定义。
     */
    public void setSourceNodeDef(NodeDef sourceNodeDef) {
        this.sourceNodeDef = sourceNodeDef;
    }

    /**
     * 设置目标节点定义。
     * 
     * @param targetNodeDef
     *        目标节点定义。
     */
    public void setTargetNodeDef(NodeDef targetNodeDef) {
        this.targetNodeDef = targetNodeDef;
    }
}