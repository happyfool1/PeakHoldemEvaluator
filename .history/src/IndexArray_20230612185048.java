
//package evaluate_streets;
import java.util.Arrays;

/*-  ******************************************************************************
* This Class is a data holding Class.
*  An instance of this Class must be created for every array that is being analyzed. 
* 1. Draws  
* 2. Made Hands
* 3. Winning Showdown hands. 
* 
* There aee no public methods in this class.
* The constructor uses arguments that are arrays in IndexArrays and from these arrays 
* creates several other arrays in this class, all derived only from the original 3.
* These additional arrays are for convenience in analysis.
* Two classes are the users of this Class:
* 1. IndexArrayReport
* 2. IndexArrayAnalysis
*
* The first index int the arrays is some integer created from analyzing the board 
* or analyzing the hand ( board + hole cards )
* For example: HML is an index created by looking at the board cards and assigning 
* a value based on each cards value, high H is A - 10, Middle M is 9 -6, 
* and  low L is 5 - 2.
*
* This class is not unique to any index type or corresponding array dimensions.
* Instead it can be used to analyze any existing or yet to be imagined. 
*
* @author PEAK_
 ****************************************************************************** */

public class IndexArray implements Constants {

    IndexArray() {
    }

    /*- **************************************************************************** 
    * Constructor.
    * The 3 arguments are index arrays for draws, made hands, and showdown winning made hands.
    *
    * Make a local copy of 3 index arrays and create new arrays from these.
    * Create 3 sorted arrays, same data just sorted
    * Create 3 arrays with totals from rows.
    * Create 3 arrays with totals from columns.
    * Create 3 totals of all values in arrays.
    *
    * Relative hand value is like wet / dry. 
    *   A high relative value corresponds approximately  to a wet board. 
    *   It means that there are a lot of possible draws and possible made hands.
    
    *   A low relative value corresponds approximately  to a dry board. 
    *   It means that there are very few of possible draws and very few possible made hands.
    *
    *   A medium value corresponds approximately to board that is neutral, not wet or dry
    *
    *   Strategy suggestions are about the same as with wet / dry.
    ****************************************************************************** */
    IndexArray(int[][] drawArray, int[][] madeArray, int[][] showdownArray, String[] rowNames) {
        this.rowNames = rowNames.clone();
        this.drawArray = drawArray.clone();
        this.madeArray = madeArray.clone();
        this.showdownArray = showdownArray.clone();

        this.drawArrayPer = new double[this.drawArray.length][this.drawArray[0].length];
        this.madeArrayPer = new double[this.madeArray.length][this.madeArray[0].length];
        this.showdownArrayPer = new double[this.showdownArray.length][this.showdownArray[0].length];

        this.drawArraySortedPer = new double[this.drawArray.length][this.drawArray[0].length];
        this.madeArraySortedPer = new double[this.madeArray.length][this.madeArray[0].length];
        this.showdownArraySortedPer = new double[this.showdownArray.length][this.showdownArray[0].length];

        this.drawRowTotalPer = new double[this.drawArray.length];
        this.madeRowTotalPer = new double[this.madeArray.length];
        this.showdownRowTotalPer = new double[this.showdownArray.length];

        this.drawColTotalPer = new double[this.drawArray[0].length];
        this.madeColTotalPer = new double[this.madeArray[0].length];
        this.showdownColTotalPer = new double[this.showdownArray[0].length];

        this.drawRowTotalSortedPer = new double[this.drawArray.length];
        this.madeRowTotalSortedPer = new double[this.madeArray.length];
        this.showdownRowTotalSortedPer = new double[this.showdownArray.length];

        this.drawColTotalSortedPer = new double[this.drawArray[0].length];
        this.madeColTotalSortedPer = new double[this.madeArray[0].length];
        this.showdownColTotalSortedPer = new double[this.showdownArray[0].length];

        for (int i = 0; i < this.drawArray.length; i++) {
            for (int j = 0; j < this.drawArray[i].length; j++) {
                this.drawSumOfAllValuesPer += this.drawArray[i][j];
            }
        }
        for (int i = 0; i < this.madeArray.length; i++) {
            for (int j = 0; j < this.madeArray[i].length; j++) {
                this.madeSumOfAllValuesPer += this.madeArray[i][j];
            }
        }
        for (int i = 0; i < this.showdownArray.length; i++) {
            for (int j = 0; j < this.showdownArray[i].length; j++) {
                this.showdownSumOfAllValuesPer += this.madeArray[i][j];
            }
        }

        for (int i = 0; i < this.drawArray.length; i++) {
            for (int j = 0; j < this.drawArray[i].length; j++) {
                this.drawArrayPer[i][j] = ((double) this.drawArray[i][j]
                        / (double) this.drawSumOfAllValuesPer) * 100.0;
            }
        }
        for (int i = 0; i < this.madeArray.length; i++) {
            for (int j = 0; j < this.madeArray[i].length; j++) {
                this.madeArrayPer[i][j] = ((double) this.madeArray[i][j]
                        / (double) this.madeSumOfAllValuesPer * 100.0);
            }
        }
        for (int i = 0; i < this.showdownArray.length; i++) {
            for (int j = 0; j < this.showdownArray[i].length; j++) {
                this.showdownArrayPer[i][j] = ((double) this.showdownArray[i][j]
                        / (double) this.showdownSumOfAllValuesPer) * 100.0;
            }
        }

        this.drawArraySortedPer = this.drawArrayPer.clone();
        this.madeArraySortedPer = this.drawArrayPer.clone();
        this.showdownArraySortedPer = this.showdownArrayPer.clone();
        for (int i = 0; i < this.drawArraySortedPer.length; i++) {
            Arrays.sort(this.drawArraySortedPer[i]);
        }
        for (int i = 0; i < this.madeArraySortedPer.length; i++) {
            Arrays.sort(this.madeArraySortedPer[i]);
        }
        for (int i = 0; i < this.showdownArraySortedPer.length; i++) {
            Arrays.sort(this.showdownArraySortedPer[i]);
        }

        // Column totals
        for (int i = 0; i < this.drawArrayPer.length; i++) {
            for (int j = 0; j < this.drawArrayPer[0].length; j++) {
                this.drawColTotalPer[j] += this.drawArrayPer[i][j];
            }
        }
        for (int i = 0; i < this.madeArrayPer.length; i++) {
            for (int j = 0; j < this.madeArrayPer[0].length; j++) {
                this.madeColTotalPer[j] += this.madeArrayPer[i][j];
            }
        }
        for (int i = 0; i < this.showdownArrayPer.length; i++) {
            for (int j = 0; j < this.showdownArrayPer[0].length; j++) {
                this.showdownColTotalPer[j] += this.showdownArrayPer[i][j];
            }
        }
        // row totals
        for (int i = 0; i < this.drawArrayPer.length; i++) {
            for (int j = 0; j < this.drawArrayPer[0].length; j++) {
                this.drawRowTotalPer[i] += this.drawArrayPer[i][j];
                this.drawSumOfAllValuesPer += this.madeArrayPer[i][j];
            }
        }
        for (int i = 0; i < this.madeArrayPer.length; i++) {
            for (int j = 0; j < this.madeArrayPer[0].length; j++) {
                this.madeRowTotalPer[i] += this.madeArrayPer[i][j];
                this.madeSumOfAllValuesPer += this.madeArrayPer[i][j];
            }
        }
        for (int i = 0; i < this.showdownArrayPer.length; i++) {
            for (int j = 0; j < this.showdownArrayPer[0].length; j++) {
                showdownRowTotalPer[i] += showdownArrayPer[i][j];
                this.showdownSumOfAllValuesPer += this.madeArrayPer[i][j];
            }
        }

        this.drawRowTotalSortedPer = this.drawRowTotalPer.clone();
        this.madeRowTotalSortedPer = this.madeRowTotalPer.clone();
        this.showdownRowTotalSortedPer = this.showdownRowTotalPer.clone();
        this.drawColTotalSortedPer = this.drawColTotalPer.clone();
        this.madeColTotalSortedPer = this.madeColTotalPer.clone();
        this.showdownColTotalSortedPer = this.showdownColTotalPer.clone();

        Arrays.sort(this.drawRowTotalSortedPer);
        Arrays.sort(this.madeRowTotalSortedPer);
        Arrays.sort(this.showdownRowTotalSortedPer);
        Arrays.sort(this.drawColTotalSortedPer);
        Arrays.sort(this.madeColTotalSortedPer);
        Arrays.sort(this.showdownColTotalSortedPer);

    }

