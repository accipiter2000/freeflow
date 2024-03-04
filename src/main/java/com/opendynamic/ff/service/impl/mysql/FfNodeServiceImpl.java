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
import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.service.FfOperationService;
import com.opendynamic.ff.service.FfService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfNodeServiceImpl implements FfNodeService {
    @Autowired
    private FfOperationService ffOperationService;
    @Autowired
    private JdbcTemplate ffJdbcTemplate;

    @Override
    public Map<String, Object> getTaskStatistic(String NODE_ID_) {
        String sql = "select count(*) TOTAL, count(case when T.TASK_STATUS_ = '9' then 1 else null end) COMPLETE from FF_TASK T where T.NODE_ID_ = ? and T.TASK_STATUS_ in ('1', '9') and T.TASK_TYPE_ != 'FORWARD_TASK'";
        Map<String, Object> result = ffJdbcTemplate.queryForMap(sql, NODE_ID_);
        Map<String, Object> taskStatistic = new HashMap<>();
        taskStatistic.put("TOTAL", ((Long) result.get("TOTAL")).doubleValue());
        taskStatistic.put("COMPLETE", ((Long) result.get("COMPLETE")).doubleValue());

        return taskStatistic;
    }

    @Override
    public Map<String, Object> getChildNodeStatistic(String NODE_ID_) {
        String sql = "select count(*) TOTAL, count(case when N.NODE_STATUS_ = '9' then 1 else null end) COMPLETE from FF_NODE N where N.PARENT_NODE_ID_ = ? and N.NODE_STATUS_ in ('1', '9')";
        Map<String, Object> result = ffJdbcTemplate.queryForMap(sql, NODE_ID_);
        Map<String, Object> subProcStatistic = new HashMap<>();
        subProcStatistic.put("TOTAL", ((Long) result.get("TOTAL")).doubleValue());
        subProcStatistic.put("COMPLETE", ((Long) result.get("COMPLETE")).doubleValue());

        return subProcStatistic;
    }

    @Override
    public Map<String, Object> loadNode(String NODE_ID_) {
        String sql = "select * from FFV_NODE where NODE_ID_ = ?";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, NODE_ID_);
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectNode(String NODE_ID_, List<String> NODE_ID_LIST, String PARENT_NODE_ID_, List<String> PARENT_NODE_ID_LIST, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST,
            String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST, String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST,
            String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, Integer page, Integer limit, String dataScope) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaNode(false, NODE_ID_, NODE_ID_LIST, PARENT_NODE_ID_, PARENT_NODE_ID_LIST, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, SUB_PROC_DEF_ID_LIST, ADJUST_SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_LIST, NODE_TYPE_, NODE_TYPE_LIST, NODE_CODE_, NODE_CODE_LIST, NODE_NAME_, NODE_NAME_LIST, PARENT_NODE_CODE_, PARENT_NODE_CODE_LIST, NODE_END_USER_, NODE_END_USER_LIST, NODE_END_USER_NAME_, NODE_END_USER_NAME_LIST, FROM_NODE_END_DATE_, TO_NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_DEF_CODE_LIST, ISOLATE_SUB_PROC_STATUS_, ISOLATE_SUB_PROC_STATUS_LIST, NODE_STATUS_, NODE_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PROC_ID_, PROC_ID_LIST, ADJUST_PROC_DEF_ID_, ADJUST_PROC_DEF_ID_LIST, ISOLATE_SUB_PROC_NODE_ID_,
                ISOLATE_SUB_PROC_NODE_ID_LIST, BIZ_ID_, BIZ_ID_LIST, BIZ_TYPE_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_CODE_LIST, BIZ_NAME_, BIZ_NAME_LIST, BIZ_DESC_, BIZ_DESC_LIST, PROC_START_USER_, PROC_START_USER_LIST, PROC_START_USER_NAME_, PROC_START_USER_NAME_LIST, PROC_END_USER_, PROC_END_USER_LIST, PROC_END_USER_NAME_, PROC_END_USER_NAME_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_, PROC_STATUS_LIST, FROM_PROC_CREATION_DATE_, TO_PROC_CREATION_DATE_, PROC_DEF_ID_, PROC_DEF_ID_LIST, PROC_DEF_CODE_, PROC_DEF_CODE_LIST, PROC_DEF_NAME_, PROC_DEF_NAME_LIST, PROC_DEF_CAT_, PROC_DEF_CAT_LIST, VERSION_, VERSION_LIST, PROC_DEF_STATUS_, PROC_DEF_STATUS_LIST, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, dataScope);// 根据查询条件组装查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        if (page != null && limit != null && limit > 0) {// 分页
            sql = sql + " limit " + (page - 1) * limit + ", " + limit;
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public int countNode(String NODE_ID_, List<String> NODE_ID_LIST, String PARENT_NODE_ID_, List<String> PARENT_NODE_ID_LIST, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST,
            String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST, String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST,
            String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, String dataScope) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaNode(true, NODE_ID_, NODE_ID_LIST, PARENT_NODE_ID_, PARENT_NODE_ID_LIST, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, SUB_PROC_DEF_ID_LIST, ADJUST_SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_LIST, NODE_TYPE_, NODE_TYPE_LIST, NODE_CODE_, NODE_CODE_LIST, NODE_NAME_, NODE_NAME_LIST, PARENT_NODE_CODE_, PARENT_NODE_CODE_LIST, NODE_END_USER_, NODE_END_USER_LIST, NODE_END_USER_NAME_, NODE_END_USER_NAME_LIST, FROM_NODE_END_DATE_, TO_NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_DEF_CODE_LIST, ISOLATE_SUB_PROC_STATUS_, ISOLATE_SUB_PROC_STATUS_LIST, NODE_STATUS_, NODE_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PROC_ID_, PROC_ID_LIST, ADJUST_PROC_DEF_ID_, ADJUST_PROC_DEF_ID_LIST, ISOLATE_SUB_PROC_NODE_ID_,
                ISOLATE_SUB_PROC_NODE_ID_LIST, BIZ_ID_, BIZ_ID_LIST, BIZ_TYPE_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_CODE_LIST, BIZ_NAME_, BIZ_NAME_LIST, BIZ_DESC_, BIZ_DESC_LIST, PROC_START_USER_, PROC_START_USER_LIST, PROC_START_USER_NAME_, PROC_START_USER_NAME_LIST, PROC_END_USER_, PROC_END_USER_LIST, PROC_END_USER_NAME_, PROC_END_USER_NAME_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_, PROC_STATUS_LIST, FROM_PROC_CREATION_DATE_, TO_PROC_CREATION_DATE_, PROC_DEF_ID_, PROC_DEF_ID_LIST, PROC_DEF_CODE_, PROC_DEF_CODE_LIST, PROC_DEF_NAME_, PROC_DEF_NAME_LIST, PROC_DEF_CAT_, PROC_DEF_CAT_LIST, VERSION_, VERSION_LIST, PROC_DEF_STATUS_, PROC_DEF_STATUS_LIST, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, dataScope);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaNode(boolean count, String NODE_ID_, List<String> NODE_ID_LIST, String PARENT_NODE_ID_, List<String> PARENT_NODE_ID_LIST, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_,
            List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST, String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST,
            String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, String dataScope) {// 组装查询SQL语句
        String sql;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String view = "FFV_NODE";
        if (FfService.DATA_SCOPE_PROC_DEF.equals(dataScope)) {
            view = "FFV_NODE_PD";
        }
        if (FfService.DATA_SCOPE_PROC.equals(dataScope)) {
            view = "FFV_NODE_P";
        }
        if (count) {
            sql = "select count(*) from " + view + " where 1 = 1";
        }
        else {
            sql = "select * from " + view + " where 1 = 1";
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
        if (FROM_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ >= :FROM_CREATION_DATE_";
            paramMap.put("FROM_CREATION_DATE_", FROM_CREATION_DATE_);
        }
        if (TO_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ <= :TO_CREATION_DATE_";
            paramMap.put("TO_CREATION_DATE_", TO_CREATION_DATE_);
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
    public List<Map<String, Object>> selectParentNode(String NODE_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST,
            String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST,
            Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, Boolean recursive, Boolean includeSelf, String dataScope) {
        if (StringUtils.isEmpty(NODE_ID_)) {
            throw new RuntimeException("errors.idRequired");
        }

        String view = "FFV_NODE";
        if (FfService.DATA_SCOPE_PROC_DEF.equals(dataScope)) {
            view = "FFV_NODE_PD";
        }
        if (FfService.DATA_SCOPE_PROC.equals(dataScope)) {
            view = "FFV_NODE_P";
        }
        String sql = "select * from " + view + " where 1 = 1";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("NODE_ID_", NODE_ID_);

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
        if (FROM_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ >= :FROM_CREATION_DATE_";
            paramMap.put("FROM_CREATION_DATE_", FROM_CREATION_DATE_);
        }
        if (TO_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ <= :TO_CREATION_DATE_";
            paramMap.put("TO_CREATION_DATE_", TO_CREATION_DATE_);
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

        if (includeSelf == null || includeSelf.equals(false)) {
            sql += " and NODE_ID_ != :NODE_ID_";
        }
        if (recursive == null || recursive.equals(false)) {
            sql += " and (NODE_ID_ = (select PARENT_NODE_ID_ from FF_NODE where NODE_ID_ = :NODE_ID_) or NODE_ID_ = :NODE_ID_)";
        }
        else {
            sql += " and NODE_ID_ in (with recursive CTE as (select NODE_ID_, PARENT_NODE_ID_ from FF_NODE where NODE_ID_ = :NODE_ID_ union all select FF_NODE.NODE_ID_, FF_NODE.PARENT_NODE_ID_ from FF_NODE inner join CTE on CTE.PARENT_NODE_ID_ = FF_NODE.NODE_ID_) select NODE_ID_ from CTE)";
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectChildNode(String NODE_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, List<String> SUB_PROC_DEF_ID_LIST, String ADJUST_SUB_PROC_DEF_ID_, List<String> ADJUST_SUB_PROC_DEF_ID_LIST, String NODE_TYPE_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_CODE_LIST, String NODE_NAME_, List<String> NODE_NAME_LIST, String PARENT_NODE_CODE_, List<String> PARENT_NODE_CODE_LIST, String NODE_END_USER_, List<String> NODE_END_USER_LIST, String NODE_END_USER_NAME_, List<String> NODE_END_USER_NAME_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, String ISOLATE_SUB_PROC_STATUS_, List<String> ISOLATE_SUB_PROC_STATUS_LIST,
            String NODE_STATUS_, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST,
            Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_PROC_CREATION_DATE_, Date TO_PROC_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, Boolean recursive, Boolean includeSelf, String dataScope) {
        if (StringUtils.isEmpty(NODE_ID_)) {
            throw new RuntimeException("errors.idRequired");
        }

        String view = "FFV_NODE";
        if (FfService.DATA_SCOPE_PROC_DEF.equals(dataScope)) {
            view = "FFV_NODE_PD";
        }
        if (FfService.DATA_SCOPE_PROC.equals(dataScope)) {
            view = "FFV_NODE_P";
        }
        String sql = "select * from " + view + " where 1 = 1";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("NODE_ID_", NODE_ID_);

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
        if (FROM_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ >= :FROM_CREATION_DATE_";
            paramMap.put("FROM_CREATION_DATE_", FROM_CREATION_DATE_);
        }
        if (TO_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ <= :TO_CREATION_DATE_";
            paramMap.put("TO_CREATION_DATE_", TO_CREATION_DATE_);
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

        if (includeSelf == null || includeSelf.equals(false)) {
            sql += " and NODE_ID_ != :NODE_ID_";
        }
        if (recursive == null || recursive.equals(false)) {
            sql += " and (PARENT_NODE_ID_ = :NODE_ID_ or NODE_ID_ = :NODE_ID_)";
        }
        else {
            sql += " and NODE_ID_ in (with recursive CTE as (select NODE_ID_, PARENT_NODE_ID_ from FF_NODE where NODE_ID_ = :NODE_ID_ union all select FF_NODE.NODE_ID_, FF_NODE.PARENT_NODE_ID_ from FF_NODE inner join CTE on CTE.NODE_ID_ = FF_NODE.PARENT_NODE_ID_) select NODE_ID_ from CTE)";
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectNodeByIdList(List<String> NODE_ID_LIST) {
        if (NODE_ID_LIST == null || NODE_ID_LIST.size() == 0) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder(NODE_ID_LIST.size() * 50 + 200);
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append("select * from FFV_NODE where NODE_ID_ in (:NODE_ID_LIST)");
        paramMap.put("NODE_ID_LIST", NODE_ID_LIST);
        sql.append(" order by FIELD(NODE_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < NODE_ID_LIST.size(); i++) {
            sql.append(" '").append(NODE_ID_LIST.get(i)).append("'");
            if (i < NODE_ID_LIST.size() - 1) {
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
    public int insertNode(String NODE_ID_, String PARENT_NODE_ID_, String PROC_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, String ADJUST_SUB_PROC_DEF_ID_, String NODE_TYPE_, String NODE_CODE_, String NODE_NAME_, String PARENT_NODE_CODE_, String CANDIDATE_ASSIGNEE_, String COMPLETE_EXPRESSION_, String COMPLETE_RETURN_, String EXCLUSIVE_, String AUTO_COMPLETE_SAME_ASSIGNEE_, String AUTO_COMPLETE_EMPTY_ASSIGNEE_, String INFORM_, String ASSIGNEE_, String ACTION_, String DUE_DATE_, String CLAIM_, String FORWARDABLE_, String PRIORITY_, String NODE_END_USER_, String NODE_END_USER_NAME_, Date NODE_END_DATE_, String NEXT_CANDIDATE_, String ISOLATE_SUB_PROC_DEF_CODE_, String ISOLATE_SUB_PROC_CANDIDATE_, String ISOLATE_SUB_PROC_STATUS_,
            String NODE_STATUS_, Date CREATION_DATE_) {
        String sql = "insert into FF_NODE (NODE_ID_, PARENT_NODE_ID_, PROC_ID_, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, CANDIDATE_ASSIGNEE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, ASSIGNEE_, ACTION_, DUE_DATE_, CLAIM_, FORWARDABLE_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, NEXT_CANDIDATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_CANDIDATE_, ISOLATE_SUB_PROC_STATUS_, NODE_STATUS_, CREATION_DATE_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int count = ffJdbcTemplate.update(sql, NODE_ID_, PARENT_NODE_ID_, PROC_ID_, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, CANDIDATE_ASSIGNEE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, ASSIGNEE_, ACTION_, DUE_DATE_, CLAIM_, FORWARDABLE_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, NEXT_CANDIDATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_CANDIDATE_, ISOLATE_SUB_PROC_STATUS_, NODE_STATUS_, CREATION_DATE_);

        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_INSERT);

        return count;
    }

    @Override
    public int updateNode(String NODE_ID_, String PROC_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, String ADJUST_SUB_PROC_DEF_ID_, String NODE_TYPE_, String NODE_CODE_, String NODE_NAME_, String PARENT_NODE_CODE_, String CANDIDATE_ASSIGNEE_, String COMPLETE_EXPRESSION_, String COMPLETE_RETURN_, String EXCLUSIVE_, String AUTO_COMPLETE_SAME_ASSIGNEE_, String AUTO_COMPLETE_EMPTY_ASSIGNEE_, String INFORM_, String ASSIGNEE_, String ACTION_, String DUE_DATE_, String CLAIM_, String FORWARDABLE_, String PRIORITY_, String NODE_END_USER_, String NODE_END_USER_NAME_, Date NODE_END_DATE_, String NEXT_CANDIDATE_, String ISOLATE_SUB_PROC_DEF_CODE_, String ISOLATE_SUB_PROC_CANDIDATE_) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_NODE set PROC_ID_ = ?, PREVIOUS_NODE_IDS_ = ?, LAST_COMPLETE_NODE_IDS_ = ?, SUB_PROC_DEF_ID_ = ?, ADJUST_SUB_PROC_DEF_ID_ = ?, NODE_TYPE_ = ?, NODE_CODE_ = ?, NODE_NAME_ = ?, PARENT_NODE_CODE_ = ?, CANDIDATE_ASSIGNEE_ = ?, COMPLETE_EXPRESSION_ = ?, COMPLETE_RETURN_ = ?, EXCLUSIVE_ = ?, AUTO_COMPLETE_SAME_ASSIGNEE_ = ?, AUTO_COMPLETE_EMPTY_ASSIGNEE_ = ?, INFORM_ = ?, ASSIGNEE_ = ?, ACTION_ = ?, DUE_DATE_ = ?, CLAIM_ = ?, FORWARDABLE_ = ?, PRIORITY_ = ?, NODE_END_USER_ = ?, NODE_END_USER_NAME_ = ?, NODE_END_DATE_ = ?, NEXT_CANDIDATE_ = ?, ISOLATE_SUB_PROC_CODE_ = ?, ISOLATE_SUB_PROC_CANDIDATE_ = ? where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_ID_, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, CANDIDATE_ASSIGNEE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, ASSIGNEE_, ACTION_, DUE_DATE_, CLAIM_, FORWARDABLE_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, NEXT_CANDIDATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_CANDIDATE_, NODE_ID_);
    }

    @Override
    public int updateNodePreviousNodeIds(String NODE_ID_, String PREVIOUS_NODE_IDS) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_NODE set PREVIOUS_NODE_IDS_ = ? where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, PREVIOUS_NODE_IDS, NODE_ID_);
    }

    @Override
    public int updateNodeLastCompleteNodeIds(String NODE_ID_, String LAST_COMPLETE_NODE_IDS_) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_NODE set LAST_COMPLETE_NODE_IDS_ = ? where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, LAST_COMPLETE_NODE_IDS_, NODE_ID_);
    }

    @Override
    public int updateBranchAdjustSubProcDefId(String BRANCH_ID_, String ADJUST_SUB_PROC_DEF_ID_) {
        String sql = "with recursive CTE as (select NODE_ID_, PARENT_NODE_ID_ from FF_NODE where NODE_ID_ = ? union all select FF_NODE.NODE_ID_, FF_NODE.PARENT_NODE_ID_ from FF_NODE inner join CTE on CTE.NODE_ID_ = FF_NODE.PARENT_NODE_ID_ and FF_NODE.NODE_TYPE_ != 'BRANCH') select NODE_ID_ from CTE";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, BRANCH_ID_);
        for (Map<String, Object> node : result) {
            ffOperationService.insertNodeOp(OdUtils.getUuid(), (String) node.get("NODE_ID_"), FfOperationService.OPERATION_TYPE_UPDATE);
        }

        sql = "update FF_NODE set ADJUST_SUB_PROC_DEF_ID_ = ? where NODE_ID_ in (with recursive CTE as (select NODE_ID_, PARENT_NODE_ID_ from FF_NODE where NODE_ID_ = ? union all select FF_NODE.NODE_ID_, FF_NODE.PARENT_NODE_ID_ from FF_NODE inner join CTE on CTE.NODE_ID_ = FF_NODE.PARENT_NODE_ID_ and FF_NODE.NODE_TYPE_ != 'BRANCH') select NODE_ID_ from CTE)";
        return ffJdbcTemplate.update(sql, ADJUST_SUB_PROC_DEF_ID_, BRANCH_ID_);
    }

    @Override
    public int updateNodeNextCandidate(String NODE_ID_, String NEXT_CANDIDATE_) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_NODE set NEXT_CANDIDATE_ = ? where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, NEXT_CANDIDATE_, NODE_ID_);
    }

    @Override
    public int updateIsolateSubProcStatus(String NODE_ID_, String ISOLATE_SUB_PROC_STATUS_) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_NODE set ISOLATE_SUB_PROC_STATUS_ = ? where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, ISOLATE_SUB_PROC_STATUS_, NODE_ID_);
    }

    @Override
    public int updateNodeStatus(String NODE_ID_, String NODE_STATUS_) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_NODE set NODE_STATUS_ = ? where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, NODE_STATUS_, NODE_ID_);
    }

    @Override
    public int updateNodeStatus(String NODE_ID_, String NODE_END_USER_, String NODE_END_USER_NAME_, Date NODE_END_DATE_, String NODE_STATUS_) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_NODE set NODE_END_USER_ = ?, NODE_END_USER_NAME_ = ?, NODE_END_DATE_ = ?, NODE_STATUS_ = ? where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, NODE_STATUS_, NODE_ID_);
    }

    @Override
    public int updateNodeStatus(String NODE_ID_, String NODE_END_USER_, String NODE_END_USER_NAME_, Date NODE_END_DATE_, String NEXT_CANDIDATE_, String NODE_STATUS_) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_NODE set NODE_END_USER_ = ?, NODE_END_USER_NAME_ = ?, NODE_END_DATE_ = ?, NEXT_CANDIDATE_ = ?, NODE_STATUS_ = ? where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, NEXT_CANDIDATE_, NODE_STATUS_, NODE_ID_);
    }

    @Override
    public int deleteNode(String NODE_ID_) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_DELETE);

        String sql = "delete from FF_NODE where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, NODE_ID_);
    }

    @Override
    public int deleteNodeByProcId(String PROC_ID_) {
        List<Map<String, Object>> nodeList = selectNode(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, PROC_ID_, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, -1, FfService.DATA_SCOPE_NODE);
        for (Map<String, Object> node : nodeList) {
            deleteNode((String) node.get("NODE_ID_"));
        }

        return nodeList.size();
    }
}