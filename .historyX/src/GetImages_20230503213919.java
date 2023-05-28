//package evaluate_streets;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*- Class to handle card image. */
class GetImages extends JFrame implements Constants {
	private GetImages() {
		throw new IllegalStateException("Utility class");
	}

	/*- Get card image arg0 card string ex. "ac" returns imageIcon */
	static ImageIcon loadCardImage(String card) {
		final var $ = new StringBuilder().append("images/images/deck/").append(card).append(".png").toString();
		if (Files.exists(Paths.get($))) {
			return new ImageIcon($);
		}
		Logger.log("ERROR file not found " + $);
		return null;
	}

	/*- Get Table image - returns imageIcon. */
	static ImageIcon loadTableImage() {
		final var $ = "images/TableImage/Table.png";
		if (Files.exists(Paths.get($))) {
			return new ImageIcon($);
		}
		Logger.log("ERROR file not found " + $);
		return null;
	}

	/*- Get flop board and return JPanel. */
	static JPanel getFlop() {
		final var $ = new JPanel();
		EvalData.board[0] = new Card("Ad");
		EvalData.board[1] = new Card("Kd");
		EvalData.board[2] = new Card("3d");
		final var card1 = loadCardImage(EvalData.board[0].toString());
		final var card2 = loadCardImage(EvalData.board[1].toString());
		final var card3 = loadCardImage(EvalData.board[2].toString());
		final var label1 = new JLabel(card1);
		final var label2 = new JLabel(card2);
		final var label3 = new JLabel(card3);
		$.add(label1);
		$.add(label2);
		$.add(label3);
		return $;
	}

	/*- Get flop board and return JPanel. */
	static JPanel getTurn() {
		final var $ = new JPanel();
		final var card1 = loadCardImage(EvalData.board[0].toString());
		final var card2 = loadCardImage(EvalData.board[1].toString());
		final var card3 = loadCardImage(EvalData.board[2].toString());
		final var card4 = loadCardImage(EvalData.board[3].toString());
		final var label1 = new JLabel(card1);
		final var label2 = new JLabel(card2);
		final var label3 = new JLabel(card3);
		final var label4 = new JLabel(card4);
		$.add(label1);
		$.add(label2);
		$.add(label3);
		$.add(label4);
		return $;
	}

	/*- Get river board and return JPanel. */
	static JPanel getRiver() {
		final var $ = new JPanel();
		final var card1 = loadCardImage(EvalData.board[0].toString());
		final var card2 = loadCardImage(EvalData.board[1].toString());
		final var card3 = loadCardImage(EvalData.board[2].toString());
		final var card4 = loadCardImage(EvalData.board[3].toString());
		final var card5 = loadCardImage(EvalData.board[4].toString());
		final var label1 = new JLabel(card1);
		final var label2 = new JLabel(card2);
		final var label3 = new JLabel(card3);
		final var label4 = new JLabel(card4);
		final var label5 = new JLabel(card5);
		$.add(label1);
		$.add(label2);
		$.add(label3);
		$.add(label4);
		$.add(label5);
		return $;
	}

	/*- Get board and return JPanel. */
	static JPanel getBoard() {
		var $ = EvalData.street == TURN ? getTurn() : EvalData.street == FLOP ? getFlop() : new JPanel();
		if (EvalData.street == RIVER) {
			$ = getRiver();
		}
		return $;
	}

	/*- Get card 1 of hand panel arg0 is player position. */
	static JPanel getHand(int seat) {
		final var $ = new JPanel();
		var st1 = EvalData.hands[seat];
		if (st1 == null) {
			Logger.log("ERROR converting hand to string " + st1);
			st1 = "ackd";
		}
		final var c1 = st1.substring(0, 2);
		final var c2 = st1.substring(2);
		final var card2 = new CardImage(c2);
		$.add(new CardImage(c1));
		$.add(card2);
		return $;
	}

	/*- Get hand card 1 as image , arg0 is player position. */
	static CardImage getHandCard1(int seat) {
		var st1 = EvalData.hands[seat];
		if (st1 == null) {
			Logger.log("ERROR converting hand to string " + st1);
			st1 = "ackd";
		}
		final var $ = st1.substring(0, 2);
		return new CardImage($);
	}

	/*- Get hand card 2 as image , arg0 is player position. */
	static CardImage getHandCard2(int seat) {
		var st1 = EvalData.hands[seat];
		if (st1 == null) {
			Logger.log("ERROR converting hand to string " + st1);
			st1 = "ackd";
		}
		final var $ = st1.substring(2);
		return new CardImage($);
	}

	/*- Get card backs - returns 2 cards with the back of the card. */
	static JPanel get2CardBacks() {
		final var $ = new JPanel();
		final var card2 = new CardImage("deck");
		$.add(new CardImage("deck"));
		$.add(card2);
		return $;
	}

	/*- Get Table panel a */
	static JPanel getTable() {
		final var $ = new JPanel();
		$.add(new CardImage("table"));
		return $;
	}

	/*- Test. */
	private static void test() {
//		final var frame = new JFrame("Happy days");
//		frame.add(getTable());
	//	frame.pack();
		frame.setVisible(true);
	}

	// static void main(String[] args) {
	// test();
	// }
}
