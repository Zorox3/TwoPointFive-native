package de.base.game.world.tiles;

import java.awt.Color;
import java.awt.Graphics;

import de.base.engine.textures.Texture;
import de.base.game.Game;
import de.base.game.world.Tile;

public class Tree extends Tile{

	public Tree(int x, int y) {
		super(x, y,TILE_SIZE*3, TILE_SIZE*3 ,Texture.TREE_01);
			
	}
	
	 @Override
	public void render(Graphics g) {
		super.render(g);
		if (Game.debubModeBounding) {
			g.setColor(Color.RED);
			g.drawRect(x, y, width, height);
		}
	}

}
