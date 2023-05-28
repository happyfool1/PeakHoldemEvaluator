//package evaluate_streets;

/*- ******************************************************************************
 * 
 * @author PEAK_
*******************************************************************************/
public class ManyData implements Constants {
	/*-  ******************************************************************************
	 * Updated foe each hand played
	 * If corresponding array in OneHand element is true then array element here 
	 * is incremented by 1
	 ****************************************************************************** */
	// Flop board analysis - hole cards not included
	static int[] boardArrayFlop = new int[3];
	static int[] boardArrayTurn = new int[4];
	static int[] boardArrayRiver = new int[5];
	static int boardArrayFlopCount = 0;
	static int boardArrayTurnCount = 0;
	static int boardArrayRiverCount = 0;

	static int[] flopArray = new int[FLOP_MAX];

	static int[] drawArrayFlop = new int[DRAW_MAX_HANDS];
	static int[] drawArrayTurn = new int[DRAW_MAX_HANDS];

	static int[] madeArrayFlop = new int[MADE_MAX_HANDS];
	static int[] madeArrayTurn = new int[MADE_MAX_HANDS];
	static int[] madeArrayRiver = new int[MADE_MAX_HANDS];

	// Board possible hands on the Flop
	static int[] boardPossibleArrayFlop = new int[POSSIBLE_MAX];
	static int[] boardPossibleArrayTurn = new int[POSSIBLE_MAX];

	static int flopArraysCount = 0;
	static int turnArraysCount = 0;
	static int riverArraysCount = 0;

	static int[] madeHandArray = new int[MADE_MAX_HANDS];
	static int[] bestDrawArray = new int[BEST_MAX_HANDS];
	static int[] possibleArray = new int[POSSIBLE_MAX];

	static int[] sumOfHandValuesFlopArray = new int[65];
	static int[] sumOfBoardValuesFlopArray = new int[39];
	static int[] sumOfHoleCardValuesArray = new int[26];

	static int[] flopSumOfDigitsArray = new int[39];

	static int[] hmlArrayFlop = new int[HML_SIZE_FLOP];
	static int[] hmlArrayTurn = new int[HML_SIZE_TURN];
	static int[] hmlArrayRiver = new int[HML_SIZE_RIVER];

	static int[] typeArray = new int[4];

	static int[] flopTypeOf1755Array = new int[1755];

	// Showdown
	static int showdownCount = 0;
	static int[] winningHand = new int[99]; // ?? TODO

	/*-  ******************************************************************************
	 * Calculated values percentage
	 ****************************************************************************** */
	// Flop board analysis - hole cards not included
	static double[] boardArrayFlopPer = new double[3];
	static double[] boardArrayTurnPer = new double[4];
	static double[] boardArrayRiverPer = new double[5];

	// Board possible hands on the Flop
	static double[] boardPossibleArrayFlopPer = new double[POSSIBLE_MAX];
	static double[] boardPossibleArrayTurnPer = new double[POSSIBLE_MAX];

	static double[] hmlArrayFlopPer = new double[HML_SIZE_FLOP];
	static double[] hmlArrayTurnPer = new double[HML_SIZE_TURN];
	static double[] hmlArrayRiverPer = new double[HML_SIZE_RIVER];


	static double[] typeArrayPer = new double[4];

	static double[] flopTypeOf1755ArrayPer = new double[1755];

	// Showdown
	static double[] winningHandPer = new double[99]; // ?? TODO

	/*-
	* Used to calculate EV for hands on a street Values of hands by seat and type
	* of hand, and Win or Loose index from Evaluate for Flop Turn and River.
	* +1 because of Showdown
	*/
	static int[][] evaluateIndex = new int[STREET + 1][PLAYERS];

	static int[][][] evaluateIndexPos = new int[STREET + 1][PLAYERS][POSITIONS];



	/*-  ******************************************************************************
	 * Evaluation arrays
	 ****************************************************************************** */
	static int[] hmlWonAtShowdown = new int[HML_SIZE_FLOP];

	static int[][] hmlDraw = new int[HML_SIZE_FLOP][_MAX_HANDS];
	static int[] hmlDrawBoardPair= new int[HML_SIZE_FLOP];
	static int[] hmlDrawPairedWithBoard = new int[HML_SIZE_FLOP];
 	static int[] hmlDrawGutshot = new int[HML_SIZE_FLOP];
	static int[] hmlDrawStraight = new int[HML_SIZE_FLOP];
	static int[] hmlDrawOESD = new int[HML_SIZE_FLOP];
	static int[] hmlDrawFlush= new int[HML_SIZE_FLOP];
	static int[] hmlDrawFlushOESD = new int[HML_SIZE_FLOP];

	static int[] hmlMadeNone = new int[HML_SIZE_FLOP];
	static int[] hmlMadePair= new int[HML_SIZE_FLOP];
	static int[] hmlMadeTwoPair = new int[HML_SIZE_FLOP];
	static int[] hmlMadeSet = new int[HML_SIZE_FLOP];
	static int[] hmlMadeStraight= new int[HML_SIZE_FLOP];
	static int[] hmlMadeFlush= new int[HML_SIZE_FLOP];
	static int[] hmlMadeFullHouse = new int[HML_SIZE_FLOP];
	static int[] hmlMadeFourOfAKind = new int[HML_SIZE_FLOP];
	static int[] hmlMadeStraightFlush= new int[HML_SIZE_FLOP];
	static int[] hmlMadeRoyalFlush= new int[HML_SIZE_FLOP];


	



	private ManyData() {
		throw new IllegalStateException("Utility class");
	}
}
