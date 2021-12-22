package com.kiple.api.payment;

import com.kiple.api.utils.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @classname: KiplePayHashMap
 * @description: 描述
 * @author: seven.zhang
 * @date: 2020/6/3 16:21
 */
public class KiplePayHashMap extends HashMap<String, String> {

    private static final long serialVersionUID = -1277791390393392630L;

    public KiplePayHashMap() {
        super();
    }

    public KiplePayHashMap(Map<? extends String, ? extends String> m) {
        super(m);
    }

    public String put(String key, Object value) {
        String strValue;

        if (value == null) {
            strValue = null;
        } else if (value instanceof String) {
            strValue = (String) value;
        } else if (value instanceof Integer) {
            strValue = ((Integer) value).toString();
        } else if (value instanceof Long) {
            strValue = ((Long) value).toString();
        } else if (value instanceof Float) {
            strValue = ((Float) value).toString();
        } else if (value instanceof Double) {
            strValue = ((Double) value).toString();
        } else if (value instanceof Boolean) {
            strValue = ((Boolean) value).toString();
        } else if (value instanceof Date) {
            DateFormat format = new SimpleDateFormat(KiplePayConstants.DATE_TIME_FORMAT);
            format.setTimeZone(TimeZone.getTimeZone(KiplePayConstants.DATE_TIMEZONE));
            strValue = format.format((Date) value);
        } else {
            strValue = value.toString();
        }

        return this.put(key, strValue);
    }

    public String put(String key, String value) {
        if (StringUtils.areNotEmpty(key, value)) {
            return super.put(key, value);
        } else {
            return null;
        }
    }

}
