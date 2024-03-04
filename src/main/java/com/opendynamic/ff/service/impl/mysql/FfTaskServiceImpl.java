package com.opendynamic.ff.service.impl.mysql;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opendynamic.OdSqlCriteria;
import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfOperationService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.service.FfTaskService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfTaskServiceImpl implements FfTaskService {
    @Autowired
    private FfOperationService ffOperationService;
    @Autowired
    private JdbcTemplate ffJdbcTemplate;

    @Override
    public Map<String, Object> loadTask(String TASK_ID_) {
        String sql = "select * from FFV_TASK where TASK_ID_ = ?";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, TASK_ID_);
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectTask(String TASK_ID_, List<String> TASK_ID_LIST, String PREVIOUS_TASK_ID_, List<String> PREVIOUS_TASK_ID_LIST, String TASK_TYPE_, List<String> TASK_TYPE_LIST, String ASSIGNEE_, List<String> ASSIGNEE_LIST, String ASSIGNEE_NAME_, List<String> ASSIGNEE_NAME_LIST, String ACTION_, List<String> ACTION_LIST, Date FROM_DUE_DATE_, Date TO_DUE_DATE_, String CLAIM_, List<String> CLAIM_LIST, String FORWARDABLE_, List<String> FORWARDABLE_LIST, Integer PRIORITY_, List<Integer> PRIORITY_LIST, String FORWARD_STATUS_, List<String> FORWARD_STATUS_LIST, String TASK_END_USER_, List<String> TASK_END_USER_LIST, String TASK_END_USER_NAME_, List<String> TASK_END_USER_NAME_LIST, Date FROM_TASK_END_DATE_, Date TO_TASK_END_DATE_, String TASK_STATUS_,
            List<String> TASK_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String NODE_ID_, List<String> NODE_ID_LIST, String PARENT_NODE_ID_, List<String> PARENT_NODE_ID_LIST, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_,
            List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST, String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_NODE_CREATION_DATE_, Date TO_NODE_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST,
            String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, Integer page, Integer limit, String dataScope) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaTask(false, TASK_ID_, TASK_ID_LIST, PREVIOUS_TASK_ID_, PREVIOUS_TASK_ID_LIST, TASK_TYPE_, TASK_TYPE_LIST, ASSIGNEE_, ASSIGNEE_LIST, ASSIGNEE_NAME_, ASSIGNEE_NAME_LIST, ACTION_, ACTION_LIST, FROM_DUE_DATE_, TO_DUE_DATE_, CLAIM_, CLAIM_LIST, FORWARDABLE_, FORWARDABLE_LIST, PRIORITY_, PRIORITY_LIST, FORWARD_STATUS_, FORWARD_STATUS_LIST, TASK_END_USER_, TASK_END_USER_LIST, TASK_END_USER_NAME_, TASK_END_USER_NAME_LIST, FROM_TASK_END_DATE_, TO_TASK_END_DATE_, TASK_STATUS_, TASK_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, NODE_ID_, NODE_ID_LIST, PARENT_NODE_ID_, PARENT_NODE_ID_LIST, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, SUB_PROC_DEF_ID_LIST, ADJUST_SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_LIST, NODE_TYPE_,
                NODE_TYPE_LIST, NODE_CODE_, NODE_CODE_LIST, NODE_NAME_, NODE_NAME_LIST, PARENT_NODE_CODE_, PARENT_NODE_CODE_LIST, NODE_END_USER_, NODE_END_USER_LIST, NODE_END_USER_NAME_, NODE_END_USER_NAME_LIST, FROM_NODE_END_DATE_, TO_NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_DEF_CODE_LIST, ISOLATE_SUB_PROC_STATUS_, ISOLATE_SUB_PROC_STATUS_LIST, NODE_STATUS_, NODE_STATUS_LIST, FROM_NODE_CREATION_DATE_, TO_NODE_CREATION_DATE_, PROC_ID_, PROC_ID_LIST, ADJUST_PROC_DEF_ID_, ADJUST_PROC_DEF_ID_LIST, ISOLATE_SUB_PROC_NODE_ID_, ISOLATE_SUB_PROC_NODE_ID_LIST, BIZ_ID_, BIZ_ID_LIST, BIZ_TYPE_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_CODE_LIST, BIZ_NAME_, BIZ_NAME_LIST, BIZ_DESC_, BIZ_DESC_LIST, PROC_START_USER_, PROC_START_USER_LIST, PROC_START_USER_NAME_, PROC_START_USER_NAME_LIST,
                PROC_END_USER_, PROC_END_USER_LIST, PROC_END_USER_NAME_, PROC_END_USER_NAME_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_, PROC_STATUS_LIST, FROM_PROC_CREATION_DATE_, TO_PROC_CREATION_DATE_, PROC_DEF_ID_, PROC_DEF_ID_LIST, PROC_DEF_CODE_, PROC_DEF_CODE_LIST, PROC_DEF_NAME_, PROC_DEF_NAME_LIST, PROC_DEF_CAT_, PROC_DEF_CAT_LIST, VERSION_, VERSION_LIST, PROC_DEF_STATUS_, PROC_DEF_STATUS_LIST, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, dataScope);// 根据查询条件组装查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        if (page != null && limit != null && limit > 0) {// 分页
            sql = sql + " limit " + (page - 1) * limit + ", " + limit;
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public int countTask(String TASK_ID_, List<String> TASK_ID_LIST, String PREVIOUS_TASK_ID_, List<String> PREVIOUS_TASK_ID_LIST, String TASK_TYPE_, List<String> TASK_TYPE_LIST, String ASSIGNEE_, List<String> ASSIGNEE_LIST, String ASSIGNEE_NAME_, List<String> ASSIGNEE_NAME_LIST, String ACTION_, List<String> ACTION_LIST, Date FROM_DUE_DATE_, Date TO_DUE_DATE_, String CLAIM_, List<String> CLAIM_LIST, String FORWARDABLE_, List<String> FORWARDABLE_LIST, Integer PRIORITY_, List<Integer> PRIORITY_LIST, String FORWARD_STATUS_, List<String> FORWARD_STATUS_LIST, String TASK_END_USER_, List<String> TASK_END_USER_LIST, String TASK_END_USER_NAME_, List<String> TASK_END_USER_NAME_LIST, Date FROM_TASK_END_DATE_, Date TO_TASK_END_DATE_, String TASK_STATUS_, List<String> TASK_STATUS_LIST,
            Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String NODE_ID_, List<String> NODE_ID_LIST, String PARENT_NODE_ID_, List<String> PARENT_NODE_ID_LIST, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_,
            List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST, String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_NODE_CREATION_DATE_, Date TO_NODE_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST,
            String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, String dataScope) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaTask(true, TASK_ID_, TASK_ID_LIST, PREVIOUS_TASK_ID_, PREVIOUS_TASK_ID_LIST, TASK_TYPE_, TASK_TYPE_LIST, ASSIGNEE_, ASSIGNEE_LIST, ASSIGNEE_NAME_, ASSIGNEE_NAME_LIST, ACTION_, ACTION_LIST, FROM_DUE_DATE_, TO_DUE_DATE_, CLAIM_, CLAIM_LIST, FORWARDABLE_, FORWARDABLE_LIST, PRIORITY_, PRIORITY_LIST, FORWARD_STATUS_, FORWARD_STATUS_LIST, TASK_END_USER_, TASK_END_USER_LIST, TASK_END_USER_NAME_, TASK_END_USER_NAME_LIST, FROM_TASK_END_DATE_, TO_TASK_END_DATE_, TASK_STATUS_, TASK_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, NODE_ID_, NODE_ID_LIST, PARENT_NODE_ID_, PARENT_NODE_ID_LIST, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, SUB_PROC_DEF_ID_LIST, ADJUST_SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_LIST, NODE_TYPE_,
                NODE_TYPE_LIST, NODE_CODE_, NODE_CODE_LIST, NODE_NAME_, NODE_NAME_LIST, PARENT_NODE_CODE_, PARENT_NODE_CODE_LIST, NODE_END_USER_, NODE_END_USER_LIST, NODE_END_USER_NAME_, NODE_END_USER_NAME_LIST, FROM_NODE_END_DATE_, TO_NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_DEF_CODE_LIST, ISOLATE_SUB_PROC_STATUS_, ISOLATE_SUB_PROC_STATUS_LIST, NODE_STATUS_, NODE_STATUS_LIST, FROM_NODE_CREATION_DATE_, TO_NODE_CREATION_DATE_, PROC_ID_, PROC_ID_LIST, ADJUST_PROC_DEF_ID_, ADJUST_PROC_DEF_ID_LIST, ISOLATE_SUB_PROC_NODE_ID_, ISOLATE_SUB_PROC_NODE_ID_LIST, BIZ_ID_, BIZ_ID_LIST, BIZ_TYPE_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_CODE_LIST, BIZ_NAME_, BIZ_NAME_LIST, BIZ_DESC_, BIZ_DESC_LIST, PROC_START_USER_, PROC_START_USER_LIST, PROC_START_USER_NAME_, PROC_START_USER_NAME_LIST,
                PROC_END_USER_, PROC_END_USER_LIST, PROC_END_USER_NAME_, PROC_END_USER_NAME_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_, PROC_STATUS_LIST, FROM_PROC_CREATION_DATE_, TO_PROC_CREATION_DATE_, PROC_DEF_ID_, PROC_DEF_ID_LIST, PROC_DEF_CODE_, PROC_DEF_CODE_LIST, PROC_DEF_NAME_, PROC_DEF_NAME_LIST, PROC_DEF_CAT_, PROC_DEF_CAT_LIST, VERSION_, VERSION_LIST, PROC_DEF_STATUS_, PROC_DEF_STATUS_LIST, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, dataScope);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaTask(boolean count, String TASK_ID_, List<String> TASK_ID_LIST, String PREVIOUS_TASK_ID_, List<String> PREVIOUS_TASK_ID_LIST, String TASK_TYPE_, List<String> TASK_TYPE_LIST, String ASSIGNEE_, List<String> ASSIGNEE_LIST, String ASSIGNEE_NAME_, List<String> ASSIGNEE_NAME_LIST, String ACTION_, List<String> ACTION_LIST, Date FROM_DUE_DATE_, Date TO_DUE_DATE_, String CLAIM_, List<String> CLAIM_LIST, String FORWARDABLE_, List<String> FORWARDABLE_LIST, Integer PRIORITY_, List<Integer> PRIORITY_LIST, String FORWARD_STATUS_, List<String> FORWARD_STATUS_LIST, String TASK_END_USER_, List<String> TASK_END_USER_LIST, String TASK_END_USER_NAME_, List<String> TASK_END_USER_NAME_LIST, Date FROM_TASK_END_DATE_, Date TO_TASK_END_DATE_, String TASK_STATUS_,
            List<String> TASK_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String NODE_ID_, List<String> NODE_ID_LIST, String PARENT_NODE_ID_, List<String> PARENT_NODE_ID_LIST, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_,
            List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST, String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_NODE_CREATION_DATE_, Date TO_NODE_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST,
            String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, String dataScope) {// 组装查询SQL语句
        String sql;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String view = "FFV_TASK_P";
        if (FfService.DATA_SCOPE_PROC_DEF.equals(dataScope)) {
            view = "FFV_TASK_PD";
        }
        if (FfService.DATA_SCOPE_NODE.equals(dataScope)) {
            view = "FFV_TASK_N";
        }
        if (FfService.DATA_SCOPE_TASK.equals(dataScope)) {
            view = "FFV_TASK";
        }
        if (count) {
            sql = "select count(*) from " + view + " where 1 = 1";
        }
        else {
            sql = "select * from " + view + " where 1 = 1";
        }

        if (StringUtils.isNotEmpty(TASK_ID_)) {
            sql += " and TASK_ID_ = :TASK_ID_";
            paramMap.put("TASK_ID_", TASK_ID_);
        }
        if (TASK_ID_LIST != null && TASK_ID_LIST.size() > 0) {
            sql += " and TASK_ID_ in (:TASK_ID_LIST)";
            paramMap.put("TASK_ID_LIST", TASK_ID_LIST);
        }
        if (StringUtils.isNotEmpty(PREVIOUS_TASK_ID_)) {
            sql += " and PREVIOUS_TASK_ID_ = :PREVIOUS_TASK_ID_";
            paramMap.put("PREVIOUS_TASK_ID_", PREVIOUS_TASK_ID_);
        }
        if (PREVIOUS_TASK_ID_LIST != null && PREVIOUS_TASK_ID_LIST.size() > 0) {
            sql += " and PREVIOUS_TASK_ID_ in (:PREVIOUS_TASK_ID_LIST)";
            paramMap.put("PREVIOUS_TASK_ID_LIST", PREVIOUS_TASK_ID_LIST);
        }
        if (StringUtils.isNotEmpty(TASK_TYPE_)) {
            sql += " and TASK_TYPE_ = :TASK_TYPE_";
            paramMap.put("TASK_TYPE_", TASK_TYPE_);
        }
        if (TASK_TYPE_LIST != null && TASK_TYPE_LIST.size() > 0) {
            sql += " and TASK_TYPE_ in (:TASK_TYPE_LIST)";
            paramMap.put("TASK_TYPE_LIST", TASK_TYPE_LIST);
        }
        if (StringUtils.isNotEmpty(ASSIGNEE_)) {
            sql += " and ASSIGNEE_ = :ASSIGNEE_";
            paramMap.put("ASSIGNEE_", ASSIGNEE_);
        }
        if (ASSIGNEE_LIST != null && ASSIGNEE_LIST.size() > 0) {
            sql += " and ASSIGNEE_ in (:ASSIGNEE_LIST)";
            paramMap.put("ASSIGNEE_LIST", ASSIGNEE_LIST);
        }
        if (StringUtils.isNotEmpty(ASSIGNEE_NAME_)) {
            sql += " and ASSIGNEE_NAME_ like concat('%',:ASSIGNEE_NAME_,'%')";
            paramMap.put("ASSIGNEE_NAME_", ASSIGNEE_NAME_);
        }
        if (ASSIGNEE_NAME_LIST != null && ASSIGNEE_NAME_LIST.size() > 0) {
            sql += " and ASSIGNEE_NAME_ in (:ASSIGNEE_NAME_LIST)";
            paramMap.put("ASSIGNEE_NAME_LIST", ASSIGNEE_NAME_LIST);
        }
        if (StringUtils.isNotEmpty(ACTION_)) {
            sql += " and ACTION_ like concat('%',:ACTION_,'%')";
            paramMap.put("ACTION_", ACTION_);
        }
        if (ACTION_LIST != null && ACTION_LIST.size() > 0) {
            sql += " and ACTION_ in (:ACTION_LIST)";
            paramMap.put("ACTION_LIST", ACTION_LIST);
        }
        if (FROM_DUE_DATE_ != null) {
            sql += " and DUE_DATE_ >= :FROM_DUE_DATE_";
            paramMap.put("FROM_DUE_DATE_", FROM_DUE_DATE_);
        }
        if (TO_DUE_DATE_ != null) {
            sql += " and DUE_DATE_ <= :TO_DUE_DATE_";
            paramMap.put("TO_DUE_DATE_", TO_DUE_DATE_);
        }
        if (StringUtils.isNotEmpty(CLAIM_)) {
            sql += " and CLAIM_ = :CLAIM_";
            paramMap.put("CLAIM_", CLAIM_);
        }
        if (CLAIM_LIST != null && CLAIM_LIST.size() > 0) {
            sql += " and CLAIM_ in (:CLAIM_LIST)";
            paramMap.put("CLAIM_LIST", CLAIM_LIST);
        }
        if (StringUtils.isNotEmpty(FORWARDABLE_)) {
            sql += " and FORWARDABLE_ = :FORWARDABLE_";
            paramMap.put("FORWARDABLE_", FORWARDABLE_);
        }
        if (FORWARDABLE_LIST != null && FORWARDABLE_LIST.size() > 0) {
            sql += " and FORWARDABLE_ in (:FORWARDABLE_LIST)";
            paramMap.put("FORWARDABLE_LIST", FORWARDABLE_LIST);
        }
        if (PRIORITY_ != null) {
            sql += " and PRIORITY_ = :PRIORITY_";
            paramMap.put("PRIORITY_", PRIORITY_);
        }
        if (PRIORITY_LIST != null && PRIORITY_LIST.size() > 0) {
            sql += " and PRIORITY_ in (:PRIORITY_LIST)";
            paramMap.put("PRIORITY_LIST", PRIORITY_LIST);
        }
        if (StringUtils.isNotEmpty(FORWARD_STATUS_)) {
            sql += " and FORWARD_STATUS_ = :FORWARD_STATUS_";
            paramMap.put("FORWARD_STATUS_", FORWARD_STATUS_);
        }
        if (FORWARD_STATUS_LIST != null && FORWARD_STATUS_LIST.size() > 0) {
            sql += " and FORWARD_STATUS_ in (:FORWARD_STATUS_LIST)";
            paramMap.put("FORWARD_STATUS_LIST", FORWARD_STATUS_LIST);
        }
        if (StringUtils.isNotEmpty(TASK_END_USER_)) {
            sql += " and TASK_END_USER_ = :TASK_END_USER_";
            paramMap.put("TASK_END_USER_", TASK_END_USER_);
        }
        if (TASK_END_USER_LIST != null && TASK_END_USER_LIST.size() > 0) {
            sql += " and TASK_END_USER_ in (:TASK_END_USER_LIST)";
            paramMap.put("TASK_END_USER_LIST", TASK_END_USER_LIST);
        }
        if (StringUtils.isNotEmpty(TASK_END_USER_NAME_)) {
            sql += " and TASK_END_USER_NAME_ like concat('%',:TASK_END_USER_NAME_,'%')";
            paramMap.put("TASK_END_USER_NAME_", TASK_END_USER_NAME_);
        }
        if (TASK_END_USER_NAME_LIST != null && TASK_END_USER_NAME_LIST.size() > 0) {
            sql += " and TASK_END_USER_NAME_ in (:TASK_END_USER_NAME_LIST)";
            paramMap.put("TASK_END_USER_NAME_LIST", TASK_END_USER_NAME_LIST);
        }
        if (FROM_TASK_END_DATE_ != null) {
            sql += " and TASK_END_DATE_ >= :FROM_TASK_END_DATE_";
            paramMap.put("FROM_TASK_END_DATE_", FROM_TASK_END_DATE_);
        }
        if (TO_TASK_END_DATE_ != null) {
            sql += " and TASK_END_DATE_ <= :TO_TASK_END_DATE_";
            paramMap.put("TO_TASK_END_DATE_", TO_TASK_END_DATE_);
        }
        if (StringUtils.isNotEmpty(TASK_STATUS_)) {
            sql += " and TASK_STATUS_ = :TASK_STATUS_";
            paramMap.put("TASK_STATUS_", TASK_STATUS_);
        }
        if (TASK_STATUS_LIST != null && TASK_STATUS_LIST.size() > 0) {
            sql += " and TASK_STATUS_ in (:TASK_STATUS_LIST)";
            paramMap.put("TASK_STATUS_LIST", TASK_STATUS_LIST);
        }
        if (FROM_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ >= :FROM_CREATION_DATE_";
            paramMap.put("FROM_CREATION_DATE_", FROM_CREATION_DATE_);
        }
        if (TO_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ <= :TO_CREATION_DATE_";
            paramMap.put("TO_CREATION_DATE_", TO_CREATION_DATE_);
        }

        if (StringUtils.isNotEmpty(NODE_ID_)) {
            sql += " and NODE_ID_ = :NODE_ID_";
            paramMap.put("NODE_ID_", NODE_ID_);
        }
        if (NODE_ID_LIST != null && NODE_ID_LIST.size() > 0) {
            sql += " and NODE_ID_ in (:NODE_ID_LIST)";
            paramMap.put("NODE_ID_LIST", NODE_ID_LIST);
        }
        if (StringUtils.isNotEmpty(PARENT_NODE_ID_)) {
            sql += " and PARENT_NODE_ID_ = :PARENT_NODE_ID_";
            paramMap.put("PARENT_NODE_ID_", PARENT_NODE_ID_);
        }
        if (PARENT_NODE_ID_LIST != null && PARENT_NODE_ID_LIST.size() > 0) {
            sql += " and PARENT_NODE_ID_ in (:PARENT_NODE_ID_LIST)";
            paramMap.put("PARENT_NODE_ID_LIST", PARENT_NODE_ID_LIST);
        }
        if (StringUtils.isNotEmpty(PREVIOUS_NODE_IDS_)) {
            sql += " and PREVIOUS_NODE_IDS_ like concat('%',:PREVIOUS_NODE_IDS_,'%')";
            paramMap.put("PREVIOUS_NODE_IDS_", PREVIOUS_NODE_IDS_);
        }
        if (StringUtils.isNotEmpty(LAST_COMPLETE_NODE_IDS_)) {
            sql += " and LAST_COMPLETE_NODE_IDS_ like concat('%',:LAST_COMPLETE_NODE_IDS_,'%')";
            paramMap.put("LAST_COMPLETE_NODE_IDS_", LAST_COMPLETE_NODE_IDS_);
        }
        if (StringUtils.isNotEmpty(SUB_PROC_DEF_ID_)) {
            sql += " and SUB_PROC_DEF_ID_ = :SUB_PROC_DEF_ID_";
            paramMap.put("SUB_PROC_DEF_ID_", SUB_PROC_DEF_ID_);
        }
        if (SUB_PROC_DEF_ID_LIST != null && SUB_PROC_DEF_ID_LIST.size() > 0) {
            sql += " and SUB_PROC_DEF_ID_ in (:SUB_PROC_DEF_ID_LIST)";
            paramMap.put("SUB_PROC_DEF_ID_LIST", SUB_PROC_DEF_ID_LIST);
        }
        if (StringUtils.isNotEmpty(ADJUST_SUB_PROC_DEF_ID_)) {
            sql += " and ADJUST_SUB_PROC_DEF_ID_ = :ADJUST_SUB_PROC_DEF_ID_";
            paramMap.put("ADJUST_SUB_PROC_DEF_ID_", ADJUST_SUB_PROC_DEF_ID_);
        }
        if (ADJUST_SUB_PROC_DEF_ID_LIST != null && ADJUST_SUB_PROC_DEF_ID_LIST.size() > 0) {
            sql += " and ADJUST_SUB_PROC_DEF_ID_ in (:ADJUST_SUB_PROC_DEF_ID_LIST)";
            paramMap.put("ADJUST_SUB_PROC_DEF_ID_LIST", ADJUST_SUB_PROC_DEF_ID_LIST);
        }
        if (StringUtils.isNotEmpty(NODE_TYPE_)) {
            sql += " and NODE_TYPE_ = :NODE_TYPE_";
            paramMap.put("NODE_TYPE_", NODE_TYPE_);
        }
        if (NODE_TYPE_LIST != null && NODE_TYPE_LIST.size() > 0) {
            sql += " and NODE_TYPE_ in (:NODE_TYPE_LIST)";
            paramMap.put("NODE_TYPE_LIST", NODE_TYPE_LIST);
        }
        if (StringUtils.isNotEmpty(NODE_CODE_)) {
            sql += " and NODE_CODE_ = :NODE_CODE_";
            paramMap.put("NODE_CODE_", NODE_CODE_);
        }
        if (NODE_CODE_LIST != null && NODE_CODE_LIST.size() > 0) {
            sql += " and NODE_CODE_ in (:NODE_CODE_LIST)";
            paramMap.put("NODE_CODE_LIST", NODE_CODE_LIST);
        }
        if (StringUtils.isNotEmpty(NODE_NAME_)) {
            sql += " and NODE_NAME_ like concat('%',:NODE_NAME_,'%')";
            paramMap.put("NODE_NAME_", NODE_NAME_);
        }
        if (NODE_NAME_LIST != null && NODE_NAME_LIST.size() > 0) {
            sql += " and NODE_NAME_ in (:NODE_NAME_LIST)";
            paramMap.put("NODE_NAME_LIST", NODE_NAME_LIST);
        }
        if (StringUtils.isNotEmpty(PARENT_NODE_CODE_)) {
            sql += " and PARENT_NODE_CODE_ = :PARENT_NODE_CODE_";
            paramMap.put("PARENT_NODE_CODE_", PARENT_NODE_CODE_);
        }
        if (PARENT_NODE_CODE_LIST != null && PARENT_NODE_CODE_LIST.size() > 0) {
            sql += " and PARENT_NODE_CODE_ in (:PARENT_NODE_CODE_LIST)";
            paramMap.put("PARENT_NODE_CODE_LIST", PARENT_NODE_CODE_LIST);
        }
        if (StringUtils.isNotEmpty(NODE_END_USER_)) {
            sql += " and NODE_END_USER_ = :NODE_END_USER_";
            paramMap.put("NODE_END_USER_", NODE_END_USER_);
        }
        if (NODE_END_USER_LIST != null && NODE_END_USER_LIST.size() > 0) {
            sql += " and NODE_END_USER_ in (:NODE_END_USER_LIST)";
            paramMap.put("NODE_END_USER_LIST", NODE_END_USER_LIST);
        }
        if (StringUtils.isNotEmpty(NODE_END_USER_NAME_)) {
            sql += " and NODE_END_USER_NAME_ like concat('%',:NODE_END_USER_NAME_,'%')";
            paramMap.put("NODE_END_USER_NAME_", NODE_END_USER_NAME_);
        }
        if (NODE_END_USER_NAME_LIST != null && NODE_END_USER_NAME_LIST.size() > 0) {
            sql += " and NODE_END_USER_NAME_ in (:NODE_END_USER_NAME_LIST)";
            paramMap.put("NODE_END_USER_NAME_LIST", NODE_END_USER_NAME_LIST);
        }
        if (FROM_NODE_END_DATE_ != null) {
            sql += " and NODE_END_DATE_ >= :FROM_NODE_END_DATE_";
            paramMap.put("FROM_NODE_END_DATE_", FROM_NODE_END_DATE_);
        }
        if (TO_NODE_END_DATE_ != null) {
            sql += " and NODE_END_DATE_ <= :TO_NODE_END_DATE_";
            paramMap.put("TO_NODE_END_DATE_", TO_NODE_END_DATE_);
        }
        if (StringUtils.isNotEmpty(ISOLATE_SUB_PROC_DEF_CODE_)) {
            sql += " and ISOLATE_SUB_PROC_DEF_CODE_ = :ISOLATE_SUB_PROC_DEF_CODE_";
            paramMap.put("ISOLATE_SUB_PROC_DEF_CODE_", ISOLATE_SUB_PROC_DEF_CODE_);
        }
        if (ISOLATE_SUB_PROC_DEF_CODE_LIST != null && ISOLATE_SUB_PROC_DEF_CODE_LIST.size() > 0) {
            sql += " and ISOLATE_SUB_PROC_DEF_CODE_ in (:ISOLATE_SUB_PROC_DEF_CODE_LIST)";
            paramMap.put("ISOLATE_SUB_PROC_DEF_CODE_LIST", ISOLATE_SUB_PROC_DEF_CODE_LIST);
        }
        if (StringUtils.isNotEmpty(ISOLATE_SUB_PROC_STATUS_)) {
            sql += " and ISOLATE_SUB_PROC_STATUS_ = :ISOLATE_SUB_PROC_STATUS_";
            paramMap.put("ISOLATE_SUB_PROC_STATUS_", ISOLATE_SUB_PROC_STATUS_);
        }
        if (ISOLATE_SUB_PROC_STATUS_LIST != null && ISOLATE_SUB_PROC_STATUS_LIST.size() > 0) {
            sql += " and ISOLATE_SUB_PROC_STATUS_ in (:ISOLATE_SUB_PROC_STATUS_LIST)";
            paramMap.put("ISOLATE_SUB_PROC_STATUS_LIST", ISOLATE_SUB_PROC_STATUS_LIST);
        }
        if (StringUtils.isNotEmpty(NODE_STATUS_)) {
            sql += " and NODE_STATUS_ = :NODE_STATUS_";
            paramMap.put("NODE_STATUS_", NODE_STATUS_);
        }
        if (NODE_STATUS_LIST != null && NODE_STATUS_LIST.size() > 0) {
            sql += " and NODE_STATUS_ in (:NODE_STATUS_LIST)";
            paramMap.put("NODE_STATUS_LIST", NODE_STATUS_LIST);
        }
        if (FROM_NODE_CREATION_DATE_ != null) {
            sql += " and NODE_CREATION_DATE_ >= :FROM_NODE_CREATION_DATE_";
            paramMap.put("FROM_NODE_CREATION_DATE_", FROM_NODE_CREATION_DATE_);
        }
        if (TO_NODE_CREATION_DATE_ != null) {
            sql += " and NODE_CREATION_DATE_ <= :TO_NODE_CREATION_DATE_";
            paramMap.put("TO_NODE_CREATION_DATE_", TO_NODE_CREATION_DATE_);
        }

        if (StringUtils.isNotEmpty(PROC_ID_)) {
            sql += " and PROC_ID_ = :PROC_ID_";
            paramMap.put("PROC_ID_", PROC_ID_);
        }
        if (PROC_ID_LIST != null && PROC_ID_LIST.size() > 0) {
            sql += " and PROC_ID_ in (:PROC_ID_LIST)";
            paramMap.put("PROC_ID_LIST", PROC_ID_LIST);
        }
        if (StringUtils.isNotEmpty(ADJUST_PROC_DEF_ID_)) {
            sql += " and ADJUST_PROC_DEF_ID_ = :ADJUST_PROC_DEF_ID_";
            paramMap.put("ADJUST_PROC_DEF_ID_", ADJUST_PROC_DEF_ID_);
        }
        if (ADJUST_PROC_DEF_ID_LIST != null && ADJUST_PROC_DEF_ID_LIST.size() > 0) {
            sql += " and ADJUST_PROC_DEF_ID_ in (:ADJUST_PROC_DEF_ID_LIST)";
            paramMap.put("ADJUST_PROC_DEF_ID_LIST", ADJUST_PROC_DEF_ID_LIST);
        }
        if (StringUtils.isNotEmpty(ISOLATE_SUB_PROC_NODE_ID_)) {
            sql += " and ISOLATE_SUB_PROC_NODE_ID_ = :ISOLATE_SUB_PROC_NODE_ID_";
            paramMap.put("ISOLATE_SUB_PROC_NODE_ID_", ISOLATE_SUB_PROC_NODE_ID_);
        }
        if (ISOLATE_SUB_PROC_NODE_ID_LIST != null && ISOLATE_SUB_PROC_NODE_ID_LIST.size() > 0) {
            sql += " and ISOLATE_SUB_PROC_NODE_ID_ in (:ISOLATE_SUB_PROC_NODE_ID_LIST)";
            paramMap.put("ISOLATE_SUB_PROC_NODE_ID_LIST", ISOLATE_SUB_PROC_NODE_ID_LIST);
        }
        if (StringUtils.isNotEmpty(BIZ_ID_)) {
            sql += " and BIZ_ID_ = :BIZ_ID_";
            paramMap.put("BIZ_ID_", BIZ_ID_);
        }
        if (BIZ_ID_LIST != null && BIZ_ID_LIST.size() > 0) {
            sql += " and BIZ_ID_ in (:BIZ_ID_LIST)";
            paramMap.put("BIZ_ID_LIST", BIZ_ID_LIST);
        }
        if (StringUtils.isNotEmpty(BIZ_TYPE_)) {
            sql += " and BIZ_TYPE_ = :BIZ_TYPE_";
            paramMap.put("BIZ_TYPE_", BIZ_TYPE_);
        }
        if (BIZ_TYPE_LIST != null && BIZ_TYPE_LIST.size() > 0) {
            sql += " and BIZ_TYPE_ in (:BIZ_TYPE_LIST)";
            paramMap.put("BIZ_TYPE_LIST", BIZ_TYPE_LIST);
        }
        if (StringUtils.isNotEmpty(BIZ_CODE_)) {
            sql += " and BIZ_CODE_ = :BIZ_CODE_";
            paramMap.put("BIZ_CODE_", BIZ_CODE_);
        }
        if (BIZ_CODE_LIST != null && BIZ_CODE_LIST.size() > 0) {
            sql += " and BIZ_CODE_ in (:BIZ_CODE_LIST)";
            paramMap.put("BIZ_CODE_LIST", BIZ_CODE_LIST);
        }
        if (StringUtils.isNotEmpty(BIZ_NAME_)) {
            sql += " and BIZ_NAME_ like concat('%',:BIZ_NAME_,'%')";
            paramMap.put("BIZ_NAME_", BIZ_NAME_);
        }
        if (BIZ_NAME_LIST != null && BIZ_NAME_LIST.size() > 0) {
            sql += " and BIZ_NAME_ in (:BIZ_NAME_LIST)";
            paramMap.put("BIZ_NAME_LIST", BIZ_NAME_LIST);
        }
        if (StringUtils.isNotEmpty(BIZ_DESC_)) {
            sql += " and BIZ_DESC_ like concat('%',:BIZ_DESC_,'%')";
            paramMap.put("BIZ_DESC_", BIZ_DESC_);
        }
        if (BIZ_DESC_LIST != null && BIZ_DESC_LIST.size() > 0) {
            sql += " and BIZ_DESC_ in (:BIZ_DESC_LIST)";
            paramMap.put("BIZ_DESC_LIST", BIZ_DESC_LIST);
        }
        if (StringUtils.isNotEmpty(PROC_START_USER_)) {
            sql += " and PROC_START_USER_ = :PROC_START_USER_";
            paramMap.put("PROC_START_USER_", PROC_START_USER_);
        }
        if (PROC_START_USER_LIST != null && PROC_START_USER_LIST.size() > 0) {
            sql += " and PROC_START_USER_ in (:PROC_START_USER_LIST)";
            paramMap.put("PROC_START_USER_LIST", PROC_START_USER_LIST);
        }
        if (StringUtils.isNotEmpty(PROC_START_USER_NAME_)) {
            sql += " and PROC_START_USER_NAME_ like concat('%',:PROC_START_USER_NAME_,'%')";
            paramMap.put("PROC_START_USER_NAME_", PROC_START_USER_NAME_);
        }
        if (PROC_START_USER_NAME_LIST != null && PROC_START_USER_NAME_LIST.size() > 0) {
            sql += " and PROC_START_USER_NAME_ in (:PROC_START_USER_NAME_LIST)";
            paramMap.put("PROC_START_USER_NAME_LIST", PROC_START_USER_NAME_LIST);
        }
        if (StringUtils.isNotEmpty(PROC_END_USER_)) {
            sql += " and PROC_END_USER_ = :PROC_END_USER_";
            paramMap.put("PROC_END_USER_", PROC_END_USER_);
        }
        if (PROC_END_USER_LIST != null && PROC_END_USER_LIST.size() > 0) {
            sql += " and PROC_END_USER_ in (:PROC_END_USER_LIST)";
            paramMap.put("PROC_END_USER_LIST", PROC_END_USER_LIST);
        }
        if (StringUtils.isNotEmpty(PROC_END_USER_NAME_)) {
            sql += " and PROC_END_USER_NAME_ like concat('%',:PROC_END_USER_NAME_,'%')";
            paramMap.put("PROC_END_USER_NAME_", PROC_END_USER_NAME_);
        }
        if (PROC_END_USER_NAME_LIST != null && PROC_END_USER_NAME_LIST.size() > 0) {
            sql += " and PROC_END_USER_NAME_ in (:PROC_END_USER_NAME_LIST)";
            paramMap.put("PROC_END_USER_NAME_LIST", PROC_END_USER_NAME_LIST);
        }
        if (FROM_PROC_END_DATE_ != null) {
            sql += " and PROC_END_DATE_ >= :FROM_PROC_END_DATE_";
            paramMap.put("FROM_PROC_END_DATE_", FROM_PROC_END_DATE_);
        }
        if (TO_PROC_END_DATE_ != null) {
            sql += " and PROC_END_DATE_ <= :TO_PROC_END_DATE_";
            paramMap.put("TO_PROC_END_DATE_", TO_PROC_END_DATE_);
        }
        if (StringUtils.isNotEmpty(PROC_STATUS_)) {
            sql += " and PROC_STATUS_ = :PROC_STATUS_";
            paramMap.put("PROC_STATUS_", PROC_STATUS_);
        }
        if (PROC_STATUS_LIST != null && PROC_STATUS_LIST.size() > 0) {
            sql += " and PROC_STATUS_ in (:PROC_STATUS_LIST)";
            paramMap.put("PROC_STATUS_LIST", PROC_STATUS_LIST);
        }
        if (FROM_PROC_CREATION_DATE_ != null) {
            sql += " and PROC_CREATION_DATE_ >= :FROM_PROC_CREATION_DATE_";
            paramMap.put("FROM_PROC_CREATION_DATE_", FROM_PROC_CREATION_DATE_);
        }
        if (TO_PROC_CREATION_DATE_ != null) {
            sql += " and PROC_CREATION_DATE_ <= :TO_PROC_CREATION_DATE_";
            paramMap.put("TO_PROC_CREATION_DATE_", TO_PROC_CREATION_DATE_);
        }

        if (StringUtils.isNotEmpty(PROC_DEF_ID_)) {
            sql += " and PROC_DEF_ID_ = :PROC_DEF_ID_";
            paramMap.put("PROC_DEF_ID_", PROC_DEF_ID_);
        }
        if (PROC_DEF_ID_LIST != null && PROC_DEF_ID_LIST.size() > 0) {
            sql += " and PROC_DEF_ID_ in (:PROC_DEF_ID_LIST)";
            paramMap.put("PROC_DEF_ID_LIST", PROC_DEF_ID_LIST);
        }
        if (StringUtils.isNotEmpty(PROC_DEF_CODE_)) {
            sql += " and PROC_DEF_CODE_ = :PROC_DEF_CODE_";
            paramMap.put("PROC_DEF_CODE_", PROC_DEF_CODE_);
        }
        if (PROC_DEF_CODE_LIST != null && PROC_DEF_CODE_LIST.size() > 0) {
            sql += " and PROC_DEF_CODE_ in (:PROC_DEF_CODE_LIST)";
            paramMap.put("PROC_DEF_CODE_LIST", PROC_DEF_CODE_LIST);
        }
        if (StringUtils.isNotEmpty(PROC_DEF_NAME_)) {
            sql += " and PROC_DEF_NAME_ like concat('%',:PROC_DEF_NAME_,'%')";
            paramMap.put("PROC_DEF_NAME_", PROC_DEF_NAME_);
        }
        if (PROC_DEF_NAME_LIST != null && PROC_DEF_NAME_LIST.size() > 0) {
            sql += " and PROC_DEF_NAME_ in (:PROC_DEF_NAME_LIST)";
            paramMap.put("PROC_DEF_NAME_LIST", PROC_DEF_NAME_LIST);
        }
        if (StringUtils.isNotEmpty(PROC_DEF_CAT_)) {
            sql += " and PROC_DEF_CAT_ like :PROC_DEF_CAT_ || '%'";
            paramMap.put("PROC_DEF_CAT_", PROC_DEF_CAT_);
        }
        if (PROC_DEF_CAT_LIST != null && PROC_DEF_CAT_LIST.size() > 0) {
            sql += " and PROC_DEF_CAT_ in (:PROC_DEF_CAT_LIST)";
            paramMap.put("PROC_DEF_CAT_LIST", PROC_DEF_CAT_LIST);
        }
        if (VERSION_ != null) {
            sql += " and VERSION_ = :VERSION_";
            paramMap.put("VERSION_", VERSION_);
        }
        if (VERSION_LIST != null && VERSION_LIST.size() > 0) {
            sql += " and VERSION_ in (:VERSION_LIST)";
            paramMap.put("VERSION_LIST", VERSION_LIST);
        }
        if (StringUtils.isNotEmpty(PROC_DEF_STATUS_)) {
            sql += " and PROC_DEF_STATUS_ = :PROC_DEF_STATUS_";
            paramMap.put("PROC_DEF_STATUS_", PROC_DEF_STATUS_);
        }
        if (PROC_DEF_STATUS_LIST != null && PROC_DEF_STATUS_LIST.size() > 0) {
            sql += " and PROC_DEF_STATUS_ in (:PROC_DEF_STATUS_LIST)";
            paramMap.put("PROC_DEF_STATUS_LIST", PROC_DEF_STATUS_LIST);
        }

        if (emptyParentNode != null && emptyParentNode.equals(true)) {
            sql += " and PARENT_NODE_ID_ is null";
        }
        if (emptyPreviousNodes != null && emptyPreviousNodes.equals(true)) {
            sql += " and PREVIOUS_NODE_IDS_ is null";
        }
        if (emptyLastCompleteNodes != null && emptyLastCompleteNodes.equals(true)) {
            sql += " and LAST_COMPLETE_NODE_IDS_ is null";
        }

        if (!count) {
            sql += " order by CREATION_DATE_ desc";
        }

        return new OdSqlCriteria(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectTaskByIdList(List<String> TASK_ID_LIST) {
        if (TASK_ID_LIST == null || TASK_ID_LIST.size() == 0) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder(TASK_ID_LIST.size() * 50 + 200);
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append("select * from FFV_TASK where TASK_ID_ in (:TASK_ID_LIST)");
        paramMap.put("TASK_ID_LIST", TASK_ID_LIST);
        sql.append(" order by FIELD(TASK_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < TASK_ID_LIST.size(); i++) {
            sql.append(" '").append(TASK_ID_LIST.get(i)).append("'");
            if (i < TASK_ID_LIST.size() - 1) {
                sql.append(",");
            }
            else {
                sql.append(")");
            }
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql.toString(), paramMap);
    }

    @Override
    public int insertTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, String TASK_TYPE_, String ASSIGNEE_, String ASSIGNEE_NAME_, String ACTION_, Date DUE_DATE_, String CLAIM_, String FORWARDABLE_, Integer PRIORITY_, String FORWARD_STATUS_, String TASK_END_USER_, String TASK_END_USER_NAME_, Date TASK_END_DATE_, String NEXT_CANDIDATE_, String TASK_STATUS_, Date CREATION_DATE_) {
        String sql = "insert into FF_TASK (TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, ACTION_, DUE_DATE_, CLAIM_, FORWARDABLE_, PRIORITY_, FORWARD_STATUS_, TASK_END_USER_, TASK_END_USER_NAME_, TASK_END_DATE_, NEXT_CANDIDATE_, TASK_STATUS_, CREATION_DATE_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int count = ffJdbcTemplate.update(sql, TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, ACTION_, DUE_DATE_, CLAIM_, FORWARDABLE_, PRIORITY_, FORWARD_STATUS_, TASK_END_USER_, TASK_END_USER_NAME_, TASK_END_DATE_, NEXT_CANDIDATE_, TASK_STATUS_, CREATION_DATE_);

        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_INSERT);

        return count;
    }

    @Override
    public int updateTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, String TASK_TYPE_, String ASSIGNEE_, String ASSIGNEE_NAME_, String ACTION_, Date DUE_DATE_, String CLAIM_, String FORWARDABLE_, Integer PRIORITY_, String FORWARD_STATUS_, String TASK_END_USER_, String TASK_END_USER_NAME_, Date TASK_END_DATE_, String NEXT_CANDIDATE_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set NODE_ID_ = ?, PREVIOUS_TASK_ID_ = ?, TASK_TYPE_ = ?, ASSIGNEE_ = ?, ASSIGNEE_NAME_ = ?, ACTION_ = ?, DUE_DATE_ = ?, CLAIM_ = ?, FORWARDABLE_ = ?, PRIORITY_ = ?, FORWARD_STATUS_ = ?, TASK_END_USER_ = ?, TASK_END_USER_NAME_ = ?, TASK_END_DATE_ = ?, NEXT_CANDIDATE_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, ACTION_, DUE_DATE_, CLAIM_, FORWARDABLE_, PRIORITY_, FORWARD_STATUS_, TASK_END_USER_, TASK_END_USER_NAME_, TASK_END_DATE_, NEXT_CANDIDATE_, TASK_ID_);
    }

    @Override
    public int updateTaskAssignee(String TASK_ID_, String ASSIGNEE_, String ASSIGNEE_NAME_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set ASSIGNEE_ = ?, ASSIGNEE_NAME_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, ASSIGNEE_, ASSIGNEE_NAME_, TASK_ID_);
    }

    @Override
    public int updateTaskClaim(String TASK_ID_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set CLAIM_ = '0' where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, TASK_ID_);
    }

    @Override
    public int updateTaskForwardStatus(String TASK_ID_, String FORWARD_STATUS_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set FORWARD_STATUS_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, FORWARD_STATUS_, TASK_ID_);
    }

    @Override
    public int updateTaskNextCandidate(String TASK_ID_, String NEXT_CANDIDATE_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set NEXT_CANDIDATE_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, NEXT_CANDIDATE_, TASK_ID_);
    }

    @Override
    public int updateTaskStatus(String TASK_ID_, String TASK_STATUS_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set TASK_STATUS_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, TASK_STATUS_, TASK_ID_);
    }

    @Override
    public int updateTaskStatus(String TASK_ID_, String TASK_END_USER_, String TASK_END_USER_NAME_, Date TASK_END_DATE_, String TASK_STATUS_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set TASK_END_USER_ = ?, TASK_END_USER_NAME_ = ?, TASK_END_DATE_ = ?, TASK_STATUS_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, TASK_END_USER_, TASK_END_USER_NAME_, TASK_END_DATE_, TASK_STATUS_, TASK_ID_);
    }

    @Override
    public int updateTaskStatus(String TASK_ID_, String TASK_END_USER_, String TASK_END_USER_NAME_, Date TASK_END_DATE_, String NEXT_CANDIDATE_, String TASK_STATUS_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set TASK_END_USER_ = ?, TASK_END_USER_NAME_ = ?, TASK_END_DATE_ = ?, NEXT_CANDIDATE_ = ?, TASK_STATUS_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, TASK_END_USER_, TASK_END_USER_NAME_, TASK_END_DATE_, NEXT_CANDIDATE_, TASK_STATUS_, TASK_ID_);
    }

    @Override
    public int deleteTask(String TASK_ID_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_DELETE);

        String sql = "delete from FF_TASK where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, TASK_ID_);
    }

    @Override
    public int deleteTaskByNodeId(String NODE_ID_) {
        List<Map<String, Object>> taskList = selectTask(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, NODE_ID_, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, -1, FfService.DATA_SCOPE_TASK);
        for (Map<String, Object> task : taskList) {
            deleteTask((String) task.get("TASK_ID_"));
        }

        return taskList.size();
    }
}