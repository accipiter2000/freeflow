package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.vo.Node;

/**
 * 下级节点查询类。
 */
@Service
public class ChildNodeQuery {
    private final FfNodeService ffNodeService;

    private String nodeId;// 节点ID。
    private String previousNodeIds; // 前节点IDs。
    private String lastCompleteNodeIds; // 最后完成节点IDs。
    private String subProcDefId; // 子流程定义ID。
    private List<String> subProcDefIdList; // 子流程定义ID列表。
    private String adjustSubProcDefId;// 调整子流程定义ID。
    private List<String> adjustSubProcDefIdList;// 调整子流程定义ID列表。
    private String nodeType; // 节点类型。
    private List<String> nodeTypeList; // 节点类型列表。
    private String nodeCode; // 节点编码。
    private List<String> nodeCodeList; // 节点编码列表。
    private String nodeName;// 节点名称。
    private List<String> nodeNameList;// 节点名称列表。
    private String parentNodeCode;// 上级节点编码。
    private List<String> parentNodeCodeList;// 上级节点编码列表。
    private String nodeEndUser;// 节点完成人员。
    private List<String> nodeEndUserList;// 节点完成人员列表。
    private String nodeEndUserName;// 节点完成人员名称。
    private List<String> nodeEndUserNameList;// 节点完成人员名称列表。
    private Date fromNodeEndDate; // 起始节点完成日期。
    private Date toNodeEndDate; // 截止节点完成日期。
    private String isolateSubProcDefCode;// 独立子流程定义编码。
    private List<String> isolateSubProcDefCodeList;// 独立子流程定义编码列表。
    private String isolateSubProcStatus; // 独立子流程状态。
    private List<String> isolateSubProcStatusList; // 独立子流程状态列表。
    private String nodeStatus;// 节点状态。
    private List<String> nodeStatusList;// 节点状态列表。
    private Date fromCreationDate;// 起始创建日期。
    private Date toCreationDate;// 截止创建日期。
    private String procId;// 流程ID。
    private List<String> procIdList;// 流程ID列表。
    private String adjustProcDefId; // 调整流程定义ID。
    private List<String> adjustProcDefIdList; // 调整流程定义ID列表。
    private String isolateSubProcNodeId; // 独立子流程所属节点ID。
    private List<String> isolateSubProcNodeIdList; // 独立子流程所属节点ID列表。
    private String bizId;// 业务主键。
    private List<String> bizIdList;// 业务主键列表。
    private String bizType; // 业务类型。
    private List<String> bizTypeList; // 业务类型列表。
    private String bizCode;// 业务编码。
    private List<String> bizCodeList;// 业务编码列表。
    private String bizName;// 业务名称。
    private List<String> bizNameList;// 业务名称列表。
    private String bizDesc;// 业务备注。
    private List<String> bizDescList;// 业务备注列表。
    private String procStartUser;// 流程开始人员。
    private List<String> procStartUserList;// 流程开始人员列表。
    private String procStartUserName;// 流程开始人员名称。
    private List<String> procStartUserNameList;// 流程开始人员名称列表。
    private String procEndUser;// 流程完成人员。
    private List<String> procEndUserList;// 流程完成人员列表。
    private String procEndUserName;// 流程完成人员名称。
    private List<String> procEndUserNameList;// 流程完成人员名称列表。
    private Date fromProcEndDate;// 起始流程完成日期。
    private Date toProcEndDate;// 截止流程完成日期。
    private String procStatus;// 流程状态。
    private List<String> procStatusList;// 流程状态列表。
    private Date fromProcCreationDate;// 起始流程创建日期。
    private Date toProcCreationDate;// 截止流程创建日期。
    private String procDefId;// 流程定义ID。
    private List<String> procDefIdList;// 流程定义ID列表。
    private String procDefCode;// 流程定义编码。
    private List<String> procDefCodeList;// 流程定义编码列表。
    private String procDefName; // 流程定义名称。
    private List<String> procDefNameList; // 流程定义名称列表。
    private String procDefCat; // 流程定义分类。
    private List<String> procDefCatList; // 流程定义分类列表。
    private Integer version; // 版本。
    private List<Integer> versionList; // 版本列表。
    private String procDefStatus;// 流程定义状态。
    private List<String> procDefStatusList;// 流程定义状态列表。
    private Boolean emptyParentNode;// 空上级节点编码。
    private Boolean emptyPreviousNodes;// 空前节点IDs。
    private Boolean emptyLastCompleteNodes;// 空最后完成节点IDs。
    private Boolean recursive;// 是否递归，默认为false。
    private Boolean includeSelf;// 是否包含自己，默认为false。
    private String dataScope;// 数据范围。

