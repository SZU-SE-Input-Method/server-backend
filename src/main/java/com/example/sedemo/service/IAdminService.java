package com.example.sedemo.service;

import com.example.sedemo.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
public interface IAdminService extends IService<Admin> {
    boolean auth(HttpServletRequest request, HttpServletResponse response, Admin admin);
}
