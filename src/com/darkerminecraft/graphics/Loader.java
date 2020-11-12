package com.darkerminecraft.graphics;

import com.darkerminecraft.graphics.model.RawModel;
import com.darkerminecraft.opengl.Vao;

public class Loader {
	
	public static RawModel loadToVAO(float[] positions, int[] indices) {
		Vao vao = new Vao();
		vao.bindVao();
		vao.bindIndicesBuffer(indices);
		vao.storeDataInAttributeList(positions, 0, 3);
		vao.unbindVao();
		return new RawModel(vao, indices.length);
	}

}
