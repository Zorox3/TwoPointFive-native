package de.base.game.world.tiles;

import de.base.engine.textures.Texture;
import de.base.game.world.Tile;

public class Grass extends Tile{

	public Grass(int x, int y) {
		super(x, y, Texture.GRASS, 0x00ff00);
	}

}
