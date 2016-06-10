package com.map;

public class Property extends Terrain {
	private int hp;		// Capture points for props, or HP for inventions
	private int owner;

	public Property(int id, int hp, int owner) {
		super(id);
		this.hp = hp;
		this.owner = owner;
	}
}