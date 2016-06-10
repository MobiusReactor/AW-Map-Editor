package com.input;

import javax.swing.undo.CompoundEdit;
import com.map.Location;
import com.map.Map;
import com.map.Terrain;
import com.map.Tile;
import com.map.Unit;
import com.ui.Editor;
import com.undo.TileEdit;

public class MapController {
	protected static Location c = new Location();
	protected static Location s = new Location();

	private CompoundEdit multiEdit = new CompoundEdit();

	private static Terrain selectedTerr = new Terrain(0);
	private static Unit selectedUnit;


	protected void moveCursor(boolean buttonDown) {
		if (buttonDown) {
			pressButton();
		}

		Editor.setCoordLabel(c);
		Editor.getMapPanel().repaint();
	}

	protected void pressButton() {
		Map m = Editor.getMap();

		if (!multiEdit.isInProgress()) {
			multiEdit = new CompoundEdit();
		}

		Tile old = m.getTile(c.x, c.y);

		TileEdit edit = new TileEdit(old, new Tile(selectedTerr, old.getUnit()), c.x, c.y);
		multiEdit.addEdit(edit);

		Editor.getMapPanel().repaint();
	}

	protected void releaseButton() {
		multiEdit.end();
		Editor.getMap().addEdit(multiEdit);
	}

	public static Location getCursor() {
		return c;
	}

	public static void setSelected(int id) {
		selectedTerr = new Terrain(id);
	}
}