package com.white.mapper;

import com.white.dao.CrudDao;
import com.white.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单DAO接口
 */
@Mapper
public interface SysMenuMapper extends CrudDao<SysMenu>{

    // 通过userId获取用户的菜单列表
    List<SysMenu> getMenusByUserId(String userId);

    // 通过roleId获取用户的菜单列表
    List<SysMenu> getMenusByRoleId(String roleId);

    // 获取所有的菜单列表
    List<SysMenu> getAllMenus();

}