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

    private static final JCheckBox hmlDraw = new JCheckBox("HML Flop Draw");
    private static final JCheckBox hmlMade = new JCheckBox("HML Flop Made Hand");
    private static final JCheckBox hmlShowdown = new JCheckBox("HML Flop Showdown");

    private JPanel panelCheckBoxesHHML;
    private static final JCheckBox hhmlDraw = new JCheckBox("HHML Turn Draw");
    private static final JCheckBox hhmlMade = new JCheckBox("HML Turn Made Hand");
    private static final JCheckBox hhmlShowdown = new JCheckBox("HML Turn Showdown");

    private JPanel panelCheckBoxesHHHML;
    private static final JCheckBox hhhmlDraw = new JCheckBox("HHHML River Draw");
    private static final JCheckBox hhhmlMade = new JCheckBox("HHHML River Made Hand");
    private static final JCheckBox hhhmlShowdown = new JCheckBox("HHHML River Showdown");

    private JPanel panelCheckBoxesSumOfFlop;
    private static final JCheckBox sumOfDraw = new JCheckBox("Sum Of Flop Cards Draw");
    private static final JCheckBox sumOfMade = new JCheckBox("Sum Of Flop Cards Made Hand");
    private static final JCheckBox sumOfShowdown = new JCheckBox("Sum of Flop Cards Showdown");

    private JPanel panelCheckBoxesType;
    private static final JCheckBox typeDraw = new JCheckBox("Type Draw");
    private static final JCheckBox typeMade = new JCheckBox("Type Made Hand");
    private static final JCheckBox typeShowdown = new JCheckBox("Yype Showdown");

    private JPanel panelCheckBoxesWetDry;
    private static final JCheckBox wetDryDraw = new JCheckBox("Wet Dry Draw");
    private static final JCheckBox wetDryMade = new JCheckBox("Wet Dry Made Hand");
    private static final JCheckBox wetDryShowdown = new JCheckBox("Wet Dry Showdown");

    private JPanel panelRadioBoxesHands;

    private static final JButton run = new JButton("Run");

    private static final JButton help = new JButton("Help");

    private static final JRadioButton thousand = new JRadioButton("1,000");

    private static final JRadioButton tenThousand = new JRadioButton("10,000");

    private static final JRadioButton hundredThousand = new JRadioButton("100,000");

    private static final JRadioButton million = new JRadioButton("1,000,000");

    private static final JRadioButton tenMillion = new JRadioButton("10,000,000");

    private static final JRadioButton hundredMillion = new JRadioButton("100,000,000");

    private static final Font fs = new Font("Dialog", Font.PLAIN, 14);
    private static final Font fc = new Font("Dialog", Font.PLAIN, 14);
    /*- **************************************************************************** 
    * Option data
    *******************************************************************************/
    private static int handsToPlay = 0;

    private static boolean hmlDrawOption = false;
    private static boolean hmlMadeOption = false;
    private static boolean hmlShowdownOption = false;

    private static boolean hhmlDrawOption = false;
    private static boolean hhmlMadeOption = false;
    private static boolean hhmlShowdownOption = false;

    private static boolean hhhmlDrawOption = false;
    private static boolean hhhmlMadeOption = false;
    private static boolean hhhmlShowdownOption = false;

    private static boolean typeDrawOption = false;
    private static boolean typeMadeOption = false;
    private static boolean typeShowdownOption = false;

    private static boolean wetDryDrawOption = false;
    private static boolean wetDryMadeOption = false;
    private static boolean wetDryShowdownOption = false;

    private static boolean sumOfFlopDrawOption = false;
    private static boolean sumOfFlopMadeOption = false;
    private static boolean sumOfFlopShowdownOption = false;

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
        panelCheckBoxesHML.setBounds(300, 380, 450, 40);
        Border hmlFlopBorder = BorderFactory.createTitledBorder("Flop nHML Reports");
        panelCheckBoxesHML.setBorder(hmlFlopBorder);
        panelCheckBoxes.add(panelCheckBoxesHML);

        panelCheckBoxesHHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHHML.setBounds(300, 380, 450, 40);
        Border hmlTurnBorder = BorderFactory.createTitledBorder("Turn HML Reports");
        panelCheckBoxesHHML.setBorder(hmlTurnBorder);
        panelCheckBoxes.add(panelCheckBoxesHHML);

        panelCheckBoxesHHHML = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesHHHML.setBounds(300, 380, 450, 40);
        Border hmlRiverBorder = BorderFactory.createTitledBorder("River HML Reports");
        panelCheckBoxesHHHML.setBorder(hmlRiverBorder);
        panelCheckBoxes.add(panelCheckBoxesHHHML);

        panelCheckBoxesSumOfFlop = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesSumOfFlop.setBounds(300, 380, 450, 40);
        Border sumOfBorder = BorderFactory.createTitledBorder("Sum of Hole Card Values Reports");
        panelCheckBoxesSumOfFlop.setBorder(sumOfBorder);
        panelCheckBoxes.add(panelCheckBoxesSumOfFlop);

        panelCheckBoxesType = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesType.setBounds(300, 380, 450, 40);
        Border typeBorder = BorderFactory.createTitledBorder("Type of Flop Reports");
        panelCheckBoxesType.setBorder(typeBorder);
        panelCheckBoxes.add(panelCheckBoxesType);

        panelCheckBoxesWetDry = new JPanel(new GridLayout(1, 3));
        panelCheckBoxesWetDry.setBounds(300, 380, 450, 40);
        Border wetDryBorder = BorderFactory.createTitledBorder("Wet Dry Reports");
        panelCheckBoxesWetDry.setBorder(wetDryBorder);
        panelCheckBoxes.add(panelCheckBoxesWetDry);

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
        hhhmlDraw.setSelected(false);
        panelCheckBoxesHHHML.add(hhhmlDraw);

        hhhmlMade.setFont(fs);
        hhhmlMade.setPreferredSize(new Dimension(25, 25));
        hhhmlMade.addActionListener(this);
        hhhmlMade.setSelected(false);
        panelCheckBoxesHHHML.add(hhhmlMade);

        hhhmlShowdown.setFont(fs);
        hhhmlShowdown.setPreferredSize(new Dimension(25, 25));
        hhhmlShowdown.addActionListener(this);
        hhhmlShowdown.setSelected(false);
        panelCheckBoxesHHHML.add(hhhmlShowdown);

        typeDraw.setFont(fs);
        typeDraw.setPreferredSize(new Dimension(25, 25));
        typeDraw.addActionListener(this);
        typeDraw.setSelected(false);
        panelCheckBoxesType.add(typeDraw);

        typeMade.setFont(fs);
        typeMade.setPreferredSize(new Dimension(25, 25));
        typeMade.addActionListener(this);
        typeMade.setSelected(false);
        panelCheckBoxesType.add(typeMade);

        typeShowdown.setFont(fs);
        typeShowdown.setPreferredSize(new Dimension(25, 25));
        typeShowdown.addActionListener(this);
        typeShowdown.setSelected(false);
        panelCheckBoxesType.add(typeShowdown);

        sumOfDraw.setFont(fs);
        sumOfDraw.setPreferredSize(new Dimension(25, 25));
        sumOfDraw.addActionListener(this);
        sumOfDraw.setSelected(false);
        panelCheckBoxesSumOfFlop.add(sumOfDraw);

        sumOfMade.setFont(fs);
        sumOfMade.setPreferredSize(new Dimension(25, 25));
        sumOfMade.addActionListener(this);
        sumOfMade.setSelected(false);
        panelCheckBoxesSumOfFlop.add(sumOfMade);

        sumOfShowdown.setFont(fs);
        sumOfShowdown.setPreferredSize(new Dimension(25, 25));
        sumOfShowdown.addActionListener(this);
        sumOfShowdown.setSelected(false);
        panelCheckBoxesSumOfFlop.add(sumOfShowdown);

        wetDryDraw.setFont(fs);
        wetDryDraw.setPreferredSize(new Dimension(25, 25));
        wetDryDraw.addActionListener(this);
        wetDryDraw.setSelected(false);
        panelCheckBoxesWetDry.add(wetDryDraw);

        wetDryMade.setFont(fs);
        wetDryMade.setPreferredSize(new Dimension(25, 25));
        wetDryMade.addActionListener(this);
        wetDryMade.setSelected(false);
        panelCheckBoxesWetDry.add(wetDryMade);

        wetDryShowdown.setFont(fs);
        wetDryShowdown.setPreferredSize(new Dimension(25, 25));
        wetDryShowdown.addActionListener(this);
        wetDryShowdown.setSelected(false);
        panelCheckBoxesWetDry.add(wetDryShowdown);

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

    /*- **************************************************************************** 
    *  Hands to playb radio buttons
    *******************************************************************************/

    private static void setHandsToPlay(int handsToPlay) {
        switch (handsToPlay) {

            case 1000:
                handsToPlay = 10000;
                break;
            case 10000:
                handsToPlay = 10000;
                break;
            case 100000:
                handsToPlay = 100000;
                break;
            case 1000000:
                handsToPlay = 1000000;
                break;
            case 10000000:
                handsToPlay = 10000000;
                break;
            case 100000000:
                handsToPlay = 100000000;
        }
    }

    /*-  ******************************************************************************
    	 * Handle check boxes 
    	 * Check the status of boxes and set boolean variables;
    	 *  ****************************************************************************** */
    private void checkBoxesl() {
        if (hmlDraw.isSelected()) {
            hmlDrawOption = true;
        } else {
            hmlDrawOption = false;
        }
        if (hmlMade.isSelected()) {
            hmlMadeOption = true;
        } else {
            hmlMadeOption = false;
        }
        if (hmlShowdown.isSelected()) {
            hmlShowdownOption = true;
        } else {
            hmlShowdownOption = false;
        }
        if (hhmlDraw.isSelected()) {
            hhmlDrawOption = true;
        } else {
            hhmlDrawOption = false;
        }
        if (hhmlMade.isSelected()) {
            hhmlMadeOption = true;
        } else {
            hhmlMadeOption = false;
        }
        if (hhmlShowdown.isSelected()) {
            hhmlShowdownOption = true;
        } else {
            hhmlShowdownOption = false;
        }
        if (hhhmlDraw.isSelected()) {
            hhhmlDrawOption = true;
        } else {
            hhhmlDrawOption = false;
        }
        if (hhhmlMade.isSelected()) {
            hhhmlMadeOption = true;
        } else {
            hhhmlMadeOption = false;
        }
        if (hhhmlShowdown.isSelected()) {
            hhhmlShowdownOption = true;
        } else {
            hhhmlShowdownOption = false;
        }
        if (typeDraw.isSelected()) {
            typeDrawOption = true;
        } else {
            typeDrawOption = false;
        }
        if (typeMade.isSelected()) {
            typeMadeOption = true;
        } else {
            typeMadeOption = false;
        }
        if (typeShowdown.isSelected()) {
            typeShowdownOption = true;
        } else {
            typeShowdownOption = false;
        }
        if (sumOfDraw.isSelected()) {
            sumOfFlopDrawOption = true;
        } else {
            sumOfFlopDrawOption = false;
        }
        if (sumOfMade.isSelected()) {
            sumOfFlopMadeOption = true;
        } else {
            sumOfFlopMadeOption = false;
        }
        if (sumOfShowdown.isSelected()) {
            sumOfFlopShowdownOption = true;
        } else {
            sumOfFlopShowdownOption = false;
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
            // Simulate the play of xxx hands, Preflop, Flop, Turn, and River
            Evaluate.doSimulationDataCollection(number);

            ReportManyHands.reportHMLMade(100, 100);
            ReportManyHands.reportHMLDraw(750, 100);
           ReportManyHands.reportHHMLMade(100, 200);
         //   ReportManyHands.reportHHMLDraw(750, 200);
         //   ReportManyHands.reportHHHMLMade(100, 300);

         //   ReportManyHands.reportHMLShowdownFlop(100, 400);
         //   ReportManyHands.reportHMLShowdownTurn(100, 500);
          //  ReportManyHands.reportHMLShowdownTurn(100, 500);
        }
    }

}
