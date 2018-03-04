import java.awt.Color;

/**
 * This class is the blueprint for a Pawn.
 * 
 * @author Cindy Li
 * @since Wednesday, July 12, 2017
 *
 */
public class Pawn extends Piece {

	// instance fields
	private boolean firstMove;
	private boolean doubleStepped;
	
	
	// constructor
	public Pawn(Player p) {
		super(p);
		
		// set the pawn's icon
		if (p.getColor() == Color.WHITE)
			setIcon(ChessGame.WHITE_PIECES[0]);
		
		else if (p.getColor() == Color.BLACK)
			setIcon(ChessGame.BLACK_PIECES[0]);
		
		firstMove = true;
		doubleStepped = false;
	}
	
	
	/**
	 * Sets the firstMove boolean to false.
	 */
	public void turnOffFirstMove() {
		firstMove = false;
	}
	
	
	/**
	 * Sets the doubleStepped boolean to b.
	 * @param b
	 */
	public void setDoubleStepped(boolean b) {
		doubleStepped = b;
	}
	
	
	@Override
	public String typeOfMove(ChessGame game, Piece[][] board, int x1, int y1, int x2, int y2) {
		
		// player 1 (going up)
		if (board[x1][y1].getOwner().getId() == 1) {
			
			// move 1 square
			if (x1 - 1 == x2 && y1 == y2 && board[x2][y2] == null) {
				
				return ChessGame.MOVE;
			}
			
			// move 2 squares (has to be first move)
			else if (x1 - 2 == x2 && y1 == y2 && board[x2][y2] == null && firstMove) {
				
				return ChessGame.MOVE;
			}
			
			// capture
			else if (x1 - 1 == x2 && (y1 - 1 == y2 || y1 + 1 == y2) 
						&& board[x2][y2] != null && board[x2][y2].getOwner().getId() == 2) {
				
				return ChessGame.CAPTURE;
			}
			
			// en passant 
			else if (x1 - 1 == x2 && (y1 - 1 == y2 || y1 + 1 == y2) && board[x1][y2] instanceof Pawn 
					&& board[x1][y2].getOwner().getId() == 2 && ((Pawn) board[x1][y2]).doubleStepped) {
				
				return ChessGame.EN_PASSANT;
			}
		}
		
		// player 2 (going down)
		else {
			
			// move 1 square
			if (x1 + 1 == x2 && y1 == y2 && board[x2][y2] == null) {
				
				return ChessGame.MOVE;
			}
			
			// move 2 squares (has to be first move)
			else if (x1 + 2 == x2 && y1 == y2 && board[x2][y2] == null && firstMove) {
				
				return ChessGame.MOVE;
			}
			
			// capture
			else if (x1 + 1 == x2 && (y1 - 1 == y2 || y1 + 1 == y2) 
						&& board[x2][y2] != null && board[x2][y2].getOwner().getId() == 1) {
				
				return ChessGame.CAPTURE;
			}
			
			// en passant 
			else if (x1 + 1 == x2 && (y1 - 1 == y2 || y1 + 1 == y2) && board[x1][y2] instanceof Pawn 
					&& board[x1][y2].getOwner().getId() == 1 && ((Pawn) board[x1][y2]).doubleStepped) {
				
				return ChessGame.EN_PASSANT;
			}
		}
		
		return ChessGame.INVALID;
	}


	@Override
	public String toString() {
		return ChessGame.PAWN;
	}

}
