
//package evaluate_streets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*- ******************************************************************************
 * This class defines a range of hands The hands are in a Container class. 
 * This array represents the commonly used 13 X 13 matrix to represent suited,
 * ofsuit, and pairs
 * An element is true if that card is in the range.
 * 
 * In previkous versions a 13 X 13 array was indexd directly.
 * If the values in the array are assigned in playability order or Expected Value 
 * order then we can simply use that single value and not an array at all.
 * 
 * The purpose of this class ( and the previous HoleCardAction class) is to select an action
 * based or on the hole cards. 
 * 
 * The hole cards are represented by the 13 X 13 matrix index for a value of 
 * 0 to 168.
 * 
 * The action is based on the bet that the player is facing:
 *      REFLOP_LIMP, PREFLOP_OPEN,  PREFLOP_BET3, PREFLOP_BET4, PREFLOP_ALLIN
 *      ( for ALLIN there is no raise option only call )
 * 
 * The player can respond to the action to him by:
 *      RAISE, CALL ( FOLD if not RAISE or CALL )
 *
 * In this class we use an array of index values that represent the last value for 
 * the last card in that range. 
 * Ranges are creted in either playability or EV order, so it is contigueous with
 * no gaps. The last card is all that we need.
 * 
 * THis class supports all combinations of actions to a player and the response 
 * based on the hole cards index ( 0 - 168).
 * 
 * We use a 3 dimensional array. 
 * The first inex is the posirion, SB, BB, UTG, MP, CO, BU
 * The second indexis the action to the playes, PREFLOP_x. 
 * The third index is the response FOLD, LIMP, ...
 * The value in the array is the largest hole card index that supports the action.
 * 
 * 
 * 
 * @author PEAK_
****************************************************************************** */

public class HoleCardAction implements Constants {


  /*-  ****************************************************************************
  * This is what this Class is all about.
  * It frepresents one type of player ( Hero, HeroX, Average, AverageX, Reg, Nit,
  LAG, TAG, Fish. Looser, )
  ****************************************************************************** */
    // THis is the only public data and is what the class is all about
    int[][][] actionArrayPlayability = new int[POSITIONS][5][2];  
    // Copied from HandRange used to construct this instance
    int[][] rangeArray = new int[POSITIONS][HANDS];
    int[][] gapsArray = new int[POSITIONS][HANDS];
    String[] cards = new String[POSITIONS];
    int[] lowPlayabilitrhyArrayIndex = new int[POSITIONS];
    int[] lowRangeArrayIndex = new int[POSITIONS];
    int[] lowPlayabilityArrayNumericValue = new int[POSITIONS];
    int[] gapCount = new int[POSITIONS];
    double[] percentage = new double[POSITIONS];
    int[] combos = new int[POSITIONS];

    private static int x = 50;

    private static int y = 50;

    private static String title = "";

    static JTextField[] cardsText = new JTextField[POSITIONS];
    static JTextField[][][] actionArrayText = new JTextField[POSITIONS][5][2];
    static JTextField[] lowRangeArrayIndexText = new JTextField[POSITIONS];
    static JTextField[] lowPlayabilitrhyArrayIndexdsText = new JTextField[POSITIONS];
    static JTextField[] lowPlayabilityArrayNumericValueText = new JTextField[POSITIONS];
    static JTextField[] gapCountText = new JTextField[POSITIONS];
    static JTextField[] percentageText = new JTextField[POSITIONS];
    static JTextField[] combosText = new JTextField[POSITIONS];
    static JTextField[][] rangeArrayText = new JTextField[POSITIONS][HANDS];
    static JTextField header = new JTextField(
            "           SB                   BB                  UTG " +
                    "                 MP                   CO                  BU");

    /*-  ******************************************************************************
    *  Equilab Equity vs. 5 random hands - Playability  
    *  Constructed from playabilityArray.
    *  
    *  Array matches drawAndMadeArray but values are playability value, index 0 is AA 169, index 1 AKs  4
    *  playabilityArrayNumeric
    *  Values are 1 - 169, 169 being AA the most playable
    *  
    *  Array matches drawAndMadeArray but values are index into 
    *  Values are index into playabilityArray, an array of String
     ****************************************************************************** */
    private static int[] playabilityArrayNumeric = new int[HANDS];

