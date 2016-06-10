package com.ui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import com.map.Location;
import com.map.Map;
import com.map.Mod;

public class Editor extends JFrame {
	private static JTabbedPane mapTab;
	private static JTabbedPane choiceTab;
	private static JLabel coordLbl;
	private static ArrayList<MapPanel> maps = new ArrayList<>();

	public Editor() {
		super("CWT Map Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 833, 600);
		setJMenuBar(new Menu());
		setLayout(null);

		mapTab = new MapTabPane();
		add(mapTab);

		addMap("Untitled", 30, 20);

		coordLbl = new JLabel("", SwingConstants.RIGHT);
		coordLbl.setBounds(745, 520, 70, 20);
		add(coordLbl);
		
		choiceTab = new JTabbedPane();
		choiceTab.setFocusable(false);
		choiceTab.setBounds(560, 5, getMap().getMod().getTileSize() * 6 + 89, 480);
		choiceTab.addTab("Terrain", new ChoicePanel());
		choiceTab.addTab("Units", new JPanel());
		choiceTab.addTab("Props", new JPanel());
		add(choiceTab);
	}

	/**
	 * Adds a new tab to the JTabbedPane, with the settings specified in the parameters
	 * 
	 * @param name The name of the map
	 * @param width The width of the map
	 * @param height The height of the map
	 */
	public static void addMap(String name, int width, int height) {
		// Removes key bindings for scroll panel, so keyboard controls work properly
		UIManager.getDefaults().put("ScrollPane.ancestorInputMap", new UIDefaults.LazyInputMap(new Object[] {}));

		MapPanel mapPanel = new MapPanel(width, height);
		JScrollPane sp = new JScrollPane();

		sp.setBorder(null);
		sp.setViewportView(mapPanel);

		maps.add(mapPanel);
		mapTab.addTab(name, sp);
		mapTab.setSelectedComponent(sp);
	}

	/**
	 * Sets the co-ordinates label to display the specified location
	 * 
	 * @param l The location to set the co-ordinates to
	 */
	public static void setCoordLabel(Location l) {
		coordLbl.setText("X: " + l.x + ",  Y: " + l.y);
	}

	/**
	 * Gets the MapPanel in the currently selected map tab
	 * 
	 * @return The currently selected MapPanel object
	 */
	public static MapPanel getMapPanel() {
		return maps.get(mapTab.getSelectedIndex());
	}

	/**
	 * Gets the Map in the currently selected map tab
	 * 
	 * @return The currently selected Map object
	 */
	public static Map getMap() {
		return getMapPanel().getMap();
	}

	/**
	 * Updates the map tab name with the name specified
	 * 
	 * @param name The map's name
	 */
	public static void setMapName(String name) {
		mapTab.setTitleAt(mapTab.getSelectedIndex(), name);
	}
}