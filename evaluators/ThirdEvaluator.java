package evaluators;

public class ThirdEvaluator extends Heuristics implements Evaluator {

	@Override
	public double evaluate(int[][] board, int player) {
//		if(BoardHelper.isGameFinished(board)){
//            return 1000*evalDiscDiff(board, player);
//        }

        //semi-terminal
//        switch (getGamePhase(board)){
//            case BEGIN:
//                return 1000*evalCorner(board,player) + 50*evalMobility(board,player);
//            case MIDLE:
//                return 1000*evalCorner(board,player) + 20*evalMobility(board,player) + 10*evalDiscDiff(board, player) + 100*evalParity(board);
//            default:
//                return 1000*evalCorner(board,player) + 100*evalMobility(board,player) + 500*evalDiscDiff(board, player) + 500*evalParity(board);
//        }
		
		if (gameManager.isGameFinished(board)) {
			return evalDiscParity(board, player);
		}
		
		
		return 0;
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
