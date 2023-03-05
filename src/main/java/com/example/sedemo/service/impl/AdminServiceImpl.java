package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.entity.Admin;
import com.example.sedemo.mapper.AdminMapper;
import com.example.sedemo.service.CookieService;
import com.example.sedemo.service.IAdminService;
import com.example.sedemo.utils.TokenUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private CookieService cookieService;
    @Override
    public boolean auth(HttpServletRequest request, HttpServletResponse response,Admin admin) {
        //Md5加密密码
        String password = admin.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //条件查询对应管理员账号
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, admin.getUsername());
        Admin info = this.getOne(queryWrapper);
        //若不存在该账号或密码不匹配则认证失败
        if(info == null) {
            return false;
        } else if(!info.getPassword().equals(password)) {
            return false;
        }

        //签发token
        String token = TokenUtils.sign(admin.getUsername());
        //判断是否已登录
        if(cookieService.get(request) != null && cookieService.get(request).equals(token)) {
            return false;
        }
        //保存cookie
        cookieService.set(request, response, token);
        return true;
    }
}
