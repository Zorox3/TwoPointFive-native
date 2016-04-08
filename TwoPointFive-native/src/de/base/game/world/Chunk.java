package de.base.game.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import de.base.engine.Texture;

public class Chunk {

	public static final int CHUNK_SIZE = 16;

	private int posX;
	private int posY;

	private List<Tile> tiles;

	public Chunk(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;

		tiles = World.getGeneration().generateChunk(this);		
	}
	public void render(Graphics g) {
		for (Tile tile : tiles) {
			tile.render(g);
		}
	}

	public boolean onDisplay() {
		return true;
	}

	public Tile getTileByCoords(int x, int y) {
		for (Tile tile : tiles) {
			if (tile.getX() == x && tile.getY() == y) {
				return tile;
			}
		}
		return null;
	}
	
	public int getIdByCoords(int x, int y) {
		for (Tile tile : tiles) {
			if (tile.getX() == x && tile.getY() == y) {
				return tiles.indexOf(tile);
			}
		}
		return -1;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public void add(Tile e){
		tiles.add(e);
	}
}
