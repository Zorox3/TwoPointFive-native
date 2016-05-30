package de.base.game.entities;

import java.awt.Color;
import java.awt.Graphics;

import de.base.engine.inputhandler.InputHandler;
import de.base.engine.inputhandler.InputHandler.GameActionListener;
import de.base.engine.inputhandler.InputHandler.InputEvent;
import de.base.engine.render.Display;
import de.base.engine.sound.SoundLoader;
import de.base.engine.textures.Animation;
import de.base.engine.textures.ImageLoader;
import de.base.engine.textures.Texture;
import de.base.game.Game;
import de.base.game.entities.items.Wood;
import de.base.game.entities.items.inventory.Inventory;
import de.base.game.entities.items.inventory.Slot;
import de.base.game.world.Chunk;
import de.base.game.world.Tile;
import de.base.game.world.World;

public class Player extends Entitiy implements GameActionListener {

	private int maxSpeed = 12;

	private int offsetX;
	private int offsetY;
	private Inventory inventory;

	public Player(int posX, int posY, int width, int height, World world) {
		super(posX, posY, width, height, Texture.PLACEHOLDER);

		InputHandler.addListener(this);

		setTextureImage(ImageLoader.getImage(Texture.PLAYER_WALK.getName() + ":0_0"));

		animations.put("walkLeft", new Animation(Texture.PLAYER_WALK, maxSpeed - getSpeed(), 0, 8));
		animations.put("walkRight", new Animation(Texture.PLAYER_WALK, maxSpeed - getSpeed(), 1, 8));
		animations.put("walkUp", new Animation(Texture.PLAYER_WALK, maxSpeed - getSpeed(), 2, 8));
		animations.put("walkDown", new Animation(Texture.PLAYER_WALK, maxSpeed - getSpeed(), 3, 8));

		inventory = new Inventory("Gui_invP01", 0, 0, 528, 498, Texture.GUI_INVENTORY);
		for (int x = 0; x < 9; x++) {
			new Slot((x + 1), 24 + x * (6 + Slot.SLOT_SIZE), 426, inventory);
		}

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				new Slot((y + 1) * (x + 1) + 9, 24 + x * (6 + Slot.SLOT_SIZE), 252 + y * (6 + Slot.SLOT_SIZE), inventory);
			}
		}

		inventory.addItemToSlot(3, new Wood());

	}

	public void addOffsetX(int x) {
		this.offsetX += x * getSpeed();
	}

	public void addOffsetY(int y) {
		this.offsetY += y * getSpeed();
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	private int getSpeed() {
		return 7;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		inventory.render(g);
		if (Game.debubModeBounding) {
			g.setColor(Color.RED);
			g.drawRect(x, y, width, height);
		}
	}

	public void update() {
		isMoving = false;

		this.x = -offsetX + Display.instance.getWidth() / 2 - width / 2;
		this.y = -offsetY + Display.instance.getHeight() / 2 - height / 2;

		setLocation(x, y);
		/**
		 * WALKING ANIMATIONS START
		 */
		if ((Game.input.left.isPressed() && Game.input.up.isPressed())) {
			addOffsetY(1);
			addOffsetX(1);

			isMoving = true;
			playAnimation(animations.get("walkUp"));
		} else if ((Game.input.right.isPressed() && Game.input.up.isPressed())) {
			addOffsetY(1);
			addOffsetX(-1);

			isMoving = true;
			playAnimation(animations.get("walkUp"));
		} else if ((Game.input.left.isPressed() && Game.input.down.isPressed())) {
			addOffsetY(-1);
			addOffsetX(1);

			isMoving = true;
			playAnimation(animations.get("walkDown"));
		} else if ((Game.input.right.isPressed() && Game.input.down.isPressed())) {
			addOffsetY(-1);
			addOffsetX(-1);

			isMoving = true;
			playAnimation(animations.get("walkDown"));
		} else {
			if (Game.input.up.isPressed()) {
				addOffsetY(1);
				isMoving = true;
				playAnimation(animations.get("walkUp"));
			} else if (Game.input.down.isPressed()) {
				addOffsetY(-1);
				isMoving = true;
				playAnimation(animations.get("walkDown"));

			}
			if (Game.input.left.isPressed()) {
				addOffsetX(1);
				isMoving = true;
				playAnimation(animations.get("walkLeft"));

			} else if (Game.input.right.isPressed()) {
				addOffsetX(-1);
				isMoving = true;
				playAnimation(animations.get("walkRight"));

			}
		}

		if (!isMoving) {
			resetAllAnimations();
			SoundLoader.stop("footsteps");
		}else SoundLoader.start("footsteps");
		/**
		 * WALKING ANIMATION END
		 */

		if (Chunk.playerChunk != null) {
			for (Tile t : Chunk.playerChunk.getTiles()) {
				if (t.getItemStack().getItemList().size() > 0) if (intersects(t)) {
					System.out.println(t.getCenterX());

					pickUpItemFromTile(t);
					break;
				}
			}
		}

	}

	private void pickUpItemFromTile(Tile t) {

		Slot s = inventory.getNextFreeSlot();
		if (s != null) {
			s.addItemStack(t.getItemStack());
			t.clearItemStack();
			
		}

	}

	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void actionPerformed(InputEvent event) {

		if (event.key.id == InputHandler.instance.inventory.id) {

			inventory.setActive(!inventory.isActive());

		}

	}
}
