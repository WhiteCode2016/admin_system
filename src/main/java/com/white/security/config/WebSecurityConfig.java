package com.white.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring-Security配置信息
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 自定义登录
        httpSecurity
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/menu/**").permitAll()
                .antMatchers("/role/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/static/**").permitAll()//访问：/static/** 无需登录认证权限
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                //登陆 .antMatchers("/admin/**").hasAuthority("ADMIN") //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/admin/index")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .invalidateHttpSession(true);

        // in a frame because it set 'X-Frame-Options' to 'DENY'
        httpSecurity
                .headers()
                .frameOptions().disable();
        httpSecurity.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置userDetailService
//        auth.userDetailsService(userDetailsService);
        // 自定义认证方式
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        // 设置忽略的静态资源
        webSecurity
                .ignoring()
                .antMatchers("/static/**")
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger-resources", "/swagger-ui.html", "/webjars/**");
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        AuthenticationProvider authenticationProvider=new MyAuthenticationProvider();
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
