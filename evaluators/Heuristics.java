package evaluators;


public class Heuristics {

	public double evalDiscParity(int[][] board , int player) {
		int oplayer = (player == 1) ? -1 : 1;
		
		int myDisc = 0;
		int opDisc = 0;
		
		double eval = 100 * (myDisc - opDisc) / (myDisc + opDisc);

        return eval;
	}
	
	public double evalMobility(int[][] board , int player) {
		int oplayer = (player == 1) ? -1 : 1;

//        int myMoveCount = BoardHelper.getAllPossibleMoves(board,player).size();
//        int opMoveCount = BoardHelper.getAllPossibleMoves(board,oplayer).size();
		
		int myValue = 0;
		int opValue = 0;
		
		if (myValue + opValue != 0) {
			return 100.0 * (myValue - myValue) / (myValue + myValue);
		} else {
			return 0;
		}
	}
	
	public double evalCornerCaptured(int[][] board , int player) {
		int oplayer = (player == 1) ? -1 : 1;
		
		int myCorners = 0;
		int opCorners = 0;
		
		if(board[0][0] == player) myCorners++;
        if(board[7][0] == player) myCorners++;
        if(board[0][7] == player) myCorners++;
        if(board[7][7] == player) myCorners++;
        
        if(board[0][0] == oplayer) opCorners++;
        if(board[7][0] == oplayer) opCorners++;
        if(board[0][7] == oplayer) opCorners++;
        if(board[7][7] == oplayer) opCorners++;
        
        if (myCorners + opCorners != 0) {
        	return 100.0 * (myCorners - opCorners) / (myCorners + opCorners);
        } else {
        	return 0;
        }
	}
	
//	public double evalStability() {
//		return 0;
//	}
	
	public double evalStaticWeights(int[][] board , int player) {
		int[][] w = {
						{100, -20, 10,  5,  5, 10, -20, 100},
						{-20, -50, -2, -2, -2, -2, -50, -20},
						{ 10,  -2, -1, -1, -1, -1,  -2,  10},
						{  5,  -2, -1, -1, -1, -1,  -2,   5},
						{  5,  -2, -1, -1, -1, -1,  -2,   5},
						{ 10,  -2, -1, -1, -1, -1,  -2,  10},
						{-20, -50, -2, -2, -2, -2, -50, -20},
						{100, -20, 10,  5,  5, 10, -20, 100}
		};
		
		int oplayer = (player == 1) ? -1 : 1;
		
		int myWeight = 0;
		int opWeight = 0;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == player) {
					myWeight += board[i][j];
				}
				
				if (board[i][j] == oplayer) {
					opWeight += board[i][j];
				}
			}
		}
		
		return myWeight - opWeight;
	}	
	
}
