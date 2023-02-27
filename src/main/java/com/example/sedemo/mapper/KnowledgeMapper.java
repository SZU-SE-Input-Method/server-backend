package com.example.sedemo.mapper;

import com.example.sedemo.entity.Knowledge;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KnowledgeMapper {
    public Knowledge selectById(Integer id);
}
