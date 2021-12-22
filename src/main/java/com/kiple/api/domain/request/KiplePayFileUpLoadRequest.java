package com.kiple.api.domain.request;

import com.kiple.api.payment.KiplePayHashMap;
import com.kiple.api.payment.PostTypeEnums;

/**
 * @classname: AlipayTradePagePayRequest
 * @description: 描述
 * @author: koe.zhang
 * @date: 2020/6/17 16:14
 */
public class KiplePayFileUpLoadRequest extends KiplePayBaseRequest {

	private String[] filesPath;

	public String[] getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String[] filesPath) {
		this.filesPath = filesPath;
	}

	@Override
	public String getPostType() {
		return PostTypeEnums.POST_MULTIPART_FORM_DATA.getType();
	}

}
