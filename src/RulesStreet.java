//package game;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RulesStreet implements Constants {
	/*- - Board analysis. */
	public static final int CBET_USE_RANGE = 0;
	public static final int CBET_RAISE_IF_CHECKED = 1;
	/*- Size includes expansion. */
	private static final int ARRAY_SIZE = 64;
	public static String ruleStringArray[] = { "CBET_USE_RANGE", "CBET_RAISE_IF_CHECKED " };
	private boolean[] ruleArray = { false, false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false };
	/*-
	 * - This is a rule class that help decide play on post-flop streets. The
	 * implementation a boolean array.
	 */

	private String rulePath = "";

	/*- Constructor. */
	RulesStreet() {
		clear();
	}

	/*- - Set everything to fold. */
	public void clear() {
		for (int i = 0; i < ARRAY_SIZE; ++i) {
			ruleArray[i] = false;
		}
	}

	/*- - Get a play. */
	public boolean getRule(int i) {
		return ruleArray[i];
	}

	/*- - Set a play. */
	public void setRule(int i, boolean b) {
		ruleArray[i] = b;
		writeRules(rulePath);
	}

	/*- - play to String. */
	public String playToString(int play) {
		return ruleStringArray[play];
	}

	/*- - Write a HandRange object to a disk file Arg0 - The full path name. */
	public void writeRules(String path) {
		deleteFile(path);
		try {
			Logger.log("WritrRules " + path);
			final FileOutputStream fileOut = new FileOutputStream(path);
			final ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(ruleArray);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/*- - Read a rangeArray from a disk file Arg0 - The full path name. */
	public void readRules(String path) {
		rulePath = path;
		try (FileInputStream fileIn = new FileInputStream(path); ObjectInputStream in = new ObjectInputStream(fileIn)) {
			ruleArray = (boolean[]) in.readObject();
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
}
