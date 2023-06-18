//package evaluate_streets;

/*- **************************************************************************************
 * This Class checks and validates drawinghands only and only for the Flop and Turn.
 * The  board3FlushDraw  boolean was calculated by the Analyze Class.
 * 		EvalData.board3FlushDraw = (suit == EvalData.boardCards[1].suit) 
 * 		&& (suit == EvalData.boardCards[2].suit);
 * This will be 100% correct for the Flop but may not be for the Turn
 * There are only 5 cards total on the Flop. On the Turn it is possible that
 * both hole cards are not included. 
 * If the value is false that will be 100% correct on any street,
 * We get far better performance by using that to skip checking for draws that 
 * are impossibel and accepting that we willhave to correct when wrong.
 * ( a small percentage of the time )
 * 
 * This Class represents a 5 card hand that contains 2 hole cards and 3 cards
 * from the shared common cards,the board.
 * The hole cards,board,and combined arrays are all in sort sequence,
 * high to low.
 * The source for cards to add is in EvalData and is an array of Card types 
 * hat includes both hole cards and all board.
 * That array has been sorted,high card first.
 * It now works with the MadeHand Class to parse the  data for construct a made hand.
 * In Texas Hold'em,each player is dealt two  private cards, which are commonly 
 * referred to as hole cards. 
 * These hole cards are essential to the hand as they are combined with the
 * community cards to form the best possible five-card hand. 
 * Without both hole cards, a player cannot participate in a hand of Texas Hold'em. 
 * Therefore, both hole cards are always required in a Hold'em poker hand.
 * 
 * @author PEAK_
 * 
 ***************************************************************************************/

public class DrawBoard implements Constants {
	private final Card[] cards;
	int handType = MADE_NONE;
	boolean ok = false; // Is hand valid
	int suit = -1; // Suit of flush
	int kicker1 = -1; // Kicker if needed by hand like one pair
	int kicker2 = -1; // Kicker if needed by hand like one pair
	int kicker3 = -1; // Kicker if needed by hand like one pair
	int size = 0; // Index into cards while being constructed

	private boolean[] taken = new boolean[7]; // True if card taken from EvalData.both hole cards + board

	// Constructor
	public DrawBoard() {
		this.cards = new Card[5];
	}

	/*-
	 * Initialize
	 */
	void initialize() {
		for (int i = 0; i < EvalData.bothCount - 1; i++) {
			this.taken[i] = false;
		}
		for (int i = 0; i < 5; i++) {
			this.cards[i] = null;
		}
		this.size = 0;
		this.handType = MADE_NONE;
		this.ok = false;
		addHoleCards();
	}

	/*-
	 * Is this nothing
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_NONE]  
	 */
	boolean isNone() {
		initialize();
		addBiggerCards();
		SortCards.quickSortCard(this.cards, 0, 4);
		if (EvalData.street == FLOP) {
			EvalData.drawTypeFlop[EvalData.seat] = DRAW_NONE;
		}
		if (EvalData.street == TURN) {
			EvalData.drawTypeTurn[EvalData.seat] = DRAW_NONE;
		} else {
			EvalData.drawTypeRiver[EvalData.seat] = DRAW_NONE;
		}

		this.ok = true;
		return this.ok;
	}

	/*-
	 * Check for flush draw
	 * ssssx xssss is a draw
	 */
	boolean checkGutshotDraw() {
		int suit = cards[0].suit;
		if (suit == cards[1].suit && suit == cards[2].suit && suit == cards[3].suit && suit != cards[4].suit) {
			return true;
		}
		suit = cards[1].suit;
		if (suit == cards[2].suit && suit == cards[3].suit && suit == cards[4].suit) {
			return true;
		} else {
			return false;
		}
	}

	/*-
	 * Check for flush draw
	 * ssssx xssss is a draw
	 */
	boolean checkFlushDraw() {
		int suit = cards[0].suit;
		if (suit == cards[1].suit && suit == cards[2].suit && suit == cards[3].suit && suit != cards[4].suit) {
			return true;
		}
		suit = cards[1].suit;
		if (suit == cards[2].suit && suit == cards[3].suit && suit == cards[4].suit) {
			return true;
		} else {
			return false;
		}
	}

