package com.opendynamic.ff.service.impl.dameng;

import java.io.InputStream;
import java.math.BigDecimal;
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
import com.opendynamic.ff.service.FfProcDefService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfProcDefServiceImpl implements FfProcDefService {// REFINED
    @Autowired
    private JdbcTemplate ffJdbcTemplate;

    @Override
    public Map<String, Object> loadProcDef(String PROC_DEF_ID_) {
        String sql = "select * from FFV_PROC_DEF where PROC_DEF_ID_ = ?";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, PROC_DEF_ID_);
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public Map<String, Object> loadProcDefByCode(String PROC_DEF_CODE_) {
        String sql = "select * from FFV_PROC_DEF where PROC_DEF_CODE_ = ? and PROC_DEF_STATUS_ = '1' order by VERSION_ desc";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, PROC_DEF_CODE_);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public InputStream loadProcDefDiagramFile(String PROC_DEF_ID_) {
        String sql = "select PROC_DEF_DIAGRAM_FILE_ from FF_PROC_DEF where PROC_DEF_ID_ = ?";
        return ffJdbcTemplate.queryForObject(sql, new Object[] { PROC_DEF_ID_ }, new RowMapper<InputStream>() {
            public InputStream mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getBinaryStream(1);
            }
        });
    }

    @Override
    public List<Map<String, Object>> selectProcDef(String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, String EXT_ATTR_1_, List<String> EXT_ATTR_1_LIST, String EXT_ATTR_2_, List<String> EXT_ATTR_2_LIST, String EXT_ATTR_3_, List<String> EXT_ATTR_3_LIST, String EXT_ATTR_4_, List<String> EXT_ATTR_4_LIST, String EXT_ATTR_5_, List<String> EXT_ATTR_5_LIST, String EXT_ATTR_6_, List<String> EXT_ATTR_6_LIST, String EXT_ATTR_7_, List<String> EXT_ATTR_7_LIST, String EXT_ATTR_8_, List<String> EXT_ATTR_8_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Integer page,
            Integer limit) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaProcDef(false, PROC_DEF_ID_, PROC_DEF_ID_LIST, PROC_DEF_CODE_, PROC_DEF_CODE_LIST, PROC_DEF_NAME_, PROC_DEF_NAME_LIST, PROC_DEF_CAT_, PROC_DEF_CAT_LIST, EXT_ATTR_1_, EXT_ATTR_1_LIST, EXT_ATTR_2_, EXT_ATTR_2_LIST, EXT_ATTR_3_, EXT_ATTR_3_LIST, EXT_ATTR_4_, EXT_ATTR_4_LIST, EXT_ATTR_5_, EXT_ATTR_5_LIST, EXT_ATTR_6_, EXT_ATTR_6_LIST, EXT_ATTR_7_, EXT_ATTR_7_LIST, EXT_ATTR_8_, EXT_ATTR_8_LIST, VERSION_, VERSION_LIST, PROC_DEF_STATUS_, PROC_DEF_STATUS_LIST);// 根据查询条件组装查询SQL语句
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
    public int countProcDef(String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, String EXT_ATTR_1_, List<String> EXT_ATTR_1_LIST, String EXT_ATTR_2_, List<String> EXT_ATTR_2_LIST, String EXT_ATTR_3_, List<String> EXT_ATTR_3_LIST, String EXT_ATTR_4_, List<String> EXT_ATTR_4_LIST, String EXT_ATTR_5_, List<String> EXT_ATTR_5_LIST, String EXT_ATTR_6_, List<String> EXT_ATTR_6_LIST, String EXT_ATTR_7_, List<String> EXT_ATTR_7_LIST, String EXT_ATTR_8_, List<String> EXT_ATTR_8_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaProcDef(true, PROC_DEF_ID_, PROC_DEF_ID_LIST, PROC_DEF_CODE_, PROC_DEF_CODE_LIST, PROC_DEF_NAME_, PROC_DEF_NAME_LIST, PROC_DEF_CAT_, PROC_DEF_CAT_LIST, EXT_ATTR_1_, EXT_ATTR_1_LIST, EXT_ATTR_2_, EXT_ATTR_2_LIST, EXT_ATTR_3_, EXT_ATTR_3_LIST, EXT_ATTR_4_, EXT_ATTR_4_LIST, EXT_ATTR_5_, EXT_ATTR_5_LIST, EXT_ATTR_6_, EXT_ATTR_6_LIST, EXT_ATTR_7_, EXT_ATTR_7_LIST, EXT_ATTR_8_, EXT_ATTR_8_LIST, VERSION_, VERSION_LIST, PROC_DEF_STATUS_, PROC_DEF_STATUS_LIST);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaProcDef(boolean count, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, String EXT_ATTR_1_, List<String> EXT_ATTR_1_LIST, String EXT_ATTR_2_, List<String> EXT_ATTR_2_LIST, String EXT_ATTR_3_, List<String> EXT_ATTR_3_LIST, String EXT_ATTR_4_, List<String> EXT_ATTR_4_LIST, String EXT_ATTR_5_, List<String> EXT_ATTR_5_LIST, String EXT_ATTR_6_, List<String> EXT_ATTR_6_LIST, String EXT_ATTR_7_, List<String> EXT_ATTR_7_LIST, String EXT_ATTR_8_, List<String> EXT_ATTR_8_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST) {// 组装查询SQL语句
        String sql;
        Map<String, Object> paramMap = new HashMap<>();

        if (count) {
            sql = "select count(*) from FFV_PROC_DEF where 1 = 1";
        }
        else {
            sql = "select * from FFV_PROC_DEF where 1 = 1";
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
            sql += " and PROC_DEF_NAME_ like '%' || :PROC_DEF_NAME_ || '%'";
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
        if (EXT_ATTR_1_ != null) {
            sql += " and EXT_ATTR_1_ = :EXT_ATTR_1_";
            paramMap.put("EXT_ATTR_1_", EXT_ATTR_1_);
        }
        if (EXT_ATTR_1_ != null && !EXT_ATTR_1_LIST.isEmpty()) {
            sql += " and EXT_ATTR_1_ in (:EXT_ATTR_1_LIST)";
            paramMap.put("EXT_ATTR_1_LIST", EXT_ATTR_1_LIST);
        }
        if (EXT_ATTR_2_ != null) {
            sql += " and EXT_ATTR_2_ = :EXT_ATTR_2_";
            paramMap.put("EXT_ATTR_2_", EXT_ATTR_2_);
        }
        if (EXT_ATTR_2_ != null && !EXT_ATTR_2_LIST.isEmpty()) {
            sql += " and EXT_ATTR_2_ in (:EXT_ATTR_2_LIST)";
            paramMap.put("EXT_ATTR_2_LIST", EXT_ATTR_2_LIST);
        }
        if (EXT_ATTR_3_ != null) {
            sql += " and EXT_ATTR_3_ = :EXT_ATTR_3_";
            paramMap.put("EXT_ATTR_3_", EXT_ATTR_3_);
        }
        if (EXT_ATTR_3_ != null && !EXT_ATTR_3_LIST.isEmpty()) {
            sql += " and EXT_ATTR_3_ in (:EXT_ATTR_3_LIST)";
            paramMap.put("EXT_ATTR_3_LIST", EXT_ATTR_3_LIST);
        }
        if (EXT_ATTR_4_ != null) {
            sql += " and EXT_ATTR_4_ = :EXT_ATTR_4_";
            paramMap.put("EXT_ATTR_4_", EXT_ATTR_4_);
        }
        if (EXT_ATTR_4_ != null && !EXT_ATTR_4_LIST.isEmpty()) {
            sql += " and EXT_ATTR_4_ in (:EXT_ATTR_4_LIST)";
            paramMap.put("EXT_ATTR_4_LIST", EXT_ATTR_4_LIST);
        }
        if (EXT_ATTR_5_ != null) {
            sql += " and EXT_ATTR_5_ = :EXT_ATTR_5_";
            paramMap.put("EXT_ATTR_5_", EXT_ATTR_5_);
        }
        if (EXT_ATTR_5_ != null && !EXT_ATTR_5_LIST.isEmpty()) {
            sql += " and EXT_ATTR_5_ in (:EXT_ATTR_5_LIST)";
            paramMap.put("EXT_ATTR_5_LIST", EXT_ATTR_5_LIST);
        }
        if (EXT_ATTR_6_ != null) {
            sql += " and EXT_ATTR_6_ = :EXT_ATTR_6_";
            paramMap.put("EXT_ATTR_6_", EXT_ATTR_6_);
        }
        if (EXT_ATTR_6_ != null && !EXT_ATTR_6_LIST.isEmpty()) {
            sql += " and EXT_ATTR_6_ in (:EXT_ATTR_6_LIST)";
            paramMap.put("EXT_ATTR_6_LIST", EXT_ATTR_6_LIST);
        }
        if (EXT_ATTR_7_ != null) {
            sql += " and EXT_ATTR_7_ = :EXT_ATTR_7_";
            paramMap.put("EXT_ATTR_7_", EXT_ATTR_7_);
        }
        if (EXT_ATTR_7_ != null && !EXT_ATTR_7_LIST.isEmpty()) {
            sql += " and EXT_ATTR_7_ in (:EXT_ATTR_7_LIST)";
            paramMap.put("EXT_ATTR_7_LIST", EXT_ATTR_7_LIST);
        }
        if (EXT_ATTR_8_ != null) {
            sql += " and EXT_ATTR_8_ = :EXT_ATTR_8_";
            paramMap.put("EXT_ATTR_8_", EXT_ATTR_8_);
        }
        if (EXT_ATTR_8_ != null && !EXT_ATTR_8_LIST.isEmpty()) {
            sql += " and EXT_ATTR_8_ in (:EXT_ATTR_8_LIST)";
            paramMap.put("EXT_ATTR_8_LIST", EXT_ATTR_8_LIST);
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
            sql += " order by PROC_DEF_CODE_, VERSION_ desc";
        }

        return new OdSqlCriteria(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectProcDefByIdList(List<String> PROC_DEF_ID_LIST) {
        if (PROC_DEF_ID_LIST == null || PROC_DEF_ID_LIST.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder(PROC_DEF_ID_LIST.size() * 50 + 200);
        Map<String, Object> paramMap = new HashMap<>();

        sql.append("select * from FFV_PROC_DEF where PROC_DEF_ID_ in (:PROC_DEF_ID_LIST)");
        paramMap.put("PROC_DEF_ID_LIST", PROC_DEF_ID_LIST);
        sql.append(" order by DECODE(PROC_DEF_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < PROC_DEF_ID_LIST.size(); i++) {
            sql.append(" '").append(PROC_DEF_ID_LIST.get(i)).append("', ").append(i);
            if (i < PROC_DEF_ID_LIST.size() - 1) {
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
    public int insertProcDef(String PROC_DEF_ID_, String PROC_DEF_CODE_, String PROC_DEF_NAME_, String PROC_DEF_CAT_, String PROC_DEF_MODEL_, InputStream PROC_DEF_DIAGRAM_FILE_, String PROC_DEF_DIAGRAM_FILE_NAME_, Integer PROC_DEF_DIAGRAM_FILE_LENGTH_, Integer PROC_DEF_DIAGRAM_WIDTH_, Integer PROC_DEF_DIAGRAM_HEIGHT_, String MEMO_, String EXT_ATTR_1_, String EXT_ATTR_2_, String EXT_ATTR_3_, String EXT_ATTR_4_, String EXT_ATTR_5_, String EXT_ATTR_6_, String EXT_ATTR_7_, String EXT_ATTR_8_, String PROC_DEF_STATUS_, Date CREATION_DATE_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_) {
        int VERSION_ = 1;
        Map<String, Object> procDef = loadProcDefByCode(PROC_DEF_CODE_);
        if (procDef != null) {
            VERSION_ = ((BigDecimal) procDef.get("VERSION_")).intValue() + 1;
        }

        String sql = "insert into FF_PROC_DEF (PROC_DEF_ID_, PROC_DEF_CODE_, PROC_DEF_NAME_, PROC_DEF_CAT_, PROC_DEF_MODEL_, PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_NAME_, PROC_DEF_DIAGRAM_FILE_LENGTH_, PROC_DEF_DIAGRAM_WIDTH_, PROC_DEF_DIAGRAM_HEIGHT_, MEMO_, EXT_ATTR_1_, EXT_ATTR_2_, EXT_ATTR_3_, EXT_ATTR_4_, EXT_ATTR_5_, EXT_ATTR_6_, EXT_ATTR_7_, EXT_ATTR_8_, VERSION_, PROC_DEF_STATUS_, CREATION_DATE_, UPDATE_DATE_, OPERATOR_ID_, OPERATOR_NAME_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return ffJdbcTemplate.update(sql, new Object[] { PROC_DEF_ID_, PROC_DEF_CODE_, PROC_DEF_NAME_, PROC_DEF_CAT_, PROC_DEF_MODEL_, new SqlLobValue(PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_LENGTH_, new DefaultLobHandler()), PROC_DEF_DIAGRAM_FILE_NAME_, PROC_DEF_DIAGRAM_FILE_LENGTH_, PROC_DEF_DIAGRAM_WIDTH_, PROC_DEF_DIAGRAM_HEIGHT_, MEMO_, EXT_ATTR_1_, EXT_ATTR_2_, EXT_ATTR_3_, EXT_ATTR_4_, EXT_ATTR_5_, EXT_ATTR_6_, EXT_ATTR_7_, EXT_ATTR_8_, VERSION_, PROC_DEF_STATUS_, CREATION_DATE_, UPDATE_DATE_, OPERATOR_ID_, OPERATOR_NAME_ },
                new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR });
    }

    @Override
    public int updateProcDefDiagramFile(String PROC_DEF_ID_, InputStream PROC_DEF_DIAGRAM_FILE_, String PROC_DEF_DIAGRAM_FILE_NAME_, Integer PROC_DEF_DIAGRAM_FILE_LENGTH_, Integer PROC_DEF_DIAGRAM_WIDTH_, Integer PROC_DEF_DIAGRAM_HEIGHT_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_) {
        String sql = "update FF_PROC_DEF set PROC_DEF_ID_ = :PROC_DEF_ID_";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (PROC_DEF_DIAGRAM_FILE_LENGTH_ != 0) {// 更新文件
            sql += ", PROC_DEF_DIAGRAM_FILE_ = :PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_NAME_ = :PROC_DEF_DIAGRAM_FILE_NAME_, PROC_DEF_DIAGRAM_FILE_LENGTH_ = :PROC_DEF_DIAGRAM_FILE_LENGTH_";
            parameterSource.addValue("PROC_DEF_DIAGRAM_FILE_", new SqlLobValue(PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_LENGTH_, new DefaultLobHandler()), Types.BLOB);
            parameterSource.addValue("PROC_DEF_DIAGRAM_FILE_NAME_", PROC_DEF_DIAGRAM_FILE_NAME_, Types.VARCHAR);
            parameterSource.addValue("PROC_DEF_DIAGRAM_FILE_LENGTH_", PROC_DEF_DIAGRAM_FILE_LENGTH_, Types.INTEGER);
        }
        sql += " , PROC_DEF_DIAGRAM_WIDTH_ = :PROC_DEF_DIAGRAM_WIDTH_, PROC_DEF_DIAGRAM_HEIGHT_ = :PROC_DEF_DIAGRAM_HEIGHT_, UPDATE_DATE_ = :UPDATE_DATE_, OPERATOR_ID_ = :OPERATOR_ID_, OPERATOR_NAME_ = :OPERATOR_NAME_ where PROC_DEF_ID_ = :PROC_DEF_ID_";
        parameterSource.addValue("PROC_DEF_DIAGRAM_WIDTH_", PROC_DEF_DIAGRAM_WIDTH_, Types.INTEGER);
        parameterSource.addValue("PROC_DEF_DIAGRAM_HEIGHT_", PROC_DEF_DIAGRAM_HEIGHT_, Types.INTEGER);
        parameterSource.addValue("UPDATE_DATE_", UPDATE_DATE_, Types.TIMESTAMP);
        parameterSource.addValue("OPERATOR_ID_", OPERATOR_ID_, Types.VARCHAR);
        parameterSource.addValue("OPERATOR_NAME_", OPERATOR_NAME_, Types.VARCHAR);
        parameterSource.addValue("PROC_DEF_ID_", PROC_DEF_ID_, Types.VARCHAR);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public int disableProcDef(String PROC_DEF_ID_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_) {
        String sql = "update FF_PROC_DEF set PROC_DEF_STATUS_ = '0', UPDATE_DATE_ = ?, OPERATOR_ID_ = ?, OPERATOR_NAME_ = ? where PROC_DEF_ID_ = ?";
        return ffJdbcTemplate.update(sql, UPDATE_DATE_, OPERATOR_ID_, OPERATOR_NAME_, PROC_DEF_ID_);
    }

    @Override
    public int enableProcDef(String PROC_DEF_ID_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_) {
        String sql = "update FF_PROC_DEF set PROC_DEF_STATUS_ = '1', UPDATE_DATE_ = ?, OPERATOR_ID_ = ?, OPERATOR_NAME_ = ? where PROC_DEF_ID_ = ?";
        return ffJdbcTemplate.update(sql, UPDATE_DATE_, OPERATOR_ID_, OPERATOR_NAME_, PROC_DEF_ID_);
    }

    @Override
    public int deleteProcDef(String PROC_DEF_ID_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_) {
        String sql = "delete from FF_PROC_DEF where PROC_DEF_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_DEF_ID_);
    }
}