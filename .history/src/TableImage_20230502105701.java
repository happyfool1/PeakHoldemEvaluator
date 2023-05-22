//package evaluate_streets;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class TableImage extends JFrame implements Constants {
	/*- * Panel classes initialized here * /. */

	private static final SeatPanel[] seats = new SeatPanel[PLAYERS];

	private static JPanel seat1 = new JPanel();

	private static JPanel seat2 = new JPanel();

	private static JPanel seat3 = new JPanel();

	private static JPanel seat4 = new JPanel();

	private static JPanel seat5 = new JPanel();

	private static JPanel seat6 = new JPanel();

	private static final JPanel messagePanel = new JPanel();

	private static final JTextField line1 = new JTextField("                             ");

	private static final JTextField line2 = new JTextField("                             ");

	private static final JTextField line3 = new JTextField("                             ");

	private static final JPanel statusPanel = new JPanel();

	private static final JLabel handNumber = new JLabel("                             ");

	private static final JPanel panelTable = new JPanel();

	private static final JPanel panelBlank1 = new JPanel();

	private static final JPanel panelBlank2 = new JPanel();

	private static final JPanel panelBlank3 = new JPanel();

	private static final JPanel panelBlank4 = new JPanel();

	private static final JPanel panelBlank5 = new JPanel();

	private static final JPanel panelBlank6 = new JPanel();

	private static final JPanel panel1 = new JPanel();

	private static final JPanel panel2 = new JPanel();

	private static final JPanel panel2A = new JPanel();

	private static final JPanel panel3 = new JPanel();

	private static final JPanel panel4 = new JPanel();

	private static final JTextField pot = new JTextField("");

	// Images
	private static JPanel handPanel = new JPanel();

	private static JPanel boardPanel = new JPanel();

	/*- The display frame and panels. */
	private static JFrame frame;

	/*- Buttons. */
	private static boolean foldButton;

	private static boolean checkButton;

	private static boolean callButton;

	private static boolean betButton;

	private static boolean allinButton;

	private static final JPanel buttonPanel = new JPanel();

	private static final JButton fold = new JButton("Fold            ");

	private static final JButton check = new JButton("Check        ");

	private static final JButton call = new JButton("Call             ");

	private static final JButton bet = new JButton("Bet              ");

	private static final JButton allin = new JButton("All-in          ");

	private static double sizeOfBet;

	/*- True if frame has been opened already */
	private static boolean frameOpen;

	private TableImage() {
		throw new IllegalStateException("Utility class");
	}

	/*- - Create the primary frame. */
	static void doFrame() {
		if (frameOpen) {
			return;
		}

		EvalData.interactive = frameOpen = true;
		EvalData.stop = false;
		frame = new JFrame("Interactive drills");
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent i) {
				i.getWindow().dispose();
				EvalData.interactive = frameOpen = false;
				EvalData.stop = true;
			}
		});

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLocation(200, 50);

		new Dimension(10, 10);
		final var s = new Dimension(100, 100);
		// TODO final ImagePanel panel = new ImagePanel(new
		// ImageIcon("C:\\ApplicationData\\Table.png").getImage());
		final var frame = new JFrame();
		// frame.getContentPane().add(panel);
		// panel.setLocation(90, 80);

		final var d1 = new Dimension(40, 40);

		new JPanel();
		final var space1 = new JPanel();
		final var space2 = new JPanel();
		final var space3 = new JPanel();
		space1.setMaximumSize(d1);
		space2.setMaximumSize(d1);
		final var jt = new JTextField("");
		space1.add(jt);
		space2.add(jt);
		space3.setMaximumSize(d1);
		space3.add(jt);

		buttonPanel.setLayout(new GridLayout(3, 2));
		new Font(Font.SERIF, Font.BOLD, 16);
		fold.addActionListener(new Listener());
		check.addActionListener(new Listener());
		call.addActionListener(new Listener());
		bet.addActionListener(new Listener());
		allin.addActionListener(new Listener());
		fold.setSize(20, 30);
		check.setSize(20, 30);
		call.setSize(20, 30);
		bet.setSize(20, 30);
		allin.setSize(20, 30);
		// fold.setFont(b);
		buttonPanel.add(fold);
		buttonPanel.add(check);
		buttonPanel.add(call);
		buttonPanel.add(bet);
		buttonPanel.add(allin);

		seats[0] = new SeatPanel(0);
		seats[1] = new SeatPanel(1);
		seats[2] = new SeatPanel(2);
		seats[3] = new SeatPanel(3);
		seats[4] = new SeatPanel(4);
		seats[5] = new SeatPanel(5);
		seat1.setMaximumSize(s);
		seat2.setMaximumSize(s);
		seat3.setMaximumSize(s);
		seat4.setMaximumSize(s);
		seat5.setMaximumSize(s);
		seat6.setMaximumSize(s);

		new Dimension(40, 40);
		updateSeats();

		final var d2 = new Dimension(10, 10);
		pot.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		pot.setForeground(Color.BLACK);
		pot.setBackground(Color.GREEN);
		pot.setMaximumSize(d2);
		pot.setMinimumSize(d2);
		pot.setLayout(new GridLayout(3, 1, 10, 10));
		/*
		 * potPanel.setLayout(new GridLayout(3, 1, 10, 10)); potPanel.add(pot); if
		 * (Table.sidePot1 != 0) { potPanel.add(panelBlank7); } else {
		 * potPanel.add(panelBlank7); } if (Table.sidePot1 != 0) {
		 * potPanel.add(panelBlank8); } else { potPanel.add(panelBlank8); }
		 */

		final var s3 = new Dimension(40, 40);
		panel2.setMaximumSize(s3);
		panel2.setMinimumSize(s3);
		panel2.setSize(300, 100);

		boardPanel.setSize(300, 100);
		boardPanel.setMinimumSize(s3);

		messagePanel.setMinimumSize(s);
		buttonPanel.setMinimumSize(s);

		// Status panel
		statusPanel.setLayout(new GridLayout(4, 1));
		handNumber.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		statusPanel.add(handNumber);

		panel1.setLayout(new GridLayout(1, 3, 100, 100));
		panel1.add(seat3);
		panel1.add(seat4);
		panel1.add(seat5);

		panel2.setLayout(new GridLayout(1, 5, 40, 40));
		panel2.add(panelBlank1);
		panel2.add(panelBlank2);
		panel2.add(pot);
		// panel2.add(potPanel);
		panel2.add(panelBlank3);
		panel2.add(panelBlank4);

		panel2A.setLayout(new GridLayout(1, 3, 100, 100));
		panel2A.add(seat2);
		panel2A.add(boardPanel);
		panel2A.add(seat6);

		panel3.setLayout(new GridLayout(1, 3, 100, 100));
		panel3.add(panelBlank5);
		panel3.add(handPanel);
		panel3.add(panelBlank6);

		panel4.setLayout(new GridLayout(1, 3, 100, 0));
		panel4.add(statusPanel);
		panel4.add(seat1);
		panel4.add(buttonPanel);

		// Message panel
		final var m = new Font(Font.SERIF, Font.BOLD, 18);
		messagePanel.setLayout(new GridLayout(3, 1));
		line1.setFont(m);
		line2.setFont(m);
		line3.setFont(m);
		messagePanel.add(line1);
		messagePanel.add(line2);
		messagePanel.add(line3);

		panelTable.setLayout(new GridLayout(8, 1));
		// panelTable.add(space1);
		panelTable.add(panel1);
		panelTable.add(space2);
		panelTable.add(panel2);
		panelTable.add(space3);
		panelTable.add(panel2A);
		// panelTable.add(space4);
		panelTable.add(panel3);
		panelTable.add(panel4);
		panelTable.add(messagePanel);

		frame.add(panelTable);

		frame.pack();
		frame.setVisible(true);
	}

	/*- Set size of bet */
	static void setSizeOfBet(double bet) {
		sizeOfBet = bet;
	}

	/*- Set frame title. */
	static void setFrameTitle(String title) {
		frame.setTitle(title);
	}

	/*- - Get Seats. */
	private static void updateSeats() {
		seat1 = seats[0].showSeat();
		seat2 = seats[1].showSeat();
		seat3 = seats[2].showSeat();
		seat4 = seats[3].showSeat();
		seat5 = seats[4].showSeat();
		seat6 = seats[5].showSeat();
	}

	/*- Get action key Return action table constant. */
	static int getActionKey(int $, boolean showSelected) {
		final boolean x = true;
		clearButtonFlags();
		buttonsOn($);
		if (showSelected) {
			actionButton($);
		}
		while (x) {
			sleep(10);
			if (foldButton) {
				break;
			}
			if (checkButton) {
				return CHECK;
			}
			if (callButton) {
				return CALL;
			}
			if (betButton) {
				if ($ == BET1) {
					return BET1;
				}
				if ($ == BET2) {
					return BET2;
				}
				if ($ == BET3) {
					return BET3;
				}
				return $ == BET4 ? BET4 : EvalData.betType;
			}
			if (allinButton) {
				return ALLIN;
			}
		}
		return FOLD;
	}

	/*- Update status panel. */
	private static void updateStatusPanel() {
		handNumber.setText("");
	}

	/*- Update message panel. */
	static void updateMessagePanel() {

		// TODO xxxx
		// if (EvalData.why2[EvalData.seat] !=0) {
		// line1.setText(EvalData.why2);
		// } else {
		// line1.setText(EvalData.why);
		// }
	}

	/*- Update message panel. */
	static void clearMessagePanel() {
		line1.setText("");
		line2.setText("");
		line3.setText("");
	}

	/*- Update pot panel */
	private static void updatePotPanel() {
		// pot.setText(decFormat(EvalData.pot));
	}

	/*-
	 * - - Show table.
	 * 
	 * The display is updated for the current table status No status is updated
	 * until it is seat 0
	 */
	static void updateDisplay() {
		if (EvalData.seat != 0) {
			return;
		}
		updateSeats();
		updateMessagePanel();
		updatePotPanel();

		if (EvalData.street == FLOP) {
			panel2A.remove(panelBlank5);
			panel2A.remove(boardPanel);
			panel2A.remove(panelBlank6);
			boardPanel = GetImages.getFlop();
			panel2A.add(panelBlank5);
			panel2A.add(boardPanel);
			panel2A.add(panelBlank6);
		}
		if (EvalData.street == TURN) {
			boardPanel = GetImages.getTurn();
		}
		if (EvalData.street == RIVER) {
			boardPanel = GetImages.getRiver();
		}
		panel3.remove(seat1);
		panel3.remove(handPanel);
		panel3.remove(seat6);
		handPanel = GetImages.getHand(0);
		panel3.add(seat2);
		panel3.add(handPanel);
		panel3.add(seat6);
	}

	/*- - Burttons off. */
	private static void buttonsOff() {
		fold.setEnabled(false);
		check.setEnabled(false);
		call.setEnabled(false);
		bet.setEnabled(false);
		allin.setEnabled(false);

		fold.setForeground(Color.WHITE);
		check.setForeground(Color.WHITE);
		call.setForeground(Color.WHITE);
		bet.setForeground(Color.WHITE);
		allin.setForeground(Color.WHITE);

		fold.setBackground(Color.RED);
		check.setBackground(Color.RED);
		call.setBackground(Color.RED);
		bet.setBackground(Color.RED);
		allin.setBackground(Color.RED);

		fold.setText("Fold       ");
		check.setText("Check ");
		call.setText("Call         ");
		bet.setText("Bet           ");
		allin.setText("All-In     ");
	}

	/*- Set button for selected action. */
	private static void actionButton(int simAction) {
		if (simAction == FOLD) {
			fold.setForeground(Color.GREEN);
		}
		if (simAction == CHECK) {
			check.setForeground(Color.GREEN);
		}
		if (simAction == CALL) {
			call.setForeground(Color.GREEN);
		}
		if (simAction == BET1) {
			bet.setForeground(Color.GREEN);
		}
		if (simAction == BET2) {
			bet.setForeground(Color.GREEN);
		}
		if (simAction == BET3) {
			bet.setForeground(Color.GREEN);
		}
		if (simAction == BET4) {
			bet.setForeground(Color.GREEN);
		}
		if (simAction == ALLIN) {
			allin.setForeground(Color.GREEN);
		}
	}

	/*- - Buttons on. */
	private static void buttonsOn(int simAction) {
		buttonsOff();
		final double betSize = 0;
		/// final double sbBet = EvalData.sbBetSize;
		// final double preflopBet2 = EvalData.preflopBet2Size;
		// final double callSize = EvalData.betNow - EvalData.moneyIn[EvalData.seat];

		if (EvalData.street == PREFLOP && EvalData.orbit == 0) {
			// betSize = preflopBet2 + sbBet * EvalData.limpCount;
		}

		// allin.setEnabled(true);
		// bet.setText("Raise " + decFormat(sizeOfBet));
		// bet.setEnabled(true);
		// call.setText("Call " + decFormat(EvalData.betNow));
		// call.setEnabled(true);
		// fold.setEnabled(true);
		// }
		// allin.setEnabled(true);
		// bet.setText("Raise " + decFormat(sizeOfBet));
		// bet.setEnabled(true);
		// }
		// call.setText("Call " + st);
		// call.setEnabled(true);
		// st = decFormat(callSize);
		// } else {
		// check.setEnabled(true);
		// if (EvalData.betNow == 0) {
		// fold.setEnabled(true);
		// if (simAction == FOLDBET) {
		if (simAction != CHECK) {
			return;
		}
		check.setEnabled(true);
		bet.setEnabled(true);
		bet.setText("Raise " + decFormat(betSize));
		allin.setEnabled(true);
		return;
	}

	/*- - Clear button flags. */
	private static void clearButtonFlags() {
		allinButton = betButton = false;
	}

	/*- - Sleep Arg0 is in seconds. */
	private static void sleep(int ms) {
		final long t = 100 * ms;
		try {
			Thread.sleep(t);
		} catch (InterruptedException i) {
			i.printStackTrace();
		}
	}

	/*- - Convert to decimal format. */
	private static String decFormat(double number) {
		final var pattern = "###.##";
		return number == 0 ? "   0  " : "   " + (new DecimalFormat(pattern)).format(number);
	}

	/*- - Responds to button clicks. */
	private static class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			final var action = e.getActionCommand();
			final var st3 = action.substring(0, 3);
			final var st4 = action.substring(0, 4);
			final var st6 = action.substring(0, 6);

			if ("Fold".equals(st4)) {
				foldButton = true;
			}
			if ("Check".equals(st4)) {
				checkButton = true;
			}
			if ("Call".equals(st4)) {
				callButton = true;
			}
			if ("Raise".equals(st3)) {
				betButton = true;
			}
			if ("All-in".equals(st6)) {
				allinButton = true;
			}
		}
	}
}
