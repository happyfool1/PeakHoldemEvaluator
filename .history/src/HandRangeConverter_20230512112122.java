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
		type = "Test";
		testRanges();
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
		rangeSBOpen.updateForHoleCardAction();
		rangeSBLimp.updateForHoleCardAction();
		// PREFLOP_OPEN BET2
		rangeSBBet3.updateForHoleCardAction();
		rangeSBCall.updateForHoleCardAction();
		// PREFLOP_BET3
		rangeSBBet4.updateForHoleCardAction();
		rangeSBBet3Call.updateForHoleCardAction();
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
		rangeBBBet3Call.updateForHoleCardAction();
		// PREFLOP_BET3
		rangeBBBet4.updateForHoleCardAction();
		rangeBBBet3Call.updateForHoleCardAction();
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
		rangeUTGBet3Call.updateForHoleCardAction();
		// PREFLOP_BET4
		rangeUTGAllin.updateForHoleCardAction();
		rangeUTGBet4Call.updateForHoleCardAction();
		// PREFLOP_ALLIN
		rangeUTGAllinCall.updateForHoleCardAction();

		// PREFLOP_LIMP BET1
		rangeMPOpen.updateForHoleCardAction();
		rangeMPLimp.updateForHoleCardAction();
		// PREFLOP_OPEN BET2
		rangeMPBet3.updateForHoleCardAction();
		rangeMPCall.updateForHoleCardAction();
		// PREFLOP_BET3
		rangeMPBet4.updateForHoleCardAction();
		rangeMPBet3Call.updateForHoleCardAction();
		// PREFLOP_BET4
		rangeMPAllin.updateForHoleCardAction();
		rangeMPBet4Call.updateForHoleCardAction();
		// PREFLOP_ALLIN
		rangeMPAllinCall.updateForHoleCardAction();

		// PREFLOP_LIMP BET1
		rangeCOOpen.updateForHoleCardAction();
		rangeCOLimp.updateForHoleCardAction();
		// PREFLOP_OPEN BET2
		rangeCOBet3.updateForHoleCardAction();
		rangeCOCall.updateForHoleCardAction();
		// PREFLOP_BET3
		rangeCOBet4.updateForHoleCardAction();
		rangeCOBet3Call.updateForHoleCardAction();
		// PREFLOP_BET4
		rangeCOAllin.updateForHoleCardAction();
		rangeCOBet4Call.updateForHoleCardAction();
		// PREFLOP_ALLIN
		rangeCOAllinCall.updateForHoleCardAction();

		// PREFLOP_LIMP BET1
		rangeBUOpen.updateForHoleCardAction();
		rangeBULimp.updateForHoleCardAction();
		// PREFLOP_OPEN BET2
		rangeBUBet3.updateForHoleCardAction();
		rangeBUCall.updateForHoleCardAction();
		// PREFLOP_BET3
		rangeBUBet4.updateForHoleCardAction();
		rangeBUBet3Call.updateForHoleCardAction();
		// PREFLOP_BET4
		rangeBUAllin.updateForHoleCardAction();
		rangeBUBet4Call.updateForHoleCardAction();
		// PREFLOP_ALLIN
		rangeBUAllinCall.updateForHoleCardAction();
		copyData();
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

	}

	// Copy data from HandRange playaGapsbility index
	private static void copyDataGaps() {
		// BET1
		action.actionArrayGapsRaise[SB][0] = rangeSBOpen.gaps;
		action.actionArrayGapsCall[SB][0] = rangeSBLimp.gaps;
		// BET2
		action.actionArrayGapsRaise[SB][1] = rangeSBBet3.gaps;
		action.actionArrayGapsCall[SB][1] = rangeSBCall.gaps;
		// BET3
		action.actionArrayGapsRaise[SB][2] = rangeSBBet4.gaps;
		action.actionArrayGapsCall[SB][2] = rangeSBBet3Call.gaps;
		// BET4
		action.actionArrayGapsRaise[SB][3] = rangeSBAllin.gaps;
		action.actionArrayGapsCall[SB][3] = rangeSBBet4Call.gaps;
		// ALLIN
		action.actionArrayGapsRaise[SB][4] = rangeSBAllin.gaps;
		action.actionArrayGapsCall[SB][4] = rangeSBAllinCall.gaps;

	}

	// Copy data from HandRange
	private static void copyData() {
		for (int i = 0; i < HANDS; i++) {
			action.gapsArray[SB][i] = rangeSBLimp.gapsArray[i];
			action.gapsArray[SB][i] = rangeSBOpen.gapsArray[i];
			action.gapsArray[SB][i] = rangeSBCall.gapsArray[i];
			action.gapsArray[SB][i] = rangeSBBet3.gapsArray[i];
			action.gapsArray[SB][i] = rangeSBBet3Call.gapsArray[i];
			action.gapsArray[SB][i] = rangeSBBet4.gapsArray[i];
			action.gapsArray[SB][i] = rangeSBBet4Call.gapsArray[i];
			action.gapsArray[SB][i] = rangeSBAllin.gapsArray[i];
			action.gapsArray[SB][i] = rangeSBAllinCall.gapsArray[i];
			action.gapsArray[BB][i] = rangeBBLimp.gapsArray[i];
			action.gapsArray[BB][i] = rangeBBOpen.gapsArray[i];
			action.gapsArray[BB][i] = rangeBBCall.gapsArray[i];
			action.gapsArray[BB][i] = rangeBBBet3.gapsArray[i];
			action.gapsArray[BB][i] = rangeBBBet3Call.gapsArray[i];
			action.gapsArray[BB][i] = rangeBBBet4.gapsArray[i];
			action.gapsArray[BB][i] = rangeBBBet4Call.gapsArray[i];
			action.gapsArray[BB][i] = rangeBBAllin.gapsArray[i];
			action.gapsArray[BB][i] = rangeBBAllinCall.gapsArray[i];
			action.gapsArray[UTG][i] = rangeUTGLimp.gapsArray[i];
			action.gapsArray[UTG][i] = rangeUTGOpen.gapsArray[i];
			action.gapsArray[UTG][i] = rangeUTGCall.gapsArray[i];
			action.gapsArray[UTG][i] = rangeUTGBet3.gapsArray[i];
			action.gapsArray[UTG][i] = rangeUTGBet3Call.gapsArray[i];
			action.gapsArray[UTG][i] = rangeUTGBet4.gapsArray[i];
			action.gapsArray[UTG][i] = rangeUTGBet4Call.gapsArray[i];
			action.gapsArray[UTG][i] = rangeUTGAllinCall.gapsArray[i];
			action.gapsArray[MP][i] = rangeMPLimp.gapsArray[i];
			action.gapsArray[MP][i] = rangeMPOpen.gapsArray[i];
			action.gapsArray[MP][i] = rangeMPCall.gapsArray[i];
			action.gapsArray[MP][i] = rangeMPBet3.gapsArray[i];
			action.gapsArray[MP][i] = rangeMPBet3Call.gapsArray[i];
			action.gapsArray[MP][i] = rangeMPBet4.gapsArray[i];
			action.gapsArray[MP][i] = rangeMPBet4Call.gapsArray[i];
			action.gapsArray[MP][i] = rangeMPAllin.gapsArray[i];
			action.gapsArray[MP][i] = rangeMPAllinCall.gapsArray[i];
			action.gapsArray[CO][i] = rangeCOLimp.gapsArray[i];
			action.gapsArray[CO][i] = rangeCOOpen.gapsArray[i];
			action.gapsArray[CO][i] = rangeCOCall.gapsArray[i];
			action.gapsArray[CO][i] = rangeCOBet3.gapsArray[i];
			action.gapsArray[CO][i] = rangeCOBet3Call.gapsArray[i];
			action.gapsArray[CO][i] = rangeCOBet4.gapsArray[i];
			action.gapsArray[CO][i] = rangeCOBet4Call.gapsArray[i];
			action.gapsArray[CO][i] = rangeCOAllin.gapsArray[i];
			action.gapsArray[CO][i] = rangeCOAllinCall.gapsArray[i];
			action.gapsArray[BU][i] = rangeBULimp.gapsArray[i];
			action.gapsArray[BU][i] = rangeBUOpen.gapsArray[i];
			action.gapsArray[BU][i] = rangeBUCall.gapsArray[i];
			action.gapsArray[BU][i] = rangeBUBet3.gapsArray[i];
			action.gapsArray[BU][i] = rangeBUBet3Call.gapsArray[i];
			action.gapsArray[BU][i] = rangeBUBet4.gapsArray[i];
			action.gapsArray[BU][i] = rangeBUBet4Call.gapsArray[i];
			action.gapsArray[BU][i] = rangeBUAllin.gapsArray[i];
			action.gapsArray[BU][i] = rangeBUAllinCall.gapsArray[i];
		}
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
