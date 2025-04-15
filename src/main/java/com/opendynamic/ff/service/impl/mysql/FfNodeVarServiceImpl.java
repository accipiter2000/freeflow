package com.opendynamic.ff.service.impl.mysql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opendynamic.OdSqlCriteria;
import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.service.FfNodeVarService;
import com.opendynamic.ff.service.FfOperationService;
import com.opendynamic.ff.service.FfService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfNodeVarServiceImpl implements FfNodeVarService {
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfOperationService ffOperationService;
    @Autowired
    private JdbcTemplate ffJdbcTemplate;

    @Override
    public Map<String, Object> loadNodeVar(String NODE_VAR_ID_) {
        String sql = "select * from FFV_NODE_VAR where NODE_VAR_ID_ = ?";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, NODE_VAR_ID_);
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectNodeVar(String NODE_VAR_ID_, List<String> NODE_VAR_ID_LIST, String NODE_ID_, List<String> NODE_ID_LIST, String VAR_TYPE_, List<String> VAR_TYPE_LIST, String VAR_NAME_, List<String> VAR_NAME_LIST, String PROC_ID_, List<String> PROC_ID_LIST, Boolean recursive, Integer page, Integer limit) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaNodeVar(false, NODE_VAR_ID_, NODE_VAR_ID_LIST, NODE_ID_, NODE_ID_LIST, VAR_TYPE_, VAR_TYPE_LIST, VAR_NAME_, VAR_NAME_LIST, PROC_ID_, PROC_ID_LIST, recursive);// 根据查询条件组装查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        if (page != null && limit != null && limit > 0) {// 分页
            sql = sql + " limit " + (page - 1) * limit + ", " + limit;
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public int countNodeVar(String NODE_VAR_ID_, List<String> NODE_VAR_ID_LIST, String NODE_ID_, List<String> NODE_ID_LIST, String VAR_TYPE_, List<String> VAR_TYPE_LIST, String VAR_NAME_, List<String> VAR_NAME_LIST, String PROC_ID_, List<String> PROC_ID_LIST, Boolean recursive) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaNodeVar(true, NODE_VAR_ID_, NODE_VAR_ID_LIST, NODE_ID_, NODE_ID_LIST, VAR_TYPE_, VAR_TYPE_LIST, VAR_NAME_, VAR_NAME_LIST, PROC_ID_, PROC_ID_LIST, recursive);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaNodeVar(boolean count, String NODE_VAR_ID_, List<String> NODE_VAR_ID_LIST, String NODE_ID_, List<String> NODE_ID_LIST, String VAR_TYPE_, List<String> VAR_TYPE_LIST, String VAR_NAME_, List<String> VAR_NAME_LIST, String PROC_ID_, List<String> PROC_ID_LIST, Boolean recursive) {// 组装查询SQL语句
        if (recursive != null && recursive.equals(true)) {
            if (StringUtils.isEmpty(NODE_ID_)) {
                throw new RuntimeException("errors.idRequired");
            }
            if (NODE_ID_LIST != null && !NODE_ID_LIST.isEmpty()) {
                throw new RuntimeException("errors.cannotRecursiveOnMultipulNodes");
            }
        }

        String sql;
        Map<String, Object> paramMap = new HashMap<>();

        if (count) {
            sql = "select count(*) from FFV_NODE_VAR where 1 = 1";
        }
        else {
            sql = "select * from FFV_NODE_VAR where 1 = 1";
        }

        if (NODE_VAR_ID_ != null) {
            sql += " and NODE_VAR_ID_ = :NODE_VAR_ID_";
            paramMap.put("NODE_VAR_ID_", NODE_VAR_ID_);
        }
        if (NODE_VAR_ID_LIST != null && !NODE_VAR_ID_LIST.isEmpty()) {
            sql += " and NODE_VAR_ID_ in (:NODE_VAR_ID_LIST)";
            paramMap.put("NODE_VAR_ID_LIST", NODE_VAR_ID_LIST);
        }
        if (recursive != null && recursive.equals(true)) {
            sql += " and NODE_ID_ in (:NODE_ID_LIST)";
            List<Map<String, Object>> nodeList = ffNodeService.selectParentNode(NODE_ID_, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, recursive, true, FfService.DATA_SCOPE_NODE);
            NODE_ID_LIST = OdUtils.collect(nodeList, "NODE_ID_", String.class);
            paramMap.put("NODE_ID_LIST", NODE_ID_LIST);
        }
        else {
            if (StringUtils.isNotEmpty(NODE_ID_)) {
                sql += " and NODE_ID_ = :NODE_ID_";
                paramMap.put("NODE_ID_", NODE_ID_);
            }
            if (NODE_ID_LIST != null && !NODE_ID_LIST.isEmpty()) {
                sql += " and NODE_ID_ in (:NODE_ID_LIST)";
                paramMap.put("NODE_ID_LIST", NODE_ID_LIST);
            }
        }
        if (VAR_TYPE_ != null) {
            sql += " and VAR_TYPE_ = :VAR_TYPE_";
            paramMap.put("VAR_TYPE_", VAR_TYPE_);
        }
        if (VAR_TYPE_LIST != null && !VAR_TYPE_LIST.isEmpty()) {
            sql += " and VAR_TYPE_ in (:VAR_TYPE_LIST)";
            paramMap.put("VAR_TYPE_LIST", VAR_TYPE_LIST);
        }
        if (VAR_NAME_ != null) {
            sql += " and VAR_NAME_ like concat('%',:VAR_NAME_,'%')";
            paramMap.put("VAR_NAME_", VAR_NAME_);
        }
        if (VAR_NAME_LIST != null && !VAR_NAME_LIST.isEmpty()) {
            sql += " and VAR_NAME_ in (:VAR_NAME_LIST)";
            paramMap.put("VAR_NAME_LIST", VAR_NAME_LIST);
        }
        if (PROC_ID_ != null) {
            sql += " and PROC_ID_ = :PROC_ID_";
            paramMap.put("PROC_ID_", PROC_ID_);
        }
        if (PROC_ID_LIST != null && !PROC_ID_LIST.isEmpty()) {
            sql += " and PROC_ID_ in (:PROC_ID_LIST)";
            paramMap.put("PROC_ID_LIST", PROC_ID_LIST);
        }

        if (recursive != null && recursive.equals(true)) {
            sql += " order by FIELD(NODE_ID_,";// 按主键列表顺序排序
            for (int i = 0; i < NODE_ID_LIST.size(); i++) {
                sql = sql + " '" + NODE_ID_LIST.get(i) + "'";
                if (i < NODE_ID_LIST.size() - 1) {
                    sql += ",";
                }
                else {
                    sql += ")";
                }
            }
        }

        return new OdSqlCriteria(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectNodeVarByIdList(List<String> NODE_VAR_ID_LIST) {
        if (NODE_VAR_ID_LIST == null || NODE_VAR_ID_LIST.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder(NODE_VAR_ID_LIST.size() * 50 + 200);
        Map<String, Object> paramMap = new HashMap<>();

        sql.append("select * from FFV_NODE_VAR where NODE_VAR_ID_ in (:NODE_VAR_ID_LIST)");
        paramMap.put("NODE_VAR_ID_LIST", NODE_VAR_ID_LIST);
        sql.append(" order by FIELD(NODE_VAR_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < NODE_VAR_ID_LIST.size(); i++) {
            sql.append(" '").append(NODE_VAR_ID_LIST.get(i)).append("'");
            if (i < NODE_VAR_ID_LIST.size() - 1) {
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
    public int insertNodeVar(String NODE_VAR_ID_, String NODE_ID_, String VAR_TYPE_, String VAR_NAME_, String VALUE_, Serializable OBJ_, Date CREATION_DATE_) {
        int count;

        if (!FfService.VAR_TYPE_OBJECT.equals(VAR_TYPE_)) {// String
            String sql = "insert into FF_NODE_VAR (NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, VAR_NAME_, VALUE_, CREATION_DATE_) values (?, ?, ?, ?, ?, ?)";
            count = ffJdbcTemplate.update(sql, NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, VAR_NAME_, VALUE_, CREATION_DATE_);
        }
        else {// object
            ObjectOutputStream objectOutputStream = null;
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                if (OBJ_ != null) {
                    objectOutputStream.writeObject(OBJ_);
                }
                ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

                String sql = "insert into FF_NODE_VAR (NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, VAR_NAME_, OBJ_, CREATION_DATE_) values (?, ?, ?, ?, ?, ?)";
                count = ffJdbcTemplate.update(sql, new Object[] { NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, VAR_NAME_, new SqlLobValue(inputStream, byteArrayOutputStream.size(), new DefaultLobHandler()), CREATION_DATE_ }, new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.TIMESTAMP });
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                try {
                    objectOutputStream.close();
                }
                catch (Exception e) {
                }
            }
        }

        ffOperationService.insertNodeVarOp(OdUtils.getUuid(), NODE_VAR_ID_, FfService.OPERATION_TYPE_INSERT);

        return count;
    }

    @Override
    public int updateNodeVar(String NODE_VAR_ID_, String VAR_TYPE_, String VAR_NAME_, String VALUE_, Serializable OBJ_) {
        int count;

        ffOperationService.insertNodeVarOp(OdUtils.getUuid(), NODE_VAR_ID_, FfService.OPERATION_TYPE_UPDATE);

        if (!FfService.VAR_TYPE_OBJECT.equals(VAR_TYPE_)) {// String
            String sql = "update FF_NODE_VAR set VAR_TYPE_ = ?, VAR_NAME_ = ?, VALUE_ = ?, OBJ_ = null where NODE_VAR_ID_ = ?";
            count = ffJdbcTemplate.update(sql, VAR_TYPE_, VAR_NAME_, VALUE_, NODE_VAR_ID_);
        }
        else {// object
            ObjectOutputStream objectOutputStream = null;
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                if (OBJ_ != null) {
                    objectOutputStream.writeObject(OBJ_);
                }
                ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

                String sql = "update FF_NODE_VAR set VAR_TYPE_ = ?, VAR_NAME_ = ?, VALUE_ = null, OBJ_ = ? where NODE_VAR_ID_ = ?";
                count = ffJdbcTemplate.update(sql, new Object[] { VAR_TYPE_, VAR_NAME_, new SqlLobValue(inputStream, byteArrayOutputStream.size(), new DefaultLobHandler()), NODE_VAR_ID_ }, new int[] { Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.VARCHAR });
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                try {
                    objectOutputStream.close();
                }
                catch (Exception e) {
                }
            }
        }

        return count;
    }

    @Override
    public List<String> updateNodeVar(String NODE_ID_, Map<String, Object> nodeVarMap) {
        List<String> nodeVarIdList = new ArrayList<>();

        if (nodeVarMap == null) {
            return nodeVarIdList;
        }

        List<Map<String, Object>> nodeVarList = selectNodeVar(null, null, NODE_ID_, null, null, null, null, null, null, null, false, 1, -1);
        List<String> nodeVarKeyList = OdUtils.collect(nodeVarList, "VAR_NAME_", String.class);

        String NODE_VAR_ID_;
        String VAR_TYPE_;
        String VALUE_;
        Serializable OBJ_;
        int index;
        for (String key : nodeVarMap.keySet()) {
            if (nodeVarMap.get(key) instanceof String) {
                VAR_TYPE_ = FfService.VAR_TYPE_STRING;
                VALUE_ = (String) nodeVarMap.get(key);
                OBJ_ = null;
            }
            else {
                VAR_TYPE_ = FfService.VAR_TYPE_OBJECT;
                VALUE_ = null;
                OBJ_ = (Serializable) nodeVarMap.get(key);
            }

            index = nodeVarKeyList.indexOf(key);
            if (index == -1) {// insert
                NODE_VAR_ID_ = OdUtils.getUuid();
                insertNodeVar(NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, key, VALUE_, OBJ_, new Date());
            }
            else {// update
                NODE_VAR_ID_ = (String) nodeVarList.get(index).get("NODE_VAR_ID_");
                updateNodeVar(NODE_VAR_ID_, VAR_TYPE_, key, VALUE_, OBJ_);
            }

            nodeVarIdList.add(NODE_VAR_ID_);
        }

        return nodeVarIdList;
    }

    @Override
    public int deleteNodeVar(String NODE_VAR_ID_) {
        ffOperationService.insertNodeVarOp(OdUtils.getUuid(), NODE_VAR_ID_, FfService.OPERATION_TYPE_DELETE);

        String sql = "delete from FF_NODE_VAR where NODE_VAR_ID_ = ?";
        return ffJdbcTemplate.update(sql, NODE_VAR_ID_);
    }

    @Override
    public int deleteNodeVarByNodeId(String NODE_ID_) {
        List<Map<String, Object>> nodeVarList = selectNodeVar(null, null, NODE_ID_, null, null, null, null, null, null, null, false, 1, -1);
        for (Map<String, Object> nodeVar : nodeVarList) {
            deleteNodeVar((String) nodeVar.get("NODE_VAR_ID_"));
        }

        return nodeVarList.size();
    }
}