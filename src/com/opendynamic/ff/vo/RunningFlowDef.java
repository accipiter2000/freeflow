package com.opendynamic.ff.vo;

import java.io.Serializable;

/**
 * 流转线定义
 */
public class RunningFlowDef extends FlowDef implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public void setSourceNodeCode(String sourceNodeCode) {
        this.sourceNodeCode = sourceNodeCode;
    }

    public void setTargetNodeCode(String targetNodeCode) {
        this.targetNodeCode = targetNodeCode;
    }

    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setProcDef(ProcDef procDef) {
        this.procDef = procDef;
    }

    public void setSourceNodeDef(NodeDef sourceNodeDef) {
        this.sourceNodeDef = sourceNodeDef;
    }

    public void setTargetNodeDef(NodeDef targetNodeDef) {
        this.targetNodeDef = targetNodeDef;
    }
}