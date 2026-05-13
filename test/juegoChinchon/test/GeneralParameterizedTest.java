package juegoChinchon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import resources.*;

class GeneralParameterizedTest {
	//decks
	@ParameterizedTest
	@ValueSource(ints = { 1, 2 })
	void deckSizeDependsOnNumberOfDecks(int numDecks) {
		Deck deck = new Deck(numDecks);
		assertEquals(40 * numDecks, deck.size());
	}

	//cards
	@ParameterizedTest
	@CsvSource({ "1,1", "2,2", "7,7" })
	void numericValueShouldMatch(String value, int expected) {
		Card c = new Card("🍷", value);
		assertEquals(expected, c.getNumericValue());
	}

	//figuras cards
	@ParameterizedTest
	@CsvSource({ "♙,10", "♞,11", "👑,12" })
	void figureCardsCorrectValue(String value, int expected) {
		Card c = new Card("🍷", value);
		assertEquals(expected, c.getNumericValue());
	}
}
