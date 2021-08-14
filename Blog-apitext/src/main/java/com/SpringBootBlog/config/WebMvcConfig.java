package com.SpringBootBlog.config;

import com.SpringBootBlog.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域配置  从 8080 转到 8888端口
        registry.addMapping("/**").allowedOrigins("http://localhost:8081");
    }
    @Override
    public  void addInterceptors(InterceptorRegistry registry){
        //拦截test接口 ，遇见需要拦截的接口 再配置为 真正的 拦截器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/test");
    }
}
