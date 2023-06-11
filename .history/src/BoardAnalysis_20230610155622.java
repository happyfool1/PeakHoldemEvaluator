//package evaluate_streets;

public class BoardAnalysis implements Constants {

	/*- **************************************************************************** 
	* This Class contains several methods used to  gather information about 
	* the board and board combined with hole cards.
	* Variables set in EvalData:
	*	EvalData.boardSet
	*	EvalData.boardTwoPair
	*	EvalData.boardPair
	*	EvalData.boardF2
	*	EvalData.boardF3
	*	EvalData.boardArray[BOARD_RAINBOW] 
	*
	*	EvalData.flopOverPair
	*	EvalData.flopWeakPair
	*	EvalData.flopOverCards
	*	EvalData.flopAceHigh
	*	EvalData.flopMiddlePair
	*	EvalData.flopSet
	*	EvalData.flopBoardPair
	*	EvalDta.flopArray is updated
	*   	EvalData.flopArray[FLOP_OVER_PAIR]
	*       EvalData.flopArray[FLOP_WEAK_PAIR]
	*       EvalData.flopArray[FLOP_OVER_CARDS]
	*       EvalData.flopArray[FLOP_ACE_HIGH]
	*       EvalData.flopArray[FLOP_MIDDLE_PAIR]
	*       EvalData.flopArray[FLOP_SET]
	*       EvalData.flopArray[FLOP_BOARD_PAIR]
	*
	*
	*		EvalData.gap0
	* 		EvalData.gap1
	* 		EvalData.gap2
	* 		Data in boardArray is updated
	*  			EvalData.boardArray[BOARD_GAP0]
	*  			EvalData.boardArray[BOARD_GAP1]
	* 
	*		EvalData.gap0Score  
	 *		EvalData.gap1Score   
	 * 		EvalData.gap2Score 
	* 		 
	* A data holding Classes Draw and Made is used to save the cards that make up
	* the hand and  other related information. F
	* indexed by seat number. 
	* 
	* @author PEAK_
	*******************************************************************************/

	private BoardAnalysis() {
		throw new IllegalStateException("Utility class");
	}

