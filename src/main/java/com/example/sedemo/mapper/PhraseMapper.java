package com.example.sedemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sedemo.entity.Phrase;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Mapper
public interface PhraseMapper extends BaseMapper<Phrase> {
}
