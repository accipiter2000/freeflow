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
import com.opendynamic.ff.service.FfDelegateService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfDelegateServiceImpl implements FfDelegateService {
    @Autowired
    private JdbcTemplate ffJdbcTemplate;

    @Override
    public Map<String, Object> loadDelegate(String DELEGATE_ID_) {
        String sql = "select * from FFV_DELEGATE where DELEGATE_ID_ = ?";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, DELEGATE_ID_);
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectDelegate(String DELEGATE_ID_, List<String> DELEGATE_ID_LIST, String ASSIGNEE_, List<String> ASSIGNEE_LIST, String ASSIGNEE_NAME_, List<String> ASSIGNEE_NAME_LIST, String DELEGATOR_, List<String> DELEGATOR_LIST, String DELEGATOR_NAME_, List<String> DELEGATOR_NAME_LIST, Date FROM_START_DATE_, Date TO_START_DATE_, Date FROM_END_DATE_, Date TO_END_DATE_, Integer page, Integer limit) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaDelegate(false, DELEGATE_ID_, DELEGATE_ID_LIST, ASSIGNEE_, ASSIGNEE_LIST, ASSIGNEE_NAME_, ASSIGNEE_NAME_LIST, DELEGATOR_, DELEGATOR_LIST, DELEGATOR_NAME_, DELEGATOR_NAME_LIST, FROM_START_DATE_, TO_START_DATE_, FROM_END_DATE_, TO_END_DATE_);// 根据查询条件组装查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        if (page != null && limit != null && limit > 0) {// 分页
            sql = sql + " limit " + (page - 1) * limit + ", " + limit;
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public int countDelegate(String DELEGATE_ID_, List<String> DELEGATE_ID_LIST, String ASSIGNEE_, List<String> ASSIGNEE_LIST, String ASSIGNEE_NAME_, List<String> ASSIGNEE_NAME_LIST, String DELEGATOR_, List<String> DELEGATOR_LIST, String DELEGATOR_NAME_, List<String> DELEGATOR_NAME_LIST, Date FROM_START_DATE_, Date TO_START_DATE_, Date FROM_END_DATE_, Date TO_END_DATE_) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaDelegate(true, DELEGATE_ID_, DELEGATE_ID_LIST, ASSIGNEE_, ASSIGNEE_LIST, ASSIGNEE_NAME_, ASSIGNEE_NAME_LIST, DELEGATOR_, DELEGATOR_LIST, DELEGATOR_NAME_, DELEGATOR_NAME_LIST, FROM_START_DATE_, TO_START_DATE_, FROM_END_DATE_, TO_END_DATE_);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaDelegate(boolean count, String DELEGATE_ID_, List<String> DELEGATE_ID_LIST, String ASSIGNEE_, List<String> ASSIGNEE_LIST, String ASSIGNEE_NAME_, List<String> ASSIGNEE_NAME_LIST, String DELEGATOR_, List<String> DELEGATOR_LIST, String DELEGATOR_NAME_, List<String> DELEGATOR_NAME_LIST, Date FROM_START_DATE_, Date TO_START_DATE_, Date FROM_END_DATE_, Date TO_END_DATE_) {// 组装查询SQL语句
        String sql;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (count) {
            sql = "select count(*) from FFV_DELEGATE where 1 = 1";
        }
        else {
            sql = "select * from FFV_DELEGATE where 1 = 1";
        }

        if (StringUtils.isNotEmpty(DELEGATE_ID_)) {
            sql += " and DELEGATE_ID_ = :DELEGATE_ID_";
            paramMap.put("DELEGATE_ID_", DELEGATE_ID_);
        }
        if (DELEGATE_ID_LIST != null && DELEGATE_ID_LIST.size() > 0) {
            sql += " and DELEGATE_ID_ in (:DELEGATE_ID_LIST)";
            paramMap.put("DELEGATE_ID_LIST", DELEGATE_ID_LIST);
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
        if (StringUtils.isNotEmpty(DELEGATOR_)) {
            sql += " and DELEGATOR_ = :DELEGATOR_";
            paramMap.put("DELEGATOR_", DELEGATOR_);
        }
        if (DELEGATOR_LIST != null && DELEGATOR_LIST.size() > 0) {
            sql += " and DELEGATOR_ in (:DELEGATOR_LIST)";
            paramMap.put("DELEGATOR_LIST", DELEGATOR_LIST);
        }
        if (StringUtils.isNotEmpty(DELEGATOR_NAME_)) {
            sql += " and DELEGATOR_NAME_ like concat('%',:DELEGATOR_NAME_,'%')";
            paramMap.put("DELEGATOR_NAME_", DELEGATOR_NAME_);
        }
        if (DELEGATOR_NAME_LIST != null && DELEGATOR_NAME_LIST.size() > 0) {
            sql += " and DELEGATOR_NAME_ in (:DELEGATOR_NAME_LIST)";
            paramMap.put("DELEGATOR_NAME_LIST", DELEGATOR_NAME_LIST);
        }
        if (FROM_START_DATE_ != null) {
            sql += " and START_DATE_ >= :FROM_START_DATE_";
            paramMap.put("FROM_START_DATE_", FROM_START_DATE_);
        }
        if (TO_START_DATE_ != null) {
            sql += " and START_DATE_ <= :TO_START_DATE_";
            paramMap.put("TO_START_DATE_", TO_START_DATE_);
        }
        if (FROM_END_DATE_ != null) {
            sql += " and END_DATE_ >= :FROM_END_DATE_";
            paramMap.put("FROM_END_DATE_", FROM_END_DATE_);
        }
        if (TO_END_DATE_ != null) {
            sql += " and END_DATE_ <= :TO_END_DATE_";
            paramMap.put("TO_END_DATE_", TO_END_DATE_);
        }

        return new OdSqlCriteria(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectDelegateByIdList(List<String> DELEGATE_ID_LIST) {
        if (DELEGATE_ID_LIST == null || DELEGATE_ID_LIST.size() == 0) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder(DELEGATE_ID_LIST.size() * 50 + 200);
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append("select * from FFV_DELEGATE where DELEGATE_ID_ in (:DELEGATE_ID_LIST)");
        paramMap.put("DELEGATE_ID_LIST", DELEGATE_ID_LIST);
        sql.append(" order by FIELD(DELEGATE_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < DELEGATE_ID_LIST.size(); i++) {
            sql.append(" '").append(DELEGATE_ID_LIST.get(i)).append("'");
            if (i < DELEGATE_ID_LIST.size() - 1) {
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
    public int insertDelegate(String DELEGATE_ID_, String ASSIGNEE_, String ASSIGNEE_NAME_, String DELEGATOR_, String DELEGATOR_NAME_, Date START_DATE_, Date END_DATE_) {
        String sql = "insert into FF_DELEGATE (DELEGATE_ID_, ASSIGNEE_, ASSIGNEE_NAME_, DELEGATOR_, DELEGATOR_NAME_, START_DATE_, END_DATE_) values (?, ?, ?, ?, ?, ?, ?)";
        return ffJdbcTemplate.update(sql, DELEGATE_ID_, ASSIGNEE_, ASSIGNEE_NAME_, DELEGATOR_, DELEGATOR_NAME_, START_DATE_, END_DATE_);
    }

    @Override
    public int updateDelegate(String DELEGATE_ID_, String ASSIGNEE_, String ASSIGNEE_NAME_, String DELEGATOR_, String DELEGATOR_NAME_, Date START_DATE_, Date END_DATE_) {
        String sql = "update FF_DELEGATE set ASSIGNEE_ = ?, ASSIGNEE_NAME_ = ?, DELEGATOR_ = ?, DELEGATOR_NAME_ = ?, START_DATE_ = ?, END_DATE_ = ? where DELEGATE_ID_ = ?";
        return ffJdbcTemplate.update(sql, ASSIGNEE_, ASSIGNEE_NAME_, DELEGATOR_, DELEGATOR_NAME_, START_DATE_, END_DATE_, DELEGATE_ID_);
    }

    @Override
    public int deleteDelegate(String DELEGATE_ID_) {
        String sql = "delete from FF_DELEGATE where DELEGATE_ID_ = ?";
        return ffJdbcTemplate.update(sql, DELEGATE_ID_);
    }
}