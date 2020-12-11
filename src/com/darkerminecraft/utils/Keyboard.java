package com.darkerminecraft.utils;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback {

	private static boolean[] keys = new boolean[GLFW_KEY_LAST];
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = (action == GLFW_PRESS);
	}
	
	public static boolean isKeyDown(int key) {
		return keys[key];
	}
	
}
