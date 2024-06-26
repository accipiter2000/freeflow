package com.opendynamic.ff;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfHelper;
import com.opendynamic.ff.service.FfOperationService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.Task;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

@Service
@Aspect
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfOperationAspect {
    @Autowired
    private FfService ffService;
    @Autowired
    private FfOperationService ffOperationService;
    @Autowired
    private FfHelper ffHelper;
    @Autowired
    private JdbcTemplate ffJdbcTemplate;

    @Around("@annotation(com.opendynamic.ff.FfOperation)")
    public Object wrapper(ProceedingJoinPoint point) {
        FfResult ffResult = null;

        Object[] arguments = point.getArgs();
        String procId = null;
        String nodeId = null;
        String taskId = null;
        boolean operationRequiresNew = (ffOperationService.getCurrentThreadOperation() == null) ? true : false;
        if (operationRequiresNew) {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            FfOperation annotation = method.getAnnotation(FfOperation.class);

            // JUEL解析
            ExpressionFactory expressionFactory = new ExpressionFactoryImpl();// 解析使用JUEL表达式的注解参数。
            SimpleContext simpleContext = new SimpleContext();
            String[] parameterNames = signature.getParameterNames();
            for (int i = 0; i < parameterNames.length; i++) {
                simpleContext.setVariable(parameterNames[i], expressionFactory.createValueExpression(arguments[i], Object.class));
            }
            ValueExpression expression;
            expression = expressionFactory.createValueExpression(simpleContext, annotation.procId(), String.class);
            procId = (String) expression.getValue(simpleContext);
            expression = expressionFactory.createValueExpression(simpleContext, annotation.nodeId(), String.class);
            nodeId = (String) expression.getValue(simpleContext);
            expression = expressionFactory.createValueExpression(simpleContext, annotation.taskId(), String.class);
            taskId = (String) expression.getValue(simpleContext);
            expression = expressionFactory.createValueExpression(simpleContext, annotation.memo(), String.class);
            String memo = (String) expression.getValue(simpleContext);
            expression = expressionFactory.createValueExpression(simpleContext, annotation.operator(), String.class);
            String operator = (String) expression.getValue(simpleContext);
            if (StringUtils.isNotEmpty(taskId) && StringUtils.isEmpty(nodeId)) {
                Task task = ffService.loadTask(taskId);
                nodeId = task.getNodeId();
                if (StringUtils.isEmpty(procId)) {
                    procId = task.getProcId();
                }
            }
            else
                if (StringUtils.isNotEmpty(nodeId) && StringUtils.isEmpty(procId)) {
                    Node node = ffService.loadNode(nodeId);
                    procId = node.getProcId();
                }
            procId = StringUtils.isNotEmpty(procId) ? procId : null;
            nodeId = StringUtils.isNotEmpty(nodeId) ? nodeId : null;
            taskId = StringUtils.isNotEmpty(taskId) ? taskId : null;
            memo = StringUtils.isNotEmpty(memo) ? memo : null;
            operator = StringUtils.isNotEmpty(operator) ? operator : null;
            String operatorName = ffHelper.getUserName(operator);

            ffOperationService.init(signature.getName(), procId, nodeId, taskId, memo, operator, operatorName);// 初始化ffOperation。
        }

        try {
            ffResult = (FfResult) point.proceed(arguments);// 执行原方法
        }
        catch (Throwable e) {
            ffOperationService.finalize();// 清理threadLocal
            throw new RuntimeException(e);
        }

        // 后期处理。
        // 新增流程补上流程ID.
        if (operationRequiresNew) {
            String operationId = ffOperationService.getCurrentThreadOperation().getOperationId();
            ffResult.setOperationId(operationId);
            if (procId == null && nodeId == null && taskId == null && ffResult.getCreateProcList().size() > 0) {
                procId = ffResult.getCreateProcList().get(0).getProcId();
                String sql = "update FF_OPERATION set PROC_ID_ = ? where OPERATION_ID_ = ?";
                ffJdbcTemplate.update(sql, procId, operationId);
            }

            // 受影响的操作修改其操作状态为不可回退。
            // 查询本次各表的操作
            String sql;
            sql = "select * from FF_PROC_OP where OPERATION_ID_ = ?";
            List<Map<String, Object>> procOpList = ffJdbcTemplate.queryForList(sql, operationId);
            sql = "select * from FF_NODE_OP where OPERATION_ID_ = ?";
            List<Map<String, Object>> nodeOpList = ffJdbcTemplate.queryForList(sql, operationId);
            sql = "select * from FF_TASK_OP where OPERATION_ID_ = ?";
            List<Map<String, Object>> taskOpList = ffJdbcTemplate.queryForList(sql, operationId);
            sql = "select * from FF_NODE_VAR_OP where OPERATION_ID_ = ?";
            List<Map<String, Object>> nodeVarOpList = ffJdbcTemplate.queryForList(sql, operationId);

            // 汇总受影响的各操作id
            Set<String> procIdSet = new HashSet<>();
            Set<String> nodeIdSet = new HashSet<>();
            Set<String> taskIdSet = new HashSet<>();
            Set<String> nodeVarIdSet = new HashSet<>();
            collectAffectedId("PROC_ID_", procOpList, operationId, procIdSet, nodeIdSet, taskIdSet, nodeVarIdSet);
            collectAffectedId("NODE_ID_", nodeOpList, operationId, procIdSet, nodeIdSet, taskIdSet, nodeVarIdSet);
            collectAffectedId("TASK_ID_", taskOpList, operationId, procIdSet, nodeIdSet, taskIdSet, nodeVarIdSet);
            collectAffectedId("NODE_VAR_ID_", nodeVarOpList, operationId, procIdSet, nodeIdSet, taskIdSet, nodeVarIdSet);

            // 计算受影响的operationId
            Set<String> operationIdSet = new HashSet<>();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("PROC_ID_LIST", procIdSet);
            paramMap.put("NODE_ID_LIST", nodeIdSet);
            paramMap.put("TASK_ID_LIST", taskIdSet);
            paramMap.put("NODE_VAR_ID_LIST", nodeVarIdSet);
            paramMap.put("OPERATION_ID_", operationId);
            if (procIdSet.size() > 0) {
                sql = "select * from FF_PROC_OP where PROC_ID_ in (:PROC_ID_LIST) and OPERATION_ID_ != :OPERATION_ID_ and OPERATION_STATUS_ = 1";
                addOperationId(sql, paramMap, operationIdSet);
            }
            if (nodeIdSet.size() > 0) {
                sql = "select * from FF_NODE_OP where NODE_ID_ in (:NODE_ID_LIST) and OPERATION_ID_ != :OPERATION_ID_ and OPERATION_STATUS_ = 1";
                addOperationId(sql, paramMap, operationIdSet);
            }
            if (taskIdSet.size() > 0) {
                sql = "select * from FF_TASK_OP where TASK_ID_ in (:TASK_ID_LIST) and OPERATION_ID_ != :OPERATION_ID_ and OPERATION_STATUS_ = 1";
                addOperationId(sql, paramMap, operationIdSet);
            }
            if (nodeVarIdSet.size() > 0) {
                sql = "select * from FF_NODE_VAR_OP where NODE_VAR_ID_ in (:NODE_VAR_ID_LIST) and OPERATION_ID_ != :OPERATION_ID_ and OPERATION_STATUS_ = 1";
                addOperationId(sql, paramMap, operationIdSet);
            }

            // 修改受影响的以往操作状态为不可取消
            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
            if (procIdSet.size() > 0) {
                sql = "update FF_PROC_OP set OPERATION_STATUS_ = 0 where PROC_ID_ in (:PROC_ID_LIST) and OPERATION_ID_ != :OPERATION_ID_ and OPERATION_STATUS_ = 1";
                namedParameterJdbcTemplate.update(sql, paramMap);
            }
            if (nodeIdSet.size() > 0) {
                sql = "update FF_NODE_OP set OPERATION_STATUS_ = 0 where NODE_ID_ in (:NODE_ID_LIST) and OPERATION_ID_ != :OPERATION_ID_ and OPERATION_STATUS_ = 1";
                namedParameterJdbcTemplate.update(sql, paramMap);
            }
            if (taskIdSet.size() > 0) {
                sql = "update FF_TASK_OP set OPERATION_STATUS_ = 0 where TASK_ID_ in (:TASK_ID_LIST) and OPERATION_ID_ != :OPERATION_ID_ and OPERATION_STATUS_ = 1";
                namedParameterJdbcTemplate.update(sql, paramMap);
            }
            if (nodeVarIdSet.size() > 0) {
                sql = "update FF_NODE_VAR_OP set OPERATION_STATUS_ = 0 where NODE_VAR_ID_ in (:NODE_VAR_ID_LIST) and OPERATION_ID_ != :OPERATION_ID_ and OPERATION_STATUS_ = 1";
                namedParameterJdbcTemplate.update(sql, paramMap);
            }

            if (operationIdSet.size() > 0) {
                // 修改operation的状态为不可取消
                sql = "update FF_OPERATION set OPERATION_STATUS_ = 0 where OPERATION_ID_ in (:OPERATION_ID_LIST) and OPERATION_STATUS_ = 1";
                paramMap.clear();
                paramMap.put("OPERATION_ID_LIST", operationIdSet);
                namedParameterJdbcTemplate.update(sql, paramMap);

                // 新增操作后续
                sql = "insert into FF_OPERATION_FOLLOW_UP (OPERATION_FOLLOW_UP_ID_, OPERATION_ID_, FOLLOW_UP_OPERATION_ID_, OPERATION_DATE_) values (?, ?, ?, ?)";
                for (String _operationId : operationIdSet) {
                    ffJdbcTemplate.update(sql, OdUtils.getUuid(), _operationId, operationId, new Date());
                }
            }

            ffOperationService.finalize();
        }

        return ffResult;
    }

    private void collectAffectedId(String key, List<Map<String, Object>> dataList, String operationId, Set<String> procIdSet, Set<String> nodeIdSet, Set<String> taskIdSet, Set<String> nodeVarIdSet) {
        List<String> insertIdList = new ArrayList<>();// 获取所有新增操作的原表ID
        List<Map<String, Object>> insertDataList = new ArrayList<>();// 获取所有新增操作的原表记录
        for (Map<String, Object> data : dataList) {
            if (data.get("OPERATION_TYPE_").equals(FfService.OPERATION_TYPE_INSERT)) {
                insertIdList.add((String) data.get(key));
                insertDataList.add(data);
            }
        }

        for (Map<String, Object> data : dataList) {// 汇总受影响的原表ID
            if (data.get("OPERATION_TYPE_").equals(FfService.OPERATION_TYPE_INSERT)) {// 本次的新增操作。
                continue;
            }
            if (!data.get("OPERATION_TYPE_").equals(FfService.OPERATION_TYPE_INSERT) && insertIdList.contains(data.get(key))) {// 本次的更新操作，但更新的是本次新增的记录
                continue;
            }

            if ("PROC_ID_".equals(key)) {
                procIdSet.add((String) data.get("PROC_ID_"));
            }
            if ("NODE_ID_".equals(key)) {
                procIdSet.add((String) data.get("PROC_ID_"));
                nodeIdSet.add((String) data.get("NODE_ID_"));
            }
            if ("TASK_ID_".equals(key)) {
                procIdSet.add((String) data.get("PROC_ID_"));
                nodeIdSet.add((String) data.get("NODE_ID_"));
                taskIdSet.add((String) data.get("TASK_ID_"));
            }
            if ("NODE_VAR_ID_".equals(key)) {
                procIdSet.add((String) data.get("PROC_ID_"));
                nodeIdSet.add((String) data.get("NODE_ID_"));
                nodeVarIdSet.add((String) data.get("NODE_VAR_ID_"));
            }
        }

        if ("TASK_ID_".equals(key)) {// 若操作是新增任务，查询其节点，将其加入到影响列表中
            List<String> nodeIdList = new ArrayList<>();// 获取所有新增操作的前任务节点ID列表
            for (Map<String, Object> data : insertDataList) {
                nodeIdList.add((String) data.get("NODE_ID_"));
            }

            if (nodeIdList.size() > 0) {
                String sql = "select * from FF_NODE_OP where NODE_ID_ in (:NODE_ID_LIST) and OPERATION_ID_ != :OPERATION_ID_ and OPERATION_STATUS_ = 1";
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("NODE_ID_LIST", nodeIdList);
                paramMap.put("OPERATION_ID_", operationId);
                NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
                List<Map<String, Object>> nodeOpList = namedParameterJdbcTemplate.queryForList(sql, paramMap);
                for (Map<String, Object> nodeOp : nodeOpList) {
                    procIdSet.add((String) nodeOp.get("PROC_ID_"));
                    nodeIdSet.add((String) nodeOp.get("NODE_ID_"));
                }
            }
        }

        if ("NODE_ID_".equals(key)) {// 若操作是新增节点，查询其前任务节点，将其加入到影响列表中
            List<String> previousNodeIdList = new ArrayList<>();// 获取所有新增操作的前任务节点ID列表
            String PREVIOUS_NODE_IDS_;
            for (Map<String, Object> data : insertDataList) {
                PREVIOUS_NODE_IDS_ = (String) data.get("PREVIOUS_NODE_IDS_");
                if (PREVIOUS_NODE_IDS_ != null) {
                    previousNodeIdList.addAll(Arrays.asList(PREVIOUS_NODE_IDS_.split(",")));
                }
            }

            if (previousNodeIdList.size() > 0) {
                String sql = "select * from FF_NODE_OP where NODE_ID_ in (:NODE_ID_LIST) and OPERATION_ID_ != :OPERATION_ID_ and OPERATION_STATUS_ = 1";
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("NODE_ID_LIST", previousNodeIdList);
                paramMap.put("OPERATION_ID_", operationId);
                NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
                List<Map<String, Object>> nodeOpList = namedParameterJdbcTemplate.queryForList(sql, paramMap);
                for (Map<String, Object> nodeOp : nodeOpList) {
                    procIdSet.add((String) nodeOp.get("PROC_ID_"));
                    nodeIdSet.add((String) nodeOp.get("NODE_ID_"));
                }
            }
        }

        if ("PROC_ID_".equals(key)) {// 终止挂起流程，影响所有本次操作外的操作
            for (Map<String, Object> data : dataList) {
                if (!data.get("OPERATION_TYPE_").equals(FfService.OPERATION_TYPE_INSERT) && !data.get("OPERATION_TYPE_").equals(FfService.OPERATION_TYPE_DELETE)) {
                    String sql = "select * from FF_PROC where PROC_ID_ = ?";
                    List<Map<String, Object>> procList = ffJdbcTemplate.queryForList(sql, data.get("PROC_ID_"));
                    if (!procList.get(0).get("PROC_STATUS_").equals(data.get("PROC_STATUS_"))) {
                        sql = "select * from FF_PROC_OP where PROC_ID_ = ? and OPERATION_ID_ != ? and OPERATION_STATUS_ = 1";
                        List<Map<String, Object>> procOpList = ffJdbcTemplate.queryForList(sql, data.get("PROC_ID_"), operationId);
                        for (Map<String, Object> procOp : procOpList) {
                            procIdSet.add((String) procOp.get("PROC_ID_"));
                        }
                        sql = "select * from FF_NODE_OP where PROC_ID_ = ? and OPERATION_ID_ != ? and OPERATION_STATUS_ = 1";
                        List<Map<String, Object>> nodeOpList = ffJdbcTemplate.queryForList(sql, data.get("PROC_ID_"), operationId);
                        for (Map<String, Object> nodeOp : nodeOpList) {
                            procIdSet.add((String) nodeOp.get("PROC_ID_"));
                            nodeIdSet.add((String) nodeOp.get("NODE_ID_"));
                        }
                        sql = "select FF_TASK_OP.*, FF_NODE.PROC_ID_ from FF_TASK_OP left outer join FF_TASK on FF_TASK.TASK_ID_ = FF_TASK_OP.TASK_ID_ inner join FF_NODE on FF_NODE.NODE_ID_ = FF_TASK.NODE_ID_ where PROC_ID_ = ? and OPERATION_ID_ != ? and OPERATION_STATUS_ = 1";
                        List<Map<String, Object>> taskOpList = ffJdbcTemplate.queryForList(sql, data.get("PROC_ID_"), operationId);
                        for (Map<String, Object> taskOp : taskOpList) {
                            procIdSet.add((String) taskOp.get("PROC_ID_"));
                            nodeIdSet.add((String) taskOp.get("NODE_ID_"));
                            taskIdSet.add((String) taskOp.get("TASK_ID_"));
                        }
                    }
                }
            }
        }
    }

    private void addOperationId(String sql, Map<String, Object> paramMap, Set<String> operationIdSet) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ffJdbcTemplate);
        List<Map<String, Object>> list = namedParameterJdbcTemplate.queryForList(sql, paramMap);
        for (Map<String, Object> map : list) {
            operationIdSet.add((String) map.get("OPERATION_ID_"));
        }
    }
}