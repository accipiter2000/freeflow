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
    public List<Map<String, Object>> selectTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, List<String> TASK_TYPE_LIST, List<String> ASSIGNEE_LIST, List<String> EXECUTOR_LIST, Date FROM_CLAIM_DATE_, Date TO_CLAIM_DATE_, Date FROM_DUE_DATE_, Date TO_DUE_DATE_, Date FROM_COMPLETE_DATE_, Date TO_COMPLETE_DATE_, Integer PRIORITY_, List<String> FORWARD_STATUS_LIST, List<String> TASK_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PARENT_NODE_ID_, String PROC_ID_, List<String> NODE_TYPE_LIST, String NODE_CODE_, String NODE_NAME_, List<String> NODE_END_USER_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, List<String> NODE_STATUS_LIST, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, List<String> PROC_START_USER_LIST,
            Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_, Integer page, Integer limit) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaTask(false, TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_LIST, ASSIGNEE_LIST, EXECUTOR_LIST, FROM_CLAIM_DATE_, TO_CLAIM_DATE_, FROM_DUE_DATE_, TO_DUE_DATE_, FROM_COMPLETE_DATE_, TO_COMPLETE_DATE_, PRIORITY_, FORWARD_STATUS_LIST, TASK_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PARENT_NODE_ID_, PROC_ID_, NODE_TYPE_LIST, NODE_CODE_, NODE_NAME_, NODE_END_USER_LIST, FROM_NODE_END_DATE_, TO_NODE_END_DATE_, NODE_STATUS_LIST, BIZ_ID_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_LIST, FROM_PROC_START_DATE_, TO_PROC_START_DATE_, PROC_END_USER_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_LIST, PROC_DEF_CODE_LIST, PROC_DEF_CAT_);// 根据查询条件组装查询SQL语句
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
    public int countTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, List<String> TASK_TYPE_LIST, List<String> ASSIGNEE_LIST, List<String> EXECUTOR_LIST, Date FROM_CLAIM_DATE_, Date TO_CLAIM_DATE_, Date FROM_DUE_DATE_, Date TO_DUE_DATE_, Date FROM_COMPLETE_DATE_, Date TO_COMPLETE_DATE_, Integer PRIORITY_, List<String> FORWARD_STATUS_LIST, List<String> TASK_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PARENT_NODE_ID_, String PROC_ID_, List<String> NODE_TYPE_LIST, String NODE_CODE_, String NODE_NAME_, List<String> NODE_END_USER_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, List<String> NODE_STATUS_LIST, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, List<String> PROC_START_USER_LIST,
            Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaTask(true, TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_LIST, ASSIGNEE_LIST, EXECUTOR_LIST, FROM_CLAIM_DATE_, TO_CLAIM_DATE_, FROM_DUE_DATE_, TO_DUE_DATE_, FROM_COMPLETE_DATE_, TO_COMPLETE_DATE_, PRIORITY_, FORWARD_STATUS_LIST, TASK_STATUS_LIST, FROM_CREATION_DATE_, TO_CREATION_DATE_, PARENT_NODE_ID_, PROC_ID_, NODE_TYPE_LIST, NODE_CODE_, NODE_NAME_, NODE_END_USER_LIST, FROM_NODE_END_DATE_, TO_NODE_END_DATE_, NODE_STATUS_LIST, BIZ_ID_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_LIST, FROM_PROC_START_DATE_, TO_PROC_START_DATE_, PROC_END_USER_LIST, FROM_PROC_END_DATE_, TO_PROC_END_DATE_, PROC_STATUS_LIST, PROC_DEF_CODE_LIST, PROC_DEF_CAT_);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaTask(boolean count, String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, List<String> TASK_TYPE_LIST, List<String> ASSIGNEE_LIST, List<String> EXECUTOR_LIST, Date FROM_CLAIM_DATE_, Date TO_CLAIM_DATE_, Date FROM_DUE_DATE_, Date TO_DUE_DATE_, Date FROM_COMPLETE_DATE_, Date TO_COMPLETE_DATE_, Integer PRIORITY_, List<String> FORWARD_STATUS_LIST, List<String> TASK_STATUS_LIST, Date FROM_CREATION_DATE_, Date TO_CREATION_DATE_, String PARENT_NODE_ID_, String PROC_ID_, List<String> NODE_TYPE_LIST, String NODE_CODE_, String NODE_NAME_, List<String> NODE_END_USER_LIST, Date FROM_NODE_END_DATE_, Date TO_NODE_END_DATE_, List<String> NODE_STATUS_LIST, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_,
            List<String> PROC_START_USER_LIST, Date FROM_PROC_START_DATE_, Date TO_PROC_START_DATE_, List<String> PROC_END_USER_LIST, Date FROM_PROC_END_DATE_, Date TO_PROC_END_DATE_, List<String> PROC_STATUS_LIST, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_CAT_) {// 组装查询SQL语句
        String sql;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (count) {
            sql = "select count(*) from FFV_TASK where 1 = 1";
        }
        else {
            sql = "select * from FFV_TASK where 1 = 1";
        }

        if (StringUtils.isNotEmpty(TASK_ID_)) {
            sql += " and TASK_ID_ = :TASK_ID_";
            paramMap.put("TASK_ID_", TASK_ID_);
        }
        if (StringUtils.isNotEmpty(NODE_ID_)) {
            sql += " and NODE_ID_ = :NODE_ID_";
            paramMap.put("NODE_ID_", NODE_ID_);
        }
        if (StringUtils.isNotEmpty(PREVIOUS_TASK_ID_)) {
            sql += " and PREVIOUS_TASK_ID_ = :PREVIOUS_TASK_ID_";
            paramMap.put("PREVIOUS_TASK_ID_", PREVIOUS_TASK_ID_);
        }
        if (TASK_TYPE_LIST != null && TASK_TYPE_LIST.size() > 0) {
            sql += " and TASK_TYPE_ in (:TASK_TYPE_LIST)";
            paramMap.put("TASK_TYPE_LIST", TASK_TYPE_LIST);
        }
        if (ASSIGNEE_LIST != null && ASSIGNEE_LIST.size() > 0) {
            sql += " and ASSIGNEE_ in (:ASSIGNEE_LIST)";
            paramMap.put("ASSIGNEE_LIST", ASSIGNEE_LIST);
        }
        if (EXECUTOR_LIST != null && EXECUTOR_LIST.size() > 0) {
            sql += " and EXECUTOR_ in (:EXECUTOR_LIST)";
            paramMap.put("EXECUTOR_LIST", EXECUTOR_LIST);
        }
        if (FROM_CLAIM_DATE_ != null) {
            sql += " and CLAIM_DATE_ >= :FROM_CLAIM_DATE_";
            paramMap.put("FROM_CLAIM_DATE_", FROM_CLAIM_DATE_);
        }
        if (TO_CLAIM_DATE_ != null) {
            sql += " and CLAIM_DATE_ <= :TO_CLAIM_DATE_";
            paramMap.put("TO_CLAIM_DATE_", TO_CLAIM_DATE_);
        }
        if (FROM_DUE_DATE_ != null) {
            sql += " and DUE_DATE_ >= :FROM_DUE_DATE_";
            paramMap.put("FROM_DUE_DATE_", FROM_DUE_DATE_);
        }
        if (TO_DUE_DATE_ != null) {
            sql += " and DUE_DATE_ <= :TO_DUE_DATE_";
            paramMap.put("TO_DUE_DATE_", TO_DUE_DATE_);
        }
        if (FROM_COMPLETE_DATE_ != null) {
            sql += " and COMPLETE_DATE_ >= :FROM_COMPLETE_DATE_";
            paramMap.put("FROM_COMPLETE_DATE_", FROM_COMPLETE_DATE_);
        }
        if (TO_COMPLETE_DATE_ != null) {
            sql += " and COMPLETE_DATE_ <= :TO_COMPLETE_DATE_";
            paramMap.put("TO_COMPLETE_DATE_", TO_COMPLETE_DATE_);
        }
        if (PRIORITY_ != null) {
            sql += " and PRIORITY_ = :PRIORITY_";
            paramMap.put("PRIORITY_", PRIORITY_);
        }
        if (FORWARD_STATUS_LIST != null && FORWARD_STATUS_LIST.size() > 0) {
            sql += " and FORWARD_STATUS_ in (:FORWARD_STATUS_LIST)";
            paramMap.put("FORWARD_STATUS_LIST", FORWARD_STATUS_LIST);
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
        if (StringUtils.isNotEmpty(PARENT_NODE_ID_)) {
            sql += " and PARENT_NODE_ID_ = :PARENT_NODE_ID_";
            paramMap.put("PARENT_NODE_ID_", PARENT_NODE_ID_);
        }
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
        if (StringUtils.isNotEmpty(NODE_NAME_)) {
            sql += " and NODE_NAME_ like '%' || :NODE_NAME_ || '%'";
            paramMap.put("NODE_NAME_", NODE_NAME_);
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
        if (NODE_STATUS_LIST != null && NODE_STATUS_LIST.size() > 0) {
            sql += " and NODE_STATUS_ in (:NODE_STATUS_LIST)";
            paramMap.put("NODE_STATUS_LIST", NODE_STATUS_LIST);
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
    public List<Map<String, Object>> selectTaskByIdList(List<String> TASK_ID_LIST) {
        if (TASK_ID_LIST == null || TASK_ID_LIST.size() == 0) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder(TASK_ID_LIST.size() * 50 + 200);
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append("select * from FFV_TASK where TASK_ID_ in (:TASK_ID_LIST)");
        paramMap.put("TASK_ID_LIST", TASK_ID_LIST);
        sql.append(" order by DECODE(TASK_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < TASK_ID_LIST.size(); i++) {
            sql.append(" '").append(TASK_ID_LIST.get(i)).append("', ").append(i);
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
    public int insertTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, String TASK_TYPE_, String ASSIGNEE_, String ASSIGNEE_NAME_, String EXECUTOR_, String EXECUTOR_NAME_, String ACTION_, Date CLAIM_DATE_, Date DUE_DATE_, Date COMPLETE_DATE_, Integer PRIORITY_, String FORWARDABLE_, String FORWARD_STATUS_, String TASK_STATUS_, Date CREATION_DATE_) {
        String sql = "insert into FF_TASK (TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, EXECUTOR_, EXECUTOR_NAME_, ACTION_, CLAIM_DATE_, DUE_DATE_, COMPLETE_DATE_, PRIORITY_, FORWARDABLE_, FORWARD_STATUS_, TASK_STATUS_, CREATION_DATE_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int count = ffJdbcTemplate.update(sql, TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, EXECUTOR_, EXECUTOR_NAME_, ACTION_, CLAIM_DATE_, DUE_DATE_, COMPLETE_DATE_, PRIORITY_, FORWARDABLE_, FORWARD_STATUS_, TASK_STATUS_, CREATION_DATE_);

        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_INSERT);

        return count;
    }

    @Override
    public int updateTask(String TASK_ID_, String NODE_ID_, String PREVIOUS_TASK_ID_, String TASK_TYPE_, String ASSIGNEE_, String ASSIGNEE_NAME_, String EXECUTOR_, String EXECUTOR_NAME_, String ACTION_, Date CLAIM_DATE_, Date DUE_DATE_, Date COMPLETE_DATE_, Integer PRIORITY_, String FORWARDABLE_, String FORWARD_STATUS_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set NODE_ID_ = ?, PREVIOUS_TASK_ID_ = ?, TASK_TYPE_ = ?, ASSIGNEE_ = ?, ASSIGNEE_NAME_ = ?, EXECUTOR_ = ?, EXECUTOR_NAME_ = ?, ACTION_ = ?, CLAIM_DATE_ = ?, DUE_DATE_ = ?, COMPLETE_DATE_ = ?, PRIORITY_ = ?, FORWARDABLE_ = ?, FORWARD_STATUS_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, EXECUTOR_, EXECUTOR_NAME_, ACTION_, CLAIM_DATE_, DUE_DATE_, COMPLETE_DATE_, PRIORITY_, FORWARDABLE_, FORWARD_STATUS_, TASK_ID_);
    }

    @Override
    public int updateTaskAssignee(String TASK_ID_, String ASSIGNEE_, String ASSIGNEE_NAME_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set ASSIGNEE_ = ?, ASSIGNEE_NAME_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, ASSIGNEE_, ASSIGNEE_NAME_, TASK_ID_);
    }

    public int updateTaskClaimDate(String TASK_ID_, Date CLAIM_DATE_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set CLAIM_DATE_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, CLAIM_DATE_, TASK_ID_);
    }

    @Override
    public int updateTaskForwardStatus(String TASK_ID_, String FORWARD_STATUS_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set FORWARD_STATUS_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, FORWARD_STATUS_, TASK_ID_);
    }

    @Override
    public int updateTaskStatus(String TASK_ID_, String TASK_STATUS_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set TASK_STATUS_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, TASK_STATUS_, TASK_ID_);
    }

    @Override
    public int updateTaskStatus(String TASK_ID_, String EXECUTOR_, String EXECUTOR_NAME_, Date COMPLETE_DATE_, String TASK_STATUS_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_UPDATE);

        String sql = "update FF_TASK set EXECUTOR_ = ?, EXECUTOR_NAME_ = ?, COMPLETE_DATE_ = ?, TASK_STATUS_ = ? where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, EXECUTOR_, EXECUTOR_NAME_, COMPLETE_DATE_, TASK_STATUS_, TASK_ID_);
    }

    @Override
    public int deleteTask(String TASK_ID_) {
        ffOperationService.insertTaskOp(OdUtils.getUuid(), TASK_ID_, FfOperationService.OPERATION_TYPE_DELETE);

        String sql = "delete from FF_TASK where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, TASK_ID_);
    }

    @Override
    public int deleteTaskByNodeId(String NODE_ID_) {
        List<Map<String, Object>> taskList = selectTask(null, NODE_ID_, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, -1);
        for (Map<String, Object> task : taskList) {
            deleteTask((String) task.get("TASK_ID_"));
        }

        return taskList.size();
    }
}