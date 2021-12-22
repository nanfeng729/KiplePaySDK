/**
 * KiplePay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.kiple.api.utils.sign;

import com.kiple.api.exception.KiplePayApiException;
import com.kiple.api.payment.KiplePayConstants;

/**
 * @classname: AsymmetricManager
 * @description: Asymmetric encryption algorithm management
 * @author: seven.zhang
 * @date: 2020/6/4 13:35
 */
public class AsymmetricManager {

    public static IAsymmetricEncryptor getByName(String type) throws KiplePayApiException {

        if (KiplePayConstants.SIGN_TYPE_RSA2.equals(type)) {
            return new RSA2Encryptor();
        }
        throw new KiplePayApiException("Invalid asymmetric encryption type:[\" + type + \"]，Available values are: RSA2。");
    }

}