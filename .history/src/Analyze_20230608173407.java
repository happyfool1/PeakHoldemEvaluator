//package evaluate_streets;

import java.math.BigDecimal;

class Analyze implements Constants {

	// Used for kicker selection - kicker as one hole card not part of pair
	static int limit = 0;

	private Analyze() {
		throw new IllegalStateException("Utility class");
	}

	/*- *****************************************************************************
	 * Initialization before calling any other method in this Class
	 * Call once for each hand being analyzed
	  ******************************************************************************/
	static void initialize() {
		for (int i = 0; i < PLAYERS; i++) {
			EvalData.manyHands = false;
			EvalData.fullSimulation = false;
			EvalData.do1755 = false;
			EvalData.simulation = false;
			EvalData.detailedAnalysis = false;
			EvalData.hh = false;
			EvalData.rotatePlayers = false;

			EvalData.playerFolded[i] = true;
			EvalData.playerWonShowdown[i] = false;
			EvalData.playerWon[i] = false;
			EvalData.stackBD[i] = EvalData.buyinBD;
			EvalData.stackPreflopBD[i] = EvalData.buyinBD;
			EvalData.moneyInBD[i] = zeroBD;

			EvalData.relativePosition[i] = 0;
			EvalData.relativePosition[i] = EvalData.positionsStart[i];
			EvalData.potOdds[i] = .1;
			EvalData.oddsForPlayerFlopToTurn[i] = .1;
			EvalData.oddsForPlayerFlopToRiver[i] = .1;
			EvalData.oddsForPlayerTurnToRiver[i] = .1;
		}
		EvalData.potBD = zeroBD;

		for (int i = 0; i < EvalData.drawArrayFlop.length; i++) {
			EvalData.drawArrayFlop[i] = false;
		}
		for (int i = 0; i < EvalData.boardPossibleArrayFlop.length; i++) {
			EvalData.boardPossibleArrayFlop[i] = false;
		}
		EvalData.boardPossibleArrayFlopMax = -1;
		for (int i = 0; i < PLAYERS; i++) {
			EvalData.oddsForPlayerFlopToTurn[i] = 0;
		}

	}

	/*- *****************************************************************************
	 * A player seat number ( Arg0 ) is used to evaluate a combination of a board 
	 * and a players hole cards. Flop only.
	 * Several types of analysis are performed:
	 *		evaluateBoard();
	 * 		overCards();
	 *		pairsPlusBoardAndHand();
	 *		straightDraw();
	 *		gutshotDraw();
	 *		flushDraw();
	 *		drawCombinations();
	 * Results are placed in the Shared Data Class and added to the Hands Class
	 * they was analyzed.
	 * 
	 * Arg0 - Seat number 0-5
	  ******************************************************************************/
	static void evaluateFlop(int seat) {
		EvalData.lastSeat = EvalData.seat;
		EvalData.lastStreet = EvalData.street;
		EvalData.seat = seat;
		EvalData.street = FLOP;
		EvalData.playerFolded[seat] = false;

		initializeFlop();
		cardsAndBoardFlop(seat);
	}

	/*- *****************************************************************************
	 * A player seat number ( Arg0 ) is used to evaluate a combination of a board 
	 * and a players hole cards. Turn only.
	 * Flop  must have been done  previously.
	 * Several types of analysis are performed:
	 *		evaluateBoard();
	 *		pairsPlusBoardAndHand();
	 *		straightDraw();
	 *		gutshotDraw();
	 *		flushDraw();
	 *		drawCombinations();
	 *  
	 * Results are placed in the Shared Data Class and added to the Hands Class
	 * that was analyzed.
	 * Arg0 - Seat number 0-5
	  ******************************************************************************/
	static void evaluateTurn(int seat) {
		EvalData.lastSeat = EvalData.seat;
		EvalData.lastStreet = EvalData.street;
		EvalData.seat = seat;
		EvalData.street = TURN;

		initializeTurn();
		cardsAndBoardTurn(seat);
	}

