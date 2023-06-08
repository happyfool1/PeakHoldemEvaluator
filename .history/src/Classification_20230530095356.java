//package evaluate_streets;

public class Classification implements Constants {

	/*-  **************************************************************************
	*
	* This class contains methods that characterize the boards foe Flop, Turn, River
	* and hands ( board + hole Cards ) with made hands and draws analyzed.
	* In general, a single integer value is used to identify the type of the board 
	* or hand.   For example:
	* The Flop is evaluated as a combination of High, Middle, and Low cards.
	* So a flop with A 7 5 is HML ( High Medium Low ). 
	* There are exactly 10 combinations so a value of 0 to 9 is assigned. 
	* At the same time the hand ( hole cards + Flop cards ) is evaluated for
	* draws and made hands. Then at showdown, the hand is evaluated for best hand
	* or not. After many thousands of games simulated the percentage of the time that
	* one of the 10 HML combinations combined with draws, made hands, and winning hands
	* is calculated as a percentage. That percentage is a clue as how to play this
	* specific Flop, hand, or draw. If you don not have a made hand, or good draw,
	* then it tells you what to expect from your opponent.
	* 
	* There are many different ways to convert a Flop into a single integer such as
	* simply adding the values of the 13 cards ( 0 to 12 ) and array at a number 
	* between 0 and 36. So, same analysis as the exampled above.
	*
	* We will be experimenting with many different ways to characterize a Flop, Turn,
	* or River board and with hands. Where exactly we will end up is unknown but  
	* after all this is an experiment.
	*
	* There is a parallel project, PeakHoldemHandHistory that evaluates millions of 
	* real Hand History files from PokerStars and is a similar experimenting but with real 
	* data.
	*
	*
	*
	* This class is used to analyze thousands or millions of simulated hands.
	* Using a monte-carlo methodology determine characteristics of a Hold'em 
	* 6-max no limit game. 
	* Specifically to find ways to characterize a Flop, such as HML ( High Medium Low )
	* which results in only 10 Flop types. A million or more hands are run, and for 
	* each hand run the draws and made hands and other data is analyzed and collected
	* in arrays which will be analyzed after a run is completed. 
	* We analyze each street and Showdown.
	* 
	* This is not a final version. May never be. We will be experimenting constantly 
	* trying to find ways to characterize a Flop that will result in  information on
	* how best to play a hand. 
	* For example for a Flop type:
	*   It may result in a high percentage of made hands and we do not have a made hand.
	*   It may result in a low percentage of made hands and we hve have a made hand.
	*   It may result in a high percentage of strong draws.
	*   It may result in a low percentage of strong draws.
	*   It may result in a a high percentage of times that the made hand is the winning
	*   hand at Showdown.
	*   And many more.
	*
	* @author PEAK_
	***************************************************************************** */

	static boolean initialized = false;


	/*- ***********************************************************************************
    *Data used for HML
     *************************************************************************************/
	private static boolean l1 = false;
	private static boolean m1 = false;
	private static boolean h1 = false;
	private static boolean l2 = false;
	private static boolean m2 = false;
	private static boolean h2 = false;
	private static boolean l3 = false;
	private static boolean m3 = false;
	private static boolean h3 = false;
	private static boolean l4 = false;
	private static boolean m4 = false;
	private static boolean h4 = false;
	private static boolean l5 = false;
	private static boolean m5 = false;
	private static boolean h5 = false;

	/*- ************************************************************************************
	* Do Flop characterization\
	* Call only one time for each Flop
	 *************************************************************************************/
	static void doFlop() {
		if (!initialized) {
			ManyData.initialize();
			initialized = true;
		}
		hmlBoardFlop();
		System.out.println("//hmlBoard " + EvalData.hmlIndexFlop);
		boardPossiblesFlop();
		evaluateDraws();
		flopType();
		wetDry();

		boardIndexesFlop();
		for (int i = 0; i < BOARD_ARRAY_SIZE; i++) {
			if (EvalData.boardArray[i]) {
				EvalData.wetDryIndex = i;
				break;
			}
		}

		EvaluateMany.updateFlop();
		for (int i = POSSIBLE_SIZE - 1; i >= 0; --i) {
			if (EvalData.boardPossibleArrayFlop[i]) {
				break;
			}
			EvalData.boardPossibleArrayFlopMax = i;
		}
	}

	/*- ************************************************************************************
	* Do Turn characterization
	 *************************************************************************************/
	static void doTurn() {
		boardPossiblesTurn();
		evaluateDraws();
		hmlBoardTurn();
		EvaluateMany.updateTurn();

		for (int i = POSSIBLE_SIZE - 1; i >= 0; --i) {
			if (EvalData.boardPossibleArrayTurn[i]) {
				break;
			}
			EvalData.boardPossibleArrayTurnMax = i;
		}
	}

	/*- ************************************************************************************
	* Do River characterization
	 *************************************************************************************/
	static void doRiver() {
		hmlBoardRiver();
	}

	/*- ************************************************************************************
	* Do Showdown characterization
	 *************************************************************************************/
	static void doShowdown() {
		EvaluateMany.updateShowdown();
	}

	/*- *****************************************************************************F
	 * This method looks Calculate indexes for Flop and put in EvalData.
	 * Somewhat still experimental. 
	 * I will be testing to determine if these indexes are useful, running
	 * millions of hands and determining how an index relates to best hand.
	 * If there is good correlation then figure out howe to use.
	 * 
	 * Flop only.
	 * 
	 * EvalData is updated. 
	 *		EvalData.sumOfHoleCardSuited 
	 *		EvalData.sumOfHoleCardOffsuit   
	 *		EvalData.sumOfHoleCardPair   	
	 *		EvalData.sumOfFlopCards
	  ******************************************************************************/
	private static void boardIndexesFlop() {

		// Sum of board values
		EvalData.sumOfBoardValuesFlop = EvalData.board[0].value + EvalData.board[1].value + EvalData.board[2].value;

		EvalData.sumOfHoleCardValues = EvalData.holeCard1.value + EvalData.holeCard2.value;

		EvalData.flopIndex = Flop1755Methods.getFlopIndex(EvalData.board[0], EvalData.board[1], EvalData.board[2]);

		EvalData.flop1755Index = EvalData.flopIndex;
		EvalData.flopTypeOf1755 = Flop1755Methods.lookupFlopType(EvalData.board[0], EvalData.board[1],
				EvalData.board[2]);
	}

