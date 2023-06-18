//package game;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class EditRules implements Constants {
	private static JFrame frame = new JFrame();

	/*- Types of hands. */
	private static final JRadioButton hero = new JRadioButton("Hero");

	private static final JRadioButton heroX = new JRadioButton("HeroX");

	private static final JRadioButton average = new JRadioButton("Average");

	private static final JRadioButton averageX = new JRadioButton("AverageX");

	private static final JRadioButton fish = new JRadioButton("Fish");

	private static final JRadioButton nit = new JRadioButton("NIT");

	private static final JRadioButton lag = new JRadioButton("LAG");

	private static final JRadioButton tag = new JRadioButton("TAG");

	private static final JCheckBox first = new JCheckBox("First");

	private static final JCheckBox middle = new JCheckBox("Middle");

	private static final JCheckBox last = new JCheckBox("Last");

	private static final JCheckBox firstHU = new JCheckBox("First Heads Up");

	private static final JCheckBox lastHU = new JCheckBox("Last Heads Up");

	private static final JButton buttonEdit = new JButton("Edit");

	/*- Used to build path to rules file. */
	private static String type = "Hero";
	private static String street = "";
	private static String position = "";
	private static String root = "";
	private static boolean headsUp;
	private static final JButton exit = new JButton("Exit");
	private static boolean frameOpen;

	/*- - Start program Arg0 - Street FLOP, TURN, RIVER */
	public static void start(int strt) {
		if (strt == FLOP) {
			street = "Flop";
		}
		if (strt == TURN) {
			street = "Turn";
		}
		if (strt == RIVER) {
			street = "River";
		}
		root = Options.path;
		if (frameOpen) {
			frame.toFront();
			return;
		}
		frameOpen = true;
		frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent i) {
				i.getWindow().dispose();
				frameOpen = false;
			}
		});

		exit.addActionListener((ActionEvent __) -> frame.dispose());
		final Font f = new Font(Font.SERIF, Font.BOLD, 14);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 200);

		frame.setMinimumSize(new Dimension(1000, 200));
		frame.setLayout(new GridLayout(3, 1));
		frame.setLocation(500, 200);

		hero.addActionListener(new Listener());
		heroX.addActionListener(new Listener());
		average.addActionListener(new Listener());
		averageX.addActionListener(new Listener());
		fish.addActionListener(new Listener());
		nit.addActionListener(new Listener());
		lag.addActionListener(new Listener());
		tag.addActionListener(new Listener());
		final Box players = Box.createHorizontalBox();
		final ButtonGroup playersGroup = new ButtonGroup();
		players.add(hero);
		players.add(heroX);
		players.add(average);
		players.add(averageX);
		players.add(fish);
		players.add(nit);
		players.add(lag);
		players.add(tag);
		playersGroup.add(hero);
		playersGroup.add(heroX);
		playersGroup.add(average);
		playersGroup.add(averageX);
		playersGroup.add(fish);
		playersGroup.add(nit);
		playersGroup.add(lag);
		playersGroup.add(tag);
		hero.setSelected(true);
		players.setBorder(BorderFactory.createTitledBorder("Player Types"));
		final JPanel playersRadio = new JPanel();
		playersRadio.add(players);
		frame.add(playersRadio);

		final JPanel positions = new JPanel();
		final Box pos = Box.createHorizontalBox();
		pos.setFont(f);
		pos.add(first);
		pos.add(middle);
		pos.add(last);
		pos.add(firstHU);
		pos.add(lastHU);

		positions.add(first);
		positions.add(middle);
		positions.add(last);
		positions.add(firstHU);
		positions.add(lastHU);
		frame.add(positions);

		final JPanel control = new JPanel();
		buttonEdit.addActionListener(new Listener());
		buttonEdit.setToolTipText("Edit Rules files");
		buttonEdit.setBackground(Color.GREEN);

		final Box cont = Box.createHorizontalBox();
		control.setFont(f);
		control.add(buttonEdit);
		cont.add(buttonEdit);
		control.add(cont);
		frame.add(control);

		frame.pack();
		frame.setVisible(true);
	}

	/*- - Controls. */
	private static void controls(String action) {
		switch (action) {
		case "Edit":
			if (first.isSelected()) {
				new ReportRules(type, street, "First", false);
			}
			if (middle.isSelected()) {
				new ReportRules(type, street, "Middle", false);
			}
			if (last.isSelected()) {
				new ReportRules(type, street, "Last", false);
			}
			if (firstHU.isSelected()) {
				new ReportRules(type, street, "First", true);
			}
			if (lastHU.isSelected()) {
				new ReportRules(type, street, "Last", true);
			}

			break;
		}
	}

	/*- - Player type. */
	private static void playerType(String action) {
		switch (action) {
		case "Hero":
			if (!"Hero".equals(type)) {
				setSelectedOff();
			}
			type = "Hero";
			editRules();
			break;
		case "HeroX":
			if (!"HeroX".equals(type)) {
				setSelectedOff();
			}
			type = "heroX";
			editRules();
			break;
		case "Average":
			if (!"Average".equals(type)) {
				setSelectedOff();
			}
			type = "Average";
			editRules();
			break;
		case "AverageX":
			if (!"AverageX".equals(type)) {
				setSelectedOff();
			}
			type = "AverageX";
			editRules();
		case "Fish":
			if (!"Fish".equals(type)) {
				setSelectedOff();
			}
			type = "Fish";
			editRules();
		case "NIT":
			if (!"NIT".equals(type)) {
				setSelectedOff();
			}
			type = "NIT";
			editRules();
		case "LAG":
			if (!"LAG".equals(type)) {
				setSelectedOff();
			}
			type = "LAG";
			editRules();
		case "TAG":
			if (!"TAG".equals(type)) {
				setSelectedOff();
			}
			type = "TAG";
			editRules();
		}
	}

	/*- - Position. */
	private static void position(String action) {
		switch (action) {
		case "First":
			if (!"First".equals(position)) {
				setSelectedOff();
			}
			position = "First";
			headsUp = false;
			editRules();
			break;
		case "Middle":
			if (!"Middle".equals(position)) {
				setSelectedOff();
			}
			position = "Middle";
			headsUp = false;
			break;
		case "Last":
			if (!"Last".equals(position)) {
				setSelectedOff();
			}
			position = "Last";
			headsUp = false;
			break;
		case "FirstHU":
			if (!"FirstHU".equals(position)) {
				setSelectedOff();
			}
			position = "First Heads Up";
			headsUp = true;
			break;
		case "Last Heads Up":
			if (!"LastHU".equals(position)) {
				setSelectedOff();
			}
			position = "LastHU";
			headsUp = true;
			break;
		}
	}

	/*- - Set all selected off. */
	private static void setSelectedOff() {
		first.setSelected(false);
		middle.setSelected(false);
		last.setSelected(false);
		firstHU.setSelected(false);
		lastHU.setSelected(false);
	}

	/*- - Exit. */
	private void exit() {
		// TODO =close frame
	}

	/*- - If a path is complete call editor. */
	private static boolean editRules() {
		return !"".equals(type) && !"".equals(position);
	}

	/*- - Responds to button clicks. */
	private static class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			final String action = e.getActionCommand();

			playerType(action);
			position(action);
			if (!"".equals(type)) {
				controls(action);
			}
		}
	}
}
