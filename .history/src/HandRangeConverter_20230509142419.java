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

	public static void main(String[] s0) {
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
		rangeSBLimp.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\SB\\Limp.ser");
		rangeSBOpen.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\SB\\Open.ser");
		rangeSBCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\SB\\Call.ser");
		rangeSBBet3.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\SB\\Bet3.ser");
		rangeSBBet3Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\SB\\Bet3Call.ser");
		rangeSBBet4.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\SB\\Bet4.ser");
		rangeSBBet4Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\SB\\Bet4Call.ser");
		rangeSBAllin.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\SB\\Allin.ser");
		rangeSBAllinCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\SB\\AllinCall.ser");

		rangeBBLimp.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BB\\Limp.ser");
		rangeBBOpen.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BB\\Open.ser");
		rangeBBCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BB\\Call.ser");
		rangeBBBet3.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BB\\Bet3.ser");
		rangeBBBet3Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BB\\Bet3Call.ser");
		rangeBBBet4.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BB\\Bet4.ser");
		rangeBBBet4Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BB\\Bet4Call.ser");
		rangeBBAllin.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BB\\Allin.ser");
		rangeBBAllinCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BB\\AllinCall.ser");

		rangeUTGLimp.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\UTG\\Limp.ser");
		rangeUTGOpen.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\UTG\\Open.ser");
		rangeUTGCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\UTG\\Call.ser");
		rangeUTGBet3.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\UTG\\Bet3.ser");
		rangeUTGBet3Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\UTG\\Bet3Call.ser");
		rangeUTGBet4.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\UTG\\Bet4.ser");
		rangeUTGBet4Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\UTG\\Bet4Call.ser");
		rangeUTGAllin.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\UTG\\Allin.ser");
		rangeUTGAllinCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\UTG\\AllinCall.ser");

		rangeMPLimp.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\MP\\Limp.ser");
		rangeMPOpen.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\MP\\Open.ser");
		rangeMPCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\MP\\Call.ser");
		rangeMPBet3.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\MP\\Bet3.ser");
		rangeMPBet3Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\MP\\Bet3Call.ser");
		rangeMPBet4.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\MP\\Bet4.ser");
		rangeMPBet4Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\MP\\Bet4Call.ser");
		rangeMPAllin.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\MP\\Allin.ser");
		rangeMPAllinCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\MP\\AllinCall.ser");

		rangeCOLimp.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\CO\\Limp.ser");
		rangeCOOpen.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\CO\\Open.ser");
		rangeCOCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\CO\\Call.ser");
		rangeCOBet3.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\CO\\Bet3.ser");
		rangeCOBet3Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\CO\\Bet3Call.ser");
		rangeCOBet4.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\CO\\Bet4.ser");
		rangeCOBet4Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\CO\\Bet4Call.ser");
		rangeCOAllin.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\CO\\Allin.ser");
		rangeCOAllinCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\CO\\AllinCall.ser");

		rangeBULimp.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BU\\Limp.ser");
		rangeBUOpen.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BU\\Open.ser");
		rangeBUCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BU\\Call.ser");
		rangeBUBet3.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BU\\Bet3.ser");
		rangeBUBet3Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BU\\Bet3Call.ser");
		rangeBUBet4.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BU\\Bet4.ser");
		rangeBUBet4Call.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BU\\Bet4Call.ser");
		rangeBUAllin.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BU\\Allin.ser");
		rangeBUAllinCall.readRange(EvalData.applicationDirectory + "Hero\\Preflop\\BU\\AllinCall.ser");

	}

	// Do conversion
private static void doConversion() {
	int ndx1 = 0;
	int ndx2 = 0;
	
	 ndx1 = rangeSBOpen.findLast();
	 action.actionArray[SB][0][0] = ndx1;
	 ndx2 = rangeSBLimp.findLast();
	 action.actionArray[SB][0][1] = ndx2;

	 ndx1 = rangeSBBet3.findLast();
	 action.actionArray[SB][2][0] = ndx1;
	 ndx2 = rangeSBCall.findLast();
	 action.actionArray[SB][3][1] = ndx2;

	 ndx1 = rangeSBBet4.findLast();
	 action.actionArray[SB][2][0] = ndx1;
	 ndx2 = rangeSBBet4Call.findLast();
	 action.actionArray[SB][3][1] = ndx2;

	 ndx1 = rangeSBAllin.findLast();
	 action.actionArray[SB][2][0] = ndx1;
	 ndx2 = rangeSBAllinCall.findLast();
	 action.actionArray[SB][3][1] = ndx2;

	 

	action.actionArray[SB][][0] = 
}

}
