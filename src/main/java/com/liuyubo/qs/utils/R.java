package com.liuyubo.qs.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
    public R() {
        put("code", HttpStatus.OK);
        put("msg", "success");
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static R ok() {
        return new R();
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R error(HttpStatus httpStatus, String msg) {
        R r = new R();
        r.put("code", httpStatus);
        r.put("msg", msg);
        return r;
    }

    public static R error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, msg);
    }

    public static R error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

}
