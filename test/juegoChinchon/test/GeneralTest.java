package juegoChinchon.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import game.Table;
import resources.*;
import rules.*;

class GeneralTest {
	//deck
	@Test
	void deckHave40Cards() {
		Deck deck = new Deck(1);
		assertEquals(40, deck.size());
	}

	@Test
	void drawReduceDeckSize() {
		Deck deck = new Deck(1);
		int initial = deck.size();
		deck.draw();
		assertEquals(initial - 1, deck.size());
	}

	//card
	@Test
	void equalsShouldWork() {
		Card c1 = new Card("🍷", "1");
		Card c2 = new Card("🍷", "1");

		assertEquals(c1, c2);
	}

	//trios
	@Test
	void detectTrio() {
		List<Card> hand = List.of(new Card("🍷", "2"), new Card("⚔️", "2"), new Card("🏏", "2"));

		assertFalse(new Combinations().findTrios(hand)
				.isEmpty());
	}

	//escaleras
	@Test
	void detectEscalera() {
		List<Card> hand = List.of(new Card("🍷", "1"), new Card("🍷", "2"), new Card("🍷", "3"));

		assertFalse(new Combinations().findEscaleras(hand)
				.isEmpty());
	}

	// CombinationValidator
	@Test
	void closeWithChinchon() {
		CombinationValidator v = new CombinationValidator();

		List<Card> hand = List.of(new Card("🍷", "1"), new Card("🍷", "2"), new Card("🍷", "3"), new Card("🍷", "4"),
				new Card("🍷", "5"), new Card("🍷", "6"), new Card("🍷", "7"));

		assertTrue(v.canClose(hand));
	}

	//table
	@Test
	void drawNotNull() {
		Table table = new Table(1);
		table.init();

		assertNotNull(table.drawDeck());
	}
}
