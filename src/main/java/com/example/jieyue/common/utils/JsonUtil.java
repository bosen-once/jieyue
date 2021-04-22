package com.example.jieyue.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    /**
     * 字符串转整数型数组
     * todo
     */
    public int[] jsonToIntArray(String json){
        JSONArray jsonArray = JSON.parseArray(json);
        int[] array = new int[jsonArray.size()];
        for (int i = 0;i < jsonArray.size();i++){
            array[i] = (Integer) jsonArray.get(i);
        }
        return array;
    }
}
