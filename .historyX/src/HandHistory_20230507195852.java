//package evaluate_streets;

/* - This class creates hand histories */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/*- Creates Hand Histories - in Poker Stars format. */
class HandHistory implements Constants {
	/*-  ******************************************************************************
	 * This class creates Hand History files in the same format as PokerStars with 
	 * minor modifications to make it not an exact copy.
	 * Copied from the game project and edited for this application but
	 * with lots of modifications.
	 * 
	 * Public methods:
	 * 		Initialize()
	 * 		createHH()
	 * 		closeHH()
	 * 
	 * All pf the data used to create aHand History file is in EvalData.
	 * The data shown below is collected specifically and only for Hand History.
	 * There is minimal impact on performance because it is only collected if we 
	 * are doing a full simulation.
	 * Data collecteed in normal operation:
	 * 
	* Card holeCards
	* bothIndexs 
	* playerNames
	* potBD
	* stackBD
	* playerMainPotBD
	* stackPreflopBD 
	
	* Data collected in simulation only
	* lastOrbit
	* isFold ; // Indexed by street,seat,orbit TODO
	* isLimp 
	* isCheck
	* isCall
	* isBet 
	* isRaise 
	* isAllin 
	* raisedToBD 
	* returnedToBD
	* betBD 
	* callBD 
	* winBD 
	* winnerCollectedBD 
	* returnedToShowdownBD 
	* playerSidePotSplitTotalBD 
	* playerSidePotSplitBD 
	* potStart$
	* potEnd$
	* bet$
	* pot$
	* moneyIn$
	* potStart$
	* potEnd$
	* bet$
	* pot$
	* moneyIn$
	* call$
	* allin$
	* returnedTo$
	* raisedTo$
	* calledTo$
	* win$
	* returnedToShowdown$ 
	
	 * 
	 *  @author PEAK_
	 *   ****************************************************************************** */

	/*- Complete hand history ready to write - each element is a line. */
	private static final String[] reportSt = new String[150];
	private static int lines;
	private static String hhDate;
	// Player names and positions - Index is seat number
	private static final String[] nameStringArray = { "", "", "", "", "", "" };
	private static String dateSt = "";
	private static PrintWriter pwFile1;
	/*- The HH to generate. */
	private static int files;
	static boolean writeEnabled = true; // Used to selectively write specific hand histories

	// Constructor
	HandHistory() {
	}

	/*-  ******************************************************************************
	 *  Initialize. 
	 *  Called one time to create a new Hand History file.
	 *   ****************************************************************************** */
	static void initialize() {
		final var file = new File(EvalData.applicationDirectory +
				"\\HandHistory");
		if (!file.exists()) {
			file.mkdir();
		}
		eraseHHFiles();
		newHHFile();
	}

	/*-  ******************************************************************************
	 *  Erase HH files
	 *   ****************************************************************************** */
	static void eraseHHFiles() {
		String directoryPath = EvalData.applicationDirectory + "\\HandHistory";
		List<Path> files = FileUtils.listFiles(directoryPath);

		if (files != null) {
			for (Path file : files) {
				System.out.println("Deleting: " + file);
				try {
					Files.delete(file);
				} catch (IOException e) {
					System.err.println("Error while deleting file: " + file + " - " + e.getMessage());
				}
			}
		} else {
			System.err.println("Error occurred while listing files in the directory.");
		}

	}

	/*-  ******************************************************************************
	 * Create a Hand History and add to file 
	 *   ****************************************************************************** */
	static void createHH() {
		System.out.println("//createHH() ");
		for (int i = 0; i < PLAYERS; ++i) {
			nameStringArray[i] = EvalData.playerNames[i];
		}
		for (int i = 0; i < reportSt.length; ++i) {
			reportSt[i] = "";
		}
		lines = 0;
		addHeader();
		doStreetHH(PREFLOP);
		if (EvalData.lastStreet >= FLOP) {
			flopHH();
			doStreetHH(FLOP);
		}
		if (EvalData.lastStreet >= TURN) {
			turnHH();
			doStreetHH(TURN);
		}
		if (EvalData.lastStreet >= RIVER) {
			riverHH();
			doStreetHH(RIVER);
		}
		if (EvalData.showdown) {
			showdownHH();
		}
		summaryHH();
		writeHH();
	}

	/*-  ******************************************************************************
	 * Close hand history file.
	 *   ****************************************************************************** */
	static void closeHH() {
		if (pwFile1 == null) {
			return;
		}
		pwFile1.flush();
		pwFile1.close();
	}

