package com.darkerminecraft;

import org.joml.Vector3f;

import com.darkerminecraft.entity.Entity;
import com.darkerminecraft.graphics.Camera;
import com.darkerminecraft.graphics.DisplayManager;
import com.darkerminecraft.graphics.Light;
import com.darkerminecraft.graphics.Loader;
import com.darkerminecraft.graphics.MasterRenderer;
import com.darkerminecraft.graphics.model.RawModel;
import com.darkerminecraft.graphics.model.TexturedModel;
import com.darkerminecraft.graphics.textures.ModelTexture;
import com.darkerminecraft.shaders.entities.EntityShader;
import com.darkerminecraft.threads.OBJThread;
import com.darkerminecraft.threads.TextureThread;

public class Game {

	public static final Object SYNCOBJECT = new Object();

	public static void main(String[] args) throws Exception {
		synchronized (SYNCOBJECT) {
			OBJThread objThread = new OBJThread();
			objThread.start();
			SYNCOBJECT.wait();
			
			TextureThread textureThread = new TextureThread();
			textureThread.start();
			SYNCOBJECT.wait();
			
			DisplayManager.createDisplay();
			
			textureThread.loadBinding();
			
			MasterRenderer renderer = new MasterRenderer();

			RawModel model = Loader.loadToVAO("dragon");
			
			ModelTexture modelTexture = TextureThread.getDefualtTexture("purple");
			
			modelTexture.setShineDamper(10);
			modelTexture.setReflectivity(1);
			
			TexturedModel texturedModel = new TexturedModel(model, modelTexture);

			Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -25), 0, 0, 0, 1);

			EntityShader shader = new EntityShader();
			
			Camera camera = new Camera();
			Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
			
			while (DisplayManager.isDisplayRunning()) {
				entity.increaseRotation(.2f, .2f, .2f);
				camera.move();
				renderer.prepare();
				shader.start();
				shader.loadLight(light);
				renderer.render(entity);
				shader.stop();
				DisplayManager.updateDisplay();
			}
			DisplayManager.destoryDisplay();
		}
	}

}
