package ai;

import game.Table;
import playerUsers.Player;
import resources.Card;

/**
 * Interfaz para que definir una estrategia dependiendo de la ia elegida
 */
public interface AIStrategy {

	Card drawCard(Player ai, Table table);

	Card chooseDiscard(Player ai);

	boolean wantsToClose(Player ai, boolean canClose);
}
