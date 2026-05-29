import java.util.Scanner;

public class PromptTickTackToe {
	public final String HORIZONTAL_SEPARATOR;
	public final String VERTICAL_SEPARATOR;
	
	/**
	 * 3x3 matrix. Every cell is either 0, 1 or 2.
	 * 0: Empty cell
	 * 1: Player X
	 * 2: Player O
	 */
	private int[][] board = new int[3][3];
	
	/**
	 * Current move number. Starts from 1. Max 9
	 */
	private int move = 1;

	/**
	 * Last player that won. "X", "O" or "DRAW".
	 * Initially "NONE"
	 */
	private String lastWinner;
	
	/**
	 * Initialize the game with default separators
	 */
	public PromptTickTackToe() {
		HORIZONTAL_SEPARATOR  = "-------";
		VERTICAL_SEPARATOR = "|";
		lastWinner = "NONE";
	}
	
	/**
	 * Initialize the game with custom separators
	 */
	public PromptTickTackToe(String horizontalSeparator, String verticalSeparator) {
		HORIZONTAL_SEPARATOR  = horizontalSeparator;
		VERTICAL_SEPARATOR = verticalSeparator;
		lastWinner = "NONE";
	}
	
	/**
	 * Initialize a game of TickTackToe
	 * @param in Scanner that the game will use. Reccomended: System.in
	 */
	public void startGame(Scanner in) {
		resetBoard();
		int win = 0;
		while(move <= 9) {
			System.out.println("=== MOVE " + move + " ===");
			System.out.println("Player " + (move % 2 == 0 ? "O" : "X"));
			
			printBoard();
			
			int x, y;
			while(true) {
				try {
					System.out.print("Input x coordinate: ");
					String input = in.next();
					x = Integer.parseInt(input);
					
					if(x < 0 || x > 2) {
						System.out.println("Invalid input. Try again");
						continue;
					}
					
					System.out.print("Input y coordinate: ");
					input = in.next();
					y = Integer.parseInt(input);
					
					if(y < 0 || y > 2) {
						System.out.println("Invalid input. Try again");
						continue;
					}
					
					if(tryMove(x, y, (move%2==0 ? 2 : 1)))
						break;
					else System.out.println("Space already occupied");
				} catch(Exception e) {
					System.out.println("Invalid input. Try again");
				}
			}

			win = checkWin();
			if(win != 0) break;
			
			move++;
		}
		
		printBoard();
		
		if(win != 0) {
			System.out.println("WINNER: Player " + (win == 1 ? 'X' : 'O'));
			lastWinner = (win == 1 ? "X" : "O");
		}
		else {
			System.out.println("GAME OVER. DRAW");
			lastWinner = "DRAW";
		}
	}

	public String getLastWinner() { return lastWinner; }
	
	/**
	 * Prints the board.
	 * On the top and bottom of cells there's HORIZONTAL_SEPARATOR
	 * Between cells there's VERTICAL_SEPARATOR;
	 */
	private void printBoard() {
		for(int i = 0; i < board.length; i++) {
			System.out.println(HORIZONTAL_SEPARATOR);
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(VERTICAL_SEPARATOR + getSymbol(i,j));
			}
			System.out.println(VERTICAL_SEPARATOR);
		}
		System.out.println(HORIZONTAL_SEPARATOR);
	}
	
	/**
	 * Attempt to make a move.
	 * If the cell is empty, assign player value to the cell
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param p Player value (1 for X, 2 for O)
	 * @return If the move was succcessful or not
	 */
	private boolean tryMove(int x, int y, int p) {
		if(board[x][y] != 0)
			return false;
			
		board[x][y] = p;
		return true;
	}
	
	/**
	 * Resets the board (all cells 0) and the move counter (to 1)
	 */
	private void resetBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
			}
		}
		move = 1;
	}
	
	/**
	 * Checks every cross, collumn and row combination for possible win
	 */
	private int checkWin() {
		if(board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
			return board[0][0];
			
		if(board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0])
			return board[0][2];
			
		for(int i = 0; i < board.length; i++) {
			if(board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2])
				return board[i][0];
		}
		
		for(int j = 0; j < board.length; j++) {
			if(board[0][j] != 0 && board[0][j] == board[1][j] && board[1][j] == board[2][j])
				return board[0][j];
		}
		
		return 0;
	}
	
	/**
	 * Helper method
	 * @param x X coordinate on board
	 * @param y Y coordinate on board
	 * @return Player symbol at coordinates X,Y
	 */
	private char getSymbol(int x, int y) {
		switch(board[x][y]) {
			case 1: return 'X';
			case 2: return 'O';
			default: return ' ';
		}
	}
}
