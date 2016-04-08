package de.base.game.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import de.base.engine.GameObject;
import de.base.engine.Texture;

public abstract class RenderObject extends GameObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Color color = null;
	protected Texture texture = null;

	public RenderObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public RenderObject setColor(Color c) {
		this.color = c;

		return this;
	}

	public RenderObject setTexture(Texture texture) {
		this.texture = texture;
		return this;
	}

	public void render(Graphics g) {
		if (color != null) {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		} else if (texture != null) {
			g.drawImage(texture.getTexture(), x, y, width, height, null);

		}
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
