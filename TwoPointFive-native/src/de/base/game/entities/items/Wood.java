package de.base.game.entities.items;

import de.base.engine.textures.Texture;

public class Wood extends Item{

	public Wood() {
		this.name = "Holz";
		this.desc = "Holz DESC";
		setTexture(Texture.ITEM_WOOD);
	}

}
