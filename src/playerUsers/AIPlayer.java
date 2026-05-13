package playerUsers;

import ai.AIStrategy;
import game.Table;
import resources.Card;

public class AIPlayer extends Player {
	private AIStrategy ai;

	public AIPlayer(String name, AIStrategy ai) {
		super(name);
		this.ai = ai;
	}

	public Card playDraw(Table table) {
		return ai.drawCard(this, table);
	}

	@Override
	public Card chooseDiscard(Table table) {
		return ai.chooseDiscard(this);
	}

	@Override
	public boolean wantsToClose(boolean canClose) {
		return ai.wantsToClose(this, canClose);
	}

}