	/*- *****************************************************************************
	 * A player seat number ( Arg0 ) is used to evaluate a combination of a board 
	 * and a players hole cards. River only. 
	 * Flop and Turn must have been done  previously.
	  * Several types of analysis are performed:
	 *		evaluateBoard();
	 *		pairsPlusBoardAndHand();
	 *		drawCombinations();
	 * Results are placed in the Shared Data Class and added to the Hands Class
	 * that was analyzed.
	 * Arg0 - Seat number 0-5
	  ******************************************************************************/
	static void evaluateRiver(int seat) {
		EvalData.lastSeat = EvalData.seat;
		EvalData.lastStreet = EvalData.street;
		EvalData.seat = seat;
		EvalData.street = RIVER;
		EvalData.playerFolded[seat] = false;

		initializeRiver();
		cardsAndBoardRiver(seat);
		Showdown.showdownValue(seat);
	}

	/*- *****************************************************************************
	* Initialize variables and arrays in preparation for analysis
	***************************************************************************** */
	private static void initializeFlop() {
		initializeAll();
		for (int i = 0; i < EvalData.boardArray.length; i++) {
			EvalData.boardArray[i] = false;
		}
		for (int i = 0; i < EvalData.madeArray.length; i++) {
			EvalData.madeArray[i] = false;

		}
		for (int i = 0; i < EvalData.drawArray.length; i++) {
			EvalData.drawArray[i] = false;
			EvalData.drawArrayFlop[i] = false;
		}

		EvalData.boardArrayIndex = -1;
		for (int i = 0; i < EvalData.flopArray.length; i++) {
			EvalData.flopArray[i] = false;
		}
	}

	/*- *****************************************************************************
	 * Initialize variables and arrays in preparation for analysis
	***************************************************************************** */
	private static void initializeTurn() {
		initializeAll();
		for (int i = 0; i < EvalData.boardArray.length; i++) {
			EvalData.boardArray[i] = false;
		}
		EvalData.boardArrayIndex = -1;
		for (int i = 0; i < EvalData.drawArrayTurn.length; i++) {
			EvalData.drawArray[i] = false;
			EvalData.drawArrayTurn[i] = false;
		}
		for (int i = 0; i < EvalData.madeArray.length; i++) {
			EvalData.madeArray[i] = false;
		}
		EvalData.boardPossibleArrayTurnMax = -1;
		for (int i = 0; i < PLAYERS; i++) {
			EvalData.oddsForPlayerTurnToRiver[i] = 0;
		}
	}

	/*- *****************************************************************************
	 * Initialize variables and arrays in preparation for analysis
	***************************************************************************** */
	private static void initializeRiver() {
		initializeAll();
		for (int i = 0; i < EvalData.boardArray.length; i++) {
			EvalData.boardArray[i] = false;
		}
		EvalData.boardArrayIndex = -1;
		for (int i = 0; i < EvalData.madeArray.length; i++) {
			EvalData.madeArray[i] = false;
		}
		for (int i = 0; i < PLAYERS; i++) {
			EvalData.oddsForPlayerFlopToRiver[i] = 0;
		}
	}

