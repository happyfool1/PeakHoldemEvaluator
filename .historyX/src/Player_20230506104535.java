//package evaluate_streets;

import java.math.BigDecimal;

/*- **************************************************************************** 
* This Class simulates a players decision during a game.
* getDecision is called whenever the player becomes active ( it is his turn ).
* Player has access to the current conditions facing him through EvalData.
* 		Street - preflop, flop, turn,river, showdown
* 		Bet to him ( check, bet, raise, all-in )
* 		Position ( SB, BB, UTG,MP, CO, BU )
* 		Relative position ( FIRST, FIRST_HU, MIDDLE, LAST, LAST_HU )
* 		Range for preflop play.
* 
* The shipped version of this project has only a primative Player.
* You may wish to make this a real simulation.
* See the Game project ( if it is available yet ).
* It is my project that plays a very sophisticated simulation using external control
* files that can all be edited for experimentation. 
* Uses GTO concepts such as MDF.
* Has many  reports of results like EV analysis.
* Creates Hand History files that you can analyze with Holdem Manager or other
* analysis products.
* Has an interactive mode where you can play against simulator.
* Uses data developed by my Hand History analysis project hand_history_analysis.
* May not be available yet.
* 
* @author PEAK_
*******************************************************************************/
public class Player implements Constants {

	/*- **************************************************************************** 
	 * Data
	 **************************************************************************** */
	private int seat = -1;
	private int handIndex = -1;
	private final HandRange rangeLimp;
	private final HandRange rangeOpen;
	private final HandRange rangeCall;
	private final HandRange rangeBet3;
	private final HandRange rangeBet3Call;
	private final HandRange rangeBet4;
	private final HandRange rangeBet4Call;
	private final HandRange rangeAllin;
	private final HandRange rangeAllinCall;
	// Can raise or can call
	private boolean canRaise = false;
	private boolean canCall = false;

	// Constructor
	Player(HandRange rangeLimp, HandRange rangeOpen, HandRange rangeCall, HandRange rangeBet3,
			HandRange rangeBet3Call, HandRange rangeBet4, HandRange rangeBet4Call, HandRange rangeAllin,
			HandRange rangeAllinCall) {
		this.rangeLimp = rangeLimp;
		this.rangeOpen = rangeOpen;
		this.rangeCall = rangeCall;
		this.rangeBet3 = rangeBet3;
		this.rangeBet3Call = rangeBet3Call;
		this.rangeBet4 = rangeBet4;
		this.rangeBet4Call = rangeBet4Call;
		this.rangeAllin = rangeAllin;
		this.rangeAllinCall = rangeAllinCall;
	}

