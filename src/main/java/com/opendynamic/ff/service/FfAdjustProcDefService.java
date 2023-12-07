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
public interface FfAdjustProcDefService {
    /**
     * 按主键查询,返回单个对象。
     */
    public Map<String, Object> loadAdjustProcDef(String ADJUST_PROC_DEF_ID_);

    /**
     * 获取文件。
     */
    public InputStream loadProcDefDiagramFile(String ADJUST_PROC_DEF_ID_);

    /**
     * 通用查询，返回对象列表。
     */
    public List<Map<String, Object>> selectAdjustProcDef(String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST, Integer page, Integer limit);

    /**
     * 总数查询，在分页时与通用查询配套使用。
     */
    public int countAdjustProcDef(String ADJUST_PROC_DEF_ID_, List<String> ADJUST_PROC_DEF_ID_LIST, String PROC_DEF_ID_, List<String> PROC_DEF_ID_LIST);

    /**
     * 按主键列表查询，返回对象列表，按主键列表顺序排序。
     */
    public List<Map<String, Object>> selectAdjustProcDefByIdList(List<String> ADJUST_PROC_DEF_ID_LIST);

    /**
     * 新增对象。
     */
    public int insertAdjustProcDef(String ADJUST_PROC_DEF_ID_, String PROC_DEF_ID_, String PROC_DEF_MODEL_, InputStream PROC_DEF_DIAGRAM_FILE_, String PROC_DEF_DIAGRAM_FILE_NAME_, Integer PROC_DEF_DIAGRAM_FILE_LENGTH_, Integer PROC_DEF_DIAGRAM_WIDTH_, Integer PROC_DEF_DIAGRAM_HEIGHT_, Date CREATION_DATE_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_);

    /**
     * 修改对象。
     */
    public int updateAdjustProcDef(String ADJUST_PROC_DEF_ID_, String PROC_DEF_MODEL_, InputStream PROC_DEF_DIAGRAM_FILE_, String PROC_DEF_DIAGRAM_FILE_NAME_, Integer PROC_DEF_DIAGRAM_FILE_LENGTH_, Integer PROC_DEF_DIAGRAM_WIDTH_, Integer PROC_DEF_DIAGRAM_HEIGHT_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_);

    /**
     * 修改对象。
     */
    public int updateAdjustProcDefModel(String ADJUST_PROC_DEF_ID_, String PROC_DEF_MODEL_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_);

    /**
     * 删除对象。
     */
    public int deleteAdjustProcDef(String ADJUST_PROC_DEF_ID_, Date UPDATE_DATE_, String OPERATOR_ID_, String OPERATOR_NAME_);
}