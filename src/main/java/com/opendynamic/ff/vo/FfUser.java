package com.opendynamic.ff.vo;

import java.io.Serializable;

public class FfUser implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String id;
    protected String userId;
    protected String userCode;
    protected String userName;
    protected String roleId;
    protected String roleCode;
    protected String roleName;
    protected String orgId;
    protected String orgCode;
    protected String orgName;

    /**
     * 获取ID。
     * 
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID。
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户ID。
     * 
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID。
     * 
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户编码。
     * 
     * @return
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户编码。
     * 
     * @param userCode
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取用户名称。
     * 
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称。
     * 
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取角色ID。
     * 
     * @return
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID。
     * 
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色编码。
     * 
     * @return
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色编码。
     * 
     * @param roleCode
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取角色名称。
     * 
     * @return
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称。
     * 
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取机构ID。
     * 
     * @return
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置机构ID。
     * 
     * @param orgId
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取机构编码。
     * 
     * @return
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置机构编码。
     * 
     * @param orgCode
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取机构名称。
     * 
     * @return
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置机构名称。
     * 
     * @param orgName
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}