package rules;

import java.util.*;

import resources.Card;

public class Combinations {

	public boolean isChinchon(List<Card> hand) {

		if (hand.size() != 7)
			return false;

		List<Card> sorted = sortByValue(hand);
		String suit = sorted.get(0)
				.getSuit();

		for (Card c : sorted) {
			if (!c.getSuit()
					.equals(suit))
				return false;
		}

		return isConsecutive(sorted);
	}

	public List<List<Card>> findTrios(List<Card> original) {
		List<Card> cards = new ArrayList<>(original); // 🔥 copia
		List<List<Card>> trios = new ArrayList<>();

		Map<String, List<Card>> byValue = groupByValue(cards);

		for (List<Card> group : byValue.values()) {

			// evitar duplicados por palo
			Map<String, Card> uniqueBySuit = new HashMap<>();

			for (Card c : group) {
				uniqueBySuit.putIfAbsent(c.getSuit(), c);
			}

			List<Card> unique = new ArrayList<>(uniqueBySuit.values());

			if (unique.size() >= 3) {
				trios.add(unique);
			}
		}

		return trios;
	}

	public List<List<Card>> findEscaleras(List<Card> original) {

		List<Card> cards = new ArrayList<>(original);
		List<List<Card>> escalas = new ArrayList<>();
		Map<String, List<Card>> bySuit = groupBySuit(cards);
		Card last;
		for (List<Card> suitCards : bySuit.values()) {

			List<Card> sorted = sortByValue(suitCards);
			List<Card> sequence = new ArrayList<>();

			for (Card c : sorted) {

				if (sequence.isEmpty()) {
					sequence.add(c);
					continue;
				} else {

					last = sequence.get(sequence.size() - 1);

					if (c.getNumericValue() == last.getNumericValue() + 1) {
						sequence.add(c);
					} else {

						if (sequence.size() >= 3) {
							escalas.add(new ArrayList<>(sequence));

						}

						sequence.clear();
						sequence.add(c);
					}
				}
			}

			if (sequence.size() >= 3) {
				escalas.add(new ArrayList<>(sequence));
			}
		}

		return escalas;
	}

	// 🔧 helpers
	private Map<String, List<Card>> groupByValue(List<Card> cards) {
		Map<String, List<Card>> map = new HashMap<>();
		for (Card c : cards) {
			map.computeIfAbsent(c.getValue(), k -> new ArrayList<>())
					.add(c);
		}
		return map;
	}

	private Map<String, List<Card>> groupBySuit(List<Card> cards) {
		Map<String, List<Card>> map = new HashMap<>();
		for (Card c : cards) {
			map.computeIfAbsent(c.getSuit(), k -> new ArrayList<>())
					.add(c);
		}
		return map;
	}

	private List<Card> sortByValue(List<Card> cards) {
		return cards.stream()
				.sorted(Comparator.comparingInt(Card::getNumericValue))
				.toList();
	}

	private boolean isConsecutive(List<Card> cards) {
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i + 1)
					.getNumericValue() != cards.get(i)
							.getNumericValue() + 1) {
				return false;
			}
		}
		return true;
	}

}
