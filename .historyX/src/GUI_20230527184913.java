//package evaluate_streets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/*- **************************************************************************** 
* This is the primary GUI for this project. The other is GUIAnalyzeMany.
* This one is use to select hole cards and board cards that will be evaluated for 
* draws and made hands.  
*
* The Help option displays a fairly comprehensive description.
*
* We do not use drag and drop, we click on a card in the array of 52 cards then
* we click on the destination. If there is a card in the destination already thayt


*
* 
* @author PEAK_
*******************************************************************************/

/*- **************************************************************************** 
* GUI definitions
*******************************************************************************/
public class GUI extends JFrame implements ActionListener, Constants {

	static final String[] CARDS = { "As", "Ac", "Ad", "Ah", "Ks", "Kc", "Kd", "Kh", "Qs", "Qc", "Qd", "Qh", "Js", "Jc",
			"Jd", "Jh", "Ts", "Tc", "Td", "Th", "9s", "9c", "9d", "9h", "8s", "8c", "8d", "8h", "7s", "7c", "7d", "7h",
			"6s", "6c", "6d", "6h", "5s", "5c", "5d", "5h", "4s", "4c", "4d", "4h", "3s", "3c", "3d", "3h", "2s", "2c",
			"2d", "2h" };

	private static final Color SPADE = SKY_BLUE;
	private static final Color DIAMOND = YELLOW;
	private static final Color CLUB = PINK;
	private static final Color HEART = LIGHT_GRAY;

	private static final Color CONTROL = GREEN;
	private static final Color BOARD = CYAN;
	private static final Color HOLE = CYAN;
	private static final Color MADE = WHEAT;
	private static final Color DRAW = KHAKI;
	private static final Color PLAY = SKY_BLUE;
	private static final Color POSITION = ORANGE;
	private static final Color STACK = MAGENTA;
	private static final Color POT_ODDS = TOMATO;
	private static final Color GOOD = RED;
	private static final Color ODDS = OLIVE_DRAB;
	private static final Color DIM = GRAY;
	private static final Color MONEY = GREEN;

	private static final JButton[] cardArray = new JButton[52];

	private static final JButton[] hole1Array = new JButton[6];

	private static final JButton[] hole2Array = new JButton[6];

	private static final JButton[] boardArray = new JButton[5];

	private static final JTextArea[][] handsArray = new JTextArea[6][5];

	private static final JTextArea[][] drawsArray = new JTextArea[6][5];

	private static final JTextArea[] drawArrayType = new JTextArea[6];

	private static final JTextArea[] madeArrayType = new JTextArea[6];

	private static final JTextArea[] playArray = new JTextArea[6];

	private static final JTextArea[] stackArray = new JTextArea[6];

	private static final JTextArea[] positionArray = new JTextArea[6];

	private static final JTextArea[] potOddsArray = new JTextArea[6];

	private static final JTextArea[] oddsArray = new JTextArea[6];

	private static final JTextArea pot = new JTextArea("$0");

	private static final JTextArea header = new JTextArea(
			"Cards                                             Seat     Hole               Play    Pos     Stack  PotO   Odds    Made Type          Made Hand   Draw Type          Draw Hand");

	private static final JButton execute = new JButton("Execute");

	private static final JButton reset = new JButton("Reset");

	private static final JButton help = new JButton("Help");

	private static final JButton exit = new JButton("Exit");

	private static final JCheckBox preflopSimulation = new JCheckBox("Preflop Simulation");

	private static final JCheckBox randomHoleCards = new JCheckBox("Random Hole");

	private static final JCheckBox randomBoard = new JCheckBox("Random Board");

	private static final JCheckBox freezeSeat1 = new JCheckBox("Freeze Seat 1");

	private static final JCheckBox detailedReport = new JCheckBox("Detailed Report");

	private static final JCheckBox boardReport = new JCheckBox("Board Report");

	private static final JCheckBox preflopReport = new JCheckBox("Preflop Report");

	private static final JRadioButton[] radioSeat = new JRadioButton[6];

	private static final JRadioButton[] radioStreet = new JRadioButton[5];

	private static final Font fh = new Font("Dialog", Font.BOLD, 16);

	private static final Font fs = new Font("Dialog", Font.BOLD, 12);

	private static final Font fx = new Font("Dialog", Font.BOLD, 12);

	private static final Font fc = new Font("Dialog", Font.BOLD, 12);

	private static final Font fp = new Font("Dialog", Font.PLAIN, 4);

	static DefaultTableModel tableModel1 = null;

	static JScrollPane pane1 = null;

	// Seat is active
	private static final boolean[] seatActive = { false, false, false, false, false, false };

	private static JPanel panelRadioStreet = new JPanel();

	private static JPanel panelRadioSeat = new JPanel();

	private JPanel mainPanel;

	private JPanel panelHeader;

	private JPanel panelControl;

	private JPanel panelCards; // 52 cards 4 X 12

	private JPanel panelHoleCards; // indexed by seat number

	private JPanel panelHands; // Hand 5 cards

	private JPanel panelDraws; // Draw 5 cards

	private JPanel panelDrawType; // Draw Type

	private JPanel panelMadeType; // Made Type

	private JPanel panelBoard; // Board

	private JPanel panelPlay; // Playability index

	private JPanel panelPosition; // Position SB, BB, UTG. MP, CO, BU

	private JPanel panelStack; // Players stack

	private JPanel panelPotOdds; // Pot odds

	private JPanel panelOdds; // Draw odds

	private JPanel panelPot; // Pot

	private JPanel panelCheckBoxesReports;

	private JPanel panelCheckBoxesOptions;

	/*-  ******************************************************************************
	 * The first click, if it is on a button representing a card, the button text is saved 
	 * in fromClicked. 
	 * The next click, if it is also on a button representing a card, the button text 
	 * is saved in toClicked.
	 * If there is a click on any other button or JTextArea both fromClicked and
	 * toClicked are cleared.
	 * Data set when a card in cardArray  or in hole1Array or hole2Array or 
	 * boardArray or flopArray of turn or river is clicked
	 *  ****************************************************************************** */
	private boolean click1 = false;

	private String fromClicked = "";

	/*- **************************************************************************** 
	* Data
	*******************************************************************************/
	private int selectedSeat = 0;

	private int selectedStreet = FLOP;

