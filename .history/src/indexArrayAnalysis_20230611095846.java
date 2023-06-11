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
    IndexArrayAnalysis(){

    }



 int[][] drawArray;  
 int[][] madeArray;  
 int[][] showdownArray;  


 int[][] drawArraySorted;  
 int[][] madeArraySorted;  
 int[][] showdownArraySorted;  	   


/*- ******************************************************************************
* Make a local copy of 3 index arrays.
 ****************************************************************************** */
void copyArrays(int [][] drawArray, int [][] madeArray, int [][] showdownArray) {
this.drawArray = drawArray;
this.madeArray = madeArray;
this.showdownArray = showdownArray;
this.drawArraySorted = drawArray;
this.madeArraySorted = madeArray;
this.showdownArraySorted = showdownArray;
Arrays.sort(drawArraySorted);
Arrays.sort(madeArraySorted);
Arrays.sort(showdownArray);

}




}