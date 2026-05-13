package ai;

import java.util.*;

import game.Table;
import playerUsers.Player;
import resources.Card;

/**
 * Clase de ia con mentalidad random
 */
public class RandomAI implements AIStrategy {
	private Random random = new Random();

	/**
	 * Decisión de la ia random de como robar carta y de donde
	 * 
	 * @param ai    jugador ia especificado
	 * @param table mesa donde robará la carta
	 *
	 * @return carta seleccionada
	 */
	@Override
	public Card drawCard(Player ai, Table table) {

		System.out.printf("%s está pensando...", ai.getName());
		//		waitTime(1000);//juego real
		waitTime(100);//pruebas
		Card drawn;

		if (random.nextDouble() < 0.3) {
			drawn = table.drawDiscard();
			System.out.printf("\n%s roba del descarte: %s", ai.getName(), drawn);
		} else {
			drawn = table.drawDeck();
			System.out.printf("\n%s roba del deck", ai.getName());
		}
		waitTime(1000);
		//		waitTime(2000);
		return drawn;
	}

	/**
	 * Decisión de la ia random de como descarta carta de su mano
	 * 
	 * @param ai jugador ia especificado
	 *
	 * @return carta seleccionada
	 */
	@Override
	public Card chooseDiscard(Player ai) {

		List<Card> hand = ai.getHand();

		Card discard = hand.get(random.nextInt(hand.size()));

		System.out.printf("\n%s descarta: %s", ai.getName(), discard);
		waitTime(100);
		//		waitTime(2000);
		return discard;
	}

	/**
	 * Decide si cerrar la ronda
	 */
	@Override
	public boolean wantsToClose(Player ai, boolean canClose) {
		boolean decision;
		if (!canClose) {
			decision = false;
		} else {
			decision = random.nextInt(8) == 0;

			if (decision) {
				System.out.printf("\n%s decide cerrar la ronda...", ai.getName());
				//			waitTime(2000);
				waitTime(200);
			}
		}

		return decision;
	}

	private void waitTime(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread()
					.interrupt();
		}
	}
}