	/*-  ******************************************************************************
	 * Close hand history file.
	 * ****************************************************************************** */
	private static void newHHFile() {
		var fileN = "";
		var num = "";
		if (EvalData.simulation) {
			fileN = new StringBuilder().append(EvalData.applicationDirectory).append("\\HandHistory\\HHSimulation")
					.append(num).append(".txt").toString();
		} else {
			final var dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			hhDate = dateFormat.format(new Date());
			dateSt = new StringBuilder().append(hhDate.substring(11, 13)).append(hhDate.substring(14, 16))
					.append(hhDate.substring(17, 19)).toString();

			num = files > 9 ? Integer.toString(files) : "0" + Integer.toString(files);

			fileN = new StringBuilder().append(EvalData.applicationDirectory).append("\\HandHistory\\HH")
					.append(hhDate.substring(5, 7))
					.append(hhDate.substring(8, 10)).append(hhDate.substring(11, 13)).append(hhDate.substring(14, 16))
					.append(num).append(".txt").toString();
			++files;
		}

		try {
			pwFile1 = new PrintWriter(new BufferedWriter(new FileWriter(fileN, true)));
		} catch (SecurityException | IOException i) {
			i.printStackTrace();
		}
	}

	/*-  ******************************************************************************
	  * Add header information
	 * 		PokerStars
	 * 		Players
	 * 		Blinds
	 * 		Hole Cards
	 * - PokerStars Hand #178803089146: Hold'em No Limit ($1/$2 USD) - 2017/11/27
	 * 16:31:46 ET
	 *   ****************************************************************************** */
	private static void addHeader() {
		final var pattern = "########";
		var num = new DecimalFormat(pattern).format(EvalData.handsPlayed);
		final var dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		hhDate = dateFormat.format(new Date());
		final int n = num.length();
		for (int i = n; i < 6; ++i) {
			num = "0" + num;
		}
		num = new StringBuilder().append("#").append(dateSt).append(num).toString();

		reportSt[lines++] = new StringBuilder().append("PokerStars Hand ").append(num)
				.append(":  Hold'em No Limit ($1/$2 USD) - ").append(hhDate)
				.append(" ET  - Generated by PeakHoldemEvaluation NOT by PokerStars\r\n").toString();

		reportSt[lines++] = new StringBuilder().append("Table 'PeakHoldem' 6-max Seat #")
				.append(Integer.toString(EvalData.playerPositions[BU] + 1)).append(" is the button\r\n").toString();

		for (int i = 0; i < PLAYERS; ++i) {
			reportSt[lines++] = new StringBuilder().append("Seat ").append(i + 1).append(": ")
					.append(nameStringArray[i]).append(" ($").append(Format.format(EvalData.stackPreflopBD[0]))
					.append(" in chips)\r\n").toString();
		}

		reportSt[lines++] = new StringBuilder().append(nameStringArray[EvalData.playerPositions[SB]])
				.append(": posts small blind $").append(Format.format(EvalData.SBBetBD)).append("\r\n").toString();
		reportSt[lines++] = new StringBuilder().append(nameStringArray[EvalData.playerPositions[BB]])
				.append(": posts big blind $").append(Format.format(EvalData.BBBetBD)).append("\r\n").toString();

		reportSt[lines++] = "*** HOLE CARDS ***\r\n";
	}

	/*-  ******************************************************************************
	 * Flop.
	 *  *** FLOP *** [7c Kc Ah] AverageX1: checks AverageX2: checks 
	* TODO PokerStarsuses this format when there are only 2 players and they are
	*  all in
	*  TODO Need to update Hand History for this format
	 * *** FIRST FLOP *** [5s 5c 6s]
	 * *** SECOND FLOP *** [9c 4h 7d]
	 *   ****************************************************************************** */
	static void flopHH() {
		if (EvalData.lastStreet < 1) {
			return;
		}
		reportSt[lines] = new StringBuilder().append("*** FLOP *** [").append(EvalData.board[0].toString()).append(" ")
				.append(EvalData.board[1].toString()).append(" ").append(EvalData.board[2].toString()).append("]")
				.toString();
		reportSt[lines++] += "\r\n";
	}

	/*-  ******************************************************************************
	 * - Add turn to Hand History
	 * TODO PokerStars uses this format when there are only 2 players and they are
	* all in
	*  TODO Need to update Hand History for this format
	*  *** FIRST TURN *** [5s 5c 6s] [8h]
	*n  *** SECOND TURN *** [9c 4h 7d] [6c]
	 *   ****************************************************************************** */
	static void turnHH() {
		if (EvalData.lastStreet < 2) {
			return;
		}
		reportSt[lines] = new StringBuilder().append("*** TURN *** [").append(EvalData.board[0].toString()).append(" ")
				.append(EvalData.board[1].toString()).append(" ").append(EvalData.board[2].toString()).append("] [")
				.append(EvalData.board[3]).append("]").toString(); // TODO

		reportSt[lines++] += "\r\n";
	}

