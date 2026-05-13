package gameConfig;

/**
 * Configuración global del juego. Patron de diseño Singleton.
 */
public class GameConfig {

	private static GameConfig instance;

	private int maxPoints;
	private int numDecks;

	private GameConfig() {
	}

	public static GameConfig getInstance() {
		if (instance == null) {
			instance = new GameConfig();
		}
		return instance;
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public int getNumDecks() {
		return numDecks;
	}

	public void setNumDecks(int numDecks) {
		this.numDecks = numDecks;
	}
}
