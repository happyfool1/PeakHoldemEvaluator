
//package evaluate_streets;
import java.util.Arrays;

/*-  ******************************************************************************
* This Class analyzes the index arrays.
*  An instance of this Class must be created for every array that is being analyzed. 
* 1. Draws  
* 2. Made Hands
* 3. Winning Showdown hands. 
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

public class IndexArrayAnalysis implements Constants {
    // Simple copy of arrays
    int[][] drawArray;
    int[][] madeArray;
    int[][] showdownArray;

     // Converted to percentage of all values in array
    double[][] drawArrayPer;
    double[][] madeArrayPer;
    double[][] showdownArrayPer;


    // Same data just sorted
    int[][] drawArraySortedPer;
    int[][] madeArraySortedPer;
    int[][] showdownArraySortedPer;

    // Sum of rows and columns
    int[] drawRowTotalPer;
    int[] drawColTotalPer;
    int[] madeRowTotalPer;
    int[] madeColTotalPer;
    int[] showdownRowTotalPer;
    int[] showdownColTotalPer;
    int drawSumOfAllValuesPer;
    int madeSumOfAllValuesPer;
    int showdownSumOfAllValuesPer;

   
    /*- **************************************************************************** 
    * Constructor.
    * The 3 arguments are index arrays for draws, made hands, and showdown winning made hands.
    
    * Make a local copy of 3 index arrays and create new arrays from these.
    * Create 3 sorted arrays, same data just sorted
    * Create 3 arrays with totals from rows.
    * Create 3 arrays with totals from columns.
    * Create 3 totals of all values in arrays.
    ****************************************************************************** */
    IndexArrayAnalysis(int[][] drawArray, int[][] madeArray, int[][] showdownArray) {
        this.drawArray = drawArray;
        this.madeArray = madeArray;
        this.showdownArray = showdownArray;

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
                / (double) this.madeSumOfAllValuesPer * 100.0;
            }
        }
        for (int i = 0; i < this.showdownArray.length; i++) {
            for (int j = 0; j < this.showdownArray[i].length; j++) {
                this.showdownArrayPer[i][j] = ((double) this.showdownArray[i][j]
                        / (double) this.showdownSumOfAllValues) * 100.0;
            }
        }

        this.drawArraySorted = drawArray;
        this.madeArraySorted = madeArray;
        this.showdownArraySorted = showdownArray;
        Arrays.sort(drawArraySorted);
        Arrays.sort(madeArraySorted);
        Arrays.sort(showdownArray);

        drawRowTotal = new int[drawArray.length];
        madeRowTotal = new int[madeArray.length];
        showdownRowTotal = new int[showdownArray.length];
        drawColTotal = new int[drawArray.length];
        madeColTotal = new int[madeArray.length];
        showdownColTotal = new int[showdownArray.length];

        for (int j = 0; j < this.drawArray.length; j++) {
            for (int i = 0; i < this.drawArray[j].length; i++) {
                this.drawColTotal[j] += this.drawArray[i][j];
            }
        }
        for (int j = 0; j < this.madeArray.length; j++) {
            for (int i = 0; i < this.madeArray[j].length; i++) {
                this.madeColTotal[j] += this.madeArray[i][j];
            }
        }
        for (int j = 0; j < this.showdownArray.length; j++) {
            for (int i = 0; i < this.showdownArray[j].length; i++) {
                this.showdownColTotal[j] += this.showdownArray[i][j];
            }
        }

        for (int i = 0; i < this.drawArray.length; i++) {
            for (int j = 0; j < this.drawArray[i].length; j++) {
                this.drawRowTotal[i] += this.drawArray[i][j];
                this.drawSumOfAllValues += this.drawArray[i][j];
            }
        }
        for (int i = 0; i < this.madeArray.length; i++) {
            for (int j = 0; j < this.madeArray[i].length; j++) {
                this.madeRowTotal[i] += this.madeArray[i][j];
                this.madeSumOfAllValues += this.madeArray[i][j];
            }
        }
        for (int j = 0; j < this.showdownArray.length; j++) {
            for (int i = 0; i < this.showdownArray[j].length; i++) {
                showdownColTotal[j] += showdownArray[i][j];
            }
        }

    }

}