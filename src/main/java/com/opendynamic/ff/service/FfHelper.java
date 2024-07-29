package com.opendynamic.ff.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface FfHelper {
    /**
     * 获取用户名称。
     * 
     * @param userId
     *        用户ID。
     * @return 用户名称。
     */
    public String getUserName(String userId);

    /**
     * 获取该用户的所有ID。在工作流任务分配中，通常分配到岗位人员ID，而非人员ID。一个用户可以有多个岗位，因此有多个岗位人员ID。
     * 
     * @param userId
     *        用户ID。
     * @return 该用户的所有用户ID（岗位人员ID）。
     */
    public List<String> getAllUserIdList(String userId);
}