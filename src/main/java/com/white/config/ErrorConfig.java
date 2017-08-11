package com.white.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 自定义错误提示页面 400、404、500
 */
@Configuration
public class ErrorConfig {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
//                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
//                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
            }
        };
    }
}
