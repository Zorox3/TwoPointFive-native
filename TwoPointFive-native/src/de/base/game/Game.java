package de.base.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import de.base.engine.Objects.GameObject;
import de.base.engine.inputhandler.InputHandler;
import de.base.engine.render.Display;
import de.base.game.entities.Player;
import de.base.game.world.Chunk;
import de.base.game.world.Tile;
import de.base.game.world.World;

public class Game {

	private List<GameObject> objects;
	private List<GameObject> removeObjects;
	
	private World world;

	public static InputHandler input;
	private static Player player;
	
	public static Random globalRandom = new Random();
	
	private JFrame display;
	
	public Game() {

		display = Display.frame;
		
		this.objects = new ArrayList<>();
		this.removeObjects = new ArrayList<>();
		this.world = new World();

		player = new Player(display.getWidth() / 2 - Tile.TILE_SIZE / 2, display.getHeight() / 2 - Tile.TILE_SIZE, 64, 96, world); 
		
		objects.add(player);
		

		
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
	public static Player getPlayer() {
		return player;
	}
	
}
