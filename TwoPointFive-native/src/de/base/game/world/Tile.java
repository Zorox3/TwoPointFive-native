package de.base.game.world;

import de.base.engine.Texture;
import de.base.game.gameObjects.RenderObject;

public class Tile extends RenderObject{

	public static final int TILE_SIZE = 16;
	
	
	
	public Tile(int x, int y, Texture texture) {
		super(x, y, TILE_SIZE, TILE_SIZE);

		setTexture(texture);
	
	}
	
}
