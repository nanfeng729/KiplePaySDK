package com.kiple.api.maxis;

import com.kiple.api.utils.sign.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
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
public class KiplePayHostPagePaymentSample {
    // #STEP1
    // 3rd party generates priviate key and public key, share the public key to Kiple

    // Use openssl to generate private key and public key
    // OpenSSL> genrsa -out rsa_private_key.pem 2048
    // OpenSSL> pkcs8 -topk8 -inform PEM -in rsa_private_key.pem -outform PEM -nocrypt -out rsa_private_key_pkcs8.pem
    // OpenSSL> rsa -in rsa_private_key.pem -pubout -out rsa_public_key.pem


    // Here is a test private key, should be replaced
    //static  String privateKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCybTWHoH8yRxCoAAW6HS+1ZbX3x/mMbHYCXwi70g3oToPNK0vUlsbFnQ3GkokXNxu2PYXKvWpclJK8Bl4UwqrtzimicSiTQslAy0olp/xpHSQNxuIWZuwUvaFSl6XPAeoCYjND8Q+eLNXPiYgQhv5Q65Y8D0tNcu9HYifQGq3s0UA8K4mTiQ3SKeiv9vG+/VgdqZFCt/FoAVRJZIsvLBQcYY1v04zwLnmzxhmAgCYm1Pq+1GALba75dIYsN1Byf3uwNuJo33CQbEd7QNFaoH6jW/q/pCHyhkFpudOlKbFNL0OpBWamr7OrLKGaCxhOKtXjqDPJJygEHOaTm+QBBftrAgMBAAECggEBAKizo3JKKRnToYcHBmMzp2E++u99r5C7GFUjn/FeKXIlkm8R6c/mg6hi15yR/nbpzxTfVLodoAxLGfYXmyrL6KuSS0Fg9rlN41WVINU74OdN+0HfcvM1ezAdIAQkiEyTBbwdN1Wc/mRTBhIFepYN3TWLmm/NEij3bBA1WWllYF9Q4aWvK52jjFIdZio6tGMUbJ12rRN9kM1jG78WyWFMrJ9AlSKDAhfR+rk0d49+GMq7LS+ddwLry71VO4SbxTWsv+PVhxt8trdyxZdV0ZJSgdzgPe1alWTORV3+ZKX5At4AM0g+dF5vkWyO9e2zttrFipP+mQgNwJCLo/TELG4i+RkCgYEA3RTJ/+m1Rs3LvP0KqVEsKqHLIrdRCOiXS1Ga3zrAOgPRNsQNNwVucSFGb/feAiXQZ4sL+EHgL6Hb7CDVuhDluGetjNVdCoMfcsU3cC4bUwFNT+XANPt/v2Ojc+gQNJIPZFby2t3iAXpxQuvGYnFd2exeuq/Ay9mWC134zoRzXY8CgYEAzpu4kmw0AVyQvMkkWewMLt5LFZ/eyFwqb65b3Lqzp8a3bYzTe7Yf3/CE/RDiD1f+4NGvXeQfULo6eS0M9hhJHcMPATv9xMNH0+FXBD4OuTR+Ojk6TAe4vhWfUExaFf38eUDe2yLON5mQTn7VY3PBSGPOCTkqs1hOOs/Of+e3zmUCgYBedvqVGeR2W4r5ttmuXVvU34svy0qZ1uCGs1jMl4r5fxYPI5hN8Ukul7xELZI0Mroo9BXqPyOu1qdk7ubD+WJe+BYE/koiKsLuRGyax2ivf/fr8sy9FtWQDuARr4/1Fo/zHN/qOzLN1Oa+ZIZBHBWw/zm+5UgpElD1jHK6s4ej6wKBgQC6Kf5JkU3a3Dleoi8rB1KC+LHXLlJjXwit0QHdBi3TQ28MOxRkKFrYQbTTAl9vS+7RTJ8jjuXYq7T8cud257MaKiRfDFtA7GTQOf9aR0ClCPOkKuSolVyVio4eoG2b21ufNiCYe+gsmSY6VHaaQjcqryN7lBMxvqPewlhjT7uDMQKBgQCyeAcpLTyD91Hvs+NdwI1xpc5RAFIsaq+0qCRsMG2FXsVyFwKdD3g2XuCwbelhc/+VMoXzslhaIBXEdrOB4J5YvsZOTRyJwbTdc1PLi6od3Gq9K1YZJYAZY7rFzucnNFE1dvBtLyg+BlA03HMoXC7gB/vVV+ub4e2vA3MtRtci5Q==";


