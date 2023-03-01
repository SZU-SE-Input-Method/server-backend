package com.example.sedemo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieService {

    void set(HttpServletRequest request, HttpServletResponse response, String token);

    String get(HttpServletRequest request);

    void delete(HttpServletRequest request, HttpServletResponse response);

}

