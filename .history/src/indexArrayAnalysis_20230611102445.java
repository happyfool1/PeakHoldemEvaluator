
//package evaluate_streets;
import java.util.Arrays;

/*-  ******************************************************************************
* This Class analyzes the index arrays.
*  An instance of this Class must be created for every array that is being analyzed. 
* 
* @author PEAK_
 ****************************************************************************** */

public class IndexArrayAnalysis implements Constants {

    // Constructor
    IndexArrayAnalysis() {

    }

    int[][] drawArray;
    int[][] madeArray;
    int[][] showdownArray;

    int[][] drawArraySorted;
    int[][] madeArraySorted;
    int[][] showdownArraySorted;

    int[] drawRowTotal;
    int[] drawColTotal;
    int[] madeRowTotal;
    int[] madeColTotal;
    int[] showdownRowTotal;
    int[] showdownColTotal;
    int drawSumOfAllValues;
    int madeSumOfAllValues;
    int showdownSumOfAllValues;

    /*- ******************************************************************************
    * Make a local copy of 3 index arrays.
     ****************************************************************************** */
    void copyArrays(int[][] drawArray, int[][] madeArray, int[][] showdownArray) {
        this.drawArray = drawArray;
        this.madeArray = madeArray;
        this.showdownArray = showdownArray;
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
               this.drawColTotal [j] +=  this.drawArray[i][j];
            }
        }
        for (int j = 0; j < this.madeArray.length; j++) {
            for (int i = 0; i < this.madeArray[j].length; i++) {
                this.madeColTotal [j] += this.madeArray[i][j];
            }
        }
        for (int j = 0; j < this.showdownArray.length; j++) {
            for (int i = 0; i < this.showdownArray[j].length; i++) {
                this.showdownColTotal [j] += this.showdownArray[i][j];
            }
        }

        for (int i = 0; i < this.drawArray[i].length; i++) {
            for (int j = 0; j < this.drawArray.length; j++) {
                this.madeRowTotal [i] += this.madeArray[i][j];
               this.madeSumOfAllValues  += this.madeArray[i][j];
            }
        }
        for (int j = 0; j < MADE_SIZE; j++) {
            for (int i = 0; i < HML_TURN_SIZE; i++) {
                IndexArrays.hmlMadeColTotalTurn[j] += IndexArrays.hmlMadeTurn[i][j];
            }
        }

    }

}