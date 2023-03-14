package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.entity.Cases;
import com.example.sedemo.mapper.CasesMapper;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.ICasesService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Service
public class CasesServiceImpl extends ServiceImpl<CasesMapper, Cases> implements ICasesService {

    @Override
    public Result addCases(Cases cases) {
        save(cases);
        return Result.success().msg("上传成功");
    }

    @Override
    public Result updateCase(Cases cases) {
        //无此案例返回错误信息
        if( this.getById(cases.getCid()) == null ){
            return Result.error().msg("未查询到对应案例");
        }

        this.updateById(cases);
        return Result.success().msg("成功修改案例");
    }

    @Override
    public Result getCaseById(Long cid) {
        Cases cases = this.getById(cid);
        if (Objects.isNull(cases)){
            return Result.error().msg("未查询到对应案例");
        }
        return Result.success().msg("查到id为"+cid+"的案例信息").data(cases);
    }

    @Override
    public Result deleteCaseById(Long cid) {
        Cases cases = this.getById(cid);
        if(Objects.isNull(cases)){
            return Result.error().msg("未查询到对应案例");
        }
        this.removeById(cid);
        return Result.success().msg("删除案例成功");
    }

    @Override
    public Result casePage(Integer pageNum, Integer pageSize,
                    String title, String text, LocalDateTime caseTime, String caseLocation, String caseResource, LocalDateTime createTime){
        //初始化分页信息
        Page<Cases> casesPage = new Page<>(pageNum, pageSize);
        //构造查询条件
        LambdaQueryWrapper<Cases> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(title), Cases::getTitle, title);
        queryWrapper.like(StringUtils.hasText(text), Cases::getText, text);
        queryWrapper.like(caseTime != null, Cases::getCaseTime, caseTime);
        queryWrapper.like(StringUtils.hasText(caseLocation), Cases::getCaseLocation, caseLocation);
        queryWrapper.like(StringUtils.hasText(caseResource), Cases::getCaseResource, caseResource);
        queryWrapper.like(createTime != null, Cases::getCreateTime, createTime);

        //调用mp接口
        this.page(casesPage, queryWrapper);
        return Result.success().data(casesPage).msg("分页查询案例列表成功");
    }


}
