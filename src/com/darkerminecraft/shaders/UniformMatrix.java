package com.darkerminecraft.shaders;

import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

public class UniformMatrix extends Uniform {
	
	private static final FloatBuffer BUFFER = BufferUtils.createFloatBuffer(16); 

	public UniformMatrix(String name) {
		super(name);
	}
	
	public void storeMatrix(Matrix4f matrix) {
		matrix.get(BUFFER);
		glUniformMatrix4fv(super.getLocation(), false, BUFFER);
	}

}
