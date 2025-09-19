package com.vanix.easygl.application.g3d;

import com.vanix.easygl.commons.bufferio.BufferField;
import com.vanix.easygl.commons.bufferio.BufferStruct;
import com.vanix.easygl.commons.util.TypeReferenceBean;
import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.graphics.DataType;
import com.vanix.easygl.graphics.Buffer;
import com.vanix.easygl.graphics.DrawMode;
import com.vanix.easygl.graphics.Texture2D;
import com.vanix.easygl.graphics.VertexArray;
import com.vanix.easygl.graphics.draw.Drawing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.collections.api.list.primitive.IntList;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Mesh implements Closeable {
    private final Map<Model.TextureType, List<TextureInfo>> textures;
    @Getter
    private final VertexArray vao;
    private final Buffer vbo;
    @Getter
    private final Buffer ebo;
    private final Drawing<VertexArray> drawable;

    public Mesh(List<Vertex> vertices, IntList indices, Map<Model.TextureType, List<TextureInfo>> textures) {
        this.textures = textures;
        vao = VertexArray.of();

        vbo = Buffer.of(DataType.Float).bind(Buffer.Target.Array)
                .realloc(Buffer.DataUsage.StaticDraw, new TypeReferenceBean<>(vertices) {});

        ebo = Buffer.of(DataType.UnsignedInt).bind(Buffer.Target.ElementArray)
                .realloc(Buffer.DataUsage.StaticDraw, indices);

        vao.bind().enableAttributePointers(3f, 3f, 2f, 3f, 3f, 14, 4f);
        drawable = vao.drawingElements(DrawMode.Triangles, ebo).build();
    }

    public void draw() {
        drawable.draw();
    }

    public List<TextureInfo> getTextures(Model.TextureType textureType) {
        var list = textures.get(textureType);
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    @Override
    public void close() {
        textures.values().forEach(list -> list.forEach(textureInfo -> textureInfo.texture.close()));
        vao.close();
        vbo.close();
        ebo.close();
    }

    @Data
    @NoArgsConstructor
    public static class Vertex {
        private Vector3f position;
        private Vector3f normal;
        private Vector2f texCoords;
        private Vector3f tangent;
        private Vector3f bitangent;
        private int[] boneIds;
        private float[] weights;

        @BufferStruct
        public Vertex(Vector3f position, Vector3f normal, Vector2f texCoords, Vector3f tangent, Vector3f bitangent,
                      @BufferField(count = 4) int[] boneIds, @BufferField(count = 4) float[] weights) {
            this.position = position;
            this.normal = normal;
            this.texCoords = texCoords;
            this.tangent = tangent;
            this.bitangent = bitangent;
            this.boneIds = boneIds;
            this.weights = weights;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TextureInfo {
        private Texture2D texture;
        private Model.TextureType type;
    }
}
