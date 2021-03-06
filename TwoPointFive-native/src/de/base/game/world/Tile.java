package de.base.game.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.base.engine.Objects.RenderObject;
import de.base.engine.inputhandler.InputHandler;
import de.base.engine.inputhandler.InputHandler.GameActionListener;
import de.base.engine.inputhandler.InputHandler.InputEvent;
import de.base.engine.inputhandler.InputHandler.InputEventType;
import de.base.engine.textures.Texture;
import de.base.game.Game;
import de.base.game.entities.items.Item;
import de.base.game.entities.items.Wood;
import de.base.game.entities.items.inventory.ItemStack;

public class Tile extends RenderObject implements GameActionListener {

	public static final int TILE_SIZE = 64;

	private ItemStack items;

	private BufferedImage renderImage;
	
	protected static int worldColor = 0x000000;
	
	public boolean hasChanged = false;

	public Tile(int x, int y, Texture texture, int color) {
		super(x, y, TILE_SIZE, TILE_SIZE);

		setTexture(texture);

		items = new ItemStack();

		InputHandler.addListener(this);
	}
	
	public Tile(int x, int y ,int width, int height, Texture texture) {
		super(x, y, width, height);

		setTexture(texture);

		items = new ItemStack();

		InputHandler.addListener(this);
	}


	public void addItem(Item item) {
		items.addItem(item);

		createRenderImage();
	}

	public static int getWorldColor() {
		return worldColor;
	}
	
	private void createRenderImage() {
		renderImage = new BufferedImage(texture.getTexture().getWidth(), texture.getTexture().getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = renderImage.createGraphics();

		g2.drawImage(textureImage, 0, 0, width, height, null);
		items.render(g2);

		g2.dispose();
		setTextureImage(renderImage);
	}

	public Item removeItem(Item item) {
		Item toRemove = null;
		for (Item i : items.getItemList()) {
			if (item.getClass().isInstance(i)) {
				toRemove = i;
				break;
			}
		}
		if (toRemove != null) {
			items.remove(toRemove);
			createRenderImage();
			return toRemove;
		}
		return null;
	}

	public ItemStack getItemStack() {
		return items;
	}

	@Override
	public void actionPerformed(InputEvent event) {
		if (contains(Game.input.mousePos) && !Game.getPlayer().getInventory().isActive()) {
			if (event.key.id == Game.input.mouseleft.id && event.type == InputEventType.PRESSED) {
				addItem(new Wood());
			}
			if (event.key.id == Game.input.mouseright.id && event.type == InputEventType.PRESSED) {
				removeItem(new Wood());
			}
		}
	}

	public void clearItemStack() {
		items = new ItemStack();
		createRenderImage();
	}
}
