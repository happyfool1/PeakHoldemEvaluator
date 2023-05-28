//package evaluate_streets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.*;

public class GUIAlalizeMany extends JFrame implements ActionListener, Constants {

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

    private static final Color CONTROL = GREEN;

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

    private static JFrame freme = new JFrame();

    private static JPanel panelRadioHands = new JPanel();

    private JPanel mainPanel;

    private JPanel panelCheckBoxes;;

    private JPanel panelControl;

    private JPanel panelCheckBoxesHML;

    private static final JCheckBox hmlDraw = new JCheckBox("HML Flop Draw");
    private static final JCheckBox hmlMade = new JCheckBox("HML Flop Made Hand");
    private static final JCheckBox hmlShowdown = new JCheckBox("HML Flop Wins Showdown");

    private JPanel panelCheckBoxesHHML;
    private static final JCheckBox hhmlDraw = new JCheckBox("HHML Turn Draw");
    private static final JCheckBox hhmlMade = new JCheckBox("HML Turn Made Hand");
    private static final JCheckBox hhmlShowdown = new JCheckBox("HML Turn Wins Showdown");

    private JPanel panelCheckBoxesHHHML;
    private static final JCheckBox hhhmlDraw = new JCheckBox("HHHML River Draw");
    private static final JCheckBox hhhmlMade = new JCheckBox("HHHML River Made Hand");
    private static final JCheckBox hhhmlShowdown = new JCheckBox("HHHML River Wins Showdown");

    private JPanel panelCheckBoxesSumOfFlop;
    private static final JCheckBox sumOfDraw = new JCheckBox("Sum Of Flop Cards Draw");
    private static final JCheckBox sumOfMade = new JCheckBox("Sum Of Flop Cards Made Hand");
    private static final JCheckBox sumOfShowdown = new JCheckBox("Sum of Flop Cards Wins Showdown");

    private JPanel panelCheckBoxesType;
    private static final JCheckBox typeDraw = new JCheckBox("Type Draw");
    private static final JCheckBox typeMade = new JCheckBox("Type Made Hand");
    private static final JCheckBox typeShowdown = new JCheckBox("Yype Wins Showdown");

    private JPanel panelCheckBoxesWetDry;
    private static final JCheckBox wetDryDraw = new JCheckBox("Wet Dry Draw");
    private static final JCheckBox wetDryMade = new JCheckBox("Wet DRy Made Hand");
    private static final JCheckBox wetDryShowdown = new JCheckBox("Wet Dry Wins Showdown");

    private JPanel panelRadioBoxesHands;

    private static final JButton run = new JButton("Run");

    private static final JButton help = new JButton("Help");

    private static final JButton exit = new JButton("Exit");

    private static final JRadioButton thousand = new JRadioButton("1,000");

    private static final JRadioButton tenThousand = new JRadioButton("10,000");

    private static final JRadioButton hundredThousand = new JRadioButton("100,000");

    private static final JRadioButton million = new JRadioButton("1,000,000");

    private static final JRadioButton tenMillion = new JRadioButton("10,000,000");

    private static final JRadioButton hundredMillion = new JRadioButton("100,000,000");

    private static final Font fs = new Font("Dialog", Font.PLAIN, 4);
    private static final Font fc = new Font("Dialog", Font.PLAIN, 4);

    /*- **************************************************************************** 
    	* Main
    	*******************************************************************************/
    public static void main(String[] args) {
        final var frame = new GUIAlalizeMany();
        frame.myFrame();
    }

    /*- **************************************************************************** 
    * Frame
    *******************************************************************************/
    public void myFrame() {
        JButton run = new JButton("Run");
        Evaluate.initialize();
        /// Set up the main panel
        mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(1150, 550));

        panelRadioHands = new JPanel(new GridLayout(1, 5));
        panelRadioHands.setBounds(300, 310, 450, 40);
        final var handsBox = Box.createHorizontalBox();
        final var handsGroup = new ButtonGroup();
        handsBox.add(panelRadioHands);
        mainPanel.add(panelRadioHands);

        panelControl = new JPanel(new GridLayout(1, 3));
        panelControl.setBounds(300, 420, 750, 40);
        mainPanel.add(panelControl);

        panelCheckBoxes = new JPanel(new GridLayout(6, 1));
        panelCheckBoxes.setBounds(100, 420, 1150, 550);
        mainPanel.add(panelCheckBoxes);

        panelCheckBoxesHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHML.setBounds(300, 380, 600, 40);
        panelCheckBoxes.add(panelCheckBoxesHML);

        panelCheckBoxesHHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHHML.setBounds(300, 380, 600, 40);
        panelCheckBoxes.add(panelCheckBoxesHHML);

        panelCheckBoxesHHHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHHHML.setBounds(300, 380, 600, 40);
        panelCheckBoxes.add(panelCheckBoxesHHHML);

        panelCheckBoxesSumOfFlop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesSumOfFlop.setBounds(300, 380, 600, 40);
        panelCheckBoxes.add(panelCheckBoxesSumOfFlop);

        panelCheckBoxesType = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesType.setBounds(300, 380, 600, 40);
        panelCheckBoxes.add(panelCheckBoxesType);

        panelCheckBoxesWetDry = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesWetDry.setBounds(300, 380, 600, 40);
        panelCheckBoxes.add(panelCheckBoxesWetDry);

        thousand.setFont(fs);
        thousand.addActionListener(this);
        panelRadioHands.add(thousand);
        handsGroup.add(thousand);

        tenThousand.setFont(fs);
        tenThousand.addActionListener(this);
        panelRadioHands.add(tenThousand);
        handsGroup.add(tenThousand);

        hundredThousand.setFont(fs);
        hundredThousand.addActionListener(this);
        panelRadioHands.add(hundredThousand);
        handsGroup.add(hundredThousand);

        million.setFont(fs);
        million.addActionListener(this);
        panelRadioHands.add(million);
        handsGroup.add(million);

        tenMillion.setFont(fs);
        tenMillion.addActionListener(this);
        panelRadioHands.add(tenMillion);
        handsGroup.add(tenMillion);

        hundredMillion.setFont(fs);
        hundredMillion.addActionListener(this);
        panelRadioHands.add(hundredMillion);
        handsGroup.add(hundredMillion);

        hmlDraw.setFont(fs);
        hmlDraw.setPreferredSize(new Dimension(25, 25));
        hmlDraw.addActionListener(this);
        hmlDraw.setSelected(true);
        panelCheckBoxesHML.add(hmlDraw);

        hmlMade.setFont(fs);
        hmlMade.setPreferredSize(new Dimension(25, 25));
        hmlMade.addActionListener(this);
        hmlMade.setSelected(true);
        panelCheckBoxesHML.add(hmlMade);

        hmlShowdown.setFont(fs);
        hmlShowdown.setPreferredSize(new Dimension(25, 25));
        hmlShowdown.addActionListener(this);
        hmlShowdown.setSelected(true);
        panelCheckBoxesHML.add(hmlShowdown);

        hhmlDraw.setFont(fs);
        hhmlDraw.setPreferredSize(new Dimension(25, 25));
        hhmlDraw.addActionListener(this);
        hhmlDraw.setSelected(true);
        panelCheckBoxesHHML.add(hhmlDraw);

        hhmlMade.setFont(fs);
        hhmlMade.setPreferredSize(new Dimension(25, 25));
        hhmlMade.addActionListener(this);
        hhmlMade.setSelected(true);
        panelCheckBoxesHHML.add(hhmlMade);

        hhmlShowdown.setFont(fs);
        hhmlShowdown.setPreferredSize(new Dimension(25, 25));
        hhmlShowdown.addActionListener(this);
        hhmlShowdown.setSelected(true);
        panelCheckBoxesHHML.add(hhmlShowdown);

        hhhmlDraw.setFont(fs);
        hhhmlDraw.setPreferredSize(new Dimension(25, 25));
        hhhmlDraw.addActionListener(this);
        hhhmlDraw.setSelected(true);
        panelCheckBoxesHHHML.add(hhhmlDraw);

        hhhmlMade.setFont(fs);
        hhhmlMade.setPreferredSize(new Dimension(25, 25));
        hhhmlMade.addActionListener(this);
        hhhmlMade.setSelected(true);
        panelCheckBoxesHHHML.add(hhhmlMade);

        hhhmlShowdown.setFont(fs);
        hhhmlShowdown.setPreferredSize(new Dimension(25, 25));
        hhhmlShowdown.addActionListener(this);
        hhhmlShowdown.setSelected(true);
        panelCheckBoxesHHHML.add(hhhmlShowdown);

        typeDraw.setFont(fs);
        typeDraw.setPreferredSize(new Dimension(25, 25));
        typeDraw.addActionListener(this);
        typeDraw.setSelected(true);
        panelCheckBoxesType.add(typeDraw);