	// Initialize all streets
	static void initializeAll() {
		for (int i = 0; i < PLAYERS; i++) {
			EvalData.positions[i] = i;
			EvalData.stackBD[i] = new BigDecimal(200);
			EvalData.potOdds[i] = 15.;
			EvalData.oddsForPlayerFlopToTurn[i] = 0.;
			EvalData.oddsForPlayerTurnToRiver[i] = 0.;
			EvalData.oddsForPlayerFlopToRiver[i] = 0.;
		}
		EvalData.potBD = new BigDecimal(12);

		if (EvalData.hand[EvalData.seat] == null) {
			EvalData.hand[EvalData.seat] = new Hand();
		}
		if (EvalData.draw[EvalData.seat] == null) {
			EvalData.draw[EvalData.seat] = new Draw();
		}

		EvalData.showdown = false;
		EvalData.handValue = -1;
		EvalData.kicker1 = -1;
		EvalData.kicker2 = -1;
		EvalData.kicker3 = -1;
		EvalData.highCard1 = -1;
		EvalData.highCard2 = -1;
		EvalData.suit = -1;
		EvalData.handStrength = -1;
		EvalData.rangeAdvantage = 0;
		EvalData.madeHand = -1;
		EvalData.bestDraw = -1;
		EvalData.outs = -1;
		EvalData.makeHandPer = -1;

		EvalData.flopHighCards = false; // A - T
		EvalData.flopOverCards = false; // Hole cards bigger than any board card
		EvalData.boardAceHigh = false; // A hole card is an Ace
		EvalData.flopHoleCardPairedWithBoard = false; // A hole card is paired with a board card
		EvalData.flopPocketPair = false; // Hole cards are a pair

		EvalData.bothGutshotDraw = false;
		EvalData.bothFlushDraw = false;
		EvalData.bothStraightDraw = false;
		EvalData.bothOesdDraw = false;
		EvalData.bothFlush = false;
		EvalData.bothStraight = false;
		EvalData.bothStraightAce = false;
		EvalData.bothNone = false;
		EvalData.bothPair = false;
		EvalData.both2Pair = false;
		EvalData.bothSet = false;
		EvalData.bothQuad = false;
		EvalData.bothFull = false;

		EvalData.onePair = false;
		EvalData.twoPair = false;
		EvalData.threeOfKind = false;
		EvalData.fourOfKind = false;
		EvalData.fullHouse = false;
		EvalData.anyPaired = false;
		EvalData.straight = false;
		EvalData.flush = false;

		EvalData.showdown = false;
		EvalData.pair = false;
	}

