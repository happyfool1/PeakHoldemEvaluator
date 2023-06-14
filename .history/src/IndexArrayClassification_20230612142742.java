//package evaluate_streets;

import java.util.Arrays;
import java.util.Comparator;

public class IndexArrayClassification implements Constants {

	/*-  **************************************************************************
	* There are only 3 public methods, doFlop(), doTurn(), and doRiver()
	*
	* This class contains methods that characterize the boards for Flop, Turn, River
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

	private static boolean initialized = false;

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
	* Do Flop characterization
	* Call only one time for each Flop
	 *************************************************************************************/
	static void doFlop() {
		if (!initialized) {
			IndexArrays.initialize();
			initialized = true;
		}
		hmlBoardFlop();
		evaluateDraws();
		flopType();
		wetDry();

		boardIndexesFlop();
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (EvalData.boardArray[i]) {
				EvalData.wetDryIndex = i;
				break;
			}
		}

		IndexArrayUpdate.updateFlop();

	}

	/*- ************************************************************************************
	* Do Turn characterization
	 *************************************************************************************/
	static void doTurn() {
		evaluateDraws();
		hmlBoardTurn();
		IndexArrayUpdate.updateTurn();

	}

	/*- ************************************************************************************
	* Do River characterization
	 *************************************************************************************/
	static void doRiver() {
		hmlBoardRiver();
		IndexArrayUpdate.updateRiver();
	}

	/*- ************************************************************************************
	* Do Showdown characterization
	 *************************************************************************************/
	static void doShowdown() {
		IndexArrayUpdate.updateShowdown();
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
	 *Set HML boolean values
	  ******************************************************************************/
	private static void hmlBoardSetBoolean() {
		if (EvalData.boardValue1 >= TEN) {
			h1 = true;
			m1 = false;
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
			l3 = false;
		} else {
			l3 = true;
			h3 = false;
			m3 = false;
		}

		if (EvalData.street >= TURN) {

			if (EvalData.boardValue3 >= TEN) {
				h4 = true;
				m4 = false;
				l4 = false;
			} else if (EvalData.boardValue3 >= SIX) {
				m4 = true;
				h4 = false;
				l4 = false;
			} else {
				l4 = true;
				h4 = false;
				m4 = false;
			}
			if (EvalData.street == RIVER) {
				if (EvalData.boardValue4 >= TEN) {
					h5 = true;
					m5 = false;
					l5 = false;
				} else if (EvalData.boardValue4 >= SIX) {
					m5 = true;
					h5 = false;
					l5 = false;
				} else {
					l5 = true;
					h5 = false;
					m5 = false;
				}
			}
		}

	}

	/*- *****************************************************************************
	 * Calculate HML value for any Flop
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
		hmlBoardSetBoolean();
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
	* Calculate HML value for any Turn
	* 
	* Defined as: High is A-T, Middle is 9-6, Low as 5-2
	* 
	 ******************************************************************************/
	private static void hmlBoardTurn() {
		hmlBoardSetBoolean();
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

	/*- *****************************************************************************
	* Calculate HML value for any River
	* 
	 * 
	 ******************************************************************************/
	private static void hmlBoardRiver() {
		hmlBoardSetBoolean();
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
		Logger.logError("hmlBoardRiver() error ");
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
			Logger.logError(new StringBuilder().append("// ").append("ERROR No wet dry ")
					.append(EvalData.hmlIndexFlop).toString());
			Crash.log("ERROR " + EvalData.hmlIndexFlop);
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

	/*- *****************************************************************************
	 * This method will classify a flop.
	 * Sure, to make this Java method, let's consider that each card has two properties:
	 *  a value and a suit. Let's represent ranks as integers from 2 (for 2) to 14 
	 * (for Ace), and suits as integers from 1 to 4.
	 * 
	 * We can have 9 combinations in total, given three types of suit distributions
	 * (Monotone, Two-tone, and Rainbow) and three types of connectivity 
	 * (Connected, Semi-connected, and Unconnected). 
	 * These combinations can be represented by integers from 1 to 9.
	 * This will return a unique integer from 1 to 9 for each possible combination. 
	 * The logic for the computation is that we subtract 1 from the suitType and 
	 * multiply by 3 (since we have three connectivity types), and then we add the 
	 * connectivityType. This ensures a unique value for each combination.
	 * 
	 *******************************************************************************/
	public static void flopTextureSuitednessConnectedness() {
		// Determine suit type
		int suitType;
		if (EvalData.board[0].suit == EvalData.board[1].suit && EvalData.board[1].suit == EvalData.board[2].suit) {
			suitType = 1; // Monotone
		} else if (EvalData.board[0].suit == EvalData.board[1].suit || EvalData.board[1].suit == EvalData.board[2].suit || EvalData.board[0].suit == EvalData.board[2].suit) {
			suitType = 2; // Two-tone
		} else {
			suitType = 3; // Rainbow
		}

		// Determine connectivity type
		int connectivityType;
		if (EvalData.board[1].value - EvalData.board[0].value == 1 && EvalData.board[2].value - EvalData.board[1].value == 1) {
			connectivityType = 1; // Connected
		} else if (EvalData.board[1].value - EvalData.board[0].value == 1 || EvalData.board[2].value - EvalData.board[1].value == 1) {
			connectivityType = 2; // Semi-connected
		} else {
			connectivityType = 3; // Unconnected
		}

		// Return the combined characterization as an integer
		EvalData.textureIndex =  (suitType - 1) * 3 + connectivityType;
	}

/*- *****************************************************************************
 * This method will classify a flop.
 * 
Another way to characterize the flop in Texas Hold'em is by assessing its Pair Distribution 
and Broadway Potential. This can be valuable in predicting possible hand strengths
and formulating a strategy.

Pair Distribution: Identify if there are any pairs on the flop, and if so, what they are.

Unpaired Flop: This is when all three cards on the flop have different ranks (e.g., 5-7-Queen).

Paired Flop: This is when two out of the three cards on the flop have the same rank (e.g., 6-6-King).

Trips Flop: This is when all three cards on the flop have the same rank (e.g., 8-8-8).

Broadway Potential: Broadway is a term for the highest possible straight, which is a 10-J-Q-K-A straight. 
The flop could have various levels of potential for a Broadway straight.

High Potential: This is when two or more cards from the flop are part of the Broadway sequence (10, J, Q, K, A).

Medium Potential: This is when only one card from the flop is part of the Broadway sequence.

Low Potential: This is when none of the cards from the flop are part of the Broadway sequence.
*******************************************************************************/
 public static int flopTexture(Card[] cards) {
        
        // Determine pair distribution type
        int pairType;
        if (cards[0].value == cards[1].value && cards[1].value == cards[2].value) {
            pairType = 1; // Trips
        } else if (cards[0].value == cards[1].value || cards[1].value == cards[2].value || cards[0].value == cards[2].value) {
            pairType = 2; // Pair
        } else {
            pairType = 3; // Unpaired
        }

        // Determine Broadway potential type
        int broadwayPotentialType;
        int broadwayCount = 0;
        for (Card card : cards) {
            if (card.value >= 10 && card.value <= 14) { // 10, J, Q, K, A
                broadwayCount++;
            }
        }
        if (broadwayCount >= 2) {
            broadwayPotentialType = 1; // High
        } else if (broadwayCount == 1) {
            broadwayPotentialType = 2; // Medium
        } else {
            broadwayPotentialType = 3; // Low
        }

        // Return the combined characterization as an integer
        return (pairType - 1) * 3 + broadwayPotentialType;
    }


}