	private boolean freezeSeat1Option = false;

	private boolean detailedReportOption = false;

	private boolean boardReportOption = false;

	private boolean preflopReportOption = false;

	private boolean preflopSimulationOption = false;

	private boolean randomHoleCardsOption = false;

	private boolean randomHoleCardsCreated = false;

	private boolean randomBoardOption = false;

	private boolean randomBoardCreated = false;

	private boolean displayActive = false; // We have done doExecute() and data being displayed

	// Card as string is key, location as string is data
	// Keys Ac, .... 52 cards
	// locations cardArray, hxa, hxb, bx x is array index
	private HashMap<String, String> location;

	// @formatter:off
	String helpString = new StringBuilder().append("Welcome to the PeakHoldemEvaluator project GUI.\r\n")
			.append("This GUI demonstrates how  hole cards are combined with a common board and many different\r\n")
			.append("types of analysis are performed.  This GUI just touches the surface of what this project can do\r\n")
			.append("To the left of the screen is an array of 52 cards. Click on one of these cards and then click on a card \r\n ")
			.append("somewhere else in the panel until you have a hand(s) that you want to evaluate. \r\n ")
			.append("The second row is radio buttons that select the seat for which detailed evaluations will be done.\r\n ")
			.append("The Scroll Panel to the far right is where the detailed evaluation will appear.\r\n")
			.append("The 4th through 8th columns are where there will be a summary evaluation.\r\n ")
			.append("The next column is the hole cards.\r\n ")
			.append("The next column is the playability of the 2 hole cards. The 169 possible hole card combinations have been\r\n")
			.append("ranked from best to worst. Best is 0 and worst is 168.\r\n ")
			.append("The next column is the 5 cards that make up a draw and the column after is the type of draw.\r\n")
			.append("After these 2 columns are 2 more columns for made hands. \r\n")
			.append("The 5 cards are simple for the Flop but require selection for the turn and river. Nan hand must include both hole cards. ")
			.append("The last column is a scroll panel with detailed analysis of one player. \r\n")
			.append("The board,  Flop, Turn, and River cards are below. The detailed analysis is done for only one street and player.\r\n")
			.append("Below that are radio buttons to select a street or showdown.  ")
			.append("Below that are radio buttons check boxes. Hole cards can be chosen at random each time that Run\r\n")
			.append("When you are satisfied with the hand(s) the hand select Run.\r\n ")
			.append("is selected.  You may choose to freeze the selected seat have all other seats add board cards random.\r\n")
			.append("\r\n ").toString();
	// @formatter:on

	// Constructor
	GUI() {

	}

	/*- **************************************************************************** 
	* Main
	*******************************************************************************/
	public static void main(String[] args) {
		final var myFrame = new GUI();
		myFrame.myFrame();
	}

