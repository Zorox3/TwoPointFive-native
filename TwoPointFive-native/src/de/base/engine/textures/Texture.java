package de.base.engine.textures;

import java.awt.image.BufferedImage;

public enum Texture {

	PLACEHOLDER("placeholder"), GRASS("grass"), STONE("stone"), SAND("sand"), DIRT("dirt"), PLAYER_WALK("player_walk", true, 4, 8, 64, 96), ITEM_WOOD("wood"), GUI_INVENTORY("inventory"), GRASS_CORNER("grass_corner"), GRASS_STRAIGHT("grass_straight"), TREE_01("tree01");

	private String name;
	private boolean isSprite;
	private int rows;
	private int cols;
	private int width;
	private int height;

	private Texture(String name, Object... params) {
		this.name = name;
		if (params.length > 0) {
			this.isSprite = (boolean) params[0];
			this.rows = (int) params[1];
			this.cols = (int) params[2];
			this.width = (int) params[3];
			this.height = (int) params[4];
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
