package com.example.sedemo.controller;

import com.alibaba.fastjson.JSONObject;
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

    @Test
    void saveUserTest() {
    }

    @Test
    void updateUserTest() {
    }

    @ParameterizedTest
    @DisplayName("用户查询测试")
    @CsvSource({"1", "2", "3"})
    void getByIdTest(Integer uid) throws Exception {
        String url = "/user/" + uid;
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
        String url = "/user/page/" + pageNum + "/" + pageSize;
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