package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfTaskService;
import com.opendynamic.ff.vo.Task;

@Service
public class TaskQuery {
    private FfTaskService ffTaskService;

    private String taskId;
    private String nodeId;
    private String previousTaskId;
    private List<String> taskTypeList;
    private List<String> assigneeList;
    private List<String> executorList;
    private Date fromClaimDate;
    private Date toClaimDate;
    private Date fromDueDate;
    private Date toDueDate;
    private Date fromCompleteDate;
    private Date toCompleteDate;
    private Integer priority;
    private List<String> forwardStatusList;
    private List<String> taskStatusList;
    private Date fromCreationDate;
    private Date toCreationDate;
    private String parentNodeId;
    private String procId;
    private List<String> nodeTypeList;
    private String nodeCode;
    private String nodeName;
    private List<String> nodeEndUserList;
    private Date fromNodeEndDate;
    private Date toNodeEndDate;
    private List<String> nodeStatusList;
    private String bizId;
    private List<String> bizTypeList;
    private String bizCode;
    private String bizName;
    private List<String> procStartUserList;
    private Date fromProcStartDate;
    private Date toProcStartDate;
    private List<String> procEndUserList;
    private Date fromProcEndDate;
    private Date toProcEndDate;
    private List<String> procStatusList;
    private List<String> procDefCodeList;
    private String procDefCat;
    private Integer page;
    private Integer limit;

    public TaskQuery(FfTaskService ffTaskService) {
        super();
        this.ffTaskService = ffTaskService;
    }

    public TaskQuery setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public TaskQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public TaskQuery setPreviousTaskId(String previousTaskId) {
        this.previousTaskId = previousTaskId;
        return this;
    }

    public TaskQuery setTaskType(String taskType) {
        if (StringUtils.isNotEmpty(taskType)) {
            this.taskTypeList = new ArrayList<>();
            this.taskTypeList.add(taskType);
        }
        return this;
    }

    public TaskQuery setTaskTypeList(List<String> taskTypeList) {
        this.taskTypeList = taskTypeList;
        return this;
    }

    public TaskQuery setAssignee(String assignee) {
        if (StringUtils.isNotEmpty(assignee)) {
            this.assigneeList = new ArrayList<>();
            this.assigneeList.add(assignee);
        }
        return this;
    }

    public TaskQuery setAssigneeList(List<String> assigneeList) {
        this.assigneeList = assigneeList;
        return this;
    }

    public TaskQuery setExecutor(String executor) {
        if (StringUtils.isNotEmpty(executor)) {
            this.executorList = new ArrayList<>();
            this.executorList.add(executor);
        }
        return this;
    }

    public TaskQuery setExecutorList(List<String> executorList) {
        this.executorList = executorList;
        return this;
    }

    public TaskQuery setFromClaimDate(Date fromClaimDate) {
        this.fromClaimDate = fromClaimDate;
        return this;
    }

    public TaskQuery setToClaimDate(Date toClaimDate) {
        this.toClaimDate = toClaimDate;
        return this;
    }

    public TaskQuery setFromDueDate(Date fromDueDate) {
        this.fromDueDate = fromDueDate;
        return this;
    }

    public TaskQuery setToDueDate(Date toDueDate) {
        this.toDueDate = toDueDate;
        return this;
    }

    public TaskQuery setFromCompleteDate(Date fromCompleteDate) {
        this.fromCompleteDate = fromCompleteDate;
        return this;
    }

    public TaskQuery setToCompleteDate(Date toCompleteDate) {
        this.toCompleteDate = toCompleteDate;
        return this;
    }

    public TaskQuery setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public TaskQuery setForwardStatus(String forwardStatus) {
        if (StringUtils.isNotEmpty(forwardStatus)) {
            this.forwardStatusList = new ArrayList<>();
            this.forwardStatusList.add(forwardStatus);
        }
        return this;
    }

    public TaskQuery setForwardStatusList(List<String> forwardStatusList) {
        this.forwardStatusList = forwardStatusList;
        return this;
    }

    public TaskQuery setTaskStatus(String taskStatus) {
        if (StringUtils.isNotEmpty(taskStatus)) {
            this.taskStatusList = new ArrayList<>();
            this.taskStatusList.add(taskStatus);
        }
        return this;
    }

    public TaskQuery setTaskStatusList(List<String> taskStatusList) {
        this.taskStatusList = taskStatusList;
        return this;
    }

