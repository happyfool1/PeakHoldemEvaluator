//package evaluate_streets;

public class DrawAnalysis implements Constants {
	/*- **************************************************************************** 
	* This Class contains several methods used to  gather information about 
	* the board and board combined with hole cards.
	* Information obtained includes:
	* 		Board analysis on Flop, gaps, suitedness, etc..
	* 		Draws available with combined board and hole cards. 
	* A data holding Classes Draw and Made is used to save the cards that make up
	* the hand and  other related information. 
	* There is one instance of Draw and Made in EvalData for each player as arrays
	* indexed by seat number. 
	* 
	* @author PEAK_
	*******************************************************************************/

	private DrawAnalysis() {
		throw new IllegalStateException("Utility class");
	}

	/*- **************************************************************************** 
	* Flop drawing hands
	*******************************************************************************/
	static int doFlopDraw() {
		boolean hit = false;
		// x_xxx xx_xx xxx_x
		if (EvalData.bothGap1_2 == 2 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1) {
			hit = true;
		} else if (EvalData.bothGap1_2 == 1 && EvalData.bothGap2_4 == 3 && EvalData.bothGap3_4 == 1) {
			hit = true;
		} else if (EvalData.bothGap1_2 == 1 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 2) {
			hit = true;
		}
		if (hit && EvalData.draw[EvalData.seat].draw(DRAW_GUTSHOT)) {
			EvalData.bothGutshotDraw = true;
			return DRAW_GUTSHOT;
		}

		// ssssx xssss
		hit = false;
		int suit = EvalData.bothCardsSuit1;
		if (suit == EvalData.bothCardsSuit2 && suit == EvalData.bothCardsSuit3 && suit == EvalData.bothCardsSuit4) {
			hit = true;
		}
		suit = EvalData.bothCardsSuit2;
		if (suit == EvalData.bothCardsSuit3 && suit == EvalData.bothCardsSuit4 && suit == EvalData.bothCardsSuit5) {
			hit = true;
		}
		if (hit && EvalData.draw[EvalData.seat].draw(DRAW_FLUSH)) {
			EvalData.bothFlushDraw = true;
			return DRAW_FLUSH;
		}

		// ssssx xssss Assss
		hit = false;
		if (EvalData.bothGap1_2 == 1 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1
				&& EvalData.bothGap4_5 != 1) {
			hit = true;
		}
		if (EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1
				&& EvalData.bothValue1 != ACE) {
			hit = true;
		}
		if (hit && EvalData.draw[EvalData.seat].draw(DRAW_OESD)) {
			EvalData.bothOesdDraw = true;
			return DRAW_OESD;
		}

		hit = false;
		// ssssx xssss
		if (EvalData.bothGap1_2 == 1 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1
				&& EvalData.bothGap4_5 != 1) {
			hit = true;
		}
		if (EvalData.bothGap1_2 != 1 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1
				&& EvalData.bothGap4_5 == 1) {
			hit = true;
		}
		if (hit) {
			if (EvalData.bothValue5 == TWO) {
				if (EvalData.draw[EvalData.seat].draw(DRAW_STRAIGHT)) {
					EvalData.bothStraightDraw = true;
					return DRAW_STRAIGHT;
				}
			} else if (EvalData.draw[EvalData.seat].draw(DRAW_OESD)) {
				EvalData.bothOesdDraw = true;
				return DRAW_OESD;
			}
		}

		final boolean condition = EvalData.bothOesdDraw
				&& ((EvalData.bothGap2_3 == 1) && (EvalData.bothGap3_4 == 1) && (EvalData.bothGap4_5 == 1)
						&& (EvalData.bothGap5_6 != 1) && (EvalData.bothValue5 == TWO))
				&& EvalData.draw[EvalData.seat].draw(DRAW_STRAIGHT);
		if (condition) {
			EvalData.bothStraightDraw = true;
			return DRAW_STRAIGHT;
		}
		EvalData.bothNone = true;
		return DRAW_NONE;
	}

	/*- **************************************************************************** 
	* Turn drawing hands
	*******************************************************************************/
	static int doTurnDraw() {
		boolean hit = false;
		if (EvalData.bothGap2_3 == 2 && EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1) {
			hit = true;
		} else if (EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 2 && EvalData.bothGap4_5 == 1) {
			hit = true;
		} else if (EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 2) {
			hit = true;
		}
		if (hit && EvalData.draw[EvalData.seat].draw(DRAW_GUTSHOT)) {
			EvalData.bothGutshotDraw = true;
			return DRAW_GUTSHOT;
		}
		hit = false;

		int suit = EvalData.bothCardsSuit1;
		if (suit == EvalData.bothCardsSuit3 && suit == EvalData.bothCardsSuit4 && suit == EvalData.bothCardsSuit5) {
			hit = true;
		} else {
			suit = EvalData.bothCardsSuit2;
			if (suit == EvalData.bothCardsSuit3 && suit == EvalData.bothCardsSuit4 && suit == EvalData.bothCardsSuit5) {
				hit = true;
			}
		}
		if (EvalData.draw[EvalData.seat].draw(DRAW_FLUSH)) {
			EvalData.bothFlushDraw = true;
			return DRAW_FLUSH;
		}

		if (EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1
				&& EvalData.bothGap6_7 != 1) {
			hit = true;
		} else if (EvalData.bothGap2_3 != 1 && EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1
				&& EvalData.bothGap5_6 == 1) {
			hit = true;
		}
		if (hit) {
			if (EvalData.both[5].value == TWO) {
				if (EvalData.draw[EvalData.seat].draw(DRAW_STRAIGHT)) {
					EvalData.bothStraightDraw = true;
					return DRAW_STRAIGHT;
				}
			} else {
				if (EvalData.draw[EvalData.seat].draw(DRAW_OESD)) {
					EvalData.bothOesdDraw = true;
					return DRAW_OESD;
				}
			}
		}
		EvalData.bothNone = true;
		return DRAW_NONE;
	}

}
