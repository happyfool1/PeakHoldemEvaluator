//package evaluate_streets;

/*- ****************************************************************************
 * 
 * @author PEAK_
*******************************************************************************/
public class IndexArrays implements Constants {

	/*-  **************************************************************************
	* This is a data holding Class. 
	* It contains many arrays that are used to evaluate 3 types of hands or draws:
	* 1. Draws  
	* 2. Made Hands
	* 3. Winning Showdown hands. 
	* 
	* The first index int the arrays is some integer created from analyzing the board 
	* or analyzing the hand ( board + hole cards )
	* For example: HML is an index created by looking at the board cards and assigning 
	* a value based on each cards value, high H is A - 10, Middle M is 9 -6, 
	* and  low L is 5 - 2.
	* This is an experiment in determining is there is a simple way to classify a board
	* or hand without the ambiguity of Wet / Dry. ( Everyone has their own definition )
	* but almost all believe that their strategy should be adjusted based on wet / dry.
	* ( or coordinated, or static / dynamic, or .....)
	* HML for example is simple to derive, not subjective, has a precise definition, and
	* results in a small number of indexes.
	* Three arrays in EvalData are used, madeArray, drawArray, and showdownHand. 
	*
	* The arrays are updated:
	* 1.Initialized to 0
	* 2.Updated for each hand played
	* 3.Updated at Showdown.
	* 4.Updated after all hands have been played ( summary arrays )
	*  
	* The only method in this class is initialize.
	* EvaluateMany does the other updates.
	* Classification calculates indexes.
	****************************************************************************** */

	/*-  ****************************************************************************
	 * Initialize for a new run
	 ****************************************************************************** */
	static void initialize() {
		for (int i = 0; i < HML_FLOP_SIZE; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				hmlDrawFlop[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				hmlMadeFlop[i][j] = 0;
				hmlShowdownFlop[i][j] = 0;
				hmlShowdownMadeWinsFlop[i][j] = 0;
			}
		}
		for (int i = 0; i < HML_TURN_SIZE; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				hmlDrawTurn[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				hmlMadeTurn[i][j] = 0;
				hmlShowdownTurn[i][j] = 0;
				hmlShowdownMadeWinsTurn[i][j] = 0;
			}
		}
		for (int i = 0; i < HML_RIVER_SIZE; i++) {
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				hmlMadeRiver[i][j] = 0;
				hmlShowdownRiver[i][j] = 0;
				hmlShowdownMadeWinsRiver[i][j] = 0;
			}
		}

		// Wet / Dry
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				wetDryDrawFlop[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				wetDryMadeFlop[i][j] = 0;
				wetDryShowdownFlop[i][j] = 0;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				wetDryDrawTurn[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				wetDryMadeTurn[i][j] = 0;
				wetDryShowdownTurn[i][j] = 0;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				wetDryDrawRiver[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				wetDryMadeRiver[i][j] = 0;
				wetDryShowdownRiver[i][j] = 0;
			}
		}

		// typeOf1755
		for (int i = 0; i < TYPE_OF_1755_SIZE; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				typeOf1755DrawFlop[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				typeOf1755MadeFlop[i][j] = 0;
				typeOf1755ShowdownFlop[i][j] = 0;
			}
		}
		for (int i = 0; i < TYPE_OF_1755_SIZE; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				typeOf1755DrawTurn[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				typeOf1755MadeTurn[i][j] = 0;
				typeOf1755ShowdownTurn[i][j] = 0;
			}
		}
		for (int i = 0; i < TYPE_OF_1755_SIZE; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				typeOf1755DrawRiver[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				typeOf1755MadeRiver[i][j] = 0;
				typeOf1755ShowdownRiver[i][j] = 0;
			}
		}

		for (int i = 0; i < HAND_VALUE_SIZE; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				handValueDrawFlop[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				handValueMadeFlop[i][j] = 0;
				handValueShowdownFlop[i][j] = 0;
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				typeDrawFlop[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				typeMadeFlop[i][j] = 0;
				typeShowdownFlop[i][j] = 0;
			}
		}
		for (int i = 0; i < 36; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				sumOfDrawFlop[i][j] = 0;
			}
			for (int j = 0; j < MADE_SHORT_SIZE; j++) {
				sumOfMadeFlop[i][j] = 0;
				sumOfShowdownFlop[i][j] = 0;
			}
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

		for (int i = 0; i < MADE_SHORT_SIZE; i++) {
			winningHand[i] = 0;
		}

		for (int i = 0; i < PLAYERS; i++) {
			drawFlop[i] = 0;
			drawTurn[i] = 0;
			madeFlop[i] = 0;
			madeTurn[i] = 0;
			madeRiver[i] = 0;
		}

		for (int i = 0; i < HML_FLOP_SIZE; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				for
				

				madeFlopToMade[i][j] = 0;
				madeTurnToMade[i][j] = 0;
				madeRiverToMade[i][j] = 0;
			}
		}

		for (int i = 0; i < PLAYERS; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				drawFlopToMade[i][j] = 0;
				drawTurnToMade[i][j] = 0;
			}
		}

