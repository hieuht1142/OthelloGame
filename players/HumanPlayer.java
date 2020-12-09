package players;

import java.awt.Point;

public class HumanPlayer extends Player {
	
	public HumanPlayer(int playerNumber) {
		super(playerNumber);
	}

	@Override
	public boolean isHumanPlayer() {
		return true;
	}

	@Override
	public Point play(int[][] board) {
		return null;
	}

}
