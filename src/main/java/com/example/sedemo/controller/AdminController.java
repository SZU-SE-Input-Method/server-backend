package com.example.sedemo.controller;


import com.example.sedemo.entity.Admin;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    public AdminController() {
    }

    @PostMapping("/login")
    public Result<Void> authAdministrator(@RequestBody Admin admin) {
        if(adminService.auth(admin)) {
            return Result.success().msg("管理员登录成功");
        }
        return Result.error().msg("管理员登录失败");
    }
}
