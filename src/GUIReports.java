//package evaluate_streets;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUIReports implements Constants {
	/*- **************************************************************************** 
	* This Class is used by the GUI Class to display detailed data in a pop up 
	* window.
	* There are 2 display methods:
	* 		report(x,y)  Reports all ( almost ) data collected by Analyze in a scroll
	* 		panel. 
	* 		reportBoard(x,y)  Reports data associated with board. 
	* @author PEAK_
	*******************************************************************************/

	private static JFrame frame1 = null;
	private static JTable table1 = null;
	static DefaultTableModel tableModel1 = null;
	private static JFrame frame2 = null;
	private static JTable table2 = null;
	static DefaultTableModel tableModel2 = null;
	private static JFrame frame3 = null;
	private static JTable table3 = null;
	static DefaultTableModel tableModel3 = null;
	private static final Object[] columns = { "", "" };
	private static final Object[][] data = { { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" } };
	private static final Object[] columns2 = { "", "" };
	private static final Object[][] data2 = { { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" } };
	private static final Object[] columns3 = { "" };
	private static final Object[][] data3 = { { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" } };

	private GUIReports() {
		throw new IllegalStateException("Utility class");
	}

	/*- **************************************************************************** 
	*  Displays a report of all data colloected by Analyze.
	 * All data is in EvalData.both
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	*******************************************************************************/
	static void report(int c, int r) {
		DefaultTableModel tableModel1 = null;
		JScrollPane pane1 = null;
		final var ff1 = new Font(Font.SERIF, Font.BOLD, 14);

		// Check if the display window already exists
		if (frame1 == null) {
			frame1 = new JFrame(String.valueOf(EvalData.seat));
			frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame1.setLocation(c, r);
			frame1.setPreferredSize(new Dimension(350, 800));
			frame1.setTitle("All about this players hand");
			tableModel1 = new DefaultTableModel(data, columns);
			table1 = new JTable(tableModel1);
			table1.setFont(ff1);
			table1.setRowHeight(25);
			pane1 = new JScrollPane(table1);
			table1.getColumnModel().getColumn(0).setPreferredWidth(140);
			table1.getColumnModel().getColumn(1).setPreferredWidth(120);
			table1.setBackground(IVORY);
			frame1.add(pane1);
			frame1.pack();
			frame1.setVisible(true);
		} else {
			// Update existing window content
			frame1.setTitle(String.valueOf(EvalData.seat));
			table1.setModel(new DefaultTableModel(data, columns));
			table1.getColumnModel().getColumn(0).setPreferredWidth(140);
			table1.getColumnModel().getColumn(1).setPreferredWidth(120);
			table1.setBackground(IVORY);
		}
		int row = 0;
		table1.setValueAt("Seat", row, 0);
		table1.setValueAt(String.valueOf(EvalData.seat + 1), row++, 1);

		table1.setValueAt("Street", row, 0);
		table1.setValueAt(STREET_ST[EvalData.street], row++, 1);

		table1.setValueAt("Hole cards ", row, 0);
		table1.setValueAt(Dealer.holeCardsToString(), row++, 1);

		table1.setValueAt("Board", row, 0);
		table1.setValueAt(Dealer.boardToString(), row++, 1);

		table1.setValueAt("Hand sorted", row, 0);
		table1.setValueAt(cardArrayString(EvalData.both), row++, 1);

		table1.setValueAt("Board sorted card", row, 0);
		table1.setValueAt(cardArrayString(EvalData.boardCards), row++, 1);

		table1.setValueAt("Hand sorted card", row, 0);
		table1.setValueAt(cardArrayString(EvalData.bothCards), row++, 1);

		if (EvalData.bestFlopIndex != -1) {
			table1.setValueAt("bestHandIndex ", row, 0);
			table1.setValueAt(MADE_ARRAY_ST[EvalData.bestFlopIndex], row++, 1);
		}
		if (EvalData.handValue != -1) {
			table1.setValueAt("Hand Value", row, 0);
			table1.setValueAt(HAND_VALUE_ST[EvalData.handValue], row++, 1);
		}
		if (EvalData.bestTurnIndex != -1) {
			table1.setValueAt("bestTurnIndex", row, 0);
			table1.setValueAt(String.valueOf(EvalData.bestTurnIndex), row++, 1);
		}
		if (EvalData.bestRiverIndex != -1) {
			table1.setValueAt("bestRiverIndex", row, 0);
			table1.setValueAt(String.valueOf(EvalData.bestRiverIndex), row++, 1);
		}
		if (EvalData.handIndex != -1) {
			table1.setValueAt("handIndex", row, 0);
			table1.setValueAt(String.valueOf(EvalData.handIndex), row++, 1);
		}
		if (EvalData.hmlIndex != -1) {
			table1.setValueAt("hmlIndex", row, 0);
			table1.setValueAt(String.valueOf(EvalData.hmlIndex), row++, 1);
		}
		if (EvalData.flop1755Index != -1) {
			table1.setValueAt("flop1755Index", row, 0);
			table1.setValueAt(String.valueOf(EvalData.flop1755Index), row++, 1);
		}
		if (EvalData.flopTypeOf1755 != -1) {
			table1.setValueAt("flopTypeOf1755", row, 0);
			table1.setValueAt(TYPE_OF_1755_ST[EvalData.flopTypeOf1755], row++, 1);
		}
		if (EvalData.bestPossibleArrayFlopIndex != -1) {
			table1.setValueAt("bestPossibleArrayFlopIndex", row, 0);
			table1.setValueAt(String.valueOf(EvalData.bestPossibleArrayFlopIndex), row++, 1);
		}
		if (EvalData.bestPossibleArrayTurnIndex != -1) {
			table1.setValueAt("bestPossibleArrayTurnIndex", row, 0);
			table1.setValueAt(String.valueOf(EvalData.bestPossibleArrayTurnIndex), row++, 1);
		}
		if (EvalData.bestPossibleArrayRiverIndex != -1) {
			table1.setValueAt("bestPossibleArrayRiverIndex         ", row, 0);
			table1.setValueAt(String.valueOf(EvalData.bestPossibleArrayRiverIndex), row++, 1);
		}
		if (EvalData.winnerShowdown != -1) {
			table1.setValueAt("winnerShowdown", row, 0);
			table1.setValueAt(String.valueOf(EvalData.winnerShowdown), row++, 1);
		}
		for (int i = 0; i < PLAYERS; i++) {
			if (EvalData.showdownValue[i] > 0) {
				table1.setValueAt("showdownValue  " + String.valueOf(i), row, 0);
				table1.setValueAt(String.valueOf(EvalData.showdownValue[i]), row++, 1);
			}
		}
		table1.setValueAt("Seat", row, 0);
		table1.setValueAt(String.valueOf(EvalData.seat + 1), row++, 1);

		table1.setValueAt("Street", row, 0);
		table1.setValueAt(STREET_ST[EvalData.street], row++, 1);

		table1.setValueAt("handStrength", row, 0);
		table1.setValueAt(String.valueOf(EvalData.handStrength), row++, 1);

		table1.setValueAt("rangeAdvantage", row, 0);
		table1.setValueAt(String.valueOf(EvalData.rangeAdvantage), row++, 1);

		table1.setValueAt(" madeHand", row, 0);
		table1.setValueAt(String.valueOf(EvalData.madeHand), row++, 1);

		table1.setValueAt("bestDraw", row, 0);
		table1.setValueAt(String.valueOf(EvalData.bestDraw), row++, 1);

		table1.setValueAt("bestPossible", row, 0);
		table1.setValueAt(Format.formatPer(EvalData.bestPossible), row++, 1);

		table1.setValueAt("outs", row, 0);
		table1.setValueAt(String.valueOf(EvalData.outs), row++, 1);

		table1.setValueAt("makeHandPer", row, 0);
		table1.setValueAt(Format.formatPer(EvalData.makeHandPer), row++, 1);

		table1.setValueAt("flopTexture ", row, 0);
		table1.setValueAt(String.valueOf(EvalData.flopTexture), row++, 1);

		table1.setValueAt(" turnTexture", row, 0);
		table1.setValueAt(String.valueOf(EvalData.turnTexture), row++, 1);

		for (int i = 0; i < EvalData.madeArray.length; ++i) {
			if (EvalData.madeArray[i]) {
				table1.setValueAt(MADE_ARRAY_ST[i], row, 0);
				++row;
			}
		}
		for (int i = 0; i < EvalData.drawArray.length; ++i) {
			if (EvalData.drawArray[i]) {
				table1.setValueAt(DRAW_ARRAY_ST[i], row, 0);
				++row;
			}
		}
		for (int i = 0; i < BOARD_ARRAY_ST.length; ++i) {
			if (EvalData.boardArray[i]) {
				table1.setValueAt(BOARD_ARRAY_ST[i], row, 0);
				++row;
			}
		}
		if (EvalData.pair) {
			table1.setValueAt("pair ", row, 0);
			++row;
		}
		if (EvalData.boardPair) {
			table1.setValueAt("boardPair", row, 0);
			++row;
		}
		if (EvalData.gap0 != 0) {
			table1.setValueAt("gap0", row, 0);
			table1.setValueAt(Format.format(EvalData.gap0), row, 1);
			++row;
		}
		if (EvalData.gap1 != 0) {
			table1.setValueAt("gap1", row, 0);
			table1.setValueAt(Format.format(EvalData.gap1), row, 1);
			++row;
		}
		if (EvalData.gap2 != 0) {
			table1.setValueAt("gap2", row, 0);
			table1.setValueAt(Format.format(EvalData.gap2), row, 1);
			++row;
		}
		if (EvalData.gap0Score != 0) {
			table1.setValueAt("gap0Score", row, 0);
			table1.setValueAt(Format.format(EvalData.gap0Score), row, 1);
			++row;
		}
		if (EvalData.gap1Score != 1) {
			table1.setValueAt("gap1Score", row, 0);
			table1.setValueAt(Format.format(EvalData.gap1Score), row, 1);
			++row;
		}
		if (EvalData.gap2Score == 0) {
			return;
		}
		table1.setValueAt("gap2Score", row, 0);
		table1.setValueAt(Format.format(EvalData.gap2Score), row, 1);
		++row;

		for (int i = 0; i < POSSIBLE_ST.length; ++i) {
			if (EvalData.boardPossibleArrayFlop[i]) {
				table1.setValueAt(POSSIBLE_ST[i], row, 0);
				++row;
			}
		}

		for (int i = 0; i < BOARD_ARRAY_ST.length; ++i) {
			if (EvalData.boardArray[i]) {
				table1.setValueAt(BOARD_ARRAY_ST[i], row, 0);
				++row;
			}
		}

		for (int i = 0; i < PLAYERS; ++i) {
			if (EvalData.playerWonShowdown[i]) {
				table1.setValueAt(String.valueOf(i), row, 0);
				table1.setValueAt("Won", row, 1);
				++row;
			} else {
				table1.setValueAt(String.valueOf(i), row, 0);
				table1.setValueAt("Lost", row, 1);
				++row;
			}
		}
		table1.setValueAt(EvalData.winnerSeat, row, 0);
		table1.setValueAt("Winners seat", row, 1);
		++row;

		if (EvalData.maxValue == -1) {
			return;
		}
		table1.setValueAt("maxValue ", row, 0);
		table1.setValueAt(Format.format(EvalData.maxValue), row, 1);

	}

	/*- **************************************************************************** 
	*  Displays a report of all data colloected by Analyze.
	 * All data is in EvalData.both
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	*******************************************************************************/
	static void reportBoard(int c, int r) {
		DefaultTableModel tableModel2 = null;
		JScrollPane pane2 = null;
		final var ff2 = new Font(Font.SERIF, Font.BOLD, 14);

		// Check if the display window already exists
		if (frame2 == null) {
			frame2 = new JFrame(String.valueOf(EvalData.seat));
			frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame2.setLocation(c, r);
			frame2.setPreferredSize(new Dimension(350, 300));
			frame2.setTitle("All about the Board ");
			tableModel2 = new DefaultTableModel(data2, columns2);
			table2 = new JTable(tableModel2);
			table2.setFont(ff2);
			table2.setRowHeight(25);
			pane2 = new JScrollPane(table2);
			table2.getColumnModel().getColumn(0).setPreferredWidth(140);
			table2.getColumnModel().getColumn(1).setPreferredWidth(120);
			table2.setBackground(IVORY);
			frame2.add(pane2);
			frame2.pack();
			frame2.setVisible(true);
		} else {
			// Update existing window content
			frame2.setTitle(String.valueOf(EvalData.seat));
			table2.setModel(new DefaultTableModel(data2, columns2));
			table2.getColumnModel().getColumn(0).setPreferredWidth(140);
			table2.getColumnModel().getColumn(1).setPreferredWidth(120);
			table2.setBackground(IVORY);
		}
		int row = 0;
		for (int i = 0; i < BOARD_ARRAY_SIZE; i++) {
			if (EvalData.boardArray[i]) {
				table2.setValueAt(BOARD_ARRAY_ST[i], row, 0);
				++row;
			}
		}

		for (int i = 0; i < FLOP_MAX; i++) {
			if (EvalData.flopArray[i]) {
				table2.setValueAt(FLOP_ARRAY_ST[i], row, 0);
				++row;
			}
		}
	}

	/*- **************************************************************************** 
	*  Displays a report of all data colloected by Analyze.
	 * All data is in EvalData.both
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	*******************************************************************************/
	static void reportPreflop(int c, int r) {
		DefaultTableModel tableModel3 = null;
		JScrollPane pane3 = null;
		final var ff3 = new Font(Font.SERIF, Font.BOLD, 14);

		// Check if the display window already exists
		if (frame3 == null) {
			frame3 = new JFrame(String.valueOf(EvalData.seat));
			frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame3.setLocation(c, r);
			frame3.setPreferredSize(new Dimension(350, 300));
			frame3.setTitle("Hand History");
			tableModel3 = new DefaultTableModel(data3, columns3);
			table3 = new JTable(tableModel3);
			table3.setFont(ff3);
			table3.setRowHeight(25);
			pane3 = new JScrollPane(table3);
			table3.getColumnModel().getColumn(0).setPreferredWidth(140);
			table3.setBackground(IVORY);
			frame3.add(pane3);
			frame3.pack();
			frame3.setVisible(true);
		} else {
			// Update existing window content
			frame3.setTitle(String.valueOf(EvalData.seat));
			table3.setModel(new DefaultTableModel(data3, columns3));
			table3.getColumnModel().getColumn(0).setPreferredWidth(140);
			table3.getColumnModel().getColumn(1).setPreferredWidth(120);
			table3.setBackground(IVORY);
		}
		int row = 0;
		// read .txt file
		final var buffer = FileUtils
				.readTxtFileIntoString(EvalData.applicationDirectory + "\\HandHistory\\HHSimulation.txt");

		// break String into array
		final var lines = buffer.split("\n");
		for (int i = 0; i < lines.length; ++i) {
			table3.setValueAt(lines[i], row, 0);
			++row;
			if (i > data3.length - 1) {
				break;
			}
		}
	}

	// Report helper
	/*- **************************************************************************** 
	*  Helper method to convert a Card[] to a String
	*******************************************************************************/
	private static String cardArrayString(Card[] cards) {
		var st = "";
		for (var card : cards) {
			if (card == null) {
				return st;
			} else {
				st += card.toString();
			}
		}
		return st;
	}

}