    public TaskQuery setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
        return this;
    }

    public TaskQuery setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
        return this;
    }

    public TaskQuery setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
        return this;
    }

    public TaskQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    public TaskQuery setNodeType(String nodeType) {
        if (StringUtils.isNotEmpty(nodeType)) {
            this.nodeTypeList = new ArrayList<>();
            this.nodeTypeList.add(nodeType);
        }
        return this;
    }

    public TaskQuery setNodeTypeList(List<String> nodeTypeList) {
        this.nodeTypeList = nodeTypeList;
        return this;
    }

    public TaskQuery setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
        return this;
    }

    public TaskQuery setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public TaskQuery setNodeEndUser(String nodeEndUser) {
        if (StringUtils.isNotEmpty(nodeEndUser)) {
            this.nodeEndUserList = new ArrayList<>();
            this.nodeEndUserList.add(nodeEndUser);
        }
        return this;
    }

    public TaskQuery setNodeEndUserList(List<String> nodeEndUserList) {
        this.nodeEndUserList = nodeEndUserList;
        return this;
    }

    public TaskQuery setFromNodeEndDate(Date fromNodeEndDate) {
        this.fromNodeEndDate = fromNodeEndDate;
        return this;
    }

    public TaskQuery setToNodeEndDate(Date toNodeEndDate) {
        this.toNodeEndDate = toNodeEndDate;
        return this;
    }

    public TaskQuery setNodeStatus(String nodeStatus) {
        if (StringUtils.isNotEmpty(nodeStatus)) {
            this.nodeStatusList = new ArrayList<>();
            this.nodeStatusList.add(nodeStatus);
        }
        return this;
    }

    public TaskQuery setNodeStatusList(List<String> nodeStatusList) {
        this.nodeStatusList = nodeStatusList;
        return this;
    }

    public TaskQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    public TaskQuery setBizType(String bizType) {
        if (StringUtils.isNotEmpty(bizType)) {
            this.bizTypeList = new ArrayList<>();
            this.bizTypeList.add(bizType);
        }
        return this;
    }

    public TaskQuery setBizTypeList(List<String> bizTypeList) {
        this.bizTypeList = bizTypeList;
        return this;
    }

    public TaskQuery setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    public TaskQuery setBizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    public TaskQuery setProcStartUser(String procStartUser) {
        if (StringUtils.isNotEmpty(procStartUser)) {
            this.procStartUserList = new ArrayList<>();
            this.procStartUserList.add(procStartUser);
        }
        return this;
    }

    public TaskQuery setProcStartUserList(List<String> procStartUserList) {
        this.procStartUserList = procStartUserList;
        return this;
    }

    public TaskQuery setFromProcStartDate(Date fromProcStartDate) {
        this.fromProcStartDate = fromProcStartDate;
        return this;
    }

    public TaskQuery setToProcStartDate(Date toProcStartDate) {
        this.toProcStartDate = toProcStartDate;
        return this;
    }

    public TaskQuery setProcEndUser(String procEndUser) {
        if (StringUtils.isNotEmpty(procEndUser)) {
            this.procEndUserList = new ArrayList<>();
            this.procEndUserList.add(procEndUser);
        }
        return this;
    }

    public TaskQuery setProcEndUserList(List<String> procEndUserList) {
        this.procEndUserList = procEndUserList;
        return this;
    }

    public TaskQuery setFromProcEndDate(Date fromProcEndDate) {
        this.fromProcEndDate = fromProcEndDate;
        return this;
    }

    public TaskQuery setToProcEndDate(Date toProcEndDate) {
        this.toProcEndDate = toProcEndDate;
        return this;
    }

    public TaskQuery setProcStatus(String procStatus) {
        if (StringUtils.isNotEmpty(procStatus)) {
            this.procStatusList = new ArrayList<>();
            this.procStatusList.add(procStatus);
        }
        return this;
    }

    public TaskQuery setProcStatusList(List<String> procStatusList) {
        this.procStatusList = procStatusList;
        return this;
    }

    public TaskQuery setProcDefCode(String procDefCode) {
        if (StringUtils.isNotEmpty(procDefCode)) {
            this.procDefCodeList = new ArrayList<>();
            this.procDefCodeList.add(procDefCode);
        }
        return this;
    }

    public TaskQuery setProcDefCodeList(List<String> procDefCodeList) {
        this.procDefCodeList = procDefCodeList;
        return this;
    }

    public TaskQuery setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
        return this;
    }

    public TaskQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    public TaskQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffTaskService.selectTask(taskId, nodeId, previousTaskId, taskTypeList, assigneeList, executorList, fromClaimDate, toClaimDate, fromDueDate, toDueDate, fromCompleteDate, toCompleteDate, priority, forwardStatusList, taskStatusList, fromCreationDate, toCreationDate, parentNodeId, procId, nodeTypeList, nodeCode, nodeName, nodeEndUserList, fromNodeEndDate, toNodeEndDate, nodeStatusList, bizId, bizTypeList, bizCode, bizName, procStartUserList, fromProcStartDate, toProcStartDate, procEndUserList, fromProcEndDate, toProcEndDate, procStatusList, procDefCodeList, procDefCat, page, limit);
    }

    /**
     * 查询单个对象。对象格式为Map。
     * 
     * @return
     */
    public Map<String, Object> queryForMap() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * 查询对象列表。对象格式为实体Bean。
     * 
     * @return
     */
    public List<Task> queryForObjectList() {
        List<Map<String, Object>> result = queryForMapList();
        List<Task> nodeList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            nodeList.add(new Task(result.get(i)));
        }

        return nodeList;
    }

    /**
     * 查询单个对象。对象格式为实体Bean。
     * 
     * @return
     */
    public Task queryForObject() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return new Task(result.get(0));
        }
        else {
            return null;
        }
    }

    /**
     * 查询总数。
     * 
     * @return
     */
    public int count() {
        return ffTaskService.countTask(taskId, nodeId, previousTaskId, taskTypeList, assigneeList, executorList, fromClaimDate, toClaimDate, fromDueDate, toDueDate, fromCompleteDate, toCompleteDate, priority, forwardStatusList, taskStatusList, fromCreationDate, toCreationDate, parentNodeId, procId, nodeTypeList, nodeCode, nodeName, nodeEndUserList, fromNodeEndDate, toNodeEndDate, nodeStatusList, bizId, bizTypeList, bizCode, bizName, procStartUserList, fromProcStartDate, toProcStartDate, procEndUserList, fromProcEndDate, toProcEndDate, procStatusList, procDefCodeList, procDefCat);
    }
}