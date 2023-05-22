//package evaluate_streets;

public class RelativePositionX implements Constants {
	/*- **************************************************************************** 
	* This Class contains methods related to Relative Position.
	* The players position relative to other players based on who will act in
	* what order and if it is heads up ( 2 players ).
	* RP_FIRST, RP_FIRST_HU, RP_MIDDLE, RP_LAST, RP_LAST_HU.
	* 
	* The Relative Position changes whenever a player folds, so it changes 
	* throught the hand. It is more important is some ways then position
	* that is unchanged throught a single hand. 
	* Position is important in determining  preflop ranges and preflop actions.
	* Relative position is now, not what happened before.
	* 
	* @author PEAK_
	*******************************************************************************/

	private RelativePositionX() {
		throw new IllegalStateException("Utility class");
	}

	static int first = 0;
	static int last = 0;
	static int middle = 0;

	/*- **************************************************************************** 
	* Get the current relative position
	*******************************************************************************/
	private static void getRelativePosition() {
		first = firstPlayer();
		if (EvalData.seat != first) {
			last = lastPlayer();
		}
		if (EvalData.seat != last) {
			middle = EvalData.seat;
		}

		if (EvalData.seat == first) {
			EvalData.relativePosition[EvalData.seat] = RP_FIRST;
			EvalData.positions[EvalData.seat] = RP_FIRST;
			if (EvalData.foldCount - EvalData.PLAYERS == 2) {
				// TODO
			}

		} else if (EvalData.seat == last) {
			EvalData.relativePosition[EvalData.seat] = RP_LAST;
			EvalData.positions[EvalData.seat] = RP_LAST;
			if (EvalData.foldCount - EvalData.PLAYERS == 2) {
				// TODO
			} else {
				// TODO
			}
		} else {
			// TODO
		}

	}

	private static int firstPlayer() {
		first = GameControl.seatSB; // TODO
		for (int i = 0; i < EvalData.PLAYERS; ++i) {
			if (!EvalData.playerFolded[first]) {
				break;
			}
			++first;
			if (first >= EvalData.PLAYERS) {
				first = 0;
			}
		}
		return first;
	}

	/*- **************************************************************************** 
	*  Find the first player return seat number. 
	*******************************************************************************/
	private static int lastPlayer() {
		last = GameControl.seatBU; // TODO
		for (int i = 0; i < EvalData.PLAYERS; ++i) {
			if (!EvalData.playerFolded[last]) {
				break;
			}
			--last;
			if (last < 0) {
				last = EvalData.PLAYERS - 1;
			}
		}
		return last;
	}

}
