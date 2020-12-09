package players;

import java.awt.Point;

public abstract class Player {
	
	protected int playerNumber; // 1 or 2	
	
	public Player() {
		
	}
	
	public Player(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	abstract public boolean isHumanPlayer();
	
	abstract public Point play(int[][] board);
	
	public int getPlayerNumber() {
		return playerNumber;
	}
}
