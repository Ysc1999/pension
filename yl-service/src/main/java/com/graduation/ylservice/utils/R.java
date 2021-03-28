package com.graduation.ylservice.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.ibatis.type.TypeReference;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * 返回类型
 */
public class R extends HashMap<String,Object>{
    private static final Long serialVersionUID = 1L;

    private Integer code;
    private String message;

    public R (){
        put("code",0);
        put("msg","success");
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {
        return (Integer) this.get("code");
    }

    public R setData(Object data) {
        put("data", data);
        return this;
    }

    public <T> T getData(TypeReference<T> tTypeReference) {
        return this.getData("data", tTypeReference);
    }

    public <T> T getData(String key, TypeReference<T> tTypeReference) {
        Object data = this.get(key);
        String toJSONString = JSON.toJSONString(data);
        T t = JSON.parseObject(toJSONString, (Type) tTypeReference);
        return t;
    }
}