	/*-
	 * Check for straight draw
	 * ssssx xssss is a draw
	 */
	boolean checkStraightDraw() {
		if (cards[1].value - cards[1].value == 1 && cards[1].value - cards[2].value == 1
				&& cards[2].value - cards[3].value == 1 && cards[3].value - cards[4].value != 1) {
			return true;
		}
		if (cards[1].value - cards[2].value == 1 && cards[2].value - cards[3].value == 1
				&& cards[3].value - cards[4].value == 1 && cards[4].value - cards[5].value == 1) {
			return true;
		} else {
			return false;
		}
	}

	/*-
	 * Is this a Pair
	 * If there is a Pair return true.
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_PAIR]  
	 *		EvalData.highCard1  
	 */
	boolean isPair() {
		initialize();
		addCardsOfPair();
		addBiggerCards();
		SortCards.quickSortValue(this.cards, 0, 4);
		this.ok = isCardsOnePair();
		return this.ok;
	}

	/*-
	 * Is this 2 pairs
	 * If there are 2 pairs return true.
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_TWO_PAIR]  
	 *		EvalData.highCard1  
	 *		EvalData.highCard2  
	 */
	boolean is2Pair() {
		initialize();
		addCardsOfPair();
		addBiggerCards();
		SortCards.quickSortValue(this.cards, 0, 4);
		this.ok = isCardsTwoPair();
		return this.ok;
	}

	/*-
	 * Is this set
	 * If sets return true.
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_SET]  
	 *		EvalData.highCard1  
	 *		EvalData.highCard2  
	 */
	boolean isSet() {
		initialize();
		addCardsOfPair();
		addBiggerCards();
		SortCards.quickSortValue(this.cards, 0, 4);
		this.ok = isCardsThreeOfAKind();
		if (this.ok) {
			if (EvalData.street == FLOP) {
				EvalData.drawTypeFlop[EvalData.seat] = DRAW_SET;
			}
			if (EvalData.street == TURN) {
				EvalData.drawTypeTurn[EvalData.seat] = DRAW_SET;
			} else {
				EvalData.drawTypeRiver[EvalData.seat] = DRAW_SET;
			}

		}
		return this.ok;
	}

	/*-
	 * Is this fill house
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[FULL_HOUSE]  
	 *		EvalData.highCard1  
	 *		EvalData.highCard2  
	 */
	boolean isFullHouse() {
		initialize();
		addCardsOfPair();
		addBiggerCards();
		SortCards.quickSortValue(this.cards, 0, 4);
		this.ok = isCardsFullHouse();
		if (this.ok) {
			if (EvalData.street == FLOP) {
				EvalData.madeTypeFlop[EvalData.seat] = MADE_FULL_HOUSE;
			}
			if (EvalData.street == TURN) {
				EvalData.madeTypeTurn[EvalData.seat] = MADE_FULL_HOUSE;
			} else {
				EvalData.madeTypeRiver[EvalData.seat] = MADE_FULL_HOUSE;
			}
		}
		return this.ok;

	}

	/*-
	 * Is this4 of a kind
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[FOUR_OF_A_KIND]  
	 *		EvalData.highCard1  
	 */
	boolean is4Of() {
		initialize();
		addCardsOfPair();
		addBiggerCards();
		SortCards.quickSortValue(this.cards, 0, 4);
		this.ok = isCardsFourOfAKind();
		if (this.ok) {
			if (EvalData.street == FLOP) {
				EvalData.madeTypeFlop[EvalData.seat] = MADE_FOUR_OF_A_KIND;
			}
			if (EvalData.street == TURN) {
				EvalData.madeTypeTurn[EvalData.seat] = MADE_FOUR_OF_A_KIND;
			} else {
				EvalData.madeTypeRiver[EvalData.seat] = MADE_FOUR_OF_A_KIND;
			}
		}
		return this.ok;
	}

