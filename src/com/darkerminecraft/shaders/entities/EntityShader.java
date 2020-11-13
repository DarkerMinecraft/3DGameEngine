package com.darkerminecraft.shaders.entities;

import com.darkerminecraft.shaders.ShaderProgram;
import com.darkerminecraft.shaders.UniformMatrix;

public class EntityShader extends ShaderProgram {
	
	public UniformMatrix transformationMatrix = new UniformMatrix("transformationMatrix");
	
	public EntityShader() {
		super("entities", false, "position");
		super.storeAllUniformLocations(transformationMatrix);
	}

}
