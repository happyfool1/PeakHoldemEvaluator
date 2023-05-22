
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
import javax.swing.border.Border;
import javax.swing.JLabel;

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
 * We use a 2 dimensional array. 
 * The first inex is the posirion, SB, BB, UTG, MP, CO, BU
 * The second indexis the action to the playes, PREFLOP_x. 
 * There are arrays for Raise and arrays for Call.
 * The value in the array is the largest hole card index that supports the action.
 * 
 * 
 * 
 * @author PEAK_
****************************************************************************** */

public class HoleCardAction implements Constants {

    /*- ****************************************************************************
    * This is what this Class is all about.
    * Preflop only.
    * It represents one type of player:
    *      Hero, HeroX, Average, AverageX, Reg, Nit, LAG, TAG, Fish. Looser, 
    *      Per40, Per30, Per20, Per10 
    * Each position taht this player may hold:
    *      SB, BB, UTG, MP, CO, BU
    * Actions that the player can face:
    *      REFLOP_LIMP, PREFLOP_OPEN,  PREFLOP_BET3, PREFLOP_BET4, PREFLOP_ALLIN
    * Actions that the player can take:
    *      RAISE, CALL ( FOLD if not RAISE or CALL )
    * For each of these possibilities there is a playability number for each of
    * the 169 possible 2 card hands. Hole cards.
    *       "AA", "AKs", "AQs", "AJs", "ATs", ....
    *       The best card first. The order is based on the Expected Value of each 
    *       2 card combination. The order is prettymuch universally accepted.
    * Because the hands in a range are chosen in playability order or Expected Value 
    * order then we can simply use that single value and not an array at all.
    * Simpler and much faster.
    *
    * handIndex is the index into an array of 169 hole cards as a 13 X 13 matrix.
    * It is a simple way to identify any hole cards as a single integer value 0-168.
    * It is the index into the orderArray to quickly find the playability number
    * for aany specific hand.
    * This number can be compared with the number in actionArrayPlayability to 
    * determine hhat action ti take given a positoon and action to player.
    * For range adjustments based on table dynamics a number can be added or subtracted
    * from the playability number. 
    * Note: playability 0 is the best and 168 the worst.
    * Example: 
    *      hand index = 15, playability = 22, actionArrayPlayability is 20.
    *      Normally this would be a fold, but because of table dynamics maybe not.
    *      We might add 2 because of limpers, relative position, stack, etc..
    * 
    * Additional data for analysis: 
    * cards is the hole cards in a position SB, BB, UTG, MP, CO, BU.
    * combos is the number of combinations of 2 card hands within a range.
    * percentage is the percentage of combinations of 2 card hands within a range. 
    * Even though we do not use ranges directly in making decision this gives a 
    * measure of what an equivalent range would be.   
    *
    * gapsArray is for the rare circumstance that hands were not assigned in strict
    * playability order. Elements ate handIndexes of habds excluded.
    * gapcount is the number of gaps in a ramnge.
    *
    ****************************************************************************** */

    int[][] actionArrayPlayabilityRaise = new int[POSITIONS][5];
    int[][] actionArrayPlayabilityCall = new int[POSITIONS][5];
    int[][] actionArrayGapsRaise = new int[POSITIONS][5];
    int[][] actionArrayGapsCall = new int[POSITIONS][5];
    int[][] actionArrayCombosRaise = new int[POSITIONS][5];
    int[][] actionArrayCombosCall = new int[POSITIONS][5];
    double[][] actionArrayPercentageRaise = new double[POSITIONS][5];
    double[][] actionArrayPercentageCall = new double[POSITIONS][5];

    // Copied from HandRange used to construct this instance
    int[][] gapsArray = new int[POSITIONS][HANDS];

    private static int x = 50;

    private static int y = 50;

    private static JTextField[][] openText = new JTextField[POSITIONS][4];
    private static JTextField[][] limpText = new JTextField[POSITIONS][4];

    private static JTextField[][] openText = new JTextField[POSITIONS][4];
    private static JTextField[][] limpText = new JTextField[POSITIONS][4];

    private static JTextField[][] openText = new JTextField[POSITIONS][4];
    private static JTextField[][] limpText = new JTextField[POSITIONS][4];

    private static JTextField[][] openText = new JTextField[POSITIONS][4];
    private static JTextField[][] limpText = new JTextField[POSITIONS][4];

    private static JTextField[][] openText = new JTextField[POSITIONS][4];
    private static JTextField[][] limpText = new JTextField[POSITIONS][4];


 



