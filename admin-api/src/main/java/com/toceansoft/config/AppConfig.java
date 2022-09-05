package com.toceansoft.config;

import com.toceansoft.web.common.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((new JwtInterceptor()))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/admin/login")
                .excludePathPatterns("/api/admin/login");
    }
}
