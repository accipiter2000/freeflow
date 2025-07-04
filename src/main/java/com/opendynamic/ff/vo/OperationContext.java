package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * OperationContext。
 */
public class OperationContext implements Serializable {
    private static final long serialVersionUID = 1L;

    private String initialOperation;// 初始操作。
    private Proc initialProc;// 初始流程。
    private Node initialNode;// 初始节点。
    private Task initialTask;// 初始任务。
    private Map<String, Object> initialNodeVarMap;// 初始节点变量MAP。
    private CandidateList initCandidateList;// 初始候选列表。
    private String initExecutor;// 初始执行人。
    private Proc currentProc;// 当前流程。
    private Node currentBranchNode;// 当前分支节点。
    private Node currentNode;// 当前节点。
    private Node currentNodeVarMapNode;// 当前节点变量MAP的节点。
    private Map<String, Object> currentNodeVarMap;// 当前节点变量MAP。
    private CandidateList currentCandidateList;// 当前候选列表。
    private String currentExecutor;// 当前执行人。
    private List<NodeHandlerOperation> nodeHandlerOperationList = new ArrayList<NodeHandlerOperation>();// 节点处理器操作列表。

    public OperationContext() {
        super();
    }

    public String getInitialOperation() {
        return initialOperation;
    }

    public OperationContext setInitialOperation(String initialOperation) {
        this.initialOperation = initialOperation;
        return this;
    }

    public Proc getInitialProc() {
        return initialProc;
    }

    public OperationContext setInitialProc(Proc initialProc) {
        this.initialProc = initialProc;
        return this;
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public OperationContext setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
        return this;
    }

    public Task getInitialTask() {
        return initialTask;
    }

    public OperationContext setInitialTask(Task initialTask) {
        this.initialTask = initialTask;
        return this;
    }

    public Map<String, Object> getInitialNodeVarMap() {
        return initialNodeVarMap;
    }

    public OperationContext setInitialNodeVarMap(Map<String, Object> initialNodeVarMap) {
        this.initialNodeVarMap = initialNodeVarMap;
        return this;
    }

    public CandidateList getInitCandidateList() {
        return initCandidateList;
    }

    public OperationContext setInitCandidateList(CandidateList initCandidateList) {
        this.initCandidateList = initCandidateList;
        return this;
    }

    public String getInitExecutor() {
        return initExecutor;
    }

    public OperationContext setInitExecutor(String initExecutor) {
        this.initExecutor = initExecutor;
        return this;
    }

    public Proc getCurrentProc() {
        return currentProc;
    }

    public OperationContext setCurrentProc(Proc currentProc) {
        this.currentProc = currentProc;
        return this;
    }

    public Node getCurrentBranchNode() {
        return currentBranchNode;
    }

    public OperationContext setCurrentBranchNode(Node currentBranchNode) {
        this.currentBranchNode = currentBranchNode;
        return this;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public OperationContext setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
        return this;
    }

    public Node getCurrentNodeVarMapNode() {
        return currentNodeVarMapNode;
    }

    public OperationContext setCurrentNodeVarMapNode(Node currentNodeVarMapNode) {
        this.currentNodeVarMapNode = currentNodeVarMapNode;
        return this;
    }

    public Map<String, Object> getCurrentNodeVarMap() {
        return currentNodeVarMap;
    }

    public OperationContext setCurrentNodeVarMap(Map<String, Object> currentNodeVarMap) {
        this.currentNodeVarMap = currentNodeVarMap;
        return this;
    }

    public CandidateList getCurrentCandidateList() {
        return currentCandidateList;
    }

    public OperationContext setCurrentCandidateList(CandidateList currentCandidateList) {
        this.currentCandidateList = currentCandidateList;
        return this;
    }

    public String getCurrentExecutor() {
        return currentExecutor;
    }

    public OperationContext setCurrentExecutor(String currentExecutor) {
        this.currentExecutor = currentExecutor;
        return this;
    }

    public List<NodeHandlerOperation> getNodeHandlerOperationList() {
        return nodeHandlerOperationList;
    }

    public OperationContext addNodeHandlerOperation(NodeHandlerOperation nodeHandlerOperation) {
        this.nodeHandlerOperationList.add(nodeHandlerOperation);
        return this;
    }
}