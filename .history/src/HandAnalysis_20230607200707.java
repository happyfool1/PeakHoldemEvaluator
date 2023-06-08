//package evaluate_streets;

public class HandAnalysis implements Constants {

	/*- **************************************************************************** 
	* This Class contains several methods used to  gather information about 
	* the board and board combined with hole cards.
	* Information obtained includes:
	* 		Board analysis on Flop, gaps, suitedness, etc..
	* 		Draws available with combined board and hole cards. 
	* 		Made hands with combined board and hole cards. 
	* A data holding Classes Draw and Made is used to save the cards that make up
	* the hand and  other related information. 
	* There is one instance of Draw and Made in EvalData for each player as arrays
	* indexed by seat number. 
	* 
	* @author PEAK_
	*******************************************************************************/

	private HandAnalysis() {
		throw new IllegalStateException("Utility class");
	}

	/*- **************************************************************************** 
	* For Flop
	*******************************************************************************/
	static void doFlop() {
		if (EvalData.holeCard1.value >= TEN && EvalData.holeCard2.value >= TEN) {
			EvalData.flopArray[FLOP_POCKET_HIGH_CARDS] = true;
			EvalData.flopHighCards = true;
		}

		if ((EvalData.holeCard1.value > EvalData.board[0].value) && (EvalData.holeCard2.value > EvalData.board[0].value)
				&& (EvalData.holeCard1.value > EvalData.board[1].value)
				&& (EvalData.holeCard2.value > EvalData.board[1].value)
				&& (EvalData.holeCard1.value > EvalData.board[2].value)
				&& (EvalData.holeCard2.value > EvalData.board[2].value)
				&& (EvalData.holeCard1.value > EvalData.board[3].value)
				&& (EvalData.holeCard2.value > EvalData.board[3].value)) {
			EvalData.flopArray[FLOP_POCKET_OVERCARDS] = true; // TODO not right
			EvalData.flopOverCards = true;
		}

		if (EvalData.holeCard1.value == ACE) {
			EvalData.flopArray[FLOP_POCKET_ACE_HIGH] = true;
			EvalData.flopAceHigh = true;
		}

		// Pocket pair combinations
		if (EvalData.holeCard1.value == EvalData.holeCard2.value) {
			if (EvalData.holeCard1.value > EvalData.board[0].value && EvalData.holeCard1.value > EvalData.board[1].value
					&& EvalData.holeCard1.value > EvalData.board[2].value) {
				EvalData.flopArray[FLOP_OVER_PAIR] = true;
				EvalData.flopOverPair = true;
			} else if ((EvalData.holeCard1.value < EvalData.board[0].value)
					&& (EvalData.holeCard1.value < EvalData.board[1].value)
					&& (EvalData.holeCard1.value < EvalData.board[2].value)) {
				EvalData.flopArray[FLOP_WEAK_PAIR] = true;
				EvalData.flopWeakPair = true;
			} else {
				EvalData.flopArray[FLOP_MIDDLE_PAIR] = true;
				EvalData.flopMiddlePair = false;
			}
		}

		if (EvalData.board[0].value == EvalData.board[1].value && EvalData.board[0].value == EvalData.board[2].value
				&& EvalData.board[0].value == EvalData.board[3].value) {
			EvalData.boardArray[FLOP_BOARD_SET] = true;
			EvalData.flopSet = true;
		} else {
			if (EvalData.board[0].value == EvalData.board[1].value) {
				EvalData.boardArray[FLOP_BOARD_PAIRED] = true;
				EvalData.flopBoardPair = true;
				EvalData.madeArray[MADE_BOARD_PAIR] = true;
				EvalData.boardPairValue = EvalData.board[0].value;
			}
			if (EvalData.board[1].value == EvalData.board[2].value) {
				EvalData.boardArray[FLOP_BOARD_PAIRED] = true;
				EvalData.flopBoardPair = true;
				EvalData.madeArray[MADE_BOARD_PAIR] = true;
				EvalData.boardPairValue = EvalData.board[1].value;
			}
		}
		doBoard3();
	}

	/*- **************************************************************************** 
	* For Flop
	*******************************************************************************/
	private static void doBoard3() {
		if (EvalData.board[0].value == ACE) {
			EvalData.boardArray[BOARD_ACE_HIGH] = true;
			EvalData.boardAceHigh = true;
		} else if (EvalData.board[0].value >= TEN) {
			EvalData.boardArray[BOARD_HIGH_CARD] = true;
			EvalData.boardHighCard = true;
		}
		if (EvalData.board[0].value == EvalData.board[1].value && EvalData.board[0].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_SET] = true;
			EvalData.boardSet = true;
		} else if (EvalData.board[0].value == EvalData.board[1].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[0].value;
		} else if (EvalData.board[0].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[0].value;
		} else if (EvalData.board[1].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[1].value;
		}
		if (!(EvalData.boardCards[0].suit != EvalData.boardCards[1].suit
				&& EvalData.boardCards[0].suit != EvalData.boardCards[2].suit
				&& EvalData.boardCards[1].suit != EvalData.boardCards[2].suit)) {
			return;
		}