	/*-
	 * Is this a Flush
	 * If there is a Flush return true.
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_FLUSH]  
			EvalData.highCard1  
			EvalData.suit  
	 */
	boolean isFlush() {
		initialize();
		addCardsOfFlush();
		if (this.size < 5) {
			this.ok = false;
			return this.ok;
		}
		SortCards.quickSortValue(this.cards, 0, 4);
		this.ok = isCardsFlush();
		if (this.ok) {
			if (EvalData.street == FLOP) {
				EvalData.madeTypeFlop[EvalData.seat] = MMADE_FLUSH;
			}
			if (EvalData.street == TURN) {
				EvalData.madeTypeTurn[EvalData.seat] = MADE_FOUR_OF_A_KIND;
			} else {
				EvalData.madeTypeRiver[EvalData.seat] = MADE_FOUR_OF_A_KIND;
			}
			EvalData.madeArray[MADE_FLUSH] = true;
			EvalData.highCard1 = EvalData.bothCards[0].value;
			EvalData.suit = EvalData.bothCards[0].suit;
		}
		return this.ok;
	}

	/*-
	 * Is this a Straight
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_STRAIGHT]  
	 *		EvalData.highCard1 
	 */
	boolean isStraight() {
		initialize();
		addCardsOfStraight();
		if (this.size < 5) {
			this.ok = false;
			return this.ok;
		}
		SortCards.quickSortValue(this.cards, 0, 4);
		this.ok = isCardsStraight();
		if (this.ok) {
			EvalData.madeArray[MADE_STRAIGHT] = true;
			EvalData.highCard1 = EvalData.bothCards[0].value;
		}
		return this.ok;
	}

	/*-
	 * Is this a Straight Flush
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_STRAIGHT_FLUSH ]  
	 *		EvalData.highCard1 
	 */
	boolean isStraightFlush() {
		initialize();
		addCardsOfFlush();
		if (this.size < 5) {
			this.ok = false;
			return this.ok;
		}
		SortCards.quickSortValue(this.cards, 0, 4);
		final boolean b = isCardsFlush();
		if (b && isCardsStraight() && this.cards[0].value != ACE) {
			this.ok = true;
		}
		return this.ok;
	}

	/*-
	 * Is this a Royal Flush
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_ROYAL_FLUSH]  
	 */
	boolean isRoyalFlush() {
		initialize();
		addCardsOfFlush();
		if (this.size < 5) {
			this.ok = false;
			return this.ok;
		}
		SortCards.quickSortValue(this.cards, 0, 4);
		final boolean b = isCardsFlush();
		if (b && isCardsStraight() && this.cards[0].value == ACE) {
			this.ok = true;
		}
		return this.ok;
	}

	/*-
	 * The combined hole cards and board array is searched for pairs.
	 * If a pair is found it is added to cards 
	 * If more than 2 cards are paired they are all added, set or quads.
	 */
	private void addCardsOfPair() {
		for (int i = 0; i < EvalData.bothCount - 2; i++) {
			if (EvalData.both[i].value == EvalData.both[i + 1].value) {
				if (EvalData.highCard1 == -1) {
					EvalData.highCard1 = EvalData.both[i].value;
				} else if (EvalData.highCard2 == -1) {
					EvalData.highCard2 = EvalData.both[i].value;
				}
				if (!this.taken[i]) {
					this.taken[i] = true;
					this.cards[this.size++] = EvalData.both[i];
				}
				if (!this.taken[i + 1]) {
					this.taken[i + 1] = true;
					this.cards[this.size++] = EvalData.both[i + 1];
				}
			}
			if (this.size >= 5) {
				break;
			}
		}
	}

	/*-
	 * Add cards that are in a straight ( no gap between card values )
	 * If a card and the next card have values exactly 1 apart add both
	 */
	private void addCardsOfStraight() {
		for (int i = 0; i < EvalData.bothCount - 2; i++) {
			if (EvalData.both[i].value - EvalData.both[i + 1].value == 1) {
				if (EvalData.highCard1 == -1) {
					EvalData.highCard1 = EvalData.both[i].value;
				}
				if (!this.taken[i]) {
					this.taken[i] = true;
					this.cards[this.size++] = EvalData.both[i];

				}
				if (!this.taken[i + 1]) {
					this.taken[i + 1] = true;
					this.cards[this.size++] = EvalData.both[i + 1];
				}
			}
			if (this.size >= 5) {
				break;
			}
		}
	}

