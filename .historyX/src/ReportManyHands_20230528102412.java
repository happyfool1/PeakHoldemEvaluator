//package evaluate_streets;
/*-  *********************************************************************************************
 * This is a quick and dirty Class that is used to help in the testing and debug 
 * of this project.
 * It is also used to show what this project does.
 * A bunch of individual frames are created that display data in ManyData.
 * ManyHands data is for just that, many hand.
 * EvaluateMany must have been called every hand played.
 * 
 * All methods have 2 arguments, the column and the row where the frame is 
 * to be displayed.
 * 
 * @author PEAK_
  **********************************************************************************************/

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReportManyHands implements Constants {

	private static final Object[] columnsSummary = { " ", " " };

	private static final Object[][] dataSummary = { { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" } };

	private static final Object[] columnsBoardArrayFlopPer = { "BoardArrayFlopPer", "Percentage" };

	private static final Object[][] dataBoardArrayFlopPer = { { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, };

	private static final Object[] columnsAny = { " ", " " };

	private static final Object[][] dataAny = { { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" } };

	private static final Object[] columnsHMLArray = { "Draw or hand" };

	private static final Object[] columnsArrayWetDry = { "Draw or hand", "Wet", "Dry", "Neither" };

	private static final Object[][] dataArrayWetDry = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, };


	private static final Object[][] dataHMLArrayWetDry = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, };




/*-
	 * Displays a report of hand contents
	 * All data is in EvalData.both
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportHands(int c, int r) {

		DefaultTableModel tableModel1 = null;
		JScrollPane pane1 = null;
		final var ff1 = new Font(Font.SERIF, Font.BOLD, 14);

		// Check if the display window already exists
		if (frame1 == null) {
			frame1 = new JFrame(String.valueOf(EvalData.seat));
			frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame1.setLocation(c, r);
			frame1.setPreferredSize(new Dimension(350, 500));
			tableModel1 = new DefaultTableModel(dataHand, columnsHand);
			table1 = new JTable(tableModel1);
			table1.setFont(ff1);
			table1.setRowHeight(25);
			pane1 = new JScrollPane(table1);
			table1.getColumnModel().getColumn(0).setPreferredWidth(140);
			table1.getColumnModel().getColumn(1).setPreferredWidth(120);
			frame1.add(pane1);
			frame1.pack();
			frame1.setVisible(true);
		} else {
			// Update existing window content
			frame1.setTitle(String.valueOf(EvalData.seat));
			table1.setModel(new DefaultTableModel(dataHand, columnsHand));
			table1.getColumnModel().getColumn(0).setPreferredWidth(140);
			table1.getColumnModel().getColumn(1).setPreferredWidth(120);
		}

		System.out.println("// report ");
		int row = 0;
		table1.setValueAt("Seat", row, 0);
		table1.setValueAt(String.valueOf(EvalData.seat), row++, 1);

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

	}








	/*-
	 * Displays a report of hand contents
	 * All data is in OneHand.hand
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static boolean reportHandsDone = false;

	private ReportManyHands() {
		throw new IllegalStateException("Utility class");
	}

	/*-
	 * Displays a report of shared data from board evaluation
	 * All data is in AnalyzeMany or EvalData.
	 * This report is for a summaty of what happened in gthis run.
	 * 
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportSummary(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);

		final var frame = new JFrame("Summary");
		columnsBoardArrayFlopPer[0] = "Summary of Data Collection run ";
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(300, 600));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataSummary, columnsSummary);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

		int row = 0;
		table.setValueAt("Games Simulated", row, 0);
		table.setValueAt(String.valueOf(EvalData.fullSimulationNum), row++, 1);

	}

	/*-
		 * Displays a report of shared data from board evaluation
		 * All data is in OneHand 
		 * 
		 * Arg0 - Column for frame
		 * Arg1 - Row for frame
		 */
	static void reportBoardArrayFlopPer(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);

		final var frame = new JFrame("Flop ");
		columnsBoardArrayFlopPer[0] = "BoardArrayFlopPer ";
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(300, 600));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataBoardArrayFlopPer, columnsBoardArrayFlopPer);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

		int row = 0;
		for (int i = 0; i < ManyData.boardArrayFlopPer.length; ++i) {
			table.setValueAt(BOARD_ARRAY_ST[i], row, 0);
			table.setValueAt(Format.formatPer(ManyData.boardArrayFlopPer[i]), row, 1);
			++row;
		}
		for (int i = 0; i < ManyData.boardArrayFlop.length; i++) {
			if (ManyData.boardArrayFlop[i] > 0) {
				table.setValueAt(BOARD_ARRAY_ST[i], row, 0);
				table.setValueAt(ManyData.boardArrayFlop[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.drawArrayFlop.length; ++i) {
			if (ManyData.drawArrayFlop[i] > 0) {
				table.setValueAt(DRAW_ARRAY_ST[i], row, 0);
				table.setValueAt(ManyData.drawArrayFlop[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.madeArrayFlop.length; ++i) {
			if (ManyData.madeArrayFlop[i] > 0) {
				table.setValueAt(MADE_ARRAY_ST[i], row, 0);
				table.setValueAt(ManyData.madeArrayFlop[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.boardArrayFlop.length; i++) {
			if (ManyData.boardArrayFlop[i] > 0) {
				table.setValueAt(BOARD_ARRAY_ST[i], row, 0);
				table.setValueAt(ManyData.boardArrayFlop[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.drawArrayTurn.length; ++i) {
			if (ManyData.drawArrayTurn[i] > 0) {
				table.setValueAt(DRAW_ARRAY_ST[i], row, 0);
				table.setValueAt(ManyData.drawArrayTurn[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.madeArrayTurn.length; ++i) {
			if (ManyData.madeArrayTurn[i] > 0) {
				table.setValueAt(MADE_ARRAY_ST[i], row, 0);
				table.setValueAt(ManyData.madeArrayTurn[i], row, 1);
				++row;
			}
		}

		for (int i = 0; i < ManyData.boardArrayRiver.length; i++) {
			if (ManyData.boardArrayFlop[i] > 0) {
				table.setValueAt(BOARD_ARRAY_ST[i], row, 0);
				table.setValueAt(ManyData.boardArrayFlop[i], row, 1);
				++row;
			}
		}
		
		for (int i = 0; i < ManyData.madeArrayRiver.length; ++i) {
			if (ManyData.madeArrayRiver[i] > 0) {
				table.setValueAt(MADE_ARRAY_ST[i], row, 0);
				table.setValueAt(ManyData.madeArrayRiver[i], row, 1);
				++row;
			}
		}

		for (int i = 0; i < ManyData.boardPossibleArrayFlop.length; ++i) {
			if (ManyData.boardPossibleArrayFlop[i] > 0) {
				table.setValueAt(POSSIBLE_ARRAY_ST[i], row, 0);
				table.setValueAt(ManyData.boardPossibleArrayFlop[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.sumOfHandValuesFlopArray.length; ++i) {
			if (ManyData.sumOfHandValuesFlopArray[i] > 0) {
				table.setValueAt("Sum of hand values", row, 0);
				table.setValueAt(ManyData.sumOfHandValuesFlopArray[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.sumOfBoardValuesFlopArray.length; ++i) {
			if (ManyData.sumOfBoardValuesFlopArray[i] > 0) {
				table.setValueAt("Sum of hand values", row, 0);
				table.setValueAt(ManyData.sumOfHandValuesFlopArray[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.sumOfHoleCardValuesArray.length; ++i) {
			if (ManyData.sumOfHoleCardValuesArray[i] > 0) {
				table.setValueAt("Sum of hole card values", row, 0);
				table.setValueAt(ManyData.sumOfHoleCardValuesArray[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.hmlArrayFlop.length; ++i) {
			if (ManyData.hmlArrayFlop[i] > 0) {
				table.setValueAt(HML_FLOP_ST[i], row, 0);
				table.setValueAt(ManyData.hmlArrayFlop[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.hmlArrayTurn.length; ++i) {
			if (ManyData.hmlArrayTurn[i] > 0) {
				table.setValueAt(HML_TURN_ST[i], row, 0);
				table.setValueAt(ManyData.hmlArrayTurn[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.boardPossibleArrayTurn.length; ++i) {
			if (ManyData.boardPossibleArrayTurn[i] > 0) {
				table.setValueAt(POSSIBLE_ARRAY_ST[i], row, 0);
				table.setValueAt(ManyData.boardPossibleArrayTurn[i], row, 1);
				++row;
			}
		}

		for (int i = 0; i < ManyData.hmlArrayRiver.length; ++i) {
			if (ManyData.hmlArrayRiver[i] > 0) {
				table.setValueAt(HML_RIVER_ST[i], row, 0);
				table.setValueAt(ManyData.hmlArrayRiver[i], row, 1);
				++row;
			}
		}
		for (int i = 0; i < ManyData.flopTypeOf1755Array.length; ++i) {
			if (ManyData.flopTypeOf1755Array[i] > 0) {
				table.setValueAt("1755", row, 0);
				table.setValueAt(ManyData.flopTypeOf1755Array[i], row, 1);
				++row;
			}
		}

		for (int i = 0; i < ManyData.typeArray.length; ++i) {
			if (ManyData.typeArray[i] > 0) {
				table.setValueAt("type", row, 0);
				table.setValueAt(ManyData.typeArray[i], row, 1);
				++row;
			}
		}

	}

	/*-
	 * Displays a report of shared data from board evaluation
	 * All data is in OneHand 
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportHML(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);

		final var frame = new JFrame(" HML  ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(200, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataHMLArrayWetDry, columnsHMLArray);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

		int row = 0;
		for (int i = 0; i < ManyData.boardArrayFlopPer.length; ++i) {
			table.setValueAt(BOARD_ARRAY_ST[i], row, 0);
			table.setValueAt(ManyData.boardArrayFlopPer[i], row, 0);
			++row;
		}
	}

	/*-
	 * Displays a report of shared data from board evaluation
	 * All data is in OneHand 
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportArrayWetDry(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);

		final var frame = new JFrame("reportArrayWetDry ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(200, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataArrayWetDry, columnsArrayWetDry);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

	}

	/*-
	 * Displays a report of shared data from board evaluation
	 * All data is in OneHand 
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportHMLArrayWetDry(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);

		final var frame = new JFrame("HMLArrayWetDry ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(200, 800));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataAny, columnsAny);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
	}

	

}
