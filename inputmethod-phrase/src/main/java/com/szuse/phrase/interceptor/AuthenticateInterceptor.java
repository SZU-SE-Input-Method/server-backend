package com.szuse.phrase.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.szuse.phrase.result.Result;
import com.szuse.phrase.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//登录验证拦截器
@Slf4j
public class AuthenticateInterceptor implements HandlerInterceptor {
    //检查是否含有token
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        log.info("拦截器调用***************");
        Cookie[] cookies = request.getCookies();

        if(cookies == null)
            return needAuthenticateResponse(response);
        String token = null;
        //从cookie中获取token信息
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token"))
                token = cookie.getValue();
        }
        //token为空
        if(token == null || token.equals(""))
            return needAuthenticateResponse(response);

        String userToken;
        try{
            userToken = TokenUtils.verify(token.trim());
        } catch (Exception e){
            return needAuthenticateResponse(response);
        }
        if(userToken == null)                    // 检查token是否合法
            return needAuthenticateResponse(response);
        return true;
    }

    private boolean needAuthenticateResponse(HttpServletResponse response){
        //返回未登录信息
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        Result result = Result.error().msg("用户未登录");
        log.info("登录失败！");
        try {
            String json = new ObjectMapper().writeValueAsString(result);
            PrintWriter writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
