package com.darkerminecraft.graphics;

import static org.lwjgl.opengl.GL11.*;

import com.darkerminecraft.graphics.model.TexturedModel;

public class MasterRenderer {
	
	private EntityRenderer entityRenderer;
	
	public MasterRenderer() {
		entityRenderer = new EntityRenderer();
	}
	
	public void prepare() {
		glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void render(TexturedModel model) {
		entityRenderer.render(model);
	}

}
