/*-  **************************************************************************
	 * This switch is set true if we are evaluating multiple hands and the 
	 * Class EvaluateMany is being called to update the Class ManyHands.
	 *  Saving data for ManyHands adds a lot of additional overhead.
	 ***************************************************************************** */
public class Temp2 implements Constants {

	manyHands = true;
	fullSimulation = false;
	 fullSimulationNum = 0;
	detailedAnalysis = true;
	do1755 = false;
	rotatePlayers = false;
	hh = false; // Hand History generate if simulation is true
	simulation = false; // if simulation is true
	/*- *****************************************************************************
	 * Customization
	  ******************************************************************************/
	pplicationDirectory = "C:\\ApplicationData\\"; // Directory for this application // files
	/*- Seed for Random. Change this number for an all new pseudorandom series of cards */
	 long seed = 223112234;
	/*-  ************************************************************************************* 
	 * For the hand currently being evaluated
	* Sorted high to low, not in the order dealt but equivalents
	  ************************************************************************************** */
	holeCards= new Card[PLAYERS][2];
	bothIndexs = new int[PLAYERS];
	 Draw[] draw = new Draw[PLAYERS];
	 Hand[] hand = new Hand[PLAYERS];
	holeCard1 = null; // Copy from Hands for active seat
	holeCard2 = null;
	handIndexes = new int[PLAYERS];
	/*- - Hole cards for each player PLAYERS hand. */
	hands = new String[PLAYERS]; // TODO
	 flop = new Card[3];
	 board = new Card[5];
	 boardUnsorted = new Card[5];
	 boardHighCardValue1 = -1; // Highest
	 boardHighCardValue2 = -1; // Second highest
	 boardLowCardValue = -1; // Lowest
	 boardCount = -1;
	turnCard = null;
	riverCard = null;
	// Hole cards plus board cards sorted
	both = new Card[7];
	 bothCount = -1;
	// Copied only in manyHands is true
	 lopHand = new Card[5];
	  turnHand = new Card[6];
	// Sorted by card ( 0 - 51 ) for Flush and Flush draws
	boardCards = new Card[5];
	bothCards = new Card[7];
	/*-  *********************************************************************************** 
	 * 
	************************************************************************************** */
	playerNames = { "Hero", "Happy", "Fool", "JustMe", "Sue", "Chucky" };
	 oddsForPlayerFlopToTurn = new double[PLAYERS];
	 oddsForPlayerTurnToRiver = new double[PLAYERS];
	 oddsForPlayerFlopToRiver = new double[PLAYERS];
	positions = new int[PLAYERS]; // indexed by seat returns position
	playerPositions = new int[PLAYERS]; // indexed by position returns seat
	 streetNumber = PREFLOP;
	   positionsStart = { SB, BB, UTG, MP, CO, BU };
	 HandRange[] preflopRanges = new HandRange[PLAYERS];
	betCalled = false;
	/*-  *********************************************************************************** 
	 * For simulation modes
	 * The seat number is the index into positions and relativePositions 
	 * positions is an array of SB BB UTG MP CO BU
	  ************************************************************************************** */
	 potBD = zeroBD;
	//  sidePotBD = zeroBD;
	 mainPotBD = zeroBD;
	//  potStartBD = zeroBD;
	//  potEndBD = zeroBD;
	 betToMeBD = zeroBD;
	 betNowBD = zeroBD;

	 tackBD = new BigDecimal[PLAYERS];
	  playerMainPotBD = new BigDecimal[PLAYERS];
	 potOdds = new double[PLAYERS];

	relativePosition = new int[PLAYERS];
	 playerAllin = new boolean[PLAYERS];
	foldedPreflop = new int[4][PLAYERS];
	 tackPreflopBD = new BigDecimal[PLAYERS];
	 playerFoldedPreflop = new boolean[PLAYERS];
	 playerLimpedPreflop = new boolean[PLAYERS];
	 playerFoldedFlop = new boolean[PLAYERS];
	 playerFoldedTurn = new boolean[PLAYERS];
	 playerFoldedRiver = new boolean[PLAYERS];
	 playerWonShowdown = new boolean[PLAYERS];
	  moneyInBD = new BigDecimal[PLAYERS];
	 playerWon = new boolean[PLAYERS]; // Indexed by street,seat,orbit

	 foldCount = -1;
	 allinCount = -1;
	 limpCount = -1;
	 handsPlayed = 0;
	streetComplete = false;
	boardComplete = false;
	 betType = 0;
	 orbit = 0;

