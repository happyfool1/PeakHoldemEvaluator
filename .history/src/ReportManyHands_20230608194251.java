//package evaluate_streets;
/*-  *********************************************************************************************
 * This Class displays variour reports.
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
	private static DefaultTableModel tableModel6 = null;
	private static DefaultTableModel tableModel7 = null;
	private static DefaultTableModel tableModel8 = null;
	private static DefaultTableModel tableModel9 = null;
	private static DefaultTableModel tableModell0 = null;
	private static DefaultTableModel tableModel11 = null;
	private static DefaultTableModel tableModel12 = null;
	private static DefaultTableModel tableModel13 = null;
	private static DefaultTableModel tableModel14 = null;
	private static DefaultTableModel tableModel15 = null;
	private static DefaultTableModel tableModel16 = null;

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
