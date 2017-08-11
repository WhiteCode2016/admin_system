package com.white.web.exception;

import com.white.enums.ResultEnum;

/**
 * 自定义异常信息类
 * Created by White on 2017/8/2.
 */
public class DefaultException extends RuntimeException {
    private Integer code;

    public DefaultException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
