//package evaluate_streets;

/*- **************************************************************************************
* This Class contains several methods used to  gather information about 
* the board and board combined with hole cards.
* Information obtained includes:
* 		Board analysis on Flop, gaps, suitedness, etc..
* 		Made hands with combined board and hole cards. 
* 
* A  Classes Draw is verysimilar to this Class.
 * There is one instance of Draw and Hand in EvalData for each player as arrays
* indexed by seat number. 
* 
* For performance purposes, the board and board combined with hole cards
* has been sorted, high cards first. Greatly simplifies logic and makes it possible
* to avoid loops.
* A second version of the combined array is sored first by suit then by value 
* and used for Flushes.
* 
* A made hand is possiblee only if it includes both hole cards.
* We verify that here.
* 
* This Class creates made hands only for the Flop, Turn, and River.
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
* Made hands that we check for:
* 	int MADE_NONE = 0;
*	int MADE_PAIR = 3;
*	int MADE_TWO_PAIR = 4;
* int MADE_SET = 5;
*	int MADE_STRAIGHT = 6;
*	int MADE_FLUSH = 7;
*	int MADE_FULL_HOUSE = 8;
*	int MADE_FOUR_OF_A_KIND = 9;
*	int MADE_STRAIGHT_FLUSH = 10;
*	int MADE_ROYAL_FLUSH = 11;
* 
* @author PEAK_
* 
***************************************************************************************/

public class Hand implements Constants {
	int type;
	boolean ok; // Is hand valid
	Card[] cards = new Card[7];
	Card[] cardsHoleCardsFirst = new Card[7];
	Card holeCard1;
	Card holeCard2;
	int seat;
	int street;
	int position;
	int rp; // Relative position
	int highCard1;
	int highCard2;
	int pair1Value;
	int pair2Value;
	int suit; // Suit of flush
	int kicker1; // Kicker if needed by hand like one pair
	int kicker2; // Kicker if needed by hand like one pair
	int kicker3; // Kicker if needed by hand like one pair
	int size;
	final boolean[] taken = new boolean[7];
	final boolean[] takenCards = new boolean[7];

