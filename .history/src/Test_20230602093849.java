//package evaluate_streets;

/*- **************************************************************************** 
* 
* @author PEAK_
*******************************************************************************/
public class Test implements Constants {

	static Card h0A = new Card("6s");
	static Card h1A = new Card("7s");

	static Card h0B = new Card("2d");
	static Card h1B = new Card("8c");

	static Card card1 = new Card("8s");
	static Card card2 = new Card("2s");
	static Card card3 = new Card("4c");
	static Card card4 = new Card("2d");
	static Card card5 = new Card("5s");

	static FlopAsNumbers flop;
	static int[] hmlCounts = new int[HML_FLOP_SIZE];
	static int[] boardCounts = new int[BOARD_ARRAY_SIZE];
	static int[] flop1755Counts = new int[1755];
	static int[] flopTypeOf1755Counts = new int[8];

	/*-
	 * Main 
	 */
	public static void main(String[] s0) {
		Evaluate.initialize();
		// test1(); // Static hands
		// test2(); // Showdown
		// test3(); // Run with hole cards in HandRange
		 test4(); // 1755 hands
		//  test5(); // Random hands until flop type found
		//test6(); // Random hands - a large number for testing EvaluateMany
		// test7(); // HandConversion
		//test9(); // Simulation multiple

	}

	/*-
	 * Demonstrate use of Hand, HoleCards, and Board classes
	 * Demonstrate basic interface to Evaluate
	 * Demonstrate basic reports 
	 */
	static void test1() {
		Evaluate.initialize();
		Evaluate.shuffle();
		Evaluate.setHoleCards(0, h0A, h1A); // Seat number 0
		// Evaluate.setHoleCards(1, h0B, h1B); // Seat number 1
		Evaluate.setFlopCards(card1, card2, card3);
		Evaluate.setTurnCard(card4);
		Evaluate.setRiverCard(card5);

		Evaluate.doFlop(0); // Flop

		// final var flopNum = new FlopAsNumbers(EvalData.flop1755Index);
		// Basic reports
		// ReportOneHand.reportHands(0, 100);
		// ReportOneHand.reportMadeArray(350, 100);
		// ReportOneHand.reportDrawArray(550, 100);
		// ReportOneHand.reportData(750, 100);
		// ReportOneHand.reportBoardPossibleArrayFlop(950, 100);

		// Evaluate.doTurn(0); // Turn
		// ReportOneHand.reportHands(0, 500);
		// ReportOneHand.reportHandArray(350, 500);
		// ReportOneHand.reportBoardArray(550, 500);
		// ReportOneHand.reportData(750, 500);
		// ReportOneHand.reportHandArray(350, 100);
		// ReportOneHand.reportBoardPossibleArrayTurn(1000, 100);

		// Evaluate.doRiver(0);
		// Add a second pair of Hole Cards and
		// ReportOneHand.reportMadeHandsRiver(1200, 100);
		// ReportOneHand.reportShowdown(1400, 100);
		// ReportOneHand.reportArrayWetDry(200,100);
		// ReportOneHand.reportHMLCount(200,100);
		// ReportOneHand.reportHMLArrayWetDry(200,100);
		// ReportOneHand.reportHMLCount(200,100);
		// ReportOneHand.reportArrayCounts(200,100);
		// ReportOneHand. reportlArrayCountsPos(200,100);
		// ReportOneHand. reportData(200,100);
	}

	/*-
	 * Showdown
	 */
	static void test2() {
		Evaluate.initialize();
		Evaluate.shuffle();
		Evaluate.setHoleCards(0, h0A, h1A); // Seat number 0
		// Evaluate.setHoleCards(1, h0B, h1B); // Seat number 1
		Evaluate.setFlopCards(card1, card2, card3);
		Evaluate.setTurnCard(card4);
		Evaluate.setRiverCard(card5);

		Evaluate.doFlop(0);
		// Evaluate.doTurn(0);
		// Evaluate.doRiver(0);
		// Evaluate.doShowdown();
		// ReportOneHand.reportHands(0, 100);
		// ReportOneHand.reportMadeArray(350, 100);
		// ReportOneHand.reportDrawArray(550, 100);

		// Evaluate.doRiver(0);
		// Evaluate.doShowdown();

	
	}

	/*-
	 * Run many hands with hole cards in HandRange
	 */
	static void test3() {
		Evaluate.initialize();
		for (int i = 0; i < 1000; i++) {
			Dealer.dealTable(); // Hole cards for six, Flop, Turn, River
			Evaluate.doFlop(0);
			Evaluate.doTurn(0);
			Evaluate.doAllRiverAndHandValueAndShowdown();
			Evaluate.doShowdown();
			
		}
		System.out.println("//Test3 complete ");
	}

