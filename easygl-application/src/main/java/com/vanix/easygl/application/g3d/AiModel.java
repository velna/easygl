package com.vanix.easygl.application.g3d;

import com.vanix.easygl.commons.collection.eclipse.IntBufferArrayList;
import com.vanix.easygl.core.media.MediaException;
import com.vanix.easygl.core.util.ResourceUtils;
import com.vanix.easygl.graphics.MagFilter;
import com.vanix.easygl.graphics.MinFilter;
import com.vanix.easygl.graphics.Texture;
import com.vanix.easygl.graphics.Texture2D;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.assimp.*;

import java.io.File;
import java.nio.IntBuffer;
import java.util.*;

import static org.lwjgl.assimp.Assimp.*;

public class AiModel implements Model {
    private Set<String> texturesLoaded = new HashSet<>();
    private final Map<Model.TextureType, List<Mesh.TextureInfo>> textures = new EnumMap<>(Model.TextureType.class);
    private final List<Mesh> meshes;
    private final String directory;
    private final boolean gammaCorrection;

    public AiModel(String path, boolean gammaCorrection) {
        var scene = aiImportFile(ResourceUtils.resolve(path), aiProcess_Triangulate | aiProcess_GenSmoothNormals | aiProcess_FlipUVs | aiProcess_CalcTangentSpace);
        if (scene == null || ((scene.mFlags() & AI_SCENE_FLAGS_INCOMPLETE) != 0) || scene.mRootNode() == null) {
            throw new MediaException("Error load model from path " + path + ": " + aiGetErrorString());
        }
        this.directory = new File(path).getParent();
        this.meshes = new ArrayList<>(scene.mNumMeshes());
        this.gammaCorrection = gammaCorrection;
        processNode(scene.mRootNode(), scene);
        texturesLoaded = null;
    }

    private void processNode(AINode node, AIScene scene) {
        var meshIndices = node.mMeshes();
        for (int i = 0; i < node.mNumMeshes(); i++) {
            var mesh = AIMesh.create(scene.mMeshes().get(meshIndices.get(i)));
            meshes.add(processMesh(mesh, scene));
        }
        var children = node.mChildren();
        if (children != null) {
            for (int i = 0; i < node.mNumChildren(); i++) {
                processNode(AINode.create(children.get(i)), scene);
            }
        }
    }

    private Mesh processMesh(AIMesh mesh, AIScene scene) {
        // data to fill
        List<Mesh.Vertex> vertices = new ArrayList<>();
        MutableIntList indices = new IntBufferArrayList();
        Map<TextureType, List<Mesh.TextureInfo>> textures = new EnumMap<>(TextureType.class);

        // walk through each of the mesh's vertices
        for (int i = 0; i < mesh.mNumVertices(); i++) {
            Mesh.Vertex vertex = new Mesh.Vertex();
            // we declare a placeholder vector since assimp uses its own vector class that doesn't directly convert to
            // glm's vec3 class so we transfer the data to this placeholder glm::vec3 first.
            var vector3d = mesh.mVertices().get(i);
            vertex.setPosition(new Vector3f(vector3d.x(), vector3d.y(), vector3d.z()));
            // normals
            var normals = mesh.mNormals();
            if (normals != null) {
                var normal = normals.get(i);
                vertex.setNormal(new Vector3f(normal.x(), normal.y(), normal.z()));
            }
            // texture coordinates
            var textureCoords = mesh.mTextureCoords(0);
            if (textureCoords != null) {// does the mesh contain texture coordinates?
                // a vertex can contain up to 8 different texture coordinates. We thus make the assumption that we won't
                // use models where a vertex can have multiple texture coordinates so we always take the first set (0).
                var vec = textureCoords.get(i);
                vertex.setTexCoords(new Vector2f(vec.x(), vec.y()));
                // tangent
                var tangent = mesh.mTangents().get(i);
                vertex.setTangent(new Vector3f(tangent.x(), tangent.y(), tangent.z()));
                // bitangent
                var bitangent = mesh.mBitangents().get(i);
                vertex.setTangent(new Vector3f(bitangent.x(), bitangent.y(), bitangent.z()));
            } else {
                vertex.setTexCoords(new Vector2f());
            }
            vertices.add(vertex);
        }
        // now wak through each of the mesh's faces (a face is a mesh its triangle) and retrieve the corresponding vertex indices.
        for (int i = 0; i < mesh.mNumFaces(); i++) {
            var face = mesh.mFaces().get(i);
            // retrieve all indices of the face and store them in the indices vector
            for (int j = 0; j < face.mNumIndices(); j++) {
                indices.add(face.mIndices().get(j));
            }
        }
        // process materials
        var materials = scene.mMaterials();
        if (materials != null) {
            AIMaterial material = AIMaterial.create(materials.get(mesh.mMaterialIndex()));
            for (var textureType : TextureType.values()) {
                var list = loadMaterialTextures(material, textureType);
                if (!list.isEmpty()) {
                    textures.merge(textureType, list, (oldValue, newValue) -> {
                        oldValue.addAll(newValue);
                        return oldValue;
                    });
                }
            }
        }

        // return a mesh object created from the extracted mesh data
        return new Mesh(vertices, indices, textures);
    }

    private List<Mesh.TextureInfo> loadMaterialTextures(AIMaterial material, TextureType textureType) {
        int textureCount = aiGetMaterialTextureCount(material, textureType.value());
        if (textureCount <= 0) {
            return Collections.emptyList();
        }
        List<Mesh.TextureInfo> list = new ArrayList<>(textureCount);
        for (int i = 0; i < textureCount; i++) {
            AIString string = AIString.calloc();
            aiGetMaterialTexture(material, textureType.value(), i, string, (IntBuffer) null, null, null, null, null, null);
            String path = string.dataString();
            if (texturesLoaded.add(path)) {
                var textureInfo = new Mesh.TextureInfo();
                textureInfo.setTexture(Texture2D.of().bind()
                        .load(directory + "/" + path, true)
                        .generateMipmap()
                        .wrapS(Texture.Wrap.Repeat)
                        .wrapT(Texture.Wrap.Repeat)
                        .minFilter(MinFilter.LinearMipmapLinear)
                        .magFilter(MagFilter.Linear));
                textureInfo.setType(textureType);
                list.add(textureInfo);
                textures.computeIfAbsent(textureType, k -> new ArrayList<>()).add(textureInfo);
            }
            string.close();
        }
        return list;
    }

    @Override
    public List<Mesh.TextureInfo> getTextures(TextureType textureType) {
        return textures.getOrDefault(textureType, Collections.emptyList());
    }

    @Override
    public List<Mesh> getMeshes() {
        return meshes;
    }

    @Override
    public void close() {
        meshes.forEach(Mesh::close);
    }
}
