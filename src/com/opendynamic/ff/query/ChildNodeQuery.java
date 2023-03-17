package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.vo.Node;

@Service
public class ChildNodeQuery {
    private FfNodeService ffNodeService;

    private String nodeId;
    private String procId;
    private List<String> nodeTypeList;
    private String nodeCode;
    private List<String> nodeStatusList;
    private Boolean includeSelf;
    private Boolean recursive;

    public ChildNodeQuery(FfNodeService ffNodeService) {
        super();
        this.ffNodeService = ffNodeService;
    }

    public ChildNodeQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public ChildNodeQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    public ChildNodeQuery setNodeType(String nodeType) {
        if (StringUtils.isNotEmpty(nodeType)) {
            this.nodeTypeList = new ArrayList<>();
            this.nodeTypeList.add(nodeType);
        }
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

    public ChildNodeQuery setNodeStatus(String nodeStatus) {
        if (StringUtils.isNotEmpty(nodeStatus)) {
            this.nodeStatusList = new ArrayList<>();
            this.nodeStatusList.add(nodeStatus);
        }
        return this;
    }

    public ChildNodeQuery setNodeStatusList(List<String> nodeStatusList) {
        this.nodeStatusList = nodeStatusList;
        return this;
    }

    /**
     * 是否包含自己，默认为false。
     * 
     * @param includeSelf
     * @return
     */
    public ChildNodeQuery setIncludeSelf(Boolean includeSelf) {
        this.includeSelf = includeSelf;
        return this;
    }

    /**
     * 是否递归，默认为false。
     * 
     * @param recursive
     * @return
     */
    public ChildNodeQuery setRecursive(Boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffNodeService.selectChildNode(nodeId, procId, nodeTypeList, nodeCode, nodeStatusList, includeSelf, recursive);
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