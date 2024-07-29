package com.opendynamic.ff.vo;

import java.io.Serializable;

/**
 * 候选。
 */
public class Candidate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String subProcPath;// 子流程路径。
    private String nodeCode;// 节点编码。
    private String candidateExpression;// 候选表达式。

    public Candidate() {
        super();
    }

    public Candidate(String subProcPath, String nodeCode, String candidateExpression) {
        super();
        this.subProcPath = subProcPath;
        this.nodeCode = nodeCode;
        this.candidateExpression = candidateExpression;
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
     * 获取候选表达式。
     * 
     * @return 候选表达式。
     */
    public String getCandidateExpression() {
        return candidateExpression;
    }

    /**
     * 设置候选表达式。
     * 
     * @param candidateExpression
     *        候选表达式。
     */
    public void setCandidateExpression(String candidateExpression) {
        this.candidateExpression = candidateExpression;
    }
}