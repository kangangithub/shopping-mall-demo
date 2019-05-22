package com.example.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Akang
 * @Date: 2019/5/22 16:54
 * @Description:
 */
@Data
public class Result implements Serializable {
    private String code;
    private String message;
    private boolean flag;
    private Object data;

    public static Result newOne() {
        return new Result();
    }

    public static Result success(String message) {
        Result result = Result.newOne();
        result.setCode("200");
        result.setFlag(true);
        result.setMessage(message);
        return result;
    }

    public static Result success(String message, Object data) {
        Result result = Result.success(message);
        result.setData(data);
        return result;
    }

    public static Result error(String code, String message) {
        Result result = Result.newOne();
        result.setCode(code);
        result.setFlag(false);
        result.setMessage(message);
        return result;
    }

    public static Result error(String code, String message, Object data) {
        Result result = Result.error(code, message);
        result.setData(data);
        return result;
    }

}