	/*- **************************************************************************** 
	 * Preflop only.
	 * Make a decision based on action to me: 
	 *  	PREFLOP_LIMP,   PREFLOP_OPEN,    PREFLOP_BET3,
	 *  	 PREFLOP_ALLIN,  PREFLOP_CALL_ALLIN.
	 *   
	 *  The decision is obtained from a range table:
	 *  	0 = not in range
	 *
	 *  HandRange ranges were createa and initialized when this instance of
	 *  Player ars created. 
	 *  
	 *  Arg0 - set
	 **************************************************************************** */
	int getDecision(int seat) {
		this.seat = seat;
		this.handIndex = EvalData.handIndexes[seat];
		System.out.println("Player " + this.seat);
		if (EvalData.betType == PREFLOP_LIMP) {
			if (this.rangeOpen.rangeArray[this.handIndex] > 0) {
				doOpen();
				return PREFLOP_OPEN;

			}
			if (this.rangeLimp.rangeArray[this.handIndex] > 0) {
				doLimp();
				return PREFLOP_LIMP;
			} else {
				doFold();
				return PREFLOP_LIMP;
			}
		}

		if (EvalData.betType == PREFLOP_OPEN) {
			if (this.rangeBet3.rangeArray[this.handIndex] > 0) {
				doBet3();
				return PREFLOP_BET3;
			}
			if (this.rangeCall.rangeArray[this.handIndex] > 0) {
				doCall();
				return PREFLOP_OPEN;

			} else {
				doFold();
				return PREFLOP_OPEN;
			}
		}

		if (EvalData.betType == PREFLOP_BET3) {
			if (this.rangeBet4.rangeArray[this.handIndex] > 0) {
				doBet4();
				return PREFLOP_BET4;
			}
			if (this.rangeBet3Call.rangeArray[this.handIndex] > 0) {
				doCallBet3();
				return PREFLOP_BET3;
			} else {
				doFold();
				return PREFLOP_BET3;
			}
		}

		if (EvalData.betType == PREFLOP_BET4) {
			if (this.rangeAllin.rangeArray[this.handIndex] > 0) {
				doAllin();
				return PREFLOP_ALLIN;
			}
			if (this.rangeBet4Call.rangeArray[this.handIndex] > 0) {
				doCallBet4();
				return PREFLOP_BET4;
			} else {
				doFold();
				return PREFLOP_BET4;
			}
		}

		if (EvalData.betType == PREFLOP_ALLIN) {
			if (this.rangeAllinCall.rangeArray[this.handIndex] > 0) {
				doAllinCall();
				return PREFLOP_ALLIN;
			} else {
				doFold();
				return PREFLOP_ALLIN;
			}
		}

		if (EvalData.betType == PREFLOP_CALL_ALLIN) {
			if (this.rangeAllin.rangeArray[this.handIndex] > 0) {
				doAllinCall();
				return PREFLOP_ALLIN;
			} else {
				doFold();
				return PREFLOP_ALLIN;
			}
		}
		Logger.log("ERROR Action getDecision(int EvalData.betType ) EvalData.betType  invalid "
				+ PLAYER_ACTIONS_ST[EvalData.betType]);
		Crash.log("Program bug ");
		return -1;
	}

	/*- **************************************************************************** 
	 * Do Fold
	 **************************************************************************** */
	private void doFold() {
		if (EvalData.playerFolded[this.seat]) {
			Crash.log("$$$");
		}
		++EvalData.foldCount;
		EvalData.isFold[0][this.seat][EvalData.orbit] = true;
		++EvalData.foldedPreflop[EvalData.orbit][this.seat];
		EvalData.playerFoldedPreflop[this.seat] = true;
		EvalData.playerFolded[this.seat] = true;
		EvalData.pot$[0][this.seat][EvalData.orbit] = EvalData.potBD;
		EvalData.moneyIn$[0][this.seat][EvalData.orbit] = zeroBD;
		System.out.println("doFold() " + this.seat);
	}

	/*- **************************************************************************** 
	 * Do Limp
	 **************************************************************************** */
	private void doLimp() {
		if (EvalData.playerFolded[this.seat]) {
			Crash.log("$$$");
		}
		// Flags and counts
		++EvalData.limpCount;
		EvalData.isCall[0][this.seat][EvalData.orbit] = true;
		EvalData.isLimp[0][this.seat][EvalData.orbit] = true;
		EvalData.playerLimpedPreflop[this.seat] = true;
		// Money
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(EvalData.BBBetBD);
		EvalData.calledTo$[0][this.seat][EvalData.orbit] = EvalData.BBBetBD;
		EvalData.callBD[0][this.seat][EvalData.orbit] = EvalData.BBBetBD;
		EvalData.potBD = EvalData.potBD.add(EvalData.BBBetBD);
		EvalData.moneyIn$[0][this.seat][EvalData.orbit] = EvalData.BBBetBD;
		EvalData.moneyInBD[this.seat] = EvalData.moneyInBD[this.seat].add(EvalData.BBBetBD);
		System.out.println("doLimp() " + this.seat + EvalData.moneyInBD[this.seat] + " " +
				EvalData.potBD + "" + EvalData.stackBD[this.seat]);
	}

