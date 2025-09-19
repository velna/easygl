package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.Leveled;

public interface LeveledFloatValue extends Leveled, FloatValue {

	float get(int level);
}
