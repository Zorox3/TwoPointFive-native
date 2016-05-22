package de.base.game.entities.items.inventory;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import de.base.game.Game;
import de.base.game.entities.items.Item;
import de.base.game.world.Tile;

public class ItemStack {

	private List<Item> itemList;
	
	public ItemStack() {
		itemList = new ArrayList<>();
	}
	
	public void addItem(Item item){
		itemList.add(item);
	}
	
	public void render(Graphics2D g2){
		for (Item i : itemList) {
			g2.drawImage(i.getTexture().getTexture(), (Tile.TILE_SIZE >> 2) + Game.globalRandom.nextInt(10), (Tile.TILE_SIZE >> 2) + Game.globalRandom.nextInt(10), Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2, null);
		}
	}
	
	public List<Item> getItemList() {
		return itemList;
	}
	
	public void remove(Item item){
		itemList.remove(item);
	}
	
}
