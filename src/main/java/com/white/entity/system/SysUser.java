package com.white.entity.system;

import com.white.entity.common.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by White on 2017/7/18.
 */
public class SysUser extends DataEntity {

    // 超级管理员ID
    public static final String ADMIN_USER_ID = "1";

    // 登录名
    private String username;
    // 密码
    private String password;
    // 头像
    private String icon;
    // 中文名
    private String userNameCn;
    // 英文名
    private String userNameEn;
    // 用户是否可用
    private Boolean enabled;
    // 备注
    private String remarks;
    // 角色列表
    private List<SysRole> roles = new ArrayList<>();
    // 菜单列表
    private List<SysMenu> menus = new ArrayList<>();

    public SysUser() {
        super();
    }

    public SysUser(String id) {
        super(id);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserNameCn() {
        return userNameCn;
    }

    public void setUserNameCn(String userNameCn) {
        this.userNameCn = userNameCn;
    }

    public String getUserNameEn() {
        return userNameEn;
    }

    public void setUserNameEn(String userNameEn) {
        this.userNameEn = userNameEn;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userNameCn='" + userNameCn + '\'' +
                ", userNameEn='" + userNameEn + '\'' +
                ", enabled=" + enabled +
                ", remarks='" + remarks + '\'' +
                ", roles=" + roles +
                ", menus=" + menus +
                '}';
    }
}