	/*-  ******************************************************************************
	 * Add River to HH
	*  TODO PokerStarsuses this format when there are only 2 players and they are
	* all in
	*  TODO Need to update Hand History for this format
	*  *** FIRST RIVER *** [5s 5c 6s 8h] [Ah]
	* *** SECOND RIVER *** [9c 4h 7d 6c] [7c]
	 *   ****************************************************************************** */
	static void riverHH() {
		if (EvalData.lastStreet != 3) {
			return;
		}
		reportSt[lines] = new StringBuilder().append("*** RIVER *** [").append(EvalData.board[0].toString())
				.append(EvalData.board[1].toString()).append(" ").append(EvalData.board[2].toString()).append(" ")
				.append(EvalData.board[3].toString()).append("] [").append(EvalData.board[4].toString()).append("]")
				.toString();
		reportSt[lines++] += "\r\n";
	}

	/*-  ******************************************************************************
	 * Preflop Hand History Format
	 * Fills in player actions
	 * 
	 * If the call was from Logger then we add a few things not in normal Hand History
	 * 
	 *   ****************************************************************************** */
	private static void doStreetHH(int street) {
		int seat = 0;
		int seatCount = PLAYERS;
		var st = "";

		for (int orbit = 0; orbit <= EvalData.lastOrbit[street]; ++orbit) {
			seat = EvalData.playerPositions[SB];
			seatCount = PLAYERS;

			if (street == PREFLOP && orbit == 0) {
				seat = EvalData.playerPositions[UTG];
				seatCount = PLAYERS - 2;
			}

			for (int j = 0; j < seatCount; ++j) {
				if (seat >= PLAYERS) {
					seat = 0;
				}
				System.out.println(
						"*** isFold " + " seat " + seat + "  orbit " + orbit + " " + EvalData.playerNames[seat]
								+ " lastorbit "
								+ EvalData.lastOrbit[street]
								+ " " + EvalData.isFold[street][seat][orbit]);
				System.out.println(
						"*** isRaise " + " seat " + seat + "  orbit " + orbit + " " + EvalData.playerNames[seat]
								+ " lasorbit "
								+ EvalData.lastOrbit[street]
								+ " " + EvalData.isRaise[street][seat][orbit]);

				if (EvalData.isFold[street][seat][orbit]) {
					reportSt[lines] = nameStringArray[seat] + ": folds";
					System.out.println("HHH isFold " + reportSt[lines]);
					reportSt[lines++] += "\r\n";
					++seat;
					continue;
				}

				if (EvalData.isRaise[street][seat][orbit] || EvalData.isBet[street][seat][orbit]) {
					st = "";
					if (EvalData.isAllin[street][seat][orbit]) {
						st = " and is all-in";
					}
					final var betBD = EvalData.betBD[street][seat][orbit];
					if (EvalData.isBet[street][seat][orbit]) {
						reportSt[lines] = new StringBuilder().append(nameStringArray[seat]).append(": bets $")
								.append(Format.format(betBD)).append(st).toString();
					} else {
						reportSt[lines] = new StringBuilder().append(nameStringArray[seat]).append(": raises $")
								.append(Format.format(betBD)).append(" to $")
								.append(Format.format(EvalData.raisedToBD[street][seat][orbit])).append(st).toString();
					}
					System.out.println("HHH isRaise " + reportSt[lines]);
					reportSt[lines++] += "\r\n";
					++seat;
					continue;
				}

				if (EvalData.isCall[street][seat][orbit]) {
					st = "";
					if (EvalData.isAllin[street][seat][orbit]) {
						st = " and is all-in";
					}
					if (EvalData.handsPlayed == 4) {
						Logger.logError("HH call$ " + EvalData.callBD[street][seat][orbit]);
					}
					reportSt[lines] = new StringBuilder().append(nameStringArray[seat]).append(": calls $")
							.append(Format.format(EvalData.callBD[street][seat][orbit])).append(st).toString();
					System.out.println("HHH isCall " + reportSt[lines]);
					reportSt[lines++] += "\r\n";
					++seat;
					continue;
				}

				if (EvalData.isCheck[street][seat][orbit]) {
					reportSt[lines] = nameStringArray[seat] + ": checks ";
					reportSt[lines++] += "\r\n";
					++seat;
					continue;
				}
				++seat;
			}
		}

		// Check for bet returned or money collected
		for (int orbit = 0; orbit < EvalData.lastOrbit[street]; ++orbit) {
			seat = EvalData.playerPositions[SB];
			seatCount = PLAYERS;
			if (street == PREFLOP && orbit == 0) {
				seat = EvalData.playerPositions[UTG];
				seatCount = PLAYERS - 2;
			}

			for (int j = 0; j < seatCount; ++j) {
				if (seat >= PLAYERS) {
					seat = 0;
				}

				if (EvalData.returnedToBD[street][seat][orbit].compareTo(zeroBD) != 0) {
					reportSt[lines] = new StringBuilder().append("Uncalled bet ($")
							.append(Format.format(EvalData.returnedToBD[street][seat][orbit])).append(") returned to ")
							.append(nameStringArray[seat]).toString();
					reportSt[lines++] += "\r\n";
				}

				if (EvalData.winBD[street][seat][orbit].compareTo(zeroBD) != 0) {
					reportSt[lines] = new StringBuilder().append(nameStringArray[seat]).append(" collected $")
							.append(Format.format(EvalData.winnerCollectedBD[seat])).append(" from pot").toString();
					reportSt[lines++] += "\r\n";
					reportSt[lines] = nameStringArray[seat] + ": doesn't show hand";
					reportSt[lines++] += "\r\n";
				}
				// Simulation so non standard Hand History
				if (!EvalData.playerFolded[seat] && EvalData.simulation && EvalData.foldCount < MAXFOLDED) {
					reportSt[lines] = new StringBuilder().append(nameStringArray[seat]).append(" completed preflop ")
							.toString();
					reportSt[lines++] += "\r\n";
				}
				++seat;
			}
		}
	}

