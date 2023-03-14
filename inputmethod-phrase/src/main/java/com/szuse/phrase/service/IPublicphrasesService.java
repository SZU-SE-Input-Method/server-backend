package com.szuse.phrase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szuse.phrase.entity.Publicphrases;
import com.szuse.phrase.result.Result;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuHonglie
 * @since 2023-03-02
 */
public interface IPublicphrasesService extends IService<Publicphrases> {

    //增加一条公共短语
    void addPublicPhrases(Publicphrases publicphrases);

    //修改公共短语
    void updatePublicPhrases(Publicphrases publicphrases);

    //查询公共短语 && 分页
    Result publicPhrasesPage(Integer pageNum, Integer pageSize, Long ppid, String content, LocalDateTime createTime);

    //删除短语
    void deletePublicPhrases(Long ppid);

    //根据ppid查询

    Result getPhraseById(Long ppid);
}
