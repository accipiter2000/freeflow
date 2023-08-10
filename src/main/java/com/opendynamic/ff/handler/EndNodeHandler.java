package com.opendynamic.ff.handler;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfHelper;
import com.opendynamic.ff.service.FfNodeService;
import com.opendynamic.ff.service.FfProcService;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.service.FfTaskService;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.Node;
import com.opendynamic.ff.vo.NodeDef;
import com.opendynamic.ff.vo.Proc;
import com.opendynamic.ff.vo.Task;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EndNodeHandler implements NodeHandler {
    @Autowired
    private FfService ffService;
    @Autowired
    private FfProcService ffProcService;
    @Autowired
    private FfNodeService ffNodeService;
    @Autowired
    private FfTaskService ffTaskService;
    @Autowired
    private FfHelper ffHelper;

    @Override
    public String getNodeType() {
        return FfService.NODE_TYPE_END;
    }

    @Override
    public FfResult insertNodeByNodeDef(NodeDef nodeDef, Node branchNode, String previousNodeIds, String triggerOperation, String executor) {
        FfResult ffResult = new FfResult();

        // 终止该节点所属子流程或独立子流程内的所有节点。如该节点直属于主流程，则终止流程所有节点
        List<Node> nodeList = null;// 需要终止的节点
        Node subProc = ffService.createParentNodeQuery().setNodeId(branchNode.getNodeId()).setNodeTypeList(Arrays.asList(FfService.NODE_TYPE_SUB_PROC, FfService.NODE_TYPE_ISOLATE_SUB_PROC)).setRecursive(true).queryForObject();// 查询子流程
        // 计算子流程或独立子流程的分支节点
        Node subProcBranchNode = null;
        if (subProc != null) {
            List<Node> parentNodeList = ffService.createParentNodeQuery().setNodeId(branchNode.getNodeId()).setRecursive(true).setIncludeSelf(true).queryForObjectList();
            for (int i = 0; i < parentNodeList.size(); i++) {
                if (parentNodeList.get(i).getNodeId().equals(subProc.getNodeId())) {
                    subProcBranchNode = parentNodeList.get(i - 1);
                    break;
                }
            }
        }

        if (subProcBranchNode == null) {
            nodeList = ffService.createNodeQuery().setProcId(branchNode.getProcId()).queryForObjectList();// 流程所有节点
        }
        else {
            nodeList = ffService.createChildNodeQuery().setNodeId(subProcBranchNode.getNodeId()).setRecursive(true).setIncludeSelf(false).queryForObjectList();// 从子流程或独立子流程下的分支节点开始查找
        }

        // 终止任务
        List<Task> taskList = ffService.createTaskQuery().setNodeIdList(OdUtils.collectFromBean(nodeList, "nodeId", String.class)).setTaskStatus(FfService.TASK_STATUS_ACTIVE).queryForObjectList();
        for (Task task : taskList) {
            Date COMPLETE_DATE_ = new Date();
            ffTaskService.updateTaskStatus(task.getTaskId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.TASK_STATUS_TERMINATE);
            task.setTaskEndUser(executor);
            task.setTaskEndUserName(ffHelper.getUserName(executor));
            task.setTaskEndDate(COMPLETE_DATE_);
            task.setTaskStatus(FfService.TASK_STATUS_TERMINATE);
            ffResult.addTerminateTask(task);
        }
        // 终止节点
        for (Node node : nodeList) {
            if (node.getNodeStatus().equals(FfService.NODE_STATUS_ACTIVE)) {
                Date COMPLETE_DATE_ = new Date();
                ffNodeService.updateNodeStatus(node.getNodeId(), executor, ffHelper.getUserName(executor), COMPLETE_DATE_, FfService.NODE_STATUS_TERMINATE);
                node.setNodeEndUser(executor);
                node.setNodeEndUserName(ffHelper.getUserName(executor));
                node.setNodeEndDate(COMPLETE_DATE_);
                node.setNodeStatus(FfService.NODE_STATUS_TERMINATE);
                ffResult.addTerminateNode(node);
            }
        }

        if (subProcBranchNode != null) {// 终止子流程或独立子路程
            ffResult.addAll(ffService.getNodeHandler(subProcBranchNode.getNodeType()).completeNode(subProcBranchNode, null, triggerOperation, executor));
        }
        else {// 终止流程
            ffProcService.updateProcStatus(branchNode.getProcId(), executor, ffHelper.getUserName(executor), new Date(), FfService.PROC_STATUS_COMPLETE);
            Proc proc = ffService.loadProc(branchNode.getProcId());
            ffResult.addCompleteProc(proc);
        }

        return ffResult;
    }

    @Override
    public FfResult completeNode(Node node, String previousNodeIds, String triggerOperation, String executor) {
        return new FfResult();
    }

    @Override
    public FfResult rejectNode(Node node, String triggerOperation, String executor) {
        return new FfResult();
    }

    @Override
    public FfResult activateNode(Node node, String previousNodeIds, String triggerOperation, String executor) {
        return new FfResult();
    }
}