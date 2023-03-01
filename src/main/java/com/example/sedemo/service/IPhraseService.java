package com.example.sedemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sedemo.entity.Phrase;
import com.example.sedemo.result.Result;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
public interface IPhraseService extends IService<Phrase> {

    Result getPhraseById(Long pid);

    Result phrasePage(Integer pageNum, Integer pageSize, String content, Long uid, LocalDate createTime);

}
