package de.base.game.world;

import java.util.ArrayList;

import de.base.game.Game;
import de.base.game.world.tiles.Dirt;
import de.base.game.world.tiles.Grass;
import de.base.game.world.tiles.Sand;
import de.base.game.world.tiles.Stone;

public class WorldGeneration {
	
	
	private float[][] whitenosie;
	private float[][] perlinnoise;
	
	
	public WorldGeneration(World world) {
				
	}
	
	public ArrayList<Tile> generateChunk(Chunk chunk){
		
		whitenosie = GenerateWhiteNoise(Chunk.CHUNK_SIZE, Chunk.CHUNK_SIZE);
		perlinnoise = GeneratePerlinNoise(whitenosie, 3);
		
		ArrayList<Tile> tiles = new ArrayList<>();
		
		for (int x = 0; x < Chunk.CHUNK_SIZE; x++) {
			for (int y = 0; y < Chunk.CHUNK_SIZE; y++) {
				int tx = (x * Tile.TILE_SIZE)
						+ (chunk.getPosX() * Chunk.CHUNK_SIZE * Tile.TILE_SIZE);
				int ty = (y * Tile.TILE_SIZE)
						+ (chunk.getPosY() * Chunk.CHUNK_SIZE * Tile.TILE_SIZE);

				Tile blockType;
	
		
				
				if (perlinnoise[x][y] >= 4f && perlinnoise[x][y] <= 8f) {
					blockType = new Stone(tx, ty);
				} else if (perlinnoise[x][y] >= 0f
						&& perlinnoise[x][y] <= 3f) {
					blockType = new Grass(tx, ty);

				} else if (perlinnoise[x][y] >= 9f
						&& perlinnoise[x][y] <= 13f) {
					blockType = new Sand(tx, ty);

				} else {
					blockType = new Dirt(tx, ty);
				}
				
				//System.out.println("TILE TYPE: " + blockType.getTexture().getName() + " > X: " + tx + " Y: " +ty);

				
				tiles.add(blockType);
			}
		}
				
		return tiles;
	}
	
	
	
//	private Chunk placeBlockCross(Chunk chunk, int x, int y, Tile tile) {
//
//		Tile tileAtPos = chunk.getTileByCoords(x, y);
//		tileAtPos = tile;
//		if (x - 1 > 0){
//			tileAtPos = chunk.getTileByCoords(x-1, y);
//		tileAtPos = tile;}
//
//		if (x + 1 < Chunk.CHUNK_SIZE){
//			tileAtPos = chunk.getTileByCoords(x+1, y);
//		tileAtPos = tile;}
//
//		if (y - 1 > 0){
//			tileAtPos = chunk.getTileByCoords(x, y-1);
//		tileAtPos = tile;}
//
//		if (y + 1 < Chunk.CHUNK_SIZE){
//			tileAtPos = chunk.getTileByCoords(x, y+1);
//		tileAtPos = tile;}
//
//		return chunk;
//	}

	private float[][] GenerateWhiteNoise(int width, int height) {
		 // Seed to 0 for testing
		float[][] noise = new float[width][height];

		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {
				noise[i][j] = (float) Game.globalRandom.nextDouble() % 1;
			}
		}

		return noise;
	}

	private float[][] GenerateSmoothNoise(float[][] baseNoise, int octave) {
		int width = baseNoise.length;
		int height = baseNoise[0].length;

		float[][] smoothNoise = new float[width][height];

		int samplePeriod = 1 << octave; // calculates 2 ^ k
		float sampleFrequency = 1.0f / samplePeriod;

		for (int i = 1; i < width; i++) {
			// calculate the horizontal sampling indices
			int sample_i0 = (i / samplePeriod) * samplePeriod;
			int sample_i1 = (sample_i0 + samplePeriod) % width; // wrap around
			float horizontal_blend = (i - sample_i0) * sampleFrequency;

			for (int j = 1; j < height; j++) {
				// calculate the vertical sampling indices
				int sample_j0 = (j / samplePeriod) * samplePeriod;
				int sample_j1 = (sample_j0 + samplePeriod) % height; // wrap
																		// around
				float vertical_blend = (j - sample_j0) * sampleFrequency;

				// blend the top two corners
				float top = Interpolate(baseNoise[sample_i0][sample_j0],
						baseNoise[sample_i1][sample_j0], horizontal_blend);

				// blend the bottom two corners
				float bottom = Interpolate(baseNoise[sample_i0][sample_j1],
						baseNoise[sample_i1][sample_j1], horizontal_blend);

				// final blend
				smoothNoise[i][j] = Interpolate(top, bottom, vertical_blend);
			}
		}
		return smoothNoise;
	}

	private float Interpolate(float x0, float x1, float alpha) {
		return x0 * (1 - alpha) + alpha * x1;
	}

	private float[][] GeneratePerlinNoise(float[][] baseNoise, int octaveCount) {
		int width = baseNoise.length;
		int height = baseNoise[0].length;

		float[][][] smoothNoise = new float[octaveCount][][];

		float persistance = 0.5f;

		// generate smooth noise
		for (int i = 0; i < octaveCount; i++) {
			smoothNoise[i] = GenerateSmoothNoise(baseNoise, i);
		}

		float[][] perlinNoise = new float[width][height];
		float amplitude = 1.0f;
		float totalAmplitude = 0.0f;

		// blend noise together
		for (int octave = octaveCount - 1; octave >= 0; octave--) {
			amplitude *= persistance;
			totalAmplitude += amplitude;

			for (int i = 1; i < width; i++) {
				for (int j = 1; j < height; j++) {
					perlinNoise[i][j] += smoothNoise[octave][i][j] * amplitude;
				}
			}
		}
		// normalisation
		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {
				perlinNoise[i][j] /= totalAmplitude / 10;
			}
		}
		return perlinNoise;
	}
}
