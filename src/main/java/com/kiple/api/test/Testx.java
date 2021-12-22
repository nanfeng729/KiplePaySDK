package com.kiple.api.test;
import com.alibaba.fastjson.JSONObject;
import com.kiple.api.domain.request.KiplePayTradePagePayRequest;
import com.kiple.api.domain.response.KiplePayResponse;
import com.kiple.api.exception.KiplePayApiException;
import com.kiple.api.payment.DefaultKiplePayClient;
import com.kiple.api.payment.KiplePayClient;
import java.util.Random;
public class Testx {



    public static class SandBox {
        //47.254.245.66:80
        //192.168.181.129 request_body is =============>

        public static final String APP_ID = "2019051064521003";
        /**###################merchant#############################*/

        public static final String privateKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDLNSIXHqB7QpeA" +
                "XY6Bk2NxQdZ2ilKfh1d17XpwsQgrJdd3JjEOP979aJ7SuwDTAnpK6xMUWT9tr6pe" +
                "61feLYNNq7eOU9hNP+rqiKS0OE6Oa3eVJAbLE+KE6PeC2O9HmfZGLwM1T1Vd2GqE" +
                "zMnRDMqHjVcs34K7LpdrRZfHgRYTbgwaUZX7V2vq+M7np4Xwj/4LLUzCsF+V2Wr0" +
                "Y7mVEXnBG5U88z1bJF9SajKuchFwUmygp92VnEkm7fOy5S2oS5m/luWZEUX79e2B" +
                "OcQ/uRXqhmrpMgUfDxnZk/HxjYoMVXmt4FO5cCyCVR+hG820DiAsQyBX2QRiVq6B" +
                "PvAM+yF5AgMBAAECggEBAIc7Xup/6VDeKjC1EkdNLNqMdAsDVqTvztaeKeOhDMya" +
                "LGAQvIi9HMsLutaGuK+0cGo3MsVR95IpW3o7qIglRcKEym0fg001gkJ+mQ2Og0jo" +
                "X/aSuSqgmxjUaRNdBzBhV7GxS7NIjokboxepqY1Ds+yttRwZysiC4yXydGXClExs" +
                "ukjlaXyVoGZIRUQUXaXs4XUC6sXwv645KbM15kzgFWOiL0+1yZMt0F6tb2kU8Z2r" +
                "QTyP5yaHEUGbgzY7z3S0rI2xf9CtuQg9+wP8Ofj2597gUv5Au5HiQwiQExe4Efos" +
                "Ig2ZxqLClj3uOw3dOx1lLxfgEvXWVWd0tm9y6QVI32ECgYEA659aClIDKMqvqz2C" +
                "+iGzDAOr6a2W2n2534duKGu5TeDh94ql6S8nD2lOfSBP5UDFt6bsmlASj/nSFz6p" +
                "7pdzPG9OGJjMWov9yxsDZWMRGJ+D3WDd3s+ZvMLZRfzaox3GNsW3cRsPJ9vYfTQi" +
                "o/hdpQ9K0+fzaGpkzP3sEOvZQK0CgYEA3MgeqKhcKDJTPODxt2IAP9CcKm9dyVRR" +
                "UeaOyroKmKGBFGHGga37n9RFpEKWtHzcIUuqXBwMwl05UnWfVn6YeLkLyFPnLsNE" +
                "CJuthnOngNJsFyNHKr6GgmpCiIEVOI3cqFYfihDLlQSQL7tO7MpfoQ9tdtERUb3q" +
                "o2wwdfeZYX0CgYBcbjWXKNb+cIx7I3U3BHNFekc9MxCjg4Cf9HO9PY0CxP4/6k7t" +
                "a8bp38ifg0Z0S3WEduIIIvM1Ma492iI1a4oUIiHDumUn/BTCUUWCx1sUirbi6DYB" +
                "SvUnPFSZhPiL1olEQUmWACRw4WhKrWINasfpkVcsS6iLxHjohY/Oj4a5PQKBgQDc" +
                "SVg90+5PtRbUUWUcIk45Xf3TYVbkgJq66x5iLApSjCJsobocvemoaXYrFL2lzEcf" +
                "eY27ZcldTQLawb1/4cRj/84/zWeHgxEovZv/4PmqUUnENFDX104CZd+Ir7LqwLD/" +
                "zR6e9W8Leoga9/shzDJqUyhXOvba5nFtKY+YxLlnSQKBgQCzpHeK/YKUvin7wCdh" +
                "eJv8wHv1UStn/bp3Ys4ID/aqVVis9ihLCkb4n35/uY0s5FYAhyy2NURWJtZg8LvH" +
                "O5K4jHjdg4yXkp5vLWilsRIybhwd027vk1+5OPoNCjgJnBx0LArkg6p3tEhlUZLw" +
                "Gfl/tX2ESvS7ImWt6GElp7tqIA==";

