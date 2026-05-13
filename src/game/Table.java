package game;

import java.util.*;

import resources.*;

public class Table {

	private Deck deck;
	private List<Card> discard = new ArrayList<>();
	private boolean firstTurn = true;

	public Table(int numDecks) {
		deck = new Deck(numDecks);
		this.discard = new ArrayList<>();
	}

	public void init() {
		deck.shuffle();
		discard.clear();

		discard.add(deck.draw());
	}

	public Deck getDeck() {
		return deck;
	}

	public List<Card> getDiscard() {
		return discard;
	}

	public Card drawDeck() {
		if (deck.size() == 0) {
			refillDeckFromDiscard();
		}
		return deck.draw();
	}

	public Card drawDiscard() {
		if (discard.isEmpty()) {
			throw new RuntimeException("No hay cartas en la zona de descarte");
		}
		return discard.remove(discard.size() - 1); // última carta
	}

	public Card peekDiscard() {
		return discard.get(discard.size() - 1); // ver sin eliminar
	}

	public void discard(Card c) {
		discard.add(c);
	}

	public boolean isFirstTurn() {
		return firstTurn;
	}

	public void endFirstTurn() {
		firstTurn = false;
	}

	// metodo para reconstruir la baraja a partir del descarte
	private void refillDeckFromDiscard() {

		if (discard.size() <= 1) {
			throw new RuntimeException("No hay suficientes cartas para reconstruir el deck");
		}

		System.out.println("\n🔄 Reconstruyendo deck...");

		//Guardar última carta (visible)
		Card topCard = discard.remove(discard.size() - 1);

		//Crear nuevo deck con el resto
		List<Card> newCards = new ArrayList<>(discard);

		//Limpiar discard y dejar solo la visible

		discard.clear();
		discard.add(topCard);

		//Reemplazar cartas del deck
		deck.setCards(newCards);
		deck.shuffle();
	}
}
