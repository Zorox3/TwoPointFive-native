package de.base.engine;

import de.base.game.Game;

public class Main {

	public static void main(String[] args) {

		Display display  = new Display(new Game(), 800, 600);
		display.init();
		
		
		new ImageLoader("res");

	}

}
