package com.example.sedemo.service;

import com.example.sedemo.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
public interface IAdminService extends IService<Admin> {
    boolean auth(Admin admin);
}
