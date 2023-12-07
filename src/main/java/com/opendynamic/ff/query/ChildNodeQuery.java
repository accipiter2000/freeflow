package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.vo.Node;

@Service
public class ChildNodeQuery {
    private FfNodeService ffNodeService;

    private String nodeId;
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
    private Date fromCreationDate;
    private Date toCreationDate;
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
    private Boolean recursive;
    private Boolean includeSelf;
    private String dataScope;

    public ChildNodeQuery(FfNodeService ffNodeService) {
        super();
        this.ffNodeService = ffNodeService;
    }

    public ChildNodeQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public ChildNodeQuery setPreviousNodeIds(String previousNodeIds) {
        this.previousNodeIds = previousNodeIds;
        return this;
    }

    public ChildNodeQuery setLastCompleteNodeIds(String lastCompleteNodeIds) {
        this.lastCompleteNodeIds = lastCompleteNodeIds;
        return this;
    }

    public ChildNodeQuery setSubProcDefId(String subProcDefId) {
        this.subProcDefId = subProcDefId;
        return this;
    }

    public ChildNodeQuery setSubProcDefIdList(List<String> subProcDefIdList) {
        this.subProcDefIdList = subProcDefIdList;
        return this;
    }

    public ChildNodeQuery setAdjustSubProcDefId(String adjustSubProcDefId) {
        this.adjustSubProcDefId = adjustSubProcDefId;
        return this;
    }

    public ChildNodeQuery setAdjustSubProcDefIdList(List<String> adjustSubProcDefIdList) {
        this.adjustSubProcDefIdList = adjustSubProcDefIdList;
        return this;
    }

    public ChildNodeQuery setNodeType(String nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public ChildNodeQuery setNodeTypeList(List<String> nodeTypeList) {
        this.nodeTypeList = nodeTypeList;
        return this;
    }

    public ChildNodeQuery setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
        return this;
    }

    public ChildNodeQuery setNodeCodeList(List<String> nodeCodeList) {
        this.nodeCodeList = nodeCodeList;
        return this;
    }

    public ChildNodeQuery setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public ChildNodeQuery setNodeNameList(List<String> nodeNameList) {
        this.nodeNameList = nodeNameList;
        return this;
    }

    public ChildNodeQuery setParentNodeCode(String parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
        return this;
    }

    public ChildNodeQuery setParentNodeCodeList(List<String> parentNodeCodeList) {
        this.parentNodeCodeList = parentNodeCodeList;
        return this;
    }

    public ChildNodeQuery setNodeEndUser(String nodeEndUser) {
        this.nodeEndUser = nodeEndUser;
        return this;
    }

    public ChildNodeQuery setNodeEndUserList(List<String> nodeEndUserList) {
        this.nodeEndUserList = nodeEndUserList;
        return this;
    }

    public ChildNodeQuery setNodeEndUserName(String nodeEndUserName) {
        this.nodeEndUserName = nodeEndUserName;
        return this;
    }

    public ChildNodeQuery setNodeEndUserNameList(List<String> nodeEndUserNameList) {
        this.nodeEndUserNameList = nodeEndUserNameList;
        return this;
    }

    public ChildNodeQuery setFromNodeEndDate(Date fromNodeEndDate) {
        this.fromNodeEndDate = fromNodeEndDate;
        return this;
    }

    public ChildNodeQuery setToNodeEndDate(Date toNodeEndDate) {
        this.toNodeEndDate = toNodeEndDate;
        return this;
    }

    public ChildNodeQuery setIsolateSubProcDefCode(String isolateSubProcDefCode) {
        this.isolateSubProcDefCode = isolateSubProcDefCode;
        return this;
    }

    public ChildNodeQuery setIsolateSubProcDefCodeList(List<String> isolateSubProcDefCodeList) {
        this.isolateSubProcDefCodeList = isolateSubProcDefCodeList;
        return this;
    }

