//package game;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DefaultRules implements Constants {
	/*-
	 * - This is a rule class that is used to decide on a play. The implementation
	 * is a multi dimensional array. The last array contains an an integer value
	 * that corresponds to the players action. Index 0 - the players hand - NONE,
	 * HIGH_Card, PAIR, etc.. Index 1 - The opponents action = FOLD, BLUFF, CHECK,
	 * CALL, BET1, etc.. The results are the players action in response.
	 */
	private static final int[][] ruleArray = new int[Rules.HANDS][Rules.OP_ACTIONS];
	private static final Rules rules = new Rules();
	/*- Street. */
	private static final JRadioButton allSt = new JRadioButton("All  Streets");
	private static final JRadioButton flop = new JRadioButton("Flop");
	private static final JRadioButton turn = new JRadioButton("Turn");
	private static final JRadioButton river = new JRadioButton("River");
	/*- Player. */
	private static final JRadioButton all = new JRadioButton("All Types");
	private static final JRadioButton hero = new JRadioButton("Hero");
	private static final JRadioButton heroX = new JRadioButton("HeroX");
	private static final JRadioButton average = new JRadioButton("Average");
	private static final JRadioButton averageX = new JRadioButton("AverageX");
	private static final JRadioButton fish = new JRadioButton("Fish");
	private static final JRadioButton nit = new JRadioButton("NIT");
	private static final JRadioButton lag = new JRadioButton("LAG");
	private static final JRadioButton tag = new JRadioButton("TAG");
	private static final JButton buttonGenerate = new JButton("Generate");
	/*- Used to build path to range file. */
	private static String type = "";
	private static String street = "";
	private static String path = "";
	private static String pos = "";
	private static boolean typeAll;
	private static boolean streetAll;

	private DefaultRules() {
		throw new IllegalStateException("Utility class");
	}

	/*- - Primary entry point. */
	public static void start() {
		streetAll = typeAll = false;
		type = street = "";
		doFrame();
	}

	/*- - Create the primary frame. */
	private static void doFrame() {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 200);
		frame.setLocation(700, 50);
		frame.setMinimumSize(new Dimension(700, 200));
		frame.setLayout(new GridLayout(3, 1));
		frame.setTitle("         Rules DEFAULT ");

		all.addActionListener(new Listener());
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
		players.add(all);
		players.add(hero);
		players.add(heroX);
		players.add(average);
		players.add(averageX);
		players.add(fish);
		players.add(nit);
		players.add(lag);
		players.add(tag);
		playersGroup.add(all);
		playersGroup.add(hero);
		playersGroup.add(heroX);
		playersGroup.add(average);
		playersGroup.add(averageX);
		playersGroup.add(fish);
		playersGroup.add(nit);
		playersGroup.add(lag);
		playersGroup.add(tag);
		players.setBorder(BorderFactory.createTitledBorder("Player Types"));
		final JPanel playersRadio = new JPanel();
		playersRadio.add(players);
		frame.add(playersRadio);

		allSt.addActionListener(new Listener());
		flop.addActionListener(new Listener());
		turn.addActionListener(new Listener());
		river.addActionListener(new Listener());
		final Box streets = Box.createHorizontalBox();
		final ButtonGroup streetsGroup = new ButtonGroup();
		streets.add(allSt);
		streets.add(flop);
		streets.add(turn);
		streets.add(river);
		streetsGroup.add(allSt);
		streetsGroup.add(flop);
		streetsGroup.add(turn);
		streetsGroup.add(river);
		streets.setBorder(BorderFactory.createTitledBorder("Streets"));
		final JPanel streetsRadio = new JPanel();
		streetsRadio.add(streets);
		frame.add(streetsRadio);

		// Control buttons
		buttonGenerate.addActionListener(new Listener());
		buttonGenerate.setEnabled(false);
		// buttonExit.addActionListener(new Listener());
		final Box control = Box.createHorizontalBox();
		control.add(buttonGenerate);
		// control.add(buttonExit);
		buttonGenerate.setBackground(Color.GREEN);
		// buttonExit.setBackground(Color.GREEN);
		buttonGenerate.setEnabled(false);
		control.setBorder(BorderFactory.createTitledBorder("Control "));
		final JPanel controlRadio = new JPanel();
		controlRadio.add(control);
		frame.add(controlRadio);

		frame.pack();
		frame.setVisible(true);
	}

	/*- - Button options. */
	private static void buttonOptions(String action) {
		switch (action) {
			case "All Types":
				typeAll = true;
				break;
			case "Hero":
				type = "Hero";
				break;
			case "HeroX":
				type = "HeroX";
				break;
			case "Average":
				type = "Average";
				break;
			case "AverageX":
				type = "AverageX";
				break;
			case "Fish":
				type = "Fish";
				break;
			case "NIT":
				type = "NIT";
				break;
			case "LAG":
				type = "LAG";
				break;
			case "TAG":
				type = "TAG";
				break;
			case "Flop":
				street = "Flop";
				break;
			case "Turn":
				street = "Turn";
				break;
			case "River":
				street = "River";
				break;
			case "All  Streets":
				streetAll = true;
				break;
			case "Generate":
				doRules();
				break;
		}
		checkReadyToAutogen();
	}

	/*- - Check ready to automatically generate MDF ranges. */
	private static void checkReadyToAutogen() {
		if (!"".equals(type) || (typeAll && !"".equals(street)) || streetAll) {
			buttonGenerate.setEnabled(true);
		}
	}

	/*- - Do Rules. */
	private static void doRules() {
		if ("Flop".equals(street)) {
			checkTypeAllFlop();
		}
		if ("Turn".equals(street)) {
			checkTypeAllTurn();
		}
		if ("River".equals(street)) {
			checkTypeAllRiver();
		}
		if (streetAll) {
			street = "Flop";
			checkTypeAllFlop();
			street = "Turn";
			checkTypeAllTurn();
			street = "River";
			checkTypeAllRiver();
		}
		Popup.popup("Default Rules  for Complete");
	}

	/*- - If all selected do them all. */
	private static void checkTypeAllFlop() {
		if (typeAll) {
			type = "Hero";
			doRulesFlop();
			type = "HeroX";
			doRulesFlop();
			type = "Average";
			doRulesFlop();
			type = "AverageX";
			doRulesFlop();
			type = "Fish";
			doRulesFlop();
			type = "NIT";
			doRulesFlop();
			type = "LAG";
			doRulesFlop();
			type = "TAG";
		}
		doRulesFlop();
	}

	/*- - If all selected do them all. */
	private static void checkTypeAllTurn() {
		if (typeAll) {
			type = "Hero";
			doRulesTurn();
			type = "HeroX";
			doRulesTurn();
			type = "Average";
			doRulesTurn();
			type = "AverageX";
			doRulesTurn();
			type = "Fish";
			doRulesTurn();
			type = "NIT";
			doRulesTurn();
			type = "LAG";
			doRulesTurn();
			type = "TAG";
		}
		doRulesTurn();
	}

	/*- - If all selected do them all. */
	private static void checkTypeAllRiver() {
		if (typeAll) {
			type = "Hero";
			doRulesRiver();
			type = "HeroX";
			doRulesRiver();
			type = "Average";
			doRulesRiver();
			type = "AverageX";
			doRulesRiver();
			type = "Fish";
			doRulesRiver();
			type = "NIT";
			doRulesRiver();
			type = "LAG";
			doRulesRiver();
			type = "TAG";
		}
		doRulesRiver();
	}

	/*- - Do Rules Flop. */
	private static void doRulesFlop() {
		pos = "First";
		doOneRuleFileFlop(new StringBuilder().append(path).append(type).append("\\Flop\\First\\Rule.ser").toString());
		pos = "Middle";
		doOneRuleFileFlop(new StringBuilder().append(path).append(type).append("\\Flop\\Middle\\Rule.ser").toString());
		pos = "Last";
		doOneRuleFileFlop(new StringBuilder().append(path).append(type).append("\\Flop\\Last\\Rule.ser").toString());
		pos = "FirstHU";
		doOneRuleFileFlop(new StringBuilder().append(path).append(type).append("\\Flop\\First\\RuleHU.ser").toString());
		pos = "LastHU";
		doOneRuleFileFlop(new StringBuilder().append(path).append(type).append("\\Flop\\Last\\RuleHU.ser").toString());
	}

	/*- - Do Rules Flop. */
	private static void doRulesTurn() {
		pos = "First";
		doOneRuleFileTurn(new StringBuilder().append(path).append(type).append("\\Turn\\First\\Rule.ser").toString());
		pos = "Middle";
		doOneRuleFileTurn(new StringBuilder().append(path).append(type).append("\\Turn\\Middle\\Rule.ser").toString());
		pos = "Last";
		doOneRuleFileTurn(new StringBuilder().append(path).append(type).append("\\Turn\\Last\\Rule.ser").toString());
		pos = "FirstHU";
		doOneRuleFileTurn(new StringBuilder().append(path).append(type).append("\\Turn\\First\\RuleHU.ser").toString());
		pos = "LastHU";
		doOneRuleFileTurn(new StringBuilder().append(path).append(type).append("\\Turn\\Last\\RuleHU.ser").toString());
	}

	/*- - Do Rules Flop. */
	private static void doRulesRiver() {
		pos = "First";
		doOneRuleFileRiver(new StringBuilder().append(path).append(type).append("\\River\\First\\Rule.ser").toString());
		pos = "Middle";
		doOneRuleFileRiver(
				new StringBuilder().append(path).append(type).append("\\River\\Middle\\Rule.ser").toString());
		pos = "Last";
		doOneRuleFileRiver(new StringBuilder().append(path).append(type).append("\\River\\Last\\Rule.ser").toString());
		pos = "FirstHU";
		doOneRuleFileRiver(
				new StringBuilder().append(path).append(type).append("\\River\\First\\RuleHU.ser").toString());
		pos = "LastHU";
		doOneRuleFileRiver(
				new StringBuilder().append(path).append(type).append("\\River\\Last\\RuleHU.ser").toString());
	}

	/*- - One rule file. */
	private static void doOneRuleFileFlop(String file) {
		clearLocal();
		autoFlop();
		copyFromLocal();
		rules.writeRules(file);
	}

	/*- - One rule file. */
	private static void doOneRuleFileTurn(String file) {
		clearLocal();
		autoTurn();
		copyFromLocal();
		rules.writeRules(file);
	}

	/*- - One rule file. */
	private static void doOneRuleFileRiver(String file) {
		clearLocal();
		autoRiver();
		copyFromLocal();
		rules.writeRules(file);
	}

	/*- - Copy a rules object to local array. */
	private static void copyToLocal() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int j = 0; j < Rules.OP_ACTIONS; ++j) {
				ruleArray[i][j] = rules.getPlay(i, j);
			}
		}
	}

	/*- - Copy a rules object from local array. */
	private static void copyFromLocal() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int j = 0; j < Rules.OP_ACTIONS; ++j) {
				rules.setPlay(i, j, ruleArray[i][j]);
			}
		}
	}

	/*- - Set everything in local array to Tules.FOLD */
	private static void clearLocal() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int j = 0; j < Rules.OP_ACTIONS; ++j) {
				ruleArray[i][j] = Rules.NO_ACTION;
			}
		}
	}

	/*- - Automatic rules for Flop - Hero, HeroX, Average, AverageX. */
	private static void autoFlop() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_SET) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
				if (i == MADE_STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
			}
		}
	}

	/*- - Automatic rules for Turn. */
	private static void autoTurn() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_SET) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
			}
		}
	}

	/*- - Automatic rules for River. */
	private static void autoRiver() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_FOUR_OF_A_KINDPOCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_SET) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
			}
		}
	}

	/*- - Automatic rules for Flop - NIT. */
	private static void autoFlopNIT() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_BOARD_PAIRMADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRSAW_DRAW_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_SET) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
			}
		}
	}

	/*- - Automatic rules for Turn. */
	private static void autoTurnNIT() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i ==DRAW_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i ==  MADE_SET) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i ==  MADE_STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_ FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i ==  MADE_FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i ==  MADE_FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i ==  MADE_STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i ==  MADE_ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
			}
		}
	}

	/*- - Automatic rules for River. */
	private static void autoRiverNIT() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (i == MADE_SET) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.ALLIN;
						}
					}
					if (i == MADE_STRAIGHT) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k >= Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == MADE_FLUSH) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k >= Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == MADE_FULL_HOUSE) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == MADE_FOUR_OF_A_KIND) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k >= Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == MADE_STRAIGHT_FLUSH) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == MADE_ROYAL_FLUSH) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
				}
			}
		}
	}

	/*- - Automatic rules for Flop - Hero, HeroX, Average, AverageX. */
	private static void autoFlopFish() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.CALL : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == DRAW_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRSW_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MDE_TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_SET) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
			}
		}
	}

	/*- - Automatic rules for Turn. */
	private static void autoTurnFish() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MaDE_POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == DRAW_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_SET) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
			}
		}
	}

	/*- - Automatic rules for River. */
	private static void autoRiverFish() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (i == MADE_BOARD_SET) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == MADE_STRAIGHT) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == MADE_FLUSH) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == MADE_FULL_HOUSE) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == MADE_FOUR_OF_A_KIND) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == MADE_STRAIGHT_FLUSH)
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
			}
		}
	}

	}

	/*- - Automatic rules for Flop - LAG. */
	private static void autoFlopLAG() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_SET) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
				if (i == MADE_STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
			}
		}
	}

	/*- - Automatic rules for Turn. */
	private static void autoTurnLAG() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i ==MADE_NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i ==MADE_MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i ==MADE_POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == THREE_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
				if (i == FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
				if (i == STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
				if (i == ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
			}
		}
	}

	/*- - Automatic rules for River. */
	private static void autoRiverLAG() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (i == THREE_OF_A_KIND) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == STRAIGHT) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == FLUSH) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == FULL_HOUSE) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.ALLIN;
						}
					}
					if (i == FOUR_OF_A_KIND) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.ALLIN;
						}
					}
					if (i == STRAIGHT_FLUSH) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.ALLIN;
						}
					}
					if (i == ROYAL_FLUSH) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.ALLIN;
						}
					}
				}
			}
		}
	}

	/*- - Automatic rules for Flop - LAG. */
	private static void autoFlopTAG() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == THREE_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
				if (i == STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
			}
		}
	}

	/*- - Automatic rules for Turn. */
	private static void autoTurnTAG() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_GUTSHOT_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_OESD_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET3) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == DRAW_FLUSH_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW_GUTSHOT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == FLUSH_DRAW_OESD) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == THREE_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == STRAIGHT) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.CALL;
					}
				}
				if (i == FULL_HOUSE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
				if (i == FOUR_OF_A_KIND) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
				if (i == STRAIGHT_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
				if (i == ROYAL_FLUSH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET4) {
						ruleArray[i][k] = Rules.ALLIN;
					}
					if (k == Rules.OP_ALLIN) {
						ruleArray[i][k] = Rules.ALLIN;
					}
				}
			}
		}
	}

	/*- - Automatic rules for River. */
	private static void autoRiverTAG() {
		for (int i = 0; i < Rules.HANDS; ++i) {
			for (int k = 0; k < Rules.OP_ACTIONS; ++k) {
				if (i == NONE) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == OVERCARDS) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == ACE_HIGH) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == WEAK_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MIDDLE_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == POCKET_PAIR_BELOW_TP) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_OVER_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == MADE_TOP_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = "Last".equals(pos) ? Rules.BET : Rules.NO_ACTION;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k > Rules.OP_BET2) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
				}
				if (i == TWO_PAIR) {
					if (k == Rules.OP_CHECK) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET1) {
						ruleArray[i][k] = Rules.BET;
					}
					if (k == Rules.OP_BET2) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k == Rules.OP_BET3) {
						ruleArray[i][k] = Rules.CALL;
					}
					if (k >= Rules.OP_BET4) {
						ruleArray[i][k] = Rules.NO_ACTION;
					}
					if (i == THREE_OF_A_KIND) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == STRAIGHT) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == FLUSH) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.CALL;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.CALL;
						}
					}
					if (i == FULL_HOUSE) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.ALLIN;
						}
					}
					if (i == FOUR_OF_A_KIND) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k >= Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.ALLIN;
						}
					}
					if (i == STRAIGHT_FLUSH) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.ALLIN;
						}
					}
					if (i == ROYAL_FLUSH) {
						if (k == Rules.OP_CHECK) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET1) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET2) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET3) {
							ruleArray[i][k] = Rules.BET;
						}
						if (k == Rules.OP_BET4) {
							ruleArray[i][k] = Rules.ALLIN;
						}
						if (k == Rules.OP_ALLIN) {
							ruleArray[i][k] = Rules.ALLIN;
						}
					}
				}
			}
		}
	}

	/*- - Responds to button clicks. */
	private static class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent i) {
			buttonOptions(i.getActionCommand());
		}
	}
}
