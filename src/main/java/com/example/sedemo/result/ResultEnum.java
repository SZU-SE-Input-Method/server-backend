package com.example.sedemo.result;

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
