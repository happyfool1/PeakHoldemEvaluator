
//package evaluate_streets;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

import java.awt.*;
import java.util.PriorityQueue;
import java.util.Comparator;
import javax.swing.table.*;
/*-  ******************************************************************************
 * This Class
*  
* 
* @author PEAK_
 ****************************************************************************** */

public class IndexArrayReport implements Constants {

	private IndexArrayReport() {
		throw new IllegalStateException("Utility class");
	}

	private static final int DRAW_COL = DRAW_SIZE;
	private static final int DRAW_TOTAL_COL = DRAW_SIZE + 1;

	private static final int MADE_COL = MADE_FLUSH;
	private static final int MADE_TOTAL_COL = MADE_FLUSH + 2;

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

	private Object[] columnsDraw = { "Index", "No draw", "Gutshot",
			"Straight", "OESD", "Flush", "Flush OESD", "Total" };

	private Object[][] dataDraw = { { "", "", "", "", "", "", "", "", "" },
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
			{ "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" } };

	private Object[] columnsMade = { "Index", "No hand", "Brd Pair", "Bottom Pair", "Middle Pair",
			"Top Pair", "Over Pair", "Brd 2Pair", "2Pair & Under", "2Pair & Over", "2Pair Bottom",
			"2Pair Top-Bot", "2Pair Top 2", "Brd Set", "Set", "Straight", "Flush",
			"Total" };

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

