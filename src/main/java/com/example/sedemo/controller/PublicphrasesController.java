package com.example.sedemo.controller;


import com.example.sedemo.entity.Publicphrases;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.IPublicphrasesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LiuHonglie
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/publicphrases")
@Slf4j
public class PublicphrasesController {

    @Autowired
    private IPublicphrasesService publicphrasesService;

    public PublicphrasesController() {
    }

    //短语增加
    @PostMapping
    public Result savePublicPhrases(@Validated @RequestBody Publicphrases publicphrases) {
        log.info("调用公共短语增加接口，接受数据:{}", publicphrases);
        publicphrasesService.addPublicPhrases(publicphrases);
        return Result.success().msg("公共短语添加成功");
    }

    //短语更新
    @PutMapping
    public Result updatePublicPhrases(@Validated @RequestBody Publicphrases publicphrases) {
        if (publicphrases.getPpid() == null) {
            return Result.error().msg("接口调用报错:公共短语id不可用");
        }
        log.info("调用更新公共短语接口，接收参数:{}", publicphrases);
        publicphrasesService.updatePublicPhrases(publicphrases);
        return Result.success().msg("公共短语更新成功");
    }

    //短语查询 && 分页
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result page(
            @PathVariable Integer pageNum,
            @PathVariable Integer pageSize,
            Long ppid,
            String content,
            LocalDateTime createTime){
        if(pageNum == null || pageSize == null){
            return Result.error().msg("当前页码或每页数量参数确实");
        }
        log.info("调用短语查询列表接口");
        return publicphrasesService.publicPhrasesPage(pageNum,pageSize,ppid,content,createTime);
    }

    //删除短语
    @DeleteMapping("/{ppid}")
    public Result deletePublicPhrases(@PathVariable Long ppid){
        if (ppid == null){
            return Result.error().msg("删除短语失败，ppid缺失");
        }
        log.info("调用短语删除接口，删除短语ppid:{}",ppid);
        publicphrasesService.deletePublicPhrases(ppid);
        return Result.success().msg("短语删除成功");
    }

    //根据ppid查询短语
    @GetMapping("/{ppid}")
    public Result getPhraseById(@PathVariable Long ppid) {
        if (ppid == null) {
            return Result.error().msg("请使用短语id查询具体短语");
        }
        log.info("调用查询短语接口,接受数据:pid = {}", ppid);
        return publicphrasesService.getPhraseById(ppid);
    }

}
