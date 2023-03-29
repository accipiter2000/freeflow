package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.vo.Node;

@Service
public class NodeQuery {
    private FfNodeService ffNodeService;

    private String nodeId;
    private String parentNodeId;
    private String procId;
    private String previousNodeIds;
    private String lastCompleteNodeIds;
    private String subProcDefId;
    private String adjustSubProcDefId;
    private List<String> nodeTypeList;
    private String nodeCode;
    private String nodeName;
    private String parentNodeCode;
    private Integer priority;
    private List<String> nodeEndUserList;
    private Date fromNodeEndDate;
    private Date toNodeEndDate;
    private List<String> isolateSubProcDefCodeList;
    private List<String> isolateSubProcStatusList;
    private List<String> nodeStatusList;
    private Date fromCreationDate;
    private Date toCreationDate;
    private Boolean emptyParentNode;
    private Boolean emptyPreviousNodes;
    private Boolean emptyLastCompleteNodes;
    private Integer page;
    private Integer limit;

    public NodeQuery(FfNodeService ffNodeService) {
        super();
        this.ffNodeService = ffNodeService;
    }

    public NodeQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public NodeQuery setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
        if (StringUtils.isEmpty(parentNodeId)) {
            emptyParentNode = true;
        }
        return this;
    }

    public NodeQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    public NodeQuery setPreviousNodeIds(String previousNodeIds) {
        this.previousNodeIds = previousNodeIds;
        if (StringUtils.isEmpty(previousNodeIds)) {
            emptyPreviousNodes = true;
        }
        return this;
    }

    public NodeQuery setLastCompleteNodeIds(String lastCompleteNodeIds) {
        this.lastCompleteNodeIds = lastCompleteNodeIds;
        if (StringUtils.isEmpty(lastCompleteNodeIds)) {
            emptyLastCompleteNodes = true;
        }
        return this;
    }

    public NodeQuery setSubProcDefId(String subProcDefId) {
        this.subProcDefId = subProcDefId;
        return this;
    }

    public NodeQuery setAdjustSubProcDefId(String adjustSubProcDefId) {
        this.adjustSubProcDefId = adjustSubProcDefId;
        return this;
    }

    public NodeQuery setNodeType(String nodeType) {
        if (StringUtils.isNotEmpty(nodeType)) {
            this.nodeTypeList = new ArrayList<>();
            this.nodeTypeList.add(nodeType);
        }
        return this;
    }

    public NodeQuery setNodeTypeList(List<String> nodeTypeList) {
        this.nodeTypeList = nodeTypeList;
        return this;
    }

    public NodeQuery setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
        return this;
    }

    public NodeQuery setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public NodeQuery setParentNodeCode(String parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
        return this;
    }

    public NodeQuery setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public NodeQuery setNodeEndUser(String nodeEndUser) {
        if (StringUtils.isNotEmpty(nodeEndUser)) {
            this.nodeEndUserList = new ArrayList<>();
            this.nodeEndUserList.add(nodeEndUser);
        }
        return this;
    }

    public NodeQuery setNodeEndUserList(List<String> nodeEndUserList) {
        this.nodeEndUserList = nodeEndUserList;
        return this;
    }

    public NodeQuery setFromNodeEndDate(Date fromNodeEndDate) {
        this.fromNodeEndDate = fromNodeEndDate;
        return this;
    }

    public NodeQuery setToNodeEndDate(Date toNodeEndDate) {
        this.toNodeEndDate = toNodeEndDate;
        return this;
    }

    public NodeQuery setIsolateSubProcDefCode(String isolateSubProcDefCode) {
        if (StringUtils.isNotEmpty(isolateSubProcDefCode)) {
            this.isolateSubProcDefCodeList = new ArrayList<>();
            this.isolateSubProcDefCodeList.add(isolateSubProcDefCode);
        }
        return this;
    }

    public NodeQuery setIsolateSubProcDefCodeList(List<String> isolateSubProcDefCodeList) {
        this.isolateSubProcDefCodeList = isolateSubProcDefCodeList;
        return this;
    }

    public NodeQuery setIsolateSubProcStatus(String isolateSubProcStatus) {
        if (StringUtils.isNotEmpty(isolateSubProcStatus)) {
            this.isolateSubProcStatusList = new ArrayList<>();
            this.isolateSubProcStatusList.add(isolateSubProcStatus);
        }
        return this;
    }

    public NodeQuery setIsolateSubProcStatusList(List<String> isolateSubProcStatusList) {
        this.isolateSubProcStatusList = isolateSubProcStatusList;
        return this;
    }

    public NodeQuery setNodeStatus(String nodeStatus) {
        if (StringUtils.isNotEmpty(nodeStatus)) {
            this.nodeStatusList = new ArrayList<>();
            this.nodeStatusList.add(nodeStatus);
        }
        return this;
    }

    public NodeQuery setNodeStatusList(List<String> nodeStatusList) {
        this.nodeStatusList = nodeStatusList;
        return this;
    }

    public NodeQuery setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
        return this;
    }

    public NodeQuery setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
        return this;
    }

    public NodeQuery setEmptyParentNode(Boolean emptyParentNode) {
        this.emptyParentNode = emptyParentNode;
        return this;
    }

    public NodeQuery setEmptyPreviousNodes(Boolean emptyPreviousNodes) {
        this.emptyPreviousNodes = emptyPreviousNodes;
        return this;
    }

    public NodeQuery setEmptyLastCompleteNodes(Boolean emptyLastCompleteNodes) {
        this.emptyLastCompleteNodes = emptyLastCompleteNodes;
        return this;
    }

    public NodeQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    public NodeQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffNodeService.selectNode(nodeId, parentNodeId, procId, previousNodeIds, lastCompleteNodeIds, subProcDefId, adjustSubProcDefId, nodeTypeList, nodeCode, nodeName, parentNodeCode, priority, nodeEndUserList, fromNodeEndDate, toNodeEndDate, isolateSubProcDefCodeList, isolateSubProcStatusList, nodeStatusList, fromCreationDate, toCreationDate, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, page, limit);
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
        return ffNodeService.countNode(nodeId, parentNodeId, procId, previousNodeIds, lastCompleteNodeIds, subProcDefId, adjustSubProcDefId, nodeTypeList, nodeCode, nodeName, parentNodeCode, priority, nodeEndUserList, fromNodeEndDate, toNodeEndDate, isolateSubProcDefCodeList, isolateSubProcStatusList, nodeStatusList, fromCreationDate, toCreationDate, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes);
    }
}