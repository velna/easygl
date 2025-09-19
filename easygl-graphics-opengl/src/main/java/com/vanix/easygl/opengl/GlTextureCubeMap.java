package com.vanix.easygl.opengl;

import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.PixelFormat;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.graphics.TextureCubeMap;

public class GlTextureCubeMap extends AbstractTexture<TextureCubeMap> implements TextureCubeMap,
        GlTextureOps.GetSubImage<TextureCubeMap>,
        GlTextureOps.GetCompressedSubImage<TextureCubeMap>,
        GlTextureOps.Parameters<TextureCubeMap>,
        GlTextureOps.CopyImageSubData<TextureCubeMap>,
        GlTextureOps.MakeView<TextureCubeMap>,
        GlTextureOps.GenerateMipmap<TextureCubeMap>,
        GlTextureOps.SetStorage2D<TextureCubeMap> {
    private Face[] faces;

    protected GlTextureCubeMap() {
        this(GLX.glGenTextures());
    }

    protected GlTextureCubeMap(int handle) {
        super(handle, Target);
    }

    private GlTextureCubeMap(int handle, TexTarget<TextureCubeMap> target) {
        super(handle, target);
    }

    @Override
    public TextureCubeMap proxy() {
        return new GlTextureCubeMap(intHandle(), target.proxy(GLX.GL_PROXY_TEXTURE_CUBE_MAP));
    }

    public TextureCubeMap load(Image... images) {
        int n = Math.min(6, images.length);
        assertBinding();
        for (int i = 0; i < n; i++) {
            Image image = images[i];
            if (image != null) {
                GLX.glTexImage2D(GLX.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i,
                        0,
                        PixelFormat.ofImageFormat(image.format()).value(),
                        image.width(),
                        image.height(),
                        0,
                        PixelFormat.ofImageFormat(image.format()).value(),
                        DataType.UnsignedByte.value(),
                        image.data());
                GLX.checkError();
            }
        }
        return this;
    }

    @Override
    public Face getFace(FaceTarget target) {
        if (faces == null) {
            faces = new Face[FaceTarget.values().length];
        }
        Face face = faces[target.ordinal()];
        if (face == null) {
            faces[target.ordinal()] = face = new FaceImpl(this, target, null);
        }
        return face;
    }

    private record FaceImpl(TextureCubeMap textureCubeMap,
                            FaceTarget faceTarget,
                            Integer proxyValue) implements Face,
            GetImage<Face>,
            GetCompressedImage<Face>,
            Load2D<Face>,
            Load2DSub<Face>,
            Copy2D<Face>,
            Copy2DSub<Face>,
            Load2DCompressed<Face>,
            Load2DCompressedSub<Face> {
        @Override
        public Face proxy() {
            return new FaceImpl(textureCubeMap, faceTarget, GLX.GL_PROXY_TEXTURE_CUBE_MAP);
        }

        @Override
        public Face self() {
            return this;
        }

        @Override
        public TextureCubeMap cubeMap() {
            return textureCubeMap;
        }

        @Override
        public int intHandle() {
            return textureCubeMap.intHandle();
        }

        @Override
        public void assertBinding() throws IllegalStateException {
            textureCubeMap.assertBinding();
        }

        @Override
        public int targetValue(boolean proxy) {
            return proxy && proxyValue != null ? proxyValue : faceTarget.value();
        }

        @Override
        public FaceTarget target() {
            return faceTarget;
        }
    }
}
