package com.opendynamic.ff.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface FfDelegateService {
    /**
     * 按主键查询,返回单个对象。
     */
    public Map<String, Object> loadDelegate(String DELEGATE_ID_);

    /**
     * 通用查询，返回对象列表。
     */
    public List<Map<String, Object>> selectDelegate(String DELEGATE_ID_, String ASSIGNEE_, String ASSIGNEE_NAME_, String DELEGATOR_, String DELEGATOR_NAME_, Date FROM_START_DATE_, Date TO_START_DATE_, Date FROM_END_DATE_, Date TO_END_DATE_, Integer page, Integer limit);

    /**
     * 总数查询，在分页时与通用查询配套使用。
     */
    public int countDelegate(String DELEGATE_ID_, String ASSIGNEE_, String ASSIGNEE_NAME_, String DELEGATOR_, String DELEGATOR_NAME_, Date FROM_START_DATE_, Date TO_START_DATE_, Date FROM_END_DATE_, Date TO_END_DATE_);

    /**
     * 按主键列表查询，返回对象列表，按主键列表顺序排序。
     */
    public List<Map<String, Object>> selectDelegateByIdList(List<String> DELEGATE_ID_LIST);

    /**
     * 新增对象。
     */
    public int insertDelegate(String DELEGATE_ID_, String ASSIGNEE_, String ASSIGNEE_NAME_, String DELEGATOR_, String DELEGATOR_NAME_, Date START_DATE_, Date END_DATE_);

    /**
     * 修改对象。
     */
    public int updateDelegate(String DELEGATE_ID_, String ASSIGNEE_, String ASSIGNEE_NAME_, String DELEGATOR_, String DELEGATOR_NAME_, Date START_DATE_, Date END_DATE_);

    /**
     * 删除对象。
     */
    public int deleteDelegate(String DELEGATE_ID_);
}