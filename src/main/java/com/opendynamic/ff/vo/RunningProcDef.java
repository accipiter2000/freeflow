package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 运行期流程定义。
 */
public class RunningProcDef extends ProcDef implements Serializable {
    private static final long serialVersionUID = 1L;

    private String procStatus;// 流程状态。
    private Boolean current;// 当前节点是否在该（子）流程定义中。
    private Map<String, Object> nodeVarMap;// 节点变量。
    private String runningDiagram;// 运行期流程图。base64编码。

    private List<RunningNodeDef> nextRunningNodeDefList;// 下一个运行期节点定义列表。

    public RunningProcDef(Map<String, Object> data) {
        super(data);
        throw new RuntimeException("errors.notSupport");
    }

    /**
     * 依据流程定义构建运行期流程定义。
     * 
     * @param procDef
     *        流程定义。
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

    /**
     * 设置流程定义ID。
     * 
     * @param procDefId
     *        流程定义ID。
     */
    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    /**
     * 设置流程定义编码。
     * 
     * @param procDefCode
     *        流程定义编码。
     */
    public void setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
    }

    /**
     * 设置流程定义名称。
     * 
     * @param procDefName
     *        流程定义名称。
     */
    public void setProcDefName(String procDefName) {
        this.procDefName = procDefName;
    }

    /**
     * 设置流程定义分类。
     * 
     * @param procDefCat
     *        流程定义分类。
     */
    public void setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
    }

    /**
     * 设置流程定义模型。
     * 
     * @param procDefModel
     *        流程定义模型。
     */
    public void setProcDefModel(String procDefModel) {
        this.procDefModel = procDefModel;
    }

    /**
     * 设置流程定义图文件。
     * 
     * @param procDefDiagramFile
     *        流程定义图文件。
     */
    public void setProcDefDiagramFile(byte[] procDefDiagramFile) {
        this.procDefDiagramFile = procDefDiagramFile;
    }

    /**
     * 设置流程定义图文件名称。
     * 
     * @param procDefDiagramFileName
     *        流程定义图文件名称。
     */
    public void setProcDefDiagramFileName(String procDefDiagramFileName) {
        this.procDefDiagramFileName = procDefDiagramFileName;
    }

    /**
     * 设置流程定义图文件长度。
     * 
     * @param procDefDiagramFileLength
     *        流程定义图文件长度。
     */
    public void setProcDefDiagramFileLength(int procDefDiagramFileLength) {
        this.procDefDiagramFileLength = procDefDiagramFileLength;
    }

    /**
     * 设置流程定义图宽度。
     * 
     * @param procDefDiagramWidth
     *        流程定义图宽度。
     */
    public void setProcDefDiagramWidth(int procDefDiagramWidth) {
        this.procDefDiagramWidth = procDefDiagramWidth;
    }

    /**
     * 设置流程定义图高度。
     * 
     * @param procDefDiagramHeight
     *        流程定义图高度。
     */
    public void setProcDefDiagramHeight(int procDefDiagramHeight) {
        this.procDefDiagramHeight = procDefDiagramHeight;
    }

    /**
     * 设置备注。
     * 
     * @param memo
     *        备注。
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 设置扩展属性1。
     * 
     * @param extAttr1
     *        扩展属性1。
     */
    public void setExtAttr1(String extAttr1) {
        this.extAttr1 = extAttr1;
    }

    /**
     * 设置扩展属性2。
     * 
     * @param extAttr2
     *        扩展属性2。
     */
    public void setExtAttr2(String extAttr2) {
        this.extAttr2 = extAttr2;
    }

    /**
     * 设置扩展属性3。
     * 
     * @param extAttr3
     *        扩展属性3。
     */
    public void setExtAttr3(String extAttr3) {
        this.extAttr3 = extAttr3;
    }

    /**
     * 设置扩展属性4。
     * 
     * @param extAttr4
     *        扩展属性4。
     */
    public void setExtAttr4(String extAttr4) {
        this.extAttr4 = extAttr4;
    }

    /**
     * 设置扩展属性5。
     * 
     * @param extAttr5
     *        扩展属性5。
     */
    public void setExtAttr5(String extAttr5) {
        this.extAttr5 = extAttr5;
    }

    /**
     * 设置扩展属性6。
     * 
     * @param extAttr6
     *        扩展属性6。
     */
    public void setExtAttr6(String extAttr6) {
        this.extAttr6 = extAttr6;
    }

    /**
     * 设置扩展属性7。
     * 
     * @param extAttr7
     *        扩展属性7。
     */
    public void setExtAttr7(String extAttr7) {
        this.extAttr7 = extAttr7;
    }

    /**
     * 设置扩展属性8。
     * 
     * @param extAttr8
     *        扩展属性8。
     */
    public void setExtAttr8(String extAttr8) {
        this.extAttr8 = extAttr8;
    }

    /**
     * 设置版本。
     * 
     * @param version
     *        版本。
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 设置流程定义状态。
     * 
     * @param procDefStatus
     *        流程定义状态。
     */
    public void setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
    }

    /**
     * 设置创建日期。
     * 
     * @param creationDate
     *        创建日期。
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * 设置更新日期。
     * 
     * @param updateDate
     *        更新日期。
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 设置操作人员ID。
     * 
     * @param operatorId
     *        操作人员ID。
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 设置操作人员名称。
     * 
     * @param operatorName
     *        操作人员名称。
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * 获取流程状态。
     * 
     * @return 流程状态。
     */
    public String getProcStatus() {
        return procStatus;
    }

    /**
     * 设置流程状态。
     * 
     * @param procStatus
     *        流程状态。
     */
    public void setProcStatus(String procStatus) {
        this.procStatus = procStatus;
    }

    /**
     * 获取当前节点是否在该（子）流程定义中。
     * 
     * @return 当前节点是否在该（子）流程定义中。
     */
    public Boolean getCurrent() {
        return current;
    }

    /**
     * 设置当前节点是否在该（子）流程定义中。
     * 
     * @param current
     *        当前节点是否在该（子）流程定义中。
     */
    public void setCurrent(Boolean current) {
        this.current = current;
    }

    /**
     * 获取节点变量。
     * 
     * @return 节点变量。
     */
    public Map<String, Object> getNodeVarMap() {
        return nodeVarMap;
    }

    /**
     * 设置节点变量。
     * 
     * @param nodeVarMap
     *        节点变量。
     */
    public void setNodeVarMap(Map<String, Object> nodeVarMap) {
        this.nodeVarMap = nodeVarMap;
    }

    /**
     * 获取运行期流程图。base64编码。
     * 
     * @return 运行期流程图。base64编码。
     */
    public String getRunningDiagram() {
        return runningDiagram;
    }

    /**
     * 设置运行期流程图。base64编码。
     * 
     * @param runningDiagram
     *        运行期流程图。base64编码。
     */
    public void setRunningDiagram(String runningDiagram) {
        this.runningDiagram = runningDiagram;
    }

    /**
     * 获取下一个运行期节点定义列表。
     * 
     * @return 下一个运行期节点定义列表。
     */
    public List<RunningNodeDef> getNextRunningNodeDefList() {
        return nextRunningNodeDefList;
    }

    /**
     * 设置下一个运行期节点定义列表。
     * 
     * @param nextRunningNodeDefList
     *        下一个运行期节点定义列表。
     */
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