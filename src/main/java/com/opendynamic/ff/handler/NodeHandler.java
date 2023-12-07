package com.opendynamic.ff.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opendynamic.ff.vo.CandidateList;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface NodeHandler {
    /**
     * 获取节点类型。
     * 
     * @return
     */
    public String getNodeType();

    /**
     * 按节点定义新增节点。
     * 
     * @param nodeDef
     *        节点定义。
     * @param branchNode
     *        分支。
     * @param previousNodeIds
     *        前节点。
     * @param candidateList
     *        候选列表。
     * @param triggerOperation
     *        触发操作。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    public FfResult insertNodeByNodeDef(NodeDef nodeDef, Node branchNode, String previousNodeIds, CandidateList candidateList, String triggerOperation, String executor);

    public FfResult appendCandidate(Node node, CandidateList candidateList, String executor);

    /**
     * 完成节点。
     * 
     * @param node
     *        节点。
     * @param previousNodeIds
     *        前节点。
     * @param candidateList
     *        候选列表。
     * @param triggerOperation
     *        触发操作。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    public FfResult completeNode(Node node, String previousNodeIds, CandidateList candidateList, String triggerOperation, String executor);

    /**
     * 驳回节点。
     * 
     * @param node
     *        要驳回的节点
     * @param candidateList
     *        候选列表。
     * @param triggerOperation
     *        触发操作。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    public FfResult rejectNode(Node node, CandidateList candidateList, String triggerOperation, String executor);

    /**
     * 激活节点。
     * 
     * @param node
     *        要激活的节点。
     * @param previousNodeIds
     *        前节点。
     * @param candidateList
     *        候选列表。
     * @param triggerOperation
     *        触发操作。
     * @param executor
     *        执行人。
     * @return 所有变化的流程，节点和任务。
     */
    public FfResult activateNode(Node node, String previousNodeIds, CandidateList candidateList, String triggerOperation, String executor);
}