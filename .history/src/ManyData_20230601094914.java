//package evaluate_streets;

/*- ******************************************************************************
 * 
 * @author PEAK_
*******************************************************************************/
public class ManyData implements Constants {

	/*-  ****************************************************************************
	* Updated foe each hand played
	* If corresponding array in OneHand element is true then array element here 
	  is incremented by 1
	****************************************************************************** */

	/*-  ****************************************************************************
	 * Initialize for a new run
	 ****************************************************************************** */
	static void initialize() {
		for (int i = 0; i < HML_FLOP_SIZE; i++) {
			for (int j = 0; j < DRAW_HANDS_SIZE; j++) {
				hmlDraw[i][j] = 0;
			}
			for (int j = 0; j < MADE_HANDS_SIZE; j++) {
				hmlMade[i][j] = 0;
				hmlShowdown[i][j] = 0;
			}
		}
		for (int i = 0; i < HML_TURN_SIZE; i++) {
			for (int j = 0; j < DRAW_HANDS_SIZE; j++) {
				hhmlDraw[i][j] = 0;
				hhmlShowdown[i][j] = 0;
			}
			for (int j = 0; j < MADE_HANDS_SIZE; j++) {
				hhmlMade[i][j] = 0;
				hhmlShowdown[i][j] = 0;
			}
		}
		for (int i = 0; i < HML_RIVER_SIZE; i++) {
			for (int j = 0; j < MADE_HANDS_SIZE; j++) {
				hhhmlMade[i][j] = 0;
				hhhmlShowdown[i][j] = 0;
			}
		}
		
		for (int i = 0; i < TYPE1755_SIZE; i++) {
			for (int j = 0; j < DRAW_HANDS_SIZE; j++) {
				type1755Draw[i][j] = 0;
			}
			for (int j = 0; j < MADE_HANDS_SIZE; j++) {
				type1755Made[i][j] = 0;
				type1755Showdown[i][j] = 0;
			}
		}
		for (int i = 0; i < HAND_VALUE_SIZE; i++) {
			for (int j = 0; j < DRAW_HANDS_SIZE; j++) {
				handValueDraw[i][j] = 0;
			}
			for (int j = 0; j < MADE_HANDS_SIZE; j++) {
				handValueMade[i][j] = 0;
				handValueShowdown[i][j] = 0;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < DRAW_HANDS_SIZE; j++) {
				wetDryDraw[i][j] = 0;
			}
			for (int j = 0; j < MADE_HANDS_SIZE; j++) {
				wetDryMade[i][j] = 0;
				wetDryShowdown[i][j] = 0;
			}

		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < DRAW_HANDS_SIZE; j++) {
				typeDraw[i][j] = 0;
			}
			for (int j = 0; j < MADE_HANDS_SIZE; j++) {
				typeMade[i][j] = 0;
				typeShowdown[i][j] = 0;
			}
		}
		for (int i = 0; i < 36; i++) {
			for (int j = 0; j < DRAW_HANDS_SIZE; j++) {
				sumOfDraw[i][j] = 0;
			}
			for (int j = 0; j < MADE_HANDS_SIZE; j++) {
				sumOfMade[i][j] = 0;
				sumOfShowdown[i][j] = 0;
			}
		}
		for (int i = 0; i < FLOP_SIZE; i++) {
			flopArray[i] = 0;
		}