		hmlCountFlop = 0;
		hmlCountTurn = 0;
		hmlCountRiver = 0;
		typeCount = 0;
		wetDryCount = 0;
		typeOf1755Count = 0;

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
	static int[][] hmlDrawFlop = new int[HML_FLOP_SIZE][DRAW_SIZE];
	static int[][] hmlMadeFlop = new int[HML_FLOP_SIZE][MADE_SHORT_SIZE];
	static int[][] hmlShowdownFlop = new int[HML_FLOP_SIZE][MADE_SHORT_SIZE];

	static int[][] hmlDrawTurn = new int[HML_TURN_SIZE][DRAW_SIZE];
	static int[][] hmlMadeTurn = new int[HML_TURN_SIZE][MADE_SHORT_SIZE];
	static int[][] hmlShowdownTurn = new int[HML_TURN_SIZE][MADE_SHORT_SIZE];

	static int[][] hmlMadeRiver = new int[HML_RIVER_SIZE][MADE_SHORT_SIZE];
	static int[][] hmlShowdownRiver = new int[HML_RIVER_SIZE][MADE_SHORT_SIZE];

	// Only update if hmlMadeFlop is the hand that won
	static int[][] hmlShowdownMadeWinsFlop = new int[HML_FLOP_SIZE][MADE_SHORT_SIZE];
	static int[][] hmlShowdownMadeWinsTurn = new int[HML_TURN_SIZE][MADE_SHORT_SIZE];
	static int[][] hmlShowdownMadeWinsRiver = new int[HML_RIVER_SIZE][MADE_SHORT_SIZE];

	// Wet / Dry
	static int[][] wetDryDrawFlop = new int[4][DRAW_SIZE];
	static int[][] wetDryMadeFlop = new int[4][MADE_SHORT_SIZE];
	static int[][] wetDryShowdownFlop = new int[4][MADE_SHORT_SIZE];

	static int[][] wetDryDrawTurn = new int[4][DRAW_SIZE];
	static int[][] wetDryMadeTurn = new int[4][MADE_SHORT_SIZE];
	static int[][] wetDryShowdownTurn = new int[4][MADE_SHORT_SIZE];

	static int[][] wetDryDrawRiver = new int[4][DRAW_SIZE];
	static int[][] wetDryMadeRiver = new int[4][MADE_SHORT_SIZE];
	static int[][] wetDryShowdownRiver = new int[4][MADE_SHORT_SIZE];

	// typeOf1755
	static int[][] typeOf1755DrawFlop = new int[TYPE_OF_1755_SIZE][DRAW_SIZE];
	static int[][] typeOf1755MadeFlop = new int[TYPE_OF_1755_SIZE][MADE_SHORT_SIZE];
	static int[][] typeOf1755ShowdownFlop = new int[TYPE_OF_1755_SIZE][MADE_SHORT_SIZE];

	static int[][] typeOf1755DrawTurn = new int[TYPE_OF_1755_SIZE][DRAW_SIZE];
	static int[][] typeOf1755MadeTurn = new int[TYPE_OF_1755_SIZE][MADE_SHORT_SIZE];
	static int[][] typeOf1755ShowdownTurn = new int[TYPE_OF_1755_SIZE][MADE_SHORT_SIZE];

	static int[][] typeOf1755DrawRiver = new int[TYPE_OF_1755_SIZE][DRAW_SIZE];
	static int[][] typeOf1755MadeRiver = new int[TYPE_OF_1755_SIZE][MADE_SHORT_SIZE];
	static int[][] typeOf1755ShowdownRiver = new int[TYPE_OF_1755_SIZE][MADE_SHORT_SIZE];

	// TypeDraw
	static int[][] typeDrawFlop = new int[4][DRAW_SIZE];
	static int[][] typeMadeFlop = new int[4][MADE_SHORT_SIZE];
	static int[][] typeShowdownFlop = new int[4][MADE_SHORT_SIZE];

	static int[][] typeDrawTurn = new int[4][DRAW_SIZE];
	static int[][] typeMadeTurn = new int[4][MADE_SHORT_SIZE];
	static int[][] typeShowdownTurn = new int[4][MADE_SHORT_SIZE];

	static int[][] typeDrawRiver = new int[4][DRAW_SIZE];
	static int[][] typeMadeRiver = new int[4][MADE_SHORT_SIZE];
	static int[][] typeShowdownRiver = new int[4][MADE_SHORT_SIZE];

	// SumOf
	static int[][] sumOfDrawFlop = new int[36][DRAW_SIZE];
	static int[][] sumOfMadeFlop = new int[36][MADE_SHORT_SIZE];
	static int[][] sumOfShowdownFlop = new int[36][MADE_SHORT_SIZE];

	static int[][] sumOfDrawTurn = new int[36][DRAW_SIZE];
	static int[][] sumOfHoleCardsMadeTurn = new int[36][MADE_SHORT_SIZE];
	static int[][] sumOfShowdownTurn = new int[36][MADE_SHORT_SIZE];

