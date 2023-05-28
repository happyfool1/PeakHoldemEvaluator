//package evaluate_streets;

import java.math.BigDecimal;

public class EvalData implements Constants {

	/*-  **************************************************************************
	 * This switch is set true if we are evaluating multiple hands and the 
	 * Class EvaluateMany is being called to update the Class ManyHands.
	 *  Saving data for ManyHands adds a lot of additional overhead.
	 ***************************************************************************** */
	static boolean manyHands = true;
	static boolean detailedAnalysis = true;
	static boolean do1755 = true;
	static boolean rotatePlayers = true;
	static boolean hh = true; // Hand History generate if simulation is true
	static boolean simulation = true; // if simulation is true
	/*- *****************************************************************************
	 * Customization
	  ******************************************************************************/
	static String applicationDirectory = "C:\\AplicationData\\"; // Directory for this application // files
	/*- Seed for Random. Change this number for an all new pseudorandom series of cards */
	static long seed = 2000141567;
	/*-  ************************************************************************************* 
	 * For the hand currently being evaluated
	* Sorted high to low, not in the order dealt but equivalents
	  ************************************************************************************** */
	static Card holeCards[][] = new Card[PLAYERS][2];
	static int[] bothIndexs = new int[PLAYERS];
	static Draw[] draw = new Draw[PLAYERS];
	static Hand[] hand = new Hand[PLAYERS];
	static Card holeCard1 = null; // Copy from Hands for active seat
	static Card holeCard2 = null;
	static int[] handIndexes = new int[PLAYERS];
	/*- - Hole cards for each player PLAYERS hand. */
	static String[] hands = new String[PLAYERS]; // TODO
	static Card[] flop = new Card[3];
	static Card[] board = new Card[5];
	static Card[] boardUnsorted = new Card[5];
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
	static final int[] positionsStart = { SB, BB, UTG, MP, CO, BU };
	static double[] rangePercentages = { 10., 10., 8., 12., 20., 35. };
	static HandRange[] preflopRanges = new HandRange[PLAYERS];
	/*-  *********************************************************************************** 
	 * For simulation modes
	 * The seat number is the index into positions and relativePositions 
	 * positions is an array of SB BB UTG MP CO BU
	  ************************************************************************************** */
	  static BigDecimal lastBetBD = zeroBD;
	
	static BigDecimal lastBetBD = zeroBD;
	static BigDecimal[] stackBD = new BigDecimal[PLAYERS];
	static BigDecimal[] playerMainPotBD = new BigDecimal[PLAYERS];
	static double[] potOdds = new double[PLAYERS];

	static int[] relativePosition = new int[PLAYERS];
	static BigDecimal[] moneyIn = new BigDecimal[PLAYERS];
	static int[][] foldedPreflop = new int[4][PLAYERS];
	static BigDecimal[] stackPreflopBD = new BigDecimal[PLAYERS];
	static boolean[] playerFoldedPreflop = new boolean[PLAYERS];
	static boolean[] playerLimpedPreflop = new boolean[PLAYERS];
	static boolean[] playerFoldedFlop = new boolean[PLAYERS];
	static boolean[] playerFoldedTurn = new boolean[PLAYERS];
	static boolean[] playerFoldedRiver = new boolean[PLAYERS];
	static boolean[] playerLostShowdown = new boolean[PLAYERS];
	static boolean[] playerWonShowdown = new boolean[PLAYERS];
	static BigDecimal[] moneyInBD = new BigDecimal[PLAYERS];
	static boolean[] playerWon = new boolean[PLAYERS]; // Indexed by street,seat,orbit

	static int foldCount = -1;
	static int allinCount = -1;
	static int limpCount = -1;
	static int handsPlayed = 0;
	static int gamesInASession = 0;
	static boolean streetComplete = false;
	static boolean boardComplete = false;
	static boolean[] playerAllin = new boolean[PLAYERS];
	static boolean stop = false;
	static int betType = 0;
	static int orbit = 0;
	static BigDecimal betNowBD = zeroBD;
	static BigDecimal potBD = zeroBD;
	static BigDecimal sidePotBD = zeroBD;
	static BigDecimal mainPotBD = zeroBD;
	static BigDecimal potStartBD = zeroBD;
	static BigDecimal potEndBD = zeroBD;
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

	static BigDecimal[][] potStart$ = new BigDecimal[4][4];
	static BigDecimal[][] potEnd$ = new BigDecimal[4][4];
	static BigDecimal[][][] bet$ = new BigDecimal[4][PLAYERS][4];
	static BigDecimal[][][] pot$ = new BigDecimal[4][PLAYERS][4];
	static BigDecimal[][][] moneyIn$ = new BigDecimal[4][PLAYERS][4];

