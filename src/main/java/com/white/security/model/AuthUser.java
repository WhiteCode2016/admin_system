package com.white.security.model;

import com.white.entity.system.SysMenu;
import com.white.security.common.AbstractAuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by White on 2017/7/18.
 */
public class AuthUser extends AbstractAuthUser {
    // 用户Id
    private String id;
    // 登录名
    private String username;
    // 密码
    private String password;
    // 中文名
    private String userNameCn;
    // 英文名
    private String userNameEn;
    // 用户是否可用
    private Boolean enabled;
    // 备注
    private String remarks;
    // 菜单列表
    private List<SysMenu> menus = new ArrayList<>();
    // 权限
    private Collection<SimpleGrantedAuthority> authorities;

    public AuthUser() {
    }

    public AuthUser(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getRemarks() {
        return remarks;
    }

    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
