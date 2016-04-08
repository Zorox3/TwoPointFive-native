package de.base.engine;

import de.base.game.Game;

public class Main {

	public static void main(String[] args) {

		Display display  = new Display(1600, 1000);
		display.setSyncToFrames(59);
		display.addGame(new Game());
		display.init();
		
		
		new ImageLoader("res");

	}

}