	/*- *****************************************************************************
	 * Calculate HML value for any street
	 * 
	 * Defined as: High is A-T, Middle is 9-6, Low as 5-2
	*  I believe that the update of a Flop as some combination of HML is an
	* 	opportunity to find a simple way to evaluate betting opportunities.
	* 	What this class does is conduct an experiment to see what value HML has. We
	*	will collect data and analyze to see Which of the 10 HML combinations has:
	*	Draws - any draws EV - How often will we have the best hand with each of the
	* 	10 HML combinations.
	* Experimental. 
	 * 
	  ******************************************************************************/
	private static void hmlBoardFlop() {
	

		if (EvalData.boardValue1 >= TEN) {
			h1 = true;
			m1 = false
			l1 = false;
		} else if (EvalData.boardValue1 >= SIX) {
			m1 = true;
			h1 = false;
			l1 = false;
		} else {
			l1 = true;
			h1 = false;
			m1 = false;
		}
		if (EvalData.boardValue2 >= TEN) {
			h2 = true;
			m2 = false;
			l2 = false;
		} else if (EvalData.boardValue2 >= SIX) {
			m2 = true;
			h2 = false;
			l2 = false;
		} else {
			l2 = true;
			h2 = false;
			m2 = false;
		}
		if (EvalData.boardValue3 >= TEN) {
			h3 = true;
			m3 = false;
			l3 = false;
		} else if (EvalData.boardValue3 >= SIX) {
			m3 = true;
			h3 = false;
			
		} else {
			l3 = true;
		}

		System.out.println("h1 " + h1 + " m1 " + m1 + " l1 " + l1);

		if (h1 && h2 && h3) {
			EvalData.hmlIndexFlop = 9;
			return;
		}
		if (h1 && h2 && m3) {
			EvalData.hmlIndexFlop = 8;
			return;
		}
		if (h1 && h2 && l3) {
			EvalData.hmlIndexFlop = 7;
			return;
		}
		if (h1 && m2 && m3) {
			EvalData.hmlIndexFlop = 6;
			return;
		}
		if (h1 && m2 && l3) {
			EvalData.hmlIndexFlop = 5;
			return;
		}
		if (h1 && l2 && l3) {
			EvalData.hmlIndexFlop = 4;
			return;
		}
		if (m1 && m2 && m3) {
			EvalData.hmlIndexFlop = 3;
			return;
		}
		if (m1 && m2 && l3) {
			EvalData.hmlIndexFlop = 2;
			return;
		}
		if (m1 && l2 && l3) {
			EvalData.hmlIndexFlop = 1;
			return;
		}
		if (l1 && l2 && l3) {
			EvalData.hmlIndexFlop = 0;
			return;
		}

	}

