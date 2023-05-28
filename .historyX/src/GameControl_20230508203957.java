//package evaluate_streets;

public class GameControl implements Constants {
	/*- **************************************************************************** 
	* This Class will remain only partially complete. 
	* In this project we will not do a holdem simulator, only as much as
	* is required to get good integrity in our results.
	* For now that is limited to Preflop play and only by using HandRange values
	* to determine if a player should fold, open, call, raise, raise all-in, or call all-in.
	* 
	* If anyone is interested this can easily be extended to cover a full game.
	* The full version is currently being used in the game project which does
	* include a full and very sophisticated simulator and much much more.
	* Contact me if you are interested.
	* 
	* What this Class does do if to play a very  limited preflop strategy.
	*		Money is taken from the stacks of the blinds and put in the pot.
	*		UTG starts off and decides using a HandRange for the UTG whether or
	*		not to fold or limp or open.  
	*		Play decisions are handled in an instance of the Player Class.
	*		This process continues around the table until either the pot is right
	*		or all but one player has folded. 
	*		Control is then returned to the Class that called us. In this project 
	*		that is the Evaluate Class.
	*		After each hand the positions are rotated.
	* 
	* 
	* @author PEAK_
	*******************************************************************************/

	private static int seat = 0;
	private static int pos = 0;
	/*- - Hand ranges for Blinds Player seat number. */
	private static int seatSB = -1;
	private static int seatBB = -1;
	private static int seatUTG = -1;
	private static int seatMP = -1;
	private static int seatCO = -1;
	private static int seatBU = -1;
	private static final boolean[] playerFolded = new boolean[PLAYERS];
	private static final boolean[] playerAllin = new boolean[PLAYERS];
	private static boolean rangesDone = false;
	private static final Player[] players = new Player[PLAYERS];
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

	private GameControl() {
		throw new IllegalStateException("Utility class");
	}

	/*- **************************************************************************** 
	* This method will do Preflop simulation. One hand only.
	* The hole cards and Flop have already been dealt.
	* We just play one hand at a time.
	* Player rotation is done elsewhere.
	* 
	* 
	* Instead of just dealing a flop we simulate preflop play.
	* The simulation is fairly advanced because it uses HandRange ranges for each
	* position and for each bet type that a player is faced with.
	* 		limp, open, 3 bet, 4 bet, all-in
	* Ranges are pretty accurate and based on results from the game project, a full
	* function simulator. Code was borrowed from game.
	* In the future, the hand ranges will be based on data obtained from the 
	* hand_history_analsis project that uses tens of millions of hands from Hand
	* History files from  PokerStars.
	* The Preflop simulator produces partial Hand History files.
	* Two classes are unique to simulation, GameControl and Player.
	* GameControl controls the sequencing of play.
	* Player simulates play of one position and there is an instance for each seat.
	 * 
	**************************************************************************** */
	static void preflopSimulationOneHand() {
		initialize();
		if (!rangesDone) {
			createRanges();
			createPlayers();
		}

		startGame(); // Get player positions and such
		seat = seatSB;
		players[SB].playerBetSB(seatSB);
		seat = seatBB;
		players[BB].playerBetBB(seatBB);
		EvalData.betType = LIMP;
		if (EvalData.hh) {
			HandHistory.initialize();
		}
		playPreflop();
		uncalledBet();

		if (EvalData.hh) {
			HandHistory.createHH();
			HandHistory.closeHH();
		}

		// If every one but onelast player is winner
		if (EvalData.foldCount == 5) {
			for (int i = 0; i < PLAYERS; i++) {
				if (!EvalData.playerFolded[i]) {
					EvalData.winnerCollectedBD[i] = EvalData.potBD;
					EvalData.playerWon[i] = true;
				}
			}
		}
	}