		int suit = EvalData.boardCards[0].suit;
		int suit2 = EvalData.boardCards[1].suit;
		if (suit == EvalData.boardCards[1].suit && suit == EvalData.boardCards[2].suit) {
			EvalData.boardArray[BOARD_3F] = true;
			EvalData.boardF3 = true;
		}else
		if (suit == EvalData.boardCards[2].suit && suit2 == EvalData.boardCards[3].suit) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;
		} else
			if (suit == EvalData.boardCards[1].suit && suit == EvalData.boardCards[2].suit) {
				EvalData.boardArray[BOARD_2F] = true;
				EvalData.boardF2 = true;
			}
		}

		EvalData.boardArray[BOARD_RAINBOW] = true;
		EvalData.boardF2 = true;
	}

	/*- **************************************************************************** 
	* Turn drawing hands
	*******************************************************************************/
	static void doTurn() {
		boolean hit = false;
		if (EvalData.bothGap2_3 == 2 && EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1) {
			hit = true;
		} else if (EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 2 && EvalData.bothGap4_5 == 1) {
			hit = true;
		} else if (EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 2) {
			hit = true;
		}
		if (hit && EvalData.draw[EvalData.seat].draw(DRAW_GUTSHOT)) {
			EvalData.bothGutshotDraw = true;
		}
		hit = false;

		int suit = EvalData.bothCards[1].suit;
		if (suit == EvalData.bothCards[2].suit && suit == EvalData.bothCards[3].suit
				&& suit == EvalData.bothCards[4].suit) {
			hit = true;
		} else {
			suit = EvalData.bothCards[2].suit;
			if (suit == EvalData.bothCards[3].suit && suit == EvalData.bothCards[4].suit
					&& suit == EvalData.bothCards[5].suit) {
				hit = true;
			}
		}
		if (EvalData.draw[EvalData.seat].draw(DRAW_FLUSH)) {
			EvalData.bothFlushDraw = true;
		}

		if (EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1
				&& EvalData.bothGap6_7 != 1) {
			hit = true;
		} else if (EvalData.bothGap2_3 != 1 && EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1
				&& EvalData.bothGap5_6 == 1) {
			hit = true;
		}
		if (hit) {
			if (EvalData.both[5].value == TWO) {
				if (EvalData.draw[EvalData.seat].draw(DRAW_STRAIGHT)) {
					EvalData.bothStraightDraw = true;
				}
			} else {
				if (EvalData.draw[EvalData.seat].draw(DRAW_OESD)) {
					EvalData.bothOesdDraw = true;
				}
			}
		}
		// And if there is nothing
		if (!(!EvalData.bothPair && !EvalData.both2Pair && !EvalData.bothSet && !EvalData.bothQuad && !EvalData.bothFull
				&& !EvalData.bothStraight && !EvalData.bothFlush)) {
		}
		doBoard4();
	}

	/*- **************************************************************************** 
	* For Turn  
	*******************************************************************************/
	private static void doBoard4() {
		if (EvalData.board[0].value == ACE) {
			EvalData.boardArray[BOARD_ACE_HIGH] = true;
			EvalData.boardAceHigh = true;
		} else if (EvalData.board[0].value >= TEN) {
			EvalData.boardArray[BOARD_HIGH_CARD] = true;
			EvalData.boardHighCard = true;
		}
		if (EvalData.board[0].value == EvalData.board[1].value && EvalData.board[0].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_SET] = true;
			EvalData.boardSet = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardSetValue = EvalData.board[0].value;
		} else if (EvalData.board[1].value == EvalData.board[2].value
				&& EvalData.board[1].value == EvalData.board[3].value) {
			EvalData.boardArray[BOARD_SET] = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardSetValue = EvalData.board[1].value;
			EvalData.boardSet = true;
		} else if (EvalData.board[0].value == EvalData.board[1].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[0].value;
		} else if (EvalData.board[0].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[0].value;
		} else if (EvalData.board[1].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[1].value;
		} else if (EvalData.board[2].value == EvalData.board[3].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[1].value;
		}

		if (!(EvalData.boardCards[0].suit != EvalData.boardCards[1].suit
				&& EvalData.boardCards[0].suit != EvalData.boardCards[2].suit
				&& EvalData.boardCards[1].suit != EvalData.boardCards[2].suit
				&& EvalData.boardCards[3].suit != EvalData.boardCards[3].suit)) {
			return;
		}

		int suit = EvalData.boardCards[0].suit;
		int suit2 = EvalData.boardCards[1].suit;
		int suit3 = EvalData.boardCards[2].suit;
		if (suit == EvalData.boardCards[1].suit && suit == EvalData.boardCards[2].suit) {
			EvalData.boardArray[BOARD_3F] = true;
			EvalData.boardF3 = true;
		} else if (suit2 == EvalData.boardCards[2].suit && suit2 == EvalData.boardCards[3].suit) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;
		} else if (suit3 == EvalData.boardCards[3].suit && suit3 == EvalData.boardCards[4].suit) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;
		} else {
			EvalData.boardArray[BOARD_RAINBOW] = true;
			EvalData.boardF2 = true;
		}
	}

	/*- **************************************************************************** 
	* River
	*******************************************************************************/
	static void doRiver() {
	}

}
