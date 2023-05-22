//package evaluate_streets;
/*- ******************************************************************************
* This class simulates a dealer dealing cards in a normal game.
* A full table is dealt, six sets of hole cards, a Flop, Turn and River.
* Cards are random.
* 
* The hole cards are checked for Ranges normally used in a $1/$2 game.
* Each position, Small Blind through the Button has it's own specific default range.
* For example, the Button might be 30%. 
* Each pair of hole cards are compared to the Range for their position and if
* not in that Range the hand is folded.  If there are not at least 2 players not 
* folded then the deck is shuffled and done over again until at least 2 players.
* 
* The positions are rotated each hand, as in a normal game. 
* Seat 0, the Hero that we are collecting information on is assigned to a position
* by simply taking the hole cards that are not folded and assigning them to
* seats starting with 0.
* 
* The idea is that this will result in a more accurate simulation of play. 
* 
* Public methods:
* 		dealTable()
* 		 setRange(int position, double range)
* 		setRotation(boolean b)
* 
* The typical ranges for each position in a no-limit Texas hold'em 6-max $1/$2 game would 
* vary depending on various factors such as the playing style of opponents, table dynamics, 
* and stack sizes, but here are some general guidelines:
*
* Under the Gun (UTG) - This is the first position after the blinds, and players in this position 
* should generally play tight and conservative ranges. 
* Some typical hands that players may play in this position are strong pairs (e.g., AA, KK, QQ), 
* broadway hands (e.g., AK, AQ), and some suited connectors (e.g., 89s, 78s).
 *
* Middle Position (MP) - Players in middle position have some information about the players 
* in earlier positions and can play a wider range of hands than UTG. 
* Some typical hands that players may play in this position are strong pairs, broadway hands, 
* some suited connectors, and some suited aces (e.g., A9s, A8s).
*
* Cutoff (CO) - The player in this position is just one seat away from the dealer and can play 
* a wider range of hands, as they have the advantage of acting last on all post-flop 
* betting rounds. 
* Some typical hands that players may play in this position are pairs, broadway hands,
* suited connectors, suited aces, and some suited one-gappers (e.g., J9s, T8s).
*
* Button (BTN) - The player on the button has the most advantageous position in the game 
* and can play a very wide range of hands. 
* Some typical hands that players may play in this position are pairs, broadway hands, 
* suited connectors, suited aces, suited one-gappers, and any two cards that
* can make a straight or flush.
*
* Small Blind (SB) - This is the first of the two forced bets and players in this position are 
* at a disadvantage, as they will be out of position for the rest of the hand. 
* Some typical hands that players may play in this position are strong pairs, broadway hands, 
* and some suited connectors.
*
* Big Blind (BB) - This is the second of the two forced bets and players in this position 
* are also at a disadvantage, but they get to act last in the pre-flop betting round. 
* Some typical hands that players may play in this position are strong pairs, 
* broadway hands, suited connectors, and some suited aces.
*
* Again, these are just general guidelines and there can be a lot of variation depending 
* on the specific table dynamics and player tendencies. 
* It's always important to adjust your range based on the table and players you're up against. 
*
* Sure, here are some rough percentages of the typical ranges for each position in a
* no-limit Texas hold'em 6-max $1/$2 game:
*
* Under the Gun (UTG) - 8% to 12% of hands
* Middle Position (MP) - 12% to 18% of hands
* Cutoff (CO) - 20% to 30% of hands
* Button (BTN) - 35% to 50% of hands
* Small Blind (SB) - 10% to 15% of hands
* Big Blind (BB) - 10% to 15% of hands
*
* Again, these are just rough estimates and the percentages may vary 
* depending on the specific table and players you're up against.
* It's always important to adjust your range based on the situation and your 
* opponents' tendencies.
* 
* @author PEAK_
*******************************************************************************/

public class Dealer implements Constants {
	static boolean initialized = false;
	static boolean initialzedRanges = false;
	static boolean initializedDeck = false;

	private Dealer() {
		throw new IllegalStateException("Utility class");
	}

	/*- ******************************************************************************
	 * One time initialization
	*********************************************************************************/
	static void initialize() {
		if (initialized) {
			return;
		}
		initialized = true;
		EvalData.positions[0] = SB;
		EvalData.positions[1] = BB;
		EvalData.positions[2] = UTG;
		EvalData.positions[3] = MP;
		EvalData.positions[4] = CO;
		EvalData.positions[5] = BU;
	}

	

