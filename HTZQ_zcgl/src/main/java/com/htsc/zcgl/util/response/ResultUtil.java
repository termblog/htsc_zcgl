package com.htsc.zcgl.util.response;

/**
 * Created by shiliangdong on 2017/4/15.
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setData(object);

        return result;
    }

    public static Result success() {
        Result result = new Result();
        return result;
    }

    public static Result error(int code, String msg) {
        Result result = new Result(code, msg);
        return result;
    }

    public static Result error(ResultEnum re) {
        Result result = new Result(re.getCode(), re.getMsg());
        return result;
    }
}