	/*- *****************************************************************************
		 * Calculate HML value for any street
		 * 
		 * Defined as: High is A-T, Middle is 9-6, Low as 5-2
		*  I believe that the update of a Flop as some combination of HML is an
		* 	opportunity to find a simple way to evaluate betting opportunities.
		* 	What this class does is conduct an experiment to see what value HML has. We
		*	will collect data and analyze to see Which of the 10 HML combinations has:
		*	Draws - any draws EV - How often will we have the best hand with each of the
		* 	10 HML combinations.
		* Experimental. 
		 * 
		  ******************************************************************************/
	private static void hmlBoardTurn() {
		boolean l1 = false;
		boolean m1 = false;
		boolean h1 = false;
		boolean l2 = false;
		boolean m2 = false;
		boolean h2 = false;
		boolean l3 = false;
		boolean m3 = false;
		boolean h3 = false;
		boolean l4 = false;
		boolean m4 = false;
		boolean h4 = false;
	
	
			if (EvalData.boardValue1 >= TEN) {
				h1 = true;
			} else if (EvalData.boardValue1 >= SIX) {
				m1 = true;
			} else {
				l1 = true;
			}
			if (EvalData.boardValue2 >= TEN) {
				h2 = true;
			} else if (EvalData.boardValue2 >= SIX) {
				m2 = true;
			} else {
				l2 = true;
			}
			if (EvalData.boardValue3 >= TEN) {
				h3 = true;
			} else if (EvalData.boardValue3 >= SIX) {
				m3 = true;
			} else {
				l3 = true;
			}
	
			if (EvalData.boardValue4 >= TEN) {
				h4 = true;
			} else if (EvalData.boardValue4 >= SIX) {
				m4 = true;
			} else {
				l4 = true;
			}
		

		System.out.println("h1 " + h1 + " m1 " + m1 + " l1 " + l1);
		if (EvalData.street == FLOP) {
			if (h1 && h2 && h3) {
				EvalData.hmlIndexFlop = 9;
				return;
			}
			if (h1 && h2 && m3) {
				EvalData.hmlIndexFlop = 8;
				return;
			}
			if (h1 && h2 && l3) {
				EvalData.hmlIndexFlop = 7;
				return;
			}
			if (h1 && m2 && m3) {
				EvalData.hmlIndexFlop = 6;
				return;
			}
			if (h1 && m2 && l3) {
				EvalData.hmlIndexFlop = 5;
				return;
			}
			if (h1 && l2 && l3) {
				EvalData.hmlIndexFlop = 4;
				return;
			}
			if (m1 && m2 && m3) {
				EvalData.hmlIndexFlop = 3;
				return;
			}
			if (m1 && m2 && l3) {
				EvalData.hmlIndexFlop = 2;
				return;
			}
			if (m1 && l2 && l3) {
				EvalData.hmlIndexFlop = 1;
				return;
			}
			if (l1 && l2 && l3) {
				EvalData.hmlIndexFlop = 0;
				return;
			}
		}

		if (EvalData.street == TURN) {
			if (h1 && h2 && h3 && h4) {
				EvalData.hmlIndexTurn = 14;
				return;
			}
			if (h1 && h2 && h3 && m4) {
				EvalData.hmlIndexTurn = 13;
				return;
			}
			if (h1 && h2 && h3 && l4) {
				EvalData.hmlIndexTurn = 12;
				return;
			}
			if (h1 && h2 && m3 && m4) {
				EvalData.hmlIndexTurn = 11;
				return;
			}
			if (h1 && h2 && m3 && l4) {
				EvalData.hmlIndexTurn = 10;
				return;
			}
			if (h1 && h2 && l3 && l4) {
				EvalData.hmlIndexTurn = 9;
				return;
			}
			if (h1 && m2 && m3 && m4) {
				EvalData.hmlIndexTurn = 8;
				return;
			}
			if (h1 && m2 && m3 && l4) {
				EvalData.hmlIndexTurn = 7;
				return;
			}
			if (h1 && m2 && l3 && l4) {
				EvalData.hmlIndexTurn = 6;
				return;
			}
			if (h1 && l2 && l3 && l4) {
				EvalData.hmlIndexTurn = 5;
				return;
			}
			if (m1 && m2 && m3 && m4) {
				EvalData.hmlIndexTurn = 4;
				return;
			}
			if (m1 && m2 && m3 && l4) {
				EvalData.hmlIndexTurn = 3;
				return;
			}
			if (m1 && m2 && l3 && l4) {
				EvalData.hmlIndexTurn = 2;
				return;
			}
			if (m1 && l2 && l3 && l4) {
				EvalData.hmlIndexTurn = 1;
				return;
			}
			if (l1 && l2 && l3 && l4) {
				EvalData.hmlIndexTurn = 0;
				return;
			}

		}

		if (EvalData.street == RIVER) {
			if (h1 && h2 && h3 && h4 && h5) {
				EvalData.hmlIndexRiver = 20;
				return;
			}
			if (h1 && h2 && h3 && h4 && m5) {
				EvalData.hmlIndexRiver = 19;
				return;
			}
			if (h1 && h2 && h3 && h4 && l5) {
				EvalData.hmlIndexRiver = 18;
				return;
			}
			if (h1 && h2 && h3 && m4 && m5) {
				EvalData.hmlIndexRiver = 17;
				return;
			}
			if (h1 && h2 && h3 && m4 && l5) {
				EvalData.hmlIndexRiver = 16;
				return;
			}
			if (h1 && h2 && h3 && l4 && l5) {
				EvalData.hmlIndexRiver = 15;
				return;
			}
			if (h1 && h2 && m3 && m4 && m5) {
				EvalData.hmlIndexRiver = 14;
				return;
			}
			if (h1 && h2 && m3 && m4 && l5) {
				EvalData.hmlIndexRiver = 13;
				return;
			}
			if (h1 && h2 && m3 && l4 && l5) {
				EvalData.hmlIndexRiver = 12;
				return;
			}
			if (h1 && h2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 11;
				return;
			}
			if (h1 && m2 && m3 && m4 && m5) {
				EvalData.hmlIndexRiver = 10;
				return;
			}
			if (h1 && m2 && m3 && m4 && l5) {
				EvalData.hmlIndexRiver = 9;
				return;
			}
			if (h1 && m2 && m3 && l4 && l5) {
				EvalData.hmlIndexRiver = 8;
				return;
			}
			if (h1 && m2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 7;
				return;
			}

			if (h1 && l2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 6;
				return;
			}

			if (m1 && m2 && m3 && m4 && m5) {
				EvalData.hmlIndexRiver = 5;
				return;
			}
			if (m1 && m2 && m3 && m4 && l5) {
				EvalData.hmlIndexRiver = 4;
				return;
			}
			if (m1 && m2 && m3 && l4 && l5) {
				EvalData.hmlIndexRiver = 3;
				return;
			}
			if (m1 && m2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 2;
				return;
			}
			if (m1 && l2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 1;
				return;
			}
			if (l1 && l2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 0;
				return;
			}

		}
	}

