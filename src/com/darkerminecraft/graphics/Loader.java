package com.darkerminecraft.graphics;

import com.darkerminecraft.graphics.model.RawModel;
import com.darkerminecraft.graphics.obj.ModelData;
import com.darkerminecraft.opengl.Vao;
import com.darkerminecraft.threads.OBJThread;

public class Loader {
	
	public static RawModel loadToVAO(String name) {
		ModelData data = OBJThread.getOBJ(name);
		return loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
	}
	
	public static RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices) {
		Vao vao = new Vao();
		vao.bindVao();
		vao.bindIndicesBuffer(indices);
		vao.storeDataInAttributeList(positions, 0, 3);
		vao.storeDataInAttributeList(textureCoords, 1, 2);
		vao.storeDataInAttributeList(normals, 2, 3);
		vao.unbindVao();
		return new RawModel(vao, indices.length);
	}
	
	public static RawModel loadToVAO(float[] positions, int[] indices) {
		Vao vao = new Vao();
		vao.bindVao();
		vao.bindIndicesBuffer(indices);
		vao.storeDataInAttributeList(positions, 0, 3);
		vao.unbindVao();
		return new RawModel(vao, indices.length);
	}

}