    private static int[] playabilityArrayOrder = new int[HANDS];

    private static int[] orderArray = new int[HANDS];

    private static String path = EvalData.applicationDirectory;

    /*- Array of all possible 2 card combinations - 169 hands.  */
    private static final String[] handsArray = { "AA", "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s",
            "A4s",
            "A3s", "A2s", "AKo", "KK", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
            "AQo", "KQo", "QQ", "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s", "AJo", "KJo",
            "QJo", "JJ", "JTs", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s", "J2s", "ATo", "KTo", "QTo", "JTo",
            "TT", "T9s", "T8s", "T7s", "T6s", "T5s", "T4s", "T3s", "T2s", "A9o", "K9o", "Q9o", "J9o", "T9o", "99",
            "98s", "97s", "96s", "95s", "94s", "93s", "92s", "A8o", "K8o", "Q8o", "J8o", "T8o", "98o", "88", "87s",
            "86s", "85s", "84s", "83s", "82s", "A7o", "K7o", "Q7o", "J7o", "T7o", "97o", "87o", "77", "76s", "75s",
            "74s", "73s", "72s", "A6o", "K6o", "Q6o", "J6o", "T6o", "96o", "86o", "76o", "66", "65s", "64s", "63s",
            "62s", "A5o", "K5o", "Q5o", "J5o", "T5o", "95o", "85o", "75o", "65o", "55", "54s", "53s", "52s", "A4o",
            "K4o", "Q4o", "J4o", "T4o", "94o", "84o", "74o", "64o", "54o", "44", "43s", "42s", "A3o", "K3o", "Q3o",
            "J3o", "T3o", "93o", "83o", "73o", "63o", "53o", "43o", "33", "32s", "A2o", "K2o", "Q2o", "J2o", "T2o",
            "92o", "82o", "72o", "62o", "52o", "42o", "32o", "22" };

    /*- Equilab Equity vs. 5 random hands - Playability  */
    private static final String[] playabilityArray = { "AA", "KK", "QQ", "JJ", "AKs", "TT", "AQs", "KQs", "AKo", "AJs",
            "KJs", "ATs", "99", "QJs", "AQo", "KTs", "QTs", "KQo", "JTs", "AJo", "A9s", "88", "KJo", "A8s", "K9s",
            "ATo", "QJo", "Q9s", "A7s", "T9s", "J9s", "A5s", "KTo", "77", "A4s", "A6s", "QTo", "JTo", "K8s", "A3s",
            "Q8s", "K7s", "A2s", "T8s", "J8s", "98s", "A9o", "66", "K6s", "K5s", "K9o", "A8o", "Q7s", "K4s", "87s",
            "T7s", "Q9o", "97s", "T9o", "J7s", "J9o", "K3s", "55", "Q6s", "A7o", "K2s", "A5o", "Q5s", "76s", "86s",
            "Q4s", "A4o", "A6o", "96s", "T6s", "K8o", "44", "J6s", "Q3s", "65s", "A3o", "J5s", "T8o", "Q8o", "Q2s",
            "75s", "J8o", "98o", "K7o", "54s", "J4s", "33", "A2o", "85s", "J3s", "T5s", "64s", "95s", "K6o", "J2s",
            "T4s", "22", "53s", "74s", "K5o", "T3s", "87o", "97o", "Q7o", "T7o", "84s", "J7o", "T2s", "43s", "K4o",
            "63s", "94s", "Q6o", "K3o", "93s", "76o", "52s", "73s", "92s", "Q5o", "86o", "K2o", "42s", "83s", "96o",
            "T6o", "82s", "65o", "Q4o", "62s", "J6o", "32s", "Q3o", "75o", "72s", "J5o", "64o", "Q2o", "85o", "J4o",
            "54o", "95o", "T5o", "J3o", "53o", "T4o", "J2o", "74o", "T3o", "43o", "84o", "63o", "T2o", "94o", "93o",
            "52o", "73o", "92o", "42o", "83o", "62o", "82o", "32o", "72o" };

