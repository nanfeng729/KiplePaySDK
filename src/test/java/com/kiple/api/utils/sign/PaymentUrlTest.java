package com.kiple.api.utils.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kiple.api.domain.request.KiplePayTradePagePayRequest;
import com.kiple.api.domain.response.KiplePayResponse;
import com.kiple.api.exception.KiplePayApiException;
import com.kiple.api.payment.DefaultKiplePayClient;
import com.kiple.api.payment.KiplePayClient;

import java.util.HashMap;
import java.util.Map;

class PaymentUrlTesttg {
    public static class SandBox {
        public static final String APP_ID = "9785158735d7ea760cde2705d2775568";
        /**###################merchant#############################*/
        //private static final String privateKey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCZM1OD2ZtfcGAG2R/qv6QpuhECCFNXgN23+eUgu0fwolR0tNs97mxuSEdeAfgjiSmdlGhF1ipRzs5TcTxyuJXIdgOo8k7gj+nW2SMQSQ0fGBTAXLgJqZqe5IFvZYJWeoYCbMmUWvKET0JJkcM6wM73vqoJ0QLHnnpRV6zqihWrjqMmn8mbbryRHYzm4fMvXqUkisQRNpPCBuTtHCYk1BmNRDpkrwuAhphgME13Amzx/D58Q6mDP54ZuvTRQMvs1RSvYxFEX4D4fLg+gYmfJoeyVmA69Vp36IacSg2/DX4tiMk/+yww+L6s63bBPfxdv14Zye5UPrm/lx9lD5X+/HVDAgMBAAECggEAVI3Se0QAEBXKuhzeE8c/yNPsDS4hKXtzY9YlkwIqe3JsoJktJCpSaC+2S/lURfCvKky9nEXOx6YXlFA8J6ke6ivQa0+JH0HusujXBGb9OfslgOHrpVGZiDbgBWaGIEqxaec2/o2PqlzAXjB4CxZdZLkzygU0vo4GPpzgT/Z0bWGKMJu+qboPm5Tu4qfgl6sweISNrlArJrMrox6uX3T5SuAHt/JlF87RV+MBPhhYdKpz08YbLtN1ERKLZniAt/41TH6/ugCXXeVSO+r7/zof0nTc3v7OJgFnZwqfkAl8q0NzV+9UD+HBU1WedI4LM/dwRkmLaMTrRl4lmQkvitDM8QKBgQDTfsYSdXohD1IF573O+Ccoppv8ONbR/lr03B7cepuxZIQ7otLT1Xyrz949WJwwIaGfZnXBoMqY1mfzuysKmCJI0zaH+ZgeRdNM4K7boEc4nAyk2ncULJL5OsirwaG3utqWrxM74tsRHhlhKSdk7U1AUrgBPyCPdvWBki5RIjtyyQKBgQC5cDjJFEySPO8sYRwJNhCNUTPrVSwsPwQSRuOruszqahgc4RXXlSfhzQzQ3AIhLRrtdbDPfvxJTzBZeEr/ck3ZFTmteVi0gNE1SSJH5OlEQR+MOki/GfGLXiLmqsFqORGeK2VWdc7MPsnrHR7PbBE85jgnXtlNBJ0WT0hXbTQBqwKBgHjW1UsUrjwXadEP8KZBqPYUA8nfm/YNs2B2KHfm4fBAM0Fzrav1La9pLFy2tT7W6GaP/zYrrbYXwvtCdLwv4l7jtyOH239oe+tP8Phxol/Y4WyLmj//m9Yk3SUtlD3K2DwCOiYcXvhvzyZxgh2rbFkVKickL3FpEar/n9Ehkv/pAoGBAIu3U/Gcy7x7ELFdUWRXSc1P21B6UdfcetLSeoNPMUmFOjm4FV7i0LO+bOOIgEpC08EKJXeQ88DAW2ttyQQCOUlQRq6/eOg3DmmZeqy0elSkxIJA/8KMZ4NcH+uvyB6idPW5OJcWGAOnYzc0L5yUjcBWrQRs4Ei5Kyo9/gFxE/gVAoGBAK+W0xLvUY+H6ujqL5bXx8FUTW8rYyI5gVTvGDBP3SWi5mnewwASa3TNJTEhoS6tUIT+qaVqiXiJ6TMYzv2l5RSiU4pV2CQ3PpAobXNSI2AEedku4QyGnwkIc37NYdhgILqgUbxdUwCT+YqIFCQxpBH5jIobh2KZwEK8b6Z9SAoG";

