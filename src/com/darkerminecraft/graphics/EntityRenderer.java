package com.darkerminecraft.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import com.darkerminecraft.graphics.model.RawModel;
import com.darkerminecraft.graphics.model.TexturedModel;
import com.darkerminecraft.opengl.Texture;
import com.darkerminecraft.opengl.Vao;

public class EntityRenderer {
	
	public EntityRenderer() {
		
	}
	
	public void render(TexturedModel model) {
		RawModel rawModel = model.getRawModel();
		Texture texture = model.getModelTexture().getTexture();
		Vao vao = rawModel.getVao();
		vao.bindVao(0, 1);
		glActiveTexture(GL_TEXTURE0);
		texture.bind();
		glDrawElements(GL_TRIANGLES, rawModel.getVertexCount(), GL_UNSIGNED_INT, 0);
		texture.unbind();
		vao.unbindVao(0, 1);
	}

}
