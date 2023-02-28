package com.example.sedemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sedemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
public interface IUserService extends IService<User> {
    void saveUser(User user);

    void updateUser(User user);

    Page<User> userPage(Integer pageNum, Integer pageSize, String name, String username, Boolean gender, String phone, String email, Date createTime);

}
