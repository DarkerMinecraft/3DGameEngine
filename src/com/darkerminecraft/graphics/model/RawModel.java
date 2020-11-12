package com.darkerminecraft.graphics.model;

import com.darkerminecraft.opengl.Vao;

public class RawModel {

	private Vao vao;
	private int vertexCount;

	public RawModel(Vao vao, int vertexCount) {
		this.vao = vao;
		this.vertexCount = vertexCount;
	}

	public Vao getVao() {
		return vao;
	}

	public int getVertexCount() {
		return vertexCount;
	}

}
