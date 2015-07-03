/*
package com.input;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.map.Map;
import com.ui.Editor;

// TODO: Replace with KeyBindings
public class OldKeyController extends MapController implements KeyListener {
	private boolean aPressed;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		Map m = Editor.getMap();

		if (k == KeyEvent.VK_UP && m.onMap(c.x, c.y - 1)) {
			c.translate(0, -1);
		}
		if (k == KeyEvent.VK_DOWN && m.onMap(c.x, c.y + 1)) {
			c.translate(0, 1);
		}
		if (k == KeyEvent.VK_LEFT && m.onMap(c.x - 1, c.y)) {
			c.translate(-1, 0);
		}
		if (k == KeyEvent.VK_RIGHT && m.onMap(c.x + 1, c.y)) {
			c.translate(1, 0);
		}

		if (k == KeyEvent.VK_Z && e.isControlDown() && Editor.getMap().getUndoManager().canUndo()) {
			Editor.getMap().getUndoManager().undo();
		}

		if (k == KeyEvent.VK_X) {
			aPressed = true;
			s.setLocation(c);
		}
		moveCursor(aPressed);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();

		if (k == KeyEvent.VK_X) {
			aPressed = false;
			releaseButton();

		} else if (k == KeyEvent.VK_X) {
			// eyedropper();
		}
	}
}
*/