    /*- ***************************************************************************************** 
    * Evaluate row totals and column totals 
    ***************************************************************************************** */
    private void evaluateTotals() {
    }

    /*- ***************************************************************************************** 
     * Create a String describing result
     ***************************************************************************************** */
    private String stringDrscription() {
        S
        return "";
    }

    /*- ***************************************************************************************** 
     * Create a String describing strategy
    If you missed the flop: In this case, the optimal play depends on a variety of factors such as
     your position, the number of opponents, their playing styles, and the betting action. 
     You may consider bluffing if you were the pre-flop aggressor, especially if the flop is 
     likely to have missed your opponents' ranges.
    
    If you have a draw: On a neutral board, you may have a decent draw such as a gutshot straight draw
     or a flush draw. The general strategy is to continue with the hand if the pot odds justify it,
      possibly through a semi-bluff or a call.
    
    If you have a pair (top pair or otherwise): With a pair on a neutral board, you should consider 
    the strength of your pair and the potential draws available. If you have top pair with a good 
    kicker or an overpair, you should generally bet for value. If you have a weaker pair, you might 
    want to play more cautiously, possibly checking or calling rather than betting or raising.
    
    
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
    
    public class IndexArrayStrategy implements Constants {
    boolean haveNothing = true;
    boolean haveMade = true;
    boolean haveDraw = true;
    boolean haveShowdown = true;
    boolean havePair = true;
    boolean haveTopPair = true;
    boolean haveWeakerPair = true;
    boolean haveGutshotDraw = true;
    boolean haveFlushDraw = true;
    boolean haveStraightDraw = true;
    
    Range Assessment: The results of your simulation can help you assess the range of hands 
    that your opponent could have. If a particular hand combination frequently wins in your 
    simulations, you can adjust your strategy to account for the possibility that your 
    opponent may have this hand.
    
    Bluffing Decisions: The results of your simulation can inform your bluffing decisions. 
    For example, if your simulations show that you have a low chance of winning the hand 
    with your current cards, you might choose to bluff, especially if your simulation 
    also suggests that your opponent is likely to have a weak hand.
    
    Betting Decisions: The results of your simulation can guide your betting decisions. 
    If your simulation shows a high probability of winning, you may choose to bet aggressively.
    Conversely, if your simulation shows a low probability of winning, you may choose to 
    bet conservatively or fold.
    
    Opponent Modeling: By combining the results of your simulations with observations about 
    your opponents' behavior, you can model how they play and make decisions that take advantage 
    of their weaknesses. If an opponent frequently folds in response to aggressive betting, 
    for example, you might choose to bet aggressively when your simulation shows that you
    have a moderately strong hand.
    
    Adaptation: Your simulation results can help you adapt your strategy as the game progresses.
    If your simulations suggest that certain hand combinations are particularly advantageous 
    or disadvantageous in the current game context, you can adapt your strategy to prioritize 
    or avoid these combinations.
    
    
    Certainly! Building on the understanding that you'll be utilizing the results of the 
    simulation to make strategic decisions, another way to characterize the flop could 
    be by considering Strength Level and Potential Hand Range.
    
    Strength Level: This categorizes the flop based on the perceived strength of your 
    hand relative to potential hands your opponents might have.
    
    Strong: The flop indicates a strong hand, such as a made hand or a very strong draw.
    Moderate: The flop suggests a moderately strong hand or a decent draw.
    Weak: The flop indicates a weak hand or a draw with limited potential.
    Potential Hand Range: This evaluates the range of possible hands that the 
    flop can complete or improve.
    
    Narrow: The flop narrows down the potential hand range, limiting the possible 
    combinations that your opponents might hold.
    Moderate: The flop provides moderate information about the potential hand range.
    Wide: The flop doesn't significantly narrow down the potential hand range, leaving it wide open.
    
    
     ***************************************************************************************** */
    private String stringStrategy() {
        bestDrawRow = drawRowTotalSortedPer[0];
        worstDrawRow = drawRowTotalSortedPer[drawRowTotalSortedPer.length - 1];
        bestMadeRow = madeRowTotalSortedPer[0];
        worstMadeRow = madeRowTotalSortedPer[madeRowTotalSortedPer.length - 1];
        bestShowdownwRow = showdownRowTotalSortedPer[0];
        worstShowdownRow = showdownRowTotalSortedPer[madeRowTotalSortedPer.length - 1];

        bestDrawCol = drawColTotalSortedPer[0];
        worstDrawCol = drawColTotalSortedPer[drawColTotalSortedPer.length - 1];
        bestMadeCol = madeColTotalSortedPer[0];
        worstMadeCol = madeColTotalSortedPer[madeColTotalSortedPer.length - 1];
        bestShowdownwCol = showdownColTotalSortedPer[0];
        worstShowdownCol = showdownColTotalSortedPer[madeColTotalSortedPer.length - 1];

        return "";
    }