    public ChildNodeQuery setIsolateSubProcStatus(String isolateSubProcStatus) {
        this.isolateSubProcStatus = isolateSubProcStatus;
        return this;
    }

    public ChildNodeQuery setIsolateSubProcStatusList(List<String> isolateSubProcStatusList) {
        this.isolateSubProcStatusList = isolateSubProcStatusList;
        return this;
    }

    public ChildNodeQuery setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
        return this;
    }

    public ChildNodeQuery setNodeStatusList(List<String> nodeStatusList) {
        this.nodeStatusList = nodeStatusList;
        return this;
    }

    public ChildNodeQuery setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
        return this;
    }

    public ChildNodeQuery setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
        return this;
    }

    public ChildNodeQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    public ChildNodeQuery setProcIdList(List<String> procIdList) {
        this.procIdList = procIdList;
        return this;
    }

    public ChildNodeQuery setAdjustProcDefId(String adjustProcDefId) {
        this.adjustProcDefId = adjustProcDefId;
        return this;
    }

    public ChildNodeQuery setAdjustProcDefIdList(List<String> adjustProcDefIdList) {
        this.adjustProcDefIdList = adjustProcDefIdList;
        return this;
    }

    public ChildNodeQuery setIsolateSubProcNodeId(String isolateSubProcNodeId) {
        this.isolateSubProcNodeId = isolateSubProcNodeId;
        return this;
    }

    public ChildNodeQuery setIsolateSubProcNodeIdList(List<String> isolateSubProcNodeIdList) {
        this.isolateSubProcNodeIdList = isolateSubProcNodeIdList;
        return this;
    }

    public ChildNodeQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    public ChildNodeQuery setBizIdList(List<String> bizIdList) {
        this.bizIdList = bizIdList;
        return this;
    }

    public ChildNodeQuery setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    public ChildNodeQuery setBizTypeList(List<String> bizTypeList) {
        this.bizTypeList = bizTypeList;
        return this;
    }

    public ChildNodeQuery setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    public ChildNodeQuery setBizCodeList(List<String> bizCodeList) {
        this.bizCodeList = bizCodeList;
        return this;
    }

    public ChildNodeQuery setBizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    public ChildNodeQuery setBizNameList(List<String> bizNameList) {
        this.bizNameList = bizNameList;
        return this;
    }

    public ChildNodeQuery setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
        return this;
    }

    public ChildNodeQuery setBizDescList(List<String> bizDescList) {
        this.bizDescList = bizDescList;
        return this;
    }

    public ChildNodeQuery setProcStartUser(String procStartUser) {
        this.procStartUser = procStartUser;
        return this;
    }

    public ChildNodeQuery setProcStartUserList(List<String> procStartUserList) {
        this.procStartUserList = procStartUserList;
        return this;
    }

    public ChildNodeQuery setProcStartUserName(String procStartUserName) {
        this.procStartUserName = procStartUserName;
        return this;
    }

    public ChildNodeQuery setProcStartUserNameList(List<String> procStartUserNameList) {
        this.procStartUserNameList = procStartUserNameList;
        return this;
    }

    public ChildNodeQuery setProcEndUser(String procEndUser) {
        this.procEndUser = procEndUser;
        return this;
    }

    public ChildNodeQuery setProcEndUserList(List<String> procEndUserList) {
        this.procEndUserList = procEndUserList;
        return this;
    }

    public ChildNodeQuery setProcEndUserName(String procEndUserName) {
        this.procEndUserName = procEndUserName;
        return this;
    }

    public ChildNodeQuery setProcEndUserNameList(List<String> procEndUserNameList) {
        this.procEndUserNameList = procEndUserNameList;
        return this;
    }

    public ChildNodeQuery setFromProcEndDate(Date fromProcEndDate) {
        this.fromProcEndDate = fromProcEndDate;
        return this;
    }

    public ChildNodeQuery setToProcEndDate(Date toProcEndDate) {
        this.toProcEndDate = toProcEndDate;
        return this;
    }

    public ChildNodeQuery setProcStatus(String procStatus) {
        this.procStatus = procStatus;
        return this;
    }

    public ChildNodeQuery setProcStatusList(List<String> procStatusList) {
        this.procStatusList = procStatusList;
        return this;
    }

    public ChildNodeQuery setFromProcCreationDate(Date fromProcCreationDate) {
        this.fromProcCreationDate = fromProcCreationDate;
        return this;
    }

    public ChildNodeQuery setToProcCreationDate(Date toProcCreationDate) {
        this.toProcCreationDate = toProcCreationDate;
        return this;
    }

    public ChildNodeQuery setProcDefId(String procDefId) {
        this.procDefId = procDefId;
        return this;
    }

    public ChildNodeQuery setProcDefIdList(List<String> procDefIdList) {
        this.procDefIdList = procDefIdList;
        return this;
    }

    public ChildNodeQuery setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
        return this;
    }

    public ChildNodeQuery setProcDefCodeList(List<String> procDefCodeList) {
        this.procDefCodeList = procDefCodeList;
        return this;
    }

    public ChildNodeQuery setProcDefName(String procDefName) {
        this.procDefName = procDefName;
        return this;
    }

    public ChildNodeQuery setProcDefNameList(List<String> procDefNameList) {
        this.procDefNameList = procDefNameList;
        return this;
    }

    public ChildNodeQuery setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
        return this;
    }

    public ChildNodeQuery setProcDefCatList(List<String> procDefCatList) {
        this.procDefCatList = procDefCatList;
        return this;
    }

    public ChildNodeQuery setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public ChildNodeQuery setVersionList(List<Integer> versionList) {
        this.versionList = versionList;
        return this;
    }

    public ChildNodeQuery setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
        return this;
    }

    public ChildNodeQuery setProcDefStatusList(List<String> procDefStatusList) {
        this.procDefStatusList = procDefStatusList;
        return this;
    }

    public ChildNodeQuery setEmptyParentNode(Boolean emptyParentNode) {
        this.emptyParentNode = emptyParentNode;
        return this;
    }

    public ChildNodeQuery setEmptyPreviousNodes(Boolean emptyPreviousNodes) {
        this.emptyPreviousNodes = emptyPreviousNodes;
        return this;
    }

    public ChildNodeQuery setEmptyLastCompleteNodes(Boolean emptyLastCompleteNodes) {
        this.emptyLastCompleteNodes = emptyLastCompleteNodes;
        return this;
    }

    public ChildNodeQuery setRecursive(Boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    public ChildNodeQuery setIncludeSelf(Boolean includeSelf) {
        this.includeSelf = includeSelf;
        return this;
    }

    public ChildNodeQuery setDataScope(String dataScope) {
        this.dataScope = dataScope;
        return this;
    }

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffNodeService.selectChildNode(nodeId, previousNodeIds, lastCompleteNodeIds, subProcDefId, subProcDefIdList, adjustSubProcDefId, adjustSubProcDefIdList, nodeType, nodeTypeList, nodeCode, nodeCodeList, nodeName, nodeNameList, parentNodeCode, parentNodeCodeList, nodeEndUser, nodeEndUserList, nodeEndUserName, nodeEndUserNameList, fromNodeEndDate, toNodeEndDate, isolateSubProcDefCode, isolateSubProcDefCodeList, isolateSubProcStatus, isolateSubProcStatusList, nodeStatus, nodeStatusList, fromCreationDate, toCreationDate, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName,
                procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromProcCreationDate, toProcCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName, procDefNameList, procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, recursive, includeSelf, dataScope);
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
    public List<Node> queryForObjectList() {
        List<Map<String, Object>> result = queryForMapList();
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            nodeList.add(new Node(result.get(i)));
        }

        return nodeList;
    }

    /**
     * 查询单个对象。对象格式为实体Bean。
     * 
     * @return
     */
    public Node queryForObject() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return new Node(result.get(0));
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
        return queryForMapList().size();
    }
}