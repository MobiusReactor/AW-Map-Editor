package com.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import com.input.KeyController;
import com.input.MapController;
import com.input.MouseController;
import com.map.Location;
import com.map.Map;
import com.map.Mod;
import com.map.Terrain;
import com.map.Tile;

public class MapPanel extends JPanel {
	private Map map;
	private boolean grid = true;

	public MapPanel(int w, int h) {
		super();
		setPreferredSize(new Dimension(w * Mod.getTileSize(), h * Mod.getTileSize()));
		initInputHandler();
		setFocusable(true);
		map = new Map(w, h);
	}

	private void initInputHandler() {
		// Mouse input
		MouseController mc = new MouseController();
		addMouseListener(mc);
		addMouseMotionListener(mc);

		// Keyboard input
		new KeyController(getInputMap(WHEN_IN_FOCUSED_WINDOW), getActionMap());
	}

	public Map getMap() {
		return map;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		int tileSize = Mod.getTileSize();

		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				Tile t = map.getTile(x, y);
				drawTerr(g2d, t.getTerr(), x, y, tileSize);
			}
		}

		if (grid) {
			g2d.setColor(Color.BLACK);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));

			for (int x = 0; x <= map.getWidth(); x++) {
				g2d.drawLine(x * tileSize, 0, x * tileSize, map.getHeight() * tileSize);
			}
			for (int y = 0; y <= map.getHeight(); y++) {
				g2d.drawLine(0, y * tileSize, map.getWidth() * tileSize, y * tileSize);
			}

			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}

		Location c = MapController.getCursor();
		g2d.setColor(Color.RED);
		g2d.drawRect(c.x * tileSize, c.y * tileSize, tileSize, tileSize);
	}

	private void drawTerr(Graphics2D g2d, Terrain t, int x, int y, int tileSize) {
		g2d.drawImage(Mod.getImage(t.getID()), x * tileSize, (y - 1) * tileSize, (x + 1) * tileSize, (y + 1) * tileSize, 0, 0, tileSize, tileSize * 2, this);
	}

	public Dimension getPreferredSize() {
		return new Dimension(map.getWidth() * Mod.getTileSize(), map.getHeight() * Mod.getTileSize());
	}
}