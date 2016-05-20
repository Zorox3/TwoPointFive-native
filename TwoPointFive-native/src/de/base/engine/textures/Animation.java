package de.base.engine.textures;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import de.base.engine.Objects.RenderObject;

public class Animation {

	private int speed;
	private int imageCount;
	private List<BufferedImage> subImages;
	private BufferedImage frameImage;
	private int frameImageIndex = 0;
	private int timer = 0;
	private boolean updated = false;
	private boolean reset = true;

	public Animation(Texture sprite, int speed, int row, int imageCount) {
		subImages = new ArrayList<>();

		for (int i = 0; i < imageCount; i++) {
			subImages.add(ImageLoader.getImage(sprite.getName() + ":" + row + "_" + i));
		}
		
		
		frameImage = subImages.get(0);
		this.speed = speed;
		this.imageCount = imageCount;

		timer = speed - 2;
	}

	public void run() {
		timer++;
		if (timer >= speed) {
			if (frameImageIndex >= imageCount) {
				frameImageIndex = 0;
				reset = true;
			}
			frameImage = subImages.get(frameImageIndex);
			updated = true;
			reset = false;
			timer = 0;
			frameImageIndex++;
		}

	}

	public void reset(RenderObject p, int... params) {
		if (!isReset()) {
			if (params.length == 0) {
				timer = 0;
				frameImageIndex = 0;
				frameImage = subImages.get(0);
				p.setTextureImage(subImages.get(0));

			} else {
				timer = params[0];
				frameImage = subImages.get(params[1]);
				frameImageIndex = params[1];
			}
			reset = true;
		}
	}

	public boolean isReset() {
		return reset;
	}

	public BufferedImage getFrameImage() {
		updated = false;
		return frameImage;
	}

	public boolean isUpdated() {
		return updated;
	}

	public int getImageCount() {
		return imageCount;
	}

	public int getSpeed() {
		return speed;
	}

}
