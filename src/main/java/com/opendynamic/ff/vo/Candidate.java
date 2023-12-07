package com.opendynamic.ff.vo;

import java.io.Serializable;

public class Candidate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String subProcPath;
    private String nodeCode;
    private String candidateExpression;

    public Candidate() {
        super();
    }

    public Candidate(String subProcPath, String nodeCode, String candidateExpression) {
        super();
        this.subProcPath = subProcPath;
        this.nodeCode = nodeCode;
        this.candidateExpression = candidateExpression;
    }

    public String getSubProcPath() {
        return subProcPath;
    }

    public void setSubProcPath(String subProcPath) {
        this.subProcPath = subProcPath;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getCandidateExpression() {
        return candidateExpression;
    }

    public void setCandidateExpression(String candidateExpression) {
        this.candidateExpression = candidateExpression;
    }
}