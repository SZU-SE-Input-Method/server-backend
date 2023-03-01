package com.example.sedemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sedemo.entity.User;
import com.example.sedemo.result.Result;

import java.time.LocalDateTime;

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

    Result getUserById(Long uid);

    Result userPage(Integer pageNum, Integer pageSize, String name, String username, Boolean gender, String phone, String email, LocalDateTime createTime);

}
