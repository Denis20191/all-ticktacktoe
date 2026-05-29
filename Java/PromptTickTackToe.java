import java.util.Scanner;

public class PromptTickTackToe {
	private static int[][] board = new int[3][3];
	private static int move = 1;
	
	public static void startGame(Scanner in) {
		resetBoard();
		int win = 0;
		while(move <= 9) {
			win = checkWin();
			if(win != 0) break;
			
			System.out.println("=== Move " + move + " ===");
			System.out.println("Player " + (move % 2 == 0 ? "O" : "X"));
			
			printBoard();
			
			int x = 0, y = 0;
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
			
			move++;
		}
		
		printBoard();
		
		if(win != 0) System.out.println("Winner: Player " + (move%2==0 ? 'X' : 'O'));
		else System.out.println("GAME OVER. DRAW");
	}
	
	private static void printBoard() {
		for(int i = 0; i < board.length; i++) {
			System.out.println("-------");
			for(int j = 0; j < board[i].length; j++) {
				System.out.print("|" + getSymbol(i,j));
			}
			System.out.println("|");
		}
		System.out.println("-------");
	}
	
	private static boolean tryMove(int i, int j, int p) {
		if(board[i][j] != 0)
			return false;
			
		board[i][j] = p;
		return true;
	}
	
	private static void resetBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
			}
		}
		move = 1;
	}
	
	private static int checkWin() {
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
	
	private static char getSymbol(int i, int j) {
		switch(board[i][j]) {
			case 0: return ' ';
			case 1: return 'X';
			case 2: return 'O';
		}
		return 'L';
	}
}
