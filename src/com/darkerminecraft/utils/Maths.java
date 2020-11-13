package com.darkerminecraft.utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {
	
	public static Matrix4f createTransformatinMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		matrix.translate(translation);
		matrix.rotateXYZ((float) Math.toRadians(rx), (float) Math.toRadians(ry), (float) Math.toRadians(rz));
		matrix.scale(scale);
		return matrix;
	}

}
