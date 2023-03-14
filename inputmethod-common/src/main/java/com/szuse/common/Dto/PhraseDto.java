package com.szuse.common.Dto;

import com.example.sedemo.entity.Phrase;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author LiuYe
 * @version 1.0
 * @date 3/3/2023 下午5:39
 */
@Data
public class PhraseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pid;

    private String content;

    private String uid;

    private LocalDate createTime;

    public static PhraseDto phraseToDto(Phrase phrase, String name) {
        PhraseDto phraseDto = new PhraseDto();
        BeanUtils.copyProperties(phrase, phraseDto, "uid");
        phraseDto.uid = name;
        return phraseDto;
    }
}
