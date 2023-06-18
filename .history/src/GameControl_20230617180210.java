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

		// Play hand CO
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
		EvalData.streetComplete = false;
			flopOrbit();
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			 streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

	 
		flopOrbit();
	
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			 streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		 		flopOrbit();
	

		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

			flopOrbit();
		
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		if (streetComplete()) {
			return;
		}
	
	}

	/*-
	 *
	*	Play Turn.
	 *
	 */
	private static void playTurn() {
		EvalData.streetComplete = false;
		turnOrbit();
			if (EvalData.foldCount == EvalData.MAXFOLDED) {
			streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		turnOrbit();
	

		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}
			turnOrbit();
	
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		turnOrbit();
	
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		if (EvalData.streetComplete) {
			return;
		}
			}

	/*-
	*
	 *  - - Play River.
	*
	 */
	private static void playRiver() {
EvalData.streetComplete = false;
		riverOrbit();
		
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			streetComplete();
			EvalData.streetComplete = true;
			return;
		}

		if (streetComplete()) {
			return;
		}
		riverOrbit();
	
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}
 
		riverOrbit();
	
	if (EvalData.foldCount == EvalData.MAXFOLDED) {
			streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

	
		riverOrbit();
	
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			streetComplete();
			EvalData.streetComplete = true;
			return;
		}
		if (streetComplete()) {
			return;
		}

		if (EvalData.streetComplete) {
			return;
		}
			}


	/*-
	 *
	 *	Play one Orbit.
	 *	Assumes 6 Max.
	  *
	 */
	private static void flopOrbit() {
		if (!EvalData.playerFolded[seatSB] && !EvalData.playerAllin[seatSB]) {
			EvalData.seat = seatSB;
			if (EvalData.orbit == 0) {
				++EvalData.flopCountPos[EvalData.SB];
			}
			flopPlay();
		}

		if (!EvalData.playerFolded[seatBB] && !EvalData.playerAllin[seatBB]) {
			EvalData.seat = seatBB;
			flopPlay();
		}

		if (!EvalData.playerFolded[seatUTG] && !EvalData.playerAllin[seatUTG]) {
			EvalData.seat = seatUTG;
			flopPlay();
		}

		if (!EvalData.playerFolded[seatMP] && !EvalData.playerAllin[seatMP]) {
			EvalData.seat = seatMP;
				flopPlay();
		}

		if (!EvalData.playerFolded[seatCO] && !EvalData.playerAllin[seatCO]) {
			EvalData.seat = seatCO;
						flopPlay();
		}

		if (!(!EvalData.playerFolded[seatBU] && !EvalData.playerAllin[seatBU])) {
			return;
		}
		EvalData.seat = seatBU;
		if (EvalData.orbit == 0) {
			++EvalData.flopCountPos[EvalData.BUTTON];
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
		if (!EvalData.playerFolded[seatSB] && !EvalData.playerAllin[seatSB]) {
			EvalData.seat = seatSB;
						turnPlay();
		}

		if (!EvalData.playerFolded[seatBB] && !EvalData.playerAllin[seatBB]) {
			EvalData.seat = seatBB;
					turnPlay();
		}

		if (!EvalData.playerFolded[seatUTG] && !EvalData.playerAllin[seatUTG]) {
			EvalData.seat = seatUTG;
						turnPlay();
		}

		if (!EvalData.playerFolded[seatMP] && !EvalData.playerAllin[seatMP]) {
			EvalData.seat = seatMP;
						turnPlay();
		}

		if (!EvalData.playerFolded[seatCO] && !EvalData.playerAllin[seatCO]) {
			EvalData.seat = seatCO;
						turnPlay();
		}

		if (!(!EvalData.playerFolded[seatBU] && !EvalData.playerAllin[seatBU])) {
			return;
		}
		EvalData.seat = seatBU;
				turnPlay();
	}

	/*-
	 *
	 * Play one Orbit
	  *
	 */
	private static void riverOrbit() {
		if (!EvalData.playerFolded[seatSB] && !EvalData.playerAllin[seatSB]) {
			EvalData.seat = seatSB;
						riverPlay();
		}

		if (!EvalData.playerFolded[seatBB] && !EvalData.playerAllin[seatBB]) {
			EvalData.seat = seatBB;
						riverPlay();
		}

		if (!EvalData.playerFolded[seatUTG] && !EvalData.playerAllin[seatUTG]) {
			EvalData.seat = seatUTG;
						riverPlay();
		}

		if (!EvalData.playerFolded[seatMP] && !EvalData.playerAllin[seatMP]) {
			EvalData.seat = seatMP;
						riverPlay();
		}

		if (!EvalData.playerFolded[seatCO] && !EvalData.playerAllin[seatCO]) {
			EvalData.seat = seatCO;
						riverPlay();
		}

		if (!(!EvalData.playerFolded[seatBU] && !EvalData.playerAllin[seatBU])) {
			return;
		}
		EvalData.seat = seatBU;
				riverPlay();

	}

	/*-
	 *
	 * - Play hand.
	 * 
	 */
	private static void flopPlay() {
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			return;
		}
		handIndex = EvalData.handIndex[EvalData.seat];

		
		playHandFlop();
		}

	/*-
	 *
	 * - Play hand.
	  *
	 */
	private static void turnPlay() {
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			return;
		}

		if (EvalData.moneyIn[EvalData.seat] == EvalData.betNow && EvalData.betType != EvalData.CHECK
				&& EvalData.moneyIn[EvalData.seat] != 0) {
			return;
		}

		++EvalData.turnCount[EvalData.seat];
		handIndex = EvalData.handIndex[EvalData.seat];

		
		playHandTurn();
			}

	/*-
	 *
	 * - Play hand.
	  *
	 */
	private static void riverPlay() {
		if (EvalData.foldCount == EvalData.MAXFOLDED) {
			return;
		}
		

		++EvalData.riverCount[EvalData.seat];
		
			playHandRiver();
			}