	/*- *****************************************************************************
		 * Calculate HML value for any street
		 * 
		 * Defined as: High is A-T, Middle is 9-6, Low as 5-2
		*  I believe that the update of a Flop as some combination of HML is an
		* 	opportunity to find a simple way to evaluate betting opportunities.
		* 	What this class does is conduct an experiment to see what value HML has. We
		*	will collect data and analyze to see Which of the 10 HML combinations has:
		*	Draws - any draws EV - How often will we have the best hand with each of the
		* 	10 HML combinations.
		* Experimental. 
		 * 
		  ******************************************************************************/
	private static void hmlBoardRiver() {
	
		if (EvalData.street == FLOP) {
			if (EvalData.boardValue1 >= TEN) {
				h1 = true;
			} else if (EvalData.boardValue1 >= SIX) {
				m1 = true;
			} else {
				l1 = true;
			}
			if (EvalData.boardValue2 >= TEN) {
				h2 = true;
			} else if (EvalData.boardValue2 >= SIX) {
				m2 = true;
			} else {
				l2 = true;
			}
			if (EvalData.boardValue3 >= TEN) {
				h3 = true;
			} else if (EvalData.boardValue3 >= SIX) {
				m3 = true;
			} else {
				l3 = true;
			}
		} else if (EvalData.street >= TURN) {
			if (EvalData.boardValue4 >= TEN) {
				h4 = true;
			} else if (EvalData.boardValue4 >= SIX) {
				m4 = true;
			} else {
				l4 = true;
			}
		} else if (EvalData.street >= RIVER) {
			if (EvalData.boardValue5 >= TEN) {
				h5 = true;
			} else if (EvalData.boardValue5 >= SIX) {
				m5 = true;
			} else {
				l5 = true;
			}
		}

		System.out.println("h1 " + h1 + " m1 " + m1 + " l1 " + l1);
		if (EvalData.street == FLOP) {
			if (h1 && h2 && h3) {
				EvalData.hmlIndexFlop = 9;
				return;
			}
			if (h1 && h2 && m3) {
				EvalData.hmlIndexFlop = 8;
				return;
			}
			if (h1 && h2 && l3) {
				EvalData.hmlIndexFlop = 7;
				return;
			}
			if (h1 && m2 && m3) {
				EvalData.hmlIndexFlop = 6;
				return;
			}
			if (h1 && m2 && l3) {
				EvalData.hmlIndexFlop = 5;
				return;
			}
			if (h1 && l2 && l3) {
				EvalData.hmlIndexFlop = 4;
				return;
			}
			if (m1 && m2 && m3) {
				EvalData.hmlIndexFlop = 3;
				return;
			}
			if (m1 && m2 && l3) {
				EvalData.hmlIndexFlop = 2;
				return;
			}
			if (m1 && l2 && l3) {
				EvalData.hmlIndexFlop = 1;
				return;
			}
			if (l1 && l2 && l3) {
				EvalData.hmlIndexFlop = 0;
				return;
			}
		}

		if (EvalData.street == TURN) {
			if (h1 && h2 && h3 && h4) {
				EvalData.hmlIndexTurn = 14;
				return;
			}
			if (h1 && h2 && h3 && m4) {
				EvalData.hmlIndexTurn = 13;
				return;
			}
			if (h1 && h2 && h3 && l4) {
				EvalData.hmlIndexTurn = 12;
				return;
			}
			if (h1 && h2 && m3 && m4) {
				EvalData.hmlIndexTurn = 11;
				return;
			}
			if (h1 && h2 && m3 && l4) {
				EvalData.hmlIndexTurn = 10;
				return;
			}
			if (h1 && h2 && l3 && l4) {
				EvalData.hmlIndexTurn = 9;
				return;
			}
			if (h1 && m2 && m3 && m4) {
				EvalData.hmlIndexTurn = 8;
				return;
			}
			if (h1 && m2 && m3 && l4) {
				EvalData.hmlIndexTurn = 7;
				return;
			}
			if (h1 && m2 && l3 && l4) {
				EvalData.hmlIndexTurn = 6;
				return;
			}
			if (h1 && l2 && l3 && l4) {
				EvalData.hmlIndexTurn = 5;
				return;
			}
			if (m1 && m2 && m3 && m4) {
				EvalData.hmlIndexTurn = 4;
				return;
			}
			if (m1 && m2 && m3 && l4) {
				EvalData.hmlIndexTurn = 3;
				return;
			}
			if (m1 && m2 && l3 && l4) {
				EvalData.hmlIndexTurn = 2;
				return;
			}
			if (m1 && l2 && l3 && l4) {
				EvalData.hmlIndexTurn = 1;
				return;
			}
			if (l1 && l2 && l3 && l4) {
				EvalData.hmlIndexTurn = 0;
				return;
			}

		}

		if (EvalData.street == RIVER) {
			if (h1 && h2 && h3 && h4 && h5) {
				EvalData.hmlIndexRiver = 20;
				return;
			}
			if (h1 && h2 && h3 && h4 && m5) {
				EvalData.hmlIndexRiver = 19;
				return;
			}
			if (h1 && h2 && h3 && h4 && l5) {
				EvalData.hmlIndexRiver = 18;
				return;
			}
			if (h1 && h2 && h3 && m4 && m5) {
				EvalData.hmlIndexRiver = 17;
				return;
			}
			if (h1 && h2 && h3 && m4 && l5) {
				EvalData.hmlIndexRiver = 16;
				return;
			}
			if (h1 && h2 && h3 && l4 && l5) {
				EvalData.hmlIndexRiver = 15;
				return;
			}
			if (h1 && h2 && m3 && m4 && m5) {
				EvalData.hmlIndexRiver = 14;
				return;
			}
			if (h1 && h2 && m3 && m4 && l5) {
				EvalData.hmlIndexRiver = 13;
				return;
			}
			if (h1 && h2 && m3 && l4 && l5) {
				EvalData.hmlIndexRiver = 12;
				return;
			}
			if (h1 && h2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 11;
				return;
			}
			if (h1 && m2 && m3 && m4 && m5) {
				EvalData.hmlIndexRiver = 10;
				return;
			}
			if (h1 && m2 && m3 && m4 && l5) {
				EvalData.hmlIndexRiver = 9;
				return;
			}
			if (h1 && m2 && m3 && l4 && l5) {
				EvalData.hmlIndexRiver = 8;
				return;
			}
			if (h1 && m2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 7;
				return;
			}

			if (h1 && l2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 6;
				return;
			}

			if (m1 && m2 && m3 && m4 && m5) {
				EvalData.hmlIndexRiver = 5;
				return;
			}
			if (m1 && m2 && m3 && m4 && l5) {
				EvalData.hmlIndexRiver = 4;
				return;
			}
			if (m1 && m2 && m3 && l4 && l5) {
				EvalData.hmlIndexRiver = 3;
				return;
			}
			if (m1 && m2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 2;
				return;
			}
			if (m1 && l2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 1;
				return;
			}
			if (l1 && l2 && l3 && l4 && l5) {
				EvalData.hmlIndexRiver = 0;
				return;
			}

		}
	}

