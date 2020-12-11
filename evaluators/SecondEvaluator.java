package evaluators;

public class SecondEvaluator extends Heuristics implements Evaluator {

	@Override
	public double evaluate(int[][] board, int player) {
		if (gameManager.isGameFinished(board)) {
			return evalDiscParity(board, player);
		}
	
		switch (getGamePhase(board)) {
			case END:
				return evalDiscParity(board, player);
			default:
				return evalStaticWeights(board, player);
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