    public ChildNodeQuery(FfNodeService ffNodeService) {
        super();
        this.ffNodeService = ffNodeService;
    }

    /**
     * 设置节点ID。
     * 
     * @param nodeId
     *        节点ID。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * 设置前节点IDs。
     * 
     * @param previousNodeIds
     *        前节点IDs。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setPreviousNodeIds(String previousNodeIds) {
        this.previousNodeIds = previousNodeIds;
        return this;
    }

    /**
     * 设置最后完成节点IDs。
     * 
     * @param lastCompleteNodeIds
     *        最后完成节点IDs。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setLastCompleteNodeIds(String lastCompleteNodeIds) {
        this.lastCompleteNodeIds = lastCompleteNodeIds;
        return this;
    }

    /**
     * 设置子流程定义ID。
     * 
     * @param subProcDefId
     *        子流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setSubProcDefId(String subProcDefId) {
        this.subProcDefId = subProcDefId;
        return this;
    }

    /**
     * 设置子流程定义ID列表。
     * 
     * @param subProcDefIdList
     *        子流程定义ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setSubProcDefIdList(List<String> subProcDefIdList) {
        this.subProcDefIdList = subProcDefIdList;
        return this;
    }

    /**
     * 设置调整子流程定义ID。
     * 
     * @param adjustSubProcDefId
     *        调整子流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setAdjustSubProcDefId(String adjustSubProcDefId) {
        this.adjustSubProcDefId = adjustSubProcDefId;
        return this;
    }

    /**
     * 设置调整子流程定义ID列表。
     * 
     * @param adjustSubProcDefIdList
     *        调整子流程定义ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setAdjustSubProcDefIdList(List<String> adjustSubProcDefIdList) {
        this.adjustSubProcDefIdList = adjustSubProcDefIdList;
        return this;
    }

    /**
     * 设置节点类型。
     * 
     * @param nodeType
     *        节点类型。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeType(String nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    /**
     * 设置节点类型列表。
     * 
     * @param nodeTypeList
     *        节点类型列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeTypeList(List<String> nodeTypeList) {
        this.nodeTypeList = nodeTypeList;
        return this;
    }

    /**
     * 设置节点编码。
     * 
     * @param nodeCode
     *        节点编码。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
        return this;
    }

    /**
     * 设置节点编码列表。
     * 
     * @param nodeCodeList
     *        节点编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeCodeList(List<String> nodeCodeList) {
        this.nodeCodeList = nodeCodeList;
        return this;
    }

    /**
     * 设置节点名称。
     * 
     * @param nodeName
     *        节点名称。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    /**
     * 设置节点名称列表。
     * 
     * @param nodeNameList
     *        节点名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeNameList(List<String> nodeNameList) {
        this.nodeNameList = nodeNameList;
        return this;
    }

    /**
     * 设置上级节点编码。
     * 
     * @param parentNodeCode
     *        上级节点编码。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setParentNodeCode(String parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
        return this;
    }

    /**
     * 设置上级节点编码列表。
     * 
     * @param parentNodeCodeList
     *        上级节点编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setParentNodeCodeList(List<String> parentNodeCodeList) {
        this.parentNodeCodeList = parentNodeCodeList;
        return this;
    }

    /**
     * 设置节点完成人员。
     * 
     * @param nodeEndUser
     *        节点完成人员。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeEndUser(String nodeEndUser) {
        this.nodeEndUser = nodeEndUser;
        return this;
    }

    /**
     * 设置节点完成人员列表。
     * 
     * @param nodeEndUserList
     *        节点完成人员列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeEndUserList(List<String> nodeEndUserList) {
        this.nodeEndUserList = nodeEndUserList;
        return this;
    }

    /**
     * 设置节点完成人员名称。
     * 
     * @param nodeEndUserName
     *        节点完成人员名称。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeEndUserName(String nodeEndUserName) {
        this.nodeEndUserName = nodeEndUserName;
        return this;
    }

    /**
     * 设置节点完成人员名称列表。
     * 
     * @param nodeEndUserNameList
     *        节点完成人员名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeEndUserNameList(List<String> nodeEndUserNameList) {
        this.nodeEndUserNameList = nodeEndUserNameList;
        return this;
    }

    /**
     * 设置起始节点完成日期。
     * 
     * @param fromNodeEndDate
     *        起始节点完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setFromNodeEndDate(Date fromNodeEndDate) {
        this.fromNodeEndDate = fromNodeEndDate;
        return this;
    }

    /**
     * 设置截止节点完成日期。
     * 
     * @param toNodeEndDate
     *        截止节点完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setToNodeEndDate(Date toNodeEndDate) {
        this.toNodeEndDate = toNodeEndDate;
        return this;
    }

    /**
     * 设置独立子流程定义编码。
     * 
     * @param isolateSubProcDefCode
     *        独立子流程定义编码。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setIsolateSubProcDefCode(String isolateSubProcDefCode) {
        this.isolateSubProcDefCode = isolateSubProcDefCode;
        return this;
    }

    /**
     * 设置独立子流程定义编码列表。
     * 
     * @param isolateSubProcDefCodeList
     *        独立子流程定义编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setIsolateSubProcDefCodeList(List<String> isolateSubProcDefCodeList) {
        this.isolateSubProcDefCodeList = isolateSubProcDefCodeList;
        return this;
    }

    /**
     * 设置独立子流程状态。
     * 
     * @param isolateSubProcStatus
     *        独立子流程状态。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setIsolateSubProcStatus(String isolateSubProcStatus) {
        this.isolateSubProcStatus = isolateSubProcStatus;
        return this;
    }

    /**
     * 设置独立子流程状态列表。
     * 
     * @param isolateSubProcStatusList
     *        独立子流程状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setIsolateSubProcStatusList(List<String> isolateSubProcStatusList) {
        this.isolateSubProcStatusList = isolateSubProcStatusList;
        return this;
    }

    /**
     * 设置节点状态。
     * 
     * @param nodeStatus
     *        节点状态。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
        return this;
    }

    /**
     * 设置节点状态列表。
     * 
     * @param nodeStatusList
     *        节点状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setNodeStatusList(List<String> nodeStatusList) {
        this.nodeStatusList = nodeStatusList;
        return this;
    }

    /**
     * 设置起始创建日期。
     * 
     * @param fromCreationDate
     *        起始创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
        return this;
    }

    /**
     * 设置截止创建日期。
     * 
     * @param toCreationDate
     *        截止创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
        return this;
    }

    /**
     * 设置流程ID。
     * 
     * @param procId
     *        流程ID。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcId(String procId) {
        this.procId = procId;
        return this;
    }

    /**
     * 设置流程ID列表。
     * 
     * @param procIdList
     *        流程ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcIdList(List<String> procIdList) {
        this.procIdList = procIdList;
        return this;
    }

    /**
     * 设置调整流程定义ID。
     * 
     * @param adjustProcDefId
     *        调整流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setAdjustProcDefId(String adjustProcDefId) {
        this.adjustProcDefId = adjustProcDefId;
        return this;
    }

    /**
     * 设置调整流程定义ID列表。
     * 
     * @param adjustProcDefIdList
     *        调整流程定义ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setAdjustProcDefIdList(List<String> adjustProcDefIdList) {
        this.adjustProcDefIdList = adjustProcDefIdList;
        return this;
    }

    /**
     * 设置独立子流程所属节点ID。
     * 
     * @param isolateSubProcNodeId
     *        独立子流程所属节点ID。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setIsolateSubProcNodeId(String isolateSubProcNodeId) {
        this.isolateSubProcNodeId = isolateSubProcNodeId;
        return this;
    }

    /**
     * 设置独立子流程所属节点ID列表。
     * 
     * @param isolateSubProcNodeIdList
     *        独立子流程所属节点ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setIsolateSubProcNodeIdList(List<String> isolateSubProcNodeIdList) {
        this.isolateSubProcNodeIdList = isolateSubProcNodeIdList;
        return this;
    }

    /**
     * 设置业务主键。
     * 
     * @param bizId
     *        业务主键。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    /**
     * 设置业务主键列表。
     * 
     * @param bizIdList
     *        业务主键列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setBizIdList(List<String> bizIdList) {
        this.bizIdList = bizIdList;
        return this;
    }

    /**
     * 设置业务类型。
     * 
     * @param bizType
     *        业务类型。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    /**
     * 设置业务类型列表。
     * 
     * @param bizTypeList
     *        业务类型列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setBizTypeList(List<String> bizTypeList) {
        this.bizTypeList = bizTypeList;
        return this;
    }

    /**
     * 设置业务编码。
     * 
     * @param bizCode
     *        业务编码。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    /**
     * 设置业务编码列表。
     * 
     * @param bizCodeList
     *        业务编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setBizCodeList(List<String> bizCodeList) {
        this.bizCodeList = bizCodeList;
        return this;
    }

    /**
     * 设置业务名称。
     * 
     * @param bizName
     *        业务名称。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setBizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    /**
     * 设置业务名称列表。
     * 
     * @param bizNameList
     *        业务名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setBizNameList(List<String> bizNameList) {
        this.bizNameList = bizNameList;
        return this;
    }

    /**
     * 设置业务备注。
     * 
     * @param bizDesc
     *        业务备注。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
        return this;
    }

    /**
     * 设置业务备注列表。
     * 
     * @param bizDescList
     *        业务备注列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setBizDescList(List<String> bizDescList) {
        this.bizDescList = bizDescList;
        return this;
    }

    /**
     * 设置流程开始人员。
     * 
     * @param procStartUser
     *        流程开始人员。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcStartUser(String procStartUser) {
        this.procStartUser = procStartUser;
        return this;
    }

    /**
     * 设置流程开始人员列表。
     * 
     * @param procStartUserList
     *        流程开始人员列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcStartUserList(List<String> procStartUserList) {
        this.procStartUserList = procStartUserList;
        return this;
    }

    /**
     * 设置流程开始人员名称。
     * 
     * @param procStartUserName
     *        流程开始人员名称。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcStartUserName(String procStartUserName) {
        this.procStartUserName = procStartUserName;
        return this;
    }

    /**
     * 设置流程开始人员名称列表。
     * 
     * @param procStartUserNameList
     *        流程开始人员名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcStartUserNameList(List<String> procStartUserNameList) {
        this.procStartUserNameList = procStartUserNameList;
        return this;
    }

    /**
     * 设置流程完成人员。
     * 
     * @param procEndUser
     *        流程完成人员。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcEndUser(String procEndUser) {
        this.procEndUser = procEndUser;
        return this;
    }

    /**
     * 设置流程完成人员列表。
     * 
     * @param procEndUserList
     *        流程完成人员列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcEndUserList(List<String> procEndUserList) {
        this.procEndUserList = procEndUserList;
        return this;
    }

    /**
     * 设置流程完成人员名称。
     * 
     * @param procEndUserName
     *        流程完成人员名称。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcEndUserName(String procEndUserName) {
        this.procEndUserName = procEndUserName;
        return this;
    }

    /**
     * 设置流程完成人员名称列表。
     * 
     * @param procEndUserNameList
     *        流程完成人员名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcEndUserNameList(List<String> procEndUserNameList) {
        this.procEndUserNameList = procEndUserNameList;
        return this;
    }

    /**
     * 设置起始流程完成日期。
     * 
     * @param fromProcEndDate
     *        起始流程完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setFromProcEndDate(Date fromProcEndDate) {
        this.fromProcEndDate = fromProcEndDate;
        return this;
    }

    /**
     * 设置截止流程完成日期。
     * 
     * @param toProcEndDate
     *        截止流程完成日期。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setToProcEndDate(Date toProcEndDate) {
        this.toProcEndDate = toProcEndDate;
        return this;
    }

    /**
     * 设置流程状态。
     * 
     * @param procStatus
     *        流程状态。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcStatus(String procStatus) {
        this.procStatus = procStatus;
        return this;
    }

    /**
     * 设置流程状态列表。
     * 
     * @param procStatusList
     *        流程状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcStatusList(List<String> procStatusList) {
        this.procStatusList = procStatusList;
        return this;
    }

    /**
     * 设置起始流程创建日期。
     * 
     * @param fromProcCreationDate
     *        起始流程创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setFromProcCreationDate(Date fromProcCreationDate) {
        this.fromProcCreationDate = fromProcCreationDate;
        return this;
    }

    /**
     * 设置截止流程创建日期。
     * 
     * @param toProcCreationDate
     *        截止流程创建日期。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setToProcCreationDate(Date toProcCreationDate) {
        this.toProcCreationDate = toProcCreationDate;
        return this;
    }

    /**
     * 设置流程定义ID。
     * 
     * @param procDefId
     *        流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcDefId(String procDefId) {
        this.procDefId = procDefId;
        return this;
    }

    /**
     * 设置流程定义ID列表。
     * 
     * @param procDefIdList
     *        流程定义ID列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcDefIdList(List<String> procDefIdList) {
        this.procDefIdList = procDefIdList;
        return this;
    }

    /**
     * 设置流程定义编码。
     * 
     * @param procDefCode
     *        流程定义编码。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
        return this;
    }

    /**
     * 设置流程定义编码列表。
     * 
     * @param procDefCodeList
     *        流程定义编码列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcDefCodeList(List<String> procDefCodeList) {
        this.procDefCodeList = procDefCodeList;
        return this;
    }

    /**
     * 设置流程定义名称。
     * 
     * @param procDefName
     *        流程定义名称。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcDefName(String procDefName) {
        this.procDefName = procDefName;
        return this;
    }

    /**
     * 设置流程定义名称列表。
     * 
     * @param procDefNameList
     *        流程定义名称列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcDefNameList(List<String> procDefNameList) {
        this.procDefNameList = procDefNameList;
        return this;
    }

    /**
     * 设置流程定义分类。
     * 
     * @param procDefCat
     *        流程定义分类。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
        return this;
    }

    /**
     * 设置流程定义分类列表。
     * 
     * @param procDefCatList
     *        流程定义分类列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcDefCatList(List<String> procDefCatList) {
        this.procDefCatList = procDefCatList;
        return this;
    }

    /**
     * 设置版本。
     * 
     * @param version
     *        版本。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * 设置版本列表。
     * 
     * @param versionList
     *        版本列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setVersionList(List<Integer> versionList) {
        this.versionList = versionList;
        return this;
    }

    /**
     * 设置流程定义状态。
     * 
     * @param procDefStatus
     *        流程定义状态。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
        return this;
    }

    /**
     * 设置流程定义状态列表。
     * 
     * @param procDefStatusList
     *        流程定义状态列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setProcDefStatusList(List<String> procDefStatusList) {
        this.procDefStatusList = procDefStatusList;
        return this;
    }

    /**
     * 设置空上级节点编码。
     * 
     * @param emptyParentNode
     *        空上级节点编码。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setEmptyParentNode(Boolean emptyParentNode) {
        this.emptyParentNode = emptyParentNode;
        return this;
    }

    /**
     * 设置空前节点IDs。
     * 
     * @param emptyPreviousNodes
     *        空前节点IDs。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setEmptyPreviousNodes(Boolean emptyPreviousNodes) {
        this.emptyPreviousNodes = emptyPreviousNodes;
        return this;
    }

    /**
     * 设置空最后完成节点IDs。
     * 
     * @param emptyLastCompleteNodes
     *        空最后完成节点IDs。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setEmptyLastCompleteNodes(Boolean emptyLastCompleteNodes) {
        this.emptyLastCompleteNodes = emptyLastCompleteNodes;
        return this;
    }

    /**
     * 设置是否递归，默认为false。
     * 
     * @param recursive
     *        是否递归，默认为false。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setRecursive(Boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    /**
     * 设置是否包含自己，默认为false。
     * 
     * @param includeSelf
     *        是否包含自己，默认为false。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setIncludeSelf(Boolean includeSelf) {
        this.includeSelf = includeSelf;
        return this;
    }

    /**
     * 设置数据范围。
     * 
     * @param dataScope
     *        数据范围。
     * @return 当前查询实例，支持链式调用。
     */
    public ChildNodeQuery setDataScope(String dataScope) {
        this.dataScope = dataScope;
        return this;
    }

