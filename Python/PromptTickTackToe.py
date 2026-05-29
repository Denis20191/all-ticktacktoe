import numpy as np

horizontalSeparator = "-------"
verticalSeparator = "|"
board = np.array([[0, 0, 0], [0, 0, 0], [0, 0, 0]])
move = 1
lastWinner = "NONE"

def customizeSeparators(horizontal: str, vertical: str):
    """Initialize the game with custom separators"""
    global horizontalSeparator, verticalSeparator
    horizontalSeparator = horizontal
    verticalSeparator = vertical

def startGame():
    """
    Initialize a game of TickTackToe
    Uses input() for user input.
    """
    global move, lastWinner
    resetBoard()

    win = 0

    while move <= 9:
        print("=== MOVE " + str(move) + " ===")
        print("Player " + ("X" if move % 2 != 0 else "O"))
        printBoard()

        while True:
            try:
                x = int(input("Enter X coordinate: "))
                if x < 0 or x > 2:
                    print("Invalid input. Try again.")
                    continue

                y = int(input("Enter Y coordinate: "))
                if y < 0 or y > 2:
                    print("Invalid input. Try again.")
                    continue

                if tryMove(x, y, 1 if move % 2 != 0 else 2):
                    break
                else:
                    print("Space already occupied")
            except ValueError:
                print("Invalid input. Try again.")

        win = checkWin()
        if win != 0:
            break

        move += 1

    printBoard()

    if win != 0:
        print("WINNER: Player " + ("X" if win == 1 else "O"))
        lastWinner = "X" if win == 1 else "O"
    else:
        print("GAME OVER. DRAW")
        lastWinner = "DRAW"

def printBoard():
    """
    Prints the board.
    On the top and bottom of cells there's HORIZONTAL_SEPARATOR
    Between cells there's VERTICAL_SEPARATOR;
    """
    for i in range(len(board)):
        print(horizontalSeparator)
        for j in range(len(board[i])):
            print(verticalSeparator + getSymbol(i, j), end="")
        print(verticalSeparator)
    print(horizontalSeparator)

def tryMove(x: int, y: int, p: int) -> bool:
    """
    Attempt to make a move.
    If the cell is empty, assign player value to the cell
    :param x: X coordinate
    :param y: Y coordinate
    :param p: Player value (1 for X, 2 for O)
    :return: If the move was successful or not
    """
    if board[x][y] != 0:
        return False

    board[x][y] = p
    return True

def resetBoard():
    """Resets the board (all cells 0) and the move counter (to 1)"""
    global board, move
    board = np.array([[0, 0, 0], [0, 0, 0], [0, 0, 0]])
    move = 1

def checkWin() -> int:
    """Checks every cross, collumn and row combination for possible win"""
    if board[0][0] == board[1][1] == board[2][2] != 0:
        return board[0][0]

    if board[0][2] == board[1][1] == board[2][0] != 0:
        return board[0][2]

    for i in range(len(board)):
        if board[i][0] == board[i][1] == board[i][2] != 0:
            return board[i][0]

    for j in range(len(board)):
        if board[0][j] == board[1][j] == board[2][j] != 0:
            return board[0][j]

    return 0

def getSymbol(x: int, y: int):
    """
    Helper method
    :param x: X coordinate on board
    :param y: Y coordinate on board
    :return: Player symbol at coordinates X,Y
    """
    if board[x][y] == 0:
        return " "
    elif board[x][y] == 1:
        return "X"
    else:
        return "O"