	/*- ************************************************************************************************
	 * Type 1 flops have the characteristic that people either hit these flops hard, or not at all.
	 * 
	 * On type 2 flops, there are lots of weak hits. When you c-bet and get called, there is not 
	 * an obvious answer to the question �what did they call me with?� 
	 * These boards are often going to elicit calls from gutshot straight draws, a weak pair 
	 * with backdoor draws, or combo draws with some other feature like a pair or backdoor draw. 
	 * J83 with a flush draw is a typical Type 2 flop.
	 * 
	 * Type 3 flops are highly coordinated, KQT with a suit or 987r. If someone hits these flops, 
	 * they tend to hit them very hard and legitimately will go to showdown with them. 
	 * These are flops that lead to boards that tend to be won by straights, flushes, 
	 * full houses and big two pair hands.
	***************************************************************************************************/
	private static void flopType() {
		EvalData.type = TYPE_NONE;
		// Type1 people either hit these flops hard, or not at all.
		// K32 rainbow
		if (EvalData.boardArray[BOARD_RAINBOW] && EvalData.hmlIndexFlop == LLL
				|| EvalData.hmlIndexFlop == HLL || EvalData.hmlIndexFlop == HML) {
			EvalData.type = TYPE1;
		}
		// Type2 there are lots of weak hits.
		if (EvalData.boardArray[BOARD_GAP2]) {
			EvalData.type = TYPE2;
		}
		// highly coordinated, KQT with a suit or 987r. If someone hits these flops,
		// they tend to hit them very hard and legitimately will go to showdown with
		// them.
		if (EvalData.hmlIndexFlop == HHH || EvalData.boardArray[BOARD_SET] || EvalData.boardArray[BOARD_PAIR]
				|| EvalData.boardArray[BOARD_3F]) {
			EvalData.type = TYPE3;
		}
		if (!(EvalData.boardArray[BOARD_3F] || EvalData.boardArray[BOARD_PAIR] || EvalData.boardArray[BOARD_SET]
				|| EvalData.boardArray[BOARD_2F] || EvalData.boardArray[BOARD_GAP0])) {
			return;
		}
		EvalData.type = TYPE3;
		if (EvalData.gap0 >= 2 && (EvalData.boardArray[BOARD_GAP0] && EvalData.boardArray[BOARD_GAP1])) {
			EvalData.type = TYPE3;
		}
		EvalData.typeIndex = EvalData.type;

	}

	/*- *****************************************************************************
	 * This method will evaluate draws.
	 * Before this class is called it has already been determined if the board has draws.
	 * For example, a player may have a pair already and at the same time have an 
	 * open ended straight draw. The combination is a much stronger hand with 
	 * more outs because the player could get either a set or a straight on the
	 * next card.
	 * 
	 * EvalData.drawArray is updated for the combined draws.
	  ******************************************************************************/
	private static void evaluateDraws() {
		if (!EvalData.drawArray[DRAW_OESD] && !EvalData.drawArray[DRAW_GUTSHOT] && !EvalData.drawArray[DRAW_FLUSH]) {
			return;
		}

		if (!(EvalData.drawArray[DRAW_OESD])) {
			return;
		}
		if (EvalData.drawArray[DRAW_FLUSH]) {
			EvalData.drawArray[DRAW_FLUSH_OESD] = true;
		}
	}

