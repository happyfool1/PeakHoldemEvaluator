//package evaluate_streets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

public class Temp extends JFrame implements ActionListener, Constants {
	private JPanel mainPanel;
	private JPanel panelCardsA;
	private JPanel panelCardsB;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JTextArea button6;
	private JTextArea button7;

	public void myFrame() {
		final var fs = new Font("Dialog", Font.BOLD, 10);
		/// Set up the main panel
		// this.setLayout(new FlowLayout());
		mainPanel = new JPanel(null);
		mainPanel.setPreferredSize(new Dimension(500, 500));
		// mainPanel.setLayout(new GridLayout(3, 1));

		// setBounds(x, y, width, height)
		panelCardsA = new JPanel(new GridLayout(4, 1));
		panelCardsA.setBackground(Color.LIGHT_GRAY);
		panelCardsA.setBounds(10, 10, 50, 80);
		mainPanel.add(panelCardsA);

		panelCardsB = new JPanel(new GridLayout(2, 1));
		panelCardsB.setBackground(Color.LIGHT_GRAY);
		panelCardsB.setBounds(200, 10, 100, 80);
		mainPanel.add(panelCardsB);

		button1 = new JButton("Ac");
		button1.setFont(fs);
		button1.setPreferredSize(new Dimension(25, 25));
		button1.addActionListener(this);
		button1.addMouseListener(new DragListener());
		button1.setTransferHandler(new TransferHandler("text"));
		System.out.println(SwingUtilities.convertPoint(button1, button1.getX(), button1.getY(), this));
		System.out.println(button1);
		panelCardsA.add(button1);

		button2 = new JButton("Kc");
		button2.setFont(fs);
		button2.setPreferredSize(new Dimension(10, 10));
		button2.addActionListener(this);
		button2.addMouseListener(new DragListener());
		System.out.println(button2);
		System.out.println(panelCardsA);
		button2.setTransferHandler(new TransferHandler("text"));
		panelCardsA.add(button2);

		button3 = new JButton("Qc");
		button3.setFont(fs);
		button3.setPreferredSize(new Dimension(25, 25));
		button3.addActionListener(this);
		button3.addMouseListener(new DragListener());
		button3.setTransferHandler(new TransferHandler("text"));
		panelCardsB.add(button3);

		button4 = new JButton("Jc");
		button4.setFont(fs);
		button4.setPreferredSize(new Dimension(25, 25));
		button4.addActionListener(this);
		button4.addMouseListener(new DragListener());
		button4.setTransferHandler(new TransferHandler("text"));
		panelCardsB.add(button4);

		button5 = new JButton("Tc");
		button5.setFont(fs);
		button5.setPreferredSize(new Dimension(25, 25));
		button5.addActionListener(this);
		button5.addMouseListener(new DragListener());
		button5.setTransferHandler(new TransferHandler("text"));
		button5.setBounds(10, 100, 50, 50);
		mainPanel.add(button5);

		button6 = new JTextArea("9c");
		button6.setFont(fs);
		button6.setPreferredSize(new Dimension(25, 25));
		button6.addMouseListener(new DragListener());
		button6.setTransferHandler(new TransferHandler("text"));
		button6.setDragEnabled(true);
		button6.setBounds(10, 100, 50, 50);
		panelCardsA.add(button6);

		button7 = new JTextArea("8c");
		button7.setFont(fs);
		button7.setPreferredSize(new Dimension(25, 25));
		button7.addMouseListener(new DragListener());
		button7.setTransferHandler(new TransferHandler("text"));
		button7.setDragEnabled(true);
		button7.setBounds(10, 100, 50, 50);
		System.out.println(button7);
		System.out.println(panelCardsA);
		panelCardsA.add(button7);

		// Set some properties of the frame
		this.setContentPane(mainPanel);
		this.setTitle("My Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final var b = (JButton) e.getSource();
		System.out.println("//PPP actionPerformed(ActionEvent e)");
		System.out
				.println(new StringBuilder().append("//PPP ").append(b.getX()).append(" ").append(b.getY()).toString());
		System.out.println("//PPP " + b.getText());
		final var p = new Point(b.getX(), b.getY());
		if (panelCardsA.contains(p)) {
			System.out.println("//PPP A contains");
		}
		if (panelCardsB.contains(p)) {
			System.out.println("//PPP B contains");
		}

		// Save the original location of the button
		// buttonLocations.put(draggedButton, draggedButton.getLocation());
	}

	private class DragListener extends MouseAdapter {
		private JButton draggedButton;

		@Override
		public void mousePressed(MouseEvent e) {
			final var st = e.paramString();
			final int i1 = st.indexOf("absolute(");
			final int i2 = st.indexOf(",", i1 + 8);
			final var st2 = st.substring(i1 + 9, i2);
			final int i3 = st.indexOf(")", i2);
			final var st3 = st.substring(i2 + 1, i3);
			final int x = button6.getX();
			final int y = button6.getY();
			final int a = panelCardsA.getX();
			final int b = panelCardsA.getY();
			final var p = e.getLocationOnScreen();
			System.out.println("//QQQ " + p);

			// JTextArea b = (JTextArea) e.getSource();
			System.out.println("//QQQ   mousePressed(MouseEvent e) ");
			System.out.println(new StringBuilder().append("//QQQst ").append(st2).append("---").append(st3).append(" ")
					.append(x).append(" ").append(y).append(" ").append(a).append(" ").append(b).toString());
			System.out.println("//QQQst " + e);
			// System.out.println("//QQQ " + b.getX() + " " + b.getY());
			// System.out.println("//QQQ " + b.getText());
			// Point p = new Point(b.getX(), b.getY());
			// if (panelCardsA.contains(p)) {
			// System.out.println("//QQQ A contains");
			// }
			// if (panelCardsB.contains(p)) {
			// System.out.println("//QQQ B contains");
			// }

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("//RRR mouseReleased(MouseEvent e) ");
			final var st = e.paramString();
			final int i1 = st.indexOf("absolute(");
			final int i2 = st.indexOf(",", i1 + 8);
			final var st2 = st.substring(i1 + 9, i2);
			final int i3 = st.indexOf(")", i2);
			final var st3 = st.substring(i2 + 1, i3);
			final int x = button6.getX();
			final int y = button6.getY();
			final int a = panelCardsA.getX();
			final int b = panelCardsA.getY();

			// JTextArea b = (JTextArea) e.getSource();
			System.out.println("//RRR  mousePressed(MouseEvent e) ");
			System.out.println(new StringBuilder().append("//RRRst ").append(st2).append("---").append(st3).append(" ")
					.append(x).append(" ").append(y).append(" ").append(a).append(" ").append(b).toString());
			System.out.println("//RRRst " + e);
			// JButton b = (JButton) e.getSource();
			// System.out.println("//RRR " + b.getX() + " " + b.getY() + " ");
			// System.out.println("//RRR " + b.getText());
			// Point p = new Point(b.getX(), b.getY());

			// if (panelCardsA.contains(p)) {
			// System.out.println("//RRRA contains");
			// }
			// if (panelCardsB.contains(p)) {
			// System.out.println("//RRRB contains");
			// }
		}

		private class DragListenerX extends MouseAdapter {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("//SSS mousePressed(MouseEvent e) ");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("//TTT mouseReleased(MouseEvent e)");
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("//UUU mouseDragged(MouseEvent e) ");
			}
		}
	}

	/*- */

}
