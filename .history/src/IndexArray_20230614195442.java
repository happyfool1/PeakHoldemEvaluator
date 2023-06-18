
//package evaluate_streets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.RowFilter;

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

    /*- ***************************************************************************************** 
    Ideas TODO
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
    If made hand and that hand wins at showdown. TODO
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
    IndexArray(int[][] drawArray, int[][] madeArray, int[][] showdownArray, String[] rowNames, String[] drawColNames,
            String[] madeColNames) {
        this.rowNames = rowNames.clone();
        this.drawArray = drawArray.clone();
        this.madeArray = madeArray.clone();
        this.showdownArray = showdownArray.clone();

        this.drawArrayPer = new double[this.drawArray.length][this.drawArray[0].length];
        this.madeArrayPer = new double[this.madeArray.length][this.madeArray[0].length];
        this.showdownArrayPer = new double[this.showdownArray.length][this.showdownArray[0].length];

        this.allArrayRowNames = rowNames.clone();
        this.drawArrayColNames = drawColNames.clone();
        this.madeArrayColNames = madeColNames.clone();

        this.drawRowTotalPer = new double[this.drawArray.length];
        this.madeRowTotalPer = new double[this.madeArray.length];
        this.showdownRowTotalPer = new double[this.showdownArray.length];

        this.drawColTotalPer = new double[this.drawArray[0].length];
        this.madeColTotalPer = new double[this.madeArray[0].length];
        this.showdownColTotalPer = new double[this.showdownArray[0].length];

        copyArrays();
        createIndexArrayList();
        createIndexArrayRowAndColumnList();
        findBestAndWorst();
        System.out.println(stringStrategy());
    }

    /*- **************************************************************************************
    * Class that holds all of an IndexArray's data.
    * percentage is the value where a row and column intersect.
    * row is the row number.
    ***************************************************************************************** */
    private class PercentageEntry {
        double percentage;
        int row;
        int column;

        private PercentageEntry(double percentage, int row, int column) {
            this.percentage = percentage;
            this.row = row;
            this.column = column;
        }

        // Getters and setters
        private double getPercentage() {
            return percentage;
        }

        private int getRow() {
            return row;
        }

        private int getColumn() {
            return column;
        }
    }

    /*- **************************************************************************************
    * Class that holds one column or one row
    * percentage is the value where a row and column intersect.
    * rowOrCol is the row number.
    ***************************************************************************************** */
    private class PairEntry {
        double percentage;
        int rowOrCol;

        private PairEntry(double percentage, int rowOrCol) {
            this.percentage = percentage;
            this.rowOrCol = rowOrCol;
        }

        // Getters and setters
        private double getPercentage() {
            return percentage;
        }

        private int getRowOrCol() {
            return rowOrCol;
        }
    }

    /*- **************************************************************************************
    * This method will create list for the 3 indexArray types.
    * Helper to constructor  
    ***************************************************************************************** */
   private  void copyArrays() {
        for (int i = 0; i < this.drawArray.length; i++) {
            for (int j = 0; j < this.drawArray[0].length; j++) {
                this.drawSumOfAllValuesPer += this.drawArray[i][j];
            }
        }
        for (int i = 0; i < this.madeArray.length; i++) {
            for (int j = 0; j < this.madeArray[0].length; j++) {
                this.madeSumOfAllValuesPer += this.madeArray[i][j];
            }
        }
        for (int i = 0; i < this.showdownArray.length; i++) {
            for (int j = 0; j < this.showdownArray[0].length; j++) {
                this.showdownSumOfAllValuesPer += this.madeArray[i][j];
            }
        }

        for (int i = 0; i < this.drawArray.length; i++) {
            for (int j = 0; j < this.drawArray[0].length; j++) {
                this.drawArrayPer[i][j] = ((double) this.drawArray[i][j]
                        / (double) this.drawSumOfAllValuesPer) * 100.0;
            }
        }
        for (int i = 0; i < this.madeArray.length; i++) {
            for (int j = 0; j < this.madeArray[0].length; j++) {
                this.madeArrayPer[i][j] = ((double) this.madeArray[i][j]
                        / (double) this.madeSumOfAllValuesPer * 100.0);
            }
        }
        for (int i = 0; i < this.showdownArray.length; i++) {
            for (int j = 0; j < this.showdownArray[0].length; j++) {
                this.showdownArrayPer[i][j] = ((double) this.showdownArray[i][j]
                        / (double) this.showdownSumOfAllValuesPer) * 100.0;
            }
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

    }

    /*- **************************************************************************************
    * This method create list for the 3 indexArray types.
    * Helper to constructor  
    ***************************************************************************************** */
   private  void createIndexArrayList() {
        for (int row = 0; row < drawArrayPer.length; row++) {
            for (int column = 0; column < drawArrayPer[row].length; column++) {
                drawEntries.add(new PercentageEntry(drawArrayPer[row][column], row, column));
            }
        }
        drawEntries.sort(Comparator.comparing(PercentageEntry::getPercentage));
        for (int row = 0; row < madeArrayPer.length; row++) {
            for (int column = 0; column < madeArrayPer[row].length; column++) {
                madeEntries.add(new PercentageEntry(madeArrayPer[row][column], row, column));
            }
        }
        madeEntries.sort(Comparator.comparing(PercentageEntry::getPercentage));
        for (int row = 0; row < showdownArrayPer.length; row++) {
            for (int column = 0; column < showdownArrayPer[row].length; column++) {
                showdownEntries.add(new PercentageEntry(showdownArrayPer[row][column], row, column));
            }
        }
        showdownEntries.sort(Comparator.comparing(PercentageEntry::getPercentage));

        // Now reversed
        for (int row = 0; row < drawArrayPer.length; row++) {
            for (int column = 0; column < drawArrayPer[row].length; column++) {
                drawEntriesReversed.add(new PercentageEntry(drawArrayPer[row][column], row, column));
            }
        }
        drawEntriesReversed.sort(Comparator.comparing(PercentageEntry::getPercentage).reversed());

        for (int row = 0; row < drawArrayPer.length; row++) {
            for (int column = 0; column < drawArrayPer[row].length; column++) {
                madeEntriesReversed.add(new PercentageEntry(drawArrayPer[row][column], row, column));
            }
        }
        madeEntriesReversed.sort(Comparator.comparing(PercentageEntry::getPercentage).reversed());

        for (int row = 0; row < madeArrayPer.length; row++) {
            for (int column = 0; column < madeArrayPer[row].length; column++) {
                madeEntriesReversed.add(new PercentageEntry(madeArrayPer[row][column], row, column));
            }
        }
        showdownEntriesReversed.sort(Comparator.comparing(PercentageEntry::getPercentage).reversed());
        for (int row = 0; row < showdownArrayPer.length; row++) {
            for (int column = 0; column < showdownArrayPer[row].length; column++) {
                showdownEntriesReversed.add(new PercentageEntry(showdownArrayPer[row][column], row, column));
            }
        }
        showdownEntriesReversed.sort(Comparator.comparing(PercentageEntry::getPercentage).reversed());
    }

    /*- **************************************************************************************
     * This method create list for the 3 indexArray types fot the rows and columns that
     * have thr row or column totals.
     * Helper to constructor  
     ***************************************************************************************** */
    private void createIndexArrayRowAndColumnList() {
        for (int row = 0; row < drawRowTotalPer.length; row++) {
            drawTotalRowPerEntries.add(new PairEntry(drawRowTotalPer[row], row));
        }
        drawTotalRowPerEntries.sort(Comparator.comparing(PairEntry::getPercentage));

        for (int row = 0; row < madeRowTotalPer.length; row++) {
            madeTotalRowPerEntries.add(new PairEntry(madeRowTotalPer[row], row));
        }
        madeTotalRowPerEntries.sort(Comparator.comparing(PairEntry::getPercentage));

        for (int row = 0; row < showdownRowTotalPer.length; row++) {
            showdownTotalRowPerEntries.add(new PairEntry(showdownRowTotalPer[row], row));
        }
        showdownTotalRowPerEntries.sort(Comparator.comparing(PairEntry::getPercentage));

        for (int col = 0; col < drawColTotalPer.length; col++) {
            drawTotalColPerEntries.add(new PairEntry(drawColTotalPer[col], col));
        }
        drawTotalColPerEntries.sort(Comparator.comparing(PairEntry::getPercentage));
        for (int col = 0; col < madeColTotalPer.length; col++) {
            madeTotalColPerEntries.add(new PairEntry(madeColTotalPer[col], col));
        }
        madeTotalColPerEntries.sort(Comparator.comparing(PairEntry::getPercentage));

        for (int col = 0; col < showdownColTotalPer.length; col++) {
            showdownTotalColPerEntries.add(new PairEntry(showdownColTotalPer[col], col));
        }
        showdownTotalColPerEntries.sort(Comparator.comparing(PairEntry::getPercentage));

        // Now reversed
        for (int row = 0; row < drawRowTotalPer.length; row++) {
            drawTotalRowPerEntriesReversed.add(new PairEntry(drawRowTotalPer[row], row));
        }
        drawTotalRowPerEntriesReversed.sort(Comparator.comparing(PairEntry::getPercentage).reversed());
        for (int row = 0; row < madeRowTotalPer.length; row++) {
            madeTotalRowPerEntriesReversed.add(new PairEntry(madeRowTotalPer[row], row));
        }
        madeTotalRowPerEntriesReversed.sort(Comparator.comparing(PairEntry::getPercentage).reversed());
        for (int row = 0; row < showdownRowTotalPer.length; row++) {
            showdownTotalRowPerEntriesReversed.add(new PairEntry(showdownRowTotalPer[row], row));
        }
        showdownTotalRowPerEntriesReversed.sort(Comparator.comparing(PairEntry::getPercentage).reversed());
        for (int col = 0; col < drawColTotalPer.length; col++) {
            drawTotalColPerEntriesReversed.add(new PairEntry(drawColTotalPer[col], col));
        }
        drawTotalColPerEntriesReversed.sort(Comparator.comparing(PairEntry::getPercentage).reversed());

        for (int col = 0; col < madeColTotalPer.length; col++) {
            madeTotalColPerEntriesReversed.add(new PairEntry(madeColTotalPer[col], col));
        }
        madeTotalColPerEntriesReversed.sort(Comparator.comparing(PairEntry::getPercentage).reversed());
        for (int col = 0; col < showdownColTotalPer.length; col++) {
            showdownTotalColPerEntriesReversed.add(new PairEntry(showdownColTotalPer[col], col));
        }
        showdownTotalColPerEntriesReversed.sort(Comparator.comparing(PairEntry::getPercentage).reversed());
    }

    /*- **************************************************************************************
    * This method will find the best and worst percentages
    * The 3 best and the 3 worst are found
    * Helper to constructor 
    ***************************************************************************************** */
   private void findBestAndWorst() {
        bestDrawRows[0] = drawTotalRowPerEntries.get(drawTotalRowPerEntries.size() - 1).getRowOrCol();
        worstDrawRows[0] = drawTotalRowPerEntries.get(0).getRowOrCol();
        bestMadeRows[0] = madeTotalRowPerEntries.get(madeTotalRowPerEntries.size() - 1).getRowOrCol();
        worstMadeRows[0] = madeTotalRowPerEntries.get(0).getRowOrCol();
        bestShowdownwRows[0] = showdownTotalRowPerEntries.get(showdownTotalRowPerEntries.size() - 1).getRowOrCol();
        worstShowdownRows[0] = showdownTotalRowPerEntries.get(0).getRowOrCol();
        bestDrawCols[0] = drawTotalColPerEntries.get(drawTotalColPerEntries.size() - 1).getRowOrCol();
        worstDrawCols[0] = drawTotalColPerEntries.get(0).getRowOrCol();
        bestMadeCols[0] = madeTotalColPerEntries.get(madeTotalColPerEntries.size() - 1).getRowOrCol();
        worstMadeCols[0] = madeTotalColPerEntries.get(0).getRowOrCol();
        bestShowdownwCols[0] = showdownTotalColPerEntries.get(showdownTotalColPerEntries.size() - 1).getRowOrCol();
        worstShowdownCols[0] = showdownTotalColPerEntries.get(0).getRowOrCol();

        bestDrawRows[1] = drawTotalRowPerEntries.get(drawTotalRowPerEntries.size() - 2).getRowOrCol();
        worstDrawRows[1] = drawTotalRowPerEntries.get(1).getRowOrCol();
        bestMadeRows[1] = madeTotalRowPerEntries.get(madeTotalRowPerEntries.size() - 2).getRowOrCol();
        worstMadeRows[1] = madeTotalRowPerEntries.get(1).getRowOrCol();
        bestShowdownwRows[1] = showdownTotalRowPerEntries.get(showdownTotalRowPerEntries.size() - 2).getRowOrCol();
        worstShowdownRows[1] = showdownTotalRowPerEntries.get(1).getRowOrCol();
        bestDrawCols[1] = drawTotalColPerEntries.get(drawTotalColPerEntries.size() - 2).getRowOrCol();
        worstDrawCols[1] = drawTotalColPerEntries.get(1).getRowOrCol();
        bestMadeCols[1] = madeTotalColPerEntries.get(madeTotalColPerEntries.size() - 2).getRowOrCol();
        worstMadeCols[1] = madeTotalColPerEntries.get(1).getRowOrCol();
        bestShowdownwCols[1] = showdownTotalColPerEntries.get(showdownTotalColPerEntries.size() - 2).getRowOrCol();
        worstShowdownCols[1] = showdownTotalColPerEntries.get(1).getRowOrCol();

        bestDrawRows[2] = drawTotalRowPerEntries.get(drawTotalRowPerEntries.size() - 3).getRowOrCol();
        worstDrawRows[2] = drawTotalRowPerEntries.get(2).getRowOrCol();
        bestMadeRows[2] = madeTotalRowPerEntries.get(madeTotalRowPerEntries.size() - 3).getRowOrCol();
        worstMadeRows[2] = madeTotalRowPerEntries.get(2).getRowOrCol();
        bestShowdownwRows[2] = showdownTotalRowPerEntries.get(showdownTotalRowPerEntries.size() - 3).getRowOrCol();
        worstShowdownRows[2] = showdownTotalRowPerEntries.get(2).getRowOrCol();
        bestDrawCols[2] = drawTotalColPerEntries.get(drawTotalColPerEntries.size() - 3).getRowOrCol();
        worstDrawCols[2] = drawTotalColPerEntries.get(2).getRowOrCol();
        bestMadeCols[2] = madeTotalColPerEntries.get(madeTotalColPerEntries.size() - 3).getRowOrCol();
        worstMadeCols[2] = madeTotalColPerEntries.get(2).getRowOrCol();
        bestShowdownwCols[2] = showdownTotalColPerEntries.get(showdownTotalColPerEntries.size() - 3).getRowOrCol();
        worstShowdownCols[2] = showdownTotalColPerEntries.get(2).getRowOrCol();
    }

    /*- **************************************************************************************
    * This method will find the best and worst percentages
    * The 5 best and the 5 worst are found.
    * Looks at complete array, not single roows or columns.
    * Helper to constructor 
    ***************************************************************************************** */
   private void findBestAndWorstOverall() {
 for (int i = 0; i < 5;)

      bestDrawTop5Row[0] = drawTotalRowPerEntries.get(drawTotalRowPerEntries.size() - 1).getRowOrCol();
        worstDrawRows[0] = drawTotalRowPerEntries.get(0).getRowOrCol();
    }

  
    /*- **************************************************************************************
    * This method will return smallest percentage entry
    ***************************************************************************************** */
    private PercentageEntry getSmallest(List<PercentageEntry> entries) {
        if (entries.isEmpty()) {
            return null; // or throw an exception, depending on your preference
        }
        entries.sort(Comparator.comparing(PercentageEntry::getPercentage));
        return entries.get(0);
    }

    /*- **************************************************************************************
    * This method will return largest percentage entry
    ***************************************************************************************** */
   private PercentageEntry getLargest(List<PercentageEntry> entries) {
        if (entries.isEmpty()) {
            return null; // or throw an exception, depending on your preference
        }
        entries.sort(Comparator.comparing(PercentageEntry::getPercentage));
        return entries.get(entries.size() - 1);
    }

    /*- **************************************************************************************
    * This method searches the 3 indexArray types.
    * 
    ***************************************************************************************** */
   private void searchIndexArrayList() {
        double targetPercentage = 0.5; // Replace with the percentage you're searching for
        List<PercentageEntry> matchingEntries = drawEntries.stream()
                .filter(entry -> Math.abs(entry.getPercentage() - targetPercentage) < 0.0001)
                .collect(Collectors.toList());

        List<PairEntry> matchingEntriesX = drawTotalRowPerEntries.stream()
                .filter(entry -> Math.abs(entry.getPercentage() - targetPercentage) < 0.0001)
                .collect(Collectors.toList());
    }

    /*- **************************************************************************************
    * This method will get entries in sort order
    *  
    ***************************************************************************************** */
    private void indexArrayListInSortedOrder() {
        for (PairEntry entry : drawTotalRowPerEntries) {
            int r = entry.getRowOrCol();
            System.out.println(
                    "Percentage: " + entry.getPercentage() + ", Row: " + RANGE_BET4 + " " + allArrayRowNames[r]);
        }
    }

    /*- **************************************************************************************
    * Get percentage for row and column
    *  
    ***************************************************************************************** */
    private double getPercentageByRowAndColumn(int targetRow, int targetColumn) {
        for (PercentageEntry entry : drawEntries) {
            if (entry.getRow() == targetRow && entry.getColumn() == targetColumn) {
                return entry.getPercentage();
            }
        }
        throw new IllegalArgumentException("Invalid row and/or column.");
    }

    /*- **************************************************************************************
    * Evaluate row totals and column totals 
    ***************************************************************************************** */
    private void evaluateTotals() {
        // TODO
    }

    /*- ***************************************************************************************** 
     Create a String describing strategy
    ***************************************************************************************** */
    private String stringStrategy() {
        String st = "The bestDraw rows were " + allArrayRowNames[bestDrawRows[0]] + ", "
                + allArrayRowNames[bestDrawRows[1]]
                + ", " + allArrayRowNames[bestDrawRows[2]] + "\r\n";
        st += "The worstDraw rows were " + allArrayRowNames[worstDrawRows[0]] + ", "
                + allArrayRowNames[worstDrawRows[1]]
                + ", " + allArrayRowNames[worstDrawRows[2]] + "\r\n";
        st += "The bestMade rows were " + allArrayRowNames[bestMadeRows[0]] + ", " + allArrayRowNames[bestMadeRows[1]]
                + ", " + allArrayRowNames[bestMadeRows[2]] + "\r\n";
        st += "The worstMade rows were " + allArrayRowNames[worstMadeRows[0]] + ", "
                + allArrayRowNames[worstMadeRows[1]]
                + ", " + allArrayRowNames[worstMadeRows[2]] + "\r\n";
        st += "The bestShowdownw rows were " + allArrayRowNames[bestShowdownwRows[0]] + ", "
                + allArrayRowNames[bestShowdownwRows[1]]
                + ", " + allArrayRowNames[bestShowdownwRows[2]] + "\r\n";
        st += "The worstShowdown rows were " + allArrayRowNames[worstShowdownRows[0]] + ", "
                + allArrayRowNames[worstShowdownRows[1]]
                + ", " + allArrayRowNames[worstShowdownRows[2]] + "\r\n";

        st += "The bestDraw cols were " + drawArrayColNames[bestDrawCols[0]] + ", " + drawArrayColNames[bestDrawCols[1]]
                + ", " + drawArrayColNames[bestDrawCols[2]] + "\r\n";
        st += "The worstDraw cols were " + drawArrayColNames[worstDrawCols[0]] + ", "
                + drawArrayColNames[worstDrawCols[1]]
                + ", " + drawArrayColNames[worstDrawCols[2]] + "\r\n";
        st += "The bestMade cols were " + madeArrayColNames[bestMadeCols[0]] + ", " + madeArrayColNames[bestMadeCols[1]]
                + ", " + madeArrayColNames[bestMadeCols[2]] + "\r\n";
        st += "The worstMade cols were " + madeArrayColNames[worstMadeCols[0]] + ", "
                + madeArrayColNames[worstMadeCols[1]]
                + ", " + madeArrayColNames[worstMadeCols[2]] + "\r\n";
        st += "The bestShowdownw cols were " + madeArrayColNames[bestShowdownwCols[0]] + ", "
                + madeArrayColNames[bestShowdownwCols[1]]
                + ", " + madeArrayColNames[bestShowdownwCols[2]] + "\r\n";
        st += "The worstShowdown cols were " + madeArrayColNames[worstShowdownCols[0]]
                + ", " + madeArrayColNames[worstShowdownCols[1]]
                + ", " + madeArrayColNames[worstShowdownCols[2]] + "\r\n";
        st += "The madeSumOfAllValuesPer was " + drawSumOfAllValuesPer + "\r\n";
        st += "The madeSumOfAllValuesPer was " + madeSumOfAllValuesPer + "\r\n";
        st += "The showdownSumOfAllValuesPer was " + showdownSumOfAllValuesPer + "\r\n";
        return st;

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

    // Array row and column names
    String[] allArrayRowNames;
    String[] drawArrayColNames;
    String[] madeArrayColNames;

    // Sorted list for each array and reversed
    List<PercentageEntry> drawEntries = new ArrayList<>();
    List<PercentageEntry> madeEntries = new ArrayList<>();
    List<PercentageEntry> showdownEntries = new ArrayList<>();
    List<PercentageEntry> drawEntriesReversed = new ArrayList<>();
    List<PercentageEntry> madeEntriesReversed = new ArrayList<>();
    List<PercentageEntry> showdownEntriesReversed = new ArrayList<>();

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

    // Sorted list for each array and reversed
    List<PairEntry> drawTotalRowPerEntries = new ArrayList<>();
    List<PairEntry> madeTotalRowPerEntries = new ArrayList<>();
    List<PairEntry> showdownTotalRowPerEntries = new ArrayList<>();
    List<PairEntry> drawTotalColPerEntries = new ArrayList<>();
    List<PairEntry> madeTotalColPerEntries = new ArrayList<>();
    List<PairEntry> showdownTotalColPerEntries = new ArrayList<>();
    // And reversed
    // Sorted list for each array and reversed
    List<PairEntry> drawTotalRowPerEntriesReversed = new ArrayList<>();
    List<PairEntry> madeTotalRowPerEntriesReversed = new ArrayList<>();
    List<PairEntry> showdownTotalRowPerEntriesReversed = new ArrayList<>();
    List<PairEntry> drawTotalColPerEntriesReversed = new ArrayList<>();
    List<PairEntry> madeTotalColPerEntriesReversed = new ArrayList<>();
    List<PairEntry> showdownTotalColPerEntriesReversed = new ArrayList<>();

    // top 3 high and low row percentages
    int[] bestDrawRows = new int[3];
    int[] worstDrawRows = new int[3];
    int[] bestMadeRows = new int[3];
    int[] worstMadeRows = new int[3];
    int[] bestShowdownwRows = new int[3];
    int[] worstShowdownRows = new int[3];
    // top 3 high and low col percentages
    int[] bestDrawCols = new int[3];
    int[] worstDrawCols = new int[3];
    int[] bestMadeCols = new int[3];
    int[] worstMadeCols = new int[3];
    int[] bestShowdownwCols = new int[3];
    int[] worstShowdownCols = new int[3];

    // Percentage value
    int[] bestDrawRowsPer = new int[3];
    int[] worstDrawRowsPer = new int[3];
    int[] bestMadeRowsPer = new int[3];
    int[] worstMadeRowsPer = new int[3];
    int[] bestShowdownwRowsPer = new int[3];
    int[] worstShowdownRowsPer = new int[3];
    // Percentage Value
    int[] bestDrawColsPer = new int[3];
    int[] worstDrawColsPer = new int[3];
    int[] bestMadeColsPer = new int[3];
    int[] worstMadeColsPer = new int[3];
    int[] bestShowdownwColsPer = new int[3];
    int[] worstShowdownColsPer = new int[3];

    // Top 5 in entire array row and column
    int[] bestDrawTop5Row = new int[5];
    int[] bestDrawTop5Col = new int[5];
    double[] bestDrawTop5Per = new double[5];
    int[] bestMadeTop5Row = new int[5];
    int[] bestMadeTop5Col = new int[5];
    double[] bestMadeTop5Per = new double[5];
    int[] bestShowdownTop5Row = new int[5];
    int[] bestShowdownTop5Col = new int[5];
    double[] bestShowdownTop5Per = new double[5];

    // Bottom 5 in entire array row and column
    int[] worstDrawBottom5Row = new int[5];
    int[] worstDrawBottom5Col = new int[5];
    double[] worstDrawBottom5Per = new double[5];
    int[] worstMadeBottom5Row = new int[5];
    int[] worstMadeBottom5Col = new int[5];
    double[] worstMadeBottom5Per = new double[5];
    int[] worstShowdownBottom5Row = new int[5];
    int[] worstShowdownBottom5Col = new int[5];
    double[] worstShowdownBottom5Per = new double[5];


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