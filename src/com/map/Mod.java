package com.map;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Mod {
	private static int tileSize = 16;
	private static String[] terrName = { "PLIN", "SEAS", "MNTN" };
	private static Image[] terrImg;

	// private static Map<String, Image> terrImg = new HashMap<>();

	public static void init() {
		terrImg = new Image[10];
		terrImg[0] = new ImageIcon("img/CWT_PLIN.png").getImage();
		terrImg[1] = new ImageIcon("img/CWT_SEAS(S)$SSSSSSSS.png").getImage();
		terrImg[2] = new ImageIcon("img/CWT_MNTN.png").getImage();
		terrImg[3] = new ImageIcon("img/CWT_PLIN.png").getImage();
		terrImg[4] = new ImageIcon("img/CWT_PLIN.png").getImage();
		terrImg[5] = new ImageIcon("img/CWT_PLIN.png").getImage();
		terrImg[6] = new ImageIcon("img/CWT_PLIN.png").getImage();
		terrImg[7] = new ImageIcon("img/CWT_PLIN.png").getImage();
		terrImg[8] = new ImageIcon("img/CWT_PLIN.png").getImage();
		terrImg[9] = new ImageIcon("img/CWT_PLIN.png").getImage();
		
	}

	public static Image getImage(int id) {
		return terrImg[id];
	}
	
	public static Image[] getTerrImageArray(){
		return terrImg;
	}

	public static int getTileSize() {
		return tileSize;
	}
}