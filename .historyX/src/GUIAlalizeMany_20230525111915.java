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

public class GUIAlalizeMany implements Constants {

    /*-  **************************************************************************
    * This class is used to analyze thousands or millions of simulated hands.
    * Using a monti carlo methodology determine characteristics of a Hold'em 
    * 6-max no limit game. 
    * Specifically to find ways to characterize a Flop, such as HML ( High Medium Low )
    * which results in only 10 Flop types. A million or more hands are run, and for 
    * each hand run the draws and made hands and other data is analyzed and collected
    * in arrays which will be analuzed after a run is completed. 
    * We analyze each street and Showdown.
    * 
    * This is not a final implememtation. We will be experimenting contineously 
    * trying to find ways to characterize a Flop that will result in  information on
    * how best to play a hand. 
    * For example for a Flop type:
    *   It may result in a high percentage of made hands and we do not have a made hand.
    *   It may result in a low percentage of made hands and we hve have a made hand.
    *   It may result in a hiogh percentage of strong draws.
    *   It may result in a low percentage of strong draws.
    *   It may result in a a high percentage of times that the made hand is the winning
    *   hand at Showdown.
    *   And many more.
    *
    *
    *   
    * 
    * @author PEAK_
    ***************************************************************************** */



   // @formater:off
	String helpString = new StringBuilder().append("Welcome to the evaluate_street project GUI.\r\n")
    .append("This GUI demonstrates how  hole cards are combined with a conmmon board and many differeny\r\n")
    .append("types of analysis are performed.  This GUI just touches the surface of what this project can do\r\n")
    .append("To the left of the screen is an array of 52 cards. Click on one of these cards and then click on a card \r\n ")
    .append("somewhere else in the panel until you have a hand(s) that you want to evaluate. \r\n ")
    .append("The second row is radio buttons that select the seat for which detailed evaluations will be done.\r\n ")
    .append("The Scroll Panel to the far right is where the detailed evaluation will appear.\r\n")
    .append("The 4th through 8th colums are where there will be a summary evaluation.\r\n ")
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
    .append(" \r\n")
    .append("In a later release I will add a GUI for more advanced simulation where hundreds of thousands of hands are \r\n")
    .append("are run, using random cards, and gathering results based on detailed analsis of each hand simulated.\r\n")
    .append("\r\n ").toString();
// @formater:on 
     private static JPanel panelRadioHands = new JPanel();

    private JPanel mainPanel;

    private JPanel panelHeader;

	private JPanel panelControl; // Control

	private static final JButton execute = new JButton("Execute");

    private static final JButton help = new JButton("Help");

	private static final JButton exit = new JButton("Exit");

    private static final JRadioButton thousand = new JRadioButton("1,000");

    private static final JRadioButton tenThousand = new JRadioButton("10,000");

   private static final JRadioButton hundredThousand = new JRadioButton("100,000");

     private static final JRadioButton million = new JRadioButton("1,000,000");

    private static final JRadioButton twoMillion = new JRadioButton("2,000,000");

    private static final JRadioButton fiveMillion = new JRadioButton("5,000,000");

    private static final JRadioButton tenMillion = new JRadioButton("10,000,000");

    private static final JRadioButton hundredMillion = new JRadioButton("100,000,000");

    private static final JButton buttonRun = new JButton("Run");


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

     

        panelRadioHands = new JPanel(new GridLayout(1, 5));
        panelRadioHands.setBounds(300, 310, 450, 40);
        final var handsBox = Box.createHorizontalBox();
        final var handstGroup = new ButtonGroup();
        handsBox.add(panelRadioHands);
        mainPanel.add(panelRadioHands);

       

        panelControl = new JPanel(new GridLayout(1, 6));
        panelControl.setBounds(300, 420, 750, 40);
        mainPanel.add(panelControl);

        panelCheckBoxesReports = new JPanel(new GridLayout(1, 3));
		panelCheckBoxesReports.setBounds(300, 380, 600, 40);
		mainPanel.add(panelCheckBoxesReports);


       
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

        run.setFont(fc);
        run.setPreferredSize(new Dimension(15, 15));
        run.addActionListener(this);
        run.setBackground(CONTROL);
        panelControl.add(run);

      
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

    handsToPlayRadio.add(handsToPlay);panel0.add(handsToPlayRadio);

    thousand.setSelected(false);tenThousand.setSelected(false);twentyFiveThousand.setSelected(false);fiftyThousand.setSelected(false);hundredThousand.setSelected(false);twoHundredFiftyThousand.setSelected(false);million.setSelected(false);tenMillion.setSelected(false);

    switch(Options.handsToPlay)

    {
    case 100:
        hundred.setSelected(true);
        break;
    case 1000:
        thousand.setSelected(true);
        break;
    case 10000:
        tenThousand.setSelected(true);
        break;
    case 25000:
        twentyFiveThousand.setSelected(true);
        break;
    case 50000:
        fiftyThousand.setSelected(true);
        break;
    case 100000:
        hundredThousand.setSelected(true);
        break;
    case 250000:
        twoHundredFiftyThousand.setSelected(true);
        break;
    case 1000000:
        million.setSelected(true);
        break;
    case 10000000:
        tenMillion.setSelected(true);
        break;
    case 100000000:
        hundredMillion.setSelected(true);
        break;
    }
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

}