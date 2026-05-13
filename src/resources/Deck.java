package resources;

import static utils.Colors.*;

import java.util.*;

/**
 * Representa una baraja de cartas con palos y valores
 */
public class Deck {
	/** Lista de cartas que formarán la baraja */
	private List<Card> cards;

	/**
	 * Construye la lista de cartas a partir de los palos y valores
	 */
	public Deck(int numDecks) {
		String blade = String.format("%s⚔️%s", CYAN, RESET);
		String coarse = String.format("%s🏏%s", GREEN, RESET);
		String gold = String.format("%s🟡%s", YELLOW, RESET);
		String cup = String.format("%s🍷%s", PURPLE, RESET);
		String[] suits = { blade, coarse, gold, cup };
		String[] values = { "1", "2", "3", "4", "5", "6", "7", "♙", "♞", "👑" };

		cards = new ArrayList<>();
		for (int i = 0; i < numDecks; i++) {
			for (String suit : suits) {
				for (String value : values) {
					cards.add(new Card(suit, value));
				}
			}
		}
		shuffle();
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	/**
	 * Barajar deck
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}

	/**
	 * Si la bajara no está vacia, devuelve una carta
	 * 
	 * @return carta del deck
	 */
	public Card draw() {
		if (cards.isEmpty()) {
			throw new RuntimeException("Deck vacío");
		}
		return cards.remove(0);
	}

	/**
	 * Indica el tamaño de la baraja
	 * 
	 * @return valor entero del tamaño
	 */
	public int size() {
		return cards.size();
	}

}