
//package peakholdemevaluator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
<<<<<<< HEAD
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
=======
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.*;
>>>>>>> ab8268c27b735166a13c143eac67dad58a78835e
/*-  ******************************************************************************
 * This Class
*  
* 
* @author PEAK_
 ****************************************************************************** */

public class IndexArrayReport implements Constants {

	// Constructor
	IndexArrayReport() {

	}

	/*- *****************************************************************************
	 * 
	*********************************************************************************/
	public class CustomRenderer extends DefaultTableCellRenderer {
		private Map<Point, Color> cellColors;

		public CustomRenderer() {
			this.cellColors = new HashMap<>();
		}

		public void setColorAt(int row, int col, Color color) {
			cellColors.put(new Point(row, col), color);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);

			Color cellColor = cellColors.get(new Point(row, column));
			if (cellColor != null) {
				cellComponent.setBackground(cellColor);
			} else {
				cellComponent.setBackground(table.getBackground());
			}

			return cellComponent;
		}
	}

	private static final int DRAW_COL = DRAW_SIZE;
	private static final int MADE_COL = MADE_FLUSH + 1;

	private JFrame frame1 = null;
	private JFrame frame2 = null;
	private JFrame frame3 = null;

	private JTable table1 = null;
	private JTable table2 = null;
	private JTable table3 = null;

	private DefaultTableModel tableModel1 = null;
	private DefaultTableModel tableModel2 = null;
	private DefaultTableModel tableModel3 = null;

	private JScrollPane pane1 = null;
	private JScrollPane pane2 = null;
	private JScrollPane pane3 = null;

	static Font ff1 = new Font(Font.SERIF, Font.BOLD, 14);

<<<<<<< HEAD
	private Object[] columnsDraw = { "Index", "No draw", "Gutshot", "Sttaight Draw", "OESD", "Flush Draw", "Total" };
=======
	private Object[] columnsDraw = { "Index", "No draw", "Gutshot",
			"Sttaight Draw", "OESD", "Flush Draw", "Total" };
