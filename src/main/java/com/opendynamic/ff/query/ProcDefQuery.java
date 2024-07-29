package com.opendynamic.ff.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.opendynamic.ff.vo.ProcDef;

/**
 * 流程定义查询类。
 */
@Service
public class ProcDefQuery {
    private List<ProcDef> procDefList;

    private String procDefId; // 流程定义ID。
    private List<String> procDefIdList; // 流程定义ID列表。
    private String procDefCode;// 流程定义编码。
    private List<String> procDefCodeList;// 流程定义编码列表。
    private String procDefName;// 流程定义名称。
    private List<String> procDefNameList;// 流程定义名称列表。
    private String procDefCat; // 流程定义分类。
    private List<String> procDefCatList; // 流程定义分类列表。
    private String extAttr1; // 扩展属性1。
    private List<String> extAttr1List; // 扩展属性1列表。
    private String extAttr2; // 扩展属性2。
    private List<String> extAttr2List; // 扩展属性2列表。
    private String extAttr3; // 扩展属性3。
    private List<String> extAttr3List; // 扩展属性3列表。
    private String extAttr4; // 扩展属性4。
    private List<String> extAttr4List; // 扩展属性4列表。
    private String extAttr5; // 扩展属性5。
    private List<String> extAttr5List; // 扩展属性5列表。
    private String extAttr6; // 扩展属性6。
    private List<String> extAttr6List; // 扩展属性6列表。
    private String extAttr7; // 扩展属性7。
    private List<String> extAttr7List; // 扩展属性7列表。
    private String extAttr8; // 扩展属性8。
    private List<String> extAttr8List; // 扩展属性8列表。
    private Integer version;// 版本。
    private List<Integer> versionList;// 版本列表。
    private String procDefStatus; // 流程定义状态。
    private List<String> procDefStatusList; // 流程定义状态列表。
    private boolean allVersion;// 获取所有版本，而非最新版本。默认为否。
    private Integer page;// 页。默认为1。
    private Integer limit;// 每页数据数量。默认为-1(全部)。

    public ProcDefQuery() {
        super();
    }

    public ProcDefQuery(List<ProcDef> procDefList) {
        super();
        this.procDefList = procDefList;
    }

