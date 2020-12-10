package evaluators;


public class FirstEvaluator extends Heuristics implements Evaluator {
	
	@Override
	public double evaluate(int[][] board, int player) {
//		if(BoardHelper.isGameFinished(board)){
//            return 1000*evalDiscDiff(board, player);
//        }
		
		switch (getGamePhase(board)) {
			case END:
				return evalDiscParity(board, player);
			default:
				return evalMobility(board, player);
		}
	}
	
	private Phase getGamePhase(int[][] board) {
//        int sc = BoardHelper.getTotalStoneCount(board);
//        if(sc<20) return GamePhase.EARLY_GAME;
//        else if(sc<=58) return GamePhase.MID_GAME;
//        else return GamePhase.LATE_GAME;		
		
		int numDisc = 0;
		if (numDisc <= 20) {
			return Phase.BEGIN;
		} else if (numDisc <= 50) {
			return Phase.MIDDLE;
		} else {
			return Phase.END;
		}
    }
}
