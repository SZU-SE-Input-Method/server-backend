package com.example.sedemo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sedemo.entity.User;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    public UserController() {
    }

    @PostMapping
    public Result<String> saveUser(@Validated @RequestBody User user) {
        userService.saveUser(user);

        return Result.success().msg("用户新增成功");
    }

    @PutMapping
    public Result<String> updateUser(@Validated @RequestBody User user) {
        if (user.getUid() == null) {
            return Result.error().msg("接口调用报错:用户id不可为空");
        }
        userService.updateUser(user);

        return Result.success().msg("用户信息修改成功");
    }

    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result<Page<User>> page(
            @PathVariable Integer pageNum,
            @PathVariable Integer pageSize,
            String name,
            String username,
            Boolean gender,
            String phone,
            String email,
            Date createTime) {
        Page<User> page = userService.userPage(pageNum, pageSize, name, username, gender, phone, email, createTime);
        return Result.success().data(page);
    }
}
