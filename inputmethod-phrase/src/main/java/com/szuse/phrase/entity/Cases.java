package com.szuse.phrase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cases")
public class Cases implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;

    @NotBlank(message = "案例标题不可为空")
    @TableField("title")
    private String title;

    @NotBlank(message = "案例文本不可为空")
    @TableField("text")
    private String text;

    @TableField("case_time")
    private LocalDateTime caseTime;

    @TableField("case_location")
    private String caseLocation;

    @TableField("case_resource")
    private String caseResource;

    @TableField("create_time")
    private LocalDateTime createTime;


}
