import java.awt.Color;

/**
 * This class is the blueprint for a Queen.
 * 
 * @author Cindy Li
 * @since Wednesday, July 12, 2017
 *
 */
public class Queen extends Piece {
	
	// constructor
	public Queen(Player p) {
		super(p);
		
		// set the queen's icon
		if (p.getColor() == Color.WHITE)
			setIcon(ChessGame.WHITE_PIECES[4]);
		
		else if (p.getColor() == Color.BLACK)
			setIcon(ChessGame.BLACK_PIECES[4]);
	}
	
	
	@Override
	public String typeOfMove(ChessGame game, Piece[][] board, int x1, int y1, int x2, int y2) {
		
		// check if move is diagonal
		if (Math.max(x1, x2) - Math.min(x1, x2) == Math.max(y1, y2) - Math.min(y1, y2)) {
					
			// check if there are pieces in the way
			for (int i = 1; i < Math.max(x1, x2) - Math.min(x1, x2); i++) {
						
				// going bottom left to top right
				if (x1 > x2 && y1 < y2 && board[x1 - i][y1 + i] != null)
					return ChessGame.INVALID;
						
				// going bottom right to top left
				else if (x1 > x2 && y1 > y2 && board[x1 - i][y1 - i] != null)
					return ChessGame.INVALID;
						
				// going top left to bottom right
				else if (x1 < x2 && y1 < y2 && board[x1 + i][y1 + i] != null)
					return ChessGame.INVALID;
						
				// going top right to bottom left
				else if (x1 < x2 && y1 > y2 && board[x1 + i][y1 - i] != null)
					return ChessGame.INVALID;
			}
					
			// check for a move
			if (board[x2][y2] == null) {
				
				return ChessGame.MOVE;
			}
								
			// else check for a capture
			else if (board[x2][y2].getOwner().getId() != board[x1][y1].getOwner().getId()) {
				
				return ChessGame.CAPTURE;
			}
		}
		
		// else check if move is in a horizontal line
		else if (x1 == x2) {
			
			// check if there are pieces in the way
			for (int i = Math.min(y1, y2) + 1; i < Math.max(y1, y2); i++) {
				
				if (board[x1][i] != null)
					return ChessGame.INVALID;
			}
			
			// check for a move
			if (board[x2][y2] == null) {
				
				return ChessGame.MOVE;
			}
			
			// else check for a capture
			else if (board[x2][y2].getOwner().getId() != board[x1][y1].getOwner().getId()) {
				
				return ChessGame.CAPTURE;
			}
		}
		
		// else check if move is in a vertical line
		else if (y1 == y2) {
			
			// check if there are pieces in the way
			for (int i = Math.min(x1, x2) + 1; i < Math.max(x1, x2); i++) {
							
				if (board[i][y1] != null)
					return ChessGame.INVALID;
			}
						
			// check for a move
			if (board[x2][y2] == null) {
				
				return ChessGame.MOVE;
			}
						
			// else check for a capture
			else if (board[x2][y2].getOwner().getId() != board[x1][y1].getOwner().getId()) {
				
				return ChessGame.CAPTURE;
			}
		}
		
		return ChessGame.INVALID;
	}


	@Override
	public String toString() {
		return ChessGame.QUEEN;
	}

}
