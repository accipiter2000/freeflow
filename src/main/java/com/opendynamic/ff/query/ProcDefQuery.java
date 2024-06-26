package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.opendynamic.ff.vo.ProcDef;

@Service
public class ProcDefQuery {
    private List<ProcDef> procDefList;

    private String procDefId;
    private List<String> procDefIdList;
    private String procDefCode;
    private List<String> procDefCodeList;
    private String procDefName;
    private List<String> procDefNameList;
    private String procDefCat;
    private List<String> procDefCatList;
    private String extAttr1;
    private List<String> extAttr1List;
    private String extAttr2;
    private List<String> extAttr2List;
    private String extAttr3;
    private List<String> extAttr3List;
    private String extAttr4;
    private List<String> extAttr4List;
    private String extAttr5;
    private List<String> extAttr5List;
    private String extAttr6;
    private List<String> extAttr6List;
    private String extAttr7;
    private List<String> extAttr7List;
    private String extAttr8;
    private List<String> extAttr8List;
    private Integer version;
    private List<Integer> versionList;
    private String procDefStatus;
    private List<String> procDefStatusList;
    private boolean allVersion;
    private Integer page;
    private Integer limit;

    public ProcDefQuery() {
        super();
    }

    public ProcDefQuery setProcDefList(List<ProcDef> procDefList) {
        this.procDefList = procDefList;
        return this;
    }

    public ProcDefQuery setProcDefId(String procDefId) {
        this.procDefId = procDefId;
        return this;
    }

    public ProcDefQuery setProcDefIdList(List<String> procDefIdList) {
        this.procDefIdList = procDefIdList;
        return this;
    }

    public ProcDefQuery setProcDefCode(String procDefCode) {
        this.procDefCode = procDefCode;
        return this;
    }

    public ProcDefQuery setProcDefCodeList(List<String> procDefCodeList) {
        this.procDefCodeList = procDefCodeList;
        return this;
    }

    public ProcDefQuery setProcDefName(String procDefName) {
        this.procDefName = procDefName;
        return this;
    }

    public ProcDefQuery setProcDefNameList(List<String> procDefNameList) {
        this.procDefNameList = procDefNameList;
        return this;
    }

    public ProcDefQuery setProcDefCat(String procDefCat) {
        this.procDefCat = procDefCat;
        return this;
    }

    public ProcDefQuery setProcDefCatList(List<String> procDefCatList) {
        this.procDefCatList = procDefCatList;
        return this;
    }

    public ProcDefQuery setExtAttr1(String extAttr1) {
        this.extAttr1 = extAttr1;
        return this;
    }

    public ProcDefQuery setExtAttr1List(List<String> extAttr1List) {
        this.extAttr1List = extAttr1List;
        return this;
    }

    public ProcDefQuery setExtAttr2(String extAttr2) {
        this.extAttr2 = extAttr2;
        return this;
    }

    public ProcDefQuery setExtAttr2List(List<String> extAttr2List) {
        this.extAttr2List = extAttr2List;
        return this;
    }

    public ProcDefQuery setExtAttr3(String extAttr3) {
        this.extAttr3 = extAttr3;
        return this;
    }

    public ProcDefQuery setExtAttr3List(List<String> extAttr3List) {
        this.extAttr3List = extAttr3List;
        return this;
    }

    public ProcDefQuery setExtAttr4(String extAttr4) {
        this.extAttr4 = extAttr4;
        return this;
    }

    public ProcDefQuery setExtAttr4List(List<String> extAttr4List) {
        this.extAttr4List = extAttr4List;
        return this;
    }

    public ProcDefQuery setExtAttr5(String extAttr5) {
        this.extAttr5 = extAttr5;
        return this;
    }

    public ProcDefQuery setExtAttr5List(List<String> extAttr5List) {
        this.extAttr5List = extAttr5List;
        return this;
    }

    public ProcDefQuery setExtAttr6(String extAttr6) {
        this.extAttr6 = extAttr6;
        return this;
    }

    public ProcDefQuery setExtAttr6List(List<String> extAttr6List) {
        this.extAttr6List = extAttr6List;
        return this;
    }

    public ProcDefQuery setExtAttr7(String extAttr7) {
        this.extAttr7 = extAttr7;
        return this;
    }

    public ProcDefQuery setExtAttr7List(List<String> extAttr7List) {
        this.extAttr7List = extAttr7List;
        return this;
    }

    public ProcDefQuery setExtAttr8(String extAttr8) {
        this.extAttr8 = extAttr8;
        return this;
    }

    public ProcDefQuery setExtAttr8List(List<String> extAttr8List) {
        this.extAttr8List = extAttr8List;
        return this;
    }

