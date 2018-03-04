import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * This class contains the game and all the 
 * interactions between the board and the pieces.
 * 
 * @author Cindy Li
 * @since Wednesday, July 12, 2017
 *
 */
public class ChessGame {
	
	// instance fields
	private Piece[][] board;
	private Player p1, p2; // p1 starts on the bottom of the board, p2 starts on the top
	private boolean turn; // true for p1, false for p2
	private boolean choseAPiece; // true if the player has chosen a piece
	private boolean justMoved;
	private int chosenPieceX, chosenPieceY;
	private int destinationX, destinationY;
	private int winner;
	private Pawn doubleSteppedPawn1, doubleSteppedPawn2;
	
	
	// ******************** CONSTANTS ********************
	
	// white pieces icons
	public static final ImageIcon[] WHITE_PIECES = new ImageIcon[] {
			new ImageIcon(ChessGame.class.getResource("/white_pawn.png")), 
			new ImageIcon(ChessGame.class.getResource("/white_rook.png")), 
			new ImageIcon(ChessGame.class.getResource("/white_knight.png")), 
			new ImageIcon(ChessGame.class.getResource("/white_bishop.png")), 
			new ImageIcon(ChessGame.class.getResource("/white_queen.png")), 
			new ImageIcon(ChessGame.class.getResource("/white_king.png")), 
	};
	
	// black pieces icons
	public static final ImageIcon[] BLACK_PIECES = new ImageIcon[] {
			new ImageIcon(ChessGame.class.getResource("/black_pawn.png")), 
			new ImageIcon(ChessGame.class.getResource("/black_rook.png")), 
			new ImageIcon(ChessGame.class.getResource("/black_knight.png")), 
			new ImageIcon(ChessGame.class.getResource("/black_bishop.png")), 
			new ImageIcon(ChessGame.class.getResource("/black_queen.png")), 
			new ImageIcon(ChessGame.class.getResource("/black_king.png")), 
	};
	
	// strings
	public static final String MOVE = "MOVE";
	public static final String CAPTURE = "CAPTURE";
	public static final String INVALID = "INVALID";
	public static final String CHECK = "CHECK";
	public static final String CASTLE_LEFT = "CASTLE LEFT";
	public static final String CASTLE_RIGHT = "CASTLE RIGHT";
	public static final String EN_PASSANT = "EN PASSANT";
	
	public static final String PAWN = "pawn";
	public static final String ROOK = "rook";
	public static final String KNIGHT = "knight";
	public static final String BISHOP = "bishop";
	public static final String QUEEN = "queen";
	public static final String KING = "king";
	
