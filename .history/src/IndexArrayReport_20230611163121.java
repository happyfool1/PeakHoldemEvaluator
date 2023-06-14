//package evaluate_streets;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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

 private static final int DRAW_COL= DRAW_SIZE;
    private static final int DRAW_TOTAL_COL =DRAW_SIZE+1;

 private static final int MADE_COL= MADE_FLUSH;
    private static final int MADE_TOTAL_COL = MADE_FLUSH+2;

 private   JFrame frame1 = null;
    private static JFrame frame2 = null;
    private static JFrame frame3 = null;
 


    private static JTable table1 = null;
    private static JTable table2 = null;
    private static JTable table3 = null;
   
  
    private static DefaultTableModel tableModel1 = null;
    private static DefaultTableModel tableModel2 = null;
    private static DefaultTableModel tableModel3 = null;
 
  

    static JScrollPane pane1 = null;
    static JScrollPane pane2 = null;
    static JScrollPane pane3 = null;
 



    static Font ff1 = new Font(Font.SERIF, Font.BOLD, 14);

 private    Object[] columnsDrawFlop = { "Index", "No draw", "Gutshot",
            "Straight", "OESD", "Flush", "Flush OESD", "Total" };

    private   Object[][] dataDrawFlop = { { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" },
              { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" } };

    private    Object[] columnsMadeFlop = { "Index", "No hand", "Brd Pair", "Bottom Pair", "Middle Pair",
            "Top Pair", "Over Pair","Brd 2Pair", "2Pair & Under", "2Pair & Over", "2Pair Bottom",
            "2Pair Top-Bot", "2Pair Top 2", "Brd Set", "Set", "Straight", "Flush",  
            "Total" };

    private  Object[][] dataMadeFlop = {
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
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" } };

    private   Object[] columnsShowdownFlop = { "Index", "No hand", "Brd Pair", "Bottom Pair", "Middle Pair",
            "Top Pair", "Over Pair","Brd 2Pair", "2Pair & Under", "2Pair & Over", "2Pair Bottom",
            "2Pair Top-Bot", "2Pair Top 2", "Brd Set", "Set", "Straight", "Flush",  "Total" };

    private   Object[][] dataShowdownFlop = {
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
            { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" } };


}