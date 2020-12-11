package game;

import java.awt.Point;
import java.util.ArrayList;

public class Board {
	
	/**
	 * 
	 * @return initial board
	 */
	public int[][] getNewBoard() {
		
        int[][] board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	board[i][j] = 0;
            }
        }
        
        board[3][3] = 2; // -1 is white
        board[3][4] = 1;  // 1 is black
        board[4][3] = 1;
        board[4][4] = 2;
        
        return board;
    }
	
	/**
	 * 
	 * @param board
	 * @param player
	 * @return the disc number of the player 
	 */
	public int getPlayerDiscNum(int[][] board, int player) {
        int num = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == player) num++;
            }
        }
        return num;
    }
	
	/**
	 * 
	 * @param board
	 * @return the total disc number on board
	 */
	public int getTotalDiscNum(int[][] board) {
        int num = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] != 0) num++;
            }
        }
        return num;
    }
	
	/**
	 * This method is used to count stable discs
	 * @return the number of stable discs (
	 */
	public int getStableDiscNum(int[][] board, int player) {
		
		ArrayList<Point> stableDiscs = new ArrayList<>();
		
		if (board[0][0] == player) {
			stableDiscs.add(new Point(0, 0));
			
			int right = 1;
			int down = 1;
			
			while (right < 7 && board[0][right] == player) {
				stableDiscs.add(new Point(0, right));
				right++;
			}
			
			while (down < 7 && board[down][0] == player) {
				stableDiscs.add(new Point(down, 0));
				down++;
			}
			
			if (board[1][0] == player) {
				for (int i = 1; i <= right-2; i++) {
					if (board[1][i] == player) {
						stableDiscs.add(new Point(1, i));
					}
				}
			}
			
			if (board[0][1] == player) {
				for (int i = 1; i <= down-2; i++) {
					if (board[i][1] == player) {
						stableDiscs.add(new Point(i, 1));
					}
				}
			}	
		}
		
		
		if (board[0][7] == player) {
			ArrayList<Point> list1 = new ArrayList<>();
			
			list1.add(new Point(0, 7));
			
			int left = 6;
			int down = 1;
			
			while (left > 0 && board[0][left] == player) {
				list1.add(new Point(0, left));
				left--;
			}
			
			while (down < 7 && board[down][7] == player) {
				list1.add(new Point(down, 7));
				down++;
			}
			
			if (board[1][7] == player) {
				for (int i = 6; i >= left+2; i--) {
					if (board[1][i] == player) {
						list1.add(new Point(1, i));
					}
				}
			}
			
			if (board[0][6] == player) {
				for (int i = 1; i <= down-2; i++) {
					if (board[i][6] == player) {
						list1.add(new Point(i, 6));
					}
				}
			}
			
			for (Point p: list1) {
				if (!stableDiscs.contains(p)) {
					stableDiscs.add(p);
				}
			}
		}
		
		// point(7,0)
		
		if (board[7][0] == player) {
			ArrayList<Point> list1 = new ArrayList<>();
			
			list1.add(new Point(7, 0));
			
			int right = 1;
			int up = 6;
			
			while (right < 7 && board[7][right] == player) {
				list1.add(new Point(7, right));
				right++;
			}
			
			while (up > 0 && board[up][0] == player) {
				list1.add(new Point(up, 0));
				up--;
			}
			
			if (board[6][0] == player) {
				for (int i = 1; i <= right-2; i++) {
					if (board[6][i] == player) {
						list1.add(new Point(6, i));
					}
				}
			}
			
			if (board[7][1] == player) {
				for (int i = 6; i >= up+2; i--) {
					if (board[i][1] == player) {
						list1.add(new Point(i, 1));
					}
				}
			}
			
			for (Point p: list1) {
				if (!stableDiscs.contains(p)) {
					stableDiscs.add(p);
				}
			}
		}
		
		if (board[7][7] == player) {
			ArrayList<Point> list1 = new ArrayList<>();
			
			list1.add(new Point(7, 7));
			
			int left = 6;
			int up = 6;
			
			while (left > 0 && board[7][left] == player) {
				list1.add(new Point(7, left));
				left--;
			}
			
			while (up > 0 && board[up][7] == player) {
				list1.add(new Point(up, 7));
				up--;
			}
			
			if (board[6][7] == player) {
				for (int i = 6; i >= left+2; i--) {
					if (board[6][i] == player) {
						list1.add(new Point(6, i));
					}
				}
			}
			
			if (board[7][6] == player) {
				for (int i = 6; i >= up+2; i--) {
					if (board[i][6] == player) {
						list1.add(new Point(i, 6));
					}
				}
			}
			
			for (Point p: list1) {
				if (!stableDiscs.contains(p)) {
					stableDiscs.add(p);
				}
			}
		}
		
		return stableDiscs.size();
	}

}
