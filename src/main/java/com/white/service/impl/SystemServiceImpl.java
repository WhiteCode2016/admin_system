package com.white.service.impl;


import com.white.common.SysConstants;
import com.white.entity.system.SysFile;
import com.white.entity.system.SysMenu;
import com.white.entity.system.SysRole;
import com.white.entity.system.SysUser;
import com.white.enums.ResultEnum;
import com.white.mapper.SysFileMapper;
import com.white.mapper.SysMenuMapper;
import com.white.mapper.SysRoleMapper;
import com.white.mapper.SysUserMapper;
import com.white.service.SystemService;
import com.white.web.exception.DefaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

/**
 * 系统管理实现类,包括用户、角色、菜单.
 * 进行事务管理
 */
@Service
@Transactional
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
    // 系统文件Mapper
    @Autowired
    private SysFileMapper sysFileMapper;

    @Override
    @Cacheable(value = "test")
    public SysUser getUserByUserName(String username) {
        SysUser sysUser = sysUserMapper.getUserByUserName(username);
        if (sysUser == null) {
            return null;
        }
        String userId = sysUser.getId();
        // 获取用户所拥有的角色列表
        List<SysRole> roles = sysRoleMapper.getRolesByUserId(userId);
        sysUser.setRoles(roles);

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
    public SysUser getUser(String id) {
        return sysUserMapper.get(id);
    }

    @Override
    public SysUser getUserAndRole(String id) {
        SysRole role = sysRoleMapper.getRoleByUserId(id);
        SysUser sysUser = sysUserMapper.get(id);
        sysUser.setRole(role);
        return sysUser;
    }

    @Override
    public List<SysUser> getAllUsers() {
        return sysUserMapper.findAllList();
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
    public void addUserAndRole(SysUser sysUser) {
        sysUser.preInsert();
        // 保存用户信息到（用户表）
        sysUserMapper.insert(sysUser);
        String userId = sysUser.getId();
        String roleId = sysUser.getRole().getId();
        // 保存userId、roleId到（用户--角色表）
        sysUserMapper.insertUserAndRole(userId,roleId);
    }

    @Override
    @Transactional
    public void addUserIncludeFile(SysUser sysUser, MultipartFile file) {
        sysUser.preInsert();
        // 保存用户信息到用户表
        sysUserMapper.insert(sysUser);
        // 保存用户上传的文件到文件表
        String userId = sysUser.getId();
        unifileUpload(file,userId);
    }

    @Override
    public void updateUser(SysUser sysUser) {
        sysUserMapper.update(sysUser);
    }

    @Override
    public void updateUserAndRole(SysUser sysUser) {
        String userId = sysUser.getId();
        String roleId = sysUser.getRole().getId();
        // 根据userId删除（用户--角色表）中的全部信息
        sysUserMapper.deleteByUserId(userId);
        // 更新用户基本信息
        sysUserMapper.update(sysUser);
        // 将userId、roleId插入用户--角色表）
        sysUserMapper.insertUserAndRole(userId, roleId);
    }

    @Override
    public void updateUserAndRoleAndFile(SysUser sysUser, MultipartFile file) {
        // 更新用户的信息
        updateUserAndRole(sysUser);
        String userId = sysUser.getId();
        // 删除用户文件，插入新的文件
        sysFileMapper.deleteByUserId(userId);
        unifileUpload(file,userId);
    }

    @Override
    public void deleteUser(String id) {
        // 根据userId删除（用户--角色表）中的全部信息
        sysUserMapper.deleteByUserId(id);
        // 删除用户表信息
        sysUserMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> getMenusByUserId(String userId) {
        return sysMenuMapper.getMenusByUserId(userId);
    }

    @Override
    public List<SysMenu> getMenusByRoleId(String roleId) {
        return sysMenuMapper.getMenusByRoleId(roleId);
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
    public SysRole getRoleAndMenu(String id) {
        // 通过Id角色表中的角色信息
        SysRole sysRole = getRole(id);
        // 通过roleId获取该角色的菜单列表
        String roleId = sysRole.getId();
        List<SysMenu> sysMenus = sysMenuMapper.getMenusByRoleId(roleId);
        // 将菜单信息放入到角色信息中
        sysRole.setMenus(sysMenus);
        return sysRole;
    }

    @Override
    public List<SysRole> getAllRoles() {
        return sysRoleMapper.getAllRoles();
    }

    @Override
    public void addRole(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
        // 默认为每个角色添加一个首页菜单
        sysRoleMapper.insertRoleAndMenu(sysRole.getId(),"1");
    }

    @Override
    public void addRoleAndMenu(String roleId, List<String> menuIds) {
        // 清空该角色所拥有的菜单
        sysRoleMapper.deleteByRoleId(roleId);
        // 批量添加角色对应的菜单
        for (String menuId : menuIds) {
            sysRoleMapper.insertRoleAndMenu(roleId,menuId);
        }
    }

    @Override
    public void updateRole(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void deleteRole(String id) {
        // 清除角色-菜单表
        sysRoleMapper.deleteByRoleId(id);
        // 清除用户-角色表
        sysUserMapper.deleteByRoleId(id);
        // 清除角色表
        sysRoleMapper.deleteById(id);
    }

    @Override
    public List<SysFile> getFileListByCondition(SysFile sysFile) {
        return sysFileMapper.findList(sysFile);
    }

    @Override
    public SysFile getFile(String id) {
        return sysFileMapper.get(id);
    }

    @Override
    public void deleteFile(String id) {
        sysFileMapper.deleteById(id);
    }

    @Override
    public void updateFile(String userId, MultipartFile file) {
        sysFileMapper.deleteByUserId(userId);
        unifileUpload(file,userId);
    }


    //单文件上传
    public void unifileUpload(MultipartFile file, String userId) {
        saveFile(file,userId);
    }

    // 多文件上传
    public void morefileUpload(MultipartFile[] files, String userId) {
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0){
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++){
                MultipartFile file = files[i];
                //保存文件
                saveFile(file,userId);
            }
        }
    }

    // 上传文件到目标目录并保存到数据库
    public void saveFile(MultipartFile file,String userId) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 获取文件名
                String fileName = file.getOriginalFilename();
                // 获取文件的后缀名
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                // 文件大小
                long size = file.getSize();
                // 文件类型
                String contentType = file.getContentType();

                // 文件上传到的路径
                String filePath = SysConstants.UPLOAD_PATH;
                File targetFile = new File(filePath,fileName);
                // 检测是否存在目录
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                file.transferTo(targetFile);
                // 保存到数据库操作
                SysFile sysFile = new SysFile();
                sysFile.preInsert();
                sysFile.setOriginalFileName(fileName);
                sysFile.setSuffix(suffix);
                sysFile.setSize(size);
                sysFile.setContentType(contentType);
                sysFile.setUserId(userId);
                sysFileMapper.insert(sysFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
