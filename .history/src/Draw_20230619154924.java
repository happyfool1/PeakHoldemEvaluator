//package evaluate_streets;

public class Draw implements Constants {

	/*- **************************************************************************************
	* This Class contains several methods used to  gather information about 
	* the board and board combined with hole cards.
	* Information obtained includes:
	* 		Draws available with combined board and hole cards. 
	* 
	* A draw is possiblee only if it includes both hole cards.
	* We verify that here.
	* 
	* A  Classes Hand is very similar to this Class.
	 * There is one instance of Draw and Hand in EvalData for each player as arrays
	* indexed by seat number. 
	* 
	* For performance purposes, the board and board combined with hole cards
	* has been sorted, high cards first. Greatly simplifies logic and makes it possible
	* to avoid loops.
	* A second version of the combined array is sored first by suit then by value 
	* and used for Flushes.
	* 
	* This Class creates draw hands only for the Flop and Turn .
	* The results are in an instance of this Class.initialized when this instance was created 
	* and some data was added or modified when by methods in this class.
	*  
	* The Card arrays in EvalData are sorted. EvalData.both is sorted by value and
	* EvalData,bothCards is sorted first by suit then by value.
	* The arrays combine hole cards with board cards.
	* 
	* This Class represents a 5 card hand that contains 2 hole cards and 3 cards
	* from the shared common cards, the board.
	*
	* We always start by placing the 2 hole cards in this.cards. 
	* Then we add cards of the type that we are looking for, such as hands that have
	* pairs  3c 3s or hands that have connectors 7s 6c, or have the same suit 9d 5d and
	* do not duplicate the hole cards. 
	* If we do not yet have 5 cards, such as with a pair, we add additional cards as kickers.
	* The result is the best possible hand of that type. 
	* We then verify that the constructed hand is what we were looking for.
	* 
	* The source for cards to add is in EvalData and is an array of Card types 
	* hat includes*both hole cards and all board.
	* That array has been sorted,high card first.
	* 
	* Draw hands that we check for:
	*  DRAW_NONE  
	*  DRAW_BOARD_PAIR  
	*  DRAW_PAIRED_WITH_BOARD  
	*  DRAW_GUTSHOT  
	 * DRAW_STRAIGHT  
	* DRAW_OESD  
	* DRAW_FLUSH  
	* DRAW_FLUSH_OESD  
	* DRAW_MAX_HANDS 
	* String[] DRAW_ARRAY_ST = { "No draw", "Board pair", "Paired with board", "Gutshot", "Straight", "OESD", "Flush" };
	* 
	* @author PEAK_
	***************************************************************************************/

	int type;
	boolean ok; // Is draw valid
	Card[] cards = new Card[7];
	Card[] cardsHoleCardsFirst = new Card[7];
	Card holeCard1;
	Card holeCard2;
	int highCard1;
	int highCard2;
	int suit;
	int kicker1;
	int kicker2;
	int kicker3;
	int seat;
	int street;
	int position;
	int rp;
	int size;
	final boolean[] taken = new boolean[7];
	final boolean[] takenCards = new boolean[7];

	// Constructor
	public Draw() {

	}

	/*- ************************************************************************************
	 * Initialize
	 ***************************************************************************************/
	private void initialize() {
		this.seat = EvalData.seat;
		this.street = EvalData.street;
		this.position = EvalData.positions[this.seat];
		this.rp = EvalData.relativePosition[this.seat];
		this.holeCard1 = EvalData.holeCard1;
		this.holeCard2 = EvalData.holeCard2;
		this.type = MADE_NONE;
		this.ok = false;
		this.size = 0;
		this.highCard1 = -1;
		this.highCard2 = -1;
		this.suit = -1;
		this.kicker1 = -1;
		this.kicker2 = -1;
		this.kicker3 = -1;
		for (int i = 0; i < taken.length; i++) {
			taken[i] = false;
		}
		for (int i = 0; i < 5; i++) {
			this.cards[i] = null;
		}
		this.cards[0] = EvalData.holeCard1;
		this.cards[1] = EvalData.holeCard2;
		this.size = 2;
		for (int i = 0; i < EvalData.bothCount; i++) {
			if (EvalData.both[i].equals(EvalData.holeCard1) || EvalData.both[i].equals(EvalData.holeCard2)) {
				taken[i] = true;
			} else {
				taken[i] = false;
			}
		}
	}

