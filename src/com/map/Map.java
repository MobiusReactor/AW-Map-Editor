package com.map;

import javax.swing.JOptionPane;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import com.ui.Editor;
import com.undo.ResizeEdit;

public class Map {
	private Tile[][] tile;

	private int width;
	private int height;

	private String name;
	private String author;

	private UndoManager undoHandler = new UndoManager();

	/**
	 * Initialises the map, setting the width and height to the appropriate values,
	 * and setting up the tiles with the default terrain.
	 * 
	 * @param width The width of the map
	 * @param height The height of the map
	 * @param panel The panel which displays this map
	 */
	public Map(int width, int height) {
		this.width = width;
		this.height = height;

		tile = new Tile[width][height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tile[x][y] = new Tile();
			}
		}
	}

	/**
	 * Checks if a given x and y are within the map's bounds.
	 * 
	 * @param x The x co-ordinate to test
	 * @param y The y co-ordinate to test
	 * @return True if the specified tile is within the map's bounds
	 */
	public boolean onMap(int x, int y) {
		return (x >= 0 && x < width && y >= 0 && y < height);
	}

	/**
	 * Gets the width of the map
	 * 
	 * @return The map's width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the height of the map
	 * 
	 * @return The map's height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets a map Tile based on its X and Y co-ordinates
	 * 
	 * @param x The X co-ordinate of the tile to get
	 * @param y The Y co-ordinate of the tile to get
	 * @return The tile requested
	 */
	public Tile getTile(int x, int y) {
		return tile[x][y];
	}

	public void setTile(int x, int y, Tile t) {
		tile[x][y] = t;
	}

	public Tile[][] getTileArray() {
		return tile;
	}

	public void setTileArray(Tile[][] tile) {
		width = tile.length;
		height = tile[0].length;
		this.tile = tile;

		Editor.getMapPanel().repaint();
		Editor.getMapPanel().revalidate();
	}

	/**
	 * Resizes the map
	 * 
	 * @param newWidth The new width for the map
	 * @param newHeight The new height for the map
	 */
	public void resize(int newWidth, int newHeight) {
		ResizeEdit re = new ResizeEdit(newWidth, newHeight);
		addEdit(re);
	}

	/**
	 * Takes in new values for width and height, then resizes the map
	 */
	public void resize() {
		try {
			int w = Integer.parseInt(JOptionPane.showInputDialog("Enter new width"));
			int h = Integer.parseInt(JOptionPane.showInputDialog("Enter new height"));

			resize(w, h);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: invalid input", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Prompts user for new map name
	 * 
	 * Will be replaced by settings screen with 
	 */
	public void rename() {
		String newName = JOptionPane.showInputDialog("Enter map name");

		if (newName != null) {
			name = newName;
			Editor.setMapName(name);
		}
	}

	/**
	 * Adds the specified edit to the undo handler
	 * 
	 * @param e The edit to add
	 */
	public void addEdit(AbstractUndoableEdit e) {
		undoHandler.addEdit(e);
	}

	/**
	 * Will undo the last action, if there is an action to undo
	 */
	public void undo() {
		if (undoHandler.canUndo()) {
			undoHandler.undo();
			Editor.getMapPanel().repaint();
		}
	}

	/**
	 * Will redo the last undone action, if there is an action to redo
	 */
	public void redo() {
		if (undoHandler.canRedo()) {
			undoHandler.redo();
			Editor.getMapPanel().repaint();
		}
	}
}