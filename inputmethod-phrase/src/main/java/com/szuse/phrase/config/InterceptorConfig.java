package com.szuse.phrase.config;//package com.example.sedemo.config;
//
//import com.example.sedemo.interceptor.AuthenticateInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
////登陆验证拦截器配置类
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authenticateInterceptor())
//                .addPathPatterns("/**")
//                .addPathPatterns("/**/user/**");
//    }
//
//    @Bean
//    public AuthenticateInterceptor authenticateInterceptor()  {
//        return new AuthenticateInterceptor();
//    }
//}