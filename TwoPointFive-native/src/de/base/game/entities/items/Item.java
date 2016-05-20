package de.base.game.entities.items;

import de.base.engine.Objects.RenderObject;
import de.base.game.world.Tile;

public abstract class Item extends RenderObject {

	protected String name;
	protected String desc = "DESC";

	public Item() {
		super(0, 0, Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2);
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}
	
	public void setPoistion(int x, int y){
		this.x = x;
		this.y = y;
	}
}
