package com.undo;

import javax.swing.undo.AbstractUndoableEdit;
import com.map.Map;
import com.map.Tile;
import com.ui.Editor;

public class TileEdit extends AbstractUndoableEdit {
	private Map m;
	private Tile oldT;
	private Tile newT;
	private int x;
	private int y;

	public TileEdit(Tile oldT, Tile newT, int x, int y) {
		this.m = Editor.getMap();
		this.oldT = oldT;
		this.newT = newT;
		this.x = x;
		this.y = y;

		m.setTile(x, y, newT);
	}

	@Override
	public void undo() {
		super.undo();
		m.setTile(x, y, oldT);
	}

	@Override
	public void redo() {
		super.redo();
		m.setTile(x, y, newT);
	}
}