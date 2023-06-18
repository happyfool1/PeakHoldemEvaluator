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

	// private static HandRangeMultiple hero = new HandRangeMultiple();
	// private static HandRangeMultiple average = new HandRangeMultiple();
	private static HandRangeMultiple heroX = new HandRangeMultiple();
	// private static HandRangeMultiple averageX = new HandRangeMultiple();
	// private static HandRangeMultiple reg = new HandRangeMultiple();
	// private static HandRangeMultiple fish = new HandRangeMultiple();
	// private static HandRangeMultiple nit = new HandRangeMultiple();
	// private static HandRangeMultiple lag = new HandRangeMultiple();
	// private static HandRangeMultiple tag = new HandRangeMultiple();
	// private static HandRangeMultiple looser = new HandRangeMultiple();
	// private static HandRangeMultiple test = new HandRangeMultiple();

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
		if (!rangesDone) {
			createRanges();
			createPlayers();
		}

		newGame(); // Get player positions and such
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

		// If every one but onelast player is winner
		if (EvalData.foldCount == 5) {
			for (int i = 0; i < PLAYERS; i++) {
				if (!EvalData.playerFolded[i]) {
					EvalData.winnerCollectedBD[i] = EvalData.potBD;
					EvalData.playerWon[i] = true;
				}
			}
		}
		if (EvalData.hh) {
			HandHistory.createHH();
			HandHistory.closeHH();
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
	* hand_history_analysis project that uses tens of millions of hands from Hand
	* History files from  PokerStars.
	* The Preflop simulator produces partial Hand History files.
	* Two classes are unique to simulation, GameControl and Player.
	* GameControl controls the sequencing of play.
	* Player simulates play of one position and there is an instance for each seat.
	 * 
	**************************************************************************** */
	static void preflopSimulationMUltipleHands(int numToPlay) {
		if (!rangesDone) {
			createRanges();
			createPlayers();
		}
		Deck.newDeck();
		if (EvalData.hh) {
			HandHistory.initialize();
		}

		EvalData.handsPlayed = 0;
		while (EvalData.handsPlayed <= numToPlay) {
			Deck.shuffle();
			Dealer.rotatePositions();
			Dealer.dealAllHoleCards();
			Dealer.dealFlop();
			newGame();
			seat = seatSB;
			players[SB].playerBetSB(seatSB);
			seat = seatBB;
			players[BB].playerBetBB(seatBB);
			EvalData.betType = LIMP;
			playPreflop();
			uncalledBet();

			// If every one but onelast player is winner
			if (EvalData.foldCount == 5) {
				for (int i = 0; i < PLAYERS; i++) {
					if (!EvalData.playerFolded[i]) {
						EvalData.winnerCollectedBD[i] = EvalData.potBD;
						EvalData.winBD[0][i][EvalData.orbit] = EvalData.potBD;
						EvalData.playerWon[i] = true;
						break;
					}
				}
			}

			if (EvalData.hh) {
				HandHistory.createHH();
			}
			++EvalData.handsPlayed;
		}
		if (EvalData.hh) {
			HandHistory.closeHH();
		}
		IndexArrayUpdate.updateSummary();
		Reports.reportPreflop(300, 300);
	}

	/*- **************************************************************************** 
	* This method will do Preflop simulation with data bcollection. Multiple hands. 
	* Arg is number of hands to play.
	* We handle shuffling deck and dealing cards, and player rotation.
	* 
	* This method is identical to preflopSimulationMUltipleHands except that:
	* 1. There is no Hand History
	* 2.  
	* 
	**************************************************************************** */
	static void simulationDataCollection(int numToPlay) {
		if (!rangesDone) {
			createRanges();
			createPlayers();
		}
		Deck.newDeck();
		EvalData.handsPlayed = 0;
		while (EvalData.handsPlayed <= numToPlay) {
			Deck.shuffle();
			Dealer.rotatePositions();
			Dealer.dealAllHoleCards();
			Dealer.dealFlop();
			newGame();
			seat = seatSB;
			players[SB].playerBetSB(seatSB);
			seat = seatBB;
			players[BB].playerBetBB(seatBB);
			EvalData.betType = LIMP;
			playPreflop();
			uncalledBet();

			for (int i = 0; i < PLAYERS; i++) {
				if (!EvalData.playerFolded[i]) {
					Evaluate.doFlop(i);
				}
			}
			Dealer.dealTurnCard();
			for (int i = 0; i < PLAYERS; i++) {
				if (!EvalData.playerFolded[i]) {
					Evaluate.doTurn(i);
				}
			}
			Dealer.dealRiverCard();
			for (int i = 0; i < PLAYERS; i++) {
				if (!EvalData.playerFolded[i]) {
					Evaluate.doRiver(i);
				}
			}
			Evaluate.doShowdown();
			++EvalData.handsPlayed;
		}
		IndexArrayUpdate.updateSummary();
	}

	/*- **************************************************************************** 
	 * Initialize instances of Player for each seat.
	 * One time initialization
	 * Instances are customized for current position :
	 * 		SB, BB, UTG, MP, CO, BU
	 **************************************************************************** */
	private static void createPlayers() {
		players[SB] = new Player(heroX);
		players[BB] = new Player(heroX);
		players[UTG] = new Player(heroX);
		players[MP] = new Player(heroX);
		players[CO] = new Player(heroX);
		players[BU] = new Player(heroX);
	}

	/*- **************************************************************************** 
	 * Initialize instances of Player for each seat.
	 * One time initialization
	 * Instances are customized for current position :
	 * 		SB, BB, UTG, MP, CO, BU
	 **************************************************************************** */
	private static void createRanges() {
		heroX = heroX.readFromFile(EvalData.applicationDirectory + "heroX-HandRangeMultiple.ser");
		// averageX = averageX.readFromFile(EvalData.applicationDirectory +
		// "averageX-HandRangeMultiple.ser");
		// hero = hero.readFromFile(EvalData.applicationDirectory +
		// "hero-HandRangeMultiple.ser");
		// average = average.readFromFile(EvalData.applicationDirectory +
		// "average-HandRangeMultiple.ser");
		// fish = fish.readFromFile(EvalData.applicationDirectory +
		// "fish-HandRangeMultiple.ser");
		// nit = nit.readFromFile(EvalData.applicationDirectory +
		// "nit-HandRangeMultiple.ser");
		// lag = lag.readFromFile(EvalData.applicationDirectory +
		// "lag-HandRangeMultiple.ser");
		// tag = tag.readFromFile(EvalData.applicationDirectory +
		// "tag-HandRangeMultiple.ser");
		// test = test.readFromFile(EvalData.applicationDirectory +
		// "test-HandRangeMultiple.ser");
		// reg = reg.readFromFile(EvalData.applicationDirectory +
		// "reg-HandRangeMultiple.ser");
		// looser = looser.readFromFile(EvalData.applicationDirectory +
		// "looser-HandRangeMultiple.ser");
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
		if (EvalData.playerFolded[seat] || EvalData.playerAllin[seat]) {
			return;
		}

		EvalData.betType = players[pos].getDecision();
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
	* Start of a new game.
	* Initialize player positions
	* Initialize data
	* Initialize Player instances with hand number
	*******************************************************************************/
	static void newGame() {
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
		players[SB].initialize(seatSB);
		players[BB].initialize(seatBB);
		players[UTG].initialize(seatUTG);
		players[MP].initialize(seatMP);
		players[CO].initialize(seatCO);
		players[BU].initialize(seatBU);
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
			return;
		}

		seat = seatBB;
		pos = BB;
		playHandPreflop();
		if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
			return;
		}
		seat = seatUTG;
		pos = UTG;
		if (!playerFolded[seatUTG] && !playerAllin[seatUTG]) {
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				return;
			}
		}
		seat = seatMP;
		pos = MP;
		if (!playerFolded[seatMP] && !playerAllin[seatMP]) {
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				return;
			}
		}
		seat = seatCO;
		pos = CO;
		if (!EvalData.playerFolded[seatCO] && !playerAllin[seatCO]) {
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				return;
			}
		}
		seat = seatBU;
		pos = BU;
		if (!(!EvalData.playerFolded[seatBU] && !playerAllin[seatBU])) {
			playHandPreflop();
			return;
		}
	}

	/*- **************************************************************************** 
	* Play Orbit 2 and 3 Assumes 6 Max.
	*******************************************************************************/
	private static void preflopOrbit23() {
		if (!EvalData.playerFolded[seatSB] && !playerAllin[seatSB]) {
			seat = seatSB;
			pos = SB;
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				return;
			}
		}
		if (!EvalData.playerFolded[seatBB] && !playerAllin[seatBB]) {
			seat = seatBB;
			pos = BB;
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				return;
			}
		}
		if (!EvalData.playerFolded[seatUTG] && !playerAllin[seatUTG]) {
			seat = seatUTG;
			pos = UTG;
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				return;
			}
		}
		if (!playerFolded[seatMP] && !playerAllin[seatMP]) {
			seat = seatMP;
			pos = MP;
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				return;
			}
		}
		seat = seatCO;
		pos = CO;
		if (!EvalData.playerFolded[seatCO] && !playerAllin[seatCO]) {
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				return;
			}
		}
		seat = seatBU;
		pos = BU;
		if (EvalData.playerFolded[seatBU] && !playerAllin[seatBU]) {
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {

			}
			return;
		}
		playHandPreflop();
	}


