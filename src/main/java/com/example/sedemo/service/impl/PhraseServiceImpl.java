package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.Dto.PhraseDto;
import com.example.sedemo.entity.Phrase;
import com.example.sedemo.entity.User;
import com.example.sedemo.mapper.PhraseMapper;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.IPhraseService;
import com.example.sedemo.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    IUserService userService;

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
        Page<PhraseDto> phraseDtoPage = new Page<>();
        BeanUtils.copyProperties(phrasePage, phraseDtoPage, "records");
        List<Phrase> records = phrasePage.getRecords();
        List<PhraseDto> dtoList = records.stream().map((item) -> {
            User user = userService.getById(item.getUid());
            return PhraseDto.phraseToDto(item,user.getName());
        }).collect(Collectors.toList());
        phraseDtoPage.setRecords(dtoList);
        return Result.success().data(phraseDtoPage).msg("分页查询私人短语成功");
    }
}
