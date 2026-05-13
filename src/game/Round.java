package game;

import java.util.*;

import playerUsers.*;
import resources.Card;
import rules.CombinationValidator;
import utils.*;

/**
 * Ejecuta una ronda completa del juego. Controla turnos, robos, descartes y
 * cierre de ronda.
 */
public class Round {
	Scanner sc = new Scanner(System.in);
	ConsoleInput ci = new ConsoleInput(sc);
	private CombinationValidator validator = new CombinationValidator();
	private List<Player> players;
	private Table table;
	private final int NUMROUND;

	public Round(List<Player> players, int decks, int numRound) {
		this.players = players;
		this.table = new Table(decks);
		this.NUMROUND = numRound;
	}

	public void play() {

		table.init();

		// 🔹 Reparto inicial
		for (Player p : players) {
			p.clearHand();
			for (int i = 0; i < 7; i++) {
				p.addCard(table.drawDeck());
			}
		}

		boolean finished;
		Card drawn;
		Card discard;
		int choice;

		System.out.println("================");
		System.out.printf("=  🔄 RONDA %d  =\n", NUMROUND);
		System.out.println("================");

		do {
			finished = false;

			for (Player p : players) {

				if (p.isEliminated() || finished) {
					continue;
				}

				boolean close = false; //  reset por jugador

				System.out.printf("\nZona descarte: %s\n", table.peekDiscard());

				// robo de carta de deck/discard
				if (p instanceof HumanPlayer) {

					System.out.printf("\nTurno de %s:\n", p.getName());
					showHandIndexed(p);

					System.out.println("\nRobar carta:");
					System.out.println("1. Deck");
					System.out.println("2. Descarte");

					choice = ci.readInt();
					drawn = (choice == 2) ? table.drawDiscard() : table.drawDeck();

				} else {
					System.out.printf("\nTurno de %s: ", p.getName());
					drawn = ((AIPlayer) p).playDraw(table);
				}

				// añadir carta elegida a la mano del jugador
				p.addCard(drawn);

				// descarte durante el turno del jugador
				discard = p.chooseDiscard(table);
				p.removeCard(discard);
				table.discard(discard);

				//metodo extra para comprobaciones de errores
				GameDebugger.validateState(table, players);

				// intento de cerrar ronda de jugador humano/ia
				if (!table.isFirstTurn()) {

					close = p.wantsToClose(validator.canClose(p.getHand()));

				}

				//  CIERRE
				if (close) {
					System.out.printf("\n⚠️ Ronda cerrada por %s\n", p.getName());
					calculatePoints();
					finished = true;

				}
			}

			if (!finished) {
				table.endFirstTurn();
			}

		} while (!finished);

		System.out.println("\n🏁 Fin de la ronda");

	}

	//Metodo para calcular los puntos según sus combinaciones
	private void calculatePoints() {
		CombinationResult result;
		int score, validCards;

		for (Player p : players) {
			result = validator.analyzeHand(p.getHand());

			if (result.isChinchon()) {
				System.out.printf("🔥 CHINCHÓN de %s\n", p.getName());
				System.out.println("🏆 GANA LA PARTIDA AUTOMÁTICAMENTE");

			} else {
				score = validator.calculateNoValidNums(p.getHand());
				validCards = p.getHand()
						.size()
						- result.getRemaining()
								.size();

				//  7 cartas combinadas

				if (validCards == 7) {
					score -= 10;
					System.out.println("✨ BONUS -10 puntos por 7 cartas combinadas");
				}
				p.addPoints(score);

				System.out.printf("👤 %s: \n", p.getName());

				if (!result.getEscalas()
						.isEmpty()) {
					System.out.printf("Escaleras: %s\n", result.getEscalas());
				}

				if (!result.getTrios()
						.isEmpty()) {
					System.out.printf("Tríos: %s\n", result.getTrios());
				}

				System.out.printf("Cartas que puntúan: %s\n", result.getRemaining());
				System.out.printf("Puntos ronda: %d\n", score);
				System.out.printf("Puntos totales: %d\n", p.getPoints());
				System.out.println("----------------------------");
			}
		}
	}

	/**
	 * Metodo para mostrar al usuario su mano para poder jugar
	 * 
	 * @param p jugador al que mostrar su mano
	 */
	private void showHandIndexed(Player p) {
		List<Card> hand = p.getHand();
		hand.sort(Comparator.comparingInt(Card::getNumericValue)
				.thenComparing(Card::getSuit));

		for (int i = 0; i < hand.size(); i++) {
			if (i == (hand.size() / 2) + 1) {
				System.out.println();
			}
			System.out.printf("[%s]", hand.get(i));
		}
	}
}