/*- 
	  *
	 * Play Flop. 
	  *
	 */
	private static void playFlop() {
		boardComplete = streetComplete = false;
		Table.initializeFlop(); // Clear tables and deal flop
		timerPlayAnalysis.startTimer();
		PlayAnalysis.initializeFlop(); // Analyze play up to this point
		timerPlayAnalysis.sumTimer();

		Table.newFlopOrbit0();
		flopOrbit();

		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		Table.newFlopOrbit();
		flopOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		Table.newFlopOrbit();
		flopOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		Table.newFlopOrbit();
		flopOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		if (streetComplete()) {
			return;
		}
		Logger.logError("ERROR Play()   street not complete after 4 orbits ");
		if (Options.krash) {
			Crash.log("$$$");
		}
	}

	/*-
	 *
	*	Play Turn.
	 *
	 */
	private static void playTurn() {
		boardComplete = streetComplete = false;
		Table.initializeTurn();
		timerPlayAnalysis.startTimer();
		PlayAnalysis.initializeTurn(); // Analyze play up to this point
		timerPlayAnalysis.sumTimer();

		Table.newTurnOrbit0();
		turnOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		Table.newTurnOrbit();
		turnOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		Table.newTurnOrbit();
		turnOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		Table.newTurnOrbit();
		turnOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		if (streetComplete) {
			return;
		}
		Logger.logError("ERROR Play()   street not complete after 4 orbits ");
		if (Options.krash) {
			Crash.log("$$$");
		}
	}

	/*-
	*
	 *  - - Play River.
	*
	 */
	private static void playRiver() {
		Table.initializeRiver();
		timerPlayAnalysis.startTimer();
		PlayAnalysis.initializeRiver(); // Analyze play up to this point
		timerPlayAnalysis.sumTimer();
		boardComplete = streetComplete = false;

		Table.newRiverOrbit0();
		riverOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}

		if (streetComplete()) {
			return;
		}

		Table.newRiverOrbit();
		riverOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		Table.newRiverOrbit();
		riverOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		Table.newRiverOrbit();
		riverOrbit();
		if (Table.dataCollection) {
			Table.updateMessages();
		}

		if (Table.foldCount == Table.MAXFOLDED) {
			Table.streetComplete();
			streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		if (streetComplete) {
			return;
		}
		// ERROR Play() street not complete after 4 orbits Table.foldCount 4
		Logger.logError(new StringBuilder().append("ERROR Play()   street not complete after 4 orbits ")
				.append(" Table.foldCount ").append(Table.foldCount).append(" handsPlayed ").append(Table.handsPlayed)
				.toString());
		if (Options.krash) {
			Crash.log("$$$");
		}
	}


	/*-
	 *
	 *	Play one Orbit.
	 *	Assumes 6 Max.
	  *
	 */
	private static void flopOrbit() {
		if (!Table.playerFolded[seatSB] && !Table.playerAllin[seatSB]) {
			Table.seat = seatSB;
			if (Table.orbit == 0) {
				++Table.flopCountPos[Table.SB];
			}
			flopPlay();
		}

		if (!Table.playerFolded[seatBB] && !Table.playerAllin[seatBB]) {
			Table.seat = seatBB;
			if (Table.orbit == 0) {
				++Table.flopCountPos[Table.BB];
			}
			flopPlay();
		}

		if (!Table.playerFolded[seatUTG] && !Table.playerAllin[seatUTG]) {
			Table.seat = seatUTG;
			if (Table.orbit == 0) {
				++Table.flopCountPos[Table.UTG];
			}
			flopPlay();
		}

		if (!Table.playerFolded[seatMP] && !Table.playerAllin[seatMP]) {
			Table.seat = seatMP;
			if (Table.orbit == 0) {
				++Table.flopCountPos[Table.MP];
			}
			flopPlay();
		}

		if (!Table.playerFolded[seatCutoff] && !Table.playerAllin[seatCutoff]) {
			Table.seat = seatCutoff;
			if (Table.orbit == 0) {
				++Table.flopCountPos[Table.CUTOFF];
			}
			flopPlay();
		}

		if (!(!Table.playerFolded[seatButton] && !Table.playerAllin[seatButton])) {
			return;
		}
		Table.seat = seatButton;
		if (Table.orbit == 0) {
			++Table.flopCountPos[Table.BUTTON];
		}
		flopPlay();
	}

	/*-
	 *
	*	 Play one Turn Orbit 
	*	Assumes 6 Max. 
	 *
	*/
	private static void turnOrbit() {
		if (!Table.playerFolded[seatSB] && !Table.playerAllin[seatSB]) {
			Table.seat = seatSB;
			if (Table.orbit == 0) {
				++Table.turnCountPos[Table.SB];
			}
			turnPlay();
		}

		if (!Table.playerFolded[seatBB] && !Table.playerAllin[seatBB]) {
			Table.seat = seatBB;
			if (Table.orbit == 0) {
				++Table.turnCountPos[Table.BB];
			}
			turnPlay();
		}

		if (!Table.playerFolded[seatUTG] && !Table.playerAllin[seatUTG]) {
			Table.seat = seatUTG;
			if (Table.orbit == 0) {
				++Table.turnCountPos[Table.UTG];
			}
			turnPlay();
		}

		if (!Table.playerFolded[seatMP] && !Table.playerAllin[seatMP]) {
			Table.seat = seatMP;
			if (Table.orbit == 0) {
				++Table.turnCountPos[Table.MP];
			}
			turnPlay();
		}

		if (!Table.playerFolded[seatCutoff] && !Table.playerAllin[seatCutoff]) {
			Table.seat = seatCutoff;
			if (Table.orbit == 0) {
				++Table.turnCountPos[Table.CUTOFF];
			}
			turnPlay();
		}

		if (!(!Table.playerFolded[seatButton] && !Table.playerAllin[seatButton])) {
			return;
		}
		Table.seat = seatButton;
		if (Table.orbit == 0) {
			++Table.turnCountPos[Table.BUTTON];
		}
		turnPlay();
	}

	/*-
	 *
	 * Play one Orbit
	  *
	 */
	private static void riverOrbit() {
		if (!Table.playerFolded[seatSB] && !Table.playerAllin[seatSB]) {
			Table.seat = seatSB;
			if (Table.orbit == 0) {
				++Table.riverCountPos[Table.SB];
			}
			riverPlay();
		}

		if (!Table.playerFolded[seatBB] && !Table.playerAllin[seatBB]) {
			Table.seat = seatBB;
			if (Table.orbit == 0) {
				++Table.riverCountPos[Table.BB];
			}
			riverPlay();
		}

		if (!Table.playerFolded[seatUTG] && !Table.playerAllin[seatUTG]) {
			Table.seat = seatUTG;
			if (Table.orbit == 0) {
				++Table.riverCountPos[Table.UTG];
			}
			riverPlay();
		}

		if (!Table.playerFolded[seatMP] && !Table.playerAllin[seatMP]) {
			Table.seat = seatMP;
			if (Table.orbit == 0) {
				++Table.riverCountPos[Table.MP];
			}
			riverPlay();
		}

		if (!Table.playerFolded[seatCutoff] && !Table.playerAllin[seatCutoff]) {
			Table.seat = seatCutoff;
			if (Table.orbit == 0) {
				++Table.riverCountPos[Table.CUTOFF];
			}
			riverPlay();
		}

		if (!(!Table.playerFolded[seatButton] && !Table.playerAllin[seatButton])) {
			return;
		}
		Table.seat = seatButton;
		if (Table.orbit == 0) {
			++Table.riverCountPos[Table.BUTTON];
		}
		riverPlay();

	}

	/*-
	 *
	 * - Play hand.
	 * 
	 */
	private static void flopPlay() {
		if (Table.foldCount == Table.MAXFOLDED) {
			return;
		}
		if (Table.moneyIn[Table.seat] == Table.betNow && Table.betType != Table.CHECK
				&& Table.moneyIn[Table.seat] != 0) {
			return;
		}

		++Table.flopCount[Table.seat];
		handIndex = Table.handIndex[Table.seat];

		if (Table.betType == Table.CHECK) {
			++Table.flopToMeCheck[Table.seat];
		} else if (Table.betType == Table.BET1) {
			++Table.flopToMeBet1[Table.seat];
		} else if (Table.betType == Table.BET2) {
			++Table.flopToMeBet2[Table.seat];
		} else if (Table.betType == Table.BET3) {
			++Table.flopToMeBet3[Table.seat];
		} else if (Table.betType == Table.BET4) {
			++Table.flopToMeBet4[Table.seat];
		} else if (Table.betType == Table.ALLIN) {
			++Table.flopToMeAllin[Table.seat];
		} else {
			Logger.logError("ERROR Play() invalid betType " + Table.betType);
			if (Options.krash) {
				Crash.log("$$$");
			}
		}
		timerFlop.startTimer();
		playHandFlop();
		timerFlop.sumTimer();
	}

	/*-
	 *
	 * - Play hand.
	  *
	 */
	private static void turnPlay() {
		if (Table.foldCount == Table.MAXFOLDED) {
			return;
		}

		if (Table.moneyIn[Table.seat] == Table.betNow && Table.betType != Table.CHECK
				&& Table.moneyIn[Table.seat] != 0) {
			return;
		}

		++Table.turnCount[Table.seat];
		handIndex = Table.handIndex[Table.seat];

		if (Table.betType == Table.CHECK) {
			++Table.turnToMeCheck[Table.seat];
		} else if (Table.betType == Table.BET1) {
			++Table.turnToMeBet1[Table.seat];
		} else if (Table.betType == Table.BET2) {
			++Table.turnToMeBet2[Table.seat];
		} else if (Table.betType == Table.BET3) {
			++Table.turnToMeBet3[Table.seat];
		} else if (Table.betType == Table.BET4) {
			++Table.turnToMeBet4[Table.seat];
		} else if (Table.betType == Table.ALLIN) {
			++Table.turnToMeAllin[Table.seat];
		} else {
			Logger.logError("ERROR Play() invalid betType " + Table.betType);
			if (Options.krash) {
				Crash.log("$$$");
			}
		}
		timerTurn.startTimer();
		playHandTurn();
		timerTurn.sumTimer();
	}

	/*-
	 *
	 * - Play hand.
	  *
	 */
	private static void riverPlay() {
		if (Table.foldCount == Table.MAXFOLDED) {
			return;
		}
		if (Table.moneyIn[Table.seat] == Table.betNow && Table.betType != Table.CHECK
				&& Table.moneyIn[Table.seat] != 0) {
			return;
		}

		++Table.riverCount[Table.seat];
		handIndex = Table.handIndex[Table.seat];

		if (Table.betType == Table.CHECK) {
			++Table.riverToMeCheck[Table.seat];
		} else if (Table.betType == Table.BET1) {
			++Table.riverToMeBet1[Table.seat];
		} else if (Table.betType == Table.BET2) {
			++Table.riverToMeBet2[Table.seat];
		} else if (Table.betType == Table.BET3) {
			++Table.riverToMeBet3[Table.seat];
		} else if (Table.betType == Table.BET4) {
			++Table.riverToMeBet4[Table.seat];
		} else if (Table.betType == Table.ALLIN) {
			++Table.riverToMeAllin[Table.seat];
		} else {
			Logger.logError("ERROR Play() invalid betType " + Table.betType);
			if (Options.krash) {
				Crash.log("$$$");
			}
		}

		timerRiver.startTimer();
		playHandRiver();
		timerRiver.sumTimer();
	}


