package com.kiple.api.test;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * @author: kiple
 */
public class KiplePayHostPageBindWalletSampleSandbox {
    // #STEP1
    // 3rd party generates priviate key and public key, share the public key to Kiple

    // Use openssl to generate private key and public key
    // OpenSSL> genrsa -out rsa_private_key.pem 2048
    // OpenSSL> pkcs8 -topk8 -inform PEM -in rsa_private_key.pem -outform PEM -nocrypt -out rsa_private_key_pkcs8.pem
    // OpenSSL> rsa -in rsa_private_key.pem -pubout -out rsa_public_key.pem


    // Here is a test private key, should be replaced
    //static  String privateKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCybTWHoH8yRxCoAAW6HS+1ZbX3x/mMbHYCXwi70g3oToPNK0vUlsbFnQ3GkokXNxu2PYXKvWpclJK8Bl4UwqrtzimicSiTQslAy0olp/xpHSQNxuIWZuwUvaFSl6XPAeoCYjND8Q+eLNXPiYgQhv5Q65Y8D0tNcu9HYifQGq3s0UA8K4mTiQ3SKeiv9vG+/VgdqZFCt/FoAVRJZIsvLBQcYY1v04zwLnmzxhmAgCYm1Pq+1GALba75dIYsN1Byf3uwNuJo33CQbEd7QNFaoH6jW/q/pCHyhkFpudOlKbFNL0OpBWamr7OrLKGaCxhOKtXjqDPJJygEHOaTm+QBBftrAgMBAAECggEBAKizo3JKKRnToYcHBmMzp2E++u99r5C7GFUjn/FeKXIlkm8R6c/mg6hi15yR/nbpzxTfVLodoAxLGfYXmyrL6KuSS0Fg9rlN41WVINU74OdN+0HfcvM1ezAdIAQkiEyTBbwdN1Wc/mRTBhIFepYN3TWLmm/NEij3bBA1WWllYF9Q4aWvK52jjFIdZio6tGMUbJ12rRN9kM1jG78WyWFMrJ9AlSKDAhfR+rk0d49+GMq7LS+ddwLry71VO4SbxTWsv+PVhxt8trdyxZdV0ZJSgdzgPe1alWTORV3+ZKX5At4AM0g+dF5vkWyO9e2zttrFipP+mQgNwJCLo/TELG4i+RkCgYEA3RTJ/+m1Rs3LvP0KqVEsKqHLIrdRCOiXS1Ga3zrAOgPRNsQNNwVucSFGb/feAiXQZ4sL+EHgL6Hb7CDVuhDluGetjNVdCoMfcsU3cC4bUwFNT+XANPt/v2Ojc+gQNJIPZFby2t3iAXpxQuvGYnFd2exeuq/Ay9mWC134zoRzXY8CgYEAzpu4kmw0AVyQvMkkWewMLt5LFZ/eyFwqb65b3Lqzp8a3bYzTe7Yf3/CE/RDiD1f+4NGvXeQfULo6eS0M9hhJHcMPATv9xMNH0+FXBD4OuTR+Ojk6TAe4vhWfUExaFf38eUDe2yLON5mQTn7VY3PBSGPOCTkqs1hOOs/Of+e3zmUCgYBedvqVGeR2W4r5ttmuXVvU34svy0qZ1uCGs1jMl4r5fxYPI5hN8Ukul7xELZI0Mroo9BXqPyOu1qdk7ubD+WJe+BYE/koiKsLuRGyax2ivf/fr8sy9FtWQDuARr4/1Fo/zHN/qOzLN1Oa+ZIZBHBWw/zm+5UgpElD1jHK6s4ej6wKBgQC6Kf5JkU3a3Dleoi8rB1KC+LHXLlJjXwit0QHdBi3TQ28MOxRkKFrYQbTTAl9vS+7RTJ8jjuXYq7T8cud257MaKiRfDFtA7GTQOf9aR0ClCPOkKuSolVyVio4eoG2b21ufNiCYe+gsmSY6VHaaQjcqryN7lBMxvqPewlhjT7uDMQKBgQCyeAcpLTyD91Hvs+NdwI1xpc5RAFIsaq+0qCRsMG2FXsVyFwKdD3g2XuCwbelhc/+VMoXzslhaIBXEdrOB4J5YvsZOTRyJwbTdc1PLi6od3Gq9K1YZJYAZY7rFzucnNFE1dvBtLyg+BlA03HMoXC7gB/vVV+ub4e2vA3MtRtci5Q==";