	static int[][] sumOfDrawRiver = new int[36][DRAW_SIZE];
	static int[][] sumOfMadeRiver = new int[36][MADE_SHORT_SIZE];
	static int[][] sumOfShowdownRiver = new int[36][MADE_SHORT_SIZE];

	// flopIndex
	static int[][] flopIndexDrawFlop = new int[FLOP_INDEX_SIZE][DRAW_SIZE];
	static int[][] flopIndexMadeFlop = new int[FLOP_INDEX_SIZE][MADE_SHORT_SIZE];
	static int[][] flopIndexShowdownFlop = new int[FLOP_INDEX_SIZE][MADE_SHORT_SIZE];

	static int[][] flopIndexDrawTurn = new int[FLOP_INDEX_SIZE][DRAW_SIZE];
	static int[][] flopIndexMadeTurn = new int[FLOP_INDEX_SIZE][MADE_SHORT_SIZE];
	static int[][] flopIndexShowdownTurn = new int[FLOP_INDEX_SIZE][MADE_SHORT_SIZE];

	static int[][] flopIndexDrawRiver = new int[FLOP_INDEX_SIZE][DRAW_SIZE];
	static int[][] flopIndexMadeRiver = new int[FLOP_INDEX_SIZE][MADE_SHORT_SIZE];
	static int[][] flopIndexShowdownRiver = new int[FLOP_INDEX_SIZE][MADE_SHORT_SIZE];

	// handValue
	static int[][] handValueDrawFlop = new int[HAND_VALUE_SIZE][DRAW_SIZE];
	static int[][] handValueMadeFlop = new int[HAND_VALUE_SIZE][MADE_SHORT_SIZE];
	static int[][] handValueShowdownFlop = new int[HAND_VALUE_SIZE][MADE_SHORT_SIZE];

	static int[][] handValueDrawTurn = new int[HAND_VALUE_SIZE][DRAW_SIZE];
	static int[][] handValueMadeTurn = new int[HAND_VALUE_SIZE][MADE_SHORT_SIZE];
	static int[][] handValueShowdownTurn = new int[HAND_VALUE_SIZE][MADE_SHORT_SIZE];

	static int[][] handValueDrawRiver = new int[HAND_VALUE_SIZE][DRAW_SIZE];
	static int[][] handValueMadeRiver = new int[HAND_VALUE_SIZE][MADE_SHORT_SIZE];
	static int[][] handValueShowdownRiver = new int[HAND_VALUE_SIZE][MADE_SHORT_SIZE];

	/*-  ************************************************************************************* 
	 * Best made hand or draw for player on this street
	  ************************************************************************************** */
	static int[] drawFlop = new int[PLAYERS];
	static int[] drawTurn = new int[PLAYERS];
	static int[] madeFlop = new int[PLAYERS];
	static int[] madeTurn = new int[PLAYERS];
	static int[] madeRiver = new int[PLAYERS];

	/*-  ************************************************************************************
	 * These arrays are  used to find out how frequently a draw or made hand on the Flop 
	 * or Turn makes what made hand on the River or wins at Showdown.
	 * Indexes are:
	 * 		hmlIndex or hhmlIndex
	 * 		Made hand or Draw on ( Flop, Turn)
	 * 		Made hand on River or made hand that won showdown.
	 * The value in the third index is the number of times that the hand in the second index was
	 * made on the River or won at Showdown
	 ************************************************************************************** */
	static int[][][] hmlDrawFlopToMadRiver = new int[HML_FLOP_SIZE][DRAW_SIZE][MADE_SIZE];
	static int[][][]  hmlDrawTurnToMadRiver  = new int[HML_TURN_SIZE][DRAW_SIZE][MADE_SIZE];
	static int[][][] hmlMadeFlopToMadeRiver = new int[HML_FLOP_SIZE][MADE_SIZE][MADE_SIZE];
	static int[][][] hmlMadeTurnToMadeRiver = new int[HML_TURN_SIZE][MADE_SIZE][MADE_SIZE];

	static int[][][] hmlDrawFlopToMadWon = new int[HML_FLOP_SIZE][DRAW_SIZE][MADE_SIZE];
	static int[][][] hmlDrawTurnToMadeWon = new int[HML_TURN_SIZE][DRAW_SIZE][MADE_SIZE];
	static int[][][] hmlMadeFlopToMadeWon = new int[HML_FLOP_SIZE][MADE_SIZE][MADE_SIZE];
	static int[][][] hmlMadeTurnToMadeWon = new int[HML_TURN_SIZE][MADE_SIZE][MADE_SIZE];

	/*-  ***********************************************************************************
	* Number of hands counted, not games
	*************************************************************************************  */
	static int hmlCountFlop = 0;
	static int hmlCountTurn = 0;
	static int hmlCountRiver = 0;
	static int wetDryCount = 0;
	static int typeOf1755Count = 0;

	static int type1755Count = 0;
	static int flopIndexCount = 0;
	static int handValueCount = 0;
	static int sumOfDrawCount = 0;
	static int sumOfMadeCount = 0;
	static int typeCount = 0;
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

	private IndexArrays() {
		throw new IllegalStateException("Utility class");
	}
}
