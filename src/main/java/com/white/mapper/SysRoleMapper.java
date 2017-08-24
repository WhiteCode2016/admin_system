package com.white.mapper;

import com.white.dao.CrudDao;
import com.white.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

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

}