	/*- ******************************************************************************
		 * This method deals two cards from the deck and places then in the hole cards.
		 * The hole cards are sorted.
		 * Arg0 - Seat number 0-5 
		  ********************************************************************************/
	static void dealHoleCards(int seat) {
		EvalData.holeCards[seat][0] = Deck.pop();
		EvalData.holeCards[seat][1] = Deck.pop();
		sortHoleCards(seat);
		EvalData.holeCard1 = EvalData.holeCards[seat][0];
		EvalData.holeCard2 = EvalData.holeCards[seat][1];
		EvalData.handIndex = getArrayIndexCard(EvalData.holeCards[seat][0], EvalData.holeCards[seat][1]);
		EvalData.handIndexes[seat] = EvalData.handIndex;
	}

	/*- ******************************************************************************
	 * This method deals two cards from the deck and places then in the hole cards 
	 * for each seat. 
	 * The hole cards are sorted.
	  ********************************************************************************/
	static void dealAllHoleCards() {
		for (int i = 0; i < PLAYERS; i++) {
			EvalData.holeCards[i][0] = Deck.pop();
			EvalData.holeCards[i][1] = Deck.pop();
			if (EvalData.holeCards
			sortHoleCards(i);
			EvalData.holeCard1 = EvalData.holeCards[i][0];
			EvalData.holeCard2 = EvalData.holeCards[i][1];
			EvalData.handIndex = getArrayIndexCard(EvalData.holeCards[i][0], EvalData.holeCards[i][1]);
			EvalData.handIndexes[i] = EvalData.handIndex;
		}
	}

	/*- ******************************************************************************
	 * This method will deal the Flop from the deck.
	 * The flop is sorted.
	  ********************************************************************************/
	static void dealFlop() {
		EvalData.flop[0] = Deck.pop();
		EvalData.flop[1] = Deck.pop();
		EvalData.flop[2] = Deck.pop();
		if (EvalData.flop[0] == null || EvalData.flop[1] == null || EvalData.flop[2] == null) {
			System.out.println("ERROR: Flop null card " + EvalData.flop[0] 
			+ " " + EvalData.flop[1] + " " + EvalData.flop[2]);
		}	
		sortFlop();
		EvalData.board[0] = EvalData.flop[0];
		EvalData.boardUnsorted[0] = EvalData.flop[0];
		EvalData.board[1] = EvalData.flop[1];
		EvalData.boardUnsorted[1] = EvalData.flop[1];
		EvalData.board[2] = EvalData.flop[2];
		EvalData.boardUnsorted[2] = EvalData.flop[2];
		EvalData.cardCount = 3;
	}

	/*- ******************************************************************************
	 * This method will deal all hole cards then deal the flop.
	  ********************************************************************************/
	static void dealHoleCardsAndFlop() {
		dealAllHoleCards();
		dealFlop();
	}

	/*- ******************************************************************************
	  * This method will deal the turn card.
	 * It is inserted into the board in order to maintain sort order.
	  ********************************************************************************/
	static void dealTurnCard() {
		EvalData.turnCard = Deck.pop();
		if (EvalData.turnCard == null) {
			System.out.println("ERROR: Turn  null card");
		}	
		EvalData.board[3] = EvalData.turnCard;
		EvalData.boardUnsorted[3] = EvalData.turnCard;
		EvalData.cardCount = 4;
	}

	/*- ******************************************************************************
	 * This method will deal theRiver card from the deck.
	 * It is inserted into the board in order to maintain sort order
	  ********************************************************************************/
	static void dealRiverCard() {
		EvalData.riverCard = Deck.pop();
		if (EvalData.riverCard == null) {
			System.out.println("ERROR:river  null card");
		}
		EvalData.board[4] = EvalData.riverCard;
		EvalData.boardUnsorted[4] = EvalData.riverCard;
		EvalData.cardCount = 5;
	}

	/*- ******************************************************************************
	 * This method will deal hole cards for 6 players, the Flop, Turn, and River.
	 * Actual implementation is in the Dealer Class.
	 * The hole cards are checked for Ranges normally used in a $1/$2 game.
	 * Each position, Small Blind through the Button has it's own specific default range.
	 * For example, the Button might be 30%. 
	 * Each pair of hole cards are compared to the Range for their position and if
	 * not in that Range the hand is folded.  If there are not at least 2 players not 
	 * folded then the deck is shuffled and done over again until at least 2 players.
	 * 
	 * The positions are rotated each hand, as in a normal game. 
	 * Seat 0, the Hero that we are collecting information on is assigned to a position
	 * by simply taking the hole cards that are not folded and assigning them to
	 * seats starting with 0.
	 * ********************************************************************************/
	static void dealTable() {
		dealHoleCardsInRange(2);
		dealFlop();
		dealTurnCard();
		dealRiverCard();
	}

	/*- ******************************************************************************
		 *  Sort the first 3 cards in the board, high cards first
		*******************************************************************************/
	static void sortFlop() {
		Card cardSave = null;
		if (EvalData.flop[0].value >= EvalData.flop[1].value && EvalData.flop[1].value >= EvalData.flop[2].value) {
			return;
		}
		if (EvalData.flop[1].value > EvalData.flop[0].value && EvalData.flop[1].value >= EvalData.flop[2].value) {
			cardSave = EvalData.flop[0];
			EvalData.flop[0] = EvalData.flop[1];
			EvalData.flop[1] = cardSave;
		} else if (EvalData.flop[2].value > EvalData.flop[0].value && EvalData.flop[2].value > EvalData.flop[1].value) {
			cardSave = EvalData.flop[0];
			EvalData.flop[0] = EvalData.flop[2];
			EvalData.flop[2] = cardSave;
		}
		if (EvalData.flop[2].value <= EvalData.flop[1].value) {
			return;
		}
		cardSave = EvalData.flop[1];
		EvalData.flop[1] = EvalData.flop[2];
		EvalData.flop[2] = cardSave;
	}

	/*- ******************************************************************************
	 *  return board as string
	*******************************************************************************/
	static String boardToString() {
		var st = "\r\n";
		st += new StringBuilder().append(EvalData.board[0].toString()).append(EvalData.board[1].toString())
				.append(EvalData.board[2].toString()).toString();
		if (EvalData.cardCount >= 4 && EvalData.board[3] != null) {
			st += EvalData.board[3].toString();
		}
		if (EvalData.cardCount == 5 && EvalData.board[4] != null) {
			st += EvalData.board[4].toString();
		}
		return st;
	}

	/*- ******************************************************************************
	 * Sort HoleCards
	*******************************************************************************/
	static void sortHoleCards(int seat) {
		if (EvalData.holeCards[seat][0].value >= EvalData.holeCards[seat][1].value) {
			return;
		}
		final var cardSave = EvalData.holeCards[seat][0];
		EvalData.holeCards[seat][0] = EvalData.holeCards[seat][1];
		EvalData.holeCards[seat][1] = cardSave;
	}

	/*- ******************************************************************************
	 *  get hole cards as string
	*******************************************************************************/
	static String holeCardsToString() {
		return EvalData.holeCard1.toString() + EvalData.holeCard2.toString();
	}

	/*- ******************************************************************************
	 * Returns the value index into an array of the 169 shorthand hands Arg0 is
	 * the first card Arg1 is the second card Returns an index 0 - 195.
	*******************************************************************************/
	static int getArrayIndexCard(Card card1, Card card2) {
		final int v1 = card1.value == 0 ? 13 : card1.value;
		final int v2 = card2.value == 0 ? 13 : card2.value;
		final int $ = -(v1 - 13);
		final int j = -(v2 - 13);
		return card1.suit != card2.suit ? $ + 13 * j : j + 13 * $;
	}

	/*- ******************************************************************************
	 * This method will deal hole cards for 6 players, the Flop, Turn, and River.
	 * The hole cards are checked for Ranges normally used in a $1/$2 game.
	 * Each position, Small Blind through the Button has it's own specific default range.
	 * For example, the Button might be 30%. 
	 * Each pair of hole cards are compared to the Range for their position and if
	 * not in that Range the hand is folded.  If there are not at least 2 players not 
	 * folded then the deck is shuffled and done over again until at least 2 players.
	 * 
	 * The positions are rotated each hand, as in a normal game. 
	 * Seat 0, the Hero that we are collecting information on is assigned to a position
	 * by simply taking the hole cards that are not folded and assigning them to
	 * seats starting with 0.
	 * 
	 * Arg0 - Minimum number of players with hole cards in range.
	 * ********************************************************************************/
	static void dealHoleCardsInRange(int num) {
		boolean done = false;
		int count = 0;
		int numOK = 0;

		rotatePositions();
		while (!done) {
			Deck.shuffle();
			numOK = 0;
			for (int i = 0; i < PLAYERS; i++) {
				if (EvalData.positions[i] == SB) {
					dealHoleCards(SB);
					if (EvalData.preflopRanges[SB].rangeArray[EvalData.bothIndexs[SB]] > 0) {
						++numOK;
					} else {
						EvalData.playerFolded[SB] = true;
					}
				} else if (EvalData.positions[i] == BB) {
					dealHoleCards(BB);
					if (EvalData.preflopRanges[BB].rangeArray[EvalData.bothIndexs[BB]] > 0) {
						++numOK;
					} else {
						EvalData.playerFolded[BB] = true;
					}
				} else if (EvalData.positions[i] == UTG) {
					dealHoleCards(UTG);
					if (EvalData.preflopRanges[UTG].rangeArray[EvalData.bothIndexs[UTG]] > 0) {
						++numOK;
					} else {
						EvalData.playerFolded[UTG] = true;
					}
				} else if (EvalData.positions[i] == MP) {
					dealHoleCards(MP);
					if (EvalData.preflopRanges[MP].rangeArray[EvalData.bothIndexs[MP]] > 0) {
						++numOK;
					} else {
						EvalData.playerFolded[MP] = true;
					}
				} else if (EvalData.positions[i] == CO) {
					dealHoleCards(CO);
					if (EvalData.preflopRanges[SB].rangeArray[EvalData.bothIndexs[CO]] > 0) {
						++numOK;
					} else {
						EvalData.playerFolded[CO] = true;
					}
				} else if (EvalData.positions[i] == BU) {
					dealHoleCards(BU);
					if (EvalData.preflopRanges[BU].rangeArray[EvalData.bothIndexs[BU]] > 0) {
						++numOK;
					} else {
						EvalData.playerFolded[BU] = true;
					}
				}
				if (numOK >= num) {
					done = true;
				}

				++count;
				if (count > 500) {
					done = true;
					Logger.log(new StringBuilder()
							.append("//ERROR dealHoleCardsInRange() unable to find enough players in range ")
							.append(count).append(" ").append(numOK).append(" ").append(num).toString());
				}
			}
		}
	}

	/*- ******************************************************************************
	 * This method will deal hole cards for 6 players .
	 * Ranges have already been setup.
	 * The positions are rotated each hand, as in a normal game. 
	 * ********************************************************************************/
	static void simulationMode() {
		rotatePositions();
		Deck.shuffle();
		dealHoleCards(0);
		dealHoleCards(1);
		dealHoleCards(2);
		dealHoleCards(3);
		dealHoleCards(4);
		dealHoleCards(5);
	}

	/*- **************************************************************************** 
	 * Set range percentage for position
	 * Instead of using the default values, the user specifies a percentage value.
	 * Arg0 - Position SB, BB,...
	 * Arg1 - Percentage 
	*********************************************************************************/
	static void setRange(int pos, double percent) {
		EvalData.preflopRanges[pos].buildRangePercentage(percent);
	}

	/*- ******************************************************************************
	 * Set rotation 
	 * Arg0 - boolean
	*********************************************************************************/
	static void setRotation(boolean b) {
		EvalData.rotatePlayers = b;
	}

	/*- ******************************************************************************
	* Rotate positions
	* The positions are rotated each hand, as in a normal game.
	* positions is indexed by seat returns position
	*********************************************************************************/
	static void rotatePositions() {
		final int save = EvalData.positions[5];
		EvalData.positions[5] = EvalData.positions[4];
		EvalData.positions[4] = EvalData.positions[3];
		EvalData.positions[3] = EvalData.positions[2];
		EvalData.positions[2] = EvalData.positions[1];
		EvalData.positions[1] = EvalData.positions[0];
		EvalData.positions[0] = save;
		setPositions();
	}

	/*- **************************************************************************** 
	* This method sets the players seat number into EvalData.positions array
	* EvalData.positions is indexed by seat returns position ( SB, BB, UTG, MO, CO, BU)
	*******************************************************************************/
	private static void setPositions() {
		for (int i = 0; i < PLAYERS; i++) {
			if (EvalData.positions[i] == SB) {
				EvalData.playerPositions[SB] = i;
			}
			if (EvalData.positions[i] == BB) {
				EvalData.playerPositions[BB] = i;
			}
			if (EvalData.positions[i] == UTG) {
				EvalData.playerPositions[UTG] = i;
			}
			if (EvalData.positions[i] == MP) {
				EvalData.playerPositions[MP] = i;
			}
			if (EvalData.positions[i] == CO) {
				EvalData.playerPositions[CO] = i;
			}
			if (EvalData.positions[i] == BU) {
				EvalData.playerPositions[BU] = i;
			}
		}
	}
}
