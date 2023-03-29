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
    private List<FfUser> assigneeList;// 解析后办理人列表
    private List<FfUser> candidateList;// 解析后候选人列表
    private List<ProcDef> assignSubProcDefList;// 解析后办理子流程定义列表
    private List<ProcDef> candidateSubProcDefList;// 解析后候选子流程定义列表
    private List<RunningProcDef> subProcRunningProcDefList;// 子流程运行期流程定义

    public RunningNodeDef(NodeDef nodeDef, RunningProcDef runningProcDef) {
        this.nodeType = nodeDef.getNodeType();
        this.nodeCode = nodeDef.getNodeCode();
        this.nodeName = nodeDef.getNodeName();
        this.parentNodeCode = nodeDef.getParentNodeCode();
        this.assignee = nodeDef.getAssignee();
        this.candidate = nodeDef.getCandidate();
        this.assignSubProcDef = nodeDef.getAssignSubProcDef();
        this.candidateSubProcDef = nodeDef.getCandidateSubProcDef();
        this.action = nodeDef.getAction();
        this.dueDate = nodeDef.getDueDate();
        this.completeExpression = nodeDef.getCompleteExpression();
        this.completeReturn = nodeDef.getCompleteReturn();
        this.exclusive = nodeDef.getExclusive();
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

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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

    public void setForwardable(String forwardable) {
        this.forwardable = forwardable;
    }

    public void setPriority(Integer priority) {
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

    public List<FfUser> getAssigneeList() {
        return assigneeList;
    }

    public void setAssigneeList(List<FfUser> assigneeList) {
        this.assigneeList = assigneeList;
    }

    public List<FfUser> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(List<FfUser> candidateList) {
        this.candidateList = candidateList;
    }

    public List<ProcDef> getAssignSubProcDefList() {
        return assignSubProcDefList;
    }

    public void setAssignSubProcDefList(List<ProcDef> assignSubProcDefList) {
        this.assignSubProcDefList = assignSubProcDefList;
    }

    public List<ProcDef> getCandidateSubProcDefList() {
        return candidateSubProcDefList;
    }

    public void setCandidateSubProcDefList(List<ProcDef> candidateSubProcDefList) {
        this.candidateSubProcDefList = candidateSubProcDefList;
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