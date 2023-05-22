//package evaluate_streets;

public class Possible implements Constants {
	/*- ******************************************************************************
	 * Data in this Class is used what is being analyzed
	 * Indirectly, a part of strategy data.
	 * @author PEAK_
	*******************************************************************************/

	private Possible() {
		throw new IllegalStateException("Utility class");
	}

	/*- *****************************************************************************
	 * This method is called after several other methods that parse the hand.
	 *  
	 * In particular, drawAndMadeArray is the result of analyzing the board combined with
	 * hole cards for one player in seat.
	 * DrawHands.
	 * 
	 * It is possible only if can make it with next card
	 * 
	 * This method will use boardArray ( the result of evaluating a Flop board ) as
	 * input and determine what are the possible draw that any player might have
	 * from this board. boardPossibleArray in EvalData is updated,
	 * boardPossibleArrayFlopMax in EvalData is set to the index value of the
	 * strongest draw possible.
	  ******************************************************************************/
	static void boardPossiblesFlop() {
		for (int i = 0; i < POSSIBLE_MAX; i++) {
			EvalData.boardPossibleArrayFlop[i] = false;
		}
		EvalData.boardPossibleArrayFlopMax = -1;

		// if (EvalData.boardArray[BOARD_GAP0]) {
		// EvalData.boardPossibleArrayFlop[POSSIBLE_OESD] = true;
		// EvalData.boardPossibleArrayFlop[POSSIBLE_GUTSHOT] = true;
		// }

		if (EvalData.boardArray[BOARD_PAIR]) {
			EvalData.boardPossibleArrayFlop[POSSIBLE_TWO_PAIR] = true;
			EvalData.boardPossibleArrayFlop[POSSIBLE_SET] = true;
			EvalData.boardPossibleArrayFlop[POSSIBLE_FOUR_OF_A_KIND] = true;
		}

		// if (EvalData.boardArray[BOARD_2F]) {
		// if (EvalData.boardPossibleArrayFlop[POSSIBLE_GUTSHOT]) {
		// EvalData.boardPossibleArrayFlop[POSSIBLE_FLUSH_DRAW_GUTSHOT] = true;
		// }
		// if (EvalData.boardPossibleArrayFlop[POSSIBLE_OESD]) {
		// EvalData.boardPossibleArrayFlop[POSSIBLE_FLUSH_DRAW_OESD] = true;
		// }
		// }

		// if (EvalData.boardArray[BOARD_GAP0] || EvalData.boardArray[BOARD_GAP1] ||
		// EvalData.boardArray[BOARD_GAP2]) {
		// EvalData.boardPossibleArrayFlop[POSSIBLE_GUTSHOT] = true;
		// EvalData.boardPossibleArrayFlop[POSSIBLE_GUTSHOT_HIGH] = true;
		// EvalData.boardPossibleArrayFlop[POSSIBLE_GUTSHOT_PAIR] = true;
		// }

		// if (EvalData.boardArray[BOARD_GAP0] || EvalData.boardArray[BOARD_GAP1] ||
		// EvalData.boardArray[BOARD_GAP2]) {
		// EvalData.boardPossibleArrayFlop[POSSIBLE_OESD] = true;
		// EvalData.boardPossibleArrayFlop[POSSIBLE_OESD_PAIR] = true;
		// EvalData.boardPossibleArrayFlop[POSSIBLE_STRAIGHT] = true;
		// }

		if (EvalData.boardArray[BOARD_3F]) {
			EvalData.boardPossibleArrayFlop[POSSIBLE_FLUSH] = true;
			// EvalData.boardPossibleArrayFlop[POSSIBLE_FLUSH_DRAW] = true;
			// EvalData.boardPossibleArrayFlop[POSSIBLE_FLUSH_DRAW_PAIR] = true;
			// if (EvalData.boardPossibleArrayFlop[OESD]) {
			// EvalData.boardPossibleArrayFlop[POSSIBLE_STRAIGHT_FLUSH] = true;
			// // TODO check this
			// if (EvalData.boardValue1 >= 8 && EvalData.boardValue2 >= 8 &&
			// EvalData.boardValue3 >= 8) {
			// EvalData.boardPossibleArrayFlop[POSSIBLE_ROYAL_FLUSH] = true;
			// }
			// }
		}

		EvalData.boardPossibleArrayFlop[POSSIBLE_FOUR_OF_A_KIND] = true;
		EvalData.boardPossibleArrayFlop[POSSIBLE_FOUR_OF_A_KIND] = true;
		EvalData.boardPossibleArrayFlop[POSSIBLE_FULL_HOUSE] = true;
	}

