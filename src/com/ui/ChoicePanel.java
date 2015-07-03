package com.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import com.input.MapController;
import com.map.Mod;

public class ChoicePanel extends JPanel implements MouseListener {
	public ChoicePanel() {
		super();

		addMouseListener(this);
	}

	int selectedID = 0;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);

		int ts = Mod.getTileSize();

		int col = 0;
		int row = 0;

		for (Image i : Mod.getTerrImageArray()) {
			int j = col * (ts + 12) + 12;

			if (col == 6) {
				col = 0;
				j = 12;
				row++;
			}

			// int k = ((3 * y * ts) + ts) / 2;
			int k = row * (ts + 12) + 12;

			g.drawImage(i, j, k - ts, j + ts, k + ts, 0, 0, ts, (2 * ts), this);
			col++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int ts = Mod.getTileSize();

		int col = -1;
		int row = -1;

		if (e.getX() % (ts + 12) >= 12) {
			col = (int) (e.getX() / (ts + 12));
		}

		if (e.getY() % (ts + 12) >= 12) {
			row = (int) (e.getY() / (ts + 12));
		}

		if (col != -1 && row != -1) {
			selectedID = col + (6 * row);
			MapController.setSelected(selectedID);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
