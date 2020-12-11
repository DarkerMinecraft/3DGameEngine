package com.darkerminecraft.shaders;

import static org.lwjgl.opengl.GL20.*;

public class Uniform {
	
	private static final int NOT_FOUND = -1;
	
	private int location;
	private String name;
	
	public Uniform(String name) {
		this.name = name;
	}
	
	protected void storeUniform(int programID) {
		this.location = glGetUniformLocation(programID, name);
		if(location == NOT_FOUND)
			System.out.println("Uniform " + name + " not found in shader program " + programID);
	}
	
	public int getLocation() {
		return location;
	}

}
