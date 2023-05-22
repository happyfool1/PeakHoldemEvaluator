//package evaluate_streets;

public class Evaluate implements Constants {

	/*- ******************************************************************************
	 * This class provides access to most of the functionality provided by this project.
	 *
	 * There are several public methods:
	 * 		initialize()
	 * 		shuffle() 
	 * 		clearDeadCards()
	 * 		setHoleCards(int seat, Card card1, Card card2)
	 * 		setFlopCards(Card card1, Card card2, Card card3)
	 * 		setTurnCard(Card card1)
	 * 		setRiverCard(Card card1
	 * 		doFlop(int seat)
	 * 		doTurn(int seat)
	 * 		doRiver(int seat)
	 * 		doShowdownValue(int seat)
	 * 		doAllRiverAndHandValueAndShowdown();
	 * 		doShowdown() 
	 * 		doPreflopSimulation()
	 * 		doFullSimulation()
	 * 		setSeed(Long seed)
	 * 		setPath(String path)
	 * 		set1755Mode(boolean b)
	 * 		setDetailedAnalysisMode(boolean b)
	 * 
	 * There are 3 Classes that display evaluation results.
	 * All of the display methods allow you to specify the column and row
	 * for the display window.
	 * 		ReportOneHand.reportHands(int c, int r)
	 * 		ReportOneHand.reportHandArray(int c, int r)
	 * 		ReportOneHand.reportBoardArray(int c, int r)
	 * 		ReportOneHand.reportData(int c, int r) 
	 * 		ReportOneHand.reportBoardPossibleArrayFlop(int c, int r)
	 * 		ReportOneHand.reportBoardPossibleArrayTurn(int c, int r)
	 * 		ReportOneHand.reportMadeHandsRiver(int c, int r) 
	 * 		ReportOneHand.reportShowdown(int c, int r) 
	 * 
	 * 		ReportManyHands.reportBoardArrayFlopPer(int c, int r) 
	 * 		ReportManyHands.reportHML(int c, int r)
	 * 		ReportManyHands.reportArrayWetDry(int c, int r)
	 * 		ReportManyHands.reportHMLArrayWetDry(int c, int r)
	 * 
	 * 	These reports have replaced all of the REportOneHand reports in a GUI class.
	 * 		GUIReports.report(int c, int r) Report all 
	 * 		GUIReports.reportBoard(int c, int r)  report board
	 * 		GUIReports.reportPreflop(int c, int r) Hand History format
	 * 
	 * When running many hands, the normal mode, it is important to know that
	 * the Flop, Turn, and River do not need to be done in order. 
	 * We are only collecting data for analysis not actually simulating a game.
	 * If you are doing a simuation application of course order is important.
	 * In the Showdown mode there is no player interaction like folding on the
	 * Turn to change things. 
	 * Like this:
	 * for (int i = 0; i < 1000000; i++) {
	 *		Evaluate.dealTable(); // Hole cards for six, Flop, Turn, River
	 *		Evaluate.doFlop(0);
	 *		Evaluate.doTurn(0);
	 *		Evaluate.doAllRiverAndHandValueAndShowdown();
	 *		Evaluate.doShowdown();
	 *	}
	 * 
	 * Results of the evaluation are stored in two data only classes.
	 * EvalData - Contains the detailed results from evaluating one hand.
	 * ManyData - Contains the detailed results of evaluating many hands.
	 * Evaluate is very fast, so a million or more hands takes less then one minute.
	 * this project does many things, all related to the 4 streets and to showdown.
	* This Class will evaluates hands, boards, hand strength, made hands, possible draws, 
	* Showdown and many other things.
	* 
	* It was originally developed as one component of PeakHoldem, a very large and
	* complex application that simulates 6-max Texas Hold'em play and that evaluates
	* Hand History files much the same as HoldemManager. 
	* Due to health reasons, I was unable to complete the products and get them to market.
	* Not to waste all of that code and design effort, I have decided to start separating 
	* the component classes and make them publically available without restriction.
	* 
	*
	* Showdown analysis
	* Determines the player with the best hand.
	* Much faster than any other code that I have been able to discover and
	* more accurate.
	* 
	 * There is a report program that will display the results of a hand analysis.
	 * 
	 * The original applications, PeakHoldem and PeakHoldemHandHistoryhave both
	 * been updated to use this new version of Evaluate.
	 * 
	 * @author PEAK_
	  ********************************************************************************/

