//package evaluate_streets;

/*-  **************************************************************************************
* This Class contains the methods used for Showdown.
* When the River evaluation is complete it will call showdownValue() to calculate the 
* relative value of the made hand on the River.
*
* After all players have completed the River evaluation showdown() will be called to
* determine the winner using values saved by showdownValue().
* A tie is possible but unusual.
* 
* @author PEAK_
* 
***************************************************************************************/
public class Showdown implements Constants {

	/*-  **************************************************************************************
	* This Class contains the methods used for Showdown.
	* The public methods are:
	*   showdownValue()
	*  	showdown()\
	* There are 2 methods that create Strings for display of the results:
	* 	showdownValueSt()
	*   showdownSt()	
	*
	***************************************************************************************/

	private Showdown() {
		throw new IllegalStateException("Utility class");
	}

	/*- *****************************************************************************
	* This method ranks one players for showdown value.
	* The larger the value the better the hand.
	* There is no other significance to the value. 
	* 
	* Note that the numbers used like 804000 are arbitrary and were chosen only to
	* be sure that the resulting value is not duplicated.
	*
	*  For a Set the number is  setHighCard + 804000
	*  80400 is just a relative number with the value of the sets high card added to it 
	*  because all sets are not equal.  For most hand types there is some equivalent
	*  math for things like kickers.
	*  Methods that evaluate the board and hands were already required for evaluations
	*  other than showdown so I wrote this code. Turned out to be faster, a bonus.
	*  
	*  Arg0 - Seat
	*   Sets values in EvalData
	******************************************************************************/
	static void showdownValue(int seat) {
		EvalData.playerDidShowdownValue[seat] = true;
		madeHands(); // Convert hands in

		int max = findMaxValue(seat);

		EvalData.showdownHand[seat] = max;
		EvalData.highCard1 = EvalData.highCards1[seat];
		EvalData.highCard2 = EvalData.highCards2[seat];
		EvalData.suit = EvalData.suits[seat];
		EvalData.kicker1 = EvalData.kickers1[seat];
		EvalData.kicker2 = EvalData.kickers2[seat];
		EvalData.kicker3 = EvalData.kickers3[seat];
		System.out.println(
				"Player " + seat + " showdown value is " + max + " " + EvalData.highCard1 + " " + EvalData.highCard2 +
						" " + EvalData.kicker1 + " " +
						EvalData.kicker2 + " " + EvalData.kicker3 + " " + EvalData.suit);

		if (max == MADE_ROYAL_FLUSH) {
			EvalData.maxValue = 816000;
		} else if (max == MADE_STRAIGHT_FLUSH) {
			EvalData.maxValue = EvalData.highCard1 + 814000;
		} else if (max == MADE_FOUR_OF_A_KIND) {
			EvalData.maxValue = EvalData.highCard1 + 812000;
		} else if (max == MADE_FULL_HOUSE) {
			EvalData.maxValue = EvalData.highCard1 + 100 * EvalData.highCard2 + 810000;
		} else if (max == MADE_FLUSH) {
			EvalData.maxValue = EvalData.highCard1 + 808000;
		} else if (max == MADE_STRAIGHT) {
			EvalData.maxValue = EvalData.highCard1 + 806000;
		} else if (max == MADE_SET) {
			EvalData.maxValue = EvalData.highCard1 + 804000;
		} else if (max == MADE_TWO_PAIR) {
			kickers(max);
			EvalData.maxValue = 800000 + EvalData.highCard1 * 100 + EvalData.highCard2 * 10 + EvalData.kicker1;
		} else if (max <= MADE_PAIR) {
			kickers(max);
			EvalData.maxValue = 500000 + EvalData.highCard1 * 4000 + EvalData.kicker1 * 200 + EvalData.kicker2 * 10
					+ EvalData.kicker3;
		} else if (max == MADE_NONE) {
			kickers(max);
			EvalData.maxValue = EvalData.kicker1 * 10000 + EvalData.kicker2 * 800 + EvalData.kicker3 * 80;
		} else {
			Crash.log("ERROR showdownValue() No hand found ");
			EvalData.maxValue = -1;
		}

		EvalData.showdownValue[EvalData.seat] = EvalData.maxValue;
		EvalData.showdownRank[EvalData.seat] = EvalData.maxValue;
	}

