package com.white.mapper;

import com.white.dao.CrudDao;
import com.white.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户DAO接口
 * Created by White on 2017/7/18.
 */
@Mapper
public interface SysUserMapper extends CrudDao<SysUser> {

    // 根据登录名获取用户
    SysUser getUserByUserName(String username);

    // 更新用户密码
    int updatePasswordByUserId(SysUser sysUser);

    // 删除用户角色关联数据
    int deleteUserRole(SysUser user);

    // 插入用户角色关联数据
    int insertUserRole(SysUser user);

    // 保存用户信息
    void updateInfo(SysUser user);
}
