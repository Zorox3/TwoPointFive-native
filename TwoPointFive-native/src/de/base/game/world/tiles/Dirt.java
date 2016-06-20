package de.base.game.world.tiles;

import de.base.engine.textures.ImageLoader;
import de.base.engine.textures.Texture;
import de.base.game.world.Tile;

public class Dirt extends Tile{

	public Dirt(int x, int y) {
		super(x, y, Texture.PLACEHOLDER, 0xaa6622);
		setTextureImage(ImageLoader.getImage(Texture.DIRT1.getName() + ":1_1"));

	}

}
