package com.opendynamic.ff.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.opendynamic.ff.service.FfHelper;
import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.service.FfProcService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.CandidateList;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.OperationContext;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.Proc;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BranchNodeHandler implements NodeHandler {
    @Autowired
    private FfService ffService;
    @Autowired
    private FfProcService ffProcService;
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfHelper ffHelper;

    @Override
    public String getNodeType() {
        return FfService.NODE_TYPE_BRANCH;
    }

    @Override
    public FfResult insertNodeByNodeDef(NodeDef nodeDef, Node branchNode, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        return new FfResult();
    }

    @Override
    public FfResult appendCandidate(Node node, CandidateList candidateList, OperationContext operationContext) {
        return new FfResult();
    }

    @Override
    public FfResult completeNode(Node node, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {// 如已经完成，直接返回
            return ffResult;
        }

        // 更新最后完成节点IDs
        List<String> lastCompleteNodeIdList = new ArrayList<>();
        if (StringUtils.isNotEmpty(node.getLastCompleteNodeIds())) {
            lastCompleteNodeIdList.addAll(Arrays.asList(node.getLastCompleteNodeIds().split(",")));
        }
        if (StringUtils.isNotEmpty(previousNodeIds)) {
            lastCompleteNodeIdList.addAll(Arrays.asList(previousNodeIds.split(",")));
        }
        ffNodeService.updateNodeLastCompleteNodeIds(node.getNodeId(), StringUtils.join(lastCompleteNodeIdList, ","));
        node.setLastCompleteNodeIds(StringUtils.join(lastCompleteNodeIdList, ","));

        // 完成判断
        if (ffService.createChildNodeQuery().setNodeId(node.getNodeId()).setNodeStatus(FfService.NODE_STATUS_ACTIVE).count() > 0) {
            return ffResult;
        }

        // 完成节点
        // 合并最后完成节点的候选
        CandidateList fullCandidateList = new CandidateList();
        fullCandidateList.addAll(candidateList);
        for (Node previousNode : ffService.createNodeQuery().setNodeIdList(Arrays.asList(node.getLastCompleteNodeIds().split(","))).queryForObjectList()) {
            if (StringUtils.isNotEmpty(previousNode.getNextCandidate())) {
                fullCandidateList.addAll(new Gson().fromJson(previousNode.getNextCandidate(), CandidateList.class));
            }
        }
        String nodeEndUserName = ffHelper.getUserName(operationContext.getExecutor());
        Date nodeEndDate = new Date();
        ffNodeService.updateNodeStatus(node.getNodeId(), operationContext.getExecutor(), nodeEndUserName, nodeEndDate, fullCandidateList.toJson(), FfService.NODE_STATUS_COMPLETE);// 完成节点
        node.setNodeEndUser(operationContext.getExecutor());
        node.setNodeEndUserName(nodeEndUserName);
        node.setNodeEndDate(nodeEndDate);
        node.setNextCandidate(fullCandidateList.toJson());
        node.setNodeStatus(FfService.NODE_STATUS_COMPLETE);
        ffResult.addCompleteNode(node);

        // 如有上级节点，递归完成上级节点，返回新增的后续节点。
        if (node.getParentNodeId() != null) {
            Node parentNode = ffService.loadNode(node.getParentNodeId());
            ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).completeNode(parentNode, node.getNodeId(), fullCandidateList, operationContext));
        }
        else {// 如没有上级节点，完成流程。
            ffProcService.updateProcStatus(node.getProcId(), operationContext.getExecutor(), ffHelper.getUserName(operationContext.getExecutor()), new Date(), FfService.PROC_STATUS_COMPLETE);
            Proc proc = ffService.loadProc(node.getProcId());
            ffResult.addCompleteProc(proc);

            if (StringUtils.isNotEmpty(proc.getIsolateSubProcNodeId())) {// 如果为独立子流程，继续完成独立子流程所属节点
                Node isolateSubProcNode = ffService.loadNode(proc.getIsolateSubProcNodeId());
                ffResult.addAll(ffService.getNodeHandler(isolateSubProcNode.getNodeType()).completeNode(isolateSubProcNode, node.getNodeId(), fullCandidateList, operationContext));
                ffNodeService.updateIsolateSubProcStatus(isolateSubProcNode.getNodeId(), FfService.PROC_STATUS_COMPLETE);
            }
        }

        return ffResult;
    }

    @Override
    public FfResult rejectNode(Node node, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        if (node.getNodeStatus().equals(FfService.NODE_STATUS_TERMINATE) || node.getNodeStatus().equals(FfService.NODE_STATUS_COMPLETE)) {
            return ffResult;
        }

        // 有并发下级节点不能驳回
        if (ffService.createNodeQuery().setParentNodeId(node.getParentNodeId()).setPreviousNodeIds(node.getPreviousNodeIds()).setNodeStatusList(Arrays.asList(FfService.NODE_STATUS_ACTIVE, FfService.NODE_STATUS_SUSPEND, FfService.NODE_STATUS_COMPLETE)).count() > 1) {
            throw new RuntimeException("errors.cannotRejectInParallel");
        }

        String nodeEndUserName = ffHelper.getUserName(operationContext.getExecutor());
        Date nodeEndDate = new Date();
        ffNodeService.updateNodeStatus(node.getNodeId(), operationContext.getExecutor(), nodeEndUserName, nodeEndDate, FfService.NODE_STATUS_TERMINATE);// 完成任务
        node.setNodeEndUser(operationContext.getExecutor());
        node.setNodeEndUserName(nodeEndUserName);
        node.setNodeEndDate(nodeEndDate);
        node.setNodeStatus(FfService.NODE_STATUS_TERMINATE);
        ffResult.addTerminateNode(node);

        if (node.getLastCompleteNodeIds() == null) {// 完成流程
            ffProcService.updateProcStatus(node.getProcId(), operationContext.getExecutor(), ffHelper.getUserName(operationContext.getExecutor()), new Date(), FfService.PROC_STATUS_TERMINATE);
            Proc proc = ffService.loadProc(node.getProcId());
            ffResult.addTerminateProc(proc);

            if (StringUtils.isNotEmpty(proc.getIsolateSubProcNodeId())) {// 如果为独立子流程，修改独立子流程所属节点
                Node isolateSubProcNode = ffService.loadNode(proc.getIsolateSubProcNodeId());
                ffNodeService.updateIsolateSubProcStatus(isolateSubProcNode.getNodeId(), FfService.PROC_STATUS_TERMINATE);
            }

            return ffResult;
        }

        Node parentNode = ffService.loadNode(node.getParentNodeId());
        if (StringUtils.isNotEmpty(node.getPreviousNodeIds()) && !node.getPreviousNodeIds().equals(parentNode.getPreviousNodeIds())) {// 有前一个节点，且与上级节点的前一个节点不同，激活前一个节点。
            String[] previousNodeIds = node.getPreviousNodeIds().split(",");
            Node previousNode;
            for (String previousNodeId : previousNodeIds) {
                previousNode = ffService.loadNode(previousNodeId);
                ffResult.addAll(ffService.getNodeHandler(previousNode.getNodeType()).activateNode(previousNode, null, candidateList, operationContext));
            }
        }
        else {// 递归驳回上级节点
            ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).rejectNode(parentNode, candidateList, operationContext));
        }

        return ffResult;
    }

    @Override
    public FfResult activateNode(Node node, String previousNodeIds, CandidateList candidateList, OperationContext operationContext) {
        FfResult ffResult = new FfResult();// 返回值

        if (FfService.OPERATION_ACTIVATE.equals(operationContext.getInitialOperation()) && StringUtils.isNotEmpty(previousNodeIds)) {// 去除最后完成节点ID
            List<String> lastCompleteNodeIdList = new ArrayList<>();
            if (StringUtils.isNotEmpty(node.getLastCompleteNodeIds())) {
                lastCompleteNodeIdList.addAll(Arrays.asList(node.getLastCompleteNodeIds().split(",")));
            }
            lastCompleteNodeIdList.remove(Arrays.asList(previousNodeIds.split(",")));
            ffNodeService.updateNodeLastCompleteNodeIds(node.getNodeId(), StringUtils.join(lastCompleteNodeIdList, ","));
        }

        if (!node.getNodeStatus().equals(FfService.NODE_STATUS_ACTIVE)) {
            ffNodeService.updateNodeStatus(node.getNodeId(), FfService.NODE_STATUS_ACTIVE);// 激活当前节点
            node.setNodeStatus(FfService.NODE_STATUS_ACTIVE);
            ffResult.addActivateNode(node);

            if (FfService.OPERATION_ACTIVATE.equals(operationContext.getInitialOperation())) {// 上行激活
                if (node.getParentNodeId() != null) {
                    Node parentNode = ffService.loadNode(node.getParentNodeId());// 递归激活上级节点
                    ffResult.addAll(ffService.getNodeHandler(parentNode.getNodeType()).activateNode(parentNode, node.getNodeId(), candidateList, operationContext));
                }
                else {// 激活流程
                    if (!ffService.loadProc(node.getProcId()).getProcStatus().equals(FfService.PROC_STATUS_ACTIVE)) {
                        ffProcService.updateProcStatus(node.getProcId(), FfService.PROC_STATUS_ACTIVE);
                        ffResult.addActivateProc(ffService.loadProc(node.getProcId()));
                    }
                }
            }
            else {// 下行激活
                String lastCompleteNodeIds = node.getLastCompleteNodeIds();
                if (StringUtils.isNotEmpty(lastCompleteNodeIds)) {
                    ffNodeService.updateNodeLastCompleteNodeIds(node.getNodeId(), null);
                    List<Node> lastCompleteNodeList = ffService.createNodeQuery().setNodeIdList(Arrays.asList(lastCompleteNodeIds.split(","))).queryForObjectList();
                    for (Node lastCompleteNode : lastCompleteNodeList) {
                        ffResult.addAll(ffService.getNodeHandler(lastCompleteNode.getNodeType()).activateNode(lastCompleteNode, null, candidateList, operationContext));
                    }
                }
            }
        }

        return ffResult;
    }
}