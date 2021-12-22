package com.kiple.api.domain.request;

import com.kiple.api.payment.PostTypeEnums;

/**
 * @classname: AlipayTradePagePayRequest
 * @description: 描述
 * @author: koe.zhang
 * @date: 2020/6/17 16:14
 */
public class KiplePayFormRequest extends KiplePayBaseRequest {

	@Override
	public String getPostType() {
		return PostTypeEnums.POST_FORM_DATA.getType();
	}
}
