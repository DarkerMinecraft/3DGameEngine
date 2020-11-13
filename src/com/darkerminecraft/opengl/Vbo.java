package com.darkerminecraft.opengl;

import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Vbo {
	
	private int target, id;
	
	public Vbo(int target) {
		this.target = target;
		this.id = glGenBuffers();
	}
	
	public void bindVbo() {
		glBindBuffer(target, id);
	}
	
	public void putData(float[] data) {
		glBufferData(target, getBuffer(data), GL_STATIC_DRAW);
	}
	
	public void putData(int[] data) {
		glBufferData(target, getBuffer(data), GL_STATIC_DRAW);
	}
	
	public void unbindVbo() {
		glBindBuffer(target, 0);
	}
	
	private FloatBuffer getBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private IntBuffer getBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

}
