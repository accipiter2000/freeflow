package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opendynamic.ff.service.FfService;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

/**
 * 节点定义。
 */
public class NodeDef implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String nodeType = FfService.NODE_TYPE_TASK;// 节点类型。
    protected String nodeCode;// 节点编码。
    protected String nodeName;// 节点名称。
    protected String parentNodeCode;// 上级节点编码。
    protected String candidateAssignee;// 候选人。
    protected String candidateSubProcDef;// 候选子流程定义。
    protected String completeExpression = FfService.DEFAULT_COMPLETE_EXPRESSION_;// 完成表达式。
    protected String completeReturn = FfService.BOOLEAN_FALSE;// 完成后返回前一个节点。
    protected String exclusive = FfService.BOOLEAN_FALSE;// 排他。
    protected String waitingForCompleteNode = FfService.BOOLEAN_FALSE;// 等待完成节点。
    protected String autoCompleteSameAssignee = FfService.BOOLEAN_FALSE;// 自动完成相同办理人任务。
    protected String autoCompleteEmptyAssignee = FfService.BOOLEAN_FALSE;// 自动完成没有办理人节点。
    protected String inform = FfService.BOOLEAN_FALSE;// 通知。
    protected String assignee; // 办理人。
    protected String assignSubProcDef; // 办理子流程定义。
    protected String action; // 业务行为。
    protected String dueDate;// 截止日期。
    protected String claim;// 认领。
    protected String forwardable = FfService.BOOLEAN_FALSE;// 可转发。
    protected String priority = "5"; // 优先级。

    protected Shape shape;// 形状。

    @JsonIgnore
    protected transient ProcDef procDef;// 所属流程定义。
    @JsonIgnore
    protected transient NodeDef parentNodeDef;// 上级节点定义。
    @JsonIgnore
    protected transient List<NodeDef> childNodeDefList = new ArrayList<>();// 下级节点定义列表（阶段节点）。
    @JsonIgnore
    protected transient List<NodeDef> startChildNodeDefList = new ArrayList<>();// 开始节点定义列表（阶段节点）。
    @JsonIgnore
    protected transient List<FlowDef> incomingFlowDefList = new ArrayList<>();// 入口流转定义列表。
    @JsonIgnore
    protected transient List<FlowDef> outgoingFlowDefList = new ArrayList<>();// 出口流转定义列表。

    /**
     * 初始化。
     *
     * @param procDef
     *        流程定义。
     */
    public void init(ProcDef procDef) {
        this.procDef = procDef;

        int count = 0;
        if (StringUtils.isNotEmpty(candidateAssignee)) {
            count++;
        }
        if (StringUtils.isNotEmpty(candidateSubProcDef)) {
            count++;
        }
        if (StringUtils.isNotEmpty(assignee)) {
            count++;
        }
        if (StringUtils.isNotEmpty(assignSubProcDef)) {
            count++;
        }
        if (count > 1) {
            throw new RuntimeException("errors.candidateCannotCoexistWithAssignee");
        }

        shape.init(this);
    }

    // 初始化节点关系
    public void initNodeDefRelation() {
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
                if (childNodeDef.getIncomingFlowDefList().isEmpty()) {
                    startChildNodeDefList.add(childNodeDef);
                }
            }
        }
    }

    /**
     * 获取节点类型。
     * 
     * @return 节点类型。
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 获取节点编码。
     * 
     * @return 节点编码。
     */
    public String getNodeCode() {
        return nodeCode;
    }

    /**
     * 获取节点名称。
     * 
     * @return 节点名称。
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 获取上级节点编码。
     * 
     * @return 上级节点编码。
     */
    public String getParentNodeCode() {
        return parentNodeCode;
    }

    /**
     * 获取候选人。
     * 
     * @return 候选人。
     */
    public String getCandidateAssignee() {
        return candidateAssignee;
    }

    /**
     * 获取候选子流程定义。
     * 
     * @return 候选子流程定义。
     */
    public String getCandidateSubProcDef() {
        return candidateSubProcDef;
    }

    /**
     * 获取完成表达式。
     * 
     * @return 完成表达式。
     */
    public String getCompleteExpression() {
        return completeExpression;
    }

    /**
     * 获取完成后返回前一个节点。
     * 
     * @return 完成后返回前一个节点。
     */
    public String getCompleteReturn() {
        return completeReturn;
    }

    /**
     * 获取排他。
     * 
     * @return 排他。
     */
    public String getExclusive() {
        return exclusive;
    }

    /**
     * 获取等待完成节点。
     * 
     * @return 等待完成节点。
     */
    public String getWaitingForCompleteNode() {
        return waitingForCompleteNode;
    }

    /**
     * 获取自动完成相同办理人任务。
     * 
     * @return 自动完成相同办理人任务。
     */
    public String getAutoCompleteSameAssignee() {
        return autoCompleteSameAssignee;
    }

    /**
     * 获取自动完成没有办理人节点。
     * 
     * @return 自动完成没有办理人节点。
     */
    public String getAutoCompleteEmptyAssignee() {
        return autoCompleteEmptyAssignee;
    }

    /**
     * 获取通知。
     * 
     * @return 通知。
     */
    public String getInform() {
        return inform;
    }

    /**
     * 获取办理人。
     * 
     * @return 办理人。
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * 获取办理子流程定义。
     * 
     * @return 办理子流程定义。
     */
    public String getAssignSubProcDef() {
        return assignSubProcDef;
    }

    /**
     * 获取业务行为。
     * 
     * @return 业务行为。
     */
    public String getAction() {
        return action;
    }

    /**
     * 获取截止日期。
     * 
     * @return 截止日期。
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * 获取认领。
     * 
     * @return 认领。
     */
    public String getClaim() {
        return claim;
    }

    /**
     * 获取可转发。
     * 
     * @return 可转发。
     */
    public String getForwardable() {
        return forwardable;
    }

    /**
     * 获取优先级。
     * 
     * @return 优先级。
     */
    public String getPriority() {
        return priority;
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

    /**
     * 获取上级节点定义。
     * 
     * @return 上级节点定义。
     */
    public NodeDef getParentNodeDef() {
        return parentNodeDef;
    }

    /**
     * 级联获取上级节点定义列表。包括自己。
     *
     * @return 上级节点定义列表。包括自己。
     */
    @JsonIgnore
    public List<? extends NodeDef> getParentNodeDefList() {
        List<NodeDef> parentNodeDefList = new ArrayList<>();

        NodeDef parentNodeDef = getParentNodeDef();
        if (parentNodeDef != null) {
            parentNodeDefList.addAll(parentNodeDef.getParentNodeDefList());
        }

        parentNodeDefList.add(this);

        return parentNodeDefList;
    }

    /**
     * 获取下级节点定义列表。
     *
     * @return 下级节点定义列表。
     */
    @JsonIgnore
    public List<? extends NodeDef> getChildNodeDefList() {
        return new ArrayList<>(this.childNodeDefList);
    }

    /**
     * 获取起始下级节点定义列表。
     *
     * @return 起始下级节点定义列表。
     */
    @JsonIgnore
    public List<? extends NodeDef> getStartChildNodeDefList() {
        return new ArrayList<>(this.startChildNodeDefList);
    }

    /**
     * 获取下一个节点定义列表。
     *
     * @param nodeVarMap
     *        节点变量。
     * @return 节点定义列表。
     */
    public List<? extends NodeDef> getNextNodeDefList(Map<String, Object> nodeVarMap) {
        List<NodeDef> nextNodeDefList = new ArrayList<>();

        // 设置JUEL解析环境
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
                // JUEL解析
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
     * @return 流转定义列表。
     */
    @JsonIgnore
    public List<? extends FlowDef> getIncomingFlowDefList() {
        return new ArrayList<>(this.incomingFlowDefList);
    }

    /**
     * 获取出口流转定义列表。
     *
     * @return 流转定义列表。
     */
    @JsonIgnore
    public List<? extends FlowDef> getOutgoingFlowDefList() {
        return new ArrayList<>(this.outgoingFlowDefList);
    }

    /**
     * 添加入口流转定义。
     *
     * @param flowDef
     *        入口流转定义。
     */
    public void addIncomingFlowDef(FlowDef flowDef) {
        incomingFlowDefList.add(flowDef);
    }

    /**
     * 添加出口流转定义。
     *
     * @param flowDef
     *        出口流转定义。
     */
    public void addOutgoingFlowDef(FlowDef flowDef) {
        outgoingFlowDefList.add(flowDef);
    }
}