        private static final String privateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC3k7Yic7ppk1zs\n" +
                "4vrpJYfBdiwWDA7uuSVbY75RlxjPMdZB248HKG0qjVrr8W6N6g7q5aOq44Yh3C0H\n" +
                "Ch5jn3IrzuqK0vlrDP9aaMOo0lykOcF5Z2takQxRHFkYZ0+Nq9teDxjzoGgk0j8h\n" +
                "DuY6v7SxtbVT7xtiu2h6q3m0p4n0rXyRSbVnP8YCb4js1GZ+NB/5KoS1Y+8gH//p\n" +
                "kUK/FR/Q9yh0lvdta+CCOIfx+mHM/bRAeb3A4ZqLHJb4AjAY9ddKuRQQPftSu4d0\n" +
                "Zs85avbiv7Cx/SLOJo1G8DSe3nqz2gO8rif+LNM4BKYxCT9ApAEW0G9WlK81arjs\n" +
                "uveaxP7dAgMBAAECggEAWSjerwDyKtCts0fSgWX+91FDvDmDDyLRJ//bsZjnLcqQ\n" +
                "u0+9ucZCrwYUgL+z6C0jraZ2xUrYHJ3pKKAbfls0gl5uv5oUFZAsfWagkOAKqqB6\n" +
                "fVJja4tDJinUg+u5UZ+EXbyLa9gefLO4gX/RBfuR8TXFqcxZyqwm5F1gMQOmFzVQ\n" +
                "m7lxi5YeZyZFELh1CPNLnqJ5SZlhl8vMFtsAp/5+oNhKxgmFxfAS/MUe6/+YQ39/\n" +
                "hHZkDZJntqZXve2n+dzw2E2JfoqmTfIQqfRuEKAKy596MQxlmJcYa7mms6xfe5Bx\n" +
                "QfdFQUImODz0dlqCS0rMtxptCDW8YF0zkge/AOcCaQKBgQDi+Sti47TWO9MQRA1h\n" +
                "uOYwTEWyKvt+vVYjOqaiSSxsRotFroLIa1DDdVLvC+N6TocGSXhIMoC/iGoeSKUj\n" +
                "Zl0B4fIAhbg3M05v236kvvFmBNYJ5dqpI4VdL+J/+yD3hL76HNZNCsHbniy9ebP4\n" +
                "zlZreCAw5FKZNnaIUqQKbt4UKwKBgQDPDcyZFqm5abI7tBr8loZ41isZs74BCGzj\n" +
                "mEJot2pxDHsjTlH8R295aAEGgxyMVDWCW4ZBrk3lgX+S6w+6Q3ioMvwcmLmN8TGO\n" +
                "FSRjxC10RQStNhW23R3HE943yi0GCihuH347v7U4ysxOSQb/hv8KA9c3yCX2vbbz\n" +
                "+2Vd7OENFwKBgGm7CrPxQO8/RQ3htZ4dJNXA4gKiNS+QAY09qBwXHCOAJSabqX61\n" +
                "RxUWFUEX96qk5A9QsEBpjkIqyMw8qPi5mX/kAfiuUecc9l/zDqat8APHIvHPajG6\n" +
                "Q0jqoodds7i51MDsKAQRi8bDgzHsfKLdc7JybgR4h+WpUkQkwxe9F6SrAoGAadwx\n" +
                "Rff7UKvEj5fW0FuZZwCAysRGGbsYCQkYsvdLuX7bGkL50dUoHldZZt3z459OOFbY\n" +
                "/o3Cj9u0wQXjZaUHdF+U3H4NKJAveOhAUa3EW5TJ/lEPqw3EIrt6uuVoPZVF9H+v\n" +
                "h5bNbbrp2ACoTk7T6GfsSj3Pgyad+oIG4o2wxoMCgYB0FxorMan7b1zEHZ/a3Spe\n" +
                "RSwHmnl2kbNXe4WfJ2OD9gWbQj5KuWD8d4h6HO6VcX0RGzDVXXwPw1f6emb3sWY9\n" +
                "wETtpMUtcnPbW/PkT3oUPH+gUjDYm7ASa/EUC8SGOr38Jo04bI/rxR3E2eL1lYQ7\n" +
                "vAdbfBk3j0Df7YJ7Xm3jng==";

