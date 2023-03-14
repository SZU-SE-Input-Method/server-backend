package com.szuse.phrase.result;

import lombok.Data;
@Data
public class Result {
    private Integer code; // 200成功，400失败

    private String msg; //错误信息

    private Object data; //数据

    private boolean ok;

    private Result(){}

    public static  Result success() {
        Result result = new Result();
        result.setOk(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMsg("成功");
        return result;
    }

    public static  Result error() {
        Result result = new Result();
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

    public Result data(Object data){
        this.setData(data);
        return this;
    }

}
