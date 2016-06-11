package com.mobiusreactor.awmap.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import com.mobiusreactor.awmap.map.Property;
import com.mobiusreactor.awmap.map.Terrain;
import com.mobiusreactor.awmap.map.Tile;
import com.mobiusreactor.awmap.ui.Editor;

public class LoadAWS {
	private static String ver = "";

	/**
	 * @param mapToLoad
	 */
	public static void load(File mapToLoad) {
		try {
			DataInputStream input = new DataInputStream(new FileInputStream(mapToLoad));

			System.out.println("Loading map: " + mapToLoad.getName());

			// Map version
			ver = "";
			for (int i = 0; i < 6; i++) {
				ver += (char) input.readByte();
			}

			System.out.println("Map format version: " + ver);

			// Useless bytes
			input.readInt();

//			// Resize map
//			int oldW = Map.width;
//			int oldH = Map.height;

			int width = 30;
			int height = 20;
//
//			if (ver.equals("AWSMap")) {
//				width = input.readByte();
//				height = input.readByte();
//			}
//			MapMenu.resizeMap(oldW, oldH);
			System.out.println("Width: " + width + ", Height: " + height);

			Editor.addMap(mapToLoad.getName(), width, height);
//			Map m = Editor.getMap();


//
//			// Tileset
//			String tileset = "Normal";
//			if (ver.equals("AWSMap")) {
//				tileset = getTileset(input.readByte());
//			} else if (ver.equals("AWDMap")) {
//				tileset = getTileset(input.readByte() - 1);
//			}
//			Resources.setTileset(tileset);
//			System.out.println("Tileset: " + tileset);

			Tile[][] tiles = new Tile[width][height];
//
//			// Terrain
			System.out.println("\nLoading terrain...");

//			boolean inventions = false;

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					Short s = Short.reverseBytes(input.readShort());

//					Terrain t = Map.getTerrain(i, j);
//
					if (s < 300) {
//						// Normal terrain
						Terrain t = new Terrain(getTerr(s, i, j));
						tiles[i][j] = new Tile(t, null);
//
					} else if (s < 500) {
//						// Properties
						int x = (s - 300) % 10;
						int y = (s - 300) / 10;
//						
						Property t = new Property(getPropID(x, i, j), getPropColour(y), 20);

						tiles[i][j] = new Tile(t, null);

					} else {
//						// Inventions
//						inventions = true;
//						t.id = Terr_ID.PLAINS;
					}
				}
			}
//
//			if (inventions) {
//				System.out.println("Map contains BH invention(s) which have been replaced with plains.");
//			}
//
//			System.out.println("Terrain loading complete.\n");
//
//			// Units
//			System.out.println("Loading units...");
//			for (int i = 0; i < Map.width; i++) {
//				for (int j = 0; j < Map.height; j++) {
//					Short s = Short.reverseBytes(input.readShort());
//
//					int x = (s - 500) % 20;
//					int y = (s - 500) / 20;
//
//					if (y % 2 == 1) {
//						x += 13;
//					}
//
//					int id = getUnitID(x, i, j);
//					int colour = getUnitColour(y / 2);
//
//					if (id != Unit_ID.NULL) {
//						Unit u = new Unit(id, colour, AI_ID.ATTACK, (byte) 10, (byte) 0);
//						Map.setUnit(i, j, u);
//					}
//				}
//			}
//			System.out.println("Unit loading complete.");

			Editor.getMap().setTileArray(tiles);



//			After the units, the length of the Name field, then the map Name,
//			then the length of the Author field, then the map's Author,
//			then the length of the Description field, then the Description.

			input.close();
//			Main.map.repaint();
//			Main.tChoice.repaint();
		} catch (Exception e) {
		}
	}

//
	private static int getTerr(short id, int x, int y) {
		switch (id) {
			case 0:
				return 0; // PLIN
			case 1:
				return 4; // ROAD
			case 2:
			case 32:
				return 5; // BRDG
			case 3:
				return 3; // RIVR
			case 30:
				return 7; // REEF
			case 39:
				return 8; // SHOA
			case 60:
				return 6; // SEAS
			case 90:
				return 1; // FRST
			case 150:
				return 2; // MNTN


//			case 16:
//				System.out.println("(" + x + ", " + y + ") - Replacing pipe with plasma");
//				return Terr_ID.LAND_PLASMA;
//			case 167:
//				System.out.println("(" + x + ", " + y + ") - Replacing destroyed invention/pipe seam with plains");
//				return Terr_ID.PLAINS;
//			case 226:
//				System.out.println("(" + x + ", " + y + ") - Replacing pipe seam with meteor");
//				return Terr_ID.LAND_METEOR;
			default:
				System.out.println("(" + x + ", " + y + ") - Replacing unknown terrain (id " + id + ") with plains");
				return 0;
		}
	}

