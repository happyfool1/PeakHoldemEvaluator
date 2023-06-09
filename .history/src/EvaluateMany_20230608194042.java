//package evaluate_streets;

/*-  ******************************************************************************
 * This Class is evaluates hands, boards, hand strength, made hands, possible draws, Showdown 

* and many other things.
* 
* It is currently being modified to better support both GTO, ABC play, and exploitation.
* Also new ideas such as HML analysis.
* 
* Public methods - In the order that they should normally be called
*
* Most of the methods are private and called as a result of calling one of the public methods.
* 
* The Flop board is evaluated first. Results are placed in boardArray.
* The Flop board is evaluated for many basic things like rainbow, paired, suited, gaps, etc..
* 
* Then the hole cards are evaluated for one player, in seatNumber. 
*  
* 
* @author PEAK_
 ****************************************************************************** */
import java.math.BigDecimal;
import java.math.RoundingMode;

public class EvaluateMany implements Constants {

	private EvaluateMany() {
		throw new IllegalStateException("Utility class");
	}

	/*-  ******************************************************************************
	 * Update Flop data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateFlop() {
		++ManyData.hmlCount;

		++ManyData.typeCount;
		++ManyData.wetDryCount;
		++ManyData.type1755Count;
		++ManyData.flopIndexCount;
		++ManyData.handValueCount;
		++ManyData.sumOfDrawCount;
		++ManyData.sumOfMadeCount;
		++ManyData.sumOfShowdownCount;
		++ManyData.boardArrayFlopCount;
		++ManyData.boardArrayTurnCount;
		++ManyData.boardArrayRiverCount;
		++ManyData.flopArraysCount;

		int index = EvalData.hmlIndexFlop;

		for (int i = DRAW_HANDS_SIZE - 1; i >= 0; i--) {
			if (EvalData.drawArray[i]) {
				ManyData.hmlDraw[index][i]++;
				break;
			}
		}
		for (int i = MADE_HANDS_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				ManyData.hmlMade[index][i]++;
				break;
			}
		}

		++ManyData.flopArraysCount;
		++ManyData.boardArrayFlopCount;
				

			}

	/*-  ******************************************************************************
	 * Update Turn data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateTurn() {
		++ManyData.hhmlCount;
		++ManyData.turnArraysCount;

		int index = EvalData.hmlIndexTurn;
		for (int i = DRAW_HANDS_SIZE - 1; i >= 0; i--) {
			if (EvalData.drawArray[i]) {
				++ManyData.hhmlDraw[index][i];
				break;
			}
		}
		for (int i = MADE_HANDS_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				++ManyData.hhmlMade[index][i];
				break;
			}
		}

			
	}

	/*-  ******************************************************************************
	 * Update River data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateRiver() {
		++ManyData.hhhmlCount;
		++ManyData.riverArraysCount;

		int index = EvalData.hmlIndexRiver;
		for (int i = MADE_HANDS_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				++ManyData.hhhmlMade[index][i];
				break;
			}
		}

			}

	/*-  ******************************************************************************
	 * Update Showdown data in ManyData  
	 ****************************************************************************** */
	static void updateShowdown() {
		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i] && EvalData.playerWonShowdown[i]) {
				++ManyData.hmlShowdownWon[EvalData.hmlIndexFlop][EvalData.showdownHand[i]];
				++ManyData.hhmlShowdownWon[EvalData.hmlIndexTurn][EvalData.showdownHand[i]];
				++ManyData.hhhmlShowdownWon[EvalData.hmlIndexRiver][EvalData.showdownHand[i]];
				++ManyData.showdownCount;
			}
		}
	}

	

	/*-  ******************************************************************************
	 * Initialize data in ManyData prior to calculating 
	 ****************************************************************************** */
	static void initializeManyData() {

		ManyData.flopArraysCount = 0;
		ManyData.turnArraysCount = 0;
		ManyData.riverArraysCount = 0;

		for (int i = 0; i < BOARD_ARRAY_SIZE; i++) {
			ManyData.boardArrayTurn[i] = 0;
			ManyData.boardArrayRiver[i] = 0;
		}
		ManyData.boardArrayFlopCount = 0;
		ManyData.boardArrayTurnCount = 0;
		ManyData.boardArrayRiverCount = 0;

		for (int i = 0; i < POSSIBLE_SIZE; i++) {
			ManyData.boardPossibleArrayFlop[i] = 0;
			ManyData.boardPossibleArrayTurn[i] = 0;
		}

		for (int i = 0; i < HML_FLOP_SIZE; i++) {
			ManyData.hmlArrayFlop[i] = 0;
		}

		for (int i = 0; i < 1755; i++) {
			ManyData.flopTypeOf1755Array[i] = 0;
		}

		for (int i = 0; i < 4; i++) {
			ManyData.typeArray[i] = 0;
		}

		for (int i = 0; i < HML_FLOP_SIZE; i++) {

		}

	}

	/*-  ******************************************************************************
	 * Convert a boardPossibleArrayFlop  index into a drawAndMadeArrayMadeHandsFlop
	 *  Example  MIDDLE_PAIR to HAND_MIDDLE_PAIR
	 *  Arg0 - Value to convert
	 *  Returns new converted value, -1 if invalid value
	 ****************************************************************************** */
	static int possibleValueToMadeHandValue(int value) {
		return switch (value) {

			// TODO case POSSIBLE_PAIR -> MADE_PAIR;
			// TODO case POSSIBLE_TWO_PAIR -> MADE_TWO_PAIR;
			case POSSIBLE_SET -> MADE_SET;
			case POSSIBLE_STRAIGHT -> MADE_STRAIGHT;
			case POSSIBLE_FLUSH -> MADE_FLUSH;
			case POSSIBLE_FULL_HOUSE -> MADE_FULL_HOUSE;
			case POSSIBLE_FOUR_OF_A_KIND -> MADE_FOUR_OF_A_KIND;
			case POSSIBLE_STRAIGHT_FLUSH -> MADE_FOUR_OF_A_KIND;
			case POSSIBLE_ROYAL_FLUSH -> MADE_ROYAL_FLUSH;
			default -> -1;
		};
	}

	/*-
	* Takes two arguments: an array of integer values and a count. 
	* Returns an array of percentages.
	* 
	* It creates a new array percentages with the same length as values, and then calculates
	* the percentage for each value using the same formula as before. 
	* The percentage is then rounded to two decimal places using the BigDecimal class, 
	* and added to the percentages array. Finally, the method returns the percentages array.
	*
	* Arg0 - An integer array of values
	* Arg1 - An integer count
	* Returns an array of percentages      
	 */
	private static double[] calculatePercentagesArrays(int[] values, int count) {
		final double[] percentages = new double[values.length];
		if (count == 0) {
			Logger.log("//calculatePercentagesArrays error count is 0");
		} else {
			for (int i = 0; i < values.length; i++) {
				final double percentage = ((double) values[i] / count) * 100;
				var bd = new BigDecimal(Double.toString(percentage));
				bd = bd.setScale(2, RoundingMode.HALF_UP);
				percentages[i] = bd.doubleValue();
			}
		}
		return percentages;
	}

	/*-  
	 *  Calculate percentage
	 *  Arg0 - Value
	 *  Arg1 - Count
	 *  Returns percentage as double rounded to 2 places     
	*/
	private static double calculatePercentage(int value, int count) {
		if (count == 0) {
			return -1.;
		}
		final double percentage = ((double) value / count) * 100;
		var bd = new BigDecimal(Double.toString(percentage));
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/*-
	* Convert a percentage to odds
	* 
	* Arg0 - Percentage
	* Returns odds as a double
	*/
	private static double[] convertToOddsX(double percentage) {
		final double decimalOdds = (100.0 - percentage) / percentage;
		final double[] odds = new double[2];
		if (decimalOdds >= 1.0) {
			odds[0] = decimalOdds;
			odds[1] = 1.0;
		} else {
			odds[0] = 1.0;
			odds[1] = 1.0 / decimalOdds;
		}
		return odds;
	}

}
