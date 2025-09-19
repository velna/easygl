package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface Shader extends Handle {

    enum Type implements IntEnum {
        Vertex("VERTEX_SHADER"),
        Geometry("GEOMETRY_SHADER"),
        Fragment("FRAGMENT_SHADER"),
        @Support(since = Version.GL40)
        TessControlShader("TESS_CONTROL_SHADER"),
        @Support(since = Version.GL40)
        TessEvaluationShader("TESS_EVALUATION_SHADER"),
        @Support(since = Version.GL43)
        ComputeShader("COMPUTE_SHADER");

        private final int value;

        Type(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    Type type();

    Shader source(String source);

    Shader source(InputStream in) throws IOException;

    String source();

    Shader compile() throws GraphicsException;

    static Shader of(Type type) {
        return MetaHolder.Shader.create(type);
    }

    static Shader vertex() {
        return of(Type.Vertex);
    }

    static Shader geometry() {
        return of(Type.Geometry);
    }

    static Shader fragment() {
        return of(Type.Fragment);
    }

    private static Shader of(Type type, String resourceFile) {
        Shader shader = of(type);
        try {
            shader.source(Shader.class.getClassLoader().getResourceAsStream(resourceFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return shader;
    }

    static Shader vertex(String resourceFile) {
        return of(Type.Vertex, resourceFile);
    }

    static Shader geometry(String resourceFile) {
        return of(Type.Geometry, resourceFile);
    }

    static Shader fragment(String resourceFile) {
        return of(Type.Fragment, resourceFile);
    }

    static ShaderArray of(int n, Type type) {
        return (ShaderArray) MetaHolder.Shader.createArray(n, type);
    }

    static ShaderArray of(List<Shader> shaderList) {
        return MetaSystem.Graphics.of(ShaderArray.class, shaderList);
    }
}