        private static final String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvBue0zGm7rR0X9vLNAh3QclovtFekTjc/bQYd++7J/FUY6QkVCNzvX+5/9UOJ8/AznPRPYpqwkv6/GddH4tZQZ5sNjuNzghU4SfA+VqD6bReAlG9rXY0bDcPPI0uW3y8GxccXfEQ6e0sHILE6DZtz4QpD8s/csI6mJXMmhe3dh67QF57GVEwG9ALG+pk7jnJOM//QrmiK3B2jpsixgZXOdDWlITlHbwhJUT8T49lFUb6fS5f07Oc3jtIbC2OYiCNnX6jDfQOBj2ju1LWQfCzsD0kUjW/cGXhr/ecMhFRgl/xfteg1ZAImBA8sMRTVxKSkMDl+JhxUtsZbX4HDD+SbwIDAQAB";
        /**###################kiplepay#############################*/
        private static final String kiplePayPublic="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsCCS+Tjw0qTt1nWzRtdLO1qnSKCEzqrxUW+zAUMuG93auvQn8CahCWTLe6zT3Me1W/IyzshxGSz7eKsC+FSr3us/SSMQzeW5eL+ADNbxhKe1m94KlMy5be8Q8Iqt1S9oNtRdTrGIu8J743dfx7/EPMmEpqo27I1iCUdngzMHAdv9FDtqpXqIFbtj1ybeFwBG+o4cSQ0yyv98IUYGb7mHYkeVGAET5IO2d/gF/e1B7+fVzv84BGEbsur/tvXKyrCPjerlBr9onfzDxgosSunOrIghS2Ll2WOV2OKE5iXs9Q7Rnm47tBeuP2iOQEwx/tiD+K2nxoEEh6NzuHT42MPsTQIDAQAB";
        private static final String kiplePayPrivateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCwIJL5OPDSpO3WdbNG10s7WqdIoITOqvFRb7MBQy4b3dq69CfwJqEJZMt7rNPcx7Vb8jLOyHEZLPt4qwL4VKve6z9JIxDN5bl4v4AM1vGEp7Wb3gqUzLlt7xDwiq3VL2g21F1OsYi7wnvjd1/Hv8Q8yYSmqjbsjWIJR2eDMwcB2/0UO2qleogVu2PXJt4XAEb6jhxJDTLK/3whRgZvuYdiR5UYARPkg7Z3+AX97UHv59XO/zgEYRuy6v+29crKsI+N6uUGv2id/MPGCixK6c6siCFLYuXZY5XY4oTmJez1DtGebju0F64/aI5ATDH+2IP4rafGgQSHo3O4dPjYw+xNAgMBAAECggEAXZGgo1iV0EKgvDuhd7QvGqRJlPL6O1VjiNEftz76gwF1D7dgdFwBV5094UFq+LL8bZGZXnPsaeSU4IFxvX42JOKzp2ZCqjAuPbD3FVsnykLS37CxzGWK6ZWFWVVDjqt14RMnUwRMUPu8l5a2wsblOSoSF/z72uYtvCI8It58DYbuLV79DfKC5x4JroI4qbUWFSn+Dqby2ZgMtLiWTs1gX64lO4At4SIAsHfp4ZZm/MOboYksj99k3AjBZh6wmx+7bmA8CqZxljdJVEbaMacRa/qlShoYaskh64Jks/Xy7i6L7/tyCfgQ7WP7Jz/5MtIbBqTuYVrU+Yw0HjkRKMNoAQKBgQD5zbIeiuBoD/xt+m/KpokQtx1p10yVSOQP0iAUFe92NOAdRDcZ3wADlUHfu5mIUFT5cqmTDnFlSl5xHTOJo0MsWPvUh1amiyCa4M9FPQ0hMgE1B/en0YWhvcHQRrYpgZ72uXSS6Wxbn7TrHCch5mKDKkDSBcbu4zAgo4cYh2zGTQKBgQC0fwTOKNNO8WTebq6n4lT26RT8HSyFLnDu4XF+6tinU7kIVUZeCda2X+quoGGPBxpkCD4otsMVW8AzDX14w5qpW753mTiYkexnv/FsWv1/67bno+2Srv90aerUnojlWniNbfYMiwm148JePpHOc68c6QrWbVwuLtmAItqaF9K+AQKBgAmEhdHWFsDAQyzpitLcz4Ich/ldY6SYd2AG8IPzZgFJ+HQFAlhw/64jRLRCkJH6BZLPhJh+x2FFmR2UMD+BQfDxnv60/V8xE9fy0tLfhZ2LKzKbEBuLxxynfzfxScWBE85wuHlUfXVsO1dt5ivLfFtlA708zRyS+WV09ZPArStNAoGAWN9V8WCesvce8HrVGqrRpQWdJIoE2srEJorwxEpERRdvOxAp5rG3e/L8beFgR/l6YBjC6m8a3VBg8YJJ7oV5iLw4q1DRUiY/ndIfwHeI7EyWsbXAi4IJHDvuuaQZybxjMVgcV466SEoN8TOjvWCur3B7upr8UW0WAQaoVkk2pgECgYA3/avqX/Mnjba4i/XYqQjBrrJ4+Wyji/RPPu6U4U2/CHwQTtKxLWRbBqy8wvJepSIv6WTeEFhocfOjj6Lt4dBf/BgXuHcwXCMoLCSZIRYvHgOwq+pNvANGURri+uTBwjLiH8CohfhdskW/OxmbV92iB+/UAd+/x/f9f8s3/Zlvuw==";
        private static final String sign_type="RSA2";

