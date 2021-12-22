package com.kiple.api.payment;

import com.alibaba.fastjson.JSON;
import com.kiple.api.domain.RequestParametersHolder;
import com.kiple.api.domain.request.KiplePayFileUpLoadRequest;
import com.kiple.api.domain.request.KiplePayRequest;
import com.kiple.api.domain.response.KiplePayResponse;
import com.kiple.api.exception.KiplePayApiException;
import com.kiple.api.utils.StringUtils;
import com.kiple.api.utils.WebUtils;
import com.kiple.api.utils.sign.KiplePayLogger;
import com.kiple.api.utils.sign.KiplePaySignature;

import java.io.IOException;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

/**
 * @projectname:KiplePaySDK
 * @author: seven.zhang
 * @date: 2020/6/3 17:26
 * @desc:
 */
public class DefaultKiplePayClient implements KiplePayClient {
    private String serverUrl;
    private String appId;
    private String privateKey;


    private String format         = KiplePayConstants.FORMAT_JSON;

    private String signType       = KiplePayConstants.SIGN_TYPE_RSA2;

    private String encryptType    = KiplePayConstants.ENCRYPT_TYPE_AES;

    private String encryptKey;

    private String kiplePayPublicKey;

    private String charset;

    private int    connectTimeout = 30000;

    private int    readTimeout    = 60000;

    private String proxyHost;
    private int    proxyPort;


    static {
        //清除安全设置
        Security.setProperty("jdk.certpath.disabledAlgorithms", "");
    }
    public DefaultKiplePayClient(String serverUrl, String appId, String privateKey) {
        this.serverUrl = serverUrl;
        this.appId = appId;
        this.privateKey = privateKey;
    }
    public DefaultKiplePayClient(String serverUrl, String appId, String privateKey, String format) {
        this(serverUrl, appId, privateKey);
        this.format = format;
    }

    public DefaultKiplePayClient(String serverUrl, String appId, String privateKey, String format,
                               String charset) {
        this(serverUrl, appId, privateKey, format);
        this.charset = charset;
    }

    public DefaultKiplePayClient(String serverUrl, String appId, String privateKey, String format,
                               String charset, String kiplePayPublicKey) {
        this(serverUrl, appId, privateKey, format, charset);
        this.kiplePayPublicKey = kiplePayPublicKey;
    }

    public DefaultKiplePayClient(String serverUrl, String appId, String privateKey, String format,
                               String charset, String kiplePayPublicKey, String signType) {
        this(serverUrl, appId, privateKey, format, charset, kiplePayPublicKey);
        this.signType = signType;
    }

    public DefaultKiplePayClient(String serverUrl, String appId, String privateKey, String format,
                               String charset, String kiplePayPublicKey, String signType,
                               String proxyHost, int proxyPort) {
        this(serverUrl, appId, privateKey, format, charset, kiplePayPublicKey, signType);
    }

