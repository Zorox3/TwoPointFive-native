package de.base.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import de.base.game.Game;



public class Display extends JFrame implements Runnable {

	private static Display display;
	private boolean vsync = true;
	private int syncToFrames = 30;
	private boolean border = true;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	private Dimension size;
	private boolean isRunning = true;
	private Image screen;

	private Graphics g;
	private Dimension pixel;
	private Thread thread;
	private int frames;
	private int ticks;
		
	private Game game;
	private InputHandler input;

	public Display(int width, int height) {
		this.size = new Dimension(width, height);
		setPreferredSize(size);
		Display.display = this;
		
		input = new InputHandler(this, game);
		Game.input = Display.getDisplay().getInput();

	}

	public void start() {
		thread = new Thread(this, "Display Thread");
		thread.start();
	}

	public static Display getDisplay() {
		return Display.display;
	}

	public void init() {

		if (!border) {
			setUndecorated(true);

		}
		setLocationRelativeTo(null);

		pack();

		setResizable(false);

		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		pixel = new Dimension(getWidth(), getHeight());

		screen = createVolatileImage(pixel.width, pixel.height);

		start();
		
	}
	
	public int getWidth(){
		return this.size.width;
	}
	public int getHeight(){
		return this.size.height;
	}

	public void run() {

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		double nsPerTickRender = 1000000000D / getSyncToFrames();
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		double deltaRender = 0;

		int ticks = 0;
		int frames = 0;

		while (true) {

			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			deltaRender += (now - lastTime) / nsPerTickRender;
			lastTime = now;

			boolean shouldRender = getSync();

			if (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;

			}
			if (deltaRender >= 1) {
				deltaRender -= 1;
				shouldRender = true;

			}

			if (shouldRender) {
				frames++;

				render();

			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				this.frames = frames;
				this.ticks = ticks;
				frames = 0;
				ticks = 0;

				setTitle("FPS: "+ this.frames + " - UPS: " + this.ticks);
				
			}
		}
	}

	public void setSyncToFrames(int syncToFrames) {
		this.syncToFrames = syncToFrames;
	}

	public int getSyncToFrames() {
		return syncToFrames;
	}

	public boolean getSync() {
		return !vsync;
	}

	public void setVsync(boolean vsync) {
		this.vsync = vsync;
	}

	public int getTicks() {
		return ticks;
	}

	public int getFramesPerSecond() {
		return frames;
	}
	
	private void tick() {
		game.update();
		input.tick();
	}

	private void render() {
		g = screen.getGraphics();

		
		
		game.render(g);


		
		g = getGraphics();
		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();
	}
	
	public InputHandler getInput() {
		return input;
	}

	public void addGame(Game game) {
		this.game = game;
	}
}
