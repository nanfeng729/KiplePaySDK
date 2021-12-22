/**
 * KiplePay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.kiple.api.utils.sign;

import com.kiple.api.exception.KiplePayApiException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @classname: RSACheckTest
 * @description: 描述
 * @author: seven.zhang
 * @date: 2020/6/4 15:41
 */
public class RSACheckTest {
    private String publicKey;

    @Before
    public void setUp() throws Exception {
        //本单元测试中使用的是商户私钥（因为拿不到支付宝私钥）签名后的参数进行测试，所以验签时指定的是商户的公钥
        //实际使用RSACheck系列函数时，应该是指定支付宝公钥，对支付宝私钥签名后的参数进行验签
        this.publicKey = KiplePaySignature.getKiplePayPublicKey("src/test/resources/appCertPublicKey_2019091767145019.crt");
    }

    @Test
    public void should_pass_rsa_check_when_sign_type_is_rsa2() throws KiplePayApiException {
        //given
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("method", "koubei.marketing.data.indicator.query");
        parameters.put("app_id", "2019090366875133");
        parameters.put("sign_type", "RSA2");
        parameters.put("sign",
                "JzoDc8VxY1/w6yN9WdWV10aipS3YcRpK"
                        + "+jw4xfLybf90ZK9L3AHLUJbNLWVnHW3IuLoJbBeSGVxSbPBhe4ggPklcYUkPowgtlZ6YlthuQDtjF23h2obXuXkQRd"
                        + "+RPbDWvOA5AYQjsKH8uSHil5aRARewPIPhukl9Mn4HEovUccsBR/RirQFSGmGYiMM0zvhVSR7pXZDEhiADzvzAkvVVTI1"
                        + "/HbNqcoBU4ctSPAGsuDPO/mah1+IwGQAuPP6xoEPL"
                        + "+3zQ0wztQCwHT2o8aQmxFJ9a09q8ybRprHaNjCgTaLDeTWE0o1pllZIE8c7wnG3cOuj6quYjTcQyLm6P4M87Zw==");
        //when
        boolean result = KiplePaySignature.rsaCheckV2(parameters, publicKey, "utf-8", "RSA2");
        //then
        assertThat(result, is(true));
    }

    @Test
    public void should_pass_rsa_check_when_charset_is_utf8() throws KiplePayApiException {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("method", "koubei.marketing.data.indicator.query");
        parameters.put("app_id", "2019090366875133");
        parameters.put("extra", "中文测试");
        parameters.put("sign_type", "RSA2");
        parameters.put("sign",
                "KrRGUY3/2JX3KtlLgus8CbK0xuUIBDCpOdJkVzJDefez6HvlA8RA6uCVj2rrMd7DgVfarG5SROdSnkZbf8MLKHbVoFqi9w0QCvto9mc8n3ezfWejZECJVCZhbJ3OslB+4gij9+F70usrnCNEJZm02ntyNdVzcqMbgdRMB93BJIRC1jjmCotpXgXWrRdTb/SfhIAkoHqgGi2aCUHuLDInLGZCn8NeziGWMnFOic6/sE/nMpwriOmwLb2nyzD0fGiolwuuxlOGMcBHAb22J8XqchFHyCpbs2A/rWdJMjhUfqsErbtZQM93fi1xfL2pUa9RMWX0Q81Qk7iSEeHCR+NGyQ==");

        boolean result = KiplePaySignature.rsaCheckV2(parameters, publicKey, "utf-8", "RSA2");

        assertThat(result, is(true));
    }