	// ***************************************************
	
	
	// constructor
	public ChessGame() {
		
		// instantiate players and board
		p1 = new Player(Color.WHITE, "White", 1);
		p2 = new Player(Color.BLACK, "Black", 2);
		board = new Piece[8][8];
		
		init();
	}
	
	
	/**
	 * Initializes the game board.
	 */
	public void init() {
		
		// clear board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				board[i][j] = null;
			}
		}
		
		
		// add player 1 pieces
		for (int i = 0; i < 8; i++) {
			
			board[6][i] = new Pawn(p1);
		}
		board[7][0] = new Rook(p1);
		board[7][1] = new Knight(p1);
		board[7][2] = new Bishop(p1);
		board[7][3] = new Queen(p1);
		board[7][4] = new King(p1);
		board[7][5] = new Bishop(p1);
		board[7][6] = new Knight(p1);
		board[7][7] = new Rook(p1);
		
		
		// add player 2 pieces
		for (int i = 0; i < 8; i++) {
					
			board[1][i] = new Pawn(p2);
		}
		board[0][0] = new Rook(p2);
		board[0][1] = new Knight(p2);
		board[0][2] = new Bishop(p2);
		board[0][3] = new Queen(p2);
		board[0][4] = new King(p2);
		board[0][5] = new Bishop(p2);
		board[0][6] = new Knight(p2);
		board[0][7] = new Rook(p2);
		
		
		// set fields to default
		turn = true;
		choseAPiece = false;
		justMoved = false;
		winner = -1;
		p1.setChecked(false);
		p2.setChecked(false);
		doubleSteppedPawn1 = null;
		doubleSteppedPawn2 = null;
	}
	
	
	/**
	 * Moves the chosen piece to the specified destination.
	 */
	public void movePiece() {
		
		board[destinationX][destinationY] = board[chosenPieceX][chosenPieceY];
		board[chosenPieceX][chosenPieceY] = null;
		
		choseAPiece = false;
		
		// turn off doubleStepped pawn(s)
		if (doubleSteppedPawn1 != null) {
			
			doubleSteppedPawn1.setDoubleStepped(false);
			doubleSteppedPawn1 = null;
		}
		if (doubleSteppedPawn2 != null) {
			
			doubleSteppedPawn2.setDoubleStepped(false);
			doubleSteppedPawn2 = null;
		}
		
		
		// if piece was a pawn
		if (board[destinationX][destinationY] instanceof Pawn) {
			
			// turn off first move
			((Pawn) board[destinationX][destinationY]).turnOffFirstMove();
			
			
			// check if move was double-step
			if (Math.max(destinationX, chosenPieceX) - Math.min(destinationX, chosenPieceX) == 2) {
				
				((Pawn) board[destinationX][destinationY]).setDoubleStepped(true);
				
				if (turn)
					doubleSteppedPawn1 = ((Pawn) board[destinationX][destinationY]);
				else
					doubleSteppedPawn2 = ((Pawn) board[destinationX][destinationY]);
			}
			
			
			// check if pawn to queen is necessary
			
			// player 1's pawn on top line
			if (board[destinationX][destinationY].getOwner() == p1 && destinationX == 0)
				board[destinationX][destinationY] = new Queen(p1);
			
			// player 2's pawn on bottom line
			else if (board[destinationX][destinationY].getOwner() == p2 && destinationX == 7)
				board[destinationX][destinationY] = new Queen(p2);
		}
		
		
		// else if piece was a king
		else if (board[destinationX][destinationY] instanceof King)
			((King) board[destinationX][destinationY]).turnOffFirstMove();
		
		// else if piece was a rook
		else if (board[destinationX][destinationY] instanceof Rook)
			((Rook) board[destinationX][destinationY]).turnOffFirstMove();
		
			
		p1.setChecked(false);
		p2.setChecked(false);
			
		// check for check
		if (check(board, turn)) {
				
			if (turn)
				p2.setChecked(true);
			else
				p1.setChecked(true);
			
			// check for check mate
			if (noValidMoves()) {
					
				if (turn)
					winner = 1;
				else
					winner = 2;
				
				justMoved = false;
			}
		}
		
		// else check for stale mate
		else if (noValidMoves()) {
			
			winner = 0;
			justMoved = false;
		}
		
		
		choseAPiece = false;
		justMoved = true;
		turn = !turn;
	}
	
	
	/**
	 * Checks if a player is in a checked position.
	 * @param board  The game board
	 * @param playedChecking True if player 1 is checking, false 
	 * if player 2 is checking
	 */
	public boolean check(Piece[][] board, boolean playerChecking) {
		
		int kingX = 0, kingY = 0;
		
		// if player 1 just finished the turn
		if (playerChecking) {
			
			// find the other player's king's position
			loop: for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					
					if (board[i][j] != null && board[i][j] instanceof King 
							&& board[i][j].getOwner() == p2) {
						kingX = i;
						kingY = j;
						break loop;
					}
				}
			}
			
			// check if player 2 is in a checked position
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					
					// check if the piece at the position is owned by player 1
					if (board[i][j] != null && board[i][j].getOwner() == p1) {
						
						// check if the piece can capture player 2's king
						if (board[i][j].typeOfMove(this, board, i, j, kingX, kingY).equals(ChessGame.CAPTURE)) {
							
							return true;
						}
					}
					
				}
			}
			return false;
		}
		
		// else player 2 just finished the turn
		else {
			
			// find the other player's king's position
			loop: for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
								
					if (board[i][j] != null && board[i][j] instanceof King 
							&& board[i][j].getOwner() == p1) {
						kingX = i;
						kingY = j;
						break loop;
					}
				}
			}
		
			// check if player 1 is in a checked position
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
								
					// check if the piece at the position is owned by player 2
					if (board[i][j] != null && board[i][j].getOwner() == p2) {
									
						// check if the piece can capture player 1's king
						if (board[i][j].typeOfMove(this, board, i, j, kingX, kingY).equals(ChessGame.CAPTURE)) {
										
							return true;
						}
					}
								
				}
			}
			return false;
		}
	}
	
	
	/**
	 * Checks if a player cannot make any valid moves.
	 */
	public boolean noValidMoves() {
		
		// create a copy of the board
		Piece[][] boardCopy = new Piece[8][8];
		
		
		// if player 1 just finished the turn
		if (turn) {
			
			// check if player 2 cannot make any valid moves
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					
					// check if the piece at the position is owned by player 2
					if (board[i][j] != null && board[i][j].getOwner() == p2) {
						
						// find all possible moves for that piece
						for (int k = 0; k < 8; k++) {
							for (int l = 0; l < 8; l++) {
								
								// as long as its a valid move
								if (!board[i][j].typeOfMove(this, board, i, j, k, l).equals(ChessGame.INVALID)
										&& !board[i][j].typeOfMove(this, board, i, j, k, l).equals(ChessGame.CHECK)) {
									
									// move the piece in the copy of the board
									for (int m = 0; m < 8; m++) {
										for (int n = 0; n < 8; n++) {
											
											boardCopy[m][n] = board[m][n];
										}
 									}
									boardCopy[k][l] = boardCopy[i][j];
									boardCopy[i][j] = null;
									
									// check for check
									if (!check(boardCopy, turn))
										return false;
								}
								
							}
						}
						
					}
					
				}
			}
			return true;
		}
		
		// else player 2 just finished the turn
		else {
					
			// check if player 1 cannot make any valid moves
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
						
					// check if the piece at the position is owned by player 1
					if (board[i][j] != null && board[i][j].getOwner() == p1) {
								
						// find all possible moves for that piece
						for (int k = 0; k < 8; k++) {
							for (int l = 0; l < 8; l++) {
										
								// as long as its a valid move
								if (!board[i][j].typeOfMove(this, board, i, j, k, l).equals(ChessGame.INVALID)
										&& !board[i][j].typeOfMove(this, board, i, j, k, l).equals(ChessGame.CHECK)) {
									
									// move the piece in the copy of the board
									for (int m = 0; m < 8; m++) {
										for (int n = 0; n < 8; n++) {
													
											boardCopy[m][n] = board[m][n];
										}
									}
									boardCopy[k][l] = boardCopy[i][j];
									boardCopy[i][j] = null;
											
									// check for check
									if (!check(boardCopy, turn))
										return false;
								}
										
							}
						}
								
					}
							
				}
			}
			return true;
		}
	}
	
	
	/**
	 * Sets choseAPiece to the given boolean.
	 * @param b
	 */
	public void setChoseAPiece(boolean b) {
		choseAPiece = b;
	}
	
	
	/**
	 * Stores the coordinates of the chosen piece.
	 * @param x  The x coordinate
	 * @param y  The y coordinate
	 */
	public void setChosenPiece(int x, int y) {
		chosenPieceX = x;
		chosenPieceY = y;
	}
	
	
	/**
	 * Stores the coordinates of the destination.
	 * @param x  The x coordinate
	 * @param y  The y coordinate
	 */
	public void setDestination(int x, int y) {
		destinationX = x;
		destinationY = y;
	}
	
	
	/**
	 * Sets justMoved to the given boolean.
	 * @param b
	 */
	public void setJustMoved(boolean b) {
		justMoved = b;
	}
	
	
	/**
	 * Returns the board.
	 * @return board
	 */
	public Piece[][] getBoard() {
		return board;
	}
	
	
	/**
	 * Returns the turn.
	 * @return turn
	 */
	public boolean getTurn() {
		return turn;
	}
	
	
	/**
	 * Returns the winner.
	 * @return winner
	 */
	public int getWinner() {
		return winner;
	}
	
	
	/**
	 * Returns whether a piece has been chosen or not.
	 * @return choseAPiece
	 */
	public boolean getChoseAPiece() {
		return choseAPiece;
	}
	
	
	/**
	 * Returns the x coordinate of the chosen piece.
	 * @return chosenPieceX
	 */
	public int getChosenPieceX() {
		return chosenPieceX;
	}
	
	
	/**
	 * Returns the y coordinate of the chosen piece.
	 * @return chosenPieceY
	 */
	public int getChosenPieceY() {
		return chosenPieceY;
	}
	
	
	/**
	 * Returns whether a move has just been made or not.
	 * @return justMoved
	 */
	public boolean getJustMoved() {
		return justMoved;
	}
	
	
	/**
	 * Returns the type of move of the chosen piece to the destination.
	 * @return Either "MOVE", "CAPTURE", or "INVALID"
	 */
	public String typeOfMove() {
		return board[chosenPieceX][chosenPieceY].typeOfMove(this, board, chosenPieceX, chosenPieceY, destinationX, destinationY);
	}
	
	
	/**
	 * Returns player 1.
	 * @return p1
	 */
	public Player getP1() {
		return p1;
	}
	
	
	/**
	 * Returns player 2.
	 * @return p2
	 */
	public Player getP2() {
		return p2;
	}
}
