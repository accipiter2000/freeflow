package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfNodeVarService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.NodeVar;

/**
 * 节点变量查询类。
 */
@Service
public class NodeVarQuery {
    private final FfNodeVarService ffNodeVarService;

    private String nodeVarId;// 节点变量ID。
    private List<String> nodeVarIdList;// 节点变量ID列表。
    private String nodeId;// 节点ID。
    private List<String> nodeIdList;// 节点ID列表。
    private String varType; // 变量类型。
    private List<String> varTypeList; // 变量类型列表。
    private String varName; // 变量名称。
    private List<String> varNameList; // 变量名称列表。
    private Boolean recursive;// 是否递归，默认为false。
    private Integer page;// 页。默认为1。
    private Integer limit;// 每页数据数量。默认为-1(全部)。

    public NodeVarQuery(FfNodeVarService ffNodeVarService) {
        super();
        this.ffNodeVarService = ffNodeVarService;
    }

    /**
     * 设置节点变量ID。
     * 
     * @param nodeVarId
     *        节点变量ID。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setNodeVarId(String nodeVarId) {
        this.nodeVarId = nodeVarId;
        return this;
    }

    /**
     * 设置节点变量ID列表。
     * 
     * @param nodeVarIdList
     *        节点变量ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setNodeVarIdList(List<String> nodeVarIdList) {
        this.nodeVarIdList = nodeVarIdList;
        return this;
    }

    /**
     * 设置节点ID。
     * 
     * @param nodeId
     *        节点ID。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * 设置节点ID列表。
     * 
     * @param nodeIdList
     *        节点ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setNodeIdList(List<String> nodeIdList) {
        this.nodeIdList = nodeIdList;
        return this;
    }

    /**
     * 设置变量类型。
     * 
     * @param varType
     *        变量类型。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setVarType(String varType) {
        this.varType = varType;
        return this;
    }

    /**
     * 设置变量类型列表。
     * 
     * @param varTypeList
     *        变量类型列表。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setVarTypeList(List<String> varTypeList) {
        this.varTypeList = varTypeList;
        return this;
    }

    /**
     * 设置变量名称。
     * 
     * @param varName
     *        变量名称。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setVarName(String varName) {
        this.varName = varName;
        return this;
    }

    /**
     * 设置变量名称列表。
     * 
     * @param varNameList
     *        变量名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setVarNameList(List<String> varNameList) {
        this.varNameList = varNameList;
        return this;
    }

    /**
     * 设置是否递归，默认为false。
     * 
     * @param recursive
     *        是否递归，默认为false。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setRecursive(Boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    /**
     * 设置页。默认为1。
     * 
     * @param page
     *        页。默认为1。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 设置每页数据数量。默认为-1(全部)。
     * 
     * @param limit
     *        每页数据数量。默认为-1(全部)。
     * @return 当前查询实例，支持链式调用。
     */
    public NodeVarQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 查询对象列表。数据格式为Map。
     * 
     * @return Map类型数据列表。
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffNodeVarService.selectNodeVar(nodeVarId, nodeVarIdList, nodeId, nodeIdList, varType, varTypeList, varName, varNameList, recursive, page, limit);
    }

    /**
     * 以Map形式返回节点变量。键为节点变量名称，值为节点变量。
     * 
     * @return 单个Map类型数据。
     */
    public Map<String, Object> queryForMap() {
        Map<String, Object> nodeVarMap = new HashMap<>();
        List<Map<String, Object>> nodeVarList = queryForMapList();
        for (Map<String, Object> nodeVar : nodeVarList) {// 组装成MAP，如有相同键值，取流程范围较小的
            if (nodeVarMap.containsKey((String) nodeVar.get("VAR_NAME_"))) {
                continue;
            }
            if (!nodeVar.get("VAR_TYPE_").equals(FfService.VAR_TYPE_OBJECT)) {
                nodeVarMap.put((String) nodeVar.get("VAR_NAME_"), nodeVar.get("VALUE_"));
            }
            else {// 执行反序列化，从流中读取对象
                nodeVarMap.put((String) nodeVar.get("VAR_NAME_"), OdUtils.deserialize((byte[]) nodeVar.get("OBJ_")));
            }
        }

        return nodeVarMap;
    }

    /**
     * 查询对象列表。数据格式为实体Bean。
     * 
     * @return Bean类型数据列表。
     */
    public List<NodeVar> queryForObjectList() {
        List<Map<String, Object>> resultList = queryForMapList();
        List<NodeVar> nodeVarList = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
            nodeVarList.add(new NodeVar(result));
        }

        return nodeVarList;
    }

    /**
     * 查询单个对象。数据格式为实体Bean。
     * 
     * @return 单个Bean类型数据。
     */
    public NodeVar queryForObject() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return new NodeVar(result.get(0));
        }
        else {
            return null;
        }
    }

    /**
     * 查询总数。
     * 
     * @return 总数。
     */
    public int count() {
        return ffNodeVarService.countNodeVar(nodeVarId, nodeVarIdList, nodeId, nodeIdList, varType, varTypeList, varName, varNameList, recursive);
    }
}