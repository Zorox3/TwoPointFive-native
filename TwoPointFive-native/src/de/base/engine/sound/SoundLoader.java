package de.base.engine.sound;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

import sun.audio.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import de.base.engine.util.FileLoader;
import de.base.game.Game;

public class SoundLoader {

	private static HashMap<String, Clip> soundList = new HashMap<>();

	public void load(String dirPath) {
		FileLoader f = new FileLoader(dirPath);

		loadSounds(f.getFiles());
	}

	private void loadSounds(HashMap<String, File> files) {
		for (Map.Entry<String, File> entry : files.entrySet()) {
			Clip temp = null;
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(entry.getValue());

				temp = AudioSystem.getClip();
				temp.open(ais);

				soundList.put(entry.getKey(), temp);
				if (Game.debubMode) System.out.println("Sound " + entry.getKey() + " loaded");

			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}

		}
	}

	public static void start(String name) {
		Clip t = soundList.get(name);
		
		if(t.getFrameLength() == t.getFramePosition()){
			t.setFramePosition(0);
		}
		
		if(!t.isRunning()) t.start();
	}

	public static void stop(String name) {
		Clip t = soundList.get(name);
		if (t.isRunning()) t.stop();
		else
			t.setFramePosition(0);
	}

}
