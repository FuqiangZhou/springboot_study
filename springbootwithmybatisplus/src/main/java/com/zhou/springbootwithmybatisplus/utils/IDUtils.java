package com.zhou.springbootwithmybatisplus.utils;

import java.util.Random;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-15 21:27
 */
public class IDUtils {

    public static String getId() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end2 = random.nextInt(99999);
        String str = millis + String.format("%02d", end2);
        Long id = new Long(str);
        return id.toString();
    }
}
