package com.SpringBootBlog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域配置  从 8080 转到 8888端口
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }
}
