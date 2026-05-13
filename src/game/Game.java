package game;

import static utils.Colors.*;

import java.util.*;

import gameConfig.GameConfig;
import playerUsers.Player;

public class Game {
	private List<Player> players;

	public Game(List<Player> players) {
		this.players = players;

	}

	public void start() {

		boolean gameFinished = false;
		int numRound = 1;
		do {
			Round round = new Round(players, GameConfig.getInstance()
					.getNumDecks(), numRound);
			round.play();
			gameFinished = checkMaxPoints();
			numRound++;
		} while (!gameFinished);
		showScores();
		showRanking();

	}

	private void showScores() {

		System.out.printf("\n%s📊%s Puntuaciones actuales:\n", PURPLE, RESET);

		for (Player p : players) {
			System.out.printf("%s: %d puntos.\n", p.getName(), p.getPoints());
		}
	}

	private boolean checkMaxPoints() {
		boolean option = false;
		for (Player p : players) {
			if (p.getPoints() >= GameConfig.getInstance()
					.getMaxPoints()) {
				System.out.printf("\n%s⚠️%s Se alcanzó la puntuación máxima\n", RED, RESET);
				option = true;
			}
		}
		return option;
	}

	private void showRanking() {

		System.out.printf("\n%s🏆%s RANKING FINAL\n", YELLOW, RESET);

		List<Player> ranking = players.stream()
				.sorted(Comparator.comparingInt(Player::getPoints)) // menor a mayor
				.toList();

		for (int i = 0; i < ranking.size(); i++) {

			Player p = ranking.get(i);

			System.out.printf("%s%dº%s - %s (%d puntos)\n", YELLOW, (i + 1), RESET, p.getName(), p.getPoints());
		}

		// 🔥 ganador
		Player winner = ranking.get(0);
		System.out.printf("\n%s🥇 Ganador%s %s🔥🔥🔥 %s 🔥🔥🔥%s", YELLOW, RESET, RED, winner.getName(), RESET);
	}
}
