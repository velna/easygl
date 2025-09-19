package com.vanix.easygl.graphics;

import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.Support;

@Support(since = Version.GL33)
public interface Sampler extends Handle, SamplerParameters<Sampler> {
    default Sampler bind(TextureUnit unit) {
        unit.bindSampler(this);
        return this;
    }

    static Sampler of() {
        return MetaHolder.Sampler.create();
    }

    static HandleArray<Sampler> of(int n) {
        return MetaHolder.Sampler.createArray(n);
    }
}
