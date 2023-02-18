package com.liuyubo.qs.utils;

import lombok.Data;

@Data
public class QsException extends RuntimeException{
    private String msg;
    private int code = 500;

    public QsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public QsException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public QsException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public QsException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
