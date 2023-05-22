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

		if (!rangesDone) {
			createRanges();
			createPlayers();
		}
		Deck.newDeck();
		if (EvalData.hh) {
			HandHistory.initialize();
		}

		while (played <= numToPlay) {
			System.out.println("//********* Starting game " + played);
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
			System.out.println("//playPreflop() ");
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
			++played;
		}
		if (EvalData.hh) {
			HandHistory.closeHH();
		}
		GUIReports.reportPreflop(300, 300);
	}

	/*- **************************************************************************** 
	 * Initialize variables in EvalData
	 * Start of a new game
	 **************************************************************************** */
	private static void initializeData() {
		for (int i = 0; i < PLAYERS; i++) {
			EvalData.playerFoldedPreflop[i] = false;
			EvalData.playerLostShowdown[i] = false;
			EvalData.playerWonShowdown[i] = false;
			EvalData.playerWon[i] = false;
			EvalData.playerAllin[i] = false;
			EvalData.playerFolded[i] = false;
			EvalData.playerSidePotSplitTotalBD[i] = zeroBD;
			EvalData.moneyInBD[i] = zeroBD;
		}

		EvalData.streetNumber = PREFLOP;
		EvalData.foldCount = 0;
		EvalData.allinCount = 0;
		EvalData.limpCount = 0;
		EvalData.potBD = zeroBD;
		EvalData.mainPotBD = zeroBD;
		EvalData.handsPlayed = 0;
		EvalData.streetComplete = false;
		EvalData.boardComplete = false;
		EvalData.betType = 0;
		EvalData.orbit = 0;
		EvalData.betNowBD = zeroBD;
		EvalData.betToMeBD = zeroBD;
		EvalData.betCalled = true;
		EvalData.handsPlayed = 0;
		// Hand history data
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
					EvalData.callBD[i][j][k] = zeroBD;
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
		initializeData();
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
		if (!EvalData.playerFolded[seatSB] && !playerAllin[seatSB]) {
			seat = seatSB;
			pos = SB;
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				System.out.println("//preflopOrbit23A()  " + EvalData.foldCount);
				return;
			}
		}
		if (!EvalData.playerFolded[seatBB] && !playerAllin[seatBB]) {
			seat = seatBB;
			pos = BB;
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				System.out.println("//preflopOrbit23B()  " + EvalData.foldCount);
				return;
			}
		}
		if (!EvalData.playerFolded[seatUTG] && !playerAllin[seatUTG]) {
			seat = seatUTG;
			pos = UTG;
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				System.out.println("//preflopOrbit23C()  " + EvalData.foldCount);
				return;
			}
		}
		if (!playerFolded[seatMP] && !playerAllin[seatMP]) {
			seat = seatMP;
			pos = MP;
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				System.out.println("//preflopOrbit23D()  " + EvalData.foldCount);
				return;
			}
		}
		seat = seatCO;
		pos = CO;
		if (!EvalData.playerFolded[seatCO] && !playerAllin[seatCO]) {
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				System.out.println("//preflopOrbit23E()  " + EvalData.foldCount);
				return;
			}
		}
		seat = seatBU;
		pos = BU;
		if (EvalData.playerFolded[seatBU] && !playerAllin[seatBU]) {
			playHandPreflop();
			if (EvalData.foldCount == MAXFOLDED || streetComplete()) {
				System.out.println("//preflopOrbit23F()  " + EvalData.foldCount);
			}
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
		System.out.println("returnUncalledBet " + EvalData.betNowBD);
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
		for (int i = 0; i < PLAYERS; ++i) {
			// Street is only complete if money is right
			if (!EvalData.playerFolded[i]) {
				// streetCompleteB 4 224.00 56.00
				System.out.println(new StringBuilder().append("streetCompleteB ").append(EvalData.foldCount).append(" ")
						.append(EvalData.betNowBD).append(" ").append(EvalData.moneyInBD[i]).toString());
			}
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
