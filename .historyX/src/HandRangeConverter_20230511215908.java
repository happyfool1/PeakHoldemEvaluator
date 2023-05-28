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
		action.actionArrayPlayability[SB][0][0] = rangeSBLimp.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[SB][0][1] = rangeSBOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[SB][1][0] = rangeSBBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[SB][1][1] = rangeSBCall.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[SB][2][0] = rangeSBBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[SB][2][1] = rangeSBBet3Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[SB][3][0] = rangeSBBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[SB][3][1] = rangeSBBet4Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[SB][4][0] = rangeSBAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[SB][4][1] = rangeSBAllinCall.lowPlayabilitrhyArrayIndex;

		action.actionArrayPlayability[BB][0][0] = rangeBBLimp.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BB][0][1] = rangeBBOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BB][1][0] = rangeBBBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BB][1][1] = rangeBBCall.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BB][2][0] = rangeBBBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BB][2][1] = rangeBBBet3Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BB][3][0] = rangeBBBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BB][3][1] = rangeBBBet4Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BB][4][0] = rangeBBAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BB][4][1] = rangeBBAllinCall.lowPlayabilitrhyArrayIndex;

		action.actionArrayPlayability[UTG][0][0] = rangeUTGLimp.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[UTG][0][1] = rangeUTGOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[UTG][1][0] = rangeUTGBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[UTG][1][1] = rangeUTGCall.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[UTG][2][0] = rangeUTGBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[UTG][2][1] = rangeUTGBet3Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[UTG][3][0] = rangeUTGBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[UTG][3][1] = rangeUTGBet4Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[UTG][4][0] = rangeUTGAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[UTG][4][1] = rangeUTGAllinCall.lowPlayabilitrhyArrayIndex;

		action.actionArrayPlayability[MP][0][0] = rangeMPLimp.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[MP][0][1] = rangeMPOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[MP][1][0] = rangeMPBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[MP][1][1] = rangeMPCall.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[MP][2][0] = rangeMPBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[MP][2][1] = rangeMPBet3Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[MP][3][0] = rangeMPBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[MP][3][1] = rangeMPBet4Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[MP][4][0] = rangeMPAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[MP][4][1] = rangeMPAllinCall.lowPlayabilitrhyArrayIndex;

		action.actionArrayPlayability[CO][0][0] = rangeCOLimp.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[CO][0][1] = rangeCOOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[CO][1][0] = rangeCOBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[CO][1][1] = rangeCOCall.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[CO][2][0] = rangeCOBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[CO][2][1] = rangeCOBet3Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[CO][3][0] = rangeCOBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[CO][3][1] = rangeCOBet4Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[CO][4][0] = rangeCOAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[CO][4][1] = rangeCOAllinCall.lowPlayabilitrhyArrayIndex;

		action.actionArrayPlayability[BU][0][0] = rangeBULimp.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BU][0][1] = rangeBUOpen.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BU][1][0] = rangeBUBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BU][1][1] = rangeBUCall.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BU][2][0] = rangeBUBet3.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BU][2][1] = rangeBUBet3Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BU][3][0] = rangeBUBet4.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BU][3][1] = rangeBUBet4Call.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BU][4][0] = rangeBUAllin.lowPlayabilitrhyArrayIndex;
		action.actionArrayPlayability[BU][4][1] = rangeBUAllinCall.lowPlayabilitrhyArrayIndex;

	}

	// Copy data from HandRange playability index
	private static void copyDataCombos() {
		action.actionArrayCombos[SB][0][0] = rangeSBLimp.combos;
		action.actionArrayCombos[SB][0][1] = rangeSBOpen.combos;
		action.actionArrayCombos[SB][1][0] = rangeSBBet3.combos;
		action.actionArrayCombos[SB][1][1] = rangeSBCall.combos;
		action.actionArrayCombos[SB][2][0] = rangeSBBet3.combos;
		action.actionArrayCombos[SB][2][1] = rangeSBBet3Call.combos;
		action.actionArrayCombos[SB][3][0] = rangeSBBet4.combos;
		action.actionArrayCombos[SB][3][1] = rangeSBBet4Call.combos;
		action.actionArrayCombos[SB][4][0] = rangeSBAllin.combos;
		action.actionArrayCombos[SB][4][1] = rangeSBAllinCall.combos;

		action.actionArrayCombos[BB][0][0] = rangeBBLimp.combos;
		action.actionArrayCombos[BB][0][1] = rangeBBOpen.combos;
		action.actionArrayCombos[BB][1][0] = rangeBBBet3.combos;
		action.actionArrayCombos[BB][1][1] = rangeBBCall.combos;
		action.actionArrayCombos[BB][2][0] = rangeBBBet3.combos;
		action.actionArrayCombos[BB][2][1] = rangeBBBet3Call.combos;
		action.actionArrayCombos[BB][3][0] = rangeBBBet4.combos;
		action.actionArrayCombos[BB][3][1] = rangeBBBet4Call.combos;
		action.actionArrayCombos[BB][4][0] = rangeBBAllin.combos;
		action.actionArrayCombos[BB][4][1] = rangeBBAllinCall.combos;

		action.actionArrayCombos[UTG][0][0] = rangeUTGLimp.combos;
		action.actionArrayCombos[UTG][0][1] = rangeUTGOpen.combos;
		action.actionArrayCombos[UTG][1][0] = rangeUTGBet3.combos;
		action.actionArrayCombos[UTG][1][1] = rangeUTGCall.combos;
		action.actionArrayCombos[UTG][2][0] = rangeUTGBet3.combos;
		action.actionArrayCombos[UTG][2][1] = rangeUTGBet3Call.combos;
		action.actionArrayCombos[UTG][3][0] = rangeUTGBet4.combos;
		action.actionArrayCombos[UTG][3][1] = rangeUTGBet4Call.combos;
		action.actionArrayCombos[UTG][4][0] = rangeUTGAllin.combos;
		action.actionArrayCombos[UTG][4][1] = rangeUTGAllinCall.combos;

		action.actionArrayCombos[MP][0][0] = rangeMPLimp.combos;
		action.actionArrayCombos[MP][0][1] = rangeMPOpen.combos;
		action.actionArrayCombos[MP][1][0] = rangeMPBet3.combos;
		action.actionArrayCombos[MP][1][1] = rangeMPCall.combos;
		action.actionArrayCombos[MP][2][0] = rangeMPBet3.combos;
		action.actionArrayCombos[MP][2][1] = rangeMPBet3Call.combos;
		action.actionArrayCombos[MP][3][0] = rangeMPBet4.combos;
		action.actionArrayCombos[MP][3][1] = rangeMPBet4Call.combos;
		action.actionArrayCombos[MP][4][0] = rangeMPAllin.combos;
		action.actionArrayCombos[MP][4][1] = rangeMPAllinCall.combos;

		action.actionArrayCombos[CO][0][0] = rangeCOLimp.combos;
		action.actionArrayCombos[CO][0][1] = rangeCOOpen.combos;
		action.actionArrayCombos[CO][1][0] = rangeCOBet3.combos;
		action.actionArrayCombos[CO][1][1] = rangeCOCall.combos;
		action.actionArrayCombos[CO][2][0] = rangeCOBet3.combos;
		action.actionArrayCombos[CO][2][1] = rangeCOBet3Call.combos;
		action.actionArrayCombos[CO][3][0] = rangeCOBet4.combos;
		action.actionArrayCombos[CO][3][1] = rangeCOBet4Call.combos;
		action.actionArrayCombos[CO][4][0] = rangeCOAllin.combos;
		action.actionArrayCombos[CO][4][1] = rangeCOAllinCall.combos;

		action.actionArrayCombos[BU][0][0] = rangeBULimp.combos;
		action.actionArrayCombos[BU][0][1] = rangeBUOpen.combos;
		action.actionArrayCombos[BU][1][0] = rangeBUBet3.combos;
		action.actionArrayCombos[BU][1][1] = rangeBUCall.combos;
		action.actionArrayCombos[BU][2][0] = rangeBUBet3.combos;
		action.actionArrayCombos[BU][2][1] = rangeBUBet3Call.combos;
		action.actionArrayCombos[BU][3][0] = rangeBUBet4.combos;
		action.actionArrayCombos[BU][3][1] = rangeBUBet4Call.combos;
		action.actionArrayCombos[BU][4][0] = rangeBUAllin.combos;
		action.actionArrayCombos[BU][4][1] = rangeBUAllinCall.combos;

	}

	// Copy data from HandRange playability index
	private static void copyDataPercentage() {
		action.actionArrayPercentage[SB][0][0] = rangeSBLimp.percentage;
		action.actionArrayPercentage[SB][0][1] = rangeSBOpen.percentage;
		action.actionArrayPercentage[SB][1][0] = rangeSBBet3.percentage;
		action.actionArrayPercentage[SB][1][1] = rangeSBCall.percentage;
		action.actionArrayPercentage[SB][2][0] = rangeSBBet3.percentage;
		action.actionArrayPercentage[SB][2][1] = rangeSBBet3Call.percentage;
		action.actionArrayPercentage[SB][3][0] = rangeSBBet4.percentage;
		action.actionArrayPercentage[SB][3][1] = rangeSBBet4Call.percentage;
		action.actionArrayPercentage[SB][4][0] = rangeSBAllin.percentage;
		action.actionArrayPercentage[SB][4][1] = rangeSBAllinCall.percentage;

		action.actionArrayPercentage[BB][0][0] = rangeBBLimp.percentage;
		action.actionArrayPercentage[BB][0][1] = rangeBBOpen.percentage;
		action.actionArrayPercentage[BB][1][0] = rangeBBBet3.percentage;
		action.actionArrayPercentage[BB][1][1] = rangeBBCall.percentage;
		action.actionArrayPercentage[BB][2][0] = rangeBBBet3.percentage;
		action.actionArrayPercentage[BB][2][1] = rangeBBBet3Call.percentage;
		action.actionArrayPercentage[BB][3][0] = rangeBBBet4.percentage;
		action.actionArrayPercentage[BB][3][1] = rangeBBBet4Call.percentage;
		action.actionArrayPercentage[BB][4][0] = rangeBBAllin.percentage;
		action.actionArrayPercentage[BB][4][1] = rangeBBAllinCall.percentage;

		action.actionArrayPercentage[UTG][0][0] = rangeUTGLimp.percentage;
		action.actionArrayPercentage[UTG][0][1] = rangeUTGOpen.percentage;
		action.actionArrayPercentage[UTG][1][0] = rangeUTGBet3.percentage;
		action.actionArrayPercentage[UTG][1][1] = rangeUTGCall.percentage;
		action.actionArrayPercentage[UTG][2][0] = rangeUTGBet3.percentage;
		action.actionArrayPercentage[UTG][2][1] = rangeUTGBet3Call.percentage;
		action.actionArrayPercentage[UTG][3][0] = rangeUTGBet4.percentage;
		action.actionArrayPercentage[UTG][3][1] = rangeUTGBet4Call.percentage;
		action.actionArrayPercentage[UTG][4][0] = rangeUTGAllin.percentage;
		action.actionArrayPercentage[UTG][4][1] = rangeUTGAllinCall.percentage;

		action.actionArrayPercentage[MP][0][0] = rangeMPLimp.percentage;
		action.actionArrayPercentage[MP][0][1] = rangeMPOpen.percentage;
		action.actionArrayPercentage[MP][1][0] = rangeMPBet3.percentage;
		action.actionArrayPercentage[MP][1][1] = rangeMPCall.percentage;
		action.actionArrayPercentage[MP][2][0] = rangeMPBet3.percentage;
		action.actionArrayPercentage[MP][2][1] = rangeMPBet3Call.percentage;
		action.actionArrayPercentage[MP][3][0] = rangeMPBet4.percentage;
		action.actionArrayPercentage[MP][3][1] = rangeMPBet4Call.percentage;
		action.actionArrayPercentage[MP][4][0] = rangeMPAllin.percentage;
		action.actionArrayPercentage[MP][4][1] = rangeMPAllinCall.percentage;

		action.actionArrayPercentage[CO][0][0] = rangeCOLimp.percentage;
		action.actionArrayPercentage[CO][0][1] = rangeCOOpen.percentage;
		action.actionArrayPercentage[CO][1][0] = rangeCOBet3.percentage;
		action.actionArrayPercentage[CO][1][1] = rangeCOCall.percentage;
		action.actionArrayPercentage[CO][2][0] = rangeCOBet3.percentage;
		action.actionArrayPercentage[CO][2][1] = rangeCOBet3Call.percentage;
		action.actionArrayPercentage[CO][3][0] = rangeCOBet4.percentage;
		action.actionArrayPercentage[CO][3][1] = rangeCOBet4Call.percentage;
		action.actionArrayPercentage[CO][4][0] = rangeCOAllin.percentage;
		action.actionArrayPercentage[CO][4][1] = rangeCOAllinCall.percentage;

		action.actionArrayPercentage[BU][0][0] = rangeBULimp.percentage;
		action.actionArrayPercentage[BU][0][1] = rangeBUOpen.percentage;
		action.actionArrayPercentage[BU][1][0] = rangeBUBet3.percentage;
		action.actionArrayPercentage[BU][1][1] = rangeBUCall.percentage;
		action.actionArrayPercentage[BU][2][0] = rangeBUBet3.percentage;
		action.actionArrayPercentage[BU][2][1] = rangeBUBet3Call.percentage;
		action.actionArrayPercentage[BU][3][0] = rangeBUBet4.percentage;
		action.actionArrayPercentage[BU][3][1] = rangeBUBet4Call.percentage;
		action.actionArrayPercentage[BU][4][0] = rangeBUAllin.percentage;
		action.actionArrayPercentage[BU][4][1] = rangeBUAllinCall.percentage;

	}

	// Copy data from HandRange playaGapsbility index
	private static void copyDataGaps() {
		action.actionArrayGaps[SB][0][0] = rangeSBLimp.gaps;
		action.actionArrayGaps[SB][0][1] = rangeSBOpen.gaps;
		action.actionArrayGaps[SB][1][0] = rangeSBBet3.gaps;
		action.actionArrayGaps[SB][1][1] = rangeSBCall.gaps;
		action.actionArrayGaps[SB][2][0] = rangeSBBet3.gaps;
		action.actionArrayGaps[SB][2][1] = rangeSBBet3Call.gaps;
		action.actionArrayGaps[SB][3][0] = rangeSBBet4.gaps;
		action.actionArrayGaps[SB][3][1] = rangeSBBet4Call.gaps;
		action.actionArrayGaps[SB][4][0] = rangeSBAllin.gaps;
		action.actionArrayGaps[SB][4][1] = rangeSBAllinCall.gaps;

		action.actionArrayGaps[BB][0][0] = rangeBBLimp.gaps;
		action.actionArrayGaps[BB][0][1] = rangeBBOpen.gaps;
		action.actionArrayGaps[BB][1][0] = rangeBBBet3.gaps;
		action.actionArrayGaps[BB][1][1] = rangeBBCall.gaps;
		action.actionArrayGaps[BB][2][0] = rangeBBBet3.gaps;
		action.actionArrayGaps[BB][2][1] = rangeBBBet3Call.gaps;
		action.actionArrayGaps[BB][3][0] = rangeBBBet4.gaps;
		action.actionArrayGaps[BB][3][1] = rangeBBBet4Call.gaps;
		action.actionArrayGaps[BB][4][0] = rangeBBAllin.gaps;
		action.actionArrayGaps[BB][4][1] = rangeBBAllinCall.gaps;

		action.actionArrayGaps[UTG][0][0] = rangeUTGLimp.gaps;
		action.actionArrayGaps[UTG][0][1] = rangeUTGOpen.gaps;
		action.actionArrayGaps[UTG][1][0] = rangeUTGBet3.gaps;
		action.actionArrayGaps[UTG][1][1] = rangeUTGCall.gaps;
		action.actionArrayGaps[UTG][2][0] = rangeUTGBet3.gaps;
		action.actionArrayGaps[UTG][2][1] = rangeUTGBet3Call.gaps;
		action.actionArrayGaps[UTG][3][0] = rangeUTGBet4.gaps;
		action.actionArrayGaps[UTG][3][1] = rangeUTGBet4Call.gaps;
		action.actionArrayGaps[UTG][4][0] = rangeUTGAllin.gaps;
		action.actionArrayGaps[UTG][4][1] = rangeUTGAllinCall.gaps;

		action.actionArrayGaps[MP][0][0] = rangeMPLimp.gaps;
		action.actionArrayGaps[MP][0][1] = rangeMPOpen.gaps;
		action.actionArrayGaps[MP][1][0] = rangeMPBet3.gaps;
		action.actionArrayGaps[MP][1][1] = rangeMPCall.gaps;
		action.actionArrayGaps[MP][2][0] = rangeMPBet3.gaps;
		action.actionArrayGaps[MP][2][1] = rangeMPBet3Call.gaps;
		action.actionArrayGaps[MP][3][0] = rangeMPBet4.gaps;
		action.actionArrayGaps[MP][3][1] = rangeMPBet4Call.gaps;
		action.actionArrayGaps[MP][4][0] = rangeMPAllin.gaps;
		action.actionArrayGaps[MP][4][1] = rangeMPAllinCall.gaps;

		action.actionArrayGaps[CO][0][0] = rangeCOLimp.gaps;
		action.actionArrayGaps[CO][0][1] = rangeCOOpen.gaps;
		action.actionArrayGaps[CO][1][0] = rangeCOBet3.gaps;
		action.actionArrayGaps[CO][1][1] = rangeCOCall.gaps;
		action.actionArrayGaps[CO][2][0] = rangeCOBet3.gaps;
		action.actionArrayGaps[CO][2][1] = rangeCOBet3Call.gaps;
		action.actionArrayGaps[CO][3][0] = rangeCOBet4.gaps;
		action.actionArrayGaps[CO][3][1] = rangeCOBet4Call.gaps;
		action.actionArrayGaps[CO][4][0] = rangeCOAllin.gaps;
		action.actionArrayGaps[CO][4][1] = rangeCOAllinCall.gaps;

		action.actionArrayGaps[BU][0][0] = rangeBULimp.gaps;
		action.actionArrayGaps[BU][0][1] = rangeBUOpen.gaps;
		action.actionArrayGaps[BU][1][0] = rangeBUBet3.gaps;
		action.actionArrayGaps[BU][1][1] = rangeBUCall.gaps;
		action.actionArrayGaps[BU][2][0] = rangeBUBet3.gaps;
		action.actionArrayGaps[BU][2][1] = rangeBUBet3Call.gaps;
		action.actionArrayGaps[BU][3][0] = rangeBUBet4.gaps;
		action.actionArrayGaps[BU][3][1] = rangeBUBet4Call.gaps;
		action.actionArrayGaps[BU][4][0] = rangeBUAllin.gaps;
		action.actionArrayGaps[BU][4][1] = rangeBUAllinCall.gaps;

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
	void newRanges(double  x,String type) {
		String path = EvalData.applicationDirectory ;
		double percentSB = 10 ;
		double percentBB = 10;
		double percentUTG = 8;		
		double percentMP = 12 ;
		double percentCO = 20;
		double percentBU = 35;
		HandRange rangeSB = new HandRange();
		HandRange rangeBB = new HandRange();
		HandRange rangeUTG = new HandRange();
		HandRange rangeMP = new HandRange();
		HandRange rangeCO = new HandRange();
		HandRange rangeBU = new HandRange();
		rangeSB.buildRangePercentage(percentSB * x );
		rangeBB.buildRangePercentage(percentBB );
		rangeUTG.buildRangePercentage(percentUTG);
		rangeMP.buildRangePercentage(percentMP);
		rangeCO.buildRangePercentage(percentCO);
		rangeBU.buildRangePercentage(percentBU);
		rangeSB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\SB\\Limp.ser");
		rangeBB.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BB\\Open.ser");
        rangeUTG.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\UTG\\Limp.ser");
		rangeMP.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\MP\\Limp.ser");
        rangeCO.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\CO\\Limp.ser");
		rangeBU.writeRange("C:\\PeakHoldemDatabase\\" + type + "\\Preflop\\BU\\Limp.ser");

	


	}
}
