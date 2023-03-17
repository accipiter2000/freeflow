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

public class ProcDef implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String procDefId; // 流程定义ID
    protected String procDefCode; // 流程定义编码
    protected String procDefName; // 流程定义名称
    protected String procDefCat; // 流程定义分类
    @JsonIgnore
    protected transient String procDefModel; // 流程定义模型
    @JsonIgnore
    protected transient byte[] procDefDiagramFile; // 流程定义图文件
    protected String procDefDiagramFileName; // 流程定义图文件名称
    protected int procDefDiagramFileLength; // 流程定义图文件长度
    protected int procDefDiagramWidth; // 流程定义图宽度
    protected int procDefDiagramHeight; // 流程定义图高度
    protected String memo; // 备注
    protected int version; // 版本
    protected String procDefStatus; // 流程定义状态
    protected Date creationDate; // 创建日期
    protected Date updateDate; // 更新日期
    protected String operatorId; // 操作人员ID
    protected String operatorName; // 操作人员名称

    @JsonIgnore
    protected transient Map<String, NodeDef> nodeDefMap;// 节点定义map，方便通过节点编码查询

    protected List<NodeDef> nodeDefList;// 节点定列表
    protected List<FlowDef> flowDefList;// 流转定义列表
    protected List<NoteDef> noteDefList;// 注释定义列表
    protected List<ProcVarDef> procVarDefList;// 流程变量定义列表

    @JsonIgnore
    protected transient List<NodeDef> startNodeDefList;// 起始节点定义列表

    /**
     * 通过数据库记录构造。
     * 
     * @param data
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

        // 初始化阶段
        for (NodeDef nodeDef : nodeDefList) {
            nodeDef.initStage();
        }

        // 初始化起始节点定义列表
        startNodeDefList = new ArrayList<>();
        for (NodeDef nodeDef : nodeDefList) {
            if (nodeDef.getParentNodeCode() == null && nodeDef.getIncomingFlowDefList().size() == 0) {
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

    public String getProcDefId() {
        return procDefId;
    }

    public String getProcDefCode() {
        return procDefCode;
    }

    public String getProcDefName() {
        return procDefName;
    }

    public String getProcDefCat() {
        return procDefCat;
    }

    public String getProcDefModel() {
        return procDefModel;
    }

    public byte[] getProcDefDiagramFile() {
        return procDefDiagramFile;
    }

    public String getProcDefDiagramFileName() {
        return procDefDiagramFileName;
    }

    public int getProcDefDiagramFileLength() {
        return procDefDiagramFileLength;
    }

    public int getProcDefDiagramWidth() {
        return procDefDiagramWidth;
    }

    public int getProcDefDiagramHeight() {
        return procDefDiagramHeight;
    }

    public String getMemo() {
        return memo;
    }

    public int getVersion() {
        return version;
    }

    public String getProcDefStatus() {
        return procDefStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 获取节点定义。
     * 
     * @param nodeCode
     * @return
     */
    public NodeDef getNodeDef(String nodeCode) {
        return nodeDefMap.get(nodeCode);
    }

    /**
     * 获取所有节点定义列表。
     * 
     * @return
     */
    public List<? extends NodeDef> getNodeDefList() {
        List<NodeDef> nodeDefList = new ArrayList<>();
        nodeDefList.addAll(this.nodeDefList);

        return nodeDefList;
    }

    /**
     * 获取起始节点定义列表。
     * 
     * @return
     */
    public List<? extends NodeDef> getStartNodeDefList() {
        List<NodeDef> startNodeDefList = new ArrayList<>();
        startNodeDefList.addAll(this.startNodeDefList);

        return startNodeDefList;
    }

    /**
     * 获取所有流转定义列表。
     * 
     * @return
     */
    public List<? extends FlowDef> getFlowDefList() {
        List<FlowDef> flowDefList = new ArrayList<>();
        flowDefList.addAll(this.flowDefList);

        return flowDefList;
    }

    /**
     * 获取所有注释定义列表。
     * 
     * @return
     */
    public List<? extends NoteDef> getNoteDefList() {
        List<NoteDef> noteDefList = new ArrayList<>();
        noteDefList.addAll(this.noteDefList);

        return noteDefList;
    }

    /**
     * 获取所有流程变量定义列表。
     * 
     * @return
     */
    public List<? extends ProcVarDef> getProcVarDefList() {
        List<ProcVarDef> procVarDefList = new ArrayList<>();
        procVarDefList.addAll(this.procVarDefList);

        return procVarDefList;
    }

    /**
     * 获取所有流程变量定义MAP。
     * @return
     */
    public Map<String, Object> getProcVarDefMap() {
        Map<String, Object> procVarDefMap = new HashMap<>();
        for (ProcVarDef procVarDef : procVarDefList) {
            procVarDefMap.put(procVarDef.getVarName(), procVarDef.getValue());
        }

        return procVarDefMap;
    }
}