//package evaluate_streets;
/*-  *********************************************************************************************
 * This Class displays reports of HML related data.
 * 
 * All methods have 2 arguments, the column and the row where the frame is 
 * to be displayed.
 * 
 * @author PEAK_
  **********************************************************************************************/

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableCellRenderer;
import java.util.PriorityQueue;
import java.util.Comparator;
import javax.swing.table.*;
import java.util.*;
import javax.swing.*;


public class ReportHML implements Constants {

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

    private static final Object[] columnsHMLDrawFlop = { "HML", "No draw", "Gutshot",
            "Straight", "OESD", "Flush", "Flush OESD" };

    private static final Object[][] dataHMLDrawFlop = { { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" },   { "", "", "", "", "", "", "", "", "" },
             { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" } };

    private static final Object[] columnsHMLMadeFlop = { "HML", "No hand", "Any pair", "Two pair", "Set", "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHMLMadeFlop = { { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "", "" } };


            private static final Object[] columnsHMLShowdownFlop = { "HML", "No hand", "Any pair", "Two pair", "Set",
            "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHMLShowdownFlop = { { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
             { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "", "" } };






    private static final Object[] columnsHMLDrawRiver= { "HML", "No draw", "Gutshot", "Straight", "OESD", "Flush",
            "Flush OESD" };

    private static final Object[][] dataHMLDrawRiver = { { "", "", "", "", "", "", "", "", "" },
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
                  { "", "", "", "", "", "", "", "", "" } };

    private static final Object[] columnsHMLMadeRiver = { "HML", "No hand", "Any pair", "Two pair", "Set", "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHMLMadeRiver= { { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
              { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "", "" } };


            private static final Object[] columnsHMLShowdownRiver = { "HML", "No hand", "Any pair", "Two pair", "Set",
            "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHMLShowdownRiver = { { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
             { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "", "" } };




            private static final Object[] columnsHMLDrawTurn= { "HML", "No draw", "Gutshot", "Straight", "OESD", "Flush",
            "Flush OESD" };

    private static final Object[][] dataHMLDrawTurn = { { "", "", "", "", "", "", "", "", "" },
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
                  { "", "", "", "", "", "", "", "", "" } };

    private static final Object[] columnsHMLMadeTurn = { "HML", "No hand", "Any pair", "Two pair", "Set", "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHMLMadeTurn= { { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
              { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "", "" } };


            private static final Object[] columnsHMLShowdownTurn = { "HML", "No hand", "Any pair", "Two pair", "Set",
            "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHMLShowdownTurn = { { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
             { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "", "" } };



    static boolean reportHandsDone = false;

    private ReportHML() {
        throw new IllegalStateException("Utility class");
    }




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
    static void reportHMLDrawFlop(int c, int r) {
        if (frame1 == null) {
            frame1 = new JFrame("Flop HML Drawing hands");
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.setLocation(c, r);
            frame1.setPreferredSize(new Dimension(750, 330));
        }

        // Create a PriorityQueue to hold the cells with the top 5 values
        PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));

        tableModel1 = new DefaultTableModel(dataHMLDrawFlop, columnsHMLDrawFlop);
        table1 = new JTable(tableModel1);
        table1.setFont(ff1);
        table1.setRowHeight(25);

        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table1.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < DRAW_HANDS_SIZE; ++j) {
                if (ManyData.hmlDraw[i][j] > 0) {
                     double per = ((double) ManyData.hmlDraw[i][j] / (double) ManyData.hmlCount) * 100.0;
                    table1.setValueAt(Format.formatPer(per), row  , col);
                
                    String cellValueStr = "   " + String.valueOf(ManyData.hmlDraw[i][j]);
              
                    // Parse the cell value to an integer and add to the priority queue
                    try {
                        int cellValue = Integer.parseInt(cellValueStr.trim());
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
                col++;
            }
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
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(Color.WHITE);
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
     static void reportHMLMadeFlop(int c, int r) {
        if (frame2 == null) {
            frame2 = new JFrame("Flop HML Made hands");
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.setLocation(c, r);
            frame2.setPreferredSize(new Dimension(750, 330));
        }

        // Create a PriorityQueue to hold the cells with the top 5 values
        PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));

        tableModel2 = new DefaultTableModel(dataHMLMadeFlop, columnsHMLMadeFlop);
        table2 = new JTable(tableModel2);
        table2.setFont(ff1);
        table2.setRowHeight(25);

        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table2.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
                if (ManyData.hmlMade[i][j] > 0) {
                     double per = ((double) ManyData.hmlMade[i][j] / (double) ManyData.hmlCount) * 200.0;
                    table2.setValueAt(Format.formatPer(per), row  , col);
                
                    String cellValueStr = "   " + String.valueOf(ManyData.hmlMade[i][j]);
              
                    // Parse the cell value to an integer and add to the priority queue
                    try {
                        int cellValue = Integer.parseInt(cellValueStr.trim());
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
                col++;
            }
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
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(Color.WHITE);
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
    static void reportHMLShowdownFlop(int c, int r) {
        if (frame3 == null) {
            frame3 = new JFrame("Showdown Flop HML Winner hands");
            frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame3.setLocation(c, r);
            frame3.setPreferredSize(new Dimension(750, 600));
        }

        // Create a PriorityQueue to hold the cells with the top 5 values
        PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));

        tableModel3 = new DefaultTableModel(dataHMLShowdownFlop, columnsHMLShowdownFlop);
        table3 = new JTable(tableModel3);
        table3.setFont(ff1);
        table3.setRowHeight(25);

        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table3.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
                if (ManyData.hmlShowdownWon[i][j] > 0) {
                    double per = ((double) ManyData.hmlShowdownWon[i][j] / (double) ManyData.showdownCount) * 30.0;
                    table3.setValueAt(Format.formatPer(per), row , col);
                    String cellValueStr = "   " + String.valueOf(ManyData.hmlShowdownWon[i][j]);
                   // table3.setValueAt(cellValueStr, row, col);

                    // Parse the cell value to an integer and add to the priority queue
                    try {
                        int cellValue = Integer.parseInt(cellValueStr.trim());
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
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(Color.WHITE);
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
 * Same as above but for Made hands
 ********************************************************************************** */
  static void reportHMLDrawTurn(int c, int r) {
        if (frame4 == null) {
            frame4 = new JFrame("Turn HML Drawing hands");
            frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame4.setLocation(c, r);
            frame4.setPreferredSize(new Dimension(750, 330));
        }

        // Create a PriorityQueue to hold the cells with the top 5 values
        PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));

        tableModel4 = new DefaultTableModel(dataHMLDrawTurn, columnsHMLDrawTurn);
        table4 = new JTable(tableModel4);
        table4.setFont(ff1);
        table4.setRowHeight(25);

        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table4.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < DRAW_HANDS_SIZE; ++j) {
                if (ManyData.hmlDraw[i][j] > 0) {
                     double per = ((double) ManyData.hmlDraw[i][j] / (double) ManyData.hmlCount) * 400.0;
                    table4.setValueAt(Format.formatPer(per), row  , col);
                
                    String cellValueStr = "   " + String.valueOf(ManyData.hmlDraw[i][j]);
              
                    // Parse the cell value to an integer and add to the priority queue
                    try {
                        int cellValue = Integer.parseInt(cellValueStr.trim());
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
                col++;
            }
            col = 1;
            ++row;
        }

        // Add custom cell renderer
        table4.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);

                if (column != 0 && containsCell(largestCells, row, column)) {
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

        pane4 = new JScrollPane(table4);
        frame4.add(pane4);
        frame4.pack();
        frame4.setVisible(true);
    }


/*-**********************************************************************************
 * Same as above but for Made hands
 ********************************************************************************** */
     static void reportHMLMadeTurn(int c, int r) {
        if (frame5 == null) {
            frame5 = new JFrame("Turn HML Made hands");
            frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame5.setLocation(c, r);
            frame5.setPreferredSize(new Dimension(750, 330));
        }

        // Create a PriorityQueue to hold the cells with the top 5 values
        PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));

        tableModel5 = new DefaultTableModel(dataHMLMadeTurn, columnsHMLMadeTurn);
        table5 = new JTable(tableModel5);
        table5.setFont(ff1);
        table5.setRowHeight(25);

        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table5.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
                if (ManyData.hmlMade[i][j] > 0) {
                     double per = ((double) ManyData.hmlMade[i][j] / (double) ManyData.hmlCount) * 500.0;
                    table5.setValueAt(Format.formatPer(per), row  , col);
                
                    String cellValueStr = "   " + String.valueOf(ManyData.hmlMade[i][j]);
              
                    // Parse the cell value to an integer and add to the priority queue
                    try {
                        int cellValue = Integer.parseInt(cellValueStr.trim());
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
                col++;
            }
            col = 1;
            ++row;
        }

        // Add custom cell renderer
        table5.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);

                if (column != 0 && containsCell(largestCells, row, column)) {
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

        pane5 = new JScrollPane(table5);
        frame5.add(pane5);
        frame5.pack();
        frame5.setVisible(true);
    }

/*-**********************************************************************************
 * Same as above but for Made hands
 ********************************************************************************** */
    static void reportHMLShowdownTurn(int c, int r) {
        if (frame6 == null) {
            frame6 = new JFrame("Showdown Turn HML Winner hands");
            frame6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame6.setLocation(c, r);
            frame6.setPreferredSize(new Dimension(750, 600));
        }

        // Create a PriorityQueue to hold the cells with the top 5 values
        PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));

