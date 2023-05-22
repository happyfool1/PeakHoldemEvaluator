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

	/*-
 texas holdem 6-max no limit $1/$2 what is usual 3 bet preflop

ChatGPT
In a 6-max No-Limit Hold'em $1/$2 game, the usual 3-bet preflop size depends on the 
specific game dynamics and your personal strategy. However, a standard 3-bet sizing
 is typically between 3 to 4 times the initial raise, with adjustments made based 
 on position and the specific opponents you're facing.

For example, if the initial raiser opens to $6 (3 big blinds), a standard 3-bet size
 would be between $18 to $24. 
 If you're in position, you can lean toward the smaller end of that range, 
 while out of position, you might opt for a larger sizing to discourage callers and 
 take control of the pot.

Keep in mind that these are general guidelines, and your strategy should be adjusted
based on your opponents' tendencies, stack sizes, and your overall game plan.





User
2 bet size
ChatGPT
In No-Limit Texas Hold'em, the term "2-bet" refers to the initial opening raise preflop. 
The sizing of a 2-bet depends on the specific game dynamics, table stakes, 
and your personal strategy. However, there are some general guidelines for 2-bet sizing 
in a 6-max No-Limit $1/$2 game.

A common 2-bet sizing in this type of game is between 2.5 to 4 big blinds. 
Using this range, you would raise anywhere from $5 to $8 in a $1/$2 game. 
The exact size will depend on factors like your position, the tendencies of your 
opponents, and your hand strength.

In early position or when facing tighter opponents, you may opt for a larger raise size 
(3.5-4 big blinds) to discourage callers and build a bigger pot with your strong hands.
 In later position or when playing against looser opponents, you can use a smaller sizing 
 (2.5-3 big blinds) to give yourself better pot odds and allow for more flexibility postflop.

These are general guidelines, and you should be prepared to adjust your 2-bet sizing
based on your opponents' tendencies, stack sizes, and your overall game plan.





User
4 bet
ChatGPT
In No-Limit Texas Hold'em, a "4-bet" refers to the act of re-raising after an 
initial raise (2-bet), a re-raise (3-bet), and before the flop is dealt. 
The size of a 4-bet depends on the specific game dynamics, your personal strategy, 
nd the size of the previous bets.

In a 6-max No-Limit $1/$2 game, a typical 4-bet sizing is between 2 to 2.5 times 
the size of the 3-bet. However, the exact sizing can vary depending on factors 
such as your position, your opponents' tendencies, and the size of the 3-bet.

For example, let's say the initial raiser (2-bet) opens to $6 (3 big blinds), 
and another player 3-bets to $18. A standard 4-bet sizing would be between $36 to $45.

When you're in position, you might opt for a smaller 4-bet size to give yourself 
better pot odds and the ability to exert more postflop pressure on your opponent. 
When you're out of position, a larger 4-bet size can help to discourage your opponent 
from calling and take down the pot preflop or give you more control of the pot if they do call.

As with all poker strategies, these are general guidelines, and you should be prepared 
to adjust your 4-bet sizing based on your opponents' tendencies, stack sizes, 
and your overall game plan.





User
guidelines for bt sizes on the flop
ChatGPT
In No-Limit Texas Hold'em, the size of your bets on the flop is an important factor in 
dictating the pace of the hand and exerting pressure on your opponents. Here are some 
general guidelines for bet sizes on the flop:

Standard sizing: A common bet size on the flop is between 50% and 75% of the pot. 
This sizing allows you to build the pot with your strong hands, while also putting 
pressure on your opponents with weaker holdings. For example, if the pot is $20,
a standard bet size would be between $10 and $15.

Position: Your position at the table influences your bet sizing. 
When you're in position (acting after your opponent), you can use smaller bet sizes 
to take advantage of your informational edge. When out of position, you might choose 
larger bet sizes to discourage your opponent from floating with weaker hands or to 
protect your strong hands.

Board texture: The texture of the flop is an important consideration when determining 
your bet size. On dry, unconnected flops (e.g., K-7-2 rainbow), you can use smaller
 bet sizes (around 1/3 to 1/2 of the pot) because it's less likely that your opponent
  has connected with the board. On wet, coordinated flops (e.g., Q-J-10 with two suited cards), 
  larger bet sizes (around 2/3 to full pot) are more appropriate to charge your opponents 
  for drawing to better hands.

Opponent tendencies: Adjust your bet sizing based on your opponents' playing styles. 
Against tighter opponents, smaller bet sizes may be enough to take down the pot, 
as they are likely to fold weaker hands. Against looser or more aggressive opponents,
larger bet sizes can help protect your strong hands and put pressure on their weaker holdings.

Stack sizes: Consider the effective stack sizes when determining your bet size. 
With deeper stacks, you may want to use larger bet sizes to build a bigger pot for when you 
have a strong hand. When you or your opponent have smaller stacks, smaller bet sizes can help you
control the pot and prevent committing too many chips with a marginal hand.

These guidelines provide a starting point for determining your bet sizes on the flop,
 but keep in mind that poker is a dynamic game that requires constant adjustments based 
 on various factors, including your opponents' tendencies and your overall game plan.





User
is there a practical way to apply GTO Flop play
ChatGPT
Game Theory Optimal (GTO) is a strategy based on the Nash Equilibrium, where neither player
 can improve their outcome by deviating from their current strategy. 
 In poker, GTO strategies provide a balanced approach that makes it difficult for opponents 
 to exploit you. However, applying GTO in real-time can be quite complex, especially on the flop.

While it's difficult to perfectly apply GTO strategies during live play, there are some practical
 ways to incorporate GTO concepts into your flop play:

Study GTO solutions: Use GTO solvers like PioSolver, GTO+, or Simple Postflop to study optimal strategies for various board textures, hand ranges, and bet sizes. Analyze the outputs and try to understand the underlying principles and patterns.

Simplify your strategy: Break down GTO solutions into simplified strategies that are easier to remember and apply during live play. Focus on key factors such as board texture, hand strength, and bet sizing.

Focus on frequencies: GTO strategies often involve mixed strategies, where certain actions are taken with specific frequencies. While it's difficult to randomize your actions during live play, understanding the ideal frequencies can help you avoid becoming predictable and make better decisions.

Balance your ranges: One of the key aspects of GTO play is having balanced ranges. Make sure you have both value bets and bluffs in your range when betting or raising, and ensure you can call with a mix of strong and marginal hands when facing aggression.

Adjust to your opponents: While GTO strategies are designed to be unexploitable, most players are not playing GTO themselves. Observe your opponents and adjust your strategy accordingly. If they are too passive, you can value bet more thinly; if they are too aggressive, you can call down lighter or incorporate more check-raises.

Practice and review: Constantly practice your GTO strategies and review your play to identify areas for improvement. Discuss hands with other players or use poker training resources to refine your understanding of GTO concepts.

Remember that GTO is just one approach to poker, and exploiting your opponents' tendencies can often lead to higher profits than adhering strictly to GTO play. Use GTO concepts as a foundation, but be prepared to deviate from GTO when facing specific opponents or situations.





User
give example of balanced range
ChatGPT
A balanced range in poker means having a mix of strong hands, medium-strength hands, and bluffs in your betting or raising range, making it difficult for your opponents to exploit your actions. Here's an example of a balanced range on the flop in a No-Limit Texas Hold'em game:

Flop: 9h 7d 5c
Pot size: $20

Suppose you are in position and your opponent checks to you. In this situation, you should have a balanced betting range that includes value hands, draws, and bluffs.

Value hands:

Sets: 99, 77, 55
Overpairs: AA, KK, QQ, JJ, TT
Top pair with a good kicker: A9, K9, Q9
Draws:

Straight draws: 86, JT, T8, 64
Flush draws: Any two suited cards of the same suit as the flop
Bluffs:

Overcards with backdoor flush draws: AhKh, AhQh, KhQh
Gutshot straight draws: 84, J8, T6, J6
By having a balanced range, you make it difficult for your opponent to accurately read your hand strength when you bet. If you only bet with strong hands or only bluff, your opponents can easily exploit your play by folding when you bet or calling you down with weaker hands. By including both value hands and bluffs in your betting range, you make your play more challenging for your opponents to counter.

It's important to note that this is just an example, and your balanced range should be adjusted based on factors such as your opponents' tendencies, your position, and the specific board texture.





User
example of percentages
ChatGPT
In poker, percentages are often used to describe hand equities, frequencies of actions, and pot odds. Here's an example involving hand equities and pot odds in a No-Limit Texas Hold'em game:

You are holding Ac Kc, and your opponent has Qd Qc. The board is 9h 7c 2c, giving you a nut flush draw with two overcards. To determine your hand equity (the percentage chance of winning the hand), you can count your outs and use the "Rule of 2 and 4" to approximate your equity.

Outs:

9 clubs for a flush
3 remaining aces for a pair of aces
3 remaining kings for a pair of kings
Total outs: 9 + 3 + 3 = 15

Rule of 2 and 4:

On the flop: 15 outs *




User
percentage of bluffs in previous response
ChatGPT
In the previous response, we provided an example of a balanced range on the flop. To calculate the percentage of bluffs within that range, we'll first count the total number of hand combinations for each hand category, and then divide the number of bluffing combinations by the total combinations.

Value hands:

Sets: 99 (3), 77 (3), 55 (3) [9 combinations]
Overpairs: AA (6), KK (6), QQ (6), JJ (6), TT (6) [30 combinations]
Top pair with a good kicker: A9 (6), K9 (6), Q9 (6) [18 combinations]
Draws:

Straight draws: 86 (16), JT (16), T8 (16), 64 (16) [64 combinations]
Flush draws: Assuming 14 combinations (excluding AcKc and hands already counted in straight draws)
Bluffs:

Overcards with backdoor flush draws: AhKh (1), AhQh (1), KhQh (1) [3 combinations]
Gutshot straight draws: 84 (16), J8 (16), T6 (16), J6 (16) [64 combinations]
Total value hand combinations: 9 + 30 + 18 = 57
Total draw combinations: 64 + 14 = 78
Total bluff combinations: 3 + 64 = 67
Total combinations: 57 + 78 + 67 = 202

Percentage of bluffs: (67 bluffs / 202 total combinations) * 100 = 33.17%

In this example, bluffs make up roughly 33.17% of the balanced betting range on the flop. Keep in mind that the actual percentage of bluffs may vary based on the specific situation, your opponent's tendencies, and the board texture.
	 * 
	 */

}