	/*- ************************************************************************************
	 * This private helper method will complete filling in data prior to exit
	 ***************************************************************************************/
	private void finish(int type) {
		this.type = type;
		if (this.size < 5) {
			addBiggerCards();
		}
		if (this.size >= 4) {
			SortCards.quickSortValue(this.cards, 0, 4);
			EvalData.highCard1 = this.highCard1;
			EvalData.highCard2 = this.highCard2;
			EvalData.suit = this.suit;
			EvalData.kicker1 = this.kicker1;
			EvalData.kicker2 = this.kicker2;
			EvalData.kicker3 = this.kicker3;
			if (EvalData.street == FLOP) {
							EvalData.drawTypeFlop[EvalData.seat] = this.type;
			} else if (EvalData.street == TURN) {
							EvalData.drawTypeTurn[EvalData.seat] = this.type;
			}
		} else {
			Crash.log("Draw");
			return;
		}
		holeCardsFirst();
	}

	/*-  ******************************************************************************
	 *  Change card order so that hole cards are first
	 *  Used for showing the hand to a user who may find it confusing if 
	 *  the cards are sorted and the hole cards are not first.
	 *  ****************************************************************************** */
	void holeCardsFirst() {
		this.cardsHoleCardsFirst[0] = this.holeCard1;
		this.cardsHoleCardsFirst[1] = this.holeCard2;
		int ndx = 2;
		for (int i = 0; i < 5; i++) {
			if (!(this.cards[i].equals(this.holeCard1) || this.cards[i].equals(this.holeCard2))) {
				this.cardsHoleCardsFirst[ndx++] = this.cards[i];
				if (ndx > 4) {
					break;
				}
			}
		}
	}

	/*- **************************************************************************** 
	* This Method checks to see if there are both hole cards in the 5 cards that
	* form some type of made hand. 
	* 
	* If both hole cards are there then create a new Made instance for the current
	* player. Set the made hand type into the EvalData.madeArray using the 
	* index passes in type.
	* 
	* Arg2 - Type from EvalData.drawArray, index into array
	*******************************************************************************/
	boolean draw(int type) {
		boolean result = false;
		initialize();
		switch (type) {
			case DRAW_NONE -> result = isNone();
			case DRAW_GUTSHOT -> result = isGutshot();
			case DRAW_STRAIGHT -> result = isStraight();
			case DRAW_OESD -> result = isOESD();
			case DRAW_FLUSH -> result = isFlush();
			case DRAW_FLUSH_OESD -> result = isFlushOESD();
			default -> Crash.log("Invalid draw type " + type);
		}
		finish(type);
		return result;
	}

	/*- ************************************************************************************
	 * Is this no draw at all
	 * EvalData is updated:
	 ***************************************************************************************/
	boolean isNone() {
		initialize();
		addBiggerCards();
		finish(DRAW_NONE);
		return false;
	}

	/*- ************************************************************************************
	 * Is paired with board
	 ***************************************************************************************/
	boolean isPairedWithBoard() {
		if (EvalData.holeValue1 == EvalData.boardValue1 || EvalData.holeValue1 == EvalData.boardValue2
				|| EvalData.holeValue1 == EvalData.boardValue3) {
			return true;
		}
		if (EvalData.holeValue2 == EvalData.boardValue1 || EvalData.holeValue2 == EvalData.boardValue2
				|| EvalData.holeValue2 == EvalData.boardValue3) {
			return true;
		}
		final boolean condition = EvalData.street == TURN
				&& (EvalData.holeValue1 == EvalData.boardValue4 || EvalData.holeValue2 == EvalData.boardValue4);
		if (condition) {
			return true;
		}
		return false;
	}