	static BigDecimal[][][] call$ = new BigDecimal[4][PLAYERS][4];
	static BigDecimal[][][] allin$ = new BigDecimal[4][PLAYERS][4];
	static BigDecimal[][][] returnedTo$ = new BigDecimal[4][PLAYERS][4];
	static BigDecimal[][][] raisedTo$ = new BigDecimal[4][PLAYERS][4];
	static BigDecimal[][][] calledTo$ = new BigDecimal[4][PLAYERS][4];
	static BigDecimal[][][] win$ = new BigDecimal[4][PLAYERS][4];
	static BigDecimal[] returnedToShowdown$ = new BigDecimal[PLAYERS];

	/*-  *********************************************************************************** 
	 * For simulation modes
	 * Constants
	  ************************************************************************************** */
	static final BigDecimal SBBetBD = new BigDecimal(1);
	static final BigDecimal BBBetBD = new BigDecimal(2);
	static final BigDecimal preflopBet2SizeBD = BBBetBD.add(BBBetBD.add(BBBetBD)); // BB *3
	static final BigDecimal preflopMinBetSizeBD = BBBetBD.add(SBBetBD); // BB * 2
	static final BigDecimal betMultiplierBD = new BigDecimal((double).5); // Times pot size
	static final BigDecimal buyinBD = new BigDecimal(200);
	static final BigDecimal rebuyBD = new BigDecimal(100);
	static final BigDecimal maxStackBD = new BigDecimal(500);
	/*-  ************************************************************************************* 
	 * For the hand currently being evaluated
	* Results of evaluation 
	  ************************************************************************************** */
	// Flop board analysis - hole cards not included
	static boolean[] boardArray = new boolean[BOARD_ARRAY_SIZE]; // Board
	static int boardArrayMax = -1;
	// Flop board analysis - hole cards included
	// static boolean[] drawAndMadeArray = new boolean[MAX_HANDS];
	// Flop only information, not draw or made hand, just information
	static boolean[] flopArray = new boolean[FLOP_MAX];
	// Drawing hands
	static boolean[] drawArray = new boolean[DRAW_MAX_HANDS];
	// Made hands
	static boolean[] madeArray = new boolean[MADE_MAX_HANDS];
	static int madeArrayMax = -1;
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
	static boolean[] boardPossibleArrayFlop = new boolean[POSSIBLE_MAX];
	static boolean[] boardPossibleArrayTurn = new boolean[POSSIBLE_MAX];
	static int boardPossibleArrayFlopMax = -1;
	static int boardPossibleArrayTurnMax = -1;
	/*-  **************************************************************************************
	 * Extracted from drawAndMadeArray, only made hands in this array, no draws
	  ************************************************************************************** */
	static boolean[] madeArrayFlop = new boolean[MADE_MAX_HANDS];
	static boolean[] madeArrayTurn = new boolean[MADE_MAX_HANDS];
	static boolean[] madeArrayRiver = new boolean[MADE_MAX_HANDS];
	static int madeArrayFlopMax = -1;
	static int madeArrayTurnMax = -1;
	static int madeArrayRiverMax = -1;
	/*-  **************************************************************************************
	 * Extracted from drawAndMadeArray, only made hands in this array, no draws
	  ************************************************************************************** */
	static boolean[] drawArrayFlop = new boolean[DRAW_MAX_HANDS];
	static boolean[] drawArrayTurn = new boolean[DRAW_MAX_HANDS];
	static int drawArrayFlopMax = -1;
	static int drawArrayTurnMax = -1;
	/*-  **************************************************************************************
	 * Experimental 
	 * Indexes are used to identify Flop and board types
	  ************************************************************************************** */
	static int type = -1; // Ed miller
	static int hmlIndexFlop = -1;
	static int hmlIndexTurn = -1;
	static int hmlIndexRiver = -1;
	static int sumOfHandValuesFlop = -1;
	static int sumOfBoardValuesFlop = -1;
	static int sumOfHoleCardValues = -1;
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
	static boolean[] playerDidShowdownValue = new boolean[PLAYERS];
	static boolean[] playerFolded = new boolean[PLAYERS];
	static int[] showdownRank = new int[PLAYERS];
	static int[] showdownHand = new int[PLAYERS];
	static String[] showdownSt = new String[PLAYERS];
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
	static boolean boardAceHigh = false;
	static boolean boardPair = false;
	static boolean boardSet = false;
	static boolean boardHighCard = false;
	static boolean boardF0 = false; // Rainbow
	static boolean boardF2 = false; // Two suited
	static boolean boardF3 = false;
	static boolean boardF4 = false;
	static boolean boardF4Draw = false;
	static boolean boardFlushDraw = false;
	static boolean boardStraightDraw = false;
	static boolean boardOesdDraw = false;
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

}
