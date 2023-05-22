//package evaluate_streets;
/*-  ******************************************************************************
 *  @author PEAK_ 
 * ****************************************************************************** */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class EditRange implements Constants {

	private static final int HANDS = 169;
	private static final int NUM_ROWS = 13;
	private static final int NUM_COLS = 13;

	private static final Color open = Color.CYAN;
	private static final Color call = Color.YELLOW;
	private static final Color minRaise = Color.PINK;
	private static final Color bet2 = Color.CYAN;
	private static final Color bet3 = Color.RED;
	private static final Color bet4 = Color.RED;
	private static final Color none = Color.LIGHT_GRAY;
	private static final Color control = Color.GREEN;

	private static int offset = 100;

	/*- - Number of ranges combined in matrix. */
	JFrame frame = new JFrame();
	HandRangeMultiple range1 = new HandRangeMultiple();
	HandRange range1 = new HandRange();
	HandRange angeShadow = new HandRange();
	HandRange rangeShadow2 = new HandRange();
	Color range1Color;
	Color rangeShadowColor = Color.GRAY;
	String file1 = "";
	String fileShadow = "";
	String fileShadow2 = "";
	String shadow = "";
	String shadow2 = "";

	boolean cautionMsg;
	boolean duplicateMsg;
	boolean shadowMsg;
	boolean subset;

	/*- The HANDS buttons objects in matrix. */
	JButton[] buttonArr = new JButton[HANDS];

	JButton buttonSave = new JButton("Save");

	/*- Report. */
	JTextField combos1 = new JTextField("Combos1");
	JTextField comboPer1 = new JTextField("Percent1");
	JTextField hands1 = new JTextField("Hands");

	/*- Report. */
	JTextField combos2 = new JTextField("Combos2");
	JTextField comboPer2 = new JTextField("Percen2");
	JTextField hands2 = new JTextField("Hands2");

	JTextField duplicates = new JTextField("");

	JTextField msg = new JTextField("");
	String caution = " Caution - Editing this range can change ";

	/*- Panel title. */
	String title = "";

	/*- File name. */
	String fileName = "";



	boolean edit;
	int hand = -1;
	int x;
	int y;

	/*-
		 * Main 
		 */
	public static void main(String[] s0) {

		new EditRange("HeroX", "Preflop", "Button", "");
	}

	/*-*******************************************************************************
	Constructor
	*********************************************************************************/
	EditRange(String typ, int pos, int betTYpe, boolean raiseOrCall ) {
		hand = -1;
		edit = true;
		fileName = EvalData.applicationDirectory + typ + "HandRangeMultiple.ser";
		shadow = fileShadow = "";
		rangesToEdit();
		doFrame();
		rangeArrayToButtonColor();

		report();
		reportShadow();
	}
	

	/*- Close frame */
	void closeFrame() {
		frame.dispose();
	}

	/*- - Determine range(s) to edit. */
	private void rangesToEdit() {
			range1 = range1.readFromFile(file1);
			range1Color = open;
			return;
		}
	}

	/*- - Convert range array to button color Sets color in buttonArray. */
	private void rangeArrayToButtonColor() {
		int dup = 0;
		for (int i = 0; i < HANDS; ++i) {
			if (range1.isRangeArray(i) > 0) {
				buttonArr[i].setBackground(range1Color);
			}
		}
		// Add shadow range
		if (!"".equals(shadow)) {
			for (int k = 0; k < HANDS; ++k) {
				if (rangeShadow.isRangeArray(k) > 0) {
					buttonArr[k].setBackground(rangeShadowColor);
				}
				if (!"".equals(shadow2) && rangeShadow2.isRangeArray(k) > 0) {
					buttonArr[k].setBackground(rangeShadowColor);
				}
				if ("".equals(shadow2)) {
					if (range1.isRangeArray(k) > 0 && rangeShadow.isRangeArray(k) > 0) {
						buttonArr[k].setBackground(Color.PINK);
						++dup;
					}
				} else if (rangeShadow2.isRangeArray(k) > 0) {
					buttonArr[k].setBackground(rangeShadowColor);
					if (range1.isRangeArray(k) > 0 && rangeShadow.isRangeArray(k) > 0
							&& rangeShadow2.isRangeArray(k) > 0) {
						buttonArr[k].setBackground(Color.PINK);
						++dup;
					}
				}
			}

			if (subset) {
				for (int k = 0; k < HANDS; ++k) {
					if (rangeShadow.isRangeArray(k) > 0) {
						buttonArr[k].setBackground(rangeShadowColor);
					}
					if (range1.isRangeArray(k) > 0 && rangeShadow.isRangeArray(k) > 0) {
						buttonArr[k].setBackground(open);
					}
				}
			}

			if (dup != 0) {
				var st = "";
				final var pattern = "##.0";
				st = new DecimalFormat(pattern).format(dup);
				duplicates.setForeground(Color.BLACK);
				duplicates.setBackground(Color.PINK);
				duplicates.setText(st + " Hands in both ranges ");
			}
		}
		if (hand != -1) {
			buttonArr[hand].setBackground(Color.RED);
		}
	}

	/*- - Create the primary frame. */
	private void doFrame() {
		// Primary frame

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent j0) {
				j0.getWindow().dispose();
			}
		});

		final var panel0 = new JPanel();
		new Font(Font.SERIF, Font.BOLD, 10);
		final var f0 = new Font(Font.SERIF, Font.BOLD, 18);
		final var f2 = new Font(Font.SERIF, Font.BOLD, 16);
		final var f3 = new Font(Font.SERIF, Font.BOLD, 16);
		final var panel1 = new JPanel();
		final var panel2 = new JPanel();
		final var panel3 = new JPanel();
		final var frameDim = new Dimension(300, 350);
		final var frameDimN = new Dimension(200, 150);
		final var panel0Dim = new Dimension(250, 500);
		final var panel0DimX = new Dimension(200, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		if (!edit) {
			frame.setMaximumSize(frameDimN);
			frame.setMinimumSize(frameDimN);
			frame.setLocation(x, y);
			panel0.setMaximumSize(panel0DimX);
			panel0.setMinimumSize(panel0DimX);
			panel0.setSize(200, 200);
			frame.setMaximumSize(panel0DimX);
			frame.setMinimumSize(panel0DimX);
			frame.setSize(200, 200);
		} else {
			frame.setFont(f0);
			frame.setMaximumSize(frameDim);
			frame.setMinimumSize(frameDim);
			frame.setLocation(offset, 350);
			offset += 40;
			if (offset > 500) {
				offset = 100;
			}
			panel0.setMaximumSize(panel0Dim);
			panel0.setMinimumSize(panel0Dim);
			panel0.setSize(300, 400);
		}

		frame.setBackground(Color.WHITE);

		panel0.setLayout(new BoxLayout(panel0, BoxLayout.Y_AXIS));
		panel0.setFont(f0);

		// Hand matrix

		final var panel1Layout = new GridLayout(NUM_ROWS, NUM_COLS);
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(panel1Layout);

		panel1.setFont(f0);
		final var f = new Font("Dialog", Font.BOLD, 10);
		final var fs = new Font("Dialog", Font.BOLD, 2);
		for (int i = 0; i < HANDS; ++i) {
			buttonArr[i] = new JButton(HandRange.getRangeArrayString(i));
			if (!edit) {
				buttonArr[i].setFont(fs);
			} else {
				buttonArr[i].addActionListener(new Listener());
				buttonArr[i].setFont(f);
			}
			buttonArr[i].setBackground(none);
			buttonArr[i].setForeground(Color.BLACK);
			panel1.add(buttonArr[i]);
		}
		panel0.add(panel1);

		if (edit) {
			panel3.setSize(300, 40);
			panel3.setBackground(Color.gray);
			panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
			final var dim3 = new Dimension(300, 40);
			panel3.setMaximumSize(dim3);
			panel3.setMinimumSize(dim3);
			combos1.setFont(f3);
			comboPer1.setFont(f3);
			hands1.setFont(f3);
			combos1.setBackground(open);
			comboPer1.setBackground(open);
			hands1.setBackground(open);

			panel3.add(combos1);
			panel3.add(comboPer1);
			panel3.add(hands1);
			panel0.add(panel3);

			final var panel4 = new JPanel();
			panel4.setBackground(Color.WHITE);
			panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
			combos2.setFont(f3);
			comboPer2.setFont(f3);
			hands2.setFont(f3);
			combos2.setBackground(open);
			comboPer2.setBackground(open);
			hands2.setBackground(open);
			final var dim = new Dimension(300, 40);
			if (shadowMsg) {
				panel4.setMaximumSize(dim);
				panel4.setMinimumSize(dim);
				panel4.add(combos2);
				panel4.add(comboPer2);
				panel4.add(hands2);
				panel0.add(panel4);
			}

			final var panel5 = new JPanel();
			panel5.setBackground(Color.gray);
			panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
			final var dimx = new Dimension(500, 40);
			if (cautionMsg) {
				panel5.setMaximumSize(dimx);
				panel5.setMinimumSize(dimx);
				msg.setFont(f3);
				msg.setBackground(Color.WHITE);
				panel5.add(msg);
				panel0.add(panel5);
			}

			final var panel6 = new JPanel();
			panel6.setBackground(Color.gray);
			panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
			final var dim6 = new Dimension(300, 40);
			panel6.setMaximumSize(dim6);
			panel6.setMinimumSize(dim6);
			if (duplicateMsg) {
				duplicates.setBackground(Color.WHITE);
				duplicates.setForeground(Color.PINK);
				duplicates.setFont(f3);
				panel6.add(duplicates);
				panel0.add(panel6);
			}
		}

		// Control buttons
		if (edit) {
			panel2.setBackground(Color.gray);
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
			buttonSave.setBackground(control);
			buttonSave.addActionListener(new Listener());
			buttonSave.setFont(f2);
			panel2.add(buttonSave);
			panel0.add(panel2);
		}

		frame.add(panel0);
		frame.pack();
		frame.setVisible(true);
	}

	/*- - Save range files. */
	private void saveRangeFiles() {
		range1.writeToFile(file1);
	}

	/*- - Edit one range. */
	private void editOne(String s0) {
		if (!"Average".equals(type)) {
			final int i = HandRange.getHandStringToIndex(s0);
			if (!subset) {
				if (buttonArr[i].getBackground() == range1Color) {
					buttonArr[i].setBackground(Color.GRAY);
					range1.setRangeArray(i, HandRange.NOT_RANGE);
				} else {
					buttonArr[i].setBackground(range1Color);
					range1.setRangeArray(i, HandRange.IN_RANGE);
				}
			} else {
				if (buttonArr[i].getBackground() == range1Color) {
					buttonArr[i].setBackground(Color.GRAY);
					range1.setRangeArray(i, HandRange.NOT_RANGE);
					return;
				}
				if (buttonArr[i].getBackground() == Color.GRAY) {
					buttonArr[i].setBackground(range1Color);
					range1.setRangeArray(i, HandRange.IN_RANGE);
					return;
				}
			}
		}
		if (!edit) {
			buttonArr[hand].setBackground(Color.PINK);
		}
	}

	private void report() {
		final var pattern = "##.0";
		final var df = new DecimalFormat(pattern);
		String st1;
		final int i;
		final double per1;
		final int j;

		i = range1.getCombos();
		st1 = new StringBuilder().append(" ").append(i).append(" ").toString();
		combos1.setText(st1);
		per1 = range1.getRangePercent();
		st1 = df.format(per1);
		comboPer1.setText(new StringBuilder().append(" ").append(st1).append("%    ").toString());
		combos1.setBackground(range1Color);
		comboPer1.setBackground(range1Color);
		hands1.setBackground(range1Color);
		j = range1.countHands();
		st1 = new StringBuilder().append(" ").append(j).append(" ").toString();
		hands1.setText(st1);

		if (!subset) {
			return;
		}
		st1 = df.format(per1 / rangeShadow.getRangePercent() * 100)
				+ " Percentage of Preflop Range is the actual percentage";
		msg.setText(st1);
	}

	private void reportShadow() {
		final var pattern = "##.0";
		final var df = new DecimalFormat(pattern);
		String st1;
		final int i;
		final double per2;
		final int j;

		i = rangeShadow.getCombos();
		st1 = new StringBuilder().append(" ").append(i).append(" ").toString();
		combos2.setText(st1);
		per2 = rangeShadow.getRangePercent();
		st1 = df.format(per2);
		comboPer2.setText(new StringBuilder().append(" ").append(st1).append("%    ").toString());
		combos2.setBackground(rangeShadowColor);
		comboPer2.setBackground(rangeShadowColor);
		hands2.setBackground(rangeShadowColor);
		j = rangeShadow.countHands();
		st1 = new StringBuilder().append(" ").append(j).append(" ").toString();
		hands2.setText(st1);
	}

	/*- - Respond to mouse clicks. */
	private class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a0) {
			final var action = a0.getActionCommand();
			if ("Save".equals(action)) {
				saveRangeFiles();
				return;
			}
			editOne(action);
			report();
		}
	}
}