	/*- **************************************************************************** 
	 * Do Open
	 **************************************************************************** */
	private void doOpen() {
		if (EvalData.playerFolded[this.seat]) {
			Crash.log("$$$");
		}
		canRaiseOrCall();
		if (canRaise) {
			doRaises(EvalData.preflopBet2SizeBD);
		} else {
			doFold();
		}
	}

	/*- **************************************************************************** 
	 * Do Call
	 **************************************************************************** */
	private void doCall() {
		if (EvalData.playerFolded[this.seat]) {
			Crash.log("$$$");
		}
		canRaiseOrCall();
		if (canCall) {
			doCalls();
		} else {
			doFold();
		}
	}

	/*- **************************************************************************** 
	 * Do Bet 3
	 **************************************************************************** */
	private void doBet3() {
		if (EvalData.playerFolded[this.seat]) {
			Crash.log("$$$");
		}
		canRaiseOrCall();
		if (canRaise) {
			EvalData.isRaise[0][this.seat][EvalData.orbit] = true;
			doRaises(EvalData.potBD.multiply(EvalData.betMultiplierBD));

		} else {
			doFold();
		}
	}

	/*- **************************************************************************** 
	 * Do Call bet 3
	 **************************************************************************** */
	private void doCallBet3() {
		if (EvalData.playerFolded[this.seat]) {
			Crash.log("$$$");
		}
		canRaiseOrCall();
		if (canCall) {
			doCalls();
		} else {
			doFold();
		}
	}

	/*- **************************************************************************** 
	 * Do Bet 4
	 **************************************************************************** */
	private void doBet4() {
		if (EvalData.playerFolded[this.seat]) {
			Crash.log("$$$");
		}
		canRaiseOrCall();
		if (canRaise) {
			doRaises(EvalData.potBD.multiply(EvalData.betMultiplierBD));
		} else {
			doFold();
		}
	}

	/*- **************************************************************************** 
	 * Do Bet 4
	 **************************************************************************** */
	private void doCallBet4() {
		if (EvalData.playerFolded[this.seat]) {
			Crash.log("$$$");
		}
		canRaiseOrCall();
		if (canCall) {
			doCalls();
		} else {
			doFold();
		}
	}

	/*- **************************************************************************** 
	 * DoAll-In
	 **************************************************************************** */
	private void doAllin() {
		if (EvalData.playerFolded[this.seat] || EvalData.playerAllin[this.seat]) {
			Crash.log("$$$");
		}
		var call = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);
		var bet = EvalData.stackBD[this.seat].add(EvalData.betToMeBD);
		// Flags and counts
		EvalData.isRaise[0][this.seat][EvalData.orbit] = true;
		++EvalData.allinCount;
		EvalData.playerAllin[this.seat] = true;
		EvalData.isAllin[0][this.seat][EvalData.orbit] = true;
// Money
		EvalData.moneyInBD[this.seat] = bet;
		EvalData.allin$[0][this.seat][EvalData.orbit] = bet;
		EvalData.betBD[0][this.seat][EvalData.orbit] = bet;
		EvalData.moneyIn$[0][this.seat][EvalData.orbit] = bet;
		EvalData.raisedToBD[0][this.seat][EvalData.orbit] = EvalData.betNowBD.add(bet);
		EvalData.betNowBD = EvalData.betNowBD.add(bet);
		EvalData.moneyInBD[this.seat] = EvalData.moneyInBD[this.seat];
		EvalData.stackBD[this.seat] = zeroBD;
		
	
		
		
		
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(bet);
		EvalData.potBD = EvalData.potBD.add(temp);
		EvalData.betBD[0][this.seat][EvalData.orbit] = bet;
		EvalData.raisedToBD[0][this.seat][EvalData.orbit] = temp;
		EvalData.betNowBD = temp;

