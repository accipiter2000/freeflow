package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

/**
 * 流程定义。
 */
public class ProcDef implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String procDefId; // 流程定义ID。
    protected String procDefCode; // 流程定义编码。
    protected String procDefName; // 流程定义名称。
    protected String procDefCat; // 流程定义分类。
    @JsonIgnore
    protected transient String procDefModel; // 流程定义模型。
    @JsonIgnore
    protected transient byte[] procDefDiagramFile; // 流程定义图文件。
    protected String procDefDiagramFileName; // 流程定义图文件名称。
    protected int procDefDiagramFileLength; // 流程定义图文件长度。
    protected int procDefDiagramWidth; // 流程定义图宽度。
    protected int procDefDiagramHeight; // 流程定义图高度。
    protected String memo; // 备注。
    protected String extAttr1; // 扩展属性1。
    protected String extAttr2; // 扩展属性2。
    protected String extAttr3; // 扩展属性3。
    protected String extAttr4; // 扩展属性4。
    protected String extAttr5; // 扩展属性5。
    protected String extAttr6; // 扩展属性6。
    protected String extAttr7; // 扩展属性7。
    protected String extAttr8; // 扩展属性8。
    protected int version; // 版本。
    protected String procDefStatus; // 流程定义状态。
    protected Date creationDate; // 创建日期。
    protected Date updateDate; // 更新日期。
    protected String operatorId; // 操作人员ID。
    protected String operatorName; // 操作人员名称。

    @JsonIgnore
    protected transient Map<String, NodeDef> nodeDefMap;// 节点定义map。键为节点编码。

    protected List<NodeDef> nodeDefList;// 节点定义列表。
    protected List<FlowDef> flowDefList;// 流转定义列表。
    protected List<NoteDef> noteDefList;// 注释定义列表。
    protected List<ProcVarDef> procVarDefList;// 流程变量定义列表。

    @JsonIgnore
    protected transient List<NodeDef> startNodeDefList;// 起始节点定义列表。

    /**
     * 依据数据库数据构造。
     * 
     * @param data
     *        数据库数据。
     */
    public ProcDef(Map<String, Object> data) {
        if (data == null) {
            return;
        }

        // 根据JSON生成流程定义
        RuntimeTypeAdapterFactory<Shape> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(Shape.class, "type");
        runtimeTypeAdapterFactory.registerSubtype(RectangleShape.class, "rectangle");
        runtimeTypeAdapterFactory.registerSubtype(OvalShape.class, "oval");
        runtimeTypeAdapterFactory.registerSubtype(DiamondShape.class, "diamond");
        runtimeTypeAdapterFactory.registerSubtype(LineShape.class, "line");
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
        ProcDef procDef = gson.fromJson((String) data.get("PROC_DEF_MODEL_"), ProcDef.class);

        this.procDefId = (String) data.get("PROC_DEF_ID_");
        this.procDefCode = procDef.getProcDefCode();
        this.procDefName = procDef.getProcDefName();
        this.procDefCat = procDef.getProcDefCat();
        this.procDefModel = (String) data.get("PROC_DEF_MODEL_");
        this.procDefDiagramFile = (byte[]) data.get("PROC_DEF_DIAGRAM_FILE_");
        this.procDefDiagramFileName = (String) data.get("PROC_DEF_DIAGRAM_FILE_NAME_");
        this.procDefDiagramFileLength = ((BigDecimal) data.get("PROC_DEF_DIAGRAM_FILE_LENGTH_")).intValue();
        this.procDefDiagramWidth = procDef.getProcDefDiagramWidth();
        this.procDefDiagramHeight = procDef.getProcDefDiagramHeight();
        this.memo = procDef.getMemo();
        this.extAttr1 = (String) data.get("EXT_ATTR_1_");
        this.extAttr2 = (String) data.get("EXT_ATTR_2_");
        this.extAttr3 = (String) data.get("EXT_ATTR_3_");
        this.extAttr4 = (String) data.get("EXT_ATTR_4_");
        this.extAttr5 = (String) data.get("EXT_ATTR_5_");
        this.extAttr6 = (String) data.get("EXT_ATTR_6_");
        this.extAttr7 = (String) data.get("EXT_ATTR_7_");
        this.extAttr8 = (String) data.get("EXT_ATTR_8_");
        this.version = (data.get("VERSION_") != null) ? ((BigDecimal) data.get("VERSION_")).intValue() : 0;
        this.procDefStatus = (data.get("PROC_DEF_STATUS_") != null) ? (String) data.get("PROC_DEF_STATUS_") : "1";
        this.creationDate = (Date) data.get("CREATION_DATE_");
        this.updateDate = (Date) data.get("UPDATE_DATE_");
        this.operatorId = (String) data.get("OPERATOR_ID_");
        this.operatorName = (String) data.get("OPERATOR_NAME_");

        this.nodeDefList = procDef.nodeDefList;
        this.flowDefList = procDef.flowDefList;
        this.noteDefList = procDef.noteDefList;
        this.procVarDefList = procDef.procVarDefList;

        init();
    }

    /**
     * 初始化。
     */
    public void init() {
        if (nodeDefList == null) {
            nodeDefList = new ArrayList<>();
        }
        if (flowDefList == null) {
            flowDefList = new ArrayList<>();
        }
        if (noteDefList == null) {
            noteDefList = new ArrayList<>();
        }
        if (procVarDefList == null) {
            procVarDefList = new ArrayList<>();
        }

        // 初始化节点定义map
        nodeDefMap = new HashMap<>();
        for (NodeDef nodeDef : nodeDefList) {
            if (nodeDefMap.containsKey(nodeDef.getNodeCode())) {
                throw new RuntimeException("errors.duplicateNodeCode: " + getProcDefName() + " - " + nodeDef.getNodeCode());
            }
            nodeDefMap.put(nodeDef.getNodeCode(), nodeDef);
        }

        // 节点定义，流转定义，注释定义，依次各自初始化自己
        for (NodeDef nodeDef : nodeDefList) {
            nodeDef.init(this);
        }
        for (FlowDef flowDef : flowDefList) {
            flowDef.init(this);
        }
        for (NoteDef noteDef : noteDefList) {
            noteDef.init(this);
        }
        for (ProcVarDef procVarDef : procVarDefList) {
            procVarDef.init(this);
        }

        // 初始化节点关联关系
        for (NodeDef nodeDef : nodeDefList) {
            nodeDef.initNodeDefRelation();
        }

        // 初始化起始节点定义列表
        startNodeDefList = new ArrayList<>();
        for (NodeDef nodeDef : nodeDefList) {
            if (nodeDef.getParentNodeCode() == null && nodeDef.getIncomingFlowDefList().isEmpty()) {
                startNodeDefList.add(nodeDef);
            }
        }

        if (procDefDiagramWidth == 0) {
            int maxX = 0;
            for (NodeDef nodeDef : nodeDefList) {
                if (nodeDef.getShape().getMaxX() > maxX) {
                    maxX = nodeDef.getShape().getMaxX();
                }
            }
            for (FlowDef flowDef : flowDefList) {
                if (flowDef.getShape().getMaxX() > maxX) {
                    maxX = flowDef.getShape().getMaxX();
                }
            }
            for (NoteDef noteDef : noteDefList) {
                if (noteDef.getShape().getMaxX() > maxX) {
                    maxX = noteDef.getShape().getMaxX();
                }
            }
            procDefDiagramWidth = maxX + 10;
        }
        if (procDefDiagramHeight == 0) {
            int maxY = 0;
            for (NodeDef nodeDef : nodeDefList) {
                if (nodeDef.getShape().getMaxY() > maxY) {
                    maxY = nodeDef.getShape().getMaxY();
                }
            }
            for (FlowDef flowDef : flowDefList) {
                if (flowDef.getShape().getMaxY() > maxY) {
                    maxY = flowDef.getShape().getMaxY();
                }
            }
            for (NoteDef noteDef : noteDefList) {
                if (noteDef.getShape().getMaxY() > maxY) {
                    maxY = noteDef.getShape().getMaxY();
                }
            }
            procDefDiagramHeight = maxY + 10;
        }
    }

    /**
     * 获取流程定义ID。
     * 
     * @return 流程定义ID。
     */
    public String getProcDefId() {
        return procDefId;
    }

    /**
     * 获取流程定义编码。
     * 
     * @return 流程定义编码。
     */
    public String getProcDefCode() {
        return procDefCode;
    }

    /**
     * 获取流程定义名称。
     * 
     * @return 流程定义名称。
     */
    public String getProcDefName() {
        return procDefName;
    }

    /**
     * 获取流程定义分类。
     * 
     * @return 流程定义分类。
     */
    public String getProcDefCat() {
        return procDefCat;
    }

    /**
     * 获取流程定义模型。
     * 
     * @return 流程定义模型。
     */
    public String getProcDefModel() {
        return procDefModel;
    }

    /**
     * 获取流程定义图文件。
     * 
     * @return 流程定义图文件。
     */
    public byte[] getProcDefDiagramFile() {
        return procDefDiagramFile;
    }

    /**
     * 获取流程定义图文件名称。
     * 
     * @return 流程定义图文件名称。
     */
    public String getProcDefDiagramFileName() {
        return procDefDiagramFileName;
    }

    /**
     * 获取流程定义图文件长度。
     * 
     * @return 流程定义图文件长度。
     */
    public int getProcDefDiagramFileLength() {
        return procDefDiagramFileLength;
    }

    /**
     * 获取流程定义图宽度。
     * 
     * @return 流程定义图宽度。
     */
    public int getProcDefDiagramWidth() {
        return procDefDiagramWidth;
    }

    /**
     * 获取流程定义图高度。
     * 
     * @return 流程定义图高度。
     */
    public int getProcDefDiagramHeight() {
        return procDefDiagramHeight;
    }

    /**
     * 获取备注。
     * 
     * @return 备注。
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 获取扩展属性1。
     * 
     * @return 扩展属性1。
     */
    public String getExtAttr1() {
        return extAttr1;
    }

    /**
     * 获取扩展属性2。
     * 
     * @return 扩展属性2。
     */
    public String getExtAttr2() {
        return extAttr2;
    }

    /**
     * 获取扩展属性3。
     * 
     * @return 扩展属性3。
     */
    public String getExtAttr3() {
        return extAttr3;
    }

    /**
     * 获取扩展属性4。
     * 
     * @return 扩展属性4。
     */
    public String getExtAttr4() {
        return extAttr4;
    }

    /**
     * 获取扩展属性5。
     * 
     * @return 扩展属性5。
     */
    public String getExtAttr5() {
        return extAttr5;
    }

    /**
     * 获取扩展属性6。
     * 
     * @return 扩展属性6。
     */
    public String getExtAttr6() {
        return extAttr6;
    }

    /**
     * 获取扩展属性7。
     * 
     * @return 扩展属性7。
     */
    public String getExtAttr7() {
        return extAttr7;
    }

    /**
     * 获取扩展属性8。
     * 
     * @return 扩展属性8。
     */
    public String getExtAttr8() {
        return extAttr8;
    }

    /**
     * 获取版本。
     * 
     * @return 版本。
     */
    public int getVersion() {
        return version;
    }

    /**
     * 获取流程定义状态。
     * 
     * @return 流程定义状态。
     */
    public String getProcDefStatus() {
        return procDefStatus;
    }

    /**
     * 获取创建日期。
     * 
     * @return 创建日期。
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * 获取更新日期。
     * 
     * @return 更新日期。
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 获取操作人员ID。
     * 
     * @return 操作人员ID。
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * 获取操作人员名称。
     * 
     * @return 操作人员名称。
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 根据节点编码获取节点定义。
     * 
     * @param nodeCode
     *        节点编码。
     * @return 节点定义。
     */
    public NodeDef getNodeDef(String nodeCode) {
        return nodeDefMap.get(nodeCode);
    }

    /**
     * 获取所有节点定义列表。
     * 
     * @return 所有节点定义列表。
     */
    public List<? extends NodeDef> getNodeDefList() {
        return new ArrayList<>(this.nodeDefList);
    }

    /**
     * 获取起始节点定义列表。
     * 
     * @return 起始节点定义列表。
     */
    public List<? extends NodeDef> getStartNodeDefList() {
        return new ArrayList<>(this.startNodeDefList);
    }

    /**
     * 获取所有流转定义列表。
     * 
     * @return 所有流转定义列表。
     */
    public List<? extends FlowDef> getFlowDefList() {
        return new ArrayList<>(this.flowDefList);
    }

    /**
     * 获取所有注释定义列表。
     * 
     * @return 所有注释定义列表。
     */
    public List<? extends NoteDef> getNoteDefList() {
        return new ArrayList<>(this.noteDefList);
    }

    /**
     * 获取所有流程变量定义列表。
     * 
     * @return 所有流程变量定义列表。
     */
    public List<? extends ProcVarDef> getProcVarDefList() {
        return new ArrayList<>(this.procVarDefList);
    }

    /**
     * 获取所有流程变量定义map。
     * 
     * @return 所有流程变量定义map。键为流程变量名称，值为流程变量值。
     */
    public Map<String, Object> getProcVarDefMap() {
        Map<String, Object> procVarDefMap = new HashMap<>();
        for (ProcVarDef procVarDef : procVarDefList) {
            procVarDefMap.put(procVarDef.getVarName(), procVarDef.getValue());
        }

        return procVarDefMap;
    }
}