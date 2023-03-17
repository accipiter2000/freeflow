package com.opendynamic.ff.service.impl.oracle;

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
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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
import com.opendynamic.ff.vo.Delegate;
import com.opendynamic.ff.vo.DiamondShape;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.FfUser;
import com.opendynamic.ff.vo.FlowDef;
import com.opendynamic.ff.vo.LineShape;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.NodeVar;
import com.opendynamic.ff.vo.NoteDef;
import com.opendynamic.ff.vo.Operation;
import com.opendynamic.ff.vo.OvalShape;
import com.opendynamic.ff.vo.Proc;
import com.opendynamic.ff.vo.ProcDef;
import com.opendynamic.ff.vo.ProcVarDef;
import com.opendynamic.ff.vo.RectangleShape;
import com.opendynamic.ff.vo.RunningNodeDef;
import com.opendynamic.ff.vo.RunningProcDef;
import com.opendynamic.ff.vo.Shape;
import com.opendynamic.ff.vo.Task;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
import sun.misc.BASE64Encoder;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfServiceImpl implements FfService, ApplicationContextAware {
    private ApplicationContext applicationContext;

    private static List<ProcDef> procDefList;
    private static Queue<ProcDef> adjustProcDefList = new LinkedList<>();
    private static Map<String, NodeHandler> nodeHandlerMap;
    private static Map<String, Object> internalServiceMap;
    private static Map<String, Object> externalServiceMap;
    private static ExpressionFactory expressionFactory;

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

        List<Map<String, Object>> procDefList = ffProcDefService.selectProcDef(null, null, null, null, null, 1, -1);
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

        Properties properties = new Properties();// 初始化JUEL工厂
        properties.put("javax.el.cacheSize", "1000");
        expressionFactory = new ExpressionFactoryImpl(properties);
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
    public ExpressionFactory getExpressionFactory() {
        return expressionFactory;
    }

    @Override
    public SimpleContext getSimpleContext(Map<String, Object> objectMap) {
        SimpleContext simpleContext = new SimpleContext();

        for (Map.Entry<String, Object> entry : internalServiceMap.entrySet()) {
            simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
        }
        for (Map.Entry<String, Object> entry : externalServiceMap.entrySet()) {
            simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
        }
        if (objectMap != null) {
            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
            }
        }

        return simpleContext;
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
    public List<ProcDef> selectProcDef(String procDefId, String procDefCode, String procDefName, String procDefCat, List<String> procDefStatusList, Integer page, Integer limit) {
        List<ProcDef> subProcDefList = selectProcDef(procDefId, procDefCode, procDefName, procDefCat, procDefStatusList);

        if (page != null && limit != null && limit > 0) {// 分页
            int start = (page - 1) * limit;
            int end = page * limit;
            if (end > subProcDefList.size()) {
                end = subProcDefList.size();
            }
            subProcDefList = subProcDefList.subList(start, end);
        }

        return subProcDefList;
    }

    @Override
    public int countProcDef(String procDefId, String procDefCode, String procDefName, String procDefCat, List<String> procDefStatusList) {
        return selectProcDef(procDefId, procDefCode, procDefName, procDefCat, procDefStatusList).size();
    }

    private List<ProcDef> selectProcDef(String procDefId, String procDefCode, String procDefName, String procDefCat, List<String> procDefStatusList) {
        if (StringUtils.isEmpty(procDefId) && StringUtils.isEmpty(procDefCode) && StringUtils.isEmpty(procDefName) && StringUtils.isEmpty(procDefCat) && (procDefStatusList == null || procDefStatusList.size() == 0)) {
            return procDefList;
        }

        List<ProcDef> subProcDefList = new ArrayList<>();
        subProcDefList.addAll(procDefList);
        ProcDef procDef;
        if (StringUtils.isNotEmpty(procDefId)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefId.equals(procDef.getProcDefId())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(procDefCode)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefCode.equals(procDef.getProcDefCode())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(procDefName)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDef.getProcDefName().contains(procDefName)) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(procDefName)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDef.getProcDefName().startsWith(procDefName)) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (procDefStatusList != null && procDefStatusList.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefStatusList.contains(procDef.getProcDefStatus())) {
                    subProcDefList.remove(procDef);
                }
            }
        }

        return subProcDefList;
    }

    @Override
    public List<ProcDef> selectProcDefByIdList(List<String> procDefIdList) {
        List<ProcDef> subProcDefList = new ArrayList<>();

        for (String procDefId : procDefIdList) {
            for (ProcDef procDef : procDefList) {
                if (procDef.getProcDefId().equals(procDefId)) {
                    subProcDefList.add(procDef);
                    continue;
                }
            }
        }

        return subProcDefList;
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

        ffProcDefService.insertProcDef(procDefId, _procDef.getProcDefCode(), _procDef.getProcDefName(), _procDef.getProcDefCat(), procDef, procDefDiagramFile, procDefDiagramFileName, procDefDiagramFileLength, _procDef.getProcDefDiagramWidth(), _procDef.getProcDefDiagramHeight(), _procDef.getMemo(), FfService.PROC_DEF_STATUS_ACTIVE, new Date(), new Date(), operatorId, operatorName);

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
        if (ffAdjustProcDefService.deleteAdjustProcDef(adjustProcDefId, updateDate, operatorId, operatorName) == 1) {
            return true;
        }

        return false;
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
    public Proc loadProc(String procId) {
        return new Proc(ffProcService.loadProc(procId));
    }

    @Override
    public ProcQuery createProcQuery() {
        return new ProcQuery(ffProcService);
    }

    public InvolvedProcQuery createInvolvedProcQuery() {
        return new InvolvedProcQuery(ffProcService);
    }

    @Override
    public List<Proc> selectProcByIdList(List<String> procIdList) {
        List<Map<String, Object>> result = ffProcService.selectProcByIdList(procIdList);
        List<Proc> procList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            procList.add(new Proc(result.get(i)));
        }

        return procList;
    }

    @Override
    public boolean updateProcBizInfo(String procId, String bizId, String bizType, String bizCode, String bizName) {
        if (ffProcService.updateProcBizInfo(procId, bizId, bizType, bizCode, bizName) == 1) {
            return true;
        }

        return false;
    }

    @Override
    @FfOperation(procId = "${procId}", taskId = "${taskId}", operator = "${executor}")
    public FfResult suspendProc(String procId, String taskId, String executor) {
        FfResult ffResult = new FfResult();

        List<Task> taskList = createTaskQuery().setProcId(procId).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE)).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_SUSPEND);
            task.setExecutor(executor);
            task.setExecutorName(ffHelper.getUserName(executor));
            task.setCompleteDate(COMPLETE_DATE_);
            task.setTaskStatus(FfService.TASK_STATUS_SUSPEND);
            ffResult.addSuspendTask(task);
        }
        List<Node> nodeList = createNodeQuery().setProcId(procId).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE)).queryForObjectList();
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

        List<Task> taskList = createTaskQuery().setProcId(procId).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_SUSPEND)).queryForObjectList();
        for (Task task : taskList) {
            ffTaskService.updateTaskStatus(task.getTaskId(), FfService.TASK_STATUS_ACTIVE);
            task.setTaskStatus(FfService.TASK_STATUS_ACTIVE);
            ffResult.addActivateTask(task);
        }
        List<Node> nodeList = createNodeQuery().setProcId(procId).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_SUSPEND)).queryForObjectList();
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

        List<Task> taskList = createTaskQuery().setProcId(procId).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE)).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_COMPLETE);
            task.setExecutor(executor);
            task.setExecutorName(ffHelper.getUserName(executor));
            task.setCompleteDate(COMPLETE_DATE_);
            task.setTaskStatus(FfService.TASK_STATUS_COMPLETE);
            ffResult.addCompleteTask(task);
        }
        List<Node> nodeList = createNodeQuery().setProcId(procId).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE)).queryForObjectList();
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

        List<Task> taskList = createTaskQuery().setProcId(procId).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE)).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_TERMINATE);
            task.setExecutor(executor);
            task.setExecutorName(ffHelper.getUserName(executor));
            task.setCompleteDate(COMPLETE_DATE_);
            task.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
            ffResult.addTerminateTask(task);
        }
        List<Node> nodeList = createNodeQuery().setProcId(procId).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE)).queryForObjectList();
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
    public Node loadNode(String nodeId) {
        return new Node(ffNodeService.loadNode(nodeId));
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
    public List<Node> selectNodeByIdList(List<String> nodeIdList) {
        List<Map<String, Object>> result = ffNodeService.selectNodeByIdList(nodeIdList);
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            nodeList.add(new Node(result.get(i)));
        }

        return nodeList;
    }

    @Override
    @FfOperation(operator = "${executor}")
    public FfResult insertNode(NodeDef nodeDef, Node branchNode, String previousNodeIds, String executor) {
        return getNodeHandler(nodeDef.getNodeType()).insertNodeByNodeDef(nodeDef, branchNode, previousNodeIds, FfService.OPERATION_INSERT, executor);
    }

    @Override
    @FfOperation(nodeId = "${nodeId}", operator = "${executor}")
    public FfResult activateNode(String nodeId, String executor) {
        Node node = loadNode(nodeId);
        return getNodeHandler(node.getNodeType()).activateNode(node, null, FfService.OPERATION_ACTIVATE, executor);
    }

    @Override
    @FfOperation(nodeId = "${nodeId}", operator = "${executor}")
    public FfResult completeNode(String nodeId, String executor) {
        FfResult ffResult = new FfResult();

        Node node = loadNode(nodeId);
        List<Task> taskList = createTaskQuery().setNodeId(nodeId).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE)).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_COMPLETE);
            task.setExecutor(executor);
            task.setExecutorName(ffHelper.getUserName(executor));
            task.setNodeEndDate(COMPLETE_DATE_);
            task.setNodeStatus(FfService.TASK_STATUS_COMPLETE);
            ffResult.addCompleteTask(task);
        }

        ffResult.addAll(getNodeHandler(node.getNodeType()).completeNode(node, null, FfService.OPERATION_COMPLETE, executor));

        return ffResult;
    }

    @Override
    @FfOperation(nodeId = "${nodeId}", operator = "${executor}")
    public FfResult terminateNode(String nodeId, String executor) {
        FfResult ffResult = new FfResult();

        Node node = loadNode(nodeId);
        List<Task> taskList = createTaskQuery().setNodeId(nodeId).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE)).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_TERMINATE);
            task.setExecutor(executor);
            task.setExecutorName(ffHelper.getUserName(executor));
            task.setNodeEndDate(COMPLETE_DATE_);
            task.setNodeStatus(FfService.TASK_STATUS_TERMINATE);
            ffResult.addTerminateTask(task);
        }

        ffResult.addAll(getNodeHandler(node.getNodeType()).completeNode(node, null, FfService.OPERATION_TERMINATE, executor));

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

    @Override
    @FfOperation(nodeId = "${subProcNodeId}", operator = "${executor}")
    public FfResult insertSubProc(String subProcNodeId, String procDefCode, String executor) {
        FfResult ffResult = new FfResult();

        // 为子流程新增分支
        Node subProcNode = loadNode(subProcNodeId);
        ProcDef subProcDef = loadProcDefByCode(procDefCode);
        String subProcBranchNodeId = OdUtils.getUuid();
        ProcDef procDef = loadProcDefByCode(subProcDef.getProcDefCode());// 获取子流程定义对应的流程定义
        ffNodeService.insertNode(subProcBranchNodeId, subProcNode.getNodeId(), subProcNode.getProcId(), subProcNode.getPreviousNodeIds(), null, procDef.getProcDefId(), null, FfService.NODE_TYPE_BRANCH, null, subProcDef.getProcDefName(), null, null, null, null, null, FfService.DEFAULT_COMPLETE_EXPRESSION_, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, 5, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
        Node subProcBranchNode = loadNode(subProcBranchNodeId);
        ffResult.addCreateNode(subProcBranchNode);

        updateNodeVar(subProcBranchNodeId, subProcDef.getProcVarDefMap());// 更新子流程节点变量

        List<? extends NodeDef> startNodeDefList = procDef.getStartNodeDefList();
        for (NodeDef startNodeDef : startNodeDefList) {
            ffResult.addAll(getNodeHandler(startNodeDef.getNodeType()).insertNodeByNodeDef(startNodeDef, subProcBranchNode, subProcNode.getPreviousNodeIds(), FfService.OPERATION_COMPLETE, executor));
        }

        return ffResult;
    }

    @Override
    @FfOperation(nodeId = "${subProcBranchId}", operator = "${executor}")
    public FfResult deleteSubProc(String subProcBranchId, String executor) {
        FfResult ffResult = new FfResult();

        List<Node> childNodeList = createChildNodeQuery().setNodeId(subProcBranchId).setRecursive(true).setIncludeSelf(true).queryForObjectList();
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
    public Task loadTask(String taskId) {
        return new Task(ffTaskService.loadTask(taskId));
    }

    @Override
    public TaskQuery createTaskQuery() {
        return new TaskQuery(ffTaskService);
    }

    @Override
    public List<Task> selectTaskByIdList(List<String> taskIdList) {
        List<Map<String, Object>> result = ffTaskService.selectTaskByIdList(taskIdList);
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            taskList.add(new Task(result.get(i)));
        }

        return taskList;
    }

    @Override
    @FfOperation(procId = "${task.procId}", nodeId = "${task.nodeId}", taskId = "${task.taskId}", operator = "${executor}")
    public FfResult insertTask(Task task, String executor) {
        FfResult ffResult = new FfResult();

        if (ffTaskService.insertTask(task.getTaskId(), task.getNodeId(), task.getPreviousTaskId(), task.getTaskType(), task.getAssignee(), task.getAssigneeName(), task.getExecutor(), task.getExecutorName(), task.getAction(), task.getClaimDate(), task.getDueDate(), task.getCompleteDate(), task.getPriority(), task.getForwardable(), task.getForwardStatus(), task.getTaskStatus(), task.getCreationDate()) == 1) {
            ffResult.addCreateTask(task);
        }

        return ffResult;
    }

    @Override
    @FfOperation(taskId = "${taskId}")
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
    public FfResult completeTask(String taskId, String executor, Map<String, Object> branchNodeVar) {
        FfResult ffResult = new FfResult();

        Task task = loadTask(taskId);
        if (task == null) {// 如果任务不存在，抛异常
            throw new RuntimeException("errors.taskNotExist");
        }
        // if (!createProcQuery().setProcId(task.getProcId()).queryForObject().getProcStatus().equals(FfService.PROC_STATUS_ACTIVE)) {// 如果流程不为运行中状态，抛异常
        // throw new RuntimeException("errors.procNotActive");
        // }
        if (!task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {// 任务状态不为待办中不能完成
            throw new RuntimeException("errors.taskHasBeenExecuted");
        }

        if (task.getAssignee().equals(executor) || isDelegator(task.getAssignee(), executor)) {
            updateNodeVar(task.getParentNodeId(), branchNodeVar);// 更新当前分支节点变量

            String executorName = ffHelper.getUserName(executor);
            Date completeDate = new Date();
            ffTaskService.updateTaskStatus(taskId, executor, executorName, completeDate, FfService.TASK_STATUS_COMPLETE);// 完成任务
            task.setExecutor(executor);
            task.setExecutorName(executorName);
            task.setCompleteDate(completeDate);
            task.setTaskStatus(FfService.TASK_STATUS_COMPLETE);
            ffResult.addCompleteTask(task);

            if (task.getTaskType().equals(FfService.TASK_TYPE_FORWARD_TASK)) {
                if (createTaskQuery().setPreviousTaskId(task.getPreviousTaskId()).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE)).count() == 0) {
                    ffTaskService.updateTaskForwardStatus(task.getPreviousTaskId(), FfService.FORWARD_STATUS_FORWARDING_PROCESSING_COMPLETED);
                    ffResult.addForwardingProcessingCompletedTask(loadTask(task.getPreviousTaskId()));
                }

                return ffResult;
            }

            Node node = loadNode(task.getNodeId());
            if (node.getExclusive().equals(FfService.BOOLEAN_TRUE)) {// 排他处理
                List<Task> remainActiveTaskList = createTaskQuery().setNodeId(node.getNodeId()).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE)).queryForObjectList();
                for (Task remainActiveTask : remainActiveTaskList) {
                    ffTaskService.updateTaskStatus(remainActiveTask.getTaskId(), executor, executorName, completeDate, FfService.TASK_STATUS_TERMINATE);
                    remainActiveTask.setExecutor(executor);
                    remainActiveTask.setExecutorName(executorName);
                    remainActiveTask.setCompleteDate(completeDate);
                    remainActiveTask.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
                    ffResult.addTerminateTask(remainActiveTask);
                }
            }

            ffResult.addAll(getNodeHandler(node.getNodeType()).completeNode(node, null, FfService.OPERATION_COMPLETE, executor));

            return ffResult;
        }
        else {
            throw new RuntimeException("errrors.unauthorizedOperator");
        }
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult claimTask(String taskId, String executor) {
        FfResult ffResult = new FfResult();

        Task task = loadTask(taskId);
        if (task.getExclusive().equals(FfService.BOOLEAN_FALSE)) {
            throw new RuntimeException("notClaimable");
        }

        ffTaskService.updateTaskClaimDate(taskId, new Date());

        String executorName = ffHelper.getUserName(executor);
        Date completeDate = new Date();
        List<Task> activeTaskList = createTaskQuery().setNodeId(task.getNodeId()).setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE)).queryForObjectList();
        for (Task activeTask : activeTaskList) {
            if (activeTask.getTaskId().equals(taskId)) {
                continue;
            }

            ffTaskService.updateTaskStatus(activeTask.getTaskId(), executor, executorName, completeDate, FfService.TASK_STATUS_TERMINATE);
            activeTask.setExecutor(executor);
            activeTask.setExecutorName(executorName);
            activeTask.setCompleteDate(completeDate);
            activeTask.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
            ffResult.addTerminateTask(activeTask);
        }

        return ffResult;
    }

    @Override
    @FfOperation(taskId = "${taskId}", operator = "${executor}")
    public FfResult forwardTask(String taskId, List<String> assigneeList, String action, Date dueDate, Integer priority, boolean forwardable, String executor) {
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
            ExpressionFactory expressionFactory = getExpressionFactory();
            SimpleContext simpleContext = getSimpleContext(createNodeVarQuery().setNodeId(node.getParentNodeId()).setRecursive(true).queryForMap());
            simpleContext.setVariable("proc", expressionFactory.createValueExpression(proc, Object.class));
            simpleContext.setVariable("branch", expressionFactory.createValueExpression(branchNode, Object.class));
            simpleContext.setVariable("node", expressionFactory.createValueExpression(node, Object.class));
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
                forwardTask.setPriority(priority);
                if (forwardable) {
                    forwardTask.setForwardable(FfService.BOOLEAN_TRUE);
                }
                else {
                    forwardTask.setForwardable(FfService.BOOLEAN_FALSE);
                }
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
    public FfResult rejectTask(String taskId, String executor) {
        FfResult ffResult = new FfResult();

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
            if (node.getExclusive().equals(FfService.BOOLEAN_FALSE) && taskList.size() > 1) {// 非独占任务不能驳回
                throw new RuntimeException("errors.cannotRejectInParallel");
            }
            for (Task _task : taskList) {// 设置其它任务状态为异常完成
                if (_task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {
                    String executorName = ffHelper.getUserName(executor);
                    Date completeDate = new Date();
                    ffTaskService.updateTaskStatus(_task.getTaskId(), executor, executorName, completeDate, FfService.TASK_STATUS_TERMINATE);
                    _task.setExecutor(executor);
                    _task.setExecutorName(executorName);
                    _task.setCompleteDate(completeDate);
                    _task.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
                    ffResult.addTerminateTask(_task);
                }
            }

            ffResult.addAll(getNodeHandler(node.getNodeType()).rejectNode(node, FfService.OPERATION_REJECT, executor));// 驳回节点

            return ffResult;
        }
        else {
            throw new RuntimeException("errrors.unauthorizedOperator");
        }
    }

    @Override
    @FfOperation(taskId = "${fromTaskId}", operator = "${executor}")
    public FfResult rejectTaskToNode(String fromTaskId, String toNodeId, String executor) {
        FfResult ffResult = new FfResult();

        Task task = loadTask(fromTaskId);
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
            if (node.getExclusive().equals(FfService.BOOLEAN_FALSE) && taskList.size() > 1) {// 非独占任务不能驳回
                throw new RuntimeException("errors.cannotRejectInParallel");
            }
            for (Task _task : taskList) {// 设置其它任务状态为异常完成
                if (_task.getTaskStatus().equals(FfService.TASK_STATUS_ACTIVE)) {
                    String executorName = ffHelper.getUserName(executor);
                    Date completeDate = new Date();
                    ffTaskService.updateTaskStatus(_task.getTaskId(), executor, executorName, completeDate, FfService.TASK_STATUS_TERMINATE);
                    _task.setExecutor(executor);
                    _task.setExecutorName(executorName);
                    _task.setCompleteDate(completeDate);
                    _task.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
                    ffResult.addTerminateTask(_task);
                }
            }

            ffResult.addAll(activateNode(toNodeId, executor));
            ffResult.addAll(rejectNode(node, executor));

            return ffResult;
        }
        else {
            throw new RuntimeException("errrors.unauthorizedOperator");
        }
    }

    private FfResult rejectNode(Node node, String executor) {
        FfResult ffResult = new FfResult();

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_TERMINATE) || node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {
            return ffResult;
        }
        if (createChildNodeQuery().setNodeId(node.getNodeId()).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE)).count() > 0) {
            return ffResult;
        }

        ffNodeService.updateNodeStatus(node.getNodeId(), FfService.NODE_STATUS_TERMINATE);

        if (node.getParentNodeId() != null) {
            ffResult.addAll(rejectNode(loadNode(node.getParentNodeId()), executor));
        }

        return ffResult;
    }

    @Override
    public NodeVar loadNodeVar(String nodeVarId) {
        return new NodeVar(ffNodeVarService.loadNodeVar(nodeVarId));
    }

    @Override
    public NodeVarQuery createNodeVarQuery() {
        return new NodeVarQuery(ffNodeVarService);
    }

    @Override
    public List<NodeVar> selectNodeVarByIdList(List<String> nodeVarIdList) {
        List<Map<String, Object>> result = ffNodeVarService.selectNodeVarByIdList(nodeVarIdList);
        List<NodeVar> nodeVarList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            nodeVarList.add(new NodeVar(result.get(i)));
        }

        return nodeVarList;
    }

    @Override
    public boolean insertNodeVar(NodeVar nodeVar) {
        if (ffNodeVarService.insertNodeVar(nodeVar.getNodeVarId(), nodeVar.getNodeId(), nodeVar.getVarType(), nodeVar.getVarName(), nodeVar.getValue(), nodeVar.getObj(), nodeVar.getCreationDate()) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean updateNodeVar(NodeVar nodeVar) {
        if (ffNodeVarService.updateNodeVar(nodeVar.getNodeVarId(), nodeVar.getVarType(), nodeVar.getVarName(), nodeVar.getValue(), nodeVar.getObj()) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public List<String> updateNodeVar(String nodeId, Map<String, Object> nodeVarMap) {
        return ffNodeVarService.updateNodeVar(nodeId, nodeVarMap);
    }

    @Override
    public boolean deleteNodeVar(String nodeVarId) {
        if (ffNodeVarService.deleteNodeVar(nodeVarId) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteNodeVarByNodeId(String nodeId) {
        if (ffNodeVarService.deleteNodeVarByNodeId(nodeId) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public Delegate loadDelegate(String delegateId) {
        return new Delegate(ffDelegateService.loadDelegate(delegateId));
    }

    @Override
    public DelegateQuery createDelegateQuery() {
        return new DelegateQuery(ffDelegateService);
    }

    @Override
    public List<Delegate> selectDelegateByIdList(List<String> delegateIdList) {
        List<Map<String, Object>> result = ffDelegateService.selectDelegateByIdList(delegateIdList);
        List<Delegate> delegateList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            delegateList.add(new Delegate(result.get(i)));
        }

        return delegateList;
    }

    @Override
    public boolean insertDelegate(String delegateId, String assignee, String assigneeName, String delegator, String delegatorName, Date startDate, Date endDate) {
        if (ffDelegateService.insertDelegate(delegateId, assignee, assigneeName, delegator, delegatorName, startDate, endDate) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean updateDelegate(String delegateId, String assignee, String assigneeName, String delegator, String delegatorName, Date startDate, Date endDate) {
        if (ffDelegateService.updateDelegate(delegateId, assignee, assigneeName, delegator, delegatorName, startDate, endDate) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteDelegate(String delegateId) {
        if (ffDelegateService.deleteDelegate(delegateId) == 1) {
            return true;
        }

        return false;
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
        return new Operation(ffOperationService.loadOperation(operationId));
    }

    @Override
    public OperationQuery createOperationQuery() {
        return new OperationQuery(ffOperationService);
    }

    @Override
    public List<Operation> selectOperationByIdList(List<String> operationIdList) {
        List<Map<String, Object>> result = ffOperationService.selectOperationByIdList(operationIdList);
        List<Operation> operationList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            operationList.add(new Operation(result.get(i)));
        }

        return operationList;
    }

    @Override
    public FfResult undo(String operationId) {
        return ffOperationService.undo(operationId);
    }

    @Override
    @FfOperation(operator = "${procStartUser}")
    public FfResult startProc(ProcDef procDef, String bizId, String bizType, String bizCode, String bizName, String procStartUser, Map<String, Object> nodeVarMap) {
        return startProc(procDef, bizId, bizType, bizCode, bizName, procStartUser, nodeVarMap, null);
    }

    @Override
    @FfOperation(operator = "${procStartUser}")
    public FfResult startProcByProcDefCode(String procDefCode, String bizId, String bizType, String bizCode, String bizName, String procStartUser, Map<String, Object> nodeVarMap) {
        ProcDef procDef = loadProcDefByCode(procDefCode);
        return startProc(procDef, bizId, bizType, bizCode, bizName, procStartUser, nodeVarMap);
    }

    @Override
    @FfOperation(operator = "${procStartUser}")
    public FfResult startIsolateSubProc(String isolateSubProcNodeId, String bizId, String bizType, String bizCode, String bizName, String procStartUser, Map<String, Object> nodeVarMap) {
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

        ProcDef procDef = loadProcDefByCode(isolateSubProcNode.getIsolateSubProcDefCode());
        ffResult.addAll(startProc(procDef, bizId, bizType, bizCode, bizName, procStartUser, nodeVarMap, isolateSubProcNodeId));

        ffNodeService.updateIsolateSubProcStatus(isolateSubProcNodeId, FfService.PROC_STATUS_ACTIVE);

        return ffResult;
    }

    private FfResult startProc(ProcDef procDef, String bizId, String bizType, String bizCode, String bizName, String procStartUser, Map<String, Object> nodeVarMap, String isolateSubProcNodeId) {
        FfResult ffResult = new FfResult();

        if (procDef == null || procDef.getProcDefStatus().equals(FfService.PROC_DEF_STATUS_DISABLE)) {
            throw new RuntimeException("errors.procDefIsNotActive");
        }

        String procId = OdUtils.getUuid();
        ffProcService.insertProc(procId, procDef.getProcDefId(), null, isolateSubProcNodeId, bizId, bizType, bizCode, bizName, procStartUser, ffHelper.getUserName(procStartUser), new Date(), null, null, null, FfService.PROC_STATUS_ACTIVE, new Date());// 新增主流程
        ffResult.addCreateProc(createProcQuery().setProcId(procId).queryForObject());
        ffNodeService.insertNode(procId, null, procId, null, null, procDef.getProcDefId(), null, FfService.NODE_TYPE_BRANCH, null, procDef.getProcDefName(), null, null, null, null, null, null, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, 5, null, null, null, null, null, FfService.NODE_STATUS_ACTIVE, new Date());
        Node branchNode = createNodeQuery().setNodeId(procId).queryForObject();
        ffResult.addCreateNode(branchNode);

        for (ProcVarDef procVarDef : procDef.getProcVarDefList()) {
            if (!nodeVarMap.containsKey(procVarDef.getVarName())) {
                nodeVarMap.put(procVarDef.getVarName(), procVarDef.getValue());
            }
        }

        updateNodeVar(procId, nodeVarMap);// 更新节点变量

        List<? extends NodeDef> startNodeDefList = procDef.getStartNodeDefList();
        for (NodeDef startNodeDef : startNodeDefList) {
            ffResult.addAll(getNodeHandler(startNodeDef.getNodeType()).insertNodeByNodeDef(startNodeDef, branchNode, null, FfService.OPERATION_INSERT, procStartUser));
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

        BufferedImage image = null;
        Graphics2D g2d = null;
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

        // 设置动态流程图
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        String base64String = new BASE64Encoder().encodeBuffer(baos.toByteArray());
        runningProcDef.setDiagram(base64String);

        return runningProcDef;
    }

    // 递归处理子流程，绘制流程图节点状态，
    private void handleChildNode(Node node, List<Node> childNodeList, Node currentNode, RunningProcDef runningProcDef, ProcDef procDef, boolean drawOptional, Graphics2D g2d) {
        NodeDef nodeDef;
        Pattern outerPattern = Pattern.compile(FfService.CENTER_FORWARD_STEP + " *== *\\d+");
        Pattern innerPattern = Pattern.compile("\\d+");
        int centerForwardStep = 0;
        for (Node childNode : childNodeList) {
            if (childNode.getParentNodeId() != null && childNode.getParentNodeId().equals(node.getNodeId())) {
                if (childNode.getNodeType().equals(FfService.NODE_TYPE_BRANCH)) {// 如果子节点为子流程，递归处理。
                    RunningProcDef childRunningProcDef = getBranchRunningProcDef(childNode.getNodeId(), currentNode, drawOptional);
                    Node parentNode = loadNode(childNode.getParentNodeId());
                    ((RunningNodeDef) runningProcDef.getNodeDef(parentNode.getNodeCode())).addSubProcRunningProcDef(childRunningProcDef);
                }
                else {// 如果子节点不是子流程，绘制状态。
                    if (childNode.getNodeStatus().equals(FfService.NODE_STATUS_ACTIVE) && childNode.getNodeCode() != null) {
                        nodeDef = procDef.getNodeDef(childNode.getNodeCode());
                        nodeDef.getShape().drawActive(g2d);// 绘制活动节点。

                        // 绘制可选节点。
                        if (drawOptional && childNode.getNodeType().equals(FfService.NODE_TYPE_CENTER_FORWARD_TASK)) {
                            Map<String, Object> nodeVarMap = createNodeVarQuery().setNodeId(childNode.getParentNodeId()).queryForMap();
                            if (nodeVarMap.get(FfService.CENTER_FORWARD_STEP) != null) {
                                int currentCenterForwardStep = Integer.parseInt((String) nodeVarMap.get(FfService.CENTER_FORWARD_STEP));

                                Matcher matcher;
                                for (FlowDef flowDef : nodeDef.getOutgoingFlowDefList()) {
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
                                }

                                centerForwardStep++;
                                RunningNodeDef parentNodeDef = (RunningNodeDef) runningProcDef.getNodeDef(node.getNodeCode());
                                List<? extends FlowDef> parentOutgoingFlowDefList = parentNodeDef.getOutgoingFlowDefList();
                                for (FlowDef parentOutgoingFlowDef : parentOutgoingFlowDefList) {
                                    ((RunningNodeDef) runningProcDef.getNodeDef(parentOutgoingFlowDef.getTargetNodeCode())).setCenterForwardStep(centerForwardStep);
                                    procDef.getNodeDef(parentOutgoingFlowDef.getTargetNodeCode()).getShape().drawOptional(g2d, centerForwardStep == currentCenterForwardStep);
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
     * 绘制流程图动态注释
     * 
     * @param g2d
     * @param procDef
     * @param subProcDef
     * @param width
     * @param height
     */
    private void drawDynamicNote(Graphics2D g2d, ProcDef procDef, Map<String, Object> nodeVarMap) {
        SimpleContext simpleContext = getSimpleContext(nodeVarMap);

        BufferedImage noteImage = new BufferedImage(procDef.getProcDefDiagramWidth(), procDef.getProcDefDiagramHeight(), BufferedImage.TYPE_INT_ARGB); // 创建绘制动态注释图用BufferedImage
        Graphics2D noteG2d = noteImage.createGraphics(); // 获取Graphics2D
        noteG2d.setComposite(AlphaComposite.Clear);// 绘制透明背景
        noteG2d.fillRect(0, 0, procDef.getProcDefDiagramWidth(), procDef.getProcDefDiagramHeight());
        noteG2d.setComposite(AlphaComposite.Src);// 准备绘制其它内容，非透明
        noteG2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 抗锯齿

        for (NoteDef noteDef : procDef.getNoteDefList()) {// 绘制动态注释
            if (noteDef.getDynamic().equals(FfService.BOOLEAN_TRUE) && StringUtils.isNotEmpty(noteDef.getNoteName())) {
                ValueExpression expression = getExpressionFactory().createValueExpression(simpleContext, noteDef.getNoteName(), String.class);// 解析注释EL表达式
                noteDef.getShape().draw(noteG2d, (String) expression.getValue(simpleContext));
            }
        }

        noteImage = OdUtils.applyShadow(noteImage, 1, Color.BLACK, 0.2f);// 添加阴影
        g2d.drawImage(noteImage, 0, 0, null);// 绘制动态注释图到流程图
    }

    @Override
    public List<RunningNodeDef> getStartRunningNodeDefList(ProcDef procDef, Map<String, Object> nodeVarMap) {
        return getNextRunningNodeDefList(null, null, procDef, nodeVarMap, nodeVarMap);
    }

    @Override
    public List<RunningNodeDef> getNextRunningNodeDefList(String taskId, Map<String, Object> nodeVarMap) {
        List<RunningNodeDef> nextRunningNodeDefList = new ArrayList<>();

        Task task = loadTask(taskId);
        if (task.getTaskType().equals(FfService.TASK_TYPE_FORWARD_TASK)) {
            return nextRunningNodeDefList;
        }

        Node node = loadNode(task.getNodeId());
        Map<String, Object> fullNodeVarMap = new HashMap<>();
        fullNodeVarMap.put("proc", loadProc(node.getProcId()));
        fullNodeVarMap.put("branch", loadNode(node.getParentNodeId()));
        fullNodeVarMap.put("node", node);
        fullNodeVarMap.putAll(createNodeVarQuery().setNodeId(node.getParentNodeId()).setRecursive(true).queryForMap());
        if (nodeVarMap != null) {
            fullNodeVarMap.putAll(nodeVarMap);
        }
        NodeDef nodeDef = getNodeProcDef(node).getNodeDef((node.getNodeCode()));// 获取当前节点所属节点定义
        nextRunningNodeDefList.addAll(getNextRunningNodeDefList(node, nodeDef, null, fullNodeVarMap, nodeVarMap));

        return nextRunningNodeDefList;
    }

    private List<RunningNodeDef> getNextRunningNodeDefList(Node node, NodeDef nodeDef, ProcDef procDef, Map<String, Object> fullNodeVarMap, Map<String, Object> nodeVarMap) {
        List<RunningNodeDef> nextRunningNodeDefList = new ArrayList<>();

        List<? extends NodeDef> nextNodeDefList;
        if (nodeDef == null) {
            nextNodeDefList = procDef.getStartNodeDefList();// 查找下一个节点定义
        }
        else {
            nextNodeDefList = nodeDef.getNextNodeDefList(fullNodeVarMap);// 查找下一个节点定义
        }
        if (nextNodeDefList.size() > 0) {// 如果有下一个节点定义，添加RunningNodeDef
            for (NodeDef nextNodeDef : nextNodeDefList) {
                if (nextNodeDef.getNodeType().equals(FfService.NODE_TYPE_TASK) || nextNodeDef.getNodeType().equals(FfService.NODE_TYPE_CENTER_FORWARD_TASK)) {// TASK直接添加
                    nextRunningNodeDefList.add(getRunningNodeDef(nextNodeDef, fullNodeVarMap));
                }
                if (nextNodeDef.getNodeType().equals(FfService.NODE_TYPE_SUB_PROC)) {// SUB_PROC直接添加
                    nextRunningNodeDefList.add(getRunningNodeDef(nextNodeDef, fullNodeVarMap));
                }
                if (nextNodeDef.getNodeType().equals(FfService.NODE_TYPE_STAGE)) {// STAGE添加其内部的开始节点定义
                    List<? extends NodeDef> startChildNodeDefList = nextNodeDef.getStartChildNodeDefList();
                    for (NodeDef startChildNodeDef : startChildNodeDefList) {
                        if (startChildNodeDef.getNodeType().equals(FfService.NODE_TYPE_STAGE)) {
                            List<? extends NodeDef> _startChildNodeDefList = startChildNodeDef.getStartChildNodeDefList();
                            for (NodeDef _startChildNodeDef : _startChildNodeDefList) {
                                nextRunningNodeDefList.addAll(getNextRunningNodeDefList(node, _startChildNodeDef, procDef, fullNodeVarMap, nodeVarMap));
                            }
                        }
                        else {
                            nextRunningNodeDefList.add(getRunningNodeDef(startChildNodeDef, fullNodeVarMap));
                        }
                    }
                }
                if (nextNodeDef.getNodeType().equals(FfService.NODE_TYPE_GATEWAY)) {// GATEWAY添加其下一个RunningNodeDef
                    nextRunningNodeDefList.addAll(getNextRunningNodeDefList(node, nextNodeDef, procDef, fullNodeVarMap, nodeVarMap));
                }
            }
        }
        else {// 如果没有下一个节点定义
            if (!node.getCompleteReturn().equals(FfService.BOOLEAN_TRUE)) {
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
                    nextRunningNodeDefList.addAll(getNextRunningNodeDefList(parentTaskNode, parentTaskNodeDef, procDef, fullNodeVarMap, nodeVarMap));
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
    private RunningNodeDef getRunningNodeDef(NodeDef nodeDef, Map<String, Object> nodeVarMap) {
        RunningNodeDef runningNodeDef = new RunningNodeDef(nodeDef, null);

        ExpressionFactory expressionFactory = getExpressionFactory();
        SimpleContext simpleContext = getSimpleContext(null);
        if (nodeVarMap != null) {// 装配节点变量
            for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
                simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
            }
        }

        if (runningNodeDef.getNodeType().equals(FfService.NODE_TYPE_SUB_PROC) && StringUtils.isNotEmpty(runningNodeDef.getAssignSubProcDef())) {
            ValueExpression expression = expressionFactory.createValueExpression(simpleContext, runningNodeDef.getAssignSubProcDef(), Object.class);
            Object object = null;
            try {
                object = expression.getValue(simpleContext);
            }
            catch (Exception e) {
                object = (String) null;
            }
            List<ProcDef> assignSubProcDefList;// 解析后办理子流程定义列表
            if (object instanceof List) {
                assignSubProcDefList = (List<ProcDef>) object;
            }
            else {
                assignSubProcDefList = new ArrayList<>();
                if (StringUtils.isNotEmpty((String) object)) {
                    String[] assignSubProcDefs = ((String) object).split(",");
                    for (int i = 0; i < assignSubProcDefs.length; i++) {
                        assignSubProcDefList.add(loadProcDefByCode(assignSubProcDefs[i]));
                    }
                }
            }

            runningNodeDef.setAssignSubProcDefList(assignSubProcDefList);
        }
        if ((runningNodeDef.getNodeType().equals(FfService.NODE_TYPE_TASK) || runningNodeDef.getNodeType().equals(FfService.NODE_TYPE_CENTER_FORWARD_TASK)) && StringUtils.isNotEmpty(runningNodeDef.getAssignee())) {
            ValueExpression expression = expressionFactory.createValueExpression(simpleContext, runningNodeDef.getAssignee(), Object.class);
            Object object;
            try {
                object = expression.getValue(simpleContext);
            }
            catch (Exception e) {
                object = (String) null;
            }
            List<FfUser> assigneeList;// 解析后办理人列表
            if (object instanceof List) {
                assigneeList = (List<FfUser>) object;
            }
            else {
                assigneeList = new ArrayList<>();
                if (StringUtils.isNotEmpty((String) object)) {
                    String[] assignees = ((String) object).split(",");
                    for (int i = 0; i < assignees.length; i++) {
                        FfUser ffUser = new FfUser();
                        ffUser.setId(assignees[i]);
                        ffUser.setUserName(ffHelper.getUserName(assignees[i]));
                        assigneeList.add(ffUser);
                    }
                }
            }

            runningNodeDef.setAssigneeList(assigneeList);
        }
        if (runningNodeDef.getNodeType().equals(FfService.NODE_TYPE_SUB_PROC) && StringUtils.isNotEmpty(runningNodeDef.getCandidateSubProcDef())) {
            ValueExpression expression = expressionFactory.createValueExpression(simpleContext, runningNodeDef.getCandidateSubProcDef(), Object.class);
            Object object = expression.getValue(simpleContext);// 获取候选子流程定义
            List<ProcDef> candidateSubProcDefList;// 解析后候选子流程定义列表
            if (object instanceof List) {
                candidateSubProcDefList = (List<ProcDef>) object;
            }
            else {
                candidateSubProcDefList = new ArrayList<>();
                if (StringUtils.isNotEmpty((String) object)) {
                    String[] assignSubProcDefs = ((String) object).split(",");
                    for (int i = 0; i < assignSubProcDefs.length; i++) {
                        candidateSubProcDefList.add(loadProcDefByCode(assignSubProcDefs[i]));
                    }
                }
            }

            runningNodeDef.setCandidateSubProcDefList(candidateSubProcDefList);
        }
        if ((runningNodeDef.getNodeType().equals(FfService.NODE_TYPE_TASK) || runningNodeDef.getNodeType().equals(FfService.NODE_TYPE_CENTER_FORWARD_TASK)) && StringUtils.isNotEmpty(runningNodeDef.getCandidate())) {
            ValueExpression expression = expressionFactory.createValueExpression(simpleContext, runningNodeDef.getCandidate(), Object.class);
            Object object = expression.getValue(simpleContext);// 获取候选人
            List<FfUser> candidateList;// 解析后候选人列表
            if (object instanceof List) {
                candidateList = (List<FfUser>) object;
            }
            else {
                candidateList = new ArrayList<>();
                if (StringUtils.isNotEmpty((String) object)) {
                    String[] assignees = ((String) object).split(",");
                    for (int i = 0; i < assignees.length; i++) {
                        FfUser ffUser = new FfUser();
                        ffUser.setId(assignees[i]);
                        ffUser.setUserName(ffHelper.getUserName(assignees[i]));
                        candidateList.add(ffUser);
                    }
                }
            }

            runningNodeDef.setCandidateList(candidateList);
        }

        return runningNodeDef;
    }

    @Override
    public RunningNodeDef getRunningNodeDef(String nodeId) {
        Node node = loadNode(nodeId);
        ProcDef procDef = getNodeProcDef(node); // 获取当前节点所属流程定义
        NodeDef nodeDef = procDef.getNodeDef((node.getNodeCode()));// 获取当前节点所属节点定义
        Map<String, Object> nodeVarMap = createNodeVarQuery().setNodeId(nodeId).setRecursive(true).queryForMap();

        return getRunningNodeDef(nodeDef, nodeVarMap);
    }
}