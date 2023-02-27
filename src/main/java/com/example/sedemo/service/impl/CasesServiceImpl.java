package com.example.sedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sedemo.entity.Cases;
import com.example.sedemo.mapper.CasesMapper;
import com.example.sedemo.service.ICasesService;
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
public class CasesServiceImpl extends ServiceImpl<CasesMapper, Cases> implements ICasesService {

}
