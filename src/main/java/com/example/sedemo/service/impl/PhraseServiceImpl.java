package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.entity.Phrase;
import com.example.sedemo.mapper.PhraseMapper;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.IPhraseService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Service
public class PhraseServiceImpl extends ServiceImpl<PhraseMapper, Phrase> implements IPhraseService {
    @Override
    public Result getPhraseById(Long pid) {
        Phrase phrase = this.getById(pid);
        if (phrase == null) {
            return Result.error().msg("未查询到对应私人短语");
        }
        return Result.success().data(phrase).msg("查询具体私人短语成功");
    }

    @Override
    public Result phrasePage(Integer pageNum, Integer pageSize, String content, Long uid, LocalDate createTime) {
        Page<Phrase> phrasePage = new Page<>(pageNum, pageSize);
        //构造查询条件
        LambdaQueryWrapper<Phrase> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(content), Phrase::getContent, content);
        queryWrapper.like(createTime != null, Phrase::getCreateTime, createTime);
        queryWrapper.eq(uid != null, Phrase::getUid, uid);
        //调用mp接口
        this.page(phrasePage, queryWrapper);
        return Result.success().data(phrasePage).msg("分页查询私人短语成功");
    }
}
