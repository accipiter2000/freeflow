package com.opendynamic.ff.vo;

import java.io.Serializable;

/**
 * FF用户。
 */
public class FfUser implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String id;// ID。
    protected String userId;// 用户ID。
    protected String userCode;// 用户编码。
    protected String userName;// 用户名称。
    protected String roleId;// 角色ID。
    protected String roleCode;// 角色编码。
    protected String roleName;// 角色名称。
    protected String orgId;// 机构ID。
    protected String orgCode;// 机构编码。
    protected String orgName;// 机构名称。

    /**
     * 获取ID。
     * 
     * @return ID。
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID。
     * 
     * @param id
     *        ID。
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户ID。
     * 
     * @return 用户ID。
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID。
     * 
     * @param userId
     *        用户ID。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户编码。
     * 
     * @return 用户编码。
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户编码。
     * 
     * @param userCode
     *        用户编码。
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取用户名称。
     * 
     * @return 用户名称。
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称。
     * 
     * @param userName
     *        用户名称。
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取角色ID。
     * 
     * @return 角色ID。
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID。
     * 
     * @param roleId
     *        角色ID。
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色编码。
     * 
     * @return 角色编码。
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色编码。
     * 
     * @param roleCode
     *        角色编码。
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取角色名称。
     * 
     * @return 角色名称。
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称。
     * 
     * @param roleName
     *        角色名称。
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取机构ID。
     * 
     * @return 机构ID。
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置机构ID。
     * 
     * @param orgId
     *        机构ID。
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取机构编码。
     * 
     * @return 机构编码。
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置机构编码。
     * 
     * @param orgCode
     *        机构编码。
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取机构名称。
     * 
     * @return 机构名称。
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置机构名称。
     * 
     * @param orgName
     *        机构名称。
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}