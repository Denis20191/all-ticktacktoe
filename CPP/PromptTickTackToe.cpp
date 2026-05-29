#include <iostream>
#include <vector>
#include <limits>

using namespace std;

class PromptTickTackToe {
	const string VERTICAL_SEPARATOR;
	const string HORIZONTAL_SEPARATOR;
	
	string lastWinner;
	int board[3][3];
	int move;
	
	public: PromptTickTackToe() :
		HORIZONTAL_SEPARATOR("-------"),
		VERTICAL_SEPARATOR("|"),
		lastWinner("NONE")
		{}
	
	public: PromptTickTackToe(string vertical, string horizontal) :
		VERTICAL_SEPARATOR(vertical),
		HORIZONTAL_SEPARATOR(horizontal),
		lastWinner("NONE")
		{}
	
	public: void startGame() {
		resetBoard();
		
		int win = 0;
		
		while(move <= 9) {
			cout << "=== MOVE " << move << " ===" << endl;
			cout << "Player " << (move % 2 == 0 ? "O" : "X") << endl;
			
			printBoard();
			
			int x, y;
			while(true) {
				cout << "Input x coordinates: ";
				cin >> x;
				if (cin.fail()) {
				    cin.clear();           // clear the fail bit
				    cin.ignore(numeric_limits<streamsize>::max(), '\n');  // discard bad input
				    cout << "Invalid input" << endl;
				    continue;
				}
				if(x < 0 || x > 2) {
					cout << "Invalid input" << endl;
				    continue;
				}
				
				cout << "Input y coordinates: ";
				cin >> y;
				if (cin.fail()) {
				    cin.clear();           // clear the fail bit
				    cin.ignore(numeric_limits<streamsize>::max(), '\n');  // discard bad input
				    cout << "Invalid input" << endl;
				    continue;
				}
				if(y < 0 || y > 2) {
					cout << "Invalid input" << endl;
				    continue;
				}
				
				if(tryMove(x, y, (move%2==0 ? 2 : 1)))
					break;
				else
					cout << "Space already occupied" << endl;
			}
			
			win = checkWin();
			if(win != 0) break;
			
			move++;
		}
		
		printBoard();
		
		if(win != 0) {
			cout << "WINNER: Player " << (win == 1 ? 'X' : 'O') << endl;
			lastWinner = (win == 1 ? 'X' : 'O');
		}
		else {
			cout << "GAME OVER. DRAW" << endl;
			lastWinner = "DRAW";
		}
	}
	
	public: string getLastWinner() { return lastWinner; }
	
	void printBoard() {
		for(int i = 0; i < sizeof(board) / sizeof(board[0]); i++) {
			cout << HORIZONTAL_SEPARATOR << endl;
			for(int j = 0; j < sizeof(board[0]) / sizeof(board[0][0]); j++) {
				cout << VERTICAL_SEPARATOR << getSymbol(i,j);
			}
			cout << VERTICAL_SEPARATOR << endl;
		}
		cout << HORIZONTAL_SEPARATOR << endl;
	}
	
	bool tryMove(int x, int y, int p) {
		if(board[x][y] != 0)
			return false;
			
		board[x][y] = p;
		return true;
	}
	
	void resetBoard() {
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				board[i][j] = 0;
		move = 1;
	}
	
	int checkWin() {
		if(board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
			return board[0][0];
			
		if(board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0])
			return board[0][2];
			
		for(int i = 0; i < sizeof(board) / sizeof(board[0]); i++) {
			if(board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2])
				return board[i][0];
		}
		
		for(int j = 0; j < sizeof(board) / sizeof(board[0]); j++) {
			if(board[0][j] != 0 && board[0][j] == board[1][j] && board[1][j] == board[2][j])
				return board[0][j];
		}
		
		return 0;
	}
	
	char getSymbol(int x, int y) {
		switch(board[x][y]) {
			case 1: return 'X';
			case 2: return 'O';
			default: return ' ';
		}
	}
};