//
	private static int getPropID(int id, int x, int y) {
		switch (id) {
			case 0:
				return 9; // HQTR
			case 1:
				return 10; // CITY
			case 2:
				return 11; // BASE
			case 3:
				return 12; // APRT
			case 4:
				return 13; // PORT
//			case 5:
//				return Terr_ID.COM_TOWER;
//			case 6:
//				System.out.println("(" + x + ", " + y + ") - Replacing lab with HQ/Silo");
//				return Terr_ID.HQ_SILO;
			default:
				return 0;
		}
	}

//
	private static int getPropColour(int y) {
		switch (y) {
			case 0:
				return 1; // Red
			case 1:
				return 2; // Blue
			case 2:
				return 3; // Green
			case 3:
				return 4; // Yellow
			case 4:
				if (ver.equals("AWMap ")) {
					return 0; // Neutral
				} else {
					return 5;
				}
			default:
				return 0;
		}
	}
//
//	private static int getUnitID(int id, int x, int y) {
//		switch (id) {
//			case 0:
//				return Unit_ID.INFANTRY;
//			case 1:
//				return Unit_ID.HEAVY_TANK;
//			case 2:
//				return Unit_ID.RECON;
//			case 3:
//				return Unit_ID.ARTILLERY;
//			case 4:
//				return Unit_ID.ANTI_AIR;
//			case 5:
//				return Unit_ID.FIGHTER;
//			case 6:
//				return Unit_ID.BATTLE_COPTER;
//			case 7:
//				return Unit_ID.BATTLESHIP;
//			case 8:
//				return Unit_ID.LANDER;
//			case 9:
//				System.out.println("(" + x + ", " + y + ") - Removing Neotank");
//				return Unit_ID.NULL;
//			case 10:
//				return Unit_ID.MEGA_TANK;
//			case 11:
//				System.out.println("(" + x + ", " + y + ") - Removing Piperunner");
//				return Unit_ID.NULL;
//			case 12:
//				System.out.println("(" + x + ", " + y + ") - Removing Oozium");
//				return Unit_ID.NULL;
//			case 13:
//				return Unit_ID.MECH;
//			case 14:
//				return Unit_ID.TANK;
//			case 15:
//				return Unit_ID.APC;
//			case 16:
//				return Unit_ID.ROCKETS;
//			case 17:
//				return Unit_ID.MISSILES;
//			case 18:
//				return Unit_ID.BOMBER;
//			case 19:
//				return Unit_ID.TRANSPORT_COPTER;
//			case 20:
//				return Unit_ID.CRUISER;
//			case 21:
//				return Unit_ID.SUBMARINE;
//			case 22:
//				System.out.println("(" + x + ", " + y + ") - Removing Black Boat");
//				return Unit_ID.NULL;
//			case 23:
//				return Unit_ID.CARRIER;
//			case 24:
//				System.out.println("(" + x + ", " + y + ") - Removing Stealth");
//				return Unit_ID.NULL;
//			case 25:
//				System.out.println("(" + x + ", " + y + ") - Removing Black Bomb");
//				return Unit_ID.NULL;
//			default:
//				return Unit_ID.NULL;
//		}
//	}
//
//	private static int getUnitColour(int y) {
//		switch (y) {
//			case 0:
//				return Army_ID.RED;
//			case 1:
//				return Army_ID.BLUE;
//			case 2:
//				return Army_ID.GREEN;
//			case 3:
//				return Army_ID.YELLOW;
//			case 4:
//				return Army_ID.BLACK;
//			default:
//				return Army_ID.NEUTRAL;
//		}
//	}
//
//	private static String getTileset(int t) {
//		switch (t) {
//			case 1:
//				return "Snow";
//			case 2:
//				return "Desert";
//			case 3:
//				return "Ruin";
//			default:
//				return "Normal";
//		}
//	}
}