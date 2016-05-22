package de.base.game.entities.items.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.base.game.Game;

public class Slot extends Rectangle {
	private static final Color HOVER_COLOR = new Color(255, 255, 255, 150);
	private static final long serialVersionUID = 1L;

	public static final int SLOT_SIZE = 48;

	private int id;
	private ItemStack items;
	private Inventory inventory;
	private int posX, posY;

	public Slot(int id, int x, int y, Inventory inventory) {
		this.inventory = inventory;
		width = SLOT_SIZE;
		height = SLOT_SIZE;
		this.id = id;
		this.posX = x;
		this.posY = y;
		setBounds((int) (x + inventory.getX()), (int) (y + inventory.getY()), (int) (width), (int) (height));

		inventory.addSlot(this);

		if (Game.debubMode) System.out.println("Slot " + id + " created in " + inventory.getName());
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getId() {
		return id;
	}

	public void render(Graphics g) {
		if (contains(Game.input.mousePos)) {
			g.setColor(HOVER_COLOR);
			g.fillRect(x, y, width, height);
		}
	}

}
