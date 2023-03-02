package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.entity.Publicphrases;
import com.example.sedemo.mapper.PublicphrasesMapper;
import com.example.sedemo.result.Result;
import com.example.sedemo.service.IPublicphrasesService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiuHonelie
 * @since 2023-03-02
 */
@Service
public class PublicphrasesServiceImpl extends ServiceImpl<PublicphrasesMapper, Publicphrases> implements IPublicphrasesService {

    @Override
    public void addPublicPhrases(Publicphrases publicphrases) {
        this.save(publicphrases);
    }

    @Override
    public void updatePublicPhrases(Publicphrases publicphrases) {
        this.updateById(publicphrases);
    }

    @Override
    public Result publicPhrasesPage(Integer pageNum, Integer pageSize, Long ppid, String content, LocalDateTime createTime) {
        //初始化分页信息
        Page<Publicphrases> publicphrasesPage = new Page<>(pageNum,pageSize);
        //构造查询条件
        LambdaQueryWrapper<Publicphrases> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(content),Publicphrases::getContent,content);
        queryWrapper.like(createTime != null,Publicphrases::getCreateTime,createTime);
        this.page(publicphrasesPage,queryWrapper);
        return Result.success().data(publicphrasesPage).msg("分页查询短语列表成功");
    }

    @Override
    public void deletePublicPhrases(Long ppid) {
        this.removeById(ppid);
    }


    @Override
    public Result getPhraseById(Long ppid) {
        Publicphrases publicphrases = this.getById(ppid);
        return Result.success().data(publicphrases).msg("短语查询成功");
    }
}
