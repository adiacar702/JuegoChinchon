package ai.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import ai.SmartAI;
import game.Table;
import playerUsers.*;
import resources.Card;
import rules.*;

class SmartAITest {

	@Test
	void testSmartAIDrawCard() {

		Table table = new Table(1);
		table.init();

		HandEvaluator evaluator = new CombinationValidator();

		SmartAI ai = new SmartAI(evaluator);

		Player player = new AIPlayer("CPU", ai);

		Card card = ai.drawCard(player, table);

		assertNotNull(card);
	}
}
