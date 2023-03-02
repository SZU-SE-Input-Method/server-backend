package com.example.sedemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sedemo.entity.Knowledge;
import com.example.sedemo.result.Result;

import javax.xml.crypto.Data;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
public interface IKnowledgeService extends IService<Knowledge> {

    //新增一条知识
    void addKnowledge(Knowledge knowledge);

    //修改知识
    void updateKnowledge(Knowledge knowledge);

    //查询知识 && 分页
    Result knowledgePage(Integer pageNum, Integer pageSize, Long kid, String title, Data createTime, String text);

    //删除知识
    void deleteKnowledge(Long kid);
}
