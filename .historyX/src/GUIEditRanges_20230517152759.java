
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

		JLabel betTypeLabel = new JLabel("Bet Type Bet1 ");
		panel.add(betTypeLabel);
		betTypeField = new JTextField();
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