    // Simple copy of arrays
    String[] rowNames; // Names for the rows in array
    int[][] drawArray;
    int[][] madeArray;
    int[][] showdownArray;

    // Converted to percentage of all values in array
    double[][] drawArrayPer;
    double[][] madeArrayPer;
    double[][] showdownArrayPer;

    // Same data just sorted
    double[][] drawArraySortedPer;
    double[][] madeArraySortedPer;
    double[][] showdownArraySortedPer;

    // Totals of rows and/or columns for the 3 index arrays
    double[] drawRowTotalPer;
    double[] drawColTotalPer;
    double[] madeRowTotalPer;
    double[] madeColTotalPer;
    double[] showdownRowTotalPer;
    double[] showdownColTotalPer;
    double drawSumOfAllValuesPer;
    double madeSumOfAllValuesPer;
    double showdownSumOfAllValuesPer;

    // same as above but sorted
    double[] drawRowTotalSortedPer;
    double[] drawColTotalSortedPer;
    double[] madeRowTotalSortedPer;
    double[] madeColTotalSortedPer;
    double[] showdownRowTotalSortedPer;
    double[] showdownColTotalSortedPer;

    // high and low row percentages
    double bestDrawRow = -1;
    double worstDrawRow = -1;
    double bestMadeRow = -1;
    double worstMadeRow = -1;
    double bestShowdownwRow = -1;
    double worstShowdownRow = -1;
    // Column is
    double bestDrawCol = -1;
    double worstDrawCol = -1;
    double bestMadeCol = -1;
    double worstMadeCol = -1;
    double bestShowdownwCol = -1;
    double worstShowdownCol = -1;

    // String descriptions
    String st = "";

    // Suggested action if we have nothing
    // Suggested action if we have a made hand
    // Suggested action if we have a draw

    // Suggested action if we have a pair
    // Suggested action if we have a bottom pair
    // Suggested action if we have a middle pair
    // Suggested action if we have a top pair
    // Suggested action if we have a over pair
    // Suggested action if we have a board pair + under pair
    // Suggested action if we have a board pair + over pair
    // Suggested action if we have a bottom 2 pair
    // Suggested action if we have top and bottom 2 pair
    // Suggested action if we have top 2 pair
    // Suggested action if we have a set

    // Suggested action if we have a draw
    // Suggested action if we have gutshot draw
    // Suggested action if we have Straight draw
    // Suggested action if we have OESD draw
    // Suggested action if we have Flush draw

    // Sum of rows and columns

}