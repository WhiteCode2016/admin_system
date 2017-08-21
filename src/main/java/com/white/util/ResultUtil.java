package com.white.util;

import com.white.web.exception.JsonResult;

/**
 * Created by White on 2017/8/2.
 */
public class ResultUtil {

    public static JsonResult success(Object object) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setMessage("成功");
        jsonResult.setData(object);
        return jsonResult;
    }

    public static JsonResult success() {
        return success(null);
    }

    public static JsonResult error(Integer code, String msg) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(code);
        jsonResult.setMessage(msg);
        return jsonResult;
    }
}
