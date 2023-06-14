//package evaluate_streets;

/*-  ******************************************************************************
 * This Class simply updates the arrays in the Class ManyData.
 * It is called after each player completes a street, at showdown, and after all hands 
 * have been played. 
* 
* The public methods are:
* 1. updateFlop()
* 2. updateTurn()
* 3. updateRiver()
* 4. updateShowdowns()
* 5. updateSummary()
*
* The first 4 update arrays using several indexes and and values from:
* EvalData.drawArray, EvalData,madeArray, EvalData.showdownHand.
*
* summary performs some simple calculations such as adding all of the rows in an array.

Sure, the following Java function takes as input a Monte Carlo simulation result, represented as a 
probability of winning, and returns a simple recommended action (CHECK, CALL, RAISE, or FOLD).
Note that this method oversimplifies the strategic nuances of poker and doesn't consider the current pot size, the bet to you, the number of players left to act, your stack size, or opponent tendencies, but it can serve as a basic guideline.

java
Copy code
public class MonteCarloPokerStrategy {
    
    public enum Action {
        CHECK, CALL, RAISE, FOLD
    }

    public static void main(String[] args) {
        // testing
        System.out.println(recommendAction(0.8)); // returns RAISE
    }

    public static Action recommendAction(double winProbability) {
        if (winProbability > 0.75) {
            return Action.RAISE;
        } else if (winProbability > 0.5) {
            return Action.CALL;
        } else if (winProbability > 0.25) {
            return Action.CHECK;
        } else {
            return Action.FOLD;
        }
    }
}
This function interprets the winning probability in a straightforward way:

If the winning probability is over 75%, it recommends raising, since your hand is strong.
If the winning probability is between 50% and 75%, it recommends calling, as your hand is 
likely competitive but not dominant.
If the winning probability is between 25% and 50%, it recommends checking, since your hand
 might have potential but is currently weak.
If the winning probability is below 25%, it recommends folding, as your hand is likely 
not worth investing more chips.
Again, this is a simplistic interpretation and should not be the sole determinant of your 
poker strategy. Real game situations require a nuanced understanding of many additional factors.

Calculating winning probability based on flop draws and made:
	If board is dry and hand or draw then probability good.
	If board is dry and no hand or draw then probability poor.
	If board is wet and hand or draw then probability good for draw, fair for hand.
	If board is wet and no hand or draw then probability poor.
	if board is neutral and hand or draw then probability poor for draw, good for hand.
	if board is neutral and no hand or draw then probability poor.

Calculating winning probability based on flop and showdown winning hand:
    If made hand and that hand wind at showdown. TODO
	Need an array that relates hand on flop to win at showdown combined with index.
	So HML on flop, made and and showdown for that made hand
	int [][][] wtf = new int [][][] wtf = new int [DRAW_SIZE][MADE_SIZE][winLoose];



*  
* 
* @author PEAK_
 ****************************************************************************** */

public class IndexArrayUpdate implements Constants {

	private IndexArrayUpdate() {
		throw new IllegalStateException("Utility class");
	}

	/*-  ****************************************************************************
	 * Update Flop data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateFlop() {
		++IndexArrays.hmlCountFlop;
		++IndexArrays.typeCount;
		++IndexArrays.wetDryCount;
		++IndexArrays.type1755Count;
		++IndexArrays.flopIndexCount;
		++IndexArrays.handValueCount;
		++IndexArrays.sumOfDrawCount;
		++IndexArrays.sumOfMadeCount;
		++IndexArrays.sumOfShowdownCount;
		++IndexArrays.boardArrayFlopCount;
		++IndexArrays.boardArrayTurnCount;
		++IndexArrays.boardArrayRiverCount;
		++IndexArrays.flopArraysCount;

		int index = EvalData.hmlIndexFlop;

		for (int i = DRAW_SIZE - 1; i >= 0; i--) {
			if (EvalData.drawArray[i]) {
				IndexArrays.hmlDrawFlop[index][i]++;
				break;
			}
		}
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				IndexArrays.hmlMadeFlop[index][i]++;
				break;
			}
		}

		

		++IndexArrays.flopArraysCount;
		++IndexArrays.boardArrayFlopCount;
	}

	/*-  ******************************************************************************
	 * Update Turn data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateTurn() {
		++IndexArrays.hmlCountTurn;
		++IndexArrays.turnArraysCount;

		int index = EvalData.hmlIndexTurn;
		for (int i = DRAW_SIZE - 1; i >= 0; i--) {
			if (EvalData.drawArray[i]) {
				++IndexArrays.hmlDrawTurn[index][i];
				break;
			}
		}
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				++IndexArrays.hmlMadeTurn[index][i];
				break;
			}
		}
	}

	/*-  ******************************************************************************
	 * Update River data in ManyData every hand is processed
	 ****************************************************************************** */
	static void updateRiver() {
		++IndexArrays.hmlCountRiver;
		++IndexArrays.riverArraysCount;

		int index = EvalData.hmlIndexRiver;
		for (int i = MADE_SIZE - 1; i >= 0; i--) {
			if (EvalData.madeArray[i]) {
				++IndexArrays.hmlMadeRiver[index][i];
				break;
			}
		}

	}

	/*-  ****************************************************************************
	 * Update Showdown data in ManyData  
	 ****************************************************************************** */
	static void updateShowdown() {
		for (int i = 0; i < PLAYERS; i++) {
			if (!EvalData.playerFolded[i] && EvalData.playerWonShowdown[i]) {
				if (EvalData.hmlIndexFlop != -1) {
					++IndexArrays.hmlShowdownFlop[EvalData.hmlIndexFlop][EvalData.showdownHand[i]];
					if (EvalData.showdownHand[i] == 0){ // TODO
					++IndexArrays.hmlShowdownMadeWinsFlop[EvalData.hmlIndexFlop][EvalData.showdownHand[i]];
				}

				}
				if (EvalData.hmlIndexTurn != -1) {
					++IndexArrays.hmlShowdownTurn[EvalData.hmlIndexTurn][EvalData.showdownHand[i]];
					++IndexArrays.hmlShowdownMadeWinsTurn[EvalData.hmlIndexTurn][EvalData.showdownHand[i]];
				}
				if (EvalData.hmlIndexRiver != -1) {
					++IndexArrays.hmlShowdownRiver[EvalData.hmlIndexRiver][EvalData.showdownHand[i]];
					++IndexArrays.hmlShowdownMadeWinsRiver[EvalData.hmlIndexRiver][EvalData.showdownHand[i]];
				}
				++IndexArrays.showdownCount;
			}
		}
	}

	/*-  ****************************************************************************
	 * Update Summary data in ManyData  
	****************************************************************************** */
	static void updateSummary() {

	}

	/*-  ******************************************************************************
	 * Initialize data in ManyData prior to calculating 
	 ****************************************************************************** */
	static void initializeManyData() {
		IndexArrays.flopArraysCount = 0;
		IndexArrays.turnArraysCount = 0;
		IndexArrays.riverArraysCount = 0;
	}

	

}
