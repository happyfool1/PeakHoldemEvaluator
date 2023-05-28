
//package evaluate_streets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIEditRanges extends JFrame implements Constants {

	private static JTextField typeField;
	private static JTextField positionField;
	private static JTextField betTypeField;
	private static JTextField statusField;
	private static JButton submitButton;

	private static String playerType = "";
	private static int playerPosition = -1;
	private static int betType = -1;

	private static boolean typeOK = false;
	private static boolean positionOK = false;
	private static boolean betTypeOK = false;

	private static void createView() {
		JFrame frame = new JFrame("Edit Ranges");
		frame.setPreferredSize(new Dimension(400, 300));
		frame.setLocation(400, 100);
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(4, 2));

		JLabel typeLabel = new JLabel("PLayer Type Hero, Average, Nit, Lag, Tag, ....");
		panel.add(typeLabel);
		typeField = new JTextField("Hero");
		panel.add(typeField);

		JLabel positionLabel = new JLabel("Position SB, BB, UTG, MP, CO, BU");
		panel.add(positionLabel);
		positionField = new JTextField("BU");
		panel.add(positionField);

		JLabel betTypeLabel = new JLabel("0 - 5 , Bet Type Bet1 Limp, Bet2 OPen, Bet3, Bet4, Allin, Call_Allin");
		panel.add(betTypeLabel);
		betTypeField = new JTextField("0");
		panel.add(betTypeField);

		submitButton = new JButton("Submit");
		panel.add(submitButton);
		frame.add(panel);

		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String type = typeField.getText();
				String position = positionField.getText();
				String bet = betTypeField.getText();

				typeOK = true;
				positionOK = true;
				betTypeOK = true;

				JOptionPane.showMessageDialog(null,
						"Type: " + type + "\nPosition: " + position + "\nBet Type: " + betType);

				switch (type) {
					case "Hero":
						playerType = type;
						break;
					case "Average":
						playerType = type;
						break;
					case "Nit":
						playerType = type;
						break;
					case "Lag":
						playerType = type;
						break;
					case "Tag":
						playerType = type;
						break;

					default:
						typeOK = false;

				}
				switch (position) {
					case "SB":
						playerPosition = SB;
						break;
					case "BB":
						playerPosition = BB;
						break;
					case "UTG":
						playerPosition = UTG;
						break;
					case "MP":
						playerPosition = MP;
						break;
					case "CO":
						playerPosition = CO;
						break;
					case "BU":
						playerPosition = BU;
						break;
					default:
						positionOK = false;
				}
				switch (bet) {
					case "Limp":
						betType = PREFLOP_LIMP;
						break;
					case "Open":
						betType = PREFLOP_OPEN;
						break;
					case "Bet3":
						betType = PREFLOP_BET3;
						break;
					case "Bet4":
						betType = PREFLOP_BET4;
						break;
					case "Allin":
						betType = PREFLOP_ALLIN;
						break;
					case "Call_Allin":
						betType = PREFLOP_CALL_ALLIN;
						break;
					default:
						typeOK = false;
				}
				if (typeOK && positionOK && betTypeOK) {
					JOptionPane.showMessageDialog(null,
						"OK");
				}
			}else{
				if (!typeOK) {
				JOptionPane.showMessageDialog(null,
                        "Type invalid");
				}
				if (!positionOK) {
                JOptionPane.showMessageDialog(null,
                        "Position invalid");
                }
			}

		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createView();
			}
		});
	}
}
