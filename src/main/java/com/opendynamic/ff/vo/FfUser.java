package com.opendynamic.ff.vo;

import java.io.Serializable;

/**
 * FF用户。
 */
public class FfUser implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String userId;// 用户ID。
    protected String userCode;// 用户编码。
    protected String userName;// 用户名称。
    protected String roleId;// 角色ID。
    protected String roleCode;// 角色编码。
    protected String roleName;// 角色名称。
    protected String orgId;// 机构ID。
    protected String orgCode;// 机构编码。
    protected String orgName;// 机构名称。

    public FfUser() {
        super();
    }

    public FfUser(String userId, String userName) {
        super();

        this.userId = userId;
        this.userName = userName;
    }

    public FfUser(String userId, String userCode, String userName, String roleId, String roleCode, String roleName, String orgId, String orgCode, String orgName) {
        super();

        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.roleId = roleId;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orgName = orgName;
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
    public FfUser setUserId(String userId) {
        this.userId = userId;
        return this;
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
    public FfUser setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
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
    public FfUser setUserName(String userName) {
        this.userName = userName;
        return this;
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
    public FfUser setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
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
    public FfUser setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
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
    public FfUser setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
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
    public FfUser setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
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
    public FfUser setOrgCode(String orgCode) {
        this.orgCode = orgCode;
        return this;
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
    public FfUser setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    @Override
    public boolean equals(Object ffUser) {
        if (ffUser != null && ffUser instanceof FfUser && ((FfUser) ffUser).getUserId().equals(this.userId)) {
            return true;
        }
        return false;
    }
}