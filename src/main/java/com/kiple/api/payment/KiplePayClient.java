package com.kiple.api.payment;

import com.kiple.api.domain.request.KiplePayRequest;
import com.kiple.api.domain.response.KiplePayResponse;
import com.kiple.api.exception.KiplePayApiException;

import java.util.Map;

/**
 * @classname: KiplePayClient
 * @description: 描述
 * @author: sevenDay
 * @date: 2020/6/3 15:05
 */
public interface KiplePayClient {

    /**
     * @param request
     * @return
     * @throws KiplePayApiException
     */
    KiplePayResponse execute(KiplePayRequest request,String httpMethod) throws KiplePayApiException;

    String generatePaymentUrl(KiplePayRequest request) throws KiplePayApiException;

   /* *//**
     * @param <T>
     * @param request
     * @return
     * @throws KiplePayApiException
     *//*
    <T extends KiplePayResponse> T execute(KiplePayRequest request) throws KiplePayApiException;*/

}
