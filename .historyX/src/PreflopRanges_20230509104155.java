//package evaluate_streets;

/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.io.File;

/*-
 * -
 * 
 * This is an entirely new approach. It is based on the idea that one bet range
 * includes all of a higher range. For example, a preflop open is a wider range
 * than a preflop bet3 and included all of the hands in Bet3. A call range is
 * larger than a betting range and includes all of the hands in the betting
 * range. This is true because of the order in which decisions are made, bet
 * evaluated first then call. Call is a wider range but none of the hands in the
 * betting range will cause a call. The betting range is in the shadow of the
 * calling range.
 * 
 * What this means is that it is possible for a single range to include all bets
 * and calls. Instead of a boolean hand array we use an integer value. 0 is used
 * for never used, 1 is used for the betting range, and 2 for the calling range,
 * 3 for limping, and so on until entire betting range is included up to call
 * allin.
 * 
 * Polarized ranges and ranges such as MDF are not studied yer but may work as
 * well.
 * 
 * The benefits is that a single layered range is far easier to memorize and
 * have a visual copy of than 8 or 9 individual ranges. There is also a
 * performance improvement and reduced memory requirements.
 *
 */
/*
 * This class defines a range of hands The hands are in a Container class. This
 * array represents the commonly used 13 X 13 matrix to represent suited,
 * ofsuit, and pairs An element is true if that card is in the range.
 * 
 */

class PreflopRanges implements Constants {
	private static final int HANDS = 169;
	private static final int SUITED = 0;
	private static final int OFFSUIT = 1;
	private static final int PAIR = 2;
	private static final int INVALID = 2;

	/*-
	 * Constants Gaps in the numbers are to accomidate loose and tight ranges
	 * Example OPEN -1 is loose and OPEN +1 is tight
	 */
	static final int UNUSED = 0;
	static final int LIMP = 2;
	static final int CALL = 5;
	static final int OPEN = 8;
	static final int BET3CALL = 11;
	static final int BET3 = 14;
	static final int BET4CALL = 17;
	static final int BET4 = 20;
	static final int ALLINCALL = 23;
	static final int ALLIN = 26;

	// Special cases - not sure waht to do yet TODO
	static final int ISOLATE = 29;
	static final int STEAL = 32;
	static final int BLUFF = 35;
	static final int MINBET = 38;
	static final int SQUEEZE = 41;
	static final int CALLMIN = 44;
	static final int RAISEDBYSB = 47;
	static final int STEAL3BET = 50;
	static final int STEAL3BETMINRAISE = 53;
	static final int STEALCALL = 56;
	static final int STEALCALLMINRAISE = 59;
	static final int FOLDEDTOSB = 63;
	static final int RAISEDBYBB = 65;

	/*- Number of 2 card combinations. */
	private static final int COMBINATIONS = 1326;
	/*- Array of all possible 2 card combinations - 169 hands. */
	private static final String[] handsArray = { "AA", "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s",
			"A4s", "A3s", "A2s", "AKo", "KK", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s",
			"K2s", "AQo", "KQo", "QQ", "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s", "AJo",
			"KJo", "QJo", "JJ", "JTs", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s", "J2s", "ATo", "KTo", "QTo",
			"JTo", "TT", "T9s", "T8s", "T7s", "T6s", "T5s", "T4s", "T3s", "T2s", "A9o", "K9o", "Q9o", "J9o", "T9o",
			"99", "98s", "97s", "96s", "95s", "94s", "93s", "92s", "A8o", "K8o", "Q8o", "J8o", "T8o", "98o", "88",
			"87s", "86s", "85s", "84s", "83s", "82s", "A7o", "K7o", "Q7o", "J7o", "T7o", "97o", "87o", "77", "76s",
			"75s", "74s", "73s", "72s", "A6o", "K6o", "Q6o", "J6o", "T6o", "96o", "86o", "76o", "66", "65s", "64s",
			"63s", "62s", "A5o", "K5o", "Q5o", "J5o", "T5o", "95o", "85o", "75o", "65o", "55", "54s", "53s", "52s",
			"A4o", "K4o", "Q4o", "J4o", "T4o", "94o", "84o", "74o", "64o", "54o", "44", "43s", "42s", "A3o", "K3o",
			"Q3o", "J3o", "T3o", "93o", "83o", "73o", "63o", "53o", "43o", "33", "32s", "A2o", "K2o", "Q2o", "J2o",
			"T2o", "92o", "82o", "72o", "62o", "52o", "42o", "32o", "22" };
	/*-
	 * This array represents the commonly used 13 X 13 matrix to represent suited,
	 * ofsuit, and pairs An element is true if that card is in the range.
	 */
	int[] rangeArray = new int[HANDS];

	/*- Constructor. */
	PreflopRanges() {
		for (int i = 0; i < HANDS; ++i) {
			rangeArray[i] = UNUSED;
		}
	}

