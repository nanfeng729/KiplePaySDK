package com.kiple.api.utils.sign;

import com.kiple.api.exception.KiplePayApiException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @projectname:KiplePaySDK
 * @author: seven.zhang
 * @date: 2020/6/4 14:00
 * @desc:
 */
public class EncryptTest {

    @Test
    public void getCorrectChiperText() throws KiplePayApiException {
        //given
        String chipertext =KiplePayEncrypt.encryptContent("test1234567", "AES", "aa4BtZ4tspm2wnXLb1ThQA==", "utf-8");
        KiplePayLogger.logBizDebug(chipertext);
        assertThat(chipertext, containsString("ILpoMowjIQjfYMR847rnFQ=="));
    }

    @Test
    public void getCorrectPlainText() throws KiplePayApiException {
        //given
        String plaintext = KiplePayEncrypt.decryptContent("ILpoMowjIQjfYMR847rnFQ==", "AES", "aa4BtZ4tspm2wnXLb1ThQA==", "utf-8");

        assertThat(plaintext, containsString("test1234567"));
    }
}
