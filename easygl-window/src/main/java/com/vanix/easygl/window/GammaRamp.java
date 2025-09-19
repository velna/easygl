package com.vanix.easygl.window;

import java.nio.ShortBuffer;

public record GammaRamp(ShortBuffer red, ShortBuffer green, ShortBuffer blue, int size) {
}
