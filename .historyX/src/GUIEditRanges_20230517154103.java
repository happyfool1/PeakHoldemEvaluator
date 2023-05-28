
//package evaluate_streets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIEditRanges extends JFrame {

	private static JTextField typeField;
	private static JTextField positionField;
	private static JTextField betTypeField;
	private static JButton submitButton;

	private static String playerType = "";
	private static int playerPosition = -1;
	private static int betType = -1;

	private static void createView() {
		JFrame frame = new JFrame("Edit Ranges");
		frame.setPreferredSize(new Dimension(400, 300));
		frame.setLocation(400,100);
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
				String betType = betTypeField.getText();

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
							 playerType = type;                                break;
                         
                         default:

			}
			switch (position) {
				case "SB":
                    positionField.setText("BB");
                    break;
                case "BB":
                    positionField.setText("UTG");
                    break;
                case "UTG":
                    positionField.setText("MP");
                    break;
                case "MP":
                    positionField.setText("CO");
                    break;
                case "CO":
                    positionField.setText("BU");
                    break;
                case "BU":
                    positionField.setText("Allin");
                    break;
                case "Allin":
                    positionField.setText("Call_Allib");
                    break;
                case "Call_Allib":
                    positionField.setText("Allin");
            }
			switch (betType) {
				case "Limp":
                    betTypeField.setText("Limp");
                    break;
                case "Open":
                    betTypeField.setText("Open");
                    break;
                case "Bet3":
                    betTypeField.setText("Bet3");
                    break;
                case "Bet4":
                    betTypeField.setText("Bet4");
                    break;
                case "Allin":
                    betTypeField.setText("Allin");
                    break;
                case "Call_Allin":
                    betTypeField.setText("Call_Allin");
                    break;
              default:
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