	/*- ************************************************************************************
	 * Is board paired
	 ***************************************************************************************/
	boolean isBoardPair() {
		final boolean condition = !(EvalData.boardValue1 == EvalData.boardValue2
				|| EvalData.boardValue2 == EvalData.boardValue3) && EvalData.street == TURN;
		if (condition) {
			if (EvalData.boardValue3 == EvalData.boardValue4) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	/*- ************************************************************************************
	 * Is this a gutshot
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_PAIR]  
	 *		EvalData.highCard1  
	 *
	 *  	x_xxx 			xx_xx			xxx_x    	Flop
	 ***************************************************************************************/
	boolean isGutshot() {
		addCardsOfGutshot();
		if (this.size < 5) {
			addBiggerCards();
		}
		SortCards.quickSortValue(this.cards, 0, 4);
		final int gap1_2 = this.cards[0].value - this.cards[1].value;
		final int gap2_3 = this.cards[1].value - this.cards[2].value;
		final int gap3_4 = this.cards[2].value - this.cards[3].value;
		final int gap4_5 = this.cards[3].value - this.cards[4].value;
		final int gap1_3 = this.cards[0].value - this.cards[2].value;
		final int gap3_5 = this.cards[2].value - this.cards[4].value;

		if (gap1_2 != 1 && gap1_3 == 2 && gap3_4 == 1 && gap4_5 == 1) {
			ok = true;
			return true;
		}
		if (gap1_2 == 1 && gap1_3 == 2 && gap2_3 != 1 && gap4_5 == 1) {
			ok = true;
			return true;
		}
		if (gap1_2 == 1 && gap2_3 == 1 && gap3_4 != 1 && gap3_5 == 2) {
			ok = true;
			return true;
		}
		this.ok = false;
		return this.ok;
	}

	/*- ************************************************************************************
	 * Is Straight draw
	 * 
	 * EvalData is udated:
	 *   Ax432  
	 ***************************************************************************************/
	boolean isStraight() {
		addCardsOfStraight();
		if (this.size < 5) {
			addBiggerCards();
		}
		SortCards.quickSortValue(this.cards, 0, 4);
		if (!(this.cards[0].value == ACE && this.cards[2].value != FIVE && this.cards[2].value == FOUR
				&& this.cards[2].value == THREE && this.cards[2].value != TWO)) {
			return false;
		}
		this.highCard1 = ACE;
		return true;
	}

	/*- ************************************************************************************
	 * Is OESD draw
	 * 
	 * EvalData is udated:
	 *  xxxx_  _xxxx   A432 is not OESD is straight only
	 ***************************************************************************************/
	boolean isOESD() {
		addCardsOfStraight();
		if (this.size < 5) {
			addBiggerCards();
		}
		SortCards.quickSortValue(this.cards, 0, 4);
		final int gap1_2 = this.cards[0].value - this.cards[1].value;
		final int gap2_3 = this.cards[1].value - this.cards[2].value;
		final int gap3_4 = this.cards[2].value - this.cards[3].value;
		final int gap4_5 = this.cards[3].value - this.cards[4].value;

		if (this.cards[0].value == ACE && this.cards[4].value == TWO) {
			return false;
		}
		if (gap1_2 == 1 && gap2_3 == 1 && gap3_4 == 1 && gap4_5 != 1) {
			this.highCard1 = this.cards[0].value;
			return true;
		}
		if (!(gap1_2 != 1 && gap2_3 == 1 && gap3_4 == 1 && gap4_5 == 1)) {
			return false;
		}
		this.highCard1 = this.cards[1].value;
		return true;
	}

	/*- ************************************************************************************
	 * Is Flush draw
	 * 
	 * EvalData is udated:
	 *  	ssss_ _ssss
	 ***************************************************************************************/
	boolean isFlush() {
		addCardsOfFlush();
		if (this.size < 5) {
			addBiggerCardsFlush();
		}

		SortCards.quickSortCard(this.cards, 0, 4);
		int suit = this.cards[0].suit;
		if (suit == this.cards[1].suit && suit == this.cards[2].suit && suit == this.cards[3].suit
				&& suit != this.cards[4].suit) {
			return true;
		}
		suit = this.cards[2].suit;
		if (suit == this.cards[2].suit && suit == this.cards[3].suit && suit == this.cards[4].suit
				&& suit != this.cards[1].suit) {
			return true;
		}
		return false;
	}

	/*- ************************************************************************************
	 * Is both Flush and Straight draw
	 * 
	 * EvalData is udated:
	 *
	 ***************************************************************************************/
	boolean isFlushOESD() {
		// TODO
		return false;
	}

	/*- ************************************************************************************
	 * Add cards that are in a gutshot ( no gap between card values or 1 gap)
	 * If a card and the next card have values exactly 1 apart add both
	 * if a card and the next card have values exactly 2 apart add both
	 *  	x_xxx 			xx_xx			xxx_x    	Flop
	 *  	_x_xxx 		_xx_xx			_xxx_x    Turn
	 ***************************************************************************************/
	private void addCardsOfGutshot() {
		for (int i = 0; i < EvalData.bothCount - 2; i++) {
			if (EvalData.both[i].value - EvalData.both[i + 1].value == 1
					|| EvalData.both[i].value - EvalData.both[i + 1].value == 2) {
				if (EvalData.highCard1 == -1 && !taken[i]) {
					EvalData.highCard1 = EvalData.both[i].value;
				}
				if (!taken[i] && this.size < 5) {
					taken[i] = true;
					this.cards[this.size++] = EvalData.both[i];
				}
				if (!taken[i + 1] && this.size < 5) {
					taken[i + 1] = true;
					this.cards[this.size++] = EvalData.both[i + 1];
				}
			}
			if (this.size >= 5) {
				break;
			}
		}
	}

	/*- ************************************************************************************
	 * Add cards that are in a straight ( no gap between card values )
	 * If a card and the next card have values exactly 1 apart add both
	 ***************************************************************************************/
	private void addCardsOfStraight() {
		for (int i = 0; i < EvalData.bothCount - 2; i++) {
			if (EvalData.both[i].value - EvalData.both[i + 1].value == 1) {
				if (EvalData.highCard1 == -1) {
					EvalData.highCard1 = EvalData.both[i].value;
				}
				if (!taken[i]) {
					taken[i] = true;
					this.cards[this.size++] = EvalData.both[i];

				}
				if (!taken[i + 1]) {
					taken[i + 1] = true;
					this.cards[this.size++] = EvalData.both[i + 1];
				}
			}
			if (this.size >= 5) {
				break;
			}
		}
	}

	/*- ************************************************************************************
	 * Player has nothing but his hole cards
	 * holeCards have been added so now we add more cards that
	 * do not duplicate hole cards until we have 5 cards in hand
	 ***************************************************************************************/
	private void addBiggerCards() {
		if (this.size > 5) {
			Crash.log("wtf " + this.size);
		}
		for (int i = 0; i < EvalData.bothCount; i++) {
			if (!taken[i]) {
				if (this.kicker1 == -1) {
					this.kicker1 = EvalData.both[i].value;
				} else if (this.kicker2 == -1) {
					this.kicker2 = EvalData.both[i].value;
				} else if (this.kicker3 == -1) {
					this.kicker3 = EvalData.both[i].value;
				}
				this.cards[this.size++] = EvalData.both[i];
				taken[i] = true;
				if (this.size >= 5) {
					return;
				}
			}
		}
		if (this.size > 5) {
			Crash.log("wtf " + this.size);
		}
	}

	/*- ************************************************************************************
	 * Player has nothing but his hole cards
	 * holeCards have been added so now we add more cards that
	 * do not duplicate hole cards until we have 5 cards in hand
	 * Used by Flush hands
	 ***************************************************************************************/
	private void addBiggerCardsFlush() {
		for (int i = 0; i < EvalData.bothCount; i++) {
			if (!takenCards[i]) {
				if (this.kicker1 == -1) {
					this.kicker1 = EvalData.bothCards[i].value;
				} else if (this.kicker2 == -1) {
					this.kicker2 = EvalData.bothCards[i].value;
				} else if (this.kicker3 == -1) {
					this.kicker3 = EvalData.bothCards[i].value;
				}
				this.cards[this.size++] = EvalData.bothCards[i];
				takenCards[i] = true;
				if (this.size > 4) {
					return;
				}
			}
		}
	}

	/*- ************************************************************************************
	 * Add Flush cards
	 ***************************************************************************************/
	private void addCardsOfFlush() {
		for (int i = 0; i < EvalData.bothCount; i++) {
			if (EvalData.bothCards[i].equals(EvalData.holeCard1) || EvalData.bothCards[i].equals(EvalData.holeCard2)) {
				takenCards[i] = true;
			} else {
				takenCards[i] = false;
			}
		}
		for (int i = 0; i < EvalData.bothCount - 1; i++) {
			if (EvalData.bothCards[i].suit == EvalData.bothCards[i + 1].suit) {
				if (!takenCards[i]) {
					takenCards[i] = true;
					this.cards[this.size++] = EvalData.bothCards[i];
				}
				if (!takenCards[i + 1]) {
					takenCards[i + 1] = true;
					this.cards[this.size++] = EvalData.bothCards[i + 1];
				}
				if (this.size > 4) {
					break;
				}
			}
		}
	}

	/*- ************************************************************************************
	 * Tell them all about it
	 ***************************************************************************************/
	@Override
	public String toString() {
		var st = new StringBuilder().append("\r\nDraw hand is ").append(DRAW_ARRAY_ST[this.type]).append(" seat ")
				.append(String.valueOf(this.seat)).append(" ").append(STREET_ST[this.street]).append(" ")
				.append(POSITION_ST[this.position]).toString();
		if (this.rp != -1) {
			st += " " + RP_ST[this.rp];
		}
		if (this.street == FLOP) {
			st += new StringBuilder().append("\r\n").append(this.cards[0]).append(this.cards[1]).append(this.cards[2])
					.append(this.cards[3]).append(this.cards[4]).toString();
		} else if (this.street == TURN) {
			st += new StringBuilder().append("\r\n").append(this.cards[0]).append(this.cards[1]).append(this.cards[2])
					.append(this.cards[3]).append(this.cards[4]).append(this.cards[5]).toString();
		} else {
			st += new StringBuilder().append("\r\n").append(this.cards[0]).append(this.cards[1]).append(this.cards[2])
					.append(this.cards[3]).append(this.cards[4]).append(this.cards[5]).append(this.cards[6]).toString();
		}
		if (this.highCard1 != -1) {
			st += "\r\nHigh card 1 " + String.valueOf(this.highCard1);
		}
		if (this.highCard2 != -1) {
			st += "\r\nHigh card 2 " + String.valueOf(this.highCard2);
		}
		if (this.suit != -1) {
			st += "\r\nsuit " + SUITS_ST[this.suit];
		}
		if (this.kicker1 != -1) {
			st += "\r\nKickers  " + String.valueOf(this.kicker1);
		}
		if (this.kicker2 != -1) {
			st += " " + String.valueOf(this.kicker2);
		}
		if (this.kicker3 != -1) {
			st += " " + String.valueOf(this.kicker3);
		}
		return st;
	}
}
