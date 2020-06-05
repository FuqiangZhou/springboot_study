package com.zhou.springbootnetty.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/29 5:31 下午
 */
public class ObjectAndByte {

    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos)
        ) {
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