	/*- **************************************************************************** 
		* This method will do Preflop simulation. Multiple hands. 
		* Arg is number of hands to play.
		* We handle shuffling deck and dealing cards, and player rotation.
		* 
		* Instead of just dealing a flop we simulate preflop play.
		* The simulation is fairly advanced because it uses HandRange ranges for each
		* position and for each bet type that a player is faced with.
		* 		limp, open, 3 bet, 4 bet, all-in
		* Ranges are pretty accurate and based on results from the game project, a full
		* function simulator. Code was borrowed from game.
		* In the future, the hand ranges will be based on data obtained from the 
		* hand_history_analsis project that uses tens of millions of hands from Hand
		* History files from  PokerStars.
		* The Preflop simulator produces partial Hand History files.
		* Two classes are unique to simulation, GameControl and Player.
		* GameControl controls the sequencing of play.
		* Player simulates play of one position and there is an instance for each seat.
		 * 
		**************************************************************************** */
	static void preflopSimulationMUltipleHands(int numToPlay) {
		int played = 0;

		initialize();
		if (!rangesDone) {
			createRanges();
			createPlayers();
		}
		Deck.newDeck();
		if (EvalData.hh) {
			HandHistory.initialize();
		}

		while (played <= numToPlay) {
			System.out.println("//$$$$$$$$$$$$$$$$ Starting game " + played);
			Deck.shuffle();
			Dealer.rotatePositions();
			Dealer.dealAllHoleCards();
			Dealer.dealFlop();
			startGame(); // Get player positions and such
			seat = seatSB;
			players[SB].playerBetSB(seatSB);
			seat = seatBB;
			players[BB].playerBetBB(seatBB);
			EvalData.betType = LIMP;
			playPreflop();
			uncalledBet();

			if (EvalData.hh) {
				HandHistory.createHH();
			}

			// If every one but onelast player is winner
			if (EvalData.foldCount == 5) {
				for (int i = 0; i < PLAYERS; i++) {
					if (!EvalData.playerFolded[i]) {
						EvalData.winnerCollectedBD[i] = EvalData.potBD;
						EvalData.playerWon[i] = true;
					}
				}
			}
			++EvalData.handsPlayed;
			++played;
		}
		if (EvalData.hh) {
			HandHistory.closeHH();
		}
		GUIReports.reportPreflop(300, 300);
	}

	/*- **************************************************************************** 
	 * Initialize variables in EvalData
	 * TODO Not all of these actually required
	 **************************************************************************** */
	private static void initialize() {
		for (int i = 0; i < PLAYERS; i++) {
			EvalData.playerFoldedPreflop[i] = false;
			EvalData.playerFoldedFlop[i] = false;
			EvalData.playerFoldedTurn[i] = false;
			EvalData.playerFoldedRiver[i] = false;
			EvalData.playerLostShowdown[i] = false;
			EvalData.playerWonShowdown[i] = false;
			EvalData.playerWon[i] = false;
			EvalData.playerAllin[i] = false;
			EvalData.playerFolded[i] = false;
			EvalData.playerSidePotSplitTotalBD[i] = zeroBD;
		}

		EvalData.streetNumber = PREFLOP;
		EvalData.foldCount = 0;
		EvalData.allinCount = 0;
		EvalData.limpCount = 0;
		EvalData.potBD = zeroBD;
		EvalData.sidePotBD = zeroBD;
		EvalData.mainPotBD = zeroBD;
		EvalData.potStartBD = zeroBD;
		EvalData.potEndBD = zeroBD;
		EvalData.handsPlayed = 0;
		EvalData.gamesInASession = 0;
		EvalData.streetComplete = false;
		EvalData.boardComplete = false;
		EvalData.stop = false;
		EvalData.betType = 0;
		EvalData.orbit = 0;
		EvalData.betNowBD = zeroBD;
		EvalData.betToMeBD = zeroBD;
		EvalData.betCalled = true;
		EvalData.handsPlayed = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < PLAYERS; j++) {
				for (int k = 0; k < 4; k++) {
					EvalData.isFold[i][j][k] = false;
					EvalData.isLimp[i][j][k] = false;
					EvalData.isCheck[i][j][k] = false;
					EvalData.isCall[i][j][k] = false;
					EvalData.isBet[i][j][k] = false;
					EvalData.isRaise[i][j][k] = false;
					EvalData.isAllin[i][j][k] = false;
					EvalData.raisedToBD[i][j][k] = zeroBD;
					EvalData.returnedToBD[i][j][k] = zeroBD;
					EvalData.betBD[i][j][k] = zeroBD;
					EvalData.callBD[i][j][k] = zeroBD;
					EvalData.winBD[i][j][k] = zeroBD;
					EvalData.bet$[i][j][k] = zeroBD;
					EvalData.call$[i][j][k] = zeroBD;
					EvalData.allin$[i][j][k] = zeroBD;
					EvalData.returnedTo$[i][j][k] = zeroBD;
					EvalData.raisedTo$[i][j][k] = zeroBD;
					EvalData.calledTo$[i][j][k] = zeroBD;
					EvalData.win$[i][j][k] = zeroBD;
					EvalData.moneyIn$[i][j][k] = zeroBD;
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				EvalData.potEnd$[i][j] = zeroBD;
				EvalData.potStart$[i][j] = zeroBD;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < PLAYERS; j++) {
				EvalData.foldedPreflop[i][j] = 0;
			}
		}
		for (int i = 0; i < PLAYERS; i++) {
			EvalData.returnedToShowdown$[i] = zeroBD;
		}

