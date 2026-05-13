package utils;

import java.util.*;

import game.Table;
import playerUsers.Player;
import resources.Card;

public class GameDebugger {

	public static void validateState(Table table, List<Player> players) {

		Map<Card, Integer> counter = new HashMap<>();

		//codigo usado para verificar si pierdo cartas cuando hago refillFromDiscard
		//		int total = 0;
		//
		//		total += table.getDeck()
		//				.size();
		//		total += table.getDiscard()
		//				.size();
		//
		//		for (Player p : players) {
		//			total += p.getHand()
		//					.size();
		//		}
		//
		//		System.out.println("TOTAL CARTAS: " + total);
		//  Deck
		for (Card c : table.getDeck()
				.getCards()) {
			counter.put(c, counter.getOrDefault(c, 0) + 1);
		}

		//  Discard
		for (Card c : table.getDiscard()) {
			counter.put(c, counter.getOrDefault(c, 0) + 1);
		}

		//  Hands
		for (Player p : players) {
			for (Card c : p.getHand()) {
				counter.put(c, counter.getOrDefault(c, 0) + 1);
			}
		}

		//  Buscar errores
		boolean error = false;

		for (Map.Entry<Card, Integer> entry : counter.entrySet()) {

			if (entry.getValue() > 1) {
				error = true;
				System.out.println("❌ DUPLICADA: " + entry.getKey() + " aparece " + entry.getValue() + " veces");
			}
		}

		if (!error) {
			System.out.print("\n✅");//Estado correcto: no hay duplicados
		}
	}
}