    //maxis production
    static  String privateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCv7d4Wp2EX6hF3" +
            "5EDmXBZoPpPqzayC7FLugWNjHT2op2L1XHdLzq+7qWYl9Q+AUYZGZcrWXABJ746J" +
            "KGzOPLAYEWBEQu654vjA4ktiVhY2pgAcg3LPaLsoexHiUmwqDzaHJKII3bCK5zWJ" +
            "wXXxD3B1TOvpBYtm8gy/kV1U8/5uKw2hAZ9tiT6CdE6R1SPid8R7vqOiBknw0zAj" +
            "N9y3Q3lyCzm87hL305RUvl6dYDHj7jUQ/2xvuPllE1qpbVKev1n9VIDjutU/1Ql4" +
            "sk+j5ZjXQRQlDV/hyKbU9MLtIg5L8qct0AcUdCsRYFQIGXYM2U8XQaakWehcXitl" +
            "Nq6MZvCpAgMBAAECggEAP9SL+agtACI6yEcWjP3oW1YpEM4iwzW/OS3Uw4c+ccyZ" +
            "iPBNkEGjXPEvUKJDR4lMsIoKOBAVQr4Z9wWlxIYcVf8n8FxghS0ml8UDGOCnGpjL" +
            "/YxrwXj8Psm4SWciO3hdjlbqO1FD1oHg2ORKSHYTlVudVGRgma9ElNTHCiWksI9w" +
            "WBabU//DClRwz+7XlyyYkBl9KPpxiIsBRBvOTa/J/xWhBFrCq13Z8IMXg5XTSUT9" +
            "ZBUlFlGY90svlNukP+LEcK6KcOqvXForCNBJZ0YYO7qd4xtLSB2J8H7USoLFs9/S" +
            "wqQaGoIKcnkxJAh4cqttF28LIVQ+lvjnssxcynPFCQKBgQDVsv9c+bkuQvIw9Ag9" +
            "P/S/Zo3ggkE7LzMDiPbz/rd5crAgc3Iebu8VCydrnfbVwMDOtedzX6O25Rz3RJht" +
            "BXfuz3uNtodu+28RxuGwKIQHnnXLHM0yZXAZ4yyJ6+guFRTaKFIQF3zbfhukUoQM" +
            "Cbwj5Gl4ly1zYYPwbMXl5B78kwKBgQDSwOjWAhZ8MIb4QOQuxL8ZL8hWZsRG5wsb" +
            "Uk1JK6Mbl77WFZJcCkabVyTepGaYKIOx7GOlptMkISGzxjXCe/vbiqneJSgzGEMB" +
            "X6d41flKZ0CIr3Aa2E1q7Dfsw+ldDYdy3B5qwwrEFQbvbq5ofhvmoT17+T3FHFzL" +
            "2CmlE63fUwKBgBgVdkYqNr6J+PWfaxZMEO9JRZAZ3fQnm544X2ycNl1PmZ3upNvd" +
            "MMC0Yxrz0I/Ci3hAc/dCvhast5j+eBdBWITniUstWRIe8COWwHSz/D1HlIrxkgat" +
            "+/ZO7gQVPDOFWLZFeQorO30ZN1BLpGoe5r88zM40a4DnapE8EQmZrgxVAoGASUP0" +
            "7Yj2RiAcm6r7aGLPSDM7QwYgz8E8NDhNU9WOs2XyWrxPEB4mY5RM5fVjERxsg+r4" +
            "zTqMhi/aZoPOZpjxemXfHbHvIqwZhx24u6IvMrVoUtwibcKqTzRlULLeqpGsd7qb" +
            "/GNnl7jzm0Lv8LV+f5OoKzP/TEnK3LZ967xGoq8CgYBB+dn0N1kj5gECtiw2Ngua" +
            "h+hMRQz1iqoRgo/lhCy0NXh5RGXfj/slMPnt6pQsRNmipUrQ+ff8nbDdi5VdFXSH" +
            "zPs+AgSkuixIz1Grpmh4MlxTGEK/1P9QctlcgbeLNczTrTj1Mm6oEnLez66Y1ALd" +
            "77KMwc1fhPDbUoe7Ghb80Q==";

    // #STEP 2
    // Get the application id from Kiple
    // Here is a test application id, should be replaced
    //static String app_id="4abb3c95e4ad10677e5fb9c9fdf54b77";

    // maxis production
    static  String app_id = "418276b7f774b0ae0ae012e1d0c10d6a";

