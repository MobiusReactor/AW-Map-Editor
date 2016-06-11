package com.mobiusreactor.awmap.io;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class Load {
	private static JFileChooser dialog;
	private static File mapToLoad;
	private static loadFilter load = new loadFilter();

	public static void loadMap() {
		dialog = new JFileChooser(new File("."));
		dialog.setFileFilter(load);
		dialog.showOpenDialog(null);
		mapToLoad = dialog.getSelectedFile();

		if (mapToLoad != null) {
			String name = mapToLoad.getName().toLowerCase();

//			if (name.endsWith(".aw4")) {
//				LoadAW4.load(mapToLoad);
//			}
//			if (name.endsWith(".map")) {
//				LoadMAP.load(mapToLoad);
//			}
			if (name.endsWith(".awm") || name.endsWith(".aw2") || name.endsWith(".awd") || name.endsWith(".aws")) {
				LoadAWS.load(mapToLoad);
			}
		}
	}
}

class loadFilter extends FileFilter {
	public boolean accept(File f) {
		String name = f.getName().toLowerCase();
		return f.isDirectory() || name.endsWith(".awm");
//		return f.isDirectory() || name.endsWith(".aw4") || name.endsWith(".map") || name.endsWith(".awm") || name.endsWith(".aw2") || name.endsWith(".awd") || name.endsWith(".aws");
	}

	public String getDescription() {
		return "Supported Map files";
	}
}
