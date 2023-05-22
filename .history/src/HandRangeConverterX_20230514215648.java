//package evaluate_streets;
/*-  ******************************************************************************
 *  @author PEAK_ 
 * ****************************************************************************** */

public class HandRangeConverterX implements Constants {

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

	private static HandRangeMultiple action = new HandRangeMultiple();
	private static String type = "?";

	public static void main(String[] s0) {
		type = "HeroX";
		createRanges();
		rangeBULimp.debug = true;
		rangeBUOpen.debug = true;
		doConversion();
		rangeBULimp.debug = false;
		rangeBUOpen.debug = false;
		action.show(type);
		type = "Test";
		testRanges();
		createRanges();
		doConversion();
	//	action.show(type);
		
	
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
		copyDataGaps();
		copyDataRanges();
	
	}

	

	

	// Copy data from HandRange playaGapsbility index
	private static void copyDataGaps() {
		// BET1
		action.gapsArrayRaise[SB][0] = rangeSBOpen.gaps;
		action.gapsArrayCall[SB][0] = rangeSBLimp.gaps;
		// BET2
		action.gapsArrayRaise[SB][1] = rangeSBBet3.gaps;
		action.gapsArrayCall[SB][1] = rangeSBCall.gaps;
		// BET3
		action.gapsArrayRaise[SB][2] = rangeSBBet4.gaps;
		action.gapsArrayCall[SB][2] = rangeSBBet3Call.gaps;
		// BET4
		action.gapsArrayRaise[SB][3] = rangeSBAllin.gaps;
		action.gapsArrayCall[SB][3] = rangeSBBet4Call.gaps;
		// ALLIN
		action.gapsArrayRaise[SB][4] = rangeSBAllin.gaps;
		action.gapsArrayCall[SB][4] = rangeSBAllinCall.gaps;

		// BET1
		action.gapsArrayRaise[SB][0] = rangeSBOpen.gaps;
		action.gapsArrayCall[SB][0] = rangeSBLimp.gaps;
		// BET2
		action.gapsArrayRaise[SB][1] = rangeSBBet3.gaps;
		action.gapsArrayCall[SB][1] = rangeSBCall.gaps;
		// BET3
		action.gapsArrayRaise[SB][2] = rangeSBBet4.gaps;
		action.gapsArrayCall[SB][2] = rangeSBBet3Call.gaps;
		// BET4
		action.gapsArrayRaise[SB][3] = rangeSBAllin.gaps;
		action.gapsArrayCall[SB][3] = rangeSBBet4Call.gaps;
		// ALLIN
		action.gapsArrayRaise[SB][4] = rangeSBAllin.gaps;
		action.gapsArrayCall[SB][4] = rangeSBAllinCall.gaps;

		// BET1
		action.gapsArrayRaise[BB][0] = rangeBBOpen.gaps;
		action.gapsArrayCall[BB][0] = rangeBBLimp.gaps;
		// BET2
		action.gapsArrayRaise[BB][1] = rangeBBBet3.gaps;
		action.gapsArrayCall[BB][1] = rangeBBCall.gaps;
		// BET3
		action.gapsArrayRaise[BB][2] = rangeBBBet4.gaps;
		action.gapsArrayCall[BB][2] = rangeBBBet3Call.gaps;
		// BET4
		action.gapsArrayRaise[BB][3] = rangeBBAllin.gaps;
		action.gapsArrayCall[BB][3] = rangeBBBet4Call.gaps;
		// ALLIN
		action.gapsArrayRaise[BB][4] = rangeBBAllin.gaps;
		action.gapsArrayCall[BB][4] = rangeBBAllinCall.gaps;

		// BET1
		action.gapsArrayRaise[UTG][0] = rangeUTGOpen.gaps;
		action.gapsArrayCall[UTG][0] = rangeUTGLimp.gaps;
		// BET2
		action.gapsArrayRaise[UTG][1] = rangeUTGBet3.gaps;
		action.gapsArrayCall[UTG][1] = rangeUTGCall.gaps;
		// BET3
		action.gapsArrayRaise[UTG][2] = rangeUTGBet4.gaps;
		action.gapsArrayCall[UTG][2] = rangeUTGBet3Call.gaps;
		// BET4
		action.gapsArrayRaise[UTG][3] = rangeUTGAllin.gaps;
		action.gapsArrayCall[UTG][3] = rangeUTGBet4Call.gaps;
		// ALLIN
		action.gapsArrayRaise[UTG][4] = rangeUTGAllin.gaps;
		action.gapsArrayCall[UTG][4] = rangeUTGAllinCall.gaps;
		// BET1
		action.gapsArrayRaise[MP][0] = rangeMPOpen.gaps;
		action.gapsArrayCall[MP][0] = rangeMPLimp.gaps;
		// BET2
		action.gapsArrayRaise[MP][1] = rangeMPBet3.gaps;
		action.gapsArrayCall[MP][1] = rangeMPCall.gaps;
		// BET3
		action.gapsArrayRaise[MP][2] = rangeMPBet4.gaps;
		action.gapsArrayCall[MP][2] = rangeMPBet3Call.gaps;
		// BET4
		action.gapsArrayRaise[MP][3] = rangeMPAllin.gaps;
		action.gapsArrayCall[MP][3] = rangeMPBet4Call.gaps;
		// ALLIN
		action.gapsArrayRaise[MP][4] = rangeMPAllin.gaps;
		action.gapsArrayCall[MP][4] = rangeMPAllinCall.gaps;
		// BET1
		action.gapsArrayRaise[CO][0] = rangeCOOpen.gaps;
		action.gapsArrayCall[CO][0] = rangeCOLimp.gaps;
		// BET2
		action.gapsArrayRaise[CO][1] = rangeCOBet3.gaps;
		action.gapsArrayCall[CO][1] = rangeCOCall.gaps;
		// BET3
		action.gapsArrayRaise[CO][2] = rangeCOBet4.gaps;
		action.gapsArrayCall[CO][2] = rangeCOBet3Call.gaps;
		// BET4
		action.gapsArrayRaise[CO][3] = rangeCOAllin.gaps;
		action.gapsArrayCall[CO][3] = rangeCOBet4Call.gaps;
		// ALLIN
		action.gapsArrayRaise[CO][4] = rangeCOAllin.gaps;
		action.gapsArrayCall[CO][4] = rangeCOAllinCall.gaps;

		// BET1
		action.gapsArrayRaise[BU][0] = rangeBUOpen.gaps;
		action.gapsArrayCall[BU][0] = rangeBULimp.gaps;
		// BET2
		action.gapsArrayRaise[BU][1] = rangeBUBet3.gaps;
		action.gapsArrayCall[BU][1] = rangeBUCall.gaps;
		// BET3
		action.gapsArrayRaise[BU][2] = rangeBUBet4.gaps;
		action.gapsArrayCall[BU][2] = rangeBUBet3Call.gaps;
		// BET4
		action.gapsArrayRaise[BU][3] = rangeBUAllin.gaps;
		action.gapsArrayCall[BU][3] = rangeBUBet4Call.gaps;
		// ALLIN
		action.gapsArrayRaise[BU][4] = rangeBUAllin.gaps;
		action.gapsArrayCall[BU][4] = rangeBUAllinCall.gaps;

	}
// Copy data from HandRange playaGapsbility index
private static void copyDataRanges() {
	// BET1
	action.rangeArrayRaise[SB][0] = rangeSBOpen;
	action.rangeArrayCall[SB][0] = rangeSBLimp;
	// BET2
	action.rangeArrayRaise[SB][1] = rangeSBBet3;
	action.rangeArrayCall[SB][1] = rangeSBCall;
	// BET3
	action.rangeArrayRaise[SB][2] = rangeSBBet4;
	action.rangeArrayCall[SB][2] = rangeSBBet3Call;
	// BET4
	action.rangeArrayRaise[SB][3] = rangeSBAllin;
	action.rangeArrayCall[SB][3] = rangeSBBet4Call;
	// ALLIN
	action.rangeArrayRaise[SB][4] = rangeSBAllin;
	action.rangeArrayCall[SB][4] = rangeSBAllinCall;

	// BET1
	action.rangeArrayRaise[SB][0] = rangeSBOpen;
	action.rangeArrayCall[SB][0] = rangeSBLimp;
	// BET2
	action.rangeArrayRaise[SB][1] = rangeSBBet3;
	action.rangeArrayCall[SB][1] = rangeSBCall;
	// BET3
	action.rangeArrayRaise[SB][2] = rangeSBBet4;
	action.rangeArrayCall[SB][2] = rangeSBBet3Call;
	// BET4
	action.rangeArrayRaise[SB][3] = rangeSBAllin;
	action.rangeArrayCall[SB][3] = rangeSBBet4Call;
	// ALLIN
	action.rangeArrayRaise[SB][4] = rangeSBAllin;
	action.rangeArrayCall[SB][4] = rangeSBAllinCall;

	// BET1
	action.rangeArrayRaise[BB][0] = rangeBBOpen;
	action.rangeArrayCall[BB][0] = rangeBBLimp;
	// BET2
	action.rangeArrayRaise[BB][1] = rangeBBBet3;
	action.rangeArrayCall[BB][1] = rangeBBCall;
	// BET3
	action.rangeArrayRaise[BB][2] = rangeBBBet4;
	action.rangeArrayCall[BB][2] = rangeBBBet3Call;
	// BET4
	action.rangeArrayRaise[BB][3] = rangeBBAllin;
	action.rangeArrayCall[BB][3] = rangeBBBet4Call;
	// ALLIN
	action.rangeArrayRaise[BB][4] = rangeBBAllin;
	action.rangeArrayCall[BB][4] = rangeBBAllinCall;

	// BET1
	action.rangeArrayRaise[UTG][0] = rangeUTGOpen;
	action.rangeArrayCall[UTG][0] = rangeUTGLimp;
	// BET2
	action.rangeArrayRaise[UTG][1] = rangeUTGBet3;
	action.rangeArrayCall[UTG][1] = rangeUTGCall;
	// BET3
	action.rangeArrayRaise[UTG][2] = rangeUTGBet4;
	action.rangeArrayCall[UTG][2] = rangeUTGBet3Call;
	// BET4
	action.rangeArrayRaise[UTG][3] = rangeUTGAllin;
	action.rangeArrayCall[UTG][3] = rangeUTGBet4Call;
	// ALLIN
	action.rangeArrayRaise[UTG][4] = rangeUTGAllin;
	action.rangeArrayCall[UTG][4] = rangeUTGAllinCall;
	// BET1
	action.rangeArrayRaise[MP][0] = rangeMPOpen;
	action.rangeArrayCall[MP][0] = rangeMPLimp;
	// BET2
	action.rangeArrayRaise[MP][1] = rangeMPBet3;
	action.rangeArrayCall[MP][1] = rangeMPCall;
	// BET3
	action.rangeArrayRaise[MP][2] = rangeMPBet4;
	action.rangeArrayCall[MP][2] = rangeMPBet3Call;
	// BET4
	action.rangeArrayRaise[MP][3] = rangeMPAllin;
	action.rangeArrayCall[MP][3] = rangeMPBet4Call;
	// ALLIN
	action.rangeArrayRaise[MP][4] = rangeMPAllin;
	action.rangeArrayCall[MP][4] = rangeMPAllinCall;
	// BET1
	action.rangeArrayRaise[CO][0] = rangeCOOpen;
	action.rangeArrayCall[CO][0] = rangeCOLimp;
	// BET2
	action.rangeArrayRaise[CO][1] = rangeCOBet3;
	action.rangeArrayCall[CO][1] = rangeCOCall;
	// BET3
	action.rangeArrayRaise[CO][2] = rangeCOBet4;
	action.rangeArrayCall[CO][2] = rangeCOBet3Call;
	// BET4
	action.rangeArrayRaise[CO][3] = rangeCOAllin;
	action.rangeArrayCall[CO][3] = rangeCOBet4Call;
	// ALLIN
	action.rangeArrayRaise[CO][4] = rangeCOAllin;
	action.rangeArrayCall[CO][4] = rangeCOAllinCall;

	// BET1
	action.rangeArrayRaise[BU][0] = rangeBUOpen;
	action.rangeArrayCall[BU][0] = rangeBULimp;
	// BET2
	action.rangeArrayRaise[BU][1] = rangeBUBet3;
	action.rangeArrayCall[BU][1] = rangeBUCall;
	// BET3
	action.rangeArrayRaise[BU][2] = rangeBUBet4;
	action.rangeArrayCall[BU][2] = rangeBUBet3Call;
	// BET4
	action.rangeArrayRaise[BU][3] = rangeBUAllin;
	action.rangeArrayCall[BU][3] = rangeBUBet4Call;
	// ALLIN
	action.rangeArrayRaise[BU][4] = rangeBUAllin;
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