	/*-  ******************************************************************************
	 * Show Down
	 * ***************************************************************************** */
	private static void showdownHH() {
		reportSt[lines++] = "*** SHOW DOWN ***\r\n";

		for (int i = 0; i < PLAYERS; ++i) {
			if (!EvalData.playerFolded[i] && EvalData.returnedToShowdownBD[i].compareTo(zeroBD) != 0
					&& EvalData.playerSidePotSplitTotalBD[i].compareTo(zeroBD) != 0) {
				Crash.log("$$$");
			} else if (EvalData.returnedToShowdownBD[i].compareTo(zeroBD) != 0) {
				reportSt[lines] += new StringBuilder().append("Uncalled bet ($")
						.append(Format.format(EvalData.returnedToShowdownBD[i])).append(" returned to ")
						.append(EvalData.playerNames[i]).toString();
				reportSt[lines++] += "\r\n";
			}
		}

		int winners = 0;
		for (int i = 0; i < PLAYERS; ++i) {
			if (EvalData.playerWon[i]) {
				winners++;
			}
		}

		// One winner, the simple case
		if (winners == 1) {
			boolean show = false;
			for (int i = 0; i < PLAYERS; ++i) {
				if (!EvalData.playerFolded[i] && EvalData.winnerCollectedBD[i].compareTo(zeroBD) != 0) {
					reportSt[lines] = new StringBuilder().append(nameStringArray[i]).append(" collected $")
							.append(Format.format(EvalData.winnerCollectedBD[i])).append(" from pot ").toString();
					reportSt[lines++] += "\r\n";
					show = true;
				}
				if (EvalData.playerSidePotSplitTotalBD[i].compareTo(zeroBD) != 0) {
					reportSt[lines] = new StringBuilder().append(nameStringArray[i]).append(" collected $")
							.append(Format.format(EvalData.playerSidePotSplitTotalBD[i])).append(" from Side pot ")
							.toString();
					reportSt[lines++] += "\r\n";
					show = true;
				}
				if (show) {
					reportSt[lines++] = new StringBuilder().append(nameStringArray[i]).append(": shows [")
							.append(EvalData.holeCards[i][0]).append(" ").append(EvalData.holeCards[i][1])
							.append("]\r\n").toString();
					show = false;
				}
			}
		}

		else {
			boolean show = false;
			for (int i = 0; i < PLAYERS; ++i) {
				if (!EvalData.playerFolded[i] && EvalData.winnerCollectedBD[i].compareTo(zeroBD) != 0) {
					reportSt[lines] = new StringBuilder().append(nameStringArray[i]).append(" collected $")
							.append(Format.format(EvalData.winnerCollectedBD[i])).append(" from Main pot ").toString();
					reportSt[lines++] += "\r\n";
					show = true;
				}
				if (EvalData.playerSidePotSplitTotalBD[i].compareTo(zeroBD) != 0) {
					reportSt[lines] = new StringBuilder().append(nameStringArray[i]).append(" collected $")
							.append(Format.format(EvalData.playerSidePotSplitTotalBD[i])).append(" from Side pot ")
							.toString();
					reportSt[lines++] += "\r\n";
					show = true;
				}
				if (show) {
					reportSt[lines++] = new StringBuilder().append(nameStringArray[i]).append(": shows [")
							.append(EvalData.holeCards[i][0]).append(" ").append(EvalData.holeCards[i][1])
							.append("]\r\n").toString();
					show = false;
				}
			}
		}
	}

