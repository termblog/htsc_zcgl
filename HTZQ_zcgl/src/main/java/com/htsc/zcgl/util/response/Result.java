package com.htsc.zcgl.util.response;

/**
 * Created by shiliangdong 2017/4/11.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Result {
    private Logger logger = LoggerFactory.getLogger(Result.class);

    private int status;

    private String message;

    private Object data;

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result() {
        this.status = 0;
        this.message = "成功";
    }

    /*
    public boolean isSucc() {
        return this.status.equals("0");
    }

    */
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
//		return JSONObject.toJSONString(this);

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
        return jsonStr;
    }
}

