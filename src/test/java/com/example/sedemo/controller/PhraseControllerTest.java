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
class PhraseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String prefix = "/phrase/private";

    @ParameterizedTest
    @DisplayName("私人短语id查询成功测试")
    @CsvSource({"1", "2"})
    public void getPhraseByIdSuccessTest(Long pid) throws Exception {
        String url = prefix + "/" + pid;
        log.info("测试参数:{}", pid);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS, jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("私人短语id查询失败测试")
    @CsvSource({"0"})
    public void getPhraseByIdErrorTest(Long pid) throws Exception {
        String url = prefix + "/" + pid;
        log.info("测试参数:{}", pid);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.ERROR, jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("私人短语分页查询成功测试")
    @CsvSource({"1,2", "1,3"})
    void pageSuccessTest(Integer pageNum, Integer pageSize) throws Exception {
        String url = prefix + "/page/" + pageNum + "/" + pageSize;
        log.info("测试参数:pageNum={},pageSize={}", pageNum, pageSize);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS, jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("私人短语分页查询条件测试")
    @CsvSource({"1,5,'骗'"})
    void pageErrorTest(Integer pageNum, Integer pageSize, String content) throws Exception {
        String url = prefix + "/page/" + pageNum + "/" + pageSize + "?content=" + content;
        log.info("测试参数:pageNum={},pageSize={}", pageNum, pageSize);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}", jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS, jsonResponse.get("code"))
        );
    }
}
