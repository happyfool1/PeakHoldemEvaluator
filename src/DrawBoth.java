//package peakholdemevaluator;

/*- **************************************************************************************
 * This Class checks and validates drawing hands only and only for the Flop and Turn.
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

public class DrawBoth implements Constants {
	private final Card[] cards;
	int handType = DRAW_NONE;
	boolean ok = false; // Is hand valid
	int suit = -1; // Suit of flush
	int kicker1 = -1; // Kicker if needed by hand like one pair
	int kicker2 = -1; // Kicker if needed by hand like one pair
	int kicker3 = -1; // Kicker if needed by hand like one pair
	int size = 0; // Index into cards while being constructed

	private final boolean[] taken = new boolean[5]; // True if card taken from EvalData.board
	private final Card[] boardCopy = new Card[4]; // Copy of board sorted by card for flush check

	// Constructor
	public DrawBoth() {
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
	}

	/*-
	 * Is this nothing
	*/
	boolean isNone() {
		initialize();
		addBiggerCards();
		SortCards.quickSortValue(this.cards, 0, 4);

		this.ok = true;
		return this.ok;
	}

	boolean isBoardPair() {
		initialize();
		return true;
	}

	boolean isPairedWithBoard() {
		initialize();
		return true;
	}

	/*-
	 * Check for flush draw
	 * ssssx xssss is a draw
	 */
	boolean isGutshotDraw() {
		initialize();
		addGutshotCards();
		SortCards.quickSortValue(this.cards, 0, 4);
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

	boolean isStraightDraw() {
		initialize();
		return true;
	}

	boolean isOesdDraw() {
		initialize();
		return true;
	}

	/*-
	 * Check for flush draw
	 * ssssx xssss is a draw
	 */
	boolean isFlushDraw() {
		initialize();
		for (int i = 0; i < EvalData.boardCount - 1; i++) {
			boardCopy[i] = EvalData.board[i];
		}
		SortCards.quickSortCard(boardCopy, 0, EvalData.boardCount - 1);
		if (!addCardsOfFlush()) {
			this.ok = false;
			return ok;
		}
		SortCards.quickSortCard(this.cards, 0, 4);
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

	boolean isFlushOesdDraw() {
		initialize();
		return true;
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
	 * Add cards that are in a straight ( no gap between card values )
	 * holeCards have been added so now we add 3 more cards.
	 * At least 1 of the hole cards must be part of the 4 cards involved 
	 * in the gutshot.
	 * 
	 *  x_xxxx   xx_xxx    xxx_xx   xxxx_x
	 *  _bbb   b_bb   bb_b 
	 *  hh bbbb
	 */
	private boolean addGutshotCards() {
		if (this.cards[0].value - EvalData.board[0].value == 1 || this.cards[1].value - EvalData.board[0].value == 1) {
			this.cards[this.size++] = EvalData.board[0];
			this.taken[0] = true;
		}
		if (this.cards[0].value - EvalData.board[1].value == 2 || this.cards[1].value - EvalData.board[1].value == 2) {
			this.cards[this.size++] = EvalData.board[1];
			this.taken[0] = true;
		}

		return false;
	}

	/*-
	 * Add Flush cards
	 * Add 3 more cards
	 * A copy of the board has been made then sorted by card so
	 * suits are in oder then value 3hTc9c2c
	 * Try to add cards that match the suit of one of the hole cards
	 * If not enough matching cards return false, can not be a Flush draw
	 */
	private boolean addCardsOfFlush() {
		if (boardCopy[0].suit == boardCopy[1].suit) {
			cards[this.size++] = boardCopy[0];
			cards[this.size++] = boardCopy[1];
			if (boardCopy[1].suit == boardCopy[2].suit) {
				cards[this.size++] = boardCopy[1];
			}
		} else if (boardCopy[1].suit == boardCopy[2].suit) {
			cards[this.size++] = boardCopy[1];
			cards[this.size++] = boardCopy[2];
			if (boardCopy[2].suit == boardCopy[3].suit) {
				cards[this.size++] = boardCopy[3];
			}
		}
		if (this.size < 5) {
			return false;
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

	@Override
	public String toString() {
		final var sb = new StringBuilder();
		for (int i = 0; i < this.size; i++) {
			sb.append(this.cards[i]).append(" ");
		}
		return sb.toString().trim();
	}

}
