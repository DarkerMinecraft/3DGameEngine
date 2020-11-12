package com.darkerminecraft.opengl;

import static org.lwjgl.opengl.GL15.*;

import java.nio.ByteBuffer;

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
		glBufferData(target, getByteBuffer(data), GL_STATIC_DRAW);
	}
	
	public void putData(int[] data) {
		glBufferData(target, getByteBuffer(data), GL_STATIC_DRAW);
	}
	
	public void unbindVbo() {
		glBindBuffer(target, 0);
	}
	
	private ByteBuffer getByteBuffer(float[] data) {
		ByteBuffer buffer = BufferUtils.createByteBuffer(data.length * 4);
		for(float f : data) buffer.putFloat(f);
		buffer.flip();
		return buffer;
	}
	
	private ByteBuffer getByteBuffer(int[] data) {
		ByteBuffer buffer = BufferUtils.createByteBuffer(data.length * 4);
		for(int i : data) buffer.putInt(i);
		buffer.flip();
		return buffer;
	}

}
