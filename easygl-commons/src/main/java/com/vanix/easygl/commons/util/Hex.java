package com.vanix.easygl.commons.util;

public class Hex {
    public static String dump(byte[] bytes) {
        byte c;
        StringBuilder sb = new StringBuilder();
        int len = bytes.length;
        for (int i = 0; i < len; i++) {
            sb.append(String.format(" %02x", bytes[i]));
            if (i % 16 == 15 || i == len - 1) {
                sb.append("   ".repeat(16 - (i % 16)));
                sb.append('\t');
                for (int j = i - (i % 16); j <= i; j++) {
                    c = bytes[j];
                    if (isPrintable(c) && c != '\n' && c != '\r' && c != '\t') {
                        sb.append(String.format("%c", c));
                    } else {
                        sb.append('.');
                    }
                }
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    private static boolean isPrintable(byte b) {
        return b >= 32 && b <= 126;
    }
}
