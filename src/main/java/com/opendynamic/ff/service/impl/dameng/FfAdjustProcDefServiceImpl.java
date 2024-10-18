package com.opendynamic.ff.service.impl.dameng;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opendynamic.OdSqlCriteria;
import com.opendynamic.ff.service.FfAdjustProcDefService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfAdjustProcDefServiceImpl implements FfAdjustProcDefService {
    @Autowired
    private JdbcTemplate ffJdbcTemplate;

    @Override
    public Map<String, Object> loadAdjustProcDef(String ADJUST_PROC_DEF_ID_) {
        String sql = "select * from FFV_ADJUST_PROC_DEF where ADJUST_PROC_DEF_ID_ = ?";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, ADJUST_PROC_DEF_ID_);
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public InputStream loadProcDefDiagramFile(String ADJUST_PROC_DEF_ID_) {
        String sql = "select PROC_DEF_DIAGRAM_FILE_ from FF_ADJUST_PROC_DEF where ADJUST_PROC_DEF_ID_ = ?";
        return ffJdbcTemplate.queryForObject(sql, new Object[] { ADJUST_PROC_DEF_ID_ }, new RowMapper<InputStream>() {
            public InputStream mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getBinaryStream(1);
            }
        });
    }

    @Override
    public List<Map<String, Object>> selectAdjustProcDef(String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, Integer page, Integer limit) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaAdjustProcDef(false, ADJUST_PROC_DEF_ID_, ADJUST_PROC_DEF_ID_LIST, PROC_DEF_ID_, PROC_DEF_ID_LIST);// 根据查询条件组装查询SQL语句
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
    public int countAdjustProcDef(String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaAdjustProcDef(true, ADJUST_PROC_DEF_ID_, ADJUST_PROC_DEF_ID_LIST, PROC_DEF_ID_, PROC_DEF_ID_LIST);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaAdjustProcDef(boolean count, String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST) {// 组装查询SQL语句
        String sql;
        Map<String, Object> paramMap = new HashMap<>();

        if (count) {
            sql = "select count(*) from FFV_ADJUST_PROC_DEF where 1 = 1";
        }
        else {
            sql = "select * from FFV_ADJUST_PROC_DEF where 1 = 1";
        }

        if (ADJUST_PROC_DEF_ID_ != null) {
            sql += " and ADJUST_PROC_DEF_ID_ = :ADJUST_PROC_DEF_ID_";
            paramMap.put("ADJUST_PROC_DEF_ID_", ADJUST_PROC_DEF_ID_);
        }
        if (ADJUST_PROC_DEF_ID_LIST != null && !ADJUST_PROC_DEF_ID_LIST.isEmpty()) {
            sql += " and ADJUST_PROC_DEF_ID_ in (:ADJUST_PROC_DEF_ID_LIST)";
            paramMap.put("ADJUST_PROC_DEF_ID_LIST", ADJUST_PROC_DEF_ID_LIST);
        }
        if (PROC_DEF_ID_ != null) {
            sql += " and PROC_DEF_ID_ = :PROC_DEF_ID_";
            paramMap.put("PROC_DEF_ID_", PROC_DEF_ID_);
        }
        if (PROC_DEF_ID_LIST != null && !PROC_DEF_ID_LIST.isEmpty()) {
            sql += " and PROC_DEF_ID_ in (:PROC_DEF_ID_LIST)";
            paramMap.put("PROC_DEF_ID_LIST", PROC_DEF_ID_LIST);
        }

        return new OdSqlCriteria(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectAdjustProcDefByIdList(List<String> ADJUST_PROC_DEF_ID_LIST) {
        if (ADJUST_PROC_DEF_ID_LIST == null || ADJUST_PROC_DEF_ID_LIST.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder(ADJUST_PROC_DEF_ID_LIST.size() * 50 + 200);
        Map<String, Object> paramMap = new HashMap<>();

        sql.append("select * from FFV_ADJUST_PROC_DEF where ADJUST_PROC_DEF_ID_ in (:ADJUST_PROC_DEF_ID_LIST)");
        paramMap.put("ADJUST_PROC_DEF_ID_LIST", ADJUST_PROC_DEF_ID_LIST);
        sql.append(" order by DECODE(ADJUST_PROC_DEF_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < ADJUST_PROC_DEF_ID_LIST.size(); i++) {
            sql.append(" '").append(ADJUST_PROC_DEF_ID_LIST.get(i)).append("', ").append(i);
            if (i < ADJUST_PROC_DEF_ID_LIST.size() - 1) {
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
    public int insertAdjustProcDef(String ADJUST_PROC_DEF_ID_, String PROC_DEF_ID_, String PROC_DEF_MODEL_, InputStream PROC_DEF_DIAGRAM_FILE_, String PROC_DEF_DIAGRAM_FILE_NAME_, Integer PROC_DEF_DIAGRAM_FILE_LENGTH_, Integer PROC_DEF_DIAGRAM_WIDTH_, Integer PROC_DEF_DIAGRAM_HEIGHT_, Date CREATION_DATE_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_) {
        String sql = "insert into FF_ADJUST_PROC_DEF (ADJUST_PROC_DEF_ID_, PROC_DEF_ID_, PROC_DEF_MODEL_, PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_NAME_, PROC_DEF_DIAGRAM_FILE_LENGTH_, PROC_DEF_DIAGRAM_WIDTH_, PROC_DEF_DIAGRAM_HEIGHT_, CREATION_DATE_, UPDATE_DATE_, OPERATOR_ID_, OPERATOR_NAME_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return ffJdbcTemplate.update(sql, new Object[] { ADJUST_PROC_DEF_ID_, PROC_DEF_ID_, PROC_DEF_MODEL_, new SqlLobValue(PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_LENGTH_, new DefaultLobHandler()), PROC_DEF_DIAGRAM_FILE_NAME_, PROC_DEF_DIAGRAM_FILE_LENGTH_, PROC_DEF_DIAGRAM_WIDTH_, PROC_DEF_DIAGRAM_HEIGHT_, CREATION_DATE_, UPDATE_DATE_, OPERATOR_ID_, OPERATOR_NAME_ }, new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR });
    }

    @Override
    public int updateAdjustProcDef(String ADJUST_PROC_DEF_ID_, String PROC_DEF_MODEL_, InputStream PROC_DEF_DIAGRAM_FILE_, String PROC_DEF_DIAGRAM_FILE_NAME_, Integer PROC_DEF_DIAGRAM_FILE_LENGTH_, Integer PROC_DEF_DIAGRAM_WIDTH_, Integer PROC_DEF_DIAGRAM_HEIGHT_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_) {
        String sql = "update FF_ADJUST_PROC_DEF set ADJUST_PROC_DEF_ID_ = :ADJUST_PROC_DEF_ID_";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (PROC_DEF_DIAGRAM_FILE_LENGTH_ != 0) {// 更新文件
            sql += ", PROC_DEF_DIAGRAM_FILE_ = :PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_NAME_ = :PROC_DEF_DIAGRAM_FILE_NAME_, PROC_DEF_DIAGRAM_FILE_LENGTH_ = :PROC_DEF_DIAGRAM_FILE_LENGTH_";
            parameterSource.addValue("PROC_DEF_DIAGRAM_FILE_", new SqlLobValue(PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_LENGTH_, new DefaultLobHandler()), Types.BLOB);
            parameterSource.addValue("PROC_DEF_DIAGRAM_FILE_NAME_", PROC_DEF_DIAGRAM_FILE_NAME_, Types.VARCHAR);
            parameterSource.addValue("PROC_DEF_DIAGRAM_FILE_LENGTH_", PROC_DEF_DIAGRAM_FILE_LENGTH_, Types.INTEGER);
        }
        sql += ", PROC_DEF_MODEL_ = :PROC_DEF_MODEL_, PROC_DEF_DIAGRAM_WIDTH_ = :PROC_DEF_DIAGRAM_WIDTH_, PROC_DEF_DIAGRAM_HEIGHT_ = :PROC_DEF_DIAGRAM_HEIGHT_, UPDATE_DATE_ = :UPDATE_DATE_, OPERATOR_ID_ = :OPERATOR_ID_, OPERATOR_NAME_ = :OPERATOR_NAME_ where ADJUST_PROC_DEF_ID_ = :ADJUST_PROC_DEF_ID_";
        parameterSource.addValue("PROC_DEF_MODEL_", PROC_DEF_MODEL_, Types.VARCHAR);
        parameterSource.addValue("PROC_DEF_DIAGRAM_WIDTH_", PROC_DEF_DIAGRAM_WIDTH_, Types.INTEGER);
        parameterSource.addValue("PROC_DEF_DIAGRAM_HEIGHT_", PROC_DEF_DIAGRAM_HEIGHT_, Types.INTEGER);
        parameterSource.addValue("UPDATE_DATE_", UPDATE_DATE_, Types.TIMESTAMP);
        parameterSource.addValue("OPERATOR_ID_", OPERATOR_ID_, Types.VARCHAR);
        parameterSource.addValue("OPERATOR_NAME_", OPERATOR_NAME_, Types.VARCHAR);
        parameterSource.addValue("ADJUST_PROC_DEF_ID_", ADJUST_PROC_DEF_ID_, Types.VARCHAR);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public int updateAdjustProcDefModel(String ADJUST_PROC_DEF_ID_, String PROC_DEF_MODEL_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_) {
        String sql = "update FF_ADJUST_PROC_DEF set PROC_DEF_MODEL_ = ?, UPDATE_DATE_ = ?, OPERATOR_ID_ = ?, OPERATOR_NAME_ = ? where ADJUST_PROC_DEF_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_DEF_MODEL_, UPDATE_DATE_, OPERATOR_ID_, OPERATOR_NAME_, ADJUST_PROC_DEF_ID_);
    }

    @Override
    public int deleteAdjustProcDef(String ADJUST_PROC_DEF_ID_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_) {
        String sql = "delete from FF_ADJUST_PROC_DEF where ADJUST_PROC_DEF_ID_ = ?";
        return ffJdbcTemplate.update(sql, ADJUST_PROC_DEF_ID_);
    }
}