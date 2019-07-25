package com.htsc.zcgl.util.exception;

import com.htsc.zcgl.util.response.ResultEnum;

/**
 * Service层的运行时异常
 *
 * @author - shiliangdong
 */

public class ServiceException extends BaseRuntimeException {
	public ServiceException(ResultEnum resultEnum){
		super(resultEnum.getCode(), resultEnum.getMsg());
	}

	public ServiceException(ResultEnum resultEnum, Throwable cause){
		super(resultEnum.getCode(), resultEnum.getMsg(), cause);
	}

	public ServiceException(String msg){
		super(msg);
	}

	public ServiceException(String msg, Throwable cause){
		super(msg, cause);
	}

	public ServiceException(int code, String msg){
		super(code, msg);
	}

	public ServiceException(int code, String msg, Throwable cause){
		super(code, msg, cause);
	}
}
