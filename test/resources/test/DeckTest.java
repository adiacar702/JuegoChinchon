package resources.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import resources.Deck;

class DeckTest {

	@Test
	void drawReducesDeckSize() {

		Deck deck = new Deck(1);

		int initial = deck.size();

		deck.draw();

		assertEquals(initial - 1, deck.size());
	}
}
