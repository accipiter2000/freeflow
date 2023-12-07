package com.opendynamic.ff.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface FfProcDefService {
    /**
     * 按主键查询,返回单个对象。
     */
    public Map<String, Object> loadProcDef(String PROC_DEF_ID_);

    /**
     * 按编码查询,返回生效的最新版本的单个对象。
     */
    public Map<String, Object> loadProcDefByCode(String PROC_DEF_CODE_);

    /**
     * 获取文件。
     */
    public InputStream loadProcDefDiagramFile(String PROC_DEF_ID_);

    /**
     * 通用查询，返回对象列表。
     */
    public List<Map<String, Object>> selectProcDef(String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST, Integer page, Integer limit);

    /**
     * 总数查询，在分页时与通用查询配套使用。
     */
    public int countProcDef(String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, String PROC_DEF_CODE_, List<String> PROC_DEF_CODE_LIST, String PROC_DEF_NAME_, List<String> PROC_DEF_NAME_LIST, String PROC_DEF_CAT_, List<String> PROC_DEF_CAT_LIST, Integer VERSION_, List<Integer> VERSION_LIST, String PROC_DEF_STATUS_, List<String> PROC_DEF_STATUS_LIST);

    /**
     * 按主键列表查询，返回对象列表，按主键列表顺序排序。
     */
    public List<Map<String, Object>> selectProcDefByIdList(List<String> PROC_DEF_ID_LIST);

    /**
     * 新增对象。
     */
    public int insertProcDef(String PROC_DEF_ID_, String PROC_DEF_CODE_, String PROC_DEF_NAME_, String PROC_DEF_CAT_, String PROC_DEF_MODEL_, InputStream PROC_DEF_DIAGRAM_FILE_, String PROC_DEF_DIAGRAM_FILE_NAME_, Integer PROC_DEF_DIAGRAM_FILE_LENGTH_, Integer PROC_DEF_DIAGRAM_WIDTH_, Integer PROC_DEF_DIAGRAM_HEIGHT_, String MEMO_, String PROC_DEF_STATUS_, Date CREATION_DATE_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_);

    /**
     * 修改对象。
     */
    public int updateProcDefDiagramFile(String PROC_DEF_ID_, InputStream PROC_DEF_DIAGRAM_FILE_, String PROC_DEF_DIAGRAM_FILE_NAME_, Integer PROC_DEF_DIAGRAM_FILE_LENGTH_, Integer PROC_DEF_DIAGRAM_WIDTH_, Integer PROC_DEF_DIAGRAM_HEIGHT_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_);

    /**
     * 废弃对象。
     */
    public int disableProcDef(String PROC_DEF_ID_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_);

    /**
     * 恢复对象。
     */
    public int enableProcDef(String PROC_DEF_ID_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_);

    /**
     * 删除对象。
     */
    public int deleteProcDef(String PROC_DEF_ID_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_);
}