	/*- ******************************************************************************
	* 
	* This code is being released on GitHub for public use without restrictions. 
	* I do ask that I be given credit if you use this in your project.
	* 
	* It was originally developed several years ago as part of the PeakHoldem 
	* 6-max Texas Hold'em product. For health reasons, the author Charles Peak
	* was unable to complete the product even though it was 90% complete. 
	* PeakHoldem was never oficially released. There is a PeakHoldem website
	* that has not been maintained but has a lot of information.
	* 
	* My health has now recovered, and I will finish this project as well as another 
	* project that analyzed Hand History files in ways that HoldemManager does not.
	* But, I will probably not try to market either project. 
	* 
	* There are literally hundreds of Classes coded and most are fully tested.
	* I do not want to waste the effort that I put into PeakHoldem and PeakHoldemEvaluator.
	* I don't plan to market, so will release the bulk of the code for public use.
	* It will be released in several independent Classes.
	* It will take some time because there will be necessary code modifications to seperate 
	* them from PeakHoldem and PeakHoldemEvaluator. I plan to finish both PeakHoldem and PeakHoldemEvaluator 
	* and will use the same code that is being released.
	* 
	* PeakHoldemEvaluatoris the first such package. This one.
	* 
	*
	 *  This code was originally developed for three projects:
	 *  	 game - Simulates a Texas Hold'em 6-max no limit game.
	 *  			It uses this Class to evaluate the Heros hand in the game, collecting
	 *  			detailed information. For his opponents it only collects information
	 *  			at Showdown when his hole cards are known. It is unique in that all
	 *  			play is controlled by external files that the user can edit. Patent Pending.
	 *  		hand_history_analysis looks at millions of Hand History files and saves
	 *  			summarized results. Not competing with Holdem Manager, instead it
	 *  			does a deeper level of evaluation. Unique in known applications. 
	 *  		evaluate - This project. I use it to run millions of hands gathering information
	 *  			on things like characterizing a Flop as HML or as one of 1755 possible Flops
	 *  			and determining how well each flop does at Showdown. Thus a way to determine
	 *  			how tp play any particular Flop. 
	 *
	 * 
	 * @author PEAK_
	******************************************************************* */

	static boolean initialized = false; // true if initialization done

	private Evaluate() {
		throw new IllegalStateException("Utility class");
	}

	/*- ******************************************************************************
	 * This method must always be called one time prior to calling any other methods.
	 * It performs essential initialization.
	  ********************************************************************************/
	static void initialize() {
		if (!initialized) {
			Deck.newDeck();
			Deck.shuffle();
			Analyze.initialize();
			Dealer.initialize();
		}
		initialized = true;
	}

	/*- ******************************************************************************
	 * This method will override the default which is to rotate after each hand.
	 * ********************************************************************************/
	static void setRotatePositions(boolean b) {
		EvalData.rotatePlayers = b;
	}

	/*- ******************************************************************************
	 * This method will override the default which is to rotate after each hand.
	 * It changes the value in EvalData and changes the HandRange in EvalData
	 * ********************************************************************************/
	static void setRanagePercent(int position, double percent) {
		EvalData.preflopRanges[position] = new HandRange();
		EvalData.preflopRanges[position].buildRangePercentage(percent);
	}

	/*- ******************************************************************************
	 * This method shuffles the deck of cards.
	 * Always call before starting a new hand.
	  ********************************************************************************/
	static void shuffle() {
		Deck.shuffle();
		Analyze.initialize();
	}

	/*- ******************************************************************************
	 * This method removes a card from the deck
	 * Used for experimentation.
	 * Arg0 - A card
	  ********************************************************************************/
	static void setDeadCard(Card card) {
		Deck.setDeadCard(card);
	}

	/*- ******************************************************************************
	 * This method clears all dead cards and returns the deck to normal.
	 * Used for experimentation.
	  ********************************************************************************/
	static void clearDeadCards() {
		Deck.clearDeadCards();
	}

