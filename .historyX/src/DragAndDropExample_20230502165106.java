//package evaluate_streets;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.TransferHandler;

public class DragAndDropExample {
	private static JButton[] sourceButtons;
	private static JButton[] destinationButtons;

	public static void main(String[] args) {
		final var frame = new JFrame("Drag and Drop Example");
		frame.setLayout(new GridLayout(2, 3));

		// Create six source buttons
		sourceButtons = new JButton[6];
		for (int i = 0; i < 6; i++) {
			sourceButtons[i] = new JButton("Source Button " + (i + 1));
			sourceButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent event) {
					// Start dragging
					final var comp = (JComponent) event.getSource();
					final var handler = comp.getTransferHandler();
					handler.exportAsDrag(comp, event, TransferHandler.COPY);
				}
			});
			frame.add(sourceButtons[i]);
		}

		// Create six destination buttons
		destinationButtons = new JButton[6];
		for (int i = 0; i < 6; i++) {
			destinationButtons[i] = new JButton("Destination Button " + (i + 1));
			destinationButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			destinationButtons[i].setContentAreaFilled(false);
			destinationButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent event) {
					// Highlight destination button when mouse enters it
					final var comp = (JComponent) event.getSource();
					comp.setBorder(BorderFactory.createLineBorder(Color.BLUE));
					// comp.setContentAreaFilled(true);
				}

				@Override
				public void mouseExited(MouseEvent event) {
					// Reset appearance when mouse leaves destination button
					final var comp = (JComponent) event.getSource();
					comp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					// comp.setContentAreaFilled(false);
				}

				@Override
				public void mouseReleased(MouseEvent event) {
					// Perform drop
					// JButton sourceButton = (JButton)
					// event.getTransferable().getSourceComponent();
					//final var destinationButton = (JButton) event.getSource();
					// String sourceText = sourceButton.getText();
					// destinationButton.setText(sourceText);
				}
			});
			frame.add(destinationButtons[i]);
		}

		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
