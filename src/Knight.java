import java.awt.Color;

/**
 * This class is the blueprint for a Knight.
 * 
 * @author Cindy Li
 * @since Wednesday, July 12, 2017
 *
 */
public class Knight extends Piece {
	
	// constructor
	public Knight(Player p) {
		super(p);
		
		// set the knight's icon
		if (p.getColor() == Color.WHITE)
			setIcon(ChessGame.WHITE_PIECES[2]);
		
		else if (p.getColor() == Color.BLACK)
			setIcon(ChessGame.BLACK_PIECES[2]);
	}
	
	
	@Override
	public String typeOfMove(ChessGame game, Piece[][] board, int x1, int y1, int x2, int y2) {
		
		// check if move is L shaped
		if ((Math.max(x1, x2) - Math.min(x1, x2) == 2 && Math.max(y1, y2) - Math.min(y1, y2) == 1) ||
			(Math.max(x1, x2) - Math.min(x1, x2) == 1 && Math.max(y1, y2) - Math.min(y1, y2) == 2)) {
			
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
		return ChessGame.KNIGHT;
	}

}
