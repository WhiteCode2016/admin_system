package com.white.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 文件上传下载配置
 */
@Configuration
public class UploadConfig {
//    @Bean
//    public MultipartConfigElement getMultipartConfig() {
//        MultipartConfigFactory config = new MultipartConfigFactory() ;
//        // 设置上传文件的单个大小限制
//        config.setMaxFileSize("10MB");
//        // 设置总的上传的大小限制
//        config.setMaxRequestSize("100MB");
//        // 设置临时保存目录
//        // config.setLocation("/");
//        return config.createMultipartConfig() ;
//    }
}
