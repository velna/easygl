package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.AbstractHandle;
import com.vanix.easygl.graphics.GraphicsException;
import com.vanix.easygl.graphics.Shader;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

public class GlShader extends AbstractHandle implements Shader {

    private final Shader.Type type;
    private String source;

    public GlShader(Shader.Type type) {
        this(GLX.glCreateShader(type.value()), type);
    }

    public GlShader(int handle, Shader.Type type) {
        super(handle, GLX::glDeleteShader);
        if (type == null) {
            type = IntEnum.valueOf(Shader.Type.class, GLX.glGetShaderi(handle, GLX.GL_SHADER_TYPE));
        }
        this.type = type;
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public Shader source(String source) {
        GLX.glShaderSource(intHandle(), source);
        this.source = source;
        return this;
    }

    @Override
    public Shader source(InputStream in) throws IOException {
        StringWriter out = new StringWriter();
        try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            reader.transferTo(out);
        }
        return source(out.toString());
    }

    @Override
    public String source() {
        if (source == null) {
            source = GLX.glGetShaderSource(intHandle());
        }
        return source;
    }

    @Override
    public Shader compile() throws GraphicsException {
        int shader = intHandle();
        GLX.glCompileShader(shader);
        try (var stack = MemoryStack.stackPush()) {
            IntBuffer success = stack.mallocInt(1);
            GLX.glGetShaderiv(shader, GLX.GL_COMPILE_STATUS, success);
            if (success.get() == 0) {
                String infoLog = GLX.glGetShaderInfoLog(shader);
                throw new GraphicsException(String.format("error compile source %s: %s", source, infoLog));
            }
            return this;
        }
    }

}
