//package evaluate_streets;
/*-  ******************************************************************************
 *  @author PEAK_ 
 * ****************************************************************************** */

public class HandRangeConverter implements Constants {

	private static final HandRange rangeSBLimp = new HandRange();
	private static final HandRange rangeSBOpen = new HandRange();
	private static final HandRange rangeSBCall = new HandRange();
	private static final HandRange rangeSBBet3 = new HandRange();
	private static final HandRange rangeSBBet3Call = new HandRange();
	private static final HandRange rangeSBBet4 = new HandRange();
	private static final HandRange rangeSBBet4Call = new HandRange();
	private static final HandRange rangeSBAllin = new HandRange();
	private static final HandRange rangeSBAllinCall = new HandRange();
	private static final HandRange rangeBBLimp = new HandRange();
	private static final HandRange rangeBBOpen = new HandRange();
	private static final HandRange rangeBBCall = new HandRange();
	private static final HandRange rangeBBBet3 = new HandRange();
	private static final HandRange rangeBBBet3Call = new HandRange();
	private static final HandRange rangeBBBet4 = new HandRange();
	private static final HandRange rangeBBBet4Call = new HandRange();
	private static final HandRange rangeBBAllin = new HandRange();
	private static final HandRange rangeBBAllinCall = new HandRange();
	private static final HandRange rangeUTGLimp = new HandRange();
	private static final HandRange rangeUTGOpen = new HandRange();
	private static final HandRange rangeUTGCall = new HandRange();
	private static final HandRange rangeUTGBet3 = new HandRange();
	private static final HandRange rangeUTGBet3Call = new HandRange();
	private static final HandRange rangeUTGBet4 = new HandRange();
	private static final HandRange rangeUTGBet4Call = new HandRange();
	private static final HandRange rangeUTGAllin = new HandRange();
	private static final HandRange rangeUTGAllinCall = new HandRange();
	private static final HandRange rangeMPLimp = new HandRange();
	private static final HandRange rangeMPOpen = new HandRange();
	private static final HandRange rangeMPCall = new HandRange();
	private static final HandRange rangeMPBet3 = new HandRange();
	private static final HandRange rangeMPBet3Call = new HandRange();
	private static final HandRange rangeMPBet4 = new HandRange();
	private static final HandRange rangeMPBet4Call = new HandRange();
	private static final HandRange rangeMPAllin = new HandRange();
	private static final HandRange rangeMPAllinCall = new HandRange();
	private static final HandRange rangeCOLimp = new HandRange();
	private static final HandRange rangeCOOpen = new HandRange();
	private static final HandRange rangeCOCall = new HandRange();
	private static final HandRange rangeCOBet3 = new HandRange();
	private static final HandRange rangeCOBet3Call = new HandRange();
	private static final HandRange rangeCOBet4 = new HandRange();
	private static final HandRange rangeCOBet4Call = new HandRange();
	private static final HandRange rangeCOAllin = new HandRange();
	private static final HandRange rangeCOAllinCall = new HandRange();
	private static final HandRange rangeBULimp = new HandRange();
	private static final HandRange rangeBUOpen = new HandRange();
	private static final HandRange rangeBUCall = new HandRange();
	private static final HandRange rangeBUBet3 = new HandRange();
	private static final HandRange rangeBUBet3Call = new HandRange();
	private static final HandRange rangeBUBet4 = new HandRange();
	private static final HandRange rangeBUBet4Call = new HandRange();
	private static final HandRange rangeBUAllin = new HandRange();
	private static final HandRange rangeBUAllinCall = new HandRange();

	private static HoleCardAction action = new HoleCardAction();
	private static String type = "?";