    private static JTextField header = new JTextField(
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
            "A4s", "A3s", "A2s", "AKo", "KK", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s",
            "K2s",
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
            for (int j = 0; j < 5; ++j) {
                for (int k = 0; k < 2; ++k) {
                    this.actionArrayGaps[i][j][k] = 0;
                    this.actionArrayCombos[i][j][k] = -1;
                    this.actionArrayPercentage[i][j][k] = -1;
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
            orderArray[i] = getOrderIndex(i);
        }
    }

    /*-  Get location in RangeArray. */
    int getOrderIndex(int ndx) {
        return this.orderArray[ndx];
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
        return handsArray[getOrderIndex(index)];
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
        this.actionArrayPlayability = FileUtils.readFile(path, this.actionArrayPlayability);
        return this.actionArrayPlayability != null;
    }

    /*- ***************************************************************************
    *	Write a HoleCardAction object to a disk file Arg0 - The full path name. 
    *******************************************************************************/
    void writeArray(String path) {
        FileUtils.writeFile(path, this.actionArrayPlayability);
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
        final var panel1 = new JPanel();

        final var panelLayout = new GridLayout(1, 1);
        panel.setLayout(panelLayout);
        panel.setSize(500, 800);
        panel.setFont(f0);

        final var panel1Layout = new GridLayout(5, 1);
        panel1.setLayout(panel1Layout);
        panel1.setSize(500, 800);
        panel1.setFont(f0);

        String spaces = "                   ";
        Border positions = BorderFactory.createTitledBorder(
                "           SB                BB                 UTG                   MP                 CO                  BU");
        Border raiseCall = BorderFactory.createTitledBorder("Raise or Call");
        Border limp = BorderFactory.createTitledBorder("Limp");
        Border open = BorderFactory.createTitledBorder("Open");
        Border bet3 = BorderFactory.createTitledBorder("3 Bet");
        Border bet4 = BorderFactory.createTitledBorder("4 Bet");
        Border allin = BorderFactory.createTitledBorder("Allin");

        panel1.setBorder(positions);

        JPanel panelActionA = new JPanel();
        panelActionA.setSize(500, 80);
        panelActionA.setFont(f0);
        final var actionLayout = new GridLayout(4, 6);
        panelActionA.setLayout(actionLayout);
        panelActionA.setBorder(limp);
        for (int i = 0; i < POSITIONS; ++i) {
            openLimpText[i][0] = new JTextField(String.valueOf(actionArrayPlayabilityRaise[i][0]));
            openLimpText[i][0].setFont(f0);
            openLimpText[i][0].setSize(40, 40);
            panelActionA.add(openText[i][0]);
              openLimpText[i][1] = new JTextField(Format.format(actionArrayPercentageRaise[i][0]));
            openLimpText[i][1].setFont(f0);
            openLimpText[i][1].setSize(40, 40);
             panelActionA.add(limpText[i][0]);
        }

        panel1.add(panelActionA);

        for (int i = 0; i < POSITIONS; ++i) {
            openLimpText[i][0] = new JTextField(String.valueOf(actionArrayPlayabilityRaise[i][0]));
            openLimpText[i][0].setFont(f0);
            openLimpText[i][0].setSize(40, 40);
            panelActionA.add(openLimpText[i][0]);
            openLimpText[i][2] = new JTextField(String.valueOf(actionArrayPlayabilityCall[i][0]));
            openLimpText[i][2].setFont(f0);
            openLimpText[i][2].setSize(40, 40);
            panelActionA.add(openLimpText[i][2]);

            openLimpText[i][1] = new JTextField(Format.format(actionArrayPercentageRaise[i][0]));
            openLimpText[i][1].setFont(f0);
            openLimpText[i][1].setSize(40, 40);
             panelActionA.add(openLimpText[i][1]);
            openLimpText[i][3] = new JTextField(Format.format(actionArrayPercentageCall[i][0]));
            openLimpText[i][3].setFont(f0);
            openLimpText[i][3].setSize(40, 40);
             panelActionA.add(openLimpText[i][3]);
        }

        panel1.add(panelActionA);

        JPanel panelActionB = new JPanel();
        panelActionB.setSize(500, 80);
        panelActionB.setFont(f0);
        panelActionB.setLayout(actionLayout);
        panelActionB.setBorder(open);
        for (int i = 0; i < POSITIONS; ++i) {
            actionArrayPlayabilityTextB[i][0][0] = new JTextField(String.valueOf(actionArrayPlayability[i][0][0]));
            actionArrayPlayabilityTextB[i][0][0].setFont(f0);
            actionArrayPlayabilityTextB[i][0][0].setSize(40, 40);
            panelActionB.add(actionArrayPlayabilityTextB[i][0][0]);
            actionArrayPlayabilityTextB[i][0][1] = new JTextField(String.valueOf(actionArrayPlayability[i][0][1]));
            actionArrayPlayabilityTextB[i][0][1].setFont(f0);
            actionArrayPlayabilityTextB[i][0][1].setSize(40, 40);
            panelActionB.add(actionArrayPlayabilityTextB[i][0][1]);
        }
      //  panel1.add(panelActionB);

        JPanel panelActionC = new JPanel();
        panelActionC.setSize(500, 80);
        panelActionC.setFont(f0);
        panelActionC.setLayout(actionLayout);
        panelActionC.setBorder(bet3);
        for (int i = 0; i < POSITIONS; ++i) {
            actionArrayPlayabilityTextC[i][0][0] = new JTextField(String.valueOf(actionArrayPlayability[i][2][0]));
            actionArrayPlayabilityTextC[i][0][0].setFont(f0);
            actionArrayPlayabilityTextC[i][0][0].setSize(40, 40);
            panelActionC.add(actionArrayPlayabilityTextC[i][0][0]);
            actionArrayPlayabilityTextC[i][0][1] = new JTextField(String.valueOf(actionArrayPlayability[i][2][1]));
            actionArrayPlayabilityTextC[i][0][1].setFont(f0);
            actionArrayPlayabilityTextC[i][0][1].setSize(40, 40);
            panelActionC.add(actionArrayPlayabilityTextC[i][0][1]);
        }
      //  panel1.add(panelActionC);

        JPanel panelActionD = new JPanel();
        panelActionD.setSize(500, 80);
        panelActionD.setFont(f0);
        panelActionD.setLayout(actionLayout);
        panelActionD.setBorder(bet4);
        for (int i = 0; i < POSITIONS; ++i) {
            actionArrayPlayabilityTextD[i][0][0] = new JTextField(String.valueOf(actionArrayPlayability[i][3][0]));
            actionArrayPlayabilityTextD[i][0][0].setFont(f0);
            actionArrayPlayabilityTextD[i][0][0].setSize(40, 40);
            panelActionD.add(actionArrayPlayabilityTextD[i][0][0]);
            actionArrayPlayabilityTextD[i][0][1] = new JTextField(String.valueOf(actionArrayPlayability[i][3][1]));
            actionArrayPlayabilityTextD[i][0][1].setFont(f0);
            actionArrayPlayabilityTextD[i][0][1].setSize(40, 40);
            panelActionD.add(actionArrayPlayabilityTextD[i][0][1]);
        }
     //   panel1.add(panelActionD);

        JPanel panelActionE = new JPanel();
        panelActionE.setSize(500, 80);
        panelActionE.setFont(f0);
        panelActionE.setLayout(actionLayout);
        panelActionE.setBorder(allin);
        for (int i = 0; i < POSITIONS; ++i) {
            actionArrayPlayabilityTextE[i][0][0] = new JTextField(String.valueOf(actionArrayPlayability[i][4][0]));
            actionArrayPlayabilityTextE[i][0][0].setFont(f0);
            actionArrayPlayabilityTextE[i][0][0].setSize(40, 40);
            panelActionE.add(actionArrayPlayabilityTextE[i][0][0]);
            actionArrayPlayabilityTextE[i][0][1] = new JTextField(String.valueOf(actionArrayPlayability[i][4][1]));
            actionArrayPlayabilityTextE[i][0][1].setFont(f0);
            actionArrayPlayabilityTextE[i][0][1].setSize(40, 40);
            panelActionE.add(actionArrayPlayabilityTextE[i][0][1]);
        }
      //  panel1.add(panelActionE);

        final var frame = new JFrame();

        new Dimension(500, 800);
        final var frameDimN = new Dimension(500, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(100, 100);
        frame.setPreferredSize(new Dimension(500, 800));
        frame.setTitle("Hole Card Action ");
        frame.setMaximumSize(frameDimN);
        frame.setMinimumSize(frameDimN);

        // panel.add(panel0);
        panel.add(panel1);

        frame.add(panel);

        panel.repaint();
        frame.pack();
        frame.setVisible(true);
    }

}