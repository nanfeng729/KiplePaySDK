/**
 * KiplePay.com Inc. Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.kiple.api.exception;


/**
 * @classname: KiplePayApiException
 * @description: Exception
 * @author: seven.zhang
 * @date: 2020/6/3 15:07
 */
public class KiplePayApiException extends Exception {

    private static final long serialVersionUID = -238091758285157331L;

    private String errCode;
    private String errMsg;

    public KiplePayApiException() {
        super();
    }

    public KiplePayApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public KiplePayApiException(String message) {
        super(message);
    }

    public KiplePayApiException(Throwable cause) {
        super(cause);
    }

    public KiplePayApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}