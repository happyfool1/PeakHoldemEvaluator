//package evaluate_streets;

import java.math.BigDecimal;

public class EvalData implements Constants {

	/*-  **************************************************************************
	 * This switch is set true if we are evaluating multiple hands and the 
	 * Class EvaluateMany is being called to update the Class ManyHands.
	 *  Saving data for ManyHands adds a lot of additional overhead.
	 ***************************************************************************** */
	static boolean manyHands = true;
	static boolean fullSimulation = false;
	static int fullSimulationNum = 0;
	static boolean detailedAnalysis = true;
	static boolean do1755 = false;
	static boolean rotatePlayers = false;
	static boolean hh = false; // Hand History generate if simulation is true
	static boolean simulation = false; // if simulation is true
	/*- *****************************************************************************
	 * Customization
	  ******************************************************************************/
	static String applicationDirectory = "C:\\ApplicationData\\"; // Directory for this application // files
	/*- Seed for Random. Change this number for an all new pseudorandom series of cards */
	static long seed = 223112234;
	/*-  ************************************************************************************* 
	 * For the hand currently being evaluated
	* Sorted high to low, not in the order dealt but equivalents
	  ************************************************************************************** */
	static Card holeCards[][] = new Card[PLAYERS][2];
	static int[] bothIndexs = new int[PLAYERS];
	static Draw[] draw = { new Draw(), new Draw(), new Draw(), new Draw(), new Draw(), new Draw() };
	static Hand[] hand = { new Hand(), new Hand(), new Hand(), new Hand(), new Hand(), new Hand() };
	static Card holeCard1 = null; // Copy from Hands for active seat
	static Card holeCard2 = null;
	static int[] handIndexes = new int[PLAYERS];
	/*- - Hole cards for each player PLAYERS hand. */
	static String[] hands = new String[PLAYERS]; // TODO
	static Card[] flop = new Card[3];
	static Card[] board = new Card[5];
	static Card[] boardUnsorted = new Card[5];
	static int boardHighCardValue1 = -1; // Highest
	static int boardHighCardValue2 = -1; // Second highest
	static int boardLowCardValue = -1; // Lowest
	static int boardCount = -1;
	static Card turnCard = null;
	static Card riverCard = null;
	// Hole cards plus board cards sorted
	static final Card[] both = new Card[7];
	static int bothCount = -1;
	// Copied only in manyHands is true
	static final Card[] flopHand = new Card[5];
	static final Card[] turnHand = new Card[6];
	// Sorted by card ( 0 - 51 ) for Flush and Flush draws
	static final Card[] boardCards = new Card[5];
	static final Card[] bothCards = new Card[7];
	/*-  *********************************************************************************** 
	 * 
	************************************************************************************** */
	static String[] playerNames = { "Hero", "Happy", "Fool", "JustMe", "Sue", "Chucky" };
	static double[] oddsForPlayerFlopToTurn = new double[PLAYERS];
	static double[] oddsForPlayerTurnToRiver = new double[PLAYERS];
	static double[] oddsForPlayerFlopToRiver = new double[PLAYERS];
	static int[] positions = new int[PLAYERS]; // indexed by seat returns position
	static int[] playerPositions = new int[PLAYERS]; // indexed by position returns seat
	static int streetNumber = PREFLOP;
	static final int[] playerPositionsStart = { SB, BB, UTG, MP, CO, BU };
	static HandRange[] preflopRanges = new HandRange[PLAYERS];
	static boolean betCalled = false;
	/*-  *********************************************************************************** 
	 * For simulation modes
	 * The seat number is the index into positions and relativePositions 
	 * positions is an array of SB BB UTG MP CO BU
	  ************************************************************************************** */
	static BigDecimal potBD = zeroBD;
	// static BigDecimal sidePotBD = zeroBD;
	static BigDecimal mainPotBD = zeroBD;
	// static BigDecimal potStartBD = zeroBD;
	// static BigDecimal potEndBD = zeroBD;
	static BigDecimal betToMeBD = zeroBD;
	static BigDecimal betNowBD = zeroBD;

	static BigDecimal[] stackBD = { new BigDecimal(200), new BigDecimal(200), new BigDecimal(200),
			new BigDecimal(200), new BigDecimal(200), new BigDecimal(200) };

