package com.darkerminecraft;

import com.darkerminecraft.graphics.DisplayManager;
import com.darkerminecraft.graphics.Loader;
import com.darkerminecraft.graphics.MasterRenderer;
import com.darkerminecraft.graphics.model.RawModel;
import com.darkerminecraft.graphics.model.TexturedModel;
import com.darkerminecraft.graphics.textures.ModelTexture;
import com.darkerminecraft.shaders.entities.EntityShader;

public class Game {

	public static void main(String[] args) {
		DisplayManager.createDisplay();

		float vertices[] = { 
				0.5f, 0.5f, 0.0f,
				0.5f, -0.5f, 0.0f, 
				-0.5f, -0.5f, 0.0f,
				-0.5f, 0.5f, 0.0f
		};
		
		float[] textureCoords = {
				
				0, 0, 
				0, 1, 
				1, 1, 
				1, 0
				
		};
		
		int indices[] = { 
				0, 1, 3, 
				1, 2, 3 
		};

		MasterRenderer renderer = new MasterRenderer();
		
		RawModel model = Loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture("dirt");
		
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		EntityShader shader = new EntityShader();

		while (DisplayManager.isDisplayRunning()) {
			renderer.prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		DisplayManager.destoryDisplay();
	}

}
