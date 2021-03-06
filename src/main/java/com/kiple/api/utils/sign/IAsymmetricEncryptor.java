/**
 * KiplePay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.kiple.api.utils.sign;


import com.kiple.api.exception.KiplePayApiException;

/**
 * @classname: IAsymmetricEncryptor
 * @description: 描述
 * @author: seven.zhang
 * @date: 2020/6/4 8:50
 */
public interface IAsymmetricEncryptor {

    /**
     * 计算指定内容的签名
     * @param content 待签名的原文
     * @param charset 待签名的原文的字符集编码
     * @param privateKey 私钥字符串
     * @return 签名字符串
     */
    String sign(String content, String charset, String privateKey) throws KiplePayApiException;

    /**
     * 验证指定内容的签名是否正确
     * @param content 待校验的原文
     * @param charset 待校验的原文的字符集编码
     * @param publicKey 公钥字符串
     * @param sign 签名字符串
     * @return true：验证通过；false：验证不通过
     */
    boolean verify(String content, String charset, String publicKey, String sign) throws KiplePayApiException;

    /**
     * 对明文进行非对称加密
     * @param plainText 明文字符串
     * @param charset 明文的字符集编码
     * @param publicKey 公钥字符串
     * @return 密文的Base64编码字符串
     */
    String encrypt(String plainText, String charset, String publicKey) throws KiplePayApiException;

    /**
     * 对密文进行非对称解密
     * @param cipherTextBase64 密文Base64编码字符串
     * @param charset 明文的字符集编码
     * @param privateKey 私钥字符串
     * @return 明文
     */
    String decrypt(String cipherTextBase64, String charset, String privateKey) throws KiplePayApiException;

}