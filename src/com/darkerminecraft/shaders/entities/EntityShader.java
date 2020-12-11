package com.darkerminecraft.shaders.entities;

import com.darkerminecraft.graphics.Light;
import com.darkerminecraft.shaders.ShaderProgram;
import com.darkerminecraft.shaders.UniformFloat;
import com.darkerminecraft.shaders.UniformMatrix;
import com.darkerminecraft.shaders.UniformVector3f;

public class EntityShader extends ShaderProgram {
	
	public UniformMatrix transformationMatrix = new UniformMatrix("transformationMatrix");
	public UniformMatrix projViewMatrix = new UniformMatrix("projViewMatrix");
		
	private UniformVector3f lightPosition = new UniformVector3f("lightPosition");
	private UniformVector3f lightColor = new UniformVector3f("lightColor");
	public UniformVector3f cameraPosition = new UniformVector3f("cameraPosition");
	
	public UniformFloat shineDamper = new UniformFloat("shineDamper");
	public UniformFloat reflectivity = new UniformFloat("reflectivity"); 
	
	public EntityShader() {
		super("entities", false, "position", "textureCoords", "normal");
		super.storeAllUniformLocations(transformationMatrix, projViewMatrix, lightColor, lightPosition, shineDamper, reflectivity, cameraPosition);
	}
	
	public void loadLight(Light light) {
		lightPosition.storeVector3f(light.getPosition());
		lightColor.storeVector3f(light.getColor());
	}

}
