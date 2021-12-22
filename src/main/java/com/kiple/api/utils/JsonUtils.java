package com.kiple.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {

    public static JSONObject getJSONObject(String content){
        if(!StringUtils.isEmpty(content)){
            return JSONObject.parseObject(content);
        }
        return null;
    }
}
