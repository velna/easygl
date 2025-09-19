package com.vanix.easygl.application.g3d;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.meta.MetaSystem;

import java.util.List;

public interface Model extends Closeable {

    List<Mesh.TextureInfo> getTextures(TextureType textureType);

    List<Mesh> getMeshes();

    static Model of(String path) {
        return of(path, false);
    }

    static Model of(String path, boolean gammaCorrection) {
        return new AiModel(path, gammaCorrection);
    }

    enum TextureType implements IntEnum {
        None("TextureType_NONE"),
        Diffuse("TextureType_DIFFUSE"),
        Specular("TextureType_SPECULAR"),
        Ambient("TextureType_AMBIENT"),
        Emissive("TextureType_EMISSIVE"),
        Height("TextureType_HEIGHT"),
        Normals("TextureType_NORMALS"),
        Shininess("TextureType_SHININESS"),
        Opacity("TextureType_OPACITY"),
        Displacement("TextureType_DISPLACEMENT"),
        LightMap("TextureType_LIGHTMAP"),
        Reflection("TextureType_REFLECTION"),
        BaseColor("TextureType_BASE_COLOR"),
        NormalCamera("TextureType_NORMAL_CAMERA"),
        EmissionColor("TextureType_EMISSION_COLOR"),
        Metalness("TextureType_METALNESS"),
        DiffuseRoughness("TextureType_DIFFUSE_ROUGHNESS"),
        AmbientOcclusion("TextureType_AMBIENT_OCCLUSION"),
        Unknown("TextureType_UNKNOWN"),
        Sheen("TextureType_SHEEN"),
        ClearCoat("TextureType_CLEARCOAT"),
        Transmission("TextureType_TRANSMISSION"),
        MayaBase("TextureType_MAYA_BASE"),
        MayaSpecular("TextureType_MAYA_SPECULAR"),
        MayaSpecularColor("TextureType_MAYA_SPECULAR_COLOR"),
        MayaSpecularRoughness("TextureType_MAYA_SPECULAR_ROUGHNESS");

        private final int value;

        TextureType(String id) {
            this.value = MetaSystem.Media.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }
}
