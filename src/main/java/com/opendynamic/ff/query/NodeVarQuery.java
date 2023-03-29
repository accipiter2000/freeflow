package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfNodeVarService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.NodeVar;

@Service
public class NodeVarQuery {
    private FfNodeVarService ffNodeVarService;

    private String nodeVarId;
    private String nodeId;
    private List<String> varTypeList;
    private String varName;
    private Boolean recursive;
    private Integer page;
    private Integer limit;

    public NodeVarQuery(FfNodeVarService ffNodeVarService) {
        super();
        this.ffNodeVarService = ffNodeVarService;
    }

    public NodeVarQuery setNodeVarId(String nodeVarId) {
        this.nodeVarId = nodeVarId;
        return this;
    }

    public NodeVarQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public NodeVarQuery setVarType(String varType) {
        if (StringUtils.isNotEmpty(varType)) {
            this.varTypeList = new ArrayList<>();
            this.varTypeList.add(varType);
        }
        return this;
    }

    public NodeVarQuery setVarTypeList(List<String> varTypeList) {
        this.varTypeList = varTypeList;
        return this;
    }

    public NodeVarQuery setVarName(String varName) {
        this.varName = varName;
        return this;
    }

    public NodeVarQuery setRecursive(Boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    public NodeVarQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    public NodeVarQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 查询对象列表。对象格式为Map。
     * 
     * @return
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffNodeVarService.selectNodeVar(nodeVarId, nodeId, varTypeList, varName, recursive, page, limit);
    }

    /**
     * 以Map形式返回节点变量。键为节点变量名称，值为节点变量。
     * 
     * @return
     */
    public Map<String, Object> queryForMap() {
        Map<String, Object> nodeVarMap = new HashMap<>();
        List<Map<String, Object>> nodeVarList = queryForMapList();
        for (Map<String, Object> nodeVar : nodeVarList) {// 组装成MAP，如有相同键值，取流程范围较小的
            if (nodeVarMap.containsKey(nodeVar.get("VAR_NAME_"))) {
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
     * 查询对象列表。对象格式为实体Bean。
     * 
     * @return
     */
    public List<NodeVar> queryForObjectList() {
        List<Map<String, Object>> result = queryForMapList();
        List<NodeVar> nodeVarList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            nodeVarList.add(new NodeVar(result.get(i)));
        }

        return nodeVarList;
    }

    /**
     * 查询单个对象。对象格式为实体Bean。
     * 
     * @return
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
     * @return
     */
    public int count() {
        return ffNodeVarService.countNodeVar(nodeVarId, nodeId, varTypeList, varName, recursive);
    }
}