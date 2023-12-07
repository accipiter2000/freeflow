package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FfResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private String operationId;

    private List<Proc> createProcList;
    private List<Proc> suspendProcList;
    private List<Proc> activateProcList;
    private List<Proc> completeProcList;
    private List<Proc> terminateProcList;
    private List<Proc> deleteProcList;

    private List<Node> createNodeList;
    private List<Node> suspendNodeList;
    private List<Node> activateNodeList;
    private List<Node> completeNodeList;
    private List<Node> terminateNodeList;
    private List<Node> deleteNodeList;

    private List<Task> createTaskList;
    private List<Task> suspendTaskList;
    private List<Task> activateTaskList;
    private List<Task> completeTaskList;
    private List<Task> terminateTaskList;
    private List<Task> deleteTaskList;
    private List<Task> forwardingTaskList;
    private List<Task> forwardingProcessingCompletedTaskList;

    private List<Object> sequenceList;

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
     */
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    /**
     * 获取操作ID。
     * 
     * @return
     */
    public String getOperationId() {
        return operationId;
    }

    /**
     * 获取新增流程列表。
     * 
     * @return
     */
    public List<Proc> getCreateProcList() {
        return createProcList;
    }

    /**
     * 获取挂起流程列表。
     * 
     * @return
     */
    public List<Proc> getSuspendProcList() {
        return suspendProcList;
    }

    /**
     * 获取激活流程列表。
     * 
     * @return
     */
    public List<Proc> getActivateProcList() {
        return activateProcList;
    }

    /**
     * 获取正常完成流程列表。
     * 
     * @return
     */
    public List<Proc> getCompleteProcList() {
        return completeProcList;
    }

    /**
     * 获取异常完成流程列表。
     * 
     * @return
     */
    public List<Proc> getTerminateProcList() {
        return terminateProcList;
    }

    /**
     * 获取删除流程列表。
     * 
     * @return
     */
    public List<Proc> getDeleteProcList() {
        return deleteProcList;
    }

    /**
     * 获取新增节点列表。
     * 
     * @return
     */
    public List<Node> getCreateNodeList() {
        return createNodeList;
    }

    /**
     * 获取挂起节点列表。
     * 
     * @return
     */
    public List<Node> getSuspendNodeList() {
        return suspendNodeList;
    }

    /**
     * 获取激活节点列表。
     * 
     * @return
     */
    public List<Node> getActivateNodeList() {
        return activateNodeList;
    }

    /**
     * 获取正常完成节点列表。
     * 
     * @return
     */
    public List<Node> getCompleteNodeList() {
        return completeNodeList;
    }

    /**
     * 获取异常完成节点列表。
     * 
     * @return
     */
    public List<Node> getTerminateNodeList() {
        return terminateNodeList;
    }

    /**
     * 获取删除节点列表。
     * 
     * @return
     */
    public List<Node> getDeleteNodeList() {
        return deleteNodeList;
    }

    /**
     * 获取新增任务列表。
     * 
     * @return
     */
    public List<Task> getCreateTaskList() {
        return createTaskList;
    }

    /**
     * 获取挂起任务列表。
     * 
     * @return
     */
    public List<Task> getSuspendTaskList() {
        return suspendTaskList;
    }

    /**
     * 获取激活任务列表。
     * 
     * @return
     */
    public List<Task> getActivateTaskList() {
        return activateTaskList;
    }

    /**
     * 获取正常完成任务列表。
     * 
     * @return
     */
    public List<Task> getCompleteTaskList() {
        return completeTaskList;
    }

    /**
     * 获取异常完成任务列表。
     * 
     * @return
     */
    public List<Task> getTerminateTaskList() {
        return terminateTaskList;
    }

    /**
     * 获取删除任务列表。
     * 
     * @return
     */
    public List<Task> getDeleteTaskList() {
        return deleteTaskList;
    }

    /**
     * 获取新增转发任务列表。
     * 
     * @return
     */
    public List<Task> getForwardingTaskList() {
        return forwardingTaskList;
    }

    /**
     * 获取完成转发任务列表。
     * 
     * @return
     */
    public List<Task> getForwardingProcessingCompletedTaskList() {
        return forwardingProcessingCompletedTaskList;
    }

    /**
     * 获取顺序LIST
     * 
     * @return
     */
    public List<Object> getSequenceList() {
        return sequenceList;
    }

    /**
     * 获取顺序
     * 
     * @param object
     * @return
     */
    public int getSequence(Object object) {
        return sequenceList.indexOf(object);
    }

    /**
     * 添加新增流程。
     * 
     * @param proc
     * @return
     */
    public boolean addCreateProc(Proc proc) {
        sequenceList.add(proc);
        return createProcList.add(proc);
    }

    /**
     * 添加挂起流程。
     * 
     * @param proc
     * @return
     */
    public boolean addSuspendProc(Proc proc) {
        sequenceList.add(proc);
        return suspendProcList.add(proc);
    }

    /**
     * 添加激活流程。
     * 
     * @param proc
     * @return
     */
    public boolean addActivateProc(Proc proc) {
        sequenceList.add(proc);
        return activateProcList.add(proc);
    }

    /**
     * 添加正常完成流程。
     * 
     * @param proc
     * @return
     */
    public boolean addCompleteProc(Proc proc) {
        sequenceList.add(proc);
        return completeProcList.add(proc);
    }

    /**
     * 添异常完成流程。
     * 
     * @param proc
     * @return
     */
    public boolean addTerminateProc(Proc proc) {
        sequenceList.add(proc);
        return terminateProcList.add(proc);
    }

    /**
     * 添加删除流程。
     * 
     * @param proc
     * @return
     */
    public boolean addDeleteProc(Proc proc) {
        sequenceList.add(proc);
        return deleteProcList.add(proc);
    }

    /**
     * 添加新增节点。
     * 
     * @param node
     * @return
     */
    public boolean addCreateNode(Node node) {
        sequenceList.add(node);
        return createNodeList.add(node);
    }

    /**
     * 添加挂起节点。
     * 
     * @param node
     * @return
     */
    public boolean addSuspendNode(Node node) {
        sequenceList.add(node);
        return suspendNodeList.add(node);
    }

    /**
     * 添加激活节点。
     * 
     * @param node
     * @return
     */
    public boolean addActivateNode(Node node) {
        sequenceList.add(node);
        return activateNodeList.add(node);
    }

    /**
     * 添加正常完成节点。
     * 
     * @param node
     * @return
     */
    public boolean addCompleteNode(Node node) {
        sequenceList.add(node);
        return completeNodeList.add(node);
    }

    /**
     * 添加异常完成节点。
     * 
     * @param node
     * @return
     */
    public boolean addTerminateNode(Node node) {
        sequenceList.add(node);
        return terminateNodeList.add(node);
    }

    /**
     * 添加删除节点。
     * 
     * @param node
     * @return
     */
    public boolean addDeleteNode(Node node) {
        sequenceList.add(node);
        return deleteNodeList.add(node);
    }

    /**
     * 添加新增任务。
     * 
     * @param task
     * @return
     */
    public boolean addCreateTask(Task task) {
        sequenceList.add(task);
        return createTaskList.add(task);
    }

    /**
     * 添加挂起任务。
     * 
     * @param task
     * @return
     */
    public boolean addSuspendTask(Task task) {
        sequenceList.add(task);
        return suspendTaskList.add(task);
    }

    /**
     * 添加激活任务。
     * 
     * @param task
     * @return
     */
    public boolean addActivateTask(Task task) {
        sequenceList.add(task);
        return activateTaskList.add(task);
    }

    /**
     * 添加正常完成任务。
     * 
     * @param task
     * @return
     */
    public boolean addCompleteTask(Task task) {
        sequenceList.add(task);
        return completeTaskList.add(task);
    }

    /**
     * 添加异常完成任务。
     * 
     * @param task
     * @return
     */
    public boolean addTerminateTask(Task task) {
        sequenceList.add(task);
        return terminateTaskList.add(task);
    }

    /**
     * 添加删除任务。
     * 
     * @param task
     * @return
     */
    public boolean addDeleteTask(Task task) {
        sequenceList.add(task);
        return deleteTaskList.add(task);
    }

    /**
     * 添加新增转发任务。
     * 
     * @param task
     * @return
     */
    public boolean addForwardingTask(Task task) {
        sequenceList.add(task);
        return forwardingTaskList.add(task);
    }

    /**
     * 添加完成转发任务。
     * 
     * @param task
     * @return
     */
    public boolean addForwardingProcessingCompletedTask(Task task) {
        sequenceList.add(task);
        return forwardingProcessingCompletedTaskList.add(task);
    }

    /**
     * 添加新增流程列表。
     * 
     * @param procList
     * @return
     */
    public boolean addAllCreateProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return createProcList.addAll(procList);
    }

    /**
     * 添加挂起流程列表。
     * 
     * @param procList
     * @return
     */
    public boolean addAllSuspendProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return suspendProcList.addAll(procList);
    }

    /**
     * 添加激活流程列表。
     * 
     * @param procList
     * @return
     */
    public boolean addAllActivateProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return activateProcList.addAll(procList);
    }

    /**
     * 添加正常完成流程列表。
     * 
     * @param procList
     * @return
     */
    public boolean addAllCompleteProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return completeProcList.addAll(procList);
    }

    /**
     * 添加异常完成流程列表。
     * 
     * @param procList
     * @return
     */
    public boolean addAllTerminateProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return terminateProcList.addAll(procList);
    }

    /**
     * 添加删除流程列表。
     * 
     * @param procList
     * @return
     */
    public boolean addAllDeleteProc(List<Proc> procList) {
        sequenceList.addAll(procList);
        return deleteProcList.addAll(procList);
    }

    /**
     * 添加新增节点列表。
     * 
     * @param nodeList
     * @return
     */
    public boolean addAllCreateNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return createNodeList.addAll(nodeList);
    }

    /**
     * 添加挂起节点列表。
     * 
     * @param nodeList
     * @return
     */
    public boolean addAllSuspendNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return suspendNodeList.addAll(nodeList);
    }

    /**
     * 添加激活节点列表。
     * 
     * @param nodeList
     * @return
     */
    public boolean addAllActivateNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return activateNodeList.addAll(nodeList);
    }

    /**
     * 添加正常完成节点列表。
     * 
     * @param nodeList
     * @return
     */
    public boolean addAllCompleteNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return completeNodeList.addAll(nodeList);
    }

    /**
     * 添加异常完成节点列表。
     * 
     * @param nodeList
     * @return
     */
    public boolean addAllTerminateNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return terminateNodeList.addAll(nodeList);
    }

    /**
     * 添加删除节点列表。
     * 
     * @param nodeList
     * @return
     */
    public boolean addAllDeleteNode(List<Node> nodeList) {
        sequenceList.addAll(nodeList);
        return deleteNodeList.addAll(nodeList);
    }

    /**
     * 添加新增任务列表。
     * 
     * @param taskList
     * @return
     */
    public boolean addAllCreateTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return createTaskList.addAll(taskList);
    }

    /**
     * 添加挂起任务列表。
     * 
     * @param taskList
     * @return
     */
    public boolean addAllSuspendTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return suspendTaskList.addAll(taskList);
    }

    /**
     * 添加激活任务列表。
     * 
     * @param taskList
     * @return
     */
    public boolean addAllActivateTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return activateTaskList.addAll(taskList);
    }

    /**
     * 添加正常完成任务列表。
     * 
     * @param taskList
     * @return
     */
    public boolean addAllCompleteTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return completeTaskList.addAll(taskList);
    }

    /**
     * 添加异常完成任务列表。
     * 
     * @param taskList
     * @return
     */
    public boolean addAllTerminateTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return terminateTaskList.addAll(taskList);
    }

    /**
     * 添加删除任务列表。
     * 
     * @param taskList
     * @return
     */
    public boolean addAllDeleteTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return deleteTaskList.addAll(taskList);
    }

    /**
     * 添加新增转发任务列表。
     * 
     * @param taskList
     * @return
     */
    public boolean addAllForwardingTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return forwardingTaskList.addAll(taskList);
    }

    /**
     * 添加完成转发任务列表。
     * 
     * @param taskList
     * @return
     */
    public boolean addAllForwardingProcessingCompletedTask(List<Task> taskList) {
        sequenceList.addAll(taskList);
        return forwardingProcessingCompletedTaskList.addAll(taskList);
    }

    /**
     * 合并另一个FfResult。
     * 
     * @param result
     * @return
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

    /**
     * override。
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(2000);

        result.append("operationId: ").append(operationId).append("\r\n");
        if (createProcList.size() > 0) {
            result.append("createProcList: ");
            for (Proc proc : createProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }
        if (suspendProcList.size() > 0) {
            result.append("suspendProcList: ");
            for (Proc proc : suspendProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }
        if (activateProcList.size() > 0) {
            result.append("activateProcList: ");
            for (Proc proc : activateProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }
        if (completeProcList.size() > 0) {
            result.append("completeProcList: ");
            for (Proc proc : completeProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }
        if (terminateProcList.size() > 0) {
            result.append("terminateProcList: ");
            for (Proc proc : terminateProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }
        if (deleteProcList.size() > 0) {
            result.append("deleteProcList: ");
            for (Proc proc : deleteProcList) {
                result.append(proc.getProcId()).append(":").append(getSequence(proc)).append(" ");
            }
            result.append("\r\n");
        }

        if (createNodeList.size() > 0) {
            result.append("createNodeList: ");
            for (Node node : createNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }
        if (suspendNodeList.size() > 0) {
            result.append("suspendNodeList: ");
            for (Node node : suspendNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }
        if (activateNodeList.size() > 0) {
            result.append("activateNodeList: ");
            for (Node node : activateNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }
        if (completeNodeList.size() > 0) {
            result.append("completeNodeList: ");
            for (Node node : completeNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }
        if (terminateNodeList.size() > 0) {
            result.append("terminateNodeList: ");
            for (Node node : terminateNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }
        if (deleteNodeList.size() > 0) {
            result.append("deleteNodeList: ");
            for (Node node : deleteNodeList) {
                result.append(node.getNodeId()).append(":").append(getSequence(node)).append(" ");
            }
            result.append("\r\n");
        }

        if (createTaskList.size() > 0) {
            result.append("createTaskList: ");
            for (Task task : createTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (suspendTaskList.size() > 0) {
            result.append("suspendTaskList: ");
            for (Task task : suspendTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (activateTaskList.size() > 0) {
            result.append("activateTaskList: ");
            for (Task task : activateTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (completeTaskList.size() > 0) {
            result.append("completeTaskList: ");
            for (Task task : completeTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (terminateTaskList.size() > 0) {
            result.append("terminateTaskList: ");
            for (Task task : terminateTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (deleteTaskList.size() > 0) {
            result.append("deleteTaskList: ");
            for (Task task : deleteTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (forwardingTaskList.size() > 0) {
            result.append("forwardingTaskList: ");
            for (Task task : forwardingTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }
        if (forwardingProcessingCompletedTaskList.size() > 0) {
            result.append("forwardingProcessingCompletedTaskList: ");
            for (Task task : forwardingProcessingCompletedTaskList) {
                result.append(task.getTaskId()).append(":").append(getSequence(task)).append(" ");
            }
            result.append("\r\n");
        }

        return result.toString();
    }
}