    //maxis production
    static String privateKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDVFMpNhIVM8WttofQK01C9zRRDjmu2vUhekautWAtlWG5DV6PKTDb9/xlFal9/17eCZvcM0LiYlz58E0/Wo83277lHBpcSkX1bU74+VjnqlXN6BbSP9ppN3N/aBuRaoGaYdttZKkYRMg2J0lLdaQ9c+MQbwLb1lkDOQVWufroO/MbgqY/KE8FhCEMaTxBBYoV9n23tPslNlK4r1NinkDCo0Rm/YUBfqWGqXLHuxOE98PRbSaTXplV6j/Rm7cZZuHrlooYAJIVFtgo61jU+5pY6YaPzaGgmfPnTbGACIOm5rglG6OepBSPvjhVT8nPAtYMTq0+tRkqaLKk4pnwyQQ3tAgMBAAECggEBAMOmo1sqI3vS8OFpo7uM7MZaWBDv5NeWVaz/9LLl9RDJcfVDs5E9CgGPCpVCZw3r9QQ3pwCNZfI2bZlf4h8jqSrkUyt0UaMrE/+XlUeHxQYw1CdmtbS6qlR0StbXHTZZsdqsyBvHRS7k1qmGBbBnl0O259rTySsXG8pHE3V1x0GaC7TSVor3vrXcMPsczbE5Jg0yp4bBScTu+q9vyScGYjC3lR2e3TtlmnOq+f8glG2O2FxInhUkdTAFttyn9Qf/jeBlznaP3B1kJW8Oi6HIANkKBB5Wev84PPSRTCYLg3TxoCdrCU1tsLScdOB6NJWsmDeYb1OSzz0OCG5WfeqjJ8ECgYEA77C4vzcMS/Tjc3RzDkXzlV4LYBRTGQXYgJfz7DewjkqMMdwO7zxKMQmBVTHRzcE+A32fUkw+urYflz2ZMHEit/dbN2CRWYQbKdWdvVYJm4U3L7Hp4N0HcyuDl9Jo2B+22JHbxG/p/IRzjnKDIyW4u3AqtuR+mMFLVw1ldo32UxcCgYEA45SNUgyapXzo83z152BY/U0lbNQvwNyyT1h6yVqApD5wt1WFY5vWn2bopS+7ymQc5WIemtdbTv1dWAvcx81FSACBPKtKH6br7q0Dq8Z5UWus4fG+IaN/euTWMlb2CZFMqSW5APMJDlwkFX54JFzE5+J2JzKMDlnPiDrHIUWsmZsCgYBlYZJDDr2cwxARnYes2dmVrBmTogUj6F0SxzLdDxEWnmF+FV4bwpx+8U5LAq+DH+FjCJeC78z4nF/wUJWi77eHqJBkc+wGuOHkZ/44P1QA++/DtLT/731fMWCPrue5wnBMWiViiROnQz69WaIuE553aZInma4SXm/HelsYMiOmMQKBgEemKEvxrep2Bf1KlTywJ7Qvyp2VvCcTFIxIhffIp4GOKvx9T06J7KsowgvnmE7i7/oDxzDmplebCv1CVtMMvEgr6IRKRT7r1ZHCJMq65M0OglJsQnjiuareStV0bWebuC/0sWcsHOjJd47Qz1BbaJ68vIggsNl/ywfPwYunHO0LAoGASm2EtSESqkk1OIn0cw0suYt8I9CEEZSOimNnp05HIut5bBsjcLjv3bHhIRjMKjHoCmB6wRFiE5KZWNvNB/MEPbLNfJriRUIfThRbpjFysB5af6bIAxKQ1O/g7A3RCeERXCVXKT/0+HqvUHIESnJZx57IwxFMsZP48W1OmfdEtzY=";

