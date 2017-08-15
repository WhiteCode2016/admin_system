package com.white.service.impl;


import com.white.entity.system.SysMenu;
import com.white.entity.system.SysRole;
import com.white.entity.system.SysUser;
import com.white.enums.ResultEnum;
import com.white.mapper.SysMenuMapper;
import com.white.mapper.SysRoleMapper;
import com.white.mapper.SysUserMapper;
import com.white.service.SystemService;
import com.white.web.exception.DefaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统管理实现类,包括用户、角色、菜单.
 */
@Service
public class SystemServiceImpl implements SystemService {

    private static final Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);

    // 系统用户Mapper
    @Autowired
    private SysUserMapper sysUserMapper;
    // 系统角色Mapper
    @Autowired
    private SysRoleMapper sysRoleMapper;
    // 系统菜单Mapper
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    @Cacheable(value = "test")
    public SysUser getUserByUserName(String username) {
        SysUser sysUser = sysUserMapper.getUserByUserName(username);
        if (sysUser == null) {
            return null;
        }
        String userId = sysUser.getId();
        // 获取用户所拥有的角色列表
        sysUser.setRoles(sysRoleMapper.getRolesByUserId(userId));

        List<SysMenu> menuList = null;

        if (SysUser.ADMIN_USER_ID.equals(userId)) {
            // 超级管理员
            menuList = sysMenuMapper.getAllMenus();
        } else {
            menuList = sysMenuMapper.getMenusByUserId(userId);
        }
        // 获取用户所拥有的菜单列表
        sysUser.setMenus(menuList);
        return sysUser;
    }

    @Override
    public List<SysUser> getAllUsers() {
        return sysUserMapper.findAllList();
    }

    @Override
    public SysUser getUser(String id) {
        return sysUserMapper.get(id);
    }

    @Override
    public List<SysUser> getUserListByCondition(SysUser sysUser) {
        return sysUserMapper.findList(sysUser);
    }

    @Override
    public void addUser(SysUser sysUser) {
        sysUser.preInsert();
        sysUserMapper.insert(sysUser);
    }

    @Override
    public void updateUser(SysUser sysUser) {
        sysUserMapper.update(sysUser);
    }

    @Override
    public void deleteUser(String id) {
        sysUserMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> getAllMenus() {
        return sysMenuMapper.getAllMenus();
    }

    @Override
    public List<SysMenu> getMenuListByCondition(SysMenu sysMenu) {
        return sysMenuMapper.findList(sysMenu);
    }

    @Override
    public  List<SysMenu> getParentMenu() {
        return sysMenuMapper.getParentMenu();
    }

    @Override
    public List<SysMenu> getChildMenuByParentId(String parentId) {
        return sysMenuMapper.getChildMenuByParentId(parentId);
    }

    @Override
    public void deleteMenu(String[] id) {
        sysMenuMapper.deleteMenu(id);
    }

    @Override
    public void deleteMenu(String id) {
        sysMenuMapper.deleteById(id);
    }

    @Override
    public void addMenu(SysMenu sysMenu) {
        if (sysMenu.getMenuName() == null || sysMenu.getMenuName().equals("")) {
            throw new DefaultException(ResultEnum.MENU_NAME);
        }
        sysMenuMapper.insert(sysMenu);
    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    @Override
    public SysMenu getMenu(String id) {
        return sysMenuMapper.get(id);
    }

    @Override
    public List<SysRole> getRoleListByCondition(SysRole sysRole) {
        return sysRoleMapper.findList(sysRole);
    }

    @Override
    public SysRole getRole(String id) {
        return sysRoleMapper.get(id);
    }

    @Override
    public void addRole(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void updateRole(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void deleteRole(String id) {
        sysRoleMapper.deleteById(id);
    }

}
