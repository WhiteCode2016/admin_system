package com.white.mapper;

import com.white.dao.CrudDao;
import com.white.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单DAO接口
 * Created by White on 2017/7/19.
 */
@Mapper
public interface SysMenuMapper extends CrudDao<SysMenu>{

    // 通过userId获取用户的菜单列表
    List<SysMenu> getMenusByUserId(String userId);

    // 获取所有的菜单列表
    List<SysMenu> getAllMenus();

    // 获取所有的父级菜单
    List<SysMenu> getParentMenu();

    // 通过parentId获取其子级菜单
    List<SysMenu> getChildMenuByParentId(String parentId);

    // 批量删除菜单
    void deleteMenu(String[] id);

}