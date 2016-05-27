package de.base.engine.textures;

import java.awt.image.BufferedImage;

import de.base.game.world.Tile;

public enum Texture {

	PLACEHOLDER("placeholder"), GRASS("grass"), DIRT1("dirt_grass1", true, 3, 3, Tile.TILE_SIZE, Tile.TILE_SIZE), DIRT2("dirt_grass2", true, 3, 3, Tile.TILE_SIZE, Tile.TILE_SIZE), STONE("stone1", true, 3, 3, Tile.TILE_SIZE, Tile.TILE_SIZE), SAND("placeholder"), PLAYER_WALK("player_walk", true, 4, 8, 64, 96), ITEM_WOOD("wood"), GUI_INVENTORY("inventory"), GRASS_CORNER("grass_corner"), GRASS_STRAIGHT("grass_straight"), TREE_01("tree01");

	private String name;
	private boolean isSprite;
	private int rows;
	private int cols;
	private int width;
	private int height;
	private int offsetX = 0;
	private int offsetY = 0;

	private Texture(String name, Object... params) {
		this.name = name;
		if (params.length > 0) {
			this.isSprite = (boolean) params[0];
			this.rows = (int) params[1];
			this.cols = (int) params[2];
			this.width = (int) params[3];
			this.height = (int) params[4];
			if (params.length > 5) {
				this.offsetX = (int) params[5];
				this.offsetY = (int) params[6];
			}
		}
	}

	public BufferedImage getTexture() {
		return ImageLoader.getImage(name);
	}

	public String getName() {
		return name;
	}

	public boolean isSprite() {
		return isSprite;
	}

	public int getCols() {
		return cols;
	}

	public int getHeight() {
		return height;
	}

	public int getRows() {
		return rows;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public int getWidth() {
		return width;
	}

	public static Texture fromString(String text) {
		if (text != null) {
			for (Texture b : Texture.values()) {
				if (text.equalsIgnoreCase(b.name)) {
					return b;
				}
			}
		}
		return null;
	}

}
