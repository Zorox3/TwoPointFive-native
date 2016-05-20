package de.base.game;

import de.base.engine.render.Display;
import de.base.engine.textures.ImageLoader;
import de.base.game.world.World;

public class StartUp implements Runnable{

	private Game game;
	@Override
	public void run() {
		start();
	}

	private void start() {
		ImageLoader loader = new ImageLoader();
		loader.load("res/textures");
		loader.load("res/items");

		//Thread.sleep(100);

		Display display = new Display(1600, 1000);
		display.setSyncToFrames(60);
		display.setVsync(true);
		display.init();

		//Thread.sleep(100);

		game = new Game();

		display.addGame(game);

		display.start();

		
		new Thread("World Generation Thread"){			
			@Override
			public void run(){
				System.out.println("World Started");
				game.world = new World();
				for (int x = 0; x < World.WORLD_SIZE; x++) {
					for (int y = 0; y < World.WORLD_SIZE; y++) {
						game.world.generateChunk(x, y);
						if((x+1) * (y+1) == World.WORLD_SIZE * World.WORLD_SIZE){
							intiMenu();
						}
					}
				}
			}
		}.start();
		


	}
	private void intiMenu() {
		System.out.println("Init Menu");

		game.init();
		

		
	}
}