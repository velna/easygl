package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.Leveled;

public interface LeveledLongValue extends Leveled, LongValue {

	long get(int level);
}
