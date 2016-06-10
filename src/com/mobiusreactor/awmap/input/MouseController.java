package com.mobiusreactor.awmap.input;

import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import com.mobiusreactor.awmap.map.Location;
import com.mobiusreactor.awmap.map.Mod;
import com.mobiusreactor.awmap.ui.Editor;

public class MouseController extends MapController implements MouseInputListener {
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			s = c.clone();

			pressButton();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			releaseButton();

			// } else if (e.getButton() == MouseEvent.BUTTON3) {
			// eyedropper();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		doMoveCursor(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		doMoveCursor(e);
	}

	private void doMoveCursor(MouseEvent e) {
		Location old = c.clone();
		c.setLocation(e.getX() / Editor.getMap().getMod().getTileSize(), e.getY() / Editor.getMap().getMod().getTileSize());

		if (!c.equals(old)) {
			moveCursor(SwingUtilities.isLeftMouseButton(e));
		}
	}
}