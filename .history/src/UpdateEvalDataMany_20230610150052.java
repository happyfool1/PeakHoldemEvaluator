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

public class UpdateEvalDataMany implements Constants {

	private UpdateEvalDataMany() {
		throw new IllegalStateException("Utility class");
	}

	/*-  ****************************************************************************
	 * Update Flop data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateFlop() {
		++EvalDataMany.hmlCountFlop;
		++EvalDataMany.typeCount;
		++EvalDataMany.wetDryCount;
		++EvalDataMany.type1755Count;
		++EvalDataMany.flopIndexCount;
		++EvalDataMany.handValueCount;
		++EvalDataMany.sumOfDrawCount;
		++EvalDataMany.sumOfMadeCount;
		++EvalDataMany.sumOfShowdownCount;
		++EvalDataMany.boardArrayFlopCount;
		++EvalDataMany.boardArrayTurnCount;
		++EvalDataMany.boardArrayRiverCount;
		++EvalDataMany.flopArraysCount;

		int index = EvalData.hmlIndexFlop;

		for (int i = DRAW_SIZE - 1; i >= 0; i--) {
			if (EvalData.drawArray[i]) {
				EvalDataMany.hmlDrawFlop[index][i]++;
				break;
			}
		}
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				EvalDataMany.hmlMadeFlop[index][i]++;
				if (i == MADE_BOARD_SET) {
					// System.out.println("yyy "+ ManyData.hmlMade[index][i]+ " " + i + " " +index +
					// " " + EvalData.board[0] +
					// " " + EvalData.board[1] + " " + EvalData.board[2]);

				}
				break;
			}
		}

		++EvalDataMany.flopArraysCount;
		++EvalDataMany.boardArrayFlopCount;
	}

	/*-  ******************************************************************************
	 * Update Turn data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateTurn() {
		++EvalDataMany.hmlCountTurn;
		++EvalDataMany.turnArraysCount;

		int index = EvalData.hmlIndexTurn;
		for (int i = DRAW_SIZE - 1; i >= 0; i--) {
			if (EvalData.drawArray[i]) {
				++EvalDataMany.hmlDrawTurn[index][i];
				break;
			}
		}
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				++EvalDataMany.hmlMadeTurn[index][i];
				break;
			}
		}
	}

	/*-  ******************************************************************************
	 * Update River data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateRiver() {
		++EvalDataMany.hmlCountRiver;
		++EvalDataMany.riverArraysCount;

		int index = EvalData.hmlIndexRiver;
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				++EvalDataMany.hmlMadeRiver[index][i];
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
					++EvalDataMany.hmlShowdownFlop[EvalData.hmlIndexFlop][EvalData.showdownHand[i]];
				}
				if (EvalData.hmlIndexTurn != -1) {
					++EvalDataMany.hmlShowdownTurn[EvalData.hmlIndexTurn][EvalData.showdownHand[i]];
				}
				if (EvalData.hmlIndexRiver != -1) {
					++EvalDataMany.hmlShowdownRiver[EvalData.hmlIndexRiver][EvalData.showdownHand[i]];
				}
				++EvalDataMany.showdownCount;
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
		for (int i = 0; i < HML_FLOP_SIZE; i++) {
			for (int j = 0; j < DRAW_SIZE; j++) {
				EvalDataMany.hmlDrawRowTotalFlop[i] += EvalDataMany.hmlDrawFlop[i][j];
				EvalDataMany.hmlDrawSumOfAllValuesFlop += EvalDataMany.hmlDrawFlop[i][j];
			}
		}
		for (int j = 0; j < DRAW_SIZE; j++) {
			for (int i = 0; i < HML_FLOP_SIZE; i++) {
				EvalDataMany.hmlDrawColTotalFlop[i] += EvalDataMany.hmlDrawFlop[i][j];
			}
		}
		for (int i = 0; i < HML_FLOP_SIZE; i++) {
			for (int j = 0; j < MADE_SIZE; j++) {
				EvalDataMany.hmlMadeRowTotalFlop[i] += EvalDataMany.hmlCountMadeFlop[i][j];
				EvalDataMany.hmlMadeSumOfAllValues += EvalDataMany.hmlMadeFlop[i][j];
			}
		}
		for (int j = 0; j < MADE_SIZE; j++) {
			for (int i = 0; i < HML_TURN_SIZE; i++) {
				EvalDataMany.hmlMadeColTotalTurn[i] += EvalDataMany.hmlMadeTurn[i][j];
						}
		}
		for (int i = 0; i < HML_TURN_SIZE; i++) {
			for (int j = 0; j < MADE_SIZE; j++) {
				EvalDataMany.hmlShowdownRowTotalTurn[i] += EvalDataMany.hmlShowdownTurn[i][j];
				EvalDataMany.hmlShowdownSumOfAllValuesTurn += EvalDataMany.hmlShowdownTurn[i][j];
			}
		}
		for (int j = 0; j < MADE_SIZE; j++) {
			for (int i = 0; i < HML_TURN_SIZE; i++) {
				EvalDataMany.hmlShowdownColTotalTurn[i] += EvalDataMany.hmlShowdownTurn[i][j];
			}
		}

	}

	/*-  ******************************************************************************
	 * Initialize data in ManyData prior to calculating 
	 ****************************************************************************** */
	static void initializeManyData() {
		EvalDataMany.flopArraysCount = 0;
		EvalDataMany.turnArraysCount = 0;
		EvalDataMany.riverArraysCount = 0;
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
