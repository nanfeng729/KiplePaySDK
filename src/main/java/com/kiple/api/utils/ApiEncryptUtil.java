package com.kiple.api.utils;

import com.kiple.api.utils.sign.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectname:KiplePaySDK
 * @author: seven.zhang
 * @date: 2020/6/5 09:16
 * @desc:
 */
public class ApiEncryptUtil {
    private final static int KEY_SIZE = 2048;

    private final static String ALGORITHM="RSA";

    private static Map<String, String> keyMap = new HashMap<String, String>();


    public static void genKeyPair() throws Exception {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
        // 初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        String publicKeyString = encryptBASE64(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = encryptBASE64(privateKey.getEncoded());
        // 将公钥和私钥保存到Map
        //0表示公钥
        // Base64.encodeBase64String(publicKey.getEncoded());
        keyMap.put("publicKeyString", Base64.encodeBase64String(publicKey.getEncoded()));
        //1表示私钥
        keyMap.put("privateKeyString", Base64.encodeBase64String(privateKey.getEncoded()));
    }

    //编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
        //return (new BASE64Encoder()).encodeBuffer(key);
        return Base64.encodeBase64(key,true).toString();
    }

    //解码返回byte
    public static byte[] decryptBASE64(String key) throws Exception {
        //return (new BASE64Decoder()).decodeBuffer(key);
        return Base64.encodeBase64(key.getBytes(),true);
    }

    public static void main(String[] args) throws Exception {
        genKeyPair();
        System.out.println(keyMap.get("publicKeyString"));
        System.out.println(keyMap.get("privateKeyString"));
    }
}
