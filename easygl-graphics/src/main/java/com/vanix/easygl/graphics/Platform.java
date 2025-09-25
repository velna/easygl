package com.vanix.easygl.graphics;

public record Platform(String vendor, String renderer) {
    @Override
    public String toString() {
        return vendor + '/' + renderer;
    }
}