    public static void main(String[] args) throws Exception {

        // Test API URL, this API is not ready, so will get the response :
        // {"time":1630482711741,"msg":"invaild token","code":80601}

      //  String url = "https://sandbox-api-ewallet.maxis.com.my/cashier/api/v1.0/checkout";
        String url = "http://47.254.245.66:10891/cashier/api/v1.0/host-page/bind-wallet";
        //https://sandbox-cashier.ewallet.maxis.com.my
        // Construct the common paramaters
        String charset="utf-8";
        String format="json";
        String sign_type="RSA2";
        String version="1.0.0";
        String sign;
        long time = System.currentTimeMillis();
        String commonParam = "app_id="+app_id+"&charset="+charset+"&format="+format+"&sign_type="+sign_type+"&timestamp="+time+"&version="+version;
        // Business content
        //{​​​​​"did":"132654504449","email":"test@google.com","merchant_id":"90000100","mobile":"602346789"}​​​​​
        int i = (int)(Math.random()*100+1);
        String merchantOrderNumber = String.valueOf(System.currentTimeMillis()+i);
        String biz_content = "{\"time_expire\":\"36000\",\"app_name\":\"Select\",\"third_part_id\":\"aaaaaaaaaaaaaaaaaaa\",\"return_url\": \"http://www.baidu.com\"}";
        // sort all the parameters
        Map<String, String> sortedParam = new HashMap<>();
        sortedParam.put("app_id", app_id);
        sortedParam.put("charset", charset);
        sortedParam.put("format", format);
        sortedParam.put("sign_type", sign_type);
        sortedParam.put("timestamp", String.valueOf(time));
        sortedParam.put("version", version);
        sortedParam.put("biz_content", biz_content);
        String params = getSortContent(sortedParam);
        sign = doSign(params, charset, privateKey);
        sign = URLEncoder.encode(sign,"UTF-8");
        // Send request
        GetFormHtml(commonParam,biz_content,sign,url);
    }
    /***
     *  build the form html
     * @param commonParam
     * @param bizParam
     * @param sign
     * @param urls
     * @return
     * @throws IOException
     */
    public static String GetFormHtml(String commonParam, String bizParam, String sign, String urls) throws IOException {
        try {
            URL url = new URL(urls+"?"+commonParam+"&sign="+sign);
            Map<String,String> applicationParameters = new HashMap<>();
            applicationParameters.put("biz_content",bizParam);
            String html = buildForm(url.toString(), applicationParameters);
            System.out.println("form html:\n"+html);
            return html;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * Sort the content
     * @param sortedParams
     * @return
     */
    public static String getSortContent(Map<String, String> sortedParams) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (String key : keys) {
            String value = sortedParams.get(key);
            if (!"".equals(key) && !"".equals(value)) {
                content.append(index == 0 ? "" : "&").append(key).append("=").append(value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * Do signature
     * @param content
     * @param charset
     * @param privateKey
     * @return
     * @throws Exception
     */
    protected static String doSign(String content, String charset, String privateKey) throws Exception {
        // "RSA"
        PrivateKey priKey = getPrivateKeyFromPKCS8(privateKey);
        Signature signature = Signature.getInstance("SHA256WithRSA");

        signature.initSign(priKey);

        if ("".equals(charset)) {
            signature.update(content.getBytes());
        } else {
            signature.update(content.getBytes(charset));
        }
        byte[] signed = signature.sign();
        return new String(Base64.getEncoder().encode(signed));
    }

    /**
     * Get private key from PKCS8
     * @param base64PrivateKey
     * @return
     */
    public static PrivateKey getPrivateKeyFromPKCS8(String base64PrivateKey) {
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    /**
     *  build the form
     * @param baseUrl
     * @param parameters
     * @return
     */
    public static String buildForm(String baseUrl, Map<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<form name=\"payment_form\" method=\"post\" action=\"");
        sb.append(baseUrl);
        sb.append("\">\n");
        sb.append(buildHiddenFields(parameters));

        sb.append("<input type=\"submit\" value=\"submit pay\" style=\"display:none\" >\n");
        sb.append("</form>\n");
        sb.append("<script>document.forms[0].submit();</script>");
        String form = sb.toString();
        return form;
    }
    private static String buildHiddenFields(Map<String, String> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Set<String> keys = parameters.keySet();
        for (String key : keys) {
            String value = parameters.get(key);
            // Remove the null value in the parameter
            if (key == null || value == null) {
                continue;
            }
            sb.append(buildHiddenField(key, value));
        }
        String result = sb.toString();
        return result;
    }
    private static String buildHiddenField(String key, String value) {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type=\"hidden\" name=\"");
        sb.append(key);

        sb.append("\" value=\"");
        //escape double quotes
        String a = value.replace("\"", "&quot;");
        sb.append(a).append("\">\n");
        return sb.toString();
    }
}
