package com.mobiusreactor.awmap.map;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;

public class Mod {
	private int tileSize;
	private String[] tilesets;

	private String[] terrName;
	private Image[] terrImg;

	// private static Map<String, Image> terrImg = new HashMap<>();

	/**
	 * Load a mod's data from the specified info file. Currently uses a plaintext file, will probably be replaced with
	 * JSON in the future.
	 * 
	 * @param path
	 *            Path to the mod info file
	 */
	public Mod(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));

			tileSize = Integer.parseInt(br.readLine());

			tilesets = br.readLine().split("\\s+");

			System.out.println("tile size: " + tileSize + "x" + tileSize);
			System.out.print("tilesets: " + tilesets.length + " ");

			for (String s : tilesets) {
				System.out.print("<" + s + ">  ");
			}

			System.out.println();


			int numTerr = Integer.parseInt(br.readLine());

			terrName = new String[numTerr];
			terrImg = new Image[numTerr];

			for (int i = 0; i < numTerr; i++) {
				String[] s = br.readLine().split("\\s+");
				terrImg[i] = new ImageIcon("mod/data/AW1/" + tilesets[0] + "/" + s[0]).getImage();
				terrName[i] = s[1];
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage(int id) {
		return terrImg[id];
	}

	public Image[] getTerrImageArray() {
		return terrImg;
	}

	public int getTileSize() {
		return tileSize;
	}
}