	/*-  ******************************************************************************
	 * - Summary 
	 * Total pot $874.85 Main pot $654.18. Side pot $217.92. | Rake $2.75
	 *
	* TODO PokerStars uses this format when there are only 2 players and they are
	* all in
	* TODO Need to update Hand History for this format
	* Total pot $548.30 | Rake $2.75
	* Hand was run twice
	* FIRST Board [5s 5c 6s 8h Ah]
	* SECOND Board [9c 4h 7d 6c 7c]
	 *   ****************************************************************************** */
	static void summaryHH() {
		final String st;
		var sidePotBD = zeroBD;
		var mainPotBD = zeroBD;

		++lines;
		reportSt[lines++] = "*** SUMMARY *** \r\n";
		for (int i = 0; i < PLAYERS; ++i) {
			if (EvalData.winnerCollectedBD[i] != null) {
				if (EvalData.winnerCollectedBD[i].compareTo(zeroBD) != 0) {
					sidePotBD = sidePotBD.add(EvalData.playerSidePotSplitTotalBD[i]);
				}
			}
			System.out.println("www " + EvalData.playerWon[i]);
		}

		st = " | Rake $0.0\r\n";

		if (sidePotBD != null) {
			if (sidePotBD.compareTo(zeroBD) == 0) {
				reportSt[lines++] = new StringBuilder().append("Total pot $").append(Format.format(EvalData.potBD))
						.append(st).toString();
			} else {
				mainPotBD = EvalData.potBD.subtract(sidePotBD);
				reportSt[lines++] = new StringBuilder().append("Total pot $").append(Format.format(EvalData.potBD))
						.append(" Main pot $").append(Format.format(mainPotBD)).append(". Side pot $")
						.append(Format.format(sidePotBD)).append(st).toString();
			}
		}
		if (EvalData.streetNumber == FLOP) {
			reportSt[lines++] = new StringBuilder().append("Board [").append(EvalData.board[0]).append(" ")
					.append(EvalData.board[1]).append(" ").append(EvalData.board[2]).append("]\r\n").toString();
		}
		if (EvalData.streetNumber == TURN) {
			reportSt[lines++] = new StringBuilder().append("Board [").append(EvalData.board[0]).append(" ")
					.append(EvalData.board[1]).append(" ").append(EvalData.board[2]).append(" ")
					.append(EvalData.board[3]).append("]\r\n").toString();
		}
		if (EvalData.streetNumber == RIVER) {
			reportSt[lines++] = new StringBuilder().append("Board [").append(EvalData.board[0]).append(" ")
					.append(EvalData.board[1]).append(" ").append(EvalData.board[2]).append(" ")
					.append(EvalData.board[3]).append(" ").append(EvalData.board[4]).append("]\r\n").toString();
		}

		// Step through each position
		for (int j = 0; j < PLAYERS; ++j) {

			reportSt[lines] = new StringBuilder().append("Seat ").append(j + 1).append(": ").append(nameStringArray[j])
					.toString();
			if (EvalData.playerPositions[SB] == j) {
				reportSt[lines] += " (small blind)";
			}
			if (EvalData.playerPositions[BB] == j) {
				reportSt[lines] += " (big blind)";
			}
			if (EvalData.playerPositions[BU] == j) {
				reportSt[lines] += " (button)";
			}

			if (EvalData.playerLostShowdown[j]) {
				reportSt[lines] += new StringBuilder().append(" showed [").append(EvalData.holeCards[j][0]).append(" ")
						.append(EvalData.holeCards[j][1]).append("] and lost with a smile ").toString();
				reportSt[lines++] += "\r\n";
				continue;
			}

			if (EvalData.playerWon[j]) {
				System.out.println("WWW2 " + j);
				reportSt[lines] += new StringBuilder().append(" showed [").append(EvalData.holeCards[j][0]).append(" ")
						.append(EvalData.holeCards[j][1]).append("] and won ($")
						.append(Format.format(EvalData.winnerCollectedBD[j].add(EvalData.playerSidePotSplitTotalBD[j])))
						.append(")").toString();
				reportSt[lines++] += "\r\n";
				continue;
			}

			if (EvalData.playerFoldedPreflop[j]) {
				reportSt[lines] += " folded before the flop";
				if (EvalData.moneyInBD[j].compareTo(zeroBD) == 0) {
					reportSt[lines] += " (didn't bet)";
				}
				reportSt[lines++] += "\r\n";
				continue;
			}
			if (EvalData.playerFoldedFlop[j]) {
				reportSt[lines] += " folded on the flop";
				reportSt[lines++] += "\r\n";
				continue;
			}
			if (EvalData.playerFoldedTurn[j]) {
				reportSt[lines] += " folded on the turn";
				reportSt[lines++] += "\r\n";
				continue;
			}
			if (EvalData.playerFoldedRiver[j]) {
				reportSt[lines] += " folded on the river";
				reportSt[lines++] += "\r\n";
			}
			// Simulation mode so non standaqrd Hand History
			reportSt[lines] += " completed hand without Showdown ";
			reportSt[lines++] += "\r\n";
		}
	}

