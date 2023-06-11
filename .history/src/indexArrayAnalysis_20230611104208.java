
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
    IndexArrayAnalysis(int[][] drawArray, int[][] madeArray, int[][] showdownArray) {

    }

    // Simple copy of arrays 
    int[][] drawArray;
    int[][] madeArray;
    int[][] showdownArray;

    // Same data just sorted
    int[][] drawArraySorted;
    int[][] madeArraySorted;
    int[][] showdownArraySorted;

    // Sum of rows and columns
    int[] drawRowTotal;
    int[] drawColTotal;
    int[] madeRowTotal;
    int[] madeColTotal;
    int[] showdownRowTotal;
    int[] showdownColTotal;
    int drawSumOfAllValues;
    int madeSumOfAllValues;
    int showdownSumOfAllValues;

 // Converted to percentage of all values in array
    double[][] drawArrayPer;
    double[][] madeArrayPer;
    double[][] showdownArrayPer;


  /*- **************************************************************************** 
  * Make a local copy of 3 index arrays and create new arrays from these.
  * Create 3 sorted arrays, same data just sorted
  * Create 3 arrays with totals from rows.
  * Create 3 arrays with totals from columns.
  * Create 3 totals of all values in arrays.
  ****************************************************************************** */
    void copyArrays(int[][] drawArray, int[][] madeArray, int[][] showdownArray) {
       

}