    public DefaultKiplePayClient(String serverUrl, String appId, String privateKey, String format,
                               String charset, String kiplePayPublicKey, String signType,
                               String encryptKey, String encryptType) {
        this(serverUrl, appId, privateKey, format, charset, kiplePayPublicKey, signType);
        this.encryptKey = encryptKey;
        this.encryptType = encryptType;
    }
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public void setKiplePayPublicKey(String kiplePayPublicKey) {
        this.kiplePayPublicKey = kiplePayPublicKey;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public KiplePayResponse execute(KiplePayRequest request,String httpMethod) throws KiplePayApiException {
        RequestParametersHolder requestHolder = getRequestHolderWithSign(request);
        String postUrl = getRequestUrl(requestHolder);
        if (KiplePayLogger.isBizDebugEnabled()) {
            KiplePayLogger.logBizDebug(getRequestUrl(requestHolder));
        }
        String rsp = null;
        if ("GET".equalsIgnoreCase(httpMethod)){
            String geturl  = getRedirectUrl(requestHolder);
            try {
                rsp = WebUtils.doGet(geturl, null,request.getHeader());
            } catch (IOException e) {
                throw new KiplePayApiException(e);
            }
        }else{
            try {
                rsp = WebUtils.doPost(postUrl, requestHolder, charset,
                        connectTimeout, readTimeout, proxyHost, proxyPort,request.getPostType());
            } catch (IOException e) {
                KiplePayResponse<String> kiplePayResponse = new KiplePayResponse();
                kiplePayResponse.setMsg(e.getMessage());
                kiplePayResponse.setData(e.fillInStackTrace().getMessage());
                return kiplePayResponse;
            }
        }
        try{
            return JSON.parseObject(rsp,KiplePayResponse.class);
        }catch (Exception e){
            KiplePayResponse response = new KiplePayResponse();
            response.setCode(-1);
            response.setData(rsp);
            response.setMsg(e.getMessage());
            return response;
        }
    }

    @Override
    public String generatePaymentUrl(KiplePayRequest request) throws KiplePayApiException {
        RequestParametersHolder requestHolder = getRequestHolderWithSign(request);
        if (KiplePayLogger.isBizDebugEnabled()) {
            KiplePayLogger.logBizDebug(getRequestUrl(requestHolder));
        }
        return getRedirectUrl(requestHolder);
    }

    /**
     * @description: append the request url POST
     * @auther: seven
     * @date: 2020/6/8 11:31
     * @param requestHolder :
     * @return : java.lang.String
     */
    private String getRequestUrl(RequestParametersHolder requestHolder) throws KiplePayApiException {

        StringBuffer urlSb = new StringBuffer(serverUrl);
        try {
            String sysMustQuery = WebUtils.buildQuery(requestHolder.getProtocalMustParams(),
                    charset);
            String sysOptQuery = WebUtils.buildQuery(requestHolder.getProtocalOptParams(), charset);

            urlSb.append("?");
            urlSb.append(sysMustQuery);
            if (sysOptQuery != null & sysOptQuery.length() > 0) {
                urlSb.append("&");
                urlSb.append(sysOptQuery);
            }
        } catch (IOException e) {
            throw new KiplePayApiException(e);
        }

        return urlSb.toString();
    }
    private String getRedirectUrl(RequestParametersHolder requestHolder) throws KiplePayApiException {
        StringBuilder urlSb = new StringBuilder(serverUrl);
        try {
            Map<String, String> sortedMap = KiplePaySignature.getSortedMap(requestHolder);
            String sortedQuery = WebUtils.buildQuery(sortedMap, charset);
            urlSb.append("?");
            urlSb.append(sortedQuery);
        } catch (IOException e) {
            throw new KiplePayApiException(e);
        }

        return urlSb.toString();
    }

    public void setFormat(String format) {
        this.format = format;
    }

/*######################################construct the request ###########################################*/

private <T extends KiplePayResponse> RequestParametersHolder getRequestHolderWithSign(KiplePayRequest request) throws KiplePayApiException {
    RequestParametersHolder requestHolder = new RequestParametersHolder();
    KiplePayHashMap appParams = new KiplePayHashMap(request.getTextParams());

    //if file upload ,then add filepath
    if(request instanceof KiplePayFileUpLoadRequest){
        requestHolder.setFilesPath(((KiplePayFileUpLoadRequest)request).getFilesPath());
    }
    requestHolder.setApplicationParams(appParams);

    if (StringUtils.isEmpty(charset)) {
        charset = KiplePayConstants.CHARSET_UTF8;
    }
    KiplePayHashMap protocalMustParams = new KiplePayHashMap();
    protocalMustParams.put(KiplePayConstants.VERSION, request.getApiVersion());
    protocalMustParams.put(KiplePayConstants.APP_ID, this.appId);
    protocalMustParams.put(KiplePayConstants.SIGN_TYPE, this.signType);
//    protocalMustParams.put(KiplePayConstants.NOTIFY_URL, request.getNotifyUrl());
//    protocalMustParams.put(KiplePayConstants.RETURN_URL, request.getReturnUrl());
    protocalMustParams.put(KiplePayConstants.TOKEN, request.getToken());
    protocalMustParams.put(KiplePayConstants.CHARSET, charset);

    Long timestamp = System.currentTimeMillis();
    DateFormat df = new SimpleDateFormat(KiplePayConstants.DATE_TIME_FORMAT);
    df.setTimeZone(TimeZone.getTimeZone(KiplePayConstants.DATE_TIMEZONE));
//    protocalMustParams.put(KiplePayConstants.TIMESTAMP, df.format(new Date(timestamp)));
    protocalMustParams.put(KiplePayConstants.TIMESTAMP,String.valueOf(timestamp));
    requestHolder.setProtocalMustParams(protocalMustParams);
    KiplePayHashMap protocalOptParams = new KiplePayHashMap();
    protocalOptParams.put(KiplePayConstants.FORMAT, format);
    requestHolder.setProtocalOptParams(protocalOptParams);

    if (!StringUtils.isEmpty(this.signType)) {

        String signContent = KiplePaySignature.getSignatureContent(requestHolder);
        String sign=KiplePaySignature.rsaSign(signContent, privateKey, charset, this.signType) ;
        protocalMustParams.put(KiplePayConstants.SIGN,sign);
//
    } else {
        protocalMustParams.put(KiplePayConstants.SIGN, "");
    }

    requestHolder.setHeaderMap(request.getHeader());
    return requestHolder;
    }
}