    /**
     * 查询对象列表。数据格式为Map。
     * 
     * @return Map类型数据列表。
     */
    public List<Map<String, Object>> queryForMapList() {
        return ffNodeService.selectChildNode(nodeId, previousNodeIds, lastCompleteNodeIds, subProcDefId, subProcDefIdList, adjustSubProcDefId, adjustSubProcDefIdList, nodeType, nodeTypeList, nodeCode, nodeCodeList, nodeName, nodeNameList, parentNodeCode, parentNodeCodeList, nodeEndUser, nodeEndUserList, nodeEndUserName, nodeEndUserNameList, fromNodeEndDate, toNodeEndDate, isolateSubProcDefCode, isolateSubProcDefCodeList, isolateSubProcStatus, isolateSubProcStatusList, nodeStatus, nodeStatusList, fromCreationDate, toCreationDate, procId, procIdList, adjustProcDefId, adjustProcDefIdList, isolateSubProcNodeId, isolateSubProcNodeIdList, bizId, bizIdList, bizType, bizTypeList, bizCode, bizCodeList, bizName, bizNameList, bizDesc, bizDescList, procStartUser, procStartUserList, procStartUserName,
                procStartUserNameList, procEndUser, procEndUserList, procEndUserName, procEndUserNameList, fromProcEndDate, toProcEndDate, procStatus, procStatusList, fromProcCreationDate, toProcCreationDate, procDefId, procDefIdList, procDefCode, procDefCodeList, procDefName, procDefNameList, procDefCat, procDefCatList, version, versionList, procDefStatus, procDefStatusList, emptyParentNode, emptyPreviousNodes, emptyLastCompleteNodes, recursive, includeSelf, dataScope);
    }

    /**
     * 查询单个对象。数据格式为Map。
     * 
     * @return 单个Map类型数据。
     */
    public Map<String, Object> queryForMap() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * 查询对象列表。数据格式为实体Bean。
     * 
     * @return Bean类型数据列表。
     */
    public List<Node> queryForObjectList() {
        List<Map<String, Object>> resultList = queryForMapList();
        List<Node> nodeList = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
            nodeList.add(new Node(result));
        }

        return nodeList;
    }

    /**
     * 查询单个对象。数据格式为实体Bean。
     * 
     * @return 单个Bean类型数据。
     */
    public Node queryForObject() {
        List<Map<String, Object>> result = queryForMapList();
        if (result.size() == 1) {
            return new Node(result.get(0));
        }
        else {
            return null;
        }
    }

    /**
     * 查询总数。
     * 
     * @return 总数。
     */
    public int count() {
        return queryForMapList().size();
    }
}