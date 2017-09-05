package com.white.mapper;

import com.white.dao.CrudDao;
import com.white.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色DAO接口
 * Created by White on 2017/7/19.
 */
@Mapper
public interface SysRoleMapper extends CrudDao<SysRole> {

    // 通过userId获取用户所拥有的角色
    List<SysRole> getRolesByUserId(String userId);

    // 通过userId获取用户所拥有的角色
    SysRole getRoleByUserId(String userId);

    // 获取所有的菜单列表
    List<SysRole> getAllRoles();

    // 通过roleId删除（角色-菜单表）中的信息
    void deleteByRoleId(String roleId);

    // 将roleId和menuId添加到表中，建立角色与菜单的对应关系
    void insertRoleAndMenu(@Param("roleId") String roleId, @Param("menuId") String menuId);
}
