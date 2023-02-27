package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.entity.Phrases;
import com.example.sedemo.mapper.PhrasesMapper;
import com.example.sedemo.service.IPhrasesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-02-27
 */
@Service
public class PhrasesServiceImpl extends ServiceImpl<PhrasesMapper, Phrases> implements IPhrasesService {

}