	/*- ******************************************************************************
	 * This method sets the two hole cards.
	 * The cards are removed from Deck so there will be no accidental duplicates. 
	 * The hole cards are sorted.
	 * Arg0 - Seat number 0-5
	 * Arg1 - A card
	 * Arg2 - A card
	  ********************************************************************************/
	static void setHoleCards(int seat, Card card1, Card card2) {
		EvalData.holeCards[seat][0] = card1;
		EvalData.holeCards[seat][1] = card2;
		Dealer.sortHoleCards(seat);
		EvalData.holeCard1 = card1;
		EvalData.holeCard2 = card2;
		EvalData.handIndex = Dealer.getArrayIndexCard(card1, card2);
		EvalData.handIndexes[seat] = EvalData.handIndex;
		Deck.setDeadCard(card1);
		Deck.setDeadCard(card2);
		EvalData.playerFolded[seat] = false;
	}

	/*- ******************************************************************************
	 * This method sets the three flop cards
	 * The cards are removed from Deck so there will be no accidental duplicates. 
	 * The flop is sorted then copied into board.
	 * The cards are removed from Deck so there will be no accidental duplicates. 
	 * Arg0 - A card
	 * Arg1 - A card
	 * Arg2 - A card
	  ********************************************************************************/
	static void setFlopCards(Card card1, Card card2, Card card3) {
		EvalData.flop[0] = card1;
		EvalData.flop[1] = card2;
		EvalData.flop[2] = card3;
		Dealer.sortFlop();
		EvalData.board[0] = EvalData.flop[0];
		EvalData.boardUnsorted[0] = EvalData.flop[0];
		EvalData.board[1] = EvalData.flop[1];
		EvalData.boardUnsorted[1] = EvalData.flop[1];
		EvalData.board[2] = EvalData.flop[2];
		EvalData.boardUnsorted[2] = EvalData.flop[2];
		EvalData.cardCount = 3;
		Deck.setDeadCard(card1);
		Deck.setDeadCard(card2);
		Deck.setDeadCard(card3);
	}

	/*- ******************************************************************************
	 * This method sets Turn card.
	* The card is removed from Deck so there will be no accidental duplicates. 
	* The card is inserted into the board in order to keep it sorted. 
	* Flop is lready sorted so just do an insert.
	* Arg0 - A  Card
	  ********************************************************************************/
	static void setTurnCard(Card card) {
		EvalData.turnCard = card;
		EvalData.board[3] = card;
		EvalData.boardUnsorted[3] = card;
		Deck.setDeadCard(card);
		EvalData.cardCount = 4;
	}

	/*- ******************************************************************************
	* This method sets River card.
	* The card is removed from Deck so there will be no accidental duplicates. 
	* The card is inserted into the board in order to keep it sorted. 
	* Flop is lready sorted so just do an insert.
	* Arg0 - A  Card
	  ********************************************************************************/
	static void setRiverCard(Card card) {
		EvalData.riverCard = card;
		EvalData.board[4] = card;
		EvalData.boardUnsorted[4] = card;
		Deck.setDeadCard(card);
		EvalData.cardCount = 5;
	}

	/*- ******************************************************************************
	 * This method will evaluate the Flop.
	 * All results are in EvalData.
	 * Arg0 - Seat number 0 - 5
	  ********************************************************************************/
	static void doFlop(int seat) {
		Analyze.evaluateFlop(seat);
	}

	/*- ******************************************************************************
	 * This method will evaluate the Turn.
	 * All results are in EvalData.
	 * Arg0 - Seat number 0 - 5
	  ********************************************************************************/
	static void doTurn(int seat) {
		Analyze.evaluateTurn(seat);
	}

	/*- ******************************************************************************
	 * This method will evaluate the River.
	 * All results are in EvalData.
	 * Arg0 - Seat number 0 - 5
	  ********************************************************************************/
	static void doRiver(int seat) {
		Analyze.evaluateRiver(seat);
	}

