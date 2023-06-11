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
 
   int[] draeRowTotal = new int[HML_FLOP_SIZE];
	 int[] drawColTotal = new int[DRAW_SIZE];
	  int[] madeowTotal = new int[HML_FLOP_SIZE];
  int[] madeColTotal = new int[MADE_SIZE];


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

for (int j = 0; j < this.drawArray.length; j++) {
    for (int i = 0; i < this.drawArray[j].length; i++) {
        IndexArrays.hmlDrawColTotalFlop[j] += IndexArrays.hmlDrawFlop[i][j];
    }
}
for (int j = 0; j < this.madeArray.length; j++) {
    for (int i = 0; i < this.madeArray[j].length; i++) {
        IndexArrays.hmlDrawColTotalFlop[j] += IndexArrays.hmlDrawFlop[i][j];
    }
}
for (int j = 0; j < this.showdownArray.length; j++) {
    for (int i = 0; i < this.showdownArray[j].length; i++) {
        IndexArrays.hmlDrawColTotalFlop[j] += IndexArrays.hmlDrawFlop[i][j];
    }
}

		for (int i = 0; i < HML_FLOP_SIZE; i++) {
			for (int j = 0; j < MADE_SIZE; j++) {
				IndexArrays.hmlMadeRowTotalFlop[i] += IndexArrays.hmlMadeFlop[i][j];
				IndexArrays.hmlMadeSumOfAllValuesFlop += IndexArrays.hmlMadeFlop[i][j];
			}
		}
		for (int j = 0; j < MADE_SIZE; j++) {
			for (int i = 0; i < HML_TURN_SIZE; i++) {
						IndexArrays.hmlMadeColTotalTurn[j] += IndexArrays.hmlMadeTurn[i][j];
			}
		}

}




}