    /*- ****************************************************************************
     *  Array of all possible 2 card combinations - 169 hands.  From PioSolver
    *******************************************************************************/
    private static final int[] handsArrayPioSolver = { UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG,
            UTG, UTG, UTG,
            UTG, UTG, MP, MP, CO, CO, CO, BU, BU, BU, UTG, UTG, UTG, UTG, UTG, MP, CO, BU, BU, BU, SB, SB, SB, UTG, MP,
            MP, UTG, UTG, MP, CO, BU, SB, SB, SB, SB, SB, UTG, CO, CO, CO, UTG, UTG, CO, BU, BU, SB, SB, SB, SB, BU, BU,
            BU, BU, BU, UTG, MP, CO, BU, SB, SB, NO, NO, BU, SB, SB, SB, SB, BU, UTG, MP, CO, BU, SB, NO, NO, BU, SB,
            SB, SB, SB, SB, SB, UTG, MP, CO, SB, SB, NO, BU, SB, SB, NO, NO, NO, SB, SB, UTG, UTG, BU, SB, NO, BU, SB,
            SB, NO, NO, NO, NO, SB, SB, UTG, MP, BU, SB, BU, SB, NO, NO, NO, NO, NO, NO, SB, SB, MP, BU, SB, SB, NO, NO,
            NO, NO, NO, NO, NO, NO, NO, NO, CO, SB, SB, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, CO };

    /*-  ******************************************************************************
     * Constructor
     ****************************************************************************** */
    HoleCardAction() {
        for (int i = 0; i < POSITIONS; ++i) {
            this.cards[i] = "";
            for (int j = 0; j < 5; ++j) {
                for (int k = 0; k < 2; ++k) {
                    this.actionArrayPlayability[i][j][k] = -1;
                }
            }
        }
        makePlayability();
        createPlayabilityArrayNumeric();
    }

    /*-  ****************************************************************************
     * Construct orderArray
     * 
     ****************************************************************************** */
    void makePlayability() {
        int k = 0;
        for (int i = 0; i < HANDS; ++i) {
            k = getHandStringToIndex(playabilityArray[i]);
            orderArray[i] = k;
        }
    }

    /*- ****************************************************************************
     *Set path for all files
    * Arg0 - Position SB, BB, UTG, MP, CO, BU
    * Arg1 - Action to me PREFLOP_LIMP, PREFLOP_OPEN, PREFLOP_BET3, PREFLOP_BET4,
    *     PREFLOP_ALLIN
    * Arg2 - Raise or Call
     *******************************************************************************/
    int getEV(int pos, int toMe, int rc) {
        return this.actionArrayPlayability[pos][toMe][rc];
    }

    /*- ****************************************************************************
     *Set path for all files
     *******************************************************************************/
    void setPath(String p) {
        path = p;
    }

    /*-  ***************************************************************************
     * Create  playabilityArrayNumeric 
     * The array will contain values from 1 - 169
     * AA is 169, 72o is 1
     *******************************************************************************/
    void createPlayabilityArray() {
        for (int k = 0, num = 169, i = 0; i < HANDS; ++i) {
            k = getHandStringToIndex(playabilityArray[i]);
            playabilityArrayNumeric[k] = num--;
        }
    }

    /*-  ****************************************************************************
    * Create  playabilityArrayNumeric 
    * The array will contain values from 1 - 169
    * AA is 169, 72o is 1
    * Create  playabilityArrayOrder 
    * AA is 0, AKs is 4, the reverse of playabilityArrayNumeric 
    * Copy RangeOrder values to array   TODO 
     ****************************************************************************** */
    void createPlayabilityArrayNumeric() {
        for (int k = 0, i = 0; i < HANDS; ++i) {
            k = getHandStringToIndex(playabilityArray[i]);
            playabilityArrayNumeric[k] = 169 - k;
            playabilityArrayOrder[i] = k;
            orderArray[i] = order.getHandIndex(i);
        }
    }

    /*- ************************************************************************
    Get playabilityNumeric value 
    ****************************************************************************/
    int getPlayabilityNumeric(int index) {
        return playabilityArrayNumeric[index];
    }

