//package evaluate_streets;

public class WetDry implements Constants {
	/*- **************************************************************************** 
	* This Class 
	* 
	* @author PEAK_
	*******************************************************************************/

	private WetDry() {
		throw new IllegalStateException("Utility class");
	}

	/*- *****************************************************************************
	 * This method will determine if a Flop is wet or dry or neutral.
	 * This is somewhat subjective as there is no precise definition.
	 * I have looked at other applications, like Huds and get consistant results. 
	 * Looking for something more defenative but this is good for now.
	 * boardArray and the HML value is used to determine wet or dry.
	 * There are 3 slots in boardArray, one for each condition.
	  ******************************************************************************/
	static void wetDry() {
		if (EvalData.boardArray[BOARD_3F]) {
			EvalData.boardArray[BOARD_WET] = true;
			return;
		}
		if (EvalData.boardArray[BOARD_PAIR] && EvalData.hmlIndexFlop != BOARD_HHH) {
			EvalData.boardArray[BOARD_DRY] = true;
			return;
		}
		if (EvalData.hmlIndexFlop == BOARD_HHH) {
			if (EvalData.boardValue1 == ACE || EvalData.gap0Score >= 10 || EvalData.gap1Score > 5
					|| EvalData.boardArray[BOARD_SET]) {
				EvalData.boardArray[BOARD_WET] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == BOARD_HHM) {
			if (EvalData.boardValue1 == ACE || EvalData.gap0Score >= 6
					|| (EvalData.gap1Score >= 6 && EvalData.boardValue2 >= QUEEN)) {
				EvalData.boardArray[BOARD_WET] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == BOARD_HHL) {
			if ((EvalData.boardValue1 == QUEEN || EvalData.gap0 == 1) || EvalData.gap1 >= 1 || EvalData.gap1Score >= 5
					|| (EvalData.gap0 == 0 && EvalData.gap1 == 0)) {
				EvalData.boardArray[BOARD_WET] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == BOARD_HMM) {
			if (EvalData.boardValue1 != ACE
					&& (EvalData.boardValue1 == QUEEN || !EvalData.boardArray[BOARD_RAINBOW] || EvalData.gap0Score >= 10
							|| EvalData.gap0 == 2 || (EvalData.boardValue1 > TEN && EvalData.gap0 != 0)
							|| EvalData.gap1Score > 6 || EvalData.gap2 == 2)) {
				EvalData.boardArray[BOARD_WET] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == BOARD_HML) {
			if (EvalData.boardValue1 == ACE && EvalData.gap0 == 1) {
				EvalData.boardArray[BOARD_DRY] = true;
			} else {
				if (EvalData.gap0Score >= 10 || EvalData.gap1Score > 6) {
					EvalData.boardArray[BOARD_WET] = true;
					return;
				}
				if (EvalData.boardValue1 == QUEEN) {
					if (EvalData.boardValue2 < NINE) {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.boardValue1 == NINE) {
					if (EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.boardValue2 < NINE && EvalData.boardArray[BOARD_RAINBOW]) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0Score >= 10 || EvalData.gap1Score > 6) {
				EvalData.boardArray[BOARD_WET] = true;
			} else {
				if (EvalData.boardValue1 == QUEEN) {
					if (EvalData.boardValue2 < NINE) {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.boardValue1 == NINE) {
					if (EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.boardValue2 < NINE && EvalData.boardArray[BOARD_RAINBOW]) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.boardValue1 == QUEEN) {
				if (EvalData.boardValue2 < NINE) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			} else {
				if (EvalData.boardValue1 == NINE) {
					if (EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.boardValue2 < EIGHT && EvalData.boardArray[BOARD_RAINBOW]) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.boardValue1 == NINE) {
				if (EvalData.boardArray[BOARD_RAINBOW]) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			} else if (EvalData.boardValue2 < NINE && EvalData.boardArray[BOARD_RAINBOW]) {
				EvalData.boardArray[BOARD_DRY] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == BOARD_HLL) {
			if (EvalData.boardValue1 == ACE) {
				if (EvalData.gap0 >= 1) {
					return;
				}
				EvalData.boardArray[BOARD_DRY] = true;
			} else {
				if (EvalData.gap0Score >= 10) {
					EvalData.boardArray[BOARD_WET] = true;
					return;
				}
				if (EvalData.gap0Score < 7 || EvalData.gap1Score < 6) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if ((EvalData.gap0 == 2 && !EvalData.boardArray[BOARD_ACE_HIGH]) || EvalData.gap1Score > 5) {
					EvalData.boardArray[BOARD_WET] = true;
					return;
				}
				if (EvalData.gap0 == 0 && EvalData.gap1 == 0) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.boardArray[BOARD_RAINBOW] && EvalData.gap0 < 2) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0Score >= 10) {
				EvalData.boardArray[BOARD_WET] = true;
			} else {
				if (EvalData.gap0Score < 7 || EvalData.gap1Score < 6) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if ((EvalData.gap0 == 2 && !EvalData.boardArray[BOARD_ACE_HIGH]) || EvalData.gap1Score > 5) {
					EvalData.boardArray[BOARD_WET] = true;
					return;
				}
				if (EvalData.gap0 == 0 && EvalData.gap1 == 0) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.boardArray[BOARD_RAINBOW] && EvalData.gap0 < 2) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0Score < 7 || EvalData.gap1Score < 6) {
				EvalData.boardArray[BOARD_DRY] = true;
			} else {
				if ((EvalData.gap0 == 2 && !EvalData.boardArray[BOARD_ACE_HIGH]) || EvalData.gap1Score > 5) {
					EvalData.boardArray[BOARD_WET] = true;
					return;
				}
				if (EvalData.gap0 == 0 && EvalData.gap1 == 0) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.boardArray[BOARD_RAINBOW] && EvalData.gap0 < 2) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if ((EvalData.gap0 == 2 && !EvalData.boardArray[BOARD_ACE_HIGH]) || EvalData.gap1Score > 5) {
				EvalData.boardArray[BOARD_WET] = true;
			} else {
				if (EvalData.gap0 == 0 && EvalData.gap1 == 0) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.boardArray[BOARD_RAINBOW] && EvalData.gap0 < 2) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if ((EvalData.gap0 == 0) || (EvalData.boardArray[BOARD_RAINBOW] && EvalData.gap0 < 2)) {
				EvalData.boardArray[BOARD_DRY] = true;
			}
			return;
		}
		if (EvalData.hmlIndexFlop == BOARD_MMM) {
			if (EvalData.gap0Score >= 10 || EvalData.gap0 == 2 || EvalData.gap1Score > 5) {
				EvalData.boardArray[BOARD_WET] = true;
			} else if (EvalData.boardArray[BOARD_RAINBOW]) {
				EvalData.boardArray[BOARD_DRY] = true;
			}
		} else if (EvalData.hmlIndexFlop == BOARD_MML) {
			if (EvalData.gap0Score > 10) {
				EvalData.boardArray[BOARD_WET] = true;
			} else {
				if (EvalData.gap0 == 1) {
					return;
				}
				if (EvalData.gap1 >= 1) {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0 != 1 && EvalData.gap1 >= 1) {
				EvalData.boardArray[BOARD_DRY] = true;
			}

		} else if (EvalData.hmlIndexFlop == BOARD_MLL) {
			if (EvalData.gap0Score >= 10) {
				EvalData.boardArray[BOARD_WET] = true;
			} else {
				if (EvalData.gap0 == 2) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.gap0 == 1) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
					} else {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.gap1 == 1) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.gap1 == 2) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
						return;
					}
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0 == 2) {
				EvalData.boardArray[BOARD_DRY] = true;
			} else {
				if (EvalData.gap0 == 1) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
					} else {
						EvalData.boardArray[BOARD_DRY] = true;
					}
					return;
				}
				if (EvalData.gap1 == 1) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.gap1 == 2) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
						return;
					}
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap0 == 1) {
				if (!EvalData.boardArray[BOARD_RAINBOW]) {
					EvalData.boardArray[BOARD_WET] = true;
				} else {
					EvalData.boardArray[BOARD_DRY] = true;
				}
			} else {
				if (EvalData.gap1 == 1) {
					EvalData.boardArray[BOARD_DRY] = true;
					return;
				}
				if (EvalData.gap1 == 2) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
						return;
					}
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap1 == 1) {
				EvalData.boardArray[BOARD_DRY] = true;
			} else {
				if (EvalData.gap1 == 2) {
					if (!EvalData.boardArray[BOARD_RAINBOW]) {
						EvalData.boardArray[BOARD_WET] = true;
						return;
					}
					EvalData.boardArray[BOARD_DRY] = true;
				}
			}
			if (EvalData.gap1 != 2) {
				return;
			}
			if (!EvalData.boardArray[BOARD_RAINBOW]) {
				EvalData.boardArray[BOARD_WET] = true;
				return;
			}
			EvalData.boardArray[BOARD_DRY] = true;
		} else if (EvalData.hmlIndexFlop != BOARD_LLL) {
			// TODO System.out.println(new StringBuilder().append("// ").append("ERROR No
			// wet dry ")
			// .append(EvalData.hmlIndexFlop).toString());
			// TODO Crash.log("ERROR " + EvalData.hmlIndexFlop);
		}
	}
}