	public static void main(String[] s0) {
		type = "Hero";
		createRanges();
		doConversion();
		copyData();
		type = "HeroX";
		createRanges();
		doConversion();
		type = "AverageX";
		createRanges();
		doConversion();
		type = "Fish";
		createRanges();
		doConversion();
		type = "Average";
		createRanges();
		doConversion();
		type = "Nit";
		createRanges();
		doConversion();
		type = "Lag";
		createRanges();
		doConversion();
		type = "Tag";
		createRanges();
		doConversion();
		type = "Reg";
		createRanges();
		doConversion();
		type = "Looser";
		createRanges();
		doConversion();

		System.out.println("//HandRangeConversion complete "+ type + " " + action.actionArray[5][3][0]
				+ " " + action.actionArray[5][0][1]
				+ " " + action.actionArray[0][1][0]
				+ " " + action.actionArray[4][1][1]);
				

	}

	/*- **************************************************************************** 
		 * Initialize instances of Player for each seat.
		 * One time initialization
		 * Instances are customized for current position :
		 * 		SB, BB, UTG, MP, CO, BU
		 **************************************************************************** */
	private static void createRanges() {
		rangeSBLimp.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Limp.ser");
		rangeSBOpen.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Open.ser");
		rangeSBCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Call.ser");
		rangeSBBet3.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Bet3.ser");
		rangeSBBet3Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Bet3Call.ser");
		rangeSBBet4.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Bet4.ser");
		rangeSBBet4Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Bet4Call.ser");
		rangeSBAllin.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Allin.ser");
		rangeSBAllinCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\AllinCall.ser");
		
		rangeBBLimp.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Limp.ser");
		rangeBBOpen.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Open.ser");
		rangeBBCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Call.ser");
		rangeBBBet3.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Bet3.ser");
		rangeBBBet3Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Bet3Call.ser");
		rangeBBBet4.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Bet4.ser");
		rangeBBBet4Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Bet4Call.ser");
		rangeBBAllin.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Allin.ser");
		rangeBBAllinCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\AllinCall.ser");

		rangeUTGLimp.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Limp.ser");
		rangeUTGOpen.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Open.ser");
		rangeUTGCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Call.ser");
		rangeUTGBet3.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Bet3.ser");
		rangeUTGBet3Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Bet3Call.ser");
		rangeUTGBet4.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Bet4.ser");
		rangeUTGBet4Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Bet4Call.ser");
		rangeUTGAllin.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Allin.ser");
		rangeUTGAllinCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\AllinCall.ser");

		rangeMPLimp.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Limp.ser");
		rangeMPOpen.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Open.ser");
		rangeMPCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Call.ser");
		rangeMPBet3.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Bet3.ser");
		rangeMPBet3Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Bet3Call.ser");
		rangeMPBet4.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Bet4.ser");
		rangeMPBet4Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Bet4Call.ser");
		rangeMPAllin.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Allin.ser");
		rangeMPAllinCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\AllinCall.ser");

		rangeCOLimp.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Limp.ser");
		rangeCOOpen.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Open.ser");
		rangeCOCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Call.ser");
		rangeCOBet3.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Bet3.ser");
		rangeCOBet3Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Bet3Call.ser");
		rangeCOBet4.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Bet4.ser");
		rangeCOBet4Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Bet4Call.ser");
		rangeCOAllin.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Allin.ser");
		rangeCOAllinCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\AllinCall.ser");

		rangeBULimp.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Limp.ser");
		rangeBUOpen.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Open.ser");
		rangeBUCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Call.ser");
		rangeBUBet3.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Bet3.ser");
		rangeBUBet3Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Bet3Call.ser");
		rangeBUBet4.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Bet4.ser");
		rangeBUBet4Call.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Bet4Call.ser");
		rangeBUAllin.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Allin.ser");
		rangeBUAllinCall.readRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\AllinCall.ser");
		}

