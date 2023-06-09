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
				hmlShowdownWon[i][j] = 0;
			}
		}
		for (int i = 0; i < HML_TURN_SIZE; i++) {
			for (int j = 0; j < DRAW_HANDS_SIZE; j++) {
				hhmlDraw[i][j] = 0;
			}
			for (int j = 0; j < MADE_HANDS_SIZE; j++) {
				hhmlMade[i][j] = 0;
			}
		}
		for (int i = 0; i < HML_RIVER_SIZE; i++) {
			for (int j = 0; j < MADE_HANDS_SIZE; j++) {
				hhhmlMade[i][j] = 0;
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
		for (int i = 0; i < MADE_HANDS_SIZE; i++) {
			winningHand[i] = 0;
		}

		hmlCount = 0;
		hhmlCount = 0;
		hhhmlCount = 0;
		typeCount = 0;
		wetDryCount = 0;
		type1755Count = 0;
		flopIndexCount = 0;
		handValueCount = 0;
		sumOfDrawCount = 0;
		sumOfMadeCount = 0;
		sumOfShowdownCount = 0;
		boardArrayFlopCount = 0;
		boardArrayTurnCount = 0;
		boardArrayRiverCount = 0;
		flopArraysCount = 0;
		turnArraysCount = 0;
		riverArraysCount = 0;
		showdownCount = 0;
	}

	
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

	static int[][] hhmlDraw = new int[HML_TURN_SIZE][DRAW_HANDS_SIZE];
	static int[][] hhmlMade = new int[HML_TURN_SIZE][MADE_HANDS_SIZE];

	static int[][] hhhmlMade = new int[HML_RIVER_SIZE][MADE_HANDS_SIZE];

	static int[][] hmlShowdownWon = new int[HML_FLOP_SIZE][MADE_HANDS_SIZE];
	static int[][] hhmlShowdownWon = new int[HML_TURN_SIZE][MADE_HANDS_SIZE];
	static int[][] hhhmlShowdownWon = new int[HML_RIVER_SIZE][MADE_HANDS_SIZE];

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
	static int hmlCount = 0;
	static int hhmlCount = 0;
	static int hhhmlCount = 0;
	static int typeCount = 0;
	static int wetDryCount = 0;
	static int type1755Count = 0;
	static int flopIndexCount = 0;
	static int handValueCount = 0;
	static int sumOfDrawCount = 0;
	static int sumOfMadeCount = 0;
	static int sumOfShowdownCount = 0;
	static int boardArrayFlopCount = 0;
	static int boardArrayTurnCount = 0;
	static int boardArrayRiverCount = 0;
	static int flopArraysCount = 0;
	static int turnArraysCount = 0;
	static int riverArraysCount = 0;

	// Showdown
	static int showdownCount = 0;
	static int[] winningHand = new int[99]; // ?? TODO

	private ManyData() {
		throw new IllegalStateException("Utility class");
	}
}