	/*-
	 * Build a combined range from HanhRange files. Arg0 is the path withour
	 * specific files such as Open and Call
	 * 
	 */
	void buildRange(String string0) {
		final var pathOpen = string0 + "Open.ser";
		final var pathCall = string0 + "Call.ser";
		final var pathBet3 = string0 + "Bet3.ser";
		final var pathBet3Call = string0 + "Bet3Call.ser";
		final var pathBet4 = string0 + "Bet4.ser";
		final var pathBet4Call = string0 + "Bet4Call.ser";
		final var pathAllin = string0 + "Allin.ser";
		final var pathAllinCall = string0 + "AllinCall.ser";
		final var pathLimp = string0 + "Limp.ser";

		final var rangeLimp = new HandRange();
		final var rangeOpen = new HandRange();
		final var rangeCall = new HandRange();
		final var rangeBet3 = new HandRange();
		final var rangeBet3Call = new HandRange();
		final var rangeBet4 = new HandRange();
		final var rangeBet4Call = new HandRange();
		final var rangeAllin = new HandRange();
		final var rangeAllinCall = new HandRange();

		rangeLimp.readRange(pathLimp);
		rangeOpen.readRange(pathOpen);
		rangeCall.readRange(pathCall);
		rangeBet3.readRange(pathBet3);
		rangeBet3Call.readRange(pathBet3Call);
		rangeBet4.readRange(pathBet4);
		rangeBet4Call.readRange(pathBet4Call);
		rangeAllin.readRange(pathAllin);
		rangeAllinCall.readRange(pathAllinCall);

		/*-
		for (int i = 0; i < 12; ++i) {
			if (rangeLimp.isRangeArray(i)) {
				this.rangeArray[i] = LIMP;
			}
		}
		for (int i = 0; i < 12; ++i) {
			if (rangeCall.isRangeArray(i)) {
				this.rangeArray[i] = CALL;
			}
		}
		for (int i = 0; i < HANDS; ++i) {
			if (rangeOpen.isRangeArray(i)) {
				this.rangeArray[i] = OPEN;
			}
		}
		for (int i = 0; i < HANDS; ++i) {
			if (rangeBet3Call.isRangeArray(i)) {
				this.rangeArray[i] = BET3CALL;
			}
		}
		for (int i = 0; i < HANDS; ++i) {
			if (rangeBet3.isRangeArray(i)) {
				this.rangeArray[i] = BET3;
			}
		}
		for (int i = 0; i < HANDS; ++i) {
			if (rangeBet4Call.isRangeArray(i)) {
				this.rangeArray[i] = BET4CALL;
			}
		}
		for (int i = 0; i < HANDS; ++i) {
			if (rangeBet4.isRangeArray(i)) {
				this.rangeArray[i] = BET4;
			}
		}
		for (int i = 0; i < HANDS; ++i) {
			if (rangeAllinCall.isRangeArray(i)) {
				this.rangeArray[i] = ALLINCALL;
			}
		}
		for (int i = 0; i < HANDS; ++i) {
			if (rangeAllin.isRangeArray(i)) {
				this.rangeArray[i] = ALLIN;
			}
		}
		*/
	}

	/*- - Get hand string from handArray using index. */
	String getHandString(int int0) {
		return handsArray[int0];
	}

	/*- - Find hand in handArray and return index Arg0 - hand in string format. */
	static int getHandStringToIndex(String string0) {
		for (int $ = 0; $ < HANDS; ++$) {
			if (handsArray[$].equals(string0)) {
				return $;
			}
		}
		Logger.logError("ERROR HandRange handStringToIndex() Invalid hand " + string0);
		return -1;
	}

	/*- - Clear hand range. */
	void clearRange() {
		for (int i = 0; i < HANDS; ++i) {
			rangeArray[i] = UNUSED;
		}
	}

	/*- - Count Hands. */
	int countHands() {
		int $ = 0;
		for (int i = 0; i < HANDS; ++i) {
			if (this.rangeArray[i] != UNUSED) {
				$++;
			}
		}
		return $;
	}

	/*- - Count Hands from starting hand. */
	int countHandsFrom(int int0) {
		int $ = 0;
		for (int i = int0; i < HANDS; ++i) {
			if (this.rangeArray[i] != UNUSED) {
				$++;
			}
		}
		return $;
	}

	/*-
	 * - Subtract one range from another range. All hands that are in the second
	 * range are removed from this range. Arg0 - Second range. The other range is
	 * this.
	 */
	void subtractRange(HandRange handrange0) {
		for (int i = 0; i < HANDS; ++i) {
			if (handrange0.isRangeArray(i) > 0) {
				setRangeArray(i, CALL); // xxxx ?????
			}
		}
	}

	/*-
	 * - Subtract one range from another range. All hands that are in the second
	 * range are removed from this range. Arg0 - Second range. The other range is
	 * this.
	 */
	void addRange(HandRange handrange0) {
		for (int i = 0; i < HANDS; ++i) {
			if (handrange0.isRangeArray(i) > 0) {
				setRangeArray(i, CALL); // xxxx ?????
			}
		}
	}

