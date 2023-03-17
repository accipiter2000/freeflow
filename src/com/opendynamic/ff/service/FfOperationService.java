package com.opendynamic.ff.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.ThreadOperation;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface FfOperationService {
    public static final String OPERATION_STATUS_UNDOABLE = "1";
    public static final String OPERATION_STATUS_NOT_UNDOABLE = "0";
    public static final String OPERATION_STATUS_UNDOED = "9";

    public static final String OPERATION_TYPE_INSERT = "INSERT";
    public static final String OPERATION_TYPE_UPDATE = "UPDATE";
    public static final String OPERATION_TYPE_DELETE = "DELETE";

    /**
     * 按主键查询,返回单个对象。
     */
    public Map<String, Object> loadOperation(String OPERATION_ID_);

    /**
     * 通用查询，返回对象列表。
     */
    public List<Map<String, Object>> selectOperation(String OPERATION_ID_, String OPERATION_, String PROC_ID_, String NODE_ID_, String TASK_ID_, List<String> OPERATOR_LIST, String OPERATOR_NAME_, Date FROM_OPERATION_DATE_, Date TO_OPERATION_DATE_, List<String> OPERATION_STATUS_LIST, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_, Integer page, Integer limit);

    /**
     * 总数查询，在分页时与通用查询配套使用。
     */
    public int countOperation(String OPERATION_ID_, String OPERATION_, String PROC_ID_, String NODE_ID_, String TASK_ID_, List<String> OPERATOR_LIST, String OPERATOR_NAME_, Date FROM_OPERATION_DATE_, Date TO_OPERATION_DATE_, List<String> OPERATION_STATUS_LIST, String BIZ_ID_, List<String> BIZ_TYPE_LIST, String BIZ_CODE_, String BIZ_NAME_);

    /**
     * 按主键列表查询，返回对象列表，按主键列表顺序排序。
     */
    public List<Map<String, Object>> selectOperationByIdList(List<String> OPERATION_ID_LIST);

    /**
     * 新增对象。
     */
    public int insertOperation(String OPERATION_ID_, String OPERATION_, String PROC_ID_, String NODE_ID_, String TASK_ID_, String MEMO_, String OPERATOR_, String OPERATOR_NAME_, Date OPERATION_DATE_, String OPERATION_STATUS_);

    /**
     * 修改对象。
     */
    public int updateOperation(String OPERATION_ID_, String OPERATION_, String PROC_ID_, String NODE_ID_, String TASK_ID_, String MEMO_, String OPERATOR_, String OPERATOR_NAME_, Date OPERATION_DATE_);

    /**
     * 拖动排序。
     */
    public int updateOperationStatus(String OPERATION_ID_, String OPERATION_STATUS_);

    /**
     * 删除对象。
     */
    public int deleteOperation(String OPERATION_ID_);

    /**
     * 新增子表对象。
     */
    public int insertProcOp(String PROC_OP_ID_, String PROC_ID_, String OPERATION_TYPE_);

    /**
     * 新增子表对象。
     */
    public int insertNodeOp(String NODE_OP_ID_, String NODE_ID_, String OPERATION_TYPE_);

    /**
     * 新增子表对象。
     */
    public int insertTaskOp(String TASK_OP_ID_, String TASK_ID_, String OPERATION_TYPE_);

    /**
     * 新增子表对象。
     */
    public int insertNodeVarOp(String NODE_VAR_OP_ID_, String NODE_VAR_ID_, String OPERATION_TYPE_);

    /**
     * 界定操作的开始。创建线程变量ThreadOperation，用于界定此次操作的边界。
     * 
     * @param operationType
     * @param procId
     * @param nodeId
     * @param taskId
     * @param memo
     * @param operator
     * @param operatorName
     */
    public void init(String operationType, String procId, String nodeId, String taskId, String memo, String operator, String operatorName);

    /**
     * 界定操作的结束。清空线程变量ThreadOperation。
     */
    public void finalize();

    /**
     * 获取当前线程的ThreadOperation。
     * 
     * @return
     */
    public ThreadOperation getCurrentThreadOperation();

    /**
     * 取消操作。
     * 
     * @param operationId
     * @return
     */
    public FfResult undo(String operationId);
}