package com.white.security;

import com.white.entity.system.SysUser;
import com.white.security.model.AuthUser;
import com.white.security.model.AuthUserFactory;
import com.white.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 获取用户的用户名、密码、权限等等信息
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private SystemService iSystemService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser sysUser = iSystemService.getUserByUserName(username);
        if (sysUser == null) {
//            throw new UsernameNotFoundException(String.format("'%s' 用户名不存在", username));
            return null;
        } else {
            return AuthUserFactory.create(sysUser);
        }
    }
}