	/*-  *********************************************************************************** 
	 * For simulation modes
	 * Used by HandHistory
	  ************************************************************************************** */
	lastOrbit = new int[4]; // Indexed by street
	 isFold = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	 isLimp = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	 isCheck = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	 isCall = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	 isBet = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	 isRaise = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit
	 isAllin = new boolean[4][PLAYERS][4]; // Indexed by street,seat,orbit

	 raisedToBD = new BigDecimal[4][PLAYERS][4]; // Indexed by street,seat,orbit
	 returnedToBD = new BigDecimal[4][PLAYERS][4]; // Indexed by street,seat,orbit
	 betBD = new BigDecimal[4][PLAYERS][4]; // Indexed by street,seat,orbit
	 callBD = new BigDecimal[4][PLAYERS][4]; // Indexed by street,seat,orbit
	 winBD = new BigDecimal[4][PLAYERS][4]; // Indexed by street,seat,orbit
	  winnerCollectedBD = new BigDecimal[PLAYERS]; // Indexed by street,seat,orbit
	  returnedToShowdownBD = new BigDecimal[PLAYERS]; // Indexed by street,seat,orbit
	  playerSidePotSplitTotalBD = new BigDecimal[PLAYERS]; // Indexed by street,seat,orbit
	  playerSidePotSplitBD = new BigDecimal[PLAYERS]; // Indexed by street,seat,orbit

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
	  SBBetBD = new BigDecimal(1);
	  BBBetBD = new BigDecimal(2);
	  preflopBetMultiplyerOpenBD = new BigDecimal(3);; // Times BB + 1 each limp
	  preflopBetMultiplyer3BetBD = new BigDecimal(3);; // Times Open 2 Bet
	  preflopBetMultiplyer4BetBD = new BigDecimal(2.5); // Times 3 Bet
	  postFlopBbetMultiplierBD = new BigDecimal(.5); // Times pot size
	  buyinBD = new BigDecimal(200);
	  rebuyBD = new BigDecimal(100);
	  maxStackBD = new BigDecimal(500);
	/*-  ************************************************************************************* 
	 * For the hand currently being evaluated
	* Results of evaluation 
	  ************************************************************************************** */
	// Flop board analysis - hole cards not included
	 boardArray = new boolean[BOARD_ARRAY_SIZE]; // Board
	 boardArrayMax = -1;
	// Flop board analysis - hole cards included
	//  drawAndMadeArray = new boolean[HANDS_SIZE];
	// Flop only information, not draw or made hand, just information
	 flopArray = new boolean[FLOP_SIZE];
	// Drawing hands
	 drawArray = new boolean[DRAW_HANDS_SIZE];
	// Made hands
	 madeArray = new boolean[MADE_HANDS_SIZE];
	 madeArrayMax = -1;
	/*-  ************************************************************************************* 
	 * Data associated with current hand being evaluated
	  ************************************************************************************** */
	 handValue = -1; // Relative value of hand 0 - 9, 0 fold, 9 nuts
	 position = -1; // SB, BB, UTG, MP, CO, BU
	 bestFlopIndex = -1; // Index into drawAndMadeArray for Flop
	 bestTurnIndex = -1; // Index into boardArray for Turn
	 bestRiverIndex = -1; // Index into boardArray for River
	 handIndex = -1; // Hole cards
	 hmlIndex = -1; // Index into HMLArray
	 flop1755Index = -1; // Index into array of 1755 Flops, all possible
	 flopTypeOf1755 = -1; // Type of 1755 flop
	 bestPossibleArrayFlopIndex = -1; // Index into bestPossibleArrayFlop
	 bestPossibleArrayTurnIndex = -1; // Index into bestPossibleArrayTurn
	 bestPossibleArrayRiverIndex = -1; // Index into bestPossibleArrayRiver
	 winnerShowdown = -1; // Winner seat number
	showdownValue = new int[PLAYERS]; // Relative value of hand
	 cardCount; // Number of cards
	// Current seat and street
	 seat;
	 street;
	// Last seat and street evaluared
	 lastSeat;
	 lastStreet;
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
	 handStrength = -1;
	 rangeAdvantage = -1;
	 madeHand = -1;
	 bestDraw = -1;
	 bestPossible = -1;
	 outs = -1;
	 makeHandPer = -1;
	 flopTexture = -1;
	 turnTexture = -1;
	/*-  ************************************************************************************* 
	 * Board possible hands on the Flop   
	 * these are hands that are possible on this board 
	 * For example on a Flop board, a pair or set is possible on any flop
	 * board but a Flush is not possible on a rainbow board, quads not possible
	 * unless board is paired, etc.. 
	 * For convenience we will use the constants
	 * defined for the OneHand.drawAndMadeArray in this array
	************************************************************************************* */
	 boardPossibleArrayFlop = new boolean[POSSIBLE_DRAW_SIZE];
	 boardPossibleArrayTurn = new boolean[POSSIBLE_DRAW_SIZE];
	 boardPossibleArrayFlopMax = -1;
	 boardPossibleArrayTurnMax = -1;