        tableModel6 = new DefaultTableModel(dataHMLShowdownTurn, columnsHMLShowdownTurn);
        table6 = new JTable(tableModel6);
        table6.setFont(ff1);
        table6.setRowHeight(25);

        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table6.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
                if (ManyData.hmlShowdownWon[i][j] > 0) {
                    double per = ((double) ManyData.hmlShowdownWon[i][j] / (double) ManyData.showdownCount) * 60.0;
                    table6.setValueAt(Format.formatPer(per), row , col);
                    String cellValueStr = "   " + String.valueOf(ManyData.hmlShowdownWon[i][j]);
                   // table6.setValueAt(cellValueStr, row, col);

                    // Parse the cell value to an integer and add to the priority queue
                    try {
                        int cellValue = Integer.parseInt(cellValueStr.trim());
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
                col++;
            }
            col = 1;
            ++row;
        }

        // Add custom cell renderer
        table6.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);

                if (column != 0 && containsCell(largestCells, row, column)) {
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

        pane6 = new JScrollPane(table6);
        frame6.add(pane6);
        frame6.pack();
        frame6.setVisible(true);
    }





/*-**********************************************************************************
* Same as above but for Made hands
********************************************************************************** */
 static void reportHMLMadeRiver(int c, int r) {
    if (frame5 == null) {
        frame5 = new JFrame("River HML Made hands");
        frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame5.setLocation(c, r);
        frame5.setPreferredSize(new Dimension(750, 330));
    }

    // Create a PriorityQueue to hold the cells with the top 5 values
    PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));

    tableModel5 = new DefaultTableModel(dataHMLMadeRiver, columnsHMLMadeRiver);
    table5 = new JTable(tableModel5);
    table5.setFont(ff1);
    table5.setRowHeight(25);

    int row = 0;
    int col = 0;
    for (int i = 0; i < HML_FLOP_SIZE; ++i) {
        table5.setValueAt(HML_FLOP_ST[i], row, 0);
        col = 1;
        for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
            if (ManyData.hmlMade[i][j] > 0) {
                 double per = ((double) ManyData.hmlMade[i][j] / (double) ManyData.hmlCount) * 500.0;
                table5.setValueAt(Format.formatPer(per), row  , col);
            
                String cellValueStr = "   " + String.valueOf(ManyData.hmlMade[i][j]);
          
                // Parse the cell value to an integer and add to the priority queue
                try {
                    int cellValue = Integer.parseInt(cellValueStr.trim());
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
            col++;
        }
        col = 1;
        ++row;
    }

    // Add custom cell renderer
    table5.setDefaultRenderer(Object.class, new TableCellRenderer() {
        private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                    row, column);

            if (column != 0 && containsCell(largestCells, row, column)) {
                c.setBackground(Color.RED);
            } else {
                c.setBackground(Color.WHITE);
            }
            return c;
        }
    });

    pane5 = new JScrollPane(table5);
    frame5.add(pane5);
    frame5.pack();
    frame5.setVisible(true);
}

