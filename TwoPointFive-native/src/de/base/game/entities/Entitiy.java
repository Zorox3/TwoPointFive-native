package de.base.game.entities;

import de.base.engine.Texture;
import de.base.game.gameObjects.RenderObject;

public abstract class Entitiy extends RenderObject{

	protected boolean isMoving = false;
	protected Texture texture;

	public Entitiy(int posX, int posY, int width, int height, Texture texture) {
		super( posX,  posY,  width,  height);
		setTexture(texture);
	}
		
}
