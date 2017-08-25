package com.white.service;



import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.white.entity.system.SysFile;
import com.white.entity.system.SysMenu;
import com.white.entity.system.SysRole;
import com.white.entity.system.SysUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 系统管理，包括用户、角色、菜单.
 */
public interface SystemService {

    /** 根据登录名获取用户 */
    SysUser getUserByUserName(String username);
    /** 通过用户Id获取用户 */
    SysUser getUser(String id);
    /** 通过用户Id获取用户信息（包含角色信息）*/
    SysUser getUserAndRole(String id);
    /** 获取所有用户 */
    List<SysUser> getAllUsers();
    /** 按条件查询用户信息并分页 */
    List<SysUser> getUserListByCondition(SysUser sysUser);
    /** 添加用户 */
    void addUser(SysUser sysUser);
    /** 添加用户（更新用户表和用户--角色表）*/
    void addUserAndRole(SysUser sysUser);
    /** 添加用户（包含文件）*/
    void addUserIncludeFile(SysUser sysUser, MultipartFile file);
    /** 更新用户 */
    void updateUser(SysUser sysUser);
    /** 更新用户（更新用户表和用户--角色表） */
    void updateUserAndRole(SysUser sysUser);
    /** 删除用户 */
    void deleteUser(String id);

    // 通过userId获取用户的菜单列表
    List<SysMenu> getMenusByUserId(String userId);
    // 通过roleId获取用户的菜单列表
    List<SysMenu> getMenusByRoleId(String roleId);
    // 获取所有菜单
    List<SysMenu> getAllMenus();
    // 按条件查询用户信息并分页
    List<SysMenu> getMenuListByCondition(SysMenu sysMenu);
    // 单个删除菜单
    void deleteMenu(String id);
    // 添加菜单
    void addMenu(SysMenu sysMenu);
    // 编辑菜单
    void update(SysMenu sysMenu);
    // 通过Id获取菜单
    SysMenu getMenu(String id);

    /** 按条件查询角色信息并分页 */
    List<SysRole> getRoleListByCondition(SysRole sysRole);
    /** 通过Id获取角色 */
    SysRole getRole(String id);
    /** 通过Id获取角色（包含菜单列表）*/
    SysRole getRoleAndMenu(String id);
    /** 获取所有角色 */
    List<SysRole> getAllRoles();
    /** 添加角色 */
    void addRole(SysRole sysRole);
    /** 编辑角色 */
    void updateRole(SysRole sysRole);
    /** 删除角色 */
    void deleteRole(String id);

    // 按条件查询文件信息并分页
    List<SysFile> getFileListByCondition(SysFile sysFile);
    //通过Id获取文件
    SysFile getFile(String id);
    // 删除文件
    void deleteFile(String id);


}
