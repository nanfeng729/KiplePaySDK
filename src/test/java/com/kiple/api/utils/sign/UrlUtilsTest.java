package com.kiple.api.utils.sign;

import com.alibaba.fastjson.JSON;
import com.kiple.api.utils.UrlUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectname:KiplePaySDK
 * @author: seven.zhang
 * @date: 2020/6/12 21:25
 * @desc:
 */
public class UrlUtilsTest {

    public static final String GATEWAY = "http://localhost/web/verify/verifysign";

    @Test
    public void testUrlPost(){
        Map<String, String> parameters = new HashMap<String, String>();
/*        parameters.put("timestamp", "2020-06-12 09:49:00");
        parameters.put("app_id", "2019051064521003");
        parameters.put("version", "1.0.0");
        parameters.put("sign_type", "RSA2");
        parameters.put("charset", "utf-8");
        parameters.put("notify_url", "http://localhost:8080/kiplepay/kiplepay-notify-notice");
        parameters.put("return_url", "http://localhost:8080/kiplepay/kiplepay-return-notice");*/
        parameters.put("biz_content", "{\"out_trade_no\":\"111\",\"total_amount\":\"1.0.0\",\"subject\":\"购买自行车\",\"body\":\"用户订购商品个数：12\",\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = UrlUtils.postRequset(GATEWAY,parameters);
    }
    @Test
    public void testUrlPostJson() throws UnsupportedEncodingException {
        Map<String, String> parameters = new HashMap<String, String>();
        Map<String, String> parameters1 = new HashMap<String, String>();
        parameters.put("timestamp", "2020-06-13 15:46:36");
        //parameters.put("sign","ONY/h2mL4Juub6hcfNT2EkxiXIyc+Pcz1mc3u5YtRCMmlbCUwduytZeeej1JndEMP3Cv/QeuCSkMCn4VqG/tUxEXPPuGHtUiAa7y/8Z5Ea9KZikOFIRiKm+cdYuchld/3or11n6EE0KeY+hSmRaF3hcdrEc23vFeecXqfT97ZGRb4J863JXOgtFtfeMTYCUrgnMTrUXUSVb31SY/PLN6w8iuMWMw0VTLovnf4+hSLWIzYZa84g5sHy/QYrI1rCjOfb6AnY03r35R1H/gDqRt1Fr5CQ4tnmaomM8OI2ykYSfpWDHfq0QZhahLU4i7G8E+NJIiPOPUnFtxHQPtTdd9rQ==");
        parameters.put("sign","ONY/h2mL4Juub6hcfNT2EkxiXIyc+Pcz1mc3u5YtRCMmlbCUwduytZeeej1JndEMP3Cv/QeuCSkMCn4VqG/tUxEXPPuGHtUiAa7y/8Z5Ea9KZikOFIRiKm+cdYuchld/3or11n6EE0KeY+hSmRaF3hcdrEc23vFeecXqfT97ZGRb4J863JXOgtFtfeMTYCUrgnMTrUXUSVb31SY/PLN6w8iuMWMw0VTLovnf4+hSLWIzYZa84g5sHy/QYrI1rCjOfb6AnY03r35R1H/gDqRt1Fr5CQ4tnmaomM8OI2ykYSfpWDHfq0QZhahLU4i7G8E+NJIiPOPUnFtxHQPtTdd9rQ==");
        parameters.put("app_id", "2019051064521003");
        parameters.put("format", "json");
        parameters.put("version", "1.0.0");
        parameters.put("sign_type", "RSA2");
        parameters.put("charset", "utf-8");
        parameters.put("notify_url", "http://localhost:8080/kiplepay/kiplepay-notify-notice");
        parameters.put("return_url", "http://localhost:8080/kiplepay/kiplepay-return-notice");
        //遍历map
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> entry:parameters.entrySet()){
            buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),"utf-8")).append("&");
        }
        String url = GATEWAY+"?"+buffer.toString();
        parameters1.put("biz_content", "{\"out_trade_no\":\"111\",\"total_amount\":\"1.0.0\",\"subject\":\"购买自行车\",\"body\":\"用户订购商品个数：12\",\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = UrlUtils.doJsonPost(url,"biz-content=%7B%22out_trade_no%22%3A%22111%22%2C%22total_amount%22%3A%221.0.0%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E8%87%AA%E8%A1%8C%E8%BD%A6%22%2C%22body%22%3A%22%E7%94%A8%E6%88%B7%E8%AE%A2%E8%B4%AD%E5%95%86%E5%93%81%E4%B8%AA%E6%95%B0%EF%BC%9A12%22%2C%22product_code%22%3A%22FAST_INSTANT_TRADE_PAY%22%7D");
    }
}
