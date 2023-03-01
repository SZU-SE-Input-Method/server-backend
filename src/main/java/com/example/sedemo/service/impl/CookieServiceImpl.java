package com.example.sedemo.service.impl;

import com.example.sedemo.service.CookieService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServiceImpl implements CookieService {

    @Override
    public void set(HttpServletRequest request, HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(5 * 60 * 60);                // 5小时过期
        cookie.setPath("/"); // 所有路径生效
        response.addCookie(cookie);
    }

    @Override
    public String get(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null)
            return null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token"))
                return cookie.getValue();
        }
        return null;
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);                         // 120分钟过期
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }

}
