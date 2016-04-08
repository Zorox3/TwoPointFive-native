package de.base.game.entities;

import de.base.engine.Texture;
import de.base.game.Game;

public class Player extends Entitiy {

	private int offsetX;
	private int offsetY;

	public Player(int posX, int posY, int width, int height, Texture texture) {
		super(posX, posY, width, height, texture);

		this.offsetX = x;
		this.offsetY = y;
		
	}

	public void addOffsetX(int x) {
		this.offsetX += x * getSpeed();
	}



	public void addOffsetY(int y) {
		this.offsetY += y * getSpeed();
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}
	
	private int getSpeed() {
		return 5;
	}
	
	public void update(){
		if(Game.input.up.isPressed())
			addOffsetY(1);
		if(Game.input.down.isPressed())
			addOffsetY(-1);
		if(Game.input.left.isPressed())
			addOffsetX(1);
		if(Game.input.right.isPressed())
			addOffsetX(-1);
	}

}