	/*-  **************************************************************************************
	 * Experimental 
	 * Indexes are used to identify Flop and board types
	  ************************************************************************************** */
	 type = -1; // Ed miller
	 hmlIndexFlop = -1;
	 hmlIndexTurn = -1;
	 hmlIndexRiver = -1;
	sumOfHandValuesFlop = new int[PLAYERS];
	 sumOfBoardValuesFlop = -1;
	 sumOfHoleCardValues = -1;
	 wetDryIndex = -1;
	 DynamicIndex = -1;
	 typeIndex = -1;

	// Array indexes
	 flopIndex = -1;
	 boardArrayIndex = -1;
	/*-  **************************************************************************************
	 * Results of hand analysis individual variables
	  ************************************************************************************** */
	pair = false;
	/*-  **************************************************************************
	* Data used for Showdown 
	* This data is in arrays, one for each player.
	***************************************************************************** */
	 playerFolded = new boolean[PLAYERS];
	showdownRank = new int[PLAYERS];
	showdownHand = new int[PLAYERS];
	highCards1 = new int[PLAYERS];
	highCards2 = new int[PLAYERS];
	suits = new int[PLAYERS];
	kickers1 = new int[PLAYERS];
	kickers2 = new int[PLAYERS];
	kickers3 = new int[PLAYERS];
	 maxValue = -1; // Showdown
	 winnerSeat = -1;
	showdown;
	winner;
	 kicker1 = -1;
	 kicker2 = -1;
	 kicker3 = -1;
	/*-  **************************************************************************
	 * Data used during  Showdown 
	 ***************************************************************************** */
	 highCard1 = -1;
	 highCard2 = -1;
	 suit = -1;
	/*-  **************************************************************************************
	 *  Board analysis, gaps
	  ************************************************************************************** */
	// Gap in sorted hand ( hole cards + board ) c1.value - c2.value -1
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
	// Gap between cards in the sorted board c1.value - c2.value -1
	 boardGap1_2 = -1;
	 boardGap2_3 = -1;
	 boardGap3_4 = -1;
	 boardGap4_5 = -1;
	 boardGap5_6 = -1;
	 boardGap6_7 = -1;
	// Hole card values
	 holeValue1 = -1;
	 holeValue2 = -1;
	// Value of cards in both
	 bothValue1 = -1;
	 bothValue2 = -1;
	 bothValue3 = -1;
	 bothValue4 = -1;
	 bothValue5 = -1;
	 bothValue6 = -1;
	 bothValue7 = -1;
	// Suit of cards in bothCards
	 bothCardsSuit1 = -1;
	 bothCardsSuit2 = -1;
	 bothCardsSuit3 = -1;
	 bothCardsSuit4 = -1;
	 bothCardsSuit5 = -1;
	 bothCardsSuit6 = -1;
	 bothCardsSuit7 = -1;
	// Value of cards in board
	 boardValue1 = -1;
	 boardValue2 = -1;
	 boardValue3 = -1;
	 boardValue4 = -1;
	 boardValue5 = -1;
	// Hands with pairs
	// Set by findPairsSetsFullHouse()
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
	boardF0 = false; // Rainbow
	boardF2 = false; // Two suited
	boardF3 = false;
	boardF4 = false;
	boardF4Draw = false;
	boardFlushDraw = false;
	boardStraightDraw = false;
	boardOesdDraw = false;

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
	flopHighCards = false; // A - T
	flopOverCards = false; // Hole cards bigger than any board card
	flopAceHigh = false; // A hole card is an Ace
	flopHoleCardPairedWithBoard = false; // A hole card is paired with a board card
	flopPocketPair = false; // Hole cards are a pair
	flopOverPair = false; // Paired hole cards higher than any board card
	flopWeakPair = false; // Paired hole cards below any board card
	flopMiddlePair = false; // Paired hole cards not higher or lower than any board card
	flopBoardPair = false;
	flopSet = false;
	flopPaired = false;

	/*- **************************************************************************** 
	* Combined hole cards and board
	*******************************************************************************/
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

	action = new int[PLAYERS]; // Range used
}