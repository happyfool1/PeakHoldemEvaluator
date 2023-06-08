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

	private static JFrame frame1 = null;
	private static JFrame frame2 = null;

	private static JTable table1 = null;
	static DefaultTableModel tableModel1 = null;
	static DefaultTableModel tableModel2 = null;
	static DefaultTableModel tableModel3 = null;
	static DefaultTableModel tableModel4 = null;
	static DefaultTableModel tableModel5 = null;
	static DefaultTableModel tableModel6 = null;
	static DefaultTableModel tableModel7 = null;

	static JScrollPane pane1 = null;
	static JScrollPane pane2 = null;
	static JScrollPane pane3 = null;
	static JScrollPane pane4 = null;
	static JScrollPane pane5 = null;
	static JScrollPane pane6 = null;
	static JScrollPane pane7 = null;

	static Font ff1 = new Font(Font.SERIF, Font.BOLD, 14);


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

	private static final Object[] columnsHMLDraw = { "HML","No draw", "Board pair", "Paired with board", "Gutshot",
			"Straight", "OESD", "Flush", "Flush OESD" };

	private static final Object[][] dataHMLDraw = { { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" } };

	private static final Object[] columnsHMLMade = {"HML", "No hand", "Any pair", "Two pair", "Set", "Straight",
			"Flush", "Full house", "Straight flush", "Royal flush" };

	private static final Object[][] dataHMLMade = { { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }
	};

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

	static boolean reportHandsDone = false;

	private ReportManyHands() {
		throw new IllegalStateException("Utility class");
	}

	/*- **********************************************************************************
	 * Displays a report of shared data from board evaluation
	 * All data is in AnalyzeMany or EvalData.
	 * This report is for a summaty of what happened in gthis run.
	 * 
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	********************************************************************************** */
	static void reportSummary(int c, int r) {
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
 
		int row = 0;
		table.setValueAt("Games Simulated", row, 0);
		table.setValueAt(String.valueOf(EvalData.fullSimulationNum), row++, 1);

	}

	/*-**********************************************************************************
	* Displays a report of shared data from board evaluation
	* All data is in OneHand 
	* 
	* Arg0 - Column for frame
	* Arg1 - Row for frame
	***********************************************************************************/
	static void reportBoardArrayFlopPer(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);
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

	/*-**********************************************************************************
	 * Displays a report of shared data from board evaluation
	 * All data is in ManyData
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	********************************************************************************** */
	static void reportHMLDraw(int c, int r) {
		JTable table = null;
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
 
		int row = 0;
		int col = 1;

		for (int i = 0; i < HML_FLOP_SIZE; ++i) {
			table.setValueAt(HML_FLOP_ST[i], row, 0);
			col = 1;
			for (int j = 0; j < DRAW_HANDS_SIZE; ++j) {
				if (ManyData.hmlDraw[i][j] > 0) {
					table.setValueAt(String.valueOf(ManyData.hmlDraw[i][j]), row, col);
				}
				col++;
			}
			col =1;
			row++;
		}
	}

	/*-**********************************************************************************
	 * Displays a report of shared data from board evaluation
	 * All data is in ManyData
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	********************************************************************************** */
	static void reportHMLMade(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);

		final var frame = new JFrame("Flop HML for Made Hands ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(800, 1000));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataHMLMade, columnsHMLMade);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

		int row = 0;
		int col = 0;
		for (int i = 0; i < HML_FLOP_SIZE; ++i) {
			table.setValueAt(HML_FLOP_ST[i], row, 0);
			col = 1;
			for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
				if (ManyData.hmlMade[i][j] > 0) {
					table.setValueAt(String.valueOf(ManyData.hmlMade[i][j]), row, col);
				}
				col++;
			}
			col = 1;
			row++;
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