	/*-
	 * - Set the value of an element in an array 0f 169 possible hands Arg0 is the
	 * first card Arg1 is the second card Arg2 is the boolean condition to set this
	 * element An element in rangeArray is set to true or false.
	 */
	void setRangeArray(Card card0, Card card1, int int2) {
		rangeArray[getArrayIndexCard(card0, card1)] = int2;
	}

	/*-
	 * - Set the value of an element in an array 0f 169 possible hands Arg0 is the
	 * index into array Arg1 is the boolean condition to set this element An element
	 * in rangeArray is set to true or false.
	 */
	boolean setRangeArray(int int0, int int1) {
		if (int0 >= 0 && int0 <= HANDS) {
			rangeArray[int0] = int1;
			return true;
		} else {
			Logger.logError("ERROR HandRange setRangeArray() invalid index " + int0);
		}
		return false;
	}

	/*-
	 * - Returns the value index into an array of the 169 shorthand hands Arg0 is
	 * the first card Arg1 is the second card Returns an index 0 - 195.
	 */
	static int getArrayIndexString(String string0, String string1) {
		final var c2 = new Card(string1);
		return getArrayIndexCard((new Card(string0)), c2);
	}

	/*-
	 * - Returns the value index into an array of the 169 shorthand hands Arg0 is
	 * the first card Arg1 is the second card Returns an index 0 - 195.
	 */
	static int getArrayIndexCard(Card card0, Card card1) {
		final int v1 = card0.getValue() == 0 ? 13 : card0.getValue();
		int v2 = card1.getValue();
		if (v2 == 0) {
			v2 = 13;
		}
		final int $ = (-(v1 - 13)) * 1;
		final int j = (-(v2 - 13)) * 1;
		return card0.getSuit() != card1.getSuit() ? $ + 13 * j : j + 13 * $;
	}

	/*-
	 * - Returns the type of hand Arg0 is the first card Arg1 is the second card.
	 */
	static int getHandType(int int0) {
		final var $ = handsArray[int0];
		if ($.length() == 2) {
			return PAIR;
		}
		if ($.length() != 3) {
			return INVALID;
		}
		if ("s".equals($.substring(2))) {
			return SUITED;
		}
		return "o".equals($.substring(2)) ? OFFSUIT : INVALID;
	}

	/*-
	 * - Returns the value of of an element in rangeArray Arg0 is the first card
	 * Arg1 is the second card Returns to true or false.
	 */
	int getRangeArray(Card card0, Card card1) {
		return rangeArray[getArrayIndexCard(card0, card1)];
	}

	/*-
	 * - Returns the value of of an element in rangeArray Arg0 is the array index
	 * Returns to true or false.
	 */
	int getRangeArray(int int0) {
		if (int0 <= HANDS && int0 >= 0) {
			return rangeArray[int0];
		}
		Logger.logError(new StringBuilder().append("ERROR  HandRange getRangeArray() index out of range ").append(int0)
				.append(" ").append(rangeArray.length).toString());
		return UNUSED;
	}

	/*-
	 * - Returns the String value of hand Arg0 is the first card Arg1 is the second
	 * card Returns hand as a string.
	 */
	static String getRangeArrayString(Card card0, Card card1) {
		return handsArray[getArrayIndexCard(card0, card1)];
	}

	/*-
	 * - Returns the String value of hand Arg0 is the index Returns hand as a
	 * string.
	 */
	static String getRangeArrayString(int int0) {
		return handsArray[int0];
	}

	/*-
	 * - Returns the String value of hand Arg0 is the first card as a String Arg1 is
	 * the second card as a String Returns hand as a string.
	 */
	String getRangeArrayStringx(String string0, String string1) {
		final var card2 = new Card(string1);
		return handsArray[getArrayIndexCard((new Card(string0)), card2)];
	}

	/*- - Count combinations. */
	int getCombos() {
		int $ = 0;
		for (int i = 0; i < HANDS; ++i) {
			if (rangeArray[i] != UNUSED) {
				if (getHandType(i) == SUITED) {
					$ += 4;
				}
				if (getHandType(i) == PAIR) {
					$ += 6;
				}
				if (getHandType(i) == OFFSUIT) {
					$ += 12;
				}
			}
		}
		return $;
	}

	/*-
	 * - Range percent. What percentage of all two card combinations (1326) does
	 * this range use.
	 */
	double getRangePercent() {
		final int $ = getCombos();
		return $ != 0 ? ((double) $ / COMBINATIONS) * 100 : 0;
	}

	/*- - Write a HandRange object to a disk file Arg0 - The full path name. */
	void writeRange(String string0) {
		FileUtils.writeFile(string0, rangeArray);
	}

	/*- - Read a rangeArray from a disk file Arg0 - The full path name. */
	boolean readRange(String string0) {
		final var file = new File(string0);
		if (!file.exists()) {
			Logger.logError("ERROR HandRange readRange() File does not exist " + string0);
			return false;
		}
		this.rangeArray = FileUtils.readFile(string0, rangeArray);
		if (this.rangeArray == null) {
			return false;
		}
		return true;
	}

	/*- - Delete file. */
	static boolean deleteFile(String string0) {
		return FileUtils.deleteFile(string0);
	}
}
