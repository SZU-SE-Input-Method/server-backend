package com.example.sedemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Knowledge {
    private Integer kid;
    private String title;
    private String text;
    private Date createTime;
}