    /*-  *************************************************************************
     Get playabilityOrder value 
     *************************************************************************** */
    int getPlayabilityOrder(int index) {
        return playabilityArrayOrder[index];
    }

    /*- ************************************************************************
     * Get order value 
    *************************************************************************** */
    int getOrder(int index) {
        return orderArray[index];
    }

    /*-  ************************************************************************
    * Get hand string from rankingArray using index. 
    ****************************************************************************/
    String getPlayabilityArrayString(int index) {
        return handsArray[order.getHandIndex(index)];
    }

    /*- *************************************************************************
    * Get hand string from drawAndMadeArray using index. 
     *************************************************************************** */
    String getHandString(int index) {
        return handsArray[index];
    }

    /*-  ***************************************************************************
    * Find hand in drawAndMadeArray and return index Arg0 - hand in string format.
     ***************************************************************************  */
    int getHandStringToIndex(String hand) {
        for (int I = 0; I < HANDS; ++I) {
            if (handsArray[I].equals(hand)) {
                return I;
            }
        }
        Logger.log("ERROR HoleCardAction handStringToIndex() Invalid hand " + hand);
        return -1;
    }

    /*- ***************************************************************************
    * Read a rangeArray from a disk file 
    * Arg0 - The full path name. 
     ****************************************************************************** */
    boolean readArray(String path) {
        if (!new File(path).exists()) {
            Logger.log("ERROR HoleCardAction readArray() File does not exist " + path);
            return false;
        }
        this.actionArray = FileUtils.readFile(path, this.actionArray);
        return this.actionArray != null;
    }

    /*- ***************************************************************************
    *	Write a HoleCardAction object to a disk file Arg0 - The full path name. 
    *******************************************************************************/
    void writeArray(String path) {
        FileUtils.writeFile(path, this.actionArray);
    }

    /*- ***************************************************************************
    *	Display 
    *******************************************************************************/
    void show() {
        constructPanel();
    }

