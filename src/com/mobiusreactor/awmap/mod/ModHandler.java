package com.mobiusreactor.awmap.mod;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.swing.ImageIcon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ModHandler {
	private static Mod[] modList;

	public static void loadMods() {
//		GsonBuilder gB = new GsonBuilder();
//		gB.registerTypeAdapter(Image.class, new ImageInstanceCreator());
//		Gson gson = new Gson();

		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (name.toLowerCase().endsWith(".json")) {
					return true;
				} else {
					return false;
				}
			}
		};

		File[] modDir = new File("mod").listFiles(filter);

		modList = new Mod[modDir.length];

		for (int i = 0; i < modDir.length; i++) {
			System.out.println(modDir[i]);

			modList[i] = new Mod(modDir[i].getPath());
//			try {
//				modList[i] = gson.fromJson(new FileReader(modDir[i]), Mod.class);
//			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
//				 TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	public static Mod getMod() {
		return modList[0];
	}

	public static Mod getMod(int index) {
		return modList[index];
	}
}

//class ImageInstanceCreator extends TypeAdapter<Image> {
//	@Override
//	public Image read(final JsonReader in){
//		try {
//			String asdf=in.nextString();
//			System.out.println(asdf);
//			return new ImageIcon(asdf).getImage();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public void write(JsonWriter out, Image value) throws IOException {
//		// TODO Auto-generated method stub
//	}
//}