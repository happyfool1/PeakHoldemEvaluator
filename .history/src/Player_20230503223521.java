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
	// Internal calculations
	private BigDecimal betNow = zeroBD;
	private BigDecimal call = zeroBD;
	private final Action action = new Action();
	private BigDecimal moneyIn = zeroBD; // How much player has in the pot now

	private BigDecimal betToMe = zeroBD;
	private boolean canRaise = false;
	private boolean canCall = false;

	// Constructor
	Player(int seat, int handIndex, HandRange rangeLimp, HandRange rangeOpen, HandRange rangeCall, HandRange rangeBet3,
			HandRange rangeBet3Call, HandRange rangeBet4, HandRange rangeBet4Call, HandRange rangeAllin,
			HandRange rangeAllinCall) {
		this.seat = seat;
		this.handIndex = handIndex;
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
	 *  Arg0 - Action to me 
	 *  Arg1 - Size of bet to me
	 *  Arg2 - 
	 **************************************************************************** */
	Action getDecision(int toMe, BigDecimal betToMe, BigDecimal betNow) {
		checkRebuy();
		this.betToMe = betToMe;
		this.betNow = betNow;
		action.betNow = betToMe;
		if (toMe == PREFLOP_LIMP) {
			if (this.rangeOpen.rangeArray[this.handIndex] > 0) {
				doOpen();
				this.action.actionAfter = PREFLOP_OPEN;
				return this.action;
			}
			if (this.rangeLimp.rangeArray[this.handIndex] > 0) {
				doLimp();
				this.action.actionAfter = PREFLOP_LIMP;
				return this.action;
			} else {
				doFold();
				this.action.actionAfter = PREFLOP_LIMP;
				return this.action;
			}
		}

		if (toMe == PREFLOP_OPEN) {
			if (this.rangeBet3.rangeArray[this.handIndex] > 0) {
				doBet3();
				this.action.actionAfter = PREFLOP_BET3;
				return this.action;
			}
			if (this.rangeCall.rangeArray[this.handIndex] > 0) {
				doCall();
				this.action.actionAfter = PREFLOP_OPEN;
				return this.action;
			} else {
				doFold();
				this.action.actionAfter = PREFLOP_OPEN;
				return this.action;
			}
		}

		if (toMe == PREFLOP_BET3) {
			if (this.rangeBet4.rangeArray[this.handIndex] > 0) {
				doBet4();
				this.action.actionAfter = PREFLOP_BET4;
				return this.action;
			}
			if (this.rangeBet3Call.rangeArray[this.handIndex] > 0) {
				doCallBet3();
				this.action.actionAfter = PREFLOP_BET3;
				return this.action;
			} else {
				doFold();
				this.action.actionAfter = PREFLOP_BET3;
				return this.action;
			}
		}

		if (toMe == PREFLOP_BET4) {
			if (this.rangeAllin.rangeArray[this.handIndex] > 0) {
				doAllin();
				this.action.actionAfter = PREFLOP_ALLIN;
				return this.action;
			}
			if (this.rangeBet4Call.rangeArray[this.handIndex] > 0) {
				doCallBet4();
				this.action.actionAfter = PREFLOP_BET4;
				return this.action;
			} else {
				doFold();
				this.action.actionAfter = PREFLOP_BET4;
				return this.action;
			}
		}

		if (toMe == PREFLOP_ALLIN) {
			if (this.rangeAllinCall.rangeArray[this.handIndex] > 0) {
				doAllinCall();
				this.action.actionAfter = PREFLOP_ALLIN;
				return this.action;
			} else {
				doFold();
				this.action.actionAfter = PREFLOP_ALLIN;
				return this.action;
			}
		}

		if (toMe == PREFLOP_CALL_ALLIN) {
			if (this.rangeAllin.rangeArray[this.handIndex] > 0) {
				doAllinCall();
				this.action.actionAfter = PREFLOP_ALLIN;
				return this.action;
			} else {
				doFold();
				this.action.actionAfter = PREFLOP_ALLIN;
				return this.action;
			}
		}
		Logger.log("ERROR Action getDecision(int toMe) toMe invalid " + PLAYER_ACTIONS_ST[toMe]);
		Crash.log("Program bug ");
		return this.action;
	}

	/*- **************************************************************************** 
	 * Do Fold
	 **************************************************************************** */
	private void doFold() {
		if (EvalData.playerFolded[this.seat]) {
			Crash.log("$$$");
		}
		++EvalData.foldCount;
		EvalData.playerFolded[this.seat] = true;
		this.action.action = FOLD;
		this.action.moneyToPot = zeroBD;
		this.action.betNow = this.betToMe;
		EvalData.isFold[0][seat][EvalData.orbit] = true;
		EvalData.pot$[0][seat][EvalData.orbit] = EvalData.potBD;
		EvalData.moneyIn$[0][seat][EvalData.orbit] = zeroBD;
		++EvalData.foldedPreflop[EvalData.orbit][seat];
		EvalData.playerFoldedPreflop[seat] = true;
		System.out.println("ff " + EvalData.foldCount
				+ new StringBuilder().append("//doFold() ").append(this.seat).append(" ")
						.append(EvalData.orbit).append(" ").append(this.action.moneyToPot).append(" ")
						.append(this.moneyIn)
						.append(" ").append(EvalData.stackBD[this.seat]).toString());
	}

	/*- **************************************************************************** 
	 * Do Limp
	 **************************************************************************** */
	private void doLimp() {
		if (EvalData.playerFolded[this.seat]) {
			Crash.log("$$$");
		}
		++EvalData.limpCount;
		this.action.action = LIMP;
		this.action.moneyToPot = EvalData.BBBetBD;
		this.moneyIn = EvalData.BBBetBD;
		this.action.betNow =  EvalData.BBBetBD;
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(EvalData.BBBetBD);
		EvalData.isLimp[0][seat][EvalData.orbit] = true;
		EvalData.playerLimpedPreflop[seat] = true;
		EvalData.calledTo$[0][seat][EvalData.orbit] = EvalData.BBBetBD;
		EvalData.isCall[0][seat][EvalData.orbit] = true;
		System.out.println(new StringBuilder().append("//doLimp() ").append(this.seat).append(" ")
				.append(EvalData.orbit).append(" ").append(this.action.moneyToPot).append(" ").append(this.moneyIn)
				.append(" ").append(EvalData.stackBD[this.seat]).toString());
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
			this.action.action = OPEN;
			final var limpCountBD = new BigDecimal(EvalData.limpCount);
			var bet = EvalData.SBBetBD.multiply(limpCountBD);
			bet = bet.add(EvalData.preflopBet2SizeBD);
			this.action.moneyToPot = this.action.moneyToPot.add(bet);
			this.moneyIn = bet;
			this.action.betNow = betNow;
			EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(bet);
			EvalData.potBD = EvalData.potBD.add(bet);
			System.out.println(new StringBuilder().append("//doOpen() ").append(this.seat).append(" ")
					.append(EvalData.orbit).append(" ").append(this.action.moneyToPot).append(" ").append(this.moneyIn)
					.append(" ").append(EvalData.stackBD[this.seat]).toString());
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
			this.call = this.betNow;
			this.action.moneyToPot = this.moneyIncall;
			this.moneyIn = this.call;
			this.action.betNow = this.betToMe;
			EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(this.call);
			this.action.action = CALL;
			EvalData.potBD = EvalData.potBD.add(call);
			System.out.println(new StringBuilder().append("//doCall() ").append(this.seat).append(" ")
					.append(EvalData.orbit).append(" ").append(this.action.moneyToPot).append(" ").append(this.moneyIn)
					.append(" ").append(EvalData.stackBD[this.seat]).toString());
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
			this.action.action = BET3;
			var bet = zeroBD;
			var temp = zeroBD;
			this.call = this.betNow.subtract(this.moneyIn);
			bet = EvalData.potBD.multiply(EvalData.betMultiplierBD);
			temp = bet.subtract(this.call);
			this.action.moneyToPot = temp;
			this.moneyIn = this.moneyIn.add(temp);
			this.action.betNow = bet.add(this.betToMe);
			EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(temp);
			EvalData.potBD = EvalData.potBD.add(temp);
			System.out.println(new StringBuilder().append("//doBet3() ").append(this.seat).append(" ")
					.append(EvalData.orbit).append(" ").append(this.action.moneyToPot).append(" ").append(this.moneyIn)
					.append(" ").append(EvalData.stackBD[this.seat]).toString());
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
			this.call = this.betNow.subtract(this.moneyIn);
			this.action.moneyToPot = call;
			this.moneyIn = this.moneyIn.add(this.call);
			this.action.betNow = this.betToMe;
			EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(this.call);
			EvalData.potBD = EvalData.potBD.add(call);
			System.out.println(new StringBuilder().append("//doCallBet3() ").append(this.seat).append(" ")
					.append(EvalData.orbit).append(" ").append(this.action.moneyToPot).append(" ").append(this.moneyIn)
					.append(" ").append(EvalData.stackBD[this.seat]).toString());
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
			this.action.action = BET4;
			var temp = zeroBD;
			var bet = zeroBD;
			this.call = this.betNow.subtract(this.moneyIn);
			bet = EvalData.potBD.multiply(EvalData.betMultiplierBD);
			temp = bet.subtract(this.call);
			this.action.moneyToPot = temp;
			this.moneyIn = this.moneyIn.add(temp);
			EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(temp);
			EvalData.potBD = EvalData.potBD.add(temp);
			System.out.println(new StringBuilder().append("//doBet4() ").append(this.seat).append(" ")
					.append(EvalData.orbit).append(" ").append(this.action.moneyToPot).append(" ").append(this.moneyIn)
					.append(" ").append(EvalData.stackBD[this.seat]).toString());
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
			this.action.action = CALL_BET4;
			
			this.call = this.betNow.subtract(this.moneyIn);
			this.action.moneyToPot = call;
			this.moneyIn = this.moneyIn.add(this.call);
			EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(this.call);
			EvalData.potBD = EvalData.potBD.add(call);
			System.out.println(new StringBuilder().append("//doCallBet4() ").append(this.seat).append(" ")
					.append(EvalData.orbit).append(" ").append(this.action.moneyToPot).append(" ").append(this.moneyIn)
					.append(" ").append(EvalData.stackBD[this.seat]).toString());
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
		this.call = this.betNow.subtract(this.moneyIn);
		var bet = EvalData.stackBD[this.seat].add(betToMe);
		++EvalData.allinCount;
		EvalData.playerAllin[this.seat] = true;
		EvalData.isAllin[0][seat][EvalData.orbit] = true;
		this.action.action = ALLIN;
		this.action.moneyToPot = EvalData.stackBD[this.seat];
		this.moneyIn = EvalData.stackBD[this.seat];
		this.action.betNow = bet.add(this.betToMe).subtract(call);
		EvalData.stackBD[this.seat] = zeroBD;
		++EvalData.allinCount;
		EvalData.allin$[0][seat][EvalData.orbit] = bet;
		System.out.println(new StringBuilder().append("//doAllin() ").append(this.seat).append(" ")
				.append(EvalData.orbit).append(" ").append(this.action.moneyToPot).append(" ").append(this.moneyIn)
				.append(" ").append(EvalData.stackBD[this.seat]).toString());
	}

	/*- **************************************************************************** 
	 * DoAll-In Call
	 **************************************************************************** */
	private void doAllinCall() {
		if (EvalData.playerFolded[this.seat] || EvalData.playerAllin[this.seat]) {
			Crash.log("$$$");
		}
		this.call = this.betNow.subtract(this.moneyIn);
		++EvalData.allinCount;
		EvalData.playerAllin[this.seat] = true;
		this.action.action = CALL_ALLIN;
		this.call = this.betNow.subtract(this.moneyIn);
		this.action.moneyToPot = call;
		this.moneyIn = this.moneyIn.add(this.call);
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(this.call);
		EvalData.isAllin[0][seat][EvalData.orbit] = true;
		EvalData.isCall[0][seat][EvalData.orbit] = true;
		EvalData.moneyIn[seat] = EvalData.moneyIn[seat].add(EvalData.stackBD[seat]);
		System.out.println(new StringBuilder().append("//doCallAllin() ").append(this.seat).append(" ")
				.append(+EvalData.orbit).append(" ").append(this.action.moneyToPot).append(" ").append(this.moneyIn)
				.append(" ").append(EvalData.stackBD[this.seat]).toString());
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
		var temp = EvalData.stackBD[seat].subtract(EvalData.betNowBD).subtract(EvalData.moneyInBD[seat]);
		if (temp.compareTo(zeroBD) == 1) {
			this.canRaise = true;
		}
		// final boolean canCall = Table.betNow - Table.moneyIn[Table.seat] <=
		// Table.stack[Table.seat];
		temp = EvalData.betNowBD.subtract(EvalData.moneyInBD[seat]);
		if (temp.compareTo(EvalData.stackBD[seat]) == -1) {
			this.canCall = true;
		}
	}
}
