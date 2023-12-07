package com.opendynamic.ff.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface FfNodeService {
    /**
     * 获取节点任务统计信息。
     */
    public Map<String, Object> getTaskStatistic(String NODE_ID_);

    /**
     * 获取子节点统计信息。
     */
    public Map<String, Object> getChildNodeStatistic(String NODE_ID_);

    /**
     * 按主键查询,返回单个对象。
     */
    public Map<String, Object> loadNode(String NODE_ID_);

    /**
     * 通用查询，返回对象列表。
     */
    public List<Map<String, Object>> selectNode(String NODE_ID_, List<String> NODE_ID_LIST, String PARENT_NODE_ID_, List<String> PARENT_NODE_ID_LIST, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST,
            String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST, String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST,
            String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, Integer page, Integer limit, String dataScope);

    /**
     * 总数查询，在分页时与通用查询配套使用。
     */
    public int countNode(String NODE_ID_, List<String> NODE_ID_LIST, String PARENT_NODE_ID_, List<String> PARENT_NODE_ID_LIST, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST,
            String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST, String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST,
            String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, String dataScope);

    /**
     * 通用父对象查询，返回父对象列表。
     * 
     * @param recursive
     *        是否递归，默认为false。
     * @param includeSelf
     *        是否包含自己，默认为false。
     */
    public List<Map<String, Object>> selectParentNode(String NODE_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST,
            String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST,
            Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, Boolean recursive, Boolean includeSelf, String dataScope);

    /**
     * 通用子对象查询，返回子对象列表。
     * 
     * @param recursive
     *        是否递归，默认为false。
     * @param includeSelf
     *        是否包含自己，默认为false。
     */
    public List<Map<String, Object>> selectChildNode(String NODE_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST,
            String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST,
            Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, Boolean recursive, Boolean includeSelf, String dataScope);

    /**
     * 按主键列表查询，返回对象列表，按主键列表顺序排序。
     */
    public List<Map<String, Object>> selectNodeByIdList(List<String> NODE_ID_LIST);

    /**
     * 新增对象。
     */
    public int insertNode(String NODE_ID_, String PARENT_NODE_ID_, String PROC_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, String ADJUST_SUB_PROC_DEF_ID_, String NODE_TYPE_, String NODE_CODE_, String NODE_NAME_, String PARENT_NODE_CODE_, String CANDIDATE_ASSIGNEE_, String COMPLETE_EXPRESSION_, String COMPLETE_RETURN_, String EXCLUSIVE_, String AUTO_COMPLETE_SAME_ASSIGNEE_, String AUTO_COMPLETE_EMPTY_ASSIGNEE_, String INFORM_, String ASSIGNEE_, String ACTION_, String DUE_DATE_, String CLAIM_, String FORWARDABLE_, String PRIORITY_, String NODE_END_USER_, String NODE_END_USER_NAME_, Date NODE_END_DATE_, String NEXT_CANDIDATE_, String ISOLATE_SUB_PROC_DEF_CODE_, String ISOLATE_SUB_PROC_CANDIDATE_, String ISOLATE_SUB_PROC_STATUS_,
            String NODE_STATUS_, Date CREATION_DATE_);

    /**
     * 修改对象。
     */
    public int updateNode(String NODE_ID_, String PROC_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, String ADJUST_SUB_PROC_DEF_ID_, String NODE_TYPE_, String NODE_CODE_, String NODE_NAME_, String PARENT_NODE_CODE_, String CANDIDATE_ASSIGNEE_, String COMPLETE_EXPRESSION_, String COMPLETE_RETURN_, String EXCLUSIVE_, String AUTO_COMPLETE_SAME_ASSIGNEE_, String AUTO_COMPLETE_EMPTY_ASSIGNEE_, String INFORM_, String ASSIGNEE_, String ACTION_, String DUE_DATE_, String CLAIM_, String FORWARDABLE_, String PRIORITY_, String NODE_END_USER_, String NODE_END_USER_NAME_, Date NODE_END_DATE_, String NEXT_CANDIDATE_, String ISOLATE_SUB_PROC_DEF_CODE_, String ISOLATE_SUB_PROC_CANDIDATE_);

    /**
     * 修改对象。
     */
    public int updateNodePreviousNodeIds(String NODE_ID_, String PREVIOUS_NODE_IDS);

    /**
     * 修改对象。
     */
    public int updateNodeLastCompleteNodeIds(String NODE_ID_, String LAST_COMPLETE_NODE_IDS_);

    /**
     * 修改对象。
     */
    public int updateBranchAdjustSubProcDefId(String BRANCH_ID_, String ADJUST_SUB_PROC_DEF_ID_);

    public int updateNodeNextCandidate(String NODE_ID_, String NEXT_CANDIDATE_);

    /**
     * 修改对象。
     */
    public int updateIsolateSubProcStatus(String NODE_ID_, String ISOLATE_SUB_PROC_STATUS_);

    /**
     * 修改对象。
     */
    public int updateNodeStatus(String NODE_ID_, String NODE_STATUS_);

    /**
     * 修改对象。
     */
    public int updateNodeStatus(String NODE_ID_, String NODE_END_USER_, String NODE_END_USER_NAME_, Date NODE_END_DATE_, String NODE_STATUS_);

    /**
     * 修改对象。
     */
    public int updateNodeStatus(String NODE_ID_, String NODE_END_USER_, String NODE_END_USER_NAME_, Date NODE_END_DATE_, String NEXT_CANDIDATE_, String NODE_STATUS_);

    /**
     * 删除对象。
     */
    public int deleteNode(String NODE_ID_);

    /**
     * 删除对象。
     */
    public int deleteNodeByProcId(String PROC_ID_);
}