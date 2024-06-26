package com.opendynamic.ff.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opendynamic.ff.FfOperation;
import com.opendynamic.ff.handler.NodeHandler;
import com.opendynamic.ff.query.ChildNodeQuery;
import com.opendynamic.ff.query.DelegateQuery;
import com.opendynamic.ff.query.InvolvedProcQuery;
import com.opendynamic.ff.query.NodeQuery;
import com.opendynamic.ff.query.NodeVarQuery;
import com.opendynamic.ff.query.OperationQuery;
import com.opendynamic.ff.query.ParentNodeQuery;
import com.opendynamic.ff.query.ProcDefQuery;
import com.opendynamic.ff.query.ProcQuery;
import com.opendynamic.ff.query.TaskQuery;
import com.opendynamic.ff.vo.CandidateList;
import com.opendynamic.ff.vo.Delegate;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.FfUser;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.NodeOp;
import com.opendynamic.ff.vo.NodeVar;
import com.opendynamic.ff.vo.NodeVarOp;
import com.opendynamic.ff.vo.Operation;
import com.opendynamic.ff.vo.Proc;
import com.opendynamic.ff.vo.ProcDef;
import com.opendynamic.ff.vo.ProcOp;
import com.opendynamic.ff.vo.RunningNodeDef;
import com.opendynamic.ff.vo.RunningProcDef;
import com.opendynamic.ff.vo.Task;
import com.opendynamic.ff.vo.TaskOp;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface FfService {
    /**
     * 流程定义状态-生效。
     */
    public static final String PROC_DEF_STATUS_ACTIVE = "1";
    /**
     * 流程定义状态-失效。
     */
    public static final String PROC_DEF_STATUS_DISABLE = "0";
    /**
     * 流程状态-未开始。
     */
    public static final String PROC_STATUS_NOT_START = "0";
    /**
     * 流程状态-运行中。
     */
    public static final String PROC_STATUS_ACTIVE = "1";
    /**
     * 流程状态-挂起中。
     */
    public static final String PROC_STATUS_SUSPEND = "7";
    /**
     * 流程状态-异常完成。
     */
    public static final String PROC_STATUS_TERMINATE = "8";
    /**
     * 流程状态-正常完成。
     */
    public static final String PROC_STATUS_COMPLETE = "9";
    /**
     * 节点状态-未开始。
     */
    public static final String NODE_STATUS_NOT_START = "0";
    /**
     * 节点状态-运行中。
     */
    public static final String NODE_STATUS_ACTIVE = "1";
    /**
     * 节点状态-挂起中。
     */
    public static final String NODE_STATUS_SUSPEND = "7";
    /**
     * 节点状态-异常完成。
     */
    public static final String NODE_STATUS_TERMINATE = "8";
    /**
     * 节点状态-正常完成。
     */
    public static final String NODE_STATUS_COMPLETE = "9";
    /**
     * 任务状态-未开始。
     */
    public static final String TASK_STATUS_NOT_START = "0";
    /**
     * 任务状态-运行中。
     */
    public static final String TASK_STATUS_ACTIVE = "1";
    /**
     * 任务状态-挂起中。
     */
    public static final String TASK_STATUS_SUSPEND = "7";
    /**
     * 任务状态-异常完成。
     */
    public static final String TASK_STATUS_TERMINATE = "8";
    /**
     * 任务状态-正常完成。
     */
    public static final String TASK_STATUS_COMPLETE = "9";
    /**
     * 转发状态-未转发。
     */
    public static final String FORWARD_STATUS_NOT_FORWARDED = "0";
    /**
     * 转发状态-转发处理中。
     */
    public static final String FORWARD_STATUS_FORWARDING = "1";
    /**
     * 转发状态-转发处理完成。
     */
    public static final String FORWARD_STATUS_FORWARDING_PROCESSING_COMPLETED = "9";
    /**
     * 节点类型-分支。
     */
    public static final String NODE_TYPE_BRANCH = "BRANCH";
    /**
     * 节点类型-任务。
     */
    public static final String NODE_TYPE_TASK = "TASK";
    /**
     * 节点类型-服务任务。
     */
    public static final String NODE_TYPE_SERVICE_TASK = "SERVICE_TASK";
    /**
     * 节点类型-阶段。
     */
    public static final String NODE_TYPE_STAGE = "STAGE";
    /**
     * 节点类型-中心转发任务。
     */
    public static final String NODE_TYPE_CENTER_FORWARD_TASK = "CENTER_FORWARD_TASK";
    /**
     * 节点类型-子流程。
     */
    public static final String NODE_TYPE_SUB_PROC = "SUB_PROC";
    /**
     * 节点类型-子流程。
     */
    public static final String NODE_TYPE_ISOLATE_SUB_PROC = "ISOLATE_SUB_PROC";
    /**
     * 节点类型-网关。
     */
    public static final String NODE_TYPE_GATEWAY = "GATEWAY";
    /**
     * 节点类型-结束。
     */
    public static final String NODE_TYPE_END = "END";
    /**
     * 任务类型-任务。
     */
    public static final String TASK_TYPE_TASK = "TASK";
    /**
     * 任务类型-转发任务。
     */
    public static final String TASK_TYPE_FORWARD_TASK = "FORWARD_TASK";
    /**
     * 节点变量类型-字符串。
     */
    public static final String VAR_TYPE_STRING = "STRING";
    /**
     * 节点变量类型-对象。
     */
    public static final String VAR_TYPE_OBJECT = "OBJECT";
    /**
     * 布尔值-真。
     */
    public static final String BOOLEAN_TRUE = "1";
    /**
     * 布尔值-假。
     */
    public static final String BOOLEAN_FALSE = "0";
    /**
     * 操作-新增。
     */
    public static final String OPERATION_INSERT = "INSERT";
    /**
     * 操作-异常完成。
     */
    public static final String OPERATION_TERMINATE = "TERMINATE";
    /**
     * 操作-正常完成。
     */
    public static final String OPERATION_COMPLETE = "COMPLETE";
    /**
     * 操作-驳回。
     */
    public static final String OPERATION_REJECT = "REJECT";
    /**
     * 操作-激活。
     */
    public static final String OPERATION_ACTIVATE = "ACTIVATE";
    /**
     * 操作状态-可取消。
     */
    public static final String OPERATION_STATUS_UNDOABLE = "1";
    /**
     * 操作状态-不可取消。
     */
    public static final String OPERATION_STATUS_NOT_UNDOABLE = "0";
    /**
     * 操作状态-已取消。
     */
    public static final String OPERATION_STATUS_UNDOED = "9";
    /**
     * 操作类型-新增。
     */
    public static final String OPERATION_TYPE_INSERT = "INSERT";
    /**
     * 操作类型-修改。
     */
    public static final String OPERATION_TYPE_UPDATE = "UPDATE";
    /**
     * 操作类型-删除。
     */
    public static final String OPERATION_TYPE_DELETE = "DELETE";
    /**
     * 数据范围-流程定义。
     */
    public static final String DATA_SCOPE_PROC_DEF = "PROC_DEF";
    /**
     * 数据范围-流程定义。
     */
    public static final String DATA_SCOPE_PROC = "PROC";
    /**
     * 数据范围-流程定义。
     */
    public static final String DATA_SCOPE_NODE = "NODE";
    /**
     * 数据范围-流程定义。
     */
    public static final String DATA_SCOPE_TASK = "TASK";
    /**
     * 数据范围-操作。
     */
    public static final String DATA_SCOPE_OPERATION = "OPERATION";
    /**
     * 默认节点完成条件表达式。
     */
    public static final String DEFAULT_COMPLETE_EXPRESSION_ = "${COMPLETE/TOTAL>=1}";
    /**
     * 中心转发步骤.
     */
    public static final String CENTER_FORWARD_STEP = "CENTER_FORWARD_STEP";

    /**
     * 系统。
     */
    public static final String USER_FF_SYSTEM = "FF_SYSTEM";

    /**
     * 刷新流程定义缓存。
     * 
     * @return
     */
    public boolean refreshProcDefCache();

    /**
     * 获取节点处理器。
     * 
     * @param nodeType
     * @return
     */
    public NodeHandler getNodeHandler(String nodeType);

    /**
     * 获取内部服务MAP，用于JUEL解析，
     * 
     * @return
     */
    public Map<String, Object> getInternalServiceMap();

    /**
     * 获取外部服务MAP，用于JUEL解析，
     * 
     * @return
     */
    public Map<String, Object> getExternalServiceMap();

    /**
     * 添加外部服务，用于JUEL解析，
     * 
     * @param serviceName
     * @param service
     */
    public void addExternalService(String serviceName, Object service);

    /**
     * 按主键查询流程定义。
     * 
     * @param procDefId
     *        流程定义ID。
     * @return 流程定义。
     */
    public ProcDef loadProcDef(String procDefId);

    /**
     * 按流程定义编码查询，返回生效的最新版本的流程定义。
     * 
     * @param procDefCode
     *        流程定义编码。
     * @return 生效的最新版本的流程定义。
     */
    public ProcDef loadProcDefByCode(String procDefCode);

    /**
     * 获取流程定义图文件。
     * 
     * @param procDefId
     *        流程定义ID。
     * @return 流程定义图文件流。
     */
    public InputStream loadProcDefDiagramFile(String procDefId);

    /**
     * 部署流程定义。
     * 
     * @param procDefId
     *        流程定义ID。
     * @param procDef
     *        流程定义。
     * @param procDefDiagramFile
     *        流程定义图文件。
     * @param procDefDiagramFileName
     *        流程定义图文件名称。
     * @param procDefDiagramFileLength
     *        流程定义图文件长度。
     * @param operatorId
     *        操作人ID。
     * @param operatorName
     *        操作人名称。
     * @return
     */
    public boolean deployProcDef(String procDefId, String procDef, InputStream procDefDiagramFile, String procDefDiagramFileName, Integer procDefDiagramFileLength, String operatorId, String operatorName);

    /**
     * 更新流程定义图文件。
     * 
     * @param procDefId
     * @param procDefDiagramFile
     * @param procDefDiagramFileName
     * @param procDefDiagramFileLength
     * @param procDefDiagramWidth
     * @param procDefDiagramHeight
     * @param operatorId
     * @param operatorName
     * @return
     */
    public boolean updateProcDefDiagramFile(String procDefId, InputStream procDefDiagramFile, String procDefDiagramFileName, Integer procDefDiagramFileLength, Integer procDefDiagramWidth, Integer procDefDiagramHeight, String operatorId, String operatorName);

    /**
     * 废弃流程定义。
     * 
     * @param procDefId
     *        流程定义ID。
     * @param operatorId
     *        操作人ID。
     * @param operatorName
     *        操作人名称。
     * @return
     */
    public boolean disableProcDef(String procDefId, String operatorId, String operatorName);

    /**
     * 恢复流程定义。
     * 
     * @param procDefId
     *        流程定义ID。
     * @param operatorId
     *        操作人ID。
     * @param operatorName
     *        操作人名称。
     * @return
     */
    public boolean enableProcDef(String procDefId, String operatorId, String operatorName);

    /**
     * 删除流程定义。
     * 
     * @param procDefId
     *        流程定义ID。
     * @param operatorId
     *        操作人ID。
     * @param operatorName
     *        操作人名称。
     * @return
     */
    public boolean deleteProcDef(String procDefId, String operatorId, String operatorName);

    /**
     * 调整分支流程定义。
     * 
     * @param branchId
     * @param procDef
     * @param procDefDiagramFile
     * @param procDefDiagramFileName
     * @param procDefDiagramFileLength
     * @param operatorId
     * @param operatorName
     * @return
     */
    @FfOperation
    public FfResult adjustBranchProcDef(String branchId, String procDef, InputStream procDefDiagramFile, String procDefDiagramFileName, Integer procDefDiagramFileLength, String operatorId, String operatorName);

    /**
     * 删除调整流程定义。
     * 
     * @param adjustProcDefId
     * @param updateDate
     * @param operatorId
     * @param operatorName
     * @return
     */
    public boolean deleteAdjustProcDef(String adjustProcDefId, Date updateDate, String operatorId, String operatorName);

    /**
     * 获取节点的流程定义。如有调整，获取调整后的流程定义
     * 
     * @param node
     * @return
     */
    public ProcDef getNodeProcDef(Node node);

    /**
     * 获取运行期流程定义。
     * 
     * @param procId
     *        流程ID。
     * @param currentTaskId
     *        当前任务ID。当前任务ID不为null时，其所在的分支会注入节点变量，并设置当前为true。
     * @param drawOptional
     *        是否绘制可选节点。
     * @return
     */
    public RunningProcDef getRunningProcDef(String procId, String currentTaskId, boolean drawOptional);

    /**
     * 获取起始即将运行节点定义列表。
     * 
     * @param procDef
     * @param nodeVarMap
     */
    public List<RunningNodeDef> getStartRunningNodeDefList(String subProcPath, ProcDef procDef, Map<String, Object> nodeVarMap);

    /**
     * 获取下个准备运行的运行期节点定义列表。
     * 
     * @param taskId
     * @param nodeVarMap
     */
    public List<RunningNodeDef> getNextRunningNodeDefList(String taskId, Map<String, Object> nodeVarMap);

    /**
     * 获取下个准备运行的运行期节点定义列表。
     * 
     * @param node
     * @param nodeVarMap
     */
    public List<RunningNodeDef> getNextRunningNodeDefList(Node node, Map<String, Object> nodeVarMap);

    /**
     * 按主键查询流程。
     * 
     * @param procId
     * @return 流程。
     */
    public Proc loadProc(String procId);

    /**
     * 创建流程查询。
     * 
     * @return 流程查询。
     */
    public ProcDefQuery createProcDefQuery();

    /**
     * 创建流程查询。
     * 
     * @return 流程查询。
     */
    public ProcQuery createProcQuery();

    /**
     * 创建参与的流程查询。
     * 
     * @return 参与的流程查询。
     */
    public InvolvedProcQuery createInvolvedProcQuery();

    /**
     * 更新流程业务数据。
     * 
     * @param procId
     * @param bizId
     * @param bizType
     * @param bizCode
     * @param bizName
     * @param bizDesc
     * @return
     */
    public boolean updateProcBizInfo(String procId, String bizId, String bizType, String bizCode, String bizName, String bizDesc);

    /**
     * 按流程定义ID启动流程。
     * 
     * @param procDef
     *        流程定义
     * @param bizId
     *        业务主键
     * @param bizType
     *        业务类型
     * @param bizName
     *        业务名称
     * @param bizDesc
     *        业务备注
     * @param procStartUser
     *        流程开始人员
     * @param nodeVarMap
     *        节点变量，
     * @param candidate
     *        候选。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult startProc(ProcDef procDef, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList);

    /**
     * 按流程定义编码启动流程。
     * 
     * @param procDefCode
     *        流程定义编码
     * @param bizId
     *        业务主键
     * @param bizType
     *        业务类型
     * @param bizCode
     *        业务编码
     * @param bizName
     *        业务名称
     * @param bizDesc
     *        业务备注
     * @param procStartUser
     *        流程开始人员
     * @param nodeVarMap
     *        节点变量，
     * @param candidate
     *        候选。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult startProcByProcDefCode(String procDefCode, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList);

    /**
     * 启动独立子流程。
     * 
     * @param isolateSubProcNodeId
     *        独立子流程所属节点ID
     * @param bizId
     *        业务主键
     * @param bizType
     *        业务类型
     * @param bizCode
     *        业务编码
     * @param bizName
     *        业务名称
     * @param bizDesc
     *        业务备注
     * @param procStartUser
     *        流程开始人员
     * @param nodeVarMap
     *        节点变量，
     * @return
     */
    @FfOperation
    public FfResult startIsolateSubProc(String isolateSubProcNodeId, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList);

    /**
     * 按流程定义编码启动流程,并直接跳转到指定节点。
     * 
     * @param procDef
     * @param subProcPath
     *        子流程路径
     * @param nodeCode
     *        跳转节点编码
     * @param bizId
     * @param bizType
     * @param bizCode
     * @param bizName
     * @param bizDesc
     * @param procStartUser
     * @param nodeVarMap
     * @param candidateList
     * @return
     */
    @FfOperation
    public FfResult startProcToNode(ProcDef procDef, String subProcPath, String nodeCode, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList);

    /**
     * 按流程定义编码启动流程,并直接跳转到指定节点。
     * 
     * @param procDefCode
     *        流程定义编码
     * @param subProcPath
     *        子流程路径
     * @param nodeCode
     *        跳转节点编码
     * @param bizId
     *        业务主键
     * @param bizType
     *        业务类型
     * @param bizCode
     *        业务编码
     * @param bizName
     *        业务名称
     * @param bizDesc
     *        业务备注
     * @param procStartUser
     *        流程开始人员
     * @param nodeVarMap
     *        节点变量，
     * @param candidate
     *        候选。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult startProcToNodeByProcDefCode(String procDefCode, String subProcPath, String nodeCode, String bizId, String bizType, String bizCode, String bizName, String bizDesc, String procStartUser, Map<String, Object> nodeVarMap, CandidateList candidateList);

    /**
     * 挂起流程。
     * 
     * @param procId
     *        流程ID。
     * @param taskId
     *        任务ID。仅用来记录该操作所关联任务的日志信息。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult suspendProc(String procId, String taskId, String executor);

    /**
     * 激活流程。
     * 
     * @param procId
     *        流程ID。
     * @param taskId
     *        任务ID。仅用来记录该操作所关联任务的日志信息。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult activateProc(String procId, String taskId, String executor);

    /**
     * 正常完成流程。
     * 
     * @param procId
     *        流程ID。
     * @param taskId
     *        任务ID。仅用来记录该操作所关联任务的日志信息。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult completeProc(String procId, String taskId, String executor);

    /**
     * 异常完成流程。
     * 
     * @param procId
     *        流程ID。
     * @param taskId
     *        任务ID。仅用来记录该操作所关联任务的日志信息。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult terminateProc(String procId, String taskId, String executor);

    /**
     * 删除流程。
     * 
     * @param procId
     *        流程ID。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult deleteProc(String procId, String executor);

    /**
     * 彻底清除流程。包括流程数据和操作数据
     * 
     * @param procId
     *        流程ID。
     */
    public void cleanProc(String procId);

    /**
     * 按主键查询节点。
     * 
     * @param nodeId
     * @return 节点。
     */
    public Node loadNode(String nodeId);

    /**
     * 创建节点查询。
     * 
     * @return 节点查询。
     */
    public NodeQuery createNodeQuery();

    /**
     * 创建父节点查询。
     * 
     * @return 父节点查询。
     */
    public ParentNodeQuery createParentNodeQuery();

    /**
     * 创建子节点查询。
     * 
     * @return 子节点查询。
     */
    public ChildNodeQuery createChildNodeQuery();

    /**
     * 新增节点。
     * 
     * @param nodeDef
     *        节点定义。
     * @param branchNode
     *        分支。
     * @param previousNodeIds
     *        前节点。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult insertNode(NodeDef nodeDef, Node branchNode, String previousNodeIds, CandidateList candidateList, String executor);

    /**
     * 激活节点。
     * 
     * @param procId
     *        流程ID。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult activateNode(String nodeId, CandidateList candidateList, String executor);

    /**
     * 正常完成节点。
     * 
     * @param procId
     *        流程ID。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult completeNode(String nodeId, CandidateList candidateList, String executor);

    /**
     * 异常完成节点。
     * 
     * @param procId
     *        流程ID。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult terminateNode(String nodeId, CandidateList candidateList, String executor);

    /**
     * 删除节点。
     * 
     * @param nodeId
     *        节点ID。
     * @param executor
     *        执行人。
     * @return 所有变化的任务，节点和任务。
     */
    @FfOperation
    public FfResult deleteNode(String nodeId, String executor);

    /**
     * 获取节点的子流程路径
     * 
     * @param node
     * @return
     */
    public String getSubProcPath(Node node);

    /**
     * 按主键查询任务。
     * 
     * @param taskId
     * @return 任务。
     */
    public Task loadTask(String taskId);

    /**
     * 创建任务查询。
     * 
     * @return 任务查询。
     */
    public TaskQuery createTaskQuery();

    /**
     * 新增任务。
     * 
     * @param task
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult insertTask(Task task, String executor);

    /**
     * 修改任务办理人。
     * 
     * @param taskId
     * @param assignee
     * @param assigneeName
     * @return
     */
    @FfOperation
    public FfResult updateTaskAssignee(String taskId, String assignee, String assigneeName, String executor);

    /**
     * 挂起任务。
     * 
     * @param taskId
     *        任务ID。
     * @param executor
     *        执行人。
     * @return 所有变化的任务，节点和任务。
     */
    @FfOperation
    public FfResult suspendTask(String taskId, String executor);

    /**
     * 激活任务。
     * 
     * @param taskId
     *        任务ID。
     * @param executor
     *        执行人。
     * @return 所有变化的任务，节点和任务。
     */
    @FfOperation
    public FfResult activateTask(String taskId, String executor);

    /**
     * 异常完成任务。
     * 
     * @param taskId
     *        任务ID。
     * @param executor
     *        执行人。
     * @return 所有变化的任务，节点和任务。
     */
    @FfOperation
    public FfResult terminateTask(String taskId, String executor);

    /**
     * 删除任务。
     * 
     * @param taskId
     *        任务ID。
     * @param executor
     *        执行人。
     * @return 所有变化的任务，节点和任务。
     */
    @FfOperation
    public FfResult deleteTask(String taskId, String executor);

    /**
     * 正常完成任务。
     * 
     * @param taskId
     *        任务ID。
     * @param branchNodeVar
     *        分支节点变量。
     * @param candidate
     *        候选。
     * @param executor
     *        执行人。
     * @return 所有变化的任务，节点和任务。
     */
    @FfOperation
    public FfResult completeTask(String taskId, Map<String, Object> branchNodeVar, CandidateList candidateList, String executor);

    /**
     * 驳回任务并跳转到指定节点，原任务异常完成。
     * 
     * @param taskId
     *        任务ID
     * @param subProcPath
     *        子流程路径
     * @param nodeCode
     *        跳转节点编码
     * @param branchNodeVar
     *        分支节点变量。
     * @param candidateList
     *        候选列表
     */
    @FfOperation
    public FfResult completeTaskToNode(String taskId, String subProcPath, String nodeCode, Map<String, Object> branchNodeVar, CandidateList candidateList, String executor);

    /**
     * 认领任务。
     * 
     * @param taskId
     *        任务ID。
     * @param executor
     *        执行人。
     * @return
     */
    @FfOperation
    public FfResult claimTask(String taskId, String executor);

    /**
     * 转发任务
     * 
     * @param taskId
     * @param assigneeList
     * @param action
     * @param dueDate
     * @param claim
     * @param forwardable
     * @param priority
     * @param executor
     * @return
     */
    @FfOperation
    public FfResult forwardTask(String taskId, List<String> assigneeList, String action, Date dueDate, String claim, String forwardable, Integer priority, String executor);

    /**
     * 驳回任务。由当前任务办理人发起， 驳回后，前节点和任务被重新激活。
     * 
     * @param taskId
     *        任务ID
     * @param executor
     *        办理人
     * @return
     */
    @FfOperation
    public FfResult rejectTask(String taskId, CandidateList candidateList, String executor);

    /**
     * 驳回任务并跳转到指定节点，原任务异常完成。
     * 
     * @param taskId
     *        任务ID
     * @param subProcPath
     *        子流程路径
     * @param nodeCode
     *        跳转节点编码
     * @param candidateList
     *        候选列表
     * @param executor
     *        办理人
     * @return 所有变化的流程，节点和任务。
     */
    @FfOperation
    public FfResult rejectTaskToNode(String taskId, String subProcPath, String nodeCode, CandidateList candidateList, String executor);

    /**
     * 在节点上追加候选
     * 
     * @param nodeId
     * @param branchNodeVar
     * @param candidateList
     * @param executor
     * @return
     */
    @FfOperation
    public FfResult appendCandidate(String nodeId, CandidateList candidateList, String executor);

    /**
     * 按主键查询节点变量。
     * 
     * @param nodeVarId
     * @return 节点变量。
     */
    public NodeVar loadNodeVar(String nodeVarId);

    /**
     * 创建节点变量查询。
     * 
     * @return 节点变量查询。
     */
    public NodeVarQuery createNodeVarQuery();

    /**
     * 部署节点变量。
     * 
     * @param nodeVar
     * @return
     */
    public boolean insertNodeVar(NodeVar nodeVar);

    /**
     * 修改节点变量。
     * 
     * @param nodeVar
     * @return
     */
    public boolean updateNodeVar(NodeVar nodeVar);

    /**
     * 批量更新节点变量.同名覆盖。
     * 
     * @param nodeId
     *        节点ID。
     * @param nodeVarMap
     *        节点变量。
     * @return 节点变量ID列表。
     */
    public List<String> updateNodeVar(String nodeId, Map<String, Object> nodeVarMap);

    /**
     * 删除节点变量。
     * 
     * @param nodeVarId
     * @return
     */
    public boolean deleteNodeVar(String nodeVarId);

    /**
     * 删除节点变量。
     * 
     * @param nodeVarId
     * @return
     */
    public boolean deleteNodeVarByNodeId(String nodeId);

    /**
     * 按主键查询代理。
     * 
     * @param delegateId
     *        代理ID。
     * @return 代理。
     */
    public Delegate loadDelegate(String delegateId);

    /**
     * 创建代理查询。
     * 
     * @return 任务查询。
     */
    public DelegateQuery createDelegateQuery();

    /**
     * 新增代理。
     * 
     * @param delegateId
     * @param assignee
     * @param assigneeName
     * @param delegator
     * @param delegatorName
     * @param startDate
     * @param endDate
     * @return
     */
    public boolean insertDelegate(String delegateId, String assignee, String assigneeName, String delegator, String delegatorName, Date startDate, Date endDate);

    /**
     * 修改代理。
     * 
     * @param delegateId
     * @param assignee
     * @param assigneeName
     * @param delegator
     * @param delegatorName
     * @param startDate
     * @param endDate
     * @return
     */
    public boolean updateDelegate(String delegateId, String assignee, String assigneeName, String delegator, String delegatorName, Date startDate, Date endDate);

    /**
     * 删除代理。
     * 
     * @param delegateId
     * @return
     */
    public boolean deleteDelegate(String delegateId);

    /**
     * 是否为代理人。
     * 
     * @param assignee
     * @param delegator
     * @return
     */
    public boolean isDelegator(String assignee, String delegator);

    /**
     * 按主键查询操作。
     * 
     * @param operationId
     * @return 操作。
     */
    public Operation loadOperation(String operationId);

    /**
     * 创建操作查询。
     * 
     * @return 操作查询。
     */
    public OperationQuery createOperationQuery();

    public List<ProcOp> selectProcOp(String operationId);

    public List<NodeOp> selectNodeOp(String operationId);

    public List<TaskOp> selectTaskOp(String operationId);

    public List<NodeVarOp> selectNodeVarOp(String operationId);

    /**
     * 取消。
     * 
     * @param operationId
     * @return 所有变化的任务，节点和任务。
     */
    public FfResult undo(String operationId);

    /**
     * 将逗号分割的办理人字符串转换为FfUser列表
     * 
     * @param assigneeString
     * @return
     */
    public List<FfUser> getAssigneeList(String assigneeString);
}