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
	private int handEV = -1;
	private int position = -1;
	private Card card1;
	private Card card2;
	private final HoleCardAction range;

	// Can raise or can call
	private boolean canRaise = false;
	private boolean canCall = false;

	// Constructor
	Player(HoleCardAction action) {
		this.range = action;
	}

	/*- **************************************************************************
	* Initialize
	* Uses seat number to initialize variables for a new game
	* Arg0 - seat
	**************************************************************************** */
	void initialize(int seat) {
		this.seat = seat;
		this.handIndex = EvalData.handIndexes[seat];
		this.position = EvalData.positions[this.seat];
		this.card1 = EvalData.holeCards[seat][0];
		this.card2 = EvalData.holeCards[seat][1];
		final int ndx = Dealer.getArrayIndexCard(card1, card2);
		this.handEV = HandRange.getPlayabilityNumeric(ndx);
	}

	/*- **************************************************************************** 
	 * Preflop only.
	 * Make a decision based on action to me: 
	 *  	PREFLOP_LIMP,   PREFLOP_OPEN,    PREFLOP_BET3,
	 *  	 PREFLOP_ALLIN,  PREFLOP_CALL_ALLIN.
	 *   
	 *  The decision is obtained from an instance of HoleCardAction
	 * **************************************************************************** */
	int getDecision() {

		System.out.println("Player " + this.seat);
		if (EvalData.betType == PREFLOP_LIMP) {
			if (this.range.Raise(position, EvalData.betType, 0) <= this.handIndex) {
				doOpen();
				return PREFLOP_OPEN;

			}
			if (this.range.getEV(position, EvalData.betType, 1) <= this.handIndex) {
				doLimp();
				return PREFLOP_LIMP;
			} else {
				doFold();
				return PREFLOP_LIMP;
			}
		}

		if (EvalData.betType == PREFLOP_OPEN) {
			if (this.range.getEV(position, EvalData.betType, 0) <= this.handIndex) {
				return PREFLOP_BET3;
			}
			if (this.range.getEV(position, EvalData.betType, 1) <= this.handIndex) {
				doCall();
				return PREFLOP_OPEN;

			} else {
				doFold();
				return PREFLOP_OPEN;
			}
		}

		if (EvalData.betType == PREFLOP_BET3) {
			if (this.range.getEV(position, EvalData.betType, 0) <= this.handIndex) {
				doBet4();
				return PREFLOP_BET4;
			}
			if (this.range.getEV(position, EvalData.betType, 1) <= this.handIndex) {
				doCallBet3();
				return PREFLOP_BET3;
			} else {
				doFold();
				return PREFLOP_BET3;
			}
		}

		if (EvalData.betType == PREFLOP_BET4) {
			if (this.range.getEV(position, EvalData.betType, 0) <= this.handIndex) {
				doAllin();
				return PREFLOP_ALLIN;
			}
			if (this.range.getEV(position, EvalData.betType, 1) <= this.handIndex) {
				doCallBet4();
				return PREFLOP_BET4;
			} else {
				doFold();
				return PREFLOP_BET4;
			}
		}

		if (EvalData.betType == PREFLOP_ALLIN) {
			if (this.range.getEV(position, EvalData.betType, 1) <= this.handIndex) {
				doAllinCall();
				return PREFLOP_ALLIN;
			} else {
				doFold();
				return PREFLOP_ALLIN;
			}
		}

		if (EvalData.betType == PREFLOP_CALL_ALLIN) {
			if (this.range.getEV(position, EvalData.betType, 1) <= this.handIndex) {
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
		System.out.println(new StringBuilder().append("//doFold() seat ").append(this.seat).append(" ")
				.append(EvalData.orbit).toString());
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
		EvalData.callBD[0][this.seat][EvalData.orbit] = EvalData.BBBetBD;
		EvalData.potBD = EvalData.potBD.add(EvalData.BBBetBD);
		EvalData.moneyInBD[this.seat] = EvalData.moneyInBD[this.seat].add(EvalData.BBBetBD);
		System.out.println(new StringBuilder().append("//doLimp() seat ").append(this.seat).append(" moneyIn ")
				.append(EvalData.moneyInBD[this.seat]).append(" stack ").append(EvalData.stackBD[this.seat])
				.append(" pot ").append(EvalData.potBD).toString());
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
			doRaises(EvalData.BBBetBD.multiply(EvalData.preflopBetMultiplyerOpenBD));
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
			doRaises(EvalData.betNowBD.multiply(EvalData.preflopBetMultiplyer3BetBD));

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
			doRaises(EvalData.betNowBD.multiply(EvalData.preflopBetMultiplyer4BetBD));
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
		final var call = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);
		final var bet = EvalData.stackBD[this.seat].add(EvalData.betToMeBD);
		// Flags and counts
		EvalData.isRaise[0][this.seat][EvalData.orbit] = true;
		++EvalData.allinCount;
		EvalData.playerAllin[this.seat] = true;
		EvalData.isAllin[0][this.seat][EvalData.orbit] = true;
		// Money
		EvalData.betBD[0][this.seat][EvalData.orbit] = bet;
		EvalData.raisedToBD[0][this.seat][EvalData.orbit] = EvalData.betNowBD.add(bet);
		EvalData.betNowBD = EvalData.betNowBD.add(bet);
		EvalData.moneyInBD[this.seat] = EvalData.moneyInBD[this.seat].add(bet);
		EvalData.potBD = EvalData.potBD.add(bet);
		EvalData.stackBD[this.seat] = zeroBD;
		EvalData.betCalled = false;

		System.out.println(new StringBuilder().append("//doAllin() seat  ").append(this.seat).append(" bet ")
				.append(bet).append(" call ").append(call).append(" stack ").append(EvalData.stackBD[this.seat])
				.append(" pot ").append(EvalData.potBD).toString());
	}

	/*- **************************************************************************** 
	 * DoAll-In Call
	 **************************************************************************** */
	private void doAllinCall() {
		if (EvalData.playerFolded[this.seat] || EvalData.playerAllin[this.seat]) {
			Crash.log("$$$");
		}
		final var call = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);
		// Flags and counts
		++EvalData.allinCount;
		EvalData.playerAllin[this.seat] = true;
		EvalData.isAllin[0][this.seat][EvalData.orbit] = true;
		EvalData.isCall[0][this.seat][EvalData.orbit] = true;
		// Money
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(call);
		EvalData.callBD[0][this.seat][EvalData.orbit] = call;
		EvalData.moneyInBD[this.seat] = EvalData.moneyInBD[this.seat].add(call);
		EvalData.potBD = EvalData.potBD.add(call);
		EvalData.betCalled = true;

		System.out.println(new StringBuilder().append("//doAllinCall() seat ").append(this.seat).append(" call ")
				.append(call).append(" stack ").append(EvalData.stackBD[this.seat]).append(" pot ")
				.append(EvalData.potBD).toString());
	}

	/*- **************************************************************************** 
		 *  Do calls
		 **************************************************************************** */
	private void doCalls() {
		System.out.println(new StringBuilder().append("//doCalls()A seat ").append(this.seat).append(" orbit ")
				.append(EvalData.orbit).append(" betNowBD ").append(EvalData.betNowBD).append(" moneyInBD ")
				.append(EvalData.moneyInBD[this.seat]).append(" stack ").append(EvalData.stackBD[this.seat])
				.append(" pot ").append(EvalData.potBD).toString());
		final var call = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);
		// Flags and counts
		EvalData.isCall[0][this.seat][EvalData.orbit] = true;
		// Money
		EvalData.moneyInBD[this.seat] = EvalData.moneyInBD[this.seat].add(call);
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(call);
		EvalData.betBD[0][this.seat][EvalData.orbit] = call;
		EvalData.potBD = EvalData.potBD.add(call);
		EvalData.callBD[0][this.seat][EvalData.orbit] = call;
		EvalData.betCalled = true;
		System.out.println(new StringBuilder().append("//doCalls()B seat ").append(this.seat).append(" orbit ")
				.append(EvalData.orbit).append(" betNowBD ").append(EvalData.betNowBD).append(" moneyInBD ")
				.append(EvalData.moneyInBD[this.seat]).append(" call ").append(call).append(" stack ")
				.append(EvalData.stackBD[this.seat]).append(" pot ").append(EvalData.potBD).toString());
	}

	/*- **************************************************************************** 
	 *  Do raises
	 * Arg- - bet size
	 **************************************************************************** */
	private void doRaises(BigDecimal bet) {
		final var call = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);
		final var temp = bet.add(call);
		// Flags and counts
		EvalData.isRaise[0][this.seat][EvalData.orbit] = true;
		// Money
		EvalData.moneyInBD[this.seat] = EvalData.moneyInBD[this.seat].add(temp);
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(temp);
		EvalData.potBD = EvalData.potBD.add(temp);
		EvalData.betBD[0][this.seat][EvalData.orbit] = bet;
		EvalData.raisedToBD[0][this.seat][EvalData.orbit] = temp;
		EvalData.betNowBD = temp;
		EvalData.betCalled = false;
		System.out.println(new StringBuilder().append("//doRaises() seat ").append(this.seat).append(" bet ")
				.append(bet).append(" temp ").append(temp).append(" stack ").append(EvalData.stackBD[this.seat])
				.append(" pot ").append(EvalData.potBD).toString());
	}

	/*- **************************************************************************** 
	 * Place SB bet
	 **************************************************************************** */
	void playerBetSB(int seat) {
		this.seat = seat;
		EvalData.potBD = EvalData.SBBetBD;
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(EvalData.SBBetBD);
		EvalData.betNowBD = EvalData.SBBetBD;
		EvalData.moneyInBD[this.seat] = EvalData.SBBetBD;
	}

	/*- **************************************************************************** 
	 * PlaceBB bet
	 **************************************************************************** */
	void playerBetBB(int seat) {
		this.seat = seat;
		EvalData.potBD = EvalData.BBBetBD.add(EvalData.SBBetBD);
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat].subtract(EvalData.BBBetBD);
		EvalData.stackBD[this.seat] = EvalData.stackBD[this.seat];
		EvalData.betNowBD = EvalData.BBBetBD;
		EvalData.moneyInBD[this.seat] = EvalData.BBBetBD;
	}

	/*- **************************************************************************** 
	 *  Re-buy if low on chips.
	 *  Called before the start of every game. 
	 **************************************************************************** */
	private void checkRebuy() {
		if (EvalData.stackBD[this.seat].compareTo(EvalData.maxStackBD) > 0) {
			EvalData.stackBD[this.seat] = EvalData.buyinBD;
		}
		if (EvalData.stackBD[this.seat].compareTo(EvalData.rebuyBD) < 0) {
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
		if (temp.compareTo(zeroBD) > 0) {
			this.canRaise = true;
		}
		temp = EvalData.betNowBD.subtract(EvalData.moneyInBD[this.seat]);
		if (temp.compareTo(EvalData.stackBD[this.seat]) < 0) {
			this.canCall = true;
		}
	}

}
