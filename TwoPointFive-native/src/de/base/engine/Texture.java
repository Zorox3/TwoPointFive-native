package de.base.engine;

import java.awt.image.BufferedImage;

public enum Texture {

	PLACEHOLDER("placeholder");

	private String name;

	private Texture(String name) {
		this.name = name;
	}

	public BufferedImage getTexture() {
		return ImageLoader.getImage(name);
	}

}
