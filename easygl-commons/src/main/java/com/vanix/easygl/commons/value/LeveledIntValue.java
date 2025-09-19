package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.Leveled;

public interface LeveledIntValue extends Leveled, IntValue {
	int get(int level);
}