		for (int i = 0; i < DRAW_HANDS_SIZE; i++) {
			drawArrayFlop[i] = 0;
			drawArrayTurn[i] = 0;
		}
		for (int i = 0; i < MADE_HANDS_SIZE; i++) {
			madeArrayFlop[i] = 0;
			madeArrayTurn[i] = 0;
			madeArrayRiver[i] = 0;
		}
		for (int i = 0; i < POSSIBLE_SIZE; i++) {
			boardPossibleArrayFlop[i] = 0;
			boardPossibleArrayTurn[i] = 0;
		}
		for (int i = 0; i < MADE_HANDS_SIZE; i++) {
			madeHandArray[i] = 0;
			bestDrawArray[i] = 0;
		}
		for (int i = 0; i < POSSIBLE_SIZE; i++) {
			possibleArray[i] = 0;
		}
		for (int i = 0; i < 65; i++) {
			sumOfHandValuesFlopArray[i] = 0;
		}
		for (int i = 0; i < 39; i++) {
			sumOfBoardValuesFlopArray[i] = 0;
		}
		for (int i = 0; i < 26; i++) {
			sumOfHoleCardValuesArray[i] = 0;

		}
		for (int i = 0; i < 39; i++) {
			flopSumOfDigitsArray[i] = 0;
		}
		for (int i = 0; i < HML_FLOP_SIZE; i++) {
			hmlArrayFlop[i] = 0;
		}
		for (int i = 0; i < HML_TURN_SIZE; i++) {
			hmlArrayTurn[i] = 0;
		}
		for (int i = 0; i < HML_RIVER_SIZE; i++) {
			hmlArrayRiver[i] = 0;
		}
		for (int i = 0; i < 4; i++) {
			typeArray[i] = 0;
		}
		for (int i = 0; i < 1755; i++) {
			flopTypeOf1755Array[i] = 0;
		}
		for (int i = 0; i < 99; i++) {// TODO
			winningHand[i] = 0;
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < PLAYERS; j++) {
				evaluateIndex[i][j] = 0;
			}
		}

		for (int i = 0; i < STREET + 1; i++) {
			for (int j = 0; j < PLAYERS; j++) {
				for (int k = 0; k < POSITIONS; k++) {
					evaluateIndexPos[i][j][k] = 0;
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			boardArrayFlopPer[i] = 0;
		}
		for (int i = 0; i < 4; i++) {
			boardArrayTurnPer[i] = 0;
		}
		for (int i = 0; i < 5; i++) {
			boardArrayRiverPer[i] = 0;
		}
		for (int i = 0; i < POSSIBLE_SIZE; i++) {
			boardPossibleArrayFlopPer[i] = 0;
			boardPossibleArrayTurnPer[i] = 0;
		}
		for (int i = 0; i < HML_FLOP_SIZE; i++) {
			hmlArrayFlopPer[i] = 0;
		}
		for (int i = 0; i < HML_TURN_SIZE; i++) {
			hmlArrayTurnPer[i] = 0;
		}
		for (int i = 0; i < HML_RIVER_SIZE; i++) {
			hmlArrayRiverPer[i] = 0;
		}
		for (int i = 0; i < 4; i++) {
			typeArrayPer[i] = 0;
		}
		for (int i = 0; i < 1755; i++) {
			flopTypeOf1755ArrayPer[i] = 0;
		}
		for (int i = 0; i < 99; i++) {// TODO
			winningHandPer[i] = 0;
		}
	}

	// Flop board analysis - hole cards not included
	static int[] boardArrayFlop = new int[BOARD_ARRAY_SIZE];
	static int[] boardArrayTurn = new int[BOARD_ARRAY_SIZE];
	static int[] boardArrayRiver = new int[BOARD_ARRAY_SIZE];
	static int boardArrayFlopCount = 0;
	static int boardArrayTurnCount = 0;
	static int boardArrayRiverCount = 0;

	static int[] flopArray = new int[FLOP_SIZE];

	static int[] drawArrayFlop = new int[DRAW_HANDS_SIZE];
	static int[] drawArrayTurn = new int[DRAW_HANDS_SIZE];

	static int[] madeArrayFlop = new int[MADE_HANDS_SIZE];
	static int[] madeArrayTurn = new int[MADE_HANDS_SIZE];
	static int[] madeArrayRiver = new int[MADE_HANDS_SIZE];

	// Board possible hands on the Flop
	static int[] boardPossibleArrayFlop = new int[POSSIBLE_SIZE];
	static int[] boardPossibleArrayTurn = new int[POSSIBLE_SIZE];

	static int flopArraysCount = 0;
	static int turnArraysCount = 0;
	static int riverArraysCount = 0;

	static int[] madeHandArray = new int[MADE_HANDS_SIZE];
	static int[] bestDrawArray = new int[BEST_HANDS_SIZE];

	static int[] possibleArray = new int[POSSIBLE_SIZE];

	static int[] sumOfHandValuesFlopArray = new int[65];
	static int[] sumOfBoardValuesFlopArray = new int[39];
	static int[] sumOfHoleCardValuesArray = new int[26];

	static int[] flopSumOfDigitsArray = new int[39];

	static int[] hmlArrayFlop = new int[HML_FLOP_SIZE];
	static int[] hmlArrayTurn = new int[HML_TURN_SIZE];
	static int[] hmlArrayRiver = new int[HML_RIVER_SIZE];

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
	static double[] boardPossibleArrayFlopPer = new double[POSSIBLE_SIZE];
	static double[] boardPossibleArrayTurnPer = new double[POSSIBLE_SIZE];

	static double[] hmlArrayFlopPer = new double[HML_FLOP_SIZE];
	static double[] hmlArrayTurnPer = new double[HML_TURN_SIZE];
	static double[] hmlArrayRiverPer = new double[HML_RIVER_SIZE];

	static double[] typeArrayPer = new double[4];

	static double[] flopTypeOf1755ArrayPer = new double[1755];

	// Showdown
	static double[] winningHandPer = new double[99]; // ?? TODO

	/*-  ******************************************************************************
	/*-
	* Used to calculate EV for hands on a street Values of hands by seat and type
	* of hand, and Win or Loose index from Evaluate for Flop Turn and River.
	* +1 because of Showdown
	 ****************************************************************************** */
	static int[][] evaluateIndex = new int[STREET + 1][PLAYERS];

	static int[][][] evaluateIndexPos = new int[STREET + 1][PLAYERS][POSITIONS];

	/*-  ******************************************************************************
	 * Evaluation arrays
	 * All of these arrays use some characteristic of the Flop, Turn, or River
	 * board to evaluate what happens with a draw, made hand, or Showdown.
	 ****************************************************************************** */
	static int[][] hmlDraw = new int[HML_FLOP_SIZE][DRAW_HANDS_SIZE];
	static int[][] hmlMade = new int[HML_FLOP_SIZE][MADE_HANDS_SIZE];
	static int[][] hmlShowdown = new int[HML_FLOP_SIZE][MADE_HANDS_SIZE];

	static int[][] hhmlDraw = new int[HML_TURN_SIZE][DRAW_HANDS_SIZE];
	static int[][] hhmlMade = new int[HML_TURN_SIZE][MADE_HANDS_SIZE];
	static int[][] hhmlShowdown = new int[HML_TURN_SIZE][MADE_HANDS_SIZE];

	static int[][] hhhmlMade = new int[HML_RIVER_SIZE][MADE_HANDS_SIZE];
	static int[][] hhhmlShowdown = new int[HML_RIVER_SIZE][MADE_HANDS_SIZE];

	static int[][] typeDraw = new int[4][DRAW_HANDS_SIZE];
	static int[][] typeMade = new int[4][MADE_HANDS_SIZE];
	static int[][] typeShowdown = new int[4][MADE_HANDS_SIZE];

	static int[][] sumOfDraw = new int[36][DRAW_HANDS_SIZE];
	static int[][] sumOfMade = new int[36][MADE_HANDS_SIZE];
	static int[][] sumOfShowdown = new int[36][MADE_HANDS_SIZE];

	static int[][] wetDryDraw = new int[4][DRAW_HANDS_SIZE];
	static int[][] wetDryMade = new int[4][MADE_HANDS_SIZE];
	static int[][] wetDryShowdown = new int[4][MADE_HANDS_SIZE];

	static int[][] type1755Draw = new int[TYPE1755_SIZE][DRAW_HANDS_SIZE];
	static int[][] type1755Made = new int[TYPE1755_SIZE][MADE_HANDS_SIZE];
	static int[][] type1755Showdown = new int[TYPE1755_SIZE][MADE_HANDS_SIZE];

	static int[][] flopIndexDraw = new int[FLOP_INDEX_SIZE][DRAW_HANDS_SIZE];
	static int[][] flopIndexMade = new int[FLOP_INDEX_SIZE][MADE_HANDS_SIZE];
	static int[][] flopIndexShowdown = new int[FLOP_INDEX_SIZE][MADE_HANDS_SIZE];

	static int[][] handValueDraw = new int[HAND_VALUE_SIZE][DRAW_HANDS_SIZE];
	static int[][] handValueMade = new int[HAND_VALUE_SIZE][MADE_HANDS_SIZE];
	static int[][] handValueShowdown = new int[HAND_VALUE_SIZE][MADE_HANDS_SIZE];

	/*-  ******************************************************************************
	* Number of hands counted, not games
	 ****************************************************************************** */
	private static int hmlCount = 0;
	private static int hhmlCount = 0;
	private static int hhhmlCount = 0;
	private static int typeCount = 0;	
	private static int wetDryCount = 0;
	private static int type1755Count = 0;
	

	private ManyData() {
		throw new IllegalStateException("Utility class");
	}
}
