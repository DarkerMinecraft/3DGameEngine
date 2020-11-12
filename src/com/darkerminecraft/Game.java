package com.darkerminecraft;

import static org.lwjgl.opengl.GL11.*;

import com.darkerminecraft.graphics.DisplayManager;
import com.darkerminecraft.opengl.Vao;
import com.darkerminecraft.shaders.entities.EntityShader;

public class Game {

	public static void main(String[] args) {
		DisplayManager.createDisplay();

		float vertices[] = { 0.5f, 0.5f, 0.0f,
				0.5f, -0.5f, 0.0f, 
				-0.5f, -0.5f, 0.0f,
				-0.5f, 0.5f, 0.0f
		};
		int indices[] = { 
				0, 1, 3, 
				1, 2, 3 
		};

		Vao vao = new Vao();
		vao.bindVao();
		vao.bindIndicesBuffer(indices);
		vao.storeDataInAttributeList(vertices, 0, 3);
		vao.unbindVao();

		EntityShader shader = new EntityShader();

		while (DisplayManager.isDisplayRunning()) {
			glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
			glClear(GL_COLOR_BUFFER_BIT);
			shader.start();
			vao.bindVao(0);
			glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
			vao.unbindVao(0);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		DisplayManager.destoryDisplay();
	}

}
