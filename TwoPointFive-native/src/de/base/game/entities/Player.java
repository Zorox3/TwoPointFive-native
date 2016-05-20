package de.base.game.entities;

import de.base.engine.textures.Animation;
import de.base.engine.textures.ImageLoader;
import de.base.engine.textures.Texture;
import de.base.game.Game;
import de.base.game.world.World;

public class Player extends Entitiy {

	private int maxSpeed = 12;

	private int offsetX;
	private int offsetY;

	public Player(int posX, int posY, int width, int height, World world) {
		super(posX, posY, width, height, Texture.PLACEHOLDER);

		this.offsetX = x + world.getCenter().x;
		this.offsetY = y + world.getCenter().y;

		setTextureImage(ImageLoader.getImage(Texture.PLAYER_WALK.getName() + ":0_0"));

		animations.put("walkLeft", new Animation(Texture.PLAYER_WALK, maxSpeed - getSpeed(), 0, 8));
		animations.put("walkRight", new Animation(Texture.PLAYER_WALK, maxSpeed - getSpeed(), 1, 8));
		animations.put("walkUp", new Animation(Texture.PLAYER_WALK, maxSpeed - getSpeed(), 2, 8));
		animations.put("walkDown", new Animation(Texture.PLAYER_WALK, maxSpeed - getSpeed(), 3, 8));

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

	public void update() {
		isMoving = false;

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
		}
		/**
		 * WALKING ANIMATION END
		 */
	}
}