	/*- *****************************************************************************
		 * This method will determine if a Flop is wet or dry or neutral.
		 * This is somewhat subjective as there is no precise definition.
		 * I have looked at other applications, like Huds and get consistent results. 
		 * Looking for something more definitive but this is good for now.
		 * boardArray and the HML value is used to determine wet or dry.
		 * There are 3 slots in boardArray, one for each condition.
		  ******************************************************************************/
	private static void wetDry() {
		if (EvalData.boardArray[BOARD_3F]) {
			EvalData.boardArray[BOARD_WET] = true;
			return;
		}
		if (EvalData.boardArray[BOARD_PAIR] && EvalData.hmlIndexFlop != HHH) {
			EvalData.boardArray[BOARD_DRY] = true;
			return;
		}
		if (EvalData.hmlIndexFlop == HHH) {
			if (EvalData.boardValue1 == ACE || EvalData.gap0Score >= 10 || EvalData.gap1Score > 5
					|| EvalData.boardArray[BOARD_SET]) {
				EvalData.boardArray[BOARD_WET] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == HHM) {
			if (EvalData.boardValue1 == ACE || EvalData.gap0Score >= 6
					|| (EvalData.gap1Score >= 6 && EvalData.boardValue2 >= QUEEN)) {
				EvalData.boardArray[BOARD_WET] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == HHL) {
			if ((EvalData.boardValue1 == QUEEN || EvalData.gap0 == 1) || EvalData.gap1 >= 1 || EvalData.gap1Score >= 5
					|| (EvalData.gap0 == 0 && EvalData.gap1 == 0)) {
				EvalData.boardArray[BOARD_WET] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == HMM) {
			if (EvalData.boardValue1 != ACE
					&& (EvalData.boardValue1 == QUEEN || !EvalData.boardArray[BOARD_RAINBOW] || EvalData.gap0Score >= 10
							|| EvalData.gap0 == 2 || (EvalData.boardValue1 > TEN && EvalData.gap0 != 0)
							|| EvalData.gap1Score > 6 || EvalData.gap2 == 2)) {
				EvalData.boardArray[BOARD_WET] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == HML) {
			if (EvalData.boardValue1 == ACE && EvalData.gap0 == 1) {
				EvalData.boardArray[BOARD_DRY] = true;
			} else {
				if (EvalData.gap0Score >= 10 || EvalData.gap1Score > 6) {
					EvalData.boardArray[BOARD_WET] = true;
					return;
				}
				if (EvalData.boardValue1 == QUEEN) {
					if (EvalData.boardValue2 < NINE) {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.boardValue1 == NINE) {
					if (EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.boardValue2 < NINE && EvalData.boardArray[BOARD_RAINBOW]) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0Score >= 10 || EvalData.gap1Score > 6) {
				EvalData.boardArray[BOARD_WET] = true;
			} else {
				if (EvalData.boardValue1 == QUEEN) {
					if (EvalData.boardValue2 < NINE) {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.boardValue1 == NINE) {
					if (EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.boardValue2 < NINE && EvalData.boardArray[BOARD_RAINBOW]) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.boardValue1 == QUEEN) {
				if (EvalData.boardValue2 < NINE) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			} else {
				if (EvalData.boardValue1 == NINE) {
					if (EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.boardValue2 < EIGHT && EvalData.boardArray[BOARD_RAINBOW]) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.boardValue1 == NINE) {
				if (EvalData.boardArray[BOARD_RAINBOW]) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			} else if (EvalData.boardValue2 < NINE && EvalData.boardArray[BOARD_RAINBOW]) {
				EvalData.boardArray[BOARD_DRY] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == HLL) {
			if (EvalData.boardValue1 == ACE) {
				if (EvalData.gap0 >= 1) {
					return;
				}
				EvalData.boardArray[BOARD_DRY] = true;
			} else {
				if (EvalData.gap0Score >= 10) {
					EvalData.boardArray[BOARD_WET] = true;
					return;
				}
				if (EvalData.gap0Score < 7 || EvalData.gap1Score < 6) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if ((EvalData.gap0 == 2 && !EvalData.boardArray[BOARD_ACE_HIGH]) || EvalData.gap1Score > 5) {
					EvalData.boardArray[BOARD_WET] = true;
					return;
				}
				if (EvalData.gap0 == 0 && EvalData.gap1 == 0) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.boardArray[BOARD_RAINBOW] && EvalData.gap0 < 2) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0Score >= 10) {
				EvalData.boardArray[BOARD_WET] = true;
			} else {
				if (EvalData.gap0Score < 7 || EvalData.gap1Score < 6) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if ((EvalData.gap0 == 2 && !EvalData.boardArray[BOARD_ACE_HIGH]) || EvalData.gap1Score > 5) {
					EvalData.boardArray[BOARD_WET] = true;
					return;
				}
				if (EvalData.gap0 == 0 && EvalData.gap1 == 0) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.boardArray[BOARD_RAINBOW] && EvalData.gap0 < 2) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0Score < 7 || EvalData.gap1Score < 6) {
				EvalData.boardArray[BOARD_DRY] = true;
			} else {
				if ((EvalData.gap0 == 2 && !EvalData.boardArray[BOARD_ACE_HIGH]) || EvalData.gap1Score > 5) {
					EvalData.boardArray[BOARD_WET] = true;
					return;
				}
				if (EvalData.gap0 == 0 && EvalData.gap1 == 0) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.boardArray[BOARD_RAINBOW] && EvalData.gap0 < 2) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if ((EvalData.gap0 == 2 && !EvalData.boardArray[BOARD_ACE_HIGH]) || EvalData.gap1Score > 5) {
				EvalData.boardArray[BOARD_WET] = true;
			} else {
				if (EvalData.gap0 == 0 && EvalData.gap1 == 0) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.boardArray[BOARD_RAINBOW] && EvalData.gap0 < 2) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if ((EvalData.gap0 == 0) || (EvalData.boardArray[BOARD_RAINBOW] && EvalData.gap0 < 2)) {
				EvalData.boardArray[BOARD_DRY] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == MMM) {
			if (EvalData.gap0Score >= 10 || EvalData.gap0 == 2 || EvalData.gap1Score > 5) {
				EvalData.boardArray[BOARD_WET] = true;
			} else if (EvalData.boardArray[BOARD_RAINBOW]) {
				EvalData.boardArray[BOARD_DRY] = true;
			}
		} else if (EvalData.hmlIndexFlop == MML) {
			if (EvalData.gap0Score > 10) {
				EvalData.boardArray[BOARD_WET] = true;
			} else {
				if (EvalData.gap0 == 1) {
					return;
				}
				if (EvalData.gap1 >= 1) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0 != 1 && EvalData.gap1 >= 1) {
				EvalData.boardArray[BOARD_DRY] = true;
			}

		} else if (EvalData.hmlIndexFlop == MLL) {
			if (EvalData.gap0Score >= 10) {
				EvalData.boardArray[BOARD_WET] = true;
			} else {
				if (EvalData.gap0 == 2) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.gap0 == 1) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
					} else {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.gap1 == 1) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.gap1 == 2) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
						return;
					}
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0 == 2) {
				EvalData.boardArray[BOARD_DRY] = true;
			} else {
				if (EvalData.gap0 == 1) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
					} else {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.gap1 == 1) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.gap1 == 2) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
						return;
					}
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0 == 1) {
				if (!EvalData.boardArray[BOARD_RAINBOW]) {
					EvalData.boardArray[BOARD_WET] = true;
				} else {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			} else {
				if (EvalData.gap1 == 1) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.gap1 == 2) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
						return;
					}
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap1 == 1) {
				EvalData.boardArray[BOARD_DRY] = true;
			} else {
				if (EvalData.gap1 == 2) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
						return;
					}
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap1 != 2) {
				return;
			}
			if (!EvalData.boardArray[BOARD_RAINBOW]) {
				EvalData.boardArray[BOARD_WET] = true;
				return;
			}
			EvalData.boardArray[BOARD_DRY] = true;
		} else if (EvalData.hmlIndexFlop != LLL) {
			// TODO System.out.println(new StringBuilder().append("// ").append("ERROR No
			// wet dry ")
			// .append(EvalData.hmlIndexFlop).toString());
			// TODO Crash.log("ERROR " + EvalData.hmlIndexFlop);
		}
	}

