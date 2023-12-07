package com.opendynamic.ff.vo;

import java.io.Serializable;

/**
 * 注释定义
 */
public class RunningProcVarDef extends ProcVarDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public RunningProcVarDef(ProcVarDef procVarDef, RunningProcDef runningProcDef) {
        super();

        this.varType = procVarDef.getVarType();
        this.varName = procVarDef.getVarName();
        this.value = procVarDef.getValue();

        this.procDef = runningProcDef;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setProcDef(RunningProcDef procDef) {
        this.procDef = procDef;
    }
}