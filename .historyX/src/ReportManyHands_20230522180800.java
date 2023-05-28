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

	private static final Object[] columnsHMLArrayWetDry = { "Draw or hand", "Wet", "Dry", "Neither" };

	private static final Object[][] dataHMLArrayWetDry = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, };

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
		for (int i = 0; i < ManyData.boardPossibleArrayFlop.length; ++i) {
			if (ManyData.boardPossibleArrayFlop[i] > 0) {
                table.setValueAt(HAND_ARRAY_ST[i], row, 0);
                table.setValueAt(ManyData.handArrayFlop[i], row, 1);
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

	/*-
	 * Displays a report of shared data from board evaluation
	 * Any array
	 * 
	 * Arg 0 - Array of values
	 * Arg 1 - Array of String description
	 * Arg2 - Column for frame
	 * Arg3 - Row for frame
	 */
	static void reportAny(int[] array, int[] arraySt, int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);

		final var frame = new JFrame("Any array ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(200, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataHMLArrayWetDry, columnsHMLArrayWetDry);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

		int row = 0;
		for (int i = 0; i < array.length; ++i) {
			table.setValueAt(arraySt[i], row, 0);
			table.setValueAt(array[i], row++, 0);
		}
	}

}