	/*- *****************************************************************************
	 * This method will determine the winner or winners of a hand.
	 * showdownValur() has already been called for all players that made it to the River. 
	 * The winner is the one with the largest showdown value.
	 * A tie is possible.
	 *  
	 *  Normal use in this project is:
	 *  	Run the Flop, Turn, and River collecting data for one seat. The Hero.
	 *  	Run showdownValue ic called on the River.
	 *  	When all players have completed the River call showdown to determine the
	 *  	winner and details on the winning hand.
	 *  		  
	  ******************************************************************************/
	static void showdown() {
		EvalData.showdown = true;
		int max = -1;

		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i]) {
				if (!EvalData.playerDidShowdownValue[i]) {
					showdownValue(i);
					EvalData.showdownRank[i] = EvalData.showdownValue[i];
					if (EvalData.showdownRank[i] > max) {
						max = EvalData.showdownRank[i];
					}
				}
			} else {
				EvalData.showdownRank[i] = -1;
			}
		}

		for (int i = 0; i < PLAYERS; i++) {
			final boolean condition = !EvalData.playerFolded[i] && EvalData.showdownRank[i] != -1;
			if (condition) {
				if (EvalData.showdownRank[i] < max) {
					EvalData.playerWonShowdown[i] = false;
				} else {
					EvalData.playerWonShowdown[i] = true;
					EvalData.winnerSeat = i;
					EvalData.showdownWinnerHand[i] = EvalData.showdownHand[i];
					System.out.println("Player " + i + " won Showdown " + EvalData.showdownHand[i]);
				}
			}
		}
		EvalData.showdown = EvalData.winner = true;
		if (EvalData.manyHands) {
			Classification.doShowdown();
		}
	}

	/*- *****************************************************************************
	 * Find maximum value of hand
	  ******************************************************************************/
	private static int findMaxValue(int seat) {
		int max = MADE_NONE;
		for (int i = MADE_HANDS_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				max = i;
				EvalData.showdownHand[seat] = max;
				break;
			}
		}
		return max;
	}

	/*- *****************************************************************************
	 * Helper method used byShowdown
	 * Determines best kicker.
	 * Arg0 - Max value of hand
	 ******************************************************************************/
	private static void kickers(int max) {
		EvalData.kicker1 = -1;

		// Find highest card not same value an any pair sorted so first found
		if (max == MADE_TWO_PAIR) {
			for (int i = 0; i < 7; i++) {
				if (EvalData.board[i].value != EvalData.highCard1 && EvalData.board[i].value != EvalData.highCard2
						&& EvalData.board[i].value > EvalData.kicker1) {
					EvalData.kicker1 = EvalData.board[i].value;
					EvalData.kickers1[EvalData.seat] = EvalData.kicker1;
					break;
				}
			}
			return;
		}

		// Pocket pair - kickers are the best 3 on board and board is sorted
		if (max == MADE_PAIR) {
			EvalData.kicker1 = EvalData.board[0].value;
			EvalData.kicker2 = EvalData.board[1].value;
			EvalData.kicker3 = EvalData.board[2].value;
			EvalData.kickers1[EvalData.seat] = EvalData.kicker1;
			EvalData.kickers2[EvalData.seat] = EvalData.kicker2;
			EvalData.kickers3[EvalData.seat] = EvalData.kicker3;
			return;
		}

		EvalData.kicker2 = -1;
		EvalData.kicker3 = -1;

		for (int i = 0; i < 7; i++) {
			if (EvalData.highCard1 != EvalData.both[i].value) {
				if (EvalData.kicker1 == -1) {
					EvalData.kicker1 = EvalData.both[i].value;
				} else if (EvalData.kicker2 == -1) {
					EvalData.kicker2 = EvalData.both[i].value;
				} else if (EvalData.kicker3 == -1) {
					EvalData.kicker3 = EvalData.both[i].value;
				}
				break;
			}
		}

		// Nothing but high cards and hand is sorted
		if (max == MADE_NONE) {
			EvalData.kicker1 = EvalData.both[0].value;
			EvalData.kicker2 = EvalData.both[1].value;
			EvalData.kicker3 = EvalData.both[2].value;
			EvalData.kickers1[EvalData.seat] = EvalData.kicker1;
			EvalData.kickers2[EvalData.seat] = EvalData.kicker2;
			EvalData.kickers3[EvalData.seat] = EvalData.kicker3;
		}

	}

	/*- *****************************************************************************
	 * A method to gather information on the players hand that called showdownValue. 
	 * It's only use is to display the results of the showdownValue method.
	 * It creates a String that is saved in EvalData.showdownValueSt[EvalData.seat].
	 ***************************************************************************** */
	static String showdownValueString() {
		String st = "Sea";
		
		switch (EvalData.showdownHand[EvalData.seat]) {
			case MADE_ROYAL_FLUSH -> {
				st += "Waited a long time for a Royal Flush";
			}
			case MADE_STRAIGHT_FLUSH -> {
				EvalData.suits[EvalData.seat] = EvalData.suit;
				st += new StringBuilder().append("Straight Flush high card ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).append(SUITS_ST[EvalData.suit]).toString();
			}
			case MADE_FOUR_OF_A_KIND -> {
				st = "Four of a kind high card "
						+ Card.CARDS[EvalData.highCard1];
			}
			case MADE_FULL_HOUSE -> {
				st = new StringBuilder().append("Full House high card ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).append(" pair high card ")
						.append(Card.CARDS_REVERSE[EvalData.highCard2]).toString();
			}
			case MADE_FLUSH -> {
				System.out.println("Flush  " + EvalData.highCard1 + " " + EvalData.suit + " " + EvalData.seat);
				st = new StringBuilder().append("Flush high card  ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).append(" suit ")
						.append(SUITS2_ST[EvalData.suit])
						.toString();
			}
			case MADE_STRAIGHT -> {
				st = "Straight high card " +
						Card.CARDS_REVERSE[EvalData.highCard1];
			}
			case MADE_SET -> {
				st = new StringBuilder().append("Set high card  ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).append(showKickers()).toString();
			}
			case MADE_TWO_PAIR -> {
				st = new StringBuilder().append("Two pair ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).toString();
			}
			case MADE_PAIR -> {
				st = new StringBuilder().append("Any pair card ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).append(showKickers()).toString();
			}
			case MADE_NONE -> {
				st = new StringBuilder()
						.append("What were you thinking kickers ")
						.append(String.valueOf(EvalData.kicker1)).append(" ").append(String.valueOf(EvalData.kicker2))
						.append(" ").append(String.valueOf(EvalData.kicker3)).append(" ").toString();
			}
			default -> {
				Logger.log(new StringBuilder().append("showdownValueString() hand not found ")
						.append(EvalData.showdownHand[EvalData.seat]).append(" ").append(EvalData.seat).toString());
			}
		}
		return st;
	}

	private static String showKickers() {
		var st = "";
		if (EvalData.kicker1 != -1) {
			st = "\r\nkickers " + Card.CARDS_REVERSE[EvalData.kicker1];
		}
		if (EvalData.kicker2 != -1) {
			st = " " + Card.CARDS_REVERSE[EvalData.kicker2];
		}
		if (EvalData.kicker3 != -1) {
			st = " " + Card.CARDS_REVERSE[EvalData.kicker3];
		}
		return st;
	}

	/*- *****************************************************************************
	 * A method to return a String showing the results of a Showdown
	 ***************************************************************************** */
	static String showdownString() {
		var st = "";

		if (!EvalData.showdown) {
			st = "No Showdown";
		}
		st = "Showdown results \r\n";
		st += new StringBuilder().append("The board was ").append(EvalData.board[0].toString())
				.append(EvalData.board[1].toString()).append(EvalData.board[2].toString())
				.append(EvalData.board[3].toString()).append(EvalData.board[4].toString()).append("\r\n").toString();

		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i]) {
				st += new StringBuilder().append("Hole cards seat  ").append(String.valueOf(i)).append(" ")
						.append(EvalData.holeCards[i][0].toString()).append(EvalData.holeCards[i][1].toString())
						.append("\r\n").toString();
			}
		}

		st += new StringBuilder().append("The winner was seat ").append(String.valueOf(EvalData.winnerSeat))
				.append(" with a ").append(MADE_ARRAY_ST[EvalData.showdownHand[EvalData.winnerSeat]]).append("\r\n")
				.toString();
		int players = 0;
		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i]) {
				++players;
			}
		}
		st += new StringBuilder().append("There were ").append(String.valueOf(players)).append(" players\r\n")
				.toString();
		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i]) {
				st += new StringBuilder().append("Seat ").append(String.valueOf(i)).append(" had ")
						.append(MADE_ARRAY_ST[EvalData.showdownHand[i]]).toString();
				if (EvalData.playerWonShowdown[i]) {
					st += " won \r\n";
				} else {
					st += " lost \r\n";
				}
			}
		}
		return st;
	}

	/*- *****************************************************************************
	 * This method will scan ay for hands that are not draws ( made hands ).
	 * A made hand array for the current street is set to those values.
	 * 
	*  Example  MIDDLE_PAIR to HAND_MIDDLE_PAIR
	 *  Hands like MIDDLE_PAIR are mostly about draws and relative strength.
	 *  At Showdown a pair is nothing but a pair and only it's relative strength 
	 *  is important.
	 * 
	  ******************************************************************************/
	private static void madeHands() {
		final int value = 0;
		for (int i = 0; i < EvalData.madeArray.length; i++) {
			final boolean condition = EvalData.madeArray[i] && value != -1;
			if (condition) {
				EvalData.madeArray[value] = true;
				if (EvalData.street == FLOP) {
					EvalData.madeArrayFlop[value] = true;
				} else if (EvalData.street == TURN) {
					EvalData.madeArrayTurn[value] = true;
				} else {
					EvalData.madeArrayRiver[value] = true;
				}
			}
		}
	}

}