        public static String notify_url = "http://localhost:8080/kiplepay/kiplepay-notify-notice";

        public static String return_url = "http://localhost:8080/kiplepay/kiplepay-return-notice";

        //47.254.245.66
        //192.168.1.240 request_body is =============>http://47.254.245.66
        public static final String GATEWAY = "https://util-staging.kiplepay.com/cashier/v1/api/cashier";
    }
    /**
     * test request and wrapper the parameters
     */
    public static void main(String[] agrs) throws KiplePayApiException {
        Map<String,Object> paraMap = new HashMap<>();
        //金额 单位分
        paraMap.put("amount","123");
        //merchant_id 子商户id
        paraMap.put("merchant_id",151);
        //type 1 - payment; 2 - topup; 3 - add card
        paraMap.put("type",1);
        //order_sn 订单号
        paraMap.put("order_sn","123234324765");

        //TODO ng平台用户调用,需要打开下面注释
//        kipleNgUser(paraMap);

        //customer_id 用户id 非ng平台必须传入
        paraMap.put("customer_id","123456");
        //TODO 非ng平台用户调用，需要打开下面注释
        nonKipleNgUser(paraMap);
    }

//    public static void kipleNgUser(Map<String,Object> paraMap) throws KiplePayApiException{
//
//        KiplePayClient kiplePayClient = new DefaultKiplePayClient(
//                "https://util-staging.kiplepay.com/cashier/v1/api/platform-cashier",
//                SandBox.APP_ID, SandBox.privateKey,"json","utf-8",
//                SandBox.kiplePayPublic,
//                SandBox.sign_type);
//        //AlipayTradeWapPayRequest
//        ///
//        KiplePayTradePagePayRequest request = new KiplePayTradePagePayRequest();
//        request.setApiVersion("1.0.0");
//        paraMap.put("platform",1);
//        request.setBizContent(JSON.toJSONString(paraMap));
//        request.setToken("e09379c3f00244699b4e2f597f68dc63");
//        System.out.println(JSONObject.toJSONString(kiplePayClient.execute(request,"post")));
//    }

    public static void nonKipleNgUser(Map<String,Object> paraMap) throws KiplePayApiException{

        KiplePayClient kiplePayClient = new DefaultKiplePayClient(
                "https://util-staging.kiplepay.com/cashier/v1/api/cashier",
                SandBox.APP_ID, SandBox.privateKey,"json","utf-8",
                SandBox.kiplePayPublic,
                SandBox.sign_type);
        //AlipayTradeWapPayRequest
        ///cashier/v1/api/platform-cashier
        KiplePayTradePagePayRequest request = new KiplePayTradePagePayRequest();
        request.setApiVersion("1.0.0");
        request.setBizContent(JSON.toJSONString(paraMap));
        paraMap.put("platform",2);
//        request.setToken("f58eea0ed2204fd2baebd352d4a504fc");
        System.out.println(JSONObject.toJSONString(kiplePayClient.execute(request,"post")));
    }
}
