package com.mobiusreactor.awmap.ui;

import javax.swing.SwingUtilities;
import com.mobiusreactor.awmap.mod.ModHandler;

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