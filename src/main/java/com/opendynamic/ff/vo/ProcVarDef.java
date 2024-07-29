package com.opendynamic.ff.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opendynamic.ff.service.FfService;

/**
 * 流程变量定义。
 */
public class ProcVarDef implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String varType = FfService.VAR_TYPE_STRING;// 变量类型。
    protected String varName;// 变量名称。
    protected String value;// 值。

    @JsonIgnore
    protected transient ProcDef procDef;// 所属流程定义。

    /**
     * 初始化。
     * 
     * @param procDef
     *        流程定义。
     */
    public void init(ProcDef procDef) {
        this.procDef = procDef;
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
     * 获取变量名称。
     * 
     * @return 变量名称。
     */
    public String getVarName() {
        return varName;
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
     * 获取所属流程定义。
     * 
     * @return 所属流程定义。
     */
    public ProcDef getProcDef() {
        return procDef;
    }
}