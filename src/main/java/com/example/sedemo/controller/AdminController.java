package com.example.sedemo.controller;


import com.example.sedemo.entity.Admin;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    public AdminController() {
    }
    /***
    * @description 管理员登录认证
    * @author LiuYe
    * @date 1/3/2023 下午8:42
    */
    @PostMapping("/login")
    public Result authAdministrator(HttpServletRequest request, HttpServletResponse response,
                                    @Validated @RequestBody Admin admin) {
        log.info("调用管理员认证接口");
        if(adminService.auth(request,response,admin)) {
            return Result.success().msg("管理员登录成功");
        }
        return Result.error().msg("管理员登录失败");
    }
}
