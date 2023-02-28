package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.entity.Knowledge;
import com.example.sedemo.mapper.KnowledgeMapper;
import com.example.sedemo.service.IKnowledgeService;
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
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements IKnowledgeService {

}
