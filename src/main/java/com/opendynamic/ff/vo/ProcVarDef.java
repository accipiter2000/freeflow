package com.opendynamic.ff.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opendynamic.ff.service.FfService;

public class ProcVarDef implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String varType = FfService.VAR_TYPE_STRING;// 变量类型
    protected String varName;// 变量名称
    protected String value;// 值

    @JsonIgnore
    protected transient ProcDef procDef;// 所属流程定义

    /**
     * 初始化。
     * 
     * @param procDef
     */
    public void init(ProcDef procDef) {
        this.procDef = procDef;
    }

    public String getVarType() {
        return varType;
    }

    public String getVarName() {
        return varName;
    }

    public String getValue() {
        return value;
    }

    public ProcDef getProcDef() {
        return procDef;
    }
}