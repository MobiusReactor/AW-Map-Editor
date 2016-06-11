package com.mobiusreactor.awmap.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.mobiusreactor.awmap.io.Load;

public class Menu extends JMenuBar {
	public Menu() {
		super();

		add(new FileMenu());
		add(new EditMenu());
	}

	private class FileMenu extends JMenu implements ActionListener {
		JMenuItem newMap = new JMenuItem("New");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem exit = new JMenuItem("Exit");

		public FileMenu() {
			super("File");

			newMap.addActionListener(this);
			load.addActionListener(this);
			exit.addActionListener(this);

			add(newMap);
			add(load);
			add(exit);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(newMap)) {
				Editor.addMap("Untitled", 30, 20);
			
			}else if (e.getSource().equals(load)){
				Load.loadMap();

			} else if (e.getSource().equals(exit)) {
				System.exit(0);
			}
		}
	}

	private class EditMenu extends JMenu implements ActionListener {
		JMenuItem undo = new JMenuItem("Undo");
		JMenuItem redo = new JMenuItem("Redo");
		JMenuItem resize = new JMenuItem("Resize");
		JMenuItem rename = new JMenuItem("Rename");

		public EditMenu() {
			super("Edit");

			undo.addActionListener(this);
			redo.addActionListener(this);
			resize.addActionListener(this);
			rename.addActionListener(this);
			
			add(undo);
			add(redo);
			add(resize);
			add(rename);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(undo)) {
				Editor.getMap().undo();

			} else if (e.getSource().equals(redo)) {
				Editor.getMap().redo();

			} else if (e.getSource().equals(resize)) {
				Editor.getMap().resize();
				
			} else if (e.getSource().equals(rename)) {
				Editor.getMap().rename();
			}
		}
	}
}