package com.swarawan.khansapos.vo;

public class ErrorVO {

    public String message;
    public int code;

    public ErrorVO(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
