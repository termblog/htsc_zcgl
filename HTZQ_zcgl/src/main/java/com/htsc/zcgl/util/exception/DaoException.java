package com.htsc.zcgl.util.exception;

import com.htsc.zcgl.util.response.ResultEnum;

/**
 * DAO层的运行时异常
 *
 * @author - shiliangdong
 */
public class DaoException extends BaseRuntimeException {
    public DaoException(ResultEnum resultEnum) {
        super(resultEnum.getCode(), resultEnum.getMsg());
    }

    public DaoException(ResultEnum resultEnum, Throwable cause) {
        super(resultEnum.getCode(), resultEnum.getMsg(), cause);
    }

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DaoException(int code, String msg) {
        super(code, msg);
    }

    public DaoException(int code, String msg, Throwable cause) {
        super(code, msg, cause);
    }
}

