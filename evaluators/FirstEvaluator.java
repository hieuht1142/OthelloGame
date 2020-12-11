package evaluators;

public class FirstEvaluator extends Heuristics implements Evaluator {
	
	@Override
	public double evaluate(int[][] board, int player) {
		if (gameManager.isGameFinished(board)) {
			return evalDiscParity(board, player);
		}
		
		switch (getGamePhase(board)) {
			case END:
				return evalDiscParity(board, player);
			default:
				return evalMobility(board, player);
		}
	}
	
	private Phase getGamePhase(int[][] board) {			

		int numDisc = boardManager.getTotalDiscNum(board);
		if (numDisc <= 20) {
			return Phase.BEGIN;
		} else if (numDisc <= 50) {
			return Phase.MIDDLE;
		} else {
			return Phase.END;
		}
    }
}
