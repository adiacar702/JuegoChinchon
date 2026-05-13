package ai;

import java.util.*;

import game.Table;
import playerUsers.Player;
import resources.Card;
import rules.HandEvaluator;

/*
 * Clase de ia con una mentalidad algo mas inteligente
 */
public class SmartAI implements AIStrategy {
	private HandEvaluator validator;
	private Random random = new Random();

	public SmartAI(HandEvaluator validator) {
		this.validator = validator;
	}

	@Override
	public Card drawCard(Player ai, Table table) {

		//a la hora de coger carta primero mira la que está en discard
		Card topDiscard = table.peekDiscard();
		Card option;
		/**
		 * Proceso para determinar calculando cual es mejor combinación:
		 * 
		 * topDiscard + mano o deck + mano
		 */

		//copia de la mano para no modificar la original hasta decidir
		List<Card> test = new ArrayList<>(ai.getHand());
		test.add(topDiscard);

		int withDiscard = validator.calculateNoValidNums(test);
		int current = validator.calculateNoValidNums(ai.getHand());

		System.out.printf("%s está pensando...", ai.getName());

		if (withDiscard < current) {
			System.out.printf("\n%s roba del descarte: %s", ai.getName(), topDiscard);
			option = table.drawDiscard();
		} else {
			System.out.printf("\n%s roba del deck", ai.getName());
			option = table.drawDeck();
		}
		return option;
	}

	@Override
	public Card chooseDiscard(Player ai) {
		Card bestCard = null;
		int minInvalid = Integer.MAX_VALUE;

		//calcula carta por carta que combinación en su mano tiene menos puntos
		for (Card c : ai.getHand()) {

			//copia de la mano para comprobar todas las combinaciones de cartas
			List<Card> temp = new ArrayList<>(ai.getHand());
			temp.remove(c);

			int invalids = validator.calculateNoValidNums(temp);

			if (invalids < minInvalid) {
				minInvalid = invalids;
				bestCard = c;
			}
		}

		System.out.printf("\n%s descarta: %s", ai.getName(), bestCard);

		return bestCard;
	}

	@Override
	public boolean wantsToClose(Player ai, boolean canClose) {

		boolean decision;
		if (!canClose) {
			decision = false;
		} else {
			decision = random.nextInt(2) == 0;

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