        typeMade.setFont(fs);
        typeMade.setPreferredSize(new Dimension(25, 25));
        typeMade.addActionListener(this);
        typeMade.setSelected(true);
        panelCheckBoxesType.add(typeMade);

        typeShowdown.setFont(fs);
        typeShowdown.setPreferredSize(new Dimension(25, 25));
        typeShowdown.addActionListener(this);
        typeShowdown.setSelected(true);
        panelCheckBoxesType.add(typeShowdown);

        sumOfDraw.setFont(fs);
        sumOfDraw.setPreferredSize(new Dimension(25, 25));
        sumOfDraw.addActionListener(this);
        sumOfDraw.setSelected(true);
        panelCheckBoxesSumOfFlop.add(sumOfDraw);

        sumOfMade.setFont(fs);
        sumOfMade.setPreferredSize(new Dimension(25, 25));
        sumOfMade.addActionListener(this);
        sumOfMade.setSelected(true);
        panelCheckBoxesSumOfFlop.add(sumOfMade);

        sumOfShowdown.setFont(fs);
        sumOfShowdown.setPreferredSize(new Dimension(25, 25));
        sumOfShowdown.addActionListener(this);
        sumOfShowdown.setSelected(true);
        panelCheckBoxesSumOfFlop.add(sumOfShowdown);

        wetDryDraw.setFont(fs);
        wetDryDraw.setPreferredSize(new Dimension(25, 25));
        wetDryDraw.addActionListener(this);
        wetDryDraw.setSelected(true);
        panelCheckBoxesWetDry.add(wetDryDraw);

        wetDryMade.setFont(fs);
        wetDryMade.setPreferredSize(new Dimension(25, 25));
        wetDryMade.addActionListener(this);
        wetDryMade.setSelected(true);
        panelCheckBoxesWetDry.add(wetDryMade);

        wetDryShowdown.setFont(fs);
        wetDryShowdown.setPreferredSize(new Dimension(25, 25));
        wetDryShowdown.addActionListener(this);
        wetDryShowdown.setSelected(true);
        panelCheckBoxesWetDry.add(wetDryShowdown);

        panelCheckBoxes.add(panelCheckBoxesHML);
        panelCheckBoxes.add(panelCheckBoxesHHML);
        panelCheckBoxes.add(panelCheckBoxesHHHML);
        panelCheckBoxes.add(panelCheckBoxesSumOfFlop);
        panelCheckBoxes.add(panelCheckBoxesType);
        panelCheckBoxes.add(panelCheckBoxesWetDry);

        mainPanel.add(panelCheckBoxes);

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

    /*- **************************************************************************** 
    *  Hands to playb radio buttons
    *******************************************************************************/

    private static void setHandsToPlay(int handsToPlay) {
        switch (handsToPlay) {

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
    	 * Handle check boxes 
    	 * Check the status of boxes and set boolean variables;
    	 *  ****************************************************************************** */
    private void checkBoxesl() {
        if (hmlDraw.isSelected()) {

        } else {

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
            case "Run" -> doSimulationDataCollection();
            case "Help" -> Popup.popup(helpString);
            case "Exit" -> {
            }
            default -> {
            }
        }

    }

    /*-  ******************************************************************************
     * The Full Simulation  option has been selected.
     * Prompt for the number of hands to play
     *  ****************************************************************************** */
    private void doSimulationDataCollection() {
        int number = 0;
        if (number != 0) {
            String response = JOptionPane.showInputDialog(null,
                    "Enter number of hands to run",
                    "Hands to run",
                    JOptionPane.QUESTION_MESSAGE);

            // check if response is null or empty before trying to parse it to prevent
            // exceptions
            if (response != null && !response.isEmpty()) {
                try {
                    number = Integer.parseInt(response);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number entered");
                    number = -1;
                }
            }
        }
        if (number == 0) {
            number = 10000;
            EvalData.fullSimulation = true;
            EvalData.manyHands = true;
            EvalData.rotatePlayers = true;
            EvalData.hh = false;
            EvalData.detailedAnalysis = true;
            EvalData.fullSimulationNum = number;
            // Simulate the play of xxx hands, Prfeflop, Flop, Turn, and River
            Evaluate.doSimulationDataCollection(number);
            ReportManyHands.reportSummary(100, 100);
            ReportManyHands.reportBoardArrayFlopPer(500, 100);
            ReportManyHands.reportHML(100, 100);
            ReportManyHands.reportArrayWetDry(600, 100);
            // ReportManyHands.reportAny(1500, 100);

        }
    }

}