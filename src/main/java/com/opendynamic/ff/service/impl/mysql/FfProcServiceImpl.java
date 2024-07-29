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
import com.opendynamic.ff.service.FfProcService;
import com.opendynamic.ff.service.FfService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfProcServiceImpl implements FfProcService {
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfOperationService ffOperationService;
    @Autowired
    private JdbcTemplate ffJdbcTemplate;

    @Override
    public Map<String, Object> loadProc(String PROC_ID_) {
        String sql = "select * from FFV_PROC where PROC_ID_ = ?";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, PROC_ID_);
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectProc(String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_,
            List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Integer page, Integer limit, String dataScope) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaProc(false, PROC_ID_, PROC_ID_LIST, ADJUST_PROC_DEF_ID_, ADJUST_PROC_DEF_ID_LIST, ISOLATE_SUB_PROC_NODE_ID_, ISOLATE_SUB_PROC_NODE_ID_LIST, BIZ_ID_, BIZ_ID_LIST, BIZ_TYPE_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_CODE_LIST, BIZ_NAME_, BIZ_NAME_LIST, BIZ_DESC_, BIZ_DESC_LIST, PROC_START_USER_, PROC_START_USER_LIST, PROC_START_USER_NAME_, PROC_START_USER_NAME_LIST, PROC_END_USER_, PROC_END_USER_LIST, PROC_END_USER_NAME_, PROC_END_USER_NAME_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_, PROC_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PROC_DEF_ID_, PROC_DEF_ID_LIST, PROC_DEF_CODE_, PROC_DEF_CODE_LIST, PROC_DEF_NAME_, PROC_DEF_NAME_LIST, PROC_DEF_CAT_, PROC_DEF_CAT_LIST, VERSION_, VERSION_LIST, PROC_DEF_STATUS_,
                PROC_DEF_STATUS_LIST, dataScope);// 根据查询条件组装查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        if (page != null && limit != null && limit > 0) {// 分页
            sql = sql + " limit " + (page - 1) * limit + ", " + limit;
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public int countProc(String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST,
            Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, String dataScope) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaProc(true, PROC_ID_, PROC_ID_LIST, ADJUST_PROC_DEF_ID_, ADJUST_PROC_DEF_ID_LIST, ISOLATE_SUB_PROC_NODE_ID_, ISOLATE_SUB_PROC_NODE_ID_LIST, BIZ_ID_, BIZ_ID_LIST, BIZ_TYPE_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_CODE_LIST, BIZ_NAME_, BIZ_NAME_LIST, BIZ_DESC_, BIZ_DESC_LIST, PROC_START_USER_, PROC_START_USER_LIST, PROC_START_USER_NAME_, PROC_START_USER_NAME_LIST, PROC_END_USER_, PROC_END_USER_LIST, PROC_END_USER_NAME_, PROC_END_USER_NAME_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_, PROC_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PROC_DEF_ID_, PROC_DEF_ID_LIST, PROC_DEF_CODE_, PROC_DEF_CODE_LIST, PROC_DEF_NAME_, PROC_DEF_NAME_LIST, PROC_DEF_CAT_, PROC_DEF_CAT_LIST, VERSION_, VERSION_LIST, PROC_DEF_STATUS_,
                PROC_DEF_STATUS_LIST, dataScope);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaProc(boolean count, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_,
            List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, String dataScope) {// 组装查询SQL语句
        String sql;
        Map<String, Object> paramMap = new HashMap<>();

        String view = "FFV_PROC_PD";
        if (FfService.DATA_SCOPE_PROC.equals(dataScope)) {
            view = "FFV_PROC";
        }
        if (count) {
            sql = "select count(*) from " + view + " where 1 = 1";
        }
        else {
            sql = "select * from " + view + " where 1 = 1";
        }

        if (PROC_ID_ != null) {
            sql += " and PROC_ID_ = :PROC_ID_";
            paramMap.put("PROC_ID_", PROC_ID_);
        }
        if (PROC_ID_LIST != null && !PROC_ID_LIST.isEmpty()) {
            sql += " and PROC_ID_ in (:PROC_ID_LIST)";
            paramMap.put("PROC_ID_LIST", PROC_ID_LIST);
        }
        if (ADJUST_PROC_DEF_ID_ != null) {
            sql += " and ADJUST_PROC_DEF_ID_ = :ADJUST_PROC_DEF_ID_";
            paramMap.put("ADJUST_PROC_DEF_ID_", ADJUST_PROC_DEF_ID_);
        }
        if (ADJUST_PROC_DEF_ID_LIST != null && !ADJUST_PROC_DEF_ID_LIST.isEmpty()) {
            sql += " and ADJUST_PROC_DEF_ID_ in (:ADJUST_PROC_DEF_ID_LIST)";
            paramMap.put("ADJUST_PROC_DEF_ID_LIST", ADJUST_PROC_DEF_ID_LIST);
        }
        if (ISOLATE_SUB_PROC_NODE_ID_ != null) {
            sql += " and ISOLATE_SUB_PROC_NODE_ID_ = :ISOLATE_SUB_PROC_NODE_ID_";
            paramMap.put("ISOLATE_SUB_PROC_NODE_ID_", ISOLATE_SUB_PROC_NODE_ID_);
        }
        if (ISOLATE_SUB_PROC_NODE_ID_LIST != null && !ISOLATE_SUB_PROC_NODE_ID_LIST.isEmpty()) {
            sql += " and ISOLATE_SUB_PROC_NODE_ID_ in (:ISOLATE_SUB_PROC_NODE_ID_LIST)";
            paramMap.put("ISOLATE_SUB_PROC_NODE_ID_LIST", ISOLATE_SUB_PROC_NODE_ID_LIST);
        }
        if (BIZ_ID_ != null) {
            sql += " and BIZ_ID_ = :BIZ_ID_";
            paramMap.put("BIZ_ID_", BIZ_ID_);
        }
        if (BIZ_ID_LIST != null && !BIZ_ID_LIST.isEmpty()) {
            sql += " and BIZ_ID_ in (:BIZ_ID_LIST)";
            paramMap.put("BIZ_ID_LIST", BIZ_ID_LIST);
        }
        if (BIZ_TYPE_ != null) {
            sql += " and BIZ_TYPE_ = :BIZ_TYPE_";
            paramMap.put("BIZ_TYPE_", BIZ_TYPE_);
        }
        if (BIZ_TYPE_LIST != null && !BIZ_TYPE_LIST.isEmpty()) {
            sql += " and BIZ_TYPE_ in (:BIZ_TYPE_LIST)";
            paramMap.put("BIZ_TYPE_LIST", BIZ_TYPE_LIST);
        }
        if (BIZ_CODE_ != null) {
            sql += " and BIZ_CODE_ = :BIZ_CODE_";
            paramMap.put("BIZ_CODE_", BIZ_CODE_);
        }
        if (BIZ_CODE_LIST != null && !BIZ_CODE_LIST.isEmpty()) {
            sql += " and BIZ_CODE_ in (:BIZ_CODE_LIST)";
            paramMap.put("BIZ_CODE_LIST", BIZ_CODE_LIST);
        }
        if (BIZ_NAME_ != null) {
            sql += " and BIZ_NAME_ like concat('%',:BIZ_NAME_,'%')";
            paramMap.put("BIZ_NAME_", BIZ_NAME_);
        }
        if (BIZ_NAME_LIST != null && !BIZ_NAME_LIST.isEmpty()) {
            sql += " and BIZ_NAME_ in (:BIZ_NAME_LIST)";
            paramMap.put("BIZ_NAME_LIST", BIZ_NAME_LIST);
        }
        if (BIZ_DESC_ != null) {
            sql += " and BIZ_DESC_ like concat('%',:BIZ_DESC_,'%')";
            paramMap.put("BIZ_DESC_", BIZ_DESC_);
        }
        if (BIZ_DESC_LIST != null && !BIZ_DESC_LIST.isEmpty()) {
            sql += " and BIZ_DESC_ in (:BIZ_DESC_LIST)";
            paramMap.put("BIZ_DESC_LIST", BIZ_DESC_LIST);
        }
        if (PROC_START_USER_ != null) {
            sql += " and PROC_START_USER_ = :PROC_START_USER_";
            paramMap.put("PROC_START_USER_", PROC_START_USER_);
        }
        if (PROC_START_USER_LIST != null && !PROC_START_USER_LIST.isEmpty()) {
            sql += " and PROC_START_USER_ in (:PROC_START_USER_LIST)";
            paramMap.put("PROC_START_USER_LIST", PROC_START_USER_LIST);
        }
        if (PROC_START_USER_NAME_ != null) {
            sql += " and PROC_START_USER_NAME_ like concat('%',:PROC_START_USER_NAME_,'%')";
            paramMap.put("PROC_START_USER_NAME_", PROC_START_USER_NAME_);
        }
        if (PROC_START_USER_NAME_LIST != null && !PROC_START_USER_NAME_LIST.isEmpty()) {
            sql += " and PROC_START_USER_NAME_ in (:PROC_START_USER_NAME_LIST)";
            paramMap.put("PROC_START_USER_NAME_LIST", PROC_START_USER_NAME_LIST);
        }
        if (PROC_END_USER_ != null) {
            sql += " and PROC_END_USER_ = :PROC_END_USER_";
            paramMap.put("PROC_END_USER_", PROC_END_USER_);
        }
        if (PROC_END_USER_LIST != null && !PROC_END_USER_LIST.isEmpty()) {
            sql += " and PROC_END_USER_ in (:PROC_END_USER_LIST)";
            paramMap.put("PROC_END_USER_LIST", PROC_END_USER_LIST);
        }
        if (PROC_END_USER_NAME_ != null) {
            sql += " and PROC_END_USER_NAME_ like concat('%',:PROC_END_USER_NAME_,'%')";
            paramMap.put("PROC_END_USER_NAME_", PROC_END_USER_NAME_);
        }
        if (PROC_END_USER_NAME_LIST != null && !PROC_END_USER_NAME_LIST.isEmpty()) {
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
        if (PROC_STATUS_ != null) {
            sql += " and PROC_STATUS_ = :PROC_STATUS_";
            paramMap.put("PROC_STATUS_", PROC_STATUS_);
        }
        if (PROC_STATUS_LIST != null && !PROC_STATUS_LIST.isEmpty()) {
            sql += " and PROC_STATUS_ in (:PROC_STATUS_LIST)";
            paramMap.put("PROC_STATUS_LIST", PROC_STATUS_LIST);
        }
        if (FROM_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ >= :FROM_CREATION_DATE_";
            paramMap.put("FROM_CREATION_DATE_", FROM_CREATION_DATE_);
        }
        if (TO_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ <= :TO_CREATION_DATE_";
            paramMap.put("TO_CREATION_DATE_", TO_CREATION_DATE_);
        }

        if (PROC_DEF_ID_ != null) {
            sql += " and PROC_DEF_ID_ = :PROC_DEF_ID_";
            paramMap.put("PROC_DEF_ID_", PROC_DEF_ID_);
        }
        if (PROC_DEF_ID_LIST != null && !PROC_DEF_ID_LIST.isEmpty()) {
            sql += " and PROC_DEF_ID_ in (:PROC_DEF_ID_LIST)";
            paramMap.put("PROC_DEF_ID_LIST", PROC_DEF_ID_LIST);
        }
        if (PROC_DEF_CODE_ != null) {
            sql += " and PROC_DEF_CODE_ = :PROC_DEF_CODE_";
            paramMap.put("PROC_DEF_CODE_", PROC_DEF_CODE_);
        }
        if (PROC_DEF_CODE_LIST != null && !PROC_DEF_CODE_LIST.isEmpty()) {
            sql += " and PROC_DEF_CODE_ in (:PROC_DEF_CODE_LIST)";
            paramMap.put("PROC_DEF_CODE_LIST", PROC_DEF_CODE_LIST);
        }
        if (PROC_DEF_NAME_ != null) {
            sql += " and PROC_DEF_NAME_ like concat('%',:PROC_DEF_NAME_,'%')";
            paramMap.put("PROC_DEF_NAME_", PROC_DEF_NAME_);
        }
        if (PROC_DEF_NAME_LIST != null && !PROC_DEF_NAME_LIST.isEmpty()) {
            sql += " and PROC_DEF_NAME_ in (:PROC_DEF_NAME_LIST)";
            paramMap.put("PROC_DEF_NAME_LIST", PROC_DEF_NAME_LIST);
        }
        if (PROC_DEF_CAT_ != null) {
            sql += " and PROC_DEF_CAT_ like :PROC_DEF_CAT_ || '%'";
            paramMap.put("PROC_DEF_CAT_", PROC_DEF_CAT_);
        }
        if (PROC_DEF_CAT_LIST != null && !PROC_DEF_CAT_LIST.isEmpty()) {
            sql += " and PROC_DEF_CAT_ in (:PROC_DEF_CAT_LIST)";
            paramMap.put("PROC_DEF_CAT_LIST", PROC_DEF_CAT_LIST);
        }
        if (VERSION_ != null) {
            sql += " and VERSION_ = :VERSION_";
            paramMap.put("VERSION_", VERSION_);
        }
        if (VERSION_LIST != null && !VERSION_LIST.isEmpty()) {
            sql += " and VERSION_ in (:VERSION_LIST)";
            paramMap.put("VERSION_LIST", VERSION_LIST);
        }
        if (PROC_DEF_STATUS_ != null) {
            sql += " and PROC_DEF_STATUS_ = :PROC_DEF_STATUS_";
            paramMap.put("PROC_DEF_STATUS_", PROC_DEF_STATUS_);
        }
        if (PROC_DEF_STATUS_LIST != null && !PROC_DEF_STATUS_LIST.isEmpty()) {
            sql += " and PROC_DEF_STATUS_ in (:PROC_DEF_STATUS_LIST)";
            paramMap.put("PROC_DEF_STATUS_LIST", PROC_DEF_STATUS_LIST);
        }

        if (!count) {
            sql += " order by CREATION_DATE_ desc";
        }

        return new OdSqlCriteria(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectInvolvedProc(String ASSIGNEE_, List<String> ASSIGNEE_LIST, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_,
            Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Integer page, Integer limit, String dataScope) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaInvolvedProc(false, ASSIGNEE_, ASSIGNEE_LIST, PROC_ID_, PROC_ID_LIST, ADJUST_PROC_DEF_ID_, ADJUST_PROC_DEF_ID_LIST, ISOLATE_SUB_PROC_NODE_ID_, ISOLATE_SUB_PROC_NODE_ID_LIST, BIZ_ID_, BIZ_ID_LIST, BIZ_TYPE_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_CODE_LIST, BIZ_NAME_, BIZ_NAME_LIST, BIZ_DESC_, BIZ_DESC_LIST, PROC_START_USER_, PROC_START_USER_LIST, PROC_START_USER_NAME_, PROC_START_USER_NAME_LIST, PROC_END_USER_, PROC_END_USER_LIST, PROC_END_USER_NAME_, PROC_END_USER_NAME_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_, PROC_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PROC_DEF_ID_, PROC_DEF_ID_LIST, PROC_DEF_CODE_, PROC_DEF_CODE_LIST, PROC_DEF_NAME_, PROC_DEF_NAME_LIST, PROC_DEF_CAT_, PROC_DEF_CAT_LIST, VERSION_, VERSION_LIST,
                PROC_DEF_STATUS_, PROC_DEF_STATUS_LIST, dataScope);// 根据查询条件组装查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        if (page != null && limit != null && limit > 0) {// 分页
            sql = sql + " limit " + (page - 1) * limit + ", " + limit;
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public int countInvolvedProc(String ASSIGNEE_, List<String> ASSIGNEE_LIST, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_,
            String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, String dataScope) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaInvolvedProc(true, ASSIGNEE_, ASSIGNEE_LIST, PROC_ID_, PROC_ID_LIST, ADJUST_PROC_DEF_ID_, ADJUST_PROC_DEF_ID_LIST, ISOLATE_SUB_PROC_NODE_ID_, ISOLATE_SUB_PROC_NODE_ID_LIST, BIZ_ID_, BIZ_ID_LIST, BIZ_TYPE_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_CODE_LIST, BIZ_NAME_, BIZ_NAME_LIST, BIZ_DESC_, BIZ_DESC_LIST, PROC_START_USER_, PROC_START_USER_LIST, PROC_START_USER_NAME_, PROC_START_USER_NAME_LIST, PROC_END_USER_, PROC_END_USER_LIST, PROC_END_USER_NAME_, PROC_END_USER_NAME_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_, PROC_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PROC_DEF_ID_, PROC_DEF_ID_LIST, PROC_DEF_CODE_, PROC_DEF_CODE_LIST, PROC_DEF_NAME_, PROC_DEF_NAME_LIST, PROC_DEF_CAT_, PROC_DEF_CAT_LIST, VERSION_, VERSION_LIST,
                PROC_DEF_STATUS_, PROC_DEF_STATUS_LIST, dataScope);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaInvolvedProc(boolean count, String ASSIGNEE_, List<String> ASSIGNEE_LIST, String PROC_ID_, List<String> PROC_ID_LIST, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String ISOLATE_SUB_PROC_NODE_ID_, List<String> ISOLATE_SUB_PROC_NODE_ID_LIST, String BIZ_ID_, List<String> BIZ_ID_LIST, String BIZ_TYPE_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, List<String> BIZ_CODE_LIST, String BIZ_NAME_, List<String> BIZ_NAME_LIST, String BIZ_DESC_, List<String> BIZ_DESC_LIST, String PROC_START_USER_, List<String> PROC_START_USER_LIST, String PROC_START_USER_NAME_, List<String> PROC_START_USER_NAME_LIST, String PROC_END_USER_, List<String> PROC_END_USER_LIST, String PROC_END_USER_NAME_, List<String> PROC_END_USER_NAME_LIST,
            Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, String PROC_STATUS_, List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, String dataScope) {// 组装查询SQL语句
        if (StringUtils.isEmpty(ASSIGNEE_) && (ASSIGNEE_LIST == null || ASSIGNEE_LIST.isEmpty())) {
            throw new RuntimeException("errors.assigneeRequired");
        }

        String sql;
        Map<String, Object> paramMap = new HashMap<>();

        String view = "FFV_PROC_PD";
        if (FfService.DATA_SCOPE_PROC.equals(dataScope)) {
            view = "FFV_PROC";
        }
        if (count) {
            sql = "select count(*) from " + view + " where PROC_ID_ in (select distinct PROC_ID_ from FFV_TASK where TASK_STATUS_ = '9'";
        }
        else {
            sql = "select * from " + view + " where PROC_ID_ in (select distinct PROC_ID_ from FFV_TASK where TASK_STATUS_ = '9'";
        }

        if (ASSIGNEE_ != null) {
            sql += " and ASSIGNEE_ = :ASSIGNEE_";
            paramMap.put("ASSIGNEE_", ASSIGNEE_);
        }
        if (ASSIGNEE_LIST != null && !ASSIGNEE_LIST.isEmpty()) {
            sql += " and ASSIGNEE_ in (:ASSIGNEE_LIST)";
            paramMap.put("ASSIGNEE_LIST", ASSIGNEE_LIST);
        }
        sql += ")";

        if (PROC_ID_ != null) {
            sql += " and PROC_ID_ = :PROC_ID_";
            paramMap.put("PROC_ID_", PROC_ID_);
        }
        if (PROC_ID_LIST != null && !PROC_ID_LIST.isEmpty()) {
            sql += " and PROC_ID_ in (:PROC_ID_LIST)";
            paramMap.put("PROC_ID_LIST", PROC_ID_LIST);
        }
        if (ADJUST_PROC_DEF_ID_ != null) {
            sql += " and ADJUST_PROC_DEF_ID_ = :ADJUST_PROC_DEF_ID_";
            paramMap.put("ADJUST_PROC_DEF_ID_", ADJUST_PROC_DEF_ID_);
        }
        if (ADJUST_PROC_DEF_ID_LIST != null && !ADJUST_PROC_DEF_ID_LIST.isEmpty()) {
            sql += " and ADJUST_PROC_DEF_ID_ in (:ADJUST_PROC_DEF_ID_LIST)";
            paramMap.put("ADJUST_PROC_DEF_ID_LIST", ADJUST_PROC_DEF_ID_LIST);
        }
        if (ISOLATE_SUB_PROC_NODE_ID_ != null) {
            sql += " and ISOLATE_SUB_PROC_NODE_ID_ = :ISOLATE_SUB_PROC_NODE_ID_";
            paramMap.put("ISOLATE_SUB_PROC_NODE_ID_", ISOLATE_SUB_PROC_NODE_ID_);
        }
        if (ISOLATE_SUB_PROC_NODE_ID_LIST != null && !ISOLATE_SUB_PROC_NODE_ID_LIST.isEmpty()) {
            sql += " and ISOLATE_SUB_PROC_NODE_ID_ in (:ISOLATE_SUB_PROC_NODE_ID_LIST)";
            paramMap.put("ISOLATE_SUB_PROC_NODE_ID_LIST", ISOLATE_SUB_PROC_NODE_ID_LIST);
        }
        if (BIZ_ID_ != null) {
            sql += " and BIZ_ID_ = :BIZ_ID_";
            paramMap.put("BIZ_ID_", BIZ_ID_);
        }
        if (BIZ_ID_LIST != null && !BIZ_ID_LIST.isEmpty()) {
            sql += " and BIZ_ID_ in (:BIZ_ID_LIST)";
            paramMap.put("BIZ_ID_LIST", BIZ_ID_LIST);
        }
        if (BIZ_TYPE_ != null) {
            sql += " and BIZ_TYPE_ = :BIZ_TYPE_";
            paramMap.put("BIZ_TYPE_", BIZ_TYPE_);
        }
        if (BIZ_TYPE_LIST != null && !BIZ_TYPE_LIST.isEmpty()) {
            sql += " and BIZ_TYPE_ in (:BIZ_TYPE_LIST)";
            paramMap.put("BIZ_TYPE_LIST", BIZ_TYPE_LIST);
        }
        if (BIZ_CODE_ != null) {
            sql += " and BIZ_CODE_ = :BIZ_CODE_";
            paramMap.put("BIZ_CODE_", BIZ_CODE_);
        }
        if (BIZ_CODE_LIST != null && !BIZ_CODE_LIST.isEmpty()) {
            sql += " and BIZ_CODE_ in (:BIZ_CODE_LIST)";
            paramMap.put("BIZ_CODE_LIST", BIZ_CODE_LIST);
        }
        if (BIZ_NAME_ != null) {
            sql += " and BIZ_NAME_ like concat('%',:BIZ_NAME_,'%')";
            paramMap.put("BIZ_NAME_", BIZ_NAME_);
        }
        if (BIZ_NAME_LIST != null && !BIZ_NAME_LIST.isEmpty()) {
            sql += " and BIZ_NAME_ in (:BIZ_NAME_LIST)";
            paramMap.put("BIZ_NAME_LIST", BIZ_NAME_LIST);
        }
        if (BIZ_DESC_ != null) {
            sql += " and BIZ_DESC_ like concat('%',:BIZ_DESC_,'%')";
            paramMap.put("BIZ_DESC_", BIZ_DESC_);
        }
        if (BIZ_DESC_LIST != null && !BIZ_DESC_LIST.isEmpty()) {
            sql += " and BIZ_DESC_ in (:BIZ_DESC_LIST)";
            paramMap.put("BIZ_DESC_LIST", BIZ_DESC_LIST);
        }
        if (PROC_START_USER_ != null) {
            sql += " and PROC_START_USER_ = :PROC_START_USER_";
            paramMap.put("PROC_START_USER_", PROC_START_USER_);
        }
        if (PROC_START_USER_LIST != null && !PROC_START_USER_LIST.isEmpty()) {
            sql += " and PROC_START_USER_ in (:PROC_START_USER_LIST)";
            paramMap.put("PROC_START_USER_LIST", PROC_START_USER_LIST);
        }
        if (PROC_START_USER_NAME_ != null) {
            sql += " and PROC_START_USER_NAME_ like concat('%',:PROC_START_USER_NAME_,'%')";
            paramMap.put("PROC_START_USER_NAME_", PROC_START_USER_NAME_);
        }
        if (PROC_START_USER_NAME_LIST != null && !PROC_START_USER_NAME_LIST.isEmpty()) {
            sql += " and PROC_START_USER_NAME_ in (:PROC_START_USER_NAME_LIST)";
            paramMap.put("PROC_START_USER_NAME_LIST", PROC_START_USER_NAME_LIST);
        }
        if (PROC_END_USER_ != null) {
            sql += " and PROC_END_USER_ = :PROC_END_USER_";
            paramMap.put("PROC_END_USER_", PROC_END_USER_);
        }
        if (PROC_END_USER_LIST != null && !PROC_END_USER_LIST.isEmpty()) {
            sql += " and PROC_END_USER_ in (:PROC_END_USER_LIST)";
            paramMap.put("PROC_END_USER_LIST", PROC_END_USER_LIST);
        }
        if (PROC_END_USER_NAME_ != null) {
            sql += " and PROC_END_USER_NAME_ like concat('%',:PROC_END_USER_NAME_,'%')";
            paramMap.put("PROC_END_USER_NAME_", PROC_END_USER_NAME_);
        }
        if (PROC_END_USER_NAME_LIST != null && !PROC_END_USER_NAME_LIST.isEmpty()) {
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
        if (PROC_STATUS_ != null) {
            sql += " and PROC_STATUS_ = :PROC_STATUS_";
            paramMap.put("PROC_STATUS_", PROC_STATUS_);
        }
        if (PROC_STATUS_LIST != null && !PROC_STATUS_LIST.isEmpty()) {
            sql += " and PROC_STATUS_ in (:PROC_STATUS_LIST)";
            paramMap.put("PROC_STATUS_LIST", PROC_STATUS_LIST);
        }
        if (FROM_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ >= :FROM_CREATION_DATE_";
            paramMap.put("FROM_CREATION_DATE_", FROM_CREATION_DATE_);
        }
        if (TO_CREATION_DATE_ != null) {
            sql += " and CREATION_DATE_ <= :TO_CREATION_DATE_";
            paramMap.put("TO_CREATION_DATE_", TO_CREATION_DATE_);
        }
        if (PROC_DEF_ID_ != null) {
            sql += " and PROC_DEF_ID_ = :PROC_DEF_ID_";
            paramMap.put("PROC_DEF_ID_", PROC_DEF_ID_);
        }
        if (PROC_DEF_ID_LIST != null && !PROC_DEF_ID_LIST.isEmpty()) {
            sql += " and PROC_DEF_ID_ in (:PROC_DEF_ID_LIST)";
            paramMap.put("PROC_DEF_ID_LIST", PROC_DEF_ID_LIST);
        }
        if (PROC_DEF_CODE_ != null) {
            sql += " and PROC_DEF_CODE_ = :PROC_DEF_CODE_";
            paramMap.put("PROC_DEF_CODE_", PROC_DEF_CODE_);
        }
        if (PROC_DEF_CODE_LIST != null && !PROC_DEF_CODE_LIST.isEmpty()) {
            sql += " and PROC_DEF_CODE_ in (:PROC_DEF_CODE_LIST)";
            paramMap.put("PROC_DEF_CODE_LIST", PROC_DEF_CODE_LIST);
        }
        if (PROC_DEF_NAME_ != null) {
            sql += " and PROC_DEF_NAME_ like concat('%',:PROC_DEF_NAME_,'%')";
            paramMap.put("PROC_DEF_NAME_", PROC_DEF_NAME_);
        }
        if (PROC_DEF_NAME_LIST != null && !PROC_DEF_NAME_LIST.isEmpty()) {
            sql += " and PROC_DEF_NAME_ in (:PROC_DEF_NAME_LIST)";
            paramMap.put("PROC_DEF_NAME_LIST", PROC_DEF_NAME_LIST);
        }
        if (PROC_DEF_CAT_ != null) {
            sql += " and PROC_DEF_CAT_ like :PROC_DEF_CAT_ || '%'";
            paramMap.put("PROC_DEF_CAT_", PROC_DEF_CAT_);
        }
        if (PROC_DEF_CAT_LIST != null && !PROC_DEF_CAT_LIST.isEmpty()) {
            sql += " and PROC_DEF_CAT_ in (:PROC_DEF_CAT_LIST)";
            paramMap.put("PROC_DEF_CAT_LIST", PROC_DEF_CAT_LIST);
        }
        if (VERSION_ != null) {
            sql += " and VERSION_ = :VERSION_";
            paramMap.put("VERSION_", VERSION_);
        }
        if (VERSION_LIST != null && !VERSION_LIST.isEmpty()) {
            sql += " and VERSION_ in (:VERSION_LIST)";
            paramMap.put("VERSION_LIST", VERSION_LIST);
        }
        if (PROC_DEF_STATUS_ != null) {
            sql += " and PROC_DEF_STATUS_ = :PROC_DEF_STATUS_";
            paramMap.put("PROC_DEF_STATUS_", PROC_DEF_STATUS_);
        }
        if (PROC_DEF_STATUS_LIST != null && !PROC_DEF_STATUS_LIST.isEmpty()) {
            sql += " and PROC_DEF_STATUS_ in (:PROC_DEF_STATUS_LIST)";
            paramMap.put("PROC_DEF_STATUS_LIST", PROC_DEF_STATUS_LIST);
        }

        if (!count) {
            sql += " order by CREATION_DATE_ desc";
        }

        return new OdSqlCriteria(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectProcByIdList(List<String> PROC_ID_LIST) {
        if (PROC_ID_LIST == null || PROC_ID_LIST.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder(PROC_ID_LIST.size() * 50 + 200);
        Map<String, Object> paramMap = new HashMap<>();

        sql.append("select * from FFV_PROC where PROC_ID_ in (:PROC_ID_LIST)");
        paramMap.put("PROC_ID_LIST", PROC_ID_LIST);
        sql.append(" order by FIELD(PROC_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < PROC_ID_LIST.size(); i++) {
            sql.append(" '").append(PROC_ID_LIST.get(i)).append("'");
            if (i < PROC_ID_LIST.size() - 1) {
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
    public int insertProc(String PROC_ID_, String PROC_DEF_ID_, String ADJUST_PROC_DEF_ID_, String ISOLATE_SUB_PROC_NODE_ID_, String BIZ_ID_, String BIZ_TYPE_, String BIZ_CODE_, String BIZ_NAME_, String BIZ_DESC_, String PROC_START_USER_, String PROC_START_USER_NAME_, String PROC_END_USER_, String PROC_END_USER_NAME_, Date PROC_END_DATE_, String PROC_STATUS_, Date CREATION_DATE_) {
        String sql = "insert into FF_PROC (PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, BIZ_DESC_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, CREATION_DATE_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int count = ffJdbcTemplate.update(sql, PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, BIZ_DESC_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, CREATION_DATE_);

        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfService.OPERATION_TYPE_INSERT);

        return count;
    }

    @Override
    public int updateProc(String PROC_ID_, String PROC_DEF_ID_, String ADJUST_PROC_DEF_ID_, String ISOLATE_SUB_PROC_NODE_ID_, String BIZ_ID_, String BIZ_TYPE_, String BIZ_CODE_, String BIZ_NAME_, String BIZ_DESC_, String PROC_START_USER_, String PROC_START_USER_NAME_, String PROC_END_USER_, String PROC_END_USER_NAME_, Date PROC_END_DATE_) {
        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_PROC set PROC_DEF_ID_ = ?, ADJUST_PROC_DEF_ID_ = ?, ISOLATE_SUB_PROC_NODE_ID_ = ?, BIZ_ID_ = ?, BIZ_TYPE_ = ?, BIZ_CODE_ = ?, BIZ_NAME_ = ?, BIZ_DESC_ = ?, PROC_START_USER_ = ?, PROC_START_USER_NAME_ = ?, PROC_END_USER_ = ?, PROC_END_USER_NAME_ = ?, PROC_END_DATE_ = ? where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, BIZ_DESC_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_ID_);
    }

    @Override
    public int updateProcBizInfo(String PROC_ID_, String BIZ_ID_, String BIZ_TYPE_, String BIZ_CODE_, String BIZ_NAME_, String BIZ_DESC_) {
        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_PROC set BIZ_ID_ = ?, BIZ_TYPE_ = ?, BIZ_CODE_ = ?, BIZ_NAME_ = ?, BIZ_DESC_ = ? where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, BIZ_DESC_, PROC_ID_);
    }

    @Override
    public int updateProcStatus(String PROC_ID_, String PROC_STATUS_) {
        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_PROC set PROC_STATUS_ = ? where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_STATUS_, PROC_ID_);
    }

    @Override
    public int updateProcStatus(String PROC_ID_, String PROC_END_USER_, String PROC_END_USER_NAME_, Date PROC_END_DATE_, String PROC_STATUS_) {
        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_PROC set PROC_END_USER_ = ?, PROC_END_USER_NAME_ = ?, PROC_END_DATE_ = ?, PROC_STATUS_ = ? where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, PROC_ID_);
    }

    @Override
    public int deleteProc(String PROC_ID_) {
        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfService.OPERATION_TYPE_DELETE);

        String sql = "delete from FF_PROC where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_ID_);
    }

    @Override
    public int cleanProc(String PROC_ID_) {
        String sql;
        Map<String, Object> paramMap = new HashMap<>();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);

        List<Map<String, Object>> operationList = ffOperationService.selectOperation(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, PROC_ID_, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, -1, null);
        if (!operationList.isEmpty()) {
            List<String> OPERATION_ID_LIST = OdUtils.collect(operationList, "OPERATION_ID_", String.class);
            paramMap.put("OPERATION_ID_LIST", OPERATION_ID_LIST);

            sql = "delete from FF_NODE_VAR_OP where OPERATION_ID_ in (:OPERATION_ID_LIST)";
            namedParameterJdbcTemplate.update(sql, paramMap);
            sql = "delete from FF_TASK_OP where OPERATION_ID_ in (:OPERATION_ID_LIST)";
            namedParameterJdbcTemplate.update(sql, paramMap);
            sql = "delete from FF_NODE_OP where OPERATION_ID_ in (:OPERATION_ID_LIST)";
            namedParameterJdbcTemplate.update(sql, paramMap);
            sql = "delete from FF_PROC_OP where OPERATION_ID_ in (:OPERATION_ID_LIST)";
            namedParameterJdbcTemplate.update(sql, paramMap);

            sql = "with recursive CTE as (select * from FF_OPERATION_FOLLOW_UP where OPERATION_ID_ = ? union all select FF_OPERATION_FOLLOW_UP.* from FF_OPERATION_FOLLOW_UP inner join CTE on CTE.FOLLOW_UP_OPERATION_ID_ = FF_OPERATION_FOLLOW_UP.OPERATION_ID_) select * from CTE";
            List<Map<String, Object>> operationFollowUpList = ffJdbcTemplate.queryForList(sql, operationList.get(operationList.size() - 1).get("OPERATION_ID_"));
            List<String> OPERATION_FOLLOW_UP_ID_LIST = OdUtils.collect(operationFollowUpList, "OPERATION_FOLLOW_UP_ID_", String.class);
            for (int i = OPERATION_FOLLOW_UP_ID_LIST.size() - 1; i >= 0; i--) {
                sql = "delete from FF_OPERATION_FOLLOW_UP where OPERATION_FOLLOW_UP_ID_ = ?";
                ffJdbcTemplate.update(sql, OPERATION_FOLLOW_UP_ID_LIST.get(i));
            }

            sql = "delete from FF_OPERATION where PROC_ID_ = ?";
            ffJdbcTemplate.update(sql, PROC_ID_);
        }

        List<Map<String, Object>> nodeList = ffNodeService.selectChildNode(PROC_ID_, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, true, true, null);
        if (!nodeList.isEmpty()) {
            List<String> NODE_ID_LIST = OdUtils.collect(nodeList, "NODE_ID_", String.class);
            paramMap.put("NODE_ID_LIST", NODE_ID_LIST);

            sql = "delete from FF_NODE_VAR where NODE_ID_ in (:NODE_ID_LIST)";
            namedParameterJdbcTemplate.update(sql, paramMap);

            sql = "delete from FF_TASK where NODE_ID_ in (:NODE_ID_LIST)";
            namedParameterJdbcTemplate.update(sql, paramMap);

            for (int i = NODE_ID_LIST.size() - 1; i >= 0; i--) {
                sql = "delete from FF_NODE where NODE_ID_ = ?";
                ffJdbcTemplate.update(sql, NODE_ID_LIST.get(i));
            }
        }

        sql = "delete from FF_PROC where PROC_ID_ = ?";
        ffJdbcTemplate.update(sql, PROC_ID_);

        return 1;
    }
}