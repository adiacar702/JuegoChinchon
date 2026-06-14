package rules.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import resources.Card;
import rules.Combinations;

class CombinationsTest {

	@Test
	void testFindTrios() {

		Combinations combinations = new Combinations();

		List<Card> hand = List.of(new Card("🟡", "5"), new Card("⚔️", "5"), new Card("🍷", "5"));

		List<List<Card>> result = combinations.findTrios(hand);

		assertEquals(1, result.size());
	}

	@Test
	void testFindEscaleras() {

		Combinations combinations = new Combinations();

		List<Card> hand = List.of(new Card("🟡", "4"), new Card("🟡", "5"), new Card("🟡", "6"));

		List<List<Card>> result = combinations.findEscaleras(hand);

		assertEquals(1, result.size());
	}
}