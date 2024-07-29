package com.opendynamic.ff.vo;

import java.io.Serializable;

/**
 * 运行期注释定义。
 */
public class RunningNoteDef extends NoteDef implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 依据注释定义构建运行期注释定义。
     * 
     * @param noteDef
     *        注释定义。
     * @param runningProcDef
     *        运行期流程定义。
     */
    public RunningNoteDef(NoteDef noteDef, RunningProcDef runningProcDef) {
        super();

        this.noteCode = noteDef.getNoteCode();
        this.noteName = noteDef.getNoteName();
        this.dynamic = noteDef.getDynamic();

        this.shape = noteDef.getShape();

        this.procDef = runningProcDef;
    }

    /**
     * 设置注释编码。
     * 
     * @param noteCode
     *        注释编码。
     */
    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode;
    }

    /**
     * 设置注释名称。
     * 
     * @param noteName
     *        注释名称。
     */
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    /**
     * 设置是否为动态。如果为动态，则在获取运行期流程图时才绘制注释内容。
     * 
     * @param dynamic
     *        是否为动态。如果为动态，则在获取运行期流程图时才绘制注释内容。
     */
    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
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
    public void setProcDef(RunningProcDef procDef) {
        this.procDef = procDef;
    }
}