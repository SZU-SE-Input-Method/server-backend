package com.example.sedemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sedemo.entity.Knowledge;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KnowledgeMapper extends BaseMapper<Knowledge> {
}
