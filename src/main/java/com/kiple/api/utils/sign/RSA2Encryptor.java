/**
 * KiplePay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.kiple.api.utils.sign;


import com.kiple.api.payment.KiplePayConstants;

/**
 * @classname: RSA2Encryptor
 * @description: 描述
 * @author: seven.zhang
 * @date: 2020/6/4 9:03
 */
/**
 * RSA2算法加密器 签名部分采用SHA256算法进行摘要计算，其余部分与RSA算法相同
 */
public class RSA2Encryptor extends RSAEncryptor {
    /**
     * RSA2最大加密明文大小(2048/8-11=244)
     */
    private static final int MAX_ENCRYPT_BLOCK_SIZE = 244;
    /**
     * RSA2最大解密密文大小(2048/8=256)
     */
    private static final int MAX_DECRYPT_BLOCK_SIZE = 256;

    @Override
    protected String getAsymmetricType() {
        return KiplePayConstants.SIGN_TYPE_RSA2;
    }

    @Override
    protected String getSignAlgorithm() {
        return KiplePayConstants.SIGN_SHA256RSA_ALGORITHMS;
    }

    @Override
    protected int getMaxDecryptBlockSize() {
        return MAX_DECRYPT_BLOCK_SIZE;
    }

    @Override
    protected int getMaxEncryptBlockSize() {
        return MAX_ENCRYPT_BLOCK_SIZE;
    }
}