package de.base.game.entities;

import de.base.engine.Animation;
import de.base.engine.ImageLoader;
import de.base.engine.Texture;
import de.base.game.Game;
import de.base.game.world.World;

public class Player extends Entitiy {

	private int maxSpeed = 12;
	
	private int offsetX;
	private int offsetY;

	private Animation walkingAnimationUp, walkingAnimationDown, walkingAnimationLeft, walkingAnimationRight;
	
	public Player(int posX, int posY, int width, int height, World world) {
		super(posX, posY, width, height, Texture.PLACEHOLDER);

		this.offsetX = x + world.getCenter().x;
		this.offsetY = y + world.getCenter().y;

		setTextureImage(ImageLoader.getImage(Texture.PLAYER_WALK.getName() + ":0_0"));

		walkingAnimationLeft = new Animation(Texture.PLAYER_WALK, maxSpeed-getSpeed(),0, 8);
		walkingAnimationRight = new Animation(Texture.PLAYER_WALK, maxSpeed-getSpeed(), 1, 8);
		walkingAnimationUp = new Animation(Texture.PLAYER_WALK, maxSpeed-getSpeed(), 2, 8);
		walkingAnimationDown = new Animation(Texture.PLAYER_WALK, maxSpeed-getSpeed(), 3, 8);


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

		if (Game.input.up.isPressed()) {
			addOffsetY(1);
			isMoving = true;
			playAnimation(walkingAnimationUp);
		}
		if (Game.input.down.isPressed()) {
			addOffsetY(-1);
			isMoving = true;
			playAnimation(walkingAnimationDown);

		}
		if (Game.input.left.isPressed()) {
			addOffsetX(1);
			isMoving = true;
			playAnimation(walkingAnimationLeft);

		}
		if (Game.input.right.isPressed()) {
			addOffsetX(-1);
			isMoving = true;
			playAnimation(walkingAnimationRight);

		}

		if (!isMoving) {
			walkingAnimationUp.reset(this);
			walkingAnimationDown.reset(this);
			walkingAnimationLeft.reset(this);
			walkingAnimationRight.reset(this);

		}
	}
	
	private void playAnimation(Animation a){
		a.run();
		if (a.isUpdated()) {
			setTextureImage(a.getFrameImage());
		}
	}

}