		// Initialize money
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					EvalData.moneyIn$[i][j][k] = zeroBD;
				}
			}
		}
	}

	/*- **************************************************************************** 
	 * Initialize instances of Player for each seat.
	 * One time initialization
	 * Instances are customized for current position :
	 * 		SB, BB, UTG, MP, CO, BU
	 **************************************************************************** */
	private static void createPlayers() {
		players[SB] = new Player(rangeSBLimp, rangeSBOpen, rangeSBCall,
				rangeSBBet3, rangeSBBet3Call, rangeSBBet4, rangeSBBet4Call, rangeSBAllin, rangeSBAllinCall);
		players[BB] = new Player(rangeBBLimp, rangeBBOpen, rangeBBCall,
				rangeBBBet3, rangeBBBet3Call, rangeBBBet4, rangeBBBet4Call, rangeBBAllin, rangeBBAllinCall);
		players[UTG] = new Player(rangeUTGLimp, rangeUTGOpen, rangeUTGCall,
				rangeUTGBet3, rangeUTGBet3Call, rangeUTGBet4, rangeUTGBet4Call, rangeUTGAllin, rangeUTGAllinCall);
		players[MP] = new Player(rangeMPLimp, rangeMPOpen, rangeMPCall,
				rangeMPBet3, rangeMPBet3Call, rangeMPBet4, rangeMPBet4Call, rangeMPAllin, rangeMPAllinCall);
		players[CO] = new Player(rangeCOLimp, rangeCOOpen, rangeCOCall,
				rangeCOBet3, rangeCOBet3Call, rangeCOBet4, rangeCOBet4Call, rangeCOAllin, rangeCOAllinCall);
		players[BU] = new Player(rangeBULimp, rangeBUOpen, rangeBUCall,
				rangeBUBet3, rangeBUBet3Call, rangeBUBet4, rangeBUBet4Call, rangeBUAllin, rangeBUAllinCall);
	}

	/*- **************************************************************************** 
	 * Initialize instances of Player for each seat.
	 * One time initialization
	 * Instances are customized for current position :
	 * 		SB, BB, UTG, MP, CO, BU
	 **************************************************************************** */
	private static void createRanges() {
		rangesDone = true;
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

	/*- **************************************************************************** 
	 * Play hand Preflop 
	 * An instance of the Player class has made a decision using HandRange.
	 * It has updated EvalData:
	 * 		playerFolded[seat[
	 * 		playerAllin[seat]
	 * 		stackBD[seat}
	 * 		potBD 
	 * 		foldCount
	 * 		allinCount
	 * In most cases there is nothing to do other than move action after to 
	 * actionTo for next player.
	 * 
	 **************************************************************************** */
	private static void playHandPreflop() {
		System.out.println("//playHand " + seat + " " + seatSB + " " + seatBB + " " + seatUTG + " " + seatMP + " "
				+ seatCO + " " + seatBU);

		if (EvalData.playerFolded[seat]) {
			return;
		}

		EvalData.betType = players[pos].getDecision(seat);
		System.out.println("//playHandPreflop()B " + EvalData.betToMeBD);
	}

	/*- **************************************************************************** 
	 * Get the seat number for a position
	 * Arg0 - Position SB, BB, UTG, MO, CO, BU
	 * Returns seat number
	 **************************************************************************** */
	private static int getPosition(int p) {
		for (int i = 0; i < PLAYERS; i++) {
			if (p == EvalData.positions[i]) {
				return i;
			}
		}
		return -1;
	}

	/*- **************************************************************************** 
	* This method is called to play one hand.
	*******************************************************************************/
	static void startGame() {
		EvalData.boardComplete = EvalData.streetComplete = false;
		EvalData.winner = false;

		int p = getPosition(SB);
		seatSB = p;
		++p;
		if (p >= PLAYERS) {
			p = 0;
		}
		seatBB = p;
		++p;
		if (p >= PLAYERS) {
			p = 0;
		}
		seatUTG = p;
		++p;
		if (p >= PLAYERS) {
			p = 0;
		}
		seatMP = p;
		++p;
		if (p >= PLAYERS) {
			p = 0;
		}
		seatCO = p;
		++p;
		if (p >= PLAYERS) {
			p = 0;
		}
		seatBU = p;
		setPlayerPositions();
	}

	/*- **************************************************************************** 
	* This method sets the players seat number intp playerPositions array
	* playerPositions is indexed by position returns seat
	*******************************************************************************/
	private static void setPlayerPositions() {
		EvalData.playerPositions[SB] = seatSB;
		EvalData.playerPositions[BB] = seatBB;
		EvalData.playerPositions[UTG] = seatUTG;
		EvalData.playerPositions[MP] = seatMP;
		EvalData.playerPositions[CO] = seatCO;
		EvalData.playerPositions[BU] = seatBU;
		setPositions();
	}

	/*- **************************************************************************** 
	* This method sets the players seat number into EvalData.positions array
	* EvalData.positions is indexed by seat returns position ( SB, BB, UTG, MO, CO, BU)
	*******************************************************************************/
	private static void setPositions() {
		EvalData.positions[EvalData.playerPositions[SB]] = SB;
		EvalData.positions[EvalData.playerPositions[BB]] = BB;
		EvalData.positions[EvalData.playerPositions[UTG]] = UTG;
		EvalData.positions[EvalData.playerPositions[MP]] = MP;
		EvalData.positions[EvalData.playerPositions[CO]] = CO;
		EvalData.positions[EvalData.playerPositions[BU]] = BU;
	}

	/*- **************************************************************************** 
	* This method plays one preflop hand 
	*******************************************************************************/
	private static void playPreflop() {
		EvalData.streetComplete = false;
		EvalData.boardComplete = false;
		EvalData.orbit = 0;
		EvalData.lastOrbit[0] = 0;
		preflopOrbit0();

		EvalData.orbit = 1;
		EvalData.lastOrbit[0] = 1;
		preflopOrbit1();
		if (EvalData.streetComplete) {
			return;
		}
		if (streetComplete()) {
			return;
		}
		EvalData.orbit = 2;
		EvalData.lastOrbit[0] = 2;
		preflopOrbit23();

		if (streetComplete()) {
			return;
		}
		EvalData.orbit = 3;
		EvalData.lastOrbit[0] = 3;
		preflopOrbit23();

		if (streetComplete()) {
			return;
		}

		Logger.logError("ERROR Play()   street not complete after 4 EvalData.orbits ");
	}

	/*- **************************************************************************** 
	*  Start first Orbit 
	 * Assumes 6-Max. 
	 * A winner is not possible yet in the first EvalData.orbit SB and BB can not fold yet
	*******************************************************************************/
	private static void preflopOrbit0() {
		// Play hand UTG
		seat = seatUTG;
		pos = UTG;
		EvalData.betType = PREFLOP_LIMP;
		playHandPreflop();

		// Play hand MP
		seat = seatMP;
		pos = MP;
		playHandPreflop();

		// Play hand Cutoff
		seat = seatCO;
		pos = CO;
		playHandPreflop();

		// Play hand Button
		seat = seatBU;
		pos = BU;
		playHandPreflop();
	}

	/*- **************************************************************************** 
	* Orbit 1
	* Blinds get to play
	*******************************************************************************/
	private static void preflopOrbit1() {
		seat = seatSB;
		pos = SB;
		playHandPreflop();
		if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
			System.out.println("//preflopOrbit1A()  " + EvalData.foldCount);
			return;
		}

		seat = seatBB;
		pos = BB;
		playHandPreflop();
		if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
			System.out.println("//preflopOrbit1B()  " + EvalData.foldCount);
			return;
		}
		seat = seatUTG;
		pos = UTG;
		if (!playerFolded[seatUTG] && !playerAllin[seatUTG]) {
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				System.out.println("//preflopOrbit1C()  " + EvalData.foldCount);
				return;
			}
		}
		seat = seatMP;
		pos = MP;
		if (!playerFolded[seatMP] && !playerAllin[seatMP]) {
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				System.out.println("//preflopOrbit1D()  " + EvalData.foldCount);
				return;
			}
		}
		seat = seatCO;
		pos = CO;
		if (!EvalData.playerFolded[seatCO] && !playerAllin[seatCO]) {
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				System.out.println("//preflopOrbit1E()  " + EvalData.foldCount);
				return;
			}
		}
		seat = seatBU;
		pos = BU;
		if (!(!EvalData.playerFolded[seatBU] && !playerAllin[seatBU])) {
			playHandPreflop();
			System.out.println("//preflopOrbit1F()  " + EvalData.foldCount);
			return;
		}

		System.out.println("//preflopOrbit1G()  " + EvalData.foldCount);
	}

	/*- **************************************************************************** 
	* Play Orbit 2 and 3 Assumes 6 Max.
	*******************************************************************************/
	private static void preflopOrbit23() {
			if (!EvalData.playerFolded[seatSB]&& !playerAllin[seatSB]) {
			seat = seatSB;
			pos = SB;
			playHandPreflop();
			

		}
		if (!EvalData.playerFolded[seatBB] && !playerAllin[seatBB]) {
			seat = seatBB;
			pos = BB;
			playHandPreflop();
		}
		if (!EvalData.playerFolded[seatUTG] && !playerAllin[seatUTG]) {
			seat = seatUTG;
			pos = UTG;
			playHandPreflop();
		}
		if (!playerFolded[seatMP] && !playerAllin[seatMP]) {
			seat = seatMP;
			pos = MP;
			playHandPreflop();
		}
		seat = seatCO;
		pos = CO;
		if (!EvalData.playerFolded[seatCO] && !playerAllin[seatCO]) {
			playHandPreflop();
		}
		seat = seatBU;
		pos = BU;
		if (EvalData.playerFolded[seatBU] && !playerAllin[seatBU]) {
			return;
		}
		playHandPreflop();
	}

	/*- **************************************************************************** 
	* This method handles uncalled bets.
	* If a player has more money in that was not called, return that money.
	* 
	* In order for an uncalled bet to be returned:
	* 		There must be only 1 player still active.
	* 		If 1 player is active then all others have folded. If the active player made a
	* 		bet or raise that was not called it is returned to him.
	* 		
	**************************************************************************** */
	private static void uncalledBet() {
		final int active = PLAYERS - EvalData.foldCount;
		if (!EvalData.betCalled && EvalData.betType == CHECK) {
			return;
		}
		if (active == 1 && EvalData.betNowBD.compareTo(zeroBD) != 0 && EvalData.betCalled == true) {
			returnUncalledBet();
		}
	}

	/*- **************************************************************************** 
	*  Return uncalled bet
	 **************************************************************************** */
	static void returnUncalledBet() {
		System.out.println("//returnUncalled() ");
		int s = -1;
		for (int i = 0; i < PLAYERS; i++) {
			System.out.println("//folded " + EvalData.playerFolded[i] + " " + EvalData.foldCount);
			if (!EvalData.playerFolded[i]) {
				s = i;
				break;
			}
		}
		EvalData.moneyInBD[s] = EvalData.moneyInBD[s].subtract(EvalData.lastBetBD);
		EvalData.stackBD[s] = EvalData.stackBD[s].add(EvalData.lastBetBD);
		EvalData.potBD = EvalData.potBD.subtract(EvalData.lastBetBD);
		EvalData.pot$[EvalData.streetNumber][s][EvalData.orbit] = EvalData.potBD;
		EvalData.returnedTo$[0][s][EvalData.orbit] = EvalData.lastBetBD;
		EvalData.returnedToBD[0][s][EvalData.orbit] = EvalData.lastBetBD;
		EvalData.betCalled = true;
	}

	/*- **************************************************************************** 
	 *  Check for a street complete. 
	 *  A street is complete if: 
	 *  	All players have the same money in the pot ( unless folded or all-in ).
	 *  	All but 1 player have folded.
	 **************************************************************************** */
	private static boolean streetComplete() {
		if (EvalData.foldCount == MAXFOLDED) {
			EvalData.streetComplete = true;
			System.out.println("streetCompleteD " + EvalData.foldCount);
			return true;
		}
		for (int i = 0; i < EvalData.PLAYERS; ++i) {
			// Street is only complete if money is right
			if (!EvalData.playerFolded[i])
				// streetCompleteB 4 224.00 56.00
				System.out.println("streetCompleteB " + EvalData.foldCount + " " + EvalData.betNowBD + " "
						+ EvalData.moneyInBD[i]);
			if (!EvalData.playerFolded[i] && EvalData.betNowBD.compareTo(EvalData.moneyInBD[i]) != 0
					&& !EvalData.playerAllin[i]) {
				return false;
			}
		}
		System.out.println("streetCompletC " + EvalData.foldCount);
		EvalData.streetComplete = true;
		return true;
	}

}
