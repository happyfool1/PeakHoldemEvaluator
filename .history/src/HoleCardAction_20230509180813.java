//package evaluate_streets;

import java.io.File;

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
 * The purpose of this class ( and the previous HandRange class) is to select an action
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
    static final int HANDS = 169;

    static final int NUM_ROWS = 13;

    static final int NUM_COLS = 13;

    static final int SUITED = 0;

    static final int OFFSUIT = 1;

    static final int PAIR = 2;

    static final int INVALID = 2;

    // THis is the only public data and is what the class is all about
    int[][][] actionArray = new int[PLAYERS][5][2];

    /*- 
     * Hand order array. 
     * This array based EV calculations
     */
    static HandRangeOrder order = new HandRangeOrder();

    static boolean rangeRead;

    static boolean firstTime = true;

    /*-
     * This array represents the commonly used 13 X 13 matrix to represent suited,
     * ofsuit, and pairs An element is true if that card is in the range.
     */
    int[] rangeArray = new int[HANDS];

    /*- 
     *  Array of all possible 2 card combinations - 169 hands.  From PioSolver
    */
    private static int[] handsArrayPioSolver = { UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG, UTG,
            UTG, UTG,
            UTG, UTG, MP, MP, CO, CO, CO, BU, BU, BU, UTG, UTG, UTG, UTG, UTG, MP, CO, BU, BU, BU, SB, SB, SB, UTG, MP,
            MP, UTG, UTG, MP, CO, BU, SB, SB, SB, SB, SB, UTG, CO, CO, CO, UTG, UTG, CO, BU, BU, SB, SB, SB, SB, BU, BU,
            BU, BU, BU, UTG, MP, CO, BU, SB, SB, NO, NO, BU, SB, SB, SB, SB, BU, UTG, MP, CO, BU, SB, NO, NO, BU, SB,
            SB, SB, SB, SB, SB, UTG, MP, CO, SB, SB, NO, BU, SB, SB, NO, NO, NO, SB, SB, UTG, UTG, BU, SB, NO, BU, SB,
            SB, NO, NO, NO, NO, SB, SB, UTG, MP, BU, SB, BU, SB, NO, NO, NO, NO, NO, NO, SB, SB, MP, BU, SB, SB, NO, NO,
            NO, NO, NO, NO, NO, NO, NO, NO, CO, SB, SB, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, CO };

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
    static int[] playabilityArrayNumeric = new int[HANDS];

    static int[] playabilityArrayOrder = new int[HANDS];

    static int[] orderArray = new int[HANDS];

    private static String path = EvalData.applicationDirectory; // Default but can be set

/*- Array of all possible 2 card combinations - 169 hands. @formatter:off  */
private static final String[] handsArray = { "AA", "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s",
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

/*- Equilab Equity vs. 5 random hands - Playability @formatter:off  */
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


 /*-  ******************************************************************************
  * Constructor
  ****************************************************************************** */
 HoleCardAction(){
    for (int i = 0; i < HANDS; ++i) {
        rangeArray[i] = NOT_RANGE;
    }
    if (rangeRead) {
        return;
    }
    for (int i = 0; i < PLAYERS; ++i) {
        for (int j = 0; j < 5; ++j) {
            for (int k = 0; k < 2; ++k) {
                this.actionArray[i][j][k] = -1;
            }
        }
    }
    order.readRange(path + "PlayabilityOrder.ser" );
    createPlayabilityArrayNumeric();
    rangeRead = true;
}

/*- ******************************************************************************
 *Set path for all files
 *******************************************************************************/
  static void setPath (String p) {
           path = p;
   }
  /*-  ***************************************************************************
   * Create  playabilityArrayNumeric 
   * The array will contain values from 1 - 169
   * AA is 169, 72o is 1
   *******************************************************************************/
  static void createPlayabilityArray  () {
     for (int k = 0, num = 169, i = 0; i < HANDS; ++i) {
      k = getHandStringToIndex(playabilityArray[i]);
      playabilityArrayNumeric[k] = num--;
      }
   }
    
    
  /*-  ******************************************************************************
  * Create  playabilityArrayNumeric 
  * The array will contain values from 1 - 169
  * AA is 169, 72o is 1
  * Create  playabilityArrayOrder 
  * AA is 0, AKs is 4, the reverse of playabilityArrayNumeric 
  * Copy RangeOrder values to array   TODO 
   ****************************************************************************** */
        static void createPlayabilityArrayNumeric () {
                for (int k = 0, i = 0; i < HANDS; ++i) {
                    k = getHandStringToIndex(playabilityArray[i]);
                    playabilityArrayNumeric[k] = 169 - k;
                    playabilityArrayOrder[i] = k;
                    orderArray[i] = order.getHandIndex(i);
                }
            }
    
    
        /*- Get playabilityNumeric value */
        static int getPlayabilityNumeric(int index) {
            return 	playabilityArrayNumeric[index]; 
        }
    
    
        /*- Get playabilityOrder value */
        static int getPlayabilityOrder(int index) {
            return 	playabilityArrayOrder[index]; 
        }
    
    
        /*- Get order value */
        static int getOrder(int index) {
            return 	orderArray[index]; 
        }
    
    
        /*- Get hand string from rankingArray using index. */
        String getPlayabilityArrayString(int index) {
            return handsArray[order.getHandIndex(index)];
        }
    
    
        /*- Get hand string from drawAndMadeArray using index. */
        String getHandString(int index) {
            return handsArray[index];
        }
    
    
        /*- Find hand in drawAndMadeArray and return index Arg0 - hand in string format. */
        static int getHandStringToIndex(String hand) {
            for (int $ = 0; $ < HANDS; ++$) {
                if (handsArray[$].equals(hand)) {
                    return $;
                }
            }
            Logger.log("ERROR HandRange handStringToIndex() Invalid hand " + hand);
            return -1;
        }
    
    
        /*- Clear hand range. */
        void clearRange() {
            for (int i = 0; i < HANDS; ++i) {
                rangeArray[i] = NOT_RANGE;
            }
        }
    
/*-  ***************************************************************************
*	Read a rangeArray from a disk file Arg0 - The full path name. 
 ****************************************************************************** */
     boolean readRange(String path) {
		if (!new File(path).exists()) {
			Logger.log("ERROR HandRange readRange() File does not exist " + path);
			return false;
		}
		this.rangeArray = FileUtils.readFile(path, this.rangeArray);
		return this.rangeArray != null;
	}

	/*- ***************************************************************************
 	*	Write a HandRange object to a disk file Arg0 - The full path name. 
 	*******************************************************************************/
     void writeArray(String path) {
        System.out.println("//writeArray() " + path);
		FileUtils.writeFile(path, this.actionArray);
	}


}