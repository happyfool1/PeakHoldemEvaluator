//package game;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Rules implements Constants {
//	public static final int HANDS = Evaluate.MAX_HANDS;
//	public static final int REAL_HANDS = Evaluate.MAX_HANDS;

	/*- Opponent actions - What Rule reacts to. Index into array */
	public static final int OP_CHECK = 0;
	public static final int OP_BET1 = 1;
	public static final int OP_BET2 = 2;
	public static final int OP_BET3 = 3;
	public static final int OP_BET4 = 4;
	public static final int OP_ALLIN = 5;
	public static final int OP_ACTIONS = 6;
	/*- Returned Values. */

	public static final int BET = 0;
	public static final int CALL = 1;
	public static final int ALLIN = 2;
	public static final int NO_ACTION = 3;

	/*- Size includes expansion. */
	private static final int ARRAYSIZE = HANDS * OP_ACTIONS;
	/*- Used. */
	//private static final int REAL_ARRAYSIZE = OP_ACTIONS * REAL_HANDS;
	/*- What is returned. */
	public static String playStringArray[] = { "Bet", "Call", "Allin" };
	public static String opActionStringArray[] = { "No Bet", "Bet1", "Bet2", "Bet3", "Bet4", "Allin" };
	/*- Percentages. */
	private static double foldPercent;
	private static double checkPercent;
	private static double callPercent;
	private static double raisePercent;
	private static double allinPercent;
	/*-
	 * Updated to rempve fold or check as actions. Rule is not a final decision. 
	 * Following Rules the player always has options such as blffing, MDF, CBet, or bluffing.
	 * NO_ACTION has been added for that reason.
	 */

	/*-
	 * - This is a rule class that is used to decide on a play. The implementation
	 * is a multi dimensional array. The last array contains an an integer value
	 * that corresponds to the players action. Index 0 - The players hand - NONE,
	 * HIGH_Card, PAIR, etc.. Index 1 - The opponents action = CALL, BET1,ALLIN
	 * . The results are the players action in response.
	 */
	public int[][] ruleArray = new int[HANDS][OP_ACTIONS];
	private String rulePath = "";

	/*- Constructor. */
	Rules() {
		clear();
	}

	/*- - Set everything to fold. */
	public void clear() {
		for (int i = 0; i < HANDS; ++i) {
			for (int j = 0; j < OP_ACTIONS; ++j) {
				ruleArray[i][j] = FOLD;
			}
		}
	}

	/*- - Get a play. */
	public int getPlay(int i, int j) {
		return ruleArray[i][j];
	}

	/*- - Set a play. */
	public void setPlay(int i, int j, int play) {
		ruleArray[i][j] = play;
		calculatePercentages();
	}

	/*- - play to String. */
	public String playToString(int play) {
		return playStringArray[play];
	}

	/*- - Opponent Action to String. */
	public String opActionToString(int opAction) {
		return opActionStringArray[opAction];
	}

	/*- - Do percentage calculations. */
	private void calculatePercentages() {
		int fold = 0;
		int check = 0;
		int call = 0;
		int raise = 0;
		int allin = 0;
		for (int i = 0; i < REAL_HANDS; ++i) {
			for (int j = 0; j < OP_ACTIONS; ++j) {
				switch (ruleArray[i][j]) {
				case FOLD:
					fold++;
					break;
				case CHECK:
					check++;
					break;
				case CALL:
					call++;
					break;
				case BET:
					raise++;
					break;
				case ALLIN:
					allin++;
					break;
				}
			}
		}
		foldPercent = doPercentage(fold);
		checkPercent = doPercentage(check);
		callPercent = doPercentage(call);
		raisePercent = doPercentage(raise);
		allinPercent = doPercentage(allin);
	}

	/*- - Do percentage calculation. */
	private double doPercentage(int num) {
		return num == 0 ? 0 : 100.0 * 1. * num / REAL_ARRAYSIZE;
	}

	/*- - Write a HandRange object to a disk file Arg0 - The full path name. */
	public void writeRules(String path) {
		try (FileOutputStream fileOut = new FileOutputStream(path);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(ruleArray);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/*- - Read a rangeArray from a disk file Arg0 - The full path name. */
	public void readRules(String path) {
		rulePath = path;
		try (FileInputStream fileIn = new FileInputStream(path); ObjectInputStream in = new ObjectInputStream(fileIn)) {
			ruleArray = (int[][]) in.readObject();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException i) {
			i.printStackTrace();
			return;
		}
	}

	/*- - Delete file. */
	public static boolean deleteFile(String fileN) {
		final boolean result = true;
		final File f = new File(fileN);
		if (f.exists()) {
			f.delete();
		}
		return result;
	}

	public double getFoldPercent() {
		return foldPercent;
	}

	public double getCheckPercent() {
		return checkPercent;
	}

	public double getCallPercent() {
		return callPercent;
	}

	public double getRaisePercent() {
		return raisePercent;
	}

	public double getAllinPercent() {
		return allinPercent;
	}
}
