package com.darkerminecraft.threads;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.darkerminecraft.Game;
import com.darkerminecraft.graphics.textures.ModelTexture;
import com.darkerminecraft.opengl.Texture;

public class TextureThread implements Runnable {

	private Thread thread;

	private static HashMap<String, Texture> defualtTextures = new HashMap<String, Texture>();
	private static HashMap<String, Texture> terrainTextures = new HashMap<String, Texture>();

	@Override
	public void run() {
		synchronized (Game.SYNCOBJECT) {
			loadDefualtDirectoryTextures();
			loadTerrainDirectoryTextures();
			Game.SYNCOBJECT.notify();
		}
	}
	
	private void loadDefualtDirectoryTextures() {
		URI uri = null;
		try {
			uri = TextureThread.class.getResource("/res").toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Path myPath;
		if (uri.getScheme().equals("jar")) {
			FileSystem fileSystem = null;
			try {
				fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
			} catch (IOException e) {
				e.printStackTrace();
			}
			myPath = fileSystem.getPath("/resources");
		} else {
			myPath = Paths.get(uri);
		}
		Stream<Path> walk = null;
		try {
			walk = Files.walk(myPath, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Path> pathList = getListFromIterator(walk.iterator());
		for (Path p : pathList) {
			File file = p.toFile();
			if(file.getName().endsWith("png")) {
				String name = file.getName().replace(".png", "");
				defualtTextures.put(name, new Texture(name));
				System.out.println("DEFUALT TEXTURE ~ " + name);
			}
		}
	}
	
	private void loadTerrainDirectoryTextures() {
		URI uri = null;
		try {
			uri = TextureThread.class.getResource("/res/terrains/").toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Path myPath;
		if (uri.getScheme().equals("jar")) {
			FileSystem fileSystem = null;
			try {
				fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
			} catch (IOException e) {
				e.printStackTrace();
			}
			myPath = fileSystem.getPath("/resources");
		} else {
			myPath = Paths.get(uri);
		}
		Stream<Path> walk = null;
		try {
			walk = Files.walk(myPath, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Path> pathList = getListFromIterator(walk.iterator());
		for (Path p : pathList) {
			File file = p.toFile();
			if(file.getName().endsWith("png")) {
				String name = file.getName().replace(".png", "");
				terrainTextures.put(name, new Texture("terrains/" + name));
				System.out.println("TERAIN TEXTURE ~ " + name);
			}
		}
	}

	public void loadBinding() {
		terrainTextures.values().forEach((Texture text) -> text.openglBinding());
		defualtTextures.values().forEach((Texture text) -> text.openglBinding());
	}
	
	private List<Path> getListFromIterator(Iterator<Path> p) {
		List<Path> list = new ArrayList<Path>();
		while(p.hasNext()) {
			list.add(p.next());
		}
		return list;
	}

	public static ModelTexture getDefualtTexture(String name) {
		return new ModelTexture(defualtTextures.get(name));
	}
	
	public static ModelTexture getTerrainTexture(String name) {
		return new ModelTexture(terrainTextures.get(name));
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}

}