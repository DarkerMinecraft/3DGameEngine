package com.darkerminecraft.graphics;

import static org.lwjgl.opengl.GL11.*;

import com.darkerminecraft.entity.Entity;

public class MasterRenderer {
	
	private EntityRenderer entityRenderer;
	
	public MasterRenderer() {
		entityRenderer = new EntityRenderer();
	}
	
	public void prepare() {
		glEnable(GL_DEPTH_TEST);
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
		glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
	}
	
	public void render(Entity entity) {
		entityRenderer.render(entity);
	}

}
