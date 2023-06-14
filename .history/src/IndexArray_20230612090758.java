
//package evaluate_streets;
import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.platform.console.shadow.picocli.CommandLine.Help.TextTable.Cell;

import java.util.Comparator;

/*-  ******************************************************************************
* This Class is a data holding Class.
*  An instance of this Class must be created for every array that is being analyzed. 
* 1. Draws  
* 2. Made Hands
* 3. Winning Showdown hands. 
* 
* There aee no public methods in this class.
* The constructor uses arguments that are arrays in IndexArrays and from these arrays 
* creates several other arrays in this class, all derived only from the original 3.
* These additional arrays are for convenience in analysis.
* Two classes are the users of this Class:
* 1. IndexArrayReport
* 2. IndexArrayAnalysis
*
* The first index int the arrays is some integer created from analyzing the board 
* or analyzing the hand ( board + hole cards )
* For example: HML is an index created by looking at the board cards and assigning 
* a value based on each cards value, high H is A - 10, Middle M is 9 -6, 
* and  low L is 5 - 2.
*
* This class is not unique to any index type or corresponding array dimensions.
* Instead it can be used to analyze any existing or yet to be imagined. 
*
* @author PEAK_
 ****************************************************************************** */

public class IndexArray implements Constants {

    IndexArray() {
    }

    /*- **************************************************************************** 
    * Constructor.
    * The 3 arguments are index arrays for draws, made hands, and showdown winning made hands.
    *
    * Make a local copy of 3 index arrays and create new arrays from these.
    * Create 3 sorted arrays, same data just sorted
    * Create 3 arrays with totals from rows.
    * Create 3 arrays with totals from columns.
    * Create 3 totals of all values in arrays.
    *
    * Relative hand value is like wet / dry. 
    *   A high relative value corresponds approximately  to a wet board. 
    *   It means that there are a lot of possible draws and possible made hands.
    
    *   A low relative value corresponds approximately  to a dry board. 
    *   It means that there are very few of possible draws and very few possible made hands.
    *
    *   A medium value corresponds approximately to board that is neutral, not wet or dry
    *
    *   Strategy suggestions are about the same as with wet / dry.
    ****************************************************************************** */
    IndexArray(int[][] drawArray, int[][] madeArray, int[][] showdownArray, String[] rowNames) {
        this.rowNames = rowNames.clone();
        this.drawArray = drawArray.clone();
        this.madeArray = madeArray.clone();
        this.showdownArray = showdownArray.clone();

        this.drawArrayPer = new double[this.drawArray.length][this.drawArray[0].length];
        this.madeArrayPer = new double[this.madeArray.length][this.madeArray[0].length];
        this.showdownArrayPer = new double[this.showdownArray.length][this.showdownArray[0].length];

        this.drawArraySortedPer = new double[this.drawArray.length][this.drawArray[0].length];
        this.madeArraySortedPer = new double[this.madeArray.length][this.madeArray[0].length];
        this.showdownArraySortedPer = new double[this.showdownArray.length][this.showdownArray[0].length];

        this.drawRowTotalPer = new double[this.drawArray.length];
        this.madeRowTotalPer = new double[this.madeArray.length];
        this.showdownRowTotalPer = new double[this.showdownArray.length];

        this.drawColTotalPer = new double[this.drawArray[0].length];
        this.madeColTotalPer = new double[this.madeArray[0].length];
        this.showdownColTotalPer = new double[this.showdownArray[0].length];

        for (int i = 0; i < this.drawArray.length; i++) {
            for (int j = 0; j < this.drawArray[i].length; j++) {
                this.drawSumOfAllValuesPer += this.drawArray[i][j];
            }
        }
        for (int i = 0; i < this.madeArray.length; i++) {
            for (int j = 0; j < this.madeArray[i].length; j++) {
                this.madeSumOfAllValuesPer += this.madeArray[i][j];
            }
        }
        for (int i = 0; i < this.showdownArray.length; i++) {
            for (int j = 0; j < this.showdownArray[i].length; j++) {
                this.showdownSumOfAllValuesPer += this.madeArray[i][j];
            }
        }

        for (int i = 0; i < this.drawArray.length; i++) {
            for (int j = 0; j < this.drawArray[i].length; j++) {
                this.drawArrayPer[i][j] = ((double) this.drawArray[i][j]
                        / (double) this.drawSumOfAllValuesPer) * 100.0;
            }
        }
        for (int i = 0; i < this.madeArray.length; i++) {
            for (int j = 0; j < this.madeArray[i].length; j++) {
                this.madeArrayPer[i][j] = ((double) this.madeArray[i][j]
                        / (double) this.madeSumOfAllValuesPer * 100.0);
            }
        }
        for (int i = 0; i < this.showdownArray.length; i++) {
            for (int j = 0; j < this.showdownArray[i].length; j++) {
                this.showdownArrayPer[i][j] = ((double) this.showdownArray[i][j]
                        / (double) this.showdownSumOfAllValuesPer) * 100.0;
            }
        }

        this.drawArraySortedPer = this.drawArrayPer.clone();
        this.madeArraySortedPer = this.drawArrayPer.clone();
        this.showdownArraySortedPer = this.showdownArrayPer.clone();
        for (int i = 0; i < this.drawArraySortedPer.length; i++) {
            Arrays.sort(this.drawArraySortedPer[i]);
        }
        for (int i = 0; i < this.madeArraySortedPer.length; i++) {
            Arrays.sort(this.madeArraySortedPer[i]);
        }
        for (int i = 0; i < this.showdownArraySortedPer.length; i++) {
            Arrays.sort(this.showdownArraySortedPer[i]);
        }

        // Column totals
        for (int i = 0; i < this.drawArrayPer.length; i++) {
            for (int j = 0; j < this.drawArrayPer[0].length; j++) {
                this.drawColTotalPer[j] += this.drawArrayPer[i][j];
            }
        }
        for (int i = 0; i < this.madeArrayPer.length; i++) {
            for (int j = 0; j < this.madeArrayPer[0].length; j++) {
                this.madeColTotalPer[j] += this.madeArrayPer[i][j];
            }
        }
        for (int i = 0; i < this.showdownArrayPer.length; i++) {
            for (int j = 0; j < this.showdownArrayPer[0].length; j++) {
                this.showdownColTotalPer[j] += this.showdownArrayPer[i][j];
            }
        }
        // row totals
        for (int i = 0; i < this.drawArrayPer.length; i++) {
            for (int j = 0; j < this.drawArrayPer[0].length; j++) {
                this.drawRowTotalPer[i] += this.drawArrayPer[i][j];
                this.drawSumOfAllValuesPer += this.madeArrayPer[i][j];
            }
        }
        for (int i = 0; i < this.madeArrayPer.length; i++) {
            for (int j = 0; j < this.madeArrayPer[0].length; j++) {
                this.madeRowTotalPer[i] += this.madeArrayPer[i][j];
                this.madeSumOfAllValuesPer += this.madeArrayPer[i][j];
            }
        }
        for (int i = 0; i < this.showdownArrayPer.length; i++) {
            for (int j = 0; j < this.showdownArrayPer[0].length; j++) {
                showdownRowTotalPer[i] += showdownArrayPer[i][j];
                this.showdownSumOfAllValuesPer += this.madeArrayPer[i][j];
            }
        }
    }

