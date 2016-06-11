package com.mobiusreactor.awmap.map;

public class Property extends Terrain {
	private int hp; // Capture points for props, or HP for inventions
	private int owner;

	public Property(int id, int owner, int hp) {
		super(id);
		this.owner = owner;
		this.hp = hp;
	}

	public int getOwner() {
		return owner;
	}
}