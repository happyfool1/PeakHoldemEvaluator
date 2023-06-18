
//package evaluate_streets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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

        private int getCol() {
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
    private void copyArrays() {
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
    private void createIndexArrayList() {
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
 int count = 0;
        int ndx = 0;
        double p;
        for (int i = 0; i < drawTotalRowPerEntriesReversed.size(); i++) {
            p = drawTotalRowPerEntriesReversed.get(ndx).getPercentage();
            if (p != 0.) {
                bestDrawRowsPer[count] = p;
                 bestDrawRows[count] = drawTotalRowPerEntriesReversed.get(ndx).getRowOrCol();
          
                ++ndx;
                if (++count >= 5) {
                    break;
                }
            }
        }

        count = 0;
        ndx = 0;
        for (int i = 0; i < drawEntries.size(); i++) {
            p = drawEntries.get(ndx).getPercentage();
            if (p != 0.) {
                worstDraw5Per[count] = p;
                worstDraw5Row[count] = drawEntries.get(ndx).getRow();
                worstDraw5Col[count] = drawEntries.get(ndx).getCol();
                ++ndx;
                if (++count >= 5) {
                    break;
                }
            }
        }

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
    }

       

    /*- **************************************************************************************
    * This method will find the best and worst percentages
    * The 5 best and the 5 worst are found.
    * Looks at complete array, not single roows or columns.
    * Helper to constructor 
    ***************************************************************************************** */
    private void findBestAndWorstOverall() {
        int count = 0;
        int ndx = 0;
        double p;
        for (int i = 0; i < drawEntriesReversed.size(); i++) {
            p = drawEntriesReversed.get(ndx).getPercentage();
            if (p != 0.) {
                bestDraw5Per[count] = p;
                bestDraw5Row[count] = drawEntriesReversed.get(ndx).getRow();
                bestDraw5Col[count] = drawEntriesReversed.get(ndx).getCol();
                ++ndx;
                if (++count >= 5) {
                    break;
                }
            }
        }

        count = 0;
        ndx = 0;
        for (int i = 0; i < drawEntries.size(); i++) {
            p = drawEntries.get(ndx).getPercentage();
            if (p != 0.) {
                worstDraw5Per[count] = p;
                worstDraw5Row[count] = drawEntries.get(ndx).getRow();
                worstDraw5Col[count] = drawEntries.get(ndx).getCol();
                ++ndx;
                if (++count >= 5) {
                    break;
                }
            }
        }

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
            if (entry.getRow() == targetRow && entry.getCol() == targetColumn) {
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
    double[] bestDrawRowsPer = new double[3];
    double[] worstDrawRowsPer = new double[3];
    double[] bestMadeRowsPer = new double[3];
    double[] worstMadeRowsPer = new double[3];
    double[] bestShowdownwRowsPer = new double[3];
    double[] worstShowdownRowsPer = new double[3];
    // Percentage Value
    int[] bestDrawColsPer = new int[3];
    int[] worstDrawColsPer = new int[3];
    int[] bestMadeColsPer = new int[3];
    int[] worstMadeColsPer = new int[3];
    int[] bestShowdownwColsPer = new int[3];
    int[] worstShowdownColsPer = new int[3];

    // 5 best in entire array row and column
    int[] bestDraw5Row = new int[5];
    int[] bestDraw5Col = new int[5];
    double[] bestDraw5Per = new double[5];
    int[] bestMade5Row = new int[5];
    int[] bestMade5Col = new int[5];
    double[] bestMade5Per = new double[5];
    int[] bestShowdown5Row = new int[5];
    int[] bestShowdown5Col = new int[5];
    double[] bestShowdown5Per = new double[5];

    // 5 worst in entire array row and column
    int[] worstDraw5Row = new int[5];
    int[] worstDraw5Col = new int[5];
    double[] worstDraw5Per = new double[5];
    int[] worstMade5Row = new int[5];
    int[] worstMade5Col = new int[5];
    double[] worstMade5Per = new double[5];
    int[] worstShowdown5Row = new int[5];
    int[] worstShowdown5Col = new int[5];
    double[] worstShowdown5Per = new double[5];

    /*-
     Texas holdem no limit 6-max java $1/$2  java
    For a flop rated wet, dry, or neutral
    In this instance a wet flop. In the matrix below fill in the percentages of the time that you would fold, bluff, call, or raise in a typical game. 
    
    Wet     	hand          		fold      	bluff     	call     	raise  
    Yes		none			70%		30%		0%		0%
    Yes		pair			70%		30%		0%		0%
    Yes		2 pair			70%		30%		0%		0%
    Yes		set		  	70%		30%		0%		0%
    Yes		gutshot draw	70%		30%		0%		0%
    Yes		straight draw	70%		30%		0%		0%
    Yes		OESD draw		70%		30%		0%		0%
    Yes		Flush draw		70%		30%		0%		0%
    
    ChatGPT
    When playing poker, one's actions should generally depend on various factors, including the strength of one's hand, the texture of the board (wet or dry), the position, the actions of other players, and the specific tendencies of one's opponents. However, I understand that you're asking for some generalized guidelines.
    
    Remember, the following matrix is a simplification and should not be strictly adhered to. It's always important to consider the specific context of each hand:
    
    Wet hand fold bluff call raise
    Yes none 70% 20% 5% 5%
    Yes pair 30% 10% 40% 20%
    Yes 2 pair 10% 5% 30% 55%
    Yes set 5% 5% 20% 70%
    Yes gutshot draw 40% 30% 20% 10%
    Yes straight draw 30% 25% 35% 10%
    Yes OESD draw 20% 25% 35% 20%
    Yes flush draw 25% 20% 35% 20%
    
    This table reflects that players will generally be more aggressive (more calling, raising)
    with stronger hands (2 pair, set) and more likely to fold with weaker hands or bluffs. 
    Remember, percentages can vary significantly depending on specific situations and players involved.
    
    
    
    
    
    User
    show for dry flop and for neutral flop
    ChatGPT
    Sure, let's fill in a similar matrix for a dry and neutral flop, keeping in mind that the 
    exact percentages can vary based on a multitude of factors including the specific opponents, 
    stack sizes, position, and more.
    
    For a dry board:
    
    Dry hand fold bluff call raise
    Yes none 80% 15% 5% 0%
    Yes pair 25% 5% 60% 10%
    Yes 2 pair 5% 5% 30% 60%
    Yes set 5% 5% 20% 70%
    Yes gutshot draw 60% 30% 10% 0%
    Yes straight draw 50% 25% 20% 5%
    Yes OESD draw 40% 25% 30% 5%
    Yes flush draw 40% 20% 35% 5%
    
    On dry boards, bluffing frequencies can be lower as opponents are less likely to have hit a 
    part of the board, meaning our bluffs will often be successful.
    
    And for a neutral board:
    
    Neutral hand fold bluff call raise
    Yes none 75% 20% 5% 0%
    Yes pair 30% 10% 50% 10%
    Yes 2 pair 10% 5% 30% 55%
    Yes set 5% 5% 25% 65%
    Yes gutshot draw 50% 30% 15% 5%
    Yes straight draw 40% 25% 30% 5%
    Yes OESD draw 30% 20% 40% 10%
    Yes flush draw 35% 15% 40% 10%
    
    Again, these matrices are just generalized guides and your actions should always take 
    into account a wide variety of factors. Poker is a complex game and decisions should 
    not be made solely based on hand strength and flop texture.
     */
    enum FlopType {
        WET, DRY, NEUTRAL
    }

    enum HandType {
        NONE, PAIR, TWO_PAIR, SET, GUTSHOT_DRAW, STRAIGHT_DRAW, OESD_DRAW, FLUSH_DRAW
    }

    class StrategyPercentages {
        int fold;
        int bluff;
        int call;
        int raise;

        public StrategyPercentages(int fold, int bluff, int call, int raise) {
            this.fold = fold;
            this.bluff = bluff;
            this.call = call;
            this.raise = raise;
        }
    }

    Map<FlopType, Map<HandType, StrategyPercentages>> strategyMatrixA = new EnumMap<>(FlopType.class);

    public void PokerStrategy() {
        for (FlopType flopType : FlopType.values()) {
            Map<HandType, StrategyPercentages> handTypeMap = new EnumMap<>(HandType.class);
            strategyMatrix.put(flopType, handTypeMap);
            for (HandType handType : HandType.values()) {
                handTypeMap.put(handType, new StrategyPercentages(0, 0, 0, 0)); // default values
            }
        }

        // Fill in the actual strategy percentages
        strategyMatrix.get(FlopType.WET).get(HandType.NONE).fold = 70;
        strategyMatrix.get(FlopType.WET).get(HandType.NONE).bluff = 20;
        // ... and so on for all the other strategy percentages
    }

    public StrategyPercentages getStrategy(FlopType flopType, HandType handType) {
        return strategyMatrix.get(flopType).get(handType);
    }

    /*-
    In this case, getStrategy is applying a simple strategy of being less aggressive in early positions 
    and more aggressive in later positions. These are not real poker strategies, just an example 
    of how you can adjust the percentages based on the position. You can define the adjustments 
    based on your poker strategy.
       
     */

    enum PositionType {
        EARLY, MIDDLE, LATE, HEADSUP_FIRST, HEADSUP_LAST
    }

    class StrategyPercentageX {
        int fold;
        int bluff;
        int call;
        int raise;

        public void StrategyPercentagesX(int fold, int bluff, int call, int raise) {
            this.fold = fold;
            this.bluff = bluff;
            this.call = call;
            this.raise = raise;
        }
    }

    Map<FlopType, Map<HandType, StrategyPercentages>> strategyMatrix = new EnumMap<>(FlopType.class);

    public void PokerStrategyA() {
        // ... Initialize the strategyMatrix as in the previous code
    }

    public StrategyPercentages getStrategy(FlopType flopType, HandType handType, PositionType position) {
        StrategyPercentages strategyPercentages = strategyMatrix.get(flopType).get(handType);

        switch (position) {
            case EARLY:
                // Reduce bluff and raise percentages, increase fold and call percentages
                strategyPercentages.bluff -= 10;
                strategyPercentages.raise -= 10;
                strategyPercentages.fold += 10;
                strategyPercentages.call += 10;
                break;
            case MIDDLE:
                // Slightly adjust percentages
                strategyPercentages.bluff -= 5;
                strategyPercentages.raise += 5;
                break;
            case LATE:
            case HEADSUP_LAST:
                // Increase bluff and raise percentages, decrease fold and call percentages
                strategyPercentages.bluff += 10;
                strategyPercentages.raise += 10;
                strategyPercentages.fold -= 10;
                strategyPercentages.call -= 10;
                break;
            case HEADSUP_FIRST:
                // Similar to EARLY position, decrease aggression
                strategyPercentages.bluff -= 10;
                strategyPercentages.raise -= 10;
                strategyPercentages.fold += 10;
                strategyPercentages.call += 10;
                break;
        }

        // Make sure percentages are in valid range [0, 100]
        strategyPercentages.fold = Math.min(100, Math.max(0, strategyPercentages.fold));
        strategyPercentages.bluff = Math.min(100, Math.max(0, strategyPercentages.bluff));
        strategyPercentages.call = Math.min(100, Math.max(0, strategyPercentages.call));
        strategyPercentages.raise = Math.min(100, Math.max(0, strategyPercentages.raise));

        return strategyPercentages;
    }

    class PlayerContext {
        PositionType position;
        int stackSize;
        // ... You could add more fields here to represent other factors.

        public PlayerContext(PositionType position, int stackSize /* , ... */) {
            this.position = position;
            this.stackSize = stackSize;
            // ...
        }
    }

    public StrategyPercentages getStrategy(FlopType flopType, HandType handType, PlayerContext context) {
        StrategyPercentages strategyPercentages = strategyMatrix.get(flopType).get(handType);

        // Adjust based on position, like before...
        // ...

        // Adjust based on stack size
        if (context.stackSize < 10) {
            strategyPercentages.bluff -= 10;
            strategyPercentages.raise -= 10;
            strategyPercentages.fold += 10;
            strategyPercentages.call += 10;
        } else if (context.stackSize > 100) {
            strategyPercentages.bluff += 10;
            strategyPercentages.raise += 10;
            strategyPercentages.fold -= 10;
            strategyPercentages.call -= 10;
        }

        // Make sure percentages are in valid range [0, 100]
        strategyPercentages.fold = Math.min(100, Math.max(0, strategyPercentages.fold));
        strategyPercentages.bluff = Math.min(100, Math.max(0, strategyPercentages.bluff));
        strategyPercentages.call = Math.min(100, Math.max(0, strategyPercentages.call));
        strategyPercentages.raise = Math.min(100, Math.max(0, strategyPercentages.raise));

        return strategyPercentages;
    }

    /*-
    In this code:
    
    calculatePotOdds method returns the pot odds as a decimal, which is the call amount divided 
    by the total size of the pot after making the call.
    getPotOddsRatio method returns the pot odds as a ratio, comparing the pot size to the call amount.
    isCallProfitable method checks whether a call would be profitable given a certain probability 
    of winning the hand. If the chance of winning is higher than the calculated pot odds, 
    then the call would be expected to be profitable in the long run.
     */
    class PotOddsCalculator {
        double potSize;
        double callAmount;

        public PotOddsCalculator(double potSize, double callAmount) {
            this.potSize = potSize;
            this.callAmount = callAmount;
        }

        public double calculatePotOdds() {
            return callAmount / (potSize + callAmount);
        }

        public String getPotOddsRatio() {
            return "1:" + (potSize / callAmount);
        }

        public boolean isCallProfitable(double winningProbability) {
            // If the probability of winning is higher than the pot odds, then the call is
            // profitable.
            return calculatePotOdds() < winningProbability;
        }
    }

    /*-
     
     */
    class PokerStrategy {
        // ... The rest of your code here...

        class PlayerContext {
            PositionType position;
            int stackSize;
            double potOdds; // Added a new field for pot odds
            // ... You could add more fields here to represent other factors.

            public PlayerContext(PositionType position, int stackSize, double potOdds /* , ... */) {
                this.position = position;
                this.stackSize = stackSize;
                this.potOdds = potOdds;
                // ...
            }
        }

        /*-
        
        Sure, let's adjust the strategy based on the pot odds, similar to the way we did for stack size.
        Here's how you can modify the getStrategy method to take into account the pot odds:
        */

        class PokerStrategyX {
            // ... The rest of your code here...

            class PlayerContext {
                PositionType position;
                int stackSize;
                double potOdds; // Added a new field for pot odds
                // ... You could add more fields here to represent other factors.

                public PlayerContext(PositionType position, int stackSize, double potOdds /* , ... */) {
                    this.position = position;
                    this.stackSize = stackSize;
                    this.potOdds = potOdds;
                    // ...
                }
            }

            public StrategyPercentages getStrategy(FlopType flopType, HandType handType, PlayerContext context) {
                StrategyPercentages strategyPercentages = strategyMatrix.get(flopType).get(handType);

                // Adjust based on position, like before...
                // ...

                // Adjust based on stack size, like before...
                // ...

                // Adjust based on pot odds
                if (context.potOdds < 0.2) {
                    // If pot odds are low, we're more likely to fold or bluff
                    strategyPercentages.fold += 10;
                    strategyPercentages.bluff += 10;
                    strategyPercentages.call -= 10;
                    strategyPercentages.raise -= 10;
                } else if (context.potOdds > 0.5) {
                    // If pot odds are high, we're more likely to call or raise
                    strategyPercentages.fold -= 10;
                    strategyPercentages.bluff -= 10;
                    strategyPercentages.call += 10;
                    strategyPercentages.raise += 10;
                }

                // Make sure percentages are in valid range [0, 100]
                strategyPercentages.fold = Math.min(100, Math.max(0, strategyPercentages.fold));
                strategyPercentages.bluff = Math.min(100, Math.max(0, strategyPercentages.bluff));
                strategyPercentages.call = Math.min(100, Math.max(0, strategyPercentages.call));
                strategyPercentages.raise = Math.min(100, Math.max(0, strategyPercentages.raise));

                return strategyPercentages;
            }
        }
        /*-
        This example  adds a new  potOdds field  to the PlayerContext class and  adjusts the   strategy percentages
            in the  getStrategy method   based on  the value  of potOdds.
            If the  pot odds are low (meaning the player has to risk a lot to win a little), the code increases 
            the likelihood of folding or bluffing and decreases the likelihood of calling or raising. 
            Conversely, if the pot odds are
        
            high (meaning the player can win a lot for a small risk), the code decreases the likelihood of 
            folding or bluffing and increases the likelihood of calling or raising.
             */

        public StrategyPercentages getStrategy(FlopType flopType, HandType handType, PlayerContext context) {
            StrategyPercentages strategyPercentages = strategyMatrix.get(flopType).get(handType);

            // Adjust based on position, like before...
            // ...

            // Adjust based on stack size, like before...
            // ...

            // Adjust based on pot odds
            if (context.potOdds < 0.2) {
                // If pot odds are low, we're more likely to fold or bluff
                strategyPercentages.fold += 10;
                strategyPercentages.bluff += 10;
                strategyPercentages.call -= 10;
                strategyPercentages.raise -= 10;
            } else if (context.potOdds > 0.5) {
                // If pot odds are high, we're more likely to call or raise
                strategyPercentages.fold -= 10;
                strategyPercentages.bluff -= 10;
                strategyPercentages.call += 10;
                strategyPercentages.raise += 10;
            }

            // Make sure percentages are in valid range [0, 100]
            strategyPercentages.fold = Math.min(100, Math.max(0, strategyPercentages.fold));
            strategyPercentages.bluff = Math.min(100, Math.max(0, strategyPercentages.bluff));
            strategyPercentages.call = Math.min(100, Math.max(0, strategyPercentages.call));
            strategyPercentages.raise = Math.min(100, Math.max(0, strategyPercentages.raise));

            return strategyPercentages;
        }

        class PokerStrategyZ {
            // ... The rest of your code here...

            enum PlayerType {
                FISH, NIT, LAG, TAG, AVERAGE, WINNER
            }

            class PlayerContext {
                PositionType position;
                int stackSize;
                double potOdds;
                PlayerType playerType; // Added a new field for player type
                // ... You could add more fields here to represent other factors.

                public PlayerContext(PositionType position, int stackSize, double potOdds, PlayerType playerType /*
                                                                                                                  * ,
                                                                                                                  * ...
                                                                                                                  */) {
                    this.position = position;
                    this.stackSize = stackSize;
                    this.potOdds = potOdds;
                    this.playerType = playerType;
                    // ...
                }
            }

            // TODO caused error public StrategyPercentages getStrategy(FlopType flopType,
            // HandType handType, PlayerContext context) {
            public void getStrategy(FlopType flopType, HandType handType, PlayerContext context) {
                StrategyPercentages strategyPercentages = strategyMatrix.get(flopType).get(handType);

                // Adjust based on position, stack size, and pot odds, like before...
                // ...

                // Adjust based on player type
                switch (context.playerType) {
                    case FISH:
                        strategyPercentages.bluff -= 10;
                        strategyPercentages.raise += 10;
                        break;
                    case NIT:
                        strategyPercentages.bluff += 10;
                        strategyPercentages.raise -= 10;
                        break;
                    case LAG:
                        strategyPercentages.fold -= 10;
                        strategyPercentages.bluff -= 10;
                        strategyPercentages.call += 10;
                        strategyPercentages.raise += 10;
                        break;
                    case TAG:
                        strategyPercentages.fold += 10;
                        strategyPercentages.bluff -= 10;
                        strategyPercentages.call += 10;
                        strategyPercentages.raise -= 10;
                        break;
                    case AVERAGE:
                        // No adjustments for average players
                        break;
                    case WINNER:
                        // Complex and situation-dependent adjustments for experienced players...
                        break;
                }

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

        }
    }
}