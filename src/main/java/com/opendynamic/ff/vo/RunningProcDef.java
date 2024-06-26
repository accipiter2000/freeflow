package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RunningProcDef extends ProcDef implements Serializable {
    private static final long serialVersionUID = 1L;

    private String procStatus;// 流程状态
    private Boolean current;// 当前节点是否在该（子）流程定义中
    private Map<String, Object> nodeVarMap;// 节点变量
    private String diagram;

    private List<RunningNodeDef> nextRunningNodeDefList;// 起始节点定义列表

    public RunningProcDef(Map<String, Object> data) {
        super(data);
        throw new RuntimeException("errors.notSupport");
    }

    /**
     * 初始化。
     * 
     * @param procDef
     */
    public RunningProcDef(ProcDef procDef) {
        super(null);

        this.procDefId = procDef.getProcDefId();
        this.procDefCode = procDef.getProcDefCode();
        this.procDefName = procDef.getProcDefName();
        this.procDefCat = procDef.getProcDefCat();
        this.procDefModel = procDef.getProcDefModel();
        this.procDefDiagramFile = procDef.getProcDefDiagramFile();
        this.procDefDiagramFileName = procDef.getProcDefDiagramFileName();
        this.procDefDiagramFileLength = procDef.getProcDefDiagramFileLength();
        this.procDefDiagramWidth = procDef.getProcDefDiagramWidth();
        this.procDefDiagramHeight = procDef.getProcDefDiagramHeight();
        this.memo = procDef.getMemo();
        this.extAttr1 = procDef.getExtAttr1();
        this.extAttr2 = procDef.getExtAttr2();
        this.extAttr3 = procDef.getExtAttr3();
        this.extAttr4 = procDef.getExtAttr4();
        this.extAttr5 = procDef.getExtAttr5();
        this.extAttr6 = procDef.getExtAttr6();
        this.extAttr7 = procDef.getExtAttr7();
        this.extAttr8 = procDef.getExtAttr8();
        this.version = procDef.getVersion();
        this.procDefStatus = procDef.getProcDefStatus();
        this.creationDate = procDef.getCreationDate();
        this.updateDate = procDef.getUpdateDate();
        this.operatorId = procDef.getOperatorId();
        this.operatorName = procDef.getOperatorName();

        this.nodeDefList = new ArrayList<>();
        this.flowDefList = new ArrayList<>();
        this.noteDefList = new ArrayList<>();
        this.procVarDefList = new ArrayList<>();

        // 节点定义，流转定义，注释定义，依次转化为Running
        for (NodeDef nodeDef : procDef.getNodeDefList()) {
            nodeDefList.add(new RunningNodeDef(nodeDef, this));
        }
        for (FlowDef flowDef : procDef.getFlowDefList()) {
            flowDefList.add(new RunningFlowDef(flowDef, this));
        }
        for (NoteDef noteDef : procDef.getNoteDefList()) {
            noteDefList.add(new RunningNoteDef(noteDef, this));
        }
        for (ProcVarDef procVarDef : procDef.getProcVarDefList()) {
            procVarDefList.add(new RunningProcVarDef(procVarDef, this));
        }

        super.init();
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public void setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
    }

    public void setProcDefName(String procDefName) {
        this.procDefName = procDefName;
    }

    public void setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
    }

    public void setProcDefModel(String procDefModel) {
        this.procDefModel = procDefModel;
    }

    public void setProcDefDiagramFile(byte[] procDefDiagramFile) {
        this.procDefDiagramFile = procDefDiagramFile;
    }

    public void setProcDefDiagramFileName(String procDefDiagramFileName) {
        this.procDefDiagramFileName = procDefDiagramFileName;
    }

    public void setProcDefDiagramFileLength(int procDefDiagramFileLength) {
        this.procDefDiagramFileLength = procDefDiagramFileLength;
    }

    public void setProcDefDiagramWidth(int procDefDiagramWidth) {
        this.procDefDiagramWidth = procDefDiagramWidth;
    }

    public void setProcDefDiagramHeight(int procDefDiagramHeight) {
        this.procDefDiagramHeight = procDefDiagramHeight;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(String procStatus) {
        this.procStatus = procStatus;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public Map<String, Object> getNodeVarMap() {
        return nodeVarMap;
    }

    public void setNodeVarMap(Map<String, Object> nodeVarMap) {
        this.nodeVarMap = nodeVarMap;
    }

    public String getDiagram() {
        return diagram;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }

    public List<RunningNodeDef> getNextRunningNodeDefList() {
        return nextRunningNodeDefList;
    }

    public void setNextRunningNodeDefList(List<RunningNodeDef> nextRunningNodeDefList) {
        this.nextRunningNodeDefList = nextRunningNodeDefList;
    }

    @Override
    public List<? extends NodeDef> getNodeDefList() {
        return nodeDefList;
    }

    @Override
    public List<? extends NodeDef> getStartNodeDefList() {
        return startNodeDefList;
    }

    @Override
    public List<? extends FlowDef> getFlowDefList() {
        return flowDefList;
    }

    @Override
    public List<? extends NoteDef> getNoteDefList() {
        return noteDefList;
    }

    @Override
    public List<? extends ProcVarDef> getProcVarDefList() {
        return procVarDefList;
    }
}