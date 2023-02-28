package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sedemo.entity.User;
import com.example.sedemo.mapper.UserMapper;
import com.example.sedemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public void saveUser(User user) {
        //对密码进行MD5加密
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password);
        //生成账号和数据库id
        user.setUsername(user.getName());
        user.setUid(1L);
        this.save(user);
    }

    @Override
    public void updateUser(User user) {
        //若填写密码则更新密码
        if(StringUtils.hasText(user.getPassword())) {
            //对密码进行MD5加密
            String password = user.getPassword();
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setPassword(password);
        }

        this.updateById(user);
    }

    @Override
    public Page<User> userPage(Integer pageNum, Integer pageSize, String name, String username, Boolean gender, String phone, String email, Date createTime) {
        //初始化分页信息
        Page<User> userPage = new Page<>(pageNum, pageSize);
        //构造查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name), User::getName, name);
        queryWrapper.like(StringUtils.hasText(username), User::getUsername, username);
        queryWrapper.like(StringUtils.hasText(phone), User::getPhone, phone);
        queryWrapper.like(StringUtils.hasText(email), User::getEmail, email);
        queryWrapper.like(createTime != null, User::getCreateTime, createTime);
        queryWrapper.eq(gender != null, User::getGender, gender);
        this.page(userPage, queryWrapper);

        return userPage;
    }
}
