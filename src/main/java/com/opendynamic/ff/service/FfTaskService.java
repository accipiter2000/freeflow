package com.opendynamic.ff.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface FfTaskService {
    /**
     * 按主键查询,返回单个对象。
     */
    public Map<String, Object> loadTask(String TASK_ID_);

    /**
     * 通用查询，返回对象列表。
     */
    public List<Map<String, Object>> selectTask(String TASK_ID_, List<String> TASK_ID_LIST, String PREVIOUS_TASK_ID_, List<String> PREVIOUS_TASK_ID_LIST, String TASK_TYPE_, List<String> TASK_TYPE_LIST, String ASSIGNEE_, List<String> ASSIGNEE_LIST, String ASSIGNEE_NAME_, List<String> ASSIGNEE_NAME_LIST, String ACTION_, List<String> ACTION_LIST, Date FROM_DUE_DATE_, Date TO_DUE_DATE_, String CLAIM_, List<String> CLAIM_LIST, String FORWARDABLE_, List<String> FORWARDABLE_LIST, Integer PRIORITY_, List<Integer> PRIORITY_LIST, String FORWARD_STATUS_, List<String> FORWARD_STATUS_LIST, String TASK_END_USER_, List<String> TASK_END_USER_LIST, String TASK_END_USER_NAME_, List<String> TASK_END_USER_NAME_LIST, Date FROM_TASK_END_DATE_, Date TO_TASK_END_DATE_, String TASK_STATUS_,
            List<String> TASK_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String NODE_ID_, List<String> NODE_ID_LIST, String PARENT_NODE_ID_, List<String> PARENT_NODE_ID_LIST, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_,
            List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST, String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_NODE_CREATION_DATE_, Date TO_NODE_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST,
            String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, Integer page, Integer limit, String dataScope);

    /**
     * 总数查询，在分页时与通用查询配套使用。
     */
    public int countTask(String TASK_ID_, List<String> TASK_ID_LIST, String PREVIOUS_TASK_ID_, List<String> PREVIOUS_TASK_ID_LIST, String TASK_TYPE_, List<String> TASK_TYPE_LIST, String ASSIGNEE_, List<String> ASSIGNEE_LIST, String ASSIGNEE_NAME_, List<String> ASSIGNEE_NAME_LIST, String ACTION_, List<String> ACTION_LIST, Date FROM_DUE_DATE_, Date TO_DUE_DATE_, String CLAIM_, List<String> CLAIM_LIST, String FORWARDABLE_, List<String> FORWARDABLE_LIST, Integer PRIORITY_, List<Integer> PRIORITY_LIST, String FORWARD_STATUS_, List<String> FORWARD_STATUS_LIST, String TASK_END_USER_, List<String> TASK_END_USER_LIST, String TASK_END_USER_NAME_, List<String> TASK_END_USER_NAME_LIST, Date FROM_TASK_END_DATE_, Date TO_TASK_END_DATE_, String TASK_STATUS_, List<String> TASK_STATUS_LIST,
            Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String NODE_ID_, List<String> NODE_ID_LIST, String PARENT_NODE_ID_, List<String> PARENT_NODE_ID_LIST, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_,
            List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST, String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_NODE_CREATION_DATE_, Date TO_NODE_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST,
            String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, String dataScope);

    /**
     * 按主键列表查询，返回对象列表，按主键列表顺序排序。
     */
    public List<Map<String, Object>> selectTaskByIdList(List<String> TASK_ID_LIST);

    /**
     * 新增对象。
     */
    public int insertTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, String TASK_TYPE_, String ASSIGNEE_, String ASSIGNEE_NAME_, String ACTION_, Date DUE_DATE_, String CLAIM_, String FORWARDABLE_, Integer PRIORITY_, String FORWARD_STATUS_, String TASK_END_USER_, String TASK_END_USER_NAME_, Date TASK_END_DATE_, String NEXT_CANDIDATE_, String TASK_STATUS_, Date CREATION_DATE_);

    /**
     * 修改对象。
     */
    public int updateTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, String TASK_TYPE_, String ASSIGNEE_, String ASSIGNEE_NAME_, String ACTION_, Date DUE_DATE_, String CLAIM_, String FORWARDABLE_, Integer PRIORITY_, String FORWARD_STATUS_, String TASK_END_USER_, String TASK_END_USER_NAME_, Date TASK_END_DATE_, String NEXT_CANDIDATE_);

    /**
     * 修改对象。
     */
    public int updateTaskAssignee(String TASK_ID_, String ASSIGNEE_, String ASSIGNEE_NAME_);

    /**
     * 修改对象。
     */
    public int updateTaskClaim(String TASK_ID_);

    /**
     * 修改对象。
     */
    public int updateTaskForwardStatus(String TASK_ID_, String FORWARD_STATUS_);

    public int updateTaskNextCandidate(String TASK_ID_, String NEXT_CANDIDATE_);

    /**
     * 修改对象。
     */
    public int updateTaskStatus(String TASK_ID_, String TASK_STATUS_);

    /**
     * 修改对象。
     */
    public int updateTaskStatus(String TASK_ID_, String TASK_END_USER_, String TASK_END_USER_NAME_, Date TASK_END_DATE_, String TASK_STATUS_);

    /**
     * 修改对象。
     */
    public int updateTaskStatus(String TASK_ID_, String TASK_END_USER_, String TASK_END_USER_NAME_, Date TASK_END_DATE_, String NEXT_CANDIDATE_, String TASK_STATUS_);

    /**
     * 删除对象。
     */
    public int deleteTask(String TASK_ID_);

    /**
     * 删除对象。
     */
    public int deleteTaskByNodeId(String NODE_ID_);
}