	static BigDecimal[] playerMainPotBD = new BigDecimal[PLAYERS];
	static double[] potOdds = new double[PLAYERS];

	static int[] relativePosition = new int[PLAYERS];
	static boolean[] playerAllin = new boolean[PLAYERS];
	static int[][] foldedPreflop = new int[4][PLAYERS];
	static BigDecimal[] stackPreflopBD = new BigDecimal[PLAYERS];
	static boolean[] playerFoldedPreflop = new boolean[PLAYERS];
	static boolean[] playerLimpedPreflop = new boolean[PLAYERS];
	static boolean[] playerFoldedFlop = new boolean[PLAYERS];
	static boolean[] playerFoldedTurn = new boolean[PLAYERS];
	static boolean[] playerFoldedRiver = new boolean[PLAYERS];
	static boolean[] playerWonShowdown = new boolean[PLAYERS];
	static BigDecimal[] moneyInBD = { zeroBD, zeroBD, zeroBD, zeroBD, zeroBD, zeroBD };
	static boolean[] playerWon = new boolean[PLAYERS]; // Indexed by street,seat,orbit

	static int foldCount = -1;
	static int allinCount = -1;
	static int limpCount = -1;
	static int handsPlayed = 0;
	static boolean streetComplete = false;
	static boolean boardComplete = false;
	static int betType = 0;
	static int orbit = 0;

	/*-  *********************************************************************************** 
	 * For simulation modes
	 * Used by HandHistory
	  ************************************************************************************** */
	static int[] lastOrbit = new int[4]; // Indexed by street
	static boolean[][][] isFold = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static boolean[][][] isLimp = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static boolean[][][] isCheck = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static boolean[][][] isCall = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static boolean[][][] isBet = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static boolean[][][] isRaise = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static boolean[][][] isAllin = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit

