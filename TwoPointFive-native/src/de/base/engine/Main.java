package de.base.engine;

import de.base.game.StartUp;

public class Main {

	private static Thread sThread;

	public static void main(String[] args) {

		Main main = new Main();
		
		main.startGame();

	}
	
	public void startGame() {
		sThread = new Thread(new StartUp());

		sThread.start();
		System.out.println("Thread Startup gestartet");
	}

}
