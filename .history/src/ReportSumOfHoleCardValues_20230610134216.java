//package evaluate_streets;
/*-  *********************************************************************************************
 * This Class displays reports of HML related data.
 * 
 * All methods have 2 arguments, the column and the row where the frame is 
 * to be displayed.
 * 
 * @author PEAK_
  **********************************************************************************************/

  import javax.swing.JFrame;
  import javax.swing.JScrollPane;
  import javax.swing.JTable;

import java.awt.*;
import java.util.PriorityQueue;
  import java.util.Comparator;
  import javax.swing.table.*;
  
  public class ReportSumOfHoleCardValues implements Constants {
  
 
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
    static void reportMadeTurn(int c, int r) {
        if (frame5 == null) {
            frame5 = new JFrame("Turn HML Made hands");
            frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame5.setLocation(c, r);
            frame5.setPreferredSize(new Dimension(1300, 440));
        }

        // Create a PriorityQueue to hold the cells with the top 5 values
        PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
        PriorityQueue<Cell> smallestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
        tableModel5 = new DefaultTableModel(dataMadeTurn, columnsMadeTurn);
        table5 = new JTable(tableModel5);
        table5.setFont(ff1);
        table5.setRowHeight(25);
        double sumx = 0.0;
        double sum = 0.0;
        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_TURN_SIZE; ++i) {
            table5.setValueAt(HML_TURN_ST[i], row, 0);
            col = 1;
            sum = 0.0;
            for (int j = 0; j < MADE_SIZE; ++j) {
                if (ManyData.hhmlMade[i][j] > 0) {
                    double per = ((double) ManyData.hhmlMade[i][j] / (double) ManyData.hmlCount) * 100.0;
                    sum += per;
                    sumx += per;
                    table5.setValueAt(Format.formatPer(per), row, col);
                    table5.setValueAt(Format.formatPer(sum), row, 15);
                    String cellValueStr = "   " + String.valueOf(ManyData.hhmlMade[i][j]);
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
        table5.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);

                if (column != 0 && containsCell(largestCells, row, column)) {
                    c.setBackground(Color.GREEN);
                } else {
                    c.setBackground(Color.CYAN);
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
    static void reportShowdownTurn(int c, int r) {
        if (frame6 == null) {
            frame6 = new JFrame("Showdown Turn HML Winner hands");
            frame6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame6.setLocation(c, r);
            frame6.setPreferredSize(new Dimension(1300, 440));
        }

        // Create a PriorityQueue to hold the cells with the top 5 values
        PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
        PriorityQueue<Cell> smallestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
        tableModel6 = new DefaultTableModel(dataShowdownTurn, columnsShowdownTurn);
        table6 = new JTable(tableModel6);
        table6.setFont(ff1);
        table6.setRowHeight(25);
        double sumx = 0.0;
        double sum = 0.0;
        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_TURN_SIZE; ++i) {
            table6.setValueAt(HML_TURN_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_SIZE; ++j) {
                if (ManyData.hhmlShowdownWon[i][j] > 0) {
                    double per = ((double) ManyData.hhmlShowdownWon[i][j] / (double) ManyData.showdownCount) * 100.0;
                    sum += per;
                    sumx += per;
                    table6.setValueAt(Format.formatPer(per), row, col);
                    table6.setValueAt(Format.formatPer(sum), row, 15);
                    String cellValueStr = "   " + String.valueOf(ManyData.hhmlShowdownWon[i][j]);
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
        table6.setDefaultRenderer(Object.class, new TableCellRenderer() {
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
        pane6 = new JScrollPane(table6);
        frame6.add(pane6);
        frame6.pack();
        frame6.setVisible(true);
    }

    /*-**********************************************************************************
    * Same as above but for Made hands
    ********************************************************************************** */
    static void reportMadeRiver(int c, int r) {
        if (frame7 == null) {
            frame7 = new JFrame("River HML Made hands");
            frame7.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame7.setLocation(c, r);
            frame7.setPreferredSize(new Dimension(1300, 600));
        }

        // Create a PriorityQueue to hold the cells with the top 5 values
        PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
        PriorityQueue<Cell> smallestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
        tableModel7 = new DefaultTableModel(dataMadeRiver, columnsMadeRiver);
        table7 = new JTable(tableModel7);
        table7.setFont(ff1);
        table7.setRowHeight(25);
        double sumx = 0.0;
        double sum = 0.0;
        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_RIVER_SIZE; ++i) {
            table7.setValueAt(HML_RIVER_ST[i], row, 0);
            col = 1;
            sum = 0.0;
            for (int j = 0; j < MADE_SIZE; ++j) {
                if (ManyData.hhhmlMade[i][j] > 0) {
                    double per = ((double) ManyData.hhhmlMade[i][j] / (double) ManyData.hmlCount) * 100.0;
                    sum += per;
                    sumx += per;
                    table7.setValueAt(Format.formatPer(per), row, col);
                    table7.setValueAt(Format.formatPer(sum), row, 15);
                    String cellValueStr = "   " + String.valueOf(ManyData.hhhmlMade[i][j]);
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
        table7.setDefaultRenderer(Object.class, new TableCellRenderer() {
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

        pane7 = new JScrollPane(table7);
        frame7.add(pane7);
        frame7.pack();
        frame7.setVisible(true);
    }

    /*-**********************************************************************************
    * Same as above but for Made hands
    ********************************************************************************** */
    static void reportShowdownRiver(int c, int r) {
        if (frame8 == null) {
            frame8 = new JFrame("Showdown River HML Winner hands");
            frame8.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame8.setLocation(c, r);
            frame8.setPreferredSize(new Dimension(1300, 600));
        }

        // Create a PriorityQueue to hold the cells with the top 5 values
        PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
        PriorityQueue<Cell> smallestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
        tableModel8 = new DefaultTableModel(dataShowdownRiver, columnsShowdownRiver);
        table8 = new JTable(tableModel8);
        table8.setFont(ff1);
        table8.setRowHeight(25);
        double sumx = 0.0;
        double sum = 0.0;
        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_RIVER_SIZE; ++i) {
            table8.setValueAt(HML_RIVER_ST[i], row, 0);
            col = 1;
            sum = 0.0;
            for (int j = 0; j < MADE_SIZE; ++j) {
                if (ManyData.hhhmlShowdownWon[i][j] > 0) {
                    double per = ((double) ManyData.hhhmlShowdownWon[i][j] / (double) ManyData.showdownCount) * 100.0;
                    sum += per;
                    sumx += per;
                    table8.setValueAt(Format.formatPer(per), row, col);
                    table8.setValueAt(Format.formatPer(sum), row, 15);
                    String cellValueStr = "   " + String.valueOf(ManyData.hhhmlShowdownWon[i][j]);
                    // Parse the cell value to an integer and add to the priority queue
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
                col++;
            }
            col = 1;
            ++row;
        }

        // Add custom cell renderer
        table8.setDefaultRenderer(Object.class, new TableCellRenderer() {
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

        pane8 = new JScrollPane(table8);
        frame8.add(pane8);
        frame8.pack();
        frame8.setVisible(true);
    }

    /*-**********************************************************************************
    * Helper method
    ********************************************************************************** */
    private static boolean containsCell(PriorityQueue<Cell> cells, int row, int column) {
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