	/*- *****************************************************************************
	 * This method is called after several other methods. 
	 * In particular, drawAndMadeArray is the result of analyzing the board combined with
	 * hole cards for one player in seat.
	 * Possible only if can make it with next card
	 * 
	 * This method will use boardArray ( the result of evaluating a Flop board ) as
	 * input and determine what are the possible draw that any player might have
	 * from this board. boardPossibleArray in EvalData is updated,
	 * boardPossibleArrayFlopMax in EvalData is set to the index value of the
	 * strongest draw possible.
	  ******************************************************************************/
	static void boardPossiblesTurn() {
		for (int i = 0; i < POSSIBLE_MAX; i++) {
			EvalData.boardPossibleArrayTurn[i] = false;
		}
		EvalData.boardPossibleArrayTurnMax = -1;

		if (EvalData.boardArray[BOARD_GAP0]) {
			// EvalData.boardPossibleArrayTurn[POSSIBLE_OESD] = true;
			// EvalData.boardPossibleArrayTurn[POSSIBLE_GUTSHOT] = true;
		}

		if (EvalData.boardArray[BOARD_PAIR]) {
			EvalData.boardPossibleArrayTurn[POSSIBLE_TWO_PAIR] = true;
			EvalData.boardPossibleArrayTurn[POSSIBLE_SET] = true;
			EvalData.boardPossibleArrayTurn[POSSIBLE_FOUR_OF_A_KIND] = true;
		}

		if (EvalData.boardArray[BOARD_2F]) {
			// if (EvalData.boardPossibleArrayTurn[POSSIBLE_GUTSHOT]) {
			// EvalData.boardPossibleArrayTurn[POSSIBLE_FLUSH_DRAW_GUTSHOT] = true;
		}
		// if (EvalData.boardPossibleArrayTurn[POSSIBLE_OESD]) {
		// EvalData.boardPossibleArrayTurn[POSSIBLE_FLUSH_DRAW_OESD] = true;
		// }
		// }

		//// if (EvalData.boardArray[BOARD_GAP0] || EvalData.boardArray[BOARD_GAP1] ||
		//// EvalData.boardArray[BOARD_GAP2]) {
		// EvalData.boardPossibleArrayTurn[POSSIBLE_GUTSHOT] = true;
		// EvalData.boardPossibleArrayTurn[POSSIBLE_GUTSHOT_HIGH] = true;
		// EvalData.boardPossibleArrayTurn[POSSIBLE_GUTSHOT_PAIR] = true;
		// }

		// if (EvalData.boardArray[BOARD_GAP0] || EvalData.boardArray[BOARD_GAP1] ||
		// EvalData.boardArray[BOARD_GAP2]) {
		// EvalData.boardPossibleArrayTurn[POSSIBLE_OESD] = true;
		// EvalData.boardPossibleArrayTurn[POSSIBLE_OESD_PAIR] = true;
		// EvalData.boardPossibleArrayTurn[POSSIBLE_STRAIGHT] = true;
		// }

		if (EvalData.boardArray[BOARD_3F]) {
			// EvalData.boardPossibleArrayTurn[POSSIBLE_FLUSH] = true;
			// EvalData.boardPossibleArrayTurn[POSSIBLE_FLUSH_DRAW] = true;
			// EvalData.boardPossibleArrayTurn[POSSIBLE_FLUSH_DRAW_PAIR] = true;
			// final boolean condition = EvalData.boardPossibleArrayTurn[OESD] &&
			// EvalData.boardValue1 >= 8
			// && EvalData.boardValue2 >= 8 && EvalData.boardValue3 >= 8;
			// check this
			// if (condition) {
			// EvalData.boardPossibleArrayFlop[POSSIBLE_ROYAL_FLUSH] = true;
			// }
		}

		EvalData.boardPossibleArrayTurn[POSSIBLE_FOUR_OF_A_KIND] = true;
		EvalData.boardPossibleArrayTurn[POSSIBLE_FOUR_OF_A_KIND] = true;
		EvalData.boardPossibleArrayTurn[POSSIBLE_FULL_HOUSE] = true;

	}

}
