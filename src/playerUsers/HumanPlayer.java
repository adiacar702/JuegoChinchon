package playerUsers;

import java.util.Scanner;

import game.Table;
import resources.Card;
import utils.ConsoleInput;

public class HumanPlayer extends Player {
	Scanner sc = new Scanner(System.in);
	ConsoleInput ci = new ConsoleInput(sc);

	public HumanPlayer(String name) {
		super(name);
	}

	@Override
	public Card chooseDiscard(Table table) {
		int choice;
		System.out.println("Elige una a descartar (1-8):");

		for (int i = 0; i < hand.size(); i++) {
			System.out.printf("%d ➤[%s]  ", i + 1, hand.getCard(i));
		}
		choice = ci.readIntInRange(1, hand.size());
		return hand.getCard(choice - 1);
	}

	@Override
	public boolean wantsToClose(boolean canClose) {
		System.out.println("¿Cerrar ronda? (s/n)");
		boolean option = ci.readBooleanUsingChar('s', 'n');
		if (option && !canClose) {
			System.out.println("❌ No puedes cerrar.");
			option = false;
		}
		return option;
	}

}
