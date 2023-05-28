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
		type = "HeroX";
		createRanges();
		doConversion();
		action.show();
		type = "Hero";
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
		copyData();

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
		// PREFLOP_LIMP BET1
		rangeSBOpen.updateForHoleCardAction();
			rangeSBLimp.updateForHoleCardAction();
			// PREFLOP_OPEN BET2
		rangeSBBet3.updateForHoleCardAction();
		rangeSBCall.updateForHoleCardAction();
			// PREFLOP_BET3
		rangeSBBet4.updateForHoleCardAction();
			rangeSBBet4Call.updateForHoleCardAction();
			// PREFLOP_BET4
		rangeSBAllin.updateForHoleCardAction();
	
		rangeSBBet4Call.updateForHoleCardAction();
				// PREFLOP_ALLIN
		rangeSBAllinCall.updateForHoleCardAction();

		// PREFLOP_LIMP BET1
		rangeBBOpen.updateForHoleCardAction();
			rangeBBLimp.updateForHoleCardAction();
	
		// PREFLOP_OPEN BET2
		rangeBBBet3.updateForHoleCardAction();
			// PREFLOP_BET3
		rangeBBBet4.updateForHoleCardAction();
			rangeBBBet4Call.updateForHoleCardAction();
			// PREFLOP_BET4
		rangeBBAllin.updateForHoleCardAction();
			rangeBBBet4Call.updateForHoleCardAction();
		// PREFLOP_ALLIN
		rangeBBAllinCall.updateForHoleCardAction();
	
		// PREFLOP_LIMP BET1
		rangeUTGOpen.updateForHoleCardAction();
		rangeUTGLimp.updateForHoleCardAction();
		// PREFLOP_OPEN BET2
		rangeUTGBet3.updateForHoleCardAction();
			rangeUTGCall.updateForHoleCardAction();
			// PREFLOP_BET3
		rangeUTGBet4.updateForHoleCardAction();
			rangeUTGBet4Call.updateForHoleCardAction();
				// PREFLOP_BET4
		rangeUTGAllin.updateForHoleCardAction();
				rangeUTGBet4Call.updateForHoleCardAction();
			// PREFLOP_ALLIN
		rangeUTGAllinCall.updateForHoleCardAction();
		action.actionArray[UTG][4][1] = ndx2;

		// PREFLOP_LIMP BET1
		rangeMPOpen.updateForHoleCardAction();
		action.actionArray[MP][0][0] = ndx1;
		rangeMPLimp.updateForHoleCardAction();
		action.actionArray[MP][0][1] = ndx2;
		// PREFLOP_OPEN BET2
		rangeMPBet3.updateForHoleCardAction();
		action.actionArray[MP][1][0] = ndx1;
		rangeMPCall.updateForHoleCardAction();
		action.actionArray[MP][1][1] = ndx2;
		// PREFLOP_BET3
		rangeMPBet4.updateForHoleCardAction();
		action.actionArray[MP][2][0] = ndx1;
		rangeMPBet4Call.updateForHoleCardAction();
		action.actionArray[MP][2][1] = ndx2;
		// PREFLOP_BET4
		rangeMPAllin.updateForHoleCardAction();
		action.actionArray[MP][3][0] = ndx1;
		rangeMPBet4Call.updateForHoleCardAction();
		action.actionArray[MP][3][1] = ndx2;
		// PREFLOP_ALLIN
		rangeMPAllinCall.updateForHoleCardAction();
		action.actionArray[MP][4][1] = ndx2;

		// PREFLOP_LIMP BET1
		rangeCOOpen.updateForHoleCardAction();
		action.actionArray[CO][0][0] = ndx1;
		rangeCOLimp.updateForHoleCardAction();
		action.actionArray[CO][0][1] = ndx2;
		// PREFLOP_OPEN BET2
		rangeCOBet3.updateForHoleCardAction();
		action.actionArray[CO][1][0] = ndx1;
		rangeCOCall.updateForHoleCardAction();
		action.actionArray[CO][1][1] = ndx2;
		// PREFLOP_BET3
		rangeCOBet4.updateForHoleCardAction();
		action.actionArray[CO][2][0] = ndx1;
		rangeCOBet4Call.updateForHoleCardAction();
		action.actionArray[CO][2][1] = ndx2;
		// PREFLOP_BET4
		rangeCOAllin.updateForHoleCardAction();
		action.actionArray[CO][3][0] = ndx1;
		rangeCOBet4Call.updateForHoleCardAction();
		action.actionArray[CO][3][1] = ndx2;
		// PREFLOP_ALLIN
		rangeCOAllinCall.updateForHoleCardAction();
		action.actionArray[CO][4][1] = ndx2;

		// PREFLOP_LIMP BET1
		rangeBUOpen.updateForHoleCardAction();
		action.actionArray[BU][0][0] = ndx1;
		rangeBULimp.updateForHoleCardAction();
		action.actionArray[BU][0][1] = ndx2;
		// PREFLOP_OPEN BET2
		rangeBUBet3.updateForHoleCardAction();
		action.actionArray[BU][1][0] = ndx1;
		rangeBUCall.updateForHoleCardAction();
		action.actionArray[BU][1][1] = ndx2;
		// PREFLOP_BET3
		rangeBUBet4.updateForHoleCardAction();
		action.actionArray[BU][2][0] = ndx1;
		rangeBUBet4Call.updateForHoleCardAction();
		action.actionArray[BU][2][1] = ndx2;
		// PREFLOP_BET4
		rangeBUAllin.updateForHoleCardAction();
		action.actionArray[BU][3][0] = ndx1;
		rangeBUBet4Call.updateForHoleCardAction();
		action.actionArray[BU][3][1] = ndx2;
		// PREFLOP_ALLIN
		rangeBUAllinCall.updateForHoleCardAction();
		action.actionArray[BU][4][1] = ndx2;
	}

	// Copy data from HandRange
	private static void copyData() {
		// ********************
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[SB][i] = rangeSBLimp.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBLimp.gapsArray[i];
		}
		action.cards[SB] = rangeSBLimp.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBLimp.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[SB] = rangeSBLimp.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBLimp.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBLimp.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[SB][i] = rangeSBOpen.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBOpen.gapsArray[i];
		}
		action.cards[SB] = rangeSBOpen.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBOpen.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[SB] = rangeSBOpen.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBOpen.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBOpen.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[SB][i] = rangeSBCall.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBCall.gapsArray[i];
		}
		action.cards[SB] = rangeSBCall.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[SB] = rangeSBCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBCall.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBCall.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[SB][i] = rangeSBBet3.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBBet3.gapsArray[i];
		}
		action.cards[SB] = rangeSBBet3.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBBet3.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[SB] = rangeSBBet3.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBBet3.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBBet3.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[SB][i] = rangeSBBet3Call.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBBet3Call.gapsArray[i];
		}
		action.cards[SB] = rangeSBBet3Call.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBBet3Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[SB] = rangeSBBet3Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBBet3Call.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBBet3Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[SB][i] = rangeSBBet4.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBBet4.gapsArray[i];
		}
		action.cards[SB] = rangeSBBet4.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBBet4.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[SB] = rangeSBBet4.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBBet4.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBBet4.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[SB][i] = rangeSBBet4Call.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBBet4Call.gapsArray[i];
		}
		action.cards[SB] = rangeSBBet4Call.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBBet4Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[SB] = rangeSBBet4Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBBet4Call.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBBet4Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[SB][i] = rangeSBAllin.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBAllin.gapsArray[i];
		}
		action.cards[SB] = rangeSBAllin.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBAllin.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[SB] = rangeSBAllin.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBAllin.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBAllin.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[SB][i] = rangeSBAllinCall.rangeArray[i];
			action.gapsArray[SB][i] = rangeSBAllinCall.gapsArray[i];
		}
		action.cards[SB] = rangeSBAllinCall.cards;
		action.lowPlayabilitrhyArrayIndex[SB] = rangeSBAllinCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[SB] = rangeSBAllinCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[SB] = rangeSBAllinCall.lowPlayabilityArrayNumericValue;
		action.gapCount[SB] = rangeSBAllinCall.gapCount;

		// **************
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BB][i] = rangeBBLimp.rangeArray[i];
			action.gapsArray[BB][i] = rangeBBLimp.gapsArray[i];
		}
		action.cards[BB] = rangeBBLimp.cards;
		action.lowPlayabilitrhyArrayIndex[BB] = rangeBBLimp.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BB] = rangeBBLimp.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BB] = rangeBBLimp.lowPlayabilityArrayNumericValue;
		action.gapCount[BB] = rangeBBLimp.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BB][i] = rangeBBOpen.rangeArray[i];
			action.gapsArray[BB][i] = rangeBBOpen.gapsArray[i];
		}
		action.cards[BB] = rangeBBOpen.cards;
		action.lowPlayabilitrhyArrayIndex[BB] = rangeBBOpen.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BB] = rangeBBOpen.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BB] = rangeBBOpen.lowPlayabilityArrayNumericValue;
		action.gapCount[BB] = rangeBBOpen.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BB][i] = rangeBBCall.rangeArray[i];
			action.gapsArray[BB][i] = rangeBBCall.gapsArray[i];
		}
		action.cards[BB] = rangeBBCall.cards;
		action.lowPlayabilitrhyArrayIndex[BB] = rangeBBCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BB] = rangeBBCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BB] = rangeBBCall.lowPlayabilityArrayNumericValue;
		action.gapCount[BB] = rangeBBCall.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BB][i] = rangeBBBet3.rangeArray[i];
			action.gapsArray[BB][i] = rangeBBBet3.gapsArray[i];
		}
		action.cards[BB] = rangeBBBet3.cards;
		action.lowPlayabilitrhyArrayIndex[BB] = rangeBBBet3.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BB] = rangeBBBet3.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BB] = rangeBBBet3.lowPlayabilityArrayNumericValue;
		action.gapCount[BB] = rangeBBBet3.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BB][i] = rangeBBBet3Call.rangeArray[i];
			action.gapsArray[BB][i] = rangeBBBet3Call.gapsArray[i];
		}
		action.cards[BB] = rangeBBBet3Call.cards;
		action.lowPlayabilitrhyArrayIndex[BB] = rangeBBBet3Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BB] = rangeBBBet3Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BB] = rangeBBBet3Call.lowPlayabilityArrayNumericValue;
		action.gapCount[BB] = rangeBBBet3Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BB][i] = rangeBBBet4.rangeArray[i];
			action.gapsArray[BB][i] = rangeBBBet4.gapsArray[i];
		}
		action.cards[BB] = rangeBBBet4.cards;
		action.lowPlayabilitrhyArrayIndex[BB] = rangeBBBet4.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BB] = rangeBBBet4.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BB] = rangeBBBet4.lowPlayabilityArrayNumericValue;
		action.gapCount[BB] = rangeBBBet4.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BB][i] = rangeBBBet4Call.rangeArray[i];
			action.gapsArray[BB][i] = rangeBBBet4Call.gapsArray[i];
		}
		action.cards[BB] = rangeBBBet4Call.cards;
		action.lowPlayabilitrhyArrayIndex[BB] = rangeBBBet4Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BB] = rangeBBBet4Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BB] = rangeBBBet4Call.lowPlayabilityArrayNumericValue;
		action.gapCount[BB] = rangeBBBet4Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BB][i] = rangeBBAllin.rangeArray[i];
			action.gapsArray[BB][i] = rangeBBAllin.gapsArray[i];
		}
		action.cards[BB] = rangeBBAllin.cards;
		action.lowPlayabilitrhyArrayIndex[BB] = rangeBBAllin.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BB] = rangeBBAllin.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BB] = rangeBBAllin.lowPlayabilityArrayNumericValue;
		action.gapCount[BB] = rangeBBAllin.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BB][i] = rangeBBAllinCall.rangeArray[i];
			action.gapsArray[BB][i] = rangeBBAllinCall.gapsArray[i];
		}
		action.cards[BB] = rangeBBAllinCall.cards;
		action.lowPlayabilitrhyArrayIndex[BB] = rangeBBAllinCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BB] = rangeBBAllinCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BB] = rangeBBAllinCall.lowPlayabilityArrayNumericValue;
		action.gapCount[BB] = rangeBBAllinCall.gapCount;

		// ********************
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[UTG][i] = rangeUTGLimp.rangeArray[i];
			action.gapsArray[UTG][i] = rangeUTGLimp.gapsArray[i];
		}
		action.cards[UTG] = rangeUTGLimp.cards;
		action.lowPlayabilitrhyArrayIndex[UTG] = rangeUTGLimp.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[UTG] = rangeUTGLimp.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[UTG] = rangeUTGLimp.lowPlayabilityArrayNumericValue;
		action.gapCount[UTG] = rangeUTGLimp.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[UTG][i] = rangeUTGOpen.rangeArray[i];
			action.gapsArray[UTG][i] = rangeUTGOpen.gapsArray[i];
		}
		action.cards[UTG] = rangeUTGOpen.cards;
		action.lowPlayabilitrhyArrayIndex[UTG] = rangeUTGOpen.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[UTG] = rangeUTGOpen.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[UTG] = rangeUTGOpen.lowPlayabilityArrayNumericValue;
		action.gapCount[UTG] = rangeUTGOpen.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[UTG][i] = rangeUTGCall.rangeArray[i];
			action.gapsArray[UTG][i] = rangeUTGCall.gapsArray[i];
		}
		action.cards[UTG] = rangeUTGCall.cards;
		action.lowPlayabilitrhyArrayIndex[UTG] = rangeUTGCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[UTG] = rangeUTGCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[UTG] = rangeUTGCall.lowPlayabilityArrayNumericValue;
		action.gapCount[UTG] = rangeUTGCall.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[UTG][i] = rangeUTGBet3.rangeArray[i];
			action.gapsArray[UTG][i] = rangeUTGBet3.gapsArray[i];
		}
		action.cards[UTG] = rangeUTGBet3.cards;
		action.lowPlayabilitrhyArrayIndex[UTG] = rangeUTGBet3.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[UTG] = rangeUTGBet3.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[UTG] = rangeUTGBet3.lowPlayabilityArrayNumericValue;
		action.gapCount[UTG] = rangeUTGBet3.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[UTG][i] = rangeUTGBet3Call.rangeArray[i];
			action.gapsArray[UTG][i] = rangeUTGBet3Call.gapsArray[i];
		}
		action.cards[UTG] = rangeUTGBet3Call.cards;
		action.lowPlayabilitrhyArrayIndex[UTG] = rangeUTGBet3Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[UTG] = rangeUTGBet3Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[UTG] = rangeUTGBet3Call.lowPlayabilityArrayNumericValue;
		action.gapCount[UTG] = rangeUTGBet3Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[UTG][i] = rangeUTGBet4.rangeArray[i];
			action.gapsArray[UTG][i] = rangeUTGBet4.gapsArray[i];
		}
		action.cards[UTG] = rangeUTGBet4.cards;
		action.lowPlayabilitrhyArrayIndex[UTG] = rangeUTGBet4.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[UTG] = rangeUTGBet4.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[UTG] = rangeUTGBet4.lowPlayabilityArrayNumericValue;
		action.gapCount[UTG] = rangeUTGBet4.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[UTG][i] = rangeUTGBet4Call.rangeArray[i];
			action.gapsArray[UTG][i] = rangeUTGBet4Call.gapsArray[i];
		}
		action.cards[UTG] = rangeUTGBet4Call.cards;
		action.lowPlayabilitrhyArrayIndex[UTG] = rangeUTGBet4Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[UTG] = rangeUTGBet4Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[UTG] = rangeUTGBet4Call.lowPlayabilityArrayNumericValue;
		action.gapCount[UTG] = rangeUTGBet4Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[UTG][i] = rangeUTGAllin.rangeArray[i];
			action.gapsArray[UTG][i] = rangeUTGAllin.gapsArray[i];
		}
		action.cards[UTG] = rangeUTGAllin.cards;
		action.lowPlayabilitrhyArrayIndex[UTG] = rangeUTGAllin.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[UTG] = rangeUTGAllin.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[UTG] = rangeUTGAllin.lowPlayabilityArrayNumericValue;
		action.gapCount[UTG] = rangeUTGAllin.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[UTG][i] = rangeUTGAllinCall.rangeArray[i];
			action.gapsArray[UTG][i] = rangeUTGAllinCall.gapsArray[i];
		}
		action.cards[UTG] = rangeUTGAllinCall.cards;
		action.lowPlayabilitrhyArrayIndex[UTG] = rangeUTGAllinCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[UTG] = rangeUTGAllinCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[UTG] = rangeUTGAllinCall.lowPlayabilityArrayNumericValue;
		action.gapCount[UTG] = rangeUTGAllinCall.gapCount;

		// ********************
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[MP][i] = rangeMPLimp.rangeArray[i];
			action.gapsArray[MP][i] = rangeMPLimp.gapsArray[i];
		}
		action.cards[MP] = rangeMPLimp.cards;
		action.lowPlayabilitrhyArrayIndex[MP] = rangeMPLimp.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[MP] = rangeMPLimp.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[MP] = rangeMPLimp.lowPlayabilityArrayNumericValue;
		action.gapCount[MP] = rangeMPLimp.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[MP][i] = rangeMPOpen.rangeArray[i];
			action.gapsArray[MP][i] = rangeMPOpen.gapsArray[i];
		}
		action.cards[MP] = rangeMPOpen.cards;
		action.lowPlayabilitrhyArrayIndex[MP] = rangeMPOpen.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[MP] = rangeMPOpen.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[MP] = rangeMPOpen.lowPlayabilityArrayNumericValue;
		action.gapCount[MP] = rangeMPOpen.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[MP][i] = rangeMPCall.rangeArray[i];
			action.gapsArray[MP][i] = rangeMPCall.gapsArray[i];
		}
		action.cards[MP] = rangeMPCall.cards;
		action.lowPlayabilitrhyArrayIndex[MP] = rangeMPCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[MP] = rangeMPCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[MP] = rangeMPCall.lowPlayabilityArrayNumericValue;
		action.gapCount[MP] = rangeMPCall.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[MP][i] = rangeMPBet3.rangeArray[i];
			action.gapsArray[MP][i] = rangeMPBet3.gapsArray[i];
		}
		action.cards[MP] = rangeMPBet3.cards;
		action.lowPlayabilitrhyArrayIndex[MP] = rangeMPBet3.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[MP] = rangeMPBet3.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[MP] = rangeMPBet3.lowPlayabilityArrayNumericValue;
		action.gapCount[MP] = rangeMPBet3.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[MP][i] = rangeMPBet3Call.rangeArray[i];
			action.gapsArray[MP][i] = rangeMPBet3Call.gapsArray[i];
		}
		action.cards[MP] = rangeMPBet3Call.cards;
		action.lowPlayabilitrhyArrayIndex[MP] = rangeMPBet3Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[MP] = rangeMPBet3Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[MP] = rangeMPBet3Call.lowPlayabilityArrayNumericValue;
		action.gapCount[MP] = rangeMPBet3Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[MP][i] = rangeMPBet4.rangeArray[i];
			action.gapsArray[MP][i] = rangeMPBet4.gapsArray[i];
		}
		action.cards[MP] = rangeMPBet4.cards;
		action.lowPlayabilitrhyArrayIndex[MP] = rangeMPBet4.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[MP] = rangeMPBet4.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[MP] = rangeMPBet4.lowPlayabilityArrayNumericValue;
		action.gapCount[MP] = rangeMPBet4.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[MP][i] = rangeMPBet4Call.rangeArray[i];
			action.gapsArray[MP][i] = rangeMPBet4Call.gapsArray[i];
		}
		action.cards[MP] = rangeMPBet4Call.cards;
		action.lowPlayabilitrhyArrayIndex[MP] = rangeMPBet4Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[MP] = rangeMPBet4Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[MP] = rangeMPBet4Call.lowPlayabilityArrayNumericValue;
		action.gapCount[MP] = rangeMPBet4Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[MP][i] = rangeMPAllin.rangeArray[i];
			action.gapsArray[MP][i] = rangeMPAllin.gapsArray[i];
		}
		action.cards[MP] = rangeMPAllin.cards;
		action.lowPlayabilitrhyArrayIndex[MP] = rangeMPAllin.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[MP] = rangeMPAllin.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[MP] = rangeMPAllin.lowPlayabilityArrayNumericValue;
		action.gapCount[MP] = rangeMPAllin.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[MP][i] = rangeMPAllinCall.rangeArray[i];
			action.gapsArray[MP][i] = rangeMPAllinCall.gapsArray[i];
		}
		action.cards[MP] = rangeMPAllinCall.cards;
		action.lowPlayabilitrhyArrayIndex[MP] = rangeMPAllinCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[MP] = rangeMPAllinCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[MP] = rangeMPAllinCall.lowPlayabilityArrayNumericValue;
		action.gapCount[MP] = rangeMPAllinCall.gapCount;

		// ********************
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[CO][i] = rangeCOLimp.rangeArray[i];
			action.gapsArray[CO][i] = rangeCOLimp.gapsArray[i];
		}
		action.cards[CO] = rangeCOLimp.cards;
		action.lowPlayabilitrhyArrayIndex[CO] = rangeCOLimp.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[CO] = rangeCOLimp.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[CO] = rangeCOLimp.lowPlayabilityArrayNumericValue;
		action.gapCount[CO] = rangeCOLimp.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[CO][i] = rangeCOOpen.rangeArray[i];
			action.gapsArray[CO][i] = rangeCOOpen.gapsArray[i];
		}
		action.cards[CO] = rangeCOOpen.cards;
		action.lowPlayabilitrhyArrayIndex[CO] = rangeCOOpen.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[CO] = rangeCOOpen.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[CO] = rangeCOOpen.lowPlayabilityArrayNumericValue;
		action.gapCount[CO] = rangeCOOpen.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[CO][i] = rangeCOCall.rangeArray[i];
			action.gapsArray[CO][i] = rangeCOCall.gapsArray[i];
		}
		action.cards[CO] = rangeCOCall.cards;
		action.lowPlayabilitrhyArrayIndex[CO] = rangeCOCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[CO] = rangeCOCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[CO] = rangeCOCall.lowPlayabilityArrayNumericValue;
		action.gapCount[CO] = rangeCOCall.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[CO][i] = rangeCOBet3.rangeArray[i];
			action.gapsArray[CO][i] = rangeCOBet3.gapsArray[i];
		}
		action.cards[CO] = rangeCOBet3.cards;
		action.lowPlayabilitrhyArrayIndex[CO] = rangeCOBet3.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[CO] = rangeCOBet3.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[CO] = rangeCOBet3.lowPlayabilityArrayNumericValue;
		action.gapCount[CO] = rangeCOBet3.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[CO][i] = rangeCOBet3Call.rangeArray[i];
			action.gapsArray[CO][i] = rangeCOBet3Call.gapsArray[i];
		}
		action.cards[CO] = rangeCOBet3Call.cards;
		action.lowPlayabilitrhyArrayIndex[CO] = rangeCOBet3Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[CO] = rangeCOBet3Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[CO] = rangeCOBet3Call.lowPlayabilityArrayNumericValue;
		action.gapCount[CO] = rangeCOBet3Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[CO][i] = rangeCOBet4.rangeArray[i];
			action.gapsArray[CO][i] = rangeCOBet4.gapsArray[i];
		}
		action.cards[CO] = rangeCOBet4.cards;
		action.lowPlayabilitrhyArrayIndex[CO] = rangeCOBet4.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[CO] = rangeCOBet4.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[CO] = rangeCOBet4.lowPlayabilityArrayNumericValue;
		action.gapCount[CO] = rangeCOBet4.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[CO][i] = rangeCOBet4Call.rangeArray[i];
			action.gapsArray[CO][i] = rangeCOBet4Call.gapsArray[i];
		}
		action.cards[CO] = rangeCOBet4Call.cards;
		action.lowPlayabilitrhyArrayIndex[CO] = rangeCOBet4Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[CO] = rangeCOBet4Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[CO] = rangeCOBet4Call.lowPlayabilityArrayNumericValue;
		action.gapCount[CO] = rangeCOBet4Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[CO][i] = rangeCOAllin.rangeArray[i];
			action.gapsArray[CO][i] = rangeCOAllin.gapsArray[i];
		}
		action.cards[CO] = rangeCOAllin.cards;
		action.lowPlayabilitrhyArrayIndex[CO] = rangeCOAllin.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[CO] = rangeCOAllin.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[CO] = rangeCOAllin.lowPlayabilityArrayNumericValue;
		action.gapCount[CO] = rangeCOAllin.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[CO][i] = rangeCOAllinCall.rangeArray[i];
			action.gapsArray[CO][i] = rangeCOAllinCall.gapsArray[i];
		}
		action.cards[CO] = rangeCOAllinCall.cards;
		action.lowPlayabilitrhyArrayIndex[CO] = rangeCOAllinCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[CO] = rangeCOAllinCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[CO] = rangeCOAllinCall.lowPlayabilityArrayNumericValue;
		action.gapCount[CO] = rangeCOAllinCall.gapCount;

		// ********************
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BU][i] = rangeBULimp.rangeArray[i];
			action.gapsArray[BU][i] = rangeBULimp.gapsArray[i];
		}
		action.cards[BU] = rangeBULimp.cards;
		action.lowPlayabilitrhyArrayIndex[BU] = rangeBULimp.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BU] = rangeBULimp.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BU] = rangeBULimp.lowPlayabilityArrayNumericValue;
		action.gapCount[BU] = rangeBULimp.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BU][i] = rangeBUOpen.rangeArray[i];
			action.gapsArray[BU][i] = rangeBUOpen.gapsArray[i];
		}
		action.cards[BU] = rangeBUOpen.cards;
		action.lowPlayabilitrhyArrayIndex[BU] = rangeBUOpen.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BU] = rangeBUOpen.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BU] = rangeBUOpen.lowPlayabilityArrayNumericValue;
		action.gapCount[BU] = rangeBUOpen.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BU][i] = rangeBUCall.rangeArray[i];
			action.gapsArray[BU][i] = rangeBUCall.gapsArray[i];
		}
		action.cards[BU] = rangeBUCall.cards;
		action.lowPlayabilitrhyArrayIndex[BU] = rangeBUCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BU] = rangeBUCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BU] = rangeBUCall.lowPlayabilityArrayNumericValue;
		action.gapCount[BU] = rangeBUCall.gapCount;

		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BU][i] = rangeBUBet3.rangeArray[i];
			action.gapsArray[BU][i] = rangeBUBet3.gapsArray[i];
		}
		action.cards[BU] = rangeBUBet3.cards;
		action.lowPlayabilitrhyArrayIndex[BU] = rangeBUBet3.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BU] = rangeBUBet3.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BU] = rangeBUBet3.lowPlayabilityArrayNumericValue;
		action.gapCount[BU] = rangeBUBet3.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BU][i] = rangeBUBet3Call.rangeArray[i];
			action.gapsArray[BU][i] = rangeBUBet3Call.gapsArray[i];
		}
		action.cards[BU] = rangeBUBet3Call.cards;
		action.lowPlayabilitrhyArrayIndex[BU] = rangeBUBet3Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BU] = rangeBUBet3Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BU] = rangeBUBet3Call.lowPlayabilityArrayNumericValue;
		action.gapCount[BU] = rangeBUBet3Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BU][i] = rangeBUBet4.rangeArray[i];
			action.gapsArray[BU][i] = rangeBUBet4.gapsArray[i];
		}
		action.cards[BU] = rangeBUBet4.cards;
		action.lowPlayabilitrhyArrayIndex[BU] = rangeBUBet4.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BU] = rangeBUBet4.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BU] = rangeBUBet4.lowPlayabilityArrayNumericValue;
		action.gapCount[BU] = rangeBUBet4.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BU][i] = rangeBUBet4Call.rangeArray[i];
			action.gapsArray[BU][i] = rangeBUBet4Call.gapsArray[i];
		}
		action.cards[BU] = rangeBUBet4Call.cards;
		action.lowPlayabilitrhyArrayIndex[BU] = rangeBUBet4Call.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BU] = rangeBUBet4Call.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BU] = rangeBUBet4Call.lowPlayabilityArrayNumericValue;
		action.gapCount[BU] = rangeBUBet4Call.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BU][i] = rangeBUAllin.rangeArray[i];
			action.gapsArray[BU][i] = rangeBUAllin.gapsArray[i];
		}
		action.cards[BU] = rangeBUAllin.cards;
		action.lowPlayabilitrhyArrayIndex[BU] = rangeBUAllin.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BU] = rangeBUAllin.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BU] = rangeBUAllin.lowPlayabilityArrayNumericValue;
		action.gapCount[BU] = rangeBUAllin.gapCount;
		for (int i = 0; i < HANDS; i++) {
			action.rangeArray[BU][i] = rangeBUAllinCall.rangeArray[i];
			action.gapsArray[BU][i] = rangeBUAllinCall.gapsArray[i];
		}
		action.cards[BU] = rangeBUAllinCall.cards;
		action.lowPlayabilitrhyArrayIndex[BU] = rangeBUAllinCall.lowPlayabilitrhyArrayIndex;
		action.lowRangeArrayIndex[BU] = rangeBUAllinCall.lowRangeArrayIndex;
		action.lowPlayabilityArrayNumericValue[BU] = rangeBUAllinCall.lowPlayabilityArrayNumericValue;
		action.gapCount[BU] = rangeBUAllinCall.gapCount;
	}
}
