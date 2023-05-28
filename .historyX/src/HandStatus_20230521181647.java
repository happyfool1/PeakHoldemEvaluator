//package evaluate_streets;

public class HandStatus implements Constants {
	private HandStatus() {
		throw new IllegalStateException("Utility class");
	}

	/*- **************************************************************************** 
	* This class contains methods that check EvalData.both and EvalData.bothCards
	* for pairs, two pairs, sets, four of a kind, full house, straights and flushes.
	* 
	* @author PEAK_
	*******************************************************************************/

	/*- **************************************************************************** 
	* This method will check EvalData.other for made hands that consist of
	* pair combinations. 
	* One of 5 boolean variables is set if found.
	* EvalData.anyPaired if any of the above set
	*******************************************************************************/
	static void findPairsSetsFullHouse() {
		final int[] rankSeen = new int[13];
		int numPairs = 0;
		int numSet = 0;
		int numQuad = 0;
		// Iterate over each card and update rankSeen
		for (int i = 0; i < EvalData.bothCount; i++) {
			++rankSeen[EvalData.both[i].value];
		}

		for (int i = 0; i < 13; i++) {
			if (rankSeen[i] == 2) {
				++numPairs;
			} else if (rankSeen[i] == 3) {
				++numSet;
			} else if (rankSeen[i] == 4) {
				++numQuad;
			}
		}
		if (numPairs == 0 && numSet == 0 && numQuad == 0) {
			return;
		}

		if (numPairs == 1 && numSet == 0) {
			EvalData.onePair = true;
		} else if (numPairs == 2 && numSet == 0) {
			EvalData.twoPair = true;
		} else if (numPairs == 0 && numSet >= 1) {
			EvalData.threeOfKind = true;
		} else if (numPairs == 1 && numSet == 1) {
			EvalData.fullHouse = true;
		} else if (numQuad == 1) {
			EvalData.fourOfKind = true;
		}

		if (EvalData.onePair || EvalData.twoPair || EvalData.threeOfKind || EvalData.fullHouse || EvalData.fourOfKind) {
			EvalData.anyPaired = true;
		}
	}

	/*- *****************************************************************************
	*Check the EvalData.both to be sure that all cards are unique
	***************************************************************************** */
	static void checkForNoDuplicates() {
		for (int i = 0; i < EvalData.bothCount - 1; i++) {
			for (int j = i + 1; j < EvalData.bothCount; j++) {
				System.out.println(//BB " +EvalData.both[i] + " " + EvalData.both[j] 
				+ " " + EvalData.bothCount);
				if (EvalData.both[i].equals(EvalData.both[j])) {
					Crash.log("Duplicate cards " + EvalData.both[i]);
				}
			}
		}
	}

	/*- **************************************************************************** 
	* This method will check EvalData.other a made hands straight.
	*EvalData.straight
	*******************************************************************************/
	static void findStraight() {
		// Check for ace-low straight
		if (EvalData.bothValue1 == ACE && EvalData.bothValue2 == KING && EvalData.bothValue3 == QUEEN
				&& EvalData.bothValue4 == JACK && EvalData.bothValue5 == TEN) {
			EvalData.straight = true;
			return;
		}
		// Check for regular straight
		if (EvalData.bothGap1_2 == 1 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1
				&& EvalData.bothGap4_5 == 1) {
			EvalData.straight = true;
		} else if (EvalData.street >= TURN) {
			if (EvalData.bothGap2_3 == 0 || EvalData.bothGap3_4 == 0 || EvalData.bothGap4_5 == 0
					|| EvalData.bothGap5_6 == 0) {
				EvalData.straight = true;
			} else if (EvalData.street == RIVER) {
				if (EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1 && EvalData.bothGap5_6 == 1
						&& EvalData.bothGap6_7 == 1) {
					EvalData.straight = true;
				}
			}
		}
	}

	/*- **************************************************************************** 
	* This method will check EvalData.other a made hands flush.
	* EvalData.flush
	*******************************************************************************/
	static void findFlush() {
		// Check if all cards have the same suit
		if (EvalData.bothCardsSuit1 == EvalData.bothCardsSuit2 && EvalData.bothCardsSuit1 == EvalData.bothCardsSuit3
				&& EvalData.bothCardsSuit1 == EvalData.bothCardsSuit4
				&& EvalData.bothCardsSuit1 == EvalData.bothCardsSuit5) {
			EvalData.flush = true;
		} else if (EvalData.street >= TURN) {
			if (EvalData.bothCardsSuit2 == EvalData.bothCardsSuit3 && EvalData.bothCardsSuit2 == EvalData.bothCardsSuit4
					&& EvalData.bothCardsSuit2 == EvalData.bothCardsSuit5
					&& EvalData.bothCardsSuit2 == EvalData.bothCardsSuit6) {
				EvalData.flush = true;
			} else if (EvalData.street == RIVER) {
				if (EvalData.bothCardsSuit3 == EvalData.bothCardsSuit4
						&& EvalData.bothCardsSuit3 == EvalData.bothCardsSuit5
						&& EvalData.bothCardsSuit3 == EvalData.bothCardsSuit6
						&& EvalData.bothCardsSuit3 == EvalData.bothCardsSuit7) {
					EvalData.flush = true;
				}
			}
		}
	}

}
