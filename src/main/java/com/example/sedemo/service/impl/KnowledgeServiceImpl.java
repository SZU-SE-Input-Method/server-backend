package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.entity.Knowledge;
import com.example.sedemo.mapper.KnowledgeMapper;
import com.example.sedemo.service.IKnowledgeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.crypto.Data;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiuHonelie
 * @since 2023-03-01
 *
 */
@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements IKnowledgeService {

    @Override
    public void addKnowledge(Knowledge knowledge) {
        this.save(knowledge);
    }

    @Override
    public void updataKnowledge(Knowledge knowledge) {
        this.updataKnowledge(knowledge);
    }

    @Override
    public Page<Knowledge> knowledgepage(Integer pageNum, Integer pageSize, Long kid, String title, Data createTime,String text) {
        //初始化分页信息
        Page<Knowledge> knowledgePage = new Page<>(pageNum,pageSize);
        //构造查询条件
        LambdaQueryWrapper<Knowledge> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(title),Knowledge::getTitle,title);
        queryWrapper.like(StringUtils.hasText(text),Knowledge::getText,text);
        queryWrapper.like(createTime != null, Knowledge::getCreateTime,createTime);
        this.page(knowledgePage,queryWrapper);
        return knowledgePage;
    }

    @Override
    public void deleteKnoowledge(Long kid) {
        this.removeById(kid);
    }
}
