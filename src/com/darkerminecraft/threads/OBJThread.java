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
import com.darkerminecraft.graphics.obj.ModelData;
import com.darkerminecraft.graphics.obj.OBJFileLoader;

public class OBJThread implements Runnable {
	
	private Thread thread;
	private static HashMap<String, ModelData> objs = new HashMap<String, ModelData>();

	@Override
	public void run() {
		synchronized (Game.SYNCOBJECT) {
			URI uri = null;
			try {
				uri = TextureThread.class.getResource("/res/objs").toURI();
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
				if(file.getName().endsWith("obj")) {
					String name = file.getName().replace(".obj", "");
					objs.put(name, OBJFileLoader.loadOBJ(name));
					System.out.println("OBJ ~ " + name);
				}
			}
			Game.SYNCOBJECT.notify();
		}
	}
	
	private List<Path> getListFromIterator(Iterator<Path> p) {
		List<Path> list = new ArrayList<Path>();
		while(p.hasNext()) {
			list.add(p.next());
		}
		return list;
	}
	
	public static ModelData getOBJ(String name) {
		return objs.get(name);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}

}
