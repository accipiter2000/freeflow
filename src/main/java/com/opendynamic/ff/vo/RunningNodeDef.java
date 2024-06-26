package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RunningNodeDef extends NodeDef implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nodeStatus;// 节点状态
    private Integer centerForwardStep;// 中心转发步骤
    private Map<String, Object> nodeVarMap;// 节点变量
    private String subProcPath;// 子流程路径
    private List<FfUser> candidateAssigneeList;// 解析后候选人列表
    private List<RunningProcDef> candidateSubProcDefList;// 解析后候选子流程定义列表
    private List<FfUser> assigneeList;// 解析后办理人列表
    private List<RunningProcDef> assignSubProcDefList;// 解析后办理子流程定义列表
    private List<RunningProcDef> subProcRunningProcDefList;// 子流程运行期流程定义

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

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setParentNodeCode(String parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
    }

    public void setCandidateAssignee(String candidateAssignee) {
        this.candidateAssignee = candidateAssignee;
    }

    public void setCandidateSubProcDef(String candidateSubProcDef) {
        this.candidateSubProcDef = candidateSubProcDef;
    }

    public void setCompleteExpression(String completeExpression) {
        this.completeExpression = completeExpression;
    }

    public void setCompleteReturn(String completeReturn) {
        this.completeReturn = completeReturn;
    }

    public void setExclusive(String exclusive) {
        this.exclusive = exclusive;
    }

    public void setWaitingForCompleteNode(String waitingForCompleteNode) {
        this.waitingForCompleteNode = waitingForCompleteNode;
    }

    public void setAutoCompleteSameAssignee(String autoCompleteSameAssignee) {
        this.autoCompleteSameAssignee = autoCompleteSameAssignee;
    }

    public void setAutoCompleteEmptyAssignee(String autoCompleteEmptyAssignee) {
        this.autoCompleteEmptyAssignee = autoCompleteEmptyAssignee;
    }

    public void setInform(String inform) {
        this.inform = inform;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setAssignSubProcDef(String assignSubProcDef) {
        this.assignSubProcDef = assignSubProcDef;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public void setForwardable(String forwardable) {
        this.forwardable = forwardable;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

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

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public Integer getCenterForwardStep() {
        return centerForwardStep;
    }

    public void setCenterForwardStep(Integer centerForwardStep) {
        this.centerForwardStep = centerForwardStep;
    }

    public Map<String, Object> getNodeVarMap() {
        return nodeVarMap;
    }

    public void setNodeVarMap(Map<String, Object> nodeVarMap) {
        this.nodeVarMap = nodeVarMap;
    }

    public String getSubProcPath() {
        return subProcPath;
    }

    public void setSubProcPath(String subProcPath) {
        this.subProcPath = subProcPath;
    }

    public List<FfUser> getCandidateAssigneeList() {
        return candidateAssigneeList;
    }

    public void setCandidateAssigneeList(List<FfUser> candidateAssigneeList) {
        this.candidateAssigneeList = candidateAssigneeList;
    }

    public List<RunningProcDef> getCandidateSubProcDefList() {
        return candidateSubProcDefList;
    }

    public void setCandidateSubProcDefList(List<RunningProcDef> candidateSubProcDefList) {
        this.candidateSubProcDefList = candidateSubProcDefList;
    }

    public List<FfUser> getAssigneeList() {
        return assigneeList;
    }

    public void setAssigneeList(List<FfUser> assigneeList) {
        this.assigneeList = assigneeList;
    }

    public List<RunningProcDef> getAssignSubProcDefList() {
        return assignSubProcDefList;
    }

    public void setAssignSubProcDefList(List<RunningProcDef> assignSubProcDefList) {
        this.assignSubProcDefList = assignSubProcDefList;
    }

    public List<RunningProcDef> getSubProcRunningProcDefList() {
        return subProcRunningProcDefList;
    }

    public void setSubProcRunningProcDefList(List<RunningProcDef> subProcRunningProcDefList) {
        this.subProcRunningProcDefList = subProcRunningProcDefList;
    }

    public void addSubProcRunningProcDef(RunningProcDef subProcRunningProcDef) {
        if (this.subProcRunningProcDefList == null) {
            this.subProcRunningProcDefList = new ArrayList<>();
        }
        this.subProcRunningProcDefList.add(subProcRunningProcDef);
    }
}