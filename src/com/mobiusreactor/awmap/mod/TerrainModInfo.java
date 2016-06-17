package com.mobiusreactor.awmap.mod;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;

public class TerrainModInfo {
	private String id;
//	private String path;
	private Image[] gfx;

//	public TerrainModInfo(String id, String path) {
//		this.id = id;
//		this.gfx = new ImageIcon(path).getImage();
//	}

	public TerrainModInfo(String id, String path, String name, List<String> tilesets) {
		this.id = id;

		gfx = new Image[tilesets.size()];

		for (int i = 0; i < tilesets.size(); i++) {
			String p = "mod/data/" + name + "/" + tilesets.get(i) + "/" + path;

			gfx[i] = new ImageIcon(p).getImage();
		}
	}

	public Image getGfx(int tileset) {
		return gfx[tileset];
	}
}