	/*- *****************************************************************************
	 * This method initializes arrays of cards for the Flop.
	 * 
	 * The cards from the Hands class are first copied into arrays in the EvalData class.
	 * There are performance advantages to having the arrays in EvalData because in
	 * hands a couple of levels of pointers are used. 
	 * It is also good to have the arrays easily visible to other Classes in EvalData. 
	 * 
	 * The cards in hand.board are already sorted, done by Hands and Board and HoleCards.
	 * The hole cards and board are combined into an array and that array is sorted.
	 * 
	 * They are then used to create two more arrays sorted by card value ( 0 - 51 ) which places 
	 * them in both value order and suit order. Ac7cQh5h8s
	 * Sorting the arrays has several advantages:
	 * 		It greatly simplifies checking the board ot hole cards plus board for
	 * 		information such as straight draws, flush draws, made hands, etc.
	 * 		It is easier for a human to look at and understand.
	 * 		It is actually faster. While sorting takes time it eliminates additional 
	 * 		programming in the methods that do evaluation.
	 * 		The sort method is very fast, uses the quick sort algorithm and is specific
	 * 		to the Card class.
	 * 
	 * Arg0 - seat 
	  ******************************************************************************/
	private static void cardsAndBoardFlop(int seat) {
		EvalData.cardCount = 5;
		EvalData.holeCard1 = EvalData.holeCards[seat][0];
		EvalData.both[0] = EvalData.holeCard1;
		EvalData.bothCards[0] = EvalData.holeCard1;
		EvalData.holeCard2 = EvalData.holeCards[seat][1];
		EvalData.both[1] = EvalData.holeCard2;
		EvalData.bothCards[1] = EvalData.holeCard2;

		EvalData.both[2] = EvalData.board[0];
		EvalData.boardCards[0] = EvalData.board[0];
		EvalData.bothCards[2] = EvalData.both[2];

		EvalData.both[3] = EvalData.board[1];
		EvalData.boardCards[1] = EvalData.board[1];
		EvalData.bothCards[3] = EvalData.both[3];

		EvalData.both[4] = EvalData.board[2];
		EvalData.boardCards[2] = EvalData.board[2];
		EvalData.bothCards[4] = EvalData.both[4];

		if (EvalData.both[0] == null || EvalData.both[1] == null ||
				EvalData.both[2] == null || EvalData.both[3] == null || EvalData.both[4] == null) {
			Logger.logError("Error:Flop cardsAndBoardFlop: both arrays are null");
		}

		EvalData.boardCount = 3;
		EvalData.bothCount = 5;

		SortCards.quickSortValue(EvalData.both, 0, 4);
		SortCards.quickSortCard(EvalData.bothCards, 0, 4);
		SortCards.quickSortCard(EvalData.boardCards, 0, 2);

		// hole cards
		EvalData.holeValue1 = EvalData.holeCard1.value;
		EvalData.holeValue2 = EvalData.holeCard2.value;

		// Card values
		EvalData.bothValue1 = EvalData.both[0].value;
		EvalData.bothValue2 = EvalData.both[1].value;
		EvalData.bothValue3 = EvalData.both[2].value;
		EvalData.bothValue4 = EvalData.both[3].value;
		EvalData.bothValue5 = EvalData.both[4].value;

		// Card suits
		EvalData.bothCardsSuit1 = EvalData.bothCards[0].suit;
		EvalData.bothCardsSuit2 = EvalData.bothCards[1].suit;
		EvalData.bothCardsSuit3 = EvalData.bothCards[2].suit;
		EvalData.bothCardsSuit4 = EvalData.bothCards[3].suit;
		EvalData.bothCardsSuit5 = EvalData.bothCards[4].suit;

		// Gaps between hand cards
		EvalData.bothGap1_2 = EvalData.bothValue1 - EvalData.bothValue2;
		EvalData.bothGap2_3 = EvalData.bothValue2 - EvalData.bothValue3;
		EvalData.bothGap3_4 = EvalData.bothValue3 - EvalData.bothValue4;
		EvalData.bothGap4_5 = EvalData.bothValue4 - EvalData.bothValue5;
		EvalData.bothGap1_3 = EvalData.bothValue1 - EvalData.bothValue3;
		EvalData.bothGap1_4 = EvalData.bothValue1 - EvalData.bothValue4;
		EvalData.bothGap2_4 = EvalData.bothValue2 - EvalData.bothValue4;
		EvalData.bothGap2_5 = EvalData.bothValue2 - EvalData.bothValue5;

		EvalData.sumOfHandValuesFlop[seat] = EvalData.both[0].value + EvalData.both[1].value
				+ EvalData.both[2].value + EvalData.both[3].value + EvalData.both[4].value;

		// Board values
		EvalData.boardValue1 = EvalData.board[0].value;
		EvalData.boardValue2 = EvalData.board[1].value;
		EvalData.boardValue3 = EvalData.board[2].value;

		// Gaps between board cards
		EvalData.boardGap1_2 = EvalData.boardValue1 - EvalData.boardValue2;
		EvalData.boardGap2_3 = EvalData.boardValue2 - EvalData.boardValue3;

		BoardAnalysis.doFlop();
		final int result = DrawAnalysis.doFlopDraw();
		EvalData.draw[seat].draw(result);

		HandStatus.findPairsSetsFullHouse();
		HandStatus.findStraight();
		HandStatus.findFlush();

		EvalData.boardHighCardValue1 = EvalData.board[0].value;
		if (EvalData.board[0].value != EvalData.board[1].value) {
			EvalData.boardHighCardValue2 = EvalData.board[1].value;
		} else {
			EvalData.boardHighCardValue2 = EvalData.board[2].value;
		}
		EvalData.boardLowCardValue = EvalData.board[2].value;

		 


		if (EvalData.anyPaired) {
			 
			System.out.println("//CCC " + EvalData.board[0].value
					+ " " + EvalData.board[1].value + " " + EvalData.board[2].value);
			EvalData.hand[EvalData.seat].doHandPairs();
		} else if (EvalData.straight) {
			EvalData.hand[EvalData.seat].doHandStraight();
		} else if (EvalData.flush) {
			EvalData.hand[EvalData.seat].doHandFlush();
		} else {
			EvalData.hand[EvalData.seat].doHandNone();
		}

		// And if there is nothing
		if (!(!EvalData.bothPair && !EvalData.both2Pair && !EvalData.bothSet && !EvalData.bothQuad && !EvalData.bothFull
				&& !EvalData.bothStraight && !EvalData.bothFlush)) {
			EvalData.madeArray[MADE_NONE] = true;
		}

	}

