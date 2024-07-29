package com.opendynamic.ff.vo;

import java.io.Serializable;

/**
 * 运行期流程变量定义。
 */
public class RunningProcVarDef extends ProcVarDef implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 依据流程变量定义构造运行期流程变量定义。
     * 
     * @param procVarDef
     *        流程变量定义。
     * @param runningProcDef
     *        运行期流程定义。
     */
    public RunningProcVarDef(ProcVarDef procVarDef, RunningProcDef runningProcDef) {
        super();

        this.varType = procVarDef.getVarType();
        this.varName = procVarDef.getVarName();
        this.value = procVarDef.getValue();

        this.procDef = runningProcDef;
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
     * 设置变量名称。
     * 
     * @param varName
     *        变量名称。
     */
    public void setVarName(String varName) {
        this.varName = varName;
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
     * 设置所属流程定义。
     * 
     * @param procDef
     *        所属流程定义。
     */
    public void setProcDef(RunningProcDef procDef) {
        this.procDef = procDef;
    }
}