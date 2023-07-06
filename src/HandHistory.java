//package peakholdemevaluator;

/* - This class creates hand histories */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	 *  @author PEAK_
	 * ****************************************************************************** */

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
		final var file = new File(EvalData.applicationDirectory + "\\HandHistory");
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
		final var directoryPath = EvalData.applicationDirectory + "\\HandHistory";
		final var files = FileUtils.listFiles(directoryPath);

		if (files != null) {
			files.forEach(file -> {
				try {
					Files.delete(file);
				} catch (IOException e) {
					System.err.println(new StringBuilder().append("Error while deleting file: ").append(file)
							.append(" - ").append(e.getMessage()).toString());
				}
			});
		} else {
			System.err.println("Error occurred while listing files in the directory.");
		}

	}

	/*-  ******************************************************************************
	 * Create a Hand History and add to file 
	 *   ****************************************************************************** */
	static void createHH() {
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
	 * New hand history file.
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
					.append(hhDate.substring(5, 7)).append(hhDate.substring(8, 10)).append(hhDate.substring(11, 13))
					.append(hhDate.substring(14, 16)).append(num).append(".txt").toString();
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
	 * ****************************************************************************** */
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

				if (EvalData.isFold[street][seat][orbit]) {
					reportSt[lines] = nameStringArray[seat] + ": folds";
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
					reportSt[lines] = new StringBuilder().append(nameStringArray[seat]).append(": shows [")
							.append(EvalData.holeCards[seat][0].toString()).append("] [")
							.append(EvalData.holeCards[seat][1].toString()).append("]").toString();
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
	 * Summary 
	 *   ****************************************************************************** */
	static void summaryHH() {
		final String st;
		var sidePotBD = zeroBD;
		var mainPotBD = zeroBD;

		++lines;
		reportSt[lines++] = "*** SUMMARY *** \r\n";
		for (int i = 0; i < PLAYERS; ++i) {
			final boolean condition = EvalData.winnerCollectedBD[i] != null
					&& EvalData.winnerCollectedBD[i].compareTo(zeroBD) != 0;
			if (condition) {
				sidePotBD = sidePotBD.add(EvalData.playerSidePotSplitTotalBD[i]);
			}
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
		if (EvalData.street == FLOP) {
			reportSt[lines++] = new StringBuilder().append("Board [").append(EvalData.board[0]).append(" ")
					.append(EvalData.board[1]).append(" ").append(EvalData.board[2]).append("]\r\n").toString();
		}
		if (EvalData.street == TURN) {
			reportSt[lines++] = new StringBuilder().append("Board [").append(EvalData.board[0]).append(" ")
					.append(EvalData.board[1]).append(" ").append(EvalData.board[2]).append(" ")
					.append(EvalData.board[3]).append("]\r\n").toString();
		}
		if (EvalData.street == RIVER) {
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

			if (!EvalData.playerWonShowdown[j]) {
				reportSt[lines] += new StringBuilder().append(" showed [").append(EvalData.holeCards[j][0]).append(" ")
						.append(EvalData.holeCards[j][1]).append("] and lost with a smile ").toString();
				reportSt[lines++] += "\r\n";
				continue;
			}

			if (EvalData.playerWon[j]) {
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
			if (EvalData.simulation && EvalData.foldCount < MAXFOLDED && !EvalData.playerWon[j]) {
				reportSt[lines] += " completed hand without Showdown or Win";
				reportSt[lines++] += "\r\n";
			}
		}
	}

	/*- Add to text file. */
	private static void writeHH() {
		for (int i = 0; i < lines; ++i) {
			pwFile1.append(reportSt[i]);
		}

		pwFile1.append("\r\n\r\n\r\n\r\n");
		pwFile1.flush();
	}

	/*-
	PokerStars Hand #198162255235:  Hold'em No Limit ($1/$2 USD) - 2019/03/15 13:30:27 ET
	Table 'Aaltje III' 6-max Seat #5 is the button
	Seat 1: psek1 ($202.38 in chips) 
	Seat 2: Rifa92 ($217.90 in chips) 
	Seat 3: solbi86 ($200 in chips) 
	Seat 4: Slavikkkk7 ($200 in chips) 
	Seat 5: typ6oky3mu4 ($200 in chips) 
	Seat 6: AlexEliseev7 ($211.01 in chips) 
	AlexEliseev7: posts small blind $1
	psek1: posts big blind $2
	*** HOLE CARDS ***
	Rifa92: calls $2
	solbi86: folds 
	Slavikkkk7: folds 
	typ6oky3mu4: folds 
	AlexEliseev7: folds 
	psek1: checks 
	*** FLOP *** [3c 5d Ks]
	psek1: bets $2
	Rifa92: calls $2
	*** TURN *** [3c 5d Ks] [3s]
	psek1: bets $7.01
	Rifa92: calls $7.01
	*** RIVER *** [3c 5d Ks 3s] [Ah]
	psek1: bets $17.93
	Rifa92: calls $17.93
	*** SHOW DOWN ***
	psek1: shows [4d 2d] (a straight, Ace to Five)
	Rifa92: mucks hand 
	psek1 collected $56.13 from pot
	*** SUMMARY ***
	Total pot $58.88 | Rake $2.75 
	Board [3c 5d Ks 3s Ah]
	Seat 1: psek1 (big blind) showed [4d 2d] and won ($56.13) with a straight, Ace to Five
	Seat 2: Rifa92 mucked
	Seat 3: solbi86 folded before Flop (didn't bet)
	Seat 4: Slavikkkk7 folded before Flop (didn't bet)
	Seat 5: typ6oky3mu4 (button) folded before Flop (didn't bet)
	Seat 6: AlexEliseev7 (small blind) folded before Flop
	
	
	PokerStars Hand #198162187333:  Hold'em No Limit ($1/$2 USD) - 2019/03/15 13:28:37 ET
	Table 'Aaltje III' 6-max Seat #3 is the button
	Seat 1: psek1 ($207.22 in chips) 
	Seat 2: Rifa92 ($158.24 in chips) 
	Seat 3: solbi86 ($200 in chips) 
	Seat 4: Slavikkkk7 ($224.65 in chips) 
	Seat 5: typ6oky3mu4 ($200 in chips) 
	Seat 6: AlexEliseev7 ($221.85 in chips) 
	Slavikkkk7: posts small blind $1
	typ6oky3mu4: posts big blind $2
	*** HOLE CARDS ***
	AlexEliseev7: raises $4 to $6
	psek1: folds 
	Rifa92: folds 
	solbi86: folds 
	Slavikkkk7: raises $19 to $25
	typ6oky3mu4: folds 
	AlexEliseev7: folds 
	Uncalled bet ($19) returned to Slavikkkk7
	Slavikkkk7 collected $14 from pot
	Slavikkkk7: doesn't show hand 
	*** SUMMARY ***
	Total pot $14 | Rake $0 
	Seat 1: psek1 folded before Flop (didn't bet)
	Seat 2: Rifa92 folded before Flop (didn't bet)
	Seat 3: solbi86 (button) folded before Flop (didn't bet)
	Seat 4: Slavikkkk7 (small blind) collected ($14)
	Seat 5: typ6oky3mu4 (big blind) folded before Flop
	Seat 6: AlexEliseev7 folded before Flop
	 */

}
