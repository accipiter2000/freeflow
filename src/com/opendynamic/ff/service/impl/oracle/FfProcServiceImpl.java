package com.opendynamic.ff.service.impl.oracle;

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
import com.opendynamic.ff.service.FfProcService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfProcServiceImpl implements FfProcService {
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
    public List<Map<String, Object>> selectProc(String PROC_ID_, String PROC_DEF_ID_, String ADJUST_PROC_DEF_ID_, String ISOLATE_SUB_PROC_NODE_ID_, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, List<String> PROC_START_USER_LIST, Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_, Integer page, Integer limit) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaProc(false, PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_LIST, FROM_PROC_START_DATE_, TO_PROC_START_DATE_, PROC_END_USER_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PROC_DEF_CODE_LIST, PROC_DEF_CAT_);// 根据查询条件组装查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        if (page != null && limit != null && limit > 0) {// 分页
            int start = (page - 1) * limit + 1;
            int end = page * limit;
            sql = "select * from (select FULLTABLE.*, ROWNUM RN from (" + sql + ") FULLTABLE where ROWNUM <= " + end + ") where RN >= " + start;
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public int countProc(String PROC_ID_, String PROC_DEF_ID_, String ADJUST_PROC_DEF_ID_, String ISOLATE_SUB_PROC_NODE_ID_, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, List<String> PROC_START_USER_LIST, Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaProc(true, PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_LIST, FROM_PROC_START_DATE_, TO_PROC_START_DATE_, PROC_END_USER_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PROC_DEF_CODE_LIST, PROC_DEF_CAT_);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaProc(boolean count, String PROC_ID_, String PROC_DEF_ID_, String ADJUST_PROC_DEF_ID_, String ISOLATE_SUB_PROC_NODE_ID_, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, List<String> PROC_START_USER_LIST, Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_) {// 组装查询SQL语句
        String sql;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (count) {
            sql = "select count(*) from FFV_PROC where 1 = 1";
        }
        else {
            sql = "select * from FFV_PROC where 1 = 1";
        }

        if (StringUtils.isNotEmpty(PROC_ID_)) {
            sql += " and PROC_ID_ = :PROC_ID_";
            paramMap.put("PROC_ID_", PROC_ID_);
        }
        if (StringUtils.isNotEmpty(PROC_DEF_ID_)) {
            sql += " and PROC_DEF_ID_ = :PROC_DEF_ID_";
            paramMap.put("PROC_DEF_ID_", PROC_DEF_ID_);
        }
        if (StringUtils.isNotEmpty(ADJUST_PROC_DEF_ID_)) {
            sql += " and ADJUST_PROC_DEF_ID_ = :ADJUST_PROC_DEF_ID_";
            paramMap.put("ADJUST_PROC_DEF_ID_", ADJUST_PROC_DEF_ID_);
        }
        if (StringUtils.isNotEmpty(ISOLATE_SUB_PROC_NODE_ID_)) {
            sql += " and ISOLATE_SUB_PROC_NODE_ID_ = :ISOLATE_SUB_PROC_NODE_ID_";
            paramMap.put("ISOLATE_SUB_PROC_NODE_ID_", ISOLATE_SUB_PROC_NODE_ID_);
        }
        if (StringUtils.isNotEmpty(BIZ_ID_)) {
            sql += " and BIZ_ID_ = :BIZ_ID_";
            paramMap.put("BIZ_ID_", BIZ_ID_);
        }
        if (BIZ_TYPE_LIST != null && BIZ_TYPE_LIST.size() > 0) {
            sql += " and BIZ_TYPE_ in (:BIZ_TYPE_LIST)";
            paramMap.put("BIZ_TYPE_LIST", BIZ_TYPE_LIST);
        }
        if (StringUtils.isNotEmpty(BIZ_CODE_)) {
            sql += " and BIZ_CODE_ = :BIZ_CODE_";
            paramMap.put("BIZ_CODE_", BIZ_CODE_);
        }
        if (StringUtils.isNotEmpty(BIZ_NAME_)) {
            sql += " and BIZ_NAME_ like '%' || :BIZ_NAME_ || '%'";
            paramMap.put("BIZ_NAME_", BIZ_NAME_);
        }
        if (PROC_START_USER_LIST != null && PROC_START_USER_LIST.size() > 0) {
            sql += " and PROC_START_USER_ in (:PROC_START_USER_LIST)";
            paramMap.put("PROC_START_USER_LIST", PROC_START_USER_LIST);
        }
        if (FROM_PROC_START_DATE_ != null) {
            sql += " and PROC_START_DATE_ >= :FROM_PROC_START_DATE_";
            paramMap.put("FROM_PROC_START_DATE_", FROM_PROC_START_DATE_);
        }
        if (TO_PROC_START_DATE_ != null) {
            sql += " and PROC_START_DATE_ <= :TO_PROC_START_DATE_";
            paramMap.put("TO_PROC_START_DATE_", TO_PROC_START_DATE_);
        }
        if (PROC_END_USER_LIST != null && PROC_END_USER_LIST.size() > 0) {
            sql += " and PROC_END_USER_ in (:PROC_END_USER_LIST)";
            paramMap.put("PROC_END_USER_LIST", PROC_END_USER_LIST);
        }
        if (FROM_PROC_END_DATE_ != null) {
            sql += " and PROC_END_DATE_ >= :FROM_PROC_END_DATE_";
            paramMap.put("FROM_PROC_END_DATE_", FROM_PROC_END_DATE_);
        }
        if (TO_PROC_END_DATE_ != null) {
            sql += " and PROC_END_DATE_ <= :TO_PROC_END_DATE_";
            paramMap.put("TO_PROC_END_DATE_", TO_PROC_END_DATE_);
        }
        if (PROC_STATUS_LIST != null && PROC_STATUS_LIST.size() > 0) {
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
        if (PROC_DEF_CODE_LIST != null && PROC_DEF_CODE_LIST.size() > 0) {
            sql += " and PROC_DEF_CODE_ in (:PROC_DEF_CODE_LIST)";
            paramMap.put("PROC_DEF_CODE_LIST", PROC_DEF_CODE_LIST);
        }
        if (StringUtils.isNotEmpty(PROC_DEF_CAT_)) {
            sql += " and PROC_DEF_CAT_ like :PROC_DEF_CAT_ || '%'";
            paramMap.put("PROC_DEF_CAT_", PROC_DEF_CAT_);
        }

        if (!count) {
            sql += " order by CREATION_DATE_ desc";
        }

        return new OdSqlCriteria(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectInvolvedProc(List<String> ASSIGNEE_LIST, String PROC_ID_, String PROC_DEF_ID_, String ADJUST_PROC_DEF_ID_, String ISOLATE_SUB_PROC_NODE_ID_, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, List<String> PROC_START_USER_LIST, Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_, Integer page, Integer limit) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaInvolvedProc(false, ASSIGNEE_LIST, PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_LIST, FROM_PROC_START_DATE_, TO_PROC_START_DATE_, PROC_END_USER_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PROC_DEF_CODE_LIST, PROC_DEF_CAT_);// 根据查询条件组装查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        if (page != null && limit != null && limit > 0) {// 分页
            int start = (page - 1) * limit + 1;
            int end = page * limit;
            sql = "select * from (select FULLTABLE.*, ROWNUM RN from (" + sql + ") FULLTABLE where ROWNUM <= " + end + ") where RN >= " + start;
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public int countInvolvedProc(List<String> ASSIGNEE_LIST, String PROC_ID_, String PROC_DEF_ID_, String ADJUST_PROC_DEF_ID_, String ISOLATE_SUB_PROC_NODE_ID_, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, List<String> PROC_START_USER_LIST, Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaInvolvedProc(true, ASSIGNEE_LIST, PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_LIST, FROM_PROC_START_DATE_, TO_PROC_START_DATE_, PROC_END_USER_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PROC_DEF_CODE_LIST, PROC_DEF_CAT_);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaInvolvedProc(boolean count, List<String> ASSIGNEE_LIST, String PROC_ID_, String PROC_DEF_ID_, String ADJUST_PROC_DEF_ID_, String ISOLATE_SUB_PROC_NODE_ID_, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, List<String> PROC_START_USER_LIST, Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_) {// 组装查询SQL语句
        if (ASSIGNEE_LIST == null || ASSIGNEE_LIST.size() == 0) {
            throw new RuntimeException("errors.assigneeRequired");
        }

        String sql;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (count) {
            sql = "select count(*) from FFV_PROC where PROC_ID_ in (select distinct PROC_ID_ from FFV_TASK where ASSIGNEE_ in (:ASSIGNEE_LIST) and TASK_STATUS_ = '9')";
        }
        else {
            sql = "select * from FFV_PROC where PROC_ID_ in (select distinct PROC_ID_ from FFV_TASK where ASSIGNEE_ in (:ASSIGNEE_LIST) and TASK_STATUS_ = '9')";
        }
        paramMap.put("ASSIGNEE_LIST", ASSIGNEE_LIST);

        if (StringUtils.isNotEmpty(PROC_ID_)) {
            sql += " and PROC_ID_ = :PROC_ID_";
            paramMap.put("PROC_ID_", PROC_ID_);
        }
        if (StringUtils.isNotEmpty(PROC_DEF_ID_)) {
            sql += " and PROC_DEF_ID_ = :PROC_DEF_ID_";
            paramMap.put("PROC_DEF_ID_", PROC_DEF_ID_);
        }
        if (StringUtils.isNotEmpty(ADJUST_PROC_DEF_ID_)) {
            sql += " and ADJUST_PROC_DEF_ID_ = :ADJUST_PROC_DEF_ID_";
            paramMap.put("ADJUST_PROC_DEF_ID_", ADJUST_PROC_DEF_ID_);
        }
        if (StringUtils.isNotEmpty(ISOLATE_SUB_PROC_NODE_ID_)) {
            sql += " and ISOLATE_SUB_PROC_NODE_ID_ = :ISOLATE_SUB_PROC_NODE_ID_";
            paramMap.put("ISOLATE_SUB_PROC_NODE_ID_", ISOLATE_SUB_PROC_NODE_ID_);
        }
        if (StringUtils.isNotEmpty(BIZ_ID_)) {
            sql += " and BIZ_ID_ = :BIZ_ID_";
            paramMap.put("BIZ_ID_", BIZ_ID_);
        }
        if (BIZ_TYPE_LIST != null && BIZ_TYPE_LIST.size() > 0) {
            sql += " and BIZ_TYPE_ in (:BIZ_TYPE_LIST)";
            paramMap.put("BIZ_TYPE_LIST", BIZ_TYPE_LIST);
        }
        if (StringUtils.isNotEmpty(BIZ_CODE_)) {
            sql += " and BIZ_CODE_ = :BIZ_CODE_";
            paramMap.put("BIZ_CODE_", BIZ_CODE_);
        }
        if (StringUtils.isNotEmpty(BIZ_NAME_)) {
            sql += " and BIZ_NAME_ like '%' || :BIZ_NAME_ || '%'";
            paramMap.put("BIZ_NAME_", BIZ_NAME_);
        }
        if (PROC_START_USER_LIST != null && PROC_START_USER_LIST.size() > 0) {
            sql += " and PROC_START_USER_ in (:PROC_START_USER_LIST)";
            paramMap.put("PROC_START_USER_LIST", PROC_START_USER_LIST);
        }
        if (FROM_PROC_START_DATE_ != null) {
            sql += " and PROC_START_DATE_ >= :FROM_PROC_START_DATE_";
            paramMap.put("FROM_PROC_START_DATE_", FROM_PROC_START_DATE_);
        }
        if (TO_PROC_START_DATE_ != null) {
            sql += " and PROC_START_DATE_ <= :TO_PROC_START_DATE_";
            paramMap.put("TO_PROC_START_DATE_", TO_PROC_START_DATE_);
        }
        if (PROC_END_USER_LIST != null && PROC_END_USER_LIST.size() > 0) {
            sql += " and PROC_END_USER_ in (:PROC_END_USER_LIST)";
            paramMap.put("PROC_END_USER_LIST", PROC_END_USER_LIST);
        }
        if (FROM_PROC_END_DATE_ != null) {
            sql += " and PROC_END_DATE_ >= :FROM_PROC_END_DATE_";
            paramMap.put("FROM_PROC_END_DATE_", FROM_PROC_END_DATE_);
        }
        if (TO_PROC_END_DATE_ != null) {
            sql += " and PROC_END_DATE_ <= :TO_PROC_END_DATE_";
            paramMap.put("TO_PROC_END_DATE_", TO_PROC_END_DATE_);
        }
        if (PROC_STATUS_LIST != null && PROC_STATUS_LIST.size() > 0) {
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
        if (PROC_DEF_CODE_LIST != null && PROC_DEF_CODE_LIST.size() > 0) {
            sql += " and PROC_DEF_CODE_ in (:PROC_DEF_CODE_LIST)";
            paramMap.put("PROC_DEF_CODE_LIST", PROC_DEF_CODE_LIST);
        }
        if (StringUtils.isNotEmpty(PROC_DEF_CAT_)) {
            sql += " and PROC_DEF_CAT_ like :PROC_DEF_CAT_ || '%'";
            paramMap.put("PROC_DEF_CAT_", PROC_DEF_CAT_);
        }

        if (!count) {
            sql += " order by CREATION_DATE_ desc";
        }

        return new OdSqlCriteria(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectProcByIdList(List<String> PROC_ID_LIST) {
        if (PROC_ID_LIST == null || PROC_ID_LIST.size() == 0) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder(PROC_ID_LIST.size() * 50 + 200);
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append("select * from FFV_PROC where PROC_ID_ in (:PROC_ID_LIST)");
        paramMap.put("PROC_ID_LIST", PROC_ID_LIST);
        sql.append(" order by DECODE(PROC_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < PROC_ID_LIST.size(); i++) {
            sql.append(" '").append(PROC_ID_LIST.get(i)).append("', ").append(i);
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
    public int insertProc(String PROC_ID_, String PROC_DEF_ID_, String ADJUST_PROC_DEF_ID_, String ISOLATE_SUB_PROC_NODE_ID_, String BIZ_ID_, String BIZ_TYPE_, String BIZ_CODE_, String BIZ_NAME_, String PROC_START_USER_, String PROC_START_USER_NAME_, Date PROC_START_DATE_, String PROC_END_USER_, String PROC_END_USER_NAME_, Date PROC_END_DATE_, String PROC_STATUS_, Date CREATION_DATE_) {
        String sql = "insert into FF_PROC (PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_START_DATE_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, CREATION_DATE_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int count = ffJdbcTemplate.update(sql, PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_START_DATE_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, CREATION_DATE_);

        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfOperationService.OPERATION_TYPE_INSERT);

        return count;
    }

    @Override
    public int updateProc(String PROC_ID_, String PROC_DEF_ID_, String ADJUST_PROC_DEF_ID_, String ISOLATE_SUB_PROC_NODE_ID_, String BIZ_ID_, String BIZ_TYPE_, String BIZ_CODE_, String BIZ_NAME_, String PROC_START_USER_, String PROC_START_USER_NAME_, Date PROC_START_DATE_, String PROC_END_USER_, String PROC_END_USER_NAME_, Date PROC_END_DATE_) {
        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_PROC set PROC_DEF_ID_ = ?, ADJUST_PROC_DEF_ID_ = ?, ISOLATE_SUB_PROC_NODE_ID_ = ?, BIZ_ID_ = ?, BIZ_TYPE_ = ?, BIZ_CODE_ = ?, BIZ_NAME_ = ?, PROC_START_USER_ = ?, PROC_START_USER_NAME_ = ?, PROC_START_DATE_ = ?, PROC_END_USER_ = ?, PROC_END_USER_NAME_ = ?, PROC_END_DATE_ = ? where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_START_DATE_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_ID_);
    }

    @Override
    public int updateProcBizInfo(String PROC_ID_, String BIZ_ID_, String BIZ_TYPE_, String BIZ_CODE_, String BIZ_NAME_) {
        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_PROC set BIZ_ID_ = ?, BIZ_TYPE_ = ?, BIZ_CODE_ = ?, BIZ_NAME_ = ? where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, PROC_ID_);
    }

    @Override
    public int updateProcStatus(String PROC_ID_, String PROC_STATUS_) {
        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_PROC set PROC_STATUS_ = ? where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_STATUS_, PROC_ID_);
    }

    @Override
    public int updateProcStatus(String PROC_ID_, String PROC_END_USER_, String PROC_END_USER_NAME_, Date PROC_END_DATE_, String PROC_STATUS_) {
        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_PROC set PROC_END_USER_ = ?, PROC_END_USER_NAME_ = ?, PROC_END_DATE_ = ?, PROC_STATUS_ = ? where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, PROC_ID_);
    }

    @Override
    public int deleteProc(String PROC_ID_) {
        ffOperationService.insertProcOp(OdUtils.getUuid(), PROC_ID_, FfOperationService.OPERATION_TYPE_DELETE);

        String sql = "delete from FF_PROC where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_ID_);
    }
}