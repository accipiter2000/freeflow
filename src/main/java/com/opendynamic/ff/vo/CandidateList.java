package com.opendynamic.ff.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.GsonBuilder;

/**
 * 候选列表。
 */
public class CandidateList extends ArrayList<Candidate> {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean add(Candidate candidate) {
        if (candidate == null) {
            throw new RuntimeException("errors.candidateIsNull");
        }

        boolean changed = false;
        // 查询是否已存在同名的候选
        Candidate existCandidate = getCandidate(candidate.getSubProcPath(), candidate.getNodeCode());
        if (existCandidate == null) { // 不存在直接添加
            return super.add(candidate);
        }
        else {
            // 已存在合并CandidateList
            List<FfUser> candidateAssigneeList = existCandidate.getCandidateAssigneeList();
            for (FfUser ffUser : candidate.getCandidateAssigneeList()) {
                if (!candidateAssigneeList.contains(ffUser)) {
                    candidateAssigneeList.add(ffUser);
                    changed = true;
                }
            }
            // 已存在合并candidateSubProcDefList
            List<String> candidateSubProcDefList = existCandidate.getCandidateSubProcDefList();
            for (String candidateSubProcDef : candidate.getCandidateSubProcDefList()) {
                if (!candidateSubProcDefList.contains(candidateSubProcDef)) {
                    candidateSubProcDefList.add(candidateSubProcDef);
                    changed = true;
                }
            }
        }

        return changed;
    }

    @Override
    public void add(int index, Candidate element) {
        throw new RuntimeException("errors.notSupport");
    }

    @Override
    public boolean addAll(Collection<? extends Candidate> c) {
        if (c == null) {
            throw new RuntimeException("errors.collectionIsNull");
        }
        boolean changed = false;
        for (Candidate candidate : c) {
            changed |= add(candidate);
        }
        return changed;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Candidate> c) {
        throw new RuntimeException("errors.notSupport");
    }

    /**
     * 转化成Json。
     * 
     * @return Json字符串。
     */
    public String toJson() {
        return new GsonBuilder().create().toJson(this);
    }

    /**
     * 根据节点编码获取候选。
     * 
     * @param nodeCode
     *        节点编码。
     * @return 候选。
     */
    public Candidate getCandidate(String nodeCode) {
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (StringUtils.equals(get(i).getNodeCode(), nodeCode)) {
                if (index != -1) {// 有多个相同nodeCode，返回null
                    return null;
                }
                index = i;
            }
        }

        if (index == -1) {// 没有相同nodeCode，返回null
            return null;
        }

        return this.get(index);
    }

    /**
     * 根据子流程路径和节点编码获取候选。
     * 
     * @param subProcPath
     *        子流程路径。
     * @param nodeCode
     *        节点编码。
     * @return 候选。
     */
    public Candidate getCandidate(String subProcPath, String nodeCode) {
        for (Candidate candidate : this) {
            if (StringUtils.isNotEmpty(nodeCode) && nodeCode.equals(candidate.getNodeCode())) {
                if ((StringUtils.isEmpty(subProcPath) && StringUtils.isEmpty(candidate.getSubProcPath())) || (StringUtils.isNotEmpty(subProcPath) && subProcPath.equals(candidate.getSubProcPath()))) {
                    return candidate;
                }
            }
        }

        return null;
    }

    /**
     * 根据子流程路径获取其下面的候选列表。
     * 
     * @param subProcPath
     *        子流程路径。
     * @return 候选列表。
     */
    public CandidateList getChildCandidate(String subProcPath) {
        CandidateList candidateList = new CandidateList();
        if (subProcPath == null) {
            subProcPath = "";
        }
        String subProcPathPrefix = subProcPath + ".";
        for (Candidate candidate : this) {
            if (StringUtils.isEmpty(subProcPath) || ((StringUtils.isNotEmpty(candidate.getSubProcPath()) && (candidate.getSubProcPath().equals(subProcPath) || candidate.getSubProcPath().startsWith(subProcPathPrefix))))) {
                candidateList.add(candidate);
            }
        }

        return candidateList;
    }
}