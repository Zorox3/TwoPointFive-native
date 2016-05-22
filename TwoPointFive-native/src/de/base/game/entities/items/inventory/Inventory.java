package de.base.game.entities.items.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.base.engine.render.Display;
import de.base.engine.textures.Texture;
import de.base.game.Game;

public class Inventory extends Rectangle {

	private static final long serialVersionUID = 1L;
	private List<Slot> inventorySlots;
	private Texture guiTexture;
	private boolean isActive;
	private String name;

	public Inventory(String name, int x, int y, int width, int height, Texture guiTexture) {
		inventorySlots = new ArrayList<>();
		this.guiTexture = guiTexture;
		this.name = name;
		setBounds(x + Display.instance.getWidth() / 2 - width / 2, y + Display.instance.getHeight() / 2 - height / 2,
				width, height);

	}

	public String getName() {
		return name;
	}

	public void addSlot(Slot s) {
		inventorySlots.add(s);
	}

	public void render(Graphics g) {
		if (isActive) {
			g.setColor(new Color(50, 50, 50, 150));
			g.fillRect(-Game.getPlayer().getOffsetX(), -Game.getPlayer().getOffsetY(), Display.instance.getWidth(),
					Display.instance.getHeight());
			g.drawImage(guiTexture.getTexture(), x, y, width, height, null);
			for (Slot s : inventorySlots) {
				s.render(g);
			}
		}
	}

	public boolean isActive() {
		return isActive;
	}

	public void activate() {
		this.isActive = true;
	}

	public void deactivate() {
		this.isActive = false;
	}

	public void setActive(boolean isActive) {
		setLocation((int) Game.getPlayer().getX() - width / 2, (int) Game.getPlayer().getY() - height / 2);
		for (Slot s : inventorySlots) {
			s.setLocation((int) (x + s.getPosX()), (int) (y + s.getPosY()));
		}
		this.isActive = isActive;
	}

}
