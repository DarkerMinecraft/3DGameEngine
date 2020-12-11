package com.darkerminecraft.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import com.darkerminecraft.entity.Entity;
import com.darkerminecraft.graphics.model.RawModel;
import com.darkerminecraft.graphics.model.TexturedModel;
import com.darkerminecraft.opengl.Texture;
import com.darkerminecraft.opengl.Vao;
import com.darkerminecraft.shaders.entities.EntityShader;
import com.darkerminecraft.utils.Maths;

public class EntityRenderer {
	
	private EntityShader shader;
	
	public EntityRenderer() {
		shader = new EntityShader();
	}
	
	public void render(Entity entity) {
		TexturedModel model = entity.getTexturedModel();
		RawModel rawModel = model.getRawModel();
		Texture texture = model.getModelTexture().getTexture();
		Vao vao = rawModel.getVao();
		vao.bindVao(0, 1, 2);
		shader.transformationMatrix.storeMatrix(Maths.createTransformatinMatrix(entity.getPosition(), entity.getRx(), entity.getRy(), entity.getRz(), entity.getScale()));
		shader.projViewMatrix.storeMatrix(Camera.getProjViewMatrix());
		shader.shineDamper.storeFloat(model.getModelTexture().getShineDamper());
		shader.reflectivity.storeFloat(model.getModelTexture().getReflectivity());
		shader.cameraPosition.storeVector3f(Camera.getPosition());
		glActiveTexture(GL_TEXTURE0);
		texture.bind();
		glDrawElements(GL_TRIANGLES, rawModel.getVertexCount(), GL_UNSIGNED_INT, 0);
		texture.unbind();
		vao.unbindVao(0, 1, 2);
	}

}
