package de.base.game.entities;

import de.base.engine.Objects.RenderObject;
import de.base.engine.textures.Texture;

public abstract class Entitiy extends RenderObject{

	protected boolean isMoving = false;
	protected Texture texture;

	public Entitiy(int posX, int posY, int width, int height, Texture texture) {
		super( posX,  posY,  width,  height);
		setTexture(texture);
	}
		
}
