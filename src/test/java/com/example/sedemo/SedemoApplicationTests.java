package com.example.sedemo;

import com.example.sedemo.entity.Admin;
import com.example.sedemo.service.IAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class SedemoApplicationTests {

    @Autowired
    IAdminService adminService;

    @Test
    void contextLoads() {
    }


    @Test
    void registAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(DigestUtils.md5DigestAsHex("admin".getBytes()));
        adminService.save(admin);
    }
}
