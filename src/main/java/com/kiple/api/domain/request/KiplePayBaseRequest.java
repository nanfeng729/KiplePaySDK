package com.kiple.api.domain.request;

import com.kiple.api.domain.KiplePayObject;
import com.kiple.api.payment.KiplePayHashMap;
import com.kiple.api.utils.StringUtils;

import java.util.Map;

/**
 * @classname: AlipayTradePagePayRequest
 * @description: 描述
 * @author: koe.zhang
 * @date: 2020/6/17 16:14
 */
public abstract class KiplePayBaseRequest implements KiplePayRequest {

	private KiplePayHashMap udfParams; // add user-defined text parameters

	private KiplePayHashMap headerMap = new KiplePayHashMap(); // add user-defined text parameters

	private String apiVersion="1.0";

	private String token;

	/**
	* 统一收单下单并支付页面接口
	 */
	private String bizContent;

	private String notifyUrl;
	private String returnUrl;
	private boolean needEncrypt=false;
	private KiplePayObject bizModel=null;

	@Override
	public String getNotifyUrl() {
		return this.notifyUrl;
	}

//	@Override
//	public void setNotifyUrl(String notifyUrl) {
//		this.notifyUrl = notifyUrl;
//	}

	@Override
	public String getReturnUrl() {
		return this.returnUrl;
	}

//	@Override
//	public void setReturnUrl(String returnUrl) {
//		this.returnUrl = returnUrl;
//	}

	public void setBizContent(String bizContent) {
		this.bizContent = bizContent;
	}

	public String getBizContent() {
		return this.bizContent;
	}


	@Override
	public String getApiVersion() {
		return this.apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}


	@Override
	public Map<String, String> getTextParams() {
		KiplePayHashMap txtParams = new KiplePayHashMap();
		if(!StringUtils.isEmpty(this.bizContent)){
			txtParams.put("biz_content", this.bizContent);
		}
		if(udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public void putOtherTextParam(String key, String value) {
		if(this.udfParams == null) {
			this.udfParams = new KiplePayHashMap();
		}
		this.udfParams.put(key, value);
	}

    public boolean isNeedEncrypt() {
    
      return this.needEncrypt;
    }


    public void setNeedEncrypt(boolean needEncrypt) {
    
         this.needEncrypt=needEncrypt;
    }
    
    @Override
	public KiplePayObject getBizModel() {
    
      return this.bizModel;
    }


    public void setBizModel(KiplePayObject bizModel) {
    
         this.bizModel=bizModel;
    }

	@Override
	public void addHeader(String key, String value) {
		headerMap.put(key,value);
	}

	@Override
	public KiplePayHashMap getHeader() {
		return headerMap;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