        public static final String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvBue0zGm7rR0X9vLNAh3QclovtFekTjc/bQYd++7J/FUY6QkVCNzvX+5/9UOJ8/AznPRPYpqwkv6/GddH4tZQZ5sNjuNzghU4SfA+VqD6bReAlG9rXY0bDcPPI0uW3y8GxccXfEQ6e0sHILE6DZtz4QpD8s/csI6mJXMmhe3dh67QF57GVEwG9ALG+pk7jnJOM//QrmiK3B2jpsixgZXOdDWlITlHbwhJUT8T49lFUb6fS5f07Oc3jtIbC2OYiCNnX6jDfQOBj2ju1LWQfCzsD0kUjW/cGXhr/ecMhFRgl/xfteg1ZAImBA8sMRTVxKSkMDl+JhxUtsZbX4HDD+SbwIDAQAB";
        /**###################kiplepay#############################*/
        public static final String kiplePayPublic="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsCCS+Tjw0qTt1nWzRtdLO1qnSKCEzqrxUW+zAUMuG93auvQn8CahCWTLe6zT3Me1W/IyzshxGSz7eKsC+FSr3us/SSMQzeW5eL+ADNbxhKe1m94KlMy5be8Q8Iqt1S9oNtRdTrGIu8J743dfx7/EPMmEpqo27I1iCUdngzMHAdv9FDtqpXqIFbtj1ybeFwBG+o4cSQ0yyv98IUYGb7mHYkeVGAET5IO2d/gF/e1B7+fVzv84BGEbsur/tvXKyrCPjerlBr9onfzDxgosSunOrIghS2Ll2WOV2OKE5iXs9Q7Rnm47tBeuP2iOQEwx/tiD+K2nxoEEh6NzuHT42MPsTQIDAQAB";
        public static final String kiplePayPrivateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCwIJL5OPDSpO3WdbNG10s7WqdIoITOqvFRb7MBQy4b3dq69CfwJqEJZMt7rNPcx7Vb8jLOyHEZLPt4qwL4VKve6z9JIxDN5bl4v4AM1vGEp7Wb3gqUzLlt7xDwiq3VL2g21F1OsYi7wnvjd1/Hv8Q8yYSmqjbsjWIJR2eDMwcB2/0UO2qleogVu2PXJt4XAEb6jhxJDTLK/3whRgZvuYdiR5UYARPkg7Z3+AX97UHv59XO/zgEYRuy6v+29crKsI+N6uUGv2id/MPGCixK6c6siCFLYuXZY5XY4oTmJez1DtGebju0F64/aI5ATDH+2IP4rafGgQSHo3O4dPjYw+xNAgMBAAECggEAXZGgo1iV0EKgvDuhd7QvGqRJlPL6O1VjiNEftz76gwF1D7dgdFwBV5094UFq+LL8bZGZXnPsaeSU4IFxvX42JOKzp2ZCqjAuPbD3FVsnykLS37CxzGWK6ZWFWVVDjqt14RMnUwRMUPu8l5a2wsblOSoSF/z72uYtvCI8It58DYbuLV79DfKC5x4JroI4qbUWFSn+Dqby2ZgMtLiWTs1gX64lO4At4SIAsHfp4ZZm/MOboYksj99k3AjBZh6wmx+7bmA8CqZxljdJVEbaMacRa/qlShoYaskh64Jks/Xy7i6L7/tyCfgQ7WP7Jz/5MtIbBqTuYVrU+Yw0HjkRKMNoAQKBgQD5zbIeiuBoD/xt+m/KpokQtx1p10yVSOQP0iAUFe92NOAdRDcZ3wADlUHfu5mIUFT5cqmTDnFlSl5xHTOJo0MsWPvUh1amiyCa4M9FPQ0hMgE1B/en0YWhvcHQRrYpgZ72uXSS6Wxbn7TrHCch5mKDKkDSBcbu4zAgo4cYh2zGTQKBgQC0fwTOKNNO8WTebq6n4lT26RT8HSyFLnDu4XF+6tinU7kIVUZeCda2X+quoGGPBxpkCD4otsMVW8AzDX14w5qpW753mTiYkexnv/FsWv1/67bno+2Srv90aerUnojlWniNbfYMiwm148JePpHOc68c6QrWbVwuLtmAItqaF9K+AQKBgAmEhdHWFsDAQyzpitLcz4Ich/ldY6SYd2AG8IPzZgFJ+HQFAlhw/64jRLRCkJH6BZLPhJh+x2FFmR2UMD+BQfDxnv60/V8xE9fy0tLfhZ2LKzKbEBuLxxynfzfxScWBE85wuHlUfXVsO1dt5ivLfFtlA708zRyS+WV09ZPArStNAoGAWN9V8WCesvce8HrVGqrRpQWdJIoE2srEJorwxEpERRdvOxAp5rG3e/L8beFgR/l6YBjC6m8a3VBg8YJJ7oV5iLw4q1DRUiY/ndIfwHeI7EyWsbXAi4IJHDvuuaQZybxjMVgcV466SEoN8TOjvWCur3B7upr8UW0WAQaoVkk2pgECgYA3/avqX/Mnjba4i/XYqQjBrrJ4+Wyji/RPPu6U4U2/CHwQTtKxLWRbBqy8wvJepSIv6WTeEFhocfOjj6Lt4dBf/BgXuHcwXCMoLCSZIRYvHgOwq+pNvANGURri+uTBwjLiH8CohfhdskW/OxmbV92iB+/UAd+/x/f9f8s3/Zlvuw==";
        public static final String sign_type="RSA2";

