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
	//	rangeBUOpen.debug = true;
	//	rangeBULimp.debug = true;
		doConversion();
	//	rangeBULimp.debug = false;
	//	rangeBUOpen.debug = false;
	
		type = "Test";
		testRanges();
		createRanges();
		rangeBULimp.debug = true;
		doConversion();
		rangeBULimp.show();
		
		doConversion();

		rangeBULimp.debug = false;
		rangeBUOpen.debug = false;
		action.show(type);

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
		rangeSBOpen.updateRangeData();
		rangeSBLimp.combineRangeWithThisRange(rangeSBOpen);
		rangeSBLimp.updateRangeData();
		// PREFLOP_OPEN BET2
		rangeSBBet3.updateRangeData();
		rangeSBCall.combineRangeWithThisRange(rangeSBBet3);
		rangeSBCall.updateRangeData();
		// PREFLOP_BET3
		rangeSBBet4.updateRangeData();
		rangeSBBet3Call.combineRangeWithThisRange(rangeSBBet4);
		rangeSBBet3Call.updateRangeData();
		// PREFLOP_BET4
		rangeSBAllin.updateRangeData();
		rangeSBBet4Call.combineRangeWithThisRange(rangeSBAllin);
		rangeSBBet4Call.updateRangeData();
		// PREFLOP_ALLIN
		rangeSBAllinCall.updateRangeData();

		// PREFLOP_LIMP BET1
		rangeBBOpen.updateRangeData();
		rangeBBLimp.combineRangeWithThisRange(rangeBBOpen);
		rangeBBLimp.updateRangeData();
		// PREFLOP_OPEN BET2
		rangeBBBet3.updateRangeData();
		rangeBBCall.combineRangeWithThisRange(rangeBBBet3);
		rangeBBBet3Call.updateRangeData();
		// PREFLOP_BET3
		rangeBBBet4.updateRangeData();
		rangeBBBet3Call.combineRangeWithThisRange(rangeBBBet4);
		rangeBBBet3Call.updateRangeData();
		// PREFLOP_BET4
		rangeBBAllin.updateRangeData();
		rangeBBBet4Call.combineRangeWithThisRange(rangeBBAllin);
		rangeBBBet4Call.updateRangeData();
		// PREFLOP_ALLIN
		rangeBBAllinCall.updateRangeData();

		// PREFLOP_LIMP BET1
		rangeUTGOpen.updateRangeData();
		rangeUTGLimp.combineRangeWithThisRange(rangeUTGOpen);
		rangeUTGLimp.updateRangeData();
		// PREFLOP_OPEN BET2
		rangeUTGBet3.updateRangeData();
		rangeUTGCall.combineRangeWithThisRange(rangeUTGBet3);
		rangeUTGCall.updateRangeData();
		// PREFLOP_BET3
		rangeUTGBet4.updateRangeData();
		rangeUTGBet3Call.combineRangeWithThisRange(rangeUTGBet4);
		rangeUTGBet3Call.updateRangeData();
		// PREFLOP_BET4
		rangeUTGAllin.updateRangeData();
		rangeUTGBet4Call.combineRangeWithThisRange(rangeUTGAllin);
		rangeUTGBet4Call.updateRangeData();
		// PREFLOP_ALLIN
		rangeUTGAllinCall.updateRangeData();

		// PREFLOP_LIMP BET1
		rangeMPOpen.updateRangeData();
		rangeMPLimp.combineRangeWithThisRange(rangeMPOpen);
		rangeMPLimp.updateRangeData();
		// PREFLOP_OPEN BET2
		rangeMPBet3.updateRangeData();
		rangeMPCall.combineRangeWithThisRange(rangeMPBet3);
		rangeMPCall.updateRangeData();
		// PREFLOP_BET3
		rangeMPBet4.updateRangeData();
		rangeMPBet3Call.combineRangeWithThisRange(rangeMPBet4);
		rangeMPBet3Call.updateRangeData();
		// PREFLOP_BET4
		rangeMPAllin.updateRangeData();
		rangeMPBet4Call.combineRangeWithThisRange(rangeMPAllin);
		rangeMPBet4Call.updateRangeData();
		// PREFLOP_ALLIN
		rangeMPAllinCall.updateRangeData();

		// PREFLOP_LIMP BET1
		rangeCOOpen.updateRangeData();
		rangeCOLimp.combineRangeWithThisRange(rangeCOOpen);
		rangeCOLimp.updateRangeData();
		// PREFLOP_OPEN BET2
		rangeCOBet3.updateRangeData();
		rangeCOCall.combineRangeWithThisRange(rangeCOBet3);
		rangeCOCall.updateRangeData();
		// PREFLOP_BET3
		rangeCOBet4.updateRangeData();
		rangeCOBet3Call.combineRangeWithThisRange(rangeCOBet4);
		rangeCOBet3Call.updateRangeData();
		// PREFLOP_BET4
		rangeCOAllin.updateRangeData();
		rangeCOBet4Call.combineRangeWithThisRange(rangeCOAllin);
		rangeCOBet4Call.updateRangeData();
		// PREFLOP_ALLIN
		rangeCOAllinCall.updateRangeData();

		// PREFLOP_LIMP BET1
		rangeBUOpen.updateRangeData();
		rangeBULimp.combineRangeWithThisRange(rangeBUOpen);
		rangeBULimp.updateRangeData();
		// PREFLOP_OPEN BET2
		rangeBUBet3.updateRangeData();
		rangeBUCall.combineRangeWithThisRange(rangeBUBet3);
		rangeBUCall.updateRangeData();
		// PREFLOP_BET3
		rangeBUBet4.updateRangeData();
		rangeBUBet3Call.combineRangeWithThisRange(rangeBUBet4);
		rangeBUBet3Call.updateRangeData();
		// PREFLOP_BET4
		rangeBUAllin.updateRangeData();
		rangeBUBet4Call.combineRangeWithThisRange(rangeBUAllin);
		rangeBUBet4Call.updateRangeData();
		// PREFLOP_ALLIN
		rangeBUAllinCall.updateRangeData();
		copyDataHandRanges();
		copyDataPlayability();
		copyDataPercentage();
		copyDataCombos();
		copyDataGaps();
	}

	// Copy data from HandRange playability index
	private static void copyDataPlayability() {
		// BET1
		action.actionArrayPlayabilityRaise[SB][0] = rangeSBOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[SB][0] = rangeSBLimp.lowPlayabilitrhyArrayIndex;
		// BET2
		action.actionArrayPlayabilityRaise[SB][1] = rangeSBBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[SB][1] = rangeSBCall.lowPlayabilitrhyArrayIndex;
		// BET3
		action.actionArrayPlayabilityRaise[SB][2] = rangeSBBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[SB][2] = rangeSBBet3Call.lowPlayabilitrhyArrayIndex;
		// BET4
		action.actionArrayPlayabilityRaise[SB][3] = rangeSBAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[SB][3] = rangeSBBet4Call.lowPlayabilitrhyArrayIndex;
		// ALLIN
		action.actionArrayPlayabilityRaise[SB][4] = rangeSBAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[SB][4] = rangeSBAllinCall.lowPlayabilitrhyArrayIndex;

		// BET1
		action.actionArrayPlayabilityRaise[BB][0] = rangeBBOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[BB][0] = rangeBBLimp.lowPlayabilitrhyArrayIndex;
		// BET2
		action.actionArrayPlayabilityRaise[BB][1] = rangeBBBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[BB][1] = rangeBBCall.lowPlayabilitrhyArrayIndex;
		// BET3
		action.actionArrayPlayabilityRaise[BB][2] = rangeBBBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[BB][2] = rangeBBBet3Call.lowPlayabilitrhyArrayIndex;
		// BET4
		action.actionArrayPlayabilityRaise[BB][3] = rangeBBAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[BB][3] = rangeBBBet4Call.lowPlayabilitrhyArrayIndex;
		// ALLIN
		action.actionArrayPlayabilityRaise[BB][4] = rangeBBAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[BB][4] = rangeBBAllinCall.lowPlayabilitrhyArrayIndex;

		// BET1
		action.actionArrayPlayabilityRaise[UTG][0] = rangeUTGOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[UTG][0] = rangeUTGLimp.lowPlayabilitrhyArrayIndex;
		// BET2
		action.actionArrayPlayabilityRaise[UTG][1] = rangeUTGBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[UTG][1] = rangeUTGCall.lowPlayabilitrhyArrayIndex;
		// BET3
		action.actionArrayPlayabilityRaise[UTG][2] = rangeUTGBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[UTG][2] = rangeUTGBet3Call.lowPlayabilitrhyArrayIndex;
		// BET4
		action.actionArrayPlayabilityRaise[UTG][3] = rangeUTGAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[UTG][3] = rangeUTGBet4Call.lowPlayabilitrhyArrayIndex;
		// ALLIN
		action.actionArrayPlayabilityRaise[UTG][4] = rangeUTGAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[UTG][4] = rangeUTGAllinCall.lowPlayabilitrhyArrayIndex;

		// BET1
		action.actionArrayPlayabilityRaise[MP][0] = rangeMPOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[MP][0] = rangeMPLimp.lowPlayabilitrhyArrayIndex;
		// BET2
		action.actionArrayPlayabilityRaise[MP][1] = rangeMPBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[MP][1] = rangeMPCall.lowPlayabilitrhyArrayIndex;
		// BET3
		action.actionArrayPlayabilityRaise[MP][2] = rangeMPBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[MP][2] = rangeMPBet3Call.lowPlayabilitrhyArrayIndex;
		// BET4
		action.actionArrayPlayabilityRaise[MP][3] = rangeMPAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[MP][3] = rangeMPBet4Call.lowPlayabilitrhyArrayIndex;
		// ALLIN
		action.actionArrayPlayabilityRaise[MP][4] = rangeMPAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[MP][4] = rangeMPAllinCall.lowPlayabilitrhyArrayIndex;

		// BET1
		action.actionArrayPlayabilityRaise[CO][0] = rangeCOOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[CO][0] = rangeCOLimp.lowPlayabilitrhyArrayIndex;
		// BET2
		action.actionArrayPlayabilityRaise[CO][1] = rangeCOBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[CO][1] = rangeCOCall.lowPlayabilitrhyArrayIndex;
		// BET3
		action.actionArrayPlayabilityRaise[CO][2] = rangeCOBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[CO][2] = rangeCOBet3Call.lowPlayabilitrhyArrayIndex;
		// BET4
		action.actionArrayPlayabilityRaise[CO][3] = rangeCOAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[CO][3] = rangeCOBet4Call.lowPlayabilitrhyArrayIndex;
		// ALLIN
		action.actionArrayPlayabilityRaise[CO][4] = rangeCOAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[CO][4] = rangeCOAllinCall.lowPlayabilitrhyArrayIndex;

		// BET1
		action.actionArrayPlayabilityRaise[BU][0] = rangeBUOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[BU][0] = rangeBULimp.lowPlayabilitrhyArrayIndex;
		// BET2
		action.actionArrayPlayabilityRaise[BU][1] = rangeBUBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[BU][1] = rangeBUCall.lowPlayabilitrhyArrayIndex;
		// BET3
		action.actionArrayPlayabilityRaise[BU][2] = rangeBUBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[BU][2] = rangeBUBet3Call.lowPlayabilitrhyArrayIndex;
		// BET4
		action.actionArrayPlayabilityRaise[BU][3] = rangeBUAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[BU][3] = rangeBUBet4Call.lowPlayabilitrhyArrayIndex;
		// ALLIN
		action.actionArrayPlayabilityRaise[BU][4] = rangeBUAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayabilityCall[BU][4] = rangeBUAllinCall.lowPlayabilitrhyArrayIndex;

	}

	// Copy data from HandRange playability index
	private static void copyDataCombos() {
		// BET1
		action.actionArrayCombosRaise[SB][0] = rangeSBOpen.combos;
		action.actionArrayCombosCall[SB][0] = rangeSBLimp.combos;
		// BET2
		action.actionArrayCombosRaise[SB][1] = rangeSBBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayCombosCall[SB][1] = rangeSBCall.combos;
		// BET3
		action.actionArrayCombosRaise[SB][2] = rangeSBBet4.combos;
		action.actionArrayCombosCall[SB][2] = rangeSBBet3Call.combos;
		// BET4
		action.actionArrayCombosRaise[SB][3] = rangeSBAllin.combos;
		action.actionArrayCombosCall[SB][3] = rangeSBBet4Call.combos;
		// ALLIN
		action.actionArrayCombosRaise[SB][4] = rangeSBAllin.combos;
		action.actionArrayCombosCall[SB][4] = rangeSBAllinCall.combos;

		// BET1
		action.actionArrayCombosRaise[BB][0] = rangeBBOpen.combos;
		action.actionArrayCombosCall[BB][0] = rangeBBLimp.combos;
		// BET2
		action.actionArrayCombosRaise[BB][1] = rangeBBBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayCombosCall[BB][1] = rangeBBCall.combos;
		// BET3
		action.actionArrayCombosRaise[BB][2] = rangeBBBet4.combos;
		action.actionArrayCombosCall[BB][2] = rangeBBBet3Call.combos;
		// BET4
		action.actionArrayCombosRaise[BB][3] = rangeBBAllin.combos;
		action.actionArrayCombosCall[BB][3] = rangeBBBet4Call.combos;
		// ALLIN
		action.actionArrayCombosRaise[BB][4] = rangeBBAllin.combos;
		action.actionArrayCombosCall[BB][4] = rangeBBAllinCall.combos;

		// BET1
		action.actionArrayCombosRaise[UTG][0] = rangeUTGOpen.combos;
		action.actionArrayCombosCall[UTG][0] = rangeUTGLimp.combos;
		// BET2
		action.actionArrayCombosRaise[UTG][1] = rangeUTGBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayCombosCall[UTG][1] = rangeUTGCall.combos;
		// BET3
		action.actionArrayCombosRaise[UTG][2] = rangeUTGBet4.combos;
		action.actionArrayCombosCall[UTG][2] = rangeUTGBet3Call.combos;
		// BET4
		action.actionArrayCombosRaise[UTG][3] = rangeUTGAllin.combos;
		action.actionArrayCombosCall[UTG][3] = rangeUTGBet4Call.combos;
		// ALLIN
		action.actionArrayCombosRaise[UTG][4] = rangeUTGAllin.combos;
		action.actionArrayCombosCall[UTG][4] = rangeUTGAllinCall.combos;

		// BET1
		action.actionArrayCombosRaise[MP][0] = rangeMPOpen.combos;
		action.actionArrayCombosCall[MP][0] = rangeMPLimp.combos;
		// BET2
		action.actionArrayCombosRaise[MP][1] = rangeMPBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayCombosCall[MP][1] = rangeMPCall.combos;
		// BET3
		action.actionArrayCombosRaise[MP][2] = rangeMPBet4.combos;
		action.actionArrayCombosCall[MP][2] = rangeMPBet3Call.combos;
		// BET4
		action.actionArrayCombosRaise[MP][3] = rangeMPAllin.combos;
		action.actionArrayCombosCall[MP][3] = rangeMPBet4Call.combos;
		// ALLIN
		action.actionArrayCombosRaise[MP][4] = rangeMPAllin.combos;
		action.actionArrayCombosCall[MP][4] = rangeMPAllinCall.combos;

		// BET1
		action.actionArrayCombosRaise[CO][0] = rangeCOOpen.combos;
		action.actionArrayCombosCall[CO][0] = rangeCOLimp.combos;
		// BET2
		action.actionArrayCombosRaise[CO][1] = rangeCOBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayCombosCall[CO][1] = rangeCOCall.combos;
		// BET3
		action.actionArrayCombosRaise[CO][2] = rangeCOBet4.combos;
		action.actionArrayCombosCall[CO][2] = rangeCOBet3Call.combos;
		// BET4
		action.actionArrayCombosRaise[CO][3] = rangeCOAllin.combos;
		action.actionArrayCombosCall[CO][3] = rangeCOBet4Call.combos;
		// ALLIN
		action.actionArrayCombosRaise[CO][4] = rangeCOAllin.combos;
		action.actionArrayCombosCall[CO][4] = rangeCOAllinCall.combos;

		// BET1
		action.actionArrayCombosRaise[BU][0] = rangeBUOpen.combos;
		action.actionArrayCombosCall[BU][0] = rangeBULimp.combos;
		// BET2
		action.actionArrayCombosRaise[BU][1] = rangeBUBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayCombosCall[BU][1] = rangeBUCall.combos;
		// BET3
		action.actionArrayCombosRaise[BU][2] = rangeBUBet4.combos;
		action.actionArrayCombosCall[BU][2] = rangeBUBet3Call.combos;
		// BET4
		action.actionArrayCombosRaise[BU][3] = rangeBUAllin.combos;
		action.actionArrayCombosCall[BU][3] = rangeBUBet4Call.combos;
		// ALLIN
		action.actionArrayCombosRaise[BU][4] = rangeBUAllin.combos;
		action.actionArrayCombosCall[BU][4] = rangeBUAllinCall.combos;

	}

	// Copy data from HandRange playability index
	private static void copyDataPercentage() {
		// BET1
		action.actionArrayPercentageRaise[SB][0] = rangeSBOpen.percentage;
		action.actionArrayPercentageCall[SB][0] = rangeSBLimp.percentage;
		// BET2
		action.actionArrayPercentageRaise[SB][1] = rangeSBBet3.percentage;
		action.actionArrayPercentageCall[SB][1] = rangeSBCall.percentage;
		// BET3
		action.actionArrayPercentageRaise[SB][2] = rangeSBBet4.percentage;
		action.actionArrayPercentageCall[SB][2] = rangeSBBet3Call.percentage;
		// BET4
		action.actionArrayPercentageRaise[SB][3] = rangeSBAllin.percentage;
		action.actionArrayPercentageCall[SB][3] = rangeSBBet4Call.percentage;
		// ALLIN
		action.actionArrayPercentageRaise[SB][4] = rangeSBAllin.percentage;
		action.actionArrayPercentageCall[SB][4] = rangeSBAllinCall.percentage;

		// BET1
		action.actionArrayPercentageRaise[BB][0] = rangeBBOpen.percentage;
		action.actionArrayPercentageCall[BB][0] = rangeBBLimp.percentage;
		// BET2
		action.actionArrayPercentageRaise[BB][1] = rangeBBBet3.percentage;
		action.actionArrayPercentageCall[BB][1] = rangeBBCall.percentage;
		// BET3
		action.actionArrayPercentageRaise[BB][2] = rangeBBBet4.percentage;
		action.actionArrayPercentageCall[BB][2] = rangeBBBet3Call.percentage;
		// BET4
		action.actionArrayPercentageRaise[BB][3] = rangeBBAllin.percentage;
		action.actionArrayPercentageCall[BB][3] = rangeBBBet4Call.percentage;
		// ALLIN
		action.actionArrayPercentageRaise[BB][4] = rangeBBAllin.percentage;
		action.actionArrayPercentageCall[BB][4] = rangeBBAllinCall.percentage;

		// BET1
		action.actionArrayPercentageRaise[UTG][0] = rangeUTGOpen.percentage;
		action.actionArrayPercentageCall[UTG][0] = rangeUTGLimp.percentage;
		// BET2
		action.actionArrayPercentageRaise[UTG][1] = rangeUTGBet3.percentage;
		action.actionArrayPercentageCall[UTG][1] = rangeUTGCall.percentage;
		// BET3
		action.actionArrayPercentageRaise[UTG][2] = rangeUTGBet4.percentage;
		action.actionArrayPercentageCall[UTG][2] = rangeUTGBet3Call.percentage;
		// BET4
		action.actionArrayPercentageRaise[UTG][3] = rangeUTGAllin.percentage;
		action.actionArrayPercentageCall[UTG][3] = rangeUTGBet4Call.percentage;
		// ALLIN
		action.actionArrayPercentageRaise[UTG][4] = rangeUTGAllin.percentage;
		action.actionArrayPercentageCall[UTG][4] = rangeUTGAllinCall.percentage;

		// BET1
		action.actionArrayPercentageRaise[MP][0] = rangeMPOpen.percentage;
		action.actionArrayPercentageCall[MP][0] = rangeMPLimp.percentage;
		// BET2
		action.actionArrayPercentageRaise[MP][1] = rangeMPBet3.percentage;
		action.actionArrayPercentageCall[MP][1] = rangeMPCall.percentage;
		// BET3
		action.actionArrayPercentageRaise[MP][2] = rangeMPBet4.percentage;
		action.actionArrayPercentageCall[MP][2] = rangeMPBet3Call.percentage;
		// BET4
		action.actionArrayPercentageRaise[MP][3] = rangeMPAllin.percentage;
		action.actionArrayPercentageCall[MP][3] = rangeMPBet4Call.percentage;
		// ALLIN
		action.actionArrayPercentageRaise[MP][4] = rangeMPAllin.percentage;
		action.actionArrayPercentageCall[MP][4] = rangeMPAllinCall.percentage;

		// BET1
		action.actionArrayPercentageRaise[CO][0] = rangeCOOpen.percentage;
		action.actionArrayPercentageCall[CO][0] = rangeCOLimp.percentage;
		// BET2
		action.actionArrayPercentageRaise[CO][1] = rangeCOBet3.percentage;
		action.actionArrayPercentageCall[CO][1] = rangeCOCall.percentage;
		// BET3
		action.actionArrayPercentageRaise[CO][2] = rangeCOBet4.percentage;
		action.actionArrayPercentageCall[CO][2] = rangeCOBet3Call.percentage;
		// BET4
		action.actionArrayPercentageRaise[CO][3] = rangeCOAllin.percentage;
		action.actionArrayPercentageCall[CO][3] = rangeCOBet4Call.percentage;
		// ALLIN
		action.actionArrayPercentageRaise[CO][4] = rangeCOAllin.percentage;
		action.actionArrayPercentageCall[CO][4] = rangeCOAllinCall.percentage;

		// BET1
		action.actionArrayPercentageRaise[BU][0] = rangeBUOpen.percentage;
		action.actionArrayPercentageCall[BU][0] = rangeBULimp.percentage;
		// BET2
		action.actionArrayPercentageRaise[BU][1] = rangeBUBet3.percentage;
		action.actionArrayPercentageCall[BU][1] = rangeBUCall.percentage;
		// BET3
		action.actionArrayPercentageRaise[BU][2] = rangeBUBet4.percentage;
		action.actionArrayPercentageCall[BU][2] = rangeBUBet3Call.percentage;
		// BET4
		action.actionArrayPercentageRaise[BU][3] = rangeBUAllin.percentage;
		action.actionArrayPercentageCall[BU][3] = rangeBUBet4Call.percentage;
		// ALLIN
		action.actionArrayPercentageRaise[BU][4] = rangeBUAllin.percentage;
		action.actionArrayPercentageCall[BU][4] = rangeBUAllinCall.percentage;

	}

	// Copy data from gapsArray
	private static void copyDataGaps() {
		action.gapsArrayCall[SB][0] = rangeSBLimp.gaps;
		action.gapsArrayRaise[SB][0] = rangeSBOpen.gaps;
		action.gapsArrayCall[SB][1] = rangeSBCall.gaps;
		action.gapsArrayRaise[SB][1] = rangeSBBet3.gaps;
		action.gapsArrayCall[SB][2] = rangeSBBet3Call.gaps;
		action.gapsArrayRaise[SB][2] = rangeSBBet4.gaps;
		action.gapsArrayCall[SB][3] = rangeSBBet4Call.gaps;
		action.gapsArrayRaise[SB][3] = rangeSBAllin.gaps;
		action.gapsArrayCall[SB][4] = rangeSBAllinCall.gaps;
		action.gapsArrayRaise[BB][0] = rangeBBLimp.gaps;
		action.gapsArrayRaise[BB][0] = rangeBBOpen.gaps;
		action.gapsArrayCall[BB][1] = rangeBBCall.gaps;
		action.gapsArrayRaise[BB][1] = rangeBBBet3.gaps;
		action.gapsArrayCall[BB][2] = rangeBBBet3Call.gaps;
		action.gapsArrayRaise[BB][2] = rangeBBBet4.gaps;
		action.gapsArrayCall[BB][3] = rangeBBBet4Call.gaps;
		action.gapsArrayRaise[BB][3] = rangeBBAllin.gaps;
		action.gapsArrayCall[BB][4] = rangeBBAllinCall.gaps;
		action.gapsArrayCall[UTG][0] = rangeUTGLimp.gaps;
		action.gapsArrayRaise[UTG][0] = rangeUTGOpen.gaps;
		action.gapsArrayCall[UTG][1] = rangeUTGCall.gaps;
		action.gapsArrayRaise[UTG][1] = rangeUTGBet3.gaps;
		action.gapsArrayCall[UTG][2] = rangeUTGBet3Call.gaps;
		action.gapsArrayRaise[UTG][2] = rangeUTGBet4.gaps;
		action.gapsArrayCall[UTG][3] = rangeUTGBet4Call.gaps;
		action.gapsArrayRaise[UTG][3] = rangeUTGAllinCall.gaps;
		action.gapsArrayCall[MP][0] = rangeMPLimp.gaps;
		action.gapsArrayRaise[MP][0] = rangeMPOpen.gaps;
		action.gapsArrayCall[MP][1] = rangeMPCall.gaps;
		action.gapsArrayRaise[MP][1] = rangeMPBet3.gaps;
		action.gapsArrayCall[MP][2] = rangeMPBet3Call.gaps;
		action.gapsArrayRaise[MP][2] = rangeMPBet4.gaps;
		action.gapsArrayCall[MP][3] = rangeMPBet4Call.gaps;
		action.gapsArrayRaise[MP][3] = rangeMPAllin.gaps;
		action.gapsArrayCall[MP][4] = rangeMPAllinCall.gaps;
		action.gapsArrayCall[CO][0] = rangeCOLimp.gaps;
		action.gapsArrayRaise[CO][0] = rangeCOOpen.gaps;
		action.gapsArrayCall[CO][1] = rangeCOCall.gaps;
		action.gapsArrayRaise[CO][1] = rangeCOBet3.gaps;
		action.gapsArrayCall[CO][2] = rangeCOBet3Call.gaps;
		action.gapsArrayRaise[CO][2] = rangeCOBet4.gaps;
		action.gapsArrayCall[CO][3] = rangeCOBet4Call.gaps;
		action.gapsArrayRaise[CO][3] = rangeCOAllin.gaps;
		action.gapsArrayCall[CO][4] = rangeCOAllinCall.gaps;
		action.gapsArrayCall[BU][0] = rangeBULimp.gaps;
		action.gapsArrayRaise[BU][0] = rangeBUOpen.gaps;
		action.gapsArrayCall[BU][1] = rangeBUCall.gaps;
		action.gapsArrayRaise[BU][1] = rangeBUBet3.gaps;
		action.gapsArrayCall[BU][2] = rangeBUBet3Call.gaps;
		action.gapsArrayRaise[BU][2] = rangeBUBet4.gaps;
		action.gapsArrayCall[BU][3] = rangeBUBet4Call.gaps;
		action.gapsArrayRaise[BU][3] = rangeBUAllin.gaps;
		action.gapsArrayCall[BU][4] = rangeBUAllinCall.gaps;
	}

	// Copy data from HandRange
	private static void copyDataHandRanges() {
		action.rangeArrayCall[SB][0] = rangeSBLimp;

		action.rangeArrayRaise[SB][0] = rangeSBOpen;
		action.rangeArrayCall[SB][1] = rangeSBCall;
		action.rangeArrayRaise[SB][1] = rangeSBBet3;
		action.rangeArrayCall[SB][2] = rangeSBBet3Call;
		action.rangeArrayRaise[SB][2] = rangeSBBet4;
		action.rangeArrayCall[SB][3] = rangeSBBet4Call;
		action.rangeArrayRaise[SB][3] = rangeSBAllin;
		action.rangeArrayCall[SB][4] = rangeSBAllinCall;
		action.rangeArrayRaise[BB][0] = rangeBBLimp;
		action.rangeArrayRaise[BB][0] = rangeBBOpen;
		action.rangeArrayCall[BB][1] = rangeBBCall;
		action.rangeArrayRaise[BB][1] = rangeBBBet3;
		action.rangeArrayCall[BB][2] = rangeBBBet3Call;
		action.rangeArrayRaise[BB][2] = rangeBBBet4;
		action.rangeArrayCall[BB][3] = rangeBBBet4Call;
		action.rangeArrayRaise[BB][3] = rangeBBAllin;
		action.rangeArrayCall[BB][4] = rangeBBAllinCall;
		action.rangeArrayCall[UTG][0] = rangeUTGLimp;
		action.rangeArrayRaise[UTG][0] = rangeUTGOpen;
		action.rangeArrayCall[UTG][1] = rangeUTGCall;
		action.rangeArrayRaise[UTG][1] = rangeUTGBet3;
		action.rangeArrayCall[UTG][2] = rangeUTGBet3Call;
		action.rangeArrayRaise[UTG][2] = rangeUTGBet4;
		action.rangeArrayCall[UTG][3] = rangeUTGBet4Call;
		action.rangeArrayRaise[UTG][3] = rangeUTGAllinCall;
		action.rangeArrayCall[MP][0] = rangeMPLimp;
		action.rangeArrayRaise[MP][0] = rangeMPOpen;
		action.rangeArrayCall[MP][1] = rangeMPCall;
		action.rangeArrayRaise[MP][1] = rangeMPBet3;
		action.rangeArrayCall[MP][2] = rangeMPBet3Call;
		action.rangeArrayRaise[MP][2] = rangeMPBet4;
		action.rangeArrayCall[MP][3] = rangeMPBet4Call;
		action.rangeArrayRaise[MP][3] = rangeMPAllin;
		action.rangeArrayCall[MP][4] = rangeMPAllinCall;
		action.rangeArrayCall[CO][0] = rangeCOLimp;
		action.rangeArrayRaise[CO][0] = rangeCOOpen;
		action.rangeArrayCall[CO][1] = rangeCOCall;
		action.rangeArrayRaise[CO][1] = rangeCOBet3;
		action.rangeArrayCall[CO][2] = rangeCOBet3Call;
		action.rangeArrayRaise[CO][2] = rangeCOBet4;
		action.rangeArrayCall[CO][3] = rangeCOBet4Call;
		action.rangeArrayRaise[CO][3] = rangeCOAllin;
		action.rangeArrayCall[CO][4] = rangeCOAllinCall;
		action.rangeArrayCall[BU][0] = rangeBULimp;
		action.rangeArrayRaise[BU][0] = rangeBUOpen;
		action.rangeArrayCall[BU][1] = rangeBUCall;
		action.rangeArrayRaise[BU][1] = rangeBUBet3;
		action.rangeArrayCall[BU][2] = rangeBUBet3Call;
		action.rangeArrayRaise[BU][2] = rangeBUBet4;
		action.rangeArrayCall[BU][3] = rangeBUBet4Call;
		action.rangeArrayRaise[BU][3] = rangeBUAllin;
		action.rangeArrayCall[BU][4] = rangeBUAllinCall;

	}

	// Create HandRange ranges based on percentages for testing
	static void testRanges() {
		String path = EvalData.applicationDirectory;
		double a = 1;
		double b = 1.1;

		double c = .7;
		double d = .8;

		double e = .3;
		double f = .4;

		double g = .2;
		double h = .3;

		double i = .1;
		double j = .1;
		double percentSB = 10;
		double percentBB = 10;
		double percentUTG = 8;
		double percentMP = 12;
		double percentCO = 20;
		double percentBU = 35;

		HandRange rangeSB = new HandRange();
		HandRange rangeBB = new HandRange();
		HandRange rangeUTG = new HandRange();
		HandRange rangeMP = new HandRange();
		HandRange rangeCO = new HandRange();
		HandRange rangeBU = new HandRange();

		rangeSB.buildRangePercentage(percentSB * a);
		rangeBB.buildRangePercentage(percentBB * a);
		rangeUTG.buildRangePercentage(percentUTG * a);
		rangeMP.buildRangePercentage(percentMP * a);
		rangeCO.buildRangePercentage(percentCO * a);
		rangeBU.buildRangePercentage(percentBU * a);
		rangeSB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Open.ser");
		rangeBB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Open.ser");
		rangeUTG.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Open.ser");
		rangeMP.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Open.ser");
		rangeCO.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Open.ser");
		rangeBU.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Open.ser");

		rangeSB.buildRangePercentage(percentSB * b);
		rangeBB.buildRangePercentage(percentBB * b);
		rangeUTG.buildRangePercentage(percentUTG * b);
		rangeMP.buildRangePercentage(percentMP * b);
		rangeCO.buildRangePercentage(percentCO * b);
		rangeBU.buildRangePercentage(percentBU * b);
		rangeSB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Limp.ser");
		rangeBB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Limp.ser");
		rangeUTG.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Limp.ser");
		rangeMP.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Limp.ser");
		rangeCO.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Limp.ser");
		rangeBU.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Limp.ser");

		rangeSB.buildRangePercentage(percentSB * c);
		rangeBB.buildRangePercentage(percentBB * c);
		rangeUTG.buildRangePercentage(percentUTG * c);
		rangeMP.buildRangePercentage(percentMP * c);
		rangeCO.buildRangePercentage(percentCO * c);
		rangeBU.buildRangePercentage(percentBU * c);
		rangeSB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Bet3.ser");
		rangeBB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Bet3.ser");
		rangeUTG.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Bet3.ser");
		rangeMP.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Bet3.ser");
		rangeCO.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Bet3.ser");
		rangeBU.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Bet3.ser");

		rangeSB.buildRangePercentage(percentSB * d);
		rangeBB.buildRangePercentage(percentBB * d);
		rangeUTG.buildRangePercentage(percentUTG * d);
		rangeMP.buildRangePercentage(percentMP * d);
		rangeCO.buildRangePercentage(percentCO * d);
		rangeBU.buildRangePercentage(percentBU * d);
		rangeSB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Bet3Call.ser");
		rangeBB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Bet3Call.ser");
		rangeUTG.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Bet3Call.ser");
		rangeMP.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Bet3Call.ser");
		rangeCO.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Bet3Call.ser");
		rangeBU.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Bet3Call.ser");

		rangeSB.buildRangePercentage(percentSB * e);
		rangeBB.buildRangePercentage(percentBB * e);
		rangeUTG.buildRangePercentage(percentUTG * e);
		rangeMP.buildRangePercentage(percentMP * e);
		rangeCO.buildRangePercentage(percentCO * e);
		rangeBU.buildRangePercentage(percentBU * e);
		rangeSB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Bet4.ser");
		rangeBB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Bet4.ser");
		rangeUTG.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Bet4.ser");
		rangeMP.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Bet4.ser");
		rangeCO.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Bet4.ser");
		rangeBU.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Bet4.ser");

		rangeSB.buildRangePercentage(percentSB * f);
		rangeBB.buildRangePercentage(percentBB * f);
		rangeUTG.buildRangePercentage(percentUTG * f);
		rangeMP.buildRangePercentage(percentMP * f);
		rangeCO.buildRangePercentage(percentCO * f);
		rangeBU.buildRangePercentage(percentBU * f);
		rangeSB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Bet4Call.ser");
		rangeBB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Bet4Call.ser");
		rangeUTG.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Bet4Call.ser");
		rangeMP.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Bet4Call.ser");
		rangeCO.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Bet4Call.ser");
		rangeBU.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Bet4Call.ser");

		rangeSB.buildRangePercentage(percentSB * g);
		rangeBB.buildRangePercentage(percentBB * g);
		rangeUTG.buildRangePercentage(percentUTG * g);
		rangeMP.buildRangePercentage(percentMP * g);
		rangeCO.buildRangePercentage(percentCO * g);
		rangeBU.buildRangePercentage(percentBU * g);
		rangeSB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Allin.ser");
		rangeBB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Allin.ser");
		rangeUTG.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Allin.ser");
		rangeMP.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Allin.ser");
		rangeCO.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\Allin.ser");
		rangeBU.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\Allin.ser");

		rangeSB.buildRangePercentage(percentSB * h);
		rangeBB.buildRangePercentage(percentBB * h);
		rangeUTG.buildRangePercentage(percentUTG * h);
		rangeMP.buildRangePercentage(percentMP * h);
		rangeCO.buildRangePercentage(percentCO * h);
		rangeBU.buildRangePercentage(percentBU * h);
		rangeSB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\AllinCall.ser");
		rangeBB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\AllinCall.ser");
		rangeUTG.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\AllinCall.ser");
		rangeMP.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\AllinCall.ser");
		rangeCO.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Cutoff\\AllinCall.ser");
		rangeBU.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\Button\\AllinCall.ser");

	}
}
