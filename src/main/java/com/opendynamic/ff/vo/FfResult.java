package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * FF结果。内含所有变更的流程、节点和任务。
 */
public class FfResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private String operationId;// 操作ID。

    private List<Proc> createProcList;// 新增流程列表。
    private List<Proc> suspendProcList;// 挂起流程列表。
    private List<Proc> activateProcList;// 激活流程列表。
    private List<Proc> completeProcList;// 正常完成流程列表。
    private List<Proc> terminateProcList;// 异常完成流程列表。
    private List<Proc> deleteProcList;// 删除流程列表。

    private List<Node> createNodeList;// 新增节点列表。
    private List<Node> suspendNodeList;// 挂起节点列表。
    private List<Node> activateNodeList;// 激活节点列表。
    private List<Node> completeNodeList;// 正常完成节点列表。
    private List<Node> terminateNodeList;// 异常完成节点列表。
    private List<Node> deleteNodeList;// 删除节点列表。

    private List<Task> createTaskList;// 新增任务列表。
    private List<Task> suspendTaskList;// 挂起任务列表。
    private List<Task> activateTaskList;// 激活任务列表。
    private List<Task> completeTaskList;// 正常完成任务列表。
    private List<Task> terminateTaskList;// 异常完成任务列表。
    private List<Task> deleteTaskList;// 删除任务列表。
    private List<Task> forwardingTaskList;// 转发任务列表。
    private List<Task> forwardingProcessingCompletedTaskList;// 完成转发任务列表。

    private List<Object> sequenceList;// 顺序列表。

    public FfResult() {
        this.createProcList = new ArrayList<>();
        this.suspendProcList = new ArrayList<>();
        this.activateProcList = new ArrayList<>();
        this.completeProcList = new ArrayList<>();
        this.terminateProcList = new ArrayList<>();
        this.deleteProcList = new ArrayList<>();

        this.createNodeList = new ArrayList<>();
        this.suspendNodeList = new ArrayList<>();
        this.activateNodeList = new ArrayList<>();
        this.completeNodeList = new ArrayList<>();
        this.terminateNodeList = new ArrayList<>();
        this.deleteNodeList = new ArrayList<>();

        this.createTaskList = new ArrayList<>();
        this.suspendTaskList = new ArrayList<>();
        this.activateTaskList = new ArrayList<>();
        this.completeTaskList = new ArrayList<>();
        this.terminateTaskList = new ArrayList<>();
        this.deleteTaskList = new ArrayList<>();
        this.forwardingTaskList = new ArrayList<>();
        this.forwardingProcessingCompletedTaskList = new ArrayList<>();

        this.sequenceList = new ArrayList<>();
    }

    /**
     * 设置操作ID。
     * 
     * @param operationId
     *        操作ID。
     */
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    /**
     * 获取操作ID。
     * 
     * @return 操作ID。
     */
    public String getOperationId() {
        return operationId;
    }

    /**
     * 获取新增流程列表。
     * 
     * @return 新增流程列表。
     */
    public List<Proc> getCreateProcList() {
        return createProcList;
    }

    /**
     * 获取挂起流程列表。
     * 
     * @return 挂起流程列表。
     */
    public List<Proc> getSuspendProcList() {
        return suspendProcList;
    }

    /**
     * 获取激活流程列表。
     * 
     * @return 激活流程列表。
     */
    public List<Proc> getActivateProcList() {
        return activateProcList;
    }

    /**
     * 获取正常完成流程列表。
     * 
     * @return 正常完成流程列表。
     */
    public List<Proc> getCompleteProcList() {
        return completeProcList;
    }

    /**
     * 获取异常完成流程列表。
     * 
     * @return 异常完成流程列表。
     */
    public List<Proc> getTerminateProcList() {
        return terminateProcList;
    }

    /**
     * 获取删除流程列表。
     * 
     * @return 删除流程列表。
     */
    public List<Proc> getDeleteProcList() {
        return deleteProcList;
    }

    /**
     * 获取新增节点列表。
     * 
     * @return 新增节点列表。
     */
    public List<Node> getCreateNodeList() {
        return createNodeList;
    }

    /**
     * 获取挂起节点列表。
     * 
     * @return 挂起节点列表。
     */
    public List<Node> getSuspendNodeList() {
        return suspendNodeList;
    }

    /**
     * 获取激活节点列表。
     * 
     * @return 激活节点列表。
     */
    public List<Node> getActivateNodeList() {
        return activateNodeList;
    }

    /**
     * 获取正常完成节点列表。
     * 
     * @return 正常完成节点列表。
     */
    public List<Node> getCompleteNodeList() {
        return completeNodeList;
    }

    /**
     * 获取异常完成节点列表。
     * 
     * @return 异常完成节点列表。
     */
    public List<Node> getTerminateNodeList() {
        return terminateNodeList;
    }

    /**
     * 获取删除节点列表。
     * 
     * @return 删除节点列表。
     */
    public List<Node> getDeleteNodeList() {
        return deleteNodeList;
    }

    /**
     * 获取新增任务列表。
     * 
     * @return 新增任务列表。
     */
    public List<Task> getCreateTaskList() {
        return createTaskList;
    }

    /**
     * 获取挂起任务列表。
     * 
     * @return 挂起任务列表。
     */
    public List<Task> getSuspendTaskList() {
        return suspendTaskList;
    }

    /**
     * 获取激活任务列表。
     * 
     * @return 激活任务列表。
     */
    public List<Task> getActivateTaskList() {
        return activateTaskList;
    }

    /**
     * 获取正常完成任务列表。
     * 
     * @return 正常完成任务列表。
     */
    public List<Task> getCompleteTaskList() {
        return completeTaskList;
    }

    /**
     * 获取异常完成任务列表。
     * 
     * @return 异常完成任务列表。
     */
    public List<Task> getTerminateTaskList() {
        return terminateTaskList;
    }

    /**
     * 获取删除任务列表。
     * 
     * @return 删除任务列表。
     */
    public List<Task> getDeleteTaskList() {
        return deleteTaskList;
    }

    /**
     * 获取新增转发任务列表。
     * 
     * @return 新增转发任务列表。
     */
    public List<Task> getForwardingTaskList() {
        return forwardingTaskList;
    }

    /**
     * 获取完成转发任务列表。
     * 
     * @return 完成转发任务列表。
     */
    public List<Task> getForwardingProcessingCompletedTaskList() {
        return forwardingProcessingCompletedTaskList;
    }

    /**
     * 获取顺序List。
     * 
     * @return 顺序List。
     */
    public List<Object> getSequenceList() {
        return sequenceList;
    }

    /**
     * 获取顺序。
     * 
     * @param object
     *        对象。
     * @return 顺序。
     */
    public int getSequence(Object object) {
        return sequenceList.indexOf(object);
    }

    /**
     * 添加新增流程。
     * 
     * @param proc
     *        新增流程。
     * @return 成功返回true，否则返回false。
     */
    public boolean addCreateProc(Proc proc) {
        sequenceList.add(proc);
        return createProcList.add(proc);
    }

    /**
     * 添加挂起流程。
     * 
     * @param proc
     *        挂起流程。
     * @return 成功返回true，否则返回false。
     */
    public boolean addSuspendProc(Proc proc) {
        sequenceList.add(proc);
        return suspendProcList.add(proc);
    }

    /**
     * 添加激活流程。
     * 
     * @param proc
     *        激活流程。
     * @return 成功返回true，否则返回false。
     */
    public boolean addActivateProc(Proc proc) {
        sequenceList.add(proc);
        return activateProcList.add(proc);
    }

    /**
     * 添加正常完成流程。
     * 
     * @param proc
     *        正常完成流程。
     * @return 成功返回true，否则返回false。
     */
    public boolean addCompleteProc(Proc proc) {
        sequenceList.add(proc);
        return completeProcList.add(proc);
    }

    /**
     * 添加异常完成流程。
     * 
     * @param proc
     *        异常完成流程。
     * @return 成功返回true，否则返回false。
     */
    public boolean addTerminateProc(Proc proc) {
        sequenceList.add(proc);
        return terminateProcList.add(proc);
    }

    /**
     * 添加删除流程。
     * 
     * @param proc
     *        删除流程。
     * @return 成功返回true，否则返回false。
     */
    public boolean addDeleteProc(Proc proc) {
        sequenceList.add(proc);
        return deleteProcList.add(proc);
    }

    /**
     * 添加新增节点。
     * 
     * @param node
     *        新增节点。
     * @return 成功返回true，否则返回false。
     */
    public boolean addCreateNode(Node node) {
        sequenceList.add(node);
        return createNodeList.add(node);
    }

    /**
     * 添加挂起节点。
     * 
     * @param node
     *        挂起节点。
     * @return 成功返回true，否则返回false。
     */
    public boolean addSuspendNode(Node node) {
        sequenceList.add(node);
        return suspendNodeList.add(node);
    }

    /**
     * 添加激活节点。
     * 
     * @param node
     *        激活节点。
     * @return 成功返回true，否则返回false。
     */
    public boolean addActivateNode(Node node) {
        sequenceList.add(node);
        return activateNodeList.add(node);
    }

    /**
     * 添加正常完成节点。
     * 
     * @param node
     *        正常完成节点。
     * @return 成功返回true，否则返回false。
     */
    public boolean addCompleteNode(Node node) {
        sequenceList.add(node);
        return completeNodeList.add(node);
    }

    /**
     * 添加异常完成节点。
     * 
     * @param node
     *        异常完成节点。
     * @return 成功返回true，否则返回false。
     */
    public boolean addTerminateNode(Node node) {
        sequenceList.add(node);
        return terminateNodeList.add(node);
    }

    /**
     * 添加删除节点。
     * 
     * @param node
     *        删除节点。
     * @return 成功返回true，否则返回false。
     */
    public boolean addDeleteNode(Node node) {
        sequenceList.add(node);
        return deleteNodeList.add(node);
    }

    /**
     * 添加新增任务。
     * 
     * @param task
     *        新增任务。
     * @return 成功返回true，否则返回false。
     */
    public boolean addCreateTask(Task task) {
        sequenceList.add(task);
        return createTaskList.add(task);
    }

    /**
     * 添加挂起任务。
     * 
     * @param task
     *        挂起任务。
     * @return 成功返回true，否则返回false。
     */
    public boolean addSuspendTask(Task task) {
        sequenceList.add(task);
        return suspendTaskList.add(task);
    }

    /**
     * 添加激活任务。
     * 
     * @param task
     *        激活任务。
     * @return 成功返回true，否则返回false。
     */
    public boolean addActivateTask(Task task) {
        sequenceList.add(task);
        return activateTaskList.add(task);
    }

    /**
     * 添加正常完成任务。
     * 
     * @param task
     *        正常完成任务。
     * @return 成功返回true，否则返回false。
     */
    public boolean addCompleteTask(Task task) {
        sequenceList.add(task);
        return completeTaskList.add(task);
    }

    /**
     * 添加异常完成任务。
     * 
     * @param task
     *        异常完成任务。
     * @return 成功返回true，否则返回false。
     */
    public boolean addTerminateTask(Task task) {
        sequenceList.add(task);
        return terminateTaskList.add(task);
    }

    /**
     * 添加删除任务。
     * 
     * @param task
     *        删除任务。
     * @return 成功返回true，否则返回false。
     */
    public boolean addDeleteTask(Task task) {
        sequenceList.add(task);
        return deleteTaskList.add(task);
    }

    /**
     * 添加转发任务。
     * 
     * @param task
     *        转发任务。
     * @return 成功返回true，否则返回false。
     */
    public boolean addForwardingTask(Task task) {
        sequenceList.add(task);
        return forwardingTaskList.add(task);
    }

    /**
     * 添加完成转发任务。
     * 
     * @param task
     *        完成转发任务。
     * @return 成功返回true，否则返回false。
     */
    public boolean addForwardingProcessingCompletedTask(Task task) {
        sequenceList.add(task);
        return forwardingProcessingCompletedTaskList.add(task);
    }

    /**
     * 添加新增流程列表。
     * 
     * @param procList
     *        新增流程列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllCreateProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return createProcList.addAll(procList);
    }

    /**
     * 添加挂起流程列表。
     * 
     * @param procList
     *        挂起流程列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllSuspendProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return suspendProcList.addAll(procList);
    }

    /**
     * 添加激活流程列表。
     * 
     * @param procList
     *        激活流程列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllActivateProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return activateProcList.addAll(procList);
    }

    /**
     * 添加正常完成流程列表。
     * 
     * @param procList
     *        正常完成流程列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllCompleteProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return completeProcList.addAll(procList);
    }

    /**
     * 添加异常完成流程列表。
     * 
     * @param procList
     *        异常完成流程列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllTerminateProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return terminateProcList.addAll(procList);
    }

    /**
     * 添加删除流程列表。
     * 
     * @param procList
     *        删除流程列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllDeleteProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return deleteProcList.addAll(procList);
    }

    /**
     * 添加新增节点列表。
     * 
     * @param nodeList
     *        新增节点列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllCreateNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return createNodeList.addAll(nodeList);
    }

    /**
     * 添加挂起节点列表。
     * 
     * @param nodeList
     *        挂起节点列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllSuspendNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return suspendNodeList.addAll(nodeList);
    }

    /**
     * 添加激活节点列表。
     * 
     * @param nodeList
     *        激活节点列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllActivateNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return activateNodeList.addAll(nodeList);
    }

    /**
     * 添加正常完成节点列表。
     * 
     * @param nodeList
     *        正常完成节点列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllCompleteNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return completeNodeList.addAll(nodeList);
    }

    /**
     * 添加异常完成节点列表。
     * 
     * @param nodeList
     *        异常完成节点列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllTerminateNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return terminateNodeList.addAll(nodeList);
    }

    /**
     * 添加删除节点列表。
     * 
     * @param nodeList
     *        删除节点列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllDeleteNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return deleteNodeList.addAll(nodeList);
    }

    /**
     * 添加新增任务列表。
     * 
     * @param taskList
     *        新增任务列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllCreateTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return createTaskList.addAll(taskList);
    }

    /**
     * 添加挂起任务列表。
     * 
     * @param taskList
     *        挂起任务列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllSuspendTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return suspendTaskList.addAll(taskList);
    }

    /**
     * 添加激活任务列表。
     * 
     * @param taskList
     *        激活任务列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllActivateTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return activateTaskList.addAll(taskList);
    }

    /**
     * 添加正常完成任务列表。
     * 
     * @param taskList
     *        正常完成任务列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllCompleteTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return completeTaskList.addAll(taskList);
    }

    /**
     * 添加异常完成任务列表。
     * 
     * @param taskList
     *        异常完成任务列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllTerminateTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return terminateTaskList.addAll(taskList);
    }

    /**
     * 添加删除任务列表。
     * 
     * @param taskList
     *        删除任务列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllDeleteTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return deleteTaskList.addAll(taskList);
    }

    /**
     * 添加新增转发任务列表。
     * 
     * @param taskList
     *        新增转发任务列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllForwardingTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return forwardingTaskList.addAll(taskList);
    }

    /**
     * 添加完成转发任务列表。
     * 
     * @param taskList
     *        完成转发任务列表。
     * @return 成功返回true，否则返回false。
     */
    public boolean addAllForwardingProcessingCompletedTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return forwardingProcessingCompletedTaskList.addAll(taskList);
    }

    /**
     * 合并另一个FfResult。
     * 
     * @param result
     *        另一个FfResult。
     * @return 合兵后的FfResult。
     */
    public boolean addAll(FfResult result) {
        addAllCreateProc(result.getCreateProcList());
        addAllSuspendProc(result.getSuspendProcList());
        addAllActivateProc(result.getActivateProcList());
        addAllCompleteProc(result.getCompleteProcList());
        addAllTerminateProc(result.getTerminateProcList());
        addAllDeleteProc(result.getDeleteProcList());

        addAllCreateNode(result.getCreateNodeList());
        addAllSuspendNode(result.getSuspendNodeList());
        addAllActivateNode(result.getActivateNodeList());
        addAllCompleteNode(result.getCompleteNodeList());
        addAllTerminateNode(result.getTerminateNodeList());
        addAllDeleteNode(result.getDeleteNodeList());

        addAllCreateTask(result.getCreateTaskList());
        addAllSuspendTask(result.getSuspendTaskList());
        addAllActivateTask(result.getActivateTaskList());
        addAllCompleteTask(result.getCompleteTaskList());
        addAllTerminateTask(result.getTerminateTaskList());
        addAllDeleteTask(result.getDeleteTaskList());
        addAllForwardingTask(result.getForwardingTaskList());
        addAllForwardingProcessingCompletedTask(result.getForwardingProcessingCompletedTaskList());

        sequenceList.removeAll(result.getSequenceList());
        sequenceList.addAll(result.getSequenceList());

        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(2000);

        result.append("operationId: ").append(operationId).append("\r\n");
        if (!createProcList.isEmpty()) {
            result.append("createProcList: ");
            for (Proc proc : createProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }
        if (!suspendProcList.isEmpty()) {
            result.append("suspendProcList: ");
            for (Proc proc : suspendProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }
        if (!activateProcList.isEmpty()) {
            result.append("activateProcList: ");
            for (Proc proc : activateProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }
        if (!completeProcList.isEmpty()) {
            result.append("completeProcList: ");
            for (Proc proc : completeProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }
        if (!terminateProcList.isEmpty()) {
            result.append("terminateProcList: ");
            for (Proc proc : terminateProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }
        if (!deleteProcList.isEmpty()) {
            result.append("deleteProcList: ");
            for (Proc proc : deleteProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }

        if (!createNodeList.isEmpty()) {
            result.append("createNodeList: ");
            for (Node node : createNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }
        if (!suspendNodeList.isEmpty()) {
            result.append("suspendNodeList: ");
            for (Node node : suspendNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }
        if (!activateNodeList.isEmpty()) {
            result.append("activateNodeList: ");
            for (Node node : activateNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }
        if (!completeNodeList.isEmpty()) {
            result.append("completeNodeList: ");
            for (Node node : completeNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }
        if (!terminateNodeList.isEmpty()) {
            result.append("terminateNodeList: ");
            for (Node node : terminateNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }
        if (!deleteNodeList.isEmpty()) {
            result.append("deleteNodeList: ");
            for (Node node : deleteNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }

        if (!createTaskList.isEmpty()) {
            result.append("createTaskList: ");
            for (Task task : createTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (!suspendTaskList.isEmpty()) {
            result.append("suspendTaskList: ");
            for (Task task : suspendTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (!activateTaskList.isEmpty()) {
            result.append("activateTaskList: ");
            for (Task task : activateTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (!completeTaskList.isEmpty()) {
            result.append("completeTaskList: ");
            for (Task task : completeTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (!terminateTaskList.isEmpty()) {
            result.append("terminateTaskList: ");
            for (Task task : terminateTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (!deleteTaskList.isEmpty()) {
            result.append("deleteTaskList: ");
            for (Task task : deleteTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (!forwardingTaskList.isEmpty()) {
            result.append("forwardingTaskList: ");
            for (Task task : forwardingTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (!forwardingProcessingCompletedTaskList.isEmpty()) {
            result.append("forwardingProcessingCompletedTaskList: ");
            for (Task task : forwardingProcessingCompletedTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }

        return result.toString();
    }
}