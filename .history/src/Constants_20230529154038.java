//package evaluate_streets;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

/*-  *****************************************************************************
 * Constant definitions used throughout entire project.
 * Should be in every class in this project.
 * In every Class that uses this add to class 
 * 		class xxx implements Constants 
 * 
  * @author PEAK_
  ***************************************************************************** */
public interface Constants {

	static final int HANDS = 169;
	static final int NUM_ROWS = 13;
	static final int NUM_COLS = 13;
	static final int SUITED = 0;
	static final int OFFSUIT = 1;
	static final int PAIR = 2;
	static final int INVALID = 2;

	static int COMBINATIONS = 1326;

	/*- **************************************************************************** 
	*  Player actions preflop and post flop
	*******************************************************************************/
	int FOLD = 0;

	int CHECK = 1;

	int BET1 = 2;

	int CALL_BET1 = 3;

	int LIMP = 4;

	int BET2 = 5;

	int OPEN = 6;

	int CALL_BET2 = 7;

	int CALL = 8;

	int BET3 = 9;

	int CALL_BET3 = 10;

	int BET4 = 11;

	int CALL_BET4 = 12;

	int ALLIN = 13;

	int CALL_ALLIN = 14;

	BigDecimal zeroBD = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);

	/*- *****************************************************************************
	 * Suits
	  ******************************************************************************/
	int SPADE = 0;

	int CLUB = 1;

	int DIAMOND = 2;

	int HEART = 3;

	String[] SUITS_ST = { "s", "c", "d", "h" };

	String[] SUITS2_ST = { "Spade", "Club", "Diamond", "Heart" };

	/*- *****************************************************************************
	 * Card
	  ******************************************************************************/
	int ACE = 12;

	int KING = 11;

	int QUEEN = 10;

	int JACK = 9;

	int TEN = 8;

	int NINE = 7;

	int EIGHT = 6;

	int SEVEN = 5;

	int SIX = 4;

	int FIVE = 3;

	int FOUR = 2;

	int THREE = 1;

	int TWO = 0;

	/*- *****************************************************************************
	 * Streets
	  ******************************************************************************/
	int PREFLOP = 0;

	int FLOP = 1;

	int TURN = 2;

	int RIVER = 3;

	int SHOWDOWN = 4;

	int SUMMARY = 5;

	String[] STREET_ST = { "Preflop", "Flop", "Turn", "River", "Showdown", "Summary" };

	/*- *****************************************************************************
	 * Basic definitions
	  ******************************************************************************/
	int PLAYERS = 6;

	int MAXFOLDED = PLAYERS - 1;

	int ORBITS = 4;

	int STREET = 4;

	int NO = 0;

	int SB = 0;

	int BB = 1;

	int UTG = 2;

	int MP = 3;

	int CUTOFF = 4;

	int CO = 4;

	int BUTTON = 5;

	int BU = 5;

	int POSITIONS = 6;

	String[] POSITION_ST = { "Small blind", "Big blind", "Under the gun", "Middle", "Cutoff", "Button" };

	String[] POSITION_ST2 = { "SB", "BB", "UTG", "MP", "CO", "BU" };

	/*- **************************************************************************** 
	* Actions as defined in HandRange.  TODO not in current version
	*******************************************************************************/
	int NOT_RANGE = -1;

	int IN_RANGE = 1;

	int RANGE_UNUSED = 0;

	int RANGE_FOLD = 0;

	int RANGE_LIMP = 10;

	int RANGE_OPEN = 20;

	int RANGE_CALL = 30;

	int RANGE_BET3 = 40;

	int RANGE_CALL_BET3 = 50;

	int RANGE_BET4 = 60;

	int RANGE_CALL_BET4 = 70;

	int RANGE_ALLIN = 80;

	int RANGE_CALL_ALLIN = 90;

	int RANGE_NOT_RANGE = -1;

	// Can not use vakues as index, must delete 0
	String[] RANGE_ST = { "Fold", "Limp", "Open", "Call", "3 Bet", "Call 3 bet", "4 Bet", "Call 4 Bet", "All-In",
			"Call All-In" };

	/*- **************************************************************************** 
	* Actions to a player - Preflop
	*******************************************************************************/
	int PREFLOP_LIMP = 0;

	int PREFLOP_BET1 = 0;

	int PREFLOP_OPEN = 1;

	int PREFLOP_BET2 = 1;

	int PREFLOP_BET3 = 2;

	int PREFLOP_BET4 = 3;

	int PREFLOP_ALLIN = 4;

	int PREFLOP_CALL_ALLIN = 5;

	String[] PREFLOP_ST = { "Limp 1 Bet", "Open 2 Bet", "3 Bet", "4 Bet", "All-In", "Call All-In" };

	/*- **************************************************************************** 
	* Actions to a player - Post Flop
	*******************************************************************************/
	int POSTFLOP_CHECK = 0;

	int POSTFLOP_BET1 = 1;

	int POSTFLOP_BET2 = 2;

	int POSTFLOP_BET3 = 3;

	int POSTFLOP_BET4 = 4;

	int POSTFLOP_ALLIN = 5;

	int POSTFLOP_CALL_ALLIN = 6;

	String[] POSTFLOP_ST = { "Check", "1 Bet", "2 Bet", "3 Bet", "4 Bet", "All-In", "Call All-In" };

	/*- **************************************************************************** 
	*  Values for relative positions
	*******************************************************************************/
	int RP_FIRST = 0;

	int RP_FIRSTHU = 1;

	int RP_MIDDLE = 2;

	int RP_LAST = 3;

	int RP_LASTHU = 4;

	String[] RP_ST = { "First", "First HU", "Middle", "Last", "Last HU" };

	/*- **************************************************************************** 
	*  Player types
	*******************************************************************************/
	int FISH = 0;

	int NIT = 1;

	int LAG = 2;

	int TAG = 3;

	int REG = 4;

	int HERO = 8;

	int HEROX = 9;

	int AVERAGE = 10;

	int AVERAGEX = 11;

	int NUM_TYPES = 12;

	String[] PLAYER_TYPE_ST = { "Fish", "Nit", "Lag", "Tag", "Regular", "Hero", "Hero alt", "Average", "Average alt" };

	/*- *****************************************************************************
		 * Flop Only
		  ******************************************************************************/
	int FLOP_NONE = 0;

	int FLOP_BOARD_PAIRED = 1;

	int FLOP_BOARD_SET = 2;

	int FLOP_POCKET_HIGH_CARDS = 3;

	int FLOP_POCKET_OVERCARDS = 4;

	int FLOP_POCKET_ACE_HIGH = 5;

	int FLOP_WEAK_PAIR = 6;

	int FLOP_MIDDLE_PAIR = 7;

	int FLOP_OVER_PAIR = 8;

	int FLOP_SIZE = 9;

	// Index array with same index as OneHand.drawAndMadeArray
	String[] FLOP_ARRAY_ST = { "None", "Board Paired", "Board Set", "Pocket High Cards", "Pocket Overcards",
			"Pocket Ace High", "Weak Pair", "Middle Pair", "Over Pair " };

	/*- *****************************************************************************
		 * Best Draws
		  ******************************************************************************/
	int BEST_NONE = 0;

	int BEST_OVERCARDS = 1;

	int BEST_ACE_HIGH = 2;

	int BEST_WEAK_PAIR = 3;

	int BEST_MIDDLE_PAIR = 4;

	int BEST_POCKET_PAIR_BELOW_TP = 5;

	int BEST_OVER_PAIR = 6;

	int BEST_TOP_PAIR = 7;

	int BEST_TWO_PAIR = 8;

	int BEST_SET = 9;

	int BEST_GUTSHOT = 10;

	int BEST_GUTSHOT_PAIR = 11;

	int BEST_GUTSHOT_HIGH = 12;

	int BEST_STRAIGHT_DRAW = 13;

	int BEST_OESD = 14;

	int BEST_OESD_PAIR = 15;

	int BEST_FLUSH_DRAW = 16;

	int BEST_FLUSH_DRAW_PAIR = 17;

	int BEST_FLUSH_DRAW_GUTSHOT = 18;

	int BEST_FLUSH_DRAW_OESD = 19;

	int BEST_HANDS_SIZE = 20;

	String[] BEST_ST = { "None", "Over cards", "Ace high", "Pair", "Weak pair", "Middle pair",
			"Pocket pair below top pair", "Over pair", "Top pair", "Two pair", "Set", "Gutshot", "Gutshot + pair",
			"Gutshot + high", "Straight draw", "OESD", "OESD + pair", "Flush draw", "Flush draw + pair",
			"Flush draw + gutshot", "Flush draw + OESD" };

	/*- *****************************************************************************
	 * Board analysis
	  ******************************************************************************/
	int BOARD_DRY = 0;

	int BOARD_WET = 1;

	int BOARD_NOT_WET_DRY = 2;

	int BOARD_RAINBOW = 3;

	int BOARD_ACE_HIGH = 4;

	int BOARD_HIGH_CARD = 5;

	int BOARD_PAIR = 6;

	int BOARD_2PAIR = 7;

	int BOARD_SET = 8;

	int BOARD_FULL = 9;

	int BOARD_QUADS = 10;

	int BOARD_GAP0 = 11;

	int BOARD_GAP1 = 12;

	int BOARD_GAP2 = 13;

	int BOARD_2F = 14;

	int BOARD_3F = 15;

	int BOARD_4F = 16;

	int BOARD_5F = 17;

	int BOARD_ARRAY_SIZE = 18;

	// Index array with same index as OneHand.boardArray
	String[] BOARD_ARRAY_ST = { "Dry", "Wet", "Not wet or dry", "Rainbow", "Ace high", "High Card", "Pair", "Two Pair",
			"Set", "Full house", "Quads", "Gap 0", "Gap 1", "Gap 2", "2 suited", "3 suited", "4 suited", "Flush" };

	/*- *****************************************************************************
	 * Made hand types
	  ******************************************************************************/
	int MADE_NONE = 0;

	int MADE_PAIR = 1;

	int MADE_TWO_PAIR = 2;

	int MADE_SET = 3;

	int MADE_STRAIGHT = 4;

	int MADE_FLUSH = 5;

	int MADE_FULL_HOUSE = 6;

	int MADE_FOUR_OF_A_KIND = 7;

	int MADE_STRAIGHT_FLUSH = 8;

	int MADE_ROYAL_FLUSH = 9;

	int MADE_HANDS_SIZE = 10;

	String[] MADE_ARRAY_ST = { "No hand", "Any pair", "Two pair", "Set", "Straight", "Flush", "Full house",
			"Straight flush", "Royal flush" };

	/*- *****************************************************************************
	 * Draws
	  ******************************************************************************/
	int DRAW_NONE = 0;

	int DRAW_BOARD_PAIR = 1;

	int DRAW_PAIRED_WITH_BOARD = 2;

	int DRAW_GUTSHOT = 3;

	int DRAW_STRAIGHT = 4;

	int DRAW_OESD = 5;

	int DRAW_FLUSH = 6;

	int DRAW_FLUSH_OESD = 7;

	int DRAW_HANDS_SIZE = 8;

	String[] DRAW_ARRAY_ST = { "No draw", "Board pair", "Paired with board", "Gutshot", "Straight", "OESD", "Flush",
			"Flush OESD" };

	/*- *****************************************************************************
	 * Possible draws from board only - Flop and Turn
	  ******************************************************************************/
	int POSSIBLE_PAIR = 0;

	int POSSIBLE_TWO_PAIR = 1;

	int POSSIBLE_SET = 2;

	int POSSIBLE_STRAIGHT = 3;

	int POSSIBLE_FLUSH = 4;

	int POSSIBLE_FULL_HOUSE = 5;

	int POSSIBLE_FOUR_OF_A_KIND = 6;

	int POSSIBLE_STRAIGHT_FLUSH = 7;

	int POSSIBLE_ROYAL_FLUSH = 8;

	int POSSIBLE_SIZE = 9;

	String[] POSSIBLE_ARRAY_ST = { "Pair", "2 pair", "Set", "Straight", "Flush", "Full House", "4 of a Kind",
			"Straight Flush", "Royal Flush," };

	/*- *****************************************************************************
	 * HLM analysis of Flop - High Medium Low
	 ******************************************************************************/
	int LLL = 0;
	int MLL = 1;
	int MML = 2;
	int MMM = 3;
	int HLL = 4;
	int HML = 5;
	int HMM = 6;
	int HHL = 7;
	int HHM = 8;
	int HHH = 9;
	int HML_FLOP_SIZE = 10;
	String[] HML_FLOP_ST = { "LLL", "MLL", "MML", "MMM", "HLL", "HML", "HMM", "HHL", "HHM", "HHH" };

	int LLLL = 0;
	int MLLL = 1;
	int MMLL = 2;
	int MMML = 3;
	int MMMM = 4;
	int HLLL = 5;
	int HMLL = 6;
	int HMML = 7;
	int HMMM = 8;
	int HHLL = 9;
	int HHML = 10;
	int HHMM = 11;
	int HHHL = 12;
	int HHHM = 13;
	int HHHH = 14;
	int HML_TURN_SIZE = 15;
	String[] HML_TURN_ST = { "LLLL", "MLLL", "MMLL", "MMML", "MMMM", "HLLL", "HMLL", "HMML", "HMMM", "HHLL", "HHML",
			"HHLMM",
			"HHHL", "HHHM", "HHHH" };

	int LLLLL = 0;
	int MLLLL = 1;
	int MMLLL = 2;
	int MMMLL = 3;
	int MMMML = 4;
	int MMMMM = 5;
	int HLLLL = 6;
	int HMLLL = 6
	int HMMLL = 7;
	int HMMML = 8;
	int HMMMM = 9;
	int HHLLL = 10;
	int HHMLL = 11;
	int HHMML = 12;
	int HHMML = 13;
	

	int HMLLL = 7;
	int HMMLL = 8;
	int HMMML = 9;

	int HMMMML = 10;
	int HHMMM = 10;
	int HHHLL = 11;
	int HHHML = 12;
	int HHHMM = 13;
	int HHHHL = 14;
	int HHHHM = 15;
	int HHHHH = 16;
	int HML_RIVER_SIZE = 17;

	String[] HML_RIVER_ST = { "LLLLL", "MLLLL", "MMLLL", "MMMLL", "MMMML", "MMMMM", "HLLLL",
			"HMLLL", "HMMLL", "HMMML", "HHMMM", "HHHLL", "HHHML", "HHHMM", "HHHHL", "HHHHM", "HHHHH" };

	/*- *****************************************************************************
	* TODO
	* Flop type using gaps
	*  - Two adjacent Car pairds with same value
	* diff1- Two adjacent cards with a 1 difference in value JT
	* diff2- Two adjacent cards with a 2 difference in value J9
	* diff3- Two adjacent cards with a 3 difference in value J8
	* Experimental.
	* Want to see if there is any interesting pattern when we do millions of hands
	 ******************************************************************************/
	int FLOP_INDEX_SIZE = 18;
	String[] FLOP_INDEX_ST = { "diff1 diff1 diff1", "pair diff1 diff1", "diff1 pair diff1", "diff1 diff1 pair",
			"pair pair diff1", "pair diff1 diff2", "pair pair diff2", "pair diff1 diff3", "pair pair diff3",
			"pair diff2 diff2", "pair diff3 diff2", "pair diff2 diff3", "pair pair diff4", "pair diff3 diff3",
			"pair diff4 diff3", "pair diff4 diff2", "pair diff4 diff4", "pair pair pair" };

	/*- *****************************************************************************
	 * TODO
	  ******************************************************************************/
	String[] TEXTURE_ST = { "None", "1 gap 0", "2 gap 0", "1 gap 1", "2 gap 1", "1 gap2", "2 gap2", "1 gap 0  1 gap 1",
			"1 gap 0  1 gap 2", "2 suit  1 gap 0", "2 suit  2 gap 0", "2 suit  1 gap 1", "2 suit  2 gap 1",
			"2 suit   1 gap2", "2 gap2", "2 suit  1 gap 0  1 gap 1", "2 suit  1 gap 0  1 gap 2", "3 suit  1 gap 0",
			"3 suit   3 gap 0", "3 suit  1 gap 1", "3 suit  3 gap 1", "3 suit  1 gap3", "3 gap3",
			"3 suit   1 gap 0  1 gap 1", "3 suit  1 gap 0  1 gap 2", "pair  1 gap 0", "pair  1 gap 1", "pair  1 gap 2",
			"pair 2 suit  1 gap 0", "pair 2 suit  1 gap 1", "pair 2 suit  1 gap 2" };

	/*- *****************************************************************************
	 * Types of 1755 possible Flops  
	 ******************************************************************************/
	int SET = 0;

	int SUITED_PAIR = 1;

	int ALL_SUITED = 2;

	int ALL_OFFSUIT = 3;

	int TWO_SUITED_LOW = 4;

	int TWO_SUITED_HIGH = 5;

	int TWO_SUITED_HIGH_LOW = 6;

	int TYPE1755_SIZE = 7;

	String[] TYPE_OF_1755_ST = { "Set", "Suited pair", "All suited", "All offsuit", "Two suited low", "Two suited high",
			"Two suited high low" };

	/*- *****************************************************************************
	 * Ed Miller Flop types 
	  ******************************************************************************/
	int TYPE_NONE = 0;

	int TYPE1 = 1;

	int TYPE2 = 2;

	int TYPE3 = 3;

	String[] TYPE_ST = { "None", "Type 1", "Type 2", "Type 3" };

	/*- *****************************************************************************
	 * Hand value
	  ******************************************************************************/
	int HAND_VALUE_NOTHING = 0;

	int HAND_VALUE_POOR = 1;

	int HAND_VALUE_FAIR = 2;

	int HAND_VALUE_OK = 3;

	int HAND_VALUE_ABOVE_AVERAGE = 4;

	int HAND_VALUE_VERY_GOOD = 5;

	int HAND_VALUE_GREAT = 6;

	int HAND_VALUE_EXCELENT = 7;

	int HAND_VALUE_THE_NUTS = 8;

	int HAND_VALUE_SIZE = 8;

	String[] HAND_VALUE_ST = { "Nothing", "Poor", "Poor", "Fair", "OK", "Above average", "Very good", "Great",
			"Excelent", "The NUTS" };

	String[] TYPE_OF_FLOP_ST = { "Coordinated", "Uncoordinated", "Paired", "Rainbow", "Monotone" };

	String[] PLAYER_ACTIONS_ST = { "Fold", "Check", "1 Bet", "Call 1 Bet", "Limp", "2 Bet", "Open", "Call 2 Bet",
			"Call", "3 Bet", "Call 3 Bet", "4 Bet", "Call 4 Bet", "All-In", "Call All-in" };

	/*- *****************************************************************************
	 *Colors for GUI
	  ******************************************************************************/
	Color RED = Color.red;

	Color BLUE = Color.blue;

	Color GREEN = Color.green;

	Color YELLOW = Color.yellow;

	Color ORANGE = Color.orange;

	Color PINK = Color.pink;

	Color MAGENTA = Color.magenta;

	Color CYAN = Color.cyan;

	Color GRAY = Color.gray;

	Color DARK_GRAY = Color.darkGray;

	Color LIGHT_GRAY = Color.lightGray;

	Color WHITE = Color.white;

	Color BLACK = Color.black;

	Color IVORY = new Color(255, 255, 240);

	Color BEIGE = new Color(245, 245, 220);

	Color WHEAT = new Color(245, 222, 179);

	Color TAN = new Color(210, 180, 140);

	Color KHAKI = new Color(195, 176, 145);

	Color SILVER = new Color(192, 192, 192);

	Color DARK_SLATE_GRAY = new Color(47, 79, 79);

	Color SLATE_GRAY = new Color(112, 128, 144);

	Color LIGHT_SLATE_GRAY = new Color(119, 136, 153);

	Color NAVY = new Color(0, 0, 128);

	Color MIDNIGHT_BLUE = new Color(25, 25, 112);

	Color CORNFLOWER_BLUE = new Color(100, 149, 237);

	Color STEEL_BLUE = new Color(70, 130, 180);

	Color ROYAL_BLUE = new Color(65, 105, 225);

	Color DODGER_BLUE = new Color(30, 144, 255);

	Color DEEP_SKY_BLUE = new Color(0, 191, 255);

	Color SKY_BLUE = new Color(135, 206, 235);

	Color LIGHT_SKY_BLUE = new Color(135, 206, 250);

	Color TEAL = new Color(0, 128, 128);

	Color DARK_CYAN = new Color(0, 139, 139);

	Color CADET_BLUE = new Color(95, 158, 160);

	Color AQUAMARINE = new Color(127, 255, 212);

	Color TURQUOISE = new Color(64, 224, 208);

	Color MEDIUM_TURQUOISE = new Color(72, 209, 204);

	Color PALE_TURQUOISE = new Color(175, 238, 238);

	Color GREEN_YELLOW = new Color(173, 255, 47);

	Color CHARTREUSE = new Color(127, 255, 0);

	Color LAWN_GREEN = new Color(124, 252, 0);

	Color LIME_GREEN = new Color(50, 205, 50);

	Color FOREST_GREEN = new Color(34, 139, 34);

	Color OLIVE_DRAB = new Color(107, 142, 35);

	Color DARK_KHAKI = new Color(189, 183, 107);

	Color GOLDEN_ROD = new Color(218, 165, 32);

	Color DARK_GOLDEN_ROD = new Color(184, 134, 11);

	Color SADDLE_BROWN = new Color(139, 69, 19);

	Color SIENNA = new Color(160, 82, 45);

	Color CHOCOLATE = new Color(210, 105, 30);

	Color PERU = new Color(205, 133, 63);

	Color ROSY_BROWN = new Color(188, 143, 143);

	Color SANDY_BROWN = new Color(244, 164, 96);

	Color LIGHT_SALMON = new Color(255, 160, 122);

	Color SALMON = new Color(250, 128, 114);

	Color DARK_SALMON = new Color(233, 150, 122);

	Color ORANGE_RED = new Color(255, 69, 0);

	Color TOMATO = new Color(255, 99, 71);

	Color CORAL = new Color(255, 127, 80);

	Color DARK_ORANGE = new Color(255, 140, 0);

	Color LIGHT_CORAL = new Color(240, 128, 128);

	Color HOT_PINK = new Color(255, 105, 180);

	Color DEEP_PINK = new Color(255, 20, 147);

	Color LIGHT_PINK = new Color(255, 182, 193);

	Color PLUM = new Color(221, 160, 221);

	Color VIOLET = new Color(238, 130, 238);

	Color ORCHID = new Color(218, 112, 214);

	Color MEDIUM_ORCHID = new Color(186, 85, 211);

	Color DARK_ORCHID = new Color(153, 50, 204);

	Color PURPLE = new Color(128, 0, 128);

	Color MEDIUM_PURPLE = new Color(147, 112, 219);

	Color THISTLE = new Color(216, 191, 216);

	Color GHOST_WHITE = new Color(248, 248, 255);

	Color LAVENDER = new Color(230, 230, 250);

	Color MISTY_ROSE = new Color(255, 228, 225);

	Color ANTIQUE_WHITE = new Color(250, 235, 215);

}
