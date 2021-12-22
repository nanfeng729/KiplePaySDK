package com.kiple.api.utils.sign;

import com.alibaba.fastjson.JSONObject;
import com.kiple.api.domain.request.KiplePayFileUpLoadRequest;
import com.kiple.api.domain.response.KiplePayResponse;
import com.kiple.api.exception.KiplePayApiException;
import com.kiple.api.payment.DefaultKiplePayClient;
import com.kiple.api.payment.KiplePayClient;
import org.junit.Test;

import java.io.*;

/**
 *
 */
public class UploadFileTest {

    public static class SandBox {
        //        public static final String GATEWAY = "http://localhost/web/verify/verifysign";

        public static final String APP_ID = "2019051064521003";
        /**###################merchant#############################*/
        //private static final String privateKey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCZM1OD2ZtfcGAG2R/qv6QpuhECCFNXgN23+eUgu0fwolR0tNs97mxuSEdeAfgjiSmdlGhF1ipRzs5TcTxyuJXIdgOo8k7gj+nW2SMQSQ0fGBTAXLgJqZqe5IFvZYJWeoYCbMmUWvKET0JJkcM6wM73vqoJ0QLHnnpRV6zqihWrjqMmn8mbbryRHYzm4fMvXqUkisQRNpPCBuTtHCYk1BmNRDpkrwuAhphgME13Amzx/D58Q6mDP54ZuvTRQMvs1RSvYxFEX4D4fLg+gYmfJoeyVmA69Vp36IacSg2/DX4tiMk/+yww+L6s63bBPfxdv14Zye5UPrm/lx9lD5X+/HVDAgMBAAECggEAVI3Se0QAEBXKuhzeE8c/yNPsDS4hKXtzY9YlkwIqe3JsoJktJCpSaC+2S/lURfCvKky9nEXOx6YXlFA8J6ke6ivQa0+JH0HusujXBGb9OfslgOHrpVGZiDbgBWaGIEqxaec2/o2PqlzAXjB4CxZdZLkzygU0vo4GPpzgT/Z0bWGKMJu+qboPm5Tu4qfgl6sweISNrlArJrMrox6uX3T5SuAHt/JlF87RV+MBPhhYdKpz08YbLtN1ERKLZniAt/41TH6/ugCXXeVSO+r7/zof0nTc3v7OJgFnZwqfkAl8q0NzV+9UD+HBU1WedI4LM/dwRkmLaMTrRl4lmQkvitDM8QKBgQDTfsYSdXohD1IF573O+Ccoppv8ONbR/lr03B7cepuxZIQ7otLT1Xyrz949WJwwIaGfZnXBoMqY1mfzuysKmCJI0zaH+ZgeRdNM4K7boEc4nAyk2ncULJL5OsirwaG3utqWrxM74tsRHhlhKSdk7U1AUrgBPyCPdvWBki5RIjtyyQKBgQC5cDjJFEySPO8sYRwJNhCNUTPrVSwsPwQSRuOruszqahgc4RXXlSfhzQzQ3AIhLRrtdbDPfvxJTzBZeEr/ck3ZFTmteVi0gNE1SSJH5OlEQR+MOki/GfGLXiLmqsFqORGeK2VWdc7MPsnrHR7PbBE85jgnXtlNBJ0WT0hXbTQBqwKBgHjW1UsUrjwXadEP8KZBqPYUA8nfm/YNs2B2KHfm4fBAM0Fzrav1La9pLFy2tT7W6GaP/zYrrbYXwvtCdLwv4l7jtyOH239oe+tP8Phxol/Y4WyLmj//m9Yk3SUtlD3K2DwCOiYcXvhvzyZxgh2rbFkVKickL3FpEar/n9Ehkv/pAoGBAIu3U/Gcy7x7ELFdUWRXSc1P21B6UdfcetLSeoNPMUmFOjm4FV7i0LO+bOOIgEpC08EKJXeQ88DAW2ttyQQCOUlQRq6/eOg3DmmZeqy0elSkxIJA/8KMZ4NcH+uvyB6idPW5OJcWGAOnYzc0L5yUjcBWrQRs4Ei5Kyo9/gFxE/gVAoGBAK+W0xLvUY+H6ujqL5bXx8FUTW8rYyI5gVTvGDBP3SWi5mnewwASa3TNJTEhoS6tUIT+qaVqiXiJ6TMYzv2l5RSiU4pV2CQ3PpAobXNSI2AEedku4QyGnwkIc37NYdhgILqgUbxdUwCT+YqIFCQxpBH5jIobh2KZwEK8b6Z9SAoG";

