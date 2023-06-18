//package evaluate_streets;
/*-  *********************************************************************************************
 * This is a quick and dirty Class that is used to help in the testing and debug 
 * of this project.
 * It is also used to show what this project does.
 * A bunch of individual frames are created that display data in EvalData.
 * EvalData. data is for just that, one hand.
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

public class ReportOneHand implements Constants {

	private static final Object[] columnsHand = { "Value ", " " };

	private static final Object[][] dataHand = { { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" } };

	private static final Object[] columnsDraws = { "Draw or made hand" };

	private static final Object[] columnsBoardArray = { "Board" };

	private static final Object[][] dataBoardArray = { { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" } };

	private static final Object[] columnsMade = { "Board" };

	private static final Object[][] dataMade = { { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" } };

	private static final Object[] columnsDraw = { "Board" };

	private static final Object[][] dataDraw = { { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" } };

	private static final Object[] columnsData = { "Data", "Count" };

	private static final Object[][] dataData = { { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" } };

	

	private static final Object[] columnsMadeHandsRiver = { "Made hand" };

	private static final Object[][] dataMadeHandsRiver = { { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
			{ "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" } };

	private static final Object[] columnsShowdown = { "Seat", "Winner ", " " };

	private static final Object[][] dataShowdown = { { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" } };

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

	private ReportOneHand() {
		throw new IllegalStateException("Utility class");
	}

	/*-
	To ensure that only one display window is created, you can check if the frame1 object
	 has already been created before creating a new one. If frame1 already exists, 
	 then you can simply update its content instead of creating a new instance. 
	 Here is an updated code example:
	In this updated code, the frame1 object is initially set to null. 
	When the reportHands method is called, it checks if frame1 is null. If it is null, 
	then a new instance of frame1 is created and initialized with the display 
	window content. If frame1 is not null, then the existing frame1 instance 
	is updated with the new content.
	
		Note that to update the table content of an existing JTable, you can simply
		 set a new TableModel object to it. 
		 This will automatically update the table with the new content.
		 
		 	Assuming you're referring to a graphical user interface (GUI) application in a 
		 	programming language like Java, you can close a frame from another class 
		 	and method by doing the following:
	
	Make sure that the frame you want to close is declared as a field or instance variable of 
	the class in which it was created. This will allow you to access it from other classes and methods.
	
	Create a method in the class that created the frame, which will be called 
	by the other class to close the frame. 
	This method should call the dispose() method on the frame to release 
	any resources it's using and remove it from the screen.
	
	Here's an example of how you can implement these steps in Java:
	
	public class ReportHandsFrame extends JFrame {
	// Declare the frame as an instance variable
	private JFrame reportHandsFrame;
	
	public ReportHandsFrame() {
	    // Create the frame
	    reportHandsFrame = new JFrame("Report Hands");
	    // Add components to the frame
	    // ...
	    // Display the frame
	    reportHandsFrame.setVisible(true);
	}
	
	// Method to close the frame
	public void closeFrame() {
	    reportHandsFrame.dispose();
	}
	}
	
	public class OtherClass {
	public void someMethod() {
	    // Get a reference to the ReportHandsFrame object
	    ReportHandsFrame frame = new ReportHandsFrame();
	    // Call the closeFrame method to close the frame
	    frame.closeFrame();
	}
	}
	In the above example, the closeFrame() method in the ReportHandsFrame class is called by 
	*/

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
	 * Displays a report of summart data 
	 * All data is in EvalData.both
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportSummary(int c, int r) {
		JTable table = null;
		final JScrollPane pane1 = null;
		final var ff1 = new Font(Font.SERIF, Font.BOLD, 14);

		// Check if the display window already exists
		if (frame2 == null) {
			frame2 = new JFrame(String.valueOf(EvalData.seat));
			frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame2.setLocation(c, r);
			frame2.setPreferredSize(new Dimension(350, 500));

			table = new JTable(tableModel1);
			table.setFont(ff1);
			table.setRowHeight(25);
			table.getColumnModel().getColumn(0).setPreferredWidth(140);
			table.getColumnModel().getColumn(1).setPreferredWidth(120);
			frame2.add(pane1);
			frame2.pack();
			frame2.setVisible(true);
		} else {
			// Update existing window content
			frame2.setTitle(String.valueOf(EvalData.seat));

		}

		int row = 0;
		table.setValueAt("Seat", row, 0);
		table.setValueAt(String.valueOf(EvalData.seat), row++, 1);

		table.setValueAt("Street", row, 0);
		table.setValueAt(STREET_ST[EvalData.street], row++, 1);

		table.setValueAt("handStrength", row, 0);
		table.setValueAt(String.valueOf(EvalData.handStrength), row++, 1);

		table.setValueAt("rangeAdvantage", row, 0);
		table.setValueAt(String.valueOf(EvalData.rangeAdvantage), row++, 1);

		table.setValueAt(" madeHand", row, 0);
	// TODO	table.setValueAt(String.valueOf(EvalData.madeHand), row++, 1);

		table.setValueAt("bestDraw", row, 0);
	// TODO	table.setValueAt(String.valueOf(EvalData.bestDraw), row++, 1);

		table.setValueAt("bestPossible", row, 0);
		table.setValueAt(Format.formatPer(EvalData.bestPossible), row++, 1);

		table.setValueAt("outs", row, 0);
		table.setValueAt(String.valueOf(EvalData.outs), row++, 1);

		table.setValueAt("makeHandPer", row, 0);
		table.setValueAt(Format.formatPer(EvalData.makeHandPer), row++, 1);

		table.setValueAt("flopTexture ", row, 0);
		table.setValueAt(String.valueOf(EvalData.flopTexture), row++, 1);

		table.setValueAt(" turnTexture", row, 0);
		table.setValueAt(String.valueOf(EvalData.turnTexture), row++, 1);

	}

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

	/*-
	 * Displays a report of drawAndMadeArray
	 * All data is in EvalData.drawAndMadeArray
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportMadeArray(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);
		final var frame = new JFrame(STREET_ST[EvalData.street]);
		columnsDraws[0] = new StringBuilder().append(STREET_ST[EvalData.street]).append(" Players hand for seat ")
				.append(String.valueOf(EvalData.seat)).toString();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(200, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataMade, columnsMade);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

		int row = 0;
		for (int i = 0; i < MADE_SIZE; ++i) {
//	TODO		if (EvalData.madeArray[i]) {
//				table.setValueAt(MADE_ARRAY_ST[i], row, 0);
//				++row;
//			}
		}
	}

	/*-
	 * Displays a report of drawAndMadeArray
	 * All data is in EvalData.drawAndMadeArray
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportDrawArray(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);
		final var frame = new JFrame(STREET_ST[EvalData.street]);
		columnsDraws[0] = new StringBuilder().append(STREET_ST[EvalData.street]).append(" Players hand for seat ")
				.append(String.valueOf(EvalData.seat)).toString();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(200, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataDraw, columnsDraw);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

		int row = 0;
		for (int i = 0; i < EvalData.drawArray.length; ++i) {
			if (EvalData.drawArray[i]) {
				table.setValueAt(DRAW_ARRAY_ST[i], row, 0);
				++row;
			}
		}
	}

	/*-
	 * Displays a report of hand contents
	* All data is in EvalData.boardArray
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportBoardArray(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);

		final var frame = new JFrame("Board only ");
		columnsBoardArray[0] = "Board only " + STREET_ST[EvalData.street];
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(200, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataBoardArray, columnsBoardArray);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

		int row = 0;
		for (int i = 0; i < BOARD_ARRAY_ST.length; ++i) {
			if (EvalData.boardArray[i]) {
				table.setValueAt(BOARD_ARRAY_ST[i], row, 0);
				++row;
			}
		}
	}

	/*-
	 * Displays a report of shared data from board evaluation
	 * All data is in EvalData. 
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame	
	 */
	static void reportData(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);

		final var frame = new JFrame(" Data ");
		columnsBoardArray[0] = "Board only " + STREET_ST[EvalData.street];
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(200, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataData, columnsData);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);

		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
		int row = 0;
		if (EvalData.pair) {
			table.setValueAt("pair ", row, 0);
			++row;
		}
		if (EvalData.boardPair) {
			table.setValueAt("boardPair", row, 0);
			++row;
		}
		if (EvalData.gap0 != 0) {
			table.setValueAt("gap0", row, 0);
			table.setValueAt(Format.format(EvalData.gap0), row, 1);
			++row;
		}
		if (EvalData.gap1 != 0) {
			table.setValueAt("gap1", row, 0);
			table.setValueAt(Format.format(EvalData.gap1), row, 1);
			++row;
		}
		if (EvalData.gap2 != 0) {
			table.setValueAt("gap2", row, 0);
			table.setValueAt(Format.format(EvalData.gap2), row, 1);
			++row;
		}
		if (EvalData.gap0Score != 0) {
			table.setValueAt("gap0Score", row, 0);
			table.setValueAt(Format.format(EvalData.gap0Score), row, 1);
			++row;
		}
		if (EvalData.gap1Score != 1) {
			table.setValueAt("gap1Score", row, 0);
			table.setValueAt(Format.format(EvalData.gap1Score), row, 1);
			++row;
		}
		if (EvalData.gap2Score == 0) {
			return;
		}
		table.setValueAt("gap2Score", row, 0);
		table.setValueAt(Format.format(EvalData.gap2Score), row, 1);
		++row;
	}

	

	/*-
	 * Displays a report of shared data from board evaluation
	 * All data is in EvalData.boardPossibleArrayTurn
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportMadeHandsRiver(int c, int r) {
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);
		final var frame = new JFrame("Made hands");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(200, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataMadeHandsRiver, columnsMadeHandsRiver);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

		int row = 0;
		for (int i = 0; i < BOARD_ARRAY_ST.length; ++i) {
			if (EvalData.boardArray[i]) {
				table.setValueAt(BOARD_ARRAY_ST[i], row, 0);
				++row;
			}
		}
	}

	/*-
	 * Displays a report of shared data from board evaluation winners and loosers
	 * All data is in EvalData. 
	 * 
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 */
	static void reportShowdown(int c, int r) {
		if (!EvalData.showdown) {
			return;
		}
		JTable table = null;
		final var ff = new Font(Font.SERIF, Font.BOLD, 14);
		final var frame = new JFrame("Showdown ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(c, r);
		frame.setPreferredSize(new Dimension(200, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final var tableModel = new DefaultTableModel(dataShowdown, columnsShowdown);
		table = new JTable(tableModel);
		table.setFont(ff);
		table.setRowHeight(25);
		final var pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);

		int row = 0;
		for (int i = 0; i < PLAYERS; ++i) {
			if (EvalData.playerWonShowdown[i]) {
				table.setValueAt(String.valueOf(i), row, 0);
				table.setValueAt("Won", row, 1);
				++row;
			} else {
				table.setValueAt(String.valueOf(i), row, 0);
				table.setValueAt("Lost", row, 1);
				++row;
			}
		}
		table.setValueAt(EvalData.winnerSeat, row, 0);
		table.setValueAt("Winners seat", row, 1);
		++row;

		if (EvalData.maxValue == -1) {
			return;
		}
		table.setValueAt("maxValue ", row, 0);
		table.setValueAt(Format.format(EvalData.maxValue), row, 1);
	}

	public class OtherClass {
		public void someMethod() {
			// Get a reference to the ReportHandsFrame object
			final var frame = new ReportHandsFrame();
			// Call the closeFrame method to close the frame
			frame.closeFrame();
		}
	}

	public class ReportHandsFrame extends JFrame {
		// Declare the frame as an instance variable
		private final JFrame reportHandsFrame;

		public ReportHandsFrame() {
			// Create the frame
			reportHandsFrame = new JFrame("Report Hands");
			// Add components to the frame
			// ...
			// Display the frame
			reportHandsFrame.setVisible(true);
		}

		// Method to close the frame
		public void closeFrame() {
			reportHandsFrame.dispose();
		}
	}

	/*-
	 * Helper
	 */

}
