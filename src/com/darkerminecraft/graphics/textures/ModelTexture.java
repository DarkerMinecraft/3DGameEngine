package com.darkerminecraft.graphics.textures;

import com.darkerminecraft.opengl.Texture;

public class ModelTexture {
	
	private Texture texture;
	
	public ModelTexture(String name) {
		this.texture = new Texture("blocks/" + name + ".png");
	}

	public Texture getTexture() {
		return texture;
	}

}
