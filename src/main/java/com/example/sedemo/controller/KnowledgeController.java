package com.example.sedemo.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sedemo.entity.Knowledge;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.IKnowledgeService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LiuHonglie
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    private IKnowledgeService knowledgeService;

    public KnowledgeController() {
    }

    // 知识添加
    @PostMapping
    public Result saveKnowledge(@Validated @RequestBody Knowledge knowledge){
        knowledgeService.addKnowledge(knowledge);

        return Result.success().msg("知识添加成功");
    }

    // 知识更新
    @PutMapping
    public Result updateKnowledge(@Validated @RequestBody Knowledge knowledge){
        if (knowledge.getKid() == null){
            return Result.error().msg("接口调用报错:知识id不可用");
        }

        knowledgeService.updateKnowledge(knowledge);

        return Result.success().msg("知识更新成功");
    }

    // 知识查询 && 分页
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result page(
            @PathVariable Integer pageNum,
            @PathVariable Integer pageSize,
            Long kid,
            String title,
            Data createTime,
            String text){
        Result result = knowledgeService.knowledgePage(pageNum,pageSize,kid,title,createTime,text);
        return Result.success().data(result.getData()).msg("知识查询分页成功");
    }

    //删除知识
    @Delete("/delete/{kid}")
    public Result deleteKnowledge(@PathVariable Long kid){
        if (kid == null){
            Result.error().msg("删除知识失败,kid无效");
        }

        knowledgeService.deleteKnowledge(kid);
        return Result.success().msg("知识删除成功");
    }

}
