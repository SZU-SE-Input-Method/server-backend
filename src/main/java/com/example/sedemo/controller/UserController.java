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
import java.util.Objects;

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
        return userService.saveUser(User.dtoToUser(userDTO));
    }

    @PutMapping
    public Result updateUser(@RequestBody UserDto userDTO) {
        log.info("调用更新用户接口,接受数据:{}", userDTO);
        if (userDTO.getUid() == null) {
            return Result.error().msg("接口调用报错:用户id不可为空");
        }
        return userService.updateUser(User.dtoToUser(userDTO));
    }

    @GetMapping("/{uid}")
    public Result getById(@PathVariable Long uid) {
        if (uid == null) {
            return Result.error().msg("查询失败,请使用用户id查询");
        }
        log.info("调用查询用户接口,接受数据:uid = {}", uid);
        return userService.getUserById(uid);
    }

    @DeleteMapping("/{uid}")
    public Result deleteById(@PathVariable Long uid) {
        if (Objects.isNull(uid)) {
            return Result.error().msg("请根据用户id删除用户");
        }
        log.info("调用删除用户接口:接收信息{}",uid);
        return userService.deleteById(uid);
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
