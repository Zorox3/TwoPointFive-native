package de.base.game.world;

import java.awt.Graphics;

import de.base.engine.Objects.RenderObject;
import de.base.engine.textures.Texture;
import de.base.game.Game;

public class Tile extends RenderObject{

	public static final int TILE_SIZE = 64;
	
	
	
	public Tile(int x, int y, Texture texture) {
		super(x, y, TILE_SIZE, TILE_SIZE);

		setTexture(texture);
	
	}
	
	public void render(Graphics g) {
		g.drawImage(texture.getTexture(), x + Game.getPlayer().getOffsetX(), y + Game.getPlayer().getOffsetY(), width, height, null);
	}

	
}
