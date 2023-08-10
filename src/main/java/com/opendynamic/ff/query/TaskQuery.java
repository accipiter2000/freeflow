package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfTaskService;
import com.opendynamic.ff.vo.Task;

@Service
public class TaskQuery {
    private FfTaskService ffTaskService;

    private String taskId;
    private List<String> taskIdList;
    private String previousTaskId;
    private List<String> previousTaskIdList;
    private String taskType;
    private List<String> taskTypeList;
    private String assignee;
    private List<String> assigneeList;
    private String assigneeName;
    private List<String> assigneeNameList;
    private String action;
    private List<String> actionList;
    private Date fromDueDate;
    private Date toDueDate;
    private String claim;
    private List<String> claimList;
    private String forwardable;
    private List<String> forwardableList;
    private Integer priority;
    private List<Integer> priorityList;
    private String forwardStatus;
    private List<String> forwardStatusList;
    private String taskEndUser;
    private List<String> taskEndUserList;
    private String taskEndUserName;
    private List<String> taskEndUserNameList;
    private Date fromTaskEndDate;
    private Date toTaskEndDate;
    private String taskStatus;
    private List<String> taskStatusList;
    private Date fromCreationDate;
    private Date toCreationDate;
    private String nodeId;
    private List<String> nodeIdList;
    private String parentNodeId;
    private List<String> parentNodeIdList;
    private String previousNodeIds;
    private String lastCompleteNodeIds;
    private String subProcDefId;
    private List<String> subProcDefIdList;
    private String adjustSubProcDefId;
    private List<String> adjustSubProcDefIdList;
    private String nodeType;
    private List<String> nodeTypeList;
    private String nodeCode;
    private List<String> nodeCodeList;
    private String nodeName;
    private List<String> nodeNameList;
    private String parentNodeCode;
    private List<String> parentNodeCodeList;
    private String nodeEndUser;
    private List<String> nodeEndUserList;
    private String nodeEndUserName;
    private List<String> nodeEndUserNameList;
    private Date fromNodeEndDate;
    private Date toNodeEndDate;
    private String isolateSubProcDefCode;
    private List<String> isolateSubProcDefCodeList;
    private String isolateSubProcStatus;
    private List<String> isolateSubProcStatusList;
    private String nodeStatus;
    private List<String> nodeStatusList;
    private Date fromNodeCreationDate;
    private Date toNodeCreationDate;
    private String procId;
    private List<String> procIdList;
    private String adjustProcDefId;
    private List<String> adjustProcDefIdList;
    private String isolateSubProcNodeId;
    private List<String> isolateSubProcNodeIdList;
    private String bizId;
    private List<String> bizIdList;
    private String bizType;
    private List<String> bizTypeList;
    private String bizCode;
    private List<String> bizCodeList;
    private String bizName;
    private List<String> bizNameList;
    private String bizDesc;
    private List<String> bizDescList;
    private String procStartUser;
    private List<String> procStartUserList;
    private String procStartUserName;
    private List<String> procStartUserNameList;
    private String procEndUser;
    private List<String> procEndUserList;
    private String procEndUserName;
    private List<String> procEndUserNameList;
    private Date fromProcEndDate;
    private Date toProcEndDate;
    private String procStatus;
    private List<String> procStatusList;
    private Date fromProcCreationDate;
    private Date toProcCreationDate;
    private String procDefId;
    private List<String> procDefIdList;
    private String procDefCode;
    private List<String> procDefCodeList;
    private String procDefName;
    private List<String> procDefNameList;
    private String procDefCat;
    private List<String> procDefCatList;
    private Integer version;
    private List<Integer> versionList;
    private String procDefStatus;
    private List<String> procDefStatusList;
    private Boolean emptyParentNode;
    private Boolean emptyPreviousNodes;
    private Boolean emptyLastCompleteNodes;
    private Integer page;
    private Integer limit;
    private String dataScope;

    public TaskQuery(FfTaskService ffTaskService) {
        super();
        this.ffTaskService = ffTaskService;
    }

    public TaskQuery setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public TaskQuery setTaskIdList(List<String> taskIdList) {
        this.taskIdList = taskIdList;
        return this;
    }

    public TaskQuery setPreviousTaskId(String previousTaskId) {
        this.previousTaskId = previousTaskId;
        return this;
    }

    public TaskQuery setPreviousTaskIdList(List<String> previousTaskIdList) {
        this.previousTaskIdList = previousTaskIdList;
        return this;
    }

    public TaskQuery setTaskType(String taskType) {
        this.taskType = taskType;
        return this;
    }

    public TaskQuery setTaskTypeList(List<String> taskTypeList) {
        this.taskTypeList = taskTypeList;
        return this;
    }

