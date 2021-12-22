/**
 * KiplePay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.kiple.api.utils.sign;


import com.kiple.api.exception.KiplePayApiException;
import com.kiple.api.utils.StringUtils;

/**
 * @classname: BaseAsymmetricEncryptor
 * @description: 描述
 * @author: seven.zhang
 * @date: 2020/6/4 8:53
 */
public abstract class BaseAsymmetricEncryptor implements IAsymmetricEncryptor {

    //默认字符集编码。现在推荐使用UTF-8，之前默认是GBK
    private static String DEFAULT_CHARSET = "UTF-8";

    public String decrypt(String cipherTextBase64, String charset, String privateKey) throws KiplePayApiException {
        try {
            if (StringUtils.isEmpty(cipherTextBase64)) {
                throw new KiplePayApiException("Cipher text cannot be empty[密文不可为空]");
            }
            if (StringUtils.isEmpty(privateKey)) {
                throw new KiplePayApiException("Private key cannot be empty[私钥不可为空]");
            }
            if (StringUtils.isEmpty(charset)) {
                //default is utf-8
                charset = DEFAULT_CHARSET;
            }
            return doDecrypt(cipherTextBase64, charset, privateKey);

        } catch (Exception e) {
            //获取非对称类型
            String errorMessage = getAsymmetricType() + "Asymmetric decryption encountered an exception, please check whether the private key format is correct." +
                    "[非对称解密遭遇异常，请检查私钥格式是否正确。]" + e.getMessage() +
                    " cipherTextBase64=" + cipherTextBase64 + "，charset=" + charset + "，privateKeySize=" + privateKey.length();
            throw new KiplePayApiException(errorMessage,e);
        }

    }

    public String encrypt(String plainText, String charset, String publicKey) throws KiplePayApiException {
        try {
            if (StringUtils.isEmpty(plainText)) {
                throw new KiplePayApiException("密文不可为空");
            }
            if (StringUtils.isEmpty(publicKey)) {
                throw new KiplePayApiException("公钥不可为空");
            }
            if (StringUtils.isEmpty(charset)) {
                charset = DEFAULT_CHARSET;
            }
            return doEncrypt(plainText, charset, publicKey);
        } catch (Exception e) {

            String errorMessage = getAsymmetricType() + "An asymmetric decryption encountered an exception. Please check if the public key format is correct.[非对称解密遭遇异常，请检查公钥格式是否正确。]" + e.getMessage() +
                    " plainText=" + plainText + "，charset=" + charset + "，publicKey=" + publicKey;
            throw new KiplePayApiException(errorMessage,e);
        }

    }

    public String sign(String content, String charset, String privateKey) throws KiplePayApiException {
        try {
            if (StringUtils.isEmpty(content)) {
                throw new KiplePayApiException("The content to be signed cannot be empty[待签名内容不可为空]");
            }
            if (StringUtils.isEmpty(privateKey)) {
                throw new KiplePayApiException("private key cannot be empty[私钥不可为空]");
            }
            if (StringUtils.isEmpty(charset)) {
                charset = DEFAULT_CHARSET;
            }
            return doSign(content, charset, privateKey);
        } catch (Exception e) {

            String errorMessage = getAsymmetricType() + "sign has some wrong,please check the format of the sign content[签名遭遇异常，请检查私钥格式是否正确。]" + e.getMessage() +
                    " content=" + content + "，charset=" + charset + "，privateKeySize=" + privateKey.length();
            throw new KiplePayApiException(errorMessage,e);
        }

    }
    /**
     * @description:verify content
     * @auther: seven
     * @date: 2020/6/4 12:51
     * @param content : to be signed content
     * @param charset : encoding type
     * @param publicKey : public key
     * @param sign : the sign content
     * @return : boolean
     */
    public boolean verify(String content, String charset, String publicKey, String sign) throws KiplePayApiException {
        try {
            if (StringUtils.isEmpty(content)) {
                throw new KiplePayApiException("the content to be signed cannot be empty[待验签内容不可为空]");
            }
            if (StringUtils.isEmpty(publicKey)) {
                throw new KiplePayApiException("the publicKey cannot be empty[公钥不可为空]");
            }
            if (StringUtils.isEmpty(sign)) {
                throw new KiplePayApiException(" the Signature string cannot be empty [签名串不可为空]");
            }
            if (StringUtils.isEmpty(charset)) {
                charset = DEFAULT_CHARSET;
            }
            return doVerify(content, charset, publicKey, sign);
        } catch (Exception e) {

            String errorMessage = getAsymmetricType() + "sign has some wrong,please check the format of the sign content [验签遭遇异常，请检查公钥格式是否正确。]" + e.getMessage() +
                    " content=" + content + "，charset=" + charset + "，publicKey=" + publicKey;
            throw new KiplePayApiException(errorMessage,e);
        }
    }

    protected abstract String doDecrypt(String cipherTextBase64, String charset, String privateKey) throws Exception;

    protected abstract String doEncrypt(String plainText, String charset, String publicKey) throws Exception;

    protected abstract String doSign(String content, String charset, String privateKey) throws Exception;

    protected abstract boolean doVerify(String content, String charset, String publicKey, String sign) throws Exception;

    protected abstract String getAsymmetricType();

}