		System.out.println("doRaises() " + this.seat + " " + bet + " " + temp + " " + EvalData.stackBD[this.seat]);
	}

	/*- **************************************************************************** 
	 * DoAll-In Call
	 **************************************************************************** */
	private void doAllinCall() {
		if (EvalData.playerFolded[this.seat] || EvalData.playerAllin[this.seat]) {
			Crash.log("$$$");
		}
		var call = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);
		++EvalData.allinCount;
		EvalData.playerAllin[this.seat] = true;
		call = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);

		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(call);
		EvalData.isAllin[0][this.seat][EvalData.orbit] = true;
		EvalData.isCall[0][this.seat][EvalData.orbit] = true;
		EvalData.moneyIn[this.seat] = EvalData.moneyIn[this.seat].add(EvalData.stackBD[this.seat]);
		EvalData.callBD[0][this.seat][EvalData.orbit] = call;
	}

	/*- **************************************************************************** 
		 *  Do calls
		 **************************************************************************** */
	private void doCalls() {
		var call = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);
		// Flags and counts
		++EvalData.allinCount;
		EvalData.playerAllin[this.seat] = true;
		EvalData.isCall[0][this.seat][EvalData.orbit] = true;
		// Money
		EvalData.moneyInBD[this.seat] = call;
		EvalData.moneyIn$[0][this.seat][EvalData.orbit] = call;
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(call);
		EvalData.betBD[0][this.seat][EvalData.orbit] = call;
		EvalData.betNowBD = EvalData.betNowBD.add(call);
		EvalData.potBD = EvalData.potBD.add(call);
		EvalData.callBD[0][this.seat][EvalData.orbit] = call;

		System.out.println("doCalls() " + this.seat + " " + call + " " + EvalData.stackBD[this.seat]);
	}

	/*- **************************************************************************** 
	 *  Do raises
	 * Arg- - bet size
	 **************************************************************************** */
	private void doRaises(BigDecimal bet) {
		var call = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);\
		var temp = bet.add(call);
		// Flags and counts
		EvalData.isRaise[0][this.seat][EvalData.orbit] = true;
		// Money
		EvalData.moneyInBD[this.seat] = temp;
		EvalData.moneyIn$[0][this.seat][EvalData.orbit] = temp;
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(bet);
		EvalData.potBD = EvalData.potBD.add(temp);
		EvalData.betBD[0][this.seat][EvalData.orbit] = bet;
		EvalData.raisedToBD[0][this.seat][EvalData.orbit] = temp;
		EvalData.betNowBD = temp;

		System.out.println("doRaises() " + this.seat + " " + bet + " " + temp + " " + EvalData.stackBD[this.seat]);
					}

	/*- **************************************************************************** 
	 *  Re-buy if low on chips.
	 *  Called before the start of every game. 
	 **************************************************************************** */
	private void checkRebuy() {
		if (EvalData.stackBD[this.seat].compareTo(EvalData.maxStackBD) == 1) {
			EvalData.stackBD[this.seat] = EvalData.buyinBD;
		}
		if (EvalData.stackBD[this.seat].compareTo(EvalData.rebuyBD) == -1) {
			EvalData.stackBD[this.seat] = EvalData.buyinBD.subtract(EvalData.stackBD[this.seat]); // Back to buy in
		}
	}

	/*- **************************************************************************** 
	 * Can raise or call?
	 * Sets boolean canRaise and canCall
	 **************************************************************************** */
	void canRaiseOrCall() {
		this.canRaise = false;
		this.canCall = false;
		var temp = EvalData.stackBD[this.seat].subtract(EvalData.betNowBD).subtract(EvalData.moneyInBD[this.seat]);
		if (temp.compareTo(zeroBD) == 1) {
			this.canRaise = true;
		}
		// final boolean canCall = Table.betNow - Table.moneyIn[Table.seat] <=
		// Table.stack[Table.seat];
		temp = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);
		if (temp.compareTo(EvalData.stackBD[this.seat]) == -1) {
			this.canCall = true;
		}
	}
}
