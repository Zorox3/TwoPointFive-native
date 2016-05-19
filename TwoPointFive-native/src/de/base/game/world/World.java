package de.base.game.world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class World {

	public static final int WORLD_SIZE = 2;

	private List<Chunk> chunks;

	private static WorldGeneration generation;
	private Point centerPoint;

	public World() {
		chunks = new ArrayList<>();

		generation = new WorldGeneration(this);

		generateChunk();

		centerPoint = new Point((WORLD_SIZE * Chunk.CHUNK_SIZE * Tile.TILE_SIZE / 2) * -1, (WORLD_SIZE * Chunk.CHUNK_SIZE * Tile.TILE_SIZE / 2) * -1);

	}

	private void generateChunk() {
		for (int x = 0; x < WORLD_SIZE; x++) {
			for (int y = 0; y < WORLD_SIZE; y++) {
				chunks.add(new Chunk(x, y));
			}
		}
	}

	public List<Chunk> getChunks() {
		return chunks;
	}

	public static WorldGeneration getGeneration() {
		return generation;
	}

	public Point getCenter() {
		System.out.println("World Center: " + centerPoint);
		return centerPoint;
	}

}
