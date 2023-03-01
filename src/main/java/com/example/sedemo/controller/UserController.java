package com.example.sedemo.controller;


import com.example.sedemo.Dto.UserDto;
import com.example.sedemo.entity.User;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    public UserController() {
    }

    @PostMapping
    public Result saveUser(@Validated @RequestBody UserDto userDTO) {
        log.info("调用查询用户接口,接受数据:{}", userDTO);
        userService.saveUser(User.dtoToUser(userDTO));
        return Result.success().msg("用户新增成功");
    }

    @PutMapping
    public Result updateUser(@Validated @RequestBody UserDto userDTO) {
        if (userDTO.getUid() == null) {
            return Result.error().msg("接口调用报错:用户id不可为空");
        }
        log.info("调用更新用户接口,接受数据:{}", userDTO);
        userService.updateUser(User.dtoToUser(userDTO));
        return Result.success().msg("用户信息修改成功");
    }

    @GetMapping("/{uid}")
    public Result getById(@PathVariable Long uid) {
        if (uid == null) {
            return Result.error().msg("查询失败,请使用用户id查询");
        }
        log.info("调用查询用户接口,接受数据:uid = {}", uid);
        return userService.getUserById(uid);
    }

    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result page(
            @PathVariable Integer pageNum,
            @PathVariable Integer pageSize,
            String name,
            String username,
            Boolean gender,
            String phone,
            String email,
            LocalDateTime createTime) {
        if (pageNum == null || pageSize == null) {
            return Result.error().msg("当前页码或每页数量参数缺失");
        }
        log.info("调用分页查询用户列表接口");
        return userService.userPage(pageNum, pageSize, name, username, gender, phone, email, createTime);
    }
}
