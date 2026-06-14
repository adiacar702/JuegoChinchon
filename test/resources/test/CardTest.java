package resources.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import resources.Card;

class CardTest {

	@Test
	void equalsReturnsTrueForSameCard() {

		Card c1 = new Card("⚔️", "5");
		Card c2 = new Card("⚔️", "5");

		assertEquals(c1, c2);
	}

	@Test
	void equalsReturnsFalseForDifferentCard() {

		Card c1 = new Card("⚔️", "5");
		Card c2 = new Card("🟡", "5");

		assertNotEquals(c1, c2);
	}
}