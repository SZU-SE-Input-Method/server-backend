package com.example.sedemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.sedemo.entity.Knowledge;
import com.example.sedemo.result.ResultCode;
import com.example.sedemo.utils.TddUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
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

/**
 * @Author Honglie liu
 * @Date 2023/3/5 20:01
 * @Version 1.0
 */

@Slf4j
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KnowledgeControllerTest {

    @Autowired
    private MockMvc mockMvc;


    /**
     * @decription 知识增加测试
     * @param title
	 * @param text
     * @return void
     * @author Honglie liu
     * @createDate 2023/3/5
     */

    @ParameterizedTest
    @DisplayName("知识增加测试")
    @CsvSource({"测试1,这是知识增加测试用例1","测试2,这是知识增加测试用例2"})
    public void saveTest(String title,String text) throws Exception {
        String url = "/knowledge";
        Knowledge knowledge = new Knowledge();
        knowledge.setTitle(title);
        knowledge.setText(text);
        log.info("测试参数:{}",knowledge);
        String requestBody = JSONObject.toJSONString(knowledge);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.getBytes())
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}",jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS,jsonResponse.get("code"))
        );
    }


    /**
     * @decription 知识更新测试
     * @param kid
	 * @param title
	 * @param text
     * @return void
     * @author Honglie liu
     * @createDate 2023/3/5
     */

    @ParameterizedTest
    @DisplayName("知识更新测试")
    @CsvSource({"2,测试1,这是知识更新测试用例1","3,测试2,这是知识更新测试用例2"})
    public void updateTest(Long kid, String title,String text) throws Exception {
        String url = "/knowledge";
        Knowledge knowledge = new Knowledge();
        knowledge.setKid(kid);
        knowledge.setTitle(title);
        knowledge.setText(text);
        log.info("测试参数:{}",knowledge);
        String requestBody = JSONObject.toJSONString(knowledge);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.getBytes())
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}",jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS,jsonResponse.get("code"))
        );
    }

    /**
     * @decription 知识分页查询测试
     * @param pageNum
	 * @param pageSize
     * @return void
     * @author Honglie liu
     * @createDate 2023/3/5
     */

    @ParameterizedTest
    @DisplayName("知识分页查询测试")
    @CsvSource({"1, 5", "2, 2"})
    public void pageTest(Integer pageNum,Integer pageSize) throws Exception {
        String url = "/knowledge/page/" + pageNum + "/" + pageSize;
        log.info("测试参数:pageNum={},pageSize={}",pageNum,pageSize);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}",jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS,jsonResponse.get("code"))
        );
    }


    /**
     * @decription 知识删除测试
     * @param kid
     * @return void
     * @author Honglie liu
     * @createDate 2023/3/5
     */

    @ParameterizedTest
    @DisplayName("知识删除测试")
    @CsvSource({"3", "4", "1"})
    public void deleteTest(Long kid) throws Exception {
        String url = "/knowledge/" + kid;
        log.info("测试参数:kid={}",kid);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}",jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS,jsonResponse.get("code"))
        );
    }

    /**
     * @decription 知识详情查询测试
     * @param kid
     * @return void
     * @author Honglie liu
     * @createDate 2023/3/5
     */

    @ParameterizedTest
    @DisplayName("知识详情查询测试")
    @CsvSource({"3", "4", "1"})
    public void getByIdTest(Long kid) throws Exception {
        String url = "/knowledge/" + kid;
        log.info("测试参数:kid={}",kid);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}",jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS,jsonResponse.get("code"))
        );
    }



}
