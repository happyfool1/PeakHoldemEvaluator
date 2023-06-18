//package game;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

class ReportRules implements Constants {
	int seat = -0;
	int x = 0;
	int y = 0;
	boolean edit = true;
	private final Rules rules = new Rules();
	private String type = "";
	private String street = "";
	private String pos = "";
	private final boolean headsUp;
	private String fileName = "";
	/*- Headers for the table. */
	Object[] columnsOpAction = { "  Hand     ", "No Bet ", " Bet1 ", "Bet2  ", "Bet3  ", " Bet4  ", "Allin " };
	/*- Actual data for the table in a 2d array. */
	Object[][] dataHand = { { "    ", "", "", "", "", "", "" }, { "   ", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "" }, { " ", "", "", "", "", "", "" }, { " ", "", "", "", "", "", "" },
			{ " ", "", "", "", "", "", "" }, { " ", "", "", "", "", "", "" }, { " ", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "" }, { " ", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "" }, { " ", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "" }, { " ", "", "", "", "", "", "" }, { "", "", "", "", "", "", "" },
			{ " ", "", "", "", "", "", "" }, { "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "" },
			{ " ", "", "", "", "", "", "" }, { "", "", "", "", "", "", "" } };
	private JTable table;
	private JFrame frame;

	/*-
	 * This constructor allows for editing Rules. - Constructor Arg0 - Player Type
	 * Hero, Average, etc.. Arg1 - Street FLOP, TURN, RIVER Arg2 -
	 * FIRST, MIDDLE, LAST Arg3 - Heads up if true
	 */
	ReportRules(String typ, String strt, String fml, boolean hu) {
		edit = true;
	//	seat = Options.reportSeat;
		street = strt;
		type = typ;
		pos = fml;
		headsUp = hu;
		if (headsUp) {
			fileName = "RuleHU.ser";
		} else {
			fileName = "Rule.ser";
		}
		report();
		display();
	}

	/*-
	 * This constructor only displays Rules, no editing. - Constructor Arg0 - Player
	 * Type Hero, Average, etc.. Arg1 - Street FLOP, TURN, RIVER
	 * Arg2 - FIRST, MIDDLE, LAST Arg3 - Heads up if true arg4 x
	 * position, arg5 y position
	 */
	ReportRules(String typ, String strt, String fml, boolean hu, int xx, int yy) {
		edit = false;
		x = xx;
		y = yy;
//		seat = Options.reportSeat;
		street = strt;
		type = typ;
		pos = fml;
		headsUp = hu;
		if (headsUp) {
			fileName = "RuleHU.ser";
		} else {
			fileName = "Rule.ser";
		}
		report();
		display();
	}

	/*- Close frame */
	void closeFrame() {
		frame.dispose();
	}

	/*- - Show frame. */
	void display() {
		if ("Flop".equals(street)) {
			if ("First".equals(pos) && headsUp) {
				frame = new JFrame(new StringBuilder().append("   FLOP  ").append(pos)
						.append("   Heads Up        Rules").toString());
				frame.setLocation(10, 50);
			}
			if ("Last".equals(pos) && headsUp) {
				frame = new JFrame(new StringBuilder().append("   FLOP  ").append(pos)
						.append(" Heads Up          Rules      ").toString());
				frame.setLocation(1000, 50);
			}
			if ("First".equals(pos) && !headsUp) {
				frame = new JFrame(new StringBuilder().append("   FLOP  ").append(pos).append("           Rules      ")
						.toString());
				frame.setLocation(10, 440);
			}
			if ("Middle".equals(pos) && !headsUp) {
				frame = new JFrame(
						new StringBuilder().append("   FLOP  ").append(pos).append("           Rules    ").toString());
				frame.setLocation(500, 440);
			}
			if ("Last".equals(pos) && !headsUp) {
				frame = new JFrame(new StringBuilder().append("   FLOP  ").append(pos).append("           Rules      ")
						.toString());
				frame.setLocation(1000, 440);
			}
		}
		if ("Turn".equals(street)) {
			if ("First".equals(pos) && headsUp) {
				frame = new JFrame(
						new StringBuilder().append("   TURN     ").append(pos).append("        Rules  ").toString());
				frame.setLocation(10, 50);
			}
			if ("Last".equals(pos) && headsUp) {
				frame = new JFrame(
						new StringBuilder().append("   TURN     ").append(pos).append("        Rules  ").toString());
				frame.setLocation(1000, 50);
			}
			if ("First".equals(pos) && !headsUp) {
				frame = new JFrame(
						new StringBuilder().append("   TURN     ").append(pos).append("        Rules  ").toString());
				frame.setLocation(10, 440);
			}
			if ("Middle".equals(pos) && !headsUp) {
				frame = new JFrame(
						new StringBuilder().append("   TURN     ").append(pos).append("        Rules ").toString());
				frame.setLocation(500, 440);
			}
			if ("Last".equals(pos) && !headsUp) {
				frame = new JFrame(
						new StringBuilder().append("   TURN     ").append(pos).append("        Rules    ").toString());
				frame.setLocation(1000, 420);
			}
		}
		if ("River".equals(street)) {
			if ("First".equals(pos) && headsUp) {
				frame = new JFrame(new StringBuilder().append("   RIVER     ").append(pos)
						.append("  Heads Up      Rules     ").toString());
				frame.setLocation(10, 50);
			}
			if ("Last".equals(pos) && headsUp) {
				frame = new JFrame(new StringBuilder().append("   RIVER     ").append(pos)
						.append(" Heads Up       Rules    ").toString());
				frame.setLocation(1000, 50);
			}
			if ("First".equals(pos) && !headsUp) {
				frame = new JFrame(new StringBuilder().append("   RIVER     ").append(pos).append("        Rules     ")
						.toString());
				frame.setLocation(10, 440);
			}
			if ("Middle".equals(pos) && !headsUp) {
				frame = new JFrame(
						new StringBuilder().append("   RIVER     ").append(pos).append("        Rules   ").toString());
				frame.setLocation(500, 440);
			}
			if ("Last".equals(pos) && !headsUp) {
				frame = new JFrame(
						new StringBuilder().append("   RIVER     ").append(pos).append("        Rules    ").toString());
				frame.setLocation(1000, 440);
			}
		}
		final Font f = new Font(Font.SERIF, Font.BOLD, 14);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				e.getWindow().dispose();
			}
		});

		JFrame.setDefaultLookAndFeelDecorated(true);

		table = new JTable(dataHand, columnsOpAction);
		table.setFont(f);
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(dataHand, columnsOpAction));
		table.getModel().addTableModelListener(new TableListener());
		final String[] playerActions = { "----", "Fold", "Check", "Call", "Bet", "Allin" };
		final JComboBox comboBox = new JComboBox(playerActions);
		if (edit) {
			table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
			table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));
			table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));
			table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(comboBox));
			table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(comboBox));
			table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(comboBox));
		}

		if (!edit) {
			frame.setLocation(x, y);
		}
		final JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(450, 340));
		final TableColumn a = table.getColumnModel().getColumn(0);
		a.setMinWidth(150);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
	}

	/*- - Controls. */
	private void controls() {
	}

	/*- Get and format report data for Flop. */
	private void report() {
		String st = "";
		String previous = "";

	//	final String file = new StringBuilder().append(Options.path).append(type).append("\\").append(street)
	//			.append("\\").append(pos).append("\\").append(fileName).toString();

	//	rules.readRules(file);

	//	for (int i = 0; i <= MADE_ROYAL_FLUSH; i++) {
	//		dataHand[i][0] = MADE_valuesSt[i];
	//	}

		for (int i =MADE_NONE; i <= MADE_ROYAL_FLUSH; i++) {
			st = rules.playToString(rules.getPlay(i, Rules.OP_CHECK));
			if ("Fold".equals(st)) {
				st = "Check";
			}
			dataHand[i][1] = st;
			previous = st;

			st = rules.playToString(rules.getPlay(i, Rules.OP_BET1));
			if (("Fold".equals(st) && "Fold".equals(previous)) || "----".equals(previous)) {
				st = "----";
			}
			previous = st;
			dataHand[i][2] = st;

			st = rules.playToString(rules.getPlay(i, Rules.OP_BET2));
			if (("Fold".equals(st) && "Fold".equals(previous)) || "----".equals(previous)) {
				st = "----";
			}
			previous = st;
			dataHand[i][3] = st;

			st = rules.playToString(rules.getPlay(i, Rules.OP_BET3));
			if (("Fold".equals(st) && "Fold".equals(previous)) || "----".equals(previous)) {
				st = "----";
			}
			previous = st;
			dataHand[i][4] = st;

			st = rules.playToString(rules.getPlay(i, Rules.OP_BET4));
			if (("Fold".equals(st) && "Fold".equals(previous)) || "----".equals(previous)) {
				st = "----";
			}
			previous = st;
			dataHand[i][5] = st;

			st = rules.playToString(rules.getPlay(i, Rules.OP_ALLIN));
			if (("Fold".equals(st) && "Fold".equals(previous)) || "----".equals(previous)) {
				st = "----";
			}
			previous = st;
			dataHand[i][6] = st;
		}
	}

	/*- - Save Rules File. */
	private void saveRules(int row, int col, String st) {
		int k = FOLD;
		if ("----".equals(st)) {
			k = Rules.NO_ACTION;
		}
		if ("Fold".equals(st)) {
			k = Rules.NO_ACTION;
		}
		if ("Check".equals(st)) {
			k = Rules.NO_ACTION;
		}
		if ("Call".equals(st)) {
			k = Rules.CALL;
		}
		if ("Bet".equals(st)) {
			k = Rules.BET;
		}
		if ("Allin".equals(st)) {
			k = Rules.ALLIN;
		}
		rules.setPlay(row, col - 1, k);
		final String file = new StringBuilder().append(Options.path).append(type).append("\\").append(street)
				.append("\\").append(pos).append("\\").append(fileName).toString();
		rules.writeRules(file);
	}

	/*- - Table cell listener. */
	class TableListener implements TableModelListener {
		@Override
		public void tableChanged(TableModelEvent e) {
			final int row = table.getSelectedRow();
			final int col = table.getSelectedColumn();
			final TableModel tableModel = (TableModel) e.getSource();
			final String columnName = tableModel.getColumnName(col);
			final Object data = tableModel.getValueAt(row, col);
			final String st = (String) data;
			if (edit) {
				saveRules(row, col, st);
			}
		}
	}

	/*- - Responds to button clicks. */
	private class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			final String action = e.getActionCommand();
			controls();
		}
	}
}
