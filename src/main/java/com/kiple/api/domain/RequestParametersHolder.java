package com.kiple.api.domain;

import com.kiple.api.payment.KiplePayHashMap;


public class RequestParametersHolder {
    private KiplePayHashMap protocalMustParams;
    private KiplePayHashMap protocalOptParams;
    private KiplePayHashMap applicationParams;
    private String[] filesPath;
    private KiplePayHashMap headerMap;

    public String[] getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String[] filesPath) {
        this.filesPath = filesPath;
    }

    public KiplePayHashMap getProtocalMustParams() {
        return protocalMustParams;
    }

    public void setProtocalMustParams(KiplePayHashMap protocalMustParams) {
        this.protocalMustParams = protocalMustParams;
    }

    public KiplePayHashMap getProtocalOptParams() {
        return protocalOptParams;
    }

    public void setProtocalOptParams(KiplePayHashMap protocalOptParams) {
        this.protocalOptParams = protocalOptParams;
    }

    public KiplePayHashMap getApplicationParams() {
        return applicationParams;
    }

    public KiplePayHashMap getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(KiplePayHashMap headerMap) {
        this.headerMap = headerMap;
    }

    public void setApplicationParams(KiplePayHashMap applicationParams) {
        this.applicationParams = applicationParams;
    }
}
