//package evaluate_streets;
/*-  ******************************************************************************
 *  @author PEAK_ 
 * ****************************************************************************** */

public class ActionToString implements Constants {
	/*-  ******************************************************************************
 *  @author PEAK_ 
 * ****************************************************************************** */

	private ActionToString() {
		throw new IllegalStateException("Utility class");
	}

	/*- 
	 * Return text for action
	 * arg0 is the action
	 * returns a String 
	 * 
	 */
	public static String getActionX(int action) {
		if (action == FOLD) {
			return "Fold ";
		}
		if (action == CALL) {
			return "Call ";
		}
		if (action == LIMP) {
			return "Limp ";
		}
		if (action == CHECK) {
			return "Check ";
		}
		if (action == BET1) {
			return "Bet1 ";
		}
		return action == BET2 ? "Bet2 "
				: action == BET3 ? "Bet3 " : action == BET4 ? "Bet4 " : action == ALLIN ? "Allin " : "ERROR ";
	}
}