/*-
	 *
	 * Play hand Flop
	  * 
	 */
	private static void playHandFlop() {
	 
		getRelativePosition();
				

		// Check the Rules
		getRulePLay();
	//	EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = EvalData.DEC_RULES;
	//	EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = rulePlay;
		if (playHandRuleFlop()) {
			return;
		}

		
	//	EvalData.decisionProcess[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = EvalData.DEC_FINAL;
	//	final double call = EvalData.betNow - EvalData.moneyIn[EvalData.seat];
		if (call == 0) {
			
			return;
		}

		// If all else fails just fold

			}

	/*-
	  *
	 * Play hand Turn
	  * 
	 */
	private static void playHandTurn() {
	//	getRelativePosition();
		

		if (EvalData.orbit == 0) {
				final int hv = Evaluate.evaluateAll(EvalData.seat);
	//		handValue[EvalData.seat] = hv;
	//		EvalData.stgy_HandValue[EvalData.streetNumber][EvalData.seat] = hv;
			
		}

				// Check the Rules

		getRulePLay();
//		EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = EvalData.DEC_RULES;
	//	EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = rulePlay;
		if (playHandRule()) {
			return;
		}

		
		

	//	EvalData.decisionProcess[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = EvalData.DEC_FINAL;
	//	final double call = EvalData.betNow - EvalData.moneyIn[EvalData.seat];
		if (call == 0) {
			
			return;
		}
	//	payerFolded();
		}
	}

	/*-
	 *
	 * Play hand River\
	  *
	 */
	private static void playHandRiver() {
	//		getRelativePosition();
	//	EvalData.decisionProcessOpponentAction[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = EvalData.betType;
	//	handIndex = EvalData.handIndex[EvalData.seat];

		if (EvalData.orbit == 0) {
	//				final int hv = Evaluate.evaluateAll(EvalData.seat);
		//	handValue[EvalData.seat] = hv;
			
		}

		

		// Check the Rules
		getRulePLay();
	//	EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = EvalData.DEC_RULES;
	//	EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = rulePlay;
		if (playHandRule()) {
			return;
		}

		

	//	EvalData.decisionProcess[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = EvalData.DEC_FINAL;
		//final double call = EvalData.betNow - EvalData.moneyIn[EvalData.seat];
	//	if (call == 0) {
	//					return;
	//	}
		// If all else fails just fold
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
	 *		EvalData.rule[EvalData.seat]   - RULEHU, RULE,		( for "Why" ) // TODO not good design
	 * 
	 */
	private static void getRulePLay() {
	//	rulePlay = -1;
	//	EvalData.decisionProcess[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = EvalData.DEC_RULES;
intfirst =
		// First to act
		if (EvalData.seat == first) {
			if (EvalData.foldCount - EvalData.PLAYERS == 2) {
				// Heads up
				EvalData.rule[EvalData.seat] = RULEHU;
				if (EvalData.streetNumber == EvalData.FLOP) {
		//			rulePlay = EvalData.strategy[stratIndex].flopFirstRules.ruleArray[handValue[EvalData.seat]][EvalData.betType];
					return;
				}
				if (EvalData.streetNumber == EvalData.TURN) {
			//		rulePlay = EvalData.strategy[stratIndex].turnFirstRules.ruleArray[handValue[EvalData.seat]][EvalData.betType];
					return;
				}
			//	rulePlay = EvalData.strategy[stratIndex].riverFirstRules.ruleArray[handValue[EvalData.seat]][EvalData.betType];
				return;
			}
			// Not heads up
		//	EvalData.rule[EvalData.seat] = RULE;
			if (EvalData.streetNumber == EvalData.FLOP) {
			//	rulePlay = EvalData.strategy[stratIndex].flopFirstRulesHU.ruleArray[handValue[EvalData.seat]][EvalData.betType];
				return;
			}
			if (EvalData.streetNumber == EvalData.TURN) {
			//	rulePlay = EvalData.strategy[stratIndex].turnFirstRulesHU.ruleArray[handValue[EvalData.seat]][EvalData.betType];
				return;
			}
		//	rulePlay = EvalData.strategy[stratIndex].riverFirstRulesHU.ruleArray[handValue[EvalData.seat]][EvalData.betType];
			return;
		}

		// Last to act
		if (EvalData.seat == last) {
			if (EvalData.foldCount - EvalData.PLAYERS == 2) {// Heads up
				// Heads up
				EvalData.rule[EvalData.seat] = RULEHU;
				if (EvalData.streetNumber == EvalData.FLOP) {
					rulePlay = EvalData.strategy[stratIndex].flopLastRules.ruleArray[handValue[EvalData.seat]][EvalData.betType];
					return;
				}
				if (EvalData.streetNumber == EvalData.TURN) {
					rulePlay = EvalData.strategy[stratIndex].turnLastRules.ruleArray[handValue[EvalData.seat]][EvalData.betType];
					return;
				}
				rulePlay = EvalData.strategy[stratIndex].riverLastRules.ruleArray[handValue[EvalData.seat]][EvalData.betType];
				return;
			}
			// Not heads up
			EvalData.rule[EvalData.seat] = RULE;
			if (EvalData.streetNumber == EvalData.FLOP) {
				rulePlay = EvalData.strategy[stratIndex].flopLastRulesHU.ruleArray[handValue[EvalData.seat]][EvalData.betType];
				return;
			} else if (EvalData.streetNumber == EvalData.TURN) {
				rulePlay = EvalData.strategy[stratIndex].turnLastRulesHU.ruleArray[handValue[EvalData.seat]][EvalData.betType];
				return;
			}
			rulePlay = EvalData.strategy[stratIndex].riverLastRulesHU.ruleArray[handValue[EvalData.seat]][EvalData.betType];
			return;
		}

		// If not first or last then it has to be middle
		EvalData.rule[EvalData.seat] = RULE;
		if (EvalData.streetNumber == EvalData.FLOP) {
			rulePlay = EvalData.strategy[stratIndex].flopMiddleRules.ruleArray[handValue[EvalData.seat]][EvalData.betType];
			return;
		}
		if (EvalData.streetNumber == EvalData.TURN) {
			rulePlay = EvalData.strategy[stratIndex].turnMiddleRules.ruleArray[handValue[EvalData.seat]][EvalData.betType];
			return;
		}
		rulePlay = EvalData.strategy[stratIndex].riverMiddleRules.ruleArray[handValue[EvalData.seat]][EvalData.betType];
	}

	/*-
	 *
	 *  Play hand using rules. Flop - handles Bet, Call, and All In
	  * 
	 */
	private static boolean playHandRuleFlop() {
		if (rulePlay == Rules.BET) {
			EvalData.why[EvalData.seat] = 53;
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
		if (rulePlay == Rules.BET) {
			EvalData.why[EvalData.seat] = 53;
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
		EvalData.rule[EvalData.seat] = RULE;
		EvalData.why[EvalData.seat] = 49;

		if (EvalData.betType == EvalData.BET1) {
			++EvalData.flopCallBet1[EvalData.seat];
			doCallCheckFold();
			return true;
		}

		if (EvalData.betType == EvalData.BET2) {
			++EvalData.flopCallBet2[EvalData.seat];
			doCallCheckFold();
			return true;
		}

		if (EvalData.betType == EvalData.BET3) {
			++EvalData.flopCallBet3[EvalData.seat];
			doCallCheckFold();
			return true;
		}

		if (EvalData.betType == EvalData.BET4) {
			++EvalData.flopCallBet4[EvalData.seat];
			doCallCheckFold();
			return true;
		}

		if (EvalData.betType != EvalData.ALLIN) {
			return false;
		}
		++EvalData.flopCallAllin[EvalData.seat];
		doCallCheckFold();
		return true;
	}

	/*-
	  *
	 * Helper method for playHandRuleTurn().
	  *
	 */
	private static boolean playHandRuleCall() {
		EvalData.rule[EvalData.seat] = RULE;
		EvalData.why[EvalData.seat] = 49;

		if (EvalData.betType == EvalData.BET1) {
			doCallCheckFold();
			return true;
		}

		if (EvalData.betType == EvalData.BET2) {
			doCallCheckFold();
			return true;
		}

		if (EvalData.betType == EvalData.BET3) {
			doCallCheckFold();
			return true;
		}

		if (EvalData.betType == EvalData.BET4) {
			doCallCheckFold();
			return true;
		}

		if (EvalData.betType != EvalData.ALLIN) {
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
			final double call = EvalData.betNow - EvalData.moneyIn[EvalData.seat];
		if (EvalData.playerAllin[EvalData.seat] && EvalData.allinCount > 0 && call > EvalData.stack[EvalData.seat]) {
			setPlayerCalledAllin(52, POSTFLOP_RULE);
			return;
		}

		if (EvalData.stack[EvalData.seat] - call <= 0) {
			setPlayerCalledAllin(52, POSTFLOP_RULE);
			return;
		}

		EvalData.setPlayerAllin(); // Able to go allin so do it
		++EvalData.flopAllin[EvalData.seat];
		EvalData.why[EvalData.seat] = 51;
		EvalData.action[EvalData.seat] = ALLIN;

	}

	/*-
	  *
	 * Helper method for playHandMDFFlop
	  *
	 */
	private static boolean playHandMDFFlopCheck() {
			EvalData.action[EvalData.seat] = BET1;
		EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = EvalData.DEC_MDFCHECK;
		if (EvalData.seat == seatButton && EvalData.strategy[stratIndex].flopMDFButton.rangeArray[handIndex] > 0) {
			doRaise(87, POSTFLOP_MDF);
			++EvalData.mdfRaiseCountFlop[EvalData.seat];
			return true;
		}
		if (EvalData.seat == seatCO && EvalData.strategy[stratIndex].flopMDFCO.rangeArray[handIndex] > 0) {
			doRaise(87, POSTFLOP_MDF);
			++EvalData.mdfRaiseCountFlop[EvalData.seat];
			return true;
		}
		if (EvalData.seat == seatMP && EvalData.strategy[stratIndex].flopMDFMP.rangeArray[handIndex] > 0) {
			doRaise(87, POSTFLOP_MDF);
			++EvalData.mdfRaiseCountFlop[EvalData.seat];
			return true;
		}
		if (EvalData.seat == seatUTG && EvalData.strategy[stratIndex].flopMDFUTG.rangeArray[handIndex] > 0) {
			doRaise(87, POSTFLOP_MDF);
			++EvalData.mdfRaiseCountFlop[EvalData.seat];
			return true;
		}
		if (EvalData.seat == seatBB && EvalData.strategy[stratIndex].flopMDFBB.rangeArray[handIndex] > 0) {
			doRaise(87, POSTFLOP_MDF);
			++EvalData.mdfRaiseCountFlop[EvalData.seat];
			return true;
		}
		if (!(EvalData.seat == seatSB && EvalData.strategy[stratIndex].flopMDFSB.rangeArray[handIndex] > 0)) {
			return false;
		}
		doRaise(87, POSTFLOP_MDF);
		++EvalData.mdfRaiseCountFlop[EvalData.seat];
		return true;
	}

	/*-
	  *
	 * Helper method for playHandMDFFlop
	  * 
	 */
	private static boolean playHandMDFFlopBet1() {

		EvalData.action[EvalData.seat] = BET2;
		EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = EvalData.DEC_MDFBET1;

		if (EvalData.seat == seatButton && EvalData.strategy[stratIndex].flopMDFBet2Button.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++EvalData.mdfRaiseBet2CountFlop[EvalData.seat];
			return true;
		}
		if (EvalData.seat == seatCO && EvalData.strategy[stratIndex].flopMDFBet2CO.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++EvalData.mdfRaiseBet2CountFlop[EvalData.seat];
			return true;
		}
		if (EvalData.seat == seatMP && EvalData.strategy[stratIndex].flopMDFBet2MP.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++EvalData.mdfRaiseBet2CountFlop[EvalData.seat];
			return true;
		}
		if (EvalData.seat == seatUTG && EvalData.strategy[stratIndex].flopMDFBet2UTG.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++EvalData.mdfRaiseBet2CountFlop[EvalData.seat];
			return true;
		}
		if (EvalData.seat == seatBB && EvalData.strategy[stratIndex].flopMDFBet2BB.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++EvalData.mdfRaiseBet2CountFlop[EvalData.seat];
			return true;
		}
		if (EvalData.seat == seatSB && EvalData.strategy[stratIndex].flopMDFBet2SB.rangeArray[handIndex] > 0) {
			doRaise(55, POSTFLOP_MDFBET2);
			++EvalData.mdfRaiseBet2CountFlop[EvalData.seat];
			return true;
		}

		if (EvalData.betType == EvalData.BET1 && Options.mdfFlop[EvalData.seat]) {
			EvalData.why[EvalData.seat] = 41;
			EvalData.action[EvalData.seat] = CALL;
			EvalData.range[EvalData.seat] = POSTFLOP_MDFCALL;

			if (EvalData.seat == seatButton && EvalData.strategy[stratIndex].flopMDFCallButton.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++EvalData.mdfCallCountFlop[EvalData.seat];
				return true;
			}
			if (EvalData.seat == seatCO && EvalData.strategy[stratIndex].flopMDFCallCO.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++EvalData.mdfCallCountFlop[EvalData.seat];
				return true;
			}
			if (EvalData.seat == seatMP && EvalData.strategy[stratIndex].flopMDFCallMP.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++EvalData.mdfCallCountFlop[EvalData.seat];
				return true;
			}
			if (EvalData.seat == seatUTG && EvalData.strategy[stratIndex].flopMDFCallUTG.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++EvalData.mdfCallCountFlop[EvalData.seat];
				return true;
			}
			if (EvalData.seat == seatBB && EvalData.strategy[stratIndex].flopMDFCallBB.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++EvalData.mdfCallCountFlop[EvalData.seat];
				return true;
			}
			if (EvalData.seat == seatSB && EvalData.strategy[stratIndex].flopMDFCallSB.rangeArray[handIndex] > 0) {
				doCallCheckFold();
				++EvalData.mdfCallCountFlop[EvalData.seat];
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
			stratIndex = EvalData.strategyIndex[EvalData.seat];
		++EvalData.flopToMeCBetOpportunity[EvalData.seat];

		if (Evaluate.evalArray[Evaluate.BOARD_WET]) {
			if (EvalData.seat == seatButton && EvalData.strategy[stratIndex].flopCBetWetButton.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if (EvalData.seat == seatCO && EvalData.strategy[stratIndex].flopCBetWetCO.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if (EvalData.seat == seatMP && EvalData.strategy[stratIndex].flopCBetWetMP.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if (EvalData.seat == seatUTG && EvalData.strategy[stratIndex].flopCBetWetUTG.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if (EvalData.seat == seatBB && EvalData.strategy[stratIndex].flopCBetWetBB.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if (EvalData.seat == seatSB && EvalData.strategy[stratIndex].flopCBetWetSB.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			return false;
		}

		if (Evaluate.evalArray[Evaluate.BOARD_DRY]) {
			if (EvalData.seat == seatButton && EvalData.strategy[stratIndex].flopCBetDryButton.rangeArray[handIndex] > 0) {
				doCBetDry();
				return true;
			}
			if (EvalData.seat == seatCO && EvalData.strategy[stratIndex].flopCBetDryCO.rangeArray[handIndex] > 0) {
				doCBetDry();
				return true;
			}
			if (EvalData.seat == seatMP && EvalData.strategy[stratIndex].flopCBetDryMP.rangeArray[handIndex] > 0) {
				doCBetDry();
				return true;
			}

			if (EvalData.seat == seatUTG && EvalData.strategy[stratIndex].flopCBetDryUTG.rangeArray[handIndex] > 0) {
				doCBetWet();
				return true;
			}
			if ((EvalData.seat == seatBB && EvalData.strategy[stratIndex].flopCBetDryBB.rangeArray[handIndex] > 0)
					|| (EvalData.seat == seatSB && EvalData.strategy[stratIndex].flopCBetDrySB.rangeArray[handIndex] > 0)) {
				doCBetDry();
				return true;
			}
			return false;
		}

		if (Evaluate.evalArray[Evaluate.BOARD_NOT_WET_DRY]) {
			if (EvalData.seat != seatButton
					&& EvalData.strategy[stratIndex].flopCBetNeutralButton.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
			if (EvalData.seat != seatCO
					&& EvalData.strategy[stratIndex].flopCBetNeutralCO.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
			if (EvalData.seat != seatMP && EvalData.strategy[stratIndex].flopCBetNeutralMP.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
			if (EvalData.seat != seatUTG && EvalData.strategy[stratIndex].flopCBetNeutralUTG.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
			if (EvalData.seat != seatBB && EvalData.strategy[stratIndex].flopCBetNeutralBB.rangeArray[handIndex] > 0) {
				doCBetNeutral();
				return true;
			}
			if (EvalData.seat != seatSB && EvalData.strategy[stratIndex].flopCBetNeutralSB.rangeArray[handIndex] > 0) {
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
			EvalData.wetDry[EvalData.seat] = WET;
		EvalData.action[EvalData.seat] = BET1;
		++EvalData.flopCBetWet[EvalData.seat];
		EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = BET1;
		doRaise(45, POSTFLOP_CBETWET);
	}

	/*-
	  *
	 * Do CBet Dry.
	  * 
	 */
	private static void doCBetDry() {
			EvalData.wetDry[EvalData.seat] = DRY;
		EvalData.action[EvalData.seat] = BET1;
		++EvalData.flopCBetDry[EvalData.seat];
		EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = BET1;
		doRaise(46, POSTFLOP_CBETDRY);
	}

	/*-
	 *
	 * Do CBet Neurtal.
	  *
	 */
	private static void doCBetNeutral() {
			EvalData.wetDry[EvalData.seat] = NEUTRAL;
		EvalData.action[EvalData.seat] = BET1;
		++EvalData.flopCBetNeutral[EvalData.seat];
		EvalData.decisionProcessRule[EvalData.streetNumber][EvalData.seat][EvalData.orbit] = BET1;
		doRaise(47, POSTFLOP_CBETNEUTRAL);
	}


/*-
	 *
	 *  Do call, check or fold.
	  *  
	 */
	private static void doCallCheckFold() {
			final double call = EvalData.betNow - EvalData.moneyIn[EvalData.seat];
		final double max = EvalData.stack[EvalData.seat] - call; // Max available to raise or call

		if (max >= call) {
			EvalData.action[EvalData.seat] = CALL;
			EvalData.setPlayerCalled();
			return;
		}

		if (EvalData.betNow == 0) {
			if (EvalData.why[EvalData.seat] == 0) {
				EvalData.action[EvalData.seat] = CHECK;
				EvalData.why[EvalData.seat] = 80;
				EvalData.range[EvalData.seat] = -1;
			}
			EvalData.setPlayerChecked();
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
