package com.zhou.devicecontrolserver.utils;

import java.util.Random;
import java.util.UUID;

public class IDUtils {

    private static final int MAX_LENGTH = String.valueOf(Integer.MAX_VALUE).length();

    public static String getImageName() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int img = random.nextInt(999);
        return millis + String.format("%3d", img);
    }

    public static String getId() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int id = random.nextInt(99999);
        return millis + String.format("%2d", id);
    }

    /**
     * 获取指定位数的整数
     * @param length 数据长度
     * @return
     */
    public static int getRandomNum(int length){
        Random random = new Random();
        if (length >= MAX_LENGTH){
            throw new RuntimeException("生成的随机数过长");
        }
        int flag = (int) Math.pow(10, length - 1);
        return random.nextInt(9 * flag) + flag;
    }

    /**
     * 随机生成32位字符串UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