	// Do conversion
	private static void doConversion() {
		int ndx1 = 0;
		int ndx2 = 0;

		// PREFLOP_LIMP BET1
		ndx1 = rangeSBOpen.findLast();
		action.actionArray[SB][0][0] = ndx1;
		ndx2 = rangeSBLimp.findLast();
		action.actionArray[SB][0][1] = ndx2;
		// PREFLOP_OPEN BET2
		ndx1 = rangeSBBet3.findLast();
		action.actionArray[SB][1][0] = ndx1;
		ndx2 = rangeSBCall.findLast();
		action.actionArray[SB][1][1] = ndx2;
		// PREFLOP_BET3
		ndx1 = rangeSBBet4.findLast();
		action.actionArray[SB][2][0] = ndx1;
		ndx2 = rangeSBBet4Call.findLast();
		action.actionArray[SB][2][1] = ndx2;
		// PREFLOP_BET4
		ndx1 = rangeSBAllin.findLast();
		action.actionArray[SB][3][0] = ndx1;
		ndx2 = rangeSBBet4Call.findLast();
		action.actionArray[SB][3][1] = ndx2;
		// PREFLOP_ALLIN
		ndx2 = rangeSBAllinCall.findLast();
		action.actionArray[SB][4][1] = ndx2;

		// PREFLOP_LIMP BET1
		ndx1 = rangeBBOpen.findLast();
		action.actionArray[BB][0][0] = ndx1;
		ndx2 = rangeBBLimp.findLast();
		action.actionArray[BB][0][1] = ndx2;
		// PREFLOP_OPEN BET2
		ndx1 = rangeBBBet3.findLast();
		action.actionArray[BB][1][0] = ndx1;
		ndx2 = rangeBBCall.findLast();
		action.actionArray[BB][1][1] = ndx2;
		// PREFLOP_BET3
		ndx1 = rangeBBBet4.findLast();
		action.actionArray[BB][2][0] = ndx1;
		ndx2 = rangeBBBet4Call.findLast();
		action.actionArray[BB][2][1] = ndx2;
		// PREFLOP_BET4
		ndx1 = rangeBBAllin.findLast();
		action.actionArray[BB][3][0] = ndx1;
		ndx2 = rangeBBBet4Call.findLast();
		action.actionArray[BB][3][1] = ndx2;
		// PREFLOP_ALLIN
		ndx2 = rangeBBAllinCall.findLast();
		action.actionArray[BB][4][1] = ndx2;

		// PREFLOP_LIMP BET1
		ndx1 = rangeUTGOpen.findLast();
		action.actionArray[UTG][0][0] = ndx1;
		ndx2 = rangeUTGLimp.findLast();
		action.actionArray[UTG][0][1] = ndx2;
		// PREFLOP_OPEN BET2
		ndx1 = rangeUTGBet3.findLast();
		action.actionArray[UTG][1][0] = ndx1;
		ndx2 = rangeUTGCall.findLast();
		action.actionArray[UTG][1][1] = ndx2;
		// PREFLOP_BET3
		ndx1 = rangeUTGBet4.findLast();
		action.actionArray[UTG][2][0] = ndx1;
		ndx2 = rangeUTGBet4Call.findLast();
		action.actionArray[UTG][2][1] = ndx2;
		// PREFLOP_BET4
		ndx1 = rangeUTGAllin.findLast();
		action.actionArray[UTG][3][0] = ndx1;
		ndx2 = rangeUTGBet4Call.findLast();
		action.actionArray[UTG][3][1] = ndx2;
		// PREFLOP_ALLIN
		ndx2 = rangeUTGAllinCall.findLast();
		action.actionArray[UTG][4][1] = ndx2;

		// PREFLOP_LIMP BET1
		ndx1 = rangeMPOpen.findLast();
		action.actionArray[MP][0][0] = ndx1;
		ndx2 = rangeMPLimp.findLast();
		action.actionArray[MP][0][1] = ndx2;
		// PREFLOP_OPEN BET2
		ndx1 = rangeMPBet3.findLast();
		action.actionArray[MP][1][0] = ndx1;
		ndx2 = rangeMPCall.findLast();
		action.actionArray[MP][1][1] = ndx2;
		// PREFLOP_BET3
		ndx1 = rangeMPBet4.findLast();
		action.actionArray[MP][2][0] = ndx1;
		ndx2 = rangeMPBet4Call.findLast();
		action.actionArray[MP][2][1] = ndx2;
		// PREFLOP_BET4
		ndx1 = rangeMPAllin.findLast();
		action.actionArray[MP][3][0] = ndx1;
		ndx2 = rangeMPBet4Call.findLast();
		action.actionArray[MP][3][1] = ndx2;
		// PREFLOP_ALLIN
		ndx2 = rangeMPAllinCall.findLast();
		action.actionArray[MP][4][1] = ndx2;

		// PREFLOP_LIMP BET1
		ndx1 = rangeCOOpen.findLast();
		action.actionArray[CO][0][0] = ndx1;
		ndx2 = rangeCOLimp.findLast();
		action.actionArray[CO][0][1] = ndx2;
		// PREFLOP_OPEN BET2
		ndx1 = rangeCOBet3.findLast();
		action.actionArray[CO][1][0] = ndx1;
		ndx2 = rangeCOCall.findLast();
		action.actionArray[CO][1][1] = ndx2;
		// PREFLOP_BET3
		ndx1 = rangeCOBet4.findLast();
		action.actionArray[CO][2][0] = ndx1;
		ndx2 = rangeCOBet4Call.findLast();
		action.actionArray[CO][2][1] = ndx2;
		// PREFLOP_BET4
		ndx1 = rangeCOAllin.findLast();
		action.actionArray[CO][3][0] = ndx1;
		ndx2 = rangeCOBet4Call.findLast();
		action.actionArray[CO][3][1] = ndx2;
		// PREFLOP_ALLIN
		ndx2 = rangeCOAllinCall.findLast();
		action.actionArray[CO][4][1] = ndx2;

		// PREFLOP_LIMP BET1
		ndx1 = rangeBUOpen.findLast();
		action.actionArray[BU][0][0] = ndx1;
		ndx2 = rangeBULimp.findLast();
		action.actionArray[BU][0][1] = ndx2;
		// PREFLOP_OPEN BET2
		ndx1 = rangeBUBet3.findLast();
		action.actionArray[BU][1][0] = ndx1;
		ndx2 = rangeBUCall.findLast();
		action.actionArray[BU][1][1] = ndx2;
		// PREFLOP_BET3
		ndx1 = rangeBUBet4.findLast();
		action.actionArray[BU][2][0] = ndx1;
		ndx2 = rangeBUBet4Call.findLast();
		action.actionArray[BU][2][1] = ndx2;
		// PREFLOP_BET4
		ndx1 = rangeBUAllin.findLast();
		action.actionArray[BU][3][0] = ndx1;
		ndx2 = rangeBUBet4Call.findLast();
		action.actionArray[BU][3][1] = ndx2;
		// PREFLOP_ALLIN
		ndx2 = rangeBUAllinCall.findLast();
		action.actionArray[BU][4][1] = ndx2;
		System.out.println("//Conversion complete "+ type + " " + action.actionArray[5][0][0]
		+ " " + action.actionArray[5][0][1]
		+ " " + action.actionArray[5][1][0]
		+ " " + action.actionArray[4][1][1]);
		action.writeArray("C:\\ApplicationData\\" + type + ".ser");
	}

