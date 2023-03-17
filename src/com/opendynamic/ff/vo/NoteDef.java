package com.opendynamic.ff.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opendynamic.ff.service.FfService;

public class NoteDef implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String noteCode;// 注释编码
    protected String noteName;// 注释名称
    protected String dynamic = FfService.BOOLEAN_FALSE;// 是否为动态。如为动态，则在获取动态流程图时才绘制注释内容。

    protected Shape shape;// 形状

    @JsonIgnore
    protected transient ProcDef procDef;// 所属流程定义

    /**
     * 初始化。
     * 
     * @param procDef
     */
    public void init(ProcDef procDef) {
        this.procDef = procDef;

        shape.init(this);
    }

    public String getNoteCode() {
        return noteCode;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getDynamic() {
        return dynamic;
    }

    public Shape getShape() {
        return shape;
    }

    public ProcDef getProcDef() {
        return procDef;
    }
}