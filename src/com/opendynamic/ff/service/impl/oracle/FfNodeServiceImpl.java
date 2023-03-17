package com.opendynamic.ff.service.impl.oracle;

import java.math.BigDecimal;
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
        taskStatistic.put("TOTAL", ((BigDecimal) result.get("TOTAL")).doubleValue());
        taskStatistic.put("COMPLETE", ((BigDecimal) result.get("COMPLETE")).doubleValue());

        return taskStatistic;
    }

    @Override
    public Map<String, Object> getChildNodeStatistic(String NODE_ID_) {
        String sql = "select count(*) TOTAL, count(case when N.NODE_STATUS_ = '9' then 1 else null end) COMPLETE from FF_NODE N where N.PARENT_NODE_ID_ = ? and N.NODE_STATUS_ in ('1', '9')";
        Map<String, Object> result = ffJdbcTemplate.queryForMap(sql, NODE_ID_);
        Map<String, Object> subProcStatistic = new HashMap<>();
        subProcStatistic.put("TOTAL", ((BigDecimal) result.get("TOTAL")).doubleValue());
        subProcStatistic.put("COMPLETE", ((BigDecimal) result.get("COMPLETE")).doubleValue());

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
    public List<Map<String, Object>> selectNode(String NODE_ID_, String PARENT_NODE_ID_, String PROC_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, String ADJUST_SUB_PROC_DEF_ID_, List<String> NODE_TYPE_LIST, String NODE_CODE_, String NODE_NAME_, String PARENT_NODE_CODE_, Integer PRIORITY_, List<String> NODE_END_USER_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, List<String> ISOLATE_SUB_PROC_STATUS_LIST, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes, Integer page, Integer limit) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaNode(false, NODE_ID_, PARENT_NODE_ID_, PROC_ID_, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_LIST, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, PRIORITY_, NODE_END_USER_LIST, FROM_NODE_END_DATE_, TO_NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_LIST, ISOLATE_SUB_PROC_STATUS_LIST, NODE_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes);// 根据查询条件组装查询SQL语句
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
    public int countNode(String NODE_ID_, String PARENT_NODE_ID_, String PROC_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, String ADJUST_SUB_PROC_DEF_ID_, List<String> NODE_TYPE_LIST, String NODE_CODE_, String NODE_NAME_, String PARENT_NODE_CODE_, Integer PRIORITY_, List<String> NODE_END_USER_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, List<String> ISOLATE_SUB_PROC_STATUS_LIST, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaNode(true, NODE_ID_, PARENT_NODE_ID_, PROC_ID_, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_LIST, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, PRIORITY_, NODE_END_USER_LIST, FROM_NODE_END_DATE_, TO_NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_LIST, ISOLATE_SUB_PROC_STATUS_LIST, NODE_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaNode(boolean count, String NODE_ID_, String PARENT_NODE_ID_, String PROC_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, String ADJUST_SUB_PROC_DEF_ID_, List<String> NODE_TYPE_LIST, String NODE_CODE_, String NODE_NAME_, String PARENT_NODE_CODE_, Integer PRIORITY_, List<String> NODE_END_USER_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, List<String> ISOLATE_SUB_PROC_DEF_CODE_LIST, List<String> ISOLATE_SUB_PROC_STATUS_LIST, List<String> NODE_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, Boolean emptyParentNode, Boolean emptyPreviousNodes, Boolean emptyLastCompleteNodes) {// 组装查询SQL语句
        String sql;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (count) {
            sql = "select count(*) from FFV_NODE where 1 = 1";
        }
        else {
            sql = "select * from FFV_NODE where 1 = 1";
        }

        if (StringUtils.isNotEmpty(NODE_ID_)) {
            sql += " and NODE_ID_ = :NODE_ID_";
            paramMap.put("NODE_ID_", NODE_ID_);
        }
        if (StringUtils.isNotEmpty(PARENT_NODE_ID_)) {
            sql += " and PARENT_NODE_ID_ = :PARENT_NODE_ID_";
            paramMap.put("PARENT_NODE_ID_", PARENT_NODE_ID_);
        }
        if (StringUtils.isNotEmpty(PROC_ID_)) {
            sql += " and PROC_ID_ = :PROC_ID_";
            paramMap.put("PROC_ID_", PROC_ID_);
        }
        if (StringUtils.isNotEmpty(PREVIOUS_NODE_IDS_)) {
            sql += " and PREVIOUS_NODE_IDS_ like '%' || :PREVIOUS_NODE_IDS_ || '%'";
            paramMap.put("PREVIOUS_NODE_IDS_", PREVIOUS_NODE_IDS_);
        }
        if (StringUtils.isNotEmpty(LAST_COMPLETE_NODE_IDS_)) {
            sql += " and LAST_COMPLETE_NODE_IDS_ like '%' || :LAST_COMPLETE_NODE_IDS_ || '%'";
            paramMap.put("LAST_COMPLETE_NODE_IDS_", LAST_COMPLETE_NODE_IDS_);
        }
        if (StringUtils.isNotEmpty(SUB_PROC_DEF_ID_)) {
            sql += " and SUB_PROC_DEF_ID_ = :SUB_PROC_DEF_ID_";
            paramMap.put("SUB_PROC_DEF_ID_", SUB_PROC_DEF_ID_);
        }
        if (StringUtils.isNotEmpty(ADJUST_SUB_PROC_DEF_ID_)) {
            sql += " and ADJUST_SUB_PROC_DEF_ID_ = :ADJUST_SUB_PROC_DEF_ID_";
            paramMap.put("ADJUST_SUB_PROC_DEF_ID_", ADJUST_SUB_PROC_DEF_ID_);
        }
        if (NODE_TYPE_LIST != null && NODE_TYPE_LIST.size() > 0) {
            sql += " and NODE_TYPE_ in (:NODE_TYPE_LIST)";
            paramMap.put("NODE_TYPE_LIST", NODE_TYPE_LIST);
        }
        if (StringUtils.isNotEmpty(NODE_CODE_)) {
            sql += " and NODE_CODE_ = :NODE_CODE_";
            paramMap.put("NODE_CODE_", NODE_CODE_);
        }
        if (StringUtils.isNotEmpty(NODE_NAME_)) {
            sql += " and NODE_NAME_ like '%' || :NODE_NAME_ || '%'";
            paramMap.put("NODE_NAME_", NODE_NAME_);
        }
        if (StringUtils.isNotEmpty(PARENT_NODE_CODE_)) {
            sql += " and PARENT_NODE_CODE_ = :PARENT_NODE_CODE_";
            paramMap.put("PARENT_NODE_CODE_", PARENT_NODE_CODE_);
        }
        if (PRIORITY_ != null) {
            sql += " and PRIORITY_ = :PRIORITY_";
            paramMap.put("PRIORITY_", PRIORITY_);
        }
        if (NODE_END_USER_LIST != null && NODE_END_USER_LIST.size() > 0) {
            sql += " and NODE_END_USER_ in (:NODE_END_USER_LIST)";
            paramMap.put("NODE_END_USER_LIST", NODE_END_USER_LIST);
        }
        if (FROM_NODE_END_DATE_ != null) {
            sql += " and NODE_END_DATE_ >= :FROM_NODE_END_DATE_";
            paramMap.put("FROM_NODE_END_DATE_", FROM_NODE_END_DATE_);
        }
        if (TO_NODE_END_DATE_ != null) {
            sql += " and NODE_END_DATE_ <= :TO_NODE_END_DATE_";
            paramMap.put("TO_NODE_END_DATE_", TO_NODE_END_DATE_);
        }
        if (ISOLATE_SUB_PROC_DEF_CODE_LIST != null && ISOLATE_SUB_PROC_DEF_CODE_LIST.size() > 0) {
            sql += " and ISOLATE_SUB_PROC_DEF_CODE_ in (:ISOLATE_SUB_PROC_DEF_CODE_LIST)";
            paramMap.put("ISOLATE_SUB_PROC_DEF_CODE_LIST", ISOLATE_SUB_PROC_DEF_CODE_LIST);
        }
        if (ISOLATE_SUB_PROC_STATUS_LIST != null && ISOLATE_SUB_PROC_STATUS_LIST.size() > 0) {
            sql += " and ISOLATE_SUB_PROC_STATUS_ in (:ISOLATE_SUB_PROC_STATUS_LIST)";
            paramMap.put("ISOLATE_SUB_PROC_STATUS_LIST", ISOLATE_SUB_PROC_STATUS_LIST);
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
    public List<Map<String, Object>> selectParentNode(String NODE_ID_, String PROC_ID_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_STATUS_LIST, Boolean includeSelf, Boolean recursive) {
        if (StringUtils.isEmpty(NODE_ID_)) {
            throw new RuntimeException("errors.idRequired");
        }

        String sql = "select * from FFV_NODE where 1 = 1";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("NODE_ID_", NODE_ID_);

        if (StringUtils.isNotEmpty(PROC_ID_)) {
            sql += " and PROC_ID_ = :PROC_ID_";
            paramMap.put("PROC_ID_", PROC_ID_);
        }
        if (NODE_TYPE_LIST != null && NODE_TYPE_LIST.size() > 0) {
            sql += " and NODE_TYPE_ in (:NODE_TYPE_LIST)";
            paramMap.put("NODE_TYPE_LIST", NODE_TYPE_LIST);
        }
        if (StringUtils.isNotEmpty(NODE_CODE_)) {
            sql += " and NODE_CODE_ = :NODE_CODE_";
            paramMap.put("NODE_CODE_", NODE_CODE_);
        }
        if (NODE_STATUS_LIST != null && NODE_STATUS_LIST.size() > 0) {
            sql += " and NODE_STATUS_ in (:NODE_STATUS_LIST)";
            paramMap.put("NODE_STATUS_LIST", NODE_STATUS_LIST);
        }
        if (includeSelf == null || includeSelf.equals(false)) {
            sql += " and NODE_ID_ != :NODE_ID_";
        }
        if (recursive == null || recursive.equals(false)) {
            sql += " and (NODE_ID_ = (select PARENT_NODE_ID_ from FF_NODE where NODE_ID_ = :NODE_ID_) or NODE_ID_ = :NODE_ID_)";
        }
        else {
            sql += " connect by prior PARENT_NODE_ID_ = NODE_ID_ start with NODE_ID_ = :NODE_ID_";
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectChildNode(String NODE_ID_, String PROC_ID_, List<String> NODE_TYPE_LIST, String NODE_CODE_, List<String> NODE_STATUS_LIST, Boolean includeSelf, Boolean recursive) {
        if (StringUtils.isEmpty(NODE_ID_)) {
            throw new RuntimeException("errors.idRequired");
        }

        String sql = "select * from FFV_NODE where 1 = 1";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("NODE_ID_", NODE_ID_);

        if (StringUtils.isNotEmpty(PROC_ID_)) {
            sql += " and PROC_ID_ = :PROC_ID_";
            paramMap.put("PROC_ID_", PROC_ID_);
        }
        if (NODE_TYPE_LIST != null && NODE_TYPE_LIST.size() > 0) {
            sql += " and NODE_TYPE_ in (:NODE_TYPE_LIST)";
            paramMap.put("NODE_TYPE_LIST", NODE_TYPE_LIST);
        }
        if (StringUtils.isNotEmpty(NODE_CODE_)) {
            sql += " and NODE_CODE_ = :NODE_CODE_";
            paramMap.put("NODE_CODE_", NODE_CODE_);
        }
        if (NODE_STATUS_LIST != null && NODE_STATUS_LIST.size() > 0) {
            sql += " and NODE_STATUS_ in (:NODE_STATUS_LIST)";
            paramMap.put("NODE_STATUS_LIST", NODE_STATUS_LIST);
        }

        if (includeSelf == null || includeSelf.equals(false)) {
            sql += " and NODE_ID_ != :NODE_ID_";
        }
        if (recursive == null || recursive.equals(false)) {
            sql += " and (PARENT_NODE_ID_ = :NODE_ID_ or NODE_ID_ = :NODE_ID_)";
        }
        else {
            sql += " connect by prior NODE_ID_ = PARENT_NODE_ID_ start with NODE_ID_ = :NODE_ID_";
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
        sql.append(" order by DECODE(NODE_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < NODE_ID_LIST.size(); i++) {
            sql.append(" '").append(NODE_ID_LIST.get(i)).append("', ").append(i);
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
    public int insertNode(String NODE_ID_, String PARENT_NODE_ID_, String PROC_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, String ADJUST_SUB_PROC_DEF_ID_, String NODE_TYPE_, String NODE_CODE_, String NODE_NAME_, String PARENT_NODE_CODE_, String ASSIGNEE_, String CANDIDATE_, String ACTION_, String DUE_DATE_, String COMPLETE_EXPRESSION_, String COMPLETE_RETURN_, String EXCLUSIVE_, String FORWARDABLE_, String AUTO_COMPLETE_SAME_ASSIGNEE_, String AUTO_COMPLETE_EMPTY_ASSIGNEE_, String INFORM_, Integer PRIORITY_, String NODE_END_USER_, String NODE_END_USER_NAME_, Date NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_, String ISOLATE_SUB_PROC_STATUS_, String NODE_STATUS_, Date CREATION_DATE_) {
        String sql = "insert into FF_NODE (NODE_ID_, PARENT_NODE_ID_, PROC_ID_, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, ASSIGNEE_, CANDIDATE_, ACTION_, DUE_DATE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, FORWARDABLE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_STATUS_, NODE_STATUS_, CREATION_DATE_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int count = ffJdbcTemplate.update(sql, NODE_ID_, PARENT_NODE_ID_, PROC_ID_, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, ASSIGNEE_, CANDIDATE_, ACTION_, DUE_DATE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, FORWARDABLE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_STATUS_, NODE_STATUS_, CREATION_DATE_);

        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_INSERT);

        return count;
    }

    @Override
    public int updateNode(String NODE_ID_, String PROC_ID_, String PREVIOUS_NODE_IDS_, String LAST_COMPLETE_NODE_IDS_, String SUB_PROC_DEF_ID_, String ADJUST_SUB_PROC_DEF_ID_, String NODE_TYPE_, String NODE_CODE_, String NODE_NAME_, String PARENT_NODE_CODE_, String ASSIGNEE_, String CANDIDATE_, String ACTION_, String DUE_DATE_, String COMPLETE_EXPRESSION_, String COMPLETE_RETURN_, String EXCLUSIVE_, String FORWARDABLE_, String AUTO_COMPLETE_SAME_ASSIGNEE_, String AUTO_COMPLETE_EMPTY_ASSIGNEE_, String INFORM_, Integer PRIORITY_, String NODE_END_USER_, String NODE_END_USER_NAME_, Date NODE_END_DATE_, String ISOLATE_SUB_PROC_DEF_CODE_) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_NODE set PROC_ID_ = ?, PREVIOUS_NODE_IDS_ = ?, LAST_COMPLETE_NODE_IDS_ = ?, SUB_PROC_DEF_ID_ = ?, ADJUST_SUB_PROC_DEF_ID_ = ?, NODE_TYPE_ = ?, NODE_CODE_ = ?, NODE_NAME_ = ?, PARENT_NODE_CODE_ = ?, ASSIGNEE_ = ?, CANDIDATE_ = ?, ACTION_ = ?, DUE_DATE_ = ?, COMPLETE_EXPRESSION_ = ?, COMPLETE_RETURN_ = ?, EXCLUSIVE_ = ?, FORWARDABLE_ = ?, AUTO_COMPLETE_SAME_ASSIGNEE_ = ?, AUTO_COMPLETE_EMPTY_ASSIGNEE_ = ?, INFORM_ = ?, PRIORITY_ = ?, NODE_END_USER_ = ?, NODE_END_USER_NAME_ = ?, NODE_END_DATE_ = ?, ISOLATE_SUB_PROC_STATUS_ = ? where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_ID_, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, ASSIGNEE_, CANDIDATE_, ACTION_, DUE_DATE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, FORWARDABLE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, NODE_ID_);
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
        String sql = "select NODE_ID_ from FF_NODE connect by prior NODE_ID_ = PARENT_NODE_ID_ and NODE_TYPE_ != 'BRANCH' start with NODE_ID_ = ?";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, BRANCH_ID_);
        for (Map<String, Object> node : result) {
            ffOperationService.insertNodeOp(OdUtils.getUuid(), (String) node.get("NODE_ID_"), FfOperationService.OPERATION_TYPE_UPDATE);
        }

        sql = "update FF_NODE set ADJUST_SUB_PROC_DEF_ID_ = ? where NODE_ID_ in (select NODE_ID_ from FF_NODE connect by prior NODE_ID_ = PARENT_NODE_ID_ and NODE_TYPE_ != 'BRANCH' start with NODE_ID_ = ?)";
        return ffJdbcTemplate.update(sql, ADJUST_SUB_PROC_DEF_ID_, BRANCH_ID_);
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
    public int deleteNode(String NODE_ID_) {
        ffOperationService.insertNodeOp(OdUtils.getUuid(), NODE_ID_, FfOperationService.OPERATION_TYPE_DELETE);

        String sql = "delete from FF_NODE where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, NODE_ID_);
    }

    @Override
    public int deleteNodeByProcId(String PROC_ID_) {
        List<Map<String, Object>> nodeList = selectNode(null, null, PROC_ID_, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, -1);
        for (Map<String, Object> node : nodeList) {
            deleteNode((String) node.get("NODE_ID_"));
        }

        return nodeList.size();
    }
}