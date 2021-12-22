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
public class KiplePayApiRunTimeException extends RuntimeException {

    private static final long serialVersionUID = -238091758285157331L;

    private String errCode;
    private String errMsg;

    public KiplePayApiRunTimeException() {
        super();
    }

    public KiplePayApiRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public KiplePayApiRunTimeException(String message) {
        super(message);
    }

    public KiplePayApiRunTimeException(Throwable cause) {
        super(cause);
    }

    public KiplePayApiRunTimeException(String errCode, String errMsg) {
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