        private static final String privateKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDLNSIXHqB7QpeAXY6Bk2NxQdZ2ilKfh1d17XpwsQgrJdd3JjEOP979aJ7SuwDTAnpK6xMUWT9tr6pe61feLYNNq7eOU9hNP+rqiKS0OE6Oa3eVJAbLE+KE6PeC2O9HmfZGLwM1T1Vd2GqEzMnRDMqHjVcs34K7LpdrRZfHgRYTbgwaUZX7V2vq+M7np4Xwj/4LLUzCsF+V2Wr0Y7mVEXnBG5U88z1bJF9SajKuchFwUmygp92VnEkm7fOy5S2oS5m/luWZEUX79e2BOcQ/uRXqhmrpMgUfDxnZk/HxjYoMVXmt4FO5cCyCVR+hG820DiAsQyBX2QRiVq6BPvAM+yF5AgMBAAECggEBAIc7Xup/6VDeKjC1EkdNLNqMdAsDVqTvztaeKeOhDMyaLGAQvIi9HMsLutaGuK+0cGo3MsVR95IpW3o7qIglRcKEym0fg001gkJ+mQ2Og0joX/aSuSqgmxjUaRNdBzBhV7GxS7NIjokboxepqY1Ds+yttRwZysiC4yXydGXClExsukjlaXyVoGZIRUQUXaXs4XUC6sXwv645KbM15kzgFWOiL0+1yZMt0F6tb2kU8Z2rQTyP5yaHEUGbgzY7z3S0rI2xf9CtuQg9+wP8Ofj2597gUv5Au5HiQwiQExe4EfosIg2ZxqLClj3uOw3dOx1lLxfgEvXWVWd0tm9y6QVI32ECgYEA659aClIDKMqvqz2C+iGzDAOr6a2W2n2534duKGu5TeDh94ql6S8nD2lOfSBP5UDFt6bsmlASj/nSFz6p7pdzPG9OGJjMWov9yxsDZWMRGJ+D3WDd3s+ZvMLZRfzaox3GNsW3cRsPJ9vYfTQio/hdpQ9K0+fzaGpkzP3sEOvZQK0CgYEA3MgeqKhcKDJTPODxt2IAP9CcKm9dyVRRUeaOyroKmKGBFGHGga37n9RFpEKWtHzcIUuqXBwMwl05UnWfVn6YeLkLyFPnLsNECJuthnOngNJsFyNHKr6GgmpCiIEVOI3cqFYfihDLlQSQL7tO7MpfoQ9tdtERUb3qo2wwdfeZYX0CgYBcbjWXKNb+cIx7I3U3BHNFekc9MxCjg4Cf9HO9PY0CxP4/6k7ta8bp38ifg0Z0S3WEduIIIvM1Ma492iI1a4oUIiHDumUn/BTCUUWCx1sUirbi6DYBSvUnPFSZhPiL1olEQUmWACRw4WhKrWINasfpkVcsS6iLxHjohY/Oj4a5PQKBgQDcSVg90+5PtRbUUWUcIk45Xf3TYVbkgJq66x5iLApSjCJsobocvemoaXYrFL2lzEcfeY27ZcldTQLawb1/4cRj/84/zWeHgxEovZv/4PmqUUnENFDX104CZd+Ir7LqwLD/zR6e9W8Leoga9/shzDJqUyhXOvba5nFtKY+YxLlnSQKBgQCzpHeK/YKUvin7wCdheJv8wHv1UStn/bp3Ys4ID/aqVVis9ihLCkb4n35/uY0s5FYAhyy2NURWJtZg8LvHO5K4jHjdg4yXkp5vLWilsRIybhwd027vk1+5OPoNCjgJnBx0LArkg6p3tEhlUZLwGfl/tX2ESvS7ImWt6GElp7tqIA==";

