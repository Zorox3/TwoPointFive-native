package de.base.game.world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class World {

	public static final int WORLD_SIZE = 10;

	private List<Chunk> chunks;

	private static WorldGeneration generation;
	private Point centerPoint;

	public World() {
		chunks = new ArrayList<>();

		generation = new WorldGeneration(this);

		centerPoint = new Point((WORLD_SIZE * Chunk.CHUNK_SIZE * Tile.TILE_SIZE / 2) * -1, (WORLD_SIZE * Chunk.CHUNK_SIZE * Tile.TILE_SIZE / 2) * -1);

	}

	public int generateChunk(int x, int y) {
		Chunk chunk = new Chunk(x, y);
		chunk.generateChunk();
			chunks.add(chunk);
		
		return chunks.size();
	}

	public List<Chunk> getChunks() {
		return chunks;
	}

	public static WorldGeneration getGeneration() {
		return generation;
	}

	public Point getCenter() {
		return centerPoint;
	}

}
