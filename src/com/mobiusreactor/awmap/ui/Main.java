package com.ui;

import javax.swing.SwingUtilities;
import com.map.Mod;
import com.map.ModHandler;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ModHandler.loadMods();
				new Editor();
			}
		});
	}
}