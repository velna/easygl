package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.Leveled;

public interface LeveledDoubleValue extends Leveled, DoubleValue {

	double get(int level);
}
