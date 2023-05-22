//package evaluate_streets;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*-
 * 
 * This class will allow the user to display and edit combined ranges.
 * A work in progress.....
 * 
 * @author PEAK_
 * 
 */
public class BuildCombinedRange implements Constants {

	private static final int HANDS = 169;

	/*- The HANDS buttons objects in matrix. */
	static JButton[] buttonArr = new JButton[HANDS];

	static PreflopRanges range = new PreflopRanges();

	static String path = EvalData.applicationDirectory;

	private static final int NUM_ROWS = 13;
	private static final int NUM_COLS = 13;

	private static final Color UNUSED = Color.LIGHT_GRAY;
	private static final Color LIMP = Color.PINK;
	private static final Color OPEN = Color.CYAN;
	private static final Color CALL = Color.MAGENTA;
	private static final Color BET3 = Color.ORANGE;
	private static final Color BET3CALL = Color.YELLOW;
	private static final Color BET4 = Color.BLUE;
	private static final Color BET4CALL = Color.RED;
	private static final Color ALLINCALL = Color.GRAY;
	private static final Color ALLIN = Color.GREEN;

	private static final Color x0 = new Color(215, 215, 255);
	private static final Color x1 = new Color(161, 161, 255);
	private static final Color x2 = new Color(95, 95, 255);
	private static final Color x3 = new Color(87, 87, 255);

	private static final Color x4 = new Color(255, 215, 255);
	private static final Color x5 = new Color(255, 161, 255);
	private static final Color x6 = new Color(255, 95, 255);
	private static final Color x7 = new Color(255, 87, 255);

	private static final Color x8 = new Color(255, 255, 215);
	private static final Color x9 = new Color(255, 255, 161);
	private static final Color x10 = new Color(255, 255, 95);
	private static final Color x11 = new Color(255, 255, 87);

	private static final Color x12 = new Color(215, 215, 255);
	private static final Color x13 = new Color(161, 161, 255);
	private static final Color x14 = new Color(95, 95, 255);
	private static final Color x15 = new Color(87, 87, 255);

	private static final Color x16 = new Color(239, 16, 255);
	private static final Color x17 = new Color(183, 72, 255);
	private static final Color x18 = new Color(145, 110, 255);
	private static final Color x19 = new Color(89, 166, 255);

	private static final Color x20 = new Color(235, 1, 20);
	private static final Color x21 = new Color(161, 1, 255);
	private static final Color x22 = new Color(195, 1, 60);
	private static final Color x23 = new Color(119, 1, 136);

	private static final Color x24 = new Color(150, 255, 150);
	private static final Color x25 = new Color(100, 255, 100);
	private static final Color x26 = new Color(60, 255, 60);
	private static final Color x27 = new Color(10, 255, 10);

	private static final int ranges = 10;
	/*- Values and color for range steps. */
	private static final double[] handsArray = { RANGE_UNUSED, RANGE_LIMP, RANGE_CALL, RANGE_OPEN, RANGE_CALL_BET3,
			RANGE_BET3, RANGE_CALL_BET4, RANGE_BET4, RANGE_CALL_ALLIN, RANGE_ALLIN };
	private static final String[] namesArray = { " Unused ", " Limp ", " Call ", " Open ", " Call 3 Bet ", " 3 Bet ",
			" Call 4 Bet ", " 4 Bet ", " All In Call ", " All In " };

	// private static Color[] colorArray = {
	// c1,c2,c3,c4,BET3CALL,BET3,BET4CALL,BET4,ALLINCALL,ALLIN};
	// private static Color[] colorArray = {
	// UNUSED,LIMP,CALL,OPEN,BET3CALL,BET3,BET4CALL,BET4,ALLINCALL,ALLIN};
	private static final Color[] colorArray = { UNUSED, LIMP, x0, x1, x4, x5, x8, x9, x12, x13 };

	/*- Color and value. */
	private static final JTextField[] fieldArray = new JTextField[10];

	/*- - Number of ranges combined in matrix. */
	static JFrame frame;

	static int r = 255;

	static int g = 255;

	static int b = 255;

	private static boolean frameOpen = false;

	/*- Main */
	public static void main(String[] args) {
		start();
	}

	static void start() {

		buildOneRange(path);
		doFrame();
		// cc();
		rangeArrayToButtonColor();
	}

