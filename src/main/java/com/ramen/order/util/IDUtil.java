package com.ramen.order.util;

public class IDUtil {
    private static byte[] lock = new byte[0];

    private final static long w = 100000000;

    public static long createID() {
        long r = (long) ((Math.random() + 1) * w);

        return System.currentTimeMillis() + r;
    }

}
