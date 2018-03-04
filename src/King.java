import java.awt.Color;

/**
 * This class is the blueprint for a King.
 * 
 * @author Cindy Li
 * @since Wednesday, July 12, 2017
 *
 */
public class King extends Piece {
	
	// instance fields
	private boolean firstMove;
		
	
	// constructor
	public King(Player p) {
		super(p);
		
		// set the king's icon
		if (p.getColor() == Color.WHITE)
			setIcon(ChessGame.WHITE_PIECES[5]);
		
		else if (p.getColor() == Color.BLACK)
			setIcon(ChessGame.BLACK_PIECES[5]);
		
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
		
		// check if move is within the king's radius
		if ((Math.max(x1, x2) - Math.min(x1, x2) == 1 || x1 == x2) && 
				(Math.max(y1, y2) - Math.min(y1, y2) == 1 || y1 == y2)) {
			
			// check for a move
			if (board[x2][y2] == null) {
				
				return ChessGame.MOVE;
			}
			
			// else check for a capture
			else if (board[x2][y2].getOwner().getId() != board[x1][y1].getOwner().getId()) {
				
				return ChessGame.CAPTURE;
			}
		}
		
		// check if move is castling
		else if (firstMove && x1 == x2 && Math.max(y1, y2) - Math.min(y1, y2) == 2) {
			
			// copy of the board
			Piece[][] boardCopy = new Piece[8][8];
			
			// check if king is in check
			if (getOwner().getChecked())
				return ChessGame.CHECK;
			
			
			// make sure there are no pieces in the way and 
			// the king will not move into a checked position
			for (int i = 1; i <= 2; i++) {
				
				for (int j = 0; j < 8; j++) {
					for (int k = 0; k < 8; k++) {
						
						boardCopy[j][k] = board[j][k];
					}
				}
				
				// left
				if (y2 < y1) {
					
					if (boardCopy[x1][y1 - 1] == null) {
						boardCopy[x1][y1 - 1] = boardCopy[x1][y1];
					}
					else
						return ChessGame.INVALID;
				}
				
				// right
				else {
					
					if (boardCopy[x1][y1 + 1] == null) {
						boardCopy[x1][y1 + 1] = boardCopy[x1][y1];
					}
					else
						return ChessGame.INVALID;
				}
				
				boardCopy[x1][y1] = null;
				
				// if in a checked position
				if (game.check(boardCopy, !game.getTurn()))
					return ChessGame.CHECK;
			}
			
			
			// player 1
			if (board[x1][y1].getOwner().getId() == 1 && !board[x1][y1].getOwner().getChecked()) {
				
				// castle left
				if (y2 < y1) {
					
					// check if its the rook's first move, and if there is no piece blocking it
					if (board[7][0] instanceof Rook && ((Rook) board[7][0]).getFirstMove()
							&& board[7][1] == null)
						return ChessGame.CASTLE_LEFT;
				}
				
				// castle right
				else {
					
					// check if its the rook's first move, and if there is no piece blocking it
					if (board[7][7] instanceof Rook && ((Rook) board[7][7]).getFirstMove()
							&& board[7][6] == null)
						return ChessGame.CASTLE_RIGHT;
				}
			}
			
			// player 2
			else if (board[x1][y1].getOwner().getId() == 2 && !board[x1][y1].getOwner().getChecked()) {
							
				// castle left
				if (y2 < y1) {
								
					// check if its the rook's first move, and if there is no piece blocking it
					if (board[0][0] instanceof Rook && ((Rook) board[0][0]).getFirstMove()
							&& board[0][1] == null)
						return ChessGame.CASTLE_LEFT;
				}
							
				// castle right
				else {
								
					// check if its the rook's first move, and if there is no piece blocking it
					if (board[0][7] instanceof Rook && ((Rook) board[0][7]).getFirstMove()
							&& board[0][6] == null)
						return ChessGame.CASTLE_RIGHT;
				}
			}
		}
		
		return ChessGame.INVALID;
	}


	@Override
	public String toString() {
		return ChessGame.KING;
	}

}
