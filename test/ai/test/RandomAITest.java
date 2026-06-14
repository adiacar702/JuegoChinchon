package ai.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import ai.RandomAI;
import game.Table;
import playerUsers.*;
import resources.Card;

class RandomAITest {

	@Test
	void testRandomAIDrawCard() {

		Table table = new Table(1);
		table.init();

		RandomAI ai = new RandomAI();

		Player player = new AIPlayer("CPU", ai);

		Card card = ai.drawCard(player, table);

		assertNotNull(card);
	}

}
