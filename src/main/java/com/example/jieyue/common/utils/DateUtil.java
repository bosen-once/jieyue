package com.example.jieyue.common.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>时间工具类</p>
 * @author Bosen
 * 2020/11/6 10:36
 */
@Component
public class DateUtil {
    /*
     * 获取纯年月日时分秒的字符串
     */
    public String getNMDHIS(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
