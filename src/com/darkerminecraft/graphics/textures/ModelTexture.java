package com.darkerminecraft.graphics.textures;

import com.darkerminecraft.opengl.Texture;

public class ModelTexture {

	private Texture texture;

	private float shineDamper = 1, reflectivity = 0;

	public ModelTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return texture;
	}

	public float getShineDamper() {
		return shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}

}
