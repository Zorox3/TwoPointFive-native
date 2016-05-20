package de.base.engine.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.base.engine.textures.Animation;
import de.base.engine.textures.Texture;

public abstract class RenderObject extends GameObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Color color = null;
	protected Texture texture = null;
	protected BufferedImage textureImage = null;
	protected boolean animated = false;
	protected Map<String, Animation> animations = new HashMap<>();

	public RenderObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void resetAllAnimations() {
		for (Entry<String, Animation> e : animations.entrySet()) {
			e.getValue().reset(this);
		}
	}

	protected void playAnimation(Animation a) {
		a.run();
		if (a.isUpdated()) {
			setTextureImage(a.getFrameImage());
		}
	}

	public RenderObject setColor(Color c) {
		this.color = c;

		return this;
	}

	public RenderObject setTexture(Texture texture) {
		this.texture = texture;
		this.textureImage = texture.getTexture();
		return this;
	}

	public void render(Graphics g) {
		if (color != null) {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		} else if (textureImage != null) {
			g.drawImage(textureImage, x, y, width, height, null);

		}
	}

	public void setTextureImage(BufferedImage textureImage) {
		this.textureImage = textureImage;
	}

	public boolean isAnimated() {
		return animated;
	}

	public void setAnimated(boolean animated) {
		this.animated = animated;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Color getColor() {
		return color;
	}

	public Texture getTexture() {
		return texture;
	}
}