	/*- Display preflop range */
	static void buildOneRange(String p) {
		final var fullPath = p + "Hero\\Preflop\\Button\\";
		range.buildRange(fullPath);
	}

	/*- colors */
	private static Color getColor(int i) {

		r = 170 - i;
		// g= i;

		b = 170 - i;

		return new Color(r, g, b);

	}

	private static void cc() {
		for (int i = 0; i < HANDS; ++i) {

			final var st = String.valueOf(i);
			buttonArr[i].setBackground(getColor(i));
			buttonArr[i].setText(st);
			Logger.log(new StringBuilder().append(i).append(" ").append(r).append(" ").append(g).append("  ").append(b)
					.toString());
		}
	}

	/*- - Convert range array to button color Sets color in buttonArray. */
	private static void rangeArrayToButtonColor() {
		for (int i = 0; i < HANDS; ++i) {
			switch (range.getRangeArray(i)) {
			case RANGE_UNUSED -> buttonArr[i].setBackground(colorArray[0]);
			case RANGE_LIMP -> buttonArr[i].setBackground(colorArray[1]);
			case RANGE_CALL -> buttonArr[i].setBackground(colorArray[2]);
			case RANGE_OPEN -> buttonArr[i].setBackground(colorArray[3]);
			case RANGE_CALL_BET3 -> buttonArr[i].setBackground(colorArray[4]);
			case RANGE_BET3 -> buttonArr[i].setBackground(colorArray[5]);
			case RANGE_CALL_BET4 -> buttonArr[i].setBackground(colorArray[6]);
			case RANGE_BET4 -> buttonArr[i].setBackground(colorArray[7]);
			case RANGE_CALL_ALLIN -> buttonArr[i].setBackground(colorArray[8]);
			case RANGE_ALLIN -> buttonArr[i].setBackground(colorArray[9]);
			default -> {
				Logger.logError("ERROR Play(DrillIneractive.preflopShowRange() program bug");
				Crash.log("$$$");
			}
			}
		}
	}

	/*- - Create the primary frame. */
	private static void doFrame() {
		// Primary frame
		if (frameOpen) {
			return;
		}
		frameOpen = true;

		frame = new JFrame("Combination range");

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent i) {
				i.getWindow().dispose();
			}
		});

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLocation(200, 50);

		final var panel0 = new JPanel();
		new Font(Font.SERIF, Font.BOLD, 10);
		final var f0 = new Font(Font.SERIF, Font.BOLD, 18);
		final var panel1 = new JPanel();

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final var frameDim = new Dimension(400, 500);
		final var panel0Dim = new Dimension(400, 500);
		frame.setFont(f0);
		frame.setMaximumSize(frameDim);
		frame.setMinimumSize(frameDim);
		frame.setLocation(50, 350);
		panel0.setMaximumSize(panel0Dim);
		panel0.setMinimumSize(panel0Dim);
		panel0.setSize(400, 500);

		frame.setBackground(Color.WHITE);

		panel0.setLayout(new BoxLayout(panel0, BoxLayout.Y_AXIS));
		panel0.setFont(f0);

		// Hand matrix

		final var panel1Layout = new GridLayout(NUM_ROWS, NUM_COLS);
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(panel1Layout);

		panel1.setFont(f0);

		final var panel3 = new JPanel();

		for (int i = 0; i < HANDS; ++i) {
			buttonArr[i] = new JButton(PreflopRanges.getRangeArrayString(i));
			buttonArr[i].setBackground(UNUSED);
			buttonArr[i].setForeground(Color.BLACK);
			panel1.add(buttonArr[i]);
		}

		final var f3 = new Font(Font.SERIF, Font.BOLD, 12);
		panel3.setSize(700, 40);
		panel3.setBackground(Color.gray);
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		final var dim3 = new Dimension(700, 40);
		panel3.setMaximumSize(dim3);
		panel3.setMinimumSize(dim3);

		for (int i = 0; i < ranges; ++i) {
			fieldArray[i] = new JTextField();
			fieldArray[i].setFont(f3);
			fieldArray[i].setSize(40, 40);
			fieldArray[i].setText(namesArray[i]);
			fieldArray[i].setBackground(Color.GRAY);
			fieldArray[i].setBackground(colorArray[i]);
			panel3.add(fieldArray[i]);
		}
		panel0.add(panel3);

		panel0.add(panel1);

		frame.add(panel0);
		frame.pack();
		frame.setVisible(true);
	}
}
