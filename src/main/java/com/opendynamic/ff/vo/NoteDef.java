package com.opendynamic.ff.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opendynamic.ff.service.FfService;

/**
 * 注释定义。
 */
public class NoteDef implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String noteCode;// 注释编码。
    protected String noteName;// 注释名称。
    protected String dynamic = FfService.BOOLEAN_FALSE;// 是否为动态。如果为动态，则在获取运行期流程图时才绘制注释内容。

    protected Shape shape;// 形状。

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

        shape.init(this);
    }

    /**
     * 获取注释编码。
     * 
     * @return 注释编码。
     */
    public String getNoteCode() {
        return noteCode;
    }

    /**
     * 获取注释名称。
     * 
     * @return 注释名称。
     */
    public String getNoteName() {
        return noteName;
    }

    /**
     * 获取是否为动态。如果为动态，则在获取运行期流程图时才绘制注释内容。
     * 
     * @return 是否为动态。如果为动态，则在获取运行期流程图时才绘制注释内容。
     */
    public String getDynamic() {
        return dynamic;
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
}