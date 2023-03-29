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
    public List<Map<String, Object>> selectTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, List<String> TASK_TYPE_LIST, List<String> ASSIGNEE_LIST, List<String> EXECUTOR_LIST, Date FROM_CLAIM_DATE_, Date TO_CLAIM_DATE_, Date FROM_DUE_DATE_, Date TO_DUE_DATE_, Date FROM_COMPLETE_DATE_, Date TO_COMPLETE_DATE_, Integer PRIORITY_, List<String> FORWARD_STATUS_LIST, List<String> TASK_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PARENT_NODE_ID_, String PROC_ID_, List<String> NODE_TYPE_LIST, String NODE_CODE_, String NODE_NAME_, List<String> NODE_END_USER_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, List<String> NODE_STATUS_LIST, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, List<String> PROC_START_USER_LIST,
            Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_, Integer page, Integer limit);

    /**
     * 总数查询，在分页时与通用查询配套使用。
     */
    public int countTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, List<String> TASK_TYPE_LIST, List<String> ASSIGNEE_LIST, List<String> EXECUTOR_LIST, Date FROM_CLAIM_DATE_, Date TO_CLAIM_DATE_, Date FROM_DUE_DATE_, Date TO_DUE_DATE_, Date FROM_COMPLETE_DATE_, Date TO_COMPLETE_DATE_, Integer PRIORITY_, List<String> FORWARD_STATUS_LIST, List<String> TASK_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PARENT_NODE_ID_, String PROC_ID_, List<String> NODE_TYPE_LIST, String NODE_CODE_, String NODE_NAME_, List<String> NODE_END_USER_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, List<String> NODE_STATUS_LIST, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, List<String> PROC_START_USER_LIST,
            Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_);

    /**
     * 按主键列表查询，返回对象列表，按主键列表顺序排序。
     */
    public List<Map<String, Object>> selectTaskByIdList(List<String> TASK_ID_LIST);

    /**
     * 新增对象。
     */
    public int insertTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, String TASK_TYPE_, String ASSIGNEE_, String ASSIGNEE_NAME_, String EXECUTOR_, String EXECUTOR_NAME_, String ACTION_, Date CLAIM_DATE_, Date DUE_DATE_, Date COMPLETE_DATE_, Integer PRIORITY_, String FORWARDABLE_, String FORWARD_STATUS_, String TASK_STATUS_, Date CREATION_DATE_);

    /**
     * 修改对象。
     */
    public int updateTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, String TASK_TYPE_, String ASSIGNEE_, String ASSIGNEE_NAME_, String EXECUTOR_, String EXECUTOR_NAME_, String ACTION_, Date CLAIM_DATE_, Date DUE_DATE_, Date COMPLETE_DATE_, Integer PRIORITY_, String FORWARDABLE_, String FORWARD_STATUS_);

    /**
     * 修改对象。
     */
    public int updateTaskAssignee(String TASK_ID_, String ASSIGNEE_, String ASSIGNEE_NAME_);

    /**
     * 修改对象。
     */
    public int updateTaskClaimDate(String TASK_ID_, Date CLAIM_DATE_);

    /**
     * 修改对象。
     */
    public int updateTaskForwardStatus(String TASK_ID_, String FORWARD_STATUS_);

    /**
     * 修改对象。
     */
    public int updateTaskStatus(String TASK_ID_, String TASK_STATUS_);

    /**
     * 修改对象。
     */
    public int updateTaskStatus(String TASK_ID_, String EXECUTOR_, String EXECUTOR_NAME_, Date COMPLETE_DATE_, String TASK_STATUS_);

    /**
     * 删除对象。
     */
    public int deleteTask(String TASK_ID_);

    /**
     * 删除对象。
     */
    public int deleteTaskByNodeId(String NODE_ID_);
}