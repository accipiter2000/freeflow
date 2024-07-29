package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 运行期节点定义。
 */
public class RunningNodeDef extends NodeDef implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nodeStatus;// 节点状态。
    private Integer centerForwardStep;// 中心转发步骤。
    private Map<String, Object> nodeVarMap;// 节点变量。
    private String subProcPath;// 子流程路径。
    private List<FfUser> candidateAssigneeList;// 解析后候选人列表。
    private List<RunningProcDef> candidateSubProcDefList;// 解析后候选子流程定义列表。
    private List<FfUser> assigneeList;// 解析后办理人列表。
    private List<RunningProcDef> assignSubProcDefList;// 解析后办理子流程定义列表。
    private List<RunningProcDef> subProcRunningProcDefList;// 子流程运行期流程定义列表。

    /**
     * 依据节点定义构建运行期节点定义。
     * 
     * @param nodeDef
     *        节点定义。
     * @param runningProcDef
     *        运行期流程定义。
     */
    public RunningNodeDef(NodeDef nodeDef, RunningProcDef runningProcDef) {
        this.nodeType = nodeDef.getNodeType();
        this.nodeCode = nodeDef.getNodeCode();
        this.nodeName = nodeDef.getNodeName();
        this.parentNodeCode = nodeDef.getParentNodeCode();
        this.candidateAssignee = nodeDef.getCandidateAssignee();
        this.candidateSubProcDef = nodeDef.getCandidateSubProcDef();
        this.completeExpression = nodeDef.getCompleteExpression();
        this.completeReturn = nodeDef.getCompleteReturn();
        this.exclusive = nodeDef.getExclusive();
        this.waitingForCompleteNode = nodeDef.getWaitingForCompleteNode();
        this.autoCompleteSameAssignee = nodeDef.getAutoCompleteSameAssignee();
        this.autoCompleteEmptyAssignee = nodeDef.getAutoCompleteEmptyAssignee();
        this.inform = nodeDef.getInform();
        this.assignee = nodeDef.getAssignee();
        this.assignSubProcDef = nodeDef.getAssignSubProcDef();
        this.action = nodeDef.getAction();
        this.dueDate = nodeDef.getDueDate();
        this.claim = nodeDef.getClaim();
        this.forwardable = nodeDef.getForwardable();
        this.priority = nodeDef.getPriority();

        this.shape = nodeDef.getShape();

        this.procDef = runningProcDef;
    }

    /**
     * 设置节点类型。
     * 
     * @param nodeType
     *        节点类型。
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 设置节点编码。
     * 
     * @param nodeCode
     *        节点编码。
     */
    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    /**
     * 设置节点名称。
     * 
     * @param nodeName
     *        节点名称。
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * 设置上级节点编码。
     * 
     * @param parentNodeCode
     *        上级节点编码。
     */
    public void setParentNodeCode(String parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
    }

    /**
     * 设置候选人。
     * 
     * @param candidateAssignee
     *        候选人。
     */
    public void setCandidateAssignee(String candidateAssignee) {
        this.candidateAssignee = candidateAssignee;
    }

    /**
     * 设置候选子流程定义。
     * 
     * @param candidateSubProcDef
     *        候选子流程定义。
     */
    public void setCandidateSubProcDef(String candidateSubProcDef) {
        this.candidateSubProcDef = candidateSubProcDef;
    }

    /**
     * 设置完成表达式。
     * 
     * @param completeExpression
     *        完成表达式。
     */
    public void setCompleteExpression(String completeExpression) {
        this.completeExpression = completeExpression;
    }

    /**
     * 设置完成后返回前一个节点。
     * 
     * @param completeReturn
     *        完成后返回前一个节点。
     */
    public void setCompleteReturn(String completeReturn) {
        this.completeReturn = completeReturn;
    }

    /**
     * 设置排他。
     * 
     * @param exclusive
     *        排他。
     */
    public void setExclusive(String exclusive) {
        this.exclusive = exclusive;
    }

    /**
     * 设置等待完成节点。
     * 
     * @param waitingForCompleteNode
     *        等待完成节点。
     */
    public void setWaitingForCompleteNode(String waitingForCompleteNode) {
        this.waitingForCompleteNode = waitingForCompleteNode;
    }

    /**
     * 设置自动完成相同办理人任务。
     * 
     * @param autoCompleteSameAssignee
     *        自动完成相同办理人任务。
     */
    public void setAutoCompleteSameAssignee(String autoCompleteSameAssignee) {
        this.autoCompleteSameAssignee = autoCompleteSameAssignee;
    }

    /**
     * 设置自动完成没有办理人节点。
     * 
     * @param autoCompleteEmptyAssignee
     *        自动完成没有办理人节点。
     */
    public void setAutoCompleteEmptyAssignee(String autoCompleteEmptyAssignee) {
        this.autoCompleteEmptyAssignee = autoCompleteEmptyAssignee;
    }

    /**
     * 设置通知。
     * 
     * @param inform
     *        通知。
     */
    public void setInform(String inform) {
        this.inform = inform;
    }

    /**
     * 设置办理人。
     * 
     * @param assignee
     *        办理人。
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * 设置办理子流程定义。
     * 
     * @param assignSubProcDef
     *        办理子流程定义。
     */
    public void setAssignSubProcDef(String assignSubProcDef) {
        this.assignSubProcDef = assignSubProcDef;
    }

    /**
     * 设置业务行为。
     * 
     * @param action
     *        业务行为。
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 设置截止日期。
     * 
     * @param dueDate
     *        截止日期。
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * 设置认领。
     * 
     * @param claim
     *        认领。
     */
    public void setClaim(String claim) {
        this.claim = claim;
    }

    /**
     * 设置可转发。
     * 
     * @param forwardable
     *        可转发。
     */
    public void setForwardable(String forwardable) {
        this.forwardable = forwardable;
    }

    /**
     * 设置优先级。
     * 
     * @param priority
     *        优先级。
     */
    public void setPriority(String priority) {
        this.priority = priority;
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

    @Override
    public List<? extends NodeDef> getChildNodeDefList() {
        return childNodeDefList;
    }

    @Override
    public List<? extends NodeDef> getStartChildNodeDefList() {
        return startChildNodeDefList;
    }

    @Override
    public List<? extends FlowDef> getIncomingFlowDefList() {
        return incomingFlowDefList;
    }

    @Override
    public List<? extends FlowDef> getOutgoingFlowDefList() {
        return outgoingFlowDefList;
    }

    /**
     * 获取节点状态。
     * 
     * @return 节点状态。
     */
    public String getNodeStatus() {
        return nodeStatus;
    }

    /**
     * 设置节点状态。
     * 
     * @param nodeStatus
     *        节点状态。
     */
    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    /**
     * 获取中心转发步骤。
     * 
     * @return 中心转发步骤。
     */
    public Integer getCenterForwardStep() {
        return centerForwardStep;
    }

    /**
     * 设置中心转发步骤。
     * 
     * @param centerForwardStep
     *        中心转发步骤。
     */
    public void setCenterForwardStep(Integer centerForwardStep) {
        this.centerForwardStep = centerForwardStep;
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
     * 获取子流程路径。
     * 
     * @return 子流程路径。
     */
    public String getSubProcPath() {
        return subProcPath;
    }

    /**
     * 设置子流程路径。
     * 
     * @param subProcPath
     *        子流程路径。
     */
    public void setSubProcPath(String subProcPath) {
        this.subProcPath = subProcPath;
    }

    /**
     * 获取解析后候选人列表。
     * 
     * @return 解析后候选人列表。
     */
    public List<FfUser> getCandidateAssigneeList() {
        return candidateAssigneeList;
    }

    /**
     * 设置解析后候选人列表。
     * 
     * @param candidateAssigneeList
     *        解析后候选人列表。
     */
    public void setCandidateAssigneeList(List<FfUser> candidateAssigneeList) {
        this.candidateAssigneeList = candidateAssigneeList;
    }

    /**
     * 获取解析后候选子流程定义列表。
     * 
     * @return 解析后候选子流程定义列表。
     */
    public List<RunningProcDef> getCandidateSubProcDefList() {
        return candidateSubProcDefList;
    }

    /**
     * 设置解析后候选子流程定义列表。
     * 
     * @param candidateSubProcDefList
     *        解析后候选子流程定义列表。
     */
    public void setCandidateSubProcDefList(List<RunningProcDef> candidateSubProcDefList) {
        this.candidateSubProcDefList = candidateSubProcDefList;
    }

    /**
     * 获取解析后办理人列表。
     * 
     * @return 解析后办理人列表。
     */
    public List<FfUser> getAssigneeList() {
        return assigneeList;
    }

    /**
     * 设置解析后办理人列表。
     * 
     * @param assigneeList
     *        解析后办理人列表。
     */
    public void setAssigneeList(List<FfUser> assigneeList) {
        this.assigneeList = assigneeList;
    }

    /**
     * 获取解析后办理子流程定义列表。
     * 
     * @return 解析后办理子流程定义列表。
     */
    public List<RunningProcDef> getAssignSubProcDefList() {
        return assignSubProcDefList;
    }

    /**
     * 设置解析后办理子流程定义列表。
     * 
     * @param assignSubProcDefList
     *        解析后办理子流程定义列表。
     */
    public void setAssignSubProcDefList(List<RunningProcDef> assignSubProcDefList) {
        this.assignSubProcDefList = assignSubProcDefList;
    }

    /**
     * 获取子流程运行期流程定义列表。
     * 
     * @return 子流程运行期流程定义列表。
     */
    public List<RunningProcDef> getSubProcRunningProcDefList() {
        return subProcRunningProcDefList;
    }

    /**
     * 设置子流程运行期流程定义列表。
     * 
     * @param subProcRunningProcDefList
     *        子流程运行期流程定义列表。
     */
    public void setSubProcRunningProcDefList(List<RunningProcDef> subProcRunningProcDefList) {
        this.subProcRunningProcDefList = subProcRunningProcDefList;
    }

    /**
     * 添加子流程运行期流程定义。
     * 
     * @param subProcRunningProcDef
     *        子流程运行期流程定义。
     */
    public void addSubProcRunningProcDef(RunningProcDef subProcRunningProcDef) {
        if (this.subProcRunningProcDefList == null) {
            this.subProcRunningProcDefList = new ArrayList<>();
        }
        this.subProcRunningProcDefList.add(subProcRunningProcDef);
    }
}