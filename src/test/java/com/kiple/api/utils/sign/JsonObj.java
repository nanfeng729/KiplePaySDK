package com.kiple.api.utils.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.management.Query;

/**
 * @projectname:KiplePaySDK
 * @author: seven.zhang
 * @date: 2020/6/8 18:34
 * @desc:
 */
public class JsonObj {

  private   String url;

  private  String type;

  private Query query;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    static class Query {

      private String  timestamp;
      private String      sign;//:"AFg6YIh/WUimLbKBHexxdZKIXxOW2yi/zDMThywh2MI6QrGFzlox47jAxpoJR1vxnD2LazMak2Ue5b1kOGd9qrizk6XeZyaak4bcGZqQpwhTPXjnj45a5ejLQJN3xsxraVZ8PcqvMwGKnn8byIRl2FvNmivLsCbfiJRjz6Bb4U/lgUFRDDQV0g7NOnFqlfbuj6ENkXj4hKWYE8zvz5S0a4oTcoxj4Q1GrpOWpBocCnetkKUwaQXNhV0RwfhZBGyi5x7YRp5JFCJFmDl2BaLU+zaHCDTiEZUj6IxPpJ41e7mTfFRSDEphTlqKGuE9FM5pj1UmqqpiwtKwj8am9gMEhA==",
      private String      app_id;//:"2019051064521003",
      private String      version;//:"1.0.0",
      private String      sign_type;//:"RSA2",
      private String      charset;//:"utf-8",
      private String      format;//:"json",
      private String      notify_url;//:"http://localhost:8080/kiplepay/kiplepay-notify-notice",
      private String      return_url;//:"http://localhost:8080/kiplepay/kiplepay-return-notice"

      public String getTimestamp() {
          return timestamp;
      }

      public void setTimestamp(String timestamp) {
          this.timestamp = timestamp;
      }

      public String getSign() {
          return sign;
      }

      public void setSign(String sign) {
          this.sign = sign;
      }

      public String getApp_id() {
          return app_id;
      }

      public void setApp_id(String app_id) {
          this.app_id = app_id;
      }

      public String getVersion() {
          return version;
      }

      public void setVersion(String version) {
          this.version = version;
      }

      public String getSign_type() {
          return sign_type;
      }

      public void setSign_type(String sign_type) {
          this.sign_type = sign_type;
      }

      public String getCharset() {
          return charset;
      }

      public void setCharset(String charset) {
          this.charset = charset;
      }

      public String getFormat() {
          return format;
      }

      public void setFormat(String format) {
          this.format = format;
      }

      public String getNotify_url() {
          return notify_url;
      }

      public void setNotify_url(String notify_url) {
          this.notify_url = notify_url;
      }

      public String getReturn_url() {
          return return_url;
      }

      public void setReturn_url(String return_url) {
          this.return_url = return_url;
      }
  }

    public static void main(String[] args) {
        JsonObj obj= JSONObject.parseObject("{\"url\":\"\\/web\\/verify\\/testagent\",\"query\":{\"timestamp\":\"2020-06-08 17:09:02\",\"sign\":\"AFg6YIh\\/WUimLbKBHexxdZKIXxOW2yi\\/zDMThywh2MI6QrGFzlox47jAxpoJR1vxnD2LazMak2Ue5b1kOGd9qrizk6XeZyaak4bcGZqQpwhTPXjnj45a5ejLQJN3xsxraVZ8PcqvMwGKnn8byIRl2FvNmivLsCbfiJRjz6Bb4U\\/lgUFRDDQV0g7NOnFqlfbuj6ENkXj4hKWYE8zvz5S0a4oTcoxj4Q1GrpOWpBocCnetkKUwaQXNhV0RwfhZBGyi5x7YRp5JFCJFmDl2BaLU+zaHCDTiEZUj6IxPpJ41e7mTfFRSDEphTlqKGuE9FM5pj1UmqqpiwtKwj8am9gMEhA==\",\"app_id\":\"2019051064521003\",\"version\":\"1.0.0\",\"sign_type\":\"RSA2\",\"charset\":\"utf-8\",\"format\":\"json\",\"notify_url\":\"http:\\/\\/localhost:8080\\/kiplepay\\/kiplepay-notify-notice\",\"return_url\":\"http:\\/\\/localhost:8080\\/kiplepay\\/kiplepay-return-notice\"},\"type\":\"GET\"}",JsonObj.class);
        System.out.printf("dsd");
    }
}
