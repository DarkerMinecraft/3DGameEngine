package com.darkerminecraft.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.opengl.GL;

import com.darkerminecraft.utils.Keyboard;

public class DisplayManager {

	private static int WIDTH = 1280, HEIGHT = 720;
	private static String TITLE = "OpenGL 3D Game Engine";

	private static long window;

	public static void createDisplay() {
		glfwInit();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

		window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create GLFW window");

		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glViewport(0, 0, WIDTH, HEIGHT);
		glfwSetFramebufferSizeCallback(window, new GLFWFramebufferSizeCallback() {
			@Override
			public void invoke(long arg0, int width, int height) {
				WIDTH = width;
				HEIGHT = height;
				glViewport(0, 0, width, height);
			}
		});
		glfwSetKeyCallback(window, new Keyboard());
	}

	public static void updateDisplay() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	public static void destoryDisplay() {
		glfwTerminate();
	}
	
	public static boolean isDisplayRunning() {
		return !glfwWindowShouldClose(window);
	}
	
	public static float getAspectRatio() {
		return WIDTH / HEIGHT;
	}

}
