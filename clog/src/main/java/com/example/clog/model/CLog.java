package com.example.clog.model;

import com.google.common.base.MoreObjects;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * @author hongbo.pan
 * @date 2020/11/2
 */
@Data
public class CLog implements Serializable {

    /**
     * 耗时
     */
    private Long expendTime;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 入参
     */
    private LinkedHashMap<String, Object> args;

    /**
     * 返回结果
     */
    private Object result;

    /**
     * 请求连接
     */
    private String url;

    /**
     * ip
     */
    private String ip;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("expendTime", expendTime)
                .add("className", className)
                .add("methodName", methodName)
                .add("args", args)
                .add("result", result)
                .add("url", url)
                .add("ip", ip)
                .toString();
    }
}