    /***********************************************************************************
     * Find top 5 rows.
     * For index arrays with less than 5 rows, TODO
     */
    void xx() {
        String cellValueStr = "   " + String.valueOf(arrays.showdownArray[i][j]);
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

    // Simple copy of arrays\
    String[] rowNames; // Names for the rows in array
    int[][] drawArray;
    int[][] madeArray;
    int[][] showdownArray;

    // Converted to percentage of all values in array
    double[][] drawArrayPer;
    double[][] madeArrayPer;
    double[][] showdownArrayPer;

    // Same data just sorted
    double[][] drawArraySortedPer;
    double[][] madeArraySortedPer;
    double[][] showdownArraySortedPer;

    /*-**********************************************************************************
     These arrays contain a row number of:
     
    ********************************************************************************** */
    int numberOfTopRows = 0;
    int numberOfTopCols = 0;
    int[] topMadeRows = new int[5];
    int[] topDrawRows = new int[5];
    int[] topShowdownRows = new int[5];
    int[] topMadeCols = new int[5];
    int[] topDrawCols = new int[5];
    int[] topShowdownCols = new int[5];
    // Bottom rows
    int numberOfBottomRows = 0;
    int numberOfBottomCols = 0;
    int[] bottomMadeRows = new int[5];
    int[] bottomDrawRows = new int[5];
    int[] bottomShowdownRows = new int[5];
    int[] bottomMadeCols = new int[5];
    int[] bottomDrawCols = new int[5];
    int[] bottomShowdownCols = new int[5];

    // Create a PriorityQueue to hold the top and bottom 5 values
    PriorityQueue<Cell> largestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));
    PriorityQueue<Cell> smallestCells = new PriorityQueue<>(5, Comparator.comparingInt(cell -> cell.value));

    // Row relative values
    double[] rowRelativeValue;

    // Suggested action if we have nothing
    // Suggested action if we have a made hand
    // Suggested action if we have a draw

    // Suggested action if we have a pair
    // Suggested action if we have a bottom pair
    // Suggested action if we have a middle pair
    // Suggested action if we have a top pair
    // Suggested action if we have a over pair
    // Suggested action if we have a board pair + under pair
    // Suggested action if we have a board pair + over pair
    // Suggested action if we have a bottom 2 pair
    // Suggested action if we have top and bottom 2 pair
    // Suggested action if we have top 2 pair
    // Suggested action if we have a set

    // Suggested action if we have a draw
    // Suggested action if we have gutshot draw
    // Suggested action if we have Straight draw
    // Suggested action if we have OESD draw
    // Suggested action if we have Flush draw

    // Sum of rows and columns
    double[] drawRowTotalPer;
    double[] drawColTotalPer;
    double[] madeRowTotalPer;
    double[] madeColTotalPer;
    double[] showdownRowTotalPer;
    double[] showdownColTotalPer;
    double drawSumOfAllValuesPer;
    double madeSumOfAllValuesPer;
    double showdownSumOfAllValuesPer;
}