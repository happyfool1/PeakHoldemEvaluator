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

public class ReportHML implements Constants {

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

    private static final Object[] columnsHMLDraw = { "HML", "No draw", "Gutshot",
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
            { "", "", "", "", "", "", "", "", "" },
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
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "", "" } };

    private static final Object[] columnsHHMLDraw = { "HML", "No draw", "Gutshot", "Straight", "OESD", "Flush",
            "Flush OESD" };

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

    private static final Object[] columnsHMLFlopShowdownMade = { "HML", "No hand", "Any pair", "Two pair", "Set",
            "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHMLFlopShowdownMade = { { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "", "" } };

    private static final Object[] columnsHHMLTurnShowdownMade = { "HML", "No hand", "Any pair", "Two pair", "Set",
            "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHHMLTurnShowdownMade = { { "", "", "", "", "", "", "", "", "", "" },
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

    private static final Object[] columnsHHHMLRiverShowdownMade = { "HML", "No hand", "Any pair", "Two pair", "Set",
            "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHHHMLRiverShowdownMade = { { "", "", "", "", "", "", "", "", "", "" },
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

    private static final Object[] columnsHMLFlopShowdownWon = { "HML", "No hand", "Any pair", "Two pair", "Set",
            "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHMLFlopShowdownWon = { { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "", "", "" } };

    private static final Object[] columnsHHMLTurnShowdownWon = { "HML", "No hand", "Any pair", "Two pair", "Set",
            "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHHMLTurnShowdownWon = { { "", "", "", "", "", "", "", "", "", "" },
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

    private static final Object[] columnsHHHMLRiverShowdownWon = { "HML", "No hand", "Any pair", "Two pair", "Set",
            "Straight",
            "Flush", "Full house", "Straight flush", "Royal flush" };

    private static final Object[][] dataHHHMLRiverShowdownWon = { { "", "", "", "", "", "", "", "", "", "" },
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

    static boolean reportHandsDone = false;

    private ReportHML() {
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
        if (frame1 == null) {
            frame1 = new JFrame("Flop HML Draw hands");
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.setLocation(c, r);
            frame1.setPreferredSize(new Dimension(650, 600));
            tableModel1 = new DefaultTableModel(dataHMLDraw, columnsHMLDraw);
            table1 = new JTable(tableModel1);
            table1.setFont(ff1);
            table1.setRowHeight(25);
            pane1 = new JScrollPane(table1);
            frame1.add(pane1);
            frame1.pack();
            frame1.setVisible(true);
        } else {

        }
        int row = 0;
        int col = 1;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table1.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < DRAW_HANDS_SIZE; ++j) {
                if (ManyData.hmlDraw[i][j] > 0) {
                    table1.setValueAt("   " + String.valueOf(ManyData.hmlDraw[i][j]), row, col);
                    double per = ((double) ManyData.hmlDraw[i][j] / (double) ManyData.hmlCount) * 100.0;
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
            frame2.add(pane2);
            frame2.pack();
            frame2.setVisible(true);
        } else {

        }

        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table2.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
                if (ManyData.hmlMade[i][j] > 0) {
                    table2.setValueAt("   " + String.valueOf(ManyData.hmlMade[i][j]), row, col);
                    double per = ((double) ManyData.hmlMade[i][j] / (double) ManyData.hmlCount) * 100.0;
                    table2.setValueAt(Format.formatPer(per), row + 1, col);
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
    static void reportHHMLDraw(int c, int r) {
        if (frame3 == null) {
            frame3 = new JFrame("Turn HML Draw hands");
            frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame3.setLocation(c, r);
            frame3.setPreferredSize(new Dimension(650, 600));
            tableModel3 = new DefaultTableModel(dataHHMLDraw, columnsHHMLDraw);
            table3 = new JTable(tableModel3);
            table3.setFont(ff1);
            table3.setRowHeight(25);
            pane3 = new JScrollPane(table3);
            frame3.add(pane3);
            frame3.pack();
            frame3.setVisible(true);
        } else {

        }
        int row = 0;
        int col = 1;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table3.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < DRAW_HANDS_SIZE; ++j) {
                if (ManyData.hhmlDraw[i][j] > 0) {
                    table3.setValueAt("   " + String.valueOf(ManyData.hhmlDraw[i][j]), row, col);
                    double per = ((double) ManyData.hhmlDraw[i][j] / (double) ManyData.hmlCount) * 100.0;
                    table3.setValueAt(Format.formatPer(per), row + 1, col);
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
    static void reportHHMLMade(int c, int r) {
        if (frame4 == null) {
            frame4 = new JFrame("Turn HML Made hands");
            frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame4.setLocation(c, r);
            frame4.setPreferredSize(new Dimension(650, 600));
            tableModel4 = new DefaultTableModel(dataHHMLMade, columnsHHMLMade);
            table4 = new JTable(tableModel4);
            table4.setFont(ff1);
            table4.setRowHeight(25);
            pane4 = new JScrollPane(table4);
            frame4.add(pane4);
            frame4.pack();
            frame4.setVisible(true);
        } else {

        }

        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table4.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
                if (ManyData.hmlMade[i][j] > 0) {
                    table4.setValueAt("   " + String.valueOf(ManyData.hhmlMade[i][j]), row, col);
                    double per = ((double) ManyData.hhmlMade[i][j] / (double) ManyData.hhmlCount) * 100.0;
                    table4.setValueAt(Format.formatPer(per), row + 1, col);
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
    static void reportHHHMLMade(int c, int r) {
        if (frame6 == null) {
            frame6 = new JFrame("River HML Made hands");
            frame6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame6.setLocation(c, r);
            frame6.setPreferredSize(new Dimension(650, 600));
            tableModel6 = new DefaultTableModel(dataHHHMLMade, columnsHHHMLMade);
            table6 = new JTable(tableModel6);
            table6.setFont(ff1);
            table6.setRowHeight(25);
            pane6 = new JScrollPane(table6);
            frame6.add(pane6);
            frame6.pack();
            frame6.setVisible(true);
        } else {

        }

        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table6.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
                if (ManyData.hmlMade[i][j] > 0) {
                    table6.setValueAt("   " + String.valueOf(ManyData.hhhmlMade[i][j]), row, col);
                    double per = ((double) ManyData.hhhmlMade[i][j] / (double) ManyData.hhhmlCount) * 100.0;
                    table6.setValueAt(Format.formatPer(per), row + 1, col);
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
    static void reportHMLShowdownFlopWon(int c, int r) {
        if (frame10 == null) {
            frame10 = new JFrame("Showdown Flop HML Winner hands");
            frame10.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame10.setLocation(c, r);
            frame10.setPreferredSize(new Dimension(1050, 600));
            tableModell0 = new DefaultTableModel(dataHMLFlopShowdownWon, columnsHMLFlopShowdownWon);
            table10 = new JTable(tableModell0);
            table10.setFont(ff1);
            table10.setRowHeight(25);
            pane10 = new JScrollPane(table10);
            frame10.add(pane10);
            frame10.pack();
            frame10.setVisible(true);
        }
        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table10.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
                if (ManyData.hmlShowdownWon[i][j] > 0) {
                    table10.setValueAt("   " + String.valueOf(ManyData.hmlShowdownWon[i][j]), row, col);
                    double per = ((double) ManyData.hmlShowdownWon[i][j] / (double) ManyData.showdownCount) * 100.0;
                    table10.setValueAt(Format.formatPer(per), row + 1, col);
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
    static void reportHMLShowdownTurnWon(int c, int r) {
        if (frame11 == null) {
            frame11 = new JFrame("Showdown Turn HML Winner hands");
            frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame11.setLocation(c, r);
            frame10.setLocation(c, r);
            frame11.setPreferredSize(new Dimension(1050, 600));
            frame10.setLocation(c, r);
            tableModel11 = new DefaultTableModel(dataHHMLTurnShowdownWon, columnsHHMLTurnShowdownWon);
            table11 = new JTable(tableModel11);
            table11.setFont(ff1);
            table11.setRowHeight(25);
            pane11 = new JScrollPane(table11);
            frame11.add(pane11);
            frame11.pack();
            frame11.setVisible(true);
        }
        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table11.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
                if (ManyData.hmlShowdownWon[i][j] > 0) {
                    table11.setValueAt("   " + String.valueOf(ManyData.hmlShowdownWon[i][j]), row, col);
                    double per = ((double) ManyData.hmlShowdownWon[i][j] / (double) ManyData.showdownCount) * 100.0;
                    table11.setValueAt(Format.formatPer(per), row + 1, col);
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
    static void reportHMLShowdownRiverWon(int c, int r) {
        if (frame12 == null) {
            frame12 = new JFrame("Showdown River HML Winner hands");
            frame12.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame12.setLocation(c, r);
            frame12.setPreferredSize(new Dimension(1050, 600));
            tableModel12 = new DefaultTableModel(dataHHHMLRiverShowdownWon, columnsHHHMLRiverShowdownWon);
            table12 = new JTable(tableModel12);
            table12.setFont(ff1);
            table12.setRowHeight(25);
            pane12 = new JScrollPane(table12);
            frame12.add(pane12);
            frame12.pack();
            frame12.setVisible(true);
        }
        int row = 0;
        int col = 0;
        for (int i = 0; i < HML_FLOP_SIZE; ++i) {
            table12.setValueAt(HML_FLOP_ST[i], row, 0);
            col = 1;
            for (int j = 0; j < MADE_HANDS_SIZE; ++j) {
                if (ManyData.hmlShowdownWon[i][j] > 0) {
                    table12.setValueAt("   " + String.valueOf(ManyData.hmlShowdownWon[i][j]), row, col);
                    double per = ((double) ManyData.hmlShowdownWon[i][j] / (double) ManyData.showdownCount) * 100.0;
                    table12.setValueAt(Format.formatPer(per), row + 1, col);
                }
                col++;
            }
            col = 1;
            row += 2;
        }
    }

}