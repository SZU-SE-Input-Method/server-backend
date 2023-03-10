package com.example.sedemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.sedemo.Dto.UserDto;
import com.example.sedemo.entity.User;
import com.example.sedemo.result.ResultCode;
import com.example.sedemo.utils.TddUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final String prefix = "/user";

    @ParameterizedTest
    @DisplayName("新建用户成功测试")
    @CsvSource({"tddUser,tddUser,password,'男"})
    @Transactional
    void saveUserSuccessTest(String name,String username,String password,String gender) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName(name);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setGender(gender);
        log.info("测试参数:user={}", userDto);
        String requestBody = JSONObject.toJSONString(userDto);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(prefix)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.getBytes())
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS, jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("新建用户失败测试")
    @CsvSource({"tddUser, ,password,'男"})
    @Transactional
    void saveUserErrorTest(String name,String username,String password,String gender) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName(name);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setGender(gender);
        log.info("测试参数:user={}", userDto);
        String requestBody = JSONObject.toJSONString(userDto);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(prefix)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.getBytes())
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.ERROR, jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("更新用户成功测试")
    @CsvSource({"1, tddUser", "2, tddtest", "3, this is a test"})
    @Transactional
    void updateUserSuccessTest(Long uid, String name) throws Exception {
        User user = new User();
        user.setUid(uid);
        user.setName(name);
        log.info("测试参数:user={}", user);
        String requestBody = JSONObject.toJSONString(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(prefix)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.getBytes())
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS, jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("更新用户失败测试")
    @CsvSource({"0, tddUser"})
    @Transactional
    void updateUserErrorTest(Long uid, String name) throws Exception {
        User user = new User();
        user.setUid(uid);
        user.setName(name);
        log.info("测试参数:user={}", user);
        String requestBody = JSONObject.toJSONString(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(prefix)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.getBytes())
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.ERROR, jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("删除用户成功测试")
    @CsvSource({"3"})
    @Transactional
    void deleteUserSuccessTest(Integer uid) throws Exception {
        String url = prefix + "/" + uid;
        log.info("测试参数:uid={}", uid);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS, jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("删除用户失败测试")
    @CsvSource({"0"})
    @Transactional
    void deleteUserErrorTest(Integer uid) throws Exception {
        String url = prefix + "/" + uid;
        log.info("测试参数:uid={}", uid);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.ERROR, jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("用户查询测试")
    @CsvSource({"1", "2", "3"})
    void getByIdTest(Integer uid) throws Exception {
        String url = prefix + "/" + uid;
        log.info("测试参数:uid={}", uid);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS, jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("用户分页查询测试")
    @CsvSource({"1, 5", "2,2"})
    void pageTest(Integer pageNum, Integer pageSize) throws Exception {
        String url = prefix + "/page/" + pageNum + "/" + pageSize;
        log.info("测试参数:pageNum={},pageSize={}", pageNum, pageSize);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS, jsonResponse.get("code"))
        );
    }
}