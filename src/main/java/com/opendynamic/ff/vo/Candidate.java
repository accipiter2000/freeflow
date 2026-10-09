package com.opendynamic.ff.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 候选。
 */
public class Candidate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String subProcPath;// 子流程路径。
    private String nodeCode;// 节点编码。
    private List<FfUser> candidateAssigneeList;// 候选人列表。
    private List<String> candidateSubProcDefList;// 候选子流程定义编码列表。

    public Candidate() {
        super();

        this.candidateAssigneeList = new ArrayList<FfUser>();
        this.candidateSubProcDefList = new ArrayList<String>();
    }

    public Candidate(String subProcPath, String nodeCode, List<FfUser> candidateAssigneeList, List<String> candidateSubProcDefList) {
        super();

        this.subProcPath = subProcPath;
        this.nodeCode = nodeCode;
        setCandidateAssigneeList(candidateAssigneeList);
        setCandidateSubProcDefList(candidateSubProcDefList);
    }

    /**
     * 获取子流程路径。
     * 
     * @return 子流程路径。
     */
    public String getSubProcPath() {
        return subProcPath;
    }

    /**
     * 设置子流程路径。
     * 
     * @param subProcPath
     *        子流程路径。
     */
    public void setSubProcPath(String subProcPath) {
        this.subProcPath = subProcPath;
    }

    /**
     * 获取节点编码。
     * 
     * @return 节点编码。
     */
    public String getNodeCode() {
        return nodeCode;
    }

    /**
     * 设置节点编码。
     * 
     * @param nodeCode
     *        节点编码。
     */
    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    /**
     * 获取候选人列表。
     * 
     * @return 候选人列表。
     */
    public List<FfUser> getCandidateAssigneeList() {
        return candidateAssigneeList;
    }

    /**
     * 设置候选人列表。
     * 
     * @param candidateAssigneeList
     *        候选人列表。
     */
    public void setCandidateAssigneeList(List<FfUser> candidateAssigneeList) {
        this.candidateAssigneeList = candidateAssigneeList;
        if (this.candidateAssigneeList == null) {
            this.candidateAssigneeList = new ArrayList<FfUser>();
        }
    }

    /**
     * 获取候选子流程定义编码列表。
     * 
     * @return 候选子流程定义编码列表。
     */
    public List<String> getCandidateSubProcDefList() {
        return candidateSubProcDefList;
    }

    /**
     * 设置候选子流程定义编码列表。
     * 
     * @param candidateSubProcDefList
     *        候选子流程定义编码列表。
     */
    public void setCandidateSubProcDefList(List<String> candidateSubProcDefList) {
        this.candidateSubProcDefList = candidateSubProcDefList;
        if (this.candidateSubProcDefList == null) {
            this.candidateSubProcDefList = new ArrayList<String>();
        }
    }
}