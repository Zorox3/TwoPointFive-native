package de.base.engine.Objects;

import java.awt.Graphics;

public abstract class GameObject {

	private boolean remove = false;
	public void update(){
		
	}
	
	public void render(Graphics g){
		
	}
	
	public GameObject delete(){
		remove = true;
		return this;
	}
	
	public boolean getRemove(){
		return remove;
	}
	

	
}