	/*- *****************************************************************************
		 * This method is called after several other methods that parse the hand.
		 *  
		 * In particular, drawAndMadeArray is the result of analyzing the board combined with
		 * hole cards for one player in seat.
		 * DrawHands.
		 * 
		 * It is possible only if can make it with next card
		 * 
		 * This method will use boardArray ( the result of evaluating a Flop board ) as
		 * input and determine what are the possible draw that any player might have
		 * from this board. boardPossibleArray in EvalData is updated,
		 * boardPossibleArrayFlopMax in EvalData is set to the index value of the
		 * strongest draw possible.
		  ******************************************************************************/
	private static void boardPossiblesFlop() {
		for (int i = 0; i < POSSIBLE_SIZE; i++) {
			EvalData.boardPossibleArrayFlop[i] = false;
		}
		EvalData.boardPossibleArrayFlopMax = -1;

		// if (EvalData.boardArray[BOARD_GAP0]) {
		// EvalData.boardPossibleArrayFlop[POSSIBLE_OESD] = true;
		// EvalData.boardPossibleArrayFlop[POSSIBLE_GUTSHOT] = true;
		// }

		if (EvalData.boardArray[BOARD_PAIR]) {
			EvalData.boardPossibleArrayFlop[POSSIBLE_TWO_PAIR] = true;
			EvalData.boardPossibleArrayFlop[POSSIBLE_SET] = true;
			EvalData.boardPossibleArrayFlop[POSSIBLE_FOUR_OF_A_KIND] = true;
		}

		// if (EvalData.boardArray[BOARD_2F]) {
		// if (EvalData.boardPossibleArrayFlop[POSSIBLE_GUTSHOT]) {
		// EvalData.boardPossibleArrayFlop[POSSIBLE_FLUSH_DRAW_GUTSHOT] = true;
		// }
		// if (EvalData.boardPossibleArrayFlop[POSSIBLE_OESD]) {
		// EvalData.boardPossibleArrayFlop[POSSIBLE_FLUSH_DRAW_OESD] = true;
		// }
		// }

		// if (EvalData.boardArray[BOARD_GAP0] || EvalData.boardArray[BOARD_GAP1] ||
		// EvalData.boardArray[BOARD_GAP2]) {
		// EvalData.boardPossibleArrayFlop[POSSIBLE_GUTSHOT] = true;
		// EvalData.boardPossibleArrayFlop[POSSIBLE_GUTSHOT_HIGH] = true;
		// EvalData.boardPossibleArrayFlop[POSSIBLE_GUTSHOT_PAIR] = true;
		// }

		// if (EvalData.boardArray[BOARD_GAP0] || EvalData.boardArray[BOARD_GAP1] ||
		// EvalData.boardArray[BOARD_GAP2]) {
		// EvalData.boardPossibleArrayFlop[POSSIBLE_OESD] = true;
		// EvalData.boardPossibleArrayFlop[POSSIBLE_OESD_PAIR] = true;
		// EvalData.boardPossibleArrayFlop[POSSIBLE_STRAIGHT] = true;
		// }

		if (EvalData.boardArray[BOARD_3F]) {
			EvalData.boardPossibleArrayFlop[POSSIBLE_FLUSH] = true;
			// EvalData.boardPossibleArrayFlop[POSSIBLE_FLUSH_DRAW] = true;
			// EvalData.boardPossibleArrayFlop[POSSIBLE_FLUSH_DRAW_PAIR] = true;
			// if (EvalData.boardPossibleArrayFlop[OESD]) {
			// EvalData.boardPossibleArrayFlop[POSSIBLE_STRAIGHT_FLUSH] = true;
			// // TODO check this
			// if (EvalData.boardValue1 >= 8 && EvalData.boardValue2 >= 8 &&
			// EvalData.boardValue3 >= 8) {
			// EvalData.boardPossibleArrayFlop[POSSIBLE_ROYAL_FLUSH] = true;
			// }
			// }
		}

		EvalData.boardPossibleArrayFlop[POSSIBLE_FOUR_OF_A_KIND] = true;
		EvalData.boardPossibleArrayFlop[POSSIBLE_FOUR_OF_A_KIND] = true;
		EvalData.boardPossibleArrayFlop[POSSIBLE_FULL_HOUSE] = true;
	}

	/*- *****************************************************************************
	 * This method is called after several other methods. 
	 * In particular, drawAndMadeArray is the result of analyzing the board combined with
	 * hole cards for one player in seat.
	 * Possible only if can make it with next card
	 * 
	 * This method will use boardArray ( the result of evaluating a Flop board ) as
	 * input and determine what are the possible draw that any player might have
	 * from this board. boardPossibleArray in EvalData is updated,
	 * boardPossibleArrayFlopMax in EvalData is set to the index value of the
	 * strongest draw possible.
	  ******************************************************************************/
	private static void boardPossiblesTurn() {
		for (int i = 0; i < POSSIBLE_SIZE; i++) {
			EvalData.boardPossibleArrayTurn[i] = false;
		}
		EvalData.boardPossibleArrayTurnMax = -1;

		if (EvalData.boardArray[BOARD_GAP0]) {
			// EvalData.boardPossibleArrayTurn[POSSIBLE_OESD] = true;
			// EvalData.boardPossibleArrayTurn[POSSIBLE_GUTSHOT] = true;
		}

		if (EvalData.boardArray[BOARD_PAIR]) {
			EvalData.boardPossibleArrayTurn[POSSIBLE_TWO_PAIR] = true;
			EvalData.boardPossibleArrayTurn[POSSIBLE_SET] = true;
			EvalData.boardPossibleArrayTurn[POSSIBLE_FOUR_OF_A_KIND] = true;
		}

		if (EvalData.boardArray[BOARD_2F]) {
			// if (EvalData.boardPossibleArrayTurn[POSSIBLE_GUTSHOT]) {
			// EvalData.boardPossibleArrayTurn[POSSIBLE_FLUSH_DRAW_GUTSHOT] = true;
		}
		// if (EvalData.boardPossibleArrayTurn[POSSIBLE_OESD]) {
		// EvalData.boardPossibleArrayTurn[POSSIBLE_FLUSH_DRAW_OESD] = true;
		// }
		// }

		//// if (EvalData.boardArray[BOARD_GAP0] || EvalData.boardArray[BOARD_GAP1] ||
		//// EvalData.boardArray[BOARD_GAP2]) {
		// EvalData.boardPossibleArrayTurn[POSSIBLE_GUTSHOT] = true;
		// EvalData.boardPossibleArrayTurn[POSSIBLE_GUTSHOT_HIGH] = true;
		// EvalData.boardPossibleArrayTurn[POSSIBLE_GUTSHOT_PAIR] = true;
		// }

		// if (EvalData.boardArray[BOARD_GAP0] || EvalData.boardArray[BOARD_GAP1] ||
		// EvalData.boardArray[BOARD_GAP2]) {
		// EvalData.boardPossibleArrayTurn[POSSIBLE_OESD] = true;
		// EvalData.boardPossibleArrayTurn[POSSIBLE_OESD_PAIR] = true;
		// EvalData.boardPossibleArrayTurn[POSSIBLE_STRAIGHT] = true;
		// }

		if (EvalData.boardArray[BOARD_3F]) {
			// EvalData.boardPossibleArrayTurn[POSSIBLE_FLUSH] = true;
			// EvalData.boardPossibleArrayTurn[POSSIBLE_FLUSH_DRAW] = true;
			// EvalData.boardPossibleArrayTurn[POSSIBLE_FLUSH_DRAW_PAIR] = true;
			// final boolean condition = EvalData.boardPossibleArrayTurn[OESD] &&
			// EvalData.boardValue1 >= 8
			// && EvalData.boardValue2 >= 8 && EvalData.boardValue3 >= 8;
			// check this
			// if (condition) {
			// EvalData.boardPossibleArrayFlop[POSSIBLE_ROYAL_FLUSH] = true;
			// }
		}

		EvalData.boardPossibleArrayTurn[POSSIBLE_FOUR_OF_A_KIND] = true;
		EvalData.boardPossibleArrayTurn[POSSIBLE_FOUR_OF_A_KIND] = true;
		EvalData.boardPossibleArrayTurn[POSSIBLE_FULL_HOUSE] = true;

	}

