package de.base.engine;

import java.awt.Graphics;

public abstract class GameObject {

	private boolean remove = false;
	
	public void update(){
		
	}
	
	public void render(Graphics g){
		
	}
	
	public void delete(){
		remove = true;
	}
	
	public boolean getRemove(){
		return remove;
	}
	
}
