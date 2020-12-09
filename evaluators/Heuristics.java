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
		return 0;
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
	
	public double evalStability() {
		return 0;
	}
	
	public double evalStaticWeights(int[][] board , int player) {
		return 0;
	}	
	
}
