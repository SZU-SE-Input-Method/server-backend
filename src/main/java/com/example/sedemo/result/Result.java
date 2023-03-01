package com.example.sedemo.result;

import lombok.Data;
@Data
public class Result<T> {
    private Integer code; // 200成功，400失败

    private String msg; //错误信息

    private T data; //数据

    private boolean ok;

    private Result(){}

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setOk(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> error() {
        Result<T> result = new Result<T>();
        result.setOk(false);
        result.setCode(ResultCode.ERROR);
        result.setMsg("失败");
        return result;
    }

    public Result ok(boolean ok){
        this.setOk(ok);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public Result data(T data){
        this.setData(data);
        return this;
    }

}
