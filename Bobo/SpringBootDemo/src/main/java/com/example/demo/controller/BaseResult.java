package com.example.demo.controller;

import lombok.Data;

@Data
public class BaseResult {
    private int code; // 200: success. Else: failure.
    private String msg; //The message when fail.
    private Object data; //The return content when succeed.

    public BaseResult(int code, String msg, Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
}
