package com.example.sedemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.sedemo.entity.Publicphrases;
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
 * @Author Liuhong lie
 * @Date 2023/3/5 19:10
 * @Version 1.0
 */

@Slf4j
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PublicphrasesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * @decription 公共短语增加测试
     * @return         
     * @author
     * @createDate 2023/3/5 
     */

    @ParameterizedTest
    @DisplayName("公共短语增加测试")
    @CsvSource({"这是公共短语增加测试用例1","这是公共短语增加测试用例2"})
    public void saveTest(String publicphrasecontent) throws Exception {
        String url = "/publicphrases";
        Publicphrases publicphrases = new Publicphrases();
        publicphrases.setContent(publicphrasecontent);
        log.info("测试参数:{}",publicphrases);
        String requestBody = JSONObject.toJSONString(publicphrases);
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
     * @decription 公共短语更新测试
     * @param ppid
	 * @param publicphrasecontent
     * @return void
     * @author Honglie liu
     * @createDate 2023/3/5
     */

    @ParameterizedTest
    @DisplayName("公共短语更新测试")
    @CsvSource({"3,这是公共短语增加测试用例1","4,这是公共短语增加测试用例2"})
    public void updateTest(Long ppid,String publicphrasecontent) throws Exception {
        String url = "/publicphrases";
        Publicphrases publicphrases = new Publicphrases();
        publicphrases.setContent(publicphrasecontent);
        publicphrases.setPpid(ppid);
        log.info("测试参数:{}",publicphrases);
        String requestBody = JSONObject.toJSONString(publicphrases);
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
     * @decription 公共短语分页查询测试
     * @param pageNum
	 * @param pageSize
     * @return void
     * @author Honglie liu
     * @createDate 2023/3/5
     */

    @ParameterizedTest
    @DisplayName("公共短语分页查询测试")
    @CsvSource({"1, 5", "2, 2"})
    public void pageTest(Integer pageNum,Integer pageSize) throws Exception {
        String url = "/publicphrases/page/" + pageNum + "/" + pageSize;
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
     * @decription 公共短语删除测试
     * @param ppid
     * @return void
     * @author Honglie liu
     * @createDate 2023/3/5
     */

    @ParameterizedTest
    @DisplayName("公共短语删除测试")
    @CsvSource({"3", "4", "1"})
    public void deleteTest(Long ppid) throws Exception {
        String url = "/publicphrases/" + ppid;
        log.info("测试参数:ppid={}",ppid);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        log.info("返回结果:{}",jsonResponse.toJSONString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResultCode.SUCCESS,jsonResponse.get("code"))
        );
    }

    @ParameterizedTest
    @DisplayName("公共短语详情查询测试")
    @CsvSource({"3", "4", "1"})
    public void getByIdTest(Long ppid) throws Exception {
        String url = "/publicphrases/" + ppid;
        log.info("测试参数:ppid={}",ppid);
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
