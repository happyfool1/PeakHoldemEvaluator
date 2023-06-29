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
		boolean hitf = false;
		boolean hit = false;
		boolean hit2 = false;
		// ssssx xssss
		int suit = EvalData.bothCardsSuit1;
		if (suit == EvalData.bothCardsSuit2 && suit == EvalData.bothCardsSuit3 && suit == EvalData.bothCardsSuit4) {
			hitf = true;
		}
		suit = EvalData.bothCardsSuit2;
		if (suit == EvalData.bothCardsSuit3 && suit == EvalData.bothCardsSuit4 && suit == EvalData.bothCardsSuit5) {
			hitf = true;
		}
		// ssssx xssss
		if (EvalData.bothGap1_2 == 1 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1
				&& EvalData.bothGap4_5 != 1) {
			hit = true;
		} else if (EvalData.bothGap1_2 != 1 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1
				&& EvalData.bothGap4_5 == 1) {
			hit2 = true;
		}
		// Flush
		if (hitf) {
			if (EvalData.draw[EvalData.seat].draw(DRAW_FLUSH)) {
				return DRAW_FLUSH;
			} else if (EvalData.draw[EvalData.seat].draw(DRAW_OESD)) {
				return DRAW_OESD;
			}
		}

		if (hit && EvalData.bothValue1 != ACE && EvalData.draw[EvalData.seat].draw(DRAW_OESD)) {
			return DRAW_OESD;
		}
		if (hit2 && EvalData.bothValue5 != TWO && EvalData.draw[EvalData.seat].draw(DRAW_OESD)) {
			return DRAW_OESD;
		}
		if (hit && EvalData.bothValue1 == ACE) {
			return DRAW_STRAIGHT;
		}
		if (hit2 && EvalData.bothValue5 == TWO) {
			return DRAW_STRAIGHT;
		}

		if (hit && EvalData.bothValue1 == ACE && EvalData.draw[EvalData.seat].draw(DRAW_STRAIGHT)) {
			return DRAW_STRAIGHT;
		}
		if (hit2 && EvalData.bothValue5 == TWO && EvalData.draw[EvalData.seat].draw(DRAW_STRAIGHT)) {
			return DRAW_STRAIGHT;
		}

		hit = false;
		// x_xxx xx_xx xxx_x
		if (EvalData.bothGap1_2 == 2 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 1) {
			hit = true;
		} else if (EvalData.bothGap1_2 == 1 && EvalData.bothGap2_4 == 2 && EvalData.bothGap3_4 == 1) {
			hit = true;
		} else if (EvalData.bothGap1_2 == 1 && EvalData.bothGap2_3 == 1 && EvalData.bothGap3_4 == 2) {
			hit = true;
		}
		if (hit && EvalData.draw[EvalData.seat].draw(DRAW_GUTSHOT)) {
			return DRAW_GUTSHOT;
		}
		return DRAW_NONE;
	}

	/*- **************************************************************************** 
	* Turn drawing hands
	*******************************************************************************/
	static int doTurnDraw() {
		int result = doFlopDraw();
		if (result >= DRAW_FLUSH) {
			return result;
		}
		boolean hitf = false;
		boolean hit = false;
		// __ssss
		hit = false;
		int suit = EvalData.bothCardsSuit3;
		if (suit == EvalData.bothCardsSuit4 && suit == EvalData.bothCardsSuit6 && suit == EvalData.bothCardsSuit6) {
			hitf = true;
		}
		if (EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1 && EvalData.bothGap5_6 == 1) {
			hit = true;
		}
		if (hitf) {
			if (EvalData.draw[EvalData.seat].draw(DRAW_FLUSH)) {
				return DRAW_FLUSH;
			} else if (result >= DRAW_OESD) {
				return result;
			} else if (EvalData.draw[EvalData.seat].draw(DRAW_OESD)) {
				return DRAW_OESD;
			}
		}
		if (hit && EvalData.bothValue6 != TWO && EvalData.draw[EvalData.seat].draw(DRAW_OESD)) {
			return DRAW_OESD;
		}
		if (result >= DRAW_STRAIGHT) {
			return result;
		}

		if (hit && EvalData.bothValue6 == TWO && EvalData.draw[EvalData.seat].draw(DRAW_STRAIGHT)) {
			return DRAW_STRAIGHT;
		}
		if (result >= DRAW_GUTSHOT) {
			return result;
		}
		// __x_xxx _xx_xx _xxx_x
		hit = false;
		if (EvalData.bothGap3_4 == 2 && EvalData.bothGap4_5 == 1 && EvalData.bothGap5_6 == 1) {
			hit = true;
		} else if (EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 2 && EvalData.bothGap5_6 == 1) {
			hit = true;
		} else if (EvalData.bothGap3_4 == 1 && EvalData.bothGap4_5 == 1 && EvalData.bothGap5_6 == 2) {
			hit = true;
		}
		if (hit && EvalData.draw[EvalData.seat].draw(DRAW_GUTSHOT)) {
			return DRAW_GUTSHOT;
		}

		return DRAW_NONE;
	}

}
