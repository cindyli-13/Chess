import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * This class is the GUI for the chess game.
 * 
 * @author Cindy Li
 * @since Wednesday, July 12, 2017
 *
 */
@SuppressWarnings("serial")
public class UserInterface extends JFrame implements ActionListener {

	// instance fields
	private JPanel gameBoard, gamePanel, howToPlayPanel, choices;
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8, 
				   labelA, labelB, labelC, labelD, labelE, labelF, labelG, labelH;
	private JLabel display, turnIndicator, title, subtitle;
	private JButton[][] buttons;
	private JButton resetButton, helpButton, returnButton;
	private JButton settingUpButton, pawnButton, bishopButton, knightButton, rookButton, 
					queenButton, kingButton, castlingButton, enpassantButton, 
					promotionButton, checkButton, checkmateButton, stalemateButton;
	private ChessGame game;
	private Color chosenButtonColor;
	private JButton chosenButton, dummy;
	private JTextArea instructions;
	
	
	// *************** CONSTANTS ***************
	
	public static final Color[] BOARD_COLORS = new Color[] {
			new Color(255, 245, 200), new Color(135, 100, 65), new Color(255, 250, 30)
	};
	
	public static final Color BACKGROUND_COLOR = new Color(210, 200, 160);
	
	public static final Color LABEL_COLOR = Color.BLACK;
	public static final Font LABEL_FONT = new Font("Bookman Old Style", Font.BOLD, 24);
	
	public static final Color TEXT_COLOR = Color.DARK_GRAY;
	public static final Font TEXT_FONT = new Font("Bookman Old Style", Font.BOLD, 20);
	
	public static final Color BUTTON_COLOR = Color.WHITE;
	
	public static final Color TITLE_COLOR = new Color(30, 30, 30);
	public static final Font TITLE_FONT = new Font("Bookman Old Style", Font.BOLD, 42);
	public static final Color TITLE_BACKGROUND_COLOR = new Color(155, 140, 100);
	
	public static final Color SUBTITLE_COLOR = new Color(45, 45, 45);
	public static final Font SUBTITLE_FONT = new Font("Bookman Old Style", Font.BOLD, 32);
	
	public static final Color INSTRUCTIONS_COLOR = new Color(55, 55, 45);
	public static final Font INSTRUCTIONS_FONT = new Font("Bookman Old Style", Font.PLAIN, 20);
	
	public static final Color CHOICES_BACKGROUND_COLOR = new Color(180, 170, 130);
	public static final Color CHOICES_COLOR = new Color(155, 150, 125);
	public static final Color CHOICES_TEXT_COLOR = new Color(70, 70, 70);
	public static final Font CHOICES_FONT = new Font("Bookman Old Style", Font.BOLD, 18);
	