	/*- **************************************************************************** 
	* Frame
	*******************************************************************************/
	public void myFrame() {
		Evaluate.initialize();
		/// Set up the main panel
		mainPanel = new JPanel(null);
		mainPanel.setPreferredSize(new Dimension(1150, 550));

		panelHeader = new JPanel(new GridLayout(1, 1));
		panelHeader.setBounds(10, 10, 1100, 20);
		header.setFont(fh);
		header.setForeground(BLACK);
		header.setBackground(WHITE);
		panelHeader.add(header);
		mainPanel.add(panelHeader);

		panelCards = new JPanel(new GridLayout(13, 4));
		final var panelCSize = new Dimension(200, 400);
		panelCards.setMaximumSize(panelCSize);
		panelCards.setPreferredSize(panelCSize);
		panelCards.setBounds(20, 40, 200, 400);
		mainPanel.add(panelCards);

		panelBoard = new JPanel(new GridLayout(1, 5));
		final var panelBSize = new Dimension(220, 40);
		panelBoard.setMaximumSize(panelBSize);
		panelBoard.setPreferredSize(panelBSize);
		panelBoard.setBounds(300, 260, 250, 40);
		mainPanel.add(panelBoard);

		panelPot = new JPanel(new GridLayout(1, 5));
		final var panelDSize = new Dimension(40, 40);
		panelPot.setMaximumSize(panelDSize);
		panelPot.setPreferredSize(panelDSize);
		panelPot.setBounds(600, 260, 40, 40);
		panelPot.add(pot);
		pot.setBackground(MONEY);
		mainPanel.add(panelPot);

		panelRadioStreet = new JPanel(new GridLayout(1, 5));
		panelRadioStreet.setBounds(300, 310, 450, 40);
		final var streetBox = Box.createHorizontalBox();
		final var streetGroup = new ButtonGroup();
		streetBox.add(panelRadioStreet);
		mainPanel.add(panelRadioStreet);

		panelCheckBoxesOptions = new JPanel(new GridLayout(1, 4));
		panelCheckBoxesOptions.setBounds(300, 340, 800, 40);
		mainPanel.add(panelCheckBoxesOptions);

		panelCheckBoxesReports = new JPanel(new GridLayout(1, 3));
		panelCheckBoxesReports.setBounds(300, 380, 600, 40);
		mainPanel.add(panelCheckBoxesReports);

		panelControl = new JPanel(new GridLayout(1, 4));
		panelControl.setBounds(300, 420, 550, 40);
		mainPanel.add(panelControl);

		panelRadioSeat = new JPanel(new GridLayout(6, 1));
		panelRadioSeat.setBackground(LIGHT_GRAY);
		panelRadioSeat.setBounds(240, 40, 60, 200);
		final var seatBox = Box.createHorizontalBox();
		final var seatGroup = new ButtonGroup();
		seatBox.add(panelRadioSeat);
		mainPanel.add(panelRadioSeat);

		panelHoleCards = new JPanel(new GridLayout(6, 2));
		panelHoleCards.setBackground(LIGHT_GRAY);
		panelHoleCards.setForeground(BLACK);
		final var panelHSize = new Dimension(80, 200);
		panelHoleCards.setMaximumSize(panelHSize);
		panelHoleCards.setPreferredSize(panelHSize);
		panelHoleCards.setBounds(300, 40, 100, 200);
		mainPanel.add(panelHoleCards);

		panelPlay = new JPanel(new GridLayout(6, 1));
		panelPlay.setBounds(410, 40, 30, 200);
		mainPanel.add(panelPlay);

		final int offset = 220;

		panelPosition = new JPanel(new GridLayout(6, 1));
		panelPosition.setBounds(460, 40, 30, 200);
		mainPanel.add(panelPosition);

		panelStack = new JPanel(new GridLayout(6, 1));
		panelStack.setBounds(510, 40, 40, 200);
		mainPanel.add(panelStack);

		panelPotOdds = new JPanel(new GridLayout(6, 1));
		panelPotOdds.setBounds(560, 40, 40, 200);
		mainPanel.add(panelPotOdds);

		panelOdds = new JPanel(new GridLayout(6, 1));
		panelOdds.setBounds(610, 40, 40, 200);
		mainPanel.add(panelOdds);

		panelMadeType = new JPanel(new GridLayout(6, 1));
		panelMadeType.setBounds(450 + offset, 40, 110, 200);
		mainPanel.add(panelMadeType);

		panelHands = new JPanel(new GridLayout(6, 5));
		panelHands.setBounds(570 + offset, 40, 90, 200);
		mainPanel.add(panelHands);

		panelDrawType = new JPanel(new GridLayout(6, 1));
		panelDrawType.setBounds(670 + offset, 40, 110, 200);
		mainPanel.add(panelDrawType);

		panelDraws = new JPanel(new GridLayout(6, 5));
		panelDraws.setBounds(790 + offset, 40, 90, 200);
		mainPanel.add(panelDraws);

		panelHeader.add(header);
		mainPanel.add(panelHeader);

		location = new HashMap<>();
		for (int i = 0; i < 52; i++) {
			cardArray[i] = new JButton(CARDS[i]);
			final var c = new Card(CARDS[i]);
			cardArray[i].setFont(fs);
			cardArray[i].setPreferredSize(new Dimension(10, 10));
			cardArray[i].addActionListener(this);
			if (c.suit == 0) {
				cardArray[i].setBackground(SPADE);
			} else if (c.suit == 1) {
				cardArray[i].setBackground(DIAMOND);
			} else if (c.suit == 2) {
				cardArray[i].setBackground(CLUB);
			} else {
				cardArray[i].setBackground(HEART);
			}
			panelCards.add(cardArray[i]);
			location.put(cardArray[i].getText(), "cardArray");
		}

		for (int i = 0; i < 6; i++) {
			radioSeat[i] = new JRadioButton("Seat" + String.valueOf(i + 1));
			radioSeat[i].addActionListener(this);
			panelRadioSeat.add(radioSeat[i]);
			seatGroup.add(radioSeat[i]);
		}
		radioSeat[0].setSelected(true);
		panelRadioSeat.setFont(fs);
		radioStreet[0] = new JRadioButton("Preflop");
		radioStreet[1] = new JRadioButton("Flop");
		radioStreet[2] = new JRadioButton("Turn");
		radioStreet[3] = new JRadioButton("River");
		radioStreet[4] = new JRadioButton("Showdown");
		for (int i = 0; i < 4; i++) {
			radioStreet[i].addActionListener(this);
			panelRadioStreet.add(radioStreet[i]);
			streetGroup.add(radioStreet[i]);
		}
		radioStreet[1].setSelected(true);
		panelRadioStreet.setFont(fs);

		for (int i = 0; i < 6; i++) {
			hole1Array[i] = new JButton("");
			hole1Array[i].setPreferredSize(new Dimension(10, 10));
			hole1Array[i].setFont(fs);
			hole1Array[i].setText(new StringBuilder().append("h").append(String.valueOf(i)).append("a").toString());
			hole1Array[i].addActionListener(this);
			hole1Array[i].setForeground(BLACK);
			hole1Array[i].setBackground(HOLE);
			panelHoleCards.add(hole1Array[i]);

			hole2Array[i] = new JButton("");
			hole2Array[i].setPreferredSize(new Dimension(10, 10));
			hole2Array[i].setFont(fs);
			hole2Array[i].setText(new StringBuilder().append("h").append(String.valueOf(i)).append("b").toString());
			hole2Array[i].addActionListener(this);
			hole2Array[i].setForeground(BLACK);
			hole2Array[i].setBackground(HOLE);
			panelHoleCards.add(hole2Array[i]);
		}

		for (int i = 0; i < 6; i++) {
			playArray[i] = new JTextArea("");
			playArray[i].setFont(fs);
			playArray[i].setPreferredSize(new Dimension(25, 25));
			playArray[i].setBackground(PLAY);
			panelPlay.add(playArray[i]);
		}

		for (int i = 0; i < 6; i++) {
			positionArray[i] = new JTextArea("");
			positionArray[i].setFont(fs);
			positionArray[i].setPreferredSize(new Dimension(25, 25));
			positionArray[i].setBackground(POSITION);
			panelPosition.add(positionArray[i]);
		}

		for (int i = 0; i < 6; i++) {
			stackArray[i] = new JTextArea("");
			stackArray[i].setFont(fs);
			stackArray[i].setPreferredSize(new Dimension(25, 25));
			stackArray[i].setBackground(STACK);
			panelStack.add(stackArray[i]);
		}

		for (int i = 0; i < 6; i++) {
			potOddsArray[i] = new JTextArea("");
			potOddsArray[i].setFont(fs);
			potOddsArray[i].setPreferredSize(new Dimension(25, 25));
			potOddsArray[i].setBackground(POT_ODDS);
			panelPotOdds.add(potOddsArray[i]);
		}

		for (int i = 0; i < 6; i++) {
			oddsArray[i] = new JTextArea("");
			oddsArray[i].setFont(fs);
			oddsArray[i].setPreferredSize(new Dimension(25, 25));
			oddsArray[i].setBackground(ODDS);
			panelOdds.add(oddsArray[i]);
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				handsArray[i][j] = new JTextArea("");
				handsArray[i][j].setFont(fs);
				handsArray[i][j].setPreferredSize(new Dimension(25, 25));
				handsArray[i][j].setBackground(MADE);
				panelHands.add(handsArray[i][j]);
			}
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				drawsArray[i][j] = new JTextArea("");
				drawsArray[i][j].setFont(fs);
				drawsArray[i][j].setPreferredSize(new Dimension(25, 25));
				drawsArray[i][j].setBackground(DRAW);
				panelDraws.add(drawsArray[i][j]);
			}
		}

		for (int i = 0; i < 6; i++) {
			drawArrayType[i] = new JTextArea("");
			drawArrayType[i].setFont(fs);
			drawArrayType[i].setPreferredSize(new Dimension(25, 25));
			drawArrayType[i].setBackground(DRAW);
			panelDrawType.add(drawArrayType[i]);
		}

		for (int i = 0; i < 6; i++) {
			madeArrayType[i] = new JTextArea("");
			madeArrayType[i].setFont(fs);
			madeArrayType[i].setPreferredSize(new Dimension(25, 25));
			madeArrayType[i].setBackground(MADE);
			panelMadeType.add(madeArrayType[i]);
		}

		for (int i = 0; i < 5; i++) {
			boardArray[i] = new JButton("");
			boardArray[i].setPreferredSize(new Dimension(10, 10));
			boardArray[i].addActionListener(this);
			boardArray[i].setFont(fp);
			boardArray[i].setText("bo" + String.valueOf(i));
			boardArray[i].setForeground(BLACK);
			boardArray[i].setBackground(BOARD);
			panelBoard.add(boardArray[i]);
		}

		preflopSimulation.setFont(fs);
		preflopSimulation.setPreferredSize(new Dimension(25, 25));
		preflopSimulation.addActionListener(this);
		preflopSimulation.setSelected(true);
		panelCheckBoxesOptions.add(preflopSimulation);

		randomHoleCards.setFont(fs);
		randomHoleCards.setPreferredSize(new Dimension(25, 25));
		randomHoleCards.addActionListener(this);
		randomHoleCards.setSelected(true);
		panelCheckBoxesOptions.add(randomHoleCards);

		randomBoard.setFont(fs);
		randomBoard.setPreferredSize(new Dimension(25, 25));
		randomBoard.addActionListener(this);
		randomBoard.setSelected(true);
		panelCheckBoxesOptions.add(randomBoard);

		freezeSeat1.setFont(fs);
		freezeSeat1.setPreferredSize(new Dimension(25, 25));
		freezeSeat1.addActionListener(this);
		panelCheckBoxesOptions.add(freezeSeat1);

		detailedReport.setFont(fs);
		detailedReport.setPreferredSize(new Dimension(25, 25));
		detailedReport.addActionListener(this);
		detailedReport.setSelected(true);
		panelCheckBoxesReports.add(detailedReport);

		boardReport.setFont(fs);
		boardReport.setPreferredSize(new Dimension(25, 25));
		boardReport.addActionListener(this);
		boardReport.setSelected(true);
		panelCheckBoxesReports.add(boardReport);

		preflopReport.setFont(fs);
		preflopReport.setPreferredSize(new Dimension(25, 25));
		preflopReport.addActionListener(this);
		preflopReport.setSelected(true);
		panelCheckBoxesReports.add(preflopReport);

		execute.setFont(fc);
		execute.setPreferredSize(new Dimension(15, 15));
		execute.addActionListener(this);
		execute.setBackground(CONTROL);
		panelControl.add(execute);

		reset.setFont(fc);
		reset.setPreferredSize(new Dimension(15, 15));
		reset.addActionListener(this);
		reset.setBackground(CONTROL);
		panelControl.add(reset);

		help.setFont(fc);
		help.setPreferredSize(new Dimension(15, 15));
		help.addActionListener(this);
		help.setBackground(CONTROL);
		panelControl.add(help);

		exit.setFont(fc);
		exit.setPreferredSize(new Dimension(15, 15));
		exit.addActionListener(this);
		exit.setBackground(CONTROL);
		panelControl.add(exit);

		// Set some properties of the frame
		this.setContentPane(mainPanel);
		this.setTitle("Evaluate Hands");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	/*-  ******************************************************************************
	 * Clear all of the click data
	 * Happens when a control button, radio button, or anything that is not a card location.
	 *  ****************************************************************************** */
	private void clearClicks() {
		click1 = false;
		fromClicked = "";
	}

	/*-  ******************************************************************************
	 * Handle button click actions from radio buttons streets
	 * Set selectedStreet.
	 *  ****************************************************************************** */
	private void buttonStreet(String action) {
		final var st = action;
		switch (st) {
			case "Preflop" -> {
				selectedStreet = PREFLOP;
				if (displayActive) {
					doExecute();
				}
			}
			case "Flop" -> {
				selectedStreet = FLOP;
				if (displayActive) {
					doExecute();
				}
			}
			case "Turn" -> {
				selectedStreet = TURN;
				if (displayActive) {
					doExecute();
				}
			}
			case "River" -> {
				selectedStreet = RIVER;
				if (displayActive) {
					doExecute();
				}
			}
			case "Showdown" -> {
				selectedStreet = SHOWDOWN;
				if (displayActive) {
					doExecute();
				}
			}
			default -> Logger.log("//ERROR  buttonStreet() invalid street " + action);
		}
	}

	/*-  ******************************************************************************
	 * The Executebutton has been ckicked.
	 * If a random check box is checked and we have not done a random setup yet
	 * then update hole cards and or board cards.
	 * 
	 * In preparation:
	 * 		if  randomHoleOption 
	 * 		if randomBoardOption 
	 * 		if freezeSeatOption 
	 * 		If Flop selected 
	 * 		if Turn selected
	 * 		if River selected
	 * 
	 * 		if Showdown selected
	 * 
	 * 		Check holeArray and call Evaluate methods to set hole cards. 
	 * 		Check board array and call Evaluate methods to set board cards. 
	 *  ****************************************************************************** */
	private void doExecute() {
		if (preflopSimulationOption) {
			boolean done = false;
			int limit = 100;
			while (!done) {
				Evaluate.shuffle();
				randomHoleCards();
				Evaluate.doPreflopSimulation();
				--limit;
				if (EvalData.foldCount < 2 || limit == 0) {
					done = true;
				}
			}
			holeCardsFolded();
		}

		if (randomHoleCardsOption || randomBoardOption) {
			if (!preflopSimulationOption) {
				Evaluate.shuffle();
			}
			restoreCardArray();
		}
		if (randomHoleCardsOption) {
			randomHoleCards();
		}
		if (randomBoardOption) {
			randomBoard();
		}

		displayActive = true;
		// Set hole cards
		if (!randomHoleCardsOption) {
			// setHoleCards();
		}
		// Set board cards
		if (!randomBoardOption) {
			// setBoardCards();
		}
		// Run for selected street
		for (int i = 0; i < PLAYERS; i++) {
			if (seatActive[i]) {
				if (selectedStreet == FLOP) {
					Evaluate.doFlop(i);
				} else if (selectedStreet == TURN) {
					Evaluate.doTurn(i);
				} else if (selectedStreet == RIVER || selectedStreet == SHOWDOWN) {
					Evaluate.doRiver(i);
				}
				if (i == selectedSeat) {
					// doReport();
					if (detailedReportOption) {
						GUIReports.report(1180, 20);
					}
					if (boardReportOption) {
						GUIReports.reportBoard(800, 600);
					}
					if (preflopReportOption) {
						GUIReports.reportPreflop(400, 600);
					}
				}
			}
		}
		if (selectedStreet == SHOWDOWN) {
			Evaluate.doShowdown();
		}

		// Update position, stack, potOdds, odds
		updateData();
		// Update hand data
		updateDrawsAndMade();
	}

	/*-  ******************************************************************************
	 * If a player has folded dim the hole cards
	 *  ****************************************************************************** */
	private void holeCardsFolded() {
		for (int i = 0; i < PLAYERS; i++) {
			if (EvalData.playerFolded[i]) {
				hole1Array[i].setBackground(DIM);
				hole2Array[i].setBackground(DIM);
			}
		}
		panelHoleCards.repaint();
	}

	/*-  ******************************************************************************
	 * This if for updating random cards in hole cards.
	* We reset cardArray by returning it to initial condition. No cards removed.
	* 
	* Hole cards in hole1Array and hole2Array are returned to cardArray.
	* 
	* This is in response to a new Deck.shuffle() where a new deck is created 
	* and dead cards removed.
	* But we want to keep the hole cards that we have in the hole cards
	 *  ****************************************************************************** */
	private void setHoleCards() {
		returnHoleCardsToCardArray();
	}

	/*-  ******************************************************************************
	* Board cards in boardArray returned to cardArray.   
	* EvalData.holeCards and removed from Deck.
	* This is in response to a new Deck.shuffle() where a new deck is created 
	* and dead cards removed.
	* But we want to keep the board cards that we have in the board
	 *  ****************************************************************************** */
	private void setBoardCards() {
		returnBoardCardsToCardArray();
	}

	/*-  **************************************************************************** 
	 * Hole cards are all returned to cardArray
	****************************************************************************** */
	void returnHoleCardsToCardArray() {
		Card card1;
		Card card2;
		for (int i = 0; i < 6; i++) {
			if (hole1Array[i].getText().length() == 2 && hole2Array[i].getText().length() == 2) {
				card1 = new Card(hole1Array[i].getText());
				card2 = new Card(hole2Array[i].getText());
				final int ndx1 = ((12 - card1.value) * 4) + card1.suit;
				final int ndx2 = ((12 - card2.value) * 4) + card2.suit;
				cardArray[ndx1].setText(card1.toString());
				cardArray[ndx2].setText(card2.toString());
				location.put(card1.toString(), "cardArray");
				location.put(card2.toString(), "cardArray");
				hole1Array[i].setText(new StringBuilder().append("h").append(String.valueOf(i)).append("a").toString());
				hole2Array[i].setText(new StringBuilder().append("h").append(String.valueOf(i)).append("b").toString());
			}
		}
	}

	/*-  **************************************************************************** 
	 * Board cards are all returned to cardArray 
	****************************************************************************** */
	void returnBoardCardsToCardArray() {
		Card card1;
		for (int i = 0; i < 5; i++) {
			if (boardArray[i].getText().length() == 2) {
				card1 = new Card(boardArray[i].getText());
				final int ndx1 = ((12 - card1.value) * 4) + card1.suit;
				cardArray[ndx1].setText(card1.toString());
				location.put(card1.toString(), "cardArray");
				boardArray[i].setText("bo" + String.valueOf(i));
			}
		}
	}

	/*-  **************************************************************************** 
	 * The hole1Array and hole2Array  cards we will keep when after the deck is
	 * shuffled but we need to restore dead cards in Deck and restore EvalData.
	****************************************************************************** */
	void restoreHoleCardsInDeckAndEvalData() {
		Card card1;
		Card card2;
		for (int i = 0; i < 6; i++) {
			if (hole1Array[i].getText().length() == 2 && hole2Array[i].getText().length() == 2) {
				card1 = new Card(hole1Array[i].getText());
				card2 = new Card(hole2Array[i].getText());
				Evaluate.setHoleCards(i, card1, card2);
			}
		}
	}

	/*-  ************************************************************************** 
	 * The boardArray cards we will keep when after the deck is shuffled 
	 * but we need to restore dead cards in Deck and restore EvalData.
	****************************************************************************** */
	void restoreBoardCardsInDeckAndEvalData() {
		Card card1 = null;
		Card card2 = null;
		Card card3 = null;
		Card card4 = null;
		Card card5 = null;
		if (boardArray[0].getText().length() == 2) {
			card1 = new Card(hole1Array[0].getText());
		}
		if (boardArray[1].getText().length() == 2) {
			card2 = new Card(hole1Array[1].getText());
		}
		if (boardArray[2].getText().length() == 2) {
			card3 = new Card(hole1Array[2].getText());
		}
		if (boardArray[3].getText().length() == 2) {
			card4 = new Card(hole1Array[3].getText());
		}
		if (boardArray[4].getText().length() == 2) {
			card5 = new Card(hole1Array[4].getText());
		}
		if (card1 != null && card2 != null && card3 != null) {
			Evaluate.setFlopCards(card1, card2, card3);
		}
		if (card4 != null) {
			Evaluate.setTurnCard(card4);
		}
		if (card5 != null) {
			Evaluate.setRiverCard(card5);
		}
	}

	/*-  ******************************************************************************
	 * Do random board.
	 * Called when random board button is clicked.
	 * We start by shuffling the deck.
	 * We do not want to loose the cards in hole1Array or hole2Array, just replace
	 * the cards in boardArray with new cards. 
	 * So we return these cards to cardArray.
	 * After the shuffle we set the cards in hole1Array and hole2Array ( not deal them )
	 * in order to update EvalData and set dead cards.
	 *
	 * If there are already cards in boardArray they are removed and returned to
	 * cardArray. 
	 * Cards in EvalData.board are 
	 * 
	 * If boardArray is empty then Evaluate is called to deal cards.
	 * The dealt cards in EvalData.board are copied to boardArray and removed from
	 * cardArray.
	 *  ****************************************************************************** */
	private void randomBoard() {
		randomBoardCreated = true;

		Dealer.dealFlop();
		Dealer.dealTurnCard();
		Dealer.dealRiverCard();
		// NDealer.ow remove these cards from card array
		for (int i = 0; i < 5; i++) {
			final var card1 = EvalData.board[i];
			final var cardSt = card1.toString();
			final int ndx = ((12 - card1.value) * 4) + card1.suit;
			cardArray[ndx].setText("");
			boardArray[i].setText(cardSt);
			boardArray[i].setFont(fs);
			location.put(cardSt, "bo" + String.valueOf(i));
		}
		panelCards.repaint();
		panelBoard.repaint();
	}

	/*-  ******************************************************************************
	 * Do random hole cards
	 * Called when random hole cards button is clicked.
	 * If there are already cards in boardArray they are removed and returned to
	 * cardArray and if also in EvalData.board ??
	 * 
	 * If holexArray is empty then Evaluate is called to deal cards.
	 * The dealt cards in EvalData.holeCards are copied to holexArray and removed 
	 * from cardArray.
	 *  ****************************************************************************** */
	private void randomHoleCards() {
		randomHoleCardsCreated = true;

		for (int i = 0; i < PLAYERS; i++) {
			seatActive[i] = true;
			Dealer.dealHoleCards(i);
			final var card1 = EvalData.holeCards[i][0];
			final var card2 = EvalData.holeCards[i][1];
			final var cardSt1 = card1.toString();
			final var cardSt2 = card2.toString();
			final int ndx1 = ((12 - card1.value) * 4) + card1.suit;
			final int ndx2 = ((12 - card2.value) * 4) + card2.suit;
			cardArray[ndx1].setText("");
			cardArray[ndx2].setText("");
			hole1Array[i].setText(cardSt1);
			hole2Array[i].setText(cardSt2);
			hole1Array[i].setFont(fx);
			hole2Array[i].setFont(fx);
			location.put(cardSt1, new StringBuilder().append("h").append(String.valueOf(i)).append("a").toString());
			location.put(cardSt2, new StringBuilder().append("h").append(String.valueOf(i)).append("b").toString());
		}
		panelCards.repaint();
		panelBoard.repaint();
	}

	/*-  ******************************************************************************
	 * Update display for made hands and draws and Playability
	 *  ****************************************************************************** */
	private void updateDrawsAndMade() {
		updateMadeHand();
		updateDrawHand();
		updatePlayability();
	}

	/*-  ******************************************************************************
	 * Update display various things, position, stack, pot odds, odds, 
	 *  ****************************************************************************** */
	private void updateData() {
		for (int i = 0; i < 6; i++) {
			positionArray[i].setText(POSITION_ST2[EvalData.positions[i]]);
			stackArray[i].setText(Format.format$(EvalData.stackBD[i]));
			potOddsArray[i].setText(Format.decFormatRatio(EvalData.potOdds[i]));
			oddsArray[i].setText(Format.decFormatRatio(EvalData.oddsForPlayerFlopToTurn[i]));
		}
		pot.setText(Format.format$(EvalData.potBD));
	}

	/*-  ******************************************************************************
	 * The Reset option has been selected.
	 * The JFrame is set back to initial startup condition
	 *  ****************************************************************************** */
	private void doReset() {
		restoreCardArray();
		restoreBoardArray();
		restoreHoleArray();
		radioSeat[0].setSelected(true);
		for (int i = 0; i < 6; i++) {
			playArray[i].setText("");
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				handsArray[i][j].setText("");
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				drawsArray[i][j].setText("");
			}
		}
		for (int i = 0; i < 6; i++) {
			drawArrayType[i].setText("");
		}
		for (int i = 0; i < 6; i++) {
			madeArrayType[i].setText("");
		}
		freezeSeat1.setSelected(false);
	}

	/*-  ******************************************************************************
	 * Restore the cardArray to initial condition
	 *  ****************************************************************************** */
	void restoreCardArray() {
		for (int i = 0; i < 52; i++) {
			final var c = new Card(CARDS[i]);
			cardArray[i].setFont(fs);
			if (c.suit == 0) {
				cardArray[i].setBackground(SPADE);
			} else if (c.suit == 1) {
				cardArray[i].setBackground(DIAMOND);
			} else if (c.suit == 2) {
				cardArray[i].setBackground(CLUB);
			} else {
				cardArray[i].setBackground(HEART);
			}
			cardArray[i].setText(Card.cardToString(i));
			location.put(cardArray[i].getText(), "cardArray");
		}
	}

	/*-  ******************************************************************************
	 * Restore the boardArray to initial condition
	 *  ****************************************************************************** */
	void restoreBoardArray() {
		for (int i = 0; i < 5; i++) {
			boardArray[i].setText("bo" + String.valueOf(i));
			boardArray[i].setFont(fp);
		}
	}

	/*-  ******************************************************************************
	 * Restore the hole1Array and hole2Array to initial condition
	 *  ****************************************************************************** */
	void restoreHoleArray() {
		for (int i = 0; i < 6; i++) {
			hole1Array[i].setText(new StringBuilder().append("h").append(String.valueOf(i)).append("a").toString());
			hole2Array[i].setText(new StringBuilder().append("h").append(String.valueOf(i)).append("b").toString());
			hole1Array[i].setFont(fp);
			hole2Array[i].setFont(fp);
		}
	}

	/*-  ******************************************************************************
	 * Handle check boxes 
	 * Check the status of boxes and set boolean variables;
	 *  ****************************************************************************** */
	private void checkBoxesl() {
		if (preflopSimulation.isSelected()) {
			preflopSimulationOption = true;
		} else {
			preflopSimulationOption = false;
		}
		if (randomHoleCards.isSelected()) {
			randomHoleCardsOption = true;
		} else {
			randomHoleCardsOption = false;
		}
		if (randomBoard.isSelected()) {
			randomBoardOption = true;
		} else {
			randomBoardOption = false;
		}
		if (freezeSeat1.isSelected()) {
			freezeSeat1Option = true;
		} else {
			freezeSeat1Option = false;
		}

		if (detailedReport.isSelected()) {
			detailedReportOption = true;
		} else {
			detailedReportOption = false;
		}
		if (boardReport.isSelected()) {
			boardReportOption = true;
		} else {
			boardReportOption = false;
		}
		if (preflopReport.isSelected()) {
			preflopReportOption = true;
		} else {
			preflopReportOption = false;
		}
	}

	/*-  ******************************************************************************
	 * Handle rafio button clicks for seat 
	 *  ****************************************************************************** */
	private void buttonSeat(String action) {
		final var st = action;
		switch (st) {
			case "Seat1" -> {
				selectedSeat = 0;
				if (seatActive[0] && displayActive) {
					doExecute();
				}
			}
			case "Seat2" -> {
				selectedSeat = 1;
				if (seatActive[0] && displayActive) {
					doExecute();
				}
			}
			case "Seat3" -> {
				selectedSeat = 2;
				if (seatActive[0] && displayActive) {
					doExecute();
				}
			}
			case "Seat4" -> {
				selectedSeat = 3;
				if (seatActive[0] && displayActive) {
					doExecute();
				}
			}
			case "Seat5" -> {
				selectedSeat = 4;
				if (seatActive[0] && displayActive) {
					doExecute();
				}
			}
			case "Seat6" -> {
				selectedSeat = 5;
				if (seatActive[0] && displayActive) {
					doExecute();
				}
			}
			default -> Logger.log("//ERROR  buttonSeat() invalid seat " + action);
		}
	}

	/*-  ******************************************************************************
	* Handle button click actions
	* There have been 2 clicks, a from click followed by a to click.
	* The second click had a length of 3 so it must be in the form hxa or hxb.
	* On the first click the card was saved in from clicked. 
	* We place the saved card in that location and update the location HashMap.
	* 
	* Arg0 -  action 
	*  ****************************************************************************** */
	private void buttonHoleClickEmpty(String action) {
		final var loc = location.get(fromClicked);
		if (loc == null || "".equals(loc)) {
			Logger.log(new StringBuilder().append("//ERROR buttonHoleClick() card not found in HashMap ")
					.append(fromClicked).append(" ").append(action).append(" ").append(loc).toString());
			Crash.log("program bug ");
		}
		final var stX = action.substring(1, 2);
		final var stAB = action.substring(2);
		final int x = Integer.parseInt(stX);

		// Location is empty
		final var card = new Card(fromClicked);
		final int ndx = ((12 - card.value) * 4) + card.suit;
		cardArray[ndx].setText("");
		if ("a".equals(stAB)) {
			hole1Array[x].setText(fromClicked);
			hole1Array[x].setFont(fx);
			location.put(fromClicked, action);
		} else {
			hole2Array[x].setText(fromClicked);
			hole2Array[x].setFont(fx);
			location.put(fromClicked, action);
		}
		panelCards.repaint();
		panelHoleCards.repaint();
	}

	/*-  ******************************************************************************
	* Handle button click actions
	 * There have been 2 clicks, a from click followed by a to click.
	`* The location is empty because the action length was 2 and started witb "b".
	 * We just add the from card and update the location HashMap.
	 * 
	 * Arg0 -  Action  
	 *  ****************************************************************************** */
	private void buttonBoardClickEmpty(String action) {
		final var card = new Card(fromClicked);
		final int ndx = ((12 - card.value) * 4) + card.suit;
		final var stX = action.substring(2);
		final int x = Integer.parseInt(stX);
		cardArray[ndx].setText("");
		boardArray[x].setText(fromClicked);
		boardArray[x].setFont(fx);
		location.put(fromClicked, action);
		panelCards.repaint();
		panelBoard.repaint();
	}

	/*-  ******************************************************************************
	* Handle button click actions
	* There have been 2 clicks, a from click followed by a to click.
	* The second click was a card and the location HashMap placed it here.
	* On the first click the card was saved in from clicked. 
	* Things are a little more complicated. 
	* First we must return the card in hole1Array or hole2Array to cardArray and
	* update the location HashMap.
	* Then we place the card in fromClicked into hole1Array or hole2 array and
	* update location HashMap fot this new location.
	* 
	* Arg0 -  card clicked on ( in holexArray )
	* Arg1 - location from HashMap
	*  ****************************************************************************** */
	private void buttonHoleClickHasCard(String card, String loc) {
		var c = new Card(card);
		int ndx = ((12 - c.value) * 4) + c.suit;
		cardArray[ndx].setText(card);
		location.put(card, loc);

		c = new Card(fromClicked);
		ndx = ((12 - c.value) * 4) + c.suit;
		cardArray[ndx].setText("");
		location.put(fromClicked, loc);
		final var stX = loc.substring(1, 2);
		final int x = Integer.parseInt(stX);

		if ("a".equals(loc.substring(2))) {
			hole1Array[x].setText(fromClicked);
			hole1Array[x].setFont(fx);
		} else {
			hole1Array[x].setText(fromClicked);
			hole1Array[x].setFont(fx);
		}
		panelCards.repaint();
		panelHoleCards.repaint();
	}

	/*-  ******************************************************************************
	* Handle button click actions
	* There have been 2 clicks, a from click followed by a to click.
	* The second click was a card and the location HashMap placed it here.
	* On the first click the card was saved in from clicked. 
	* Things are a little more complicated. 
	* First we must return the card in boardArray to cardArray and
	* update the location HashMap.
	* Then we place the card in fromClicked into boardArray and
	* update location HashMap fot this new location.
	* 
	* Arg0 -  card clicked on ( in holexArray )
	* Arg1 - location from HashMap
	*  ****************************************************************************** */
	private void buttonBoardClickHasCard(String card, String loc) {
		var c = new Card(card);
		int ndx = ((12 - c.value) * 4) + c.suit;
		cardArray[ndx].setText(card);
		location.put(card, loc);

		c = new Card(fromClicked);
		ndx = ((12 - c.value) * 4) + c.suit;
		cardArray[ndx].setText("");
		location.put(fromClicked, loc);

		final var stX = loc.substring(2);
		final int x = Integer.parseInt(stX);
		boardArray[x].setText(fromClicked);
		boardArray[x].setFont(fx);
		panelCards.repaint();
		panelBoard.repaint();
	}

	/*-  ******************************************************************************
	 * Check that card is a valid card
	 *******************************************************************************/
	boolean isValidCard(String st) {
		boolean result = false;
		for (int i = 0; i < 52; i++) {
			if (st.equals(Card.CARD_STRING[i])) {
				result = true;
				break;
			}
		}
		return result;
	}

	/*-  ******************************************************************************
	 * Update Made hand
	 * Check the cards in every evalData.hand for each street.
	 * If not null then move cards from instance to hands JTextArea
	 *  ****************************************************************************** */
	private void updateMadeHand() {
		for (int i = 0; i < 6; i++) {
			if (EvalData.hand[i] == null) {
				for (int j = 0; j < 5; j++) {
					handsArray[i][j].setText("");
				}
			} else {
				for (int j = 0; j < 5; j++) {
					if (EvalData.hand[i].type == MADE_NONE) {
						handsArray[i][j].setForeground(BLACK);
					} else {
						handsArray[i][j].setForeground(GOOD);
					}
					handsArray[i][j].setText(EvalData.hand[i].cardsHoleCardsFirst[j].toString());
				}
				if (EvalData.hand[i].type == MADE_NONE) {
					madeArrayType[i].setForeground(BLACK);
				} else {
					madeArrayType[i].setForeground(GOOD);
				}
				madeArrayType[i].setText(MADE_ARRAY_ST[EvalData.hand[i].type]);
			}
		}
		panelHands.repaint();
		panelMadeType.repaint();
	}

	/*-  ******************************************************************************
	 * Update Draw hand
	 * Check the cards in every evalData.hand for each street.
	 * If not null then move cards from instance to hands JTextArea
	 *  ****************************************************************************** */
	private void updateDrawHand() {
		if (selectedStreet == RIVER || selectedStreet == SHOWDOWN) {
			panelDraws.setVisible(false);
			panelDrawType.setVisible(false);
			return;
		}
		for (int i = 0; i < 6; i++) {
			if (EvalData.draw[i] != null) {
				if (selectedStreet == TURN) {
					for (int j = 0; j < 5; j++) {
						if (EvalData.draw[i].type == DRAW_NONE) {
							drawsArray[i][j].setForeground(BLACK);
						} else {
							drawsArray[i][j].setForeground(GOOD);
							handsArray[i][j].setForeground(GOOD);
						}
						drawsArray[i][j].setText(EvalData.draw[i].cardsHoleCardsFirst[j].toString());
					}
				}
				if (selectedStreet == FLOP || selectedStreet == TURN) {
					if (EvalData.draw[i].type == DRAW_NONE) {
						drawArrayType[i].setForeground(BLACK);
					} else {
						drawArrayType[i].setForeground(GOOD);
					}
					drawArrayType[i].setText(DRAW_ARRAY_ST[EvalData.draw[i].type]);
				}
			}
		}
		if (selectedStreet == FLOP) {
			panelDraws.setVisible(false);
		} else {
			panelDraws.setVisible(true);
			panelDraws.repaint();
		}
		panelDrawType.setVisible(true);
		panelDrawType.repaint();
		panelHands.repaint();
	}

	/*-  ******************************************************************************
	 * Update Playability 
	 *  ****************************************************************************** */
	private void updatePlayability() {
		Card card1;
		Card card2;
		for (int i = 0; i < 6; i++) {
			if (hole1Array[i].getText().length() == 2 && hole2Array[i].getText().length() == 2) {
				card1 = new Card(hole1Array[i].getText());
				card2 = new Card(hole2Array[i].getText());
				final int ndx = Dealer.getArrayIndexCard(card1, card2);
				int pn = HandRange.getPlayabilityNumeric(ndx);
				pn = 169 - pn;
				playArray[i].setText(String.valueOf(pn));
				if (pn > 148) {
					playArray[i].setForeground(GOOD);
				} else {
					playArray[i].setForeground(BLACK);
				}
			} else {
				playArray[i].setText("");
				playArray[i].setForeground(BLACK);
			}
		}
		panelPlay.repaint();
	}

	/*-  ******************************************************************************
	* Implement the actionPerformed method of the ActionListener interface
	* Responds to any button click.
	* First we determine if this is a click on cardArray.
	* If it is, then it is  first click and all that we do is to save he card in firstClick
	* and exit waiting for second click.
	* 
	* If there is a click on a hole card or board card that is the first click it is ignored.
	* 
	* If it is not a click on cardArray then it may be a second click, or a click from 
	* a radio button or control button.
	 *  ****************************************************************************** */
	@Override
	public void actionPerformed(ActionEvent e) {
		final var action = e.getActionCommand();
		checkBoxesl();
		var st = "";

		if (action.length() == 2) {
			final var loc = location.get(action);
			if (loc == null || "".equals(loc)) {
				Logger.log("//ERROR location HashMap returned null " + action);
				Crash.log("//Program bug ");
			}
			if ("cardArray".equals(loc)) {
				click1 = true;
				fromClicked = action;
				return;
			}
			// Click must be a location with a card in it
			st = loc.substring(0, 1);
			switch (st) {
				case "h" -> {
					buttonHoleClickHasCard(action, loc);
					clearClicks();
				}
				case "b" -> {
					buttonBoardClickHasCard(action, loc);
					clearClicks();
				}
				default -> {
					Logger.log("//ERROR location HashMap returned impossible location " + loc);
					Crash.log("//Program bug ");
				}
			}
			return;
		}

		if (action.length() == 3) {
			if ("bo".equals(action.substring(0, 2))) {
				if (click1) {
					buttonBoardClickEmpty(action);
					return;
				} else {
					clearClicks();
					return;
				}
			}
			if ("h".equals(action.substring(0, 1))) {
				if (click1) {
					buttonHoleClickEmpty(action);
				} else {
					clearClicks();
				}
			}
			return;
		}

		st = action.substring(0, 4);
		clearClicks();
		switch (st) {
			case "Seat" -> buttonSeat(action);
			case "Flop" -> buttonStreet(action);
			case "Turn" -> buttonStreet(action);
			case "Rive" -> buttonStreet(action);
			case "Show" -> buttonStreet(action);
			default -> {
			}
		}

		switch (action) {
			case "Execute" -> doExecute();
			case "Reset" -> doReset();
			case "Help" -> Popup.popup(helpString);
			case "Exit" -> {
			}
			default -> {
			}
		}

	}

}