	/*- ************************************************************************************
	* Calculate Flop index
	 *************************************************************************************/
	private static void calculateFlopIndex(int rank1, int rank2, int rank3) {
		final int[] ranks = { rank1, rank2, rank3 };
		// TODO Sort
		// Calculate the differences between the ranks
		final int diff1 = ranks[1] - ranks[0];
		final int diff2 = ranks[2] - ranks[1];
		final int diff3 = ranks[2] - ranks[0];

		// Find the index of the matching condition in the matrix
		for (int i = 0; i < FLOP_INDEX_ST.length; i++) {
			final var condition = FLOP_INDEX_ST[i];
			final var parts = condition.split(" ");
			if (parts.length == 3) {
				final int c1 = Integer.parseInt(parts[0]);
				final int c2 = Integer.parseInt(parts[1]);
				final int c3 = Integer.parseInt(parts[2]);
				if ((c1 == diff1 && c2 == diff2 && c3 == diff3) || (c1 == diff1 && c2 == diff3 && c3 == diff2)
						|| (c1 == diff2 && c2 == diff1 && c3 == diff3) || (c1 == diff2 && c2 == diff3 && c3 == diff1)
						|| (c1 == diff3 && c2 == diff1 && c3 == diff2) || (c1 == diff3 && c2 == diff2 && c3 == diff1)) {
					EvalData.flopIndex = i;
				}
			}
		}

		// If no matching condition was found
		EvalData.flopIndex = -1;
	}

	/*- *****************************************************************************
	* TODO
	***************************************************************************** */
	static void checkIfPossibleCorrect() {
		for (int i = 0; i < EvalData.boardPossibleArrayFlop.length; i++) {
			if (EvalData.boardPossibleArrayFlop[i]) {
				final int hand_ = EvaluateMany.possibleValueToMadeHandValue(i);
				if (hand_ != -1) {
					for (int j = 0; j < 9; j++) {
						// TODO value = EvaluateMany.possibleValueToMadeHandValue(hand);

					}
				}
			}
		}
	}

	static int flopCount;
	static int[] count = new int[HML_FLOP_SIZE]; // How many times we had this combination
	static int hmlCount;
	static int[] winner = new int[HML_FLOP_SIZE]; // How many we won
	static int[] showdownCount = new int[HML_FLOP_SIZE]; // How many we went to at showdown
	static int[] showdownWin = new int[HML_FLOP_SIZE]; // How many we went to at showdown
	static int[] showdownLoose = new int[HML_FLOP_SIZE]; // How many we went to at showdown
	static int[] showdown = new int[HML_FLOP_SIZE]; // How many we won at showdown
	static int[] flopoCount = new int[HML_FLOP_SIZE]; // How many we won at Flop
	static int[] turnCount = new int[HML_FLOP_SIZE]; // How many we won at Turn
	static int[] riverCount = new int[HML_FLOP_SIZE]; // How many we won at River

	/*-
	 * Initialize - One time only
	 */
	static void initialize() {
		hmlCount = flopCount = 0;
		for (int i = 0; i < HML_FLOP_SIZE; ++i) {
			riverCount[i] = turnCount[i] = 0;
		}
	}

	/*--Count times for each HML */
	static void countHML(int i0) {
		++count[i0];
		++hmlCount;
	}

	/*--Showdown win / loose */
	static void showdownData(int i0, boolean b1) {
		++showdownCount[i0];
		if (b1) {
			++showdownWin[i0];
		} else {
			++showdownLoose[i0];
		}
		// TODO++handTypeCountShowdown[i0][i2];
	}

	/*--Count Winners */
	static void countWinners(int i0) {
		++winner[i0];
	}

	/*- **************************************************************************** 
	 * Set the variables that are a static analysis of the combined hole cards and
	 * board cards and for the board alone. 
	 * 100% accurate for the Flop and very close for the Turn and the River.
	 *  
	 * For the Turn and River these are potential, not for certain.
	 * That is because at showdown our hand must contain both of our hole cards 
	 * and 3 cards from the common board cards.
	 * The boolean determinations below are based on looking at out hole cards combined
	 * with all of the board cards. That group of 7 cards is sorted in ascending order. 
	 * Simple boolean expressions are used to calculate these results. Example:
	 *  		suit = EvalData.bothCards[2].suit;
	 *			EvalData.riverFlush = (EvalData.bothCards[3].suit == suit) == 
	 *			(EvalData.bothCards[4].suit == suit)  	==(EvalData.bothCards[5].suit == suit)
	 *			 == (EvalData.bothCards[6].suit == suit);
	 * This will be correct most of the time on the River and 100% of the time on the Flop.
	 * Flop is 5 cards so both hole cards are there.
	 * The way that we use this data on the Turn and the River is as a trigger to do a more
	 * complete evaluation. That improves performance considerably because we are not 
	 * spending time looking for the impossible.
	 * For example, riverFlush true may sometimes be incorrect but false ia 100% correct.
	*******************************************************************************/

}
