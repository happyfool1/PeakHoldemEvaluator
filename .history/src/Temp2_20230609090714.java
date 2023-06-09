
public class Temp2 implements Constants {


    static void initialize(){
	manyHands = true;
	fullSimulation = false;
	 fullSimulationNum = 0;
	detailedAnalysis = true;
	do1755 = false;
	rotatePlayers = false;
	hh = false; // Hand History generate if simulation is true
	simulation = false; // if simulation is true

	 
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
 
	playerNames = { "Hero", "Happy", "Fool", "JustMe", "Sue", "Chucky" };
	 oddsForPlayerFlopToTurn = new double[PLAYERS];
	 oddsForPlayerTurnToRiver = new double[PLAYERS];
	 oddsForPlayerFlopToRiver = new double[PLAYERS];
	positions = new int[PLAYERS]; // indexed by seat returns position
	playerPositions = new int[PLAYERS]; // indexed by position returns seat
	 streetNumber = PREFLOP;
	   positionsStart = { SB, BB, UTG, MP, CO, BU };
	preflopRanges = new HandRange[PLAYERS];
	betCalled = false;
	 
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

		  SBBetBD = new BigDecimal(1);
	  BBBetBD = new BigDecimal(2);
	  preflopBetMultiplyerOpenBD = new BigDecimal(3);; // Times BB + 1 each limp
	  preflopBetMultiplyer3BetBD = new BigDecimal(3);; // Times Open 2 Bet
	  preflopBetMultiplyer4BetBD = new BigDecimal(2.5); // Times 3 Bet
	  postFlopBbetMultiplierBD = new BigDecimal(.5); // Times pot size
	  buyinBD = new BigDecimal(200);
	  rebuyBD = new BigDecimal(100);
	  maxStackBD = new BigDecimal(500);
	 boardArray = new boolean[BOARD_ARRAY_SIZE]; // Board
	 boardArrayMax = -1;
	 
	 flopArray = new boolean[FLOP_SIZE];
 
	 drawArray = new boolean[DRAW_HANDS_SIZE];
 
	 madeArray = new boolean[MADE_HANDS_SIZE];
	 madeArrayMax = -1;
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
 et
	 seat;
	 street;
 
	 lastSeat;
	 lastStreet;
	
	 handStrength = -1;
	 rangeAdvantage = -1;
	 madeHand = -1;
	 bestDraw = -1;
	 bestPossible = -1;
	 outs = -1;
	 makeHandPer = -1;
	 flopTexture = -1;
	 turnTexture = -1;

	 boardPossibleArrayFlop = new boolean[POSSIBLE_DRAW_SIZE];
	 boardPossibleArrayTurn = new boolean[POSSIBLE_DRAW_SIZE];
	 boardPossibleArrayFlopMax = -1;
	 boardPossibleArrayTurnMax = -1;

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
 
	 flopIndex = -1;
	 boardArrayIndex = -1;
	 
	pair = false;
 
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
}