	// Constructor
	public Hand() {

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
	 * Flop
	 * Also used in both doTurnHands() and doRiverHands() because hands found 
	 * earlier will be better hands.
	 * Confusing but because the card arrays are sorted the higher cards will be first in the array.
	 * Example: 
	 * Hole cards Qh Qc
	 * Flop array was Qh Qc Tc 6c 5c but turn card Ah new sorted array 
	 * Ah Qd Qd Tc 6c 5c still a pair but better kicker. River card is Ac and array is
	 * Ac Ah Qh Qc Tc 6c 5c and best hand is actually two pair. 
	 * The Flush is not valid because a made hand must include both hole cards.
	 * His hand is actually Qh Qc Ah Ac Tc or sorted as Ah Ac Qh Qc Tc
	 * 
	* This method will determine if there is a made hand in the current combination of
	* hole cards and the shared board. The both array is sorted. 
	* There is a second array, bothCards that is sorted by card, first by suit then by value.
	* Example 3h 9c Kd Qd Td 9d ( first by suit then by value.
	* 
	* Sorted arrays make checking for made hands very much more efficient, but 
	* a little confusing.
	* 
	* Returns the hand type MADE_NONE, MADE_PAIR, MADE_TWO_PAIR etc..
	* Updates this instance data.
	* If a hand can be made it is here it the instance of this class.
	* 
	* Note: The calling method should have determined that this type of hand is
	* either a sure thing ( Flop ) or likely ( Turn or River )
	* 
	*	if (EvalData.bothGap1_2 == 0 || EvalData.bothGap2_3 == 0 || EvalData.bothGap3_4 == 0
	*		EvalData.hand[EvalData.seat].doHandPairs();
	*	}else{
	* if (EvalData.bothGap1_2 == 1 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1
	*		&& EvalData.bothGap4_5 == 1) {
	*		EvalData.hand[EvalData.seat].doHandStraight();
	* }else{
	* if (EvalData.bothCardsSuit1 == EvalData.bothCardsSuit2
	*		&& EvalData.bothCardsSuit1 == EvalData.bothCardsSuit3
	*   	&& EvalData.bothCardsSuit1 == EvalData.bothCardsSuit4 && EvalData.bothGap4_5 == 1) {
	*   EvalData.hand[EvalData.seat].doHandFlush();
	* }
	* 
	* Top Pair: This refers to having a pair with the highest card on the flop. 
	* For example, if you have AK and the flop comes A-7-2, you have the top pair (a pair of Aces).
	*		
	* Middle Pair: This refers to having a pair with the middle card on the flop. 
	* For example, if you have 7K and the flop comes A-7-2, you have the middle pair (a pair of Sevens).
	*
	* Bottom Pair: This refers to having a pair with the lowest card on the flop. 
	* For example, if you have 2K and the flop comes A-7-2, you have the bottom pair (a pair of Twos).
	*
	* Over Pair: This refers to having a pair in your hand that is higher than any card on the board. 
	* For example, if you have QQ and the flop comes J-7-2, you have an over pair (a pair of Queens, which is higher
	* The strength of your pair can greatly influence your decisions in poker. 
	* A top pair or over pair is generally a strong hand, while a middle or bottom pair can often be vulnerable. 
	* However, context is crucial: the strength of your hand should always be evaluated relative to what 
	* other hands your opponents might have.
	*
	* Yes, when a player has two pair, they're often described based on the cards that make up the pairs.
	* While not as commonly referred to as top pair, middle pair, bottom pair, or over pair, 
	* these classifications can still be helpful in analyzing the strength of the hand. Here are a
	* few possible scenarios:
	*
	* Top Two Pair: When a player's hand includes the two highest cards on the board. For example, 
	* if you hold A-7 and the flop is A-7-2, you have top two pair.
	*
	* Top and Bottom Pair: When a player's hand includes the highest and the lowest card on the board. 
	* For example, if you hold A-2 and the flop is A-7-2, you have top and bottom pair.
	*
	* Bottom Two Pair: When a player's hand includes the two lowest cards on the board. 
	* For example, if you hold 7-2 and the flop is A-7-2, you have bottom two pair.
	*
	* Just like with a single pair, the strength of your two pair hand can greatly influence your 
	* decisions in poker. 
	* In general, two pair is a strong hand, but the exact strength of it can vary significantly based 
	* on the board texture, possible hands of your opponents, and your position at the table. 
	* Always consider these factors when deciding how to proceed with your hand.
	*
	 ***************************************************************************************/
	void doHandPairs() {
		int result = -1;
		result = doPairs();
		finish(result);
	}

	// Like above
	void doHandStraight() {
		final int result = doStraight();
		if (result == MADE_NONE) {
			this.type = MADE_NONE;
		}
		finish(result);
	}

	// Like above
	void doHandFlush() {
		final int result = doFlush();
		if (result == MADE_NONE) {
			this.type = MADE_NONE;
		}
		finish(result);
	}

	// Like above
	void doHandNone() {
		initialize();
		addBiggerCards();
		final int result = MADE_NONE;
		this.type = MADE_NONE;
		finish(result);
	}

	/*- ************************************************************************************
	 * This private helper method will complete filling in data prior to exit
	 ***************************************************************************************/
	private void finish(int type) {
		this.type = type;
		EvalData.madeArray[type] = true;
		if (this.size >= 4) {
			SortCards.quickSortValue(this.cards, 0, 4);
			EvalData.highCard1 = this.highCard1;
			EvalData.highCard2 = this.highCard2;
			EvalData.suit = this.suit;
			EvalData.kicker1 = this.kicker1;
			EvalData.kicker2 = this.kicker2;
			EvalData.kicker3 = this.kicker3;
			EvalData.highCards1[this.seat] = this.highCard1;
			EvalData.highCards2[this.seat] = this.highCard2;
			EvalData.suits[this.seat] = this.suit;
			EvalData.kickers1[this.seat] = this.kicker1;
			EvalData.kickers2[this.seat] = this.kicker2;
			EvalData.kickers3[this.seat] = this.kicker3;
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

	/*- ************************************************************************************
	 * This private helper method will check for the various hands that contain 
	 * paired cards and return either MADE_NONE or a hand type such as MADE_TWO_PAIR.
	 * The resulting hand is in this.cards.
	 ***************************************************************************************/
	private int doPairs() {
		if (EvalData.onePair && isPair()) {
			int x = whatKindOfPair();
			return x;
		}

		if (EvalData.twoPair && isTwoPair()) {
			int x = whatKindOfTwoPair();
			return x;
		}

		if (EvalData.threeOfKind && isSet()) {
			return MADE_SET;
		}

		if (EvalData.fourOfKind && is4Of()) {
			return MADE_FOUR_OF_A_KIND;
		}

		if (EvalData.fullHouse && isFullHouse()) {
			return MADE_FULL_HOUSE;
		}

		if (isNone()) {
		}
		return MADE_NONE;
	}

	/*- ************************************************************************************
	 * This private helper method will check for a Straight
	 * The resulting hand is in this.cards.
	 ***************************************************************************************/
	private int doStraight() {
		if (isStraight()) {
			return MADE_STRAIGHT;
		} else {
			if (isNone()) {
			}
		}
		return MADE_NONE;
	}

	/*- ************************************************************************************
	 * This private helper method will check for a Flush
	 * The resulting hand is in this.cards.
	 ***************************************************************************************/
	private int doFlush() {
		if (isFlush()) {
			return MADE_FLUSH;
		}
		return MADE_NONE;
	}

	/*- ************************************************************************************
	* Is this MADE_NONE
	* Completely accurate if Flop.
	* Not conclusive for Turn or River because it is possible that a hole card was not
	* included in boolean result. 
	* To be sure we need to check every possible hand on Turn and River using doFlopHands().
	* 
	* EvalData is udated:
	* 		EvalData.madeArray[MADE_NONE
	 ***************************************************************************************/
	boolean isNone() {
		initialize();
		addBiggerCards();
		boolean pf = false;
		boolean sf = false;
		boolean ff = false;
		boolean pt = false;
		boolean st = false;
		boolean ft = false;
		boolean pr = false;
		boolean sr = false;
		boolean fr = false;

		if (EvalData.street == FLOP) {
			pf = (EvalData.bothGap1_2 == 0 || EvalData.bothGap2_3 == 0 || EvalData.bothGap3_4 == 0
					|| EvalData.bothGap4_5 == 0);
			sf = (EvalData.bothGap1_2 == 1 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1
					&& EvalData.bothGap4_5 == 1);
			ff = (EvalData.bothCardsSuit1 == EvalData.bothCardsSuit2
					&& EvalData.bothCardsSuit1 == EvalData.bothCardsSuit3
					&& EvalData.bothCardsSuit1 == EvalData.bothCardsSuit4
					&& EvalData.bothCardsSuit1 == EvalData.bothCardsSuit5);
		} else if (EvalData.street == TURN) {
			pt = (EvalData.bothGap2_3 == 0 || EvalData.bothGap3_4 == 0 || EvalData.bothGap4_5 == 0
					|| EvalData.bothGap5_6 == 0);
			st = (EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1
					&& EvalData.bothGap5_6 == 1);
			ft = (EvalData.bothCardsSuit2 == EvalData.bothCardsSuit3
					&& EvalData.bothCardsSuit2 == EvalData.bothCardsSuit4
					&& EvalData.bothCardsSuit2 == EvalData.bothCardsSuit5
					&& EvalData.bothCardsSuit2 == EvalData.bothCardsSuit6);
		} else {
			pr = (EvalData.bothGap3_4 == 0 || EvalData.bothGap4_5 == 0 || EvalData.bothGap5_6 == 0
					|| EvalData.bothGap6_7 == 0);
			sr = (EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1 && EvalData.bothGap5_6 == 1
					&& EvalData.bothGap6_7 == 1);
			fr = (EvalData.bothCardsSuit3 == EvalData.bothCardsSuit4
					&& EvalData.bothCardsSuit3 == EvalData.bothCardsSuit5
					&& EvalData.bothCardsSuit3 == EvalData.bothCardsSuit6
					&& EvalData.bothCardsSuit3 == EvalData.bothCardsSuit7);
		}
		// If any are true there might be a made hand
		// Possibly not if hole cards were not part of true result
		if (pf || pt || pr || sf || st || sr || ff || ft || fr) {
			this.ok = false;
			return this.ok;
		}

		this.ok = true;
		return this.ok;
	}

	/*- ************************************************************************************
	* What kind of pair is this?
	* Returns MADE_BOTTOM_PAIR, MADE_MIDDLE_PAIR, MADE_TOP_PAIR, or MADE_OVER_PAIR 
	/*- ************************************************************************************/
	int whatKindOfPair() {
		if (this.pair1Value > EvalData.boardHighCardValue1) {
			return MADE_OVER_PAIR;
		}
		if (this.pair1Value == EvalData.boardLowCardValue) {
			return MADE_TOP_PAIR;
		}
		if (this.pair1Value > EvalData.boardLowCardValue) {
			return MADE_MIDDLE_PAIR;
		}
		if (this.pair1Value > EvalData.boardLowCardValue) {
			return MADE_BOTTOM_PAIR;
		}
		return -1;
	}

	/*- ************************************************************************************
	* What kind of two pair is this?
	* Returns MADE_BOTTOM_TWO_PAIR, MADE_TOP_AND_BOTTOM_TWO_PAIR, or MADE_TOP_TWO_PAIR
	/*- ************************************************************************************/
	int whatKindOfTwoPair() {
		if (this.pair1Value > EvalData.boardHighCardValue1 && this.pair2Value > EvalData.boardHighCardValue1) {
			return MADE_BOTTOM_TWO_PAIR;
		}else if (this.pair1Value == EvalData.boardLowCardValue && this.pair2Value > EvalData.boardLowCardValue) {
			return MADE_TOP_AND_BOTTOM_TWO_PAIR;
		}else if (this.pair1Value > EvalData.boardLowCardValue && this.pair2Value == EvalData.boardLowCardValue) {
			return MADE_TOP_TWO_PAIR;
		}

		return -1;
	}

	/*- ************************************************************************************
	 * Is this a Pair
	 * If there is a Pair return true.
	 * 
	 * EvalData is udated:
	 * EvalData.madeArray[MADE_PAIR]  
	 * EvalData.highCard1  
	 ***************************************************************************************/
	boolean isPair() {
		initialize();
		addCardsOfPair();
		if (this.size < 5) {
			addBiggerCards();
		}
		this.ok = isCardsOnePair();
		return this.ok;
	}

	/*- ************************************************************************************
	 * Is this 2 pairs
	 * If there are 2 pairs return true.
	 * 
	 * EvalData is udated:
	 * EvalData.madeArray[MADE_TWO_PAIR]  
	 * EvalData.highCard1  
	 * EvalData.highCard2  		
	 ***************************************************************************************/
	boolean isTwoPair() {
		initialize();
		addCardsOfPair();
		if (this.size < 5) {
			addBiggerCards();
		}
		this.ok = isCardsTwoPair();
		return this.ok;
	}

	/*- ************************************************************************************
	 * Is this set
	 * If sets return true.
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_SET]  
	 *		EvalData.highCard1  
	 *		EvalData.highCard2  	EvalData.highCard2  		
	 *		EvalData.highCard1  
	 ***************************************************************************************/
	boolean isSet() {
		initialize();
		addCardsOfPair();
		if (this.size < 5) {
			addBiggerCards();
		}
		this.ok = isCardsThreeOfAKind();
		return this.ok;
	}

	/*- ************************************************************************************
	* Is this fill house
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[FULL_HOUSE]  
	 *		EvalData.highCard1  
	 *		EvalData.highCard2  
	 ***************************************************************************************/
	boolean isFullHouse() {
		initialize();
		addCardsOfPair();
		if (this.size < 5) {
			this.ok = false;
			return this.ok;
		}
		this.ok = isCardsFullHouse();
		return this.ok;
	}

	/*- ************************************************************************************
	 * Is this4 of a kind
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[FOUR_OF_A_KIND]  
	 *		EvalData.highCard1  
	 ***************************************************************************************/
	boolean is4Of() {
		initialize();
		addCardsOfPair();
		if (this.size < 5) {
			addBiggerCards();
		}
		this.ok = isCardsFourOfAKind();
		return this.ok;
	}

	/*- ************************************************************************************
	 * Is this a Flush
	 * If there is a Flush return true.
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_FLUSH]  
	 *		EvalData.highCard1  
	 *		EvalData.suit  
	 ***************************************************************************************/
	boolean isFlush() {
		initialize();
		addCardsOfFlush();
		if (this.size < 5) {
			this.ok = false;
			return this.ok;
		}
		this.ok = isCardsFlush();
		if (this.ok) {
			EvalData.highCard1 = EvalData.bothCards[0].value;
			EvalData.suit = EvalData.bothCards[0].suit;
		}
		return this.ok;
	}

	/*- ************************************************************************************
	 * Is this a Straight
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_STRAIGHT]  
	 *		EvalData.highCard1 
	 ***************************************************************************************/
	boolean isStraight() {
		initialize();
		addCardsOfStraight();
		if (this.size < 5) {
			this.ok = false;
			return this.ok;
		}
		this.ok = isCardsStraight();
		if (this.ok) {
			EvalData.highCard1 = EvalData.bothCards[0].value;
		}
		return this.ok;
	}

	/*- ************************************************************************************
	 * Is this a Straight Flush
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_STRAIGHT_FLUSH ]  
	 *		EvalData.highCard1 
	 ***************************************************************************************/
	boolean isStraightFlush() {
		initialize();
		addCardsOfFlush();
		if (this.size < 5) {
			this.ok = false;
			return this.ok;
		}
		final boolean b = isCardsFlush();
		if (b && isCardsStraight() && this.cards[0].value != ACE) {
			this.ok = true;
		}
		return this.ok;
	}

	/*- ************************************************************************************
	* Is this a Royal Flush
	 * 
	 * EvalData is udated:
	 * 		EvalData.madeArray[MADE_ROYAL_FLUSH]  
	 ***************************************************************************************/
	boolean isRoyalFlush() {
		initialize();
		addCardsOfFlush();
		if (size < 5) {
			this.ok = false;
			return this.ok;
		}
		final boolean b = isCardsFlush();
		if (b && isCardsStraight() && this.cards[0].value == ACE) {
			this.ok = true;
		}
		return this.ok;
	}

	/*- ************************************************************************************
	 * The combined hole cards and board array is searched for pairs.
	 * If a pair is found it is added to cards 
	 * If more than 2 cards are paired they are all added, set or quads.
	 ***************************************************************************************/
	private void addCardsOfPair() {
		for (int i = 0; i < EvalData.bothCount - 1; i++) {
			if (EvalData.both[i].value == EvalData.both[i + 1].value) {
				if (this.highCard1 == -1) {
					this.highCard1 = EvalData.both[i].value;
				} else if (this.highCard2 == -1) {
					this.highCard2 = EvalData.both[i].value;
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
			if (this.size > 4) {
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
				if (EvalData.highCard1 == -1 && !taken[i]) {
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
		if (this.size < 5) {
			Crash.log("wtf " + this.size);
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
	 * Does cards have one pair
	 * Returns true or false
	 ***************************************************************************************/
	private boolean isCardsOnePair() {
		if (this.size == 5) {
			if (this.cards[0].value == this.cards[1].value) {
				this.pair1Value = this.cards[0].value;
				return true;
			}
			if (this.cards[1].value == this.cards[2].value) {
				this.pair1Value = this.cards[1].value;
				return true;
			}
			if (this.cards[2].value == this.cards[3].value) {
				this.pair1Value = this.cards[2].value;
				return true;
			}
			if (this.cards[3].value == this.cards[4].value) {
				this.pair1Value = this.cards[3].value;
				return true;
			}
		} else if (this.size == 4) {
			if (this.cards[0].value == this.cards[1].value) {
				this.pair1Value = this.cards[0].value;
				return true;
			}
			if (this.cards[1].value == this.cards[2].value) {
				this.pair1Value = this.cards[1].value;
				return true;
			}
			if (this.cards[2].value == this.cards[3].value) {
				this.pair1Value = this.cards[2].value;
				return true;
			}
		} else if (this.size == 3) {
			if (this.cards[0].value == this.cards[1].value) {
				this.pair1Value = this.cards[0].value;
				return true;
			}
			if (this.cards[1].value == this.cards[2].value) {
				this.pair1Value = this.cards[1].value;
				return true;
			}
		}
		return false;
	}

	/*- ************************************************************************************
	 * Does cards have one pair
	 * Returns true or false.
	 * Must be exactly 2 pair, no set or quads
	 * Returns number of pairs.
	 * Sets pair values in this.pair1Valur and this.pair2Value
	 ***************************************************************************************/
	private boolean isCardsTwoPair() {
		int numPairs = 0;
		for (int i = 0; i < 4; i++) {
			if (this.cards[i].value == this.cards[i + 1].value) {
				if (numPairs == 0) {
				}
				this.pair1Value = this.cards[i].value;
			} else {
				this.pair1Value = this.cards[i].value;
			}
			numPairs++;
		}
		return numPairs == 2;
	}

	/*- ************************************************************************************
	* Does cards have a set Returns true or false
	 ***************************************************************************************/
	private boolean isCardsThreeOfAKind() {
		for (int i = 0; i < 3; i++) {
			if (this.cards[i].value == this.cards[i + 1].value && this.cards[i].value == this.cards[i + 2].value) {
				return true;
			}
		}
		return false;
	}

	/*- ************************************************************************************
	 * Does cards have four of a kind
	 * Returns true or false
	 ***************************************************************************************/
	private boolean isCardsFourOfAKind() {
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

	/*- ************************************************************************************
	 * Does cards have a full house
	 * Returns true or false
	 ***************************************************************************************/
	private boolean isCardsFullHouse() {
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

	/*- ************************************************************************************
	 * Does cards contain a straight
	 * Returns true or false
	 ***************************************************************************************/
	private boolean isCardsStraight() {
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

	/*- ************************************************************************************
	 * Determines if the cards have a flush.
	 * Returns true or false.
	 ***************************************************************************************/
	boolean isCardsFlush() {
		for (int i = 0; i < 4; i++) {
			if (this.cards[i].suit != this.cards[i + 1].suit) {
				return false;
			}
		}
		return true;
	}

	/*- ************************************************************************************
	* Show details
	 ***************************************************************************************/
	String show() {
		var st = new StringBuilder().append("//Hand for seat ").append(String.valueOf(EvalData.seat)).append(" ")
				.append(STREET_ST[EvalData.street]).append(" handType ").append(MADE_ARRAY_ST[this.type]).append("\r\n")
				.toString();
		st += new StringBuilder().append(" position ").append(POSITION_ST[this.seat]).append(" Relative position ")
				.append(RP_ST[this.seat]).toString();
		st += new StringBuilder().append("//Correct ").append(Format.format(this.ok)).append("\r\n").toString();
		st += new StringBuilder().append("//hole cards ").append(this.holeCard1).append(this.holeCard2).append("\r\n")
				.toString();
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
