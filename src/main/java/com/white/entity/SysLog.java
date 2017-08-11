package com.white.entity;

import java.util.Date;

/**
 * 错误日志实体类
 * Created by White on 2017/7/24.
 */
public class SysLog {
    private String id;
    // 用户名
    private String userName;
    // 请求的url
    private String url;
    // 请求的ip
    private String ip;
    // http请求的方法
    private String method;
    // 请求方法的参数
    private String args;
    // 对应的类方法
    private String classMethod;
    // 异常信息
    private String exception;
    // 操作时间
    private Date operateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userName;
    }

    public void setUserId(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
