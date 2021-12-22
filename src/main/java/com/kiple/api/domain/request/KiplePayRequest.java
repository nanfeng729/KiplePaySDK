package com.kiple.api.domain.request;


import com.kiple.api.domain.KiplePayObject;
import com.kiple.api.payment.KiplePayHashMap;

import java.util.Map;

/**
 * @projectname:KiplePaySDK
 * @author: seven.zhang
 * @date: 2020/6/3 15:15
 * @desc:
 */
public interface KiplePayRequest  {

    Map<String, String> getTextParams();

    KiplePayObject getBizModel();



    String getApiVersion();
    /**
     * callback norify
     *
     * @return
     */
    String getNotifyUrl();

    /**
     *  设置通知地址
     *
     * @param notifyUrl
     */
//    void setNotifyUrl(String notifyUrl);


    String getReturnUrl();


//    void setReturnUrl(String returnUrl);

    String getPostType();

    void addHeader(String key,String value);

    KiplePayHashMap getHeader();

    String getToken();
}
