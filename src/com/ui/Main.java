package com.ui;

import javax.swing.SwingUtilities;
import com.map.Mod;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Mod.init();
				new Editor();
			}
		});
	}
}