	/*-
	 * Run many hands in simulation mode
	 */
	static void test4() {
		Evaluate.initialize();
		for (int i = 0; i < 10; i++) {
			Dealer.dealTable(); // Hole cards for six, Flop, Turn, River
			Evaluate.doFlop(0);
			Evaluate.doTurn(0);
			Evaluate.doAllRiverAndHandValueAndShowdown();
			Evaluate.doShowdown();
			
		}
	}

	/*-
	 * How often a flop type appears when run with random cards.
	 * The deck is shuffled.
	 * 3 cards are poped to create a flop.
	 * Evaluate is called to evaluate the board.
	 * Counts are updated in arrays corresponding to a Flop type like HML
	 * 
	 */
	static void test5() {
		Evaluate.initialize();
		Evaluate.shuffle();
		
		for (int i = 0; i < 100000; ++i) {
			Deck.clearDeadCards();

			Dealer.dealAllHoleCards();
			Dealer.dealFlop();
			Evaluate.doFlop(0); // Flop
			for (int j = 0; j < BOARD_ARRAY_SIZE; ++j) {
				if (EvalData.boardArray[j]) {
					++boardCounts[j];
				}
			}
			++hmlCounts[EvalData.hmlIndex];
			++flop1755Counts[EvalData.flop1755Index];
			++flopTypeOf1755Counts[EvalData.flopTypeOf1755];
		}
		
		
	}

	/*-
	 * Deal random hands until the flop type that we are looking for is found then
	 * display analysis data.
	 * Used for testing code as well an demonstration.
	 */
	static void test6() {
		Evaluate.initialize();
		boolean done = false;
		boolean hit = false;
		int count = 0;
		final int limit = 10000;

		while (!done) {
			Deck.shuffle();
			Dealer.dealHoleCardsAndFlop();
			++count;
			Evaluate.doFlop(0); // Flop
			if (EvalData.madeArray[MADE_PAIR]) {
				done = true;
				hit = true;
				System.out.println("hit " + EvalData.madeArrayMax);
			} else {
				++count;
				if (++count == limit) {
					done = true;
				}
			}
		}
		if (!hit) {
			Logger.log("//ERROR flop type not found  ");
		} else {
			Logger.log(new StringBuilder().append(EvalData.holeCards[0].toString()).append(" ")
					.append(EvalData.board.toString()).append("  ").append(count).toString());
			ReportOneHand.reportHands(0, 100);
			ReportOneHand.reportMadeArray(260, 100);
			ReportOneHand.reportDrawArray(460, 100);
			ReportOneHand.reportBoardArray(650, 100);
			ReportOneHand.reportData(850, 100);
			ReportOneHand.reportBoardPossibleArrayFlop(1050, 100);

			// Create Turn and evaluate
			Evaluate.setTurnCard(card4);
			Logger.log(new StringBuilder().append(STREET_ST[EvalData.street]).append(" ")
					.append(EvalData.holeCards[0].toString()).append(" ").append(EvalData.board.toString()).append("  ")
					.append(count).toString());
			Evaluate.doTurn(0); // Turn

			ReportOneHand.reportHands(0, 500);
			ReportOneHand.reportMadeArray(260, 500);
			ReportOneHand.reportDrawArray(460, 500);
			ReportOneHand.reportBoardArray(650, 500);
			ReportOneHand.reportData(850, 500);
			ReportOneHand.reportBoardPossibleArrayTurn(1050, 500);

			// Create River and evaluate
			Evaluate.setRiverCard(card5);
			Logger.log(new StringBuilder().append(STREET_ST[EvalData.street]).append(" ")
					.append(EvalData.holeCards[0].toString()).append(" ").append(EvalData.board.toString()).append("  ")
					.append(count).toString());

			// Add a second pair of Hole Cards and
			Evaluate.setHoleCards(1, h0B, h1B); // Seat number 0
			Evaluate.doRiver(0);
			Evaluate.doShowdown();
			ReportOneHand.reportShowdown(1060, 500);

			// ReportOneHand.reportEvalArray(460, 500);
			// ReportOneHand.reportMadeHandsRiver(660, 500);

		}
	}

	/*-
	 * Deal random hands all streets and showdown fornumHands number of hands 
	 * and then do calculations in EvaluateMany.
	 * Report results.
	 * 
	 * Used for testing code as well an demonstration.
	 */
	static void test7() {
		Evaluate.initialize();
		final int numHands = 10;

		for (int i = 0; i < numHands; i++) {
			Deck.shuffle();
			Dealer.dealHoleCardsAndFlop();
			Evaluate.doFlop(0); // Flop
			Evaluate.doTurn(0); // Turn
			Evaluate.doRiver(0); // River
			Evaluate.doShowdown();
		}
		EvaluateMany.calculate();
		ReportOneHand.reportBoardArray(100, 100);
		ReportManyHands.reportBoardArrayFlopPer(300, 100);
	}

	
	/*-
	 * Simulate multiple preflop hands
	 */
	static void test9() {
		Evaluate.initialize();
		Dealer.initialize();
		GameControl.preflopSimulationMUltipleHands(10);

	}

}
