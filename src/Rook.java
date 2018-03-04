import java.awt.Color;

/**
 * This class is the blueprint for a Rook.
 * 
 * @author Cindy Li
 * @since Wednesday, July 12, 2017
 *
 */
public class Rook extends Piece {
	
	// instance fields
	private boolean firstMove;
	
	
	// constructor
	public Rook(Player p) {
		super(p);
		
		// set the rook's icon
		if (p.getColor() == Color.WHITE)
			setIcon(ChessGame.WHITE_PIECES[1]);
		
		else if (p.getColor() == Color.BLACK)
			setIcon(ChessGame.BLACK_PIECES[1]);
		
		firstMove = true;
	}
	
	
	/**
	 * Sets the firstMove boolean to false.
	 */
	public void turnOffFirstMove() {
		firstMove = false;
	}
	
	
	@Override
	public String typeOfMove(ChessGame game, Piece[][] board, int x1, int y1, int x2, int y2) {
		
		// check if move is in a horizontal line
		if (x1 == x2) {
			
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
	
	
	/**
	 * Returns whether its the rook's first move or not.
	 * @return firstMove
	 */
	public boolean getFirstMove() {
		return firstMove;
	}


	@Override
	public String toString() {
		return ChessGame.ROOK;
	}

}
