package com.map;

import java.io.File;
import java.io.FilenameFilter;

public class ModHandler {
	private static Mod[] modList;

	public static void loadMods() {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (name.toLowerCase().endsWith(".txt")) {
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
		}
	}

	public static Mod getMod() {
		return modList[0];
	}
	
	public static Mod getMod(int index) {
		return modList[index];
	}
}