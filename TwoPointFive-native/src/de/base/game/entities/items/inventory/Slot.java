package de.base.game.entities.items.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import de.base.engine.inputhandler.InputHandler;
import de.base.engine.inputhandler.InputHandler.GameActionListener;
import de.base.engine.inputhandler.InputHandler.InputEvent;
import de.base.engine.inputhandler.InputHandler.InputEventType;
import de.base.engine.render.Display;
import de.base.game.Game;
import de.base.game.entities.items.Item;
import de.base.game.entities.items.Wood;

public class Slot extends Rectangle implements GameActionListener {
	private static final Color HOVER_COLOR = new Color(255, 255, 255, 120);
	private static final long serialVersionUID = 1L;

	public static final int SLOT_SIZE = 48;

	private int id;
	private ItemStack items;
	private Inventory inventory;
	private int posX, posY;
	private boolean dragged = false;
	public static Slot draggedSlot;

	public Slot(int id, int x, int y, Inventory inventory) {
		this.inventory = inventory;
		this.items = new ItemStack();
		width = SLOT_SIZE;
		height = SLOT_SIZE;
		this.id = id;
		this.posX = x;
		this.posY = y;
		setBounds((int) (x + inventory.getX()), (int) (y + inventory.getY()), (int) (width), (int) (height));

		inventory.addSlot(this);

		InputHandler.addListener(this);

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

	public void addItem(Item i) {
		items.addItem(i);
	}

	public void render(Graphics g) {
		if (contains(Game.input.mousePos)) {
			g.setColor(HOVER_COLOR);
			g.fillRect(x, y, width, height);
		}
		if (items.getItemList().size() > 0) {
			if (dragged) {

				items.getItemList().get(0).setPoistion(Game.input.mousePos.x, Game.input.mousePos.y);

			} else {
				items.getItemList().get(0).setPoistion(x, y);

			}
			items.getItemList().get(0).setWidth(SLOT_SIZE);
			items.getItemList().get(0).setHeight(SLOT_SIZE);
			items.getItemList().get(0).render(g);
			g.setColor(Color.WHITE);
			if (dragged) g.drawString("" + items.getItemList().size(), (int) Game.input.mousePos.x + SLOT_SIZE - 15, (int) Game.input.mousePos.y + SLOT_SIZE - 5);
			else
				g.drawString("" + items.getItemList().size(), (int) x + SLOT_SIZE - 15, (int) y + SLOT_SIZE - 5);
		}
	}

	public boolean addItemStack(ItemStack stack) {
		if (items.getItemList().size() == 0) {
			items = stack;
			return true;
		}
		return false;
	}

	public ItemStack getItemStack() {
		return items;
	}

	@Override
	public void actionPerformed(InputEvent event) {
		if (contains(Game.input.mousePos)) {
			if (event.key.id == Game.input.mouseleft.id && event.type == InputEventType.PRESSED) {

				if (items.getItemList().size() > 0) {
					draggedSlot = this;
					dragged = true;
					return;
				}
				if (draggedSlot != this) {
					if (draggedSlot != null) {

						for(Item i : draggedSlot.items.getItemList()){
							items.addItem(i);
						}
						
						draggedSlot.items = new ItemStack();
						draggedSlot.dragged = false;
					}
				}
				draggedSlot = null;

			}
		}
	}
}
