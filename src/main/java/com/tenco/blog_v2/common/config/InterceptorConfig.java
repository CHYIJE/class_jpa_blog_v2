package com.tenco.blog_v2.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig {

    @Bean
    public LoginIntercepter loginIntercepter() {
        return new LoginIntercepter();
    }


}