    public ProcDefQuery setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public ProcDefQuery setVersionList(List<Integer> versionList) {
        this.versionList = versionList;
        return this;
    }

    public ProcDefQuery setProcDefStatus(String procDefStatus) {
        this.procDefStatus = procDefStatus;
        return this;
    }

    public ProcDefQuery setProcDefStatusList(List<String> procDefStatusList) {
        this.procDefStatusList = procDefStatusList;
        return this;
    }

    public ProcDefQuery setAllVersion(boolean allVersion) {
        this.allVersion = allVersion;
        return this;
    }

    public ProcDefQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    public ProcDefQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 查询对象列表。对象格式为实体Bean。
     * 
     * @return
     */
    public List<ProcDef> queryForObjectList() {
        List<ProcDef> subProcDefList = selectProcDef();

        if (page != null && limit != null && limit > 0) {// 分页
            int start = (page - 1) * limit;
            int end = page * limit;
            if (end > subProcDefList.size()) {
                end = subProcDefList.size();
            }
            subProcDefList = subProcDefList.subList(start, end);
        }

        return subProcDefList;
    }

    /**
     * 查询单个对象。对象格式为实体Bean。
     * 
     * @return
     */
    public ProcDef queryForObject() {
        List<ProcDef> subProcDefList = queryForObjectList();
        if (subProcDefList.size() == 1) {
            return subProcDefList.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * 查询总数。
     * 
     * @return
     */
    public int count() {
        return selectProcDef().size();
    }

    private List<ProcDef> selectProcDef() {
        if (procDefList == null) {
            throw new RuntimeException("errors.procDefListNotSet");
        }

        List<ProcDef> subProcDefList = new ArrayList<>();
        subProcDefList.addAll(procDefList);

        ProcDef procDef;
        if (StringUtils.isNotEmpty(procDefId)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefId.equals(procDef.getProcDefId())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(procDefCode)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefCode.equals(procDef.getProcDefCode())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(procDefName)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefName.equals(procDef.getProcDefName())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(procDefCat)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefCat.equals(procDef.getProcDefCat())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(extAttr1)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr1.equals(procDef.getExtAttr1())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(extAttr2)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr2.equals(procDef.getExtAttr2())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(extAttr3)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr3.equals(procDef.getExtAttr3())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(extAttr4)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr4.equals(procDef.getExtAttr4())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(extAttr5)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr5.equals(procDef.getExtAttr5())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(extAttr6)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr6.equals(procDef.getExtAttr6())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(extAttr7)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr7.equals(procDef.getExtAttr7())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(extAttr8)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr8.equals(procDef.getExtAttr8())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (version != null) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!version.equals(procDef.getVersion())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (StringUtils.isNotEmpty(procDefStatus)) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefStatus.equals(procDef.getProcDefStatus())) {
                    subProcDefList.remove(procDef);
                }
            }
        }

        if (procDefIdList != null && procDefIdList.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefIdList.contains(procDef.getProcDefId())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (procDefCodeList != null && procDefCodeList.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefCodeList.contains(procDef.getProcDefCode())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (procDefNameList != null && procDefNameList.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefNameList.contains(procDef.getProcDefName())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (procDefCatList != null && procDefCatList.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefCatList.contains(procDef.getProcDefCat())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr1List != null && extAttr1List.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr1List.contains(procDef.getExtAttr1())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr2List != null && extAttr2List.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr2List.contains(procDef.getExtAttr2())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr3List != null && extAttr3List.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr3List.contains(procDef.getExtAttr3())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr4List != null && extAttr4List.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr4List.contains(procDef.getExtAttr4())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr5List != null && extAttr5List.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr5List.contains(procDef.getExtAttr5())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr6List != null && extAttr6List.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr6List.contains(procDef.getExtAttr6())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr7List != null && extAttr7List.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr7List.contains(procDef.getExtAttr7())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr8List != null && extAttr8List.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr8List.contains(procDef.getExtAttr8())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (versionList != null && versionList.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!versionList.contains(procDef.getVersion())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (procDefStatusList != null && procDefStatusList.size() > 0) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefStatusList.contains(procDef.getProcDefStatus())) {
                    subProcDefList.remove(procDef);
                }
            }
        }

        if (!allVersion) {
            Collections.reverse(subProcDefList);
            List<String> procDefCodeList = new ArrayList<>();
            String procDefCode;
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDefCode = subProcDefList.get(i).getProcDefCode();
                if (procDefCodeList.contains(procDefCode)) {
                    subProcDefList.remove(i);
                    continue;
                }
                else {
                    procDefCodeList.add(procDefCode);
                }
            }
        }

        return subProcDefList;
    }
}