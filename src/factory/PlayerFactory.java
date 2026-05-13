package factory;

import ai.*;
import playerUsers.*;
import rules.CombinationValidator;

public class PlayerFactory {

	/**
	 * Crea jugadores según tipo. Patron de diseño factory.
	 */
	public static Player createPlayer(int type, String name) {

		return switch (type) {

		case 1 -> new HumanPlayer(name);

		case 2 -> new AIPlayer(name, new RandomAI());
		case 3 -> new AIPlayer(name, new SmartAI(new CombinationValidator()));

		default -> throw new IllegalArgumentException("Tipo inválido");
		};
	}
}
