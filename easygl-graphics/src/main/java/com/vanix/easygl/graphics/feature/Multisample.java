package com.vanix.easygl.graphics.feature;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.MultiFeature;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.Version;

public interface Multisample extends GraphicsFeature<Multisample>, MultiFeature<Multisample.Feature, Multisample, Graphics> {
    @Support(since = Version.GL32)
    int MaxMaskIndex = MetaSystem.Graphics.queryInt("GET.GL_MAX_SAMPLE_MASK_WORDS");

    Multisample setCoverage(float value, boolean invert);

    float getCoverageValue();

    boolean isCoverageInvert();

    @Support(since = Version.GL40)
    Multisample setMinSampleShading(float value);

    @Support(since = Version.GL40)
    float getMinSampleShading();

    @Support(since = Version.GL32)
    Multisample setMask(int index, int mask);

    @Support(since = Version.GL32)
    int getMask(int index);

    enum Feature implements IntEnum {
        @Support(since = Version.GL40)
        SampleShading("SAMPLE_SHADING"),
        AlphaToCoverage("SAMPLE_ALPHA_TO_COVERAGE"),
        AlphaToOne("SAMPLE_ALPHA_TO_ONE"),
        Coverage("SAMPLE_COVERAGE"),
        @Support(since = Version.GL32)
        Mask("SAMPLE_MASK");
        private final int value;

        Feature(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }
}
