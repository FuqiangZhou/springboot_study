package com.zhou.springbootnetty.utils;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/29 4:42 下午
 */
public class Byte2StrUtil {

    public static String printHexString(byte[] data) {

        return printHexString(data, true);
    }

    public static String printHexString(byte[] data, boolean flag) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < data.length; i++) {
            int anInt = Byte.toUnsignedInt(data[i]);
            if (flag){
                if (anInt > Byte.MAX_VALUE) {
                    stringBuilder.append((byte) anInt);
                } else {
                    stringBuilder.append("0x").append(Integer.toHexString(anInt).toUpperCase().length() < 2 ? "0" + Integer.toHexString(anInt).toUpperCase() : Integer.toHexString(anInt).toUpperCase());
                }
            }else {
                stringBuilder.append("0x").append(Integer.toHexString(anInt).toUpperCase().length() < 2 ? "0" + Integer.toHexString(anInt).toUpperCase() : Integer.toHexString(anInt).toUpperCase());
            }
            if (i < data.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
