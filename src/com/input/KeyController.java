package com.input;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import com.ui.Editor;

public class KeyController extends MapController {
	private InputMap im;
	private ActionMap am;

	public KeyController(InputMap im, ActionMap am) {
		this.im = im;
		this.am = am;

		addBinding("UP", "UpArrow");
		addBinding("DOWN", "DownArrow");
		addBinding("LEFT", "LeftArrow");
		addBinding("RIGHT", "RightArrow");

		addBinding("control Y", "CtrlY");
		addBinding("control Z", "CtrlZ");
		addBinding("control R", "CtrlR");
	}

	private void addBinding(String key, String action) {
		im.put(KeyStroke.getKeyStroke(key), action);
		am.put(action, new KeyAction(action));
	}

	private class KeyAction extends AbstractAction {
		private String cmd;

		public KeyAction(String cmd) {
			this.cmd = cmd;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (cmd.equals("UpArrow")) {
				c.translate(0, -1);
			}
			if (cmd.equals("DownArrow")) {
				c.translate(0, 1);
			}
			if (cmd.equals("LeftArrow")) {
				c.translate(-1, 0);
			}
			if (cmd.equals("RightArrow")) {
				c.translate(1, 0);
			}

			if (cmd.equals("CtrlY")) {
				Editor.getMap().redo();
			}
			if (cmd.equals("CtrlZ")) {
				Editor.getMap().undo();
			}
			if (cmd.equals("CtrlR")) {
				Editor.getMap().resize();
			}

			moveCursor(false);
		}
	}
}