    /**
     * 设置流程定义ID。
     * 
     * @param procDefId
     *        流程定义ID。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setProcDefId(String procDefId) {
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
    public ProcDefQuery setProcDefIdList(List<String> procDefIdList) {
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
    public ProcDefQuery setProcDefCode(String procDefCode) {
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
    public ProcDefQuery setProcDefCodeList(List<String> procDefCodeList) {
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
    public ProcDefQuery setProcDefName(String procDefName) {
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
    public ProcDefQuery setProcDefNameList(List<String> procDefNameList) {
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
    public ProcDefQuery setProcDefCat(String procDefCat) {
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
    public ProcDefQuery setProcDefCatList(List<String> procDefCatList) {
        this.procDefCatList = procDefCatList;
        return this;
    }

    /**
     * 设置扩展属性1。
     * 
     * @param extAttr1
     *        扩展属性1。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr1(String extAttr1) {
        this.extAttr1 = extAttr1;
        return this;
    }

    /**
     * 设置扩展属性1列表。
     * 
     * @param extAttr1List
     *        扩展属性1列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr1List(List<String> extAttr1List) {
        this.extAttr1List = extAttr1List;
        return this;
    }

    /**
     * 设置扩展属性2。
     * 
     * @param extAttr2
     *        扩展属性2。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr2(String extAttr2) {
        this.extAttr2 = extAttr2;
        return this;
    }

    /**
     * 设置扩展属性2列表。
     * 
     * @param extAttr2List
     *        扩展属性2列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr2List(List<String> extAttr2List) {
        this.extAttr2List = extAttr2List;
        return this;
    }

    /**
     * 设置扩展属性3。
     * 
     * @param extAttr3
     *        扩展属性3。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr3(String extAttr3) {
        this.extAttr3 = extAttr3;
        return this;
    }

    /**
     * 设置扩展属性3列表。
     * 
     * @param extAttr3List
     *        扩展属性3列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr3List(List<String> extAttr3List) {
        this.extAttr3List = extAttr3List;
        return this;
    }

    /**
     * 设置扩展属性4。
     * 
     * @param extAttr4
     *        扩展属性4。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr4(String extAttr4) {
        this.extAttr4 = extAttr4;
        return this;
    }

    /**
     * 设置扩展属性4列表。
     * 
     * @param extAttr4List
     *        扩展属性4列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr4List(List<String> extAttr4List) {
        this.extAttr4List = extAttr4List;
        return this;
    }

    /**
     * 设置扩展属性5。
     * 
     * @param extAttr5
     *        扩展属性5。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr5(String extAttr5) {
        this.extAttr5 = extAttr5;
        return this;
    }

    /**
     * 设置扩展属性5列表。
     * 
     * @param extAttr5List
     *        扩展属性5列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr5List(List<String> extAttr5List) {
        this.extAttr5List = extAttr5List;
        return this;
    }

    /**
     * 设置扩展属性6。
     * 
     * @param extAttr6
     *        扩展属性6。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr6(String extAttr6) {
        this.extAttr6 = extAttr6;
        return this;
    }

    /**
     * 设置扩展属性6列表。
     * 
     * @param extAttr6List
     *        扩展属性6列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr6List(List<String> extAttr6List) {
        this.extAttr6List = extAttr6List;
        return this;
    }

    /**
     * 设置扩展属性7。
     * 
     * @param extAttr7
     *        扩展属性7。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr7(String extAttr7) {
        this.extAttr7 = extAttr7;
        return this;
    }

    /**
     * 设置扩展属性7列表。
     * 
     * @param extAttr7List
     *        扩展属性7列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr7List(List<String> extAttr7List) {
        this.extAttr7List = extAttr7List;
        return this;
    }

    /**
     * 设置扩展属性8。
     * 
     * @param extAttr8
     *        扩展属性8。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr8(String extAttr8) {
        this.extAttr8 = extAttr8;
        return this;
    }

    /**
     * 设置扩展属性8列表。
     * 
     * @param extAttr8List
     *        扩展属性8列表。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setExtAttr8List(List<String> extAttr8List) {
        this.extAttr8List = extAttr8List;
        return this;
    }

    /**
     * 设置版本。
     * 
     * @param version
     *        版本。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setVersion(Integer version) {
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
    public ProcDefQuery setVersionList(List<Integer> versionList) {
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
    public ProcDefQuery setProcDefStatus(String procDefStatus) {
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
    public ProcDefQuery setProcDefStatusList(List<String> procDefStatusList) {
        this.procDefStatusList = procDefStatusList;
        return this;
    }

    /**
     * 设置获取所有版本，而非最新版本。默认为否。
     * 
     * @param allVersion
     *        获取所有版本，而非最新版本。默认为否。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setAllVersion(boolean allVersion) {
        this.allVersion = allVersion;
        return this;
    }

    /**
     * 设置页。默认为1。
     * 
     * @param page
     *        页。默认为1。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 设置每页数据数量。默认为-1(全部)。
     * 
     * @param limit
     *        每页数据数量。默认为-1(全部)。
     * @return 当前查询实例，支持链式调用。
     */
    public ProcDefQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * 查询对象列表。数据格式为实体Bean。
     * 
     * @return Bean类型数据列表。
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
     * 查询单个对象。数据格式为实体Bean。
     * 
     * @return 单个Bean类型数据。
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
     * @return 总数。
     */
    public int count() {
        return selectProcDef().size();
    }

    private List<ProcDef> selectProcDef() {
        if (procDefList == null) {
            throw new RuntimeException("errors.procDefListNotSet");
        }

        List<ProcDef> subProcDefList = new ArrayList<>(procDefList);

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

        if (procDefIdList != null && !procDefIdList.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefIdList.contains(procDef.getProcDefId())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (procDefCodeList != null && !procDefCodeList.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefCodeList.contains(procDef.getProcDefCode())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (procDefNameList != null && !procDefNameList.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefNameList.contains(procDef.getProcDefName())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (procDefCatList != null && !procDefCatList.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!procDefCatList.contains(procDef.getProcDefCat())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr1List != null && !extAttr1List.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr1List.contains(procDef.getExtAttr1())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr2List != null && !extAttr2List.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr2List.contains(procDef.getExtAttr2())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr3List != null && !extAttr3List.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr3List.contains(procDef.getExtAttr3())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr4List != null && !extAttr4List.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr4List.contains(procDef.getExtAttr4())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr5List != null && !extAttr5List.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr5List.contains(procDef.getExtAttr5())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr6List != null && !extAttr6List.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr6List.contains(procDef.getExtAttr6())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr7List != null && !extAttr7List.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr7List.contains(procDef.getExtAttr7())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (extAttr8List != null && !extAttr8List.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!extAttr8List.contains(procDef.getExtAttr8())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (versionList != null && !versionList.isEmpty()) {
            for (int i = subProcDefList.size() - 1; i >= 0; i--) {
                procDef = subProcDefList.get(i);
                if (!versionList.contains(procDef.getVersion())) {
                    subProcDefList.remove(procDef);
                }
            }
        }
        if (procDefStatusList != null && !procDefStatusList.isEmpty()) {
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
                }
                else {
                    procDefCodeList.add(procDefCode);
                }
            }
        }

        return subProcDefList;
    }
}