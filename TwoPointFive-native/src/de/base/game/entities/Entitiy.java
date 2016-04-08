package de.base.game.entities;

import java.awt.Graphics;

import de.base.engine.Texture;
import de.base.game.gameObjects.RenderObject;

public abstract class Entitiy extends RenderObject{


	protected Texture texture;

	public Entitiy(int posX, int posY, int width, int height, Texture texture) {
		super( posX,  posY,  width,  height);
		setTexture(texture);
	}
		
}
