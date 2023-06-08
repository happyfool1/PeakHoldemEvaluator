//package evaluate_streets;

/*-  **************************************************************************************
* This Class contains the methods used for Showdown.
* First the strength of each made hand must be determined, then that relative
* strength is compared and the best hand or hands are selected.
* A tie is possible but unusual.
* 
* @author PEAK_
* 
***************************************************************************************/
public class Showdown implements Constants {

	/*-  **************************************************************************************
	* This Class contains the methods used for Showdown.
	* The public methods are:
	*  		showdown()
	* 		showShowdown()	
	***************************************************************************************/

	private Showdown() {
		throw new IllegalStateException("Utility class");
	}

	/*- *****************************************************************************
	 * This method evaluates a Showdown for all players that have not folded.
	 *  A showdown value is calculated for each player.  
	 *  The winner is the one with the largest showdown value.
	 *  A tie is possible.
	 *  
	 *  Before calling this method you must either:
	 *  	Have gone through the normal sequence of Flop, Turn, and River for 
	 *  	one player.
	 *		Set the hole cards for 1 or more other players.
	 *  	Call showdown to determine the best hand.
	 *  
	 *  Normal use in this project is:
	 *  		Run the Flop, Turn, and River collecting data for one seat. The Hero.
	 *  		Run showdownValue for the hero. Data already collected.
	 *  		Run the River only for another player then run showdownValue for that player.
	 *  		Repeat for as many players as you want.
	 *  		Run showdown to determine the winner and details on the winning hand.
	 *  		  
	 *  This code was originally developed for three projects:
	 *  	 game - Simulates a Texas Hold'em 6-max no limit game.
	 *  			It uses this Class to evaluate the Heros hand in the game, collecting
	 *  			detailed information. For his opponents it only collects information
	 *  			at Showdown when his hole cards are known. It is unique in that all
	 *  			play is controlled by external files that the user can edit. Patent Pending.
	 *  		hand_history_analysis looks at millions of Hand History files and saves
	 *  			summarized results. Not competing with Holdem Manager, instead it
	 *  			does a deeper level of evaluation. Unique in known applications. 
	 *  		evaluate - This project. I use it to run millions of hands gathering information
	 *  			on things like characterizing a Flop as HML or as one of 1755 possible Flops
	 *  			and determining how well each flop does at Showdown. Thus a way to determine
	 *  			how tp play any particular Flop.
	 *  
	 *  Results are in EvalData
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
	 *  This evaluation has been compared to foldem, is just as accurate and is
	 *  about 2 times faster.
	 *  Methods that evaluate the board and hands were already required for evaluations
	 *  other than showdown so I wrote this code. Turned out to be faster, a bonus.
	 *  
	 *This method ranks a player's hand for its showdown value. 
	 * The higher the value, the better the hand.
	 * However, this value does not hold any other significance other than indicating the 
	 * strength of the hand.
	 *
	 * For example, if the player has a Set, the method calculates the value by adding the 
	 * high card of the Set to 804000. 
	 * This is done because all Sets are not equal and the high card adds to the strength 
	 * of the Set. Similarly, for other hand types, there are equivalent calculations for things like kickers.
	 *
	 * Here's a breakdown of how the method works:
	 *
	 * It takes the player's seat as an argument and returns the showdown value 
	 * of the player's hand.
	 *
	 * If there has been a change in the street or the seat since the last evaluation, 
	 * the method resets the hand array and evaluates the cards and board for the current street.
	 *
	 * The method then initializes the showdown evaluation.
	 *
	 * If the street or the seat has changed, the method evaluates the player's hand for 
	 * pairs, overcards, flush, and straight.
	 *
	 * The method then determines the maximum hand type the player has.
	 *
	 * It calculates the showdown value for each hand type, considering the high cards, pairs, and kickers.
	 *
	 * It returns the maximum showdown value calculated for the player's hand.
	 *
	 * Overall, this method calculates the value of a player's hand for the purpose of 
	 * comparing it to the hands of other players at the end of a hand in a game of poker.
	 * it logs an error message and returns -1.		 *  
	 *  
	 *  Arg0 - Seat
	 *   Sets values in EvalData
	 ******************************************************************************/
	static void showdownValue(int seat) {
		// Do not collect data again if this was the most recent player that

		if (EvalData.playerDidShowdownValue[seat]) {
			return;
		}

		EvalData.playerDidShowdownValue[seat] = true;
		madeHands(); // Convert hands in

		int max = MADE_NONE;

		// TODO do not analyze a second time
		Analyze.evaluateRiver(seat);
		max = findMaxValue(seat);

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

		if (EvalData.detailedAnalysis) {
			playerData();
		}
		EvalData.showdownValue[EvalData.seat] = EvalData.maxValue;
		EvalData.showdownRank[EvalData.seat] = EvalData.maxValue;
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
	 ******************************************************************************/
	private static void kickers() {
		EvalData.kicker1 = -1;

		// Find highest card not same value an any pair sorted so first found
		if (EvalData.madeArray[MADE_TWO_PAIR]) {
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
		if (EvalData.holeCards[EvalData.seat][0].value == EvalData.holeCards[EvalData.seat][1].value) {
			EvalData.kicker1 = EvalData.board[0].value;
			EvalData.kicker2 = EvalData.board[1].value;
			EvalData.kicker3 = EvalData.board[2].value;
			EvalData.kickers1[EvalData.seat] =  EvalData.kicker1 ;
			EvalData.kickers2[EvalData.seat] =  EvalData.kicker2 ;
			EvalData.kickers3[EvalData.seat] =  EvalData.kicker3 ;
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


		EvalData.kickers1[EvalData.seat] =  EvalData.kicker1 ;
		EvalData.kickers2[EvalData.seat] =  EvalData.kicker2 ;
		EvalData.kickers3[EvalData.seat] =  EvalData.kicker3 ;

		// Nothing but high cards and hand is sorted
		if (EvalData.showdownHand[EvalData.seat]  == MADE_NONE) {
		EvalData.kicker1 = EvalData.both[0].value;
		EvalData.kicker2 = EvalData.both[1].value;
		EvalData.kicker3 = EvalData.both[2].value;
		}

	}

	/*- *****************************************************************************
	 * A method to gather information on the players hand that called 
	 * showdownValue. 
	 * Information is hand specific.
	 ***************************************************************************** */
	static void playerData() {
		switch (EvalData.showdownHand[EvalData.seat]) {
			case MADE_ROYAL_FLUSH -> {
				EvalData.showdownSt[EvalData.seat] = "//Waited a long time for a Royal Flush";
			}
			case MADE_STRAIGHT_FLUSH -> {
			EvalData.suits[EvalData.seat] = EvalData.suit;
				EvalData.showdownSt[EvalData.seat] = new StringBuilder().append("//Straight Flush high card ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).append(SUITS_ST[EvalData.suit]).toString();
			}
			case MADE_FOUR_OF_A_KIND -> {
							EvalData.showdownSt[EvalData.seat] = "//Four of a kind high card " + Card.CARDS[EvalData.highCard1];
			}
			case MADE_FULL_HOUSE -> {
				EvalData.showdownSt[EvalData.seat] = new StringBuilder().append("//Full House high card ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).append(" pair high card ")
						.append(Card.CARDS_REVERSE[EvalData.highCard2]).toString();
			}
			case MADE_FLUSH -> {
				System.out.println("//Flush  " + EvalData.highCard1 + " " + EvalData.suit + " " + EvalData.seat);
				EvalData.showdownSt[EvalData.seat] = new StringBuilder().append("//Flush high card  ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).append(" suit ")
						.append(SUITS2_ST[EvalData.suit])
						.toString();
			}
			case MADE_STRAIGHT -> {
					EvalData.showdownSt[EvalData.seat] = "//Straight high card " +
						Card.CARDS_REVERSE[EvalData.highCard1];
			}
			case MADE_SET -> {
							EvalData.showdownSt[EvalData.seat] = new StringBuilder().append("//Set high card  ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).append(showKickers()).toString();
			}
			case MADE_TWO_PAIR -> {
							EvalData.showdownSt[EvalData.seat] = new StringBuilder().append("//Two pair ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).toString();
			}
			case MADE_PAIR -> {
							EvalData.showdownSt[EvalData.seat] = new StringBuilder().append("//Any pair card ")
						.append(Card.CARDS_REVERSE[EvalData.highCard1]).append(showKickers()).toString();
			}
			case MADE_NONE -> {
				EvalData.showdownSt[EvalData.seat] = new StringBuilder().append("//What were you thinking kickers ")
						.append(String.valueOf(EvalData.kicker1)).append(" ").append(String.valueOf(EvalData.kicker2))
						.append(" ").append(String.valueOf(EvalData.kicker3)).append(" ").toString();
			}
			default -> {
				Logger.log(new StringBuilder().append("ERROR playerData() hand not found ")
						.append(EvalData.showdownHand[EvalData.seat]).append(" ").append(EvalData.seat).toString());
			}
		}
	}

	private static String showKickers() {
		var st = "";
		if (EvalData.kicker1 != -1) {
			st = "\r\n//kickers " + Card.CARDS_REVERSE[EvalData.kicker1];
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
	static String showShowdown() {
		var st = "";

		if (!EvalData.showdown) {
			st = "//No Showdown";
			return st;
		}
		st = "//Showdown results \r\n";
		st += new StringBuilder().append("//The board was ").append(EvalData.board[0].toString())
				.append(EvalData.board[1].toString()).append(EvalData.board[2].toString())
				.append(EvalData.board[3].toString()).append(EvalData.board[4].toString()).append("\r\n").toString();

		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i]) {
				st += new StringBuilder().append("//Hole cards seat  ").append(String.valueOf(i)).append(" ")
						.append(EvalData.holeCards[i][0].toString()).append(EvalData.holeCards[i][1].toString())
						.append("\r\n").toString();
			}
		}

		st += new StringBuilder().append("//The winner was seat ").append(String.valueOf(EvalData.winnerSeat))
				.append(" with a ").append(MADE_ARRAY_ST[EvalData.showdownHand[EvalData.winnerSeat]]).append("\r\n")
				.toString();
		int players = 0;
		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i]) {
				++players;
			}
		}
		st += new StringBuilder().append("//There were ").append(String.valueOf(players)).append(" players\r\n")
				.toString();
		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i]) {
				st += new StringBuilder().append("//Seat ").append(String.valueOf(i)).append(" had ")
						.append(MADE_ARRAY_ST[EvalData.showdownHand[i]]).toString();
				if (EvalData.playerWonShowdown[i]) {
					st += " won \r\n";
				} else {
					st += " lost \r\n";
				}
				if (!"".equals(EvalData.showdownSt[i])) {
					st += EvalData.showdownSt[i] + "\r\n";
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
