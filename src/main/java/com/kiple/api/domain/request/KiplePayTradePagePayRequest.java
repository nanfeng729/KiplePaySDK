package com.kiple.api.domain.request;

import java.util.Map;


import com.kiple.api.domain.KiplePayObject;
import com.kiple.api.payment.KiplePayHashMap;
import com.kiple.api.payment.PostTypeEnums;

/**
 * @classname: AlipayTradePagePayRequest
 * @description: 描述
 * @author: seven.zhang
 * @date: 2020/6/3 18:01
 */
public class KiplePayTradePagePayRequest extends KiplePayBaseRequest {

	@Override
	public String getPostType() {
		return PostTypeEnums.POST_APPLICATION_JSON.getType();
	}
}