	private Object[] columnsShowdown = { "Index", "No hand", "Brd Pair", "Bottom Pair", "Middle Pair",
			"Top Pair", "Over Pair", "Brd 2Pair", "2Pair & Under", "2Pair & Over", "2Pair Bottom",
			"2Pair Top-Bot", "2Pair Top 2", "Brd Set", "Set", "Straight", "Flush", "Total" };

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
	****************************************************************************************************************/
	void reportDraw(int c, int r, String title, IndexArray arrays) {
		if (frame1 == null) {
			frame1 = new JFrame(title);
			frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame1.setLocation(c, r);
			frame1.setPreferredSize(new Dimension(750, 330));
		}

		// Create a PriorityQueue to hold the cells with the top 5 values
		PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
		PriorityQueue<Cell> smallestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));

		tableModel1 = new DefaultTableModel(dataDraw, columnsDraw);
		table1 = new JTable(tableModel1);
		table1.setFont(ff1);
		table1.setRowHeight(25);

		int row = 0;
		int col = 0;
		for (int i = 0; i < arrays.drawArray.length; ++i) {
			table1.setValueAt(arrays.rowNames[i], row, 0);
			col = 1;

			for (int j = 0; j < DRAW_COL; ++j) {
				if (arrays.drawArray[i][j] > 0) {
					table1.setValueAt(Format.formatPer(arrays.drawArrayPer[i][j]), row, col);
					String cellValueStr = "   " + String.valueOf(arrays.madeArray[i][j]);
					// Parse the cell value to an integer and add to the priority queue
					if (col > 1) {
						try {
							int cellValue = Integer.parseInt(cellValueStr.trim());
							// For smallest cells
							if (smallestCells.size() < 5) {
								smallestCells.offer(new Cell(row, col, cellValue));
							} else if (cellValue < smallestCells.peek().value) {
								smallestCells.poll();
								smallestCells.offer(new Cell(row, col, cellValue));
							}
							// For largest cells
							if (largestCells.size() < 5) {
								largestCells.offer(new Cell(row, col, cellValue));
							} else if (cellValue > largestCells.peek().value) {
								largestCells.poll();
								largestCells.offer(new Cell(row, col, cellValue));
							}
						} catch (NumberFormatException e) {
							// Handle or ignore if cell doesn't contain a valid integer
						}
					}
				}
				col++;
			}
			table1.setValueAt(Format.formatPer(arrays.drawRowTotalPer[i]), row, col);
			col = 1;

			++row;
		}
		// Add custom cell renderer
		table1.setDefaultRenderer(Object.class, new TableCellRenderer() {
			private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				if (column != 0 && containsCell(largestCells, row, column)) {
					c.setBackground(Color.GREEN);
				} else if (column != 0 && containsCell(smallestCells, row, column)) {
					c.setBackground(Color.RED);
				} else {
					c.setBackground(Color.CYAN);
				}
				return c;
			}
		});

		pane1 = new JScrollPane(table1);
		frame1.add(pane1);
		frame1.pack();
		frame1.setVisible(true);
	}

	/*-**********************************************************************************
	 * Same as above but for Made hands
	 ********************************************************************************** */
	void reportMade(int c, int r, String title, IndexArray arrays) {
		if (frame2 == null) {
			frame2 = new JFrame(title);
			frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame2.setLocation(c, r);
			frame2.setPreferredSize(new Dimension(1500, 330));
		}

		// Create a PriorityQueue to hold the cells with the top 5 values
		PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
		PriorityQueue<Cell> smallestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
		tableModel2 = new DefaultTableModel(dataMade, columnsMade);
		table2 = new JTable(tableModel2);
		table2.setFont(ff1);
		table2.setRowHeight(25);
		int row = 0;
		int col = 0;
		for (int i = 0; i < arrays.madeArray.length; ++i) {
			table2.setValueAt(arrays.rowNames[i], row, 0);
			col = 1;
					for (int j = 0; j < MADE_COL; ++j) {
				if (arrays.madeArray[i][j] > 0) {
			 
					table2.setValueAt(Format.formatPer(per), row, col);
				
					String cellValueStr = "   " + String.valueOf(IndexArrays.hmlMade[i][j]);
					// Parse the cell value to an integer and add to the priority queue
					if (col > 1) {
						try {
							int cellValue = Integer.parseInt(cellValueStr.trim());
							// For smallest cells
							if (smallestCells.size() < 5) {
								smallestCells.offer(new Cell(row, col, cellValue));
							} else if (cellValue < smallestCells.peek().value) {
								smallestCells.poll();
								smallestCells.offer(new Cell(row, col, cellValue));
							}
							// For largest cells
							if (largestCells.size() < 5) {
								largestCells.offer(new Cell(row, col, cellValue));
							} else if (cellValue > largestCells.peek().value) {
								largestCells.poll();
								largestCells.offer(new Cell(row, col, cellValue));
							}
						} catch (NumberFormatException e) {
							// Handle or ignore if cell doesn't contain a valid integer
						}
					}
				}
				col++;
			}
			table2.setValueAt(Format.formatPer(arrays.madeRowTotalPer[i]), row, col);
			col = 1;
			++row;
		}
		// Add custom cell renderer
		table2.setDefaultRenderer(Object.class, new TableCellRenderer() {
			private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				if (column != 0 && containsCell(largestCells, row, column)) {
					c.setBackground(Color.GREEN);
				} else if (column != 0 && containsCell(smallestCells, row, column)) {
					c.setBackground(Color.RED);
				} else {
					c.setBackground(Color.CYAN);
				}
				return c;
			}
		});

		pane2 = new JScrollPane(table2);
		frame2.add(pane2);
		frame2.pack();
		frame2.setVisible(true);
	}

	/*-**********************************************************************************
	 * Same as above but for Made hands
	 ********************************************************************************** */
	void reportShowdown(int c, int r, String title, IndexArray arrays) {
		if (frame3 == null) {
			frame3 = new JFrame(title);
			frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame3.setLocation(c, r);
			frame3.setPreferredSize(new Dimension(1300, 330));
		}

		// Create a PriorityQueue to hold the cells with the top 5 values
		PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
		PriorityQueue<Cell> smallestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
		tableModel3 = new DefaultTableModel(dataShowdown, columnsShowdown);
		table3 = new JTable(tableModel3);
		table3.setFont(ff1);
		table3.setRowHeight(25);
		double sumx = 0.0;
		double sum = 0.0;
		int row = 0;
		int col = 0;
		for (int i = 0; i < HML_FLOP_SIZE; ++i) {
			table3.setValueAt(HML_FLOP_ST[i], row, 0);
			col = 1;
			sum = 0.0;

			for (int j = 0; j < MADE_COL; ++j) {
				if (IndexArrays.hmlShowdown[i][j] > 0) {
					double per = ((double) IndexArrays.hmlShowdown[i][j] / (double) IndexArrays.showdownCount)
							* 100.0;
					sum += per;
					sumx += per;
					table3.setValueAt(Format.formatPer(per), row, col);
					table3.setValueAt(Format.formatPer(sum), row, MADE_TOTAL_COL);
					String cellValueStr = "   " + String.valueOf(IndexArrays.hmlShowdown[i][j]);
					// Parse the cell value to an integer and add to the priority queue

					if (col > 1) {
						try {
							int cellValue = Integer.parseInt(cellValueStr.trim());
							// For smallest cells
							if (smallestCells.size() < 5) {
								smallestCells.offer(new Cell(row, col, cellValue));
							} else if (cellValue < smallestCells.peek().value) {
								smallestCells.poll();
								smallestCells.offer(new Cell(row, col, cellValue));
							}
							// For largest cells
							if (largestCells.size() < 5) {
								largestCells.offer(new Cell(row, col, cellValue));
							} else if (cellValue > largestCells.peek().value) {
								largestCells.poll();
								largestCells.offer(new Cell(row, col, cellValue));
							}
						} catch (NumberFormatException e) {
							// Handle or ignore if cell doesn't contain a valid integer
						}
					}
				}
				col++;
			}
			col = 1;
			++row;
		}

		// Add custom cell renderer
		table3.setDefaultRenderer(Object.class, new TableCellRenderer() {
			private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				if (column != 0 && containsCell(largestCells, row, column)) {
					c.setBackground(Color.GREEN);
				} else if (column != 0 && containsCell(smallestCells, row, column)) {
					c.setBackground(Color.RED);
				} else {
					c.setBackground(Color.CYAN);
				}
				return c;
			}
		});

		pane3 = new JScrollPane(table3);
		frame3.add(pane3);
		frame3.pack();
		frame3.setVisible(true);
	}

	/*-**********************************************************************************
	* Helper method
	********************************************************************************** */
	private boolean containsCell(PriorityQueue<Cell> cells, int row, int column) {
		for (Cell cell : cells) {
			if (cell.row == row && cell.column == column) {
				return true;
			}
		}
		return false;
	}

	/*-**********************************************************************************
	* Helper method
	********************************************************************************** */
	class Cell {
		int row;
		int column;
		int value;

		public Cell(int row, int column, int value) {
			this.row = row;
			this.column = column;
			this.value = value;
		}
	}

}