package com.example.jieyue.common.utils;

import org.springframework.stereotype.Component;

/**
 * <p>判空工具类</p>
 * @author Bosen
 * 2020/10/31 16:03
 */
@Component
public class IsEmptyUtil {
    /*
     * 单例模式
     */
    private static class IsEmptyUtilHoler{
        private static IsEmptyUtil INSTANCE = new IsEmptyUtil();
    }

    public static IsEmptyUtil getInstance(){
        return IsEmptyUtilHoler.INSTANCE;
    }

    /*
     * 多个字符串判空操作,当存在有空字符时返回true
     */
    public boolean strings(String ... strings){
        for (String string : strings) {
            if (string==null || string.equals("")){
                return true;
            }
        }
        return false;
    }

    /*
     * 单个字符串判空操作
     */
    public boolean string(String string){
        if (string==null || string.equals("")){
            return true;
        }else{
            return false;
        }
    }

    /*
     * 单个对象判空操作
     */
    public boolean object(Object object){
        if (object==null){
            return true;
        }else{
            return false;
        }
    }

    /*
     * 多个对象判空操作,当存在有null对象时返回true
     */
    public boolean objects(Object ... objects){
        for (Object object : objects) {
            if (object==null){
                return true;
            }
        }
        return false;
    }
}
