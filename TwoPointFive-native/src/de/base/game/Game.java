package de.base.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import de.base.engine.GameObject;
import de.base.game.world.Chunk;
import de.base.game.world.World;

public class Game {

	private List<GameObject> objects;
	private List<GameObject> removeObjects;
	
	private World world;

	public Game() {
		this.objects = new ArrayList<>();
		this.removeObjects = new ArrayList<>();

		this.world = new World();

		
		
	}

	public void update() {
		for (GameObject go : objects) {
			if (go.getRemove()) {
				removeObjects.add(go);
			}
			go.update();

		}

		for (GameObject go : removeObjects) {
			objects.remove(go);
		}
	}

	public void render(Graphics g) {
		for (Chunk chunk : world.getChunks()) {
			if(!chunk.onDisplay())
				continue;
			chunk.render(g);
		}
		for (GameObject go : objects) {
			go.render(g);
		}
	}

}