	/*- *****************************************************************************
	 * This method initializes arrays of cards for theTurn.
	 * Arg0 - seat 
	  ******************************************************************************/
	private static void cardsAndBoardTurn(int seat) {
		EvalData.holeCard1 = EvalData.holeCards[seat][0];
		EvalData.both[0] = EvalData.holeCard1;
		EvalData.bothCards[0] = EvalData.holeCard1;
		EvalData.holeCard2 = EvalData.holeCards[seat][1];
		EvalData.both[1] = EvalData.holeCard2;
		EvalData.bothCards[1] = EvalData.holeCard2;

		for (int i = 0; i < 4; i++) {
			EvalData.both[i + 2] = EvalData.boardUnsorted[i];
			EvalData.bothCards[i + 2] = EvalData.boardUnsorted[i];
		}

		if (EvalData.both[0] == null || EvalData.both[1] == null ||
				EvalData.both[2] == null || EvalData.both[3] == null
				|| EvalData.both[4] == null || EvalData.both[5] == null) {
			Logger.logError("Error:TurnB " + EvalData.both[0] + " " + EvalData.both[1] +
					" " + EvalData.both[2] + " " + EvalData.both[3] + " " + EvalData.both[4]
					+ " " + EvalData.both[5] + "    " + EvalData.boardUnsorted[3]);
		}

		EvalData.boardCount = 4;
		EvalData.bothCount = 6;

		SortCards.quickSortValue(EvalData.both, 0, 5);
		SortCards.quickSortCard(EvalData.bothCards, 0, 5);

		if (EvalData.both[0] == null || EvalData.both[1] == null ||
				EvalData.both[2] == null || EvalData.both[3] == null
				|| EvalData.both[4] == null || EvalData.both[5] == null) {
			Logger.logError("Error:TurnC cardsAndBoardFlop: both arrays are null");
		}

		// hole cards
		EvalData.holeValue1 = EvalData.holeCard1.value;
		EvalData.holeValue2 = EvalData.holeCard2.value;

		// Card values
		EvalData.bothValue1 = EvalData.both[0].value;
		EvalData.bothValue2 = EvalData.both[1].value;
		EvalData.bothValue3 = EvalData.both[2].value;
		EvalData.bothValue4 = EvalData.both[3].value;
		EvalData.bothValue5 = EvalData.both[4].value;
		EvalData.bothValue6 = EvalData.both[5].value;
		// Card suits
		EvalData.bothCardsSuit1 = EvalData.bothCards[0].suit;
		EvalData.bothCardsSuit2 = EvalData.bothCards[1].suit;
		EvalData.bothCardsSuit3 = EvalData.bothCards[2].suit;
		EvalData.bothCardsSuit4 = EvalData.bothCards[3].suit;
		EvalData.bothCardsSuit5 = EvalData.bothCards[4].suit;
		EvalData.bothCardsSuit6 = EvalData.bothCards[5].suit;

		// Gaps between hand cards
		EvalData.bothGap1_2 = EvalData.bothValue1 - EvalData.bothValue2;
		EvalData.bothGap2_3 = EvalData.bothValue2 - EvalData.bothValue3;
		EvalData.bothGap3_4 = EvalData.bothValue3 - EvalData.bothValue4;
		EvalData.bothGap4_5 = EvalData.bothValue4 - EvalData.bothValue5;
		EvalData.bothGap5_6 = EvalData.bothValue5 - EvalData.bothValue6;

		EvalData.bothGap5_6 = EvalData.bothValue5 - EvalData.bothValue6;
		EvalData.bothGap1_3 = EvalData.bothValue1 - EvalData.bothValue3;
		EvalData.bothGap1_4 = EvalData.bothValue1 - EvalData.bothValue4;
		EvalData.bothGap2_4 = EvalData.bothValue2 - EvalData.bothValue4;
		EvalData.bothGap2_5 = EvalData.bothValue2 - EvalData.bothValue5;
		EvalData.bothGap4_6 = EvalData.bothValue4 - EvalData.bothValue6;

		// Gaps between board cards
		EvalData.boardGap1_2 = EvalData.boardValue1 - EvalData.boardValue2;
		EvalData.boardGap2_3 = EvalData.boardValue2 - EvalData.boardValue3;
		EvalData.boardGap3_4 = EvalData.boardValue3 - EvalData.boardValue4;

		BoardAnalysis.doTurn();

		HandStatus.findPairsSetsFullHouse();
		HandStatus.findStraight();
		HandStatus.findFlush();

		EvalData.boardHighCardValue1 = EvalData.board[0].value;
		if (EvalData.board[0].value != EvalData.board[1].value) {
			EvalData.boardHighCardValue2 = EvalData.board[1].value;
		} else if (EvalData.board[0].value != EvalData.board[2].value) {
			EvalData.boardHighCardValue2 = EvalData.board[2].value;
		} else {
			EvalData.boardHighCardValue2 = EvalData.board[3].value;
		}
		EvalData.boardLowCardValue = EvalData.board[3].value;

		int result = DrawAnalysis.doFlopDraw();
		if (result == DRAW_NONE) {
			result = DrawAnalysis.doTurnDraw();
		}

		EvalData.draw[seat].draw(result);
		// Decision for Hand
		if (EvalData.anyPaired) {
			EvalData.hand[EvalData.seat].doHandPairs();
		} else if (EvalData.straight) {
			EvalData.hand[EvalData.seat].doHandStraight();
		} else if (EvalData.flush) {
			EvalData.hand[EvalData.seat].doHandFlush();
		} else {
			EvalData.hand[EvalData.seat].doHandNone();
		}
		// And if there is nothing
		if (!(!EvalData.bothPair && !EvalData.both2Pair && !EvalData.bothSet && !EvalData.bothQuad && !EvalData.bothFull
				&& !EvalData.bothStraight && !EvalData.bothFlush)) {
			EvalData.madeArray[MADE_NONE] = true;
		}
	}

