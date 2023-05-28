//package evaluate_streets;

import java.math.BigDecimal;

class Analyze implements Constants {

	// Used for kicker selection - kicker as one hole card not part of pair
	static int limit = 0;

	/*-  **************************************************************************
	 * Benchmarking
	 ***************************************************************************** */
	String[] TEXTURE_ST = { "None", "1 gap 0", "2 gap 0", "1 gap 1", "2 gap 1", "1 gap2", "2 gap2", "1 gap 0  1 gap 1",
			"1 gap 0  1 gap 2", "2 suit  1 gap 0", "2 suit  2 gap 0", "2 suit  1 gap 1", "2 suit  2 gap 1",
			"2 suit  1 gap2", "2 gap2", "2 suit  1 gap 0  1 gap 1", "2 suit  1 gap 0  1 gap 2", "3 suit  1 gap 0",
			"3 suit  3 gap 0", "3 suit  1 gap 1", "3 suit  3 gap 1", "3 suit  1 gap3", "3 gap3",
			"3 suit  1 gap 0  1 gap 1", "3 suit  1 gap 0  1 gap 2", "pair  1 gap 0", "pair  1 gap 1", "pair  1 gap 2",
			"pair 2 suit  1 gap 0", "pair 2 suit  1 gap 1", "pair 2 suit  1 gap 2" };

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
			EvalData.playerDidShowdownValue[i] = false;
			EvalData.showdownSt[i] = "";
			EvalData.stackBD[i] = EvalData.buyinBD;
			EvalData.stackPreflopBD[i] = EvalData.buyinBD;
			EvalData.moneyInBD[i] = zeroBD;
			EvalData.showdownSt[i] = "";

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

		AnalyzeBoard.boardGaps();
		AnalyzeBoard.boardGapScore();

		Possible.boardPossiblesFlop();
		evaluateDraws();
		flopType();
		WetDry.wetDry();
		boardIndexesFlop();

		for (int i = POSSIBLE_MAX - 1; i >= 0; --i) {
			if (EvalData.boardPossibleArrayFlop[i]) {
				break;
			}
			EvalData.boardPossibleArrayFlopMax = i;
		}

		EvalData.flopIndex = Flop1755Methods.getFlopIndex(EvalData.board[0], EvalData.board[1], EvalData.board[2]);
		EvalData.flop1755Index = EvalData.flopIndex;
		EvalData.flopTypeOf1755 = Flop1755Methods.lookupFlopType(EvalData.board[0], EvalData.board[1],
				EvalData.board[2]);

		if (EvalData.manyHands) {
			EvaluateMany.updateFlop();
		}
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
		Possible.boardPossiblesTurn();
		evaluateDraws();
		hmlBoard();
		for (int i = POSSIBLE_MAX - 1; i >= 0; --i) {
			if (EvalData.boardPossibleArrayTurn[i]) {
				break;
			}
			EvalData.boardPossibleArrayTurnMax = i;
		}
		if (EvalData.manyHands) {
			EvaluateMany.updateTurn();
		}
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
		hmlBoard();
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