	/*-
	 * Player has nothing but his hole cards
	 * holeCards have been added so now we add more cards that
	 * do not duplicate hole cards until we have 5 cards in hand
	 */
	private void addBiggerCards() {
		for (int i = 0; i < EvalData.bothCount; i++) {
			if (this.size < 5 && !EvalData.both[i].equals(EvalData.holeCard1)
					&& !EvalData.both[i].equals(EvalData.holeCard2) && !this.taken[i]) {

				if (this.kicker1 == -1) {
					this.kicker1 = EvalData.both[i].value;
				} else if (this.kicker2 == -1) {
					this.kicker2 = EvalData.both[i].value;
				} else if (this.kicker3 == -1) {
					this.kicker3 = EvalData.both[i].value;
				}
				this.cards[this.size++] = EvalData.both[i];
				this.taken[i] = true;
			}
			if (this.size >= 5) {
				break;
			}
		}
	}

	/*-
	 * Add Flush cards
	 */
	private void addCardsOfFlush() {
		int suitCount = 0;
		int suit = -1;
		for (int i = 0; i < EvalData.bothCount; i++) {
			if (EvalData.bothCards[i].suit == suit) {
				suitCount++;
				if (!this.taken[i]) {
					this.taken[i] = true;
					this.cards[this.size++] = EvalData.both[i];
				}
				if (suitCount >= 5) {
					break;
				}
			} else {
				suit = EvalData.bothCards[i].suit;
				suitCount = 1;
				this.size = 2;
				this.taken = new boolean[EvalData.bothCount];
				this.taken[i] = true;
				this.cards[this.size++] = EvalData.both[i];
			}
			if (this.size >= 5) {
				break;
			}
		}
	}

	/*-
	 * Add hole cards  to cards
	 * Arg0 - 3 add both, 1 add holeCard1, 2 addHoleCard2
	 */
	private void addHoleCards() {
		this.cards[this.size++] = EvalData.holeCard1;
		this.cards[this.size++] = EvalData.holeCard2;
	}

	/*-
	 * Does cards have an Ace
	 * Returns true or false
	 */
	boolean areAceHigh() {
		if (this.cards[0].value == ACE) {
			return true;
		}
		return false;
	}

	/*-
	 * Does cards have 2 big cards A - T
	 * Returns true or false
	 */
	boolean areBigCards() {
		int count = 0;
		for (int i = 0; i < this.size; i++) {
			if (this.cards[i].value >= TEN) {
				++count;
				if (count >= 2) {
					return true;
				}
			}
		}
		return false;
	}

	/*-
	 * Does cards have one pair
	 * Returns true or false
	 */
	boolean isCardsOnePair() {
		if (this.size == 5) {
			if (this.cards[0].value == this.cards[1].value || this.cards[1].value == this.cards[2].value
					|| this.cards[2].value == this.cards[3].value || this.cards[3].value == this.cards[4].value) {
				return true;
			}

		} else if (this.size == 4) {
			if (this.cards[0].value == this.cards[1].value || this.cards[1].value == this.cards[2].value
					|| this.cards[2].value == this.cards[3].value) {
				return true;
			}
		} else if (this.size == 3 && this.cards[0].value == this.cards[1].value
				|| this.cards[1].value == this.cards[2].value) {
			return true;
		}
		return false;
	}

	/*-
	 * Does cards have one pair
	 * Returns true or false.
	 * Must be exactly 2 pair, no set or quads
	 * pp ppp
	 */
	boolean isCardsTwoPair() {
		int numPairs = 0;
		for (int i = 0; i < 4; i++) {
			if (this.cards[i].value == this.cards[i + 1].value) {
				numPairs++;
			}
		}
		return numPairs == 2;
	}

