package com.vanix.easygl.graphics;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public final class Version {
    public static final String GL20 = "2.0";
    public static final String GL21 = "2.1";
    public static final String GL30 = "3.0";
    public static final String GL31 = "3.1";
    public static final String GL32 = "3.2";
    public static final String GL33 = "3.3";
    public static final String GL40 = "4.0";
    public static final String GL41 = "4.1";
    public static final String GL42 = "4.2";
    public static final String GL43 = "4.3";
    public static final String GL44 = "4.4";
    public static final String GL45 = "4.5";
    public static final String GL46 = "4.6";
    private final String major;
    private final String minor;
    private final String release;
    private final String vendorSpecific;

    public Version(String version) {
        String[] split = StringUtils.split(version, " ", 2);
        String[] numbers = split[0].split("\\.");
        major = numbers[0];
        minor = numbers[1];
        release = numbers.length > 2 ? numbers[2] : "";
        vendorSpecific = split.length > 1 ? split[1] : "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(major).append('.').append(minor);
        if (!release.isEmpty()) {
            sb.append('.').append(release);
        }
        if (!vendorSpecific.isEmpty()) {
            sb.append(' ').append(vendorSpecific);
        }
        return sb.toString();
    }
}