        private static final String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvBue0zGm7rR0X9vLNAh3QclovtFekTjc/bQYd++7J/FUY6QkVCNzvX+5/9UOJ8/AznPRPYpqwkv6/GddH4tZQZ5sNjuNzghU4SfA+VqD6bReAlG9rXY0bDcPPI0uW3y8GxccXfEQ6e0sHILE6DZtz4QpD8s/csI6mJXMmhe3dh67QF57GVEwG9ALG+pk7jnJOM//QrmiK3B2jpsixgZXOdDWlITlHbwhJUT8T49lFUb6fS5f07Oc3jtIbC2OYiCNnX6jDfQOBj2ju1LWQfCzsD0kUjW/cGXhr/ecMhFRgl/xfteg1ZAImBA8sMRTVxKSkMDl+JhxUtsZbX4HDD+SbwIDAQAB";
        /**###################kiplepay#############################*/
        private static final String kiplePayPublic="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsCCS+Tjw0qTt1nWzRtdLO1qnSKCEzqrxUW+zAUMuG93auvQn8CahCWTLe6zT3Me1W/IyzshxGSz7eKsC+FSr3us/SSMQzeW5eL+ADNbxhKe1m94KlMy5be8Q8Iqt1S9oNtRdTrGIu8J743dfx7/EPMmEpqo27I1iCUdngzMHAdv9FDtqpXqIFbtj1ybeFwBG+o4cSQ0yyv98IUYGb7mHYkeVGAET5IO2d/gF/e1B7+fVzv84BGEbsur/tvXKyrCPjerlBr9onfzDxgosSunOrIghS2Ll2WOV2OKE5iXs9Q7Rnm47tBeuP2iOQEwx/tiD+K2nxoEEh6NzuHT42MPsTQIDAQAB";
        private static final String kiplePayPrivateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCwIJL5OPDSpO3WdbNG10s7WqdIoITOqvFRb7MBQy4b3dq69CfwJqEJZMt7rNPcx7Vb8jLOyHEZLPt4qwL4VKve6z9JIxDN5bl4v4AM1vGEp7Wb3gqUzLlt7xDwiq3VL2g21F1OsYi7wnvjd1/Hv8Q8yYSmqjbsjWIJR2eDMwcB2/0UO2qleogVu2PXJt4XAEb6jhxJDTLK/3whRgZvuYdiR5UYARPkg7Z3+AX97UHv59XO/zgEYRuy6v+29crKsI+N6uUGv2id/MPGCixK6c6siCFLYuXZY5XY4oTmJez1DtGebju0F64/aI5ATDH+2IP4rafGgQSHo3O4dPjYw+xNAgMBAAECggEAXZGgo1iV0EKgvDuhd7QvGqRJlPL6O1VjiNEftz76gwF1D7dgdFwBV5094UFq+LL8bZGZXnPsaeSU4IFxvX42JOKzp2ZCqjAuPbD3FVsnykLS37CxzGWK6ZWFWVVDjqt14RMnUwRMUPu8l5a2wsblOSoSF/z72uYtvCI8It58DYbuLV79DfKC5x4JroI4qbUWFSn+Dqby2ZgMtLiWTs1gX64lO4At4SIAsHfp4ZZm/MOboYksj99k3AjBZh6wmx+7bmA8CqZxljdJVEbaMacRa/qlShoYaskh64Jks/Xy7i6L7/tyCfgQ7WP7Jz/5MtIbBqTuYVrU+Yw0HjkRKMNoAQKBgQD5zbIeiuBoD/xt+m/KpokQtx1p10yVSOQP0iAUFe92NOAdRDcZ3wADlUHfu5mIUFT5cqmTDnFlSl5xHTOJo0MsWPvUh1amiyCa4M9FPQ0hMgE1B/en0YWhvcHQRrYpgZ72uXSS6Wxbn7TrHCch5mKDKkDSBcbu4zAgo4cYh2zGTQKBgQC0fwTOKNNO8WTebq6n4lT26RT8HSyFLnDu4XF+6tinU7kIVUZeCda2X+quoGGPBxpkCD4otsMVW8AzDX14w5qpW753mTiYkexnv/FsWv1/67bno+2Srv90aerUnojlWniNbfYMiwm148JePpHOc68c6QrWbVwuLtmAItqaF9K+AQKBgAmEhdHWFsDAQyzpitLcz4Ich/ldY6SYd2AG8IPzZgFJ+HQFAlhw/64jRLRCkJH6BZLPhJh+x2FFmR2UMD+BQfDxnv60/V8xE9fy0tLfhZ2LKzKbEBuLxxynfzfxScWBE85wuHlUfXVsO1dt5ivLfFtlA708zRyS+WV09ZPArStNAoGAWN9V8WCesvce8HrVGqrRpQWdJIoE2srEJorwxEpERRdvOxAp5rG3e/L8beFgR/l6YBjC6m8a3VBg8YJJ7oV5iLw4q1DRUiY/ndIfwHeI7EyWsbXAi4IJHDvuuaQZybxjMVgcV466SEoN8TOjvWCur3B7upr8UW0WAQaoVkk2pgECgYA3/avqX/Mnjba4i/XYqQjBrrJ4+Wyji/RPPu6U4U2/CHwQTtKxLWRbBqy8wvJepSIv6WTeEFhocfOjj6Lt4dBf/BgXuHcwXCMoLCSZIRYvHgOwq+pNvANGURri+uTBwjLiH8CohfhdskW/OxmbV92iB+/UAd+/x/f9f8s3/Zlvuw==";
        private static final String sign_type="RSA2";

