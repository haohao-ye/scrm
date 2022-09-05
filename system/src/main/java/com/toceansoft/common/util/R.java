package com.toceansoft.common.util;

import lombok.Data;

/**
 * 响应的json
 */
@Data
public class R<T> {
    private int code;//状态码
    private  String msg;//提示信息
    private  T data;//响应的数据

    public static <T> R ok(int code,T data){
        R r=new R();
        r.code=code;
        r.data=data;
        return r;
    }

    public static R fail(int code,String msg){
        R r=new R();
        r.code=code;
        r.msg=msg;
        return r;
    }
}
