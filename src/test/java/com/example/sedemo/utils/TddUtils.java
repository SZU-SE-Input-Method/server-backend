package com.example.sedemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class TddUtils {

    public static String getToken(MockMvc mockMvc) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("account", "admin")
                .param("password", "admin")
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        return jsonResponse.getJSONObject("data").get("token").toString();
    }

    public static String getToken(MockMvc mockMvc, String account, String pasword) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("account", account)
                .param("password", pasword)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        JSONObject jsonResponse = TddUtils.getObject(result);
        return jsonResponse.getJSONObject("data").get("token").toString();
    }

    public static JSONObject getObject(MvcResult result) throws UnsupportedEncodingException {
        //获取响应数据，使用UTF-8解码，防止出现中文乱码
        String json = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //将json字符串转换为实体对象,获取属性用于断言
        return JSON.parseObject(json);
    }
}