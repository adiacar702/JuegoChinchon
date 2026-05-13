package app;

import static utils.Colors.*;

import java.util.*;

import factory.PlayerFactory;
import game.Game;
import gameConfig.GameConfig;
import playerUsers.Player;
import utils.ConsoleInput;

public class Main {
	public void game() {
		Scanner sc = new Scanner(System.in);
		ConsoleInput ci = new ConsoleInput(sc);
		int type;
		String name;
		int numPlayers, decks, maxPoints;
		System.out.printf("%s%s🃏%s%s ¡Bienvenido al juego del Chinchón!\n", BLACK, WHITE_BACKGROUND, RESET, RESET);

		do {
			System.out.println("Elija cuantos Decks se usarán en la partida (1/2):");
			decks = ci.readInt();
		} while (decks < 1 || decks > 2);
		do {
			System.out.println("Indique puntuación maxima (50, 100, 200 ...)");
			maxPoints = ci.readInt();
		} while (maxPoints < 50);
		GameConfig.getInstance()
				.setNumDecks(decks);
		GameConfig.getInstance()
				.setMaxPoints(maxPoints);

		do {
			System.out.println("Elija la cantidad de jugadores (2-5):");
			numPlayers = ci.readInt();
		} while (numPlayers < 2 || numPlayers > 5);
		List<Player> players = new ArrayList<>(numPlayers);

		for (int i = 0; i < numPlayers; i++) {
			System.out.println("Indique tipo de jugador:\n1. humano\n2. IA Aleatoria\n3. IA Inteligente");
			type = ci.readInt();

			if (type == 1) {
				System.out.print("Nombre: ");
				name = ci.readString();
				players.add(PlayerFactory.createPlayer(type, name));
			} else {
				players.add(PlayerFactory.createPlayer(type, "COM." + (i + 1)));
			}
		}

		Game game = new Game(players);
		game.start();
	}

	public static void main(String[] args) {
		new Main().game();
	}
}