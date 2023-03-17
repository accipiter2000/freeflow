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
     */
    public String getUserName(String userId);

    /**
     * 获取该用户的所有id。
     * 
     * @param userId
     *        在RBAC中，同一个用户可以拥有多个岗位，此时的userId通常为岗位人员ID，
     * @return 在RBAC中，返回该用户的所有岗位人员ID。
     */
    public List<String> getAllUserIdList(String userId);
}