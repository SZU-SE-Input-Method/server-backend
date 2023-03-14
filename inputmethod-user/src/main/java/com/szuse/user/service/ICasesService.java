package com.example.sedemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sedemo.entity.Cases;
import com.example.sedemo.result.Result;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
public interface ICasesService extends IService<Cases> {
    public Result addCases(Cases cases);

    public Result updateCase(Cases cases);

    public Result getCaseById(Long cid);

    public Result deleteCaseById(Long cid);

    Result casePage(Integer pageNum, Integer pageSize,
                    String title, String text, LocalDateTime caseTime, String caseLocation, String caseResource, LocalDateTime createTime);
}
