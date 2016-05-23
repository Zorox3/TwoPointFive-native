package de.base.game.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import de.base.engine.render.Display;
import de.base.game.Game;

public class Chunk extends Rectangle {

	public static final int CHUNK_SIZE = 16;
	public static final int SIZE = CHUNK_SIZE * Tile.TILE_SIZE;

	public static Chunk playerChunk = null;

	private int posX;
	private int posY;
	private Point coordCornerTL;
	private Point coordCornerBR;

	private List<Tile> tiles;

	public Chunk(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;

		this.coordCornerTL = new Point(CHUNK_SIZE * Tile.TILE_SIZE * posX, CHUNK_SIZE * Tile.TILE_SIZE * posY);
		this.coordCornerBR = new Point(CHUNK_SIZE * Tile.TILE_SIZE * posX + CHUNK_SIZE * Tile.TILE_SIZE, CHUNK_SIZE * Tile.TILE_SIZE * posY + CHUNK_SIZE * Tile.TILE_SIZE);

		setBounds(coordCornerTL.x, coordCornerTL.y, SIZE, SIZE);
	}

	public int generateChunk() {
		this.tiles = World.getGeneration().generateChunk(this);
		return tiles.size();
	}

	public void render(Graphics g) {
		for (Tile tile : tiles) {
			tile.render(g);
		}
		if (Game.debubModeBounding) {
			g.setColor(Color.RED);
			g.drawRect(coordCornerTL.x, coordCornerTL.y, CHUNK_SIZE * Tile.TILE_SIZE, CHUNK_SIZE * Tile.TILE_SIZE);
		}
	}

	public boolean onDisplay() {

		if (coordCornerTL.x + Game.getPlayer().getOffsetX() >= Display.frame.getWidth()) {
			return false;
		}
		if (coordCornerBR.x + Game.getPlayer().getOffsetX() <= 0) {
			return false;
		}

		if (coordCornerTL.y + Game.getPlayer().getOffsetY() >= Display.frame.getHeight()) {
			return false;
		}
		if (coordCornerBR.y + Game.getPlayer().getOffsetY() <= 0) {
			return false;
		}
		playerInChunk();
		//if (playerChunk != null) System.out.println(playerChunk.getPosX() + " " + playerChunk.getPosY());

		return true;
	}

	public void playerInChunk() {
		if (this != playerChunk) {
			if (Game.getPlayer().intersects(this)) {
				playerChunk = this;
			}
		}
	}

	public Tile getTileByCoords(int x, int y) {
		for (Tile tile : tiles) {
			if (tile.getX() == x && tile.getY() == y) {
				return tile;
			}
		}
		return null;
	}

	public int getIdByCoords(int x, int y) {
		for (Tile tile : tiles) {
			if (tile.getX() == x && tile.getY() == y) {
				return tiles.indexOf(tile);
			}
		}
		return -1;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void add(Tile e) {
		tiles.add(e);
	}
}
