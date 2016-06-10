package com.map;

import com.ui.Editor;

public class Location {
	public int x;
	public int y;

	public Location() {
		x = 0;
		y = 0;
	}

	public Location(int x, int y) {
		if (Editor.getMap().onMap(x, y)) {
			this.x = x;
			this.y = y;
		} else {
			this.x = 0;
			this.y = 0;
		}
	}

	public void translate(int dx, int dy) {
		setLocation(x + dx, y + dy);
	}

	public void setLocation(int x, int y) {
		if (Editor.getMap().onMap(x, y)) {
			this.x = x;
			this.y = y;
		}
	}

	@Override
	public Location clone() {
		return new Location(x, y);
	}

	public boolean equals(Location l) {
		return (x == l.x && y == l.y);
	}
}
