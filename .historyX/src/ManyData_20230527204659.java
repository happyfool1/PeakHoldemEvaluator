//package evaluate_streets;

/*- ******************************************************************************
 * 
 * @author PEAK_
*******************************************************************************/
public class ManyData implements Constants {
	/*-  ******************************************************************************
	 * Updated foe each hand played
	 * If corresponding array in OneHand element is true then array element here 
	 * is incremented by 1
	 ****************************************************************************** */
	// Flop board analysis - hole cards not included
	static int[] boardArrayFlop = new int[3];
	static int[] boardArrayTurn = new int[4];
	static int[] boardArrayRiver = new int[5];
	static int boardArrayFlopCount = 0;
	static int boardArrayTurnCount = 0;
	static int boardArrayRiverCount = 0;

	static int[] flopArray = new int[FLOP_MAX];

	static int[] drawArrayFlop = new int[DRAW_MAX_HANDS];
	static int[] drawArrayTurn = new int[DRAW_MAX_HANDS];

	static int[] madeArrayFlop = new int[MADE_MAX_HANDS];
	static int[] madeArrayTurn = new int[MADE_MAX_HANDS];
	static int[] madeArrayRiver = new int[MADE_MAX_HANDS];

	// Board possible hands on the Flop
	static int[] boardPossibleArrayFlop = new int[POSSIBLE_MAX];
	static int[] boardPossibleArrayTurn = new int[POSSIBLE_MAX];

	static int flopArraysCount = 0;
	static int turnArraysCount = 0;
	static int riverArraysCount = 0;

	static int[] madeHandArray = new int[MADE_MAX_HANDS];
	static int[] bestDrawArray = new int[BEST_MAX_HANDS];
	static int[] possibleArray = new int[POSSIBLE_MAX];

	static int[] sumOfHandValuesFlopArray = new int[65];
	static int[] sumOfBoardValuesFlopArray = new int[39];
	static int[] sumOfHoleCardValuesArray = new int[26];

	static int[] flopSumOfDigitsArray = new int[39];

	static int[] hmlArrayFlop = new int[HML_SIZE_FLOP];
	static int[] hmlArrayTurn = new int[HML_SIZE_TURN];
	static int[] hmlArrayRiver = new int[HML_SIZE_RIVER];

	static int[] typeArray = new int[4];

	static int[] flopTypeOf1755Array = new int[1755];

	// Showdown
	static int showdownCount = 0;
	static int[] winningHand = new int[99]; // ?? TODO

	/*-  ******************************************************************************
	 * Calculated values percentage
	 ****************************************************************************** */
	// Flop board analysis - hole cards not included
	static double[] boardArrayFlopPer = new double[3];
	static double[] boardArrayTurnPer = new double[4];
	static double[] boardArrayRiverPer = new double[5];

	// Board possible hands on the Flop
	static double[] boardPossibleArrayFlopPer = new double[POSSIBLE_MAX];
	static double[] boardPossibleArrayTurnPer = new double[POSSIBLE_MAX];

	static double[] hmlArrayFlopPer = new double[HML_SIZE_FLOP];
	static double[] hmlArrayTurnPer = new double[HML_SIZE_TURN];
	static double[] hmlArrayRiverPer = new double[HML_SIZE_RIVER];

	static double[] typeArrayPer = new double[4];

	static double[] flopTypeOf1755ArrayPer = new double[1755];

	// Showdown
	static double[] winningHandPer = new double[99]; // ?? TODO

	/*-
	* Used to calculate EV for hands on a street Values of hands by seat and type
	* of hand, and Win or Loose index from Evaluate for Flop Turn and River.
	* +1 because of Showdown
	*/
	static int[][] evaluateIndex = new int[STREET + 1][PLAYERS];

	static int[][][] evaluateIndexPos = new int[STREET + 1][PLAYERS][POSITIONS];

/*-  ******************************************************************************
	 * Initialize for a new run
	 ****************************************************************************** */
		static void initialize(){
			for (int i = 0; i < HML_SIZE_FLOP; i++) {
			}
			for (int i = 0; i < HML_SIZE_TURN; i++) {
			}	 

			for (int i = 0; i < HML_SIZE_RIVER; i++) {
			}
			for (int i = 0; i < FLOP_INDEX_MAX; i++) {
			}
			for (int i = 0; i < TYPE1755_MAX; i++) {
			}
			for (int i = 0; i < HAND_VALUE_MAX]; i++) {
			}
			for (int i = 0; i < TYPE1755_MAX; i++) {
			}
			for (int i = 0; i < TYPE1755_MAX; i++) {
			}

	}

	/*-  ******************************************************************************
	 * Evaluation arrays
	 * All of these arrays use some characteristic of the Flop, Turn, or River
	 * board to evaluate what happens with a draw, made hand, or Showdown.
	 ****************************************************************************** */
	static int[][] hmlDraw = new int[HML_SIZE_FLOP][DRAW_MAX_HANDS];
	static int[][] hmlMade = new int[HML_SIZE_FLOP][MADE_MAX_HANDS];
	static int[] hmlShowdown = new int[HML_SIZE_FLOP];

	static int[][] hhmlDraw = new int[HML_SIZE_TURN][DRAW_MAX_HANDS];
	static int[][] hhmlMade = new int[HML_SIZE_TURN][MADE_MAX_HANDS];
	static int[] hhmlShowdown = new int[HML_SIZE_TURN];

	static int[][] hhhmlMade = new int[HML_SIZE_RIVER][MADE_MAX_HANDS];
	static int[] hhhmlShowdown = new int[HML_SIZE_RIVER];

	static int[][] typeDraw = new int[4][DRAW_MAX_HANDS];
	static int[][] typeMade = new int[4][MADE_MAX_HANDS];
	static int[] typeShowdown = new int[4];

	static int[][] sumOfDraw = new int[36][DRAW_MAX_HANDS];
	static int[][] sumOfMade = new int[36][MADE_MAX_HANDS];
	static int[] sumOfShowdown = new int[36];

	static int[][] wetDryDraw = new int[4][DRAW_MAX_HANDS];
	static int[][] wetDryMade = new int[4][MADE_MAX_HANDS];
	static int[] wetDryShowdown = new int[4];

	static int[][] type755Draw = new int[TYPE1755_MAX][DRAW_MAX_HANDS];
	static int[][] type1755Made = new int[TYPE1755_MAX][DRAW_MAX_HANDS];
	static int[] type1755Showdown = new int[TYPE1755_MAX];

	static int[][] flopIndexDraw = new int[FLOP_INDEX_MAX][DRAW_MAX_HANDS];
	static int[][] flopIndexMade = new int[FLOP_INDEX_MAX][DRAW_MAX_HANDS];
	static int[] flopIndexShowdown = new int[FLOP_INDEX_MAX];

	static int[][] handValueDraw = new int[HAND_VALUE_MAX][DRAW_MAX_HANDS];
	static int[][] handValueMade = new int[HAND_VALUE_MAX][DRAW_MAX_HANDS];
	static int[] handValueShowdown = new int[HAND_VALUE_MAX];

	private ManyData() {
		throw new IllegalStateException("Utility class");
	}
}
