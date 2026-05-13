package resources;

import java.util.Objects;

/**
 * Representa una carta de la baraja. Contiene palo y valor.
 */
public class Card {
	private String suit;
	private String value;

	/**
	 * Constructor de carta.
	 * 
	 * @param suit  Palo de la carta
	 * @param value Valor de la carta
	 */
	public Card(String suit, String value) {
		this.suit = suit;
		this.value = value;
	}

	/**
	 * Devuelve el valor numérico base de la carta.
	 * 
	 * @return puntos de la carta
	 */
	public int getNumericValue() {
		switch (value) {
		case "♙":
			return 10;
		case "♞":
			return 11;
		case "👑":
			return 12;
		default:
			return Integer.parseInt(value);
		}
	}

	public String getSuit() {
		return suit;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Card))
			return false;

		Card card = (Card) o;

		return suit.equals(card.suit) && value.equals(card.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(suit, value);
	}

	@Override
	public String toString() {
		return String.format("%s%s", value, suit);
	}
}
