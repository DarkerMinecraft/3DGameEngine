package com.darkerminecraft.shaders;

import static org.lwjgl.opengl.GL20.*;

import org.joml.Vector3f;

public class UniformVector3f extends Uniform {

	public UniformVector3f(String name) {
		super(name);
	}
	
	public void storeVector3f(float x, float y, float z) {
		glUniform3f(super.getLocation(), x, y, z);
	}
	
	public void storeVector3f(Vector3f vector) {
		storeVector3f(vector.x, vector.y, vector.z);
	}

}