/*-**********************************************************************************
* Same as above but for Made hands
********************************************************************************** */
static void reportHMLShowdownRiver(int c, int r) {
    if (frame6 == null) {
        frame6 = new JFrame("Showdown River HML Winner hands");
        frame6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame6.setLocation(c, r);
        frame6.setPreferredSize(new Dimension(750, 600));
    }

    // Create a PriorityQueue to hold the cells with the top 5 values
    PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));

    tableModel6 = new DefaultTableModel(dataHMLShowdownRiver, columnsHMLShowdownRiver);
    table6 = new JTable(tableModel6);
    table6.setFont(ff1);
    table6.setRowHeight(25);

    int row = 0;
    int col = 0;
    for (int i = 0; i < HML_FLOP_SIZE; ++i) {
        table6.setValueAt(HML_FLOP_ST[i], row, 0);
        col = 1;
        for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
            if (ManyData.hmlShowdownWon[i][j] > 0) {
                double per = ((double) ManyData.hmlShowdownWon[i][j] / (double) ManyData.showdownCount) * 60.0;
                table6.setValueAt(Format.formatPer(per), row , col);
                String cellValueStr = "   " + String.valueOf(ManyData.hmlShowdownWon[i][j]);
               // table6.setValueAt(cellValueStr, row, col);

                // Parse the cell value to an integer and add to the priority queue
                try {
                    int cellValue = Integer.parseInt(cellValueStr.trim());
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
            col++;
        }
        col = 1;
        ++row;
    }

    // Add custom cell renderer
    table6.setDefaultRenderer(Object.class, new TableCellRenderer() {
        private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                    row, column);

            if (column != 0 && containsCell(largestCells, row, column)) {
                c.setBackground(Color.RED);
            } else {
                c.setBackground(Color.WHITE);
            }
            return c;
        }
    });

    pane6 = new JScrollPane(table6);
    frame6.add(pane6);
    frame6.pack();
    frame6.setVisible(true);
}









    private static boolean containsCell(PriorityQueue<Cell> cells, int row, int column) {
        for (Cell cell : cells) {
            if (cell.row == row && cell.column == column) {
                return true;
            }
        }
        return false;
    }

    static class Cell {
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