    public TaskQuery setAssignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    public TaskQuery setAssigneeList(List<String> assigneeList) {
        this.assigneeList = assigneeList;
        return this;
    }

    public TaskQuery setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
        return this;
    }

    public TaskQuery setAssigneeNameList(List<String> assigneeNameList) {
        this.assigneeNameList = assigneeNameList;
        return this;
    }

    public TaskQuery setAction(String action) {
        this.action = action;
        return this;
    }

    public TaskQuery setActionList(List<String> actionList) {
        this.actionList = actionList;
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

    public TaskQuery setClaim(String claim) {
        this.claim = claim;
        return this;
    }

    public TaskQuery setClaimList(List<String> claimList) {
        this.claimList = claimList;
        return this;
    }

    public TaskQuery setForwardable(String forwardable) {
        this.forwardable = forwardable;
        return this;
    }

    public TaskQuery setForwardableList(List<String> forwardableList) {
        this.forwardableList = forwardableList;
        return this;
    }

    public TaskQuery setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public TaskQuery setPriorityList(List<Integer> priorityList) {
        this.priorityList = priorityList;
        return this;
    }

    public TaskQuery setForwardStatus(String forwardStatus) {
        this.forwardStatus = forwardStatus;
        return this;
    }

    public TaskQuery setForwardStatusList(List<String> forwardStatusList) {
        this.forwardStatusList = forwardStatusList;
        return this;
    }

    public TaskQuery setTaskEndUser(String taskEndUser) {
        this.taskEndUser = taskEndUser;
        return this;
    }

    public TaskQuery setTaskEndUserList(List<String> taskEndUserList) {
        this.taskEndUserList = taskEndUserList;
        return this;
    }

    public TaskQuery setTaskEndUserName(String taskEndUserName) {
        this.taskEndUserName = taskEndUserName;
        return this;
    }

    public TaskQuery setTaskEndUserNameList(List<String> taskEndUserNameList) {
        this.taskEndUserNameList = taskEndUserNameList;
        return this;
    }

    public TaskQuery setFromTaskEndDate(Date fromTaskEndDate) {
        this.fromTaskEndDate = fromTaskEndDate;
        return this;
    }

    public TaskQuery setToTaskEndDate(Date toTaskEndDate) {
        this.toTaskEndDate = toTaskEndDate;
        return this;
    }

    public TaskQuery setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
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

    public TaskQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public TaskQuery setNodeIdList(List<String> nodeIdList) {
        this.nodeIdList = nodeIdList;
        return this;
    }

    public TaskQuery setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
        return this;
    }

    public TaskQuery setParentNodeIdList(List<String> parentNodeIdList) {
        this.parentNodeIdList = parentNodeIdList;
        return this;
    }

    public TaskQuery setPreviousNodeIds(String previousNodeIds) {
        this.previousNodeIds = previousNodeIds;
        return this;
    }

    public TaskQuery setLastCompleteNodeIds(String lastCompleteNodeIds) {
        this.lastCompleteNodeIds = lastCompleteNodeIds;
        return this;
    }

    public TaskQuery setSubProcDefId(String subProcDefId) {
        this.subProcDefId = subProcDefId;
        return this;
    }

    public TaskQuery setSubProcDefIdList(List<String> subProcDefIdList) {
        this.subProcDefIdList = subProcDefIdList;
        return this;
    }

    public TaskQuery setAdjustSubProcDefId(String adjustSubProcDefId) {
        this.adjustSubProcDefId = adjustSubProcDefId;
        return this;
    }

    public TaskQuery setAdjustSubProcDefIdList(List<String> adjustSubProcDefIdList) {
        this.adjustSubProcDefIdList = adjustSubProcDefIdList;
        return this;
    }

    public TaskQuery setNodeType(String nodeType) {
        this.nodeType = nodeType;
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

    public TaskQuery setNodeCodeList(List<String> nodeCodeList) {
        this.nodeCodeList = nodeCodeList;
        return this;
    }

    public TaskQuery setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public TaskQuery setNodeNameList(List<String> nodeNameList) {
        this.nodeNameList = nodeNameList;
        return this;
    }

    public TaskQuery setParentNodeCode(String parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
        return this;
    }

    public TaskQuery setParentNodeCodeList(List<String> parentNodeCodeList) {
        this.parentNodeCodeList = parentNodeCodeList;
        return this;
    }

    public TaskQuery setNodeEndUser(String nodeEndUser) {
        this.nodeEndUser = nodeEndUser;
        return this;
    }

    public TaskQuery setNodeEndUserList(List<String> nodeEndUserList) {
        this.nodeEndUserList = nodeEndUserList;
        return this;
    }

    public TaskQuery setNodeEndUserName(String nodeEndUserName) {
        this.nodeEndUserName = nodeEndUserName;
        return this;
    }

    public TaskQuery setNodeEndUserNameList(List<String> nodeEndUserNameList) {
        this.nodeEndUserNameList = nodeEndUserNameList;
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

    public TaskQuery setIsolateSubProcDefCode(String isolateSubProcDefCode) {
        this.isolateSubProcDefCode = isolateSubProcDefCode;
        return this;
    }

    public TaskQuery setIsolateSubProcDefCodeList(List<String> isolateSubProcDefCodeList) {
        this.isolateSubProcDefCodeList = isolateSubProcDefCodeList;
        return this;
    }

    public TaskQuery setIsolateSubProcStatus(String isolateSubProcStatus) {
        this.isolateSubProcStatus = isolateSubProcStatus;
        return this;
    }

    public TaskQuery setIsolateSubProcStatusList(List<String> isolateSubProcStatusList) {
        this.isolateSubProcStatusList = isolateSubProcStatusList;
        return this;
    }

    public TaskQuery setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
        return this;
    }

    public TaskQuery setNodeStatusList(List<String> nodeStatusList) {
        this.nodeStatusList = nodeStatusList;
        return this;
    }

    public TaskQuery setFromNodeCreationDate(Date fromNodeCreationDate) {
        this.fromNodeCreationDate = fromNodeCreationDate;
        return this;
    }

    public TaskQuery setToNodeCreationDate(Date toNodeCreationDate) {
        this.toNodeCreationDate = toNodeCreationDate;
        return this;
    }

    public TaskQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    public TaskQuery setProcIdList(List<String> procIdList) {
        this.procIdList = procIdList;
        return this;
    }

    public TaskQuery setAdjustProcDefId(String adjustProcDefId) {
        this.adjustProcDefId = adjustProcDefId;
        return this;
    }

    public TaskQuery setAdjustProcDefIdList(List<String> adjustProcDefIdList) {
        this.adjustProcDefIdList = adjustProcDefIdList;
        return this;
    }

    public TaskQuery setIsolateSubProcNodeId(String isolateSubProcNodeId) {
        this.isolateSubProcNodeId = isolateSubProcNodeId;
        return this;
    }

    public TaskQuery setIsolateSubProcNodeIdList(List<String> isolateSubProcNodeIdList) {
        this.isolateSubProcNodeIdList = isolateSubProcNodeIdList;
        return this;
    }

    public TaskQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    public TaskQuery setBizIdList(List<String> bizIdList) {
        this.bizIdList = bizIdList;
        return this;
    }

    public TaskQuery setBizType(String bizType) {
        this.bizType = bizType;
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

    public TaskQuery setBizCodeList(List<String> bizCodeList) {
        this.bizCodeList = bizCodeList;
        return this;
    }

    public TaskQuery setBizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    public TaskQuery setBizNameList(List<String> bizNameList) {
        this.bizNameList = bizNameList;
        return this;
    }

    public TaskQuery setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
        return this;
    }

    public TaskQuery setBizDescList(List<String> bizDescList) {
        this.bizDescList = bizDescList;
        return this;
    }

    public TaskQuery setProcStartUser(String procStartUser) {
        this.procStartUser = procStartUser;
        return this;
    }

    public TaskQuery setProcStartUserList(List<String> procStartUserList) {
        this.procStartUserList = procStartUserList;
        return this;
    }

    public TaskQuery setProcStartUserName(String procStartUserName) {
        this.procStartUserName = procStartUserName;
        return this;
    }

    public TaskQuery setProcStartUserNameList(List<String> procStartUserNameList) {
        this.procStartUserNameList = procStartUserNameList;
        return this;
    }

    public TaskQuery setProcEndUser(String procEndUser) {
        this.procEndUser = procEndUser;
        return this;
    }

    public TaskQuery setProcEndUserList(List<String> procEndUserList) {
        this.procEndUserList = procEndUserList;
        return this;
    }

    public TaskQuery setProcEndUserName(String procEndUserName) {
        this.procEndUserName = procEndUserName;
        return this;
    }

    public TaskQuery setProcEndUserNameList(List<String> procEndUserNameList) {
        this.procEndUserNameList = procEndUserNameList;
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
        this.procStatus = procStatus;
        return this;
    }

    public TaskQuery setProcStatusList(List<String> procStatusList) {
        this.procStatusList = procStatusList;
        return this;
    }

    public TaskQuery setFromProcCreationDate(Date fromProcCreationDate) {
        this.fromProcCreationDate = fromProcCreationDate;
        return this;
    }

    public TaskQuery setToProcCreationDate(Date toProcCreationDate) {
        this.toProcCreationDate = toProcCreationDate;
        return this;
    }

    public TaskQuery setProcDefId(String procDefId) {
        this.procDefId = procDefId;
        return this;
    }

    public TaskQuery setProcDefIdList(List<String> procDefIdList) {
        this.procDefIdList = procDefIdList;
        return this;
    }

    public TaskQuery setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
        return this;
    }

    public TaskQuery setProcDefCodeList(List<String> procDefCodeList) {
        this.procDefCodeList = procDefCodeList;
        return this;
    }

    public TaskQuery setProcDefName(String procDefName) {
        this.procDefName = procDefName;
        return this;
    }

    public TaskQuery setProcDefNameList(List<String> procDefNameList) {
        this.procDefNameList = procDefNameList;
        return this;
    }

    public TaskQuery setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
        return this;
    }

    public TaskQuery setProcDefCatList(List<String> procDefCatList) {
        this.procDefCatList = procDefCatList;
        return this;
    }

    public TaskQuery setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public TaskQuery setVersionList(List<Integer> versionList) {
        this.versionList = versionList;
        return this;
    }

    public TaskQuery setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
        return this;
    }

    public TaskQuery setProcDefStatusList(List<String> procDefStatusList) {
        this.procDefStatusList = procDefStatusList;
        return this;
    }

    public TaskQuery setEmptyParentNode(Boolean emptyParentNode) {
        this.emptyParentNode = emptyParentNode;
        return this;
    }

    public TaskQuery setEmptyPreviousNodes(Boolean emptyPreviousNodes) {
        this.emptyPreviousNodes = emptyPreviousNodes;
        return this;
    }

    public TaskQuery setEmptyLastCompleteNodes(Boolean emptyLastCompleteNodes) {
        this.emptyLastCompleteNodes = emptyLastCompleteNodes;
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

    public TaskQuery setDataScope(String dataScope) {
        this.dataScope = dataScope;
        return this;
    }

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffTaskService.selectTask(taskId, taskIdList, previousTaskId, previousTaskIdList, taskType, taskTypeList, assignee, assigneeList, assigneeName, assigneeNameList, action, actionList, fromDueDate, toDueDate, claim, claimList, forwardable, forwardableList, priority, priorityList, forwardStatus, forwardStatusList, taskEndUser, taskEndUserList, taskEndUserName, taskEndUserNameList, fromTaskEndDate, toTaskEndDate, taskStatus, taskStatusList, fromCreationDate, toCreationDate, nodeId, nodeIdList, parentNodeId, parentNodeIdList, previousNodeIds, lastCompleteNodeIds, subProcDefId, subProcDefIdList, adjustSubProcDefId, adjustSubProcDefIdList, nodeType, nodeTypeList, nodeCode, nodeCodeList, nodeName, nodeNameList, parentNodeCode, parentNodeCodeList, nodeEndUser, nodeEndUserList,
                nodeEndUserName, nodeEndUserNameList, fromNodeEndDate, toNodeEndDate, isolateSubProcDefCode, isolateSubProcDefCodeList, isolateSubProcStatus, isolateSubProcStatusList, nodeStatus, nodeStatusList, fromNodeCreationDate, toNodeCreationDate, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromProcCreationDate, toProcCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName, procDefNameList,
                procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, page, limit, dataScope);
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
        return ffTaskService.countTask(taskId, taskIdList, previousTaskId, previousTaskIdList, taskType, taskTypeList, assignee, assigneeList, assigneeName, assigneeNameList, action, actionList, fromDueDate, toDueDate, claim, claimList, forwardable, forwardableList, priority, priorityList, forwardStatus, forwardStatusList, taskEndUser, taskEndUserList, taskEndUserName, taskEndUserNameList, fromTaskEndDate, toTaskEndDate, taskStatus, taskStatusList, fromCreationDate, toCreationDate, nodeId, nodeIdList, parentNodeId, parentNodeIdList, previousNodeIds, lastCompleteNodeIds, subProcDefId, subProcDefIdList, adjustSubProcDefId, adjustSubProcDefIdList, nodeType, nodeTypeList, nodeCode, nodeCodeList, nodeName, nodeNameList, parentNodeCode, parentNodeCodeList, nodeEndUser, nodeEndUserList,
                nodeEndUserName, nodeEndUserNameList, fromNodeEndDate, toNodeEndDate, isolateSubProcDefCode, isolateSubProcDefCodeList, isolateSubProcStatus, isolateSubProcStatusList, nodeStatus, nodeStatusList, fromNodeCreationDate, toNodeCreationDate, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName, procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromProcCreationDate, toProcCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName, procDefNameList,
                procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, dataScope);
    }
}