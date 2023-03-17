package com.opendynamic.ff.vo;

import java.io.Serializable;

/**
 * 注释定义
 */
public class RunningNoteDef extends NoteDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public RunningNoteDef(NoteDef noteDef, RunningProcDef runningProcDef) {
        super();

        this.noteCode = noteDef.getNoteCode();
        this.noteName = noteDef.getNoteName();
        this.dynamic = noteDef.getDynamic();
        this.shape = noteDef.getShape();

        this.procDef = runningProcDef;
    }

    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setProcDef(RunningProcDef procDef) {
        this.procDef = procDef;
    }
}