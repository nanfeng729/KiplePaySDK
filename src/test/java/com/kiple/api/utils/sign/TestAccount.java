/**
 * KiplePay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.kiple.api.utils.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kiple.api.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @classname: TestAccount
 * @description: 描述
 * @author: seven.zhang
 * @date: 2020/6/4 15:48
 */
public class TestAccount {
    public static final String NOT_SET_KEY_APP_ID = "2019092567661029";

    public static class ProdLife {
        public static final String GATEWAY = "https://openfile.KiplePay.com/chat/multimedia.do";

        public static final String APP_ID = "2019051064521003";

        public static final String APP_PRIVATE_KEY = getPrivateKey("ProdLife");
    }

    public static class DevSM2 {
        public static final String GATEWAY = "http://openapi-ztt-1.gz00b.dev.KiplePay.net/gateway.do";

        public static final String ALIPAY_PUBLIC_KEY = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE7oZrr9paKyUq9zbFwoqNKJ0kr+w5/AszLFPm7"
                + "38GZksKQmIqBHlbuR6yXQ25tQlhL/L28f536re3UkLsDhVcdA==";

        public static final String APP_ID = "2021000146614327";

        public static final String AES_KEY = "Cn0fll653X1S1zom0/bL5A==";

        public static final String APP_PRIVATE_KEY = getPrivateKey("DevSM2");
    }

    /**
     * 从文件中读取私钥
     * <p>
     * 注意：实际开发过程中，请务必注意不要将私钥信息配置在源码中（比如配置为常量或储存在配置文件的某个字段中等），因为私钥的保密等级往往比源码高得多，将会增加私钥泄露的风险。推荐将私钥信息储存在专用的私钥文件中，
     * 将私钥文件通过安全的流程分发到服务器的安全储存区域上，仅供自己的应用运行时读取。
     * <p>
     * 此处为了单元测试执行的环境普适性，私钥文件配置在resources资源下，实际过程中请不要这样做。
     *
     * @param key 私钥对应的Key
     * @return 私钥字符串
     */
    private static String getPrivateKey(String key) {
        try {
            InputStream stream = TestAccount.class.getResourceAsStream("/fixture/privateKey.json");
            JSONObject jsonObject = JSON.parseObject(IOUtils.toString(stream, "utf-8"));
            return jsonObject.getString(key);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}