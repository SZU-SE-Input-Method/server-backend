package com.example.sedemo.service.impl;

import com.example.sedemo.entity.Phrase;
import com.example.sedemo.mapper.PhraseMapper;
import com.example.sedemo.service.IPhraseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
