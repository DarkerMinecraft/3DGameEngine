package com.darkerminecraft.shaders;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL42.*;

import java.io.BufferedReader;

import com.darkerminecraft.utils.MyFile;

public class ShaderProgram {
	
	private int programID, vertexShaderID, fragmentShaderID, geometryShaderID;
	private boolean geometry;
	
	public ShaderProgram(String shaderName, boolean geometry, String... attributes) {
		this.geometry = geometry;
		MyFile vertexShader = new MyFile("com/darkerminecraft/shaders/" + shaderName + "/vertexShader.glsl");
		MyFile fragmentShader = new MyFile("com/darkerminecraft/shaders/" + shaderName + "/fragmentShader.glsl");
		programID = glCreateProgram();
		vertexShaderID = createShader(vertexShader, GL_VERTEX_SHADER);
		fragmentShaderID = createShader(fragmentShader, GL_FRAGMENT_SHADER);
		if(geometry) {
			MyFile geometryShader = new MyFile("com/darkerminecraft/shaders/" + shaderName + "/geometryShader.glsl");
			geometryShaderID = createShader(geometryShader, GL_GEOMETRY_SHADER); 
		}
		glAttachShader(programID, vertexShaderID);
		glAttachShader(programID, fragmentShaderID);
		if(geometry) glAttachShader(programID, geometryShaderID);
		for(int i = 0; i < attributes.length; i++) {
			glBindAttribLocation(programID, i, attributes[i]);
		}
		glLinkProgram(programID);
	}
	
	public void storeAllUniformLocations(Uniform... uniforms) {
		for(Uniform uniform : uniforms) 
			uniform.storeUniform(programID);
	}
	
	public void start() {
		glUseProgram(programID);
	}
	
	public void stop() {
		glUseProgram(0);
	}
	
	public void cleanUp() {
		glDeleteShader(vertexShaderID);
		glDeleteShader(fragmentShaderID);
		if(geometry) glDeleteShader(geometryShaderID);
	}

	private int createShader(MyFile file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = file.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				shaderSource.append(line).append("//\n");
			}
			reader.close();
		} catch (Exception e) {
			System.err.println("Could not read file.");
			e.printStackTrace();
			System.exit(-1);
		}
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, shaderSource);
		glCompileShader(shaderID);
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println(glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader " + file);
			System.exit(-1);
		}
		return shaderID;
	}

}
