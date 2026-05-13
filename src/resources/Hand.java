package resources;

import java.util.*;

/**
 * Representa la mano de un jugador. Se encarga de almacenar cartas.
 */
public class Hand {
	private final List<Card> CARDS = new ArrayList<>();

	/**
	 * Sirve para indicar una carta en la mano segun indice.
	 * 
	 * @param c indice donde se guarda la carta
	 * @return carta elegida con el indice
	 */
	public Card getCard(int c) {
		return CARDS.get(c);
	}

	/**
	 * Agrega una carta a la mano.
	 * 
	 * @param card Carta recibida
	 */

	public void addCard(Card card) {
		CARDS.add(card);
	}

	/**
	 * Elimina una carta de la mano.
	 * 
	 * @param card carta a eliminar
	 */
	public void removeCard(Card card) {
		CARDS.remove(card);
	}

	public List<Card> getCards() {
		return CARDS;
	}

	/**
	 * Limpia completamente la mano.
	 */
	public void clear() {
		CARDS.clear();
	}

	/**
	 * Número de cartas en la mano.
	 * 
	 * @return valor entero del tamaño de la mano de cartas
	 */
	public int size() {
		return CARDS.size();
	}
}