	/*-
	 * Does cards have a set Returns true or false
	 */
	boolean isCardsThreeOfAKind() {
		for (int i = 0; i < 3; i++) {
			if (this.cards[i].value == this.cards[i + 1].value && this.cards[i].value == this.cards[i + 2].value) {
				return true;
			}
		}
		return false;
	}

	/*-
	 * Does cards have four of a kind
	 * Returns true or false
	 */
	boolean isCardsFourOfAKind() {
		int num = 0;
		for (int i = 0; i < 4; i++) {
			if (this.cards[i].value == this.cards[i + 1].value) {
				++num;
			}
		}
		if (num == 3) {
			return true;
		}
		return false;
	}

	/*-
	 * Does cards have a full house
	 * Returns true or false
	 */
	boolean isCardsFullHouse() {
		if (this.cards[0].value == this.cards[1].value && this.cards[0].value == this.cards[2].value
				&& this.cards[3].value == this.cards[4].value) {
			return true;
		}
		if (this.cards[0].value == this.cards[1].value && this.cards[2].value == this.cards[3].value
				&& this.cards[2].value == this.cards[4].value) {
			return true;
		}
		return false;
	}

	/*-
	 * Does cards contain a straight
	 * Returns true or false
	 */
	boolean isCardsStraight() {
		int num = 0;
		for (int i = 0; i < 4; i++) {
			if (this.cards[i].value - this.cards[i + 1].value == 1
					|| this.cards[i].value - this.cards[i + 1].value == -1) {
				++num;
			}
		}
		final boolean condition = num != 4 && this.cards[1].value == FIVE && this.cards[2].value == FOUR
				&& this.cards[3].value == THREE && this.cards[4].value == TWO && this.cards[1].value == ACE;
		if (condition) {
			return true;
		}
		return (num == 4);
	}

	/*-
	 * Determines if the cards have a flush.
	 * Returns true or false.
	 */
	boolean isCardsFlush() {
		for (int i = 0; i < 4; i++) {
			if (this.cards[i].suit != this.cards[i + 1].suit) {
				return false;
			}
		}
		return true;
	}

	/*-
	 * Show details
	 */
	String show() {
		var st = new StringBuilder().append("//Hand for seat ").append(String.valueOf(EvalData.seat)).append(" ")
				.append(STREET_ST[EvalData.street]).append(" handType ").append(DRAW_ARRAY_ST[this.handType])
				.append("\r\n").toString();
		st += new StringBuilder().append("//Correct ").append(Format.format(this.ok)).append("\r\n").toString();
		st += new StringBuilder().append("//hole cards ").append(EvalData.holeCard1).append(EvalData.holeCard2)
				.append("\r\n").toString();
		st += new StringBuilder().append("// both cards ").append(EvalData.both[0]).append(EvalData.both[1])
				.append(EvalData.both[2]).append(EvalData.both[3]).append(EvalData.both[4]).append(EvalData.both[5])
				.append(EvalData.both[6]).append("\r\n").toString();
		st += new StringBuilder().append("//hand cards ").append(this.cards[0]).append(this.cards[1])
				.append(this.cards[2]).append(this.cards[4]).append(this.cards[4]).append("\r\n").toString();
		st += new StringBuilder().append("//EvalData.highCard1").append(String.valueOf(EvalData.highCard1))
				.append(" highCard21 ").append(String.valueOf(EvalData.highCard1)).append(" suit ")
				.append(String.valueOf(suit)).append("\r\n").toString();
		if (this.kicker1 != -1) {
			st += "//kickers " + Card.CARDS_REVERSE[this.kicker1];
		}
		if (this.kicker2 != -1) {
			st += "  " + Card.CARDS_REVERSE[this.kicker2];
		}
		if (this.kicker3 != -1) {
			st += "  " + Card.CARDS_REVERSE[this.kicker3];
		}
		st += "\r\n";
		return st;
	}

	/*-
	 * Sort cards
	 */
	void sortCards() {
		SortCards.quickSortValue(this.cards, 0, 4);
	}

	@Override
	public String toString() {
		final var sb = new StringBuilder();
		for (int i = 0; i < this.size; i++) {
			sb.append(this.cards[i]).append(" ");
		}
		return sb.toString().trim();
	}

}