    /*-  ****************************************************************************
    *  Construct panel. 
     *****************************************************************************/
    void constructPanel() {
        final var f0 = new Font(Font.SERIF, Font.BOLD, 18);

        final var panel = new JPanel();
        final var panelH = new JPanel();
        final var panel0 = new JPanel();
        final var panel1 = new JPanel();

        final var panelHLayout = new GridLayout(1, 1);
        panelH.setLayout(panelHLayout);
        panelH.setSize(500, 20);
        panelH.setFont(f0);

        final var panelLayout = (new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(panelLayout);
        panel.setSize(500, 800);
        panel.setFont(f0);

        final var panel0Layout = new GridLayout(7, 6);
        panel0.setLayout(panel0Layout);
        panel0.setSize(500, 280);
        panel0.setFont(f0);

        final var panel1Layout = new GridLayout(1, 1);
        panel1.setLayout(panel1Layout);
        panel1.setSize(500, 40);
        panel1.setFont(f0);

        JPanel panelCards = new JPanel();
        panelCards.setSize(500, 40);
        panelCards.setFont(f0);
        final var panelCardsLayout = new GridLayout(1, 6);
        panelCards.setLayout(panelCardsLayout);

        JPanel panelLowRange = new JPanel();
        panelLowRange.setSize(500, 40);
        panelLowRange.setFont(f0);
        final var panelLowLayout = new GridLayout(1, 6);
        panelLowRange.setLayout(panelLowLayout);

        JPanel panelLowPlay = new JPanel();
        panelLowPlay.setSize(500, 40);
        panelLowPlay.setFont(f0);
        final var panelLowPlayLayout = new GridLayout(1, 6);
        panelLowPlay.setLayout(panelLowPlayLayout);

        JPanel panelLowPlanNum = new JPanel();
        panelLowPlanNum.setSize(200, 40);
        panelLowPlanNum.setFont(f0);
        final var panelLowPlanNumLayout = new GridLayout(1, 6);
        panelLowPlanNum.setLayout(panelLowPlanNumLayout);

        JPanel panelGaps = new JPanel();
        panelGaps.setSize(500, 40);
        panelGaps.setFont(f0);
        final var panelGapsLayout = new GridLayout(1, 6);
        panelGaps.setLayout(panelGapsLayout);

        JPanel panelPercentage = new JPanel();
        panelPercentage.setSize(500, 40);
        panelPercentage.setFont(f0);
        final var panelPercentageLayout = new GridLayout(1, 6);
        panelPercentage.setLayout(panelGapsLayout);

        JPanel panelCombos = new JPanel();
        panelCombos.setSize(500, 40);
        panelCombos.setFont(f0);
        final var panelCombosLayout = new GridLayout(1, 6);
        panelCombos.setLayout(panelCombosLayout);

        for (int i = 0; i < POSITIONS; ++i) {
            cardsText[i] = new JTextField(cards[i]);
            lowRangeArrayIndexText[i] = new JTextField(String.valueOf(lowRangeArrayIndex[i]));
            lowPlayabilitrhyArrayIndexdsText[i] = new JTextField(String.valueOf(lowPlayabilitrhyArrayIndex[i]));
            lowPlayabilityArrayNumericValueText[i] = new JTextField(String.valueOf(lowPlayabilityArrayNumericValue[i]));
            gapCountText[i] = new JTextField(String.valueOf(gapCount[i]));
            percentageText[i] = new JTextField(Format.formatPer(percentage[i] * 10.));
            combosText[i] = new JTextField(String.valueOf(combos[i]));

            cardsText[i].setFont(f0);
            lowRangeArrayIndexText[i].setFont(f0);
            lowPlayabilitrhyArrayIndexdsText[i].setFont(f0);
            lowPlayabilityArrayNumericValueText[i].setFont(f0);
            gapCountText[i].setFont(f0);
            percentageText[i].setFont(f0);
            combosText[i].setFont(f0);

            panelCards.add(cardsText[i]);
            panelLowRange.add(lowRangeArrayIndexText[i]);
            panelLowPlay.add(lowPlayabilitrhyArrayIndexdsText[i]);
            panelLowPlanNum.add(lowPlayabilityArrayNumericValueText[i]);
            panelGaps.add(gapCountText[i]);
            panelPercentage.add(percentageText[i]);
            panelCombos.add(combosText[i]);
        }
        panel0.add(panelCards);
        panel0.add(panelLowRange);
        panel0.add(panelLowPlay);
        panel0.add(panelLowPlanNum);
        panel0.add(panelGaps);
        panel0.add(panelPercentage);
        panel0.add(panelCombos);

        final var actionArrayLayout = new GridLayout(10, POSITIONS);
        final var panelActionArray = new JPanel();
        panelActionArray.setBackground(Color.WHITE);
        panelActionArray.setLayout(actionArrayLayout);
        panelActionArray.setFont(f0);

        JPanel panelAction = new JPanel();
        panelAction.setSize(500, 700);
        panelAction.setFont(f0);
        final var actionLayout = new GridLayout(10, 6);
        panelAction.setLayout(actionLayout);
        for (int i = 0; i < POSITIONS; ++i) {
            for (int j = 0; j < 5; ++j) {
                actionArrayText[i][j][0] = new JTextField(String.valueOf(actionArray[i][j][0]));
                actionArrayText[i][j][0].setFont(f0);
                actionArrayText[i][j][0].setSize(40, 40);
                panelAction.add(actionArrayText[i][j][0]);
                actionArrayText[i][j][1] = new JTextField(String.valueOf(actionArray[i][j][1]));
                actionArrayText[i][j][1].setFont(f0);
                actionArrayText[i][j][1].setSize(40, 40);
                panelAction.add(actionArrayText[i][j][1]);
            }
        }
        panel1.add(panelAction);

        final var frame = new JFrame();

        new Dimension(500, 800);
        final var frameDimN = new Dimension(500, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(x, y);
        frame.setPreferredSize(new Dimension(500, 800));
        frame.setTitle("Hole Card Action ");
        frame.setMaximumSize(frameDimN);
        frame.setMinimumSize(frameDimN);

        panelH.add(header);
        panel.add(panelH);
        panel.add(panel0);
        panel.add(panel1);

        // frame.add(panelH);
        frame.add(panel);

        panel.repaint();
        frame.pack();
        frame.setVisible(true);
    }

}