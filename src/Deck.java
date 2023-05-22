//package evaluate_streets;

import java.security.SecureRandom;
import java.util.Random;

/*-
 * Represents a standard 52-card deck of cards. 
 * Methods allow shuffling, dealing, dead cards
 * 
 * @author PEAK_ 
 */
class Deck implements Constants {
	private static final int DECK_SIZE = 52;

	/*- Backing array containing cards held within this deck. */
	private static Card[] cards = new Card[DECK_SIZE];

	private static final Card[] cardsInit = new Card[DECK_SIZE];

	/*- Current cursor offset in the backing array. */
	private static int currentIndex;

	private static final Random random = new SecureRandom();

	/*- Dead cards. */
	private static final Card[] deadCards = new Card[DECK_SIZE - 17];
	private static int deadCardCount = 0;

	private static boolean newDeck;

	/*-  Change this number for an all new pseudorandom series of cards set by setSeed method */
	private static long seed = 2000141567;

	private Deck() {
		throw new IllegalStateException("Utility class");
	}

	/*-
	 * Constructs a new deck with the cards placed in a basic uniform order. 
	 * Call one time only! 
	 * The sequence will repeat exactly the same forever if the seed is not changed. 
	 * So it is really pseudo random, not random. 
	 * Starts all over again if called again with the same seed. 
	 * Truly random is if the seed in Options is 0 ( no seed ) so Random will choose a random seed.
	 */
	static void newDeck() {
		if (seed != 0L) {
			random.setSeed(seed);
		}
		for (int i = 0; i < 52; ++i) {
			cardsInit[i] = new Card(i);
			cards[i] = new Card(i);
		}
		clearDeadCards();
		newDeck = true;
	}

	/*-
	 *Pops the next available from the deck. 
	 *ReturnThe next available from the deck. 
	 *The sequence will repeat exactly the same forever if the seed is not  changed. 
	 *So it is really pseudo random, not random. 
	 *This method if for the Cards calass, not Cards
	 */
	static Card pop() {
		var $ = cards[currentIndex++];
		if ($ == null) {
			for (int i = 0; i < deadCardCount; ++i) {
				$ = cards[currentIndex++];
				if ($ != null) {
					return $;
				}
			}
		}
		return $;
	}

	/*-
	 * Look at  next card to be dealt
	 * without disturbing the deck
	 */
	static Card peekAtNextCard() {
		var $ = cards[currentIndex];
		if ($ == null && deadCardCount != 0) {
			for (int i = 0; i < deadCardCount; ++i) {
				$ = cards[++currentIndex];
				if ($ != null) {
					return $;
				}
			}
		}
		return $;
	}

	/*-
	 * Look at the value of the next card to be dealt
	 * without disturbing the deck
	 */
	static int peekAtNextCardValue() {
		var $ = cards[currentIndex];
		if ($ == null && deadCardCount != 0) {
			for (int i = 0; i < deadCardCount; ++i) {
				$ = cards[++currentIndex];
				if ($ != null) {
					return $.value;
				}
			}
		}
		return $.value;
	}

	/*- 
	 *The Knuth (or Fisher-Yates) shuffling algorithm. 
	 */
	static void shuffle() {
		if (!newDeck) {
			newDeck();
		}
		cards = cardsInit;
		currentIndex = 0;
		// Loop over array.
		for (int i = 0; i < DECK_SIZE; ++i) {
			// Get a random index of the array past the current index.
			// ... The argument is an exclusive bound.
			// It will not go past the array's end.
			final int randomValue = i + random.nextInt(DECK_SIZE - i);
			// Swap the random element with the present element.
			final var randomElement = cards[randomValue];
			cards[randomValue] = cards[i];
			cards[i] = randomElement;
		}
		if (deadCardCount != 0) {
			for (int i = 0; i < deadCardCount; i++) {
				for (int j = 0; j < DECK_SIZE; j++) {
					if (cards[j] == deadCards[i]) {
						cards[j] = null;
					}
				}
			}
		}
	}

	/*- Clear dead card array. */
	static void clearDeadCards() {
		deadCardCount = 0;
	}

	/*-   Add card to dead card array. */
	static void setDeadCard(Card c0) {
		deadCards[deadCardCount++] = c0;
	}

	static void setSeed(long s) {
		seed = s;
	}
}
