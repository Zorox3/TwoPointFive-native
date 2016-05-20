package de.base.engine.Objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import de.base.game.Game;
import de.base.game.entities.Player;

public abstract class GameObject extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	protected int x;
	protected int y;

	public int finalPosX;
	public int finalPosY;
	
	protected int width;
	protected int height;

	private boolean remove = false;

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		setBounds(x, y, width, height);
	}

	public void update() {

	}

	public void render(Graphics g) {
	}

	public GameObject delete() {
		remove = true;
		return this;
	}

	public boolean getRemove() {
		return remove;
	}

}
