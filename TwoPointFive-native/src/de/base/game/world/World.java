package de.base.game.world;

import java.util.ArrayList;
import java.util.List;

public class World {

	private List<Chunk> chunks;
	
	private static WorldGeneration generation;
	
	public World() {
		chunks = new ArrayList<>();
		
		generation = new WorldGeneration(this);
		
		generateChunk();
	}

	private void generateChunk() {
		chunks.add(new Chunk(0,0));
		chunks.add(new Chunk(-1,0));
		chunks.add(new Chunk(-1,-1));
		chunks.add(new Chunk(0,-1));

	}
	
	public List<Chunk> getChunks() {
		return chunks;
	}
	
	public static WorldGeneration getGeneration() {
		return generation;
	}
	
}
