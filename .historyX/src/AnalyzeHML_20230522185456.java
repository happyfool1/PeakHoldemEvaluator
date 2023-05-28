//package evaluate_streets;

/*-
 * I believe that the classification of a Flop as some combination of HML is an
 * opportunity to finf a simple way to evaluate betting opportunities.
 * 
 * What this class does is conduct an experiment to see what value HML has. We
 * will collect data and analyze to see Which of the 10 HML combinations has:
 * Draws - any draws EV - How often will we have the best hand with each of the
 * 10 HML cominations.
 * 
 * @author PEAK_
 */
public class AnalyzeHML implements Constants {
	/*--Arrays */
	static int flopCount;
	static int[] count = new int[HML_SIZE_FLOP]; // How many times we had this combination
	static int hmlCount;
	static int[] winner = new int[HML_SIZE_FLOP]; // How many we won
	static int[] showdownCount = new int[HML_SIZE]; // How many we went to at showdown
	static int[] showdownWin = new int[HML_SIZE]; // How many we went to at showdown
	static int[] showdownLoose = new int[HML_SIZE]; // How many we went to at showdown
	static int[] showdown = new int[HML_SIZE]; // How many we won at showdown
	static int[] flopoCount = new int[HML_SIZE]; // How many we won at Flop
	static int[] turnCount = new int[HML_SIZE]; // How many we won at Turn
	static int[] riverCount = new int[HML_SIZE]; // How many we won at River

	/*--Constructor */
	AnalyzeHML() {

	}

	/*-
	 * Initialize - One time only
	 */
	static void initialize() {
		hmlCount = flopCount = 0;
		for (int i = 0; i < HML_SIZE; ++i) {
			riverCount[i] = turnCount[i] = 0;
		}
	}

	/*--Count times for each HML */
	static void countHML(int i0) {
		++count[i0];
		++hmlCount;
	}

	/*--Showdown win / loose */
	static void showdownData(int i0, boolean b1) {
		++showdownCount[i0];
		if (b1) {
			++showdownWin[i0];
		} else {
			++showdownLoose[i0];
		}
		// TODO++handTypeCountShowdown[i0][i2];
	}

	/*--Count Winners */
	static void countWinners(int i0) {
		++winner[i0];
	}

}