	// Copy data from HandRange
	private static void copyData() {
		for (int i = 0; i < HANDS; i++){
			action.rangeArray[SB][i] = rangeSBLimp.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBLimp.gapsArray[i];
		}
		action.cards[SB] = rangeSBLimp.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBLimp.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex [SB] = rangeSBLimp.lowRangeArrayIndex ;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBLimp.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBLimp.gapCount;

		for (int i = 0; i < HANDS; i++){
			action.rangeArray[SB][i] = rangeSBOpen.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBOpen.gapsArray[i];
		}
		action.cards[SB] = rangeSBOpen.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBOpen.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex [SB] = rangeSBOpen.lowRangeArrayIndex ;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBOpen.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBOpen.gapCount;
		for (int i = 0; i < HANDS; i++){
			action.rangeArray[SB][i] = rangeSBCall.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBCall.gapsArray[i];
		}
		action.cards[SB] = rangeSBCall.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex [SB] = rangeSBCall.lowRangeArrayIndex ;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBCall.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBCall.gapCount;

		for (int i = 0; i < HANDS; i++){
			action.rangeArray[SB][i] = rangeSBBet3.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBBet3.gapsArray[i];
		}
		action.cards[SB] = rangeSBBet3.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBBet3.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex [SB] = rangeSBBet3.lowRangeArrayIndex ;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBBet3.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBBet3.gapCount;
		for (int i = 0; i < HANDS; i++){
			action.rangeArray[SB][i] = rangeSBCallBet3.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBCallBet3.gapsArray[i];
		}
		action.cards[SB] = rangeSBCallBet3.cards;
		action.lowPlayabilitrhyArrayIndex=[SB] = rangeSBCallBet3.lowPlayabilitrhyArrayIndex=;
		action.lowRangeArrayIndex [SB] = rangeSBCallBet3.lowRangeArrayIndex ;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBCallBet3.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBCallBet3.gapCount;
		for (int i = 0; i < HANDS; i++){
			action.rangeArray[SB][i] = rangeSBBet4.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBBet4.gapsArray[i];
		}
		action.cards[SB] = rangeSBBet4.cards;
		action.lowPlayabilitrhyArrayIndex=[SB] = rangeSBBet4.lowPlayabilitrhyArrayIndex=;
		action.lowRangeArrayIndex [SB] = rangeSBBet4.lowRangeArrayIndex ;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBBet4.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBBet4.gapCount;
		for (int i = 0; i < HANDS; i++){
			action.rangeArray[SB][i] = rangeSBCallBet4.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBCallBet4.gapsArray[i];
		}
		action.cards[SB] = rangeSBCallBet4.cards;
		action.lowPlayabilitrhyArrayIndex=[SB] = rangeSBCallBet4.lowPlayabilitrhyArrayIndex=;
		action.lowRangeArrayIndex [SB] = rangeSBCallBet4.lowRangeArrayIndex ;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBCallBet4.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBCallBet4.gapCount;
		for (int i = 0; i < HANDS; i++){
			action.rangeArray[SB][i] = rangeSBAllin.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBAllin.gapsArray[i];
		}
		action.cards[SB] = rangeSBAllin.cards;
		action.lowPlayabilitrhyArrayIndex=[SB] = rangeSBAllin.lowPlayabilitrhyArrayIndex=;
		action.lowRangeArrayIndex [SB] = rangeSBAllin.lowRangeArrayIndex ;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBAllin.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBAllin.gapCount;
		for (int i = 0; i < HANDS; i++){
			action.rangeArray[SB][i] = rangeSBCallAllin.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBCallAllin.gapsArray[i];
		}
		action.cards[SB] = rangeSBCallAllin.cards;
		action.lowPlayabilitrhyArrayIndex=[SB] = rangeSBCallAllin.lowPlayabilitrhyArrayIndex=;
		action.lowRangeArrayIndex [SB] = rangeSBCallAllin.lowRangeArrayIndex ;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBCallAllin.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBCallAllin.gapCount;

		rangeSBOpen.
		rangeSBCall.
		rangeSBBet3.
		rangeSBBet3Call.
		rangeSBBet4.
		rangeSBBet4Call
		rangeSBAllin.
		rangeSBAllinCall.

		int[][][] actionArray = new int[PLAYERS][5][2];
		// Copied from HandRange used to construct this instance
		int[][] rangeArray = new int[PLAYERS][HANDS];
		int[][] gapsArray = new int[PLAYERS][HANDS];
		String[] cardsArray = new String[PLAYERS];
		int[] lowPlayabilitrhyArrayIndex= new int[PLAYERS];
		int[] lowRangeArrayIndex = new int[PLAYERS];
		int[] lowPlayabilityArrayNumericValue = new int[PLAYERS];
		int[] gapCount = new int[PLAYERS];


}
