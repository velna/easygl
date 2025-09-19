package com.vanix.easygl.commons;

public class Random {

    private static final java.util.Random random = new java.util.Random();

    public static int nextInt() {
        return random.nextInt();
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public static long nextLong(long bound) {
        return random.nextLong(bound);
    }

    public static double nextDouble(double bound) {
        return random.nextDouble(bound);
    }
}
