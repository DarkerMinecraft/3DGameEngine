package com.darkerminecraft.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.darkerminecraft.utils.Keyboard;

public class Camera {
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	private static Vector3f position = new Vector3f(0, 0, 0);
	private static float pitch = 0, yaw = 0, roll = 0;
	
	public void move() {
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
			position.z -= 0.02f;
		} 
		else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
			position.x += 0.02f;
		}
		else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
			position.x -= 0.02f;
		}
	}
	
	public static Matrix4f getProjViewMatrix() {
		return loadProjectionMatrix().mul(loadViewMatrix());
	}
	
	private static Matrix4f loadViewMatrix() {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.identity();
		viewMatrix.rotateXYZ((float) Math.toRadians(pitch), (float) Math.toRadians(yaw), (float) Math.toRadians(roll));
		Vector3f negativeCameraPosition = new Vector3f(-position.x, -position.y, -position.z);
		viewMatrix.translate(negativeCameraPosition);
		return viewMatrix;
	}
	
	private static Matrix4f loadProjectionMatrix() {
		float aspectRatio = DisplayManager.getAspectRatio();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		Matrix4f projectionMatrix = new Matrix4f();
		projectionMatrix.m00(x_scale);
		projectionMatrix.m11(y_scale);
		projectionMatrix.m22(-((FAR_PLANE + NEAR_PLANE) / frustum_length));
		projectionMatrix.m23(-1);
		projectionMatrix.m32(-((2 * NEAR_PLANE * FAR_PLANE) / frustum_length));
		projectionMatrix.m33(0);
		return projectionMatrix;
	}

	public static Vector3f getPosition() {
		return position;
	}

}
