package com.example.sedemo.config;

import com.example.sedemo.interceptor.AuthenticateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置登录验证拦截器
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    //注册拦截器，配置拦截路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticateInterceptor())
                .excludePathPatterns("/admin/login"); //放行登录，其余拦截
    }

    @Bean
    public AuthenticateInterceptor authenticateInterceptor()  {
        return new AuthenticateInterceptor();
    }
}