package de.base.game.world.tiles;

import de.base.engine.textures.ImageLoader;
import de.base.engine.textures.Texture;
import de.base.game.world.Tile;

public class Stone extends Tile{

	public Stone(int x, int y) {
		super(x, y, Texture.PLACEHOLDER);
		setTextureImage(ImageLoader.getImage(Texture.STONE.getName() + ":1_1"));

	}

}