	/*- *****************************************************************************
	* Initialize variables and arrays in preparation for analysis
	***************************************************************************** */
	private static void initializeFlop() {
		initializeBoard();
		initializeAll();
		for (int i = 0; i < EvalData.boardArray.length; i++) {
			EvalData.boardArray[i] = false;
		}
		for (int i = 0; i < EvalData.madeArray.length; i++) {
			EvalData.madeArray[i] = false;
			EvalData.madeArrayFlop[i] = false;
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
		EvalData.hmlIndexTurn = -1;
		initializeBoard();
		initializeAll();
		for (int i = 0; i < EvalData.boardArray.length; i++) {
			EvalData.boardArray[i] = false;
		}
		EvalData.boardArrayIndex = -1;
		for (int i = 0; i < EvalData.drawArrayTurn.length; i++) {
			EvalData.drawArray[i] = false;
			EvalData.drawArrayTurn[i] = false;
		}
		for (int i = 0; i < EvalData.madeArrayTurn.length; i++) {
			EvalData.madeArray[i] = false;
			EvalData.madeArrayTurn[i] = false;
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
		EvalData.hmlIndexRiver = -1;
		initializeAll();
		EvalData.madeArrayRiverMax = -1;
		for (int i = 0; i < EvalData.boardArray.length; i++) {
			EvalData.boardArray[i] = false;
		}
		EvalData.boardArrayIndex = -1;
		for (int i = 0; i < EvalData.madeArrayRiver.length; i++) {
			EvalData.madeArray[i] = false;
			EvalData.madeArrayRiver[i] = false;
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
		EvalData.hmlIndexFlop = -1;
		EvalData.flopTypeOf1755 = -1;
		EvalData.handValue = -1;
		EvalData.madeArrayFlopMax = -1;
		EvalData.boardPossibleArrayFlopMax = -1;
		EvalData.flopIndex = -1;
		EvalData.flopRainbow = false;
		EvalData.flop2Suited = false;
		EvalData.flop3Suited = false;
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
		EvalData.bestPossible = -1;
		EvalData.outs = -1;
		EvalData.makeHandPer = -1;
		EvalData.flopTexture = -1;
		EvalData.turnTexture = -1;

		EvalData.flopHighCards = false; // A - T
		EvalData.flopOverCards = false; // Hole cards bigger than any board card
		EvalData.boardAceHigh = false; // A hole card is an Ace
		EvalData.flopHoleCardPairedWithBoard = false; // A hole card is paired with a board card
		EvalData.flopPocketPair = false; // Hole cards are a pair

		EvalData.flopOverPair = false; // Paired hole cards higher than any board card
		EvalData.flopWeakPair = false; // Paired hole cards below any board card
		EvalData.flopMiddlePair = false; // Paired hole cards not higher or lower than any board card
		EvalData.boardAceHigh = false;
		EvalData.boardPair = false;
		EvalData.boardSet = false;
		EvalData.boardHighCard = false;
		EvalData.boardF0 = false; // Rainbow
		EvalData.boardF2 = false; // Two suited
		EvalData.boardF3 = false;
		EvalData.boardF4 = false;
		EvalData.boardF4Draw = false;
		EvalData.boardFlushDraw = false;
		EvalData.boardStraightDraw = false;
		EvalData.boardOesdDraw = false;
		EvalData.boardFlushDraw = false;
		EvalData.boardStraightDraw = false;
		EvalData.boardF4Draw = false;
		EvalData.boardOesdDraw = false;

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
		EvalData.flopRainbow = false;
		EvalData.flop2Suited = false;
		EvalData.flop3Suited = false;

		EvalData.onePair = false;
		EvalData.twoPair = false;
		EvalData.threeOfKind = false;
		EvalData.fourOfKind = false;
		EvalData.fullHouse = false;
		EvalData.anyPaired = false;
		EvalData.straight = false;
		EvalData.flush = false;

	}

	/*- *************************************************************************** 
	 * Initialize variables for board analysis
	***************************************************************************** */
	private static void initializeBoard() {
		EvalData.showdown = false;
		EvalData.pair = false;
		EvalData.boardPair = false;
		EvalData.gap0 = 0;
		EvalData.gap1 = 0;
		EvalData.gap2 = 0;
		EvalData.gap0Score = 0;
		EvalData.gap1Score = 0;
		EvalData.gap2Score = 0;
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
	static void flopType() {
		EvalData.type = TYPE_NONE;
		// Type1 people either hit these flops hard, or not at all.
		// K32 rainbow
		if (EvalData.boardArray[BOARD_RAINBOW] && EvalData.hmlIndexFlop == BOARD_LLL
				|| EvalData.hmlIndexFlop == BOARD_HLL || EvalData.hmlIndexFlop == BOARD_HML) {
			EvalData.type = TYPE1;
		}
		// Type2 there are lots of weak hits.
		if (EvalData.boardArray[BOARD_GAP2]) {
			EvalData.type = TYPE2;
		}
		// highly coordinated, KQT with a suit or 987r. If someone hits these flops,
		// they tend to hit them very hard and legitimately will go to showdown with
		// them.
		if (EvalData.hmlIndexFlop == BOARD_HHH || EvalData.boardArray[BOARD_SET] || EvalData.boardArray[BOARD_PAIR]
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

	}

	/*- *****************************************************************************
	 * This method will evaluate draws.
	 * Before this class is called it has already been determined if the board has draws.
	 * For example, a player may have a pair already and at the same time have an 
	 * open ended straight draw. The combination is a much stronger hand with 
	 * more outs becaause the player could get either a set or a straight on the
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
	 * This method initializes arrays of cards for the Flop.
	 * 
	 * The cards from the Hands class are first copied into arrays in the EvalData class.
	 * There are performance advantages to having the arrays in EvalData because in
	 * hands a couple of levels of pointrs are used. 
	 * It is also good to have the arrays easily visible to other Classes in EvalData. 
	 * 
	 * The cards in hand.board are already sorted, done by Hands and Board and HoleCards.
	 * The hole cards and board are combined into an array and that array is sorted.
	 * 
	 * They are then used to create two more attays sorted by card valur ( 0 - 51 ) which places 
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
			System.out.println("Error:Flop cardsAndBoardFlop: both arrays are null");
		}

		EvalData.boardCount = 3;
		EvalData.bothCount = 5;

		HandStatus.checkForNoDuplicates();

		SortCards.quickSortValue(EvalData.both, 0, 4);
		SortCards.quickSortCard(EvalData.bothCards, 0, 4);
		SortCards.quickSortCard(EvalData.boardCards, 0, 2);

		// hole cards
		EvalData.holeValue1 = EvalData.holeCard1.value;
		EvalData.holeValue2 = EvalData.holeCard2.value;
		// Board values
		EvalData.boardValue1 = EvalData.board[0].value;
		EvalData.boardValue2 = EvalData.board[1].value;
		EvalData.boardValue3 = EvalData.board[2].value;

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

		// Gaps between board cards
		EvalData.boardGap1_2 = EvalData.boardValue1 - EvalData.boardValue2;
		EvalData.boardGap2_3 = EvalData.boardValue2 - EvalData.boardValue3;

		HandStatus.findPairsSetsFullHouse();
		HandStatus.findStraight();
		HandStatus.findFlush();

		HandAnalysis.doFlop();
		final int result = DrawAnalysis.doFlopDraw();
		EvalData.draw[seat].draw(result);
		HandAnalysis.doBoard3();

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
			EvalData.board[i] = EvalData.boardUnsorted[i];
			EvalData.boardCards[i] = EvalData.boardUnsorted[i];
		}

		if (EvalData.both[0] == null || EvalData.both[1] == null ||
				EvalData.both[2] == null || EvalData.both[3] == null
				|| EvalData.both[4] == null || EvalData.both[5] == null) {
			System.out.println("Error:TurnB " + EvalData.both[0] + " " + EvalData.both[1] +
					" " + EvalData.both[2] + " " + EvalData.both[3] + " " + EvalData.both[4]
					+ " " + EvalData.both[5] + "    " + EvalData.boardUnsorted[3]);
		}

		EvalData.boardCount = 4;
		EvalData.bothCount = 6;

		HandStatus.checkForNoDuplicates();

		SortCards.quickSortValue(EvalData.board, 0, 3);
		SortCards.quickSortValue(EvalData.both, 0, 5);
		SortCards.quickSortCard(EvalData.bothCards, 0, 5);
		SortCards.quickSortCard(EvalData.boardCards, 0, 3);

		if (EvalData.both[0] == null || EvalData.both[1] == null ||
				EvalData.both[2] == null || EvalData.both[3] == null
				|| EvalData.both[4] == null || EvalData.both[5] == null) {
			System.out.println("Error:TurnC cardsAndBoardFlop: both arrays are null");
		}

		// hole cards
		EvalData.holeValue1 = EvalData.holeCard1.value;
		EvalData.holeValue2 = EvalData.holeCard2.value;
		// Board values
		EvalData.boardValue1 = EvalData.board[0].value;
		EvalData.boardValue2 = EvalData.board[1].value;
		EvalData.boardValue3 = EvalData.board[2].value;
		EvalData.boardValue4 = EvalData.board[3].value;
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

		HandStatus.findPairsSetsFullHouse();
		HandStatus.findStraight();
		HandStatus.findFlush();

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
			EvalData.board[i] = EvalData.boardUnsorted[i];
			EvalData.boardCards[i] = EvalData.boardUnsorted[i];
		}

		EvalData.boardCount = 5;
		EvalData.bothCount = 7;

		HandStatus.checkForNoDuplicates();

		SortCards.quickSortValue(EvalData.board, 0, 4);
		SortCards.quickSortValue(EvalData.both, 0, 6);
		SortCards.quickSortCard(EvalData.bothCards, 0, 6);
		SortCards.quickSortCard(EvalData.boardCards, 0, 4);

		if (EvalData.both[0] == null || EvalData.both[1] == null ||
				EvalData.both[2] == null || EvalData.both[3] == null || EvalData.both[4] == null ||
				EvalData.both[5] == null || EvalData.both[6] == null) {
			System.out.println("Error:Flop cardsAndBoardFlop: both arrays are null");
		}

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

		HandStatus.findPairsSetsFullHouse();
		HandStatus.findStraight();
		HandStatus.findFlush();

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