    @Test
    public void verifyRSA() throws KiplePayApiException{
        //String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoCfro3e3VLl/F3ObMqE71rKzob/bD0r+qwoCfWt4DZ0f8YYwWDOXR3iCR9zKGudcrkyravL55V1u+r43OZKDGFfprct+nXXYRoKif0bZHScPQ5rTo1oM76YF6MB560tZB45EMMpSLOYJvsU+eifQE5M5LAkzgJTFkTq/he+wmcZjGF5+bY6dv0ya4EEDWj3tfFiqWV4aVoMTUIhVwbgevkANMFWYBEIqrsk1V75GA3phSui8fdFusFTc0OBcTEsVj0Y3fxatkWVT+LcV2GXOTQyRFWZuIEuUxZ2A2Tn4YoJI98G37q5xjyVqtJpyZnf8OuI3mz3LR3xvyza/0PWxyQIDAQAB";
        String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmTNTg9mbX3BgBtkf6r+kKboRAghTV4Ddt/nlILtH8KJUdLTbPe5sbkhHXgH4I4kpnZRoRdYqUc7OU3E8criVyHYDqPJO4I/p1tkjEEkNHxgUwFy4CamanuSBb2WCVnqGAmzJlFryhE9CSZHDOsDO976qCdECx556UVes6ooVq46jJp/Jm268kR2M5uHzL16lJIrEETaTwgbk7RwmJNQZjUQ6ZK8LgIaYYDBNdwJs8fw+fEOpgz+eGbr00UDL7NUUr2MRRF+A+Hy4PoGJnyaHslZgOvVad+iGnEoNvw1+LYjJP/ssMPi+rOt2wT38Xb9eGcnuVD65v5cfZQ+V/vx1QwIDAQAB";

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("timestamp", "2020-06-13 15:46:36");
        parameters.put("app_id", "2019051064521003");
        parameters.put("version", "1.0.0");
        parameters.put("sign_type", "RSA2");
        parameters.put("charset", "utf-8");
        parameters.put("format","json");
        parameters.put("notify_url", "http://localhost:8080/kiplepay/kiplepay-notify-notice");
        parameters.put("return_url", "http://localhost:8080/kiplepay/kiplepay-return-notice");
        //收到的bizcontent需要进行转化为json字符串进行url解码才能验签过
        parameters.put("biz_content", "{\"out_trade_no\":\"111\",\"total_amount\":\"1.0.0\",\"subject\":\"购买自行车\",\"body\":\"用户订购商品个数：12\",\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //parameters.put("sign","JIl4z+eOLn41VjRJ+5e/e7UmsJXUibYZsS5ly76U4Wv8cUfPEy8UXJVqX4h94+5we3IxW//eGiWtUUGtSoGJ+tjedq65Xg4Sd1tCwEc2QndkOTKMInS0J8+FA3LJBEvXo6D8svPK8DJ191JpT8kc3XaO5n9gH8ZWM7BRyr1EncLSdOeR+fRxsGJ9kES5frsimLzi6doyxWsSpdzW2ExWW/nlNBdasybm7cCmMzi3TtbzsaFodPPuMKkF7wqLyHRpGut7JSwqGOroZfohFHmfpEsbhbgTP+kvW+eXeI2hbfVLaZRPx//3xLZ7h+6kEyJUohpCBtoyHboZXMXDHXnOxg==");
        //最后移出sign的内容
       // parameters.put("sign","HsXT8vi/ix+ddqjSZ7Epeli72kuemxpmhho2WfUxbZUsKF/vFF+UxxvWGQL+MODqn80T4pSZbicw5hkTYZsWjvCYvclmGtmbiVHBnbKYxSD1rsplhoCgPhEVHSTVDt+aakpS8yV3GXZb1MdGFEIxr5NajtOMazy1BiaESfI92MQ4Y/BkDkeTfhRO8tRX1qItjU2HnlspNn/8tQccGqJtMyXkGF6njgomrgseYbBWsy7lq2d+BWoEl04dHlrnLILRulTWdwaW5F5q9UACnhZTf6QJR2YKBPba77vFtb/npktGcIuOfW4iUDFHCM/stlhibjp0bNrrU/oG6WzGJ3VQlg==");
        //parameters.put("sign","ONY/h2mL4Juub6hcfNT2EkxiXIyc+Pcz1mc3u5YtRCMmlbCUwduytZeeej1JndEMP3Cv/QeuCSkMCn4VqG/tUxEXPPuGHtUiAa7y/8Z5Ea9KZikOFIRiKm+cdYuchld/3or11n6EE0KeY+hSmRaF3hcdrEc23vFeecXqfT97ZGRb4J863JXOgtFtfeMTYCUrgnMTrUXUSVb31SY/PLN6w8iuMWMw0VTLovnf4+hSLWIzYZa84g5sHy/QYrI1rCjOfb6AnY03r35R1H/gDqRt1Fr5CQ4tnmaomM8OI2ykYSfpWDHfq0QZhahLU4i7G8E+NJIiPOPUnFtxHQPtTdd9rQ==");
        parameters.put("sign","ONY/h2mL4Juub6hcfNT2EkxiXIyc+Pcz1mc3u5YtRCMmlbCUwduytZeeej1JndEMP3Cv/QeuCSkMCn4VqG/tUxEXPPuGHtUiAa7y/8Z5Ea9KZikOFIRiKm+cdYuchld/3or11n6EE0KeY+hSmRaF3hcdrEc23vFeecXqfT97ZGRb4J863JXOgtFtfeMTYCUrgnMTrUXUSVb31SY/PLN6w8iuMWMw0VTLovnf4+hSLWIzYZa84g5sHy/QYrI1rCjOfb6AnY03r35R1H/gDqRt1Fr5CQ4tnmaomM8OI2ykYSfpWDHfq0QZhahLU4i7G8E+NJIiPOPUnFtxHQPtTdd9rQ==");
        boolean result = KiplePaySignature.rsaCheckV2(parameters, publicKey, "utf-8", "RSA2");
        System.out.println(result);
    }
    @Test
    public void signRSA() throws KiplePayApiException{
        //String privateKey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCZM1OD2ZtfcGAG2R/qv6QpuhECCFNXgN23+eUgu0fwolR0tNs97mxuSEdeAfgjiSmdlGhF1ipRzs5TcTxyuJXIdgOo8k7gj+nW2SMQSQ0fGBTAXLgJqZqe5IFvZYJWeoYCbMmUWvKET0JJkcM6wM73vqoJ0QLHnnpRV6zqihWrjqMmn8mbbryRHYzm4fMvXqUkisQRNpPCBuTtHCYk1BmNRDpkrwuAhphgME13Amzx/D58Q6mDP54ZuvTRQMvs1RSvYxFEX4D4fLg+gYmfJoeyVmA69Vp36IacSg2/DX4tiMk/+yww+L6s63bBPfxdv14Zye5UPrm/lx9lD5X+/HVDAgMBAAECggEAVI3Se0QAEBXKuhzeE8c/yNPsDS4hKXtzY9YlkwIqe3JsoJktJCpSaC+2S/lURfCvKky9nEXOx6YXlFA8J6ke6ivQa0+JH0HusujXBGb9OfslgOHrpVGZiDbgBWaGIEqxaec2/o2PqlzAXjB4CxZdZLkzygU0vo4GPpzgT/Z0bWGKMJu+qboPm5Tu4qfgl6sweISNrlArJrMrox6uX3T5SuAHt/JlF87RV+MBPhhYdKpz08YbLtN1ERKLZniAt/41TH6/ugCXXeVSO+r7/zof0nTc3v7OJgFnZwqfkAl8q0NzV+9UD+HBU1WedI4LM/dwRkmLaMTrRl4lmQkvitDM8QKBgQDTfsYSdXohD1IF573O+Ccoppv8ONbR/lr03B7cepuxZIQ7otLT1Xyrz949WJwwIaGfZnXBoMqY1mfzuysKmCJI0zaH+ZgeRdNM4K7boEc4nAyk2ncULJL5OsirwaG3utqWrxM74tsRHhlhKSdk7U1AUrgBPyCPdvWBki5RIjtyyQKBgQC5cDjJFEySPO8sYRwJNhCNUTPrVSwsPwQSRuOruszqahgc4RXXlSfhzQzQ3AIhLRrtdbDPfvxJTzBZeEr/ck3ZFTmteVi0gNE1SSJH5OlEQR+MOki/GfGLXiLmqsFqORGeK2VWdc7MPsnrHR7PbBE85jgnXtlNBJ0WT0hXbTQBqwKBgHjW1UsUrjwXadEP8KZBqPYUA8nfm/YNs2B2KHfm4fBAM0Fzrav1La9pLFy2tT7W6GaP/zYrrbYXwvtCdLwv4l7jtyOH239oe+tP8Phxol/Y4WyLmj//m9Yk3SUtlD3K2DwCOiYcXvhvzyZxgh2rbFkVKickL3FpEar/n9Ehkv/pAoGBAIu3U/Gcy7x7ELFdUWRXSc1P21B6UdfcetLSeoNPMUmFOjm4FV7i0LO+bOOIgEpC08EKJXeQ88DAW2ttyQQCOUlQRq6/eOg3DmmZeqy0elSkxIJA/8KMZ4NcH+uvyB6idPW5OJcWGAOnYzc0L5yUjcBWrQRs4Ei5Kyo9/gFxE/gVAoGBAK+W0xLvUY+H6ujqL5bXx8FUTW8rYyI5gVTvGDBP3SWi5mnewwASa3TNJTEhoS6tUIT+qaVqiXiJ6TMYzv2l5RSiU4pV2CQ3PpAobXNSI2AEedku4QyGnwkIc37NYdhgILqgUbxdUwCT+YqIFCQxpBH5jIobh2KZwEK8b6Z9SAoG";
        String privateKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDLNSIXHqB7QpeAXY6Bk2NxQdZ2ilKfh1d17XpwsQgrJdd3JjEOP979aJ7SuwDTAnpK6xMUWT9tr6pe61feLYNNq7eOU9hNP+rqiKS0OE6Oa3eVJAbLE+KE6PeC2O9HmfZGLwM1T1Vd2GqEzMnRDMqHjVcs34K7LpdrRZfHgRYTbgwaUZX7V2vq+M7np4Xwj/4LLUzCsF+V2Wr0Y7mVEXnBG5U88z1bJF9SajKuchFwUmygp92VnEkm7fOy5S2oS5m/luWZEUX79e2BOcQ/uRXqhmrpMgUfDxnZk/HxjYoMVXmt4FO5cCyCVR+hG820DiAsQyBX2QRiVq6BPvAM+yF5AgMBAAECggEBAIc7Xup/6VDeKjC1EkdNLNqMdAsDVqTvztaeKeOhDMyaLGAQvIi9HMsLutaGuK+0cGo3MsVR95IpW3o7qIglRcKEym0fg001gkJ+mQ2Og0joX/aSuSqgmxjUaRNdBzBhV7GxS7NIjokboxepqY1Ds+yttRwZysiC4yXydGXClExsukjlaXyVoGZIRUQUXaXs4XUC6sXwv645KbM15kzgFWOiL0+1yZMt0F6tb2kU8Z2rQTyP5yaHEUGbgzY7z3S0rI2xf9CtuQg9+wP8Ofj2597gUv5Au5HiQwiQExe4EfosIg2ZxqLClj3uOw3dOx1lLxfgEvXWVWd0tm9y6QVI32ECgYEA659aClIDKMqvqz2C+iGzDAOr6a2W2n2534duKGu5TeDh94ql6S8nD2lOfSBP5UDFt6bsmlASj/nSFz6p7pdzPG9OGJjMWov9yxsDZWMRGJ+D3WDd3s+ZvMLZRfzaox3GNsW3cRsPJ9vYfTQio/hdpQ9K0+fzaGpkzP3sEOvZQK0CgYEA3MgeqKhcKDJTPODxt2IAP9CcKm9dyVRRUeaOyroKmKGBFGHGga37n9RFpEKWtHzcIUuqXBwMwl05UnWfVn6YeLkLyFPnLsNECJuthnOngNJsFyNHKr6GgmpCiIEVOI3cqFYfihDLlQSQL7tO7MpfoQ9tdtERUb3qo2wwdfeZYX0CgYBcbjWXKNb+cIx7I3U3BHNFekc9MxCjg4Cf9HO9PY0CxP4/6k7ta8bp38ifg0Z0S3WEduIIIvM1Ma492iI1a4oUIiHDumUn/BTCUUWCx1sUirbi6DYBSvUnPFSZhPiL1olEQUmWACRw4WhKrWINasfpkVcsS6iLxHjohY/Oj4a5PQKBgQDcSVg90+5PtRbUUWUcIk45Xf3TYVbkgJq66x5iLApSjCJsobocvemoaXYrFL2lzEcfeY27ZcldTQLawb1/4cRj/84/zWeHgxEovZv/4PmqUUnENFDX104CZd+Ir7LqwLD/zR6e9W8Leoga9/shzDJqUyhXOvba5nFtKY+YxLlnSQKBgQCzpHeK/YKUvin7wCdheJv8wHv1UStn/bp3Ys4ID/aqVVis9ihLCkb4n35/uY0s5FYAhyy2NURWJtZg8LvHO5K4jHjdg4yXkp5vLWilsRIybhwd027vk1+5OPoNCjgJnBx0LArkg6p3tEhlUZLwGfl/tX2ESvS7ImWt6GElp7tqIA==";
        Map<String, String> parameters = new HashMap<String, String>();
        //parameters.put("timestamp", "2020-06-13 15:46:36");
        parameters.put("timestamp", "2020-06-15 11:27:04");
       /* parameters.put("app_id", "2019051064521003");
        parameters.put("format","json");
        parameters.put("version", "1.0.0");
        parameters.put("sign_type", "RSA2");
        parameters.put("charset", "utf-8");
        parameters.put("notify_url", "http://localhost:8080/kiplepay/kiplepay-notify-notice");
        parameters.put("return_url", "http://localhost:8080/kiplepay/kiplepay-return-notice");
        parameters.put("biz_content", "{\"out_trade_no\":\"111\",\"total_amount\":\"1.0.0\",\"subject\":\"购买自行车\",\"body\":\"用户订购商品个数：12\",\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
*/        String result = KiplePaySignature.signWithPlainText("123456", privateKey, "utf-8", "RSA2");
        System.out.println(result);
    }

}