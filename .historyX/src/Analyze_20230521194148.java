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
		}if (EvalData.both[0] == null || EvalData.both[1] == null || 
		EvalData.both[2] == null || EvalData.both[3] == null || EvalData.both[4] == null) {
			System.out.println("Error:Turn cardsAndBoardFlop: both arrays are null");	
		}

		EvalData.boardCount = 4;
		EvalData.bothCount = 6;

		HandStatus.checkForNoDuplicates();

		SortCards.quickSortValue(EvalData.board, 0, 3);
		SortCards.quickSortValue(EvalData.both, 0, 5);
		SortCards.quickSortCard(EvalData.bothCards, 0, 5);
		SortCards.quickSortCard(EvalData.boardCards, 0, 3);

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
		EvalData.both[2] == null || EvalData.both[3] == null || EvalData.both[4] == null
		EvalData.both[2] == null || EvalData.both[3] == null || EvalData.both[4] == null
		) {
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

	/*- *****************************************************************************
	 * This method looks Calculate indexes for Flop and put in EvalData.
	 * Somewhat still experimental. 
	 * I will be testing to determine if these indexes are useful, running
	 * millions of hands and determining how an index relates to best hand.
	 * If there is good correlation then figure out howe to use.
	 * 
	 * Flop only.
	 * Hole cards combined with board sorted in EvalData.both 
	 * 
	 * Another idea about how to characterize Flop texture.
	 * We develop an index into an array of 
	 * 		Starting with Rainbow and no pair 
	 * 			Number of gap0 in Flop. 1 gap 0 , 2 gap 0  ...
	 * 		Include 3 suited and no pair
	* 		Include pair with pair there can not be 3 suit and must have at least 1 gap 0 	
	* 
	* Arg0 - Flop card 1
	* Arg1 - Flop card 2	
	* Arg2 - Flop card 3 
	*/
	public static void calculateFlopIndex(int rank1, int rank2, int rank3) {
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
		hmlBoard();
		// Sum of board values
		EvalData.sumOfBoardValuesFlop = EvalData.board[0].value + EvalData.board[1].value + EvalData.board[2].value - 6;

		EvalData.sumOfHandValuesFlop = EvalData.both[0].value + EvalData.both[1].value + EvalData.both[2].value - 12;

		EvalData.sumOfHoleCardValues = EvalData.holeCard1.value + EvalData.holeCard2.value - 4;
	}

	/*- *****************************************************************************
	 * Calculate HML value for any street
	 * 
	 * Defined as: High is A-T, Middle is 9-6, Low as 5-2
	 * 	  H = 2  M = 1  L = 0 
	*  I believe that the update of a Flop as some combination of HML is an
	* 	opportunuty to find a simple way to evaluate betting opportunities.
	* 	What this class does is conduct an experiment to see what value HML has. We
	*	will collect data and analyze to seewWhich of the 10 HML combinations has:
	*	Draws - any draws EV - How often will we have the best hand with each of the
	* 	10 HML combinations.
	* Experimental. 
	 * 
	  ******************************************************************************/
	private static void hmlBoard() {
		int c1 = 0;
		int c2 = 0;
		int c3 = 0;
		int c4 = 0;
		if (EvalData.boardValue1 >= TEN) {
			c1 = H;
		} else if (EvalData.boardValue1 >= SIX) {
			c1 = M;
		}
		if (EvalData.boardValue2 >= TEN) {
			c2 = H;
		} else if (EvalData.boardValue2 >= SIX) {
			c2 = M;
		}
		if (EvalData.boardValue3 >= TEN) {
			c3 = H;
		} else if (EvalData.boardValue3 >= SIX) {
			c3 = M;
		}
		if (EvalData.street >= FLOP) {
			EvalData.hmlIndexFlop = c1 + c2 + c3;
			return;
		}

		if (EvalData.street >= TURN) {
			if (EvalData.boardValue4 >= TEN) {
				c4 = H;
			} else if (EvalData.boardValue4 >= SIX) {
				c4 = M;
			}
		}
		if (EvalData.street == TURN) {
			EvalData.hmlIndexTurn = c1 + c2 + c3 + c4;
			return;
		}

		if (EvalData.street >= TURN) {
			if (EvalData.boardValue5 >= TEN) {
				c4 = H;
			} else if (EvalData.boardValue5 >= SIX) {
				c4 = M;
			}
		}
		EvalData.hmlIndexRiver = c1 + c2 + c3 + c4;
		System.out.println(EvalData.hmlIndexFlop + " " + EvalData.hmlIndexTurn + " " + EvalData.hmlIndexRiver);
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
	 * Simple boolean expresions are used to calculate these results. Example:
	 *  		suit = EvalData.bothCards[2].suit;
	 *			EvalData.riverFlush = (EvalData.bothCards[3].suit == suit) == 
	 *			(EvalData.bothCards[4].suit == suit)  	==(EvalData.bothCards[5].suit == suit)
	 *			 == (EvalData.bothCards[6].suit == suit);
	 * This will be correct most of the time on the River and 100% of the time on the Flop.
	 * Flop is 5 cards so both hole cards are there.
	 * The way that we use this data on the Turn and the River is as a trigger to do a more
	 * complete evaluation. That improves performance considerably because we are not 
	 * spending timenlooking for the impossible.
	 * For example, riverFlush true may sometimes be incorrect but false ia 100% correct.
	*******************************************************************************/

}
