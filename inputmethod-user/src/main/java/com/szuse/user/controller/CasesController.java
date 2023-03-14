package com.example.sedemo.controller;


import com.example.sedemo.entity.Cases;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.ICasesService;
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
 * @author LiuYe
 * @since 2023-02-28
 */
@Slf4j
@RestController
@RequestMapping("/cases")
public class CasesController {

    @Autowired
    private ICasesService iCasesService;
    @PostMapping
    public Result addCase(@Validated @RequestBody Cases cases){
        log.info("调用查询用户接口,接受数据:{}", cases);
        if (cases.getTitle() == null || cases.getText() == null){
            return Result.error().msg("必填字段为空");
        }
        return iCasesService.addCases(cases);
    }

    @PutMapping
    public Result updateCase(@RequestBody Cases cases) {
        log.info("调用更新案例接口,接受数据:{}", cases);
        if (cases.getCid() == null) {
            return Result.error().msg("接口调用报错:案例id不可为空");
        }
        return iCasesService.updateCase(cases);
    }

    @GetMapping("/{cid}")
    public Result getById(@PathVariable Long cid){
        if (cid == null){
            return Result.error().msg("案例id不能为空");
        }
        log.info("调用获取案例接口,接受数据:{}", cid);

        return iCasesService.getCaseById(cid);
    }

    @DeleteMapping("/{cid}")
    public Result deleteById(@PathVariable Long cid) {
        if (cid == null) {
            return Result.error().msg("案例id不能为空");
        }
        log.info("调用删除案例接口:接收信息{}",cid);
        return iCasesService.deleteCaseById(cid);
    }

    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result page(
            @PathVariable Integer pageNum,
            @PathVariable Integer pageSize,
            String title,
            String text,
            LocalDateTime caseTime,
            String caseLocation,
            String caseResource,
            LocalDateTime createTime){
        if (pageNum == null || pageSize == null) {
            return Result.error().msg("当前页码或每页数量参数缺失");
        }
        log.info("调用分页查询案例列表接口");
        return iCasesService.casePage(pageNum, pageSize, title, text, caseTime, caseLocation, caseResource, createTime);
    }
}