	/*- Add to text file. */
	private static void writeHH() {
		if (!writeEnabled) {
			return;
		}

		for (int i = 0; i < lines; ++i) {
			pwFile1.append(reportSt[i]);
		}

		pwFile1.append("\r\n\r\n\r\n\r\n");
		pwFile1.flush();
	}

	/*-
	 * This was done in the Logger class first, because there was a need to create
	 * a HandHistory as part of the Logger saved data and the displayed data.
	 * When there is a problem that vauses Logger to be called, it is too late to build
	 * a HandHiastory the old way, by calling several methods in the HandHistory class 
	 * as a hand progressed. Now there are only 2 methods called: 
	 * 		HandHistory.initialize()
	 * 		HandHistory.createHH()
	* 		HandHistory.closeHH()
	 * 
	 * The Logger Class is able to call a special method, unique to Logger, to have a Hand History
	 * created for it's use alone. There isno interfearance with the normal Hand History files
	 * being created. ( If the user has selected the HH option.
	 * 
	 * 
	  
	TODO PokerStars uses this format when there are only 2 players and they are 	 all in
	TODO Need to update Hand History for this format
	PokerStars Hand #228721632458:  Hold'em No Limit ($1/$2 USD) - 2021/07/31 22:25:58 ET
	Table 'Aase' 6-max Seat #2 is the button
	Seat 1: Satan'sBingo ($273.15 in chips) 
	Seat 2: mike21_4life ($256.47 in chips) 
	Seat 4: nicofellow ($313.34 in chips) 
	Seat 5: EBE_77 ($210.68 in chips) 
	Seat 6: vest78 ($200 in chips) 
	nicofellow: posts small blind $1
	EBE_77: posts big blind $2
	 *** HOLE CARDS ***
	vest78: folds 
	Satan'sBingo: raises $4 to $6
	mike21_4life: folds 
	nicofellow: raises $18 to $24
	EBE_77: folds 
	Satan'sBingo: raises $36 to $60
	nicofellow: raises $253.34 to $313.34 and is all-in
	atan'sBingo: calls $213.15 and is all-in
	Uncalled bet ($40.19) returned to nicofellow
	 *** FIRST FLOP *** [5s 5c 6s]
	 *** FIRST TURN *** [5s 5c 6s] [8h]
	 *** FIRST RIVER *** [5s 5c 6s 8h] [Ah]
	 *** SECOND FLOP *** [9c 4h 7d]
	 *** SECOND TURN *** [9c 4h 7d] [6c]
	 *** SECOND RIVER *** [9c 4h 7d 6c] [7c]
	 *** FIRST SHOW DOWN ***
	nicofellow: shows [Kh As] (two pair, Aces and Fives)
	Satan'sBingo: shows [Kd Ac] (two pair, Aces and Fives)
	nicofellow collected $136.39 from pot
	Satan'sBingo collected $136.39 from pot
	 *** SECOND SHOW DOWN ***
	nicofellow: shows [Kh As] (a pair of Sevens)
	Satan'sBingo: shows [Kd Ac] (a pair of Sevens)
	nicofellow collected $136.39 from pot
	Satan'sBingo collected $136.38 from pot
	 *** SUMMARY ***
	Total pot $548.30 | Rake $2.75 
	Hand was run twice
	FIRST Board [5s 5c 6s 8h Ah]
	SECOND Board [9c 4h 7d 6c 7c]
	Seat 1: Satan'sBingo showed [Kd Ac] and won ($136.39) with two pair, Aces and Fives, and won ($136.38) with a pair of SevensSeat 2: mike21_4life (button) folded before Flop (didn't bet)
	Seat 4: nicofellow (small blind) showed [Kh As] and won ($136.39) with two pair, Aces and Fives, and won ($136.39) with a pair of Sevens
	Seat 5: EBE_77 (big blind) folded before Flop
	Seat 6: vest78 folded before Flop (didn't bet)
	
	PokerStars Hand #PokerStars Hand #PokerStars Hand #229597511637:  Hold'em No Limit ($1/$2 USD) - 2021/09/04 19:48:32 ET
	Table 'Agenor' 6-max Seat #5 is the button
	Seat 1: energyvadym ($209.60 in chips) 
	Seat 2: YourScream ($248.09 in chips) 
	Seat 3: kbone008 ($114.75 in chips) 
	Seat 4: Dfy_88 ($37.79 in chips) 
	Seat 5: AMMADNAV ($250.44 in chips) 
	Seat 6: nicofellow ($210.22 in chips) 
	nicofellow: posts small blind $1
	energyvadym: posts big blind $2
	*** HOLE CARDS ***
	YourScream: folds 
	kbone008: calls $2
	Dfy_88: raises $6 to $8
	AMMADNAV: folds 
	nicofellow: folds 
	energyvadym: raises $16 to $24
	kbone008: folds 
	Dfy_88: raises $13.79 to $37.79 and is all-in
	energyvadym: calls $13.79
	
	4 folds, 2 players at end of preflop folded 2 3 5 6
	seat 1 BB   	raises 	bet 2
	seat 4 BU 	raises	bet 3
	seat 1 BB		raises 	bet 4
	seat 4 BU		raises all-in
	seat 1 BB		calls
	
	WHY play 2 hands, just 2 players   ?????
	
	*** FIRST FLOP *** [4h 9s 9c]
	*** FIRST TURN *** [4h 9s 9c] [Kd]
	*** FIRST RIVER *** [4h 9s 9c Kd] [6s]
	*** SECOND FLOP *** [7h Ac 6d]
	*** SECOND TURN *** [7h Ac 6d] [Th]
	*** SECOND RIVER *** [7h Ac 6d Th] [Tc]
	*** FIRST SHOW DOWN ***
	energyvadym: shows [Qh Ad] (a pair of Nines)
	Dfy_88: shows [3h 3c] (two pair, Nines and Threes)
	Dfy_88 collected $37.92 from pot
	*** SECOND SHOW DOWN ***
	energyvadym: shows [Qh Ad] (two pair, Aces and Tens)
	Dfy_88: shows [3h 3c] (two pair, Tens and Threes)
	energyvadym collected $37.91 from pot
	*** SUMMARY ***
	Total pot $78.58 | Rake $2.75 
	Hand was run twice
	FIRST Board [4h 9s 9c Kd 6s]
	SECOND Board [7h Ac 6d Th Tc]
	Seat 1: energyvadym (big blind) showed [Qh Ad] and lost with a pair of Nines, and won ($37.91) with two pair, Aces and Tens
	Seat 2: YourScream folded before Flop (didn't bet)
	Seat 3: kbone008 folded before Flop
	Seat 4: Dfy_88 showed [3h 3c] and won ($37.92) with two pair, Nines and Threes, and lost with two pair, Tens and Threes
	Seat 5: AMMADNAV (button) folded before Flop (didn't bet)
	Seat 6: nicofellow (small blind) folded before Flop
	
	PokerStars Hand #PokerStars Hand #PokerStars Hand #229586475617:  Hold'em No Limit ($1/$2 USD) - 2021/09/04 13:15:48 ET
	Table 'Agenor' 6-max Seat #6 is the button
	Seat 1: AkUnAMaTaTa24 ($343.47 in chips) 
	Seat 2: Juan-Cruz-Talin ($200 in chips) 
	Seat 3: Basmatillo ($633.05 in chips) 
	Seat 4: thegamble626 ($160.48 in chips) 
	Seat 5: ppalomin ($205 in chips) 
	Seat 6: Dimastiy X ($210.86 in chips) 
	AkUnAMaTaTa24: posts small blind $1
	Juan-Cruz-Talin: posts big blind $2
	*** HOLE CARDS ***
	Basmatillo: folds 
	thegamble626: raises $4 to $6
	ppalomin: raises $15 to $21
	Dimastiy X: folds 
	AkUnAMaTaTa24: folds 
	Juan-Cruz-Talin: raises $25 to $46
	thegamble626: folds 
	ppalomin: calls $25
	*** FLOP *** [3d Th 2d]
	Juan-Cruz-Talin: bets $48
	ppalomin: calls $48
	*** TURN *** [3d Th 2d] [7h]
	Juan-Cruz-Talin: bets $106 and is all-in
	ppalomin: calls $106
	
	WHY ?? there are only 2 players
	
	
	*** FIRST RIVER *** [3d Th 2d 7h] [9h]
	*** SECOND RIVER *** [3d Th 2d 7h] [7d]
	*** FIRST SHOW DOWN ***
	Juan-Cruz-Talin: shows [Qh Qs] (a pair of Queens)
	ppalomin: shows [Ac 2c] (a pair of Deuces)
	Juan-Cruz-Talin collected $202.13 from pot
	*** SECOND SHOW DOWN ***
	Juan-Cruz-Talin: shows [Qh Qs] (two pair, Queens and Sevens)
	ppalomin: shows [Ac 2c] (two pair, Sevens and Deuces)
	Juan-Cruz-Talin collected $202.12 from pot
	*** SUMMARY ***
	Total pot $407 | Rake $2.75 
	Hand was run twice
	FIRST Board [3d Th 2d 7h 9h]
	SECOND Board [3d Th 2d 7h 7d]
	Seat 1: AkUnAMaTaTa24 (small blind) folded before Flop
	Seat 2: Juan-Cruz-Talin (big blind) showed [Qh Qs] and won ($202.13) with a pair of Queens, and won ($202.12) with two pair, Queens and Sevens
	Seat 3: Basmatillo folded before Flop (didn't bet)
	Seat 4: thegamble626 folded before Flop
	Seat 5: ppalomin showed [Ac 2c] and lost with a pair of Deuces, and lost with two pair, Sevens and Deuces
	Seat 6: Dimastiy X (button) folded before Flop (didn't bet)
	
	
	//PokerStars Hand #PokerStars Hand #PokerStars Hand #228625418673:  Hold'em No Limit ($1/$2 USD) - 2021/07/28 1:34:35 ET
	//Table 'Aaryn' 6-max Seat #1 is the button
	//Seat 1: alesikStar ($200 in chips) 
	//Seat 2: MatoStar14 ($207.55 in chips) 
	//Seat 3: ludatil ($108.78 in chips) 
	//Seat 4: Belqi ($310.44 in chips) 
	//Seat 5: A.E.M.07 ($276.04 in chips) 
	//Seat 6: Rietzel ($94.43 in chips) 
	//MatoStar14: posts small blind $1
	//ludatil: posts big blind $2
	//*-* HOLE CARDS ***
	//Belqi: raises $3.04 to $5.04
	//A.E.M.07: folds 
	//Rietzel: raises $13.08 to $18.12
	//alesikStar: folds 
	//MatoStar14: folds 
	//ludatil: calls $16.12
	//Belqi: raises $292.32 to $310.44 and is all-in
	//Rietzel: calls $76.31 and is all-in
	//ludatil: calls $90.66 and is all-in
	//Uncalled bet ($201.66) returned to Belqi
	//*-* FLOP *** [9d 3h 5s]
	//*-* TURN *** [9d 3h 5s] [Kd]
	//*-* RIVER *** [9d 3h 5s Kd] [6h]
	//*-* SHOW DOWN ***
	//ludatil: shows [6c 5c] (two pair, Sixes and Fives)
	//Belqi: shows [Td Ts] (a pair of Tens)
	//ludatil collected $28.70 from side pot
	//Rietzel: shows [Ks Ah] (a pair of Kings)
	//ludatil collected $281.54 from main pot
	//*-* SUMMARY ***
	//Total pot $312.99 Main pot $281.54. Side pot $28.70. | Rake $2.75 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	//Board [9d 3h 5s Kd 6h]
	//Seat 1: alesikStar (button) folded before Flop (didn't bet)
	//Seat 2: MatoStar14 (small blind) folded before Flop
	//Seat 3: ludatil (big blind) showed [6c 5c] and won ($310.24) with two pair, Sixes and Fives
	//Seat 4: Belqi showed [Td Ts] and lost with a pair of Tens
	//Seat 5: A.E.M.07 folded before Flop (didn't bet)
	//Seat 6: Rietzel showed [Ks Ah] and lost with a pair of Kings
	
	 * 
	 */

}
