package com.white.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * WebMVC 配置
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 错误页
        registry.addViewController("/400").setViewName("error/400");
        registry.addViewController("/404").setViewName("error/404");
        registry.addViewController("/500").setViewName("error/500");
        // 登录页
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
        // 注册页
        registry.addViewController("/register").setViewName("register");
        // 后台主页
        registry.addViewController("/admin/index").setViewName("admin/index");

        // 用户管理
        registry.addViewController("/user/add").setViewName("admin/user/user_add");
        registry.addViewController("/user/list").setViewName("admin/user/user_list");
        // 菜单管理
        registry.addViewController("/menu/add").setViewName("admin/menu/menu_add");
        registry.addViewController("/menu/list").setViewName("admin/menu/menu_list");
        // 角色管理
        registry.addViewController("/role/add").setViewName("admin/role/role_add");
        registry.addViewController("/role/list").setViewName("admin/role/role_list");
        // 文件管理
        registry.addViewController("/file/add").setViewName("admin/file/file_add");
        registry.addViewController("/file/list").setViewName("admin/file/file_list");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/");
    }
}
