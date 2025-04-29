package com.opendynamic.ff.service.impl.mysql;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.opendynamic.OdUtils;
import com.opendynamic.ff.FfOperation;
import com.opendynamic.ff.handler.NodeHandler;
import com.opendynamic.ff.query.ChildNodeQuery;
import com.opendynamic.ff.query.DelegateQuery;
import com.opendynamic.ff.query.InvolvedProcQuery;
import com.opendynamic.ff.query.NodeQuery;
import com.opendynamic.ff.query.NodeVarQuery;
import com.opendynamic.ff.query.OperationQuery;
import com.opendynamic.ff.query.ParentNodeQuery;
import com.opendynamic.ff.query.ProcDefQuery;
import com.opendynamic.ff.query.ProcQuery;
import com.opendynamic.ff.query.TaskQuery;
import com.opendynamic.ff.service.FfAdjustProcDefService;
import com.opendynamic.ff.service.FfDelegateService;
import com.opendynamic.ff.service.FfHelper;
import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.service.FfNodeVarService;
import com.opendynamic.ff.service.FfOperationService;
import com.opendynamic.ff.service.FfProcDefService;
import com.opendynamic.ff.service.FfProcService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.service.FfTaskService;
import com.opendynamic.ff.vo.CandidateList;
import com.opendynamic.ff.vo.Delegate;
import com.opendynamic.ff.vo.DiamondShape;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.FfUser;
import com.opendynamic.ff.vo.FlowDef;
import com.opendynamic.ff.vo.LineShape;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.NodeOp;
import com.opendynamic.ff.vo.NodeVar;
import com.opendynamic.ff.vo.NodeVarOp;
import com.opendynamic.ff.vo.NoteDef;
import com.opendynamic.ff.vo.Operation;
import com.opendynamic.ff.vo.OperationContext;
import com.opendynamic.ff.vo.OvalShape;
import com.opendynamic.ff.vo.Proc;
import com.opendynamic.ff.vo.ProcDef;
import com.opendynamic.ff.vo.ProcOp;
import com.opendynamic.ff.vo.ProcVarDef;
import com.opendynamic.ff.vo.RectangleShape;
import com.opendynamic.ff.vo.RunningNodeDef;
import com.opendynamic.ff.vo.RunningProcDef;
import com.opendynamic.ff.vo.Shape;
import com.opendynamic.ff.vo.Task;
import com.opendynamic.ff.vo.TaskOp;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfServiceImpl implements FfService, ApplicationContextAware {
    private ApplicationContext applicationContext;

    private static List<ProcDef> procDefList;
    private static Queue<ProcDef> adjustProcDefList = new LinkedList<>();
    private static Map<String, NodeHandler> nodeHandlerMap;
    private static Map<String, Object> internalServiceMap;
    private static Map<String, Object> externalServiceMap;

    @Autowired
    private FfProcDefService ffProcDefService;
    @Autowired
    private FfAdjustProcDefService ffAdjustProcDefService;
    @Autowired
    private FfProcService ffProcService;
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfTaskService ffTaskService;
    @Autowired
    private FfNodeVarService ffNodeVarService;
    @Autowired
    private FfDelegateService ffDelegateService;
    @Autowired
    private FfOperationService ffOperationService;
    @Autowired
    private FfHelper ffHelper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    @Override
    public boolean refreshProcDefCache() {
        System.out.println("Initializing workflow definition...");

        List<Map<String, Object>> procDefList = ffProcDefService.selectProcDef(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, -1);
        List<ProcDef> latestProcDefList = new ArrayList<>(procDefList.size());
        for (Map<String, Object> procDef : procDefList) {
            latestProcDefList.add(new ProcDef(procDef));
        }
        FfServiceImpl.procDefList = latestProcDefList;

        return true;
    }

    @PostConstruct
    private void initNodeHandler() {
        System.out.println("Initializing node handler...");

        FfServiceImpl.nodeHandlerMap = new HashMap<>();
        Map<String, NodeHandler> nodeHandlerMap = applicationContext.getBeansOfType(NodeHandler.class);// 装配节点处理器
        for (Map.Entry<String, NodeHandler> entry : nodeHandlerMap.entrySet()) {
            FfServiceImpl.nodeHandlerMap.put(entry.getValue().getNodeType(), entry.getValue());
        }
    }

    @Override
    public NodeHandler getNodeHandler(String nodeType) {
        return nodeHandlerMap.get(nodeType);
    }

    @PostConstruct
    private void initJuel() {
        internalServiceMap = new HashMap<>();
        externalServiceMap = new HashMap<>();

        internalServiceMap.put("ffService", this);
        internalServiceMap.put("ffProcDefService", ffProcDefService);// 装配默认的service bean
        internalServiceMap.put("ffProcService", ffProcService);
        internalServiceMap.put("ffNodeService", ffNodeService);
        internalServiceMap.put("ffTaskService", ffTaskService);
        internalServiceMap.put("ffNodeVarService", ffNodeVarService);
        internalServiceMap.put("ffDelegateService", ffDelegateService);
        internalServiceMap.put("ffOperationService", ffOperationService);

        internalServiceMap = Collections.unmodifiableMap(internalServiceMap);
    }

    @Override
    public Map<String, Object> getInternalServiceMap() {
        return FfServiceImpl.internalServiceMap;
    }

    @Override
    public Map<String, Object> getExternalServiceMap() {
        return FfServiceImpl.externalServiceMap;
    }

    @Override
    public void addExternalService(String serviceName, Object service) {
        FfServiceImpl.externalServiceMap.put(serviceName, service);
    }

    @Override
    public ProcDefQuery createProcDefQuery() {
        return new ProcDefQuery(procDefList);
    }

    @Override
    public ProcQuery createProcQuery() {
        return new ProcQuery(ffProcService);
    }

    public InvolvedProcQuery createInvolvedProcQuery() {
        return new InvolvedProcQuery(ffProcService);
    }

    @Override
    public NodeQuery createNodeQuery() {
        return new NodeQuery(ffNodeService);
    }

    @Override
    public ParentNodeQuery createParentNodeQuery() {
        return new ParentNodeQuery(ffNodeService);
    }

    @Override
    public ChildNodeQuery createChildNodeQuery() {
        return new ChildNodeQuery(ffNodeService);
    }

    @Override
    public TaskQuery createTaskQuery() {
        return new TaskQuery(ffTaskService);
    }

    @Override
    public NodeVarQuery createNodeVarQuery() {
        return new NodeVarQuery(ffNodeVarService);
    }

    @Override
    public DelegateQuery createDelegateQuery() {
        return new DelegateQuery(ffDelegateService);
    }

    @Override
    public OperationQuery createOperationQuery() {
        return new OperationQuery(ffOperationService);
    }

    @Override
    public ProcDef loadProcDef(String procDefId) {
        for (ProcDef procDef : procDefList) {// 在流程定义缓存中查找
            if (procDef.getProcDefId().equals(procDefId)) {
                return procDef;
            }
        }
        for (ProcDef procDef : adjustProcDefList) {// 在调整流程定义缓存中查找
            if (procDef.getProcDefId().equals(procDefId)) {
                return procDef;
            }
        }
        // 在数据库中查找调整流程定义，如找到返回并加入到缓存中，缓存不超过200条，先进先出。
        Map<String, Object> adjustProcDef = ffAdjustProcDefService.loadAdjustProcDef(procDefId);
        if (adjustProcDef != null) {
            ProcDef procDef = new ProcDef(adjustProcDef);
            adjustProcDefList.offer(procDef);
            if (adjustProcDefList.size() > 200) {
                adjustProcDefList.poll();
            }
            return procDef;
        }

        throw new RuntimeException("errors.procDefNotFound");
    }

    @Override
    public ProcDef loadProcDefByCode(String procDefCode) {
        for (ProcDef procDef : procDefList) {
            if (procDef.getProcDefCode().equals(procDefCode) && procDef.getProcDefStatus().equals("1")) {
                return procDef;
            }
        }

        throw new RuntimeException("errors.procDefNotFound");
    }

    @Override
    public InputStream loadProcDefDiagramFile(String procDefId) {
        return new ByteArrayInputStream(loadProcDef(procDefId).getProcDefDiagramFile());
    }

    @Override
    public boolean deployProcDef(String procDefId, String procDef, InputStream procDefDiagramFile, String procDefDiagramFileName, Integer procDefDiagramFileLength, String operatorId, String operatorName) {
        RuntimeTypeAdapterFactory<Shape> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(Shape.class, "type");
        runtimeTypeAdapterFactory.registerSubtype(RectangleShape.class, "rectangle");
        runtimeTypeAdapterFactory.registerSubtype(OvalShape.class, "oval");
        runtimeTypeAdapterFactory.registerSubtype(DiamondShape.class, "diamond");
        runtimeTypeAdapterFactory.registerSubtype(LineShape.class, "line");
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

        ProcDef _procDef = gson.fromJson(procDef, ProcDef.class);

        if (procDefDiagramFile == null || procDefDiagramFileLength == null || procDefDiagramFileLength == 0) {// 根据定义生成流程图
            _procDef.init();

            BufferedImage image = new BufferedImage(_procDef.getProcDefDiagramWidth(), _procDef.getProcDefDiagramHeight(), BufferedImage.TYPE_INT_ARGB); // 创建BufferedImage对象
            Graphics2D g2d = image.createGraphics(); // 获取Graphics2D
            g2d.setComposite(AlphaComposite.Clear);// 绘制透明背景
            g2d.fillRect(0, 0, _procDef.getProcDefDiagramWidth(), _procDef.getProcDefDiagramHeight());
            g2d.setComposite(AlphaComposite.Src);// 准备绘制其它内容，非透明
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 抗锯齿

            for (NodeDef nodeDef : _procDef.getNodeDefList()) {
                nodeDef.getShape().draw(g2d, nodeDef.getNodeName());
            }
            for (FlowDef flowDef : _procDef.getFlowDefList()) {
                flowDef.getShape().draw(g2d, flowDef.getFlowName());
            }
            for (NoteDef noteDef : _procDef.getNoteDefList()) {
                if (noteDef.getDynamic().equals(FfService.BOOLEAN_FALSE)) {
                    noteDef.getShape().draw(g2d, noteDef.getNoteName());
                }
            }

            g2d.dispose(); // 释放对象

            image = OdUtils.applyShadow(image, 1, Color.BLACK, 0.2f);// 添加阴影

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, "png", baos);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            procDefDiagramFile = new ByteArrayInputStream(baos.toByteArray());
            procDefDiagramFileName = "procDef.png";
            procDefDiagramFileLength = baos.size();
        }

        ffProcDefService.insertProcDef(procDefId, _procDef.getProcDefCode(), _procDef.getProcDefName(), _procDef.getProcDefCat(), procDef, procDefDiagramFile, procDefDiagramFileName, procDefDiagramFileLength, _procDef.getProcDefDiagramWidth(), _procDef.getProcDefDiagramHeight(), _procDef.getMemo(), _procDef.getExtAttr1(), _procDef.getExtAttr2(), _procDef.getExtAttr3(), _procDef.getExtAttr4(), _procDef.getExtAttr5(), _procDef.getExtAttr6(), _procDef.getExtAttr7(), _procDef.getExtAttr8(), FfService.PROC_DEF_STATUS_ACTIVE, new Date(), new Date(), operatorId, operatorName);

        refreshProcDefCache();

        return true;
    }

    @Override
    public boolean updateProcDefDiagramFile(String procDefId, InputStream procDefDiagramFile, String procDefDiagramFileName, Integer procDefDiagramFileLength, Integer procDefDiagramWidth, Integer procDefDiagramHeight, String operatorId, String operatorName) {
        if (ffProcDefService.updateProcDefDiagramFile(procDefId, procDefDiagramFile, procDefDiagramFileName, procDefDiagramFileLength, procDefDiagramWidth, procDefDiagramHeight, new Date(), operatorId, operatorName) == 1) {
            refreshProcDefCache();
            return true;
        }

        return false;
    }

    @Override
    public boolean disableProcDef(String procDefId, String operatorId, String operatorName) {
        if (ffProcDefService.disableProcDef(procDefId, new Date(), operatorId, operatorName) == 1) {
            refreshProcDefCache();
            return true;
        }

        return false;
    }

    @Override
    public boolean enableProcDef(String procDefId, String operatorId, String operatorName) {
        if (ffProcDefService.enableProcDef(procDefId, new Date(), operatorId, operatorName) == 1) {
            refreshProcDefCache();
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteProcDef(String procDefId, String operatorId, String operatorName) {
        if (ffProcDefService.deleteProcDef(procDefId, new Date(), operatorId, operatorName) == 1) {
            refreshProcDefCache();
            return true;
        }

        return false;
    }

    @Override
    @FfOperation(nodeId = "${branchId}", operator = "${operatorId}")
    public FfResult adjustBranchProcDef(String branchId, String procDef, InputStream procDefDiagramFile, String procDefDiagramFileName, Integer procDefDiagramFileLength, String operatorId, String operatorName) {
        FfResult ffResult = new FfResult();

        Node branch = loadNode(branchId);
        String adjustSubProcDefId = branch.getAdjustSubProcDefId();

        if (StringUtils.isEmpty(procDef)) {
            if (adjustSubProcDefId != null) {
                ffNodeService.updateBranchAdjustSubProcDefId(branchId, null);
                ffAdjustProcDefService.deleteAdjustProcDef(adjustSubProcDefId, new Date(), operatorId, operatorName);
                return ffResult;
            }
            else {
                throw new RuntimeException("errors.procDefIsRequired");
            }
        }

        RuntimeTypeAdapterFactory<Shape> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(Shape.class, "type");
        runtimeTypeAdapterFactory.registerSubtype(RectangleShape.class, "rectangle");
        runtimeTypeAdapterFactory.registerSubtype(OvalShape.class, "oval");
        runtimeTypeAdapterFactory.registerSubtype(DiamondShape.class, "diamond");
        runtimeTypeAdapterFactory.registerSubtype(LineShape.class, "line");
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

        ProcDef _procDef = gson.fromJson(procDef, ProcDef.class);

        if (procDefDiagramFile == null || procDefDiagramFileLength == null || procDefDiagramFileLength == 0) {// 根据定义生成流程图
            _procDef.init();

            BufferedImage image = new BufferedImage(_procDef.getProcDefDiagramWidth(), _procDef.getProcDefDiagramHeight(), BufferedImage.TYPE_INT_ARGB); // 创建BufferedImage对象
            Graphics2D g2d = image.createGraphics(); // 获取Graphics2D
            g2d.setComposite(AlphaComposite.Clear);// 绘制透明背景
            g2d.fillRect(0, 0, _procDef.getProcDefDiagramWidth(), _procDef.getProcDefDiagramHeight());
            g2d.setComposite(AlphaComposite.Src);// 准备绘制其它内容，非透明
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 抗锯齿

            for (NodeDef nodeDef : _procDef.getNodeDefList()) {
                nodeDef.getShape().draw(g2d, nodeDef.getNodeName());
            }
            for (FlowDef flowDef : _procDef.getFlowDefList()) {
                flowDef.getShape().draw(g2d, flowDef.getFlowName());
            }
            for (NoteDef noteDef : _procDef.getNoteDefList()) {
                if (noteDef.getDynamic().equals(FfService.BOOLEAN_FALSE)) {
                    noteDef.getShape().draw(g2d, noteDef.getNoteName());
                }
            }

            g2d.dispose(); // 释放对象

            image = OdUtils.applyShadow(image, 1, Color.BLACK, 0.2f);// 添加阴影

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, "png", baos);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            procDefDiagramFile = new ByteArrayInputStream(baos.toByteArray());
            procDefDiagramFileName = "procDef.png";
            procDefDiagramFileLength = baos.size();
        }

        if (adjustSubProcDefId == null) {
            adjustSubProcDefId = OdUtils.getUuid();
            ffAdjustProcDefService.insertAdjustProcDef(adjustSubProcDefId, branch.getSubProcDefId(), procDef, procDefDiagramFile, procDefDiagramFileName, procDefDiagramFileLength, _procDef.getProcDefDiagramWidth(), _procDef.getProcDefDiagramHeight(), new Date(), new Date(), operatorId, operatorName);
            ffNodeService.updateBranchAdjustSubProcDefId(branchId, adjustSubProcDefId);
        }
        else {
            ffAdjustProcDefService.updateAdjustProcDef(adjustSubProcDefId, procDef, procDefDiagramFile, procDefDiagramFileName, procDefDiagramFileLength, _procDef.getProcDefDiagramWidth(), _procDef.getProcDefDiagramHeight(), new Date(), operatorId, operatorName);
        }

        return ffResult;
    }

    @Override
    public boolean deleteAdjustProcDef(String adjustProcDefId, Date updateDate, String operatorId, String operatorName) {
        return ffAdjustProcDefService.deleteAdjustProcDef(adjustProcDefId, updateDate, operatorId, operatorName) == 1;
    }

    @Override
    public ProcDef getNodeProcDef(Node node) {
        if (node.getAdjustSubProcDefId() == null) {
            return loadProcDef(node.getSubProcDefId());
        }
        else {
            return loadProcDef(node.getAdjustSubProcDefId());
        }
    }

    @Override
    public List<RunningNodeDef> getStartRunningNodeDefList(String subProcPath, ProcDef procDef, Map<String, Object> nodeVarMap) {
        return getNextRunningNodeDefList(subProcPath, null, null, procDef, nodeVarMap, nodeVarMap);
    }

    @Override
    public List<RunningNodeDef> getNextRunningNodeDefList(String taskId, Map<String, Object> nodeVarMap) {
        Task task = loadTask(taskId);
        if (task.getTaskType().equals(FfService.TASK_TYPE_FORWARD_TASK)) {
            return new ArrayList<>();
        }

        Node node = loadNode(task.getNodeId());
        return getNextRunningNodeDefList(node, nodeVarMap);
    }

    @Override
    public List<RunningNodeDef> getNextRunningNodeDefList(Node node, Map<String, Object> nodeVarMap) {
        Map<String, Object> fullNodeVarMap = new HashMap<>();
        fullNodeVarMap.putAll(internalServiceMap);// 添加内部服务
        fullNodeVarMap.putAll(externalServiceMap);// 添加外部服务
        fullNodeVarMap.put("proc", loadProc(node.getProcId()));
        fullNodeVarMap.put("branch", loadNode(node.getParentNodeId()));
        fullNodeVarMap.put("node", node);
        fullNodeVarMap.putAll(createNodeVarQuery().setNodeId(node.getParentNodeId()).setRecursive(true).queryForMap());
        if (nodeVarMap != null) {
            fullNodeVarMap.putAll(nodeVarMap);
        }
        NodeDef nodeDef = getNodeProcDef(node).getNodeDef((node.getNodeCode()));// 获取当前节点所属节点定义

        return new ArrayList<>(getNextRunningNodeDefList(getSubProcPath(node), node, nodeDef, null, fullNodeVarMap, nodeVarMap));
    }

    private List<RunningNodeDef> getNextRunningNodeDefList(String subProcPath, Node node, NodeDef nodeDef, ProcDef procDef, Map<String, Object> fullNodeVarMap, Map<String, Object> nodeVarMap) {
        List<RunningNodeDef> nextRunningNodeDefList = new ArrayList<>();

        List<? extends NodeDef> nextNodeDefList;
        if (nodeDef == null) {// 主流程取开始节点定义
            nextNodeDefList = procDef.getStartNodeDefList();
        }
        else {
            nextNodeDefList = nodeDef.getNextNodeDefList(fullNodeVarMap);// 查找下一个节点定义
        }

        if (!nextNodeDefList.isEmpty()) {// 如果有下一个节点定义，添加RunningNodeDef
            for (NodeDef nextNodeDef : nextNodeDefList) {
                nextRunningNodeDefList.addAll(getRunningNodeDef(subProcPath, nextNodeDef, fullNodeVarMap));
            }
        }
        else {// 如果没有下一个节点定义
            String completeReturn = node.getCompleteReturn();
            if (completeReturn != null && completeReturn.contains("${")) {// JUEL解析
                nodeVarMap.putAll(ffNodeService.getTaskStatistic(node.getNodeId()));// 获取节点任务完成信息

                // 设置JUEL解析环境
                ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
                SimpleContext simpleContext = new SimpleContext();
                for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
                    simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
                }
                // JUEL解析
                ValueExpression expression = expressionFactory.createValueExpression(simpleContext, completeReturn, String.class);
                completeReturn = (String) expression.getValue(simpleContext);
            }

            if (FfService.BOOLEAN_TRUE.equals(completeReturn)) {// 完成返回节点，获取其前一个节点
                Node previousNode = loadNode(node.getPreviousNodeIds());// 获取前一个节点。
                NodeDef previousNodeDef = getNodeProcDef(previousNode).getNodeDef((previousNode.getNodeCode()));// 获取当前节点所属节点定义

                // 计算激活的办理人，为上一次该节点任务的办理人。
                List<Task> taskList = createTaskQuery().setNodeId(previousNode.getNodeId()).queryForObjectList();
                List<String> assigneeList = new ArrayList<>();
                for (Task task : taskList) {
                    assigneeList.add(task.getAssignee());
                }
                RunningNodeDef runningNodeDef = new RunningNodeDef(previousNodeDef, null);
                runningNodeDef.setSubProcPath(subProcPath);
                runningNodeDef.setAssigneeList(getAssigneeList(StringUtils.join(assigneeList, ",")));
                nextRunningNodeDefList.add(runningNodeDef);
            }
            else {// 非完成返回节点，继续处理其上一级节点。
                Node parentTaskNode = getParentTaskNode(node);
                if (parentTaskNode != null) {
                    fullNodeVarMap.put("proc", loadProc(parentTaskNode.getProcId()));
                    fullNodeVarMap.put("branch", loadNode(parentTaskNode.getParentNodeId()));
                    fullNodeVarMap.put("node", parentTaskNode);
                    fullNodeVarMap.putAll(createNodeVarQuery().setNodeId(parentTaskNode.getParentNodeId()).setRecursive(true).queryForMap());
                    if (nodeVarMap != null) {
                        fullNodeVarMap.putAll(nodeVarMap);
                    }
                    NodeDef parentTaskNodeDef = getNodeProcDef(parentTaskNode).getNodeDef((parentTaskNode.getNodeCode()));// 获取当前节点所属节点定义
                    if (parentTaskNodeDef.getNodeType().equals(FfService.NODE_TYPE_SUB_PROC)) { // 上级节点类型为子流程时，计算子流程路径
                        if (StringUtils.isNotEmpty(subProcPath)) {
                            String[] splits = subProcPath.split("\\.");
                            subProcPath = StringUtils.join(splits, ".", 0, splits.length - 1);
                        }
                    }
                    nextRunningNodeDefList.addAll(getNextRunningNodeDefList(subProcPath, parentTaskNode, parentTaskNodeDef, procDef, fullNodeVarMap, nodeVarMap));
                }
            }
        }

        return nextRunningNodeDefList;
    }

    private Node getParentTaskNode(Node node) {
        if (node.getParentNodeId() == null) {
            return null;
        }

        Node parentNode = loadNode(node.getParentNodeId());
        if (parentNode.getNodeType().equals(FfService.NODE_TYPE_STAGE) || parentNode.getNodeType().equals(FfService.NODE_TYPE_SUB_PROC)) {
            return parentNode;
        }
        else {
            return getParentTaskNode(parentNode);
        }
    }

    @SuppressWarnings("unchecked")
    private List<RunningNodeDef> getRunningNodeDef(String subProcPath, NodeDef nodeDef, Map<String, Object> nodeVarMap) {
        List<RunningNodeDef> runningNodeDefList = new ArrayList<>();

        // 设置JUEL解析环境
        HashMap<String, Object> allNodeVarMap = new HashMap<>();
        allNodeVarMap.putAll(getInternalServiceMap());
        allNodeVarMap.putAll(getExternalServiceMap());
        if (nodeVarMap != null) {
            allNodeVarMap.putAll(nodeVarMap);
        }
        ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
        SimpleContext simpleContext = new SimpleContext();
        for (Map.Entry<String, Object> entry : allNodeVarMap.entrySet()) {
            simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
        }
        // JUEL解析
        // Stage取其内部的开始节点定义
        if (nodeDef.getNodeType().equals(FfService.NODE_TYPE_STAGE)) {
            List<? extends NodeDef> nextNodeDefList = nodeDef.getStartChildNodeDefList();
            for (NodeDef nextNodeDef : nextNodeDefList) {
                runningNodeDefList.addAll(getRunningNodeDef(subProcPath, nextNodeDef, nodeVarMap));
            }
        }
        // GATEWAY取其下一个节点定义
        if (nodeDef.getNodeType().equals(FfService.NODE_TYPE_GATEWAY)) {
            List<? extends NodeDef> nextNodeDefList = nodeDef.getNextNodeDefList(allNodeVarMap);// 查找下一个节点定义
            for (NodeDef nextNodeDef : nextNodeDefList) {
                runningNodeDefList.addAll(getRunningNodeDef(subProcPath, nextNodeDef, nodeVarMap));
            }
        }
        // TASK,CENTER_FORWARD_TASK,NODE_TYPE_SERVICE_TASK候选人解析
        if ((nodeDef.getNodeType().equals(FfService.NODE_TYPE_TASK) || nodeDef.getNodeType().equals(FfService.NODE_TYPE_CENTER_FORWARD_TASK) || nodeDef.getNodeType().equals(FfService.NODE_TYPE_SERVICE_TASK)) && StringUtils.isNotEmpty(nodeDef.getCandidateAssignee())) {
            ValueExpression expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getCandidateAssignee(), Object.class);
            Object object;
            try {
                object = expression.getValue(simpleContext);
            }
            catch (Exception e) {
                e.printStackTrace();
                object = null;
            }
            List<FfUser> candidateAssigneeList;// 解析后候选人列表
            if (object instanceof List) {
                candidateAssigneeList = (List<FfUser>) object;
            }
            else {
                candidateAssigneeList = getAssigneeList((String) object);
            }

            RunningNodeDef runningNodeDef = new RunningNodeDef(nodeDef, null);
            runningNodeDef.setSubProcPath(subProcPath);
            runningNodeDef.setCandidateAssigneeList(candidateAssigneeList);
            runningNodeDefList.add(runningNodeDef);
        }
        // TASK,CENTER_FORWARD_TASK,NODE_TYPE_SERVICE_TASK办理人解析
        if ((nodeDef.getNodeType().equals(FfService.NODE_TYPE_TASK) || nodeDef.getNodeType().equals(FfService.NODE_TYPE_CENTER_FORWARD_TASK) || nodeDef.getNodeType().equals(FfService.NODE_TYPE_SERVICE_TASK)) && StringUtils.isNotEmpty(nodeDef.getAssignee())) {
            ValueExpression expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getAssignee(), Object.class);
            Object object;
            try {
                object = expression.getValue(simpleContext);
            }
            catch (Exception e) {
                e.printStackTrace();
                object = null;
            }
            List<FfUser> assigneeList;// 解析后办理人列表
            if (object instanceof List) {
                assigneeList = (List<FfUser>) object;
            }
            else {
                assigneeList = getAssigneeList((String) object);
            }

            RunningNodeDef runningNodeDef = new RunningNodeDef(nodeDef, null);
            runningNodeDef.setSubProcPath(subProcPath);
            runningNodeDef.setAssigneeList(assigneeList);
            runningNodeDefList.add(runningNodeDef);
        }
        // SERVICE_TASK无办理人和候选人
        if (nodeDef.getNodeType().equals(FfService.NODE_TYPE_SERVICE_TASK) && StringUtils.isEmpty(nodeDef.getCandidateAssignee()) && StringUtils.isEmpty(nodeDef.getAssignee())) {
            RunningNodeDef runningNodeDef = new RunningNodeDef(nodeDef, null);
            runningNodeDef.setSubProcPath(subProcPath);
            runningNodeDefList.add(runningNodeDef);
        }
        // SUB_PROC候选子流程定义解析
        if ((nodeDef.getNodeType().equals(FfService.NODE_TYPE_SUB_PROC) || nodeDef.getNodeType().equals(FfService.NODE_TYPE_ISOLATE_SUB_PROC)) && StringUtils.isNotEmpty(nodeDef.getCandidateSubProcDef())) {
            ValueExpression expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getCandidateSubProcDef(), Object.class);
            Object object;
            try {
                object = expression.getValue(simpleContext);
            }
            catch (Exception e) {
                e.printStackTrace();
                object = null;
            }
            List<RunningProcDef> candidateSubProcDefList = new ArrayList<>();// 解析后候选子流程定义列表
            if (object instanceof List) {
                List<ProcDef> procDefList = (List<ProcDef>) object;
                for (ProcDef procDef : procDefList) {
                    candidateSubProcDefList.add(new RunningProcDef(procDef));
                }
            }
            else {
                if (StringUtils.isNotEmpty((String) object)) {
                    String[] assignSubProcDefs = ((String) object).split(",");
                    for (String assignSubProcDef : assignSubProcDefs) {
                        RunningProcDef runningProcDef = new RunningProcDef(loadProcDefByCode(assignSubProcDef));
                        candidateSubProcDefList.add(runningProcDef);
                    }
                }
            }

            // 递归处理内部子流程定义起始节点定义列表
            for (RunningProcDef runningProcDef : candidateSubProcDefList) {
                Map<String, Object> subProcAllNodeVarMap = new HashMap<>();
                subProcAllNodeVarMap.putAll(allNodeVarMap);
                subProcAllNodeVarMap.putAll(runningProcDef.getProcVarDefMap());
                List<RunningNodeDef> startRunningNodeDefList = getStartRunningNodeDefList(StringUtils.isNotEmpty(subProcPath) ? subProcPath + "." + nodeDef.getNodeCode() + ":" + runningProcDef.getProcDefCode() : nodeDef.getNodeCode() + ":" + runningProcDef.getProcDefCode(), runningProcDef, subProcAllNodeVarMap);
                runningProcDef.setNextRunningNodeDefList(startRunningNodeDefList);
            }

            RunningNodeDef runningNodeDef = new RunningNodeDef(nodeDef, null);
            runningNodeDef.setSubProcPath(subProcPath);
            runningNodeDef.setCandidateSubProcDefList(candidateSubProcDefList);
            runningNodeDefList.add(runningNodeDef);
        }
        // SUB_PROC子流程定义解析
        if ((nodeDef.getNodeType().equals(FfService.NODE_TYPE_SUB_PROC) || nodeDef.getNodeType().equals(FfService.NODE_TYPE_ISOLATE_SUB_PROC)) && StringUtils.isNotEmpty(nodeDef.getAssignSubProcDef())) {
            ValueExpression expression = expressionFactory.createValueExpression(simpleContext, nodeDef.getAssignSubProcDef(), Object.class);
            Object object;
            try {
                object = expression.getValue(simpleContext);
            }
            catch (Exception e) {
                e.printStackTrace();
                object = null;
            }
            List<RunningProcDef> assignSubProcDefList = new ArrayList<>();// 解析后候选子流程定义列表
            if (object instanceof List) {
                List<ProcDef> procDefList = (List<ProcDef>) object;
                for (ProcDef procDef : procDefList) {
                    assignSubProcDefList.add(new RunningProcDef(procDef));
                }
            }
            else {
                if (StringUtils.isNotEmpty((String) object)) {
                    String[] assignSubProcDefs = ((String) object).split(",");
                    for (String assignSubProcDef : assignSubProcDefs) {
                        RunningProcDef runningProcDef = new RunningProcDef(loadProcDefByCode(assignSubProcDef));
                        assignSubProcDefList.add(runningProcDef);
                    }
                }
            }

            // 递归处理内部子流程定义起始节点定义列表
            for (RunningProcDef runningProcDef : assignSubProcDefList) {
                Map<String, Object> subProcAllNodeVarMap = new HashMap<>();
                subProcAllNodeVarMap.putAll(allNodeVarMap);
                subProcAllNodeVarMap.putAll(runningProcDef.getProcVarDefMap());
                List<RunningNodeDef> startRunningNodeDefList = getStartRunningNodeDefList(StringUtils.isNotEmpty(subProcPath) ? subProcPath + "." + nodeDef.getNodeCode() + ":" + runningProcDef.getProcDefCode() : nodeDef.getNodeCode() + ":" + runningProcDef.getProcDefCode(), runningProcDef, subProcAllNodeVarMap);
                runningProcDef.setNextRunningNodeDefList(startRunningNodeDefList);
            }

            RunningNodeDef runningNodeDef = new RunningNodeDef(nodeDef, null);
            runningNodeDef.setSubProcPath(subProcPath);
            runningNodeDef.setAssignSubProcDefList(assignSubProcDefList);
            runningNodeDefList.add(runningNodeDef);
        }
        // END
        if (nodeDef.getNodeType().equals(FfService.NODE_TYPE_END)) {
            RunningNodeDef runningNodeDef = new RunningNodeDef(nodeDef, null);
            runningNodeDef.setSubProcPath(subProcPath);
            runningNodeDefList.add(runningNodeDef);
        }

        return runningNodeDefList;
    }

    @Override
    public Proc loadProc(String procId) {
        return createProcQuery().setProcId(procId).queryForObject();
    }

    @Override
    public boolean updateProcBizInfo(String procId, String bizId, String bizType, String bizCode, String bizName, String bizDesc) {
        return ffProcService.updateProcBizInfo(procId, bizId, bizType, bizCode, bizName, bizDesc) == 1;
    }

    @Override
    @FfOperation(operator = "${procStartUser}")
    public FfResult startProc(ProcDef procDef, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList) {
        if (candidateList == null) {
            candidateList = new CandidateList();
        }

        return startProc(procDef, bizId, bizType, bizCode, bizName, bizDesc, procStartUser, nodeVarMap, candidateList, null);
    }

    @Override
    @FfOperation(operator = "${procStartUser}")
    public FfResult startProcByProcDefCode(String procDefCode, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList) {
        ProcDef procDef = loadProcDefByCode(procDefCode);
        return startProc(procDef, bizId, bizType, bizCode, bizName, bizDesc, procStartUser, nodeVarMap, candidateList);
    }

    @Override
    @FfOperation(operator = "${procStartUser}")
    public FfResult startIsolateSubProc(String isolateSubProcNodeId, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList) {
        FfResult ffResult = new FfResult();

        Node isolateSubProcNode = loadNode(isolateSubProcNodeId);
        if (!isolateSubProcNode.getIsolateSubProcStatus().equals(FfService.PROC_STATUS_NOT_START) && !isolateSubProcNode.getIsolateSubProcStatus().equals(FfService.PROC_STATUS_TERMINATE)) {
            throw new RuntimeException("errors.isolateSubProcIsAlreadyStart");
        }

        for (Map.Entry<String, Object> entry : createNodeVarQuery().setNodeId(isolateSubProcNode.getNodeId()).setRecursive(false).queryForMap().entrySet()) {
            if (!nodeVarMap.containsKey(entry.getKey())) {
                nodeVarMap.put(entry.getKey(), entry.getValue());
            }
        }

        if (candidateList == null || candidateList.isEmpty()) {// 如果没有candidateList，使用节点定义中的isolateSubProcCandidate
            String isolateSubProcCandidate = isolateSubProcNode.getIsolateSubProcCandidate();
            if (StringUtils.isNotEmpty(isolateSubProcCandidate)) {
                candidateList = new Gson().fromJson(isolateSubProcCandidate, CandidateList.class);
            }
        }

        ProcDef procDef = loadProcDefByCode(isolateSubProcNode.getIsolateSubProcDefCode());
        ffResult.addAll(startProc(procDef, bizId, bizType, bizCode, bizName, bizDesc, procStartUser, nodeVarMap, candidateList, isolateSubProcNodeId));

        ffNodeService.updateIsolateSubProcStatus(isolateSubProcNodeId, FfService.PROC_STATUS_ACTIVE);

        return ffResult;
    }

    private FfResult startProc(ProcDef procDef, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList, String isolateSubProcNodeId) {
        FfResult ffResult = new FfResult();

        if (procDef == null || procDef.getProcDefStatus().equals(FfService.PROC_DEF_STATUS_DISABLE)) {
            throw new RuntimeException("errors.procDefIsNotActive");
        }

        String procId = OdUtils.getUuid();
        ffProcService.insertProc(procId, procDef.getProcDefId(), null, isolateSubProcNodeId, bizId, bizType, bizCode, bizName, bizDesc, procStartUser, ffHelper.getUserName(procStartUser), null, null, null, FfService.PROC_STATUS_ACTIVE, new Date());// 新增主流程
        Proc proc = createProcQuery().setProcId(procId).queryForObject();
        ffResult.addCreateProc(proc);
        ffNodeService.insertNode(procId, null, procId, null, null, procDef.getProcDefId(), null, FfService.NODE_TYPE_BRANCH, null, procDef.getProcDefName(), null, null, null, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, null, null, null, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, "5", null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
        Node branchNode = createNodeQuery().setNodeId(procId).queryForObject();
        ffResult.addCreateNode(branchNode);

        for (ProcVarDef procVarDef : procDef.getProcVarDefList()) {
            if (!nodeVarMap.containsKey(procVarDef.getVarName())) {
                nodeVarMap.put(procVarDef.getVarName(), procVarDef.getValue());
            }
        }

        updateNodeVar(procId, nodeVarMap);// 更新节点变量

        List<? extends NodeDef> startNodeDefList = procDef.getStartNodeDefList();
        OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_INSERT).setInitialNodeVarMap(nodeVarMap).setExecutor(procStartUser).setCurrentProc(proc).setCurrentBranchNode(branchNode).setCurrentNodeVarMapNode(branchNode).setCurrentNodeVarMap(nodeVarMap);
        for (NodeDef startNodeDef : startNodeDefList) {
            ffResult.addAll(getNodeHandler(startNodeDef.getNodeType()).insertNodeByNodeDef(startNodeDef, branchNode, null, candidateList, operationContext));
        }

        return ffResult;
    }

    @Override
    @FfOperation(operator = "${procStartUser}")
    public FfResult startProcToNode(ProcDef procDef, String subProcPath, String nodeCode, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList) {
        if (candidateList == null) {
            candidateList = new CandidateList();
        }

        return startProcToNode(procDef, subProcPath, nodeCode, bizId, bizType, bizCode, bizName, bizDesc, procStartUser, nodeVarMap, candidateList, null);
    }

    @Override
    @FfOperation(operator = "${procStartUser}")
    public FfResult startProcToNodeByProcDefCode(String procDefCode, String subProcPath, String nodeCode, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList) {
        ProcDef procDef = loadProcDefByCode(procDefCode);
        return startProcToNode(procDef, subProcPath, nodeCode, bizId, bizType, bizCode, bizName, bizDesc, procStartUser, nodeVarMap, candidateList);
    }

    private FfResult startProcToNode(ProcDef procDef, String subProcPath, String nodeCode, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList, String isolateSubProcNodeId) {
        FfResult ffResult = new FfResult();

        if (procDef == null || procDef.getProcDefStatus().equals(FfService.PROC_DEF_STATUS_DISABLE)) {
            throw new RuntimeException("errors.procDefIsNotActive");
        }

        String procId = OdUtils.getUuid();
        ffProcService.insertProc(procId, procDef.getProcDefId(), null, isolateSubProcNodeId, bizId, bizType, bizCode, bizName, bizDesc, procStartUser, ffHelper.getUserName(procStartUser), null, null, null, FfService.PROC_STATUS_ACTIVE, new Date());// 新增主流程
        Proc proc = createProcQuery().setProcId(procId).queryForObject();
        ffResult.addCreateProc(proc);
        ffNodeService.insertNode(procId, null, procId, null, null, procDef.getProcDefId(), null, FfService.NODE_TYPE_BRANCH, null, procDef.getProcDefName(), null, null, null, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, null, null, null, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, "5", null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
        Node branchNode = createNodeQuery().setNodeId(procId).queryForObject();
        ffResult.addCreateNode(branchNode);

        for (ProcVarDef procVarDef : procDef.getProcVarDefList()) {
            if (!nodeVarMap.containsKey(procVarDef.getVarName())) {
                nodeVarMap.put(procVarDef.getVarName(), procVarDef.getValue());
            }
        }

        updateNodeVar(procId, nodeVarMap);// 更新节点变量

        ProcDef subProcDef = procDef;
        String branchNodeId;
        if (StringUtils.isNotEmpty(subProcPath)) {// 建立子流程路径上所有节点
            String[] splits = subProcPath.split("\\.");
            String[] path;

            for (String split : splits) {
                path = split.split(":");

                // 创建上级节点
                ffResult.addAll(insertParentNodes(path[0], subProcDef, branchNode.getNodeId(), procId, true));
                List<Node> createNodeList = ffResult.getCreateNodeList();
                branchNode = createNodeList.get(createNodeList.size() - 1);

                // 新增子流程分支节点
                subProcDef = loadProcDefByCode(path[1]);
                branchNodeId = OdUtils.getUuid();
                ffNodeService.insertNode(branchNodeId, branchNode.getNodeId(), procId, null, null, subProcDef.getProcDefId(), null, FfService.NODE_TYPE_BRANCH, null, subProcDef.getProcDefName(), null, null, FfService.DEFAULT_COMPLETE_EXPRESSION_, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, null, null, null, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, "5", null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
                branchNode = createNodeQuery().setNodeId(branchNodeId).queryForObject();
                ffResult.addCreateNode(branchNode);
            }
        }

        ffResult.addAll(insertParentNodes(nodeCode, subProcDef, branchNode.getNodeId(), procId, false));
        List<Node> createNodeList = ffResult.getCreateNodeList();
        branchNode = createNodeList.get(createNodeList.size() - 1);

        NodeDef nodeDef = subProcDef.getNodeDef(nodeCode);
        OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_INSERT).setInitialNodeVarMap(nodeVarMap).setExecutor(procStartUser).setCurrentProc(proc).setCurrentBranchNode(branchNode).setCurrentNodeVarMapNode(branchNode).setCurrentNodeVarMap(nodeVarMap);
        ;
        ffResult.addAll(getNodeHandler(nodeDef.getNodeType()).insertNodeByNodeDef(nodeDef, branchNode, null, candidateList, operationContext));

        return ffResult;
    }

    /**
     * 根据指定的节点编码和流程定义创建上级节点。
     */
    private FfResult insertParentNodes(String nodeCode, ProcDef procDef, String parentNodeId, String procId, boolean includeSelf) {
        FfResult ffResult = new FfResult();

        NodeDef nodeDef = procDef.getNodeDef(nodeCode);
        List<? extends NodeDef> parentNodeDefList = nodeDef.getParentNodeDefList();// 获取节点的所有上级节点，包括自身，并新增这些节点
        NodeDef parentNodeDef;
        String nodeId;
        for (int i = 0; i < parentNodeDefList.size(); i++) {
            if (!includeSelf && i >= parentNodeDefList.size() - 1) {
                continue;
            }

            parentNodeDef = parentNodeDefList.get(i);
            nodeId = OdUtils.getUuid();
            ffNodeService.insertNode(nodeId, parentNodeId, procId, null, null, procDef.getProcDefId(), null, parentNodeDef.getNodeType(), parentNodeDef.getNodeCode(), parentNodeDef.getNodeName(), parentNodeDef.getParentNodeCode(), parentNodeDef.getCandidateAssignee(), parentNodeDef.getCompleteExpression(), parentNodeDef.getCompleteReturn(), parentNodeDef.getExclusive(), parentNodeDef.getWaitingForCompleteNode(), parentNodeDef.getAutoCompleteSameAssignee(), parentNodeDef.getAutoCompleteEmptyAssignee(), parentNodeDef.getInform(), parentNodeDef.getAssignee(), parentNodeDef.getAction(), parentNodeDef.getDueDate(), parentNodeDef.getClaim(), parentNodeDef.getForwardable(), parentNodeDef.getPriority(), null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
            ffResult.addCreateNode(createNodeQuery().setNodeId(nodeId).queryForObject());
            parentNodeId = nodeId;
        }

        return ffResult;
    }

    @Override
    public RunningProcDef getRunningProcDef(String procId, String currentTaskId, boolean drawOptional) {
        Node currentNode = null;
        if (StringUtils.isNotEmpty(currentTaskId)) {
            currentNode = loadNode(loadTask(currentTaskId).getNodeId());
        }

        return getBranchRunningProcDef(procId, currentNode, drawOptional);// 从主流程开始处理。
    }

    // 处理流程或子流程。
    private RunningProcDef getBranchRunningProcDef(String branchId, Node currentNode, boolean drawOptional) {
        Node branch = loadNode(branchId);
        ProcDef procDef = getNodeProcDef(branch); // 获取当前节点所属流程定义

        RunningProcDef runningProcDef = new RunningProcDef(procDef);

        // 设置流程状态。
        if (branch.getParentNodeId() == null) {
            runningProcDef.setProcStatus(branch.getProcStatus());
        }

        BufferedImage image;
        Graphics2D g2d;
        try {
            image = ImageIO.read(new ByteArrayInputStream(procDef.getProcDefDiagramFile()));// 绘流程定义图
            g2d = image.createGraphics();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 递归处理子流程。
        List<Node> childNodeList = createChildNodeQuery().setNodeId(branchId).setRecursive(true).setIncludeSelf(true).queryForObjectList();
        for (Node childNode : childNodeList) {
            if (childNode.getNodeId().equals(branchId)) {
                handleChildNode(childNode, childNodeList, currentNode, runningProcDef, procDef, drawOptional, g2d);
            }

            if (runningProcDef.getNodeDef(childNode.getNodeCode()) != null) {
                ((RunningNodeDef) runningProcDef.getNodeDef(childNode.getNodeCode())).setNodeStatus(childNode.getNodeStatus());// 设置节点状态 。
            }
        }

        // 绘制动态注释
        Map<String, Object> nodeVarMap = createNodeVarQuery().setNodeId(branchId).queryForMap();
        drawDynamicNote(g2d, procDef, nodeVarMap);

        // 设置当前节点
        runningProcDef.setNodeVarMap(nodeVarMap);
        if (currentNode != null) {
            List<String> parentNodeIdList = OdUtils.collectFromBean(createParentNodeQuery().setNodeId(currentNode.getNodeId()).setIncludeSelf(true).setRecursive(true).queryForObjectList(), "nodeId", String.class);
            if (parentNodeIdList.contains(branchId)) {
                runningProcDef.setCurrent(true);
            }
        }

        g2d.dispose();

        // 设置运行期流程图
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        String base64String = Base64.getEncoder().encodeToString(baos.toByteArray());
        runningProcDef.setRunningDiagram(base64String);

        return runningProcDef;
    }

    // 递归处理子流程，绘制流程图节点状态，
    private void handleChildNode(Node node, List<Node> childNodeList, Node currentNode, RunningProcDef runningProcDef, ProcDef procDef, boolean drawOptional, Graphics2D g2d) {
        NodeDef nodeDef;
        Pattern outerPattern = Pattern.compile(FfService.CENTER_FORWARD_STEP + " *== *-?\\d+");
        Pattern innerPattern = Pattern.compile("-?\\d+");
        int centerForwardStep;
        int maxCenterForwardStep = 0;
        for (Node childNode : childNodeList) {
            if (childNode.getParentNodeId() != null && childNode.getParentNodeId().equals(node.getNodeId())) {
                if (childNode.getNodeType().equals(FfService.NODE_TYPE_BRANCH)) {// 如果下级节点为子流程，递归处理。
                    RunningProcDef childRunningProcDef = getBranchRunningProcDef(childNode.getNodeId(), currentNode, drawOptional);
                    Node parentNode = loadNode(childNode.getParentNodeId());
                    ((RunningNodeDef) runningProcDef.getNodeDef(parentNode.getNodeCode())).addSubProcRunningProcDef(childRunningProcDef);
                }
                else {// 如果下级节点不是子流程，绘制状态。
                    if (childNode.getNodeStatus().equals(FfService.NODE_STATUS_ACTIVE) && childNode.getNodeCode() != null) {
                        nodeDef = procDef.getNodeDef(childNode.getNodeCode());
                        nodeDef.getShape().drawActive(g2d);// 绘制活动节点。

                        // 绘制可选节点。(围绕中心转发节点）
                        if (drawOptional && childNode.getNodeType().equals(FfService.NODE_TYPE_CENTER_FORWARD_TASK)) {
                            Map<String, Object> nodeVarMap = createNodeVarQuery().setNodeId(childNode.getParentNodeId()).queryForMap();
                            if (nodeVarMap.get(FfService.CENTER_FORWARD_STEP) != null) {
                                int currentCenterForwardStep = Integer.parseInt((String) nodeVarMap.get(FfService.CENTER_FORWARD_STEP));// 获取当前CENTER_FORWARD_STEP

                                Matcher matcher;
                                for (FlowDef flowDef : nodeDef.getOutgoingFlowDefList()) {// 判断中心转发节点相关的下一步转发节点，按当前和可选绘制其状态
                                    boolean isDefault = false;

                                    if (flowDef.getConditionExpression() == null) {
                                        continue;
                                    }
                                    matcher = outerPattern.matcher(flowDef.getConditionExpression());
                                    if (!matcher.find()) {
                                        continue;
                                    }
                                    matcher = innerPattern.matcher(matcher.group());
                                    if (!matcher.find()) {
                                        continue;
                                    }
                                    centerForwardStep = Integer.parseInt(matcher.group());
                                    if (centerForwardStep == currentCenterForwardStep) {
                                        isDefault = true;
                                    }

                                    ((RunningNodeDef) runningProcDef.getNodeDef(flowDef.getTargetNodeCode())).setCenterForwardStep(centerForwardStep);
                                    procDef.getNodeDef(flowDef.getTargetNodeCode()).getShape().drawOptional(g2d, isDefault);

                                    if (centerForwardStep > maxCenterForwardStep) {
                                        maxCenterForwardStep = centerForwardStep;
                                    }
                                }

                                // 绘制中心转发节点上级的STAGE节点相关的下一步节点，按当前和可选绘制其状态
                                maxCenterForwardStep++;
                                RunningNodeDef parentNodeDef = (RunningNodeDef) runningProcDef.getNodeDef(node.getNodeCode());
                                List<? extends FlowDef> parentOutgoingFlowDefList = parentNodeDef.getOutgoingFlowDefList();
                                for (FlowDef parentOutgoingFlowDef : parentOutgoingFlowDefList) {
                                    ((RunningNodeDef) runningProcDef.getNodeDef(parentOutgoingFlowDef.getTargetNodeCode())).setCenterForwardStep(maxCenterForwardStep);
                                    procDef.getNodeDef(parentOutgoingFlowDef.getTargetNodeCode()).getShape().drawOptional(g2d, maxCenterForwardStep == currentCenterForwardStep);
                                }
                            }
                        }
                    }

                    handleChildNode(childNode, childNodeList, currentNode, runningProcDef, procDef, drawOptional, g2d);
                }
            }
        }
    }

    /**
     * 绘制流程图动态注释。
     */
    private void drawDynamicNote(Graphics2D g2d, ProcDef procDef, Map<String, Object> nodeVarMap) {
        // 设置JUEL解析环境
        HashMap<String, Object> allNodeVarMap = new HashMap<>();
        allNodeVarMap.putAll(getInternalServiceMap());
        allNodeVarMap.putAll(getExternalServiceMap());
        if (nodeVarMap != null) {
            allNodeVarMap.putAll(nodeVarMap);
        }
        ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
        SimpleContext simpleContext = new SimpleContext();
        for (Map.Entry<String, Object> entry : allNodeVarMap.entrySet()) {
            simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
        }

        BufferedImage noteImage = new BufferedImage(procDef.getProcDefDiagramWidth(), procDef.getProcDefDiagramHeight(), BufferedImage.TYPE_INT_ARGB); // 创建绘制动态注释图用BufferedImage
        Graphics2D noteG2d = noteImage.createGraphics(); // 获取Graphics2D
        noteG2d.setComposite(AlphaComposite.Clear);// 绘制透明背景
        noteG2d.fillRect(0, 0, procDef.getProcDefDiagramWidth(), procDef.getProcDefDiagramHeight());
        noteG2d.setComposite(AlphaComposite.Src);// 准备绘制其它内容，非透明
        noteG2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 抗锯齿

        for (NoteDef noteDef : procDef.getNoteDefList()) {// 绘制动态注释
            if (noteDef.getDynamic().equals(FfService.BOOLEAN_TRUE) && StringUtils.isNotEmpty(noteDef.getNoteName())) {
                // JUEL解析
                ValueExpression expression = expressionFactory.createValueExpression(simpleContext, noteDef.getNoteName(), String.class);// 解析注释EL表达式
                noteDef.getShape().draw(noteG2d, (String) expression.getValue(simpleContext));
            }
        }

        noteImage = OdUtils.applyShadow(noteImage, 1, Color.BLACK, 0.2f);// 添加阴影
        g2d.drawImage(noteImage, 0, 0, null);// 绘制动态注释图到流程图
    }

    @Override
    @FfOperation(procId = "${procId}", taskId = "${taskId}", operator = "${executor}")
    public FfResult suspendProc(String procId, String taskId, String executor) {
        FfResult ffResult = new FfResult();

        List<Task> taskList = createTaskQuery().setProcId(procId).setTaskStatus(FfService.TASK_STATUS_ACTIVE).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_SUSPEND);
            task.setTaskEndUser(executor);
            task.setTaskEndUserName(ffHelper.getUserName(executor));
            task.setTaskEndDate(COMPLETE_DATE_);
            task.setTaskStatus(FfService.TASK_STATUS_SUSPEND);
            ffResult.addSuspendTask(task);
        }
        List<Node> nodeList = createNodeQuery().setProcId(procId).setNodeStatus(FfService.NODE_STATUS_ACTIVE).queryForObjectList();
        for (Node node : nodeList) {
            Date COMPLETE_DATE_ = new Date();
            ffNodeService.updateNodeStatus(node.getNodeId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.NODE_STATUS_SUSPEND);
            node.setNodeEndUser(executor);
            node.setNodeEndUserName(ffHelper.getUserName(executor));
            node.setNodeEndDate(COMPLETE_DATE_);
            node.setNodeStatus(FfService.NODE_STATUS_SUSPEND);
            ffResult.addSuspendNode(node);
        }

        ffProcService.updateProcStatus(procId, executor, ffHelper.getUserName(executor), new Date(), FfService.PROC_STATUS_SUSPEND);
        ffResult.addSuspendProc(loadProc(procId));

        return ffResult;
    }

    @Override
    @FfOperation(procId = "${procId}", taskId = "${taskId}", operator = "${executor}")
    public FfResult activateProc(String procId, String taskId, String executor) {
        FfResult ffResult = new FfResult();

        List<Task> taskList = createTaskQuery().setProcId(procId).setTaskStatus(FfService.TASK_STATUS_SUSPEND).queryForObjectList();
        for (Task task : taskList) {
            ffTaskService.updateTaskStatus(task.getTaskId(), FfService.TASK_STATUS_ACTIVE);
            task.setTaskStatus(FfService.TASK_STATUS_ACTIVE);
            ffResult.addActivateTask(task);
        }
        List<Node> nodeList = createNodeQuery().setProcId(procId).setNodeStatus(FfService.NODE_STATUS_SUSPEND).queryForObjectList();
        for (Node node : nodeList) {
            ffNodeService.updateNodeStatus(node.getNodeId(), FfService.NODE_STATUS_ACTIVE);
            node.setNodeStatus(FfService.NODE_STATUS_ACTIVE);
            ffResult.addActivateNode(node);
        }

        ffProcService.updateProcStatus(procId, FfService.PROC_STATUS_ACTIVE);
        ffResult.addActivateProc(loadProc(procId));

        return ffResult;
    }

    @Override
    @FfOperation(procId = "${procId}", taskId = "${taskId}", operator = "${executor}")
    public FfResult completeProc(String procId, String taskId, String executor) {
        FfResult ffResult = new FfResult();

        List<Task> taskList = createTaskQuery().setProcId(procId).setTaskStatus(FfService.TASK_STATUS_ACTIVE).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_COMPLETE);
            task.setTaskEndUser(executor);
            task.setTaskEndUserName(ffHelper.getUserName(executor));
            task.setTaskEndDate(COMPLETE_DATE_);
            task.setTaskStatus(FfService.TASK_STATUS_COMPLETE);
            ffResult.addCompleteTask(task);
        }
        List<Node> nodeList = createNodeQuery().setProcId(procId).setNodeStatus(FfService.NODE_STATUS_ACTIVE).queryForObjectList();
        for (Node node : nodeList) {
            Date COMPLETE_DATE_ = new Date();
            ffNodeService.updateNodeStatus(node.getNodeId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.NODE_STATUS_COMPLETE);
            node.setNodeEndUser(executor);
            node.setNodeEndUserName(ffHelper.getUserName(executor));
            node.setNodeEndDate(COMPLETE_DATE_);
            node.setNodeStatus(FfService.NODE_STATUS_COMPLETE);
            ffResult.addCompleteNode(node);
        }

        ffProcService.updateProcStatus(procId, executor, ffHelper.getUserName(executor), new Date(), FfService.PROC_STATUS_COMPLETE);
        ffResult.addSuspendProc(loadProc(procId));

        return ffResult;
    }

    @Override
    @FfOperation(procId = "${procId}", taskId = "${taskId}", operator = "${executor}")
    public FfResult terminateProc(String procId, String taskId, String executor) {
        FfResult ffResult = new FfResult();

        List<Task> taskList = createTaskQuery().setProcId(procId).setTaskStatus(FfService.TASK_STATUS_ACTIVE).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_TERMINATE);
            task.setTaskEndUser(executor);
            task.setTaskEndUserName(ffHelper.getUserName(executor));
            task.setTaskEndDate(COMPLETE_DATE_);
            task.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
            ffResult.addTerminateTask(task);
        }
        List<Node> nodeList = createNodeQuery().setProcId(procId).setNodeStatus(FfService.NODE_STATUS_ACTIVE).queryForObjectList();
        for (Node node : nodeList) {
            Date COMPLETE_DATE_ = new Date();
            ffNodeService.updateNodeStatus(node.getNodeId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.NODE_STATUS_TERMINATE);
            node.setNodeEndUser(executor);
            node.setNodeEndUserName(ffHelper.getUserName(executor));
            node.setNodeEndDate(COMPLETE_DATE_);
            node.setNodeStatus(FfService.NODE_STATUS_TERMINATE);
            ffResult.addTerminateNode(node);
        }

        ffProcService.updateProcStatus(procId, executor, ffHelper.getUserName(executor), new Date(), FfService.PROC_STATUS_TERMINATE);
        ffResult.addTerminateProc(loadProc(procId));

        return ffResult;
    }

    @Override
    @FfOperation(procId = "${procId}", operator = "${executor}")
    public FfResult deleteProc(String procId, String executor) {
        FfResult ffResult = new FfResult();

        List<Node> nodeList = createChildNodeQuery().setNodeId(procId).setRecursive(true).setIncludeSelf(true).queryForObjectList();
        Node node;
        for (int i = nodeList.size() - 1; i >= 0; i--) {
            node = nodeList.get(i);

            List<Task> taskList = createTaskQuery().setNodeId(node.getNodeId()).queryForObjectList();
            for (Task task : taskList) {
                ffTaskService.deleteTask(task.getTaskId());
                ffResult.addDeleteTask(task);
            }

            ffNodeVarService.deleteNodeVarByNodeId(node.getNodeId());
            ffNodeService.deleteNode(node.getNodeId());
            ffResult.addDeleteNode(node);
        }

        Proc proc = loadProc(procId);
        ffProcService.deleteProc(procId);
        ffResult.addDeleteProc(proc);

        return ffResult;
    }

    @Override
    public void cleanProc(String procId) {
        ffProcService.cleanProc(procId);
    }

    @Override
    public Node loadNode(String nodeId) {
        return createNodeQuery().setNodeId(nodeId).queryForObject();
    }

    @Override
    @FfOperation(operator = "${executor}")
    public FfResult insertNode(NodeDef nodeDef, Node branchNode, String previousNodeIds, CandidateList candidateList, String executor) {
        if (candidateList == null) {
            candidateList = new CandidateList();
        }

        OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_INSERT).setExecutor(executor);
        return getNodeHandler(nodeDef.getNodeType()).insertNodeByNodeDef(nodeDef, branchNode, previousNodeIds, candidateList, operationContext);
    }

    @Override
    @FfOperation(nodeId = "${nodeId}", operator = "${executor}")
    public FfResult activateNode(String nodeId, CandidateList candidateList, String executor) {
        if (candidateList == null) {
            candidateList = new CandidateList();
        }

        Node node = loadNode(nodeId);
        OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_ACTIVATE).setInitialNode(node).setExecutor(executor);
        return getNodeHandler(node.getNodeType()).activateNode(node, null, candidateList, operationContext);
    }

    @Override
    @FfOperation(nodeId = "${nodeId}", operator = "${executor}")
    public FfResult completeNode(String nodeId, Map<String, Object> branchNodeVar, CandidateList candidateList, String executor) {
        FfResult ffResult = new FfResult();

        if (candidateList == null) {
            candidateList = new CandidateList();
        }

        Node node = loadNode(nodeId);
        updateNodeVar(node.getParentNodeId(), branchNodeVar);// 更新当前分支节点变量
        List<Task> taskList = createTaskQuery().setNodeId(nodeId).setTaskStatus(FfService.TASK_STATUS_ACTIVE).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_COMPLETE);
            task.setTaskEndUser(executor);
            task.setTaskEndUserName(ffHelper.getUserName(executor));
            task.setTaskEndDate(COMPLETE_DATE_);
            task.setTaskStatus(FfService.TASK_STATUS_COMPLETE);
            ffResult.addCompleteTask(task);
        }

        OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_COMPLETE).setInitialNode(node).setInitialNodeVarMap(branchNodeVar).setExecutor(executor);
        ffResult.addAll(getNodeHandler(node.getNodeType()).completeNode(node, null, candidateList, operationContext));

        return ffResult;
    }

    @Override
    @FfOperation(nodeId = "${nodeId}", operator = "${executor}")
    public FfResult terminateNode(String nodeId, CandidateList candidateList, String executor) {
        FfResult ffResult = new FfResult();

        if (candidateList == null) {
            candidateList = new CandidateList();
        }

        Node node = loadNode(nodeId);
        List<Task> taskList = createTaskQuery().setNodeId(nodeId).setNodeStatus(FfService.NODE_STATUS_ACTIVE).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_TERMINATE);
            task.setTaskEndUser(executor);
            task.setTaskEndUserName(ffHelper.getUserName(executor));
            task.setTaskEndDate(COMPLETE_DATE_);
            task.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
            ffResult.addTerminateTask(task);
        }

        OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_TERMINATE).setInitialNode(node).setExecutor(executor);
        ffResult.addAll(getNodeHandler(node.getNodeType()).completeNode(node, null, candidateList, operationContext));

        List<Node> completeNodeList = ffResult.getCompleteNodeList();
        for (Node completeNode : completeNodeList) {
            if (completeNode.getNodeId().equals(nodeId)) {
                ffNodeService.updateNodeStatus(nodeId, FfService.NODE_STATUS_TERMINATE);

                completeNodeList.remove(completeNode);
                completeNode.setNodeStatus(FfService.NODE_STATUS_TERMINATE);
                ffResult.addTerminateNode(completeNode);
                break;
            }
        }

        return ffResult;
    }

    @FfOperation(nodeId = "${nodeId}", operator = "${executor}")
    public FfResult deleteNode(String nodeId, String executor) {
        FfResult ffResult = new FfResult();

        List<Node> childNodeList = createChildNodeQuery().setNodeId(nodeId).setRecursive(true).setIncludeSelf(true).queryForObjectList();
        Node childNode;
        for (int i = childNodeList.size() - 1; i >= 0; i--) {
            childNode = childNodeList.get(i);
            List<Task> taskList = createTaskQuery().setNodeId(childNode.getNodeId()).queryForObjectList();
            for (Task task : taskList) {
                ffTaskService.deleteTask(task.getTaskId());
                ffResult.addDeleteTask(task);
            }

            ffNodeVarService.deleteNodeVarByNodeId(childNode.getNodeId());
            ffNodeService.deleteNode(childNode.getNodeId());
            ffResult.addDeleteNode(childNode);
        }

        return ffResult;
    }

    @Override
    public String getSubProcPath(Node node) {
        // 递归查询上级节点，获取所有节点类型为NODE_TYPE_BRANCH和NODE_TYPE_SUB_PROC的节点。
        List<Node> parentNodeList = createParentNodeQuery().setNodeId(node.getNodeId()).setRecursive(true).setIncludeSelf(true).setNodeTypeList(Arrays.asList(FfService.NODE_TYPE_SUB_PROC, FfService.NODE_TYPE_BRANCH)).setDataScope(FfService.DATA_SCOPE_PROC_DEF).queryForObjectList();
        List<String> subProcPathList = new ArrayList<>();
        for (Node parentNode : parentNodeList) {
            if (parentNode.getNodeType().equals(FfService.NODE_TYPE_SUB_PROC)) {
                subProcPathList.add(0, parentNode.getNodeCode());
            }
            if (parentNode.getNodeType().equals(FfService.NODE_TYPE_BRANCH)) {
                subProcPathList.add(0, parentNode.getSubProcDefCode());
            }
        }

        subProcPathList.remove(0);// 去掉根上级节点。主流程的BRANCH节点
        if (subProcPathList.size() % 2 != 0) {// 如果节点个数为奇数，去掉最后一个子流程类型的节点
            subProcPathList.remove(subProcPathList.size() - 1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < subProcPathList.size(); i++) {
            stringBuilder.append(subProcPathList.get(i));
            if (i < subProcPathList.size() - 1) {
                if (i % 2 == 0) {
                    stringBuilder.append(":");
                }
                else {
                    stringBuilder.append(".");
                }
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public Task loadTask(String taskId) {
        return createTaskQuery().setTaskId(taskId).queryForObject();
    }

    @Override
    @FfOperation(procId = "${task.procId}", nodeId = "${task.nodeId}", taskId = "${task.taskId}", operator = "${executor}")
    public FfResult insertTask(Task task, String executor) {
        FfResult ffResult = new FfResult();

        if (ffTaskService.insertTask(task.getTaskId(), task.getNodeId(), task.getPreviousTaskId(), task.getTaskType(), task.getAssignee(), task.getAssigneeName(), task.getAction(), task.getDueDate(), task.getClaim(), task.getForwardable(), task.getPriority(), task.getForwardStatus(), task.getTaskEndUser(), task.getTaskEndUserName(), task.getTaskEndDate(), task.getNextCandidate(), task.getTaskStatus(), task.getCreationDate()) == 1) {
            ffResult.addCreateTask(task);
        }

        return ffResult;
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult updateTaskAssignee(String taskId, String assignee, String assigneeName, String executor) {
        FfResult ffResult = new FfResult();

        ffTaskService.updateTaskAssignee(taskId, assignee, assigneeName);

        return ffResult;
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult suspendTask(String taskId, String executor) {
        FfResult ffResult = new FfResult();

        ffTaskService.updateTaskStatus(taskId, executor, ffHelper.getUserName(executor), new Date(), FfService.TASK_STATUS_SUSPEND);
        ffResult.addSuspendTask(loadTask(taskId));

        return ffResult;
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult activateTask(String taskId, String executor) {
        FfResult ffResult = new FfResult();

        ffTaskService.updateTaskStatus(taskId, FfService.TASK_STATUS_ACTIVE);
        ffResult.addActivateTask(loadTask(taskId));

        return ffResult;
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult terminateTask(String taskId, String executor) {
        FfResult ffResult = new FfResult();

        ffTaskService.updateTaskStatus(taskId, executor, ffHelper.getUserName(executor), new Date(), FfService.TASK_STATUS_TERMINATE);
        ffResult.addTerminateTask(loadTask(taskId));

        return ffResult;
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult deleteTask(String taskId, String executor) {
        FfResult ffResult = new FfResult();

        ffResult.addDeleteTask(loadTask(taskId));
        ffTaskService.deleteTask(taskId);

        return ffResult;
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult completeTask(String taskId, Map<String, Object> branchNodeVar, CandidateList candidateList, String executor) {
        FfResult ffResult = new FfResult();

        if (candidateList == null) {
            candidateList = new CandidateList();
        }

        Task task = loadTask(taskId);
        if (task == null) {// 如果任务不存在，抛异常
            throw new RuntimeException("errors.taskNotExist");
        }
        if (!task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {// 任务状态不为待办中不能完成
            throw new RuntimeException("errors.taskHasBeenExecuted");
        }
        if (task.getClaim().equals(FfService.BOOLEAN_TRUE)) {// 需要认领不能完成
            throw new RuntimeException("errors.claimRequired");
        }

        if (task.getAssignee().equals(executor) || isDelegator(task.getAssignee(), executor)) {
            updateNodeVar(task.getParentNodeId(), branchNodeVar);// 更新当前分支节点变量

            String executorName = ffHelper.getUserName(executor);
            Date completeDate = new Date();
            ffTaskService.updateTaskStatus(taskId, executor, executorName, completeDate, candidateList.toJson(), FfService.TASK_STATUS_COMPLETE);// 完成任务
            task.setTaskEndUser(executor);
            task.setTaskEndUserName(executorName);
            task.setTaskEndDate(completeDate);
            task.setNextCandidate(candidateList.toJson());
            task.setTaskStatus(FfService.TASK_STATUS_COMPLETE);
            ffResult.addCompleteTask(task);

            if (task.getTaskType().equals(FfService.TASK_TYPE_FORWARD_TASK)) {
                if (createTaskQuery().setPreviousTaskId(task.getPreviousTaskId()).setTaskStatus(FfService.TASK_STATUS_ACTIVE).setDataScope(FfService.DATA_SCOPE_TASK).count() == 0) {
                    ffTaskService.updateTaskForwardStatus(task.getPreviousTaskId(), FfService.FORWARD_STATUS_FORWARDING_PROCESSING_COMPLETED);
                    ffResult.addForwardingProcessingCompletedTask(loadTask(task.getPreviousTaskId()));
                }

                return ffResult;
            }

            Node node = loadNode(task.getNodeId());
            String exclusive = node.getExclusive();
            String waitingForCompleteNode = node.getWaitingForCompleteNode();
            if ((exclusive != null && exclusive.contains("${")) || (waitingForCompleteNode != null && waitingForCompleteNode.contains("${"))) {// JUEL解析
                // 设置JUEL解析环境
                Map<String, Object> nodeVarMap = createNodeVarQuery().setNodeId(node.getParentNodeId()).setRecursive(true).queryForMap();// 获取节点变量
                nodeVarMap.putAll(getInternalServiceMap());
                nodeVarMap.putAll(getExternalServiceMap());
                nodeVarMap.put("proc", loadProc(node.getProcId()));
                nodeVarMap.put("branch", loadNode(node.getParentNodeId()));
                nodeVarMap.put("node", node);
                if (branchNodeVar != null) {
                    nodeVarMap.putAll(branchNodeVar);
                }
                nodeVarMap.putAll(ffNodeService.getTaskStatistic(node.getNodeId()));// 获取节点任务完成信息
                ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
                SimpleContext simpleContext = new SimpleContext();
                for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
                    simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
                }
                // JUEL解析
                ValueExpression expression;
                expression = expressionFactory.createValueExpression(simpleContext, exclusive, String.class);
                exclusive = (String) expression.getValue(simpleContext);
                expression = expressionFactory.createValueExpression(simpleContext, waitingForCompleteNode, String.class);
                waitingForCompleteNode = (String) expression.getValue(simpleContext);
            }
            if (FfService.BOOLEAN_TRUE.equals(exclusive)) {// 排他处理
                List<Task> remainActiveTaskList = createTaskQuery().setNodeId(node.getNodeId()).setTaskStatus(FfService.TASK_STATUS_ACTIVE).queryForObjectList();
                for (Task remainActiveTask : remainActiveTaskList) {
                    ffTaskService.updateTaskStatus(remainActiveTask.getTaskId(), executor, executorName, completeDate, FfService.TASK_STATUS_TERMINATE);
                    remainActiveTask.setTaskEndUser(executor);
                    remainActiveTask.setTaskEndUserName(executorName);
                    remainActiveTask.setTaskEndDate(completeDate);
                    remainActiveTask.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
                    ffResult.addTerminateTask(remainActiveTask);
                }
            }

            if (!FfService.BOOLEAN_TRUE.equals(waitingForCompleteNode)) {// 自动完成节点
                OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_COMPLETE).setInitialTask(task).setInitialNodeVarMap(branchNodeVar).setExecutor(executor);
                ffResult.addAll(getNodeHandler(node.getNodeType()).completeNode(node, null, candidateList, operationContext));
            }

            return ffResult;
        }
        else {
            throw new RuntimeException("errrors.unauthorizedOperator");
        }
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult completeTaskToNode(String taskId, String subProcPath, String nodeCode, Map<String, Object> branchNodeVar, CandidateList candidateList, String executor) {
        FfResult ffResult = new FfResult();

        if (candidateList == null) {
            candidateList = new CandidateList();
        }

        Task task = loadTask(taskId);
        if (task == null) {// 如果任务不存在，抛异常
            throw new RuntimeException("errors.taskNotExist");
        }
        if (!task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {// 任务状态不为待办中不能完成
            throw new RuntimeException("errors.taskHasBeenExecuted");
        }
        if (task.getClaim().equals(FfService.BOOLEAN_TRUE)) {// 需要认领不能完成
            throw new RuntimeException("errors.claimRequired");
        }

        if (task.getAssignee().equals(executor) || isDelegator(task.getAssignee(), executor)) {
            updateNodeVar(task.getParentNodeId(), branchNodeVar);// 更新当前分支节点变量

            String executorName = ffHelper.getUserName(executor);
            Date completeDate = new Date();
            ffTaskService.updateTaskStatus(taskId, executor, executorName, completeDate, candidateList.toJson(), FfService.TASK_STATUS_COMPLETE);// 完成任务
            task.setTaskEndUser(executor);
            task.setTaskEndUserName(executorName);
            task.setTaskEndDate(completeDate);
            task.setNextCandidate(candidateList.toJson());
            task.setTaskStatus(FfService.TASK_STATUS_COMPLETE);
            ffResult.addCompleteTask(task);

            Node node = loadNode(task.getNodeId());
            String exclusive = node.getExclusive();
            if (exclusive != null && exclusive.contains("${")) {// JUEL解析
                // 设置JUEL解析环境
                Map<String, Object> nodeVarMap = createNodeVarQuery().setNodeId(node.getParentNodeId()).setRecursive(true).queryForMap();// 获取节点变量
                nodeVarMap.putAll(getInternalServiceMap());
                nodeVarMap.putAll(getExternalServiceMap());
                nodeVarMap.put("proc", loadProc(node.getProcId()));
                nodeVarMap.put("branch", loadNode(node.getParentNodeId()));
                nodeVarMap.put("node", node);
                if (branchNodeVar != null) {
                    nodeVarMap.putAll(branchNodeVar);
                }
                nodeVarMap.putAll(ffNodeService.getTaskStatistic(node.getNodeId()));// 获取节点任务完成信息
                ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
                SimpleContext simpleContext = new SimpleContext();
                for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
                    simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
                }
                // JUEL解析
                ValueExpression expression = expressionFactory.createValueExpression(simpleContext, exclusive, String.class);
                exclusive = (String) expression.getValue(simpleContext);
            }
            if (FfService.BOOLEAN_TRUE.equals(exclusive)) {// 排他处理
                List<Task> remainActiveTaskList = createTaskQuery().setNodeId(node.getNodeId()).setTaskStatus(FfService.TASK_STATUS_ACTIVE).queryForObjectList();
                for (Task remainActiveTask : remainActiveTaskList) {
                    ffTaskService.updateTaskStatus(remainActiveTask.getTaskId(), executor, executorName, completeDate, FfService.TASK_STATUS_TERMINATE);
                    remainActiveTask.setTaskEndUser(executor);
                    remainActiveTask.setTaskEndUserName(executorName);
                    remainActiveTask.setTaskEndDate(completeDate);
                    remainActiveTask.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
                    ffResult.addTerminateTask(remainActiveTask);
                }
            }

            String nodeEndUserName = ffHelper.getUserName(executor);
            Date nodeEndDate = new Date();
            ffNodeService.updateNodeStatus(node.getNodeId(), executor, nodeEndUserName, nodeEndDate, FfService.NODE_STATUS_COMPLETE);// 完成节点
            node.setNodeEndUser(executor);
            node.setNodeEndUserName(nodeEndUserName);
            node.setNodeEndDate(nodeEndDate);
            node.setNodeStatus(FfService.NODE_STATUS_COMPLETE);
            ffResult.addCompleteNode(node);

            OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_COMPLETE).setInitialTask(task).setInitialNodeVarMap(branchNodeVar).setExecutor(executor);
            ffResult.addAll(jumpToNode(node, subProcPath, nodeCode, candidateList, operationContext));

            return ffResult;
        }
        else {
            throw new RuntimeException("errrors.unauthorizedOperator");
        }
    }

    /**
     * 任意跳转到其它节点。
     */
    private FfResult jumpToNode(Node currentNode, String subProcPath, String nodeCode, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();

        List<Node> parentNodeList = createParentNodeQuery().setNodeId(currentNode.getNodeId()).setRecursive(true).setIncludeSelf(false).queryForObjectList();
        Node branchNode = parentNodeList.get(parentNodeList.size() - 1);
        ProcDef subProcDef = loadProcDef(loadProc(currentNode.getProcId()).getProcDefId());
        String branchNodeId;
        int index = parentNodeList.size() - 2;
        boolean reserve = true;
        if (StringUtils.isNotEmpty(subProcPath)) {// 建立子流程路径上所有节点
            String[] splits = subProcPath.split("\\.");
            String[] path;

            for (String split : splits) {
                path = split.split(":");

                // 创建上级节点
                NodeDef nodeDef = subProcDef.getNodeDef(path[0]);
                List<? extends NodeDef> parentNodeDefList = nodeDef.getParentNodeDefList();// 获取节点的所有上级节点，包括自身，并新增这些节点
                String nodeId;
                for (NodeDef parentNodeDef : parentNodeDefList) {
                    if (reserve) {// 任务节点和指定跳转节点的共用上级节点不做处理，余下下级节点需要完成。
                        if (index >= 0 && parentNodeDef.getNodeCode().equals(parentNodeList.get(index).getNodeCode())) {
                            branchNode = parentNodeList.get(index);
                            index--;
                            continue;
                        }
                        else {
                            ffResult.addAll(completeNode(parentNodeList, index, operationContext));
                            reserve = false;
                        }
                    }

                    nodeId = OdUtils.getUuid();
                    ffNodeService.insertNode(nodeId, branchNode.getNodeId(), currentNode.getProcId(), null, null, subProcDef.getProcDefId(), null, parentNodeDef.getNodeType(), parentNodeDef.getNodeCode(), parentNodeDef.getNodeName(), parentNodeDef.getParentNodeCode(), parentNodeDef.getCandidateAssignee(), parentNodeDef.getCompleteExpression(), parentNodeDef.getCompleteReturn(), parentNodeDef.getExclusive(), parentNodeDef.getWaitingForCompleteNode(), parentNodeDef.getAutoCompleteSameAssignee(), parentNodeDef.getAutoCompleteEmptyAssignee(), parentNodeDef.getInform(), parentNodeDef.getAssignee(), parentNodeDef.getAction(), parentNodeDef.getDueDate(), parentNodeDef.getClaim(), parentNodeDef.getForwardable(), parentNodeDef.getPriority(), null, null, null, null, null, null, null,
                            FfService.NODE_STATUS_ACTIVE, new Date());
                    branchNode = createNodeQuery().setNodeId(nodeId).queryForObject();
                    ffResult.addCreateNode(branchNode);
                }

                // 新增子流程分支节点
                subProcDef = loadProcDefByCode(path[1]);
                branchNodeId = OdUtils.getUuid();
                ffNodeService.insertNode(branchNodeId, branchNode.getNodeId(), currentNode.getProcId(), null, null, subProcDef.getProcDefId(), null, FfService.NODE_TYPE_BRANCH, null, subProcDef.getProcDefName(), null, null, FfService.DEFAULT_COMPLETE_EXPRESSION_, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, null, null, null, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, "5", null, null, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
                branchNode = createNodeQuery().setNodeId(branchNodeId).queryForObject();
                ffResult.addCreateNode(branchNode);
            }
        }

        NodeDef nodeDef = subProcDef.getNodeDef(nodeCode);
        List<? extends NodeDef> parentNodeDefList = nodeDef.getParentNodeDefList();// 获取节点的所有上级节点，包括自身，并新增这些节点
        NodeDef parentNodeDef;
        String nodeId;
        for (int j = 0; j < parentNodeDefList.size(); j++) {
            parentNodeDef = parentNodeDefList.get(j);

            if (reserve) {// 任务节点和指定跳转节点的共用上级节点不做处理，余下下级节点需要完成。
                if (index >= 0 && parentNodeDef.getNodeCode().equals(parentNodeList.get(index).getNodeCode())) {
                    branchNode = parentNodeList.get(index);
                    index--;
                    continue;
                }
                else {
                    ffResult.addAll(completeNode(parentNodeList, index, operationContext));
                    reserve = false;
                }
            }

            if (j < parentNodeDefList.size() - 1) {
                nodeId = OdUtils.getUuid();
                ffNodeService.insertNode(nodeId, branchNode.getNodeId(), currentNode.getProcId(), null, null, subProcDef.getProcDefId(), null, parentNodeDef.getNodeType(), parentNodeDef.getNodeCode(), parentNodeDef.getNodeName(), parentNodeDef.getParentNodeCode(), parentNodeDef.getCandidateAssignee(), parentNodeDef.getCompleteExpression(), parentNodeDef.getCompleteReturn(), parentNodeDef.getExclusive(), parentNodeDef.getWaitingForCompleteNode(), parentNodeDef.getAutoCompleteSameAssignee(), parentNodeDef.getAutoCompleteEmptyAssignee(), parentNodeDef.getInform(), parentNodeDef.getAssignee(), parentNodeDef.getAction(), parentNodeDef.getDueDate(), parentNodeDef.getClaim(), parentNodeDef.getForwardable(), parentNodeDef.getPriority(), null, null, null, null, null, null, null,
                        FfService.NODE_STATUS_ACTIVE, new Date());
                branchNode = createNodeQuery().setNodeId(nodeId).queryForObject();
                ffResult.addCreateNode(branchNode);
            }
        }

        nodeDef = subProcDef.getNodeDef(nodeCode);
        ffResult.addAll(getNodeHandler(nodeDef.getNodeType()).insertNodeByNodeDef(nodeDef, branchNode, null, candidateList, operationContext));

        return ffResult;
    }

    private FfResult completeNode(List<Node> parentNodeList, int index, OperationContext operationContext) {
        FfResult ffResult = new FfResult();

        String nodeStatus = FfService.NODE_STATUS_COMPLETE;
        if (FfService.OPERATION_REJECT.equals(operationContext.getInitialOperation())) {
            nodeStatus = FfService.NODE_STATUS_TERMINATE;
        }

        Node node;
        for (int i = index; i >= 0; i--) {
            node = parentNodeList.get(i);
            String nodeEndUserName = ffHelper.getUserName(operationContext.getExecutor());
            Date nodeEndDate = new Date();
            ffNodeService.updateNodeStatus(node.getNodeId(), operationContext.getExecutor(), nodeEndUserName, nodeEndDate, nodeStatus);// 完成节点
            node.setNodeEndUser(operationContext.getExecutor());
            node.setNodeEndUserName(nodeEndUserName);
            node.setNodeEndDate(nodeEndDate);
            node.setNodeStatus(nodeStatus);
            ffResult.addCompleteNode(node);
        }

        return ffResult;
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult claimTask(String taskId, String executor) {
        FfResult ffResult = new FfResult();

        Task task = loadTask(taskId);
        if (!FfService.BOOLEAN_TRUE.equals(task.getClaim())) {// 认领状态不为true不能认领
            throw new RuntimeException("notClaimable");
        }
        if (!task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {// 任务状态不为待办中不能认领
            throw new RuntimeException("errors.taskHasBeenExecuted");
        }

        ffTaskService.updateTaskClaim(taskId);

        String executorName = ffHelper.getUserName(executor);
        Date completeDate = new Date();
        List<Task> activeTaskList = createTaskQuery().setNodeId(task.getNodeId()).setTaskStatus(FfService.TASK_STATUS_ACTIVE).queryForObjectList();
        for (Task activeTask : activeTaskList) {
            if (activeTask.getTaskId().equals(taskId)) {
                continue;
            }

            ffTaskService.updateTaskStatus(activeTask.getTaskId(), executor, executorName, completeDate, FfService.TASK_STATUS_TERMINATE);
            activeTask.setTaskEndUser(executor);
            activeTask.setTaskEndUserName(executorName);
            activeTask.setTaskEndDate(completeDate);
            activeTask.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
            ffResult.addTerminateTask(activeTask);
        }

        return ffResult;
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult forwardTask(String taskId, List<String> assigneeList, String action, Date dueDate, String claim, String forwardable, Integer priority, String executor) {
        FfResult ffResult = new FfResult();

        Task task = loadTask(taskId);
        if (task == null) {// 如果任务不存在，抛异常
            throw new RuntimeException("errors.taskNotExist");
        }
        if (!createProcQuery().setProcId(task.getProcId()).queryForObject().getProcStatus().equals(FfService.PROC_STATUS_ACTIVE)) {// 如果流程不为运行中状态，抛异常
            throw new RuntimeException("errors.procNotActive");
        }
        if (!task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {// 任务状态不为待办中不能转发
            throw new RuntimeException("errors.taskHasBeenExecuted");
        }

        if (task.getAssignee().equals(executor) || isDelegator(task.getAssignee(), executor)) {
            Node node = loadNode(task.getNodeId());
            Node branchNode = loadNode(node.getParentNodeId());
            Proc proc = loadProc(node.getProcId());
            // 设置JUEL解析环境
            Map<String, Object> nodeVarMap = createNodeVarQuery().setNodeId(node.getParentNodeId()).setRecursive(true).queryForMap();// 获取节点变量
            nodeVarMap.putAll(getInternalServiceMap());
            nodeVarMap.putAll(getExternalServiceMap());
            nodeVarMap.put("proc", proc);
            nodeVarMap.put("branch", branchNode);
            nodeVarMap.put("node", node);
            ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
            SimpleContext simpleContext = new SimpleContext();
            for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
                simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
            }
            // JUEL解析
            ValueExpression expression;
            // 计算办理人
            for (String assignee : assigneeList) {
                Task forwardTask = new Task();
                forwardTask.setTaskId(OdUtils.getUuid());
                forwardTask.setNodeId(task.getNodeId());
                forwardTask.setPreviousTaskId(taskId);
                forwardTask.setTaskType(FfService.TASK_TYPE_FORWARD_TASK);
                forwardTask.setAssignee(assignee);
                forwardTask.setAssigneeName(ffHelper.getUserName(assignee));
                forwardTask.setAction(action);
                forwardTask.setDueDate(dueDate);
                forwardTask.setClaim(claim);
                forwardTask.setForwardable(forwardable);
                forwardTask.setPriority(priority);
                forwardTask.setForwardStatus(FfService.FORWARD_STATUS_NOT_FORWARDED);
                forwardTask.setTaskStatus(FfService.TASK_STATUS_ACTIVE);
                forwardTask.setCreationDate(new Date());
                simpleContext.setVariable("task", expressionFactory.createValueExpression(forwardTask, Object.class));
                if (StringUtils.isNotEmpty(action)) {// 解析JUEL,获取业务系统操作
                    expression = expressionFactory.createValueExpression(simpleContext, action, String.class);
                    forwardTask.setAction((String) expression.getValue(simpleContext));
                }

                insertTask(forwardTask, executor);
                ffResult.addCreateTask(loadTask(forwardTask.getTaskId()));
            }

            ffTaskService.updateTaskForwardStatus(taskId, FfService.FORWARD_STATUS_FORWARDING);
            task.setForwardStatus(FfService.FORWARD_STATUS_FORWARDING);
            ffResult.addForwardingTask(task);

            return ffResult;
        }
        else {
            throw new RuntimeException("errrors.unauthorizedOperator");
        }
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult rejectTask(String taskId, CandidateList candidateList, String executor) {
        FfResult ffResult = new FfResult();

        if (candidateList == null) {
            candidateList = new CandidateList();
        }

        Task task = loadTask(taskId);
        if (task == null) {// 如果任务不存在，抛异常
            throw new RuntimeException("errors.taskNotExist");
        }
        if (!createProcQuery().setProcId(task.getProcId()).queryForObject().getProcStatus().equals(FfService.PROC_STATUS_ACTIVE)) {// 如果流程不为运行中状态，抛异常
            throw new RuntimeException("errors.procNotActive");
        }
        if (!task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {// 任务状态不为待办中不能完成
            throw new RuntimeException("errors.taskHasBeenExecuted");
        }

        if (task.getAssignee().equals(executor) || isDelegator(task.getAssignee(), executor)) {
            Node node = createNodeQuery().setNodeId(task.getNodeId()).queryForObject();
            List<Task> taskList = createTaskQuery().setNodeId(task.getNodeId()).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE, FfService.TASK_STATUS_SUSPEND, FfService.TASK_STATUS_COMPLETE)).queryForObjectList();

            String exclusive = node.getExclusive();
            if (exclusive != null && exclusive.contains("${")) {// JUEL解析
                // 设置JUEL解析环境
                Map<String, Object> nodeVarMap = createNodeVarQuery().setNodeId(node.getParentNodeId()).setRecursive(true).queryForMap();// 获取节点变量
                nodeVarMap.putAll(getInternalServiceMap());
                nodeVarMap.putAll(getExternalServiceMap());
                nodeVarMap.put("proc", loadProc(node.getProcId()));
                nodeVarMap.put("branch", loadNode(node.getParentNodeId()));
                nodeVarMap.put("node", node);
                nodeVarMap.putAll(ffNodeService.getTaskStatistic(node.getNodeId()));// 获取节点任务完成信息
                ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
                SimpleContext simpleContext = new SimpleContext();
                for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
                    simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
                }
                // JUEL解析
                ValueExpression expression = expressionFactory.createValueExpression(simpleContext, exclusive, String.class);
                exclusive = (String) expression.getValue(simpleContext);
            }
            if (!FfService.BOOLEAN_TRUE.equals(exclusive) && taskList.size() > 1) {// 非独占任务不能驳回
                throw new RuntimeException("errors.cannotRejectInParallel");
            }
            for (Task _task : taskList) {// 设置其它任务状态为异常完成
                if (_task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {
                    String executorName = ffHelper.getUserName(executor);
                    Date completeDate = new Date();
                    ffTaskService.updateTaskStatus(_task.getTaskId(), executor, executorName, completeDate, FfService.TASK_STATUS_TERMINATE);
                    _task.setTaskEndUser(executor);
                    _task.setTaskEndUserName(executorName);
                    _task.setTaskEndDate(completeDate);
                    _task.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
                    ffResult.addTerminateTask(_task);
                }
            }

            OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_REJECT).setInitialTask(task).setExecutor(executor);
            ffResult.addAll(getNodeHandler(node.getNodeType()).rejectNode(node, candidateList, operationContext));// 驳回节点

            return ffResult;
        }
        else {
            throw new RuntimeException("errrors.unauthorizedOperator");
        }
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult rejectTaskToNode(String taskId, String subProcPath, String nodeCode, CandidateList candidateList, String executor) {
        FfResult ffResult = new FfResult();

        if (candidateList == null) {
            candidateList = new CandidateList();
        }

        Task task = loadTask(taskId);
        if (task == null) {// 如果任务不存在，抛异常
            throw new RuntimeException("errors.taskNotExist");
        }
        if (!createProcQuery().setProcId(task.getProcId()).queryForObject().getProcStatus().equals(FfService.PROC_STATUS_ACTIVE)) {// 如果流程不为激活状态，抛异常
            throw new RuntimeException("errors.procNotActive");
        }
        if (!task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {// 任务状态不为激活不能驳回
            throw new RuntimeException("errors.taskHasBeenExecuted");
        }

        if (task.getAssignee().equals(executor) || isDelegator(task.getAssignee(), executor)) {
            Node node = createNodeQuery().setNodeId(task.getNodeId()).queryForObject();
            List<Task> taskList = createTaskQuery().setNodeId(task.getNodeId()).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE, FfService.TASK_STATUS_SUSPEND, FfService.TASK_STATUS_COMPLETE)).queryForObjectList();

            String exclusive = node.getExclusive();
            if (exclusive != null && exclusive.contains("${")) {// JUEL解析
                // 设置JUEL解析环境
                Map<String, Object> nodeVarMap = createNodeVarQuery().setNodeId(node.getParentNodeId()).setRecursive(true).queryForMap();// 获取节点变量
                nodeVarMap.putAll(getInternalServiceMap());
                nodeVarMap.putAll(getExternalServiceMap());
                nodeVarMap.put("proc", loadProc(node.getProcId()));
                nodeVarMap.put("branch", loadNode(node.getParentNodeId()));
                nodeVarMap.put("node", node);
                nodeVarMap.putAll(ffNodeService.getTaskStatistic(node.getNodeId()));// 获取节点任务完成信息
                ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
                SimpleContext simpleContext = new SimpleContext();
                for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
                    simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
                }
                // JUEL解析
                ValueExpression expression = expressionFactory.createValueExpression(simpleContext, exclusive, String.class);
                exclusive = (String) expression.getValue(simpleContext);
            }
            if (!FfService.BOOLEAN_TRUE.equals(exclusive) && taskList.size() > 1) {// 非独占任务不能驳回
                throw new RuntimeException("errors.cannotRejectInParallel");
            }
            for (Task _task : taskList) {// 设置其它任务状态为异常完成
                if (_task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {
                    String executorName = ffHelper.getUserName(executor);
                    Date completeDate = new Date();
                    ffTaskService.updateTaskStatus(_task.getTaskId(), executor, executorName, completeDate, FfService.TASK_STATUS_TERMINATE);
                    _task.setTaskEndUser(executor);
                    _task.setTaskEndUserName(executorName);
                    _task.setTaskEndDate(completeDate);
                    _task.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
                    ffResult.addTerminateTask(_task);
                }
            }

            String nodeEndUserName = ffHelper.getUserName(executor);
            Date nodeEndDate = new Date();
            ffNodeService.updateNodeStatus(node.getNodeId(), executor, nodeEndUserName, nodeEndDate, FfService.NODE_STATUS_TERMINATE);// 完成节点
            node.setNodeEndUser(executor);
            node.setNodeEndUserName(nodeEndUserName);
            node.setNodeEndDate(nodeEndDate);
            node.setNodeStatus(FfService.NODE_STATUS_TERMINATE);
            ffResult.addTerminateNode(node);

            OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_REJECT).setInitialTask(task).setExecutor(executor);
            ffResult.addAll(jumpToNode(node, subProcPath, nodeCode, candidateList, operationContext));

            return ffResult;
        }
        else {
            throw new RuntimeException("errrors.unauthorizedOperator");
        }
    }

    @Override
    @FfOperation(nodeId = "${nodeId}", operator = "${executor}")
    public FfResult appendCandidate(String nodeId, CandidateList candidateList, String executor) {
        FfResult ffResult = new FfResult();

        Node node = loadNode(nodeId);
        if (node == null) {
            throw new RuntimeException("errors.nodeNotExist");
        }

        if (candidateList == null) {
            return ffResult;
        }

        OperationContext operationContext = new OperationContext().setInitialOperation(FfService.OPERATION_INSERT).setInitialNode(node).setExecutor(executor);
        ffResult.addAll(getNodeHandler(node.getNodeType()).appendCandidate(node, candidateList, operationContext));

        return ffResult;
    }

    @Override
    public NodeVar loadNodeVar(String nodeVarId) {
        return createNodeVarQuery().setNodeVarId(nodeVarId).queryForObject();
    }

    @Override
    public boolean insertNodeVar(NodeVar nodeVar) {
        return ffNodeVarService.insertNodeVar(nodeVar.getNodeVarId(), nodeVar.getNodeId(), nodeVar.getVarType(), nodeVar.getVarName(), nodeVar.getValue(), nodeVar.getObj(), nodeVar.getCreationDate()) == 1;
    }

    @Override
    public boolean updateNodeVar(NodeVar nodeVar) {
        return ffNodeVarService.updateNodeVar(nodeVar.getNodeVarId(), nodeVar.getVarType(), nodeVar.getVarName(), nodeVar.getValue(), nodeVar.getObj()) == 1;
    }

    @Override
    public List<String> updateNodeVar(String nodeId, Map<String, Object> nodeVarMap) {
        return ffNodeVarService.updateNodeVar(nodeId, nodeVarMap);
    }

    @Override
    public boolean deleteNodeVar(String nodeVarId) {
        return ffNodeVarService.deleteNodeVar(nodeVarId) == 1;
    }

    @Override
    public boolean deleteNodeVarByNodeId(String nodeId) {
        return ffNodeVarService.deleteNodeVarByNodeId(nodeId) == 1;
    }

    @Override
    public Delegate loadDelegate(String delegateId) {
        return createDelegateQuery().setDelegateId(delegateId).queryForObject();
    }

    @Override
    public boolean insertDelegate(String delegateId, String assignee, String assigneeName, String delegator, String delegatorName, Date startDate, Date endDate) {
        return ffDelegateService.insertDelegate(delegateId, assignee, assigneeName, delegator, delegatorName, startDate, endDate) == 1;
    }

    @Override
    public boolean updateDelegate(String delegateId, String assignee, String assigneeName, String delegator, String delegatorName, Date startDate, Date endDate) {
        return ffDelegateService.updateDelegate(delegateId, assignee, assigneeName, delegator, delegatorName, startDate, endDate) == 1;
    }

    @Override
    public boolean deleteDelegate(String delegateId) {
        return ffDelegateService.deleteDelegate(delegateId) == 1;
    }

    @Override
    public boolean isDelegator(String assignee, String delegator) {
        List<Delegate> delegateList = createDelegateQuery().setDelegator(delegator).queryForObjectList();

        for (Delegate delegate : delegateList) {
            if (delegate.getAssignee() != null && !delegate.getAssignee().equals(assignee)) {
                continue;
            }
            if (delegate.getStartDate() != null && delegate.getStartDate().getTime() > System.currentTimeMillis()) {
                continue;
            }
            if (delegate.getEndDate() != null && delegate.getEndDate().getTime() < System.currentTimeMillis()) {
                continue;
            }

            return true;
        }

        return false;
    }

    @Override
    public Operation loadOperation(String operationId) {
        return createOperationQuery().setOperationId(operationId).queryForObject();
    }

    @Override
    public List<ProcOp> selectProcOp(String operationId) {
        List<ProcOp> procOpList = new ArrayList<>();

        List<Map<String, Object>> resultList = ffOperationService.selectProcOp(operationId);
        for (Map<String, Object> result : resultList) {
            procOpList.add(new ProcOp(result));
        }

        return procOpList;
    }

    @Override
    public List<NodeOp> selectNodeOp(String operationId) {
        List<NodeOp> nodeOpList = new ArrayList<>();

        List<Map<String, Object>> resultList = ffOperationService.selectNodeOp(operationId);
        for (Map<String, Object> result : resultList) {
            nodeOpList.add(new NodeOp(result));
        }

        return nodeOpList;
    }

    @Override
    public List<TaskOp> selectTaskOp(String operationId) {
        List<TaskOp> taskOpList = new ArrayList<>();

        List<Map<String, Object>> resultList = ffOperationService.selectTaskOp(operationId);
        for (Map<String, Object> result : resultList) {
            taskOpList.add(new TaskOp(result));
        }

        return taskOpList;
    }

    @Override
    public List<NodeVarOp> selectNodeVarOp(String operationId) {
        List<NodeVarOp> nodeVarOpList = new ArrayList<>();

        List<Map<String, Object>> resultList = ffOperationService.selectNodeVarOp(operationId);
        for (Map<String, Object> result : resultList) {
            nodeVarOpList.add(new NodeVarOp(result));
        }

        return nodeVarOpList;
    }

    @Override
    public FfResult undo(String operationId) {
        return ffOperationService.undo(operationId);
    }

    @Override
    public List<FfUser> getAssigneeList(String assigneeString) {
        List<FfUser> assigneeList = new ArrayList<>();

        if (StringUtils.isNotEmpty(assigneeString)) {
            String[] assignees = assigneeString.split(",");
            for (String assignee : assignees) {
                FfUser ffUser = new FfUser();
                ffUser.setId(assignee);
                ffUser.setUserName(ffHelper.getUserName(assignee));
                assigneeList.add(ffUser);
            }
        }

        return assigneeList;
    }
}