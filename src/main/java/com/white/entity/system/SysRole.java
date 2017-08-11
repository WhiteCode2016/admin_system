package com.white.entity.system;

import com.white.entity.common.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息
 * Created by White on 2017/7/18.
 */
public class SysRole extends DataEntity {

    // 角色名称
    private String roleName;
    // 角色是否可用
    private Boolean enabled;
    // 备注
    private String remarks;
    // 菜单列表
    private List<SysMenu> menus = new ArrayList<>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleName='" + roleName + '\'' +
                ", enabled=" + enabled +
                ", remarks='" + remarks + '\'' +
                ", menus=" + menus +
                '}';
    }
}

