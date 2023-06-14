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

    static Font ff1 = new Font(Font.SERIF, Font.BOLD, 14);

 private   final Object[] columnsDrawFlop = { "Index", "No draw", "Gutshot",
            "Straight", "OESD", "Flush", "Flush OESD", "Total" };

    private   final Object[][] dataDrawFlop = { { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" },
              { "", "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "", "" } };

    private   final Object[] columnsMadeFlop = { "Index", "No hand", "Brd Pair", "Bottom Pair", "Middle Pair",
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

    private static final Object[] columnsShowdownFlop = { "Index", "No hand", "Brd Pair", "Bottom Pair", "Middle Pair",
            "Top Pair", "Over Pair","Brd 2Pair", "2Pair & Under", "2Pair & Over", "2Pair Bottom",
            "2Pair Top-Bot", "2Pair Top 2", "Brd Set", "Set", "Straight", "Flush",  "Total" };

    private static final Object[][] dataShowdownFlop = {
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