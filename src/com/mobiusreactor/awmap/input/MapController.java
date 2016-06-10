package com.mobiusreactor.awmap.input;

import javax.swing.undo.CompoundEdit;
import com.mobiusreactor.awmap.map.Location;
import com.mobiusreactor.awmap.map.Map;
import com.mobiusreactor.awmap.map.Terrain;
import com.mobiusreactor.awmap.map.Tile;
import com.mobiusreactor.awmap.map.Unit;
import com.mobiusreactor.awmap.ui.Editor;
import com.mobiusreactor.awmap.undo.TileEdit;

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