	/*- **************************************************************************** 
	* For Flop
	*******************************************************************************/
	static void doFlop() {
		EvalData.boardSet = false;
		EvalData.boardTwoPair = false;
		EvalData.boardPair = false;
		EvalData.boardF2 = false;
		EvalData.boardF3 = false;
		EvalData.boardArray[BOARD_RAINBOW] = true;

		EvalData.flopOverPair = false;
		EvalData.flopWeakPair = false;
		EvalData.flopOverCards = false;
		EvalData.flopAceHigh = false;
		EvalData.flopMiddlePair = false;
		EvalData.flopSet = false;
		EvalData.flopBoardPair = false;

		boardGaps();
		boardGapScore();

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
			EvalData.flopArray[FLOP_POCKET_OVERCARDS] = true;
			EvalData.flopOverCards = true;
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
				EvalData.flopMiddlePair = true;
			}
		}

		if (EvalData.board[0].value == ACE) {
			EvalData.boardArray[BOARD_ACE_HIGH] = true;
			EvalData.flopArray[FLOP_POCKET_ACE_HIGH] = true;
			EvalData.flopAceHigh = true;
			EvalData.boardAceHigh = true;
			EvalData.boardHighCardValue = EvalData.board[0].value;
		} else if (EvalData.board[0].value >= TEN) {
			EvalData.boardArray[BOARD_HIGH_CARD] = true;
			EvalData.boardHighCard = true;
			EvalData.boardHighCardValue = EvalData.board[0].value;
		}

		if (EvalData.board[0].value == EvalData.board[1].value && EvalData.board[0].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_SET] = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardArray[FLOP_BOARD_SET] = true;
			EvalData.flopSet = true;
			EvalData.boardSet = true;
			EvalData.boardSetValue = EvalData.board[0].value;
		} else if (EvalData.board[0].value == EvalData.board[1].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_PAIR] = true;
			EvalData.boardArray[FLOP_BOARD_PAIRED] = true;
			EvalData.flopBoardPair = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[0].value;
		} else if (EvalData.board[1].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_PAIR] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[0].value;
		}
		int suit = EvalData.boardCards[0].suit;
		int suit2 = EvalData.boardCards[1].suit;
		if (suit == EvalData.boardCards[1].suit && suit == EvalData.boardCards[2].suit) {
			EvalData.boardArray[BOARD_3F] = true;
			EvalData.boardF3 = true;
		} else if (suit == EvalData.boardCards[1].suit) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;
		} else if (suit2 == EvalData.boardCards[2].suit) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;
		} else {
			EvalData.boardArray[BOARD_RAINBOW] = true;
		}
	 
	}

	
	/*- **************************************************************************** 
	* Turn drawing hands
	*******************************************************************************/
	static void doTurn() {
		EvalData.boardSet = false;
		EvalData.boardTwoPair = false;
		EvalData.boardPair = false;
		EvalData.boardF2 = false;
		EvalData.boardF3 = false;
		EvalData.boardArray[BOARD_RAINBOW] = true;

		EvalData.flopOverPair = false;
		EvalData.flopWeakPair = false;
		EvalData.flopOverCards = false;
		EvalData.flopAceHigh = false;
		EvalData.flopMiddlePair = false;
		EvalData.flopSet = false;
		EvalData.flopBoardPair = false;

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
			EvalData.boardHighCardValue = EvalData.board[0].value;
		} else if (EvalData.board[0].value >= TEN) {
			EvalData.boardArray[BOARD_HIGH_CARD] = true;
			EvalData.boardHighCard = true;
			EvalData.boardHighCardValue = EvalData.board[0].value;
		}
		if (EvalData.board[0].value == EvalData.board[1].value && EvalData.board[0].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_SET] = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardSetValue = EvalData.board[0].value;
			EvalData.boardSet = true;
		} else if (EvalData.board[1].value == EvalData.board[2].value
				&& EvalData.board[1].value == EvalData.board[3].value) {
			EvalData.boardArray[BOARD_SET] = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardSetValue = EvalData.board[1].value;
			EvalData.boardSet = true;
			// Board 2 pair
		} else if (EvalData.board[0].value == EvalData.board[1].value
				&& EvalData.board[2].value == EvalData.board[3].value) {
			EvalData.boardArray[BOARD_TWO_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_TWO_PAIR] = true;
			EvalData.boardTwoPair = true;
			EvalData.boardTwoPairValue1 = EvalData.board[0].value;
			EvalData.boardTwoPairValue2 = EvalData.board[2].value;
		} else if (EvalData.board[0].value == EvalData.board[1].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_PAIR] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[0].value;
		} else if (EvalData.board[1].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_PAIR] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[0].value;
		} else if (EvalData.board[2].value == EvalData.board[3].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_PAIR] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[1].value;
		}

		int suit = EvalData.boardCards[0].suit;
		int suit2 = EvalData.boardCards[1].suit;
		int suit3 = EvalData.boardCards[2].suit;

		if (suit == EvalData.boardCards[1].suit && suit == EvalData.boardCards[2].suit &&
		 suit == EvalData.boardCards[3].suit) {
            EvalData.boardArray[BOARD_4F] = true;
            EvalData.boardF4 = true;
        } else if (suit == EvalData.boardCards[1].suit && suit == 
		EvalData.boardCards[2].suit) {	
			EvalData.boardArray[BOARD_4F] = true;
			EvalData.boardF4 = true;
		} else
		if (suit == EvalData.boardCards[1].suit && suit == EvalData.boardCards[2].suit) {
			EvalData.boardArray[BOARD_3F] = true;
			EvalData.boardF3 = true;
		}else
		if (suit2 == EvalData.boardCards[2].suit && suit == EvalData.boardCards[3].suit) {
			EvalData.boardArray[BOARD_3F] = true;
			EvalData.boardF3 = true;
		}
		 else if (suit == EvalData.boardCards[1].suit  ) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;}
		 else if (suit2 == EvalData.boardCards[2].suit ) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;	
		 }
		 else if (suit3 == EvalData.boardCards[3].suit  ) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;
		} else {
			EvalData.boardArray[BOARD_RAINBOW] = true;
		}
	}

	/*- **************************************************************************** 
	* For River - no draws
	*******************************************************************************/
	static void doRiver() {
		EvalData.boardSet = false;
		EvalData.boardTwoPair = false;
		EvalData.boardPair = false;
		EvalData.boardF2 = false;
		EvalData.boardF3 = false;
		EvalData.boardArray[BOARD_RAINBOW] = true;

		EvalData.flopOverPair = false;
		EvalData.flopWeakPair = false;
		EvalData.flopOverCards = false;
		EvalData.flopAceHigh = false;
		EvalData.flopMiddlePair = false;
		EvalData.flopSet = false;
		EvalData.flopBoardPair = false;

		if (EvalData.board[0].value == ACE) {
			EvalData.boardArray[BOARD_ACE_HIGH] = true;
			EvalData.boardAceHigh = true;
			EvalData.boardHighCardValue = EvalData.board[0].value;
		} else if (EvalData.board[0].value >= TEN) {
			EvalData.boardArray[BOARD_HIGH_CARD] = true;
			EvalData.boardHighCard = true;
			EvalData.boardHighCardValue = EvalData.board[0].value;
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
		} else if (EvalData.board[2].value == EvalData.board[3].value
				&& EvalData.board[2].value == EvalData.board[4].value) {
			EvalData.boardArray[BOARD_SET] = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardSetValue = EvalData.board[2].value;
			EvalData.boardSet = true;
		} else if (EvalData.board[0].value == EvalData.board[1].value
				&& EvalData.board[2].value == EvalData.board[3].value) {
			EvalData.boardArray[BOARD_TWO_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_TWO_PAIR] = true;
			EvalData.boardTwoPair = true;
			EvalData.boardTwoPairValue1 = EvalData.board[0].value;
			EvalData.boardTwoPairValue2 = EvalData.board[2].value;
		} else if (EvalData.board[1].value == EvalData.board[2].value
				&& EvalData.board[3].value == EvalData.board[4].value) {
			EvalData.boardArray[BOARD_TWO_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_TWO_PAIR] = true;
			EvalData.boardTwoPair = true;
			EvalData.boardTwoPairValue1 = EvalData.board[1].value;
			EvalData.boardTwoPairValue2 = EvalData.board[3].value;
		} else if (EvalData.board[0].value == EvalData.board[1].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_PAIR] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[0].value;
		} else if (EvalData.board[1].value == EvalData.board[2].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_PAIR] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[0].value;
		} else if (EvalData.board[2].value == EvalData.board[3].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_PAIR] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[1].value;
		} else if (EvalData.board[3].value == EvalData.board[4].value) {
			EvalData.boardArray[BOARD_PAIR] = true;
			EvalData.madeArray[MADE_BOARD_SET] = true;
			EvalData.boardPair = true;
			EvalData.boardPairValue = EvalData.board[1].value;
		} 

		int suit = EvalData.boardCards[0].suit;
		int suit2 = EvalData.boardCards[1].suit;
		int suit3 = EvalData.boardCards[2].suit;
		int suit4 = EvalData.boardCards[3].suit;
		if (suit == EvalData.boardCards[1].suit && suit == EvalData.boardCards[2].suit &&
		 suit == EvalData.boardCards[3].suit && suit == EvalData.boardCards[4].suit) {
            EvalData.boardArray[BOARD_5F] = true;
        } else 
		 if (suit2 == EvalData.boardCards[2].suit && suit == EvalData.boardCards[3].suit &&
		 suit == EvalData.boardCards[4].suit) {
            EvalData.boardArray[BOARD_4F] = true;
            EvalData.boardF4 = true;
        } else 
		 if (suit2 == EvalData.boardCards[1].suit && suit == EvalData.boardCards[2].suit &&
		 suit == EvalData.boardCards[3].suit) {
            EvalData.boardArray[BOARD_4F] = true;
            EvalData.boardF4 = true;
        } else
		
		if (suit == EvalData.boardCards[1].suit && suit == EvalData.boardCards[2].suit) {
			EvalData.boardArray[BOARD_3F] = true;
			EvalData.boardF3 = true;
		} else
			if (suit2 == EvalData.boardCards[2].suit && suit == EvalData.boardCards[3].suit) {
			EvalData.boardArray[BOARD_3F] = true;
			EvalData.boardF3 = true;
		} else 
			if (suit3 == EvalData.boardCards[3].suit && suit == EvalData.boardCards[4].suit) {
			EvalData.boardArray[BOARD_3F] = true;
			EvalData.boardF3 = true;
		} else if (suit == EvalData.boardCards[1].suit) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;
		} else if (suit2 == EvalData.boardCards[2].suit) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;
		} else  if (suit3 == EvalData.boardCards[3].suit) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;
		}else
			if (suit4 == EvalData.boardCards[4].suit) {
			EvalData.boardArray[BOARD_2F] = true;
			EvalData.boardF2 = true;
		}
		else {
			EvalData.boardArray[BOARD_RAINBOW] = true;
		}
	}

	/*- *****************************************************************************
	 * This method will look at the board and  determine what gaps there are.
	 * 
	 * A gap of one would be Js9d ( no Tx ), card values are not consecutive. 
	 * A Ten is needed to complete the sequence, One gap.
	 * Two gaps Qs9c. 
	 * A2 is a special case as an Ace is both a 12 and a 1.
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
			if (EvalData.boardArray[HHH] || EvalData.boardArray[HHM]) {
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
