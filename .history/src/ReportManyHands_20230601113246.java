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
	private static JFrame frame3 = null;
	private static JFrame frame4 = null;
	private static JFrame frame5 = null;
	private static JFrame frame6 = null;
	private static JFrame frame7 = null;
	private static JFrame frame8 = null;
	private static JFrame frame9 = null;
	private static JFrame frame10 = null;
	private static JFrame frame11 = null;
	private static JFrame frame12 = null;
	private static JFrame frame13 = null;
	private static JFrame frame14 = null;
	private static JFrame frame15 = null;
	private static JFrame frame16 = null;

	private static JTable table1 = null;
	private static JTable table2 = null;
	private static JTable table3 = null;
	private static JTable table4 = null;
	private static JTable table5 = null;
	private static JTable table6 = null;
	private static JTable table7 = null;
	private static JTable table8 = null;
	private static JTable table9 = null;
	private static JTable table10 = null;
	private static JTable table11 = null;
	private static JTable table12 = null;
	private static JTable table13 = null;
	private static JTable table14 = null;
	private static JTable table15 = null;
	private static JTable table16 = null;

	private static DefaultTableModel tableModel1 = null;
	private static DefaultTableModel tableModel2 = null;
	private static DefaultTableModel tableModel3 = null;
	private static DefaultTableModel tableModel4 = null;
	private static DefaultTableModel tableModel5 = null;
	private static DefaultTableModel tableModel6 = null;
	private static DefaultTableModel tableModel7 = null;
	private static DefaultTableModel tableModel8 = null;
	private static DefaultTableModel tableModel9 = null;
	private static DefaultTableModel tableModel0 = null;
	private static DefaultTableModel tableMode11 = null;
	private static DefaultTableModel tableMode12 = null;
	private static DefaultTableModel tableMode13 = null;
	private static DefaultTableModel tableMode14 = null;
	private static DefaultTableModel tableMode15 = null;
	private static DefaultTableModel tableMode16 = null;

	static JScrollPane pane1 = null;
	static JScrollPane pane2 = null;
	static JScrollPane pane3 = null;
	static JScrollPane pane4 = null;
	static JScrollPane pane5 = null;
	static JScrollPane pane6 = null;
	static JScrollPane pane7 = null;
	static JScrollPane pane8 = null;
	static JScrollPane pane9 = null;
	static JScrollPane pane10 = null;
	static JScrollPane pane11 = null;
	static JScrollPane pane12 = null;
	static JScrollPane pane13 = null;
	static JScrollPane pane14 = null;
	static JScrollPane pane15 = null;
	static JScrollPane pane16 = null;

	static Font ff1 = new Font(Font.SERIF, Font.BOLD, 14);

	private static final Object[] columnsHMLDraw = { "HML", "No draw", "Board pair", "Paired with board", "Gutshot",
			"Straight", "OESD", "Flush", "Flush OESD" };

	private static final Object[][] dataHMLDraw = { { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" } };

	private static final Object[] columnsHMLMade = { "HML", "No hand", "Any pair", "Two pair", "Set", "Straight",
			"Flush", "Full house", "Straight flush", "Royal flush" };

	private static final Object[][] dataHMLMade = { { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "" } };

	private static final Object[] columnsHMLShowdown = { "HML", "No hand", "Any pair", "Two pair", "Set", "Straight",
			"Flush", "Full house", "Straight flush", "Royal flush" };

	private static final Object[][] dataHMLShowdown = { { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "" } };

	private static final Object[] columnsHHMLDraw = { "HML", "No draw", "Board pair", "Paired with board", "Gutshot",
			"Straight", "OESD", "Flush", "Flush OESD" };

	private static final Object[][] dataHHMLDraw = { { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" } };

	private static final Object[] columnsHHMLMade = { "HML", "No hand", "Any pair", "Two pair", "Set", "Straight",
			"Flush", "Full house", "Straight flush", "Royal flush" };

	private static final Object[][] dataHHMLMade = { { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "" } };

	private static final Object[] columnsHHHMLDraw = { "HML", "No draw", "Board pair", "Paired with board", "Gutshot",
			"Straight", "OESD", "Flush", "Flush OESD" };

	private static final Object[][] dataHHHMLDraw = { { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" } };

	private static final Object[] columnsHHHMLMade = { "HML", "No hand", "Any pair", "Two pair", "Set", "Straight",
			"Flush", "Full house", "Straight flush", "Royal flush" };

	private static final Object[][] dataHHHMLMade = { { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "" } };

	private static final Object[] columnsHand = { "Draw or hand", "Wet", "Dry", "Neither" };

	private static final Object[][] dataHand = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, };

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

	static boolean reportHandsDone = false;

	private ReportManyHands() {
		throw new IllegalStateException("Utility class");
	}

	/*-**********************************************************************************
	 * Displays a report of shared data from board evaluation
	 * All data is in ManyData
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	********************************************************************************** */
	static void reportHMLDraw(int c, int r) {
		// if (frame1 == null)return;
		if (frame1 == null) {
			frame1 = new JFrame("Flop HML DRaw hands");
			frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame1.setLocation(c, r);
			frame1.setPreferredSize(new Dimension(650, 600));
			tableModel1 = new DefaultTableModel(dataHMLDraw, columnsHMLDraw);
			table1 = new JTable(tableModel1);
			table1.setFont(ff1);
			table1.setRowHeight(25);
			pane1 = new JScrollPane(table1);
			table1.getColumnModel().getColumn(0).setPreferredWidth(140);
			// table1.getColumnModel().getColumn(1).setPreferredWidth(180);
			frame1.add(pane1);
			frame1.pack();
			frame1.setVisible(true);
		} else {
			table1.getColumnModel().getColumn(0).setPreferredWidth(140);
			// table1.getColumnModel().getColumn(1).setPreferredWidth(180);
		}
		int row = 0;
		int col = 1;
		for (int i = 0; i < HML_FLOP_SIZE; ++i) {
			table1.setValueAt(HML_FLOP_ST[i], row, 0);
			col = 1;
			for (int j = 0; j < DRAW_HANDS_SIZE; ++j) {
				if (ManyData.hmlDraw[i][j] > 0) {
					table1.setValueAt(String.valueOf(ManyData.hmlDraw[i][j]), row, col);
					double per = ((double) ManyData.hmlDraw[i][j] / (double) ManyData.hmlCount) * 100.0;
					System.out.println("Draw " + per + " " + ManyData.hmlCount + " " + ManyData.hmlDraw[i][j]);
					table1.setValueAt(Format.formatPer(per), row + 1, col);
				}
				col++;
			}
			col = 1;
			row += 2;
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
		if (frame2 == null) {
			frame2 = new JFrame("Flop HML Made hands");
			frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame2.setLocation(c, r);
			frame2.setPreferredSize(new Dimension(650, 600));
			tableModel2 = new DefaultTableModel(dataHMLMade, columnsHMLMade);
			table2 = new JTable(tableModel2);
			table2.setFont(ff1);
			table2.setRowHeight(25);
			pane2 = new JScrollPane(table2);
			table2.getColumnModel().getColumn(0).setPreferredWidth(140);
			 
			frame2.add(pane2);
			frame2.pack();
			frame2.setVisible(true);
		} else {
			table2.getColumnModel().getColumn(0).setPreferredWidth(140);
		 
		}

		int row = 0;
		int col = 0;
		for (int i = 0; i < HML_FLOP_SIZE; ++i) {
			table2.setValueAt(HML_FLOP_ST[i], row, 0);
			col = 1;
			for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
				if (ManyData.hmlMade[i][j] > 0) {
					table2.setValueAt(String.valueOf(ManyData.hmlMade[i][j]), row, col);
					double per = ((double) ManyData.hmlMade[i][j] / (double) ManyData.hmlCount) * 100.0;
					table2.setValueAt(Format.formatPer(per), row + 1, col);
				}
				col++;
			}
			col = 1;
			row += 2;
		}
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
		if (frame11 == null) {
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
		table1.setValueAt("Games Simulated", row, 0);
		table1.setValueAt(String.valueOf(EvalData.fullSimulationNum), row++, 1);

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

	/*-
	 * Displays a report of shared data from board evaluation
	 * All data is in OneHand 
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportArrayWetDry(int c, int r) {
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

	}

	/*-
	 * Displays a report of shared data from board evaluation
	 * All data is in OneHand 
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportHMLArrayWetDry(int c, int r) {
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

	}

}
