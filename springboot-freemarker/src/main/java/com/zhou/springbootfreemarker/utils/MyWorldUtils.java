package com.zhou.springbootfreemarker.utils;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/10 6:12 下午
 */
public class MyWorldUtils {

    public static String toCamelCase(String str, char replaceChar){
        if (null == str) {
            return null;
        } else {
            if (str.contains(String.valueOf(replaceChar))) {
                StringBuilder sb = new StringBuilder(str.length());
                boolean upperCase = false;

                for(int i = 0; i < str.length(); ++i) {
                    char c = str.charAt(i);
                    if (c == replaceChar) {
                        upperCase = true;
                    } else if (upperCase) {
                        sb.append(Character.toUpperCase(c));
                        upperCase = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                }

                return sb.toString();
            } else {
                return str;
            }
        }
    }

    public static String toCamelCase(String str){
        return toCamelCase(str, '_');
    }
}