>>>>>>> ab8268c27b735166a13c143eac67dad58a78835e

	private Object[][] dataDraw = { { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" } };

<<<<<<< HEAD
	private Object[] columnsMade = { "Index", "No hand", "Board Pair", "Bottom Pair", "Middle Pair", "Top Pair",
			"Over Pair", "Bottom 2Pair", "Middle 2Pair", "Top 2Pair", "Set", "Straight", "Flush", "Total" };
=======
	private Object[] columnsMade = { "Index", "No hand", "Board Pair", "Bottom Pair", "Middle Pair",
			"Top Pair", "Over Pair", "Bottom 2Pair", "Middle 2Pair", "Top 2Pair",
			"Set", "Straight", "Flush", "Total" };
>>>>>>> ab8268c27b735166a13c143eac67dad58a78835e

	private Object[][] dataMade = {
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" } };

<<<<<<< HEAD
	private Object[] columnsShowdown = { "Index", "No hand", "Board Pair", "Bottom Pair", "Middle Pair", "Top Pair",
			"Over Pair", "Bottom 2Pair", "Middle 2Pair", "Top 2Pair", "Set", "Straight", "Flush", "Total" };
=======
	private Object[] columnsShowdown = { "Index", "No hand", "Board Pair", "Bottom Pair", "Middle Pair",
			"Top Pair", "Over Pair", "Bottom 2Pair", "Middle 2Pair", "Top 2Pair",
			"Set", "Straight", "Flush", "Total" };
>>>>>>> ab8268c27b735166a13c143eac67dad58a78835e

	private Object[][] dataShowdown = {
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" } };

<<<<<<< HEAD
	private Object[] columnsDraw2 = { "Index", "No draw", "Gutshot", "Sttaight Draw", "OESD", "Flush Draw", "Total" };

	private Object[][] dataDraw2 = { { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
=======
	private Object[] columnsDraw2 = { "Index", "No draw", "Gutshot",
			"Sttaight Draw", "OESD", "Flush Draw", "Total" };

	private Object[][] dataDraw2 = { { "", "", "", "", "", "", "", "", "" },
>>>>>>> ab8268c27b735166a13c143eac67dad58a78835e
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
<<<<<<< HEAD
			{ "", "", "", "", "", "", "", "", "" } };

	private Object[] columnsMade2 = { "Index", "No hand", "Board Pair", "Bottom Pair", "Middle Pair", "Top Pair",
			"Over Pair", "Bottom 2Pair", "Middle 2Pair", "Top 2Pair", "Set", "Straight", "Flush", "Total" };
=======
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" } };

	private Object[] columnsMade2 = { "Index", "No hand", "Board Pair", "Bottom Pair", "Middle Pair",
			"Top Pair", "Over Pair", "Bottom 2Pair", "Middle 2Pair", "Top 2Pair",
			"Set", "Straight", "Flush", "Total" };
>>>>>>> ab8268c27b735166a13c143eac67dad58a78835e

	private Object[][] dataMade2 = {
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" } };

<<<<<<< HEAD
	private Object[] columnsShowdown2 = { "Index", "No hand", "Board Pair", "Bottom Pair", "Middle Pair", "Top Pair",
			"Over Pair", "Bottom 2Pair", "Middle 2Pair", "Top 2Pair", "Set", "Straight", "Flush", "Total" };
=======
	private Object[] columnsShowdown2 = { "Index", "No hand", "Board Pair", "Bottom Pair", "Middle Pair",
			"Top Pair", "Over Pair", "Bottom 2Pair", "Middle 2Pair", "Top 2Pair",
			"Set", "Straight", "Flush", "Total" };
>>>>>>> ab8268c27b735166a13c143eac67dad58a78835e

	private Object[][] dataShowdown2 = {
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" } };

	/*-*************************************************************************************************************
	 * This method does the following:
	 * 
	 * 1. It checks if the JFrame is null. If so, it creates a new JFrame, sets its properties,
	 *    and creates a JScrollPane.
	 * 2. It initializes a PriorityQueue to hold the cells with the five highest integer values.
	 *    These cells are determined by parsing the string value of each cell (excluding column 0) to an integer.
	 * 3. It iterates over the data array (excluding column 0), checking each cell. If the PriorityQueue is not full, 
	 *    it adds the cell to the queue. If the PriorityQueue is full, but the current cell has 
	 *    a higher value than the smallest value in the queue, it removes the smallest cell and 
	 *    adds the current cell.
	 * 4. It creates a JTable, passing in the data and column arrays. It overrides the prepareRenderer method 
	 *    of the JTable to highlight cells (excluding cells in column 0) that are one of the five highest values.
	 * 5. Finally, it adds the JTable to the JScrollPane, adds the JScrollPane to the JFrame, and displays the JFrame.
	 *
	 * Arg0 - Column for frame
	 * Arg1 - Row for frame
	 * arg2 - Title of the frame
	 * Arg3 - IndexArray object
	****************************************************************************************************************/
	void reportDraw(int c, int r, String title, IndexArrayDrawMadeWin arrays) {
		int rr = 0;
		int cc = 0;
		int[] l = new int[3];
		int w = 0;
		if (frame1 == null) {
			frame1 = new JFrame(title);
			frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame1.setLocation(c, r);
			int x = (arrays.drawArray.length * 30) + 70;
			frame1.setPreferredSize(new Dimension(750, x));
		}

		tableModel1 = new DefaultTableModel(dataDraw, columnsDraw);
		if (tableModel1.getRowCount() > 0) {
			for (int i = tableModel1.getRowCount() - 1; i > arrays.allArrayRowNames.length; i--) {
				tableModel1.removeRow(i);
			}
		}
		table1 = new JTable(tableModel1);
		table1.setFont(ff1);
		table1.setRowHeight(25);
		for (int i = 0; i < arrays.allArrayRowNames.length; ++i) {
			if (arrays.allArrayRowNames[i].length() > w) {
				w = arrays.allArrayRowNames[i].length();
			}
		}
<<<<<<< HEAD
		w = (w * 10);
=======
		w = (w * 10)  ;
>>>>>>> ab8268c27b735166a13c143eac67dad58a78835e
		table1.getColumnModel().getColumn(0).setPreferredWidth(w);
		int row = 0;
		int col = 0;
		for (int i = 0; i < arrays.allArrayRowNames.length; ++i) {
			table1.setValueAt(arrays.allArrayRowNames[i], row, 0);
			col = 1;
			for (int j = 0; j < DRAW_COL; ++j) {
				if (arrays.drawArray[i][j] >= 0) {
					table1.setValueAt(Format.formatPer(arrays.drawArrayPer[i][j]), row, col);
				}
				table1.setValueAt(Format.formatPer(arrays.drawColTotalPer[j]), arrays.drawArray.length, col);
				col++;
			}
			table1.setValueAt(Format.formatPer(arrays.drawRowTotalPer[row]), row, col);
			col = 1;
			++row;
		}

		CustomRenderer customRendererDraw = new CustomRenderer();
		for (int i = 0; i < arrays.bestDraw5Row.length; ++i) {
			rr = arrays.bestDraw5Row[i];
			cc = arrays.bestDraw5Col[i] + 1;
			customRendererDraw.setColorAt(rr, cc, Color.GREEN);
			table1.setDefaultRenderer(Object.class, customRendererDraw);
		}
		for (int i = 0; i < arrays.worstDraw5Row.length; ++i) {
			rr = arrays.worstDraw5Row[i];
			cc = arrays.worstDraw5Col[i] + 1;
			customRendererDraw.setColorAt(rr, cc, Color.RED);
			table1.setDefaultRenderer(Object.class, customRendererDraw);
		}
		for (int i = 0; i < arrays.bestDrawRows.length; ++i) {
			rr = arrays.bestDrawRows[i];
			cc = DRAW_COL + 1;
			customRendererDraw.setColorAt(rr, cc, Color.GREEN);
			table1.setDefaultRenderer(Object.class, customRendererDraw);
		}
		for (int i = 0; i < arrays.bestDrawCols.length; ++i) {
			rr = row;
			cc = arrays.bestDrawCols[i] + 1;
			customRendererDraw.setColorAt(rr, cc, Color.GREEN);
			table1.setDefaultRenderer(Object.class, customRendererDraw);
		}

		pane1 = new JScrollPane(table1);
		frame1.add(pane1);
		frame1.pack();
		frame1.setVisible(true);
	}

	/*-**********************************************************************************
	 * Same as above but for Made hands
	 ********************************************************************************** */
	void reportMade(int c, int r, String title, IndexArrayDrawMadeWin arrays) {
		int rr = 0;
		int cc = 0;
		int[] l = new int[3];
		int w = 0;
		if (frame2 == null) {
			frame2 = new JFrame(title);
			frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame2.setLocation(c, r);
			int x = (arrays.madeArray.length * 30) + 70;
			frame2.setPreferredSize(new Dimension(1300, x));
		}

		tableModel2 = new DefaultTableModel(dataMade, columnsMade);
		for (int i = 0; i < arrays.allArrayRowNames.length; ++i) {
			if (arrays.allArrayRowNames[i].length() > w) {
				w = arrays.allArrayRowNames[i].length();
			}
		}
<<<<<<< HEAD
		w = (w * 10);
=======
		w = (w * 10)  ;
>>>>>>> ab8268c27b735166a13c143eac67dad58a78835e

		table2 = new JTable(tableModel2);
		table2.setFont(ff1);
		table2.setRowHeight(25);
		table2.getColumnModel().getColumn(0).setPreferredWidth(w);
		if (tableModel2.getRowCount() > 0) {
			for (int i = tableModel2.getRowCount() - 1; i > arrays.allArrayRowNames.length; i--) {
				tableModel2.removeRow(i);
			}
		}
		int row = 0;
		int col = 0;
		for (int i = 0; i < arrays.allArrayRowNames.length; ++i) {
			table2.setValueAt(arrays.allArrayRowNames[i], row, 0);
			col = 1;
			for (int j = 0; j < MADE_COL; ++j) {
				if (arrays.madeArray[i][j] >= 0) {
					table2.setValueAt(Format.formatPer(arrays.madeArrayPer[i][j]), row, col);
				}
				table2.setValueAt(Format.formatPer(arrays.madeColTotalPer[j]), arrays.madeArray.length, col);
				++col;
			}
			table2.setValueAt(Format.formatPer(arrays.madeRowTotalPer[row]), row, col);
			col = 1;
			++row;
		}
		CustomRenderer customRendererMade = new CustomRenderer();
		for (int i = 0; i < arrays.bestMade5Row.length; ++i) {
			rr = arrays.bestMade5Row[i];
			cc = arrays.bestMade5Col[i] + 1;
			customRendererMade.setColorAt(rr, cc, Color.GREEN);
			table2.setDefaultRenderer(Object.class, customRendererMade);
		}
		for (int i = 0; i < arrays.worstMade5Row.length; ++i) {
			rr = arrays.worstMade5Row[i];
			cc = arrays.worstMade5Col[i] + 1;
			customRendererMade.setColorAt(rr, cc, Color.RED);
			table2.setDefaultRenderer(Object.class, customRendererMade);
		}
		for (int i = 0; i < arrays.bestMadeRows.length; ++i) {
			rr = arrays.bestMadeRows[i];
			cc = DRAW_COL + 1;
			customRendererMade.setColorAt(rr, cc, Color.GREEN);
			table2.setDefaultRenderer(Object.class, customRendererMade);
		}
		for (int i = 0; i < arrays.bestMadeCols.length; ++i) {
			rr = row;
			cc = arrays.bestMadeCols[i] + 1;
			customRendererMade.setColorAt(rr, cc, Color.GREEN);
			table2.setDefaultRenderer(Object.class, customRendererMade);
		}
		pane2 = new JScrollPane(table2);
		frame2.add(pane2);
		frame2.pack();
		frame2.setVisible(true);
	}

	/*-**********************************************************************************
	 * Same as above but for Made hands
	 ********************************************************************************** */
	void reportShowdown(int c, int r, String title, IndexArrayDrawMadeWin arrays) {
		int rr = 0;
		int cc = 0;
		int[] l = new int[3];
		int w = 0;
		if (frame3 == null) {
			frame3 = new JFrame(title);
			frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame3.setLocation(c, r);
			int x = (arrays.showdownArray.length * 30) + 70;
			frame3.setPreferredSize(new Dimension(1300, x));
		}

		tableModel3 = new DefaultTableModel(dataShowdown, columnsShowdown);
		for (int i = 0; i < arrays.allArrayRowNames.length; ++i) {
			if (arrays.allArrayRowNames[i].length() > w) {
				w = arrays.allArrayRowNames[i].length();
			}
		}
<<<<<<< HEAD
		w = (w * 10);
=======
		w = (w * 10) ;
>>>>>>> ab8268c27b735166a13c143eac67dad58a78835e

		table3 = new JTable(tableModel3);
		table3.setFont(ff1);
		table3.setRowHeight(25);
		table3.getColumnModel().getColumn(0).setPreferredWidth(w);
		if (tableModel3.getRowCount() > 0) {
			for (int i = tableModel3.getRowCount() - 1; i > arrays.allArrayRowNames.length; i--) {
				tableModel3.removeRow(i);
			}
		}
		int row = 0;
		int col = 0;
		for (int i = 0; i < arrays.showdownArray.length; ++i) {
			table3.setValueAt(arrays.allArrayRowNames[i], row, 0);
			col = 1;
			for (int j = 0; j < MADE_COL; ++j) {
				if (arrays.showdownArray[i][j] >= 0) {
					table3.setValueAt(Format.formatPer(arrays.showdownArrayPer[i][j]), row, col);
				}
				table3.setValueAt(Format.formatPer(arrays.showdownColTotalPer[j]), arrays.showdownArray.length, col);
				col++;
			}
			table3.setValueAt(Format.formatPer(arrays.showdownRowTotalPer[row]), row, col);
			col = 1;
			++row;
		}
		CustomRenderer customRendererShowdown = new CustomRenderer();
		for (int i = 0; i < arrays.bestShowdown5Row.length; ++i) {
			rr = arrays.bestShowdown5Row[i];
			cc = arrays.bestShowdown5Col[i] + 1;
			customRendererShowdown.setColorAt(rr, cc, Color.GREEN);
			table3.setDefaultRenderer(Object.class, customRendererShowdown);
		}
		for (int i = 0; i < arrays.worstShowdown5Row.length; ++i) {
			rr = arrays.worstShowdown5Row[i];
			cc = arrays.worstShowdown5Col[i] + 1;
			customRendererShowdown.setColorAt(rr, cc, Color.RED);
			table3.setDefaultRenderer(Object.class, customRendererShowdown);
		}
		for (int i = 0; i < arrays.bestShowdownRows.length; ++i) {
			rr = arrays.bestShowdownRows[i];
			cc = DRAW_COL + 1;
			customRendererShowdown.setColorAt(rr, cc, Color.GREEN);
			table3.setDefaultRenderer(Object.class, customRendererShowdown);
		}
		for (int i = 0; i < arrays.bestShowdownCols.length; ++i) {
			rr = row;
			cc = arrays.bestShowdownCols[i] + 1;
			customRendererShowdown.setColorAt(rr, cc, Color.GREEN);
			table3.setDefaultRenderer(Object.class, customRendererShowdown);
		}
		pane3 = new JScrollPane(table3);
		frame3.add(pane3);
		frame3.pack();
		frame3.setVisible(true);
	}

}
