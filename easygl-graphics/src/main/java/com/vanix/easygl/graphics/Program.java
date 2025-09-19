package com.vanix.easygl.graphics;

import com.vanix.easygl.core.*;
import com.vanix.easygl.graphics.program.Uniform;
import com.vanix.easygl.graphics.program.UniformBlock;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public interface Program extends Bindable<BindTarget.Default<Program>, Program> {

    BindTarget.Default<Program> Target = new BindTarget.Default<>("Program", MetaHolder.Program);

    Program attach(Shader shader);

    default Program attachVertex(String shaderSource) {
        return attach(Shader.Type.Vertex, shaderSource);
    }

    default Program attachFragment(String shaderSource) {
        return attach(Shader.Type.Fragment, shaderSource);
    }

    default Program attachGeometry(String shaderSource) {
        return attach(Shader.Type.Geometry, shaderSource);
    }

    default Program attach(Shader.Type type, String shaderSource) {
        try (var shader = Shader.of(type).source(shaderSource).compile()) {
            return attach(shader);
        }
    }

    default Program attach(Shader.Type type, InputStream inputStream) throws IOException {
        try (var shader = Shader.of(type).source(inputStream).compile()) {
            return attach(shader);
        }
    }

    default Program attachResource(Shader.Type type, String resource) throws IOException {
        return attach(type, Thread.currentThread().getContextClassLoader().getResourceAsStream(resource));
    }

    ShaderArray getAttachedShaders();

    Program detach(Shader shader);

    @Support(since = Version.GL30)
    Program bindFragData(FrameInnerBuffer.DrawBuffer drawBuffer, String varyingOutVariableName);

    @Support(since = Version.GL30)
    @Nullable
    FrameInnerBuffer.DrawBuffer getFragData(String varyingOutVariableName);

    @Support(since = Version.GL32)
    Program bindFragData(FrameInnerBuffer.DrawBuffer drawBuffer, String varyingOutVariableName, int index);

    @Support(since = Version.GL32)
    int getFragDataIndex(String varyingOutVariableName);

    Program link();

    Program validate();

    @Support(since = Version.GL41)
    Program setBinaryRetrievable(boolean retrievable);

    @Support(since = Version.GL41)
    Binary getBinary();

    @Support(since = Version.GL41)
    Program loadBinary(int format, ByteBuffer data);

    @Support(since = Version.GL41)
    default Program loadBinary(Binary binary) {
        return loadBinary(binary.format, binary.data);
    }

    @Support(since = Version.GL41)
    Program setSeparable(boolean separable);

    <B> B bindResources(B bean);

    boolean getAttribute(ProgramAttribute.Bool attribute);

    int getAttribute(ProgramAttribute.Int attribute);

    boolean containsUniform(String name);

    @Support(since = Version.GL31)
    List<String> uniformNames();

    Uniform getUniform(String name);

    @Support(since = Version.GL43)
    ProgramInterfaces interfaces();

    @Support(since = Version.GL31)
    UniformBlock getUniformBlock(String name);

    Program bindVertexAttribute(VertexAttribute vertexAttribute, String variableName);

    Variable getActiveVertexAttribute(VertexAttribute vertexAttribute);

    int getVertexAttributeLocation(String name);

    Program transformFeedbackVaryings(boolean interleaved, String... varyings);

    Variable getTransformFeedbackVarying(int varyingsIndex);

    static Program of() {
        return MetaHolder.Program.create();
    }

    record Binary(int format, ByteBuffer data) {
    }

    record Variable(int size, DataType dataType, String name) {}

}
