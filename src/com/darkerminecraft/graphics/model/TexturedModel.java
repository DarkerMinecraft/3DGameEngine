package com.darkerminecraft.graphics.model;

import com.darkerminecraft.graphics.textures.ModelTexture;

public class TexturedModel {
	
	private RawModel rawModel;
	private ModelTexture modelTexture;

	public TexturedModel(RawModel rawModel, ModelTexture modelTexture) {
		super();
		this.rawModel = rawModel;
		this.modelTexture = modelTexture;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getModelTexture() {
		return modelTexture;
	}
	
}
