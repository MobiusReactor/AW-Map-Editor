package com.mobiusreactor.awmap.map;

public class Tile {
	private Terrain t;
	private Unit u;

	public Tile() {
		t = new Terrain();
	}

	public Tile(Terrain t, Unit u) {
		this.t = t;
		this.u = u;
	}

	public Tile(int terrID) {
		t = new Terrain(terrID);
	}

	public Terrain getTerr() {
		return t;
	}
	
	public Unit getUnit() {
		return u;
	}

	@Override
	public Tile clone() {
		return new Tile(t.getID());
	}

	@Override
	public String toString() {
		return "Terrain: " + t.getID();
	}
}