	static BigDecimal[][][] raisedToBD = new BigDecimal[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static BigDecimal[][][] returnedToBD = new BigDecimal[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static BigDecimal[][][] betBD = new BigDecimal[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static BigDecimal[][][] callBD = new BigDecimal[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static BigDecimal[][][] winBD = new BigDecimal[4][PLAYERS][4]; // Indexed by street,seat,orbit
	static BigDecimal[] winnerCollectedBD = new BigDecimal[PLAYERS]; // Indexed by street,seat,orbit
	static BigDecimal[] returnedToShowdownBD = new BigDecimal[PLAYERS]; // Indexed by street,seat,orbit
	static BigDecimal[] playerSidePotSplitTotalBD = new BigDecimal[PLAYERS]; // Indexed by street,seat,orbit
	static BigDecimal[] playerSidePotSplitBD = new BigDecimal[PLAYERS]; // Indexed by street,seat,orbit

	/*-  *********************************************************************************** 
	 * For simulation modes
	 * Constants
	 * 
	 * // TODO adjustments to preflop bet sizes
	 * In early position or when facing tighter opponents, you may opt for a larger raise size 
	 * (3.5-4 big blinds) to discourage callers and build a bigger pot with your strong hands.
	 * In later position or when playing against looser opponents, you can use a smaller 
	 * sizing (2.5-3 big blinds) to give yourself better pot odds and allow for more flexibility postflop.
	 * For example, if the initial raiser opens to $6 (3 big blinds), a standard 3-bet size 
	 * would be between $18 to $24. If you're in position, you can lean toward the smaller 
	 * end of that range, while out of position, you might opt for a larger sizing to 
	 * discourage callers and take control of the pot.
	 * 
	 * In a 6-max No-Limit $1/$2 game, a typical 4-bet sizing is between 2 to 2.5 times the size 
	 * of the 3-bet. However, the exact sizing can vary depending on factors such as your position,
	 * your opponents' tendencies, and the size of the 3-bet.
	
	* For example, let's say the initial raiser (2-bet) opens to $6 (3 big blinds), and another player 
	* 3-bets to $18. A standard 4-bet sizing would be between $36 to $45.
	
	* When you're in position, you might opt for a smaller 4-bet size to give yourself better pot odds 
	* and the ability to exert more postflop pressure on your opponent. When you're out of position, 
	* a larger 4-bet size can help to discourage your opponent from calling and take down the pot 
	* preflop or give you more control of the pot if they do call.
	
	* In No-Limit Texas Hold'em, the size of your bets on the flop is an important factor in dictating 
	 *the pace of the hand and exerting pressure on your opponents. Here are some general guidelines for 
	 * bet sizes on the flop:
	*
	* Standard sizing: A common bet size on the flop is between 50% and 75% of the pot. 
	* This sizing allows you to build the pot with your strong hands, while also putting pressure on 
	* your opponents with weaker holdings. For example, if the pot is $20, a standard bet size would be 
	* between $10 and $15.
	
	* Position: Your position at the table influences your bet sizing. When you're in position 
	* (acting after your opponent), you can use smaller bet sizes to take advantage of your informational edge.
	* When out of position, you might choose larger bet sizes to discourage your opponent from floating 
	* with weaker hands or to protect your strong hands.
	*
	* Board texture: The texture of the flop is an important consideration when determining your bet size.
	* On dry, unconnected flops (e.g., K-7-2 rainbow), you can use smaller bet sizes 
	* (around 1/3 to 1/2 of the pot) because it's less likely that your opponent has connected with the board.
	* On wet, coordinated flops (e.g., Q-J-10 with two suited cards), larger bet sizes 
	* (around 2/3 to full pot) are more appropriate to charge your opponents for drawing to better hands.
	*
	* Opponent tendencies: Adjust your bet sizing based on your opponents' playing styles.
	 Against tighter opponents, smaller bet sizes may be enough to take down the pot, 
	 as they are likely to fold weaker hands. Against looser or more aggressive opponents,
	  larger bet sizes can help protect your strong hands and put pressure on their weaker holdings.
	*
	* Stack sizes: Consider the effective stack sizes when determining your bet size. 
	* With deeper stacks, you may want to use larger bet sizes to build a bigger pot for 
	* when you have a strong hand. When you or your opponent have smaller stacks, 
	* smaller bet sizes can help you control the pot and prevent committing too many 
	* chips with a marginal hand.
	*
	* These guidelines provide a starting point for determining your bet sizes on the flop,
	*  but keep in mind that poker is a dynamic game that requires constant adjustments
	 * based on various factors, including your opponents' tendencies and your overall game plan.
	 * 
	 ************************************************************************************** */
	static final BigDecimal SBBetBD = new BigDecimal(1);
	static final BigDecimal BBBetBD = new BigDecimal(2);
	static final BigDecimal preflopBetMultiplyerOpenBD = new BigDecimal(3);; // Times BB + 1 each limp
	static final BigDecimal preflopBetMultiplyer3BetBD = new BigDecimal(3);; // Times Open 2 Bet
	static final BigDecimal preflopBetMultiplyer4BetBD = new BigDecimal(2.5); // Times 3 Bet
	static final BigDecimal postFlopBbetMultiplierBD = new BigDecimal(.5); // Times pot size
	static final BigDecimal buyinBD = new BigDecimal(200);
	static final BigDecimal rebuyBD = new BigDecimal(100);
	static final BigDecimal maxStackBD = new BigDecimal(500);
	/*-  ************************************************************************************* 
	 * For the hand currently being evaluated
	* Results of evaluation 
	*************************************************************************************** */
	// Drawing hands
	static boolean[] drawArray = new boolean[DRAW_SIZE];
	// Made hands
	static boolean[] madeArray = new boolean[MADE_SIZE];
	static int madeArrayMax = -1;
	// board analysis - hole cards not included
	static boolean[] boardArray = new boolean[BOARD_SIZE]; // Board
	static int boardArrayMax = -1;
	// Flop board analysis - hole cards included
	static boolean[] flopArray = new boolean[FLOP_SIZE];

	/*-  ************************************************************************************* 
	 * Best made hand or draw for player on this street
	  ************************************************************************************** */
	static int[] drawFlop = new int[PLAYERS];
	static int[] drawTurn = new int[PLAYERS];
	static int[] madeFlop = new int[PLAYERS];
	static int[] madeTurn = new int[PLAYERS];
	static int[] madeRiver = new int[PLAYERS];
		static int[] showdownHand = new int[PLAYERS];

	/*-  ************************************************************************************* 
	 * Data associated with current hand being evaluated
	  ************************************************************************************** */
	static int handValue = -1; // Relative value of hand 0 - 9, 0 fold, 9 nuts
	static int position = -1; // SB, BB, UTG, MP, CO, BU
	static int bestFlopIndex = -1; // Index into drawAndMadeArray for Flop
	static int bestTurnIndex = -1; // Index into boardArray for Turn
	static int bestRiverIndex = -1; // Index into boardArray for River
	static int handIndex = -1; // Hole cards
	static int hmlIndex = -1; // Index into HMLArray
	static int flop1755Index = -1; // Index into array of 1755 Flops, all possible
	static int flopTypeOf1755 = -1; // Type of 1755 flop
	static int bestPossibleArrayFlopIndex = -1; // Index into bestPossibleArrayFlop
	static int bestPossibleArrayTurnIndex = -1; // Index into bestPossibleArrayTurn
	static int bestPossibleArrayRiverIndex = -1; // Index into bestPossibleArrayRiver
	static int winnerShowdown = -1; // Winner seat number
	static int[] showdownValue = new int[PLAYERS]; // Relative value of hand
	static int cardCount; // Number of cards
	// Current seat and street
	static int seat;
	static int street;
	// Last seat and street evaluared
	static int lastSeat;
	static int lastStreet;
	/*-  ************************************************************************************* 
	 * Net results of analyzing this hand.
	 * These are the most important to be able to decide on how to play the hand.
	 * When evaluating Texas hold'em hands and draws on the flop without any information 
	 * on player actions, there are several important things to consider:
	 *
	 * Your own hand strength: This includes your current hand and the potential to improve
	 *  with future community cards.
	 *
	 * Possible opponent hand ranges: Consider what hands your opponents could have
	 *  based on their position, preflop betting, and any visible community cards. 
	 *  This will give you an idea of the strength of your hand relative to your opponents' 
	 *  possible holdings.
	 *
	 * Pot odds: Determine the size of the pot and compare it to the cost of calling a bet 
	 * or making a bet yourself. 
	 * This will help you decide if it's worth continuing in the hand or folding.
	 * 
	 * Board texture: Analyze the texture of the flop to determine how it affects the range 
	 * of possible hands that your opponents could have. 
	 *  A dry flop with no draws will limit the range of possible hands, while a 
	 *  wet flop with many draws will expand it.
	 *
	 * Position: Your position at the table is crucial in determining your actions. 
	 * Being in late position will give you more information on your opponents' actions, 
	 * which can help you make better decisions.
	 *
	 * By considering these factors, you can make informed decisions on whether to 
	 * continue in the hand, fold, or make a bet. 
	 * Keep in mind that evaluating hands and draws in Texas hold'em is an ongoing process, 
	 * and new information will continue to be revealed with each subsequent community card.
	  ************************************************************************************** */
	static int handStrength = -1;
	static double rangeAdvantage = -1;
	static int madeHand = -1;
	static int bestDraw = -1;
	static double bestPossible = -1;
	static int outs = -1;
	static double makeHandPer = -1;
	static int flopTexture = -1;
	static int turnTexture = -1;
	/*-  ************************************************************************************* 
	 * Board possible hands on the Flop   
	 * these are hands that are possible on this board 
	 * For example on a Flop board, a pair or set is possible on any flop
	 * board but a Flush is not possible on a rainbow board, quads not possible
	 * unless board is paired, etc.. 
	 * For convenience we will use the constants
	 * defined for the OneHand.drawAndMadeArray in this array
	************************************************************************************* */
	static boolean[] boardPossibleArrayFlop = new boolean[POSSIBLE_DRAW_SIZE];
	static boolean[] boardPossibleArrayTurn = new boolean[POSSIBLE_DRAW_SIZE];
	static int boardPossibleArrayFlopMax = -1;
	static int boardPossibleArrayTurnMax = -1;

	/*-  **************************************************************************************
	 * Experimental 
	 * Indexes are used to identify Flop and board types
	  ************************************************************************************** */
	static int type = -1; // Ed miller
	static int hmlIndexFlop = -1;
	static int hmlIndexTurn = -1;
	static int hmlIndexRiver = -1;
	static int[] sumOfHandValuesFlop = new int[PLAYERS];
	static int sumOfBoardValuesFlop = -1;
	static int sumOfHoleCardValues = -1;
	static int wetDryIndex = -1;
	static int staticDynamicIndex = -1;
	static int typeIndex = -1;

	// Array indexes
	static int flopIndex = -1;
	static int boardArrayIndex = -1;
	/*-  **************************************************************************************
	 * Results of hand analysis individual variables
	  ************************************************************************************** */
	static boolean pair = false;
	/*-  **************************************************************************
	* Data used for Showdown 
	* This data is in arrays, one for each player.
	***************************************************************************** */
	static boolean[] playerFolded = new boolean[PLAYERS];
	static int[] showdownRank = new int[PLAYERS];

	static int[] highCards1 = new int[PLAYERS];
	static int[] highCards2 = new int[PLAYERS];
	static int[] suits = new int[PLAYERS];
	static int[] kickers1 = new int[PLAYERS];
	static int[] kickers2 = new int[PLAYERS];
	static int[] kickers3 = new int[PLAYERS];
	static int maxValue = -1; // Showdown
	static int winnerSeat = -1;
	static boolean showdown;
	static boolean winner;
	static int kicker1 = -1;
	static int kicker2 = -1;
	static int kicker3 = -1;
	/*-  **************************************************************************
	 * Data used during  Showdown 
	 ***************************************************************************** */
	static int highCard1 = -1;
	static int highCard2 = -1;
	static int suit = -1;
	/*-  **************************************************************************************
	 *  Board analysis, gaps
	  ************************************************************************************** */
	// Gap in sorted hand ( hole cards + board ) c1.value - c2.value -1
	static int bothGap1_2 = -1;
	static int bothGap2_3 = -1;
	static int bothGap3_4 = -1;
	static int bothGap4_5 = -1;
	static int bothGap5_6 = -1;
	static int bothGap6_7 = -1;
	static int bothGap1_3 = -1;
	static int bothGap1_4 = -1;
	static int bothGap2_4 = -1;
	static int bothGap2_5 = -1;
	static int bothGap4_6 = -1;
	static int bothGap5_7 = -1;
	// Gap between cards in the sorted board c1.value - c2.value -1
	static int boardGap1_2 = -1;
	static int boardGap2_3 = -1;
	static int boardGap3_4 = -1;
	static int boardGap4_5 = -1;
	static int boardGap5_6 = -1;
	static int boardGap6_7 = -1;
	// Hole card values
	static int holeValue1 = -1;
	static int holeValue2 = -1;
	// Value of cards in both
	static int bothValue1 = -1;
	static int bothValue2 = -1;
	static int bothValue3 = -1;
	static int bothValue4 = -1;
	static int bothValue5 = -1;
	static int bothValue6 = -1;
	static int bothValue7 = -1;
	// Suit of cards in bothCards
	static int bothCardsSuit1 = -1;
	static int bothCardsSuit2 = -1;
	static int bothCardsSuit3 = -1;
	static int bothCardsSuit4 = -1;
	static int bothCardsSuit5 = -1;
	static int bothCardsSuit6 = -1;
	static int bothCardsSuit7 = -1;
	// Value of cards in board
	static int boardValue1 = -1;
	static int boardValue2 = -1;
	static int boardValue3 = -1;
	static int boardValue4 = -1;
	static int boardValue5 = -1;
	// Hands with pairs
	// Set by findPairsSetsFullHouse()
	static boolean onePair = false;
	static boolean twoPair = false;
	static boolean threeOfKind = false;
	static boolean fourOfKind = false;
	static boolean fullHouse = false;
	static boolean anyPaired = false;
	static boolean straight = false;
	static boolean flush = false;

	static boolean boardAceHigh = false;
	static boolean boardPair = false;
	static int boardPairValue = -1;
	static boolean boardTwoPair = false;
	static int boardTwoPairValue1 = -1;
	static int boardTwoPairValue2 = -1;
	static boolean boardSet = false;
	static int boardSetValue = -1;
	static boolean boardHighCard = false;
	static int boardHighCardValue = -1;
	static boolean boardF0 = false; // Rainbow
	static boolean boardF2 = false; // Two suited
	static boolean boardF3 = false;
	static boolean boardF4 = false;
	static boolean boardF4Draw = false;
	static boolean boardFlushDraw = false;
	static boolean boardStraightDraw = false;
	static boolean boardOesdDraw = false;

	/*-  **************************************************************************************
	 *  All ot these are potential, not for certain.
	 *  That is because at showdown our hand must contain both of our hole cards 
	 *  and 3 cards from the common board cards.
	 *  The boolean determinations below are based on looking at out hole cards combined
	 *  with all of the board cards. That group of 7 cards is sorted in ascending order. 
	 *  Simple boolean expresions are used to calculate these results. Example:
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
	 * 
	  ************************************************************************************** */
	static boolean flopHighCards = false; // A - T
	static boolean flopOverCards = false; // Hole cards bigger than any board card
	static boolean flopAceHigh = false; // A hole card is an Ace
	static boolean flopHoleCardPairedWithBoard = false; // A hole card is paired with a board card
	static boolean flopPocketPair = false; // Hole cards are a pair
	static boolean flopOverPair = false; // Paired hole cards higher than any board card
	static boolean flopWeakPair = false; // Paired hole cards below any board card
	static boolean flopMiddlePair = false; // Paired hole cards not higher or lower than any board card
	static boolean flopBoardPair = false;
	static boolean flopSet = false;
	static boolean flopPaired = false;

	/*- **************************************************************************** 
	* Combined hole cards and board
	*******************************************************************************/
	static boolean bothGutshotDraw = false;
	static boolean bothFlushDraw = false;
	static boolean bothStraightDraw = false;
	static boolean bothOesdDraw = false;
	static boolean bothFlush = false;
	static boolean bothStraight = false;
	static boolean bothStraightAce = false;
	static boolean bothNone = false;
	static boolean bothPair = false;
	static boolean both2Pair = false;
	static boolean bothSet = false;
	static boolean bothQuad = false;
	static boolean bothFull = false;
	static boolean flopRainbow = false;
	static boolean flop2Suited = false;
	static boolean flop3Suited = false;
	static int gap0 = -1;
	static int gap1 = -1;
	static int gap2 = -1;
	static int gap0Score = -1;
	static int gap1Score = -1;
	static int gap2Score = -1;
	static int handsToPlay = 0;
	static boolean interactive = false;
	static String[] decSt = { "Rules", "MDFCheck", "MDFBet1", "MDFBet2", "Bluff", "ABC", "C-Bet", "GTO", "Barrel",
			"Final", "None" };
	static String[] rpSt = { "First", "First HU", "Middle", "Last", "Last HU" };
	static String[] actionSt = { "Check", "Bet", "Raise", "Call", "All-in", "Call All-in", "Fold", "None" };
	static String[] ruleSt = { "Bet", "Call", "All-in", "None" };
	static String[] opActionSt = { "Check", "Bet", "Raise", "Call", "All-in", "Call All-in", "Fold", "None" };
	static String[] streetSt = { "Preflop", "Flop", "Turn", "River", "None" };
	static int[] action = new int[PLAYERS]; // Range used
	static String releaseDate = "202100511";
	static String installDate = "202100511";
	static String expirationDateFree = "202100511";
	static String expirationDateTrial = "202100511";
	static String machineID = "chucky";
	static String UUID = "11324";
	static String version = "02.00.00";
	static boolean freeVersion = false;
	static boolean trialVersion = false;
	static boolean paidVersion = true;
	static boolean developerVersion = true;

	private EvalData() {
		throw new IllegalStateException("Utility class");
	}

	/*- *******************************************************************************************
	* This method simply initializes almost all variables in EvalData
	 ******************************************************************************************** */
	static void initialize() {

		for (int i = 0; i < PLAYERS; i++) {
			sumOfHandValuesFlop[i] = -1;
			bothIndexs[i] = -1;
			handIndexes[i] = -1;
			hands[i] = "";
			positions[i] = playerPositionsStart[i];
			playerPositions[i] = playerPositionsStart[i];
			preflopRanges[i] = null;
			oddsForPlayerFlopToTurn[i] = -1.;
			oddsForPlayerTurnToRiver[i] = -1.;
			oddsForPlayerFlopToRiver[i] = -1.;
			stackBD[i] = buyinBD;
			playerMainPotBD[i] = zeroBD;
			potOdds[i] = -1.;
			relativePosition[i] = -1;
			playerAllin[i] = false;
			foldedPreflop = new int[4][PLAYERS];
			stackPreflopBD[i] = zeroBD;
			playerFoldedPreflop[i] = false;
			playerLimpedPreflop[i] = false;
			playerFoldedFlop[i] = false;
			playerFoldedTurn[i] = false;
			playerFoldedRiver[i] = false;
			playerWonShowdown[i] = false;
			moneyInBD[i] = zeroBD;
			playerWon[i] = false;
			showdownValue[i] = -1;
			playerFolded[i] = false;
			showdownRank[i] = -1;
			showdownHand[i] = -1;
			highCards1[i] = -1;
			highCards2[i] = -1;
			suits[i] = -1;
			kickers1[i] = -1;
			kickers2[i] = -1;
			kickers3[i] = -1;
			action[i] = -1;
			returnedToShowdownBD[i] = zeroBD;
			playerSidePotSplitTotalBD[i] = zeroBD;
			playerSidePotSplitBD[i] = zeroBD;
			drawFlop[i] = 0;
			drawTurn[i] = 0;
			madeFlop[i] = 0;
			madeTurn[i] = 0;
			madeRiver[i] = 0;
		}

		lastOrbit = new int[4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < PLAYERS; j++) {
				for (int k = 0; k < 4; k++) {
					isFold[i][j][k] = false;
					isLimp[i][j][k] = false;
					isCheck[i][j][k] = false;
					isCall[i][j][k] = false;
					isBet[i][j][k] = false;
					isRaise[i][j][k] = false;
					isAllin[i][j][k] = false;
					raisedToBD[i][j][k] = zeroBD;
					returnedToBD[i][j][k] = zeroBD;
					betBD[i][j][k] = zeroBD;
					callBD[i][j][k] = zeroBD;
				}
			}
		}

		for (int i = 0; i < BOARD_SIZE; i++) {
			boardArray[i] = false;
		}
		for (int i = 0; i < FLOP_SIZE; i++) {
			flopArray[i] = false;
		}

		for (int i = 0; i < DRAW_SIZE; i++) {
			drawArray[i] = false;
		}
		for (int i = 0; i < MADE_SIZE; i++) {
			madeArray[i] = false;
		}

		for (int i = 0; i < POSSIBLE_DRAW_SIZE; i++) {
			boardPossibleArrayFlop[i] = false;
		}

		maxValue = -1;
		streetNumber = PREFLOP;
		manyHands = true;
		fullSimulation = false;
		fullSimulationNum = 0;
		detailedAnalysis = true;
		do1755 = false;
		rotatePlayers = false;
		hh = false;
		simulation = false;
		holeCard1 = null;
		holeCard2 = null;
		boardHighCardValue1 = -1;
		boardHighCardValue2 = -1;
		boardLowCardValue = -1;
		boardCount = -1;
		turnCard = null;
		riverCard = null;
		bothCount = -1;
		betCalled = false;
		potBD = zeroBD;
		mainPotBD = zeroBD;
		betToMeBD = zeroBD;
		betNowBD = zeroBD;
		foldCount = -1;
		allinCount = -1;
		limpCount = -1;
		streetComplete = false;
		boardComplete = false;
		betType = 0;
		orbit = 0;

		boardArrayMax = -1;

		madeArrayMax = -1;
		handValue = -1;
		position = -1;
		bestFlopIndex = -1;
		bestTurnIndex = -1;
		bestRiverIndex = -1;
		handIndex = -1;
		hmlIndex = -1;
		flop1755Index = -1;
		flopTypeOf1755 = -1;
		bestPossibleArrayFlopIndex = -1;
		bestPossibleArrayTurnIndex = -1;
		bestPossibleArrayRiverIndex = -1;
		winnerShowdown = -1;

		cardCount = -1;

		seat = -1;
		street = -1;

		lastSeat = -1;
		lastStreet = -1;

		handStrength = -1;
		rangeAdvantage = -1;
		madeHand = -1;
		bestDraw = -1;
		bestPossible = -1;
		outs = -1;
		makeHandPer = -1;
		flopTexture = -1;
		turnTexture = -1;

		boardPossibleArrayFlopMax = -1;
		boardPossibleArrayTurnMax = -1;

		type = -1;
		hmlIndexFlop = -1;
		hmlIndexTurn = -1;
		hmlIndexRiver = -1;

		sumOfBoardValuesFlop = -1;
		sumOfHoleCardValues = -1;
		wetDryIndex = -1;
		staticDynamicIndex = -1;
		typeIndex = -1;

		flopIndex = -1;
		boardArrayIndex = -1;

		pair = false;

		winnerSeat = -1;
		showdown = false;
		;
		winner = false;
		;
		kicker1 = -1;
		kicker2 = -1;
		kicker3 = -1;

		highCard1 = -1;
		highCard2 = -1;
		suit = -1;

		bothGap1_2 = -1;
		bothGap2_3 = -1;
		bothGap3_4 = -1;
		bothGap4_5 = -1;
		bothGap5_6 = -1;
		bothGap6_7 = -1;
		bothGap1_3 = -1;
		bothGap1_4 = -1;
		bothGap2_4 = -1;
		bothGap2_5 = -1;
		bothGap4_6 = -1;
		bothGap5_7 = -1;

		boardGap1_2 = -1;
		boardGap2_3 = -1;
		boardGap3_4 = -1;
		boardGap4_5 = -1;
		boardGap5_6 = -1;
		boardGap6_7 = -1;

		holeValue1 = -1;
		holeValue2 = -1;

		bothValue1 = -1;
		bothValue2 = -1;
		bothValue3 = -1;
		bothValue4 = -1;
		bothValue5 = -1;
		bothValue6 = -1;
		bothValue7 = -1;

		bothCardsSuit1 = -1;
		bothCardsSuit2 = -1;
		bothCardsSuit3 = -1;
		bothCardsSuit4 = -1;
		bothCardsSuit5 = -1;
		bothCardsSuit6 = -1;
		bothCardsSuit7 = -1;

		boardValue1 = -1;
		boardValue2 = -1;
		boardValue3 = -1;
		boardValue4 = -1;
		boardValue5 = -1;

		onePair = false;
		twoPair = false;
		threeOfKind = false;
		fourOfKind = false;
		fullHouse = false;
		anyPaired = false;
		straight = false;
		flush = false;

		boardAceHigh = false;
		boardPair = false;
		boardPairValue = -1;
		boardTwoPair = false;
		boardTwoPairValue1 = -1;
		boardTwoPairValue2 = -1;
		boardSet = false;
		boardSetValue = -1;
		boardHighCard = false;
		boardHighCardValue = -1;
		boardF0 = false;
		boardF2 = false;
		boardF3 = false;
		boardF4 = false;
		boardF4Draw = false;
		boardFlushDraw = false;
		boardStraightDraw = false;
		boardOesdDraw = false;

		flopHighCards = false;
		flopOverCards = false;
		flopAceHigh = false;
		flopHoleCardPairedWithBoard = false;
		flopPocketPair = false;
		flopOverPair = false;
		flopWeakPair = false;
		flopMiddlePair = false;
		flopBoardPair = false;
		flopSet = false;
		flopPaired = false;

		bothGutshotDraw = false;
		bothFlushDraw = false;
		bothStraightDraw = false;
		bothOesdDraw = false;
		bothFlush = false;
		bothStraight = false;
		bothStraightAce = false;
		bothNone = false;
		bothPair = false;
		both2Pair = false;
		bothSet = false;
		bothQuad = false;
		bothFull = false;
		flopRainbow = false;
		flop2Suited = false;
		flop3Suited = false;
		gap0 = -1;
		gap1 = -1;
		gap2 = -1;
		gap0Score = -1;
		gap1Score = -1;
		gap2Score = -1;
		handsToPlay = 0;
		interactive = false;

	}

}
