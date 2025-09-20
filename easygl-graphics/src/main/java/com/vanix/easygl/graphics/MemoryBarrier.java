package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.meta.MetaSystem;

public sealed interface MemoryBarrier extends IntEnum {
    Regional All = new Regional("ALL_BARRIER_BITS");
    Regional Uniform = new Regional("UNIFORM_BARRIER_BIT");
    Regional TextureFetch = new Regional("TEXTURE_FETCH_BARRIER_BIT");
    Regional ShaderImageAccess = new Regional("SHADER_IMAGE_ACCESS_BARRIER_BIT");
    Regional Framebuffer = new Regional("FRAMEBUFFER_BARRIER_BIT");
    Regional AtomicCounter = new Regional("ATOMIC_COUNTER_BARRIER_BIT");
    Regional ShaderStorage = new Regional("SHADER_STORAGE_BARRIER_BIT");
    MemoryBarrier VertexAttribArray = new Regional("VERTEX_ATTRIB_ARRAY_BARRIER_BIT");
    MemoryBarrier ElementArray = new Regional("ELEMENT_ARRAY_BARRIER_BIT");
    MemoryBarrier Command = new Regional("COMMAND_BARRIER_BIT");
    MemoryBarrier PixelBuffer = new Regional("PIXEL_BUFFER_BARRIER_BIT");
    MemoryBarrier TextureUpdate = new Regional("TEXTURE_UPDATE_BARRIER_BIT");
    MemoryBarrier BufferUpdate = new Regional("BUFFER_UPDATE_BARRIER_BIT");
    MemoryBarrier TransformFeedback = new Regional("TRANSFORM_FEEDBACK_BARRIER_BIT");

    final class Regional implements MemoryBarrier {
        private final int value;

        private Regional(String id) {
            value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

}