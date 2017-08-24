package com.white.mapper;

import com.white.dao.CrudDao;
import com.white.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户DAO接口
 * Created by White on 2017/7/18.
 */
@Mapper
public interface SysUserMapper extends CrudDao<SysUser> {

    // 根据登录名获取用户
    SysUser getUserByUserName(String username);
    // 将userId和roleId添加到表中，建立用户与角色的对应关系
    void insertUserAndRole(@Param("userId") String userId, @Param("roleId") String roleId);
    // 根据userId删除（用户--角色表）中的全部信息
    void deleteRoleByUserId(@Param("userId") String userId);
}
