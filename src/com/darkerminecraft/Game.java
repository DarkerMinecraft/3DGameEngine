package com.darkerminecraft;

import static org.lwjgl.opengl.GL11.*;

import com.darkerminecraft.graphics.DisplayManager;

public class Game {
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		while(DisplayManager.isDisplayRunning()) {
			glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
			glClear(GL_COLOR_BUFFER_BIT);
			DisplayManager.updateDisplay();
		}
		DisplayManager.destoryDisplay();
	}

}
