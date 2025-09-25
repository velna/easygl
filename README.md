# easy-gl

这是一个面向对象的OpenGL API库，基于lwjgl。

## 本项目的目标：
1. 使用面向对象的方法来编写所有API，完全隔离lwjgl。
2. OpenGL中的所有常量全部使用枚举或接口实现，尽一切可能避免误使用。
3. 简化各类API的使用，减少代码量的同时尽可能保证性能。
4. 在调试模式下检查所有API调用，给出详尽的错误信息。（尚未实现）

## 进展
(根据[opengl45-quick-reference-card](https://www.khronos.org/files/opengl45-quick-reference-card.pdf)里的分类)

详细API实现情况索引请[点击这里](OpenGLFunctionIndex.md)

| 类别                         | API完成度 | 功能完成度 |
|----------------------------|--------|-------|
| Synchronization            | 100%   | 100%  |
| Asynchronous Queries       | 100%   | 10%   |
| Timer Queries              | 100%   | 100%  |
| Buffer Objects             | 100%   | 100%  |
| Shaders and Programs       | 80%    | 95%   |
| Textures and Samplers      | 80%    | 95%   |
| Framebuffer Objects        | 90%    | 95%   |
| Vertices                   | 90%    | 100%  |
| Vertex Arrays              | 90%    | 100%  |
| Vertex Attributes          | 90%    | 100%  |
| Vertex Post-Processing     | 100%   | 100%  |
| Rasterization              | 100%   | 100%  |
| Fragment Shaders           | 100%   | 100%  |
| Compute Shaders            | 100%   | 100%  |
| Per-Fragment Operations    | 100%   | 100%  |
| Hints                      | 100%   | 100%  |
| Whole Framebuffer          | 100%   | 100%  |
| Reading and Copying Pixels | 100%   | 100%  |
| Debug Output               | 100%   | 100%  |
| State and State Requests   | 0%     | 0%    |


## 一个HelloTriangle的示例：
```java
package com.vanix.easygl.learnopengl.c1_gettingstarted;

import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.window.input.Keyboard;
import com.vanix.easygl.window.Window;
import com.vanix.easygl.window.WindowHints;
import com.vanix.easygl.graphics.*;

public class C2_1_HelloTriangle {
    public static void main(String[] args) {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL").bind();
             var graphics = Graphics.of().viewport(window.frameBufferWidth(), window.frameBufferHeight());
             var program = Program.of();
             var vao = VertexArray.of();
             var vbo = Buffer.of(DataType.Float)) {
            window.bind().inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE)
                    .subscribe((event) -> event.source().window().shouldClose(true));

            program.attachVertex("""
                            #version 330 core
                            layout (location = 0) in vec3 aPos;
                            void main() {
                                gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);
                            }
                            """)
                    .attachFragment("""
                            #version 330 core
                            out vec4 FragColor;
                            void main(){
                                FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);
                            }
                            """)
                    .link();
            vbo.bind(Buffer.Target.Array)
                    .realloc(Buffer.DataUsage.StaticDraw, new float[]{
                            -0.5f, -0.5f, 0.0f, // left
                            0.5f, -0.5f, 0.0f, // right
                            0.0f, 0.5f, 0.0f  // top
                    });
            var triangleCount = vao.bind().enableAttributePointers(3f).countOfStride();

            var drawable = vao.drawingArrays(DrawMode.Triangles, triangleCount).build();
            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer().setClearColor(0.2f, 0.3f, 0.3f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.Color);

                program.bind();
                drawable.draw();

                window.swapBuffers().pollEvents();
            }
        }
    }

}

```
运行效果：
![](screenshots/C2_1_HelloTriangle.png)

## 全屏抗锯齿
```java
package com.vanix.easygl.learnopengl.c4_advancedopengl;

import com.vanix.easygl.application.g3d.ControllableCamera;
import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.InternalPixelFormat;
import com.vanix.easygl.window.input.Keyboard;
import com.vanix.easygl.window.input.Mouse;
import com.vanix.easygl.window.Window;
import com.vanix.easygl.window.WindowHints;
import com.vanix.easygl.learnopengl.Uniforms;
import com.vanix.easygl.graphics.*;
import org.joml.Math;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;

public class C11_2_AntiAliasingMsaaOffscreen {
    public static void main(String[] args) throws IOException {
        WindowHints.ContextVersionMajor.set(3);
        WindowHints.ContextVersionMinor.set(3);
        WindowHints.OpenGlProfile.Core.set();

        try (var window = Window.of(800, 600, "LearnOpenGL").bind();
             var graphics = Graphics.of().viewport(window.frameBufferWidth(), window.frameBufferHeight());
             var frameBuffer = FrameBuffer.of();
             var renderBuffer = RenderBuffer.of();
             var intermediateFBO = FrameBuffer.of();
             var screenTexture = Texture2D.of();
             var textureColorBufferMultiSampled = Texture2DMultiSample.of();
             var program = Program.of();
             var screenProgram = Program.of();
             var cubeVao = VertexArray.of();
             var cubeVbo = Buffer.of(DataType.Float);
             var quadVao = VertexArray.of();
             var quadVbo = Buffer.of(DataType.Float)) {
            window.bind().inputs().keyboard().onKey(Keyboard.FunctionKey.ESCAPE)
                    .subscribe((event) -> event.source().window().shouldClose(true));
            window.inputs().mouse().cursorMode(Mouse.CursorMode.CURSOR_DISABLED);
            graphics.depthTest().enable();
            graphics.enable(Capability.Multisample);

            program.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/11.2.anti_aliasing.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/11.2.anti_aliasing.fs")
                    .link();
            var uniforms = program.bindResources(new Uniforms<>());
            screenProgram.attachResource(Shader.Type.Vertex, "shaders/4_advanced_opengl/11.2.aa_post.vs")
                    .attachResource(Shader.Type.Fragment, "shaders/4_advanced_opengl/11.2.aa_post.fs")
                    .link();

            cubeVbo.bind(Buffer.Target.Array)
                    .realloc(Buffer.DataUsage.StaticDraw, new float[]{
                            // positions
                            -0.5f, -0.5f, -0.5f,
                            0.5f, -0.5f, -0.5f,
                            0.5f, 0.5f, -0.5f,
                            0.5f, 0.5f, -0.5f,
                            -0.5f, 0.5f, -0.5f,
                            -0.5f, -0.5f, -0.5f,

                            -0.5f, -0.5f, 0.5f,
                            0.5f, -0.5f, 0.5f,
                            0.5f, 0.5f, 0.5f,
                            0.5f, 0.5f, 0.5f,
                            -0.5f, 0.5f, 0.5f,
                            -0.5f, -0.5f, 0.5f,

                            -0.5f, 0.5f, 0.5f,
                            -0.5f, 0.5f, -0.5f,
                            -0.5f, -0.5f, -0.5f,
                            -0.5f, -0.5f, -0.5f,
                            -0.5f, -0.5f, 0.5f,
                            -0.5f, 0.5f, 0.5f,

                            0.5f, 0.5f, 0.5f,
                            0.5f, 0.5f, -0.5f,
                            0.5f, -0.5f, -0.5f,
                            0.5f, -0.5f, -0.5f,
                            0.5f, -0.5f, 0.5f,
                            0.5f, 0.5f, 0.5f,

                            -0.5f, -0.5f, -0.5f,
                            0.5f, -0.5f, -0.5f,
                            0.5f, -0.5f, 0.5f,
                            0.5f, -0.5f, 0.5f,
                            -0.5f, -0.5f, 0.5f,
                            -0.5f, -0.5f, -0.5f,

                            -0.5f, 0.5f, -0.5f,
                            0.5f, 0.5f, -0.5f,
                            0.5f, 0.5f, 0.5f,
                            0.5f, 0.5f, 0.5f,
                            -0.5f, 0.5f, 0.5f,
                            -0.5f, 0.5f, -0.5f
                    });
            var cubeTriangleCount = cubeVao.bind().enableAttributePointers(3f).countOfStride();

            quadVbo.bind(Buffer.Target.Array)
                    .realloc(Buffer.DataUsage.StaticDraw, new float[]{// vertex attributes for a quad that fills the entire screen in Normalized Device Coordinates.
                            // positions   // texCoords
                            -1.0f, 1.0f, 0.0f, 1.0f,
                            -1.0f, -1.0f, 0.0f, 0.0f,
                            1.0f, -1.0f, 1.0f, 0.0f,

                            -1.0f, 1.0f, 0.0f, 1.0f,
                            1.0f, -1.0f, 1.0f, 0.0f,
                            1.0f, 1.0f, 1.0f, 1.0f
                    });
            var quadTriangleCount = quadVao.bind().enableAttributePointers(2f, 2f).countOfStride();

            renderBuffer.bind()
                    .storageMultiSample(4, InternalPixelFormat.Base.DEPTH24_STENCIL8, window.frameBufferWidth(), window.frameBufferHeight())
                    .unbind();
            textureColorBufferMultiSampled.bind()
                    .establish(4, InternalPixelFormat.Base.RGB, window.frameBufferWidth(), window.frameBufferHeight())
                    .unbind();
            frameBuffer.bindFrame()
                    .attach(FrameInnerBuffer.Attachment.ofColor(0), textureColorBufferMultiSampled)
                    .attach(FrameInnerBuffer.Attachment.DepthStencil, renderBuffer)
                    .checkStatus()
                    .unbind();

            screenTexture.bind()
                    .allocate(InternalPixelFormat.Base.RGB, window.frameBufferWidth(), window.frameBufferHeight())
                    .minFilter(MinFilter.Linear)
                    .magFilter(MagFilter.Linear);
            intermediateFBO.bindFrame()
                    .attach(FrameInnerBuffer.Attachment.ofColor(0), screenTexture)
                    .checkStatus()
                    .unbind();

            screenProgram.bind()
                    .getUniform("screenTexture").setTextureUnit(TextureUnit.U0);

            var cubeDrawable = cubeVao.drawingArrays(DrawMode.Triangles, cubeTriangleCount).build();
            var quadDrawable = quadVao.drawingArrays(DrawMode.Triangles, quadTriangleCount).build();
            var camera = new ControllableCamera(window.inputs().keyboard(), window.inputs().mouse());
            FloatBuffer mat4f = BufferUtils.createFloatBuffer(4 * 4);
            while (!window.shouldClose()) {
                graphics.defaultFrameBuffer()
                        .setClearColor(0.1f, 0.1f, 0.1f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);
                frameBuffer.bindFrame()
                        .setClearColor(0.1f, 0.1f, 0.1f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.ColorAndDepth);
                graphics.depthTest().enable();

                var projection = new Matrix4f()
                        .perspective(Math.toRadians(camera.fov().get()), window.getAspect(), 0.1f, 100.0f);

                program.bind();
                uniforms
                        .projection.setMatrix4(projection.get(mat4f))
                        .view.setMatrix4(camera.update().view().get(mat4f))
                        .model.setMatrix4(new Matrix4f().get(mat4f));
                cubeDrawable.draw();

                frameBuffer.bindRead();
                intermediateFBO.bindDraw()
                        .blit(0, 0, window.frameBufferWidth(), window.frameBufferHeight(), FrameInnerBuffer.Mask.Color, MagFilter.Nearest)
                        .unbind();
                graphics.defaultFrameBuffer()
                        .setClearColor(1.0f, 1.0f, 1.0f, 1.0f)
                        .clear(FrameInnerBuffer.Mask.Color);
                graphics.depthTest().disable();

                screenProgram.bind();
                screenTexture.bind();
                quadDrawable.draw();

                window.swapBuffers().pollEvents();
            }
        }
    }

}
```
运行效果：
![](screenshots/C11_2_AntiAliasingMsaaOffscreen.png)

## 更多示例见 [learnopengl项目](https://github.com/velna/learnopengl)
