package com.kiple.api.domain.response;

import java.io.Serializable;

/**
 * @projectname:KiplePaySDK
 * @author: seven.zhang
 * @date: 2020/6/3 15:09
 * @desc:
 */
public class KiplePayResponse<T> implements Serializable {

    private static final long serialVersionUID = 5014379068811962022L;

    private String msg;

    private Integer code;

    private Long    time;

    private T data;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
