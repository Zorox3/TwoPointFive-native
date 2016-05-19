package de.base.engine;

import de.base.game.Game;

public class Main {

	public static void main(String[] args) {

		ImageLoader loader = new ImageLoader("res");

		if (loader.isFinish()) {
			Display display = new Display(1600, 1000);
			display.setSyncToFrames(60);
			display.setVsync(false);
			display.init();

			display.addGame(new Game());
			
			display.start();

		}

	}

}
