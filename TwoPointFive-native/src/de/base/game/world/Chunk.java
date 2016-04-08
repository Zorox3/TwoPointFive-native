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
		
		tiles = new ArrayList<>();
		
		fillChunk();
	}

	private void fillChunk() {
		for(int x = 0; x <= CHUNK_SIZE; x++){
			for(int y = 0; y <= CHUNK_SIZE; y++){
				int tx = (x * Tile.TILE_SIZE) + (posX * CHUNK_SIZE * Tile.TILE_SIZE);
				int ty = (y * Tile.TILE_SIZE) + (posY * CHUNK_SIZE * Tile.TILE_SIZE);
				
				tiles.add(new Tile(tx, ty, Texture.PLACEHOLDER));
			}	
		}
	}
	
	public void render(Graphics g){
		for(Tile tile : tiles){
			tile.render(g);
		}
	}
	
	public boolean onDisplay(){
		return true;
	}
}
