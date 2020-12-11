package com.darkerminecraft.shaders;

import static org.lwjgl.opengl.GL20.*;

public class UniformFloat extends Uniform {

	public UniformFloat(String name) {
		super(name);
	}
	
	public void storeFloat(float value) {
		glUniform1f(super.getLocation(), value);
	}

}
