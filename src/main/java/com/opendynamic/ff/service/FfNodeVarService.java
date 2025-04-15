package com.opendynamic.ff.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface FfNodeVarService {
    /**
     * 按主键查询,返回单个对象。
     */
    public Map<String, Object> loadNodeVar(String NODE_VAR_ID_);

    /**
     * 通用查询，返回对象列表。
     */
    public List<Map<String, Object>> selectNodeVar(String NODE_VAR_ID_, List<String> NODE_VAR_ID_LIST, String NODE_ID_, List<String> NODE_ID_LIST, String VAR_TYPE_, List<String> VAR_TYPE_LIST, String VAR_NAME_, List<String> VAR_NAME_LIST, String PROC_ID_, List<String> PROC_ID_LIST, Boolean recursive, Integer page, Integer limit);

    /**
     * 总数查询，在分页时与通用查询配套使用。
     */
    public int countNodeVar(String NODE_VAR_ID_, List<String> NODE_VAR_ID_LIST, String NODE_ID_, List<String> NODE_ID_LIST, String VAR_TYPE_, List<String> VAR_TYPE_LIST, String VAR_NAME_, List<String> VAR_NAME_LIST, String PROC_ID_, List<String> PROC_ID_LIST, Boolean recursive);

    /**
     * 按主键列表查询，返回对象列表，按主键列表顺序排序。
     */
    public List<Map<String, Object>> selectNodeVarByIdList(List<String> NODE_VAR_ID_LIST);

    /**
     * 新增对象。
     */
    public int insertNodeVar(String NODE_VAR_ID_, String NODE_ID_, String VAR_TYPE_, String VAR_NAME_, String VALUE_, Serializable OBJ_, Date CREATION_DATE_);

    /**
     * 修改对象。
     */
    public int updateNodeVar(String NODE_VAR_ID_, String VAR_TYPE_, String VAR_NAME_, String VALUE_, Serializable OBJ_);

    /**
     * 批量更新节点变量.同名覆盖。
     * 
     * @param NODE_ID_
     *        节点ID
     * @param nodeVarMap
     *        节点变量
     * @return 节点变量ID列表
     */
    public List<String> updateNodeVar(String NODE_ID_, Map<String, Object> nodeVarMap);

    /**
     * 删除对象。
     */
    public int deleteNodeVar(String NODE_VAR_ID_);

    /**
     * 删除对象。
     */
    public int deleteNodeVarByNodeId(String NODE_ID_);
}