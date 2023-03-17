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
import com.opendynamic.ff.service.FfOperationService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.Proc;
import com.opendynamic.ff.vo.Task;
import com.opendynamic.ff.vo.ThreadOperation;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfOperationServiceImpl implements FfOperationService {
    private static ThreadLocal<ThreadOperation> threadLocal = new ThreadLocal<>();

    @Autowired
    private FfService ffService;
    @Autowired
    private JdbcTemplate ffJdbcTemplate;

    @Override
    public Map<String, Object> loadOperation(String OPERATION_ID_) {
        String sql = "select * from FFV_OPERATION where OPERATION_ID_ = ?";
        List<Map<String, Object>> result = ffJdbcTemplate.queryForList(sql, OPERATION_ID_);
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectOperation(String OPERATION_ID_, String OPERATION_, String PROC_ID_, String NODE_ID_, String TASK_ID_, List<String> OPERATOR_LIST, String OPERATOR_NAME_, Date FROM_OPERATION_DATE_, Date TO_OPERATION_DATE_, List<String> OPERATION_STATUS_LIST, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, Integer page, Integer limit) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaOperation(false, OPERATION_ID_, OPERATION_, PROC_ID_, NODE_ID_, TASK_ID_, OPERATOR_LIST, OPERATOR_NAME_, FROM_OPERATION_DATE_, TO_OPERATION_DATE_, OPERATION_STATUS_LIST, BIZ_ID_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_NAME_);// 根据查询条件组装查询SQL语句
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
    public int countOperation(String OPERATION_ID_, String OPERATION_, String PROC_ID_, String NODE_ID_, String TASK_ID_, List<String> OPERATOR_LIST, String OPERATOR_NAME_, Date FROM_OPERATION_DATE_, Date TO_OPERATION_DATE_, List<String> OPERATION_STATUS_LIST, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_) {
        OdSqlCriteria odSqlCriteria = buildSqlCriteriaOperation(true, OPERATION_ID_, OPERATION_, PROC_ID_, NODE_ID_, TASK_ID_, OPERATOR_LIST, OPERATOR_NAME_, FROM_OPERATION_DATE_, TO_OPERATION_DATE_, OPERATION_STATUS_LIST, BIZ_ID_, BIZ_TYPE_LIST, BIZ_CODE_, BIZ_NAME_);// 根据查询条件组装总数查询SQL语句
        String sql = odSqlCriteria.getSql();
        Map<String, Object> paramMap = odSqlCriteria.getParamMap();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    private OdSqlCriteria buildSqlCriteriaOperation(boolean count, String OPERATION_ID_, String OPERATION_, String PROC_ID_, String NODE_ID_, String TASK_ID_, List<String> OPERATOR_LIST, String OPERATOR_NAME_, Date FROM_OPERATION_DATE_, Date TO_OPERATION_DATE_, List<String> OPERATION_STATUS_LIST, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_) {// 组装查询SQL语句
        String sql;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (count) {
            sql = "select count(*) from FFV_OPERATION where 1 = 1";
        }
        else {
            sql = "select * from FFV_OPERATION where 1 = 1";
        }

        if (StringUtils.isNotEmpty(OPERATION_ID_)) {
            sql += " and OPERATION_ID_ = :OPERATION_ID_";
            paramMap.put("OPERATION_ID_", OPERATION_ID_);
        }
        if (StringUtils.isNotEmpty(OPERATION_)) {
            sql += " and OPERATION_ = :OPERATION_";
            paramMap.put("OPERATION_", OPERATION_);
        }
        if (StringUtils.isNotEmpty(PROC_ID_)) {
            sql += " and PROC_ID_ = :PROC_ID_";
            paramMap.put("PROC_ID_", PROC_ID_);
        }
        if (StringUtils.isNotEmpty(NODE_ID_)) {
            sql += " and NODE_ID_ = :NODE_ID_";
            paramMap.put("NODE_ID_", NODE_ID_);
        }
        if (StringUtils.isNotEmpty(TASK_ID_)) {
            sql += " and TASK_ID_ = :TASK_ID_";
            paramMap.put("TASK_ID_", TASK_ID_);
        }
        if (OPERATOR_LIST != null && OPERATOR_LIST.size() > 0) {
            sql += " and OPERATOR_ in (:OPERATOR_LIST)";
            paramMap.put("OPERATOR_LIST", OPERATOR_LIST);
        }
        if (StringUtils.isNotEmpty(OPERATOR_NAME_)) {
            sql += " and OPERATOR_NAME_ like '%' || :OPERATOR_NAME_ || '%'";
            paramMap.put("OPERATOR_NAME_", OPERATOR_NAME_);
        }
        if (FROM_OPERATION_DATE_ != null) {
            sql += " and OPERATION_DATE_ >= :FROM_OPERATION_DATE_";
            paramMap.put("FROM_OPERATION_DATE_", FROM_OPERATION_DATE_);
        }
        if (TO_OPERATION_DATE_ != null) {
            sql += " and OPERATION_DATE_ <= :TO_OPERATION_DATE_";
            paramMap.put("TO_OPERATION_DATE_", TO_OPERATION_DATE_);
        }
        if (OPERATION_STATUS_LIST != null && OPERATION_STATUS_LIST.size() > 0) {
            sql += " and OPERATION_STATUS_ in (:OPERATION_STATUS_LIST)";
            paramMap.put("OPERATION_STATUS_LIST", OPERATION_STATUS_LIST);
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

        if (!count) {
            sql += " order by OPERATION_DATE_ desc";
        }

        return new OdSqlCriteria(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectOperationByIdList(List<String> OPERATION_ID_LIST) {
        if (OPERATION_ID_LIST == null || OPERATION_ID_LIST.size() == 0) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder(OPERATION_ID_LIST.size() * 50 + 200);
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append("select * from FFV_OPERATION where OPERATION_ID_ in (:OPERATION_ID_LIST)");
        paramMap.put("OPERATION_ID_LIST", OPERATION_ID_LIST);
        sql.append(" order by DECODE(OPERATION_ID_,");// 按主键列表顺序排序
        for (int i = 0; i < OPERATION_ID_LIST.size(); i++) {
            sql.append(" '").append(OPERATION_ID_LIST.get(i)).append("', ").append(i);
            if (i < OPERATION_ID_LIST.size() - 1) {
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
    public int insertOperation(String OPERATION_ID_, String OPERATION_, String PROC_ID_, String NODE_ID_, String TASK_ID_, String MEMO_, String OPERATOR_, String OPERATOR_NAME_, Date OPERATION_DATE_, String OPERATION_STATUS_) {
        String sql = "insert into FF_OPERATION (OPERATION_ID_, OPERATION_, PROC_ID_, NODE_ID_, TASK_ID_, MEMO_, OPERATOR_, OPERATOR_NAME_, OPERATION_DATE_, OPERATION_STATUS_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return ffJdbcTemplate.update(sql, OPERATION_ID_, OPERATION_, PROC_ID_, NODE_ID_, TASK_ID_, MEMO_, OPERATOR_, OPERATOR_NAME_, OPERATION_DATE_, OPERATION_STATUS_);
    }

    @Override
    public int updateOperation(String OPERATION_ID_, String OPERATION_, String PROC_ID_, String NODE_ID_, String TASK_ID_, String MEMO_, String OPERATOR_, String OPERATOR_NAME_, Date OPERATION_DATE_) {
        String sql = "update FF_OPERATION set OPERATION_ = ?, PROC_ID_ = ?, NODE_ID_ = ?, TASK_ID_ = ?, MEMO_ = ?, OPERATOR_ = ?, OPERATOR_NAME_ = ?, OPERATION_DATE_ = ? where OPERATION_ID_ = ?";
        return ffJdbcTemplate.update(sql, OPERATION_, PROC_ID_, NODE_ID_, TASK_ID_, MEMO_, OPERATOR_, OPERATOR_NAME_, OPERATION_DATE_, OPERATION_ID_);
    }

    @Override
    public int updateOperationStatus(String OPERATION_ID_, String OPERATION_STATUS_) {
        String sql = "update FF_OPERATION set OPERATION_STATUS_ = ? where OPERATION_ID_ = ?";
        return ffJdbcTemplate.update(sql, OPERATION_STATUS_, OPERATION_ID_);
    }

    @Override
    public int deleteOperation(String OPERATION_ID_) {
        String sql = "delete from FF_OPERATION where OPERATION_ID_ = ?";
        return ffJdbcTemplate.update(sql, OPERATION_ID_);
    }

    @Override
    public int insertProcOp(String PROC_OP_ID_, String PROC_ID_, String OPERATION_TYPE_) {
        ThreadOperation threadOperation = getCurrentThreadOperation();
        String sql = "insert into FF_PROC_OP (PROC_OP_ID_, OPERATION_ID_, OPERATION_TYPE_, OPERATION_ORDER_, OPERATION_DATE_, OPERATION_STATUS_, PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_START_DATE_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, CREATION_DATE_) select ?, ?, ?, ?, ?, ?, PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_START_DATE_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, CREATION_DATE_ from FF_PROC where PROC_ID_ = ?";
        return ffJdbcTemplate.update(sql, PROC_OP_ID_, threadOperation.getOperationId(), OPERATION_TYPE_, threadOperation.getNextOrder(), new Date(), FfOperationService.OPERATION_STATUS_UNDOABLE, PROC_ID_);
    }

    @Override
    public int insertNodeOp(String NODE_OP_ID_, String NODE_ID_, String OPERATION_TYPE_) {
        ThreadOperation threadOperation = getCurrentThreadOperation();
        String sql = "insert into FF_NODE_OP (NODE_OP_ID_, OPERATION_ID_, OPERATION_TYPE_, OPERATION_ORDER_, OPERATION_DATE_, OPERATION_STATUS_, NODE_ID_, PARENT_NODE_ID_, PROC_ID_, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, ASSIGNEE_, CANDIDATE_, ACTION_, DUE_DATE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, FORWARDABLE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, NODE_STATUS_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_STATUS_, CREATION_DATE_) select ?, ?, ?, ?, ?, ?, NODE_ID_, PARENT_NODE_ID_, PROC_ID_, PREVIOUS_NODE_IDS_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, ASSIGNEE_, CANDIDATE_, ACTION_, DUE_DATE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, FORWARDABLE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, NODE_STATUS_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_STATUS_, CREATION_DATE_ from FF_NODE where NODE_ID_ = ?";
        return ffJdbcTemplate.update(sql, NODE_OP_ID_, threadOperation.getOperationId(), OPERATION_TYPE_, threadOperation.getNextOrder(), new Date(), FfOperationService.OPERATION_STATUS_UNDOABLE, NODE_ID_);
    }

    @Override
    public int insertTaskOp(String TASK_OP_ID_, String TASK_ID_, String OPERATION_TYPE_) {
        ThreadOperation threadOperation = getCurrentThreadOperation();
        String sql = "insert into FF_TASK_OP (TASK_OP_ID_, OPERATION_ID_, OPERATION_TYPE_, OPERATION_ORDER_, OPERATION_DATE_, OPERATION_STATUS_, TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, EXECUTOR_, EXECUTOR_NAME_, ACTION_, CLAIM_DATE_, DUE_DATE_, COMPLETE_DATE_, PRIORITY_, FORWARDABLE_, FORWARD_STATUS_, TASK_STATUS_, CREATION_DATE_) select ?, ?, ?, ?, ?, ?, TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, EXECUTOR_, EXECUTOR_NAME_, ACTION_, CLAIM_DATE_, DUE_DATE_, COMPLETE_DATE_, PRIORITY_, FORWARDABLE_, FORWARD_STATUS_, TASK_STATUS_, CREATION_DATE_ from FF_TASK where TASK_ID_ = ?";
        return ffJdbcTemplate.update(sql, TASK_OP_ID_, threadOperation.getOperationId(), OPERATION_TYPE_, threadOperation.getNextOrder(), new Date(), FfOperationService.OPERATION_STATUS_UNDOABLE, TASK_ID_);
    }

    @Override
    public int insertNodeVarOp(String NODE_VAR_OP_ID_, String NODE_VAR_ID_, String OPERATION_TYPE_) {
        ThreadOperation threadOperation = getCurrentThreadOperation();
        String sql = "insert into FF_NODE_VAR_OP (NODE_VAR_OP_ID_, OPERATION_ID_, OPERATION_TYPE_, OPERATION_ORDER_, OPERATION_DATE_, OPERATION_STATUS_, NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, VAR_NAME_, VALUE_, OBJ_, CREATION_DATE_) select ?, ?, ?, ?, ?, ?, NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, VAR_NAME_, VALUE_, OBJ_, CREATION_DATE_ from FF_NODE_VAR where NODE_VAR_ID_ = ?";
        return ffJdbcTemplate.update(sql, NODE_VAR_OP_ID_, threadOperation.getOperationId(), OPERATION_TYPE_, threadOperation.getNextOrder(), new Date(), FfOperationService.OPERATION_STATUS_UNDOABLE, NODE_VAR_ID_);
    }

    @Override
    public void init(String operationType, String procId, String nodeId, String taskId, String memo, String operator, String operatorName) {
        String operationId = OdUtils.getUuid();
        insertOperation(operationId, operationType, procId, nodeId, taskId, memo, operator, operatorName, new Date(), FfOperationService.OPERATION_STATUS_UNDOABLE);
        threadLocal.set(new ThreadOperation(operationId));
    }

    @Override
    public void finalize() {
        threadLocal.set(null);
    }

    @Override
    public ThreadOperation getCurrentThreadOperation() {
        return threadLocal.get();
    }

    @Override
    public FfResult undo(String operationId) {
        FfResult ffResult = new FfResult();

        // 操作状态为不能取消和已经取消时，抛异常。
        Map<String, Object> operation = loadOperation(operationId);
        if (operation.get("OPERATION_STATUS_").equals(FfOperationService.OPERATION_STATUS_NOT_UNDOABLE)) {
            throw new RuntimeException("errors.cannotUndo");
        }
        if (operation.get("OPERATION_STATUS_").equals(FfOperationService.OPERATION_STATUS_UNDOED)) {
            throw new RuntimeException("errors.alreadyUndoed");
        }

        // 获取所有子表操作。
        String sql;
        sql = "select * from FF_PROC_OP where OPERATION_ID_ = ? order by OPERATION_ORDER_";
        List<Map<String, Object>> procOpList = ffJdbcTemplate.queryForList(sql, operationId);
        sql = "select * from FF_NODE_OP where OPERATION_ID_ = ? order by OPERATION_ORDER_";
        List<Map<String, Object>> nodeOpList = ffJdbcTemplate.queryForList(sql, operationId);
        sql = "select * from FF_TASK_OP where OPERATION_ID_ = ? order by OPERATION_ORDER_";
        List<Map<String, Object>> taskOpList = ffJdbcTemplate.queryForList(sql, operationId);
        sql = "select * from FF_NODE_VAR_OP where OPERATION_ID_ = ? order by OPERATION_ORDER_";
        List<Map<String, Object>> nodeVarOpList = ffJdbcTemplate.queryForList(sql, operationId);

        // 将操作按顺序排列。
        Map<Integer, Map<String, Object>> allOpMap = new HashMap<>();
        for (Map<String, Object> op : procOpList) {
            allOpMap.put(((BigDecimal) op.get("OPERATION_ORDER_")).intValue(), op);
        }
        for (Map<String, Object> op : nodeOpList) {
            allOpMap.put(((BigDecimal) op.get("OPERATION_ORDER_")).intValue(), op);
        }
        for (Map<String, Object> op : taskOpList) {
            allOpMap.put(((BigDecimal) op.get("OPERATION_ORDER_")).intValue(), op);
        }
        for (Map<String, Object> op : nodeVarOpList) {
            allOpMap.put(((BigDecimal) op.get("OPERATION_ORDER_")).intValue(), op);
        }

        // 按顺序依次取消操作。
        Map<String, Object> op;
        for (int i = allOpMap.size() - 1; i >= 0; i--) {
            op = allOpMap.get(i);
            if (op.containsKey("PROC_OP_ID_")) {
                ffResult.addAll(undoProcOp(op));
            }
            if (op.containsKey("NODE_OP_ID_")) {
                ffResult.addAll(undoNodeOp(op));
            }
            if (op.containsKey("TASK_OP_ID_")) {
                ffResult.addAll(undoTaskOp(op));
            }
            if (op.containsKey("NODE_VAR_OP_ID_")) {
                undoNodeVarOp(op);
            }
        }

        // 设置操作状态为已经取消。
        sql = "update FF_OPERATION set OPERATION_STATUS_ = 9 where OPERATION_ID_ = ?";
        ffJdbcTemplate.update(sql, operationId);

        // 更新操作后续
        List<Map<String, Object>> operationFollowUpList = ffJdbcTemplate.queryForList("select * from FF_OPERATION_FOLLOW_UP where FOLLOW_UP_OPERATION_ID_ = ?", operationId);
        for (Map<String, Object> operationFollowUp : operationFollowUpList) {
            ffJdbcTemplate.update("delete from FF_OPERATION_FOLLOW_UP where OPERATION_FOLLOW_UP_ID_ = ?", operationFollowUp.get("OPERATION_FOLLOW_UP_ID_"));
            if (ffJdbcTemplate.queryForObject("select count(*) from FF_OPERATION_FOLLOW_UP where OPERATION_ID_ = ?", new Object[] { operationFollowUp.get("OPERATION_ID_") }, Integer.class) == 0) {
                ffJdbcTemplate.update("update FF_OPERATION set OPERATION_STATUS_ = '1' where OPERATION_ID_ = ?", operationFollowUp.get("OPERATION_ID_"));
                ffJdbcTemplate.update("update FF_PROC_OP set OPERATION_STATUS_ = '1' where OPERATION_ID_ = ?", operationFollowUp.get("OPERATION_ID_"));
                ffJdbcTemplate.update("update FF_NODE_OP set OPERATION_STATUS_ = '1' where OPERATION_ID_ = ?", operationFollowUp.get("OPERATION_ID_"));
                ffJdbcTemplate.update("update FF_TASK_OP set OPERATION_STATUS_ = '1' where OPERATION_ID_ = ?", operationFollowUp.get("OPERATION_ID_"));
                ffJdbcTemplate.update("update FF_NODE_VAR_OP set OPERATION_STATUS_ = '1' where OPERATION_ID_ = ?", operationFollowUp.get("OPERATION_ID_"));
            }
        }

        return ffResult;
    }

    // 取消流程操作。
    private FfResult undoProcOp(Map<String, Object> procOp) {
        FfResult ffResult = new FfResult();

        String OPERATION_TYPE_ = (String) procOp.get("OPERATION_TYPE_");
        String sql;
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_INSERT)) {
            ffResult.addDeleteProc(ffService.loadProc((String) procOp.get("PROC_ID_")));
            sql = "delete from FF_PROC where PROC_ID_ = ?";
            ffJdbcTemplate.update(sql, procOp.get("PROC_ID_"));
        }
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_UPDATE)) {
            sql = "select * from FF_PROC where PROC_ID_ = ?";
            List<Map<String, Object>> procList = ffJdbcTemplate.queryForList(sql, procOp.get("PROC_ID_"));
            String CURRENT_PROC_STATUS_ = (String) procList.get(0).get("PROC_STATUS_");
            String UNDO_PROC_STATUS_ = (String) procOp.get("PROC_STATUS_");

            sql = "update FF_PROC set (PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_START_DATE_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, CREATION_DATE_) = (select PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_START_DATE_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, CREATION_DATE_ from FF_PROC_OP where PROC_OP_ID_ = ?) where PROC_ID_ = ?";
            ffJdbcTemplate.update(sql, procOp.get("PROC_OP_ID_"), procOp.get("PROC_ID_"));

            Proc proc = ffService.loadProc((String) procOp.get("PROC_ID_"));
            if (!CURRENT_PROC_STATUS_.equals(UNDO_PROC_STATUS_)) {
                if (UNDO_PROC_STATUS_.equals(FfService.NODE_STATUS_ACTIVE)) {
                    ffResult.addActivateProc(proc);
                }
                if (UNDO_PROC_STATUS_.equals(FfService.NODE_STATUS_SUSPEND)) {
                    ffResult.addSuspendProc(proc);
                }
                if (UNDO_PROC_STATUS_.equals(FfService.NODE_STATUS_COMPLETE)) {
                    ffResult.addCompleteProc(proc);
                }
                if (UNDO_PROC_STATUS_.equals(FfService.NODE_STATUS_TERMINATE)) {
                    ffResult.addTerminateProc(proc);
                }
            }
        }
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_DELETE)) {
            sql = "insert into FF_PROC(PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_START_DATE_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, CREATION_DATE_) select PROC_ID_, PROC_DEF_ID_, ADJUST_PROC_DEF_ID_, ISOLATE_SUB_PROC_NODE_ID_, BIZ_ID_, BIZ_TYPE_, BIZ_CODE_, BIZ_NAME_, PROC_START_USER_, PROC_START_USER_NAME_, PROC_START_DATE_, PROC_END_USER_, PROC_END_USER_NAME_, PROC_END_DATE_, PROC_STATUS_, CREATION_DATE_ from FF_PROC_OP where PROC_OP_ID_ = ?";
            ffJdbcTemplate.update(sql, procOp.get("PROC_OP_ID_"));
            ffResult.addCreateProc(ffService.loadProc((String) procOp.get("PROC_ID_")));
        }

        // 修改状态为已取消
        sql = "update FF_PROC_OP set OPERATION_STATUS_ = '9' where PROC_OP_ID_ = ?";
        ffJdbcTemplate.update(sql, procOp.get("PROC_OP_ID_"));

        return ffResult;
    }

    // 取消节点操作。
    private FfResult undoNodeOp(Map<String, Object> nodeOp) {
        FfResult ffResult = new FfResult();

        String OPERATION_TYPE_ = (String) nodeOp.get("OPERATION_TYPE_");
        String sql;
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_INSERT)) {
            ffResult.addDeleteNode(ffService.loadNode((String) nodeOp.get("NODE_ID_")));
            sql = "delete from FF_NODE where NODE_ID_ = ?";
            ffJdbcTemplate.update(sql, nodeOp.get("NODE_ID_"));
        }
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_UPDATE)) {
            sql = "select * from FF_NODE where NODE_ID_ = ?";
            List<Map<String, Object>> nodeList = ffJdbcTemplate.queryForList(sql, nodeOp.get("NODE_ID_"));
            String CURRENT_NODE_STATUS_ = (String) nodeList.get(0).get("NODE_STATUS_");
            String UNDO_NODE_STATUS_ = (String) nodeOp.get("NODE_STATUS_");

            sql = "update FF_NODE set (NODE_ID_, PARENT_NODE_ID_, PROC_ID_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, ASSIGNEE_, CANDIDATE_, ACTION_, DUE_DATE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, FORWARDABLE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_STATUS_, NODE_STATUS_, CREATION_DATE_) = (select NODE_ID_, PARENT_NODE_ID_, PROC_ID_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, ASSIGNEE_, CANDIDATE_, ACTION_, DUE_DATE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, FORWARDABLE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_STATUS_, NODE_STATUS_, CREATION_DATE_ from FF_NODE_OP where NODE_OP_ID_ = ?) where NODE_ID_ = ?";
            ffJdbcTemplate.update(sql, nodeOp.get("NODE_OP_ID_"), nodeOp.get("NODE_ID_"));

            Node node = ffService.loadNode((String) nodeOp.get("NODE_ID_"));
            if (!CURRENT_NODE_STATUS_.equals(UNDO_NODE_STATUS_)) {
                if (UNDO_NODE_STATUS_.equals(FfService.NODE_STATUS_ACTIVE)) {
                    ffResult.addActivateNode(node);
                }
                if (UNDO_NODE_STATUS_.equals(FfService.NODE_STATUS_SUSPEND)) {
                    ffResult.addSuspendNode(node);
                }
                if (UNDO_NODE_STATUS_.equals(FfService.NODE_STATUS_COMPLETE)) {
                    ffResult.addCompleteNode(node);
                }
                if (UNDO_NODE_STATUS_.equals(FfService.NODE_STATUS_TERMINATE)) {
                    ffResult.addTerminateNode(node);
                }
            }
        }
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_DELETE)) {
            sql = "insert into FF_NODE (NODE_ID_, PARENT_NODE_ID_, PROC_ID_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, ASSIGNEE_, CANDIDATE_, ACTION_, DUE_DATE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, FORWARDABLE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_STATUS_, NODE_STATUS_, CREATION_DATE_) select NODE_ID_, PARENT_NODE_ID_, PROC_ID_, LAST_COMPLETE_NODE_IDS_, SUB_PROC_DEF_ID_, ADJUST_SUB_PROC_DEF_ID_, NODE_TYPE_, NODE_CODE_, NODE_NAME_, PARENT_NODE_CODE_, ASSIGNEE_, CANDIDATE_, ACTION_, DUE_DATE_, COMPLETE_EXPRESSION_, COMPLETE_RETURN_, EXCLUSIVE_, FORWARDABLE_, AUTO_COMPLETE_SAME_ASSIGNEE_, AUTO_COMPLETE_EMPTY_ASSIGNEE_, INFORM_, PRIORITY_, NODE_END_USER_, NODE_END_USER_NAME_, NODE_END_DATE_, ISOLATE_SUB_PROC_DEF_CODE_, ISOLATE_SUB_PROC_STATUS_, NODE_STATUS_, CREATION_DATE_ from FF_NODE_OP where NODE_OP_ID_ = ?";
            ffJdbcTemplate.update(sql, nodeOp.get("NODE_OP_ID_"));
            ffResult.addCreateNode(ffService.loadNode((String) nodeOp.get("NODE_ID_")));
        }

        // 修改状态为已取消
        sql = "update FF_NODE_OP set OPERATION_STATUS_ = '9' where NODE_OP_ID_ = ?";
        ffJdbcTemplate.update(sql, nodeOp.get("NODE_OP_ID_"));

        return ffResult;
    }

    // 取消任务操作。
    private FfResult undoTaskOp(Map<String, Object> taskOp) {
        FfResult ffResult = new FfResult();

        String OPERATION_TYPE_ = (String) taskOp.get("OPERATION_TYPE_");
        String sql;
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_INSERT)) {
            ffResult.addDeleteTask(ffService.loadTask((String) taskOp.get("TASK_ID_")));
            sql = "delete from FF_TASK where TASK_ID_ = ?";
            ffJdbcTemplate.update(sql, taskOp.get("TASK_ID_"));
        }
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_UPDATE)) {
            sql = "select * from FF_TASK where TASK_ID_ = ?";
            List<Map<String, Object>> taskList = ffJdbcTemplate.queryForList(sql, taskOp.get("TASK_ID_"));
            String CURRENT_TASK_STATUS_ = (String) taskList.get(0).get("TASK_STATUS_");
            String UNDO_TASK_STATUS_ = (String) taskOp.get("TASK_STATUS_");

            sql = "update FF_TASK set (TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, EXECUTOR_, EXECUTOR_NAME_, ACTION_, CLAIM_DATE_, DUE_DATE_, COMPLETE_DATE_, PRIORITY_, FORWARDABLE_, FORWARD_STATUS_, TASK_STATUS_, CREATION_DATE_) = (select TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, EXECUTOR_, EXECUTOR_NAME_, ACTION_, CLAIM_DATE_, DUE_DATE_, COMPLETE_DATE_, PRIORITY_, FORWARDABLE_, FORWARD_STATUS_, TASK_STATUS_, CREATION_DATE_ from FF_TASK_OP where TASK_OP_ID_ = ?) where TASK_ID_ = ?";
            ffJdbcTemplate.update(sql, taskOp.get("TASK_OP_ID_"), taskOp.get("TASK_ID_"));

            Task task = ffService.loadTask((String) taskOp.get("TASK_ID_"));
            if (!CURRENT_TASK_STATUS_.equals(UNDO_TASK_STATUS_)) {
                if (UNDO_TASK_STATUS_.equals(FfService.NODE_STATUS_ACTIVE)) {
                    ffResult.addActivateTask(task);
                }
                if (UNDO_TASK_STATUS_.equals(FfService.NODE_STATUS_SUSPEND)) {
                    ffResult.addSuspendTask(task);
                }
                if (UNDO_TASK_STATUS_.equals(FfService.NODE_STATUS_COMPLETE)) {
                    ffResult.addCompleteTask(task);
                }
                if (UNDO_TASK_STATUS_.equals(FfService.NODE_STATUS_TERMINATE)) {
                    ffResult.addTerminateTask(task);
                }
            }
        }
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_DELETE)) {
            sql = "insert into FF_TASK(TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, EXECUTOR_, EXECUTOR_NAME_, ACTION_, CLAIM_DATE_, DUE_DATE_, COMPLETE_DATE_, PRIORITY_, FORWARDABLE_, FORWARD_STATUS_, TASK_STATUS_, CREATION_DATE_) select TASK_ID_, NODE_ID_, PREVIOUS_TASK_ID_, TASK_TYPE_, ASSIGNEE_, ASSIGNEE_NAME_, EXECUTOR_, EXECUTOR_NAME_, ACTION_, CLAIM_DATE_, DUE_DATE_, COMPLETE_DATE_, PRIORITY_, FORWARDABLE_, FORWARD_STATUS_, TASK_STATUS_, CREATION_DATE_ from FF_TASK_OP where TASK_OP_ID_ = ?";
            ffJdbcTemplate.update(sql, taskOp.get("TASK_OP_ID_"));
            ffResult.addCreateTask(ffService.loadTask((String) taskOp.get("TASK_ID_")));
        }

        // 修改状态为已取消
        sql = "update FF_TASK_OP set OPERATION_STATUS_ = '9' where TASK_OP_ID_ = ?";
        ffJdbcTemplate.update(sql, taskOp.get("TASK_OP_ID_"));

        return ffResult;
    }

    // 取消节点变量操作。
    private void undoNodeVarOp(Map<String, Object> nodeVarOp) {
        String OPERATION_TYPE_ = (String) nodeVarOp.get("OPERATION_TYPE_");
        String sql;
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_INSERT)) {
            sql = "delete from FF_NODE_VAR where NODE_VAR_ID_ = ?";
            ffJdbcTemplate.update(sql, nodeVarOp.get("NODE_VAR_ID_"));
        }
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_UPDATE)) {
            sql = "update FF_NODE_VAR set (NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, VAR_NAME_, VALUE_, OBJ_, CREATION_DATE_) = (select NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, VAR_NAME_, VALUE_, OBJ_, CREATION_DATE_ from FF_NODE_VAR_OP where NODE_VAR_OP_ID_ = ?) where NODE_VAR_ID_ = ?";
            ffJdbcTemplate.update(sql, nodeVarOp.get("NODE_VAR_OP_ID_"), nodeVarOp.get("NODE_VAR_ID_"));
        }
        if (OPERATION_TYPE_.equals(FfOperationService.OPERATION_TYPE_DELETE)) {
            sql = "insert into FF_NODE_VAR(NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, VAR_NAME_, VALUE_, OBJ_, CREATION_DATE_)  select NODE_VAR_ID_, NODE_ID_, VAR_TYPE_, VAR_NAME_, VALUE_, OBJ_, CREATION_DATE_ from FF_NODE_VAR_OP where NODE_VAR_OP_ID_ = ?";
            ffJdbcTemplate.update(sql, nodeVarOp.get("NODE_VAR_OP_ID_"));
        }

        // 修改状态为已取消
        sql = "update FF_NODE_VAR_OP set OPERATION_STATUS_ = '9' where NODE_VAR_OP_ID_ = ?";
        ffJdbcTemplate.update(sql, nodeVarOp.get("NODE_VAR_OP_ID_"));
    }
}