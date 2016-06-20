package de.base.engine.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import de.base.engine.util.FileLoader;
import de.base.game.Game;

public class ImageLoader {

	private static HashMap<String, BufferedImage> imageList = new HashMap<>();

	public boolean finish = false;

	public ImageLoader() {

	}

	private void loadImages(HashMap<String, File> files) {

		for (Map.Entry<String, File> entry : files.entrySet()) {
			try {
				if (Game.debubMode) System.err.println("IMAGE LOADED: " + entry.getKey());
				if (entry.getKey().startsWith("sprite_")) {
					loadAsSprite(entry.getKey(), ImageIO.read(entry.getValue()));
				} else {
					imageList.put(entry.getKey(), ImageIO.read(entry.getValue()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		finish = true;
	}

	private void loadAsSprite(String key, BufferedImage read) {
		String newKey = key.substring(7);
		Texture t = Texture.fromString(newKey);

		for (int rows = 0; rows < t.getRows(); rows++) {
			for (int i = 0; i < t.getCols(); i++) {
				String imageName = newKey + ":" + rows + "_" + i;
				imageList.put(imageName, read.getSubimage(i * t.getWidth() + t.getOffsetX(), t.getHeight() * rows + t.getOffsetY(), t.getWidth(), t.getHeight()));
				if (Game.debubMode) System.err.println("IMAGE FROM SPRITE: " + key + " -> " + imageName);
			}
		}

	}

	public boolean isFinish() {
		return finish;
	}

	public static HashMap<String, BufferedImage> getImageList() {
		return imageList;
	}

	public static BufferedImage getImage(String filename) {
		BufferedImage image = imageList.get(Texture.PLACEHOLDER.getName());
		if (imageList.containsKey(filename)) {
			image = imageList.get(filename);
		} else {
			System.err.println("File not found! -> " + filename);
		}

		return image;
	}

	public void load(String dirPath) {
		FileLoader f = new FileLoader(dirPath);

		loadImages(f.getFiles());
	}

}