/*-
	 *
	 * Play hand Flop
	  * 
	 */
	private static void playHandFlop() {
		errorCheck();
		getRelativePosition();
		Table.decisionProcessOpponentAction[Table.streetNumber][Table.seat][Table.orbit] = Table.betType;
		handIndex = Table.handIndex[Table.seat];

		if (Table.orbit == 0) {
			timerEvaluate.startTimer();
			final int hv = Evaluate.evaluateAll(Table.seat);
			handValue[Table.seat] = hv;
			Table.stgy_HandValue[Table.streetNumber][Table.seat] = hv;
			Table.evaluateIndex[Table.streetNumber][Table.seat] = hv;
			Table.evaluateIndexPos[Table.streetNumber][Table.seat][Table.positions[Table.seat]] = hv;
			++Table.evalArrayCounts[Table.streetNumber][Table.seat][handValue[Table.seat]];
			++Table.evalArrayCountsPos[Table.streetNumber][Table.positions[Table.seat]][Table.seat][handValue[Table.seat]];

			if (Evaluate.evalArray[Evaluate.BOARD_WET]) {
				Table.stgy_WetDry = Table.WET;
			} else if (Evaluate.evalArray[Evaluate.BOARD_DRY]) {
				Table.stgy_WetDry = Table.DRY;
			} else {
				Table.stgy_WetDry = Table.NOT_WET_DRY;
			}
			if (Evaluate.evalArray[Evaluate.BOARD_STATIC]) {
				Table.stgy_StaticDynamic = Table.STATIC;
			} else if (Evaluate.evalArray[Evaluate.BOARD_DYNAMIC]) {
				Table.stgy_StaticDynamic = Table.DYNAMIC;
			} else {
				Table.stgy_StaticDynamic = Table.NOT_STATIC_DYNAMIC;
			}
			Table.stgy_HMLIndex = Evaluate.HMLIndex;
			Table.stgy_BoardDrawIndex = Evaluate.boardPossibleArrayFlopMax;
			// TODO Table.stgy_BoardDrawIndex1577 = 0;

			timerEvaluate.sumTimer();
		}

		if (Options.flopABC[Table.seat]) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_ABC;
			if (PlayABC.flopOrbit0Preemptive()) {
				Table.decisionProcessDecision[Table.streetNumber][Table.seat][Table.orbit] = -1;
				return;
			}
		}
		if (Options.flopGTO[Table.seat]) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_GTO;
			if (PlayGTO.flopOrbit0Preemptive()) {
				Table.decisionProcessDecision[Table.streetNumber][Table.seat][Table.orbit] = -1;
				return;
			}
		}

		// Check the Rules
		getRulePLay();
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_RULES;
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = rulePlay;
		if (playHandRuleFlop()) {
			return;
		}

		if (!Options.flopAdvanced[Table.seat]) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_BLUFF;
			if (playHandBluffFlop()) {
				return;
			}
		}

		// Next we try C Bet
		if (Options.CBet[Table.seat] && Table.seat == Table.preflopRaiser && Table.betType == Table.CHECK) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_CBET;
			if (playHandCBet()) {
				return;
			}
		}

		final boolean condition = (Table.betType == Table.CHECK || Table.betType == Table.BET1
				|| Table.betType == Table.BET2) && (Options.mdfFlop[Table.seat] || Options.mdfBet2Flop[Table.seat])
				&& (Table.betType == Table.CHECK || Table.betType == Table.BET1 || Table.betType == Table.BET2)
				&& (Options.mdfFlop[Table.seat] || Options.mdfBet2Flop[Table.seat]) && playHandMDFFlop();
		/// TODO this makes no sense to me
		// TODO Looks like we will play MDF only if we did not raise or call bet 3 and
		/// did call or raise bet 2
		// TODO ??? then why || bet3 ??????
		// Will need to check orbits in a loop
		// if ((!Table.isCalledBet3[Table.PREFLOP][Table.seat] [Table.orbit] &&
		// !Table.isRaisedToBet3[Table.PREFLOP][Table.seat] [Table.orbit])
		// && ((!Table.isCalledBet2[Table.PREFLOP][Table.seat] [Table.orbit] &&
		// !Table.isRaisedToBet2[Table.PREFLOP][Table.seat] [Table.orbit]))
		// || ((!Table.isCallAllin[Table.PREFLOP][Table.seat] [Table.orbit] &&
		// !Table.isAllin[Table.PREFLOP][Table.seat] [Table.orbit])))
		// copied from Turn
		// No decision by Rules or Bluff or C Bet, so we will use MDF
		if (condition) {
			return;
		}
		Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_FINAL;
		final double call = Table.betNow - Table.moneyIn[Table.seat];
		if (call == 0) {
			if (Table.dataCollection) {
				setPlayerCheckedMsg(56, POSTFLOP_MDF);
			} else {
				setPlayerChecked();
			}
			return;
		}

		// If all else fails just fold

		if (Table.dataCollection) {
			setPlayerFoldedMsg(12, POSTFLOP_MDF);
		} else {
			setPlayerFolded();
		}
	}

	/*-
	  *
	 * Play hand Turn
	  * 
	 */
	private static void playHandTurn() {
		getRelativePosition();
		Table.decisionProcessOpponentAction[Table.streetNumber][Table.seat][Table.orbit] = Table.betType;
		errorCheck();
		handIndex = Table.handIndex[Table.seat];

		if (Table.orbit == 0) {
			timerEvaluate.startTimer();
			final int hv = Evaluate.evaluateAll(Table.seat);
			handValue[Table.seat] = hv;
			Table.stgy_HandValue[Table.streetNumber][Table.seat] = hv;
			Table.evaluateIndex[Table.streetNumber][Table.seat] = hv;
			Table.evaluateIndexPos[Table.streetNumber][Table.seat][Table.positions[Table.seat]] = hv;
			++Table.evalArrayCounts[Table.streetNumber][Table.seat][handValue[Table.seat]];
			++Table.evalArrayCountsPos[Table.streetNumber][Table.positions[Table.seat]][Table.seat][handValue[Table.seat]];
			timerEvaluate.sumTimer();
		}

		if (Options.turnABC[Table.seat]) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_ABC;
			if (PlayABC.turnOrbit0Preemptive()) {
				Table.decisionProcessDecision[Table.streetNumber][Table.seat][Table.orbit] = -1;
				return;
			}
		}
		if (Options.turnGTO[Table.seat]) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_GTO;
			if (PlayGTO.turnOrbit0Preemptive()) {
				return;
			}
		}

		// Check the Rules

		getRulePLay();
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_RULES;
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = rulePlay;
		if (playHandRule()) {
			return;
		}

		if (!Options.turnAdvanced[Table.seat]) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_BLUFF;
			if (playHandBluffTurn()) {
				Table.decisionProcessDecision[Table.streetNumber][Table.seat][Table.orbit] = -1;
				return;
			}
		}

		// Next we try barreling
		if (Options.barrelTurn[Table.seat] && barrelTurn()) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_BARREL;
			return;
		}

		final boolean condition = (Table.betType == Table.CHECK || Table.betType == Table.BET1
				|| Table.betType == Table.BET2) && (Options.mdfTurn[Table.seat] || Options.mdfBet2Turn[Table.seat])
				&& playHandMDFTurn();
		// No decision by Rules or Bluff or C Bet or Barreling so we will use MDF
		if (condition) {
			return;
		}

		Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_FINAL;
		final double call = Table.betNow - Table.moneyIn[Table.seat];
		if (call == 0) {
			if (Table.dataCollection) {
				setPlayerCheckedMsg(56, POSTFLOP_MDF);
			} else {
				setPlayerChecked();
			}
			return;
		}
		// If all else fails just fold
		if (Table.dataCollection) {
			setPlayerFoldedMsg(12, POSTFLOP_MDF);
		} else {
			setPlayerFolded();
		}
	}

	/*-
	 *
	 * Play hand River\
	  *
	 */
	private static void playHandRiver() {
		errorCheck();
		getRelativePosition();
		Table.decisionProcessOpponentAction[Table.streetNumber][Table.seat][Table.orbit] = Table.betType;
		handIndex = Table.handIndex[Table.seat];

		if (Table.orbit == 0) {
			timerEvaluate.startTimer();
			final int hv = Evaluate.evaluateAll(Table.seat);
			handValue[Table.seat] = hv;
			Table.stgy_HandValue[Table.streetNumber][Table.seat] = hv;
			Table.evaluateIndex[Table.streetNumber][Table.seat] = hv;
			Table.evaluateIndexPos[Table.streetNumber][Table.seat][Table.positions[Table.seat]] = hv;
			++Table.evalArrayCounts[Table.streetNumber][Table.seat][handValue[Table.seat]];
			++Table.evalArrayCountsPos[Table.streetNumber][Table.positions[Table.seat]][Table.seat][handValue[Table.seat]];
			timerEvaluate.sumTimer();
		}

		if (Options.riverABC[Table.seat]) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_ABC;
			if (PlayABC.riverOrbit0Preemptive()) {
				Table.decisionProcessDecision[Table.streetNumber][Table.seat][Table.orbit] = -1;
				return;
			}
		}
		if (Options.riverGTO[Table.seat]) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_GTO;
			if (PlayGTO.riverOrbit0Preemptive()) {
				return;
			}
		}

		// Check the Rules
		getRulePLay();
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_RULES;
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = rulePlay;
		if (playHandRule()) {
			return;
		}

		if (!Options.riverAdvanced[Table.seat]) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_BLUFF;
			if (playHandBluffRiver()) {
				return;
			}
		}

		// Next we try barreling
		if (Options.barrelRiver[Table.seat] && barrelRiver()) {
			Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_BARREL;
			return;
		}

		final boolean condition = (Table.betType == Table.CHECK || Table.betType == Table.BET1
				|| Table.betType == Table.BET2) && (Options.mdfRiver[Table.seat] || Options.mdfBet2River[Table.seat])
				&& playHandMDFRiver();
		// No decision by Rules or Bluff or Barreling so we will use MDF
		if (condition) {
			return;
		}

		Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_FINAL;
		final double call = Table.betNow - Table.moneyIn[Table.seat];
		if (call == 0) {
			if (Table.dataCollection) {
				setPlayerCheckedMsg(56, POSTFLOP_MDF);
			} else {
				setPlayerChecked();
			}
			return;
		}
		// If all else fails just fold
		if (Table.dataCollection) {
			setPlayerFoldedMsg(12, POSTFLOP_MDF);
		} else {
			setPlayerFolded();
		}
	}

