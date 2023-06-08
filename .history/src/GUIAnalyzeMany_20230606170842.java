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
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class GUIAnalyzeMany extends JFrame implements ActionListener, Constants {

    /*-  **************************************************************************
    * This class is used to analyze thousands or millions of simulated hands.
    * Using a monte-carlo methodology determine characteristics of a Hold'em 
    * 6-max no limit game. 
    * Specifically to find ways to characterize a Flop, such as HML ( High Medium Low )
    * which results in only 10 Flop types. A million or more hands are run, and for 
    * each hand run the draws and made hands and other data is analyzed and collected
    * in arrays which will be analyzed after a run is completed. 
    * We analyze each street and Showdown.
    * 
    * This is not a final implementation. We will be experimenting continuously 
    * trying to find ways to characterize a Flop that will result in  information on
    * how best to play a hand. 
    * For example for a Flop type:
    *   It may result in a high percentage of made hands and we do not have a made hand.
    *   It may result in a low percentage of made hands and we hve have a made hand.
    *   It may result in a high percentage of strong draws.
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

    // @formatter:off
    String helpString = new StringBuilder().append("Welcome to the PeakHoldemEvaluator project GUI.\r\n")
            .append("This GUI runs thousands or millions of hands in order to obtain data used in \r\n")
            .append("evaluating how different indexes relate to the draws, made hands and best hand at Showdown.\r\n")
            .append("We analyze thousands or millions of simulated hands\r\n")
            .append("Using a monte-carlo methodology determine characteristics of a Hold'em \r\n")
            .append("6-max no limit game. \r\n")
            .append("Specifically to find ways to characterize a Flop, such as HML ( High Medium Low )\r\n ")
            .append("which results in only 10 Flop types. A million or more hands are run, and for \r\n")
            .append("each hand run the draws and made hands and other data is analyzed and collected\r\n ")
            .append("in arrays which will be analyzed after a run is completed. \r\n")
            .append(" We analyze each street and Showdown.\r\n")
            .append("This is not a final implementation. We will be experimenting continuously \r\n ")
            .append("trying to find ways to characterize a Flop that will result in  information on\r\n")
            .append(" how best to play a hand.  \r\n")
             .append("For example for a Flop type:\r\n")
            .append(" It may result in a high percentage of made hands and we do not have a made hand.\r\n")
            .append(" It may result in a low percentage of made hands and we hve have a made hand.\r\n")
            .append(" It may result in a high percentage of strong draws.\r\n ")
            .append(" It may result in a low percentage of strong draws.\r\n")
            .append(" It may result in a a high percentage of times that the made hand is the winning \r\n")
            .append(" hand at Showdown.\r\n")
            .append("  And many more.\r\n\r\n")
            .append("\r\n ").toString();
    // @formatter:on

    private static JFrame frame = new JFrame();

    private static JPanel panelRadioHands = new JPanel();

    private JPanel mainPanel;

    private JPanel panelCheckBoxes;;

    private JPanel panelControl;

    private JPanel panelCheckBoxesHML;
    private static final JCheckBox hmlDrawFlop = new JCheckBox("HML Flop Draw");
    private static final JCheckBox hmlMadeFlop = new JCheckBox("HML Flop Made Hand");
    private static final JCheckBox hmlShowdownFlop = new JCheckBox("HML Flop Showdown");

    private JPanel panelCheckBoxesHHML;
    private static final JCheckBox hmlDrawTurn = new JCheckBox("HHML Turn Draw");
    private static final JCheckBox hmlMadeTurn = new JCheckBox("HML Turn Made Hand");
    private static final JCheckBox hmlShowdownTurn = new JCheckBox("HML Turn Showdown");

    private JPanel panelCheckBoxesHHHML;
    private static final JCheckBox hmlMadeRiver = new JCheckBox("HHHML River Made Hand");
    private static final JCheckBox hmlShowdownRiver = new JCheckBox("HHHML River Showdown");

    private JPanel panelCheckBoxesSumOfFlop;
    private static final JCheckBox sumOfDrawFlop = new JCheckBox("Sum Of Flop Cards Made Hand");
    private static final JCheckBox sumOfMadeFlop = new JCheckBox("Sum Of Flop Cards Made Hand");
    private static final JCheckBox sumOfShowdownFlop = new JCheckBox("Sum of Flop Cards Showdown");

    private JPanel panelCheckBoxesSumOfTurn;
    private static final JCheckBox sumOfDrawTurn = new JCheckBox("Sum Of Turn Cards Made Hand");
    private static final JCheckBox sumOfMadeTurn = new JCheckBox("Sum Of Turn Cards Made Hand");
    private static final JCheckBox sumOfShowdownTurn = new JCheckBox("Sum of Turn Cards Showdown");

    private JPanel panelCheckBoxesSumOfRiver;
    private static final JCheckBox sumOfMadeRiver = new JCheckBox("Sum Of River Cards Made Hand");
    private static final JCheckBox sumOfShowdownRiver = new JCheckBox("Sum of River Cards Showdown");

    private JPanel panelCheckBoxesSumOfBoardCardsFlop;
    private static final JCheckBox sumOfBoardCardsDrawFlop = new JCheckBox("Flop Sum Of Board Cards Made Hand");
    private static final JCheckBox sumOfBoardCardsMadeFlop = new JCheckBox("Flop Sum Of BoardCards Made Hand");
    private static final JCheckBox sumOfBoardCardsShowdownFlop = new JCheckBox("Flop Sum of Board Cards Showdown");

    private JPanel panelCheckBoxesSumOfBoardCardsTurn;
    private static final JCheckBox sumOfBoardCardsMadeTurn = new JCheckBox("Turn Sum Of BoardCards Made Hand");
    private static final JCheckBox sumOfBoardCardsShowdownTurn = new JCheckBox("Turn Sum of Board Cards Showdown");

    private JPanel panelCheckBoxesSumOfBoardCardsRiver;
    private static final JCheckBox sumOfBoardCardsDrawRiver = new JCheckBox("River Sum Of Board Cards Made Hand");
    private static final JCheckBox sumOfBoardCardsMadeRiver = new JCheckBox("River Sum Of BoardCards Made Hand");
    private static final JCheckBox sumOfBoardCardsShowdownRiver = new JCheckBox("River Sum of Board Cards Showdown");

    private JPanel panelCheckBoxesTypeFlop;
    private static final JCheckBox typeDrawFlop = new JCheckBox("Type Draw");
    private static final JCheckBox typeMadeFlop = new JCheckBox("Type Made Hand");
    private static final JCheckBox typeShowdownFlop = new JCheckBox("Type Showdown");

    private JPanel panelCheckBoxesTypeTurn;
    private static final JCheckBox typeDrawTurn = new JCheckBox("Type Draw");
    private static final JCheckBox typeMadeTurn = new JCheckBox("Type Made Hand");
    private static final JCheckBox typeShowdownTurn = new JCheckBox("Type Showdown");

    private JPanel panelCheckBoxesTypeRiver;
    private static final JCheckBox typeMadeRiver = new JCheckBox("Type Made Hand");
    private static final JCheckBox typeShowdownRiver = new JCheckBox("Type Showdown");

    private JPanel panelCheckBoxesWetDryFlop;
    private static final JCheckBox wetDryDrawFlop = new JCheckBox("Wet Dry Draw");
    private static final JCheckBox wetDryMadeFlop = new JCheckBox("Wet Dry Made Hand");
    private static final JCheckBox wetDryShowdownFlop = new JCheckBox("Wet Dry Showdown");

    private JPanel panelCheckBoxesWetDryTurn;
    private static final JCheckBox wetDryDrawTurn = new JCheckBox("Wet Dry Draw");
    private static final JCheckBox wetDryMadeTurn = new JCheckBox("Wet Dry Made Hand");
    private static final JCheckBox wetDryShowdownTurn = new JCheckBox("Wet Dry Showdown");

    private JPanel panelCheckBoxesWetDryRiver;
    private static final JCheckBox wetDryMadeRiver = new JCheckBox("Wet Dry Made Hand");
    private static final JCheckBox wetDryShowdownRiver = new JCheckBox("Wet Dry Showdown");

    private JPanel panelpanelCheckBoxesIndexFlop;
    private static final JCheckBox indexesDrawFlop = new JCheckBox("Flop Indexes Draw");
    private static final JCheckBox indexesMadeFlop = new JCheckBox("Flop Indexes Made Hand");
    private static final JCheckBox indexesShowdownFlop = new JCheckBox("Flop Indexes Showdown");

    private JPanel panelCheckBoxesIndexTurn;
    private static final JCheckBox indexesDrawTurn = new JCheckBox("Turn Indexes Draw");
    private static final JCheckBox indexesMadeTurn = new JCheckBox("Turn Indexes Made Hand");
    private static final JCheckBox indexesShowdownTurn = new JCheckBox("Turn Indexes Showdown");

    private JPanel panelCheckBoxesIndexRiver;
    private static final JCheckBox indexesMadeRiver = new JCheckBox("River Indexes Made Hand");
    private static final JCheckBox indexesShowdownRiver = new JCheckBox("River Indexes Showdown");

    private JPanel panelCheckBoxesSumOfHoleCardsFlop;
    private static final JCheckBox sumOfHoleCatdsDrawFlop = new JCheckBox("Flop Indexes Draw");
    private static final JCheckBox sumOfHoleCardsMadeFlop = new JCheckBox("Flop Indexes Made Hand");
    private static final JCheckBox sumOfHoleCardsShowdownFlop = new JCheckBox("Flop Indexes Showdown");

    private JPanel panelCheckBoxesSumOfHoleCardsTurn;
    private static final JCheckBox sumOfHoleCatdsDrawTurn = new JCheckBox("Turn Indexes Draw");
    private static final JCheckBox sumOfHoleCardsMadeTurn = new JCheckBox("Turn Indexes Made Hand");
    private static final JCheckBox sumOfHoleCardsShowdownTurn = new JCheckBox("Turn Indexes Showdown");

    private JPanel panelCheckBoxesSumOfHoleCardsRiver;
    private static final JCheckBox sumOfHoleCardsMadeRiver = new JCheckBox("River Indexes Made Hand");
    private static final JCheckBox sumOfHoleCardsShowdownRiver = new JCheckBox("River Indexes Showdown");

    private JPanel panelCheckBoxesTypeHoleCardsFlop;
    private static final JCheckBox typeHoleCatdsDrawFlop = new JCheckBox("Hole Cards Type Draw");
    private static final JCheckBox typeHoleCardsMadeFlop = new JCheckBox("Hole Cards Type Made Hand");
    private static final JCheckBox typeHoleCardsShowdownFlop = new JCheckBox("Hole Cards Type Showdown");

    private JPanel panelCheckBoxesTypeHoleCardsTurn;
    private static final JCheckBox typeHoleCatdsDrawTurn = new JCheckBox("Hole Cards Type Draw");
    private static final JCheckBox typeHoleCardsMadeTurn = new JCheckBox("Hole Cards Type Made Hand");
    private static final JCheckBox typeHoleCardsShowdownTurn = new JCheckBox("Hole Cards Type Showdown");

    private JPanel panelCheckBoxesTypeHoleCardsRiver;
    private static final JCheckBox typeHoleCardsMadeRiver = new JCheckBox("Hole Cards Type Made Hand");
    private static final JCheckBox typeHoleCardsShowdownRiver = new JCheckBox("Hole Cards Type Showdown");

    private JPanel panelCheckBoxesTypeOf1755Flop;
    private static final JCheckBox typeOf1755DrawFlop = new JCheckBox("HML Flop Draw");
    private static final JCheckBox typeOf1755MadeFlop = new JCheckBox("HML Flop Made Hand");
    private static final JCheckBox typeOf1755ShowdownFlop = new JCheckBox("HML Flop Showdown");

    private JPanel panelCheckBoxesTypeOf1755Turn;
    private static final JCheckBox typeOf1755DrawTurn = new JCheckBox("HML Turn Draw");
    private static final JCheckBox typeOf1755MadeTurn = new JCheckBox("HML Turn Made Hand");
    private static final JCheckBox typeOf1755ShowdownTurn = new JCheckBox("HML Turn Showdown");

    private JPanel panelCheckBoxesTypesOf1755River;
    private static final JCheckBox typesOf1755MadeRiver = new JCheckBox("HML River Made Hand");
    private static final JCheckBox typesOf1755ShowdownRiver = new JCheckBox("HML River Showdown");

    private static final JButton run = new JButton("Run");

    private static final JButton help = new JButton("Help");

    private static final JRadioButton hundred = new JRadioButton("100");

    private static final JRadioButton thousand = new JRadioButton("1,000");

    private static final JRadioButton tenThousand = new JRadioButton("10,000");

    private static final JRadioButton hundredThousand = new JRadioButton("100,000");

    private static final JRadioButton million = new JRadioButton("1,000,000");

    private static final JRadioButton tenMillion = new JRadioButton("10,000,000");

    private static final JRadioButton hundredMillion = new JRadioButton("100,000,000");

    private static final Font fs = new Font("Dialog", Font.PLAIN, 12);
    private static final Font fc = new Font("Dialog", Font.PLAIN, 12);

    /*- **************************************************************************** 
    * Main
    *******************************************************************************/
    public static void main(String[] args) {
        final var frame = new GUIAnalyzeMany();
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
        mainPanel.setPreferredSize(new Dimension(700, 600));

        panelRadioHands = new JPanel(new GridLayout(1, 5));
        panelRadioHands.setBounds(100, 10, 450, 50);
        final var handsBox = Box.createHorizontalBox();
        final var handsGroup = new ButtonGroup();
        Border radioHandsBorder = BorderFactory.createTitledBorder("Number of Hnnds to Play");
        panelRadioHands.setBorder(radioHandsBorder);
        handsBox.add(panelRadioHands);
        mainPanel.add(panelRadioHands);

        panelCheckBoxes = new JPanel(new GridLayout(6, 1));
        panelCheckBoxes.setBounds(100, 100, 450, 400);
        mainPanel.add(panelCheckBoxes);

        panelControl = new JPanel(new GridLayout(1, 2));
        panelControl.setBounds(100, 500, 450, 50);
        mainPanel.add(panelControl);

        panelCheckBoxesHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHML.setBounds(300, 380, 450, 20);
        Border hmlFlopBorder = BorderFactory.createTitledBorder("Flop nHML Reports");
        panelCheckBoxesHML.setBorder(hmlFlopBorder);
        panelCheckBoxes.add(panelCheckBoxesHML);

        panelCheckBoxesHHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHHML.setBounds(300, 380, 450, 20);
        Border hmlTurnBorder = BorderFactory.createTitledBorder("Turn HML Reports");
        panelCheckBoxesHHML.setBorder(hmlTurnBorder);
        panelCheckBoxes.add(panelCheckBoxesHHML);

        panelCheckBoxesHHHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHHHML.setBounds(300, 380, 450, 40);
        Border hmlRiverBorder = BorderFactory.createTitledBorder("River HML Reports");
        panelCheckBoxesHHHML.setBorder(hmlRiverBorder);
        panelCheckBoxes.add(panelCheckBoxesHHHML);

        panelCheckBoxesSumOfFlop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesSumOfFlop.setBounds(300, 380, 450, 20);
        Border sumOfBorderFlop = BorderFactory.createTitledBorder("Sum of Hole Card Values Reports");
        panelCheckBoxesSumOfFlop.setBorder(sumOfBorderFlop);
        panelCheckBoxes.add(panelCheckBoxesSumOfFlop);

        panelCheckBoxesTypeFlop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesTypeFlop.setBounds(300, 380, 450, 20);
        Border typFlopBorderFlop = BorderFactory.createTitledBorder("Type of Flop Reports");
        panelCheckBoxesTypeFlop.setBorder(typFlopBorderFlop);
        panelCheckBoxes.add(panelCheckBoxesTypeFlop);

        panelCheckBoxesWetDryFlop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesWetDryFlop.setBounds(300, 380, 450, 20);
        Border wetDryBorder = BorderFactory.createTitledBorder("Wet Dry Reports");
        panelCheckBoxesWetDryFlop.setBorder(wetDryBorder);
        panelCheckBoxes.add(panelCheckBoxesWetDryFlop);

        panelCheckBoxesSumOfHoleCardsFlop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesSumOfHoleCardsFlop.setBounds(300, 380, 450, 20);
        Border sumOfHoleCardsFlopBorder = BorderFactory.createTitledBorder("Wet Dry Reports");
        panelCheckBoxesSumOfHoleCardsFlop.setBorder(sumOfHoleCardsFlopBorder);
        panelCheckBoxes.add(panelCheckBoxesSumOfHoleCardsFlop);

        panelCheckBoxesSumOfBoardCardsFlop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesSumOfBoardCardsFlop.setBounds(300, 380, 450, 20);
        Border SumOfBoardCardsBorder = BorderFactory.createTitledBorder("Wet Dry Reports");
        panelCheckBoxesSumOfBoardCardsFlop.setBorder(wetDryBorder);
        panelCheckBoxes.add(panelCheckBoxesSumOfBoardCardsFlop);

        panelCheckBoxesTypeOf1755Flop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesTypeOf1755Flop.setBounds(300, 380, 450, 20);
        Border typeOf17ffBorderFlop = BorderFactory.createTitledBorder("Wet Dry Reports");
        panelCheckBoxesTypeOf1755Flop.setBorder(typeOf17ffBorderFlop);
        panelCheckBoxes.add(panelCheckBoxesTypeOf1755Flop);

        panelCheckBoxesFlopIndexFlop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesFlopIndexFlop.setBounds(300, 380, 450, 20);
        Border flopIndexBorderFlop = BorderFactory.createTitledBorder("Wet Dry Reports");
        panelCheckBoxesFlopINdexFlop.setBorder(flopIndexBorderFlop);
        panelCheckBoxes.add(panelCheckBoxesFlopINdexFlop);

        hundred.setFont(fs);
        hundred.addActionListener(this);
        panelRadioHands.add(hundred);
        handsGroup.add(hundred);

        thousand.setFont(fs);
        thousand.addActionListener(this);
        panelRadioHands.add(thousand);
        handsGroup.add(thousand);

        tenThousand.setFont(fs);
        tenThousand.addActionListener(this);
        tenThousand.setSelected(true);
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

        hmlDrawFlop.setFont(fs);
        hmlDrawFlop.setPreferredSize(new Dimension(20, 20));
        hmlDrawFlop.addActionListener(this);
        hmlDrawFlop.setSelected(false);
        panelCheckBoxesHML.add(hmlDrawFlop);

        hmlMadeFlop.setFont(fs);
        hmlMadeFlop.setPreferredSize(new Dimension(20, 20));
        hmlMadeFlop.addActionListener(this);
        hmlMadeFlop.setSelected(false);
        panelCheckBoxesHML.add(hmlMadeFlop);

        hmlShowdownFlop.setFont(fs);
        hmlShowdownFlop.setPreferredSize(new Dimension(20, 20));
        hmlShowdownFlop.addActionListener(this);
        hmlShowdownFlop.setSelected(false);
        panelCheckBoxesHML.add(hmlShowdownFlop);

        hmlDrawTurn.setFont(fs);
        hmlDrawTurn.setPreferredSize(new Dimension(20, 20));
        hmlDrawTurn.addActionListener(this);
        hmlDrawTurn.setSelected(false);
        panelCheckBoxesHHML.add(hmlDrawTurn);

        hmlMadeTurn.setFont(fs);
        hmlMadeTurn.setPreferredSize(new Dimension(20, 20));
        hmlMadeTurn.addActionListener(this);
        hmlMadeTurn.setSelected(false);
        panelCheckBoxesHHML.add(hmlMadeTurn);

        hmlShowdownTurn.setFont(fs);
        hmlShowdownTurn.setPreferredSize(new Dimension(20, 20));
        hmlShowdownTurn.addActionListener(this);
        hmlShowdownTurn.setSelected(false);
        panelCheckBoxesHHML.add(hmlShowdownTurn);

        hmlMadeRiver.setFont(fs);
        hmlMadeRiver.setPreferredSize(new Dimension(20, 20));
        hmlMadeRiver.addActionListener(this);
        hmlMadeRiver.setSelected(false);
        panelCheckBoxesHHHML.add(hmlMadeRiver);

        hmlShowdownRiver.setFont(fs);
        hmlShowdownRiver.setPreferredSize(new Dimension(20, 20));
        hmlShowdownRiver.addActionListener(this);
        hmlShowdownRiver.setSelected(false);
        panelCheckBoxesHHHML.add(hmlShowdownRiver);

        typeDrawFlop.setFont(fs);
        typeDrawFlop.setPreferredSize(new Dimension(20, 20));
        typeDrawFlop.addActionListener(this);
        typeDrawFlop.setSelected(false);
        panelCheckBoxesType.add(typeDrawFlop);

        typeMadeFlop.setFont(fs);
        typeMadeFlop.setPreferredSize(new Dimension(20, 20));
        typeMadeFlop.addActionListener(this);
        typeMadeFlop.setSelected(false);
        panelCheckBoxesType.add(typeMadeFlop);

        typeShowdownFlop.setFont(fs);
        typeShowdownFlop.setPreferredSize(new Dimension(20, 20));
        typeShowdownFlop.addActionListener(this);
        typeShowdownFlop.setSelected(false);
        panelCheckBoxesType.add(typeShowdownFlop);

        sumOfDrawFlop.setFont(fs);
        sumOfDrawFlop.setPreferredSize(new Dimension(20, 20));
        sumOfDrawFlop.addActionListener(this);
        sumOfDrawFlop.setSelected(false);
        panelCheckBoxesSumOfFlop.add(sumOfDrawFlop);

        sumOfMadeFlop.setFont(fs);
        sumOfMadeFlop.setPreferredSize(new Dimension(20, 20));
        sumOfMadeFlop.addActionListener(this);
        sumOfMadeFlop.setSelected(false);
        panelCheckBoxesSumOfFlop.add(sumOfMadeFlop);

        sumOfShowdownFlop.setFont(fs);
        sumOfShowdownFlop.setPreferredSize(new Dimension(20, 20));
        sumOfShowdownFlop.addActionListener(this);
        sumOfShowdownFlop.setSelected(false);
        panelCheckBoxesSumOfFlop.add(sumOfShowdownFlop);

        wetDryDrawFlop.setFont(fs);
        wetDryDrawFlop.setPreferredSize(new Dimension(20, 20));
        wetDryDrawFlop.addActionListener(this);
        wetDryMadeFlop.setFont(fs);
        wetDryMadeFlop.setPreferredSize(new Dimension(20, 20));
        wetDryMadeFlop.addActionListener(this);
        wetDryMadeFlop.setSelected(false);
        panelCheckBoxesWetDry.add(wetDryMadeFlop);

        wetDryShowdownFlop.setFont(fs);
        wetDryShowdownFlop.setPreferredSize(new Dimension(20, 20));
        wetDryShowdownFlop.addActionListener(this);
        wetDryShowdownFlop.setSelected(false);
        panelCheckBoxesWetDry.add(wetDryShowdownFlop);

        panelCheckBoxes.add(panelCheckBoxesHML);
        panelCheckBoxes.add(panelCheckBoxesHHML);
        panelCheckBoxes.add(panelCheckBoxesHHHML);
        panelCheckBoxes.add(panelCheckBoxesSumOfFlop);
        panelCheckBoxes.add(panelCheckBoxesType);
        panelCheckBoxes.add(panelCheckBoxesWetDry);

        // mainPanel.add(panelCheckBoxes);

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

        // Set some properties of the frame
        this.setContentPane(mainPanel);
        this.setTitle("Evaluate Hands");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
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
        if (hundred.isSelected()) {
            number = 100;
        } else if (thousand.isSelected()) {
            number = 1000;
        } else if (tenThousand.isSelected()) {
            number = 10000;
        } else if (hundredThousand.isSelected()) {
            number = 100000;
        } else if (million.isSelected()) {
            number = 1000000;
        } else if (tenMillion.isSelected()) {
            number = 10000000;
        } else if (hundredMillion.isSelected()) {
            number = 100000000;
        }

        EvalData.fullSimulation = true;
        EvalData.manyHands = true;
        EvalData.rotatePlayers = true;
        EvalData.hh = false;
        EvalData.detailedAnalysis = true;
        EvalData.fullSimulationNum = number;
        // Simulate the play of xxx hands, Preflop, Flop, Turn, and River
        Evaluate.doSimulationDataCollection(number);
        if (hmlMadeFlop.isSelected()) {
            ReportHML.reportMadeFlop(10, 100);
        }
        if (hmlDrawFlop.isSelected()) {
            ReportHML.reportDrawFlop(100, 100);
        }
        if (hmlShowdownFlop.isSelected()) {
            ReportHML.reportShowdownFlop(200, 100);
        }

        // Simulate the play of xxx hands, Preflop, Flop, Turn, and River
        Evaluate.doSimulationDataCollection(number);
        if (hmlMadeTurn.isSelected()) {
            ReportHML.reportMadeTurn(10, 200);
        }
        if (hmlDrawTurn.isSelected()) {
            ReportHML.reportDrawTurn(100, 200);
        }
        if (hmlShowdownTurn.isSelected()) {
            ReportHML.reportShowdownTurn(200, 200);
        }
        if (hmlMadeRiver.isSelected()) {
            ReportHML.reportMadeRiver(10, 300);
        }
        if (hmlShowdownRiver.isSelected()) {
            ReportHML.reportShowdownRiver(100, 300);
        }

    }
}