	// *****************************************
	
	
	// main
	public static void main(String[] args) {
		new UserInterface();
	}
	
	
	// constructor
	public UserInterface() {
		super("Chess");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(587,700);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		// set up game board
		gameBoard = new JPanel();
		gameBoard.setBounds(50, 80, 480, 480);
		gameBoard.setLayout(new GridLayout(8, 8));
		
		
		buttons = new JButton[8][8];
		
		int colorIndex = 0;
		
		
		// add buttons to game board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				if (i % 2 == 0)
					colorIndex = 0;
				else
					colorIndex = 1;
				
				if (j % 2 == 1)
					colorIndex = 1 - colorIndex;
				
				JButton b = new JButton();
				b.setFocusPainted(false);
				b.addActionListener(this);
				b.setBackground(BOARD_COLORS[colorIndex]);
				
				buttons[i][j] = b;
				gameBoard.add(b);
			}
		}
		
		// set up chess game
		game = new ChessGame();
		
		
		// set up labels
		label1 = new JLabel("1");
		label1.setBounds(15, 80, 25, 60);
		label1.setFont(LABEL_FONT);
		label1.setForeground(LABEL_COLOR);
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setVerticalAlignment(JLabel.CENTER);
		
		label2 = new JLabel("2");
		label2.setBounds(15, 140, 25, 60);
		label2.setFont(LABEL_FONT);
		label2.setForeground(LABEL_COLOR);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setVerticalAlignment(JLabel.CENTER);
		
		label3 = new JLabel("3");
		label3.setBounds(15, 200, 25, 60);
		label3.setFont(LABEL_FONT);
		label3.setForeground(LABEL_COLOR);
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setVerticalAlignment(JLabel.CENTER);
		
		label4 = new JLabel("4");
		label4.setBounds(15, 260, 25, 60);
		label4.setFont(LABEL_FONT);
		label4.setForeground(LABEL_COLOR);
		label4.setHorizontalAlignment(JLabel.CENTER);
		label4.setVerticalAlignment(JLabel.CENTER);
		
		label5 = new JLabel("5");
		label5.setBounds(15, 320, 25, 60);
		label5.setFont(LABEL_FONT);
		label5.setForeground(LABEL_COLOR);
		label5.setHorizontalAlignment(JLabel.CENTER);
		label5.setVerticalAlignment(JLabel.CENTER);
	
		label6 = new JLabel("6");
		label6.setBounds(15, 380, 25, 60);
		label6.setFont(LABEL_FONT);
		label6.setForeground(LABEL_COLOR);
		label6.setHorizontalAlignment(JLabel.CENTER);
		label6.setVerticalAlignment(JLabel.CENTER);
		
		label7 = new JLabel("7");
		label7.setBounds(15, 440, 25, 60);
		label7.setFont(LABEL_FONT);
		label7.setForeground(LABEL_COLOR);
		label7.setHorizontalAlignment(JLabel.CENTER);
		label7.setVerticalAlignment(JLabel.CENTER);
		
		label8 = new JLabel("8");
		label8.setBounds(15, 500, 25, 60);
		label8.setFont(LABEL_FONT);
		label8.setForeground(LABEL_COLOR);
		label8.setHorizontalAlignment(JLabel.CENTER);
		label8.setVerticalAlignment(JLabel.CENTER);
		
		labelA = new JLabel("a");
		labelA.setBounds(50, 560, 60, 40);
		labelA.setFont(LABEL_FONT);
		labelA.setForeground(LABEL_COLOR);
		labelA.setHorizontalAlignment(JLabel.CENTER);
		labelA.setVerticalAlignment(JLabel.CENTER);
		
		labelB = new JLabel("b");
		labelB.setBounds(110, 560, 60, 40);
		labelB.setFont(LABEL_FONT);
		labelB.setForeground(LABEL_COLOR);
		labelB.setHorizontalAlignment(JLabel.CENTER);
		labelB.setVerticalAlignment(JLabel.CENTER);
		
		labelC = new JLabel("c");
		labelC.setBounds(170, 560, 60, 40);
		labelC.setFont(LABEL_FONT);
		labelC.setForeground(LABEL_COLOR);
		labelC.setHorizontalAlignment(JLabel.CENTER);
		labelC.setVerticalAlignment(JLabel.CENTER);
		
		labelD = new JLabel("d");
		labelD.setBounds(230, 560, 60, 40);
		labelD.setFont(LABEL_FONT);
		labelD.setForeground(LABEL_COLOR);
		labelD.setHorizontalAlignment(JLabel.CENTER);
		labelD.setVerticalAlignment(JLabel.CENTER);
		
		labelE = new JLabel("e");
		labelE.setBounds(290, 560, 60, 40);
		labelE.setFont(LABEL_FONT);
		labelE.setForeground(LABEL_COLOR);
		labelE.setHorizontalAlignment(JLabel.CENTER);
		labelE.setVerticalAlignment(JLabel.CENTER);
		
		labelF = new JLabel("f");
		labelF.setBounds(350, 560, 60, 40);
		labelF.setFont(LABEL_FONT);
		labelF.setForeground(LABEL_COLOR);
		labelF.setHorizontalAlignment(JLabel.CENTER);
		labelF.setVerticalAlignment(JLabel.CENTER);
		
		labelG = new JLabel("g");
		labelG.setBounds(410, 560, 60, 40);
		labelG.setFont(LABEL_FONT);
		labelG.setForeground(LABEL_COLOR);
		labelG.setHorizontalAlignment(JLabel.CENTER);
		labelG.setVerticalAlignment(JLabel.CENTER);
		
		labelH = new JLabel("h");
		labelH.setBounds(470, 560, 60, 40);
		labelH.setFont(LABEL_FONT);
		labelH.setForeground(LABEL_COLOR);
		labelH.setHorizontalAlignment(JLabel.CENTER);
		labelH.setVerticalAlignment(JLabel.CENTER);
		
		
		// set up display
		display = new JLabel();
		display.setBounds(50, 610, 480, 40);
		display.setBackground(Color.WHITE);
		display.setFont(TEXT_FONT);
		display.setForeground(TEXT_COLOR);
		display.setHorizontalAlignment(JLabel.CENTER);
		display.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		display.setOpaque(true);
		
		
		// set up turn indicator
		turnIndicator = new JLabel();
		turnIndicator.setBounds(120, 20, 340, 40);
		turnIndicator.setBackground(Color.WHITE);
		turnIndicator.setFont(TEXT_FONT);
		turnIndicator.setForeground(TEXT_COLOR);
		turnIndicator.setHorizontalAlignment(JLabel.CENTER);
		turnIndicator.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		turnIndicator.setOpaque(true);
		
		
		// set up reset button
		ImageIcon resetIcon = new ImageIcon(getClass().getResource("/reset.png"));
		Image resetImage = resetIcon.getImage().getScaledInstance(35, 27, java.awt.Image.SCALE_SMOOTH);
		resetIcon = new ImageIcon(resetImage);
		
		resetButton = new JButton(resetIcon);
		resetButton.setBounds(490, 20, 40, 40);
		resetButton.setFocusPainted(false);
		resetButton.setBackground(BUTTON_COLOR);
		resetButton.addActionListener(this);
		
		
		// set up help button
		ImageIcon helpIcon = new ImageIcon(getClass().getResource("/question_mark.png"));
		Image helpImage = helpIcon.getImage().getScaledInstance(17, 27, java.awt.Image.SCALE_SMOOTH);
		helpIcon = new ImageIcon(helpImage);
		
		helpButton = new JButton(helpIcon);
		helpButton.setBounds(50, 20, 40, 40);
		helpButton.setFocusPainted(false);
		helpButton.setBackground(BUTTON_COLOR);
		helpButton.addActionListener(this);
		
		
		// set up gamePanel
		gamePanel = new JPanel();
		gamePanel.setBounds(0, 0, 587, 700);
		gamePanel.setLayout(null);
		gamePanel.setBackground(BACKGROUND_COLOR);
		
		
		// add everything to the gamePanel
		gamePanel.add(gameBoard);
		gamePanel.add(label1);
		gamePanel.add(label2);
		gamePanel.add(label3);
		gamePanel.add(label4);
		gamePanel.add(label5);
		gamePanel.add(label6);
		gamePanel.add(label7);
		gamePanel.add(label8);
		gamePanel.add(labelA);
		gamePanel.add(labelB);
		gamePanel.add(labelC);
		gamePanel.add(labelD);
		gamePanel.add(labelE);
		gamePanel.add(labelF);
		gamePanel.add(labelG);
		gamePanel.add(labelH);
		gamePanel.add(display);
		gamePanel.add(turnIndicator);
		gamePanel.add(resetButton);
		gamePanel.add(helpButton);
		
		
		// set up how to play panel
		howToPlayPanel = new JPanel();
		howToPlayPanel.setLayout(null);
		howToPlayPanel.setBounds(0, 0, 587, 700);
		howToPlayPanel.setBackground(BACKGROUND_COLOR);
				
		
		// set up title label
		title = new JLabel("  How To Play");
		title.setBounds(0, 0, 587, 90);
		title.setFont(TITLE_FONT);
		title.setForeground(TITLE_COLOR);
		title.setBackground(TITLE_BACKGROUND_COLOR);
		title.setBorder(BorderFactory.createRaisedBevelBorder());
		title.setOpaque(true);
		
		
		// set up return button
		ImageIcon returnIcon = new ImageIcon(getClass().getResource("/return.png"));
		Image returnImage = returnIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		returnIcon = new ImageIcon(returnImage);
				
		returnButton = new JButton(returnIcon);
		returnButton.setBounds(510, 25, 40, 40);
		returnButton.setFocusPainted(false);
		returnButton.setBackground(BUTTON_COLOR);
		returnButton.addActionListener(this);
		
		// set up subtitle
		subtitle = new JLabel();
		subtitle.setBounds(190, 120, 400, 40);
		subtitle.setFont(SUBTITLE_FONT);
		subtitle.setForeground(SUBTITLE_COLOR);
		
		
		// set up instructions text area
		instructions = new JTextArea();
		instructions.setBounds(190, 200, 345, 410);
		instructions.setFont(INSTRUCTIONS_FONT);
		instructions.setBackground(BACKGROUND_COLOR);
		instructions.setForeground(INSTRUCTIONS_COLOR);
		instructions.setWrapStyleWord(true);
		instructions.setLineWrap(true);
		instructions.setEditable(false);
		
		
		// set up choices panel
		choices = new JPanel();
		choices.setLayout(null);
		choices.setBounds(0, 90, 150, 610);
		choices.setBackground(CHOICES_BACKGROUND_COLOR);
		
		
		// set up choices buttons
		settingUpButton = new JButton("Setting Up");
		settingUpButton.setBounds(0, 0, 150, 40);
		settingUpButton.setBackground(CHOICES_COLOR);
		settingUpButton.setForeground(CHOICES_TEXT_COLOR);
		settingUpButton.setFont(CHOICES_FONT);
		settingUpButton.setFocusPainted(false);
		settingUpButton.setHorizontalAlignment(SwingConstants.LEFT);
		settingUpButton.addActionListener(this);
		
		pawnButton = new JButton("Pawn");
		pawnButton.setBounds(0, 40, 150, 40);
		pawnButton.setBackground(CHOICES_COLOR);
		pawnButton.setForeground(CHOICES_TEXT_COLOR);
		pawnButton.setFont(CHOICES_FONT);
		pawnButton.setFocusPainted(false);
		pawnButton.setHorizontalAlignment(SwingConstants.LEFT);
		pawnButton.addActionListener(this);
		
		bishopButton = new JButton("Bishop");
		bishopButton.setBounds(0, 80, 150, 40);
		bishopButton.setBackground(CHOICES_COLOR);
		bishopButton.setForeground(CHOICES_TEXT_COLOR);
		bishopButton.setFont(CHOICES_FONT);
		bishopButton.setFocusPainted(false);
		bishopButton.setHorizontalAlignment(SwingConstants.LEFT);
		bishopButton.addActionListener(this);
		
		knightButton = new JButton("Knight");
		knightButton.setBounds(0, 120, 150, 40);
		knightButton.setBackground(CHOICES_COLOR);
		knightButton.setForeground(CHOICES_TEXT_COLOR);
		knightButton.setFont(CHOICES_FONT);
		knightButton.setFocusPainted(false);
		knightButton.setHorizontalAlignment(SwingConstants.LEFT);
		knightButton.addActionListener(this);
		
		rookButton = new JButton("Rook");
		rookButton.setBounds(0, 160, 150, 40);
		rookButton.setBackground(CHOICES_COLOR);
		rookButton.setForeground(CHOICES_TEXT_COLOR);
		rookButton.setFont(CHOICES_FONT);
		rookButton.setFocusPainted(false);
		rookButton.setHorizontalAlignment(SwingConstants.LEFT);
		rookButton.addActionListener(this);
		
		queenButton = new JButton("Queen");
		queenButton.setBounds(0, 200, 150, 40);
		queenButton.setBackground(CHOICES_COLOR);
		queenButton.setForeground(CHOICES_TEXT_COLOR);
		queenButton.setFont(CHOICES_FONT);
		queenButton.setFocusPainted(false);
		queenButton.setHorizontalAlignment(SwingConstants.LEFT);
		queenButton.addActionListener(this);
		
		kingButton = new JButton("King");
		kingButton.setBounds(0, 240, 150, 40);
		kingButton.setBackground(CHOICES_COLOR);
		kingButton.setForeground(CHOICES_TEXT_COLOR);
		kingButton.setFont(CHOICES_FONT);
		kingButton.setFocusPainted(false);
		kingButton.setHorizontalAlignment(SwingConstants.LEFT);
		kingButton.addActionListener(this);
		
		castlingButton = new JButton("Castling");
		castlingButton.setBounds(0, 280, 150, 40);
		castlingButton.setBackground(CHOICES_COLOR);
		castlingButton.setForeground(CHOICES_TEXT_COLOR);
		castlingButton.setFont(CHOICES_FONT);
		castlingButton.setFocusPainted(false);
		castlingButton.setHorizontalAlignment(SwingConstants.LEFT);
		castlingButton.addActionListener(this);
		
		enpassantButton = new JButton("En Passant");
		enpassantButton.setBounds(0, 320, 150, 40);
		enpassantButton.setBackground(CHOICES_COLOR);
		enpassantButton.setForeground(CHOICES_TEXT_COLOR);
		enpassantButton.setFont(CHOICES_FONT);
		enpassantButton.setFocusPainted(false);
		enpassantButton.setHorizontalAlignment(SwingConstants.LEFT);
		enpassantButton.addActionListener(this);
		
		promotionButton = new JButton("Promotion");
		promotionButton.setBounds(0, 360, 150, 40);
		promotionButton.setBackground(CHOICES_COLOR);
		promotionButton.setForeground(CHOICES_TEXT_COLOR);
		promotionButton.setFont(CHOICES_FONT);
		promotionButton.setFocusPainted(false);
		promotionButton.setHorizontalAlignment(SwingConstants.LEFT);
		promotionButton.addActionListener(this);
		
		checkButton = new JButton("Check");
		checkButton.setBounds(0, 400, 150, 40);
		checkButton.setBackground(CHOICES_COLOR);
		checkButton.setForeground(CHOICES_TEXT_COLOR);
		checkButton.setFont(CHOICES_FONT);
		checkButton.setFocusPainted(false);
		checkButton.setHorizontalAlignment(SwingConstants.LEFT);
		checkButton.addActionListener(this);
		
		checkmateButton = new JButton("Checkmate");
		checkmateButton.setBounds(0, 440, 150, 40);
		checkmateButton.setBackground(CHOICES_COLOR);
		checkmateButton.setForeground(CHOICES_TEXT_COLOR);
		checkmateButton.setFont(CHOICES_FONT);
		checkmateButton.setFocusPainted(false);
		checkmateButton.setHorizontalAlignment(SwingConstants.LEFT);
		checkmateButton.addActionListener(this);
		
		stalemateButton = new JButton("Stalemate");
		stalemateButton.setBounds(0, 480, 150, 40);
		stalemateButton.setBackground(CHOICES_COLOR);
		stalemateButton.setForeground(CHOICES_TEXT_COLOR);
		stalemateButton.setFont(CHOICES_FONT);
		stalemateButton.setFocusPainted(false);
		stalemateButton.setHorizontalAlignment(SwingConstants.LEFT);
		stalemateButton.addActionListener(this);
		
		
		// add buttons to choices panel
		choices.add(settingUpButton);
		choices.add(pawnButton);
		choices.add(bishopButton);
		choices.add(knightButton);
		choices.add(rookButton);
		choices.add(queenButton);
		choices.add(kingButton);
		choices.add(castlingButton);
		choices.add(enpassantButton);
		choices.add(promotionButton);
		choices.add(checkButton);
		choices.add(checkmateButton);
		choices.add(stalemateButton);
		
		
		// add everything to the how to play panel
		howToPlayPanel.add(title);
		howToPlayPanel.add(returnButton);
		howToPlayPanel.add(subtitle);
		howToPlayPanel.add(instructions);
		howToPlayPanel.add(choices);
		
		howToPlayPanel.setComponentZOrder(returnButton, 0);
		
		
		// add everything panel to the JFrame
		add(gamePanel);
		add(howToPlayPanel);
		
		
		// update the board
		update();
		
		// set turn indicator
		turnIndicator.setText(game.getP1().getName() + "'s turn");
		
		// set up default instructions page
		setInstructions(settingUpButton);
		
		// show game panel
		gamePanel.setVisible(true);
		howToPlayPanel.setVisible(false);
		
		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// reset button
		if (e.getSource() == resetButton) {
			
			// reset the game
			game.init();
			update();
			
			// set turn indicator
			turnIndicator.setText(game.getP1().getName() + "'s turn");
			
			// set display text
			display.setText("");
		}
		
		// help button
		else if (e.getSource() == helpButton) {
			
			// show the how to play panel
			gamePanel.setVisible(false);
			howToPlayPanel.setVisible(true);
		}
		
		// return button
		else if (e.getSource() == returnButton) {
			
			// show the game panel
			gamePanel.setVisible(true);
			howToPlayPanel.setVisible(false);
		}
		
		// setting up button
		else if (e.getSource() == settingUpButton) {
					
			setInstructions(settingUpButton);
		}
		
		// pawn button
		else if (e.getSource() == pawnButton) {
			
			setInstructions(pawnButton);
		}
		
		// bishop button
		else if (e.getSource() == bishopButton) {
					
			setInstructions(bishopButton);
		}
		
		// knight button
		else if (e.getSource() == knightButton) {
				
			setInstructions(knightButton);
		}
		
		// rook button
		else if (e.getSource() == rookButton) {
					
			setInstructions(rookButton);
		}
		
		// queen button
		else if (e.getSource() == queenButton) {
							
			setInstructions(queenButton);
		}
		
		// king button
		else if (e.getSource() == kingButton) {
							
			setInstructions(kingButton);
		}
		
		// castling button
		else if (e.getSource() == castlingButton) {
							
			setInstructions(castlingButton);
		}
		
		// en passant button
		else if (e.getSource() == enpassantButton) {
							
			setInstructions(enpassantButton);
		}
		
		// promotion button
		else if (e.getSource() == promotionButton) {
									
			setInstructions(promotionButton);
		}
		
		// check button
		else if (e.getSource() == checkButton) {
									
			setInstructions(checkButton);
		}
		
		// check mate button
		else if (e.getSource() == checkmateButton) {
									
			setInstructions(checkmateButton);
		}
		
		// stale mate button
		else if (e.getSource() == stalemateButton) {
									
			setInstructions(stalemateButton);
		}
		
		// board
		else {
			
			JButton b = (JButton) e.getSource();
			int x = 0, y = 0;
			
			loop: while (x < 8) {
				
				y = 0;
				
				while (y < 8) {
					
					if (b == buttons[x][y])
						break loop;
					
					y++;
				}
				x++;
			}
			
			
			// as long as the game is still going
			if (game.getWinner() == -1) {
				
				// player 1's turn
				if (game.getTurn()) {
					
					// if a piece was already chosen
					if (game.getChoseAPiece()) {
						
						game.setDestination(x, y);
						
						// move
						if (game.typeOfMove().equals(ChessGame.MOVE)) {
							
							// check if the move will result in a checked position
							if (game.getBoard()[game.getChosenPieceX()][game.getChosenPieceY()].willBeChecked
									(game, game.getBoard(), game.getChosenPieceX(), game.getChosenPieceY(), x, y)) {
								
								// set display text
								display.setText("Invalid move: check");
								
								game.setChoseAPiece(false);
								chosenButton.setBackground(chosenButtonColor);
								
								game.setJustMoved(false);
							}
							else {
								
								game.movePiece();
								update();
								
								// set turn indicator
								turnIndicator.setText(game.getP2().getName() + "'s turn");
								
								display.setText("");
							}
						}
						
						// capture
						else if (game.typeOfMove().equals(ChessGame.CAPTURE)) {
							
							// check if the move will result in a checked position
							if (game.getBoard()[game.getChosenPieceX()][game.getChosenPieceY()].willBeChecked
									(game, game.getBoard(), game.getChosenPieceX(), game.getChosenPieceY(), x, y)) {
								
								// set display text
								display.setText("Invalid move: check");
								
								game.setChoseAPiece(false);
								chosenButton.setBackground(chosenButtonColor);
								
								game.setJustMoved(false);
							}
							else {
								
								// set display text
								display.setText(game.getP1().getName() + " captured a " 
										+ game.getBoard()[x][y].toString() + "!");
								
								game.movePiece();
								update();
								
								// set turn indicator
								turnIndicator.setText(game.getP2().getName() + "'s turn");
							}
						}
						
						// castle left
						else if (game.typeOfMove().equals(ChessGame.CASTLE_LEFT)) {
							
							// check if the move will result in a checked position
							if (game.getBoard()[game.getChosenPieceX()][game.getChosenPieceY()].willBeChecked
									(game, game.getBoard(), game.getChosenPieceX(), game.getChosenPieceY(), x, y)) {
								
								// set display text
								display.setText("Invalid move: check");
								
								game.setChoseAPiece(false);
								chosenButton.setBackground(chosenButtonColor);
								
								game.setJustMoved(false);
							}
							else {
								
								game.movePiece();
								
								game.getBoard()[7][3] = game.getBoard()[7][0];
								game.getBoard()[7][0] = null;
								((Rook) game.getBoard()[7][3]).turnOffFirstMove();
								
								update();
								
								// set turn indicator
								turnIndicator.setText(game.getP2().getName() + "'s turn");
								
								display.setText("");
							}
						}
						
						// castle right
						else if (game.typeOfMove().equals(ChessGame.CASTLE_RIGHT)) {
							
							// check if the move will result in a checked position
							if (game.getBoard()[game.getChosenPieceX()][game.getChosenPieceY()].willBeChecked
									(game, game.getBoard(), game.getChosenPieceX(), game.getChosenPieceY(), x, y)) {
								
								// set display text
								display.setText("Invalid move: check");
								
								game.setChoseAPiece(false);
								chosenButton.setBackground(chosenButtonColor);
								
								game.setJustMoved(false);
							}
							else {
								
								game.movePiece();
								
								game.getBoard()[7][5] = game.getBoard()[7][7];
								game.getBoard()[7][7] = null;
								((Rook) game.getBoard()[7][5]).turnOffFirstMove();
								
								update();
								
								// set turn indicator
								turnIndicator.setText(game.getP2().getName() + "'s turn");
								
								display.setText("");
							}
						}
						
						// en passant
						else if (game.typeOfMove().equals(ChessGame.EN_PASSANT)) {
							
							// check if the move will result in a checked position
							if (game.getBoard()[game.getChosenPieceX()][game.getChosenPieceY()].willBeChecked
									(game, game.getBoard(), game.getChosenPieceX(), game.getChosenPieceY(), x, y)) {
								
								// set display text
								display.setText("Invalid move: check");
								
								game.setChoseAPiece(false);
								chosenButton.setBackground(chosenButtonColor);
								
								game.setJustMoved(false);
							}
							else {
								
								// set display text
								display.setText(game.getP1().getName() + " captured a " 
										+ game.getBoard()[x + 1][y].toString() + "!");
								
								game.getBoard()[x + 1][y] = null;
								
								game.movePiece();
								update();
								
								// set turn indicator
								turnIndicator.setText(game.getP2().getName() + "'s turn");
							}
						}
						
						// invalid
						else {
							
							// set display text
							// set display text
							if (game.typeOfMove().equals(ChessGame.CHECK))
								display.setText("Invalid move: check");
							
							else if (!(x == game.getChosenPieceX() && y == game.getChosenPieceY()))
								display.setText("Invalid move");
							
							game.setChoseAPiece(false);
							game.setJustMoved(false);
						}
						
						chosenButton.setBackground(chosenButtonColor);
					}
					
					// else no piece was chosen yet
					else {
						
						// if the button contains a piece owned by player 1
						if (game.getBoard()[x][y] != null 
								&& game.getBoard()[x][y].getOwner().getId() == 1) {
								
							// save the button's color
							chosenButtonColor = b.getBackground();
								
							// set the chosen button
							game.setChoseAPiece(true);
							game.setChosenPiece(x, y);
							b.setBackground(BOARD_COLORS[2]);
							chosenButton = b;
							
							// set display text
							display.setText("");
							
							game.setJustMoved(false);
						}
					}
				}
				
				// player 2's turn
				else {
							
					// if a piece was already chosen
					if (game.getChoseAPiece()) {
						
						game.setDestination(x, y);
						
						// move
						if (game.typeOfMove().equals(ChessGame.MOVE)) {
							
							// check if the move will result in a checked position
							if (game.getBoard()[game.getChosenPieceX()][game.getChosenPieceY()].willBeChecked
									(game, game.getBoard(), game.getChosenPieceX(), game.getChosenPieceY(), x, y)) {
								
								// set display text
								display.setText("Invalid move: check");
								
								game.setChoseAPiece(false);
								chosenButton.setBackground(chosenButtonColor);
								
								game.setJustMoved(false);
							}
							else {
								
								game.movePiece();
								update();
								
								// set turn indicator
								turnIndicator.setText(game.getP1().getName() + "'s turn");
								
								display.setText("");
							}
						}
						
						// capture
						else if (game.typeOfMove().equals(ChessGame.CAPTURE)) {
							
							// check if the move will result in a checked position
							if (game.getBoard()[game.getChosenPieceX()][game.getChosenPieceY()].willBeChecked
									(game, game.getBoard(), game.getChosenPieceX(), game.getChosenPieceY(), x, y)) {
								
								// set display text
								display.setText("Invalid move: check");
								
								game.setChoseAPiece(false);
								chosenButton.setBackground(chosenButtonColor);
								
								game.setJustMoved(false);
							}
							else {
								
								// set display text
								display.setText(game.getP2().getName() + " captured a " 
										+ game.getBoard()[x][y].toString() + "!");
								
								game.movePiece();
								update();
								
								// set turn indicator
								turnIndicator.setText(game.getP1().getName() + "'s turn");
							}
						}
						
						// castle left
						else if (game.typeOfMove().equals(ChessGame.CASTLE_LEFT)) {
							
							// check if the move will result in a checked position
							if (game.getBoard()[game.getChosenPieceX()][game.getChosenPieceY()].willBeChecked
									(game, game.getBoard(), game.getChosenPieceX(), game.getChosenPieceY(), x, y)) {
								
								// set display text
								display.setText("Invalid move: check");
								
								game.setChoseAPiece(false);
								chosenButton.setBackground(chosenButtonColor);
								
								game.setJustMoved(false);
							}
							else {
								
								game.movePiece();
								
								game.getBoard()[0][3] = game.getBoard()[0][0];
								game.getBoard()[0][0] = null;
								((Rook) game.getBoard()[0][3]).turnOffFirstMove();
								
								update();
								
								// set turn indicator
								turnIndicator.setText(game.getP1().getName() + "'s turn");
									
								display.setText("");
							}
						}
						
						// castle right
						else if (game.typeOfMove().equals(ChessGame.CASTLE_RIGHT)) {
							
							// check if the move will result in a checked position
							if (game.getBoard()[game.getChosenPieceX()][game.getChosenPieceY()].willBeChecked
									(game, game.getBoard(), game.getChosenPieceX(), game.getChosenPieceY(), x, y)) {
								
								// set display text
								display.setText("Invalid move: check");
								
								game.setChoseAPiece(false);
								chosenButton.setBackground(chosenButtonColor);
								
								game.setJustMoved(false);
							}
							else {
								
								game.movePiece();
								
								game.getBoard()[0][5] = game.getBoard()[0][7];
								game.getBoard()[0][7] = null;
								((Rook) game.getBoard()[0][5]).turnOffFirstMove();
								
								update();
								
								// set turn indicator
								turnIndicator.setText(game.getP1().getName() + "'s turn");
								
								display.setText("");
							}
						}
						
						// en passant
						else if (game.typeOfMove().equals(ChessGame.EN_PASSANT)) {
							
							// check if the move will result in a checked position
							if (game.getBoard()[game.getChosenPieceX()][game.getChosenPieceY()].willBeChecked
									(game, game.getBoard(), game.getChosenPieceX(), game.getChosenPieceY(), x, y)) {
								
								// set display text
								display.setText("Invalid move: check");
								
								game.setChoseAPiece(false);
								chosenButton.setBackground(chosenButtonColor);
								
								game.setJustMoved(false);
							}
							else {
								
								// set display text
								display.setText(game.getP2().getName() + " captured a " 
										+ game.getBoard()[x - 1][y].toString() + "!");
								
								game.getBoard()[x - 1][y] = null;
								
								game.movePiece();
								update();
								
								// set turn indicator
								turnIndicator.setText(game.getP1().getName() + "'s turn");
							}
						}
						
						// invalid
						else {
							
							// set display text
							if (game.typeOfMove().equals(ChessGame.CHECK))
								display.setText("Invalid move: check");
							
							else if (!(x == game.getChosenPieceX() && y == game.getChosenPieceY()))
									display.setText("Invalid move");
							
							game.setChoseAPiece(false);
							game.setJustMoved(false);
						}
						
						chosenButton.setBackground(chosenButtonColor);
					}
					
					// else no piece was chosen yet
					else {
						
						// if the button contains a piece owned by player 2
						if (game.getBoard()[x][y] != null 
								&& game.getBoard()[x][y].getOwner().getId() == 2) {
								
							// save the button's color
							chosenButtonColor = b.getBackground();
							
							// set it to the chosen button
							game.setChoseAPiece(true);
							game.setChosenPiece(x, y);
							b.setBackground(BOARD_COLORS[2]);
							chosenButton = b;
							
							// set display text
							display.setText("");
							
							game.setJustMoved(false);
						}
					}
				}
				
				// if player 1 wins
				if (game.getWinner() == 1) {
					
					// set turn indicator
					turnIndicator.setText(game.getP1().getName() + " wins!");
					
					// set display text
					if (display.getText().equals(""))
						display.setText("Checkmate!");
					else
						display.setText(display.getText() + " Checkmate!");
				}
				
				// else if player 2 wins
				else if (game.getWinner() == 2) {
					
					// set turn indicator
					turnIndicator.setText(game.getP2().getName() + " wins!");
					
					// set display text
					if (display.getText().equals(""))
						display.setText("Checkmate!");
					else
						display.setText(display.getText() + " Checkmate!");
				}
				
				// else if its just a check
				else if (game.getJustMoved() && (game.getP1().getChecked() || game.getP2().getChecked())) {
					
					// set display text
					if (display.getText().equals(""))
						display.setText("Check!");
					else
						display.setText(display.getText() + " Check!");
				}
				
				// else if its a stale mate
				else if (game.getWinner() == 0){
					
					// set turn indicator
					turnIndicator.setText("Stalemate!");
				}
				
			}
		}
	}
	
	
	/**
	 * Updates the game board.
	 */
	public void update() {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				if (game.getBoard()[i][j] == null)
					buttons[i][j].setIcon(null);
				else
					buttons[i][j].setIcon(game.getBoard()[i][j].getIcon());
			}
		}
	}
	
	
	/**
	 * Sets the instructions page to the chosen page.
	 * @param b  The button referring to the page
	 */
	public void setInstructions(JButton b) {
		
		// reset dummy button color
		if (dummy != null)
			dummy.setBackground(CHOICES_COLOR);
		
		// modify the button
		b.setBackground(BACKGROUND_COLOR);
		dummy = b;
		
		
		// set up the page depending on the button
		
		// setting up
		if (b == settingUpButton) {
			
			subtitle.setText("Setting Up");
			instructions.setText("At the start of the game, the chess pieces are arranged the same "
					+ "way for each player.\n\nThe second row is filled with pawns.\n\nOn the first "
					+ "row, rooks go in the corners, followed by the knights, then the bishops, then "
					+ "the queen who goes on the square matching her color. The king goes on the "
					+ "final square.\n\nWhite always makes the first move in chess.");
		}
		
		// pawn
		else if (b == pawnButton) {
			
			subtitle.setText("Pawn");
			instructions.setText("Pawns can only move one square in the forward direction, "
					+ "except on their very first move where they can move two squares "
					+ "forward.\n\nHowever, the pawn can only capture one square diagonally in front "
					+ "of it. It cannot capture or move past a piece directly in front of it, "
					+ "nor can it move backwards.");
		}
		
		// bishop
		else if (b == bishopButton) {
			
			subtitle.setText("Bishop");
			instructions.setText("The bishop can move as far as it wants, but only diagonally."
					+ "\n\nEach bishop starts on a separate color and stays on that color for "
					+ "the whole game.\n\nBishops cannot pass through other pieces.");
		}
		
		// knight
		else if (b == knightButton) {
					
			subtitle.setText("Knight");
			instructions.setText("Knights move two squares in one direction, then one square at "
					+ "a 90 degree angle, like an \"L\" shape.\n\nThey are the only pieces that "
					+ "can jump over other pieces.");
		}
		
		// rook
		else if (b == rookButton) {
					
			subtitle.setText("Rook");
			instructions.setText("Rooks are relatively powerful pieces.\n\nThey can move as far as "
					+ "they want in a horizontal or vertical line.\n\nRooks cannot pass through other "
					+ "pieces.");
		}
		
		// queen
		else if (b == queenButton) {
					
			subtitle.setText("Queen");
			instructions.setText("The queen is the most powerful piece of the game.\n\nHer movement "
					+ "combines that of a rook's and a bishop's: she can move as far as she wants "
					+ "in a horizontal, vertical, or diagonal line.\n\nThe queen cannot pass through "
					+ "other pieces.");
		}
		
		// king
		else if (b == kingButton) {
					
			subtitle.setText("King");
			instructions.setText("The king is the most important piece, but is one of the weakest."
					+ "\n\nHe can only move one square in any direction: up, down, left, right, or "
					+ "diagonally.\n\nCheckmate the opponent's king and you win.");
		}
		
		// castling
		else if (b == castlingButton) {
					
			subtitle.setText("Castling");
			instructions.setText("Castling is a special move involving the king and the player's"
					+ " rook.\n\nTo castle, the king moves two squares either to the left or to the "
					+ "right, and the rook on that side moves to the square right next to the king "
					+ "on the opposite side.\n\nConditions:\n  - It must be the king's first move"
					+ "\n  - It must be the rook's first move\n  - There cannot be any pieces\n     "
					+ "between the king and the rook\n  - The king must not be in check\n     or "
					+ "pass through a checked\n     square");
		}
		
		// en passant
		else if (b == enpassantButton) {
					
			subtitle.setText("En Passant");
			instructions.setText("Pawns have a special move called \"en passant\", which is French "
					+ "for \"in passing\".\n\nIf a pawn moves two squares forward on its first move "
					+ "and lands to the side of an opponent's pawn, the other pawn can capture the "
					+ "first pawn by moving diagonally to the square behind the first pawn.\n\n"
					+ "However, this move must be done right after the first pawn has moved two "
					+ "squares, otherwise the option to capture it is lost.");
		}
		
		// promotion
		else if (b == promotionButton) {
					
			subtitle.setText("Promotion");
			instructions.setText("When a pawn reaches the opposite side of the board, it can become "
					+ "(or be promoted to) any other piece.\n\nIn this version of the game, the pawn "
					+ "will be promoted to a queen.");
		}
		
		// check
		else if (b == checkButton) {
							
			subtitle.setText("Check");
			instructions.setText("Check occurs when a player's king is in a position where an "
					+ "opponent's piece can capture it.\n\nWhile under check, the player must make "
					+ "a move to relieve his or her king out of check.\n\nThe player cannot make "
					+ "a move that will result in his or her king being in a checked position.");
		}
		
		// check mate
		else if (b == checkmateButton) {
									
			subtitle.setText("Checkmate");
			instructions.setText("If a player's king is under check and there are no valid moves "
					+ "that the player can make to relieve his or her king out of check, the game is "
					+ "over and the opponent wins.\n\nThis is called checkmate.");
		}
		
		// stale mate
		else if (b == stalemateButton) {
									
			subtitle.setText("Stalemate");
			instructions.setText("A stalemate occurs when a player cannot make a valid move that "
					+ "will not result in his or her king being in a checked position, but the "
					+ "player is not currently in check.\n\nIn this situation, the game is declared "
					+ "a draw.");
		}
	}
	
}
