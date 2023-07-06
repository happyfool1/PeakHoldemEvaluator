//package peakholdemevaluator;

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
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class GUIAnalyzeIndexArrays extends JFrame implements ActionListener, Constants {

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

    private static JPanel panelRadioHands = new JPanel();

    private static JPanel mainPanel;

    private static JPanel panelCheckBoxes;;

    private static JPanel panelControl;

    private static JPanel panelStatus;

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

    private JPanel panelCheckBoxesTypeOf1755Flop;
    private static final JCheckBox typeOf1755DrawFlop = new JCheckBox("HML Flop Draw");
    private static final JCheckBox typeOf1755MadeFlop = new JCheckBox("HML Flop Made Hand");
    private static final JCheckBox typeOf1755ShowdownFlop = new JCheckBox("HML Flop Showdown");

    private JPanel panelCheckBoxesTypeOf1755Turn;
    private static final JCheckBox typeOf1755DrawTurn = new JCheckBox("HML Turn Draw");
    private static final JCheckBox typeOf1755MadeTurn = new JCheckBox("HML Turn Made Hand");
    private static final JCheckBox typeOf1755ShowdownTurn = new JCheckBox("HML Turn Showdown");

    private JPanel panelCheckBoxesTypeOf1755River;
    private static final JCheckBox typeOf1755MadeRiver = new JCheckBox("HML River Made Hand");
    private static final JCheckBox typeOf1755ShowdownRiver = new JCheckBox("HML River Showdown");

    private JPanel panelCheckBoxesSCBPFlop;
    private static final JCheckBox SCBPDrawFlop = new JCheckBox("SCBP Flop Draw");
    private static final JCheckBox SCBPMadeFlop = new JCheckBox("SCBP Flop Made Hand");
    private static final JCheckBox SCBPShowdownFlop = new JCheckBox("SCBP Flop Showdown");

    private JPanel panelCheckBoxesSCBPTurn;
    private static final JCheckBox SCBP5DrawTurn = new JCheckBox("SCBP Turn Draw");
    private static final JCheckBox SCBP5MadeTurn = new JCheckBox("SCBP Turn Made Hand");
    private static final JCheckBox SCBP5ShowdownTurn = new JCheckBox("SCBP Turn Showdown");

    private JPanel panelCheckBoxesSCBPRiver;
    private static final JCheckBox SCBP5MadeRiver = new JCheckBox("SCBP River Made Hand");
    private static final JCheckBox SCBP5ShowdownRiver = new JCheckBox("SCBP River Showdown");

    private static final JButton run = new JButton("Run");

    private static final JButton exit = new JButton("Exit");

    private static final JButton help = new JButton("Help");

    private static final JRadioButton hundred = new JRadioButton("100");

    private static final JRadioButton thousand = new JRadioButton("1,000");

    private static final JRadioButton tenThousand = new JRadioButton("10,000");

    private static final JRadioButton hundredThousand = new JRadioButton("100,000");

    private static final JRadioButton million = new JRadioButton("1,000,000");

    private static final JRadioButton tenMillion = new JRadioButton("10,000,000");

    private static final JRadioButton hundredMillion = new JRadioButton("100,000,000");

    private static JTextField status = new JTextField(10);
    private static boolean quit = false;

    private static final Font fs = new Font("Dialog", Font.PLAIN, 12);
    private static final Font fc = new Font("Dialog", Font.PLAIN, 12);

    /*- **************************************************************************** 
    * Main
    *******************************************************************************/
    public static void main(String[] args) {
        final var frame = new GUIAnalyzeIndexArrays();
        frame.myFrame();
    }

    /*- **************************************************************************** 
    * Frame
    *******************************************************************************/
    public void myFrame() {
        JButton run = new JButton("Run");

        /// Set up the main panel
        mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(1100, 700));
        mainPanel.setBackground(CYAN);

        panelRadioHands = new JPanel(new GridLayout(1, 6));
        panelRadioHands.setBounds(100, 10, 900, 50);
        panelRadioHands.setBackground(TAN);
        final var handsBox = Box.createHorizontalBox();
        final var handsGroup = new ButtonGroup();
        Border radioHandsBorder = BorderFactory.createTitledBorder("Number of Hnnds to Play");
        panelRadioHands.setBorder(radioHandsBorder);
        handsBox.add(panelRadioHands);
        mainPanel.add(panelRadioHands);

        panelCheckBoxes = new JPanel(new GridLayout(11, 1));
        panelCheckBoxes.setBounds(100, 100, 900, 400);
        mainPanel.add(panelCheckBoxes);

        panelControl = new JPanel(new GridLayout(1, 3));
        panelControl.setBounds(100, 500, 450, 50);
        mainPanel.add(panelControl);

        panelStatus = new JPanel(new GridLayout(1, 3));
        panelStatus.setBounds(100, 550, 450, 50);
        mainPanel.add(panelStatus);

        panelCheckBoxesHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHML.setBounds(300, 380, 450, 20);
        Border hmlFlopBorder = BorderFactory.createTitledBorder("Flop nHML Reports");
        panelCheckBoxesHML.setBorder(hmlFlopBorder);

        panelCheckBoxesHHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHHML.setBounds(300, 380, 450, 20);
        Border hmlTurnBorder = BorderFactory.createTitledBorder("Turn HML Reports");
        panelCheckBoxesHHML.setBorder(hmlTurnBorder);

        panelCheckBoxesHHHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHHHML.setBounds(300, 380, 450, 40);
        Border hmlRiverBorder = BorderFactory.createTitledBorder("River HML Reports");
        panelCheckBoxesHHHML.setBorder(hmlRiverBorder);

        panelCheckBoxesWetDryFlop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesWetDryFlop.setBounds(300, 380, 450, 20);
        Border wetDryBorderFlop = BorderFactory.createTitledBorder("Flop Wet Dry Reports");
        panelCheckBoxesWetDryFlop.setBorder(wetDryBorderFlop);

        panelCheckBoxesTypeOf1755Flop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesTypeOf1755Flop.setBounds(300, 380, 450, 20);
        Border typeOf17ffBorderFlop = BorderFactory.createTitledBorder("Flop Type of 1755 Reports");
        panelCheckBoxesTypeOf1755Flop.setBorder(typeOf17ffBorderFlop);

        panelCheckBoxesSCBPFlop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesSCBPFlop.setBounds(300, 380, 450, 20);
        Border SCBPBorderFlop = BorderFactory.createTitledBorder("Flop Type of SCBP Reports");
        panelCheckBoxesSCBPFlop.setBorder(SCBPBorderFlop);

        hundred.setFont(fs);
        hundred.addActionListener(this);
        hundred.setBackground(TAN);
        panelRadioHands.add(hundred);
        handsGroup.add(hundred);

        thousand.setFont(fs);
        thousand.addActionListener(this);
        thousand.setBackground(TAN);
        panelRadioHands.add(thousand);
        thousand.setSelected(true);
        handsGroup.add(thousand);

        tenThousand.setFont(fs);
        tenThousand.addActionListener(this);
        tenThousand.setBackground(TAN);
        panelRadioHands.add(tenThousand);
        handsGroup.add(tenThousand);

        hundredThousand.setFont(fs);
        hundredThousand.addActionListener(this);
        hundredThousand.setBackground(TAN);
        panelRadioHands.add(hundredThousand);
        handsGroup.add(hundredThousand);

        million.setFont(fs);
        million.addActionListener(this);
        million.setBackground(TAN);
        panelRadioHands.add(million);
        handsGroup.add(million);

        tenMillion.setFont(fs);
        tenMillion.addActionListener(this);
        tenMillion.setBackground(TAN);
        panelRadioHands.add(tenMillion);
        handsGroup.add(tenMillion);

        hundredMillion.setFont(fs);
        hundredMillion.addActionListener(this);
        hundredMillion.setBackground(TAN);
        panelRadioHands.add(hundredMillion);
        handsGroup.add(hundredMillion);

        hmlDrawFlop.setFont(fs);
        hmlDrawFlop.setPreferredSize(new Dimension(20, 20));
        hmlDrawFlop.addActionListener(this);
        hmlDrawFlop.setSelected(false);
        hmlDrawFlop.setBackground(SILVER);
        panelCheckBoxesHML.add(hmlDrawFlop);
        panelCheckBoxesHML.setBackground(SILVER);

        hmlMadeFlop.setFont(fs);
        hmlMadeFlop.setPreferredSize(new Dimension(20, 20));
        hmlMadeFlop.addActionListener(this);
        hmlMadeFlop.setSelected(false);
        hmlMadeFlop.setBackground(SILVER);
        panelCheckBoxesHML.add(hmlMadeFlop);

        hmlShowdownFlop.setFont(fs);
        hmlShowdownFlop.setPreferredSize(new Dimension(20, 20));
        hmlShowdownFlop.addActionListener(this);
        hmlShowdownFlop.setSelected(false);
        hmlShowdownFlop.setBackground(SILVER);
        panelCheckBoxesHML.add(hmlShowdownFlop);

        hmlDrawTurn.setFont(fs);
        hmlDrawTurn.setPreferredSize(new Dimension(20, 20));
        hmlDrawTurn.addActionListener(this);
        hmlDrawTurn.setSelected(false);
        hmlDrawTurn.setBackground(SILVER);
        panelCheckBoxesHHML.add(hmlDrawTurn);

        hmlMadeTurn.setFont(fs);
        hmlMadeTurn.setPreferredSize(new Dimension(20, 20));
        hmlMadeTurn.addActionListener(this);
        hmlMadeTurn.setBackground(SILVER);
        hmlMadeTurn.setSelected(false);

        panelCheckBoxesHHML.add(hmlMadeTurn);

        hmlShowdownTurn.setFont(fs);
        hmlShowdownTurn.setPreferredSize(new Dimension(20, 20));
        hmlShowdownTurn.addActionListener(this);
        hmlShowdownTurn.setSelected(false);
        hmlShowdownTurn.setBackground(SILVER);
        panelCheckBoxesHHML.add(hmlShowdownTurn);
        panelCheckBoxesHHML.setBackground(SILVER);

        hmlMadeRiver.setFont(fs);
        hmlMadeRiver.setPreferredSize(new Dimension(20, 20));
        hmlMadeRiver.addActionListener(this);
        hmlMadeRiver.setSelected(false);
        hmlMadeRiver.setBackground(SILVER);
        panelCheckBoxesHHHML.add(hmlMadeRiver);

        hmlShowdownRiver.setFont(fs);
        hmlShowdownRiver.setPreferredSize(new Dimension(20, 20));
        hmlShowdownRiver.addActionListener(this);
        hmlShowdownRiver.setSelected(false);
        hmlShowdownRiver.setBackground(SILVER);
        panelCheckBoxesHHHML.add(hmlShowdownRiver);
        panelCheckBoxesHHHML.setBackground(SILVER);

        wetDryDrawFlop.setFont(fs);
        wetDryDrawFlop.setPreferredSize(new Dimension(20, 20));
        wetDryDrawFlop.addActionListener(this);
        wetDryDrawFlop.setSelected(false);
        wetDryDrawFlop.setBackground(BEIGE);
        panelCheckBoxesWetDryFlop.add(wetDryDrawFlop);

        wetDryMadeFlop.setFont(fs);
        wetDryMadeFlop.setPreferredSize(new Dimension(20, 20));
        wetDryMadeFlop.addActionListener(this);
        wetDryMadeFlop.setSelected(false);
        wetDryMadeFlop.setBackground(BEIGE);
        panelCheckBoxesWetDryFlop.add(wetDryMadeFlop);

        wetDryShowdownFlop.setFont(fs);
        wetDryShowdownFlop.setPreferredSize(new Dimension(20, 20));
        wetDryShowdownFlop.addActionListener(this);
        wetDryShowdownFlop.setSelected(false);
        wetDryShowdownFlop.setBackground(BEIGE);
        panelCheckBoxesWetDryFlop.add(wetDryShowdownFlop);
        panelCheckBoxesWetDryFlop.setBackground(BEIGE);

        typeOf1755DrawFlop.setFont(fs);
        typeOf1755DrawFlop.setPreferredSize(new Dimension(20, 20));
        typeOf1755DrawFlop.addActionListener(this);
        typeOf1755DrawFlop.setSelected(false);
        typeOf1755DrawFlop.setBackground(LIGHT_SKY_BLUE);
        panelCheckBoxesTypeOf1755Flop.add(typeOf1755DrawFlop);

        typeOf1755MadeFlop.setFont(fs);
        typeOf1755MadeFlop.setPreferredSize(new Dimension(20, 20));
        typeOf1755MadeFlop.addActionListener(this);
        typeOf1755MadeFlop.setSelected(false);
        typeOf1755MadeFlop.setBackground(LIGHT_SKY_BLUE);
        panelCheckBoxesTypeOf1755Flop.add(typeOf1755MadeFlop);

        typeOf1755ShowdownFlop.setFont(fs);
        typeOf1755ShowdownFlop.setPreferredSize(new Dimension(20, 20));
        typeOf1755ShowdownFlop.addActionListener(this);
        typeOf1755ShowdownFlop.setSelected(false);
        typeOf1755ShowdownFlop.setBackground(LIGHT_SKY_BLUE);
        panelCheckBoxesTypeOf1755Flop.add(typeOf1755ShowdownFlop);
        panelCheckBoxesTypeOf1755Flop.setBackground(LIGHT_SKY_BLUE);

     
        SCBPDrawFlop.setFont(fs);
        SCBPDrawFlop.setPreferredSize(new Dimension(20, 20));
        SCBPDrawFlop.addActionListener(this);
        SCBPDrawFlop.setSelected(false);
        SCBPDrawFlop.setBackground(TAN);
        panelCheckBoxesSCBPFlop.add(SCBPDrawFlop);

        SCBPMadeFlop.setFont(fs);
        SCBPMadeFlop.setPreferredSize(new Dimension(20, 20));
        SCBPMadeFlop.addActionListener(this);
        SCBPMadeFlop.setSelected(false);
        SCBPMadeFlop.setBackground(TAN);
        panelCheckBoxesSCBPFlop.add(SCBPMadeFlop);

        SCBPShowdownFlop.setFont(fs);
        SCBPShowdownFlop.setPreferredSize(new Dimension(20, 20));
        SCBPShowdownFlop.addActionListener(this);
        SCBPShowdownFlop.setSelected(false);
        SCBPShowdownFlop.setBackground(TAN);
        panelCheckBoxesSCBPFlop.add(SCBPShowdownFlop);
        panelCheckBoxesSCBPFlop.setBackground(TAN);


        panelCheckBoxes.add(panelCheckBoxesHML);
        panelCheckBoxes.add(panelCheckBoxesHHML);
        panelCheckBoxes.add(panelCheckBoxesWetDryFlop);
        panelCheckBoxes.add(panelCheckBoxesTypeOf1755Flop);
        panelCheckBoxes.add(panelCheckBoxesSCBPFlop);

        run.setFont(fc);
        run.setPreferredSize(new Dimension(15, 15));
        run.addActionListener(this);
        run.setBackground(CONTROL);
        panelControl.add(run);

        exit.setFont(fc);
        exit.setPreferredSize(new Dimension(15, 15));
        exit.addActionListener(this);
        exit.setBackground(CONTROL);
        panelControl.add(exit);

        help.setFont(fc);
        help.setPreferredSize(new Dimension(15, 15));
        help.addActionListener(this);
        help.setBackground(CONTROL);
        panelControl.add(help);

        status.setFont(fc);
        status.setPreferredSize(new Dimension(15, 15));
        status.setBackground(TAN);
        // status.setVisible(false);
        // panelStatus.setVisible(false);
        panelStatus.add(status);

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
                quit = true;
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
            status.setVisible(true);
            panelStatus.setVisible(true);
        } else if (tenMillion.isSelected()) {
            number = 10000000;
            status.setVisible(true);
            panelStatus.setVisible(true);
        } else if (hundredMillion.isSelected()) {
            number = 100000000;
            status.setVisible(true);
            panelStatus.setVisible(true);
            status.setBackground(RED);
        }

        EvalData.fullSimulation = true;
        EvalData.manyHands = true;
        EvalData.rotatePlayers = true;
        EvalData.hh = false;
        EvalData.detailedAnalysis = true;
        EvalData.fullSimulationNum = number;
        quit = false;

        // Simulate the play of xxx hands, Preflop, Flop, Turn, and River
        Evaluate.doSimulationDataCollection(number);

        IndexArrayDrawMadeWin hmlFlop = new IndexArrayDrawMadeWin(IndexArrays.hmlDrawFlop, IndexArrays.hmlMadeFlop,
                IndexArrays.hmlShowdownFlop,
                HML_FLOP_ST, DRAW_ARRAY_ST,
                MADE_ARRAY_ST);

        IndexArrayDrawMadeWin hmlTurn = new IndexArrayDrawMadeWin(IndexArrays.hmlDrawTurn, IndexArrays.hmlMadeTurn,
                IndexArrays.hmlShowdownTurn,
                HML_TURN_ST, DRAW_ARRAY_ST,
                MADE_ARRAY_ST);

        IndexArrayDrawMadeWin wetDryFlop = new IndexArrayDrawMadeWin(IndexArrays.wetDryDrawFlop,
                IndexArrays.wetDryMadeFlop,
                IndexArrays.wetDryShowdownFlop,
                WET_DRY_ST, DRAW_ARRAY_ST,
                MADE_ARRAY_ST);

        IndexArrayDrawMadeWin typeOf1755Flop = new IndexArrayDrawMadeWin(IndexArrays.typeOf1755DrawFlop,
                IndexArrays.typeOf1755MadeFlop,
                IndexArrays.typeOf1755ShowdownFlop,
                TYPE_OF_1755_ST, DRAW_ARRAY_ST,
                MADE_ARRAY_ST);

        IndexArrayDrawMadeWin SCBPFlop = new IndexArrayDrawMadeWin(IndexArrays.SCBPDrawFlop,
                IndexArrays.SCBPMadeFlop,
                IndexArrays.SCBPShowdownFlop,
                SCBP_ST, DRAW_ARRAY_ST,
                MADE_ARRAY_ST);

        IndexArrayReport reportFlop = new IndexArrayReport();
        if (hmlDrawFlop.isSelected()) {
            reportFlop.reportDraw(10, 100, "Flop Draws with HML Index", hmlFlop);
        }
        if (hmlMadeFlop.isSelected()) {
            reportFlop.reportMade(10, 400, "Flop  Made hands with HML Index", hmlFlop);

        }
        if (hmlShowdownFlop.isSelected()) {
            reportFlop.reportShowdown(10, 600, "Flop  Showdown hands with HML Index", hmlFlop);
        }

        IndexArrayReport reportTurn = new IndexArrayReport();
        if (hmlDrawTurn.isSelected()) {
            reportTurn.reportDraw(10, 100, "Turn Draws with HML Index", hmlTurn);
        }
        if (hmlMadeTurn.isSelected()) {
            reportTurn.reportMade(10, 400, "Turn  Made hands with HML Index", hmlTurn);
        }
        if (hmlShowdownTurn.isSelected()) {
            reportTurn.reportShowdown(10, 600, "Turn  Showdown hands with HML Index", hmlTurn);
        }

        IndexArrayReport reportFlopWetDry = new IndexArrayReport();
        if (wetDryDrawFlop.isSelected()) {
            reportFlopWetDry.reportDraw(10, 100, "Flop Draws Wet Dry Index", wetDryFlop);
        }
        if (wetDryMadeFlop.isSelected()) {
            reportFlopWetDry.reportMade(10, 400, "Flop  Made hands Wet Dry Index", wetDryFlop);

        }
        if (wetDryShowdownFlop.isSelected()) {
            reportFlopWetDry.reportShowdown(10, 600, "Flop  Showdown hands Wet Dry Index", wetDryFlop);
        }

        IndexArrayReport reportFlopTypeOf1755 = new IndexArrayReport();
        if (typeOf1755DrawFlop.isSelected()) {
            reportFlopTypeOf1755.reportDraw(10, 100, "Flop Draws type of 1755 Index", typeOf1755Flop);
        }
        if (typeOf1755MadeFlop.isSelected()) {
            reportFlopTypeOf1755.reportMade(10, 400, "Flop  Made type of 1755Dry Index", typeOf1755Flop);

        }
        if (typeOf1755ShowdownFlop.isSelected()) {
            reportFlopTypeOf1755.reportShowdown(10, 600, "Flop  Showdown hands type of 1755 Index", typeOf1755Flop);
        }

        IndexArrayReport reportFlopSCBP = new IndexArrayReport();
        if (SCBPDrawFlop.isSelected()) {
           reportFlopSCBP .reportDraw(10, 100, "Flop Draws SCPB Index", SCBPFlop);
        }
        if (SCBPMadeFlop.isSelected()) {
           reportFlopSCBP .reportMade(10, 400, "Flop  Made SCPB Index", SCBPFlop);
        }
        if (SCBPShowdownFlop.isSelected()) {
           reportFlopSCBP .reportShowdown(10, 600, "Flop  Showdown hands SCPBIndex", SCBPFlop);
        }

    }

    static int statusUpdate = 0;

    /*-  ******************************************************************************
    * For very long runs update elapsed time and check for exit selected
    *  ****************************************************************************** */
    static boolean checkExit(int played) {
        if (statusUpdate++ > 10000) {
            statusUpdate = 0;
            status.setVisible(true);
            panelStatus.setVisible(true);
            status.setText("WTF");
            status.repaint();
            panelStatus.repaint();
            System.out.println("WTF");
        }
        if (quit) {
            System.out.println("Exiting...");
            return true;
        }
        return false;
    }

}
