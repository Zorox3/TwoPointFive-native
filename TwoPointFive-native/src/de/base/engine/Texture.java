package de.base.engine;

import java.awt.image.BufferedImage;

public enum Texture {

	PLACEHOLDER("placeholder"), GRASS("grass"), STONE("stone"), SAND("sand"), DIRT("dirt");

	private String name;

	private Texture(String name) {
		this.name = name;
	}

	public BufferedImage getTexture() {
		return ImageLoader.getImage(name);
	}
	
	public String getName(){
		return name;
	}

}
