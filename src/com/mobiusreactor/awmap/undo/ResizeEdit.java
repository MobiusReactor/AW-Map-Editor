package com.mobiusreactor.awmap.undo;

import javax.swing.undo.AbstractUndoableEdit;
import com.mobiusreactor.awmap.map.Map;
import com.mobiusreactor.awmap.map.Tile;
import com.mobiusreactor.awmap.ui.Editor;

public class ResizeEdit extends AbstractUndoableEdit {
	private Map m;

	private int oldWidth, oldHeight;
	private int newWidth, newHeight;

	private Tile[][] tiles;

	public ResizeEdit(int width, int height) {
		m = Editor.getMap();

		oldWidth = m.getWidth();
		oldHeight = m.getHeight();

		newWidth = width;
		newHeight = height;

		tiles = new Tile[oldWidth][oldHeight];

		// Save tiles clipped from right
		if (newWidth < oldWidth) {
			for (int i = newWidth; i < oldWidth; i++) {
				for (int j = 0; j < oldHeight; j++) {
					tiles[i][j] = m.getTile(i, j).clone();
				}
			}
		}

		// Save tiles clipped from bottom
		if (newHeight < oldHeight) {
			for (int i = 0; i < oldWidth; i++) {
				for (int j = newHeight; j < oldHeight; j++) {
					tiles[i][j] = m.getTile(i, j).clone();
				}
			}
		}

		// Perform resize
		resize(newWidth, newHeight, false);
	}

	@Override
	public void undo() {
		super.undo();

		resize(oldWidth, oldHeight, true);
	}

	@Override
	public void redo() {
		super.redo();

		resize(newWidth, newHeight, false);
	}

	private void resize(int w, int h, boolean undo) {
		Tile[][] temp = new Tile[w][h];

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (m.onMap(x, y)) {
					temp[x][y] = m.getTile(x, y);
					
				} else {
					if (undo) {
						temp[x][y] = tiles[x][y];
					} else {
						temp[x][y] = new Tile();
					}
				}
			}
		}
		m.setTileArray(temp);
	}
}