        public static String notify_url = "http://localhost:8080/kiplepay/kiplepay-notify-notice";

        public static String return_url = "http://localhost:8080/kiplepay/kiplepay-return-notice";


        public static final String GATEWAY = "http://47.254.245.66/wallet/api/v1.0/publish/wallet/register";
    }

    /**
     * for upload file test
     * @throws KiplePayApiException
     */
    @Test
    public void  testUploadFile() throws KiplePayApiException {

        // String key = KiplePayCertificationUtil.getPrivateKeyContent(SandBox.privateKey);
        // System.out.println(key);
        KiplePayClient kiplePayClient = new DefaultKiplePayClient(SandBox.GATEWAY,
                SandBox.APP_ID, SandBox.privateKey,"json","utf-8",
                SandBox.kiplePayPublic,
                SandBox.sign_type);
        KiplePayFileUpLoadRequest request = new KiplePayFileUpLoadRequest();

        request.setApiVersion("1.0.0");

        //上传文件路径，支持多个文件上传
        request.setFilesPath(new String[]{"face_video,C:\\Users\\ray\\images\\malai\\hc-2.mp4"});

        //参数
        request.setBizContent("{\"device_id\":\"9856655\",\"device_model\":\"098765\",\"ip_address\":\"192.168.1.1\"" +
                ",\"latitude\":\"263\",\"longitude\":\"2543\"}");
        request.setToken("c5bb30ba8b454f688fda40986c321520");
        long start_time = System.currentTimeMillis();
        final KiplePayResponse<String> kiplePayResponse = kiplePayClient.execute(request,"POST");
        System.out.println(JSONObject.toJSONString(kiplePayResponse));
        System.out.println(System.currentTimeMillis()-start_time);
    }


    public static void byteToFile(byte[] contents, String filePath) {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream output = null;
        try {
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(contents);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            // 获取文件的父路径字符串
            File path = file.getParentFile();
            if (!path.exists()) {
                boolean isCreated = path.mkdirs();
            }
            fos = new FileOutputStream(file);
            // 实例化OutputString 对象
            output = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                output.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                fos.close();
                output.close();
            } catch (IOException e0) {
                e0.printStackTrace();
            }
        }
    }
}