        public static String notify_url = "http://localhost:8080/kiplepay/kiplepay-notify-notice";

        public static String return_url = "http://localhost:8080/kiplepay/kiplepay-return-notice";

        //请求地址
        public static String request_url = "http://47.254.245.66/payment/api/v1.0/topup/card";
        public static final String GATEWAY = request_url;

    }
    /**
     * user/* payment/* qrcode/*
     *
     * test request and wrapper the parameters
     */

//get random String
    public static String getRandomString(int length){
        String str="0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String main() throws KiplePayApiException {

        KiplePayClient kiplePayClient = new DefaultKiplePayClient(SandBox.GATEWAY,
                SandBox.APP_ID,SandBox.privateKey,"json","utf-8",
                SandBox.kiplePayPublic,
                SandBox.sign_type);
        KiplePayTradePagePayRequest request = new KiplePayTradePagePayRequest();
        request.setApiVersion("1.0.0");

        String reference = String.valueOf(System.nanoTime())+getRandomString(3);
//	  String reference = getRandomString(20);

        String request_data = "{\"amount\":\"10\","+
                "\"card_bin\":\"541060XXXXXX3563\","+
                "\"merchant_order_sn\":\""+reference+"\","+
                "\"redirect_url\":\"https://www.baidu.com\"}";
        request.setBizContent(request_data);

        request.setToken("fe23d803-84f3-42a1-a302-3abe999cd39d");
        return JSONObject.toJSONString(kiplePayClient.execute(request,"post"));
    }

}