	/*- ******************************************************************************
	 * This method will check the showdown value of a hand
	 * All results are in EvalData.
	 * Arg0 - Seat number 0 - 5
	  ********************************************************************************/
	static void doShowdownValue(int seat) {
		Showdown.showdownValue(seat);
	}

	/*- ******************************************************************************
	 * This method will do the River and Showdown value for all players that have not
	 * folded. 
	 * The River evaluation must be done prior to calling showdownValue because
	 * the data accumulated by River analysis is what is needed for the vakue of a hand.
	 * After all players have been to the River and hand value determined then 
	 * showdown is called to determine the best hand.
	 * Note, a tie is possible but unusual.
	 * All results are in EvalData.
	 * Arg0 - Seat number 0 - 5
	  ********************************************************************************/
	static void doAllRiverAndHandValueAndShowdown() {
		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i]) {
				Analyze.evaluateRiver(i);
				Showdown.showdownValue(i);
			}
		}
		Showdown.showdown();
	}

	/*- ******************************************************************************
	 * This method will do a showdown.
	 * For all seats that have active players ( hole cards have been set ) their
	 * made hands are determined and the best hand 
	  ********************************************************************************/
	static void doShowdown() {
		Showdown.showdown();
	}

	/*- ******************************************************************************
	* This method will do Preflop simulation.
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
	* Two classes are unique ti simulation, GameControl and Player.
	* GameControl controls the sequencing of play.
	* Player simulates play of one position and there is an instance for each seat.
	* 
	 ********************************************************************************/
	static void doPreflopSimulation() {
		GameControl.preflopSimulationOneHand();
	}

	/*- ******************************************************************************
	 * Set the value of a seed number for random hand generation.
	 * The seed is used to generate an infinate number of random hands.
	 * But, if restarted the same rando hands will be generated, very useful
	 * in debug and eveluation.
	 * A seed of 0L will result in a new random sequence every time.
	 * The seed is then also random. 
	  ********************************************************************************/
	static void setSeed(Long seed) {
		EvalData.seed = seed;
	}

	/*- ******************************************************************************
	 * This method does partial simulation mode.
	 * Cards are dealt to all players and preflop play begins.
	 *
	 * The GameControl Class, and the Player Class and the Dealer Class
	 * are now involved.
	 * HandRange instances are constructed for each position.
	 * 
	 * This method will do the River and Showdown value for all players that have not
	 * folded. 
	 * The River evaluation must be done prior to calling showdownValue because
	 * the data accumulated by River analysis is what is needed for the vakue of a hand.
	 * After all players have been to the River and hand value determined then 
	 * showdown is called to determine the best hand.
	 * Note, a tie is possible but unusual.
	 * All results are in EvalData.
	 * Arg0 - Seat number 0 - 5
	  ********************************************************************************/
	static void doSimulationMode() {
		Dealer.initialize();
	}

	/*- ******************************************************************************
	 * The path to a directory for your application is set. 
	 * Log files and any files created by this project will be in that directory.
	 * Default is "C:\\AplicationData\\"
	  ********************************************************************************/
	static void setPath(String path) {
		EvalData.applicationDirectory = path;
	}

	/*- ******************************************************************************
	 * This boolean controls the behavior of Analyze.
	 * If true then EvaluateMany is called to save data after each street.
	 * A lot of overhead as arrays are merged with previous data.
	 * Required if you want to evaluate something that requires a lot of hands.
	 * For example, you might want to run 100000 hands and then see what 
	 * percentage of times KJs resulted in the best hand.
	 * An unlimited opportunity to experiment.
	  ********************************************************************************/
	static void setManyHandsMode(boolean b) {
		EvalData.manyHands = b;
	}

	/*- ******************************************************************************
	 * More detailed analysis 
	 * A little more overhead
	********************************************************************************/
	static void setDetailedAnalysisMode(boolean b) {
		EvalData.detailedAnalysis = b;
	}

	/*- ******************************************************************************
	 * This boolean controls the behavior of Analyze.
	 *1755 is the number of possible flop hands.
	 * Too complex to explain hers. See  Flop1755Methods.
	 * 
	  ********************************************************************************/
	static void set1755Mode(boolean b) {
		EvalData.do1755 = b;
	}

}
