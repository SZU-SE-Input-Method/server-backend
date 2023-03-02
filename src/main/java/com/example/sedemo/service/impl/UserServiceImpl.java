package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.Dto.UserDto;
import com.example.sedemo.entity.User;
import com.example.sedemo.mapper.UserMapper;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public Result saveUser(User user) {
        //对密码进行MD5加密
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password);
        //调用mp的insert接口
        this.save(user);
        return Result.success().msg("新建用户成功");
    }

    @Override
    public Result updateUser(User user) {
        //判断是否存在对应用户
        User userInfo = this.getById(user.getUid());
        if(Objects.isNull(userInfo)) {
            return Result.error().msg("未查询到对应用户");
        }

        //若填写密码则更新密码
        if (StringUtils.hasText(user.getPassword())) {
            //对密码进行MD5加密
            String password = user.getPassword();
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setPassword(password);
        }
        //调用mp的update接口
        this.updateById(user);
        return Result.success().msg("修改用户信息成功");
    }

    @Override
    public Result getUserById(Long uid) {
        //调用mp接口查询用户
        User user = this.getById(uid);
        //判断是否查询到数据
        if (user == null) {
            return Result.error().msg("查询失败,不存在对应用户");
        }
        return Result.success().data(UserDto.userToDTO(user)).msg("查询到id为:" + uid + "的用户信息");
    }

    @Override
    public Result userPage(Integer pageNum, Integer pageSize, String name, String username, Boolean gender, String phone, String email, LocalDateTime createTime) {
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
        //调用mp接口
        this.page(userPage, queryWrapper);
        //DTO转换
        Page<UserDto> userDTOPage = new Page<>();
        BeanUtils.copyProperties(userPage, userDTOPage, "records");
        List<User> records = userPage.getRecords();
        List<UserDto> list = records.stream().map(UserDto::userToDTO).collect(Collectors.toList());
        userDTOPage.setRecords(list);
        return Result.success().data(userDTOPage).msg("分页查询用户列表成功");
    }

    @Override
    public Result deleteById(Long uid) {
        User user = getById(uid);
        if(Objects.isNull(user)) {
            return Result.error().msg("未查询到对应用户");
        }
        this.removeById(uid);
        return Result.success().msg("删除用户成功");
    }
}
