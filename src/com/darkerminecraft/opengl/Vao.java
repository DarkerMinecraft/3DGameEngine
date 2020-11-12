package com.darkerminecraft.opengl;

import static org.lwjgl.opengl.GL30.*;

public class Vao {
	
	private int id;
	
	public Vao() {
		id = glGenVertexArrays();
	}
	
	public void bindVao(int... index) {
		glBindVertexArray(id);
		for(int i : index) glEnableVertexAttribArray(i);
	}
	
	public void bindIndicesBuffer(int[] data) {
		Vbo vbo = new Vbo(GL_ELEMENT_ARRAY_BUFFER);
		vbo.bindVbo();
		vbo.putData(data);
	}
	
	public void storeDataInAttributeList(float[] data, int index, int size) {
		Vbo vbo = new Vbo(GL_ARRAY_BUFFER);
		vbo.bindVbo();
		vbo.putData(data);
		glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
		vbo.unbindVbo();
	}
	
	public void unbindVao(int... index) {
		for(int i : index) glEnableVertexAttribArray(i);
		glBindVertexArray(0);
	}

}
