//package evaluate_streets;
/*- ******************************************************************************
 * This is a Class thar contains several helper methods.
 * What they all have in common is that they perform various calculations.
 * 
 * All of them were first generated bt ChatGPT and then edited by me to
 * be specific to definitions in this project.
 * 
 * 
 * 
 * @author PEAK_
*******************************************************************************/

public class AnalyzeHelper implements Constants {

	private AnalyzeHelper() {
		throw new IllegalStateException("Utility class");
	}

	/*-
		 * 	MDF (Minimum Defense Frequency) is a concept in poker strategy that
		 * refers to the minimum percentage of time that a player must defend
		 * their range against a particular bet in order to prevent their opponent 
		 * from profiting by betting too frequently. It can be calculated using the
		 * following formula:
		 * MDF = Pot Size / (Bet Size + Pot Size)
		 *	To calculate the MDF for a given situation, you need to know the 
		 *	size of the pot and the size of the bet that you are facing. 
		 *	Here's an example method in Java for calculating the MDF:
		 *	
		 *	This method takes in two parameters:
		 *		potSize: the current size of the pot (in chips or units)
		 *		betSize: the size of the bet that the player is facing (in chips or units)
		 *	The method then calculates the MDF using the formula above, and returns
		 * the result as a double.
		 * This value represents the minimum percentage of the player's range that
		 * hey must defend against the bet in order to prevent their opponent from
		 * profiting by betting too frequently. 
		 * If the player defends their range more often than the MDF, their opponent
		 * will be unable to profit by bluffing or betting too often, and if they defend
		 * their range less often than the MDF, their opponent will be able to profit 
		 * by bluffing or betting more often.
		 */
	public static double calculateMDFX(double potSize, double betSize) {
		return potSize / (potSize + betSize);
	}

	/*-
	 * EV (Expected Value) is a measure of how much a bet is worth
	 * over the long term. It can be calculated using the following formula:
	 *
	 * EV = (Probability of Winning * Amount Won) - (Probability of Losing * Amount Lost)
	 *	To calculate the Effective Value (EV) of a bet, you need to take into account the
	 * probabilities of each possible outcome, as well as the potential payouts and losses. 
	 * Here's an example method in Java for calculating the EV of a bet:
	 * 
	 * This method takes in four parameters:
	 *		probabilityOfWinning: the probability of winning the bet (expressed as a decimal between 0 and 1)
	 *		amountWon: the amount that will be won if the bet is successful
	 *		probabilityOfLosing: the probability of losing the bet (expressed as a decimal between 0 and 1)
	 *		amountLost: the amount that will be lost if the bet is unsuccessful
	 *	The method then calculates the expected value of the bet using the formula 
	 *	above, and returns the result as a double. This value can be used to evaluate 
	 *	the profitability of the bet over the long term.
	 */
	public static double calculateEVX(double probabilityOfWinning, double amountWon, double probabilityOfLosing,
			double amountLost) {
		return (probabilityOfWinning * amountWon) - (probabilityOfLosing * amountLost);

	}

	/*-
	 * 	To convert the number of outs to a percentage, you can use the following formula:
	 * outsPercentage = (outs / totalCards) * 100
	 *	where outs is the number of outs and totalCards is the number of cards remaining
	 * in the deck (which is typically 52 minus the number of cards that have already been dealt).
	 *  A method that takes the number of outs 
	 * as a parameter and returns the corresponding percentage:
	 *
	 * 	In this implementation, we calculate totalCards as 52
	 * (the number of cards in a standard deck) minus 7 (the number of cards that have already been 
	 * dealt in a game of Texas hold'em: 2 hole cards for the player and 5 community cards on the board).
	 *
	 *	We then use the formula above to calculate outsPercentage, ensuring that we use 
	 *	(double) totalCards to force floating point division. Finally, we return outsPercentage.
	 *
	 *	Note that this calculation assumes that each remaining card in the deck is equally
	 * likely to be drawn. In reality, this is not always the case (e.g., if some cards have
	 *already been folded or burned), but it provides a good estimate of the likelihood of
	 * hitting a particular draw.
	 */
	public static double convertOutsToPercentage(int outs) {
		final int totalCards = 52 - 7; // 52 cards in a deck, 7 cards in play (2 hole cards + 5 community cards)
		return (outs / (double) totalCards) * 100;
	}

	/*-
	 * To convert a percentage to odds, you can use the following formula:
	 *	odds = (100 / percentage) - 1
	 *	where percentage is the percentage chance of hitting your draw.
	 *	
	* 	Here's an example implementation of a method that takes the percentage 
	* as a parameter and returns the corresponding odds:
	* 
	* 	For example, if the percentage chance of hitting your draw is 20%,
	*  you can call this method with percentage = 20:
	*	double odds = convertPercentageToOdds(20);
	*	System.out.println("Odds of hitting your draw: " + odds + ":1");
	*	This will output:
	*
	*	Odds of hitting your draw: 4.0:1
	*	This means that you have a 1 in 5 chance of hitting your draw (or a 20% chance),
	*	 which is equivalent to odds of 4 to 1 against hitting your draw.
	*/
	public static double convertPercentageToOdds(double percentage) {
		return (100 / percentage) - 1;

	}

}
