package de.base.engine.inputhandler;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import de.base.engine.render.Display;
import de.base.game.Game;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

	public static InputHandler instance;

	public Key[] keys = new Key[10];
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key space = new Key();
	public Key esc = new Key();
	public Key action = new Key();
	public Key inventory = new Key();

	public Key mouseleft = new Key();
	public Key mouseright = new Key();
	private static List<GameActionListener> listeners = new ArrayList<GameActionListener>();
	private static List<GameActionListener> newListeners = new ArrayList<GameActionListener>();

	private boolean actionPerformed;
	public int mouseButtonPressed;
	public Point mousePos;

	public InputHandler(Display display, Game game) {
		instance = this;

		display.addKeyListener(this);
		display.addMouseListener(this);
		display.addMouseMotionListener(this);
	}

	public void tick() {
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) {
				keys[i].update();
			}
		}
		if (actionPerformed) {
			notifyListeners();
			actionPerformed = false;
		}
	}

	private void notifyListeners() {
		for (int j = 0; j < keys.length; j++) {
			if (keys[j].gotPressed()) {
				for (int i = 0; i < listeners.size(); i++) {
					if (!newListeners.contains(listeners.get(i))) {
						listeners.get(i).actionPerformed(new InputEvent(keys[j], InputEventType.PRESSED));
					} else {
						newListeners.remove(listeners.get(i));
					}
				}
			}
		}
	}

	public class Key {

		public int id;
		boolean isPressed;
		private boolean lastState;
		private boolean gotPressed;

		public Key() {
			for (int i = 0; i < keys.length; i++) {
				if (keys[i] == null) {
					this.id = i;
					keys[i] = this;
					break;
				}
			}
		}

		public void toggle(boolean pressed) {
			isPressed = pressed;
		}

		public void update() {
			if (lastState != isPressed && isPressed == true) {
				gotPressed = true;
				lastState = isPressed;
			} else {
				gotPressed = false;
				lastState = isPressed;
			}
		}

		public boolean isPressed() {
			return isPressed;
		}

		public boolean gotPressed() {
			return gotPressed;
		}
	}

	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true, false);
	}

	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false, false);
	}

	public void keyTyped(KeyEvent e) {

	}

	public void toggleKey(int keyCode, boolean isPressed, boolean isMouse) {

		actionPerformed = true;

		if (!isMouse) {
			if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
				up.toggle(isPressed);
			}
			if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
				down.toggle(isPressed);
			}
			if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
				left.toggle(isPressed);
			}
			if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
				right.toggle(isPressed);
			}
			if (keyCode == KeyEvent.VK_SPACE) {
				space.toggle(isPressed);
			}
			if (keyCode == KeyEvent.VK_E) {
				action.toggle(isPressed);
			}
			if (keyCode == KeyEvent.VK_ESCAPE) {
				esc.toggle(isPressed);
			}
			if (keyCode == KeyEvent.VK_I) {
				inventory.toggle(isPressed);
			}
		} else {
			if (keyCode == MouseEvent.BUTTON1) {
				mouseleft.toggle(isPressed);
			}
			if (keyCode == MouseEvent.BUTTON3) {
				mouseright.toggle(isPressed);
			}
		}
	}

	public enum InputEventType {
		PRESSED, RELEASED;
	}

	public class InputEvent {
		public InputEventType type;
		public Key key;

		public InputEvent(Key key, InputEventType type) {
			this.key = key;
			this.type = type;
		}
	}

	public interface GameActionListener {
		public abstract void actionPerformed(InputEvent event);
	}

	public static void addListener(GameActionListener listener) {
		newListeners.add(listener);
		listeners.add(listener);
	}

	public void removeListener(GameActionListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		toggleKey(e.getButton(), true, true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		toggleKey(e.getButton(), false, true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Game.getPlayer() != null) this.mousePos = new Point(e.getX() - Game.getPlayer().getOffsetX(),
				e.getY() - Game.getPlayer().getOffsetY());
	}
}
