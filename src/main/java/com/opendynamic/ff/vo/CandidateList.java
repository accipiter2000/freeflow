package com.opendynamic.ff.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.GsonBuilder;

/**
 * 候选列表。
 */
public class CandidateList extends ArrayList<Candidate> {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean add(Candidate e) {
        // 查询是否已存在同名的候选
        Candidate existCandidate = getCandidate(e.getSubProcPath(), e.getNodeCode());
        if (existCandidate == null) { // 不存在直接添加
            return super.add(e);
        }
        else {// 已存在合并CandidateExpression
            Set<String> candidateSet = new HashSet<>();
            if (StringUtils.isNotEmpty(existCandidate.getCandidateExpression())) {
                candidateSet.addAll(Arrays.asList(existCandidate.getCandidateExpression().split(",")));
            }
            if (StringUtils.isNotEmpty(e.getCandidateExpression())) {
                candidateSet.addAll(Arrays.asList(e.getCandidateExpression().split(",")));
            }
            existCandidate.setCandidateExpression(StringUtils.join(candidateSet, ","));
            return true;
        }
    }

    @Override
    public void add(int index, Candidate element) {
        throw new RuntimeException("errors.notSupport");
    }

    @Override
    public boolean addAll(Collection<? extends Candidate> c) {
        for (Candidate candidate : c) {
            add(candidate);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Candidate> c) {
        throw new RuntimeException("errors.notSupport");
    }

    public boolean addAll(CandidateList candidateList) {
        for (Candidate candidate : candidateList) {
            this.add(candidate);
        }

        return true;
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
            if (get(i).getNodeCode().equals(nodeCode)) {
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
            if (candidate.getNodeCode().equals(nodeCode) && (StringUtils.isEmpty(subProcPath) && (StringUtils.isEmpty(candidate.getSubProcPath())) || (StringUtils.isNotEmpty(subProcPath) && subProcPath.equals(candidate.getSubProcPath())))) {
                return candidate;
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
            if (StringUtils.isEmpty(subProcPath) || (StringUtils.isNotEmpty(candidate.getSubProcPath()) && (candidate.getSubProcPath().equals(subProcPath) || candidate.getSubProcPath().startsWith(subProcPathPrefix)))) {
                candidateList.add(candidate);
            }
        }

        return candidateList;
    }
}