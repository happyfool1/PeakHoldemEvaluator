//package evaluate_streets;

/*-  ******************************************************************************
 * This Class simply updates the arrays in the Class ManyData.
 * It is called after each player completes a street, at showdown, and after all hands 
 * have been played. 
* 
* The public methods are:
* 1. updateFlop()
* 2. updateTurn()
* 3. updateRiver()
* 4. updateShowdowns()
* 5. updateSummary()
*
* The first 4 update arrays using several indexes and and values from:
* EvalData.drawArray, EvalData,madeArray, EvalData.showdownHand.
*
* summary performs some simple calculations such as adding all of the rows in an array.
*  
* 
* @author PEAK_
 ****************************************************************************** */

public class IndexArrayUpdate implements Constants {

	private IndexArrayUpdate() {
		throw new IllegalStateException("Utility class");
	}

	/*-  ****************************************************************************
	 * Update Flop data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateFlop() {
		++IndexArrays.hmlCountFlop;
		++IndexArrays.typeCount;
		++IndexArrays.wetDryCount;
		++IndexArrays.type1755Count;
		++IndexArrays.flopIndexCount;
		++IndexArrays.handValueCount;
		++IndexArrays.sumOfDrawCount;
		++IndexArrays.sumOfMadeCount;
		++IndexArrays.sumOfShowdownCount;
		++IndexArrays.boardArrayFlopCount;
		++IndexArrays.boardArrayTurnCount;
		++IndexArrays.boardArrayRiverCount;
		++IndexArrays.flopArraysCount;

		int index = EvalData.hmlIndexFlop;

		for (int i = DRAW_SIZE - 1; i >= 0; i--) {
			if (EvalData.drawArray[i]) {
				IndexArrays.hmlDrawFlop[index][i]++;
				break;
			}
		}
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				IndexArrays.hmlMadeFlop[index][i]++;
				if (i == MADE_BOARD_SET) {
					// System.out.println("yyy "+ ManyData.hmlMade[index][i]+ " " + i + " " +index +
					// " " + EvalData.board[0] +
					// " " + EvalData.board[1] + " " + EvalData.board[2]);

				}
				break;
			}
		}

		++IndexArrays.flopArraysCount;
		++IndexArrays.boardArrayFlopCount;
	}

	/*-  ******************************************************************************
	 * Update Turn data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateTurn() {
		++IndexArrays.hmlCountTurn;
		++IndexArrays.turnArraysCount;

		int index = EvalData.hmlIndexTurn;
		for (int i = DRAW_SIZE - 1; i >= 0; i--) {
			if (EvalData.drawArray[i]) {
				++IndexArrays.hmlDrawTurn[index][i];
				break;
			}
		}
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				++IndexArrays.hmlMadeTurn[index][i];
				break;
			}
		}
	}

	/*-  ******************************************************************************
	 * Update River data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateRiver() {
		++IndexArrays.hmlCountRiver;
		++IndexArrays.riverArraysCount;

		int index = EvalData.hmlIndexRiver;
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				++IndexArrays.hmlMadeRiver[index][i];
				break;
			}
		}

	}

	/*-  ****************************************************************************
	 * Update Showdown data in ManyData  
	 ****************************************************************************** */
	static void updateShowdown() {
		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i] && EvalData.playerWonShowdown[i]) {
				if (EvalData.hmlIndexFlop != -1) {
					++IndexArrays.hmlShowdownFlop[EvalData.hmlIndexFlop][EvalData.showdownHand[i]];
				}
				if (EvalData.hmlIndexTurn != -1) {
					++IndexArrays.hmlShowdownTurn[EvalData.hmlIndexTurn][EvalData.showdownHand[i]];
				}
				if (EvalData.hmlIndexRiver != -1) {
					++IndexArrays.hmlShowdownRiver[EvalData.hmlIndexRiver][EvalData.showdownHand[i]];
				}
				++IndexArrays.showdownCount;
			}
		}
	}

	/*-  ****************************************************************************
	 * Update Summary data in ManyData  
	static int[] hhmlDrawRowTotal = new int[HML_FLOP_SIZE];
	static int[] hhmlDrawColTotal = new int[DRAW_SIZE];
	static int[] hhmlMadeRowTotal = new int[HML_FLOP_SIZE];
	static int[] hhmlMadeColTotal = new int[MADE_SIZE];
	
	static int[] hhmlDrawShowdownRowTotal = new int[HML_FLOP_SIZE];
	static int[] hhmlDrawShowdownColTotal = new int[MADE_SIZE];
	static int hmlDrawSumOfAllValues = -1; 
	static int hmlMadeSumOfAllValues = -1; 
	static int hmlShowdownumOfAllValues = -1; 
	 ****************************************************************************** */
	static void updateSummary() {
		
		for (int j = 0; j < DRAW_SIZE; j++) {
			for (int i = 0; i < HML_FLOP_SIZE; i++) {
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
		for (int i = 0; i < HML_TURN_SIZE; i++) {
			for (int j = 0; j < MADE_SIZE; j++) {
				IndexArrays.hmlShowdownRowTotalTurn[i] += IndexArrays.hmlShowdownTurn[i][j];
				IndexArrays.hmlShowdownSumOfAllValuesTurn += IndexArrays.hmlShowdownTurn[i][j];
			}
		}
		for (int j = 0; j < MADE_SIZE; j++) {
			for (int i = 0; i < HML_TURN_SIZE; i++) {
				IndexArrays.hmlShowdownColTotalTurn[j] += IndexArrays.hmlShowdownTurn[i][j];
			}
		}

		for (int j = 0; j < MADE_SIZE; j++) {
			for (int i = 0; i < HML_RIVER_SIZE; i++) {
				IndexArrays.hmlMadeColTotalRiver[j] += IndexArrays.hmlMadeRiver[i][j];
			}
		}

		for (int i = 0; i < HML_RIVER_SIZE; i++) {
			for (int j = 0; j < MADE_SIZE; j++) {
				IndexArrays.hmlShowdownRowTotalRiver[i] += IndexArrays.hmlShowdownRiver[i][j];
				IndexArrays.hmlShowdownSumOfAllValuesRiver += IndexArrays.hmlShowdownRiver[i][j];
			}
		}
		for (int j = 0; j < MADE_SIZE; j++) {
			for (int i = 0; i < HML_RIVER_SIZE; i++) {
				IndexArrays.hmlShowdownColTotalRiver[j] += IndexArrays.hmlShowdownRiver[i][j];
			}
		}

	}

	/*-  ******************************************************************************
	 * Initialize data in ManyData prior to calculating 
	 ****************************************************************************** */
	static void initializeManyData() {
		IndexArrays.flopArraysCount = 0;
		IndexArrays.turnArraysCount = 0;
		IndexArrays.riverArraysCount = 0;
	}

	/*-  ******************************************************************************
	 * Convert a boardPossibleArrayFlop  index into a drawAndMadeArrayMadeHandsFlop
	 *  Example  MIDDLE_PAIR to HAND_MIDDLE_PAIR
	 *  Arg0 - Value to convert
	 *  Returns new converted value, -1 if invalid value
	 ****************************************************************************** */
	static int possibleValueToMadeHandValue(int value) {
		return switch (value) {

			// TODO case POSSIBLE_PAIR -> MADE_PAIR;
			// TODO case POSSIBLE_TWO_PAIR -> MADE_TWO_PAIR;
			case POSSIBLE_DRAW_SET -> MADE_SET;
			case POSSIBLE_DRAW_STRAIGHT -> MADE_STRAIGHT;
			case POSSIBLE_DRAW_FLUSH -> MADE_FLUSH;
			case POSSIBLE_DRAW_FULL_HOUSE -> MADE_FULL_HOUSE;
			case POSSIBLE_DRAW_FOUR_OF_A_KIND -> MADE_FOUR_OF_A_KIND;
			case POSSIBLE_DRAW_STRAIGHT_FLUSH -> MADE_FOUR_OF_A_KIND;
			case POSSIBLE_DRAW_ROYAL_FLUSH -> MADE_ROYAL_FLUSH;
			default -> -1;
		};
	}

}