/*-
	  *
	 * Get Rule play . 
	 * Sets rule to play from Rules, Rule.BET, Rule.Call,  Rule.ALLIN
	 *  Rules uses opponent actions, Rules.Check, Rules.BET1,
	 * Rules.BET2. Rules.BET3, Rules.BET4, Rules.ALLIN 
	 *	Opponent actions are only betToMe so Fold and Call are not included as an action.
	 *	Sets global variables:
	 *		rulePlay - Value returned from Strategy ( reads rule file array on startup )
	 *		Table.rule[Table.seat]   - RULEHU, RULE,		( for "Why" ) // TODO not good design
	 * 
	 */
	private static void getRulePLay() {
		errorCheck();
		stratIndex = Table.strategyIndex[Table.seat];
		rulePlay = -1;
		Table.decisionProcess[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_RULES;

		// First to act
		if (Table.seat == first) {
			if (Table.foldCount - Table.PLAYERS == 2) {
				// Heads up
				Table.rule[Table.seat] = RULEHU;
				if (Table.streetNumber == Table.FLOP) {
					rulePlay = Table.strategy[stratIndex].flopFirstRules.ruleArray[handValue[Table.seat]][Table.betType];
					return;
				}
				if (Table.streetNumber == Table.TURN) {
					rulePlay = Table.strategy[stratIndex].turnFirstRules.ruleArray[handValue[Table.seat]][Table.betType];
					return;
				}
				rulePlay = Table.strategy[stratIndex].riverFirstRules.ruleArray[handValue[Table.seat]][Table.betType];
				return;
			}
			// Not heads up
			Table.rule[Table.seat] = RULE;
			if (Table.streetNumber == Table.FLOP) {
				rulePlay = Table.strategy[stratIndex].flopFirstRulesHU.ruleArray[handValue[Table.seat]][Table.betType];
				return;
			}
			if (Table.streetNumber == Table.TURN) {
				rulePlay = Table.strategy[stratIndex].turnFirstRulesHU.ruleArray[handValue[Table.seat]][Table.betType];
				return;
			}
			rulePlay = Table.strategy[stratIndex].riverFirstRulesHU.ruleArray[handValue[Table.seat]][Table.betType];
			return;
		}

		// Last to act
		if (Table.seat == last) {
			if (Table.foldCount - Table.PLAYERS == 2) {// Heads up
				// Heads up
				Table.rule[Table.seat] = RULEHU;
				if (Table.streetNumber == Table.FLOP) {
					rulePlay = Table.strategy[stratIndex].flopLastRules.ruleArray[handValue[Table.seat]][Table.betType];
					return;
				}
				if (Table.streetNumber == Table.TURN) {
					rulePlay = Table.strategy[stratIndex].turnLastRules.ruleArray[handValue[Table.seat]][Table.betType];
					return;
				}
				rulePlay = Table.strategy[stratIndex].riverLastRules.ruleArray[handValue[Table.seat]][Table.betType];
				return;
			}
			// Not heads up
			Table.rule[Table.seat] = RULE;
			if (Table.streetNumber == Table.FLOP) {
				rulePlay = Table.strategy[stratIndex].flopLastRulesHU.ruleArray[handValue[Table.seat]][Table.betType];
				return;
			} else if (Table.streetNumber == Table.TURN) {
				rulePlay = Table.strategy[stratIndex].turnLastRulesHU.ruleArray[handValue[Table.seat]][Table.betType];
				return;
			}
			rulePlay = Table.strategy[stratIndex].riverLastRulesHU.ruleArray[handValue[Table.seat]][Table.betType];
			return;
		}

		// If not first or last then it has to be middle
		Table.rule[Table.seat] = RULE;
		if (Table.streetNumber == Table.FLOP) {
			rulePlay = Table.strategy[stratIndex].flopMiddleRules.ruleArray[handValue[Table.seat]][Table.betType];
			return;
		}
		if (Table.streetNumber == Table.TURN) {
			rulePlay = Table.strategy[stratIndex].turnMiddleRules.ruleArray[handValue[Table.seat]][Table.betType];
			return;
		}
		rulePlay = Table.strategy[stratIndex].riverMiddleRules.ruleArray[handValue[Table.seat]][Table.betType];
	}

	/*-
	 *
	 *  Play hand using rules. Flop - handles Bet, Call, and All In
	  * 
	 */
	private static boolean playHandRuleFlop() {
		errorCheck();

		if (rulePlay == Rules.BET) {
			Table.why[Table.seat] = 53;
			doRaise(86, POSTFLOP_RULE);
			return true;
		}
		if (rulePlay == Rules.CALL) {
			playHandRuleFlopCall();
			return true;
		}
		if (rulePlay != Rules.ALLIN) {
			return false;
		}
		playHandRuleAllin();
		return true;
	}

	/*-
	 *
	 * Play hand using rules. Turn  or River
	 * Handles BET, CALL, and ALLIN
	  * 
	 */
	private static boolean playHandRule() {
		errorCheck();

		if (rulePlay == Rules.BET) {
			Table.why[Table.seat] = 53;
			doRaise(86, POSTFLOP_RULE);
			return true;
		}

		if (rulePlay == Rules.CALL) {
			playHandRuleCall();
			return true;
		}
		if (rulePlay != Rules.ALLIN) {
			return false;
		}
		playHandRuleAllin();
		return true;
	}

	/*-
	 *
	 * Play hand Bluff Flop
	  *
	 */
	private static boolean playHandBluffFlop() {
		errorCheck();
		final int r = getRandomNumberInRange(0, 99); // Percentage of time to bluff
		if (r >= 0) {
			// TODO
			return false;
		}
		doRaise(91, POSTFLOP_BLUFF);
		return true;
	}

	/*-
	 *
	 * Play hand Bluff Turn
	  * 
	 */
	private static boolean playHandBluffTurn() {
		errorCheck();
		final int r = getRandomNumberInRange(0, 99); // Percentage of time to bluff
		if (r >= 0) {
			// TODO
			return false;
		}
		doRaise(91, POSTFLOP_BLUFF);
		return true;
	}

	/*-
	 *
	 * Play hand Bluff River
	  *
	 */
	private static boolean playHandBluffRiver() {
		errorCheck();
		final int r = getRandomNumberInRange(0, 99); // Percentage of time to bluff
		if (r >= 0) {
			// TODO
			return false;
		}
		doRaise(91, POSTFLOP_BLUFF);
		return true;
	}

	/*-
	 *
	 * Helper method for playHandRuleFlop().Call
	 *
	 */
	private static boolean playHandRuleFlopCall() {
		errorCheck();
		Table.rule[Table.seat] = RULE;
		Table.why[Table.seat] = 49;

		if (Table.betType == Table.BET1) {
			++Table.flopCallBet1[Table.seat];
			doCallCheckFold();
			return true;
		}

		if (Table.betType == Table.BET2) {
			++Table.flopCallBet2[Table.seat];
			doCallCheckFold();
			return true;
		}

		if (Table.betType == Table.BET3) {
			++Table.flopCallBet3[Table.seat];
			doCallCheckFold();
			return true;
		}

		if (Table.betType == Table.BET4) {
			++Table.flopCallBet4[Table.seat];
			doCallCheckFold();
			return true;
		}

		if (Table.betType != Table.ALLIN) {
			return false;
		}
		++Table.flopCallAllin[Table.seat];
		doCallCheckFold();
		return true;
	}

	/*-
	  *
	 * Helper method for playHandRuleTurn().
	  *
	 */
	private static boolean playHandRuleCall() {
		errorCheck();
		Table.rule[Table.seat] = RULE;
		Table.why[Table.seat] = 49;

		if (Table.betType == Table.BET1) {
			doCallCheckFold();
			return true;
		}

		if (Table.betType == Table.BET2) {
			doCallCheckFold();
			return true;
		}

		if (Table.betType == Table.BET3) {
			doCallCheckFold();
			return true;
		}

		if (Table.betType == Table.BET4) {
			doCallCheckFold();
			return true;
		}

		if (Table.betType != Table.ALLIN) {
			return false;
		}
		doCallCheckFold();
		return true;
	}

	/*-
	 *
	 * Helper method for playHandRuleFlop().
	  *
	 */
	private static void playHandRuleAllin() {
		errorCheck();
		final double call = Table.betNow - Table.moneyIn[Table.seat];
		if (Table.playerAllin[Table.seat] && Table.allinCount > 0 && call > Table.stack[Table.seat]) {
			setPlayerCalledAllin(52, POSTFLOP_RULE);
			return;
		}

		if (Table.stack[Table.seat] - call <= 0) {
			setPlayerCalledAllin(52, POSTFLOP_RULE);
			return;
		}

		Table.setPlayerAllin(); // Able to go allin so do it
		++Table.flopAllin[Table.seat];
		Table.why[Table.seat] = 51;
		Table.action[Table.seat] = ALLIN;

	}

	/*-
	  *
	 * Helper method for playHandMDFFlop
	  *
	 */
	private static boolean playHandMDFFlopCheck() {
		errorCheck();
		Table.action[Table.seat] = BET1;
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_MDFCHECK;
		if (Table.seat == seatButton && Table.strategy[stratIndex].flopMDFButton.rangeArray[handIndex] > 0) {
			doRaise(87, POSTFLOP_MDF);
			++Table.mdfRaiseCountFlop[Table.seat];
			return true;
		}
		if (Table.seat == seatCutoff && Table.strategy[stratIndex].flopMDFCutoff.rangeArray[handIndex] > 0) {
			doRaise(87, POSTFLOP_MDF);
			++Table.mdfRaiseCountFlop[Table.seat];
			return true;
		}
		if (Table.seat == seatMP && Table.strategy[stratIndex].flopMDFMP.rangeArray[handIndex] > 0) {
			doRaise(87, POSTFLOP_MDF);
			++Table.mdfRaiseCountFlop[Table.seat];
			return true;
		}
		if (Table.seat == seatUTG && Table.strategy[stratIndex].flopMDFUTG.rangeArray[handIndex] > 0) {
			doRaise(87, POSTFLOP_MDF);
			++Table.mdfRaiseCountFlop[Table.seat];
			return true;
		}
		if (Table.seat == seatBB && Table.strategy[stratIndex].flopMDFBB.rangeArray[handIndex] > 0) {
			doRaise(87, POSTFLOP_MDF);
			++Table.mdfRaiseCountFlop[Table.seat];
			return true;
		}
		if (!(Table.seat == seatSB && Table.strategy[stratIndex].flopMDFSB.rangeArray[handIndex] > 0)) {
			return false;
		}
		doRaise(87, POSTFLOP_MDF);
		++Table.mdfRaiseCountFlop[Table.seat];
		return true;
	}

	/*-
	  *
	 * Helper method for playHandMDFFlop
	  * 
	 */
	private static boolean playHandMDFFlopBet1() {
		errorCheck();
		Table.action[Table.seat] = BET2;
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = Table.DEC_MDFBET1;

		if (Table.seat == seatButton && Table.strategy[stratIndex].flopMDFBet2Button.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++Table.mdfRaiseBet2CountFlop[Table.seat];
			return true;
		}
		if (Table.seat == seatCutoff && Table.strategy[stratIndex].flopMDFBet2Cutoff.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++Table.mdfRaiseBet2CountFlop[Table.seat];
			return true;
		}
		if (Table.seat == seatMP && Table.strategy[stratIndex].flopMDFBet2MP.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++Table.mdfRaiseBet2CountFlop[Table.seat];
			return true;
		}
		if (Table.seat == seatUTG && Table.strategy[stratIndex].flopMDFBet2UTG.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++Table.mdfRaiseBet2CountFlop[Table.seat];
			return true;
		}
		if (Table.seat == seatBB && Table.strategy[stratIndex].flopMDFBet2BB.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++Table.mdfRaiseBet2CountFlop[Table.seat];
			return true;
		}
		if (Table.seat == seatSB && Table.strategy[stratIndex].flopMDFBet2SB.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++Table.mdfRaiseBet2CountFlop[Table.seat];
			return true;
		}

		if (Table.betType == Table.BET1 && Options.mdfFlop[Table.seat]) {
			Table.why[Table.seat] = 41;
			Table.action[Table.seat] = CALL;
			Table.range[Table.seat] = POSTFLOP_MDFCALL;

			if (Table.seat == seatButton && Table.strategy[stratIndex].flopMDFCallButton.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++Table.mdfCallCountFlop[Table.seat];
				return true;
			}
			if (Table.seat == seatCutoff && Table.strategy[stratIndex].flopMDFCallCutoff.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++Table.mdfCallCountFlop[Table.seat];
				return true;
			}
			if (Table.seat == seatMP && Table.strategy[stratIndex].flopMDFCallMP.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++Table.mdfCallCountFlop[Table.seat];
				return true;
			}
			if (Table.seat == seatUTG && Table.strategy[stratIndex].flopMDFCallUTG.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++Table.mdfCallCountFlop[Table.seat];
				return true;
			}
			if (Table.seat == seatBB && Table.strategy[stratIndex].flopMDFCallBB.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++Table.mdfCallCountFlop[Table.seat];
				return true;
			}
			if (Table.seat == seatSB && Table.strategy[stratIndex].flopMDFCallSB.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++Table.mdfCallCountFlop[Table.seat];
				return true;
			}
		}
		return false;
	}



	/*- 
	 *
	*	Play CBet. 
	 *
	*/
	private static boolean playHandCBet() {
		errorCheck();
		stratIndex = Table.strategyIndex[Table.seat];
		++Table.flopToMeCBetOpportunity[Table.seat];

		if (Evaluate.evalArray[Evaluate.BOARD_WET]) {
			if (Table.seat == seatButton && Table.strategy[stratIndex].flopCBetWetButton.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if (Table.seat == seatCutoff && Table.strategy[stratIndex].flopCBetWetCutoff.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if (Table.seat == seatMP && Table.strategy[stratIndex].flopCBetWetMP.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if (Table.seat == seatUTG && Table.strategy[stratIndex].flopCBetWetUTG.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if (Table.seat == seatBB && Table.strategy[stratIndex].flopCBetWetBB.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if (Table.seat == seatSB && Table.strategy[stratIndex].flopCBetWetSB.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			return false;
		}

		if (Evaluate.evalArray[Evaluate.BOARD_DRY]) {
			if (Table.seat == seatButton && Table.strategy[stratIndex].flopCBetDryButton.rangeArray[handIndex] > 0) {
				doCBetDry();
				return true;
			}
			if (Table.seat == seatCutoff && Table.strategy[stratIndex].flopCBetDryCutoff.rangeArray[handIndex] > 0) {
				doCBetDry();
				return true;
			}
			if (Table.seat == seatMP && Table.strategy[stratIndex].flopCBetDryMP.rangeArray[handIndex] > 0) {
				doCBetDry();
				return true;
			}

			if (Table.seat == seatUTG && Table.strategy[stratIndex].flopCBetDryUTG.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if ((Table.seat == seatBB && Table.strategy[stratIndex].flopCBetDryBB.rangeArray[handIndex] > 0)
					|| (Table.seat == seatSB && Table.strategy[stratIndex].flopCBetDrySB.rangeArray[handIndex] > 0)) {
				doCBetDry();
				return true;
			}
			return false;
		}

		if (Evaluate.evalArray[Evaluate.BOARD_NOT_WET_DRY]) {
			if (Table.seat != seatButton
					&& Table.strategy[stratIndex].flopCBetNeutralButton.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
			if (Table.seat != seatCutoff
					&& Table.strategy[stratIndex].flopCBetNeutralCutoff.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
			if (Table.seat != seatMP && Table.strategy[stratIndex].flopCBetNeutralMP.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
			if (Table.seat != seatUTG && Table.strategy[stratIndex].flopCBetNeutralUTG.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
			if (Table.seat != seatBB && Table.strategy[stratIndex].flopCBetNeutralBB.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
			if (Table.seat != seatSB && Table.strategy[stratIndex].flopCBetNeutralSB.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
		}
		return false;
	}

	/*-
	  *
	 * Do CBet Wet.
	  *
	 */
	private static void doCBetWet() {
		errorCheck();
		Table.wetDry[Table.seat] = WET;
		Table.action[Table.seat] = BET1;
		++Table.flopCBetWet[Table.seat];
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = BET1;
		doRaise(45, POSTFLOP_CBETWET);
	}

	/*-
	  *
	 * Do CBet Dry.
	  * 
	 */
	private static void doCBetDry() {
		errorCheck();
		Table.wetDry[Table.seat] = DRY;
		Table.action[Table.seat] = BET1;
		++Table.flopCBetDry[Table.seat];
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = BET1;
		doRaise(46, POSTFLOP_CBETDRY);
	}

	/*-
	 *
	 * Do CBet Neurtal.
	  *
	 */
	private static void doCBetNeutral() {
		errorCheck();
		Table.wetDry[Table.seat] = NEUTRAL;
		Table.action[Table.seat] = BET1;
		++Table.flopCBetNeutral[Table.seat];
		Table.decisionProcessRule[Table.streetNumber][Table.seat][Table.orbit] = BET1;
		doRaise(47, POSTFLOP_CBETNEUTRAL);
	}


/*-
	 *
	 *  Do call, check or fold.
	  *  
	 */
	private static void doCallCheckFold() {
		errorCheck();
		final double call = Table.betNow - Table.moneyIn[Table.seat];
		final double max = Table.stack[Table.seat] - call; // Max available to raise or call

		if (max >= call) {
			Table.action[Table.seat] = CALL;
			Table.setPlayerCalled();
			return;
		}

		if (Table.betNow == 0) {
			if (Table.why[Table.seat] == 0) {
				Table.action[Table.seat] = CHECK;
				Table.why[Table.seat] = 80;
				Table.range[Table.seat] = -1;
			}
			Table.setPlayerChecked();
			return;
		}

		setPlayerFoldedMsg(95, FOLD);
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
		if (active == 1 && EvalData.betNowBD.compareTo(zeroBD) != 0 && !EvalData.betCalled) {
			returnUncalledBet();
		}
	}

	/*- **************************************************************************** 
	*  Return uncalled bet
	 **************************************************************************** */
	static void returnUncalledBet() {
		int s = -1;
		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i]) {
				s = i;
				break;
			}
		}
		EvalData.moneyInBD[s] = EvalData.moneyInBD[s].subtract(EvalData.betNowBD);
		EvalData.stackBD[s] = EvalData.stackBD[s].add(EvalData.betNowBD);
		EvalData.potBD = EvalData.potBD.subtract(EvalData.betNowBD);
		EvalData.returnedToBD[0][s][EvalData.orbit] = EvalData.betNowBD;
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
			return true;
		}
		for (int i = 0; i < PLAYERS; ++i) {
			// Street is only complete if money is right
			if (!EvalData.playerFolded[i]) {
				// streetCompleteB 4 224.00 56.00
			}
			if (!EvalData.playerFolded[i] && EvalData.betNowBD.compareTo(EvalData.moneyInBD[i]) != 0
					&& !EvalData.playerAllin[i]) {
				return false;
			}
		}
		EvalData.streetComplete = true;
		return true;
	}

}
