package game.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Table;
import resources.Card;

class TableTest {

	@Test
	void testDrawDeck() {

		Table table = new Table(1);

		table.init();

		Card card = table.drawDeck();

		assertNotNull(card);
	}

	@Test
	void testDiscard() {

		Table table = new Table(1);

		table.init();

		Card card = new Card("🟡", "7");

		table.discard(card);

		assertEquals(card, table.peekDiscard());
	}
}
