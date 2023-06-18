//package game;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;

/*-
 * This class is not unique to the Interactive application. 
 * This is a general helper class that will be used by multiple applications.\Interactive is the first one.
 * Interactive is an application that allows a user to play against the simuletor but with information
 * on the game display that helps him make the correct decisions.
 * 
 * Range tables and rules are obtained and displayed. 
 * Panels are generated and are returned by methods in this class.
 * 
 * Public Methods
 * 	Initialize
 * 	getRangePanel1
 * 		Returns a panel with the appropriate range table
 * getRangePanel2
 * 		Returns a panel with the appropriate range table
 * getRangePanel3
 * 		Returns a panel with the appropriate range table
 * getRulePanel
 * 		Returns a panel with the appropriate rule table
 * 
 * There is only one instance for this class because range tables are only displayed for the 
 * human player, not for the simulated players.
 * This is the standard 13X13 panel with colors to indicate status.
 */

import javax.swing.JPanel;
import javax.swing.JTextField;

public class RulePanel implements Constants {

	private static int xxx;

	/*- From Evaluate class. */
	private static int handValue;

	JPanel panelRule = new JPanel();

	HandRange range = new HandRange();

	Rules rules = new Rules();

	JTextField handTxt = new JTextField("");

	JTextField handRule = new JTextField("");

	/*- Board. */
	JTextField fml = new JTextField("");

	JTextField texture = new JTextField("");

	/*- - Constructor. */
	public RulePanel() {
		constructPanel();
	}

	/*- - Create the JPanes. */
	public void constructPanel() {
		panelRule.setLayout(new GridLayout(3, 2));

		panelRule.setMaximumSize(new Dimension(30, 30));

		new Dimension(1, 1);
		new Font("Dialog", Font.BOLD, 6);
		final Font ruleFont = new Font("Dialog", Font.BOLD, 14);

		handRule.setFont(ruleFont);
		handRule.setForeground(Color.BLACK);
		handRule.setBackground(Color.CYAN);

		fml.setFont(ruleFont);
		fml.setForeground(Color.BLACK);
		fml.setBackground(Color.CYAN);

		texture.setForeground(Color.BLACK);
		texture.setBackground(Color.CYAN);
	}

	/*- Get Rule panel. */
	public JPanel getPanel() {
		return this.panelRule;
	}

	/*- -Get Rule Panel. */
	public JPanel updatePanel(String path) {
		if (EvalData.streetNumber != PREFLOP) {
			Logger.log("getRule " + path);
			String pos;
			String file;
			String street = "Error";
			final String fullPath;
			fml.setText("Middle");
			pos = "Middle";
			file = "Rule.ser";
			if (EvalData.streetNumber == FLOP) {
				street = "Flop";
			}
			if (EvalData.streetNumber == TURN) {
				street = "Turn";
			}
			if (EvalData.streetNumber == RIVER) {
				street = "River";
			}
			/*- 
			if (Play.first == 0) {
				if (EvalData.foldCount - PLAYERS == 2) {
					fml.setText("First HU");
					pos = "First";
					file = "RuleHU.ser";
				} else {
					fml.setText("First");
					pos = "First";
					file = "Rule.ser";
				}
			}
			if (Play.last == 0) {
				if (EvalData.foldCount - PLAYERS == 2) {
					fml.setText("Last");
					pos = "First";
				} else {
					fml.setText("Last HU");
					pos = "Last";
				}
				file = "RuleHU.ser";
			}
			fullPath = new StringBuilder().append("C:\\PeakHoldemDatabase\\Hero\\").append(street).append("\\")
					.append(pos).append("\\").append(file).toString();
			Logger.log("path rules " + fullPath);
			rules.readRules(fullPath);
		//	handValue = Evaluate.evaluateAll(0);
			handRule.setText(rules.playToString(rules.getPlay(handValue, EvalData.betType)));
			panelRule.add(handTxt);
			panelRule.add(handRule);
			panelRule.add(texture);
			panelRule.add(fml);
		}
		*/
		return this.panelRule;
		
	}

	/*- - Convert to decimal format. */
	private static String decFormat(double number) {
		final String pattern = "######.";
		return number == 0 ? "   0  " : "   " + (new DecimalFormat(pattern)).format(number);
	}
}
