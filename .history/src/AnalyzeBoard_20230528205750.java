//package evaluate_streets;

public class AnalyzeBoard implements Constants {
	/*- **************************************************************************** 
	* This Class contains several methods used to  gather information about 
	* the board.  
	* Flop only
	* 
	* Information obtained includes:
	* 		Board analysis on Flop, gaps, suitedness, etc..
	* 
	* @author PEAK_
	*******************************************************************************/

	private AnalyzeBoard() {
		throw new IllegalStateException("Utility class");
	}

	/*- *****************************************************************************
	 * This method will look at the board and  determine what gaps there are.
	 * 
	 * A gap of one would be Js9d ( no Tx ), card values are not consecutive. 
	 * A Ten is needed to complete the sequence, One gap.
	 * Two gaps Qs9c. 
	 * A2 is a special cace as an Ace is both a 12 and a 1.
	 * There may be multiple gaps in a board, especially a Turn board with 6 cards.
	 * River boards are not analyzed because there will be no opportunity to draw.
	 * 
	 * Gap data in EvalData is updated. These are boolean
	 * 		EvalData.gap0
	 * 		EvalData.gap1
	 * 		EvalData.gap2
	 * Data in boardArray is updated
	 *  	EvalData.boardArray[BOARD_GAP0]
	 *  	EvalData.boardArray[BOARD_GAP1]
	 *  	EvalData.boardArray[BOARD_GAP2]   
	  ******************************************************************************/
	static void boardGaps() {
		EvalData.gap0 = 0;
		EvalData.gap1 = 0;
		EvalData.gap2 = 0;

		for (int i = 0; i < EvalData.boardCount - 1; i++) {
			if (EvalData.board[i].value - EvalData.board[i + 1].value == 1) {
				++EvalData.gap0;
			} else if (EvalData.board[i].value - EvalData.board[i + 1].value == 2) {
				++EvalData.gap1;
			} else if (EvalData.board[i].value - EvalData.board[i + 1].value == 3) {
				++EvalData.gap2;
			}
		}

		if (EvalData.boardValue1 == ACE && EvalData.board[EvalData.boardCount - 1].value == TWO) {
			++EvalData.gap0;
		} else if (EvalData.boardValue1 == ACE && EvalData.board[EvalData.boardCount - 1].value == THREE) {
			++EvalData.gap1;
		} else if (EvalData.boardValue1 == ACE && EvalData.board[EvalData.boardCount - 1].value == FOUR) {
			EvalData.boardArray[BOARD_GAP2] = true;
		}

		if (EvalData.gap0 != 0) {
			EvalData.boardArray[BOARD_GAP0] = true;
		}
		if (EvalData.gap1 != 0) {
			EvalData.boardArray[BOARD_GAP1] = true;
		}
		if (EvalData.gap2 != 0) {
			EvalData.boardArray[BOARD_GAP2] = true;
		}
	}

	/*- *****************************************************************************
	 * This method will
	 * Gap score is very subjective but is an attempt to determine drawing possibilities
	 * with a combination of gaps. Gap score is used by other methods evaluatng board
	 * possibilities such as wet/dry or static /dynamic. 
	 * 
	 * Gap data in EvalData is updated:
	 *		EvalData.gap0Score  
	 *		EvalData.gap1Score   
	 * 		EvalData.gap2Score 
	  ******************************************************************************/
	static void boardGapScore() {
		EvalData.gap2Score = 0;
		EvalData.gap1Score = 0;
		EvalData.gap0Score = 0;

		if (EvalData.gap0 > 0) {
			if (EvalData.gap0 == 2) {
				EvalData.gap0Score = 8;
			}
			if (EvalData.gap0 == 1 && EvalData.gap1 == 1) {
				EvalData.gap0Score = 6;
			}
			if (EvalData.gap0 == 1 && EvalData.gap2 == 1) {
				EvalData.gap0Score = 5;
			}
			if (EvalData.gap0 == 1) {
				EvalData.gap0Score = 4;
			}
		} else if (EvalData.gap1 <= 0) {
			if (EvalData.gap2 > 0) {
				if (EvalData.gap2 == 1) {
					EvalData.gap2Score = 1;
				}
				if (EvalData.gap2 == 2) {
					EvalData.gap2Score = 2;
				}
			}
		} else {
			if (EvalData.gap1 == 1) {
				EvalData.gap1Score = 4;
			}
			if (EvalData.gap1 == 1 && EvalData.gap2 == 1) {
				EvalData.gap1Score = 6;
			}
			if (EvalData.gap1 == 2) {
				EvalData.gap1Score = 8;
			}
		}

		if (EvalData.gap0 > 0) {
			EvalData.gap0Score += 2;
			if (EvalData.boardArray[_HHH] || EvalData.boardArray[HHM]) {
				EvalData.gap0Score += 3;
			}
			if (EvalData.boardArray[MMM] || EvalData.boardArray[HMM]) {
				EvalData.gap0Score += 2;
			}
		}
		if (EvalData.gap1 <= 0) {
			return;
		}
		if (EvalData.boardArray[HHH] || (EvalData.boardArray[HHM] && EvalData.boardValue1 != ACE)) {
			EvalData.gap1Score += 2;
		}
		if (EvalData.boardArray[MMM] || EvalData.boardArray[HMM]) {
			EvalData.gap1Score += 2;
		}
		if (EvalData.boardArray[MLL] || (EvalData.boardArray[LLL] && EvalData.boardValue1 != ACE)) {
			EvalData.gap1Score += 1;
		}
	}

}