    // #STEP 2
    // Get the application id from Kiple
    // Here is a test application id, should be replaced
    //static String app_id="4abb3c95e4ad10677e5fb9c9fdf54b77";

    // maxis production
    static  String app_id = "fb13eaba18cb5b94ab45cc315c622c46";

    public static void main(String[] args) throws Exception {

        // Test API URL, this API is not ready, so will get the response :
        // {"time":1630482711741,"msg":"invaild token","code":80601}

      //  String url = "https://sandbox-api-ewallet.maxis.com.my/cashier/api/v1.0/checkout";
        String url = "https://staging-cashier.ewallet.maxis.com.my/cashier/api/v1.0/checkout";
        //https://sandbox-cashier.ewallet.maxis.com.my
        // Construct the common paramaters
        String charset="utf-8";
        String format="json";
        String sign_type="RSA2";
        String version="1.0.0";
        String sign;
        long l = System.currentTimeMillis();
        long time = l;
        String commonParam = "app_id="+app_id+"&charset="+charset+"&format="+format+"&sign_type="+sign_type+"&timestamp="+time+"&version="+version;
        // Business content
        //{​​​​​"did":"132654504449","email":"test@google.com","merchant_id":"90000100","mobile":"602346789"}​​​​​
        int i = (int)(Math.random()*100+1);
        String merchantOrderNumber = "1420238166434005345"+i;
//        String biz_content = "{\"merchant_id\": 90000104,\"order_ref\": \""+merchantOrderNumber+"\",\"order_amount\": 110,\"did\": \"9939\",\"ip_address\":\"192.168.1.131\",\"mobile\": \"60299393900\",\"merchant_data\": \"busytype_type:001\",\"notify_url\": \"http://192.168.1.162:8082/kiplepay/payment/server/notification\",\"return_url\": \"http://192.168.1.162:8082/kiplepay/payment/notification\"}";
        String biz_content = "{\"merchant_id\": 90000106,\"order_ref\": \""+merchantOrderNumber+"\",\"order_amount\": 4000,\"did\": \"\",\"ip_address\":\"192.168.1.131\",\"mobile\": \"609949994900\",\"merchant_data\": \"busytype_type:001\",\"notify_url\": \"http://192.168.1.162:8082/kiplepay/payment/server/notification\",\"return_url\": \"http://192.168.1.162:8082/kiplepay/payment/notification\"}";
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
        return new String(Base64.encodeBase64(signed));
    }

    /**
     * Get private key from PKCS8
     * @param base64PrivateKey
     * @return
     */
    public static PrivateKey getPrivateKeyFromPKCS8(String base64PrivateKey) {
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(base64PrivateKey.getBytes()));
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
        java.lang.StringBuffer sb = new StringBuffer();
        sb.append("<form name=\"payment_form\" method=\"post\" action=\"");
        sb.append(baseUrl);
        sb.append("\">\n");
        sb.append(buildHiddenFields(parameters));

        sb.append("<input type=\"submit\" value=\"submit pay\" style=\"display:none\" >\n");
        sb.append("</form>\n");
        sb.append("<script>document.forms[0].submit();</script>");
        java.lang.String form = sb.toString();
        return form;
    }
    private static String buildHiddenFields(Map<String, String> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return "";
        }
        java.lang.StringBuffer sb = new StringBuffer();
        Set<String> keys = parameters.keySet();
        for (String key : keys) {
            String value = parameters.get(key);
            // Remove the null value in the parameter
            if (key == null || value == null) {
                continue;
            }
            sb.append(buildHiddenField(key, value));
        }
        java.lang.String result = sb.toString();
        return result;
    }
    private static String buildHiddenField(String key, String value) {
        java.lang.StringBuffer sb = new StringBuffer();
        sb.append("<input type=\"hidden\" name=\"");
        sb.append(key);

        sb.append("\" value=\"");
        //escape double quotes
        String a = value.replace("\"", "&quot;");
        sb.append(a).append("\">\n");
        return sb.toString();
    }
}
