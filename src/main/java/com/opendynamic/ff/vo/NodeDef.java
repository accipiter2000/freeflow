package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opendynamic.ff.service.FfService;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

public class NodeDef implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String nodeType = FfService.NODE_TYPE_TASK;// 节点类型
    protected String nodeCode;// 节点编码
    protected String nodeName;// 节点名称
    protected String parentNodeCode;// 上级节点编码
    protected String assignee; // 办理人
    protected String candidate;// 候选人
    protected String assignSubProcDef; // 办理子流程定义
    protected String candidateSubProcDef;// 候选子流程定义
    protected String action; // 业务行为
    protected String dueDate;// 截止日期
    protected String completeExpression = FfService.DEFAULT_COMPLETE_EXPRESSION_;// 完成表达式
    protected String completeReturn = FfService.BOOLEAN_FALSE;// 完成后返回前一个节点
    protected String exclusive = FfService.BOOLEAN_FALSE;// 排他
    protected String forwardable = FfService.BOOLEAN_FALSE;// 可转发
    protected String autoCompleteSameAssignee = FfService.BOOLEAN_FALSE;// 自动完成相同办理人任务
    protected String autoCompleteEmptyAssignee = FfService.BOOLEAN_FALSE;// 自动完成没有办理人节点
    protected String inform = FfService.BOOLEAN_FALSE;// 通知
    protected Integer priority = 5; // 优先级

    protected Shape shape;// 形状

    @JsonIgnore
    protected transient ProcDef procDef;// 所属流程定义
    @JsonIgnore
    protected transient NodeDef parentNodeDef;// 上级节点定义
    @JsonIgnore
    protected transient List<NodeDef> childNodeDefList;// 下级节点定义列表
    @JsonIgnore
    protected transient List<NodeDef> startChildNodeDefList;// 下级节点定义列表
    @JsonIgnore
    protected transient List<FlowDef> incomingFlowDefList;// 入口流转定义列表
    @JsonIgnore
    protected transient List<FlowDef> outgoingFlowDefList;// 出口流转定义列表

    /**
     * 初始化。
     * 
     * @param procDef
     */
    public void init(ProcDef procDef) {
        this.procDef = procDef;

        childNodeDefList = new ArrayList<>();
        startChildNodeDefList = new ArrayList<>();
        incomingFlowDefList = new ArrayList<>();
        outgoingFlowDefList = new ArrayList<>();

        shape.init(this);
    }

    /**
     * 初始化阶段。
     */
    public void initStage() {
        parentNodeDef = procDef.getNodeDef(parentNodeCode);
        if (parentNodeCode != null && parentNodeDef == null) {
            throw new RuntimeException("errors.parentNodeDefNotFound");
        }

        if (nodeType.equals(FfService.NODE_TYPE_STAGE)) {
            for (NodeDef nodeDef : procDef.getNodeDefList()) {
                if (nodeCode.equals(nodeDef.getParentNodeCode())) {
                    childNodeDefList.add(nodeDef);
                }
            }

            for (NodeDef childNodeDef : childNodeDefList) {
                if (childNodeDef.getIncomingFlowDefList().size() == 0) {
                    startChildNodeDefList.add(childNodeDef);
                }
            }
        }
    }

    public String getNodeType() {
        return nodeType;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getParentNodeCode() {
        return parentNodeCode;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getAssignSubProcDef() {
        return assignSubProcDef;
    }

    public String getCandidateSubProcDef() {
        return candidateSubProcDef;
    }

    public String getAction() {
        return action;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCompleteExpression() {
        return completeExpression;
    }

    public String getCompleteReturn() {
        return completeReturn;
    }

    public String getExclusive() {
        return exclusive;
    }

    public String getForwardable() {
        return forwardable;
    }

    public String getAutoCompleteSameAssignee() {
        return autoCompleteSameAssignee;
    }

    public String getAutoCompleteEmptyAssignee() {
        return autoCompleteEmptyAssignee;
    }

    public String getInform() {
        return inform;
    }

    public Integer getPriority() {
        return priority;
    }

    public Shape getShape() {
        return shape;
    }

    public ProcDef getProcDef() {
        return procDef;
    }

    public NodeDef getParentNodeDef() {
        return parentNodeDef;
    }

    /**
     * 获取子节点定义列表。
     * 
     * @return
     */
    public List<? extends NodeDef> getChildNodeDefList() {
        List<NodeDef> childNodeDefList = new ArrayList<>();
        childNodeDefList.addAll(this.childNodeDefList);

        return childNodeDefList;
    }

    /**
     * 获取起始子节点定义列表。
     * 
     * @return
     */
    public List<? extends NodeDef> getStartChildNodeDefList() {
        List<NodeDef> startChildNodeDefList = new ArrayList<>();
        startChildNodeDefList.addAll(this.startChildNodeDefList);

        return startChildNodeDefList;
    }

    /**
     * 获取下一个节点定义列表。
     * 
     * @param nodeVarMap
     * @return
     */
    public List<? extends NodeDef> getNextNodeDefList(Map<String, Object> nodeVarMap) {
        List<NodeDef> nextNodeDefList = new ArrayList<>();

        ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
        SimpleContext simpleContext = new SimpleContext();
        if (nodeVarMap != null) {// 装配节点变量
            for (Map.Entry<String, Object> entry : nodeVarMap.entrySet()) {
                simpleContext.setVariable(entry.getKey(), expressionFactory.createValueExpression(entry.getValue(), Object.class));
            }
        }

        List<? extends FlowDef> outgoingFlowDefList = getOutgoingFlowDefList();
        for (FlowDef flowDef : outgoingFlowDefList) {
            if (flowDef.getConditionExpression() == null) {
                nextNodeDefList.add(flowDef.getTargetNodeDef());
            }
            else {
                ValueExpression expression = expressionFactory.createValueExpression(simpleContext, flowDef.getConditionExpression(), Boolean.class);
                if (Boolean.TRUE.equals(expression.getValue(simpleContext))) {
                    nextNodeDefList.add(flowDef.getTargetNodeDef());
                }
            }
        }

        return nextNodeDefList;
    }

    /**
     * 获取入口流转定义列表。
     * 
     * @return
     */
    public List<? extends FlowDef> getIncomingFlowDefList() {
        List<FlowDef> incomingFlowDefList = new ArrayList<>();
        incomingFlowDefList.addAll(this.incomingFlowDefList);

        return incomingFlowDefList;
    }

    /**
     * 获取出口流转定义列表。
     * 
     * @return
     */
    public List<? extends FlowDef> getOutgoingFlowDefList() {
        List<FlowDef> outgoingFlowDefList = new ArrayList<>();
        outgoingFlowDefList.addAll(this.outgoingFlowDefList);

        return outgoingFlowDefList;
    }

    /**
     * 添加入口流转定义。
     * 
     * @param flowDef
     */
    public void addIncomingFlowDef(FlowDef flowDef) {
        incomingFlowDefList.add(flowDef);
    }

    /**
     * 添加出口流转定义列表。
     * 
     * @param flowDef
     */
    public void addOutgoingFlowDef(FlowDef flowDef) {
        outgoingFlowDefList.add(flowDef);
    }
}