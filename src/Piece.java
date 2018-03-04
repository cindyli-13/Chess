import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This abstract class is the blueprint for a Piece.
 * 
 * @author Cindy Li
 * @since Wednesday, July 12, 2017
 *
 */
public abstract class Piece {

	// fields
	private Player owner;
	private ImageIcon icon;
	
	
	// constructor
	public Piece(Player p) {
		
		owner = p;
	}
	
	/**
	 * Sets the piece's icon.
	 * @param i  The icon to set to
	 */
	public void setIcon(ImageIcon i) {
		Image image = i.getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
	}
	
	
	/**
	 * Returns the piece's icon.
	 * @return icon
	 */
	public ImageIcon getIcon() {
		return icon;
	}
	
	
	/**
	 * Returns the piece's owner.
	 * @return owner
	 */
	public Player getOwner() {
		return owner;
	}
	
	
	/**
	 * Checks if the move is valid given the piece, positions, and game board.
	 * @param game  The chess game
	 * @param board  The game board
	 * @param x1  The x coordinate of the chosen piece
	 * @param y1  The y coordinate of the chosen piece
	 * @param x2  The x coordinate of the destination
	 * @param y2  The y coordinate of the destination
	 * @return Either "MOVE", "CAPTURE", or "INVALID"
	 */
	public abstract String typeOfMove(ChessGame game, Piece[][] board, int x1, int y1, int x2, int y2);
	
	
	/**
	 * Returns the name of the piece.
	 */
	public abstract String toString();
	
	
	/**
	 * Checks whether a move will result in a checked position.
	 * @param game  The chess game
	 * @param board  The game board
	 * @param x1  The x coordinate of the chosen piece
	 * @param y1  The y coordinate of the chosen piece
	 * @param x2  The x coordinate of the destination
	 * @param y2  The y coordinate of the destination
	 * @return True for yes, false for no
	 */
	public boolean willBeChecked(ChessGame game, Piece[][] board, int x1, int y1, int x2, int y2) {
		
		// create a copy of the board
		Piece[][] boardCopy = new Piece[8][8];
		
		// for storing the player's king's position
		int kingX = 0, kingY = 0;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				boardCopy[i][j] = board[i][j];
				
				// check if the piece is the player's king
				if (boardCopy[i][j] != null && boardCopy[i][j] instanceof King 
						&& boardCopy[i][j].getOwner() == board[x1][y1].getOwner()) {
					
					kingX = i;
					kingY = j;
				}
			}
		}
		
		//store the player's id
		int id = boardCopy[kingX][kingY].getOwner().getId(); 
				
		// move the piece on the copied board
		boardCopy[x2][y2] = boardCopy[x1][y1];
		boardCopy[x1][y1] = null;
		
		
		// edit king's position
		if (boardCopy[x2][y2] instanceof King) {
			
			kingX = x2;
			kingY = y2;
		}
		
		
		// check for check
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
							
				// check if the piece at the position is owned by the other player
				if (boardCopy[i][j] != null 
						&& boardCopy[i][j].getOwner().getId() != id) {
					
					// check if the piece can capture the player's king
					if (boardCopy[i][j].typeOfMove(game, boardCopy, i, j, kingX, kingY).equals(ChessGame.CAPTURE)) {
					
						return true;
					}
				}
							
			}
		}
		return false;
	}
	
}
