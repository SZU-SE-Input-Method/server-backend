package com.example.sedemo.utils.result;

public enum ResultEnum {

    SUCCESS(200,"success"),
    FAILURE(400,"FAILURE");


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
