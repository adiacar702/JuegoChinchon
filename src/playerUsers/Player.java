package playerUsers;

import java.util.List;

import game.Table;
import resources.*;

/**
 * Representa a un jugador de los que jugarán la partida
 */
public abstract class Player {

	protected String name;
	protected Hand hand = new Hand();
	protected int points = 0;
	protected boolean eliminated = false;

	/**
	 * Constructor del Jugador con el nombre indicado
	 * 
	 * @param name Nombre de la persona que quiere jugar o IA
	 * 
	 */
	public Player(String name) {
		this.name = name;
	}

	public void addCard(Card c) {
		hand.addCard(c);
	}

	public void removeCard(Card c) {
		hand.removeCard(c);
	}

	public List<Card> getHand() {
		return hand.getCards();
	}

	public void clearHand() {
		hand.clear();
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int pts) {
		this.points += pts;
	}

	public boolean isEliminated() {
		return eliminated;
	}

	public void eliminate() {
		eliminated = true;
	}

	public abstract Card chooseDiscard(Table table);

	public abstract boolean wantsToClose(boolean canClose);

	@Override
	public String toString() {
		return String.format("%s\n", name);
	}

	public void setEliminated(boolean eliminated) {
		this.eliminated = eliminated;
	}
}
