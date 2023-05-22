//package evaluate_streets;

/*- ******************************************************************************
* This Class contains methods that will assist in adapting other projects 
* to  use this PeakHoldemEvaluatorproject.
* 
* The most basic conversion is from a String representation of a Hold'em hand,
* hole cards and board, to an instance of Hands. Hands is the interface to the
* EvaluateOne Class.
* 
 * @author PEAK_
****************************************************************************** */

public class HandConversion implements Constants {

	private HandConversion() {
		throw new IllegalStateException("Utility class");
	}

	/*-
	 * Convert a String representation of a Hold'em hand, hole cards and board 
	 * to an instance of Hands.
	 * 
	 * Example aHand = HandConversion.convertStringToHands("AcTd 4h3sQc9s8c");
	 * Must be 2 hole cards + 5 board cards. Spaces optional.
	 * 
	 * Arg0 - A String representation of a Hold'em hand
	 * Returns Hands
	 */
	static Card[] convertStringToHands(String h) {
		final var st = h.replace(" ", "");
		final var cards = new Card[7];
		if (st.length() != 14) {
			Logger.log(new StringBuilder().append("//ERROR  convertStringToHands invalid length ").append(st)
					.append(" ").append(st.length()).toString());
			return cards;
		}
		final var c1 = st.substring(0, 2);
		final var c2 = st.substring(2, 4);
		final var c3 = st.substring(4, 6);
		final var c4 = st.substring(6, 8);
		final var c5 = st.substring(8, 10);
		final var c6 = st.substring(10, 12);
		final var c7 = st.substring(12, 14);

		if (!Card.isValid(c1) || !Card.isValid(c2) || !Card.isValid(c3) || !Card.isValid(c4) || !Card.isValid(c5)
				|| !Card.isValid(c6) || !Card.isValid(c7)) {
			Logger.log(new StringBuilder().append("//ERROR convertStringToHands invalid card in ").append(st)
					.append(" ").append(c1).append(" ").append(c2).append(" ").append(c3).append(" ").append(c4)
					.append(" ").append(c5).append(" ").append(c6).append(" ").append(c7).toString());
			return cards;
		}
	

		// TODO
		// cards[0] = addHoleCards(0, card1, card2); // Seat number 0
		// cards[0] = addBoard(card3, card4, card5);
		// cards[0] = board.addTurnBoardCard(card6);
		// cards[0] = board.addRiverBoardCard(card7);
		return cards;
	}
}
