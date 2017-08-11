package com.white.security.model;


import com.white.entity.system.SysMenu;
import com.white.entity.system.SysRole;
import com.white.entity.system.SysUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 封装登录用户的信息（包括基本信息、菜单、角色等等）
 */
public final class AuthUserFactory {

    // 获取用户的基本信息
    public static AuthUser create(SysUser user) {
        AuthUser authUser = new AuthUser(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setPassword(user.getPassword());
        authUser.setUserNameCn(user.getUserNameCn());
        authUser.setUserNameEn(user.getUserNameEn());
        authUser.setEnabled(user.getEnabled());
        authUser.setRemarks(user.getRemarks());
        authUser.setMenus(menuTree(user.getMenus()));
        // 获取用户的权限信息
        authUser.setAuthorities(mapToGrantedAuthorities(user.getRoles(), user.getMenus()));
        return authUser;
    }

    /**
     * 获取用户的权限列表
     * @param sysRoles 角色列表
     * @param sysMenus 菜单列表
     * @return 权限列表
     */
    private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(List<SysRole> sysRoles, List<SysMenu> sysMenus) {
        List<SimpleGrantedAuthority> authorities = sysRoles.stream()
                .filter(SysRole::getEnabled)
                .map(sysRole -> new SimpleGrantedAuthority(sysRole.getRoleName()))
                .collect(Collectors.toList());

       /* // 添加基于Permission的权限信息
        sysMenus.stream().filter(menu -> StringHelper.isNotBlank(menu.getPermission())).forEach(menu -> {
            // 添加基于Permission的权限信息
            for (String permission : StringHelper.split(menu.getPermission(), ",")) {
                authorities.add(new SimpleGrantedAuthority(permission));
            }
        });*/
        return authorities;
    }

    /**
     * 生成树形菜单
     * @param sysMenus
     * @return
     */
    private static List<SysMenu> menuTree(List<SysMenu> sysMenus) {
        List<SysMenu> menuParents = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : sysMenus) {
            if (sysMenu.getParentId() == null || sysMenu.getParentId().equals("")) {
                // 获取所有的父节点
                menuParents.add(sysMenu);
            }
        }
        for (SysMenu menuParent : menuParents) {
            for (SysMenu sysMenu : sysMenus) {
                if (menuParent.getId().equals(sysMenu.getParentId())) {
                    // 为父节点添加子节点
                    menuParent.addChild(sysMenu);
                }
            }
        }
        // 对菜单进行排序
        Collections.sort(menuParents, new Comparator<SysMenu>() {
            @Override
            public int compare(SysMenu o1, SysMenu o2) {
                return o1.getSort().compareTo(o2.getSort());
            }
        });
        return  menuParents;
    }
}
