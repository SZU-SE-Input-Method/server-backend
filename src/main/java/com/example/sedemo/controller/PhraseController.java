package com.example.sedemo.controller;


import com.example.sedemo.result.Result;
import com.example.sedemo.service.IPhraseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * <p>
 * 前端控制器
 * </p>
 * 私人短语模块
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/phrase/private")
@Slf4j
public class PhraseController {

    @Autowired
    IPhraseService phraseService;

    /***
     * @description 根据传入的短语id查询特定私人短语内容
     * @param pid 短语id
     * @author LiuYe
     * @date 1/3/2023 下午8:44
     */
    @GetMapping("/{pid}")
    public Result getPhraseById(@PathVariable Long pid) {
        if (pid == null) {
            return Result.error().msg("请使用短语id查询具体短语");
        }
        log.info("调用查询短语接口,接受数据:pid = {}", pid);
        return phraseService.getPhraseById(pid);
    }

    /***
     * @description 分页查询私人短语列表
     * @param pageNum 当前页码
     * @param pageSize 每页数量
     * @param content 短语内容
     * @param uid 用户id
     * @param createTime 创建时间
     * @author LiuYe
     * @date 1/3/2023 下午8:43
     */
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result page(
            @PathVariable Integer pageNum,
            @PathVariable Integer pageSize,
            String content,
            Long uid,
            LocalDate createTime) {
        if (pageNum == null || pageSize == null) {
            return Result.error().msg("当前页码或每页数量参数缺失");
        }
        log.info("调用分页查询用户列表接口");
        return phraseService.phrasePage(pageNum, pageSize, content, uid, createTime);
    }
}
