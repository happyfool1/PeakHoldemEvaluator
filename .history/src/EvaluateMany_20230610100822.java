//package evaluate_streets;

/*-  ******************************************************************************
 * This Class simply updates the arrays in the Class ManyData.
* 
* It is currently being modified to better support both GTO, ABC play, and exploitation.
* Also new ideas such as HML analysis.
* 
* Public methods - In the order that they should normally be called
*
* Most of the methods are private and called as a result of calling one of the public methods.
* 
* The Flop board is evaluated first. Results are placed in boardArray.
* The Flop board is evaluated for many basic things like rainbow, paired, suited, gaps, etc..
* 
* Then the hole cards are evaluated for one player, in seatNumber. 
*  
* 
* @author PEAK_
 ****************************************************************************** */

public class EvaluateMany implements Constants {

	private EvaluateMany() {
		throw new IllegalStateException("Utility class");
	}

	/*-  ****************************************************************************
	 * Update Flop data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateFlop() {
		++ManyData.hmlCount;
		++ManyData.typeCount;
		++ManyData.wetDryCount;
		++ManyData.type1755Count;
		++ManyData.flopIndexCount;
		++ManyData.handValueCount;
		++ManyData.sumOfDrawCount;
		++ManyData.sumOfMadeCount;
		++ManyData.sumOfShowdownCount;
		++ManyData.boardArrayFlopCount;
		++ManyData.boardArrayTurnCount;
		++ManyData.boardArrayRiverCount;
		++ManyData.flopArraysCount;

		int index = EvalData.hmlIndexFlop;

		for (int i = DRAW_SIZE - 1; i >= 0; i--) {
			if (EvalData.drawArray[i]) {
				ManyData.hmlDraw[index][i]++;
				break;
			}
		}
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				ManyData.hmlMade[index][i]++;
				if (i == MADE_BOARD_SET){
			//	System.out.println("yyy "+ ManyData.hmlMade[index][i]+ " " + i + " " +index + " " + EvalData.board[0] +
			//	" " + EvalData.board[1] + " " + EvalData.board[2]);
			
				}
				break;
			}
		}

		++ManyData.flopArraysCount;
		++ManyData.boardArrayFlopCount;
	}

	/*-  ******************************************************************************
	 * Update Turn data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateTurn() {
		++ManyData.hhmlCount;
		++ManyData.turnArraysCount;

		int index = EvalData.hmlIndexTurn;
		for (int i = DRAW_SIZE - 1; i >= 0; i--) {
			if (EvalData.drawArray[i]) {
				++ManyData.hhmlDraw[index][i];
				break;
			}
		}
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				++ManyData.hhmlMade[index][i];
				break;
			}
		}
	}

	/*-  ******************************************************************************
	 * Update River data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateRiver() {
		++ManyData.hhhmlCount;
		++ManyData.riverArraysCount;

		int index = EvalData.hmlIndexRiver;
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				++ManyData.hhhmlMade[index][i];
				break;
			}
		}

	}

	/*-  ******************************************************************************
	 * Update Showdown data in ManyData  
	 ****************************************************************************** */
	static void updateShowdown() {

		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i] && EvalData.playerWonShowdown[i]) {
				if (EvalData.hmlIndexFlop != -1) {
					++ManyData.hmlShowdownWon[EvalData.hmlIndexFlop][EvalData.showdownHand[i]];
				}
				if (EvalData.hmlIndexTurn != -1) {
					++ManyData.hhmlShowdownWon[EvalData.hmlIndexTurn][EvalData.showdownHand[i]];
				}
				if (EvalData.hmlIndexRiver != -1) {
					++ManyData.hhhmlShowdownWon[EvalData.hmlIndexRiver][EvalData.showdownHand[i]];
				}
				++ManyData.showdownCount;
			}
		}

	}

	/*-  ******************************************************************************
	 * Initialize data in ManyData prior to calculating 
	 ****************************************************************************** */
	static void initializeManyData() {
		ManyData.flopArraysCount = 0;
		ManyData.turnArraysCount = 0;
		ManyData.riverArraysCount = 0;
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