	/*- *****************************************************************************
	 * This method initializes arrays of cards for the River.
	 * Arg0 - seat  
	  ******************************************************************************/
	private static void cardsAndBoardRiver(int seat) {
		EvalData.holeCard1 = EvalData.holeCards[seat][0];
		EvalData.both[0] = EvalData.holeCard1;
		EvalData.bothCards[0] = EvalData.holeCard1;
		EvalData.holeCard2 = EvalData.holeCards[seat][1];
		EvalData.both[1] = EvalData.holeCard2;
		EvalData.bothCards[1] = EvalData.holeCard2;

		for (int i = 0; i < 5; i++) {
			EvalData.both[i + 2] = EvalData.boardUnsorted[i];
			EvalData.bothCards[i + 2] = EvalData.boardUnsorted[i];
		}

		EvalData.boardCount = 5;
		EvalData.bothCount = 7;

		if (EvalData.both[0] == null || EvalData.both[1] == null ||
				EvalData.both[2] == null || EvalData.both[3] == null || EvalData.both[4] == null ||
				EvalData.both[5] == null || EvalData.both[6] == null) {
			Logger.logError("Error:Flop cardsAndBoardFlop: both arrays are null");
		}

		SortCards.quickSortValue(EvalData.both, 0, 6);
		SortCards.quickSortCard(EvalData.bothCards, 0, 6);

		EvalData.cardCount = 7;

		// hole cards
		EvalData.holeValue1 = EvalData.holeCard1.value;
		EvalData.holeValue2 = EvalData.holeCard2.value;
		// Board values
		EvalData.boardValue1 = EvalData.board[0].value;
		EvalData.boardValue2 = EvalData.board[1].value;
		EvalData.boardValue3 = EvalData.board[2].value;
		EvalData.boardValue4 = EvalData.board[3].value;
		EvalData.boardValue5 = EvalData.board[4].value;
		// Card values
		EvalData.bothValue1 = EvalData.both[0].value;
		EvalData.bothValue2 = EvalData.both[1].value;
		EvalData.bothValue3 = EvalData.both[2].value;
		EvalData.bothValue4 = EvalData.both[3].value;
		EvalData.bothValue5 = EvalData.both[4].value;
		EvalData.bothValue6 = EvalData.both[5].value;
		EvalData.bothValue7 = EvalData.both[6].value;
		// Card suits
		EvalData.bothCardsSuit1 = EvalData.bothCards[0].suit;
		EvalData.bothCardsSuit2 = EvalData.bothCards[1].suit;
		EvalData.bothCardsSuit3 = EvalData.bothCards[2].suit;
		EvalData.bothCardsSuit4 = EvalData.bothCards[3].suit;
		EvalData.bothCardsSuit5 = EvalData.bothCards[4].suit;
		EvalData.bothCardsSuit6 = EvalData.bothCards[5].suit;
		EvalData.bothCardsSuit7 = EvalData.bothCards[6].suit;

		// Gaps between hand cards
		EvalData.bothGap1_2 = EvalData.bothValue1 - EvalData.bothValue2;
		EvalData.bothGap2_3 = EvalData.bothValue2 - EvalData.bothValue3;
		EvalData.bothGap3_4 = EvalData.bothValue3 - EvalData.bothValue4;
		EvalData.bothGap4_5 = EvalData.bothValue4 - EvalData.bothValue5;
		EvalData.bothGap5_6 = EvalData.bothValue5 - EvalData.bothValue6;
		EvalData.bothGap6_7 = EvalData.bothValue6 - EvalData.bothValue7;

		EvalData.bothGap1_3 = EvalData.bothValue1 - EvalData.bothValue3;
		EvalData.bothGap1_4 = EvalData.bothValue1 - EvalData.bothValue4;
		EvalData.bothGap2_4 = EvalData.bothValue2 - EvalData.bothValue4;
		EvalData.bothGap2_5 = EvalData.bothValue2 - EvalData.bothValue5;
		EvalData.bothGap4_6 = EvalData.bothValue4 - EvalData.bothValue6;
		EvalData.bothGap5_7 = EvalData.bothValue5 - EvalData.bothValue7;

		BoardAnalysis.doRiver();
		HandStatus.findPairsSetsFullHouse();
		HandStatus.findStraight();
		HandStatus.findFlush();

		EvalData.boardHighCardValue1 = EvalData.board[0].value;
		if (EvalData.board[0].value != EvalData.board[1].value) {
			EvalData.boardHighCardValue2 = EvalData.board[1].value;
		} else if (EvalData.board[0].value != EvalData.board[2].value) {
			EvalData.boardHighCardValue2 = EvalData.board[2].value;
		} else if (EvalData.board[0].value != EvalData.board[3].value) {
			EvalData.boardHighCardValue2 = EvalData.board[3].value;
		} else {
			EvalData.boardHighCardValue2 = EvalData.board[4].value;
		}
		EvalData.boardLowCardValue = EvalData.board[4].value;

		// Decision for Hand
		if (EvalData.anyPaired) {
			EvalData.hand[EvalData.seat].doHandPairs();
		} else if (EvalData.straight) {
			EvalData.hand[EvalData.seat].doHandStraight();
		} else if (EvalData.flush) {
			EvalData.hand[EvalData.seat].doHandFlush();
		} else {
			EvalData.hand[EvalData.seat].doHandNone();
		}
		// And if there is nothing
		if (!(!EvalData.bothPair && !EvalData.both2Pair && !EvalData.bothSet && !EvalData.bothQuad && !EvalData.bothFull
				&& !EvalData.bothStraight && !EvalData.bothFlush)) {
			EvalData.madeArray[MADE_NONE] = true;
		}
	}

}
