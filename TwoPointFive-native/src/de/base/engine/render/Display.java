package de.base.engine.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import de.base.engine.inputhandler.InputHandler;
import de.base.game.Game;



public class Display extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Display instance;
	private boolean vsync = true;
	private int syncToFrames = 30;
	private boolean border = true;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	private Dimension size;
	private boolean isRunning = true;

	private Graphics g;
	private Thread thread;
	private int frames;
	private int ticks;
	
	public static JFrame frame;
		
	private Game game;
	private InputHandler input;

	public Display(int width, int height) {
		this.size = new Dimension(width, height);
		setPreferredSize(size);
		Display.instance = this;
		
		input = new InputHandler(this, game);
		Game.input = getInput();

	}

	public void start() {
		thread = new Thread(this, "Display Thread");
		thread.start();
		isRunning = true;
	}

	public static Display getDisplay() {
		return Display.instance;
	}

	public void init() {

		frame = new  JFrame();
		
		if (!border) {
			frame.setUndecorated(true);

		}
		frame.setLocationRelativeTo(null);

		frame.add(this);
		frame.pack();

		frame.setResizable(false);

		frame.setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
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

		while (isRunning) {

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

				frame.setTitle("FPS: "+ this.frames + " - UPS: " + this.ticks);
				
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
		
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();

		g.setColor(Color.WHITE);
		g.drawRect(0, 0, frame.getWidth(), frame.getHeight());
		
		
		
		game.render(g);


		
		g = getGraphics();
		g.dispose();
		bs.show();
	}
	
	public InputHandler getInput() {
		return input;
	}

	public void addGame(Game game) {
		this.game = game;
	}
}
