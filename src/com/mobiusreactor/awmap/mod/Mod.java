package com.mobiusreactor.awmap.mod;

import java.awt.Image;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.stream.JsonReader;

public class Mod {
	private String name;
	private int tilesize;
	private List<String> tilesets = new ArrayList<String>();
	private List<TerrainModInfo> terrain = new ArrayList<TerrainModInfo>();

//	private String[] terrName;
//	private Image[] terrImg;

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

			JsonReader r = new JsonReader(new FileReader(path));

			r.beginObject();

			while (r.hasNext()) {
				String n = r.nextName();

				switch (n) {
					case "name":
						name = r.nextString();
						System.out.println("Mod: " + name);
						break;

					case "tilesize":
						tilesize = r.nextInt();
						System.out.println("Tile size: " + tilesize + "x" + tilesize);
						break;

					case "tilesets":
						r.beginArray();
						while (r.hasNext()) {
							tilesets.add(r.nextString());
						}
						r.endArray();
						break;

					case "terrain":
						r.beginArray();
						while (r.hasNext()) {
							String id = "";
							String gfx = "";

							r.beginObject();

							while (r.hasNext()) {
								n = r.nextName();

								switch (n) {
									case "id":
										id = r.nextString();
										break;
									case "gfx":
										gfx = r.nextString();
										break;
									default:
										r.skipValue();
								}
							}

							terrain.add(new TerrainModInfo(id, gfx, name, tilesets));
							r.endObject();
						}
						r.endArray();
						break;

					default:
						r.skipValue();
				}
			}

			r.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage(int id, int tileset) {
		return terrain.get(id).getGfx(tileset);
	}

	public List<TerrainModInfo> getTerrainData() {
//		return terrImg;
		return terrain;
	}

	public int getTilesize() {
		return tilesize;
	}
}