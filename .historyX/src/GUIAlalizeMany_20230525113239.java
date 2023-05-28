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

	private JPanel panelControl;

    private JPanel panelCheckBoxesReports;

    private JPanel panelRadioBoxesHands;



	private static final JButton run = new JButton("Execute");

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

  

    static void setHandsToPlay(int handsToPlay) {
    switch(Options.handsToPlay)

    {
 
    case 1000:
        thousand.setSelected(true);
        break;
    case 10000:
        tenThousand.setSelected(true);
        break;
      case 100000:
        hundredThousand.setSelected(true);
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

					
		switch (action) {
			case "Run -> doExecute();
			case "Help" -> Popup.popup(helpString);
			case "Exit" -> {
			}
			default -> {
			}
		}

	}
   

}
