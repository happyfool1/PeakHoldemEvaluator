
//package evaluate_streets;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIEditRanges extends JFrame {

	private static JTextField typeField;
	private static JTextField positionField;
	private static JTextField betTypeField;
	private static JButton submitButton;



	private static void createView() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(4, 2));

		JLabel typeLabel = new JLabel("Type");
		panel.add(typeLabel);
		typeField = new JTextField();
		panel.add(typeField);

		JLabel positionLabel = new JLabel("Position");
		panel.add(positionLabel);
		positionField = new JTextField();
		panel.add(positionField);

		JLabel betTypeLabel = new JLabel("Bet Type");
		panel.add(betTypeLabel);
		betTypeField = new JTextField();
		panel.add(betTypeField);

		submitButton = new JButton("Submit");
		panel.add(submitButton);

		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String type = typeField.getText();
				String position = positionField.getText();
				String betType = betTypeField.getText();

				JOptionPane.showMessageDialog(null,
						"Type: " + type + "\nPosition: " + position + "\nBet Type: " + betType);
